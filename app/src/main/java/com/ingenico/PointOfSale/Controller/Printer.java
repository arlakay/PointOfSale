package com.ingenico.PointOfSale.Controller;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ingenico.PointOfSale.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Administrator-Handy on 10/25/2016.
 */

public class Printer {
    private Context context;

    // android built in classes for bluetooth operations
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothSocket mmSocket;
    private BluetoothDevice mmDevice;

    // needed for communication to bluetooth device / network
    private OutputStream mmOutputStream;
    private InputStream mmInputStream;
    private Thread workerThread;

    private byte[] readBuffer;
    private int readBufferPosition;
    private volatile boolean stopWorker;

    public Printer(Context mContext){
        context = mContext;
    }

    public void openBluetoothConnection(){
        try{
            // more codes will be here
            findBT();
            openBT();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void closeBluetoothConnection(){
        try {
            try {
                stopWorker = true;
                mmOutputStream.close();
                mmInputStream.close();
                mmSocket.close();
                Toast.makeText(context, "Bluetooth Closed", Toast.LENGTH_SHORT).show();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendDataString(String msg, byte[] alignment) throws IOException {
        try {
            // the text typed by the user
            mmOutputStream.write(alignment);
            msg += "\n";
            mmOutputStream.write(msg.getBytes());

            // tell the user data were sent
            Toast.makeText(context, "Data send", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendDataSpace() throws IOException {
        try {
            // the text typed by the user
            mmOutputStream.write(PrinterCommands.FEED_LINE);

            // tell the user data were sent
            Toast.makeText(context, "Data send", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendDataBitmap(Bitmap bitmap){
        printImage(
                arrayFromBitmap(
                        Bitmap.createScaledBitmap(
                                bitmap,
                                100,
                                100,
                                false
                        )
                )
        );
    }

    public void sendDataBitmapQR(Bitmap bitmap){
        printImage(
                arrayFromBitmap(
                        Bitmap.createScaledBitmap(
                                bitmap,
                                400,
                                400,
                                false
                        )
                )
        );
    }

    public void sendFullBitmap (Bitmap bitmap){
        int newWidth = bitmap.getWidth();
        int newHeight = bitmap.getHeight();

        Bitmap firstBitmap = Bitmap.createBitmap(bitmap,0,0,newWidth,newWidth);

        int y = newHeight-newWidth;
        Bitmap secondBitmap = Bitmap.createBitmap(bitmap,0,newWidth,newWidth,y);

        sendDataBitmapSquare(firstBitmap,newBitmapSquare(secondBitmap));


    }

    public void sendDataBitmapSquare (Bitmap fBitmap, Bitmap sBitmap){
        printImageNotFeedPaper(
                arrayFromBitmap(
                        Bitmap.createScaledBitmap(
                                fBitmap,
                                400,
                                400,
                                false
                        )
                ),
                arrayFromBitmap(
                        Bitmap.createScaledBitmap(
                                sBitmap,
                                400,
                                400,
                                false
                        )
                )
        );
    }

    private Bitmap newBitmapSquare (Bitmap bmp){
        Bitmap newBitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getWidth(), bmp.getConfig());
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bmp, 0, 0, null);
        return newBitmap;
    }

    private void findBT(){
        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            if(mBluetoothAdapter == null) {
                Toast.makeText(context, "No bluetooth adapter available", Toast.LENGTH_SHORT).show();
            }
            if(!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                ((Activity)context).startActivityForResult(enableBluetooth, 0);
            }

            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            if(pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    // RPP300 is the name of the bluetooth printer device
                    // we got this name from the list of paired devices
                    if (device.getName().equals("AB-320M")) {
//                    if (device.getName().equals("XXZN15-09-0068")) {
//                    if (device.getName().equals("DPP-350")) {
                        mmDevice = device;
                        break;
                    }
                }
            }
            Toast.makeText(context, "Bluetooth device found.", Toast.LENGTH_SHORT).show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void openBT() throws IOException {
        try {
            // Standard SerialPortService ID
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
            mmSocket.connect();
            mmOutputStream = mmSocket.getOutputStream();
            mmInputStream = mmSocket.getInputStream();

            beginListenForData();

            Toast.makeText(context, "Bluetooth Opened", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void beginListenForData() {
        try {
            final Handler handler = new Handler();
            // this is the ASCII code for a newline character
            final byte delimiter = 10;

            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];

            workerThread = new Thread(new Runnable() {
                public void run() {
                    while (!Thread.currentThread().isInterrupted() && !stopWorker) {
                        try {
                            int bytesAvailable = mmInputStream.available();
                            if (bytesAvailable > 0) {
                                byte[] packetBytes = new byte[bytesAvailable];
                                mmInputStream.read(packetBytes);
                                for (int i = 0; i < bytesAvailable; i++) {
                                    byte b = packetBytes[i];
                                    if (b == delimiter) {
                                        byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(
                                                readBuffer, 0,
                                                encodedBytes, 0,
                                                encodedBytes.length
                                        );
                                        // specify US-ASCII encoding
                                        final String data = new String(encodedBytes, "US-ASCII");
                                        readBufferPosition = 0;

                                        // tell the user data were sent to bluetooth printer device
                                        handler.post(new Runnable() {
                                            public void run() {
                                                Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } else {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                            }
                        } catch (IOException ex) {
                            stopWorker = true;
                        }
                    }
                }
            });
            workerThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void printImageNotFeedPaper(int[][] fPixels, int[][] sPixels){
        try{
            mmOutputStream.write(PrinterCommands.SET_LINE_SPACING_24);
            mmOutputStream.write(PrinterCommands.SET_ALIGNMENT_CENTERED);
            for (int i = 0; i < fPixels.length; i+=24) {
                mmOutputStream.write(PrinterCommands.SELECT_BIT_IMAGE_MODE);
                mmOutputStream.write(new byte[]{(byte)(0x00ff & fPixels[i].length)
                        , (byte)((0xff00 & fPixels[i].length) >> 8)});
                for (int j = 0; j < fPixels[i].length; j++) {
                    mmOutputStream.write(recollectSlice(i, j, fPixels));
                }
            }
            for (int i = 0; i < sPixels.length; i+=24) {
                mmOutputStream.write(PrinterCommands.SELECT_BIT_IMAGE_MODE);
                mmOutputStream.write(new byte[]{(byte)(0x00ff & sPixels[i].length)
                        , (byte)((0xff00 & sPixels[i].length) >> 8)});
                for (int j = 0; j < sPixels[i].length; j++) {
                    mmOutputStream.write(recollectSlice(i, j, sPixels));
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void printImage(int[][] pixels){
        try{
            mmOutputStream.write(PrinterCommands.SET_LINE_SPACING_24);
            mmOutputStream.write(PrinterCommands.SET_ALIGNMENT_CENTERED);
            for (int i = 0; i < pixels.length; i+=24) {
                mmOutputStream.write(PrinterCommands.SELECT_BIT_IMAGE_MODE);
                mmOutputStream.write(new byte[]{(byte)(0x00ff & pixels[i].length)
                        , (byte)((0xff00 & pixels[i].length) >> 8)});
                for (int j = 0; j < pixels[i].length; j++) {
                    mmOutputStream.write(recollectSlice(i, j, pixels));
                }
//                mmOutputStream.write(PrinterCommands.FEED_LINE);
            }
            mmOutputStream.write(PrinterCommands.SET_LINE_SPACING_30);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private byte[] recollectSlice(int y, int x, int[][] img) {
        byte[] slices = new byte[] {0, 0, 0};
        for (int yy = y, i = 0; yy < y + 24 && i < 3; yy += 8, i++) {
            byte slice = 0;
            for (int b = 0; b < 8; b++) {
                int yyy = yy + b;
                if (yyy >= img.length) {
                    continue;
                }
                int col = img[yyy][x];
                boolean v = shouldPrintColor(col);
                slice |= (byte) ((v ? 1 : 0) << (7 - b));
            }
            slices[i] = slice;
        }
        return slices;
    }

    private boolean shouldPrintColor(int col) {
        // originally 127, but i need only black or white
        // so i put max amount 255, we cant print gray color
        final int threshold = 255;
        int a, r, g, b, luminance;
        a = (col >> 24) & 0xff;
        if (a != 0xff) {// Ignore transparencies
            return false;
        }
        r = (col >> 16) & 0xff;
        g = (col >> 8) & 0xff;
        b = col & 0xff;

        luminance = (int) (0.299 * r + 0.587 * g + 0.114 * b);

        return luminance < threshold;
    }

    public static Bitmap bitmapFromArray(int[][] pixels2d){
        int width = pixels2d.length;
        int height = pixels2d[0].length;
        int[] pixels = new int[width * height];
        int pixelsIndex = 0;
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                pixels[pixelsIndex] = pixels2d[i][j];
                pixelsIndex ++;
            }
        }
        return Bitmap.createBitmap(pixels, width, height, Bitmap.Config.ARGB_8888);
    }

    public static int[][] arrayFromBitmap(Bitmap source){
        int width = source.getWidth();
        int height = source.getHeight();
        int[][] result = new int[width][height];
        int[] pixels = new int[width*height];
        source.getPixels(pixels, 0, width, 0, 0, width, height);
        int pixelsIndex = 0;
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                result[i][j] =  pixels[pixelsIndex];
                pixelsIndex++;
            }
        }
        return result;
    }

    public void printQrCodeInStruck(String transaction_id, String total_amount,
                                    String merchant_name, String store_name,
                                    String total_point_acquired, String flag){

        String value =
                String.valueOf(
                    jsonObject(
                            transaction_id,
                            total_amount,
                            merchant_name,
                            store_name,
                            total_point_acquired,
                            flag
                    )
                )
                ;

        try {
            Bitmap bmp = textToImageEncode(value);
            sendDataBitmapQR(bmp);
            sendDataString("Please Scan QR for Point"
                    ,PrinterCommands.SET_ALIGNMENT_CENTERED);
            sendDataSpace();
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

    }

    private JSONObject jsonObject(String transaction_id, String total_amount,
                                  String merchant_name, String store_name,
                                  String total_point_acquired, String flag){

        JSONObject obj = new JSONObject();
        try {
            obj.put("flag",flag);
            obj.put("transaction_id",transaction_id);
            obj.put("total_amount",total_amount);
            obj.put("merchant_name",merchant_name);
            obj.put("store_name",store_name);
            obj.put("total_point_acquired",total_point_acquired);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private Bitmap textToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    200, 200, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        context.getResources().getColor(R.color.QRCodeBlackColor):context.getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 200, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }


}

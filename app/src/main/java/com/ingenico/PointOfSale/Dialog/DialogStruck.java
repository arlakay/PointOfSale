package com.ingenico.PointOfSale.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Controller.PrinterCommands;
import com.ingenico.PointOfSale.R;
import com.ingenico.PointOfSale.ZoneActivity.PaymentSuccess;
import com.ingenico.pclservice.PclService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator-Handy on 8/17/2016.
 */
public class DialogStruck extends DialogFragment {

    private String transaction_id = "";
    private String string_image_struck = "";
    private String test_string_struck = "";
    private boolean mServiceStarted;

    public void setTransactionId (String t_id){this.transaction_id = t_id;}
    public void setStringImageStruck (String string_image){this.string_image_struck = string_image;}
    public void setStringStruck (String string_struck){this.test_string_struck = string_struck;}

    private ImageView imageViewDialogStruckClose, imageViewDialogStruck;
    private ImageView buttonDialogStruckPrintReceipt;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View RootView=inflater.inflate(R.layout.dialog_struck_icmp, null);

        imageViewDialogStruckClose = (ImageView) RootView.findViewById(R.id.imageViewDialogStruckClose);
        imageViewDialogStruck = (ImageView) RootView.findViewById(R.id.imageViewDialogStruck);
        buttonDialogStruckPrintReceipt = (ImageView) RootView.findViewById(R.id.buttonDialogStruckPrintReceipt);


        final Bitmap bitmap;
        byte[] byteArrayBitmap = decodeImage(string_image_struck);
        bitmap = BitmapFactory.decodeByteArray(byteArrayBitmap,0,byteArrayBitmap.length);

        imageViewDialogStruck.setImageBitmap(bitmap);

        ((CashRegisterActivity)getActivity()).image.saveToSDCard(transaction_id,string_image_struck);

        //loadImageFromPath(Declaration.IMAGE_OUTPUT_PATH+transaction_id,imageViewDialogStruck);

        mServiceStarted = true;

        imageViewDialogStruck.buildDrawingCache();
        imageViewDialogStruck.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        imageViewDialogStruck.layout(0, 0, imageViewDialogStruck.getMeasuredWidth(), imageViewDialogStruck.getMeasuredHeight());

        Bitmap bmap = imageViewDialogStruck.getDrawingCache();

        Drawable d = imageViewDialogStruck.getDrawable();
        Bitmap bitmap1= ((BitmapDrawable) d).getBitmap();
//        File file = savebitmap(transaction_id);

//        saveStruck(Declaration.IMAGE_OUTPUT_PATH+transaction_id+".png",bitmap1);

        imageViewDialogStruckClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CashRegisterActivity)getActivity()).printer.closeBluetoothConnection();
                deleteStruck(Declaration.IMAGE_OUTPUT_PATH+transaction_id+".png");
                startPclService();
//                ((CashRegisterActivity)getActivity()).initService();
                getDialog().dismiss();
                Intent mIntent = new Intent(((CashRegisterActivity)getActivity()), PaymentSuccess.class);
                mIntent.putExtra("transaction_id",transaction_id);
                mIntent.putExtra("total_change", "0");
                startActivity(mIntent);
                ((CashRegisterActivity)getActivity()).finish();
            }
        });



        buttonDialogStruckPrintReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((CashRegisterActivity)getActivity()).releaseService();
                stopPclService();
                ((CashRegisterActivity)getActivity()).printer.openBluetoothConnection();
                Log.e("error",String.valueOf(loadBitmap(Declaration.IMAGE_OUTPUT_PATH+transaction_id+".png"))+"");
                if (!String.valueOf(loadBitmap(Declaration.IMAGE_OUTPUT_PATH+transaction_id+".png")).equals("null")){
                    ((CashRegisterActivity)getActivity()).printer.sendFullBitmap(
                            loadBitmap(Declaration.IMAGE_OUTPUT_PATH+transaction_id+".png")
                    );
                }
            }
        });

        setCancelable(false);
        builder.setView(RootView);
        return builder.create();
    }

    public static byte[] decodeImage (String imageDataString){
        return Base64.decode(imageDataString, Base64.DEFAULT);
    }

    private void saveStruck(String filename, Bitmap bmp){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File savebitmap(String filename) {
        OutputStream outStream = null;

        File file = new File(filename + ".png");
        if (file.exists()) {
            file.delete();
            file = new File(Declaration.IMAGE_OUTPUT_PATH, filename + ".png");
            Log.e("file exist", "" + file + ",Bitmap= " + filename);
        }
        try {
            // make a new bitmap from your file
            Bitmap bitmap = BitmapFactory.decodeFile(file.getName());

            outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("file", "" + file);
        return file;

    }

    private void deleteStruck(String selectedFilePath){
        File file = new File(selectedFilePath);
        boolean deleted = file.delete();
    }

    private static void loadImageFromPath(String path, ImageView imageView){
        Log.d("Load Path",path);
        File imgFile = new  File(path);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
        }
    }

    private static Bitmap loadBitmap (String path){
        Log.d("Load Path",path);
        Bitmap myBitmap = null;
        File imgFile = new  File(path);
        if(imgFile.exists()){
            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        return myBitmap;
    }

    private void startPclService() {
        if (!mServiceStarted)
        {
            Intent i = new Intent(((CashRegisterActivity)getActivity()), PclService.class);
            i.putExtra("PACKAGE_NAME", "com.ingenico.PointOfSale");
            i.putExtra("FILE_NAME", "pairing_addr.txt");

            if (((CashRegisterActivity)getActivity()).startService(i) != null)
                mServiceStarted = true;
        }
    }

    private void stopPclService() {
        if (mServiceStarted)
        {
            Intent i = new Intent(((CashRegisterActivity)getActivity()), PclService.class);
            if (((CashRegisterActivity)getActivity()).stopService(i))
                mServiceStarted = false;
        }
    }


}

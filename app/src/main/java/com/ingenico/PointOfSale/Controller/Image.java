package com.ingenico.PointOfSale.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Administrator-Handy on 4/20/2016.
 */
public class Image {

    Context mContext;

    public Image(Context context){
        mContext = context;
        if(createFolder(Declaration.IMAGE_OUTPUT_PATH)) {
            Log.d(this.getClass().getSimpleName(), "mkdir():true -> The directory has been created.");
        } else {
            if(createFolder(Declaration.IMAGE_OUTPUT_PATH)) {
                Log.d(this.getClass().getSimpleName(), "mkdir():false -> Otherwise. (Already exists)");
            }
        }
    }

    private boolean createFolder(String newDir) {
        Log.d("createFolder()", "createFolder(" + newDir + ")");
        File file = new File(newDir);

        if(file.exists()) {
            Log.d("createFolder()", "return true; (already exists)");
            return true;
        } else {
            boolean bRet = file.mkdir();
            if( bRet ) {
                Log.d("createFolder()", "return true; (create folder)");
            } else {
                Log.d("createFolder()", "return false; (error)");
            }
            return bRet;
        }
    }

    public static String encodeImage (byte[] ImageByteArray){
        return Base64.encodeToString(ImageByteArray,Base64.DEFAULT);
    }

    public static byte[] decodeImage (String imageDataString){
        return Base64.decode(imageDataString,Base64.DEFAULT);
    }

    public void saveToSDCard (String imageName ,String imageString){
        byte[] byteImage = decodeImage(imageString);
        Bitmap bitmapImage = BitmapFactory.decodeByteArray(byteImage,0,byteImage.length);
        saveToCacheFile(imageName,bitmapImage);

    }

    public static void saveBitmap(String filename,Bitmap bmp) {
        try {
            FileOutputStream out = new FileOutputStream(filename);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveToCacheFile(String imageName,Bitmap bmp) {
        saveBitmap(getCacheFilename(imageName),bmp);
    }

    public static String getCacheFilename(String imageName) {
        File file = new File(Declaration.IMAGE_OUTPUT_PATH);
        return file.getAbsolutePath()+ "/" + imageName +".png";
    }

    public static void loadImageFromPath(String path, ImageView imageView){
        Log.d("Load Path",path);
        File imgFile = new  File(path);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
        }
    }

    public void loadImageFromPathParamaterize(String path, ImageView imageView){
        Log.d("Load Path",path);
        File imgFile = new  File(path);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
        }
    }

}

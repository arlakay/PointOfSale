package com.ingenico.PointOfSale.Controller;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

/**
 * Created by Administrator-Handy on 4/13/2016.
 */

public class ServiceLogger extends ContextWrapper {

    public static final int LEVEL_OFF   = 0;
    public static final int LEVEL_FATAL = 1;
    public static final int LEVEL_ERROR = 2;
    public static final int LEVEL_WARN  = 3;
    public static final int LEVEL_INFO  = 4;
    public static final int LEVEL_DEBUG = 5;
    public static final int LEVEL_TRACE = 6;

    protected static int LOG_LEVEL = Declaration.LOG_OUTPUT_LEVEL;
    protected static String PATH  = Declaration.LOG_OUTPUT_PATH;
    protected static boolean LOGCAT_ENABLE = Declaration.LOG_OUTPUT_TO_LOGCAT;   // Write LogCat (true: write to LogCat, false: no write to LogCat)

    /**
     *  Constructor
     * @param base
     */
    public ServiceLogger(Context base) {
        super(base);
        Log.d(this.getClass().getSimpleName(), "Logger()");

        if(createFolder(PATH)) {
            if(LOGCAT_ENABLE) {
                Log.d(this.getClass().getSimpleName(), "mkdir():true -> The directory has been created.");
            }
        } else {
            if(LOGCAT_ENABLE) {
                Log.d(this.getClass().getSimpleName(), "mkdir():false -> Otherwise. (Already exists)");
            }
        }
    }

    /**
     * Create Folder
     * @param newDir    create directory path
     * @return
     */
    private boolean createFolder(String newDir) {
        Log.d("createFolder()", "createFolder(" + newDir + ")");
        File file = new File(newDir);

        if( file.exists() ) {
            Log.d("createFolder()", "return true; (already exists)");
            return true;
        } else {
            boolean bRet = file.mkdirs();
            if( bRet ) {
                Log.d("createFolder()", "return true; (create folder)");
            } else {
                Log.d("createFolder()", "return false; (error)");
            }
            return bRet;
        }
    }


    /**
     * Mengubah Log Level
     * @param level
     */
    public static void setLogLevel(int level){
        LOG_LEVEL = level;
    }

    /**
     * Set LogCat Enable Flag
     * @param b : 0=no write to LogCat, 1=write to LogCat
     */
    public static void setLogCatEnable(boolean b) {
        LOGCAT_ENABLE = b;
    }

    /**
     * Set Log File Path
     * @param path : New Log File Path
     */
    public static void setLogFilePath(String path) {
        PATH = path;
    }


    /**
     * FATAL
     */
    public void addFatal(String title, String mess){
        StackTraceElement element = null;
        element = new Throwable().getStackTrace()[1];
        Log.e(title, mess);
        if(LOG_LEVEL >= LEVEL_FATAL){ addLog("Fatal", title, mess, element); }
    }
    /**
     * ERROR
     */
    public void addError(String title, String mess){
        StackTraceElement element = null;
        element = new Throwable().getStackTrace()[1];
        Log.e(title, mess);
        if(LOG_LEVEL >= LEVEL_ERROR){ addLog("Error", title, mess, element); }
    }
    /**
     * WARN
     */
    public void addWarn(String title, String mess){
        StackTraceElement element = null;
        element = new Throwable().getStackTrace()[1];
        Log.w(title, mess);
        if(LOG_LEVEL >= LEVEL_WARN){ addLog("Warn", title, mess, element); }
    }
    /**
     * INFO
     */
    public void addInfo(String title, String mess){
        StackTraceElement element = null;
        element = new Throwable().getStackTrace()[1];
        Log.i(title, mess);
        if(LOG_LEVEL >= LEVEL_INFO){ addLog("Info", title, mess, element); }
    }
    /**
     * DEBUG
     */
    public void addDebug(String title, String mess){
        StackTraceElement element = null;
        try{
            element = new Throwable().getStackTrace()[1];
        } catch (Exception e) {
            Log.d(this.getClass().getSimpleName(), "catch(Exception) : " + e.toString());
        }
        Log.d(title, mess);
        if(LOG_LEVEL >= LEVEL_DEBUG){ addLog("Debug", title, mess, element); }
    }
    /**
     * TRACE
     */
    public void addTrace(String title, String mess){
        StackTraceElement element = null;
        element = new Throwable().getStackTrace()[1];
        Log.v(title, mess);
        if(LOG_LEVEL >= LEVEL_TRACE){ addLog("Trace", title, mess, element); }
    }

    /**
     * Kirim Log File
     */
    public void sendLogFile(String server, String port, String id, String pass){

    }


    /**
     * Tambah Log
     * @param title
     * @param mess
     */
    private void addLog(String level, String title, String mess, StackTraceElement element){

        StringBuilder builder = new StringBuilder();
        builder.append(" >>> ");
        builder.append(title);
        builder.append(" (");
        builder.append(level);
        builder.append(") ");
        builder.append(getNowTimeString());
        builder.append(" :: ");
        builder.append("\n");
        builder.append(mess);
        builder.append("\n");
        String output = builder.toString();
        String filename = getLogFileName();

        try {
            BufferedWriter stream = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(new File(filename), true)) );

            try {
                stream.write(output);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return String Log file name
     */
    protected String getLogFileName(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        String appname;
        appname = "PointOfSale";
        return PATH + appname + "_" + format(day)+"-"+ format(month) + "_log.txt";
    }

    /**
     * get Now Date
     * @return Date(yyyy/MM/dd hh:mm:ss)
     */
    protected String getNowTimeString() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        StringBuilder builder = new StringBuilder();

        builder.append(year);
        builder.append("/");
        builder.append(this.format(month));
        builder.append("/");
        builder.append(this.format(day));
        builder.append(" ");
        builder.append(this.format(hour));
        builder.append(":");
        builder.append(this.format(minute));
        builder.append(":");
        builder.append(this.format(second));

        return builder.toString();
    }

    /**
     * Balikan Nomer
     * @param value angka
     * @return
     */
    protected String format(int value) {
        StringBuilder builder = new StringBuilder();

        if (value < 10) {
            builder.append("0");
        }

        builder.append(value);
        return builder.toString();
    }
}

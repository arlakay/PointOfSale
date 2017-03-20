package com.ingenico.PointOfSale.Controller;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;


import com.ingenico.PointOfSale.ZoneActivity.LoginActivity;
import com.ingenico.PointOfSale.ZoneActivity.StaffDashboardActivity;

import java.util.HashMap;


/**
 * Created by Administrator-Handy on 4/19/2016.
 */
public class SessionManager {
    /**
     * Logcat tag
     */
    private static String TAG = SessionManager.class.getSimpleName();
    /**
     * Share pref
     */
    SharedPreferences pref;
    Editor editor;
    Context _context;
    /**
     * Service logger
     */
    ServiceLogger logger;
    /**
     * Share pref mode
     */
    int PRIVATE_MODE = 0;

    /**
     * Share pref name file
     */
    private static final String PREF_NAME = "PointOfSale";

    private static final String KEY_IS_MERCHANT_LOGGEDIN = "isMerchantLoggedIn";

    private static final String KEY_IS_OUTLET_LOGGEDIN = "isOutletLoggedIn";

    private static final String KEY_IS_CASHIER_LOGGEDIN = "isCashierLoggedIn";

    private static final String KEY_IS_DRAWER_LOGGEDIN = "isDrawerLoggedIn";

    private static final String KEY_IS_CONNECTED = "isConnected";

    /**
     * Merchant username (make variable public to access from outside)
     */
    public static final String KEY_MERCHANT_USERNAME = "key_merchant_username";
    /**
     * Merchant password (make variable public to access from outside)
     */
    public static final String KEY_MERCHANT_PASSWORD = "key_merchant_password";
    /**
     * Cashier username (make variable public to access from outside)
     */
    public static final String KEY_CASHIER_USERNAME_ID = "key_cashier_username_id";
    /**
     * Cashier username (make variable public to access from outside)
     */
    public static final String KEY_CASHIER_PASSWORD = "key_cashier_password";
    /**
     * Cashier username (make variable public to access from outside)
     */
    public static final String KEY_CASHIER_USERNAME = "key_cashier_username";
    /**
     * Access code (make variable public to access from outside)
     */
    public static final String KEY_ACCESS_CODE = "key_access_code";
    /**
     * Outlet ID (make variable public to access from outside)
     */
    public static final String KEY_OUTLET_ID = "key_outlet_id";
    /**
     * Outlet ID (make variable public to access from outside)
     */
    public static final String KEY_OUTLET_PASS = "key_outlet_pass";
    /**
     * Outlet name (make variable public to access from outside)
     */
    public static final String KEY_OUTLET_NAME = "key_outlet_name";

    public static final String KEY_TRANSACTION_ID = "key_transaction_id";

    public static final String KEY_TABLE_NUMBER = "key_table_number";

    public static final String KEY_ALREADY_CONNECTED = "key_already_connected";

    public SessionManager(Context context) {
        this._context = context;
        this.logger= new ServiceLogger(_context);
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    /**
     * For Session Merchant Login
     */
    public void createMerchantLoginSession(String id, String pass){
        /**
         * Storing login value as TRUE
         */
        editor.putBoolean(KEY_IS_MERCHANT_LOGGEDIN, true);

        /**
         * Storing username in pref
         */
        editor.putString(KEY_MERCHANT_USERNAME, id);

        /**
         * Storing password in pref
         */
        editor.putString(KEY_MERCHANT_PASSWORD, pass);

        /**
         * Commit change
         */
        logger.addInfo(TAG,"Session Data Modified");
        editor.commit();
    }

    /**
     * For Session Merchant Login
     */
    public void createOutletLoginSession (String id, String pass){
        /**
         * Storing login value as TRUE
         */
        editor.putBoolean(KEY_IS_OUTLET_LOGGEDIN, true);

        /**
         * Storing username in pref
         */
        editor.putString(KEY_OUTLET_ID, id);

        /**
         * Storing password in pref
         */
        editor.putString(KEY_OUTLET_PASS, pass);

        /**
         * Commit change
         */
        logger.addInfo(TAG,"Session Data Modified");
        editor.commit();
    }

    /**
     * For Session Merchant Login
     */
    public void createCashierLoginSession(String id, String pass){
        /**
         * Storing login value as TRUE
         */
        editor.putBoolean(KEY_IS_CASHIER_LOGGEDIN, true);

        /**
         * Storing username in pref
         */
        editor.putString(KEY_CASHIER_USERNAME_ID, id);

        /**
         * Storing username in pref
         */
        editor.putString(KEY_CASHIER_PASSWORD, pass);

        /**
         * Commit change
         */
        logger.addInfo(TAG,"Session Data Modified");
        editor.commit();
    }

    /**
     * For Session Merchant Login
     */
    public void setCashierUserID (String user_id){

        /**
         * Storing username in pref
         */
        editor.putString(KEY_CASHIER_USERNAME_ID, user_id);

        /**
         * Commit change
         */
        logger.addInfo(TAG,"Session Data Modified");
        editor.commit();
    }

    /**
     * For Session Merchant Login
     */
    public void setCashierUsername (String username){

        /**
         * Storing username in pref
         */
        editor.putString(KEY_CASHIER_USERNAME, username);

        /**
         * Commit change
         */
        logger.addInfo(TAG,"Session Data Modified");
        editor.commit();
    }

    /**
     * For session access code
     */
    public void setAccessCode(String accessCode){
        /**
         * Storing access code in pref
         */
        editor.putString(KEY_ACCESS_CODE, accessCode);
        /**
         * Commit change
         */
        logger.addInfo(TAG,"Session Data Modified");
        editor.commit();
    }

    /**
     * For session outlet ID
     */
    public void setOutletID(String outletID){
        /**
         * Storing outlet name in pref
         */
        editor.putString(KEY_OUTLET_ID, outletID);
        /**
         * Commit change
         */
        logger.addInfo(TAG,"Session Data Modified");
        editor.commit();
    }

    /**
     * For session outlet name
     */
    public void setOutletName(String outletName){
        /**
         * Storing outlet name in pref
         */
        editor.putString(KEY_OUTLET_NAME, outletName);
        /**
         * Commit change
         */
        logger.addInfo(TAG,"Session Data Modified");
        editor.commit();
    }

    public void setIsConnected(int connected){
        /**
         * Storing outlet name in pref
         */
        editor.putInt(KEY_IS_CONNECTED, connected);
        /**
         * Commit change
         */
        logger.addInfo(TAG,"Session Data Modified");
        editor.commit();
    }

    /**
     * For Merchant Check Login
     */
    public void checkMerchantLogin(){
        if(!this.isMerchantLoggedIn()){
            /**
             * User is not logged in redirect him to Login Activity
             */
            Intent i = new Intent(_context, LoginActivity.class);
            /**
             * Closing all the Activities
             */
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            /**
             * Add new Flag to start new Activity
             */
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            Toast.makeText(_context,
                    "Welcome You're Logging In!", Toast.LENGTH_LONG)
                    .show();
            /**
             * Staring Login Activity
             */
            _context.startActivity(i);
        }
    }

    /**
     * For Merchant Check Login
     */
    public void checkOutletLogin(){
        if(!this.isMerchantLoggedIn()){
            /**
             * User is not logged in redirect him to Login Activity
             */
            Intent i = new Intent(_context, LoginActivity.class);
            /**
             * Closing all the Activities
             */
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            /**
             * Add new Flag to start new Activity
             */
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            Toast.makeText(_context,
                    "Welcome You're Logging In!", Toast.LENGTH_LONG)
                    .show();
            /**
             * Staring Login Activity
             */
            _context.startActivity(i);
        }
    }


    /**
     * For Cashier Check Login
     */
    public void checkCashierLogin(){
        if(!this.isCashierLoggedIn()){
            /**
             * User is not logged in redirect him to Login Activity
             */
            Intent i = new Intent(_context, StaffDashboardActivity.class);
            /**
             * Closing all the Activities
             */
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            /**
             * Add new Flag to start new Activity
             */
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            Toast.makeText(_context,
                    "Welcome You're Logging In!", Toast.LENGTH_LONG)
                    .show();
            /**
             * Staring Login Activity
             */
            _context.startActivity(i);
        }
    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getSessionDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        /**
         * Get stored username in session data
         */
        user.put(KEY_MERCHANT_USERNAME, pref.getString(KEY_MERCHANT_USERNAME, "null data"));
        /**
         * Get stored password in session data
         */
        user.put(KEY_MERCHANT_USERNAME, pref.getString(KEY_MERCHANT_USERNAME, "null data"));
        /**
         * Get stored username in session data
         */
        user.put(KEY_CASHIER_USERNAME_ID, pref.getString(KEY_CASHIER_USERNAME_ID, "null data"));
        /**
         * Get stored username in session data
         */
        user.put(KEY_CASHIER_USERNAME, pref.getString(KEY_CASHIER_USERNAME, "null data"));
        /**
         * Get access code in session data
         */
        user.put(KEY_ACCESS_CODE, pref.getString(KEY_ACCESS_CODE, "null data"));
        /**
         * Get outlet id in session data
         */
        user.put(KEY_OUTLET_ID, pref.getString(KEY_OUTLET_ID, "null data"));
        /**
         * Get outlet name in session data
         */
        user.put(KEY_OUTLET_NAME, pref.getString(KEY_OUTLET_NAME, "null data"));

        return user;
    }

    /**
     * Logout user and remove session
     */
    public void logoutMerchant(){
        /**
         * Clearing all data from Shared Preferences
         */
        editor.clear();
        editor.commit();
        logger.addInfo(TAG,"User Logout!");
        /**
         * After logout redirect user to Login Activity
         */
        Intent i;
        i = new Intent(_context, LoginActivity.class);
        /**
         * Closing all the Activities
         */
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        /**
         * Add new Flag to start new Activity
         */
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        /**
         * Staring Login Activity
         */
        _context.startActivity(i);

    }

    public void clearSessionManager (){
        /**
         * Clearing all data from Shared Preferences
         */
        editor.clear();
        editor.commit();
        logger.addInfo(TAG,"Session Clear!");
    }

    /**
     * Logout user and remove session
     */
    public void logoutCashier(){
        /**
         * After logout redirect user to Staff Dashboard Activity
         */
        Intent i;
        i = new Intent(_context, StaffDashboardActivity.class);
        /**
         * Closing all the Activities
         */
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        /**
         * Add new Flag to start new Activity
         */
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        /**
         * Staring Staff Dashboard Activity
         */
        _context.startActivity(i);
    }

    public void setKeyIsDrawerLoggedin (boolean drawerLoggedin){
        editor.putBoolean(KEY_IS_DRAWER_LOGGEDIN, drawerLoggedin);
        logger.addInfo(TAG,"Session Data Modified");
        editor.commit();
    }

    public void setKeyIsCashierLoggedin (boolean cashierLoggedin){
        editor.putBoolean(KEY_IS_CASHIER_LOGGEDIN, cashierLoggedin);
        logger.addInfo(TAG,"Session Data Modified");
        editor.commit();
    }

    public void setKeyIsOutletLoggedin (boolean outletLoggedin){
        editor.putBoolean(KEY_IS_OUTLET_LOGGEDIN, outletLoggedin);
        logger.addInfo(TAG,"Session Data Modified");
        editor.commit();
    }

    public void setKeyIsMerchantLoggedin (boolean merchantLoggedin){
        editor.putBoolean(KEY_IS_MERCHANT_LOGGEDIN, merchantLoggedin);
        logger.addInfo(TAG,"Session Data Modified");
        editor.commit();
    }

    public void setTransactionId (String transactionId){
        editor.putString(KEY_TRANSACTION_ID,transactionId);
        editor.commit();
        logger.addInfo(TAG,"Transaction ID Changed to : " + transactionId);
    }

    public void setTableNumber (String tableNumber){
        editor.putString(KEY_TABLE_NUMBER,tableNumber);
        editor.commit();
        logger.addInfo(TAG,"Table Number Changed to : " + tableNumber);
    }

    public void setAlreadyConnected (String alreadyConnected){
        editor.putString(KEY_ALREADY_CONNECTED, alreadyConnected);
        editor.commit();
        logger.addInfo(TAG,"Already Connected: " + alreadyConnected);
    }

    public boolean isMerchantLoggedIn(){
        return pref.getBoolean(KEY_IS_MERCHANT_LOGGEDIN, false);
    }

    public boolean isOutletLoggedIn(){
        return pref.getBoolean(KEY_IS_OUTLET_LOGGEDIN, false);
    }

    public boolean isCashierLoggedIn(){
        return pref.getBoolean(KEY_IS_CASHIER_LOGGEDIN, false);
    }

    public boolean isDrawerLoggedIn(){
        return pref.getBoolean(KEY_IS_DRAWER_LOGGEDIN, false);
    }

    public String getKeyAccessCode(){
        return pref.getString(KEY_ACCESS_CODE, "null data");
    }

    public String getTansactionID(){return pref.getString(KEY_TRANSACTION_ID, "null data");}

    public String getTableNumber(){return pref.getString(KEY_TABLE_NUMBER, "null data");}

    public String getAlreadyConnected(){return pref.getString(KEY_ALREADY_CONNECTED, "null data");}

    public int isConnected(){return pref.getInt(KEY_IS_CONNECTED, 0);}
}
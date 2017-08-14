package com.ingenico.PointOfSale.ZoneActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.AppController;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.ModelRealm.Login;
import com.ingenico.PointOfSale.ModelRealm.Outlet;
import com.ingenico.PointOfSale.PointOfSaleActivity;
import com.ingenico.PointOfSale.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 4/13/2016.
 */
public class LoginActivity extends PointOfSaleActivity {
    /**
     * Declare in xml
     */
    EditText editTextUsername, editTextPassword;
    TextView textViewForgotPassword, textViewRegister;
    Button buttonEnter;

    String username;
    String password;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        /**
         * Declare in xml
         */
        editTextUsername = (EditText)findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        textViewForgotPassword = (TextView)findViewById(R.id.textViewForgotPassword);
        textViewRegister = (TextView)findViewById(R.id.textViewRegister);
        buttonEnter = (Button)findViewById(R.id.buttonEnter);

        /**
         * Event button enter
         */
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Validation connectivity internet
                 */
                ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
                if (netInfo == null) {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Permission")
                            .setMessage("This application need your internet connectivity, please turn on your internet.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                                }
                            }).create().show();
                } else {
                    username = editTextUsername.getText().toString();
                    password = editTextPassword.getText().toString();
                    /**
                     * Validation credential
                     */
                     if (username.trim().length() > 0 && password.trim().length() > 0) {
                        logger.addInfo("User Login", username+" | "+ password);
                        sendLoginTrace(username,password,getMacAddress());
                    }else {
                        showAlertDialogNullEvent(LoginActivity.this,"Error","Please insert your username and password !");
                    }
                }
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            userInterface.hideSystemUI();
        }
    }

    @Override
    protected void onDestroy() {
        logger.addInfo("Destroy Activity","Success");
        super.onDestroy();
    }

    /**
     * Send login trace to API
     */
    private void sendLoginTrace(final String username, final String password, final String mac_address) {
        String tag_string_req = "send_API";
        /**
         * Show progress dialog
         */
        showProgressDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                Declaration.URL_LOGIN, new Response.Listener<String>() {
            /**
             * Response volley from hit API
             */
            @Override
            public void onResponse(String response) {
                logger.addInfo("Response API",response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    String code = jObj.getString("code");
                    String msg = jObj.getString("msg");
                    /**
                     * Check code
                     */
                    if (code.equals("0007")){
                        Toast.makeText(getApplicationContext(),
                                msg, Toast.LENGTH_LONG).show();
                        /**
                         * Save access code in session
                         */
                        String accessCode = jObj.getString("kode_akses");
                        sessionManager.setAccessCode(accessCode);
                        logger.addInfo("Access Code",accessCode);
                        /**
                         * Validate access code and process the json object
                         */
                        validateAccessCode(accessCode,jObj);


                    } else {
                        Toast.makeText(getApplicationContext(),
                                msg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    /**
                     * JSON error
                     */
                    e.printStackTrace();
                }
                /**
                 * Hide progress dialog
                 */
                hideProgressDialog();

            }
        }, new Response.ErrorListener() {
            /**
             * Volley response error
             */
            @Override
            public void onErrorResponse(VolleyError error) {
                logger.addInfo("VolleyError API",error.toString());
                /**
                 * Show progress dialog
                 */
                hideProgressDialog();
            }
        }) {
            /**
             * Posting parameter in url
             */
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("login", "1");
                params.put("username", username);
                params.put("password", password);
                params.put("mac_address", mac_address);
                logger.addInfo("Parameter in Volley",params.toString());
                return params;
            }

        };

        strReq.setRetryPolicy(new DefaultRetryPolicy(
                Declaration.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        /**
         * Adding request to request queue
         */
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    /**
     * Get mac address
     */
    private String getMacAddress(){
        String macAddress;
        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        macAddress = info.getMacAddress();
        return macAddress;
    }


    private void validateAccessCode (String accessCode, JSONObject jsonObject) {
        try {
            if (accessCode.equals("1")){                                                            /** User as Merchant */
                /**
                 * Create session merchant login
                 */
                sessionManager.createMerchantLoginSession(username, password);
                /**
                 * Populate all data to database
                 */
                populateLoginDataToDatabase(jsonObject);
                populateMerchantDataToDatabase(jsonObject);
                populateOutletDataToDatabase(jsonObject);
                populateServiceDataToDatabase(jsonObject);
                populateTaxDataToDatabase(jsonObject);
                populateDiscountMasterDataToDatabase(jsonObject);
                populateCategoryDataToDatabase(jsonObject);
                populateItemDataToDatabase(jsonObject);
                populateVariantDataToDatabase(jsonObject);
                populateModifierDetailDataToDatabase(jsonObject);
                populateModifierGroupDataToDatabase(jsonObject);
                /**
                 * Validate logo merchant updated or not
                 */
                if (jsonObject.getString("status_logo").equals("updated")){
                    /**
                     * Save merchant logo in sdcard
                     */
                    String stringImageLogo = jsonObject.getString("logo");
                    image.saveToSDCard("merchant_logo",stringImageLogo);
                    logger.addInfo("Merchant Logo","Saved");
                }
                /**
                 * Intent to Choice Outlet activity
                 */
                startActivity(new Intent(getApplicationContext(),ChoiceOutlet.class));
                finish();
            } else if (accessCode.equals("2")){                                                     /** User as Outlet */
                /**
                 * Create session merchant login
                 */
                sessionManager.createOutletLoginSession(username, password);
                /**
                 * Populate all data to database
                 */
                populateLoginDataToDatabase(jsonObject);
                populateMerchantDataToDatabase(jsonObject);
                populateOutletDataToDatabase(jsonObject);
                populateServiceDataToDatabase(jsonObject);
                populateTaxDataToDatabase(jsonObject);
                populateDiscountMasterDataToDatabase(jsonObject);
                populateCategoryDataToDatabase(jsonObject);
                populateItemDataToDatabase(jsonObject);
                populateVariantDataToDatabase(jsonObject);
                populateModifierDetailDataToDatabase(jsonObject);
                populateModifierGroupDataToDatabase(jsonObject);

                RealmResults<Login> realmResultsLogin = realm.
                        where(Login.class)
                        .equalTo("username",username)
                        .findAll();

                Login login = realmResultsLogin.first();
                String outletID = login.getOutletID();

                Log.e("LoginActivity", "Outlet login :" + outletID);

                RealmResults<Outlet> realmResultsOutlet = realm.
                        where(Outlet.class)
                        .equalTo("outletID",outletID)
                        .findAll();
                Outlet outlet = realmResultsOutlet.first();
                logger.addInfo("Outlet Name",outlet.getOutletName());
                sessionManager.setOutletName(outlet.getOutletName());
                sessionManager.setOutletID(outlet.getOutletID());
                /**
                 * prepare database for save order
                 */
                //prepareSaveOrderData(realm, "13");
                /**
                 * Intent to staff dashboard activity
                 */
                sessionManager.setKeyIsOutletLoggedin(true);

                /**
                 * Intent to staff dashboard activity
                 */
                startActivity(new Intent(getApplicationContext(),StaffDashboardActivity.class));
                finish();
            } else if (accessCode.equals("3")){                                                     /** User as cashier */
                /**
                 * Create session cashier login
                 */
                sessionManager.createCashierLoginSession(username, password);
                /**
                 * Populate all data to database
                 */
                populateLoginDataToDatabase(jsonObject);
                populateMerchantDataToDatabase(jsonObject);
                populateOutletDataToDatabase(jsonObject);
                populateServiceDataToDatabase(jsonObject);
                populateTaxDataToDatabase(jsonObject);
                populateDiscountMasterDataToDatabase(jsonObject);
                populateCategoryDataToDatabase(jsonObject);
                populateItemDataToDatabase(jsonObject);
                populateVariantDataToDatabase(jsonObject);
                populateModifierDetailDataToDatabase(jsonObject);
                populateModifierGroupDataToDatabase(jsonObject);

                RealmResults<Login> realmResultsLogin = realm.
                        where(Login.class)
                        .equalTo("username",username)
                        .findAll();

                Login login = realmResultsLogin.first();
                String outletID = login.getOutletID();

                Log.e("LoginActivity", "Outlet login :" + outletID);

                RealmResults<Outlet> realmResultsOutlet = realm.
                        where(Outlet.class)
                        .equalTo("outletID",outletID)
                        .findAll();
                Outlet outlet = realmResultsOutlet.first();
                logger.addInfo("Outlet Name",outlet.getOutletName());
                sessionManager.setCashierUserID(username);
                sessionManager.setCashierUsername(username);
                sessionManager.setKeyIsCashierLoggedin(true);
                sessionManager.setOutletID(outletID);
                /**
                 * Intent to staff dashboard activity
                 */
                startActivity(new Intent(getApplicationContext(),CashRegisterActivity.class));
                finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

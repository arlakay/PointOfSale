package com.ingenico.PointOfSale.ZoneActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Controller.Image;
import com.ingenico.PointOfSale.PointOfSaleActivity;
import com.ingenico.PointOfSale.ModelRealm.Outlet;
import com.ingenico.PointOfSale.ModelRealm.SaveOrder;
import com.ingenico.PointOfSale.R;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 4/20/2016.
 */
public class ChoiceOutlet extends PointOfSaleActivity implements AdapterView.OnItemSelectedListener {
    /**
     * Declare in xml
     */
    ImageView imageViewShopLogo;
    Spinner spinnerAllOutletName;
    Button buttonEnter;
    TextView textViewLogout;
    LinkedList<String> listAllOutlet = new LinkedList<>();
    String outletName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_outlet);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**
         * Declare in xml
         */
        textViewLogout = (TextView) findViewById(R.id.textViewLogout);
        imageViewShopLogo = (ImageView) findViewById(R.id.imageViewShopLogo);
        spinnerAllOutletName = (Spinner) findViewById(R.id.spinnerAllOutletName);
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        /**
         * Load merchant logo from sdcard
         */
        Image.loadImageFromPath(Declaration.IMAGE_OUTPUT_PATH+"merchant_logo.png",imageViewShopLogo);
        logger.addInfo("Merchant Logo","is Created");

        /**
         * Load outlet name from database
         */
        populateOutletDataToView(realm);

        /**
         * Handle button enter event
         */
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outletName = String.valueOf(spinnerAllOutletName.getSelectedItem());
                if (outletName.equals("Outlet Name")){
                    logger.addInfo("Outlet Option",outletName);
                    showAlertDialogNullEvent(ChoiceOutlet.this,getString(R.string.warning), getString(R.string.please_choose_your_outlet_correctly));
                } else {
                    RealmResults<Outlet> realmResultsOutlet = realm.
                            where(Outlet.class)
                            .equalTo("outletName",outletName)
                            .findAll();
                    Outlet outlet = realmResultsOutlet.first();
                    logger.addInfo("Outlet Name",outletName);
                    sessionManager.setOutletName(outletName);
                    sessionManager.setOutletID(outlet.getOutletID());
                    /**
                     * prepare database for save order
                     */
                    //prepareSaveOrderData(realm, "13");
                    /**
                     * Intent to staff dashboard activity
                     */
                    sessionManager.setKeyIsOutletLoggedin(true);
                    startActivity(new Intent(getApplicationContext(),StaffDashboardActivity.class));
                    finish();
                }
            }
        });

        textViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutAlertDialog(getResources().getString(R.string.logout_message));
            }
        });

    }

    public void showLogoutAlertDialog(String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(message);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                if (sessionManager.getKeyAccessCode().equals("1")){
                    sessionManager.setAccessCode("null data");
                    sessionManager.setKeyIsMerchantLoggedin(false);
                    startActivity(new Intent(ChoiceOutlet.this,LoginActivity.class));
                    finish();
                }
                //Toast.makeText(getApplicationContext(),"You clicked yes button",Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            userInterface.hideSystemUIWithActionBar();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /**
         * On selecting a spinner item
         */
        outletName = parent.getItemAtPosition(position).toString();
        logger.addInfo("Outlet Name",outletName);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Populate all outlet data to view
     */
    private void populateOutletDataToView (Realm realm){
        listAllOutlet.add("Outlet Name");
        RealmResults<Outlet> outletRealmResults = realm.where(Outlet.class).findAll();
        for (int i = 0; i < outletRealmResults.size(); i++) {
            Outlet outlet = outletRealmResults.get(i);
            listAllOutlet.add(outlet.getOutletName());
        }
        showListOutletNameToView(listAllOutlet);
    }

    private void showListOutletNameToView (List listOutletName){
        /**
         * Get all outlet name from session and put it all in list
         */
        //Collections.addAll(listAllOutlet, user.get(SessionManager.LIST_OUTLET_NAME).split(Declaration.DELIMITER));
        logger.addInfo("Outlet List",listOutletName.toString());
        /**
         * Creating adapter for spinner
         */
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listOutletName);
        /**
         * Drop down layout style - list view with radio button
         */
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        /**
         * Attaching data adapter to spinner
         */
        spinnerAllOutletName.setAdapter(dataAdapter);
        logger.addInfo("Spinner Outlet","is Created");
    }

    private void prepareSaveOrderData (Realm realm, String totalTable){
        for (int i = 1; i < integerOf(totalTable)+1; i++) {
            RealmPrepareDatabaseTableSaveOrder(realm, Declaration.NOT_FILLED, stringOf(i), Declaration.UNSELECTED);
        }
    }

    /**
     * Create realm database as a discount master table
     */
    private void RealmPrepareDatabaseTableSaveOrder (Realm realm, String save_order_transaction_id,
                                                     String save_order_number_table, String save_order_selected_flag){
        /**
         * All writes must be wrapped in a transaction to facilitate safe multi threading
         */
        realm.beginTransaction();
        /**
         * Add table discount master
         */
        SaveOrder saveOrder = realm.createObject(SaveOrder.class);
        saveOrder.setSaveOrderTransactionID(save_order_transaction_id);
        saveOrder.setSaveOrderNumberTable(save_order_number_table);
        saveOrder.setSaveOrderIsBeingSelected(save_order_selected_flag);
        /**
         * When the transaction is committed, all changes a synced to disk.
         */
        realm.commitTransaction();
    }

    @Override
    public void onBackPressed() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(home);
    }
}

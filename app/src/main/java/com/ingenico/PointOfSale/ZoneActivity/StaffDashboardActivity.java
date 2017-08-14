package com.ingenico.PointOfSale.ZoneActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ingenico.PointOfSale.Adapter.GridViewAdapterStaffInDashboard;
import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Controller.HashPassword;
import com.ingenico.PointOfSale.Controller.Image;
import com.ingenico.PointOfSale.Controller.SessionManager;
import com.ingenico.PointOfSale.Controller.UserInterface;
import com.ingenico.PointOfSale.ModelRealm.Category;
import com.ingenico.PointOfSale.ModelRealm.DiscountMaster;
import com.ingenico.PointOfSale.ModelRealm.Drawer;
import com.ingenico.PointOfSale.ModelRealm.Item;
import com.ingenico.PointOfSale.ModelRealm.ItemModifierGroup;
import com.ingenico.PointOfSale.ModelRealm.ItemVariant;
import com.ingenico.PointOfSale.ModelRealm.Login;
import com.ingenico.PointOfSale.ModelRealm.Merchant;
import com.ingenico.PointOfSale.ModelRealm.ModifierDetail;
import com.ingenico.PointOfSale.ModelRealm.ModifierGroup;
import com.ingenico.PointOfSale.ModelRealm.Outlet;
import com.ingenico.PointOfSale.ModelRealm.SaveOrder;
import com.ingenico.PointOfSale.ModelRealm.Service;
import com.ingenico.PointOfSale.ModelRealm.Tax;
import com.ingenico.PointOfSale.ModelRealm.Variant;
import com.ingenico.PointOfSale.PointOfSaleActivity;
import com.ingenico.PointOfSale.R;
import com.ingenico.PointOfSale.WelcomeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 4/14/2016.
 */
public class StaffDashboardActivity extends PointOfSaleActivity {
    /**
     * Declare in xml
     */
    ImageView imageViewShopLogo, imageViewPinOne, imageViewPinTwo, imageViewPinThree, imageViewPinFour,
            imageViewBackspace;
    TextView textViewMerchantName, textViewLogout, textViewICMP;
    GridView gridViewStaff;
    LinearLayout linearLayoutEnterPIN;
    RelativeLayout relativeLayoutNumberOne, relativeLayoutNumberTwo, relativeLayoutNumberThree, relativeLayoutNumberFour,
            relativeLayoutNumberFive,relativeLayoutNumberSix,relativeLayoutNumberSeven,relativeLayoutNumberEight,
            relativeLayoutNumberNine,relativeLayoutNumberZero,relativeLayoutBackspace,relativeLayoutEnter;
    /**
     * Pin staff that use in staff login
     */
    private String staffPin = "";
    /**
     * List for outlet name
     */
    LinkedList<HashMap<String, String>> linkedListOutletName;
    /**
     * username cashier that use in staff login
     */
    private String userIdCashier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**
         * Declare in xml
         */
        imageViewShopLogo = (ImageView)findViewById(R.id.imageViewShopLogo);
        textViewMerchantName = (TextView)findViewById(R.id.textViewMerchantName);
        textViewLogout = (TextView)findViewById(R.id.textViewLogout);
        textViewICMP = (TextView)findViewById(R.id.textViewICMP);
        gridViewStaff = (GridView)findViewById(R.id.gridViewStaff);

        linearLayoutEnterPIN = (LinearLayout)findViewById(R.id.linearLayoutEnterPIN);
        imageViewPinOne = (ImageView)findViewById(R.id.imageViewPinOne);
        imageViewPinTwo = (ImageView)findViewById(R.id.imageViewPinTwo);
        imageViewPinThree = (ImageView)findViewById(R.id.imageViewPinThree);
        imageViewPinFour = (ImageView)findViewById(R.id.imageViewPinFour);
        imageViewBackspace = (ImageView)findViewById(R.id.imageViewBackspace);

        relativeLayoutNumberOne = (RelativeLayout) findViewById(R.id.relativeLayoutNumberOne);
        relativeLayoutNumberTwo = (RelativeLayout) findViewById(R.id.relativeLayoutNumberTwo);
        relativeLayoutNumberThree = (RelativeLayout) findViewById(R.id.relativeLayoutNumberThree);
        relativeLayoutNumberFour = (RelativeLayout) findViewById(R.id.relativeLayoutNumberFour);
        relativeLayoutNumberFive = (RelativeLayout) findViewById(R.id.relativeLayoutNumberFive);
        relativeLayoutNumberSix = (RelativeLayout) findViewById(R.id.relativeLayoutNumberSix);
        relativeLayoutNumberSeven = (RelativeLayout) findViewById(R.id.relativeLayoutNumberSeven);
        relativeLayoutNumberEight = (RelativeLayout) findViewById(R.id.relativeLayoutNumberEight);
        relativeLayoutNumberNine = (RelativeLayout) findViewById(R.id.relativeLayoutNumberNine);
        relativeLayoutNumberZero = (RelativeLayout) findViewById(R.id.relativeLayoutNumberZero);
        relativeLayoutBackspace = (RelativeLayout) findViewById(R.id.relativeLayoutBackspace);
        relativeLayoutEnter = (RelativeLayout) findViewById(R.id.relativeLayoutEnter);

        /**
         * Load merchant logo from sdcard
         */
        Image.loadImageFromPath(Declaration.IMAGE_OUTPUT_PATH+"merchant_logo.png",imageViewShopLogo);
        logger.addInfo("Merchant Logo","is Created");
        /**
         * Set outlet name from database
         */
        textViewMerchantName.setText(session.get(SessionManager.KEY_OUTLET_NAME));
        /**
         * Set cashier name and image from database
         */
        gridViewStaff.setAdapter(new GridViewAdapterStaffInDashboard(this,
                listStaffName(realm,session.get(SessionManager.KEY_OUTLET_ID)),
                listStaffImage(realm,session.get(SessionManager.KEY_OUTLET_ID)))
        );
        gridViewStaff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                gridViewStaff.setVisibility(View.GONE);
                linearLayoutEnterPIN.setVisibility(View.VISIBLE);
                userIdCashier = listStaffUserID(realm,session.get(SessionManager.KEY_OUTLET_ID)).get(position);

            }
        });
        /**
         * List for outlet name
         */
        linkedListOutletName = new LinkedList<HashMap<String, String>>();

        /**
         * Handle all event input pin in staff dashboard
         */
        relativeLayoutNumberOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staffPin.length() != 4){
                    staffPin +="1";
                    showPin();
                    logger.addInfo("Write Pin",staffPin);
                    if (staffPin.length() == 4){
                        validateCashierCredential(realm,userIdCashier,staffPin);
                    }
                }else {
                    validateCashierCredential(realm,userIdCashier,staffPin);
                }
            }
        });

        relativeLayoutNumberTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staffPin.length() != 4){
                    staffPin +="2";
                    showPin();
                    logger.addInfo("Write Pin",staffPin);
                    if (staffPin.length() == 4){
                        validateCashierCredential(realm,userIdCashier,staffPin);
                    }
                }else {
                    validateCashierCredential(realm,userIdCashier,staffPin);
                }
            }
        });

        relativeLayoutNumberThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staffPin.length() != 4){
                    staffPin +="3";
                    showPin();
                    logger.addInfo("Write Pin",staffPin);
                    if (staffPin.length() == 4){
                        validateCashierCredential(realm,userIdCashier,staffPin);
                    }
                }else {
                    validateCashierCredential(realm,userIdCashier,staffPin);
                }
            }
        });

        relativeLayoutNumberFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staffPin.length() != 4){
                    staffPin +="4";
                    showPin();
                    logger.addInfo("Write Pin",staffPin);
                    if (staffPin.length() == 4){
                        validateCashierCredential(realm,userIdCashier,staffPin);
                    }
                }else {
                    validateCashierCredential(realm,userIdCashier,staffPin);
                }
            }
        });

        relativeLayoutNumberFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staffPin.length() != 4){
                    staffPin +="5";
                    showPin();
                    logger.addInfo("Write Pin",staffPin);
                    if (staffPin.length() == 4){
                        validateCashierCredential(realm,userIdCashier,staffPin);
                    }
                }else {
                    validateCashierCredential(realm,userIdCashier,staffPin);
                }
            }
        });

        relativeLayoutNumberSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staffPin.length() != 4){
                    staffPin +="6";
                    showPin();
                    logger.addInfo("Write Pin",staffPin);
                    if (staffPin.length() == 4){
                        validateCashierCredential(realm,userIdCashier,staffPin);
                    }
                }else {
                    validateCashierCredential(realm,userIdCashier,staffPin);
                }
            }
        });

        relativeLayoutNumberSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staffPin.length() != 4){
                    staffPin +="7";
                    showPin();
                    logger.addInfo("Write Pin",staffPin);
                    if (staffPin.length() == 4){
                        validateCashierCredential(realm,userIdCashier,staffPin);
                    }
                }else {
                    validateCashierCredential(realm,userIdCashier,staffPin);
                }
            }
        });

        relativeLayoutNumberEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staffPin.length() != 4){
                    staffPin +="8";
                    showPin();
                    logger.addInfo("Write Pin",staffPin);
                    if (staffPin.length() == 4){
                        validateCashierCredential(realm,userIdCashier,staffPin);
                    }
                }else {
                    validateCashierCredential(realm,userIdCashier,staffPin);
                }
            }
        });


        relativeLayoutNumberNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staffPin.length() != 4){
                    staffPin +="9";
                    showPin();
                    logger.addInfo("Write Pin",staffPin);
                    if (staffPin.length() == 4){
                        validateCashierCredential(realm,userIdCashier,staffPin);
                    }
                }else {
                    validateCashierCredential(realm,userIdCashier,staffPin);
                }
            }
        });

        relativeLayoutNumberZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staffPin.length() != 4){
                    staffPin +="0";
                    showPin();
                    logger.addInfo("Write Pin",staffPin);
                    if (staffPin.length() == 4){
                        validateCashierCredential(realm,userIdCashier,staffPin);
                    }
                }else {
                    validateCashierCredential(realm,userIdCashier,staffPin);
                }
            }
        });

        relativeLayoutBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staffPin.length() > 0){
                    staffPin = removeLastCharacter(staffPin);
                    erasePin();
                    logger.addInfo("Erase Pin",staffPin);
                }else if (staffPin.length() == 0){
                    linearLayoutEnterPIN.setVisibility(View.GONE);
                    gridViewStaff.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(StaffDashboardActivity.this, "Button cant be use.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        relativeLayoutEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logger.addInfo("Input Pin",staffPin);
                Toast.makeText(getApplicationContext(),staffPin,Toast.LENGTH_SHORT).show();
                validateCashierCredential(realm,userIdCashier,staffPin);
            }
        });

        /**
         * Event log out
         */
        textViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logger.addInfo("Logout Event","Alert Dialog");
                showLogoutAlertDialog(getResources().getString(R.string.logout_message));
            }
        });

        /**
         * Event to welcome activity
         */

        textViewICMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StaffDashboardActivity.this, WelcomeActivity.class));
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        userInterface = new UserInterface(this);
        if (hasFocus){
            userInterface.hideSystemUIWithActionBar();
        }
    }

    /**
     * Event showing pin in layout
     */
    private void showPin(){
        imageViewBackspace.setImageDrawable(getResources().getDrawable(R.drawable.icon_03_arrowleft));
        if (imageViewPinOne.getVisibility() == View.INVISIBLE){
            imageViewPinOne.setVisibility(View.VISIBLE);
        }else if (imageViewPinOne.getVisibility() == View.VISIBLE && imageViewPinTwo.getVisibility() == View.INVISIBLE){
            imageViewPinTwo.setVisibility(View.VISIBLE);
        }else if (imageViewPinTwo.getVisibility() == View.VISIBLE && imageViewPinThree.getVisibility() == View.INVISIBLE){
            imageViewPinThree.setVisibility(View.VISIBLE);
        }else if (imageViewPinThree.getVisibility() == View.VISIBLE){
            imageViewPinFour.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Event erase pin in layout
     */
    private void erasePin(){
        if (imageViewPinFour.getVisibility() == View.VISIBLE){
            imageViewPinFour.setVisibility(View.INVISIBLE);
        }else if (imageViewPinThree.getVisibility() == View.VISIBLE){
            imageViewPinThree.setVisibility(View.INVISIBLE);
        }else if (imageViewPinTwo.getVisibility() == View.VISIBLE){
            imageViewPinTwo.setVisibility(View.INVISIBLE);
        }else if (imageViewPinOne.getVisibility() == View.VISIBLE){
            imageViewBackspace.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_delete));
            imageViewPinOne.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Erase last character pin in layout
     */
    public String removeLastCharacter (String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    /**
     * Event alert dialog
     */
    public void showLogoutAlertDialog(String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(message);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                if (sessionManager.getKeyAccessCode().equals("2")){
                    sessionManager.setAccessCode("null data");
                    sessionManager.setKeyIsOutletLoggedin(false);

                    realm.beginTransaction();
                    realm.allObjects(Category.class).deleteAllFromRealm();
                    realm.allObjects(DiscountMaster.class).deleteAllFromRealm();
                    realm.allObjects(Drawer.class).deleteAllFromRealm();
                    realm.allObjects(Item.class).deleteAllFromRealm();
                    realm.allObjects(ItemModifierGroup.class).deleteAllFromRealm();
                    realm.allObjects(ItemVariant.class).deleteAllFromRealm();
                    realm.allObjects(Login.class).deleteAllFromRealm();
                    realm.allObjects(Merchant.class).deleteAllFromRealm();
                    realm.allObjects(ModifierDetail.class).deleteAllFromRealm();
                    realm.allObjects(ModifierGroup.class).deleteAllFromRealm();
                    realm.allObjects(Outlet.class).deleteAllFromRealm();
                    realm.allObjects(SaveOrder.class).deleteAllFromRealm();
                    realm.allObjects(Service.class).deleteAllFromRealm();
                    realm.allObjects(Tax.class).deleteAllFromRealm();
//                    realm.allObjects(TransactionDetail.class).deleteAllFromRealm();
//                    realm.allObjects(TransactionDetailDiscount.class).deleteAllFromRealm();
//                    realm.allObjects(TransactionDetailModifier.class).deleteAllFromRealm();
//                    realm.allObjects(TransactionMaster.class).deleteAllFromRealm();
                    realm.allObjects(Variant.class).deleteAllFromRealm();
                    realm.commitTransaction();

                    startActivity(new Intent(StaffDashboardActivity.this,LoginActivity.class));
                    finish();
                }else {
                    sessionManager.setKeyIsOutletLoggedin(false);

                    startActivity(new Intent(StaffDashboardActivity.this,ChoiceOutlet.class));
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

    /**
     * Get merchant name from database
     */
    private String getMerchantNameFromDatabase(Realm realm) {
        RealmResults<Merchant> realmResults = realm.where(Merchant.class).findAll();
        Merchant merchant = realmResults.get(0);
        return merchant.getMerchantName();
    }

    /**
     * Get staff name from database
     */
    private static ArrayList<String> listStaffUserID(Realm realm, String outlet_id){
        RealmResults<Login> loginRealmResults = realm.where(Login.class)
                .equalTo("userPrivilege", "3")
                .equalTo("outletID",outlet_id)
                .findAll();
        ArrayList<String> listStaffName = new ArrayList<String>();
        for (int i = 0; i < loginRealmResults.size(); i++) {
            Login login = loginRealmResults.get(i);
            listStaffName.add(login.getUsername());
        }
        return listStaffName;
    }

    /**
     * Get staff name from database
     */
    private static ArrayList<String> listStaffName(Realm realm, String outlet_id){
        RealmResults<Login> loginRealmResults = realm.where(Login.class)
                .equalTo("userPrivilege", "3")
                .equalTo("outletID",outlet_id)
                .findAll();
        ArrayList<String> listStaffName = new ArrayList<String>();
        for (int i = 0; i < loginRealmResults.size(); i++) {
            Login login = loginRealmResults.get(i);
            listStaffName.add(login.getUsername());
        }
        return listStaffName;
    }

    /**
     * Get staff image from database
     */
    private static ArrayList<String> listStaffImage(Realm realm, String outlet_id){
        RealmResults<Login> loginRealmResults = realm.where(Login.class)
                .equalTo("userPrivilege", "3")
                .equalTo("outletID",outlet_id)
                .findAll();
        ArrayList<String> listStaffImage = new ArrayList<String>();
        for (int i = 0; i < loginRealmResults.size(); i++) {
            Login login = loginRealmResults.get(i);
            listStaffImage.add(login.getPictureUser());
            //listStaffImage.add("ic_launcher");
        }
        return listStaffImage;
    }

    private void validateCashierCredential (Realm realm ,String username, String pinCashier){
        if (hashingPIN(pinCashier).equals(getCashierPasswordFromDatabase(realm,username))){
            Toast.makeText(getApplicationContext(),"Cashier Login Success",Toast.LENGTH_SHORT).show();
            RealmResults<Login> realmResultsLogin = realm
                    .where(Login.class)
                    .equalTo("username",username)
                    .findAll();
            sessionManager.setCashierUserID(username);
            sessionManager.setCashierUsername(realmResultsLogin.get(0).getUsername());
            sessionManager.setKeyIsCashierLoggedin(true);
            startActivity(new Intent(getApplicationContext(),CashRegisterActivity.class));
            finish();
        } else {
            Toast.makeText(getApplicationContext(),getString(R.string.please_input_your_credential_correctly),Toast.LENGTH_SHORT).show();
        }

    }

    private String getCashierPasswordFromDatabase (Realm realm, String user_id){
        RealmResults<Login> loginRealmResults = realm.where(Login.class)
                .equalTo("userPrivilege", "3")
                .equalTo("username",user_id)
                .findAll();
        Login login = loginRealmResults.get(0);
        return login.getUserPassword();
    }

    private String getCashierIDFromDatabase (Realm realm, String user_id){
        RealmResults<Login> loginRealmResults = realm.where(Login.class)
                .equalTo("userPrivilege", "3")
                .equalTo("userID",user_id)
                .findAll();
        Login login = loginRealmResults.get(0);
        return login.getUsername();
    }

    private String hashingPIN (String pin){
        return HashPassword.sha512(pin).toUpperCase();
    }

    @Override
    public void onBackPressed() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(home);
    }
}

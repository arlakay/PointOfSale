package com.ingenico.PointOfSale;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ingenico.PointOfSale.Controller.AppController;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Controller.EmailValidator;
import com.ingenico.PointOfSale.Controller.Image;
import com.ingenico.PointOfSale.Controller.Printer;
import com.ingenico.PointOfSale.Controller.PrinterCommands;
import com.ingenico.PointOfSale.Controller.ServiceLogger;
import com.ingenico.PointOfSale.Controller.SessionManager;
import com.ingenico.PointOfSale.Controller.UserInterface;
import com.ingenico.PointOfSale.Dialog.DialogAddDiscount;
import com.ingenico.PointOfSale.Dialog.DialogAddEmail;
import com.ingenico.PointOfSale.Dialog.DialogCreateDrawer;
import com.ingenico.PointOfSale.Dialog.DialogEndDrawer;
import com.ingenico.PointOfSale.Dialog.DialogInventoryAllItem;
import com.ingenico.PointOfSale.Dialog.DialogInventoryCategory;
import com.ingenico.PointOfSale.Dialog.DialogInventoryDiscount;
import com.ingenico.PointOfSale.Dialog.DialogInventoryModifier;
import com.ingenico.PointOfSale.Dialog.DialogStruck;
import com.ingenico.PointOfSale.Dialog.ItemDialogAtCashRegister;
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
import com.ingenico.PointOfSale.ModelRealm.Service;
import com.ingenico.PointOfSale.ModelRealm.Tax;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailModifier;
import com.ingenico.PointOfSale.ModelRealm.TransactionMaster;
import com.ingenico.PointOfSale.ModelRealm.Variant;
import com.ingenico.PointOfSale.Object.ItemBill;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;


/**
 * Created by Administrator-Handy on 4/19/2016.
 */
public class PointOfSaleActivity extends AppCompatActivity {
    /**
     * Service logger for logging
     */
    public ServiceLogger logger;
    /**
     * For translate image from string to view and the opposite
     */
    public Image image;
    /**
     * Make UI hide navigation bar at top and bottom
     */
    public UserInterface userInterface;
    /**
     * Session manager for login with string
     */
    public SessionManager sessionManager;
    /**
     * Make progress dialog for every activity
     */
    public ProgressDialog progressDialog;
    /**
     * Hashmap for session in activity
     */
    public HashMap<String, String> session;
    /**
     * Realm is a database for every data in apps
     */
    public Realm realm;
    public static RealmConfiguration realmConfig;
    /**
     * Item dialog for add variant or modifier in item
     */
    public ItemDialogAtCashRegister itemDialogAtCashRegister;
    /**
     * dialog for open drawer
     */
    public DialogCreateDrawer dialogCreateDrawer;
    /**
     * dialog for email
     */
    public DialogAddEmail dialogAddEmail;
    public DialogAddDiscount dialogAddDiscount;
    public DialogEndDrawer dialogEndDrawer;
    public DialogInventoryAllItem dialogInventoryAllItem;
    public DialogInventoryCategory dialogInventoryCategory;
    public DialogInventoryModifier dialogInventoryModifier;
    public DialogInventoryDiscount dialogInventoryDiscount;

    public DialogStruck dialogStruck;
    /**
     * email validator
     */
    public EmailValidator emailValidator;

    public Printer printer;

    /**
     * declare key for sync
     */
    static final String KEY_TRANSACTION_MASTER_ID = "key_transaction_master_id";
    static final String KEY_TRANSACTION_MASTER_CASHIER_NAME = "key_transaction_master_cashier_name";
    static final String KEY_TRANSACTION_MASTER_TOTAL_TRANSACTION = "key_transaction_master_total_transaction";
    static final String KEY_TRANSACTION_MASTER_TOTAL_QUANTITY = "key_transaction_master_total_quantity";
    static final String KEY_TRANSACTION_MASTER_TAX = "key_transaction_master_tax";
    static final String KEY_TRANSACTION_MASTER_SERVICE = "key_transaction_master_service";
    static final String KEY_TRANSACTION_MASTER_DISCOUNT = "key_transaction_master_discount";
    static final String KEY_TRANSACTION_MASTER_SUB_TOTAL = "key_transaction_master_sub_total";
    static final String KEY_TRANSACTION_MASTER_DATE_AND_TIME = "key_transaction_master_date_and_time";
    static final String KEY_TRANSACTION_MASTER_PAYMENT_TYPE = "key_transaction_master_payment_type";
    static final String KEY_TRANSACTION_MASTER_TENDERED = "key_transaction_master_tendered";
    static final String KEY_TRANSACTION_MASTER_CHANGE = "key_transaction_master_change";
    static final String KEY_TRANSACTION_MASTER_EMAIL = "key_transaction_master_email";

    static final String KEY_TRANSACTION_DETAIL = "key_transaction_detail";
    static final String KEY_TRANSACTION_DETAIL_ID = "key_transaction_detail_id";
    static final String KEY_TRANSACTION_DETAIL_ITEM_ID = "key_transaction_detail_item_id";
    static final String KEY_TRANSACTION_DETAIL_VARIANT_ID = "key_transaction_detail_variant_id";
    static final String KEY_TRANSACTION_DETAIL_MODIFIER_ID = "key_transaction_detail_modifier_id";
    static final String KEY_TRANSACTION_DETAIL_TOTAL_PRICE = "key_transaction_detail_total_price";
    static final String KEY_TRANSACTION_DETAIL_DISCOUNT_ID = "key_transaction_detail_discount_id";
    static final String KEY_TRANSACTION_DETAIL_QUANTITY = "key_transaction_detail_quantity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.logger= new ServiceLogger(this);
        this.image= new Image(this);
        this.userInterface = new UserInterface(this);
        this.sessionManager = new SessionManager(this);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setMessage("Loading...");
        this.progressDialog.setCancelable(false);
        this.session = sessionManager.getSessionDetails();
        this.emailValidator = new EmailValidator();
        this.printer = new Printer(this);

        /**
         * Create Realm configuration if it doesn't exist.
         */
        realmConfig = new RealmConfiguration.Builder(this).build();
        /**
         * Open the default Realm for the UI thread.
         */
        this.realm = Realm.getInstance(realmConfig);

        this.itemDialogAtCashRegister = new ItemDialogAtCashRegister();
        this.dialogAddEmail = new DialogAddEmail();
        this.dialogCreateDrawer = new DialogCreateDrawer();
        this.dialogEndDrawer = new DialogEndDrawer();
        this.dialogStruck = new DialogStruck();
        this.dialogAddDiscount = new DialogAddDiscount();
        this.dialogInventoryAllItem  = new DialogInventoryAllItem();
        this.dialogInventoryCategory  = new DialogInventoryCategory();
        this.dialogInventoryModifier  = new DialogInventoryModifier();
        this.dialogInventoryDiscount  = new DialogInventoryDiscount();
    }

    public void showProgressDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public void showAlertDialogNullEvent(Context context, String title, String msg){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {}
                }).create().show();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus){
            userInterface.toggleHideyBar();
        }
    }

    /**
     * Create realm database as a login table
     */
    public void RealmCreateDatabaseTableLogin (Realm realm, long realm_login_id,
                                               String username, String user_password,
                                               String user_privilege, String merchant_id,
                                               String outlet_id, String counter , String pict_user_url){
        /**
         * All writes must be wrapped in a transaction to facilitate safe multi threading
         */
        realm.beginTransaction();
        /**
         * Add table login
         */
        Login login = realm.createObject(Login.class);
        login.setRealmLoginID(realm_login_id);
        login.setUsername(username);
        login.setUserPassword(user_password);
        login.setUserPrivilege(user_privilege);
        login.setMerchantID(merchant_id);
        login.setOutletID(outlet_id);
        login.setCounter(counter);
        login.setPictureUser(pict_user_url);
        /**
         * When the transaction is committed, all changes a synced to disk.
         */
        realm.commitTransaction();
    }

    /**
     * Create realm database as a merchant table
     */
    public void RealmCreateDatabaseTableMerchant (Realm realm, long realm_merchant_id, String merchant_id, String merchant_name,
                                                   String merchant_address, String merchant_phone){
        /**
         * All writes must be wrapped in a transaction to facilitate safe multi threading
         */
        realm.beginTransaction();
        /**
         * Add table login
         */
        Merchant merchant = realm.createObject(Merchant.class);
        merchant.setRealmMerchantID(realm_merchant_id);
        merchant.setMerchantID(merchant_id);
        merchant.setMerchantName(merchant_name);
        merchant.setMerchantAddress(merchant_address);
        merchant.setMerhantPhone(merchant_phone);
        /**
         * When the transaction is committed, all changes a synced to disk.
         */
        realm.commitTransaction();
    }


    /**
     * Create realm database as a outlet table
     */
    public void RealmCreateDatabaseTableOutlet (Realm realm, long realm_outlet_id, String outlet_id, String outlet_name,
                                                 String outlet_address, String outlet_phone, String outlet_table){
        /**
         * All writes must be wrapped in a transaction to facilitate safe multi threading
         */
        realm.beginTransaction();
        /**
         * Add table outlet
         */
        Outlet outlet = realm.createObject(Outlet.class);
        outlet.setRealmOutletID(realm_outlet_id);
        outlet.setOutletID(outlet_id);
        outlet.setOutletName(outlet_name);
        outlet.setOutletAddress(outlet_address);
        outlet.setOutletPhone(outlet_phone);
        outlet.setOutletTable(outlet_table);
        /**
         * When the transaction is committed, all changes a synced to disk.
         */
        realm.commitTransaction();
    }

    /**
     * Create realm database as a category table
     */
    public void RealmCreateDatabaseTableCategory (Realm realm, long realm_category_id, String categ){
        /**
         * All writes must be wrapped in a transaction to facilitate safe multi threading
         */
        realm.beginTransaction();
        /**
         * Add table category
         */
        Category category = realm.createObject(Category.class);
        category.setRealmCategoryID(realm_category_id);
        category.setCategory(categ);
        /**
         * When the transaction is committed, all changes a synced to disk.
         */
        realm.commitTransaction();
    }

    /**
     * Create realm database as a item table
     */
    public void RealmCreateDatabaseTableItem (Realm realm, long realm_item_id, String item_id, String item_name,
                                              String item_price, String item_discount, String item_category,
                                              String status_tax, LinkedList varian, LinkedList modifier,
                                              String item_outlet_id){
        /**
         * All writes must be wrapped in a transaction to facilitate safe multi threading
         */
        realm.beginTransaction();
        /**
         * Add table item
         */
        Item item = realm.createObject(Item.class);
        item.setRealmItemID(realm_item_id);
        item.setItemID(item_id);
        item.setItemName(item_name);
        item.setItemPrice(item_price);
        item.setItemDiscount(item_discount);
        item.setItemCategory(item_category);
        item.setItemStatusTax(status_tax);
        for (int i = 0; i < varian.size(); i++) {
            ItemVariant itemVariant = realm.createObject(ItemVariant.class);
            itemVariant.setRealmItemVariantID(i);
            itemVariant.setItemVariantID(String.valueOf(varian.get(i)));
            item.itemVarian.add(itemVariant);
        }
        for (int j = 0; j < modifier.size(); j++) {
            ItemModifierGroup itemModifier = realm.createObject(ItemModifierGroup.class);
            itemModifier.setRealmItemModifierGroupID(j);
            itemModifier.setItemModifierGroupID(String.valueOf(modifier.get(j)));
            item.itemModifier.add(itemModifier);
        }
        item.setItemOutletID(item_outlet_id);

        /**
         * When the transaction is committed, all changes a synced to disk.
         */
        realm.commitTransaction();
    }

    /**
     * Create realm database as a tax table
     */
    public void RealmCreateDatabaseTableTax (Realm realm, long realm_tax_id, String tax_outlet_id, String tax_value){
        /**
         * All writes must be wrapped in a transaction to facilitate safe multi threading
         */
        realm.beginTransaction();
        /**
         * Add table item
         */
        Tax tax = realm.createObject(Tax.class);
        tax.setRealmTaxID(realm_tax_id);
        tax.setTaxOutletID(tax_outlet_id);
        tax.setTaxValue(tax_value);
        /**
         * When the transaction is committed, all changes a synced to disk.
         */
        realm.commitTransaction();
    }

    /**
     * Create realm database as a service and tax table
     */
    public void RealmCreateDatabaseTableService (Realm realm, long realm_service_id, String service_outlet_id, String service_value){
        /**
         * All writes must be wrapped in a transaction to facilitate safe multi threading
         */
        realm.beginTransaction();
        /**
         * Add table item
         */
        Service service = realm.createObject(Service.class);
        service.setRealmServiceID(realm_service_id);
        service.setServiceOutletID(service_outlet_id);
        service.setServiceValue(service_value);
        /**
         * When the transaction is committed, all changes a synced to disk.
         */
        realm.commitTransaction();
    }

    /**
     * Create realm database as a variant table
     */
    public void RealmCreateDatabaseTableVariant (Realm realm, long realm_variant_id, String variant_id,
                                                 String variant_name, String variant_price){
        /**
         * All writes must be wrapped in a transaction to facilitate safe multi threading
         */
        realm.beginTransaction();
        /**
         * Add table variant
         */
        Variant variant = realm.createObject(Variant.class);
        variant.setRealmVariantID(realm_variant_id);
        variant.setVariantID(variant_id);
        variant.setVariantName(variant_name);
        variant.setVariantPrice(variant_price);
        /**
         * When the transaction is committed, all changes a synced to disk.
         */
        realm.commitTransaction();
    }

    /**
     * Create realm database as a modifier table
     */
    public void RealmCreateDatabaseTableModifierDetail (Realm realm, long realm_modifier_detail_id, String modifier_detail_id,
                                                  String modifier_detail_name, String modifier_detail_price,
                                                  String modifier_detail_group_id){
        /**
         * All writes must be wrapped in a transaction to facilitate safe multi threading
         */
        realm.beginTransaction();
        /**
         * Add table modifierDetail
         */
        ModifierDetail modifierDetail = realm.createObject(ModifierDetail.class);
        modifierDetail.setRealmModifierDetailID(realm_modifier_detail_id);
        modifierDetail.setModifierDetailName(modifier_detail_name);
        modifierDetail.setModifierDetailID(modifier_detail_id);
        modifierDetail.setModifierDetailPrice(modifier_detail_price);
        modifierDetail.setModifierDetailGroupID(modifier_detail_group_id);
        /**
         * When the transaction is committed, all changes a synced to disk.
         */
        realm.commitTransaction();
    }

    /**
     * Create realm database as a modifier group table
     */
    public void RealmCreateDatabaseTableModifierGroup (Realm realm, long realm_modifier_group_id, String modifier_group_id,
                                                       String modifier_group_name, String modifier_group_option_flag,
                                                       String modifier_group_outlet_id){
        /**
         * All writes must be wrapped in a transaction to facilitate safe multi threading
         */
        realm.beginTransaction();
        /**
         * Add table modifier group
         */
        ModifierGroup modifierGroup = realm.createObject(ModifierGroup.class);
        modifierGroup.setRealmModifierGroupID(realm_modifier_group_id);
        modifierGroup.setModifierGroupID(modifier_group_id);
        modifierGroup.setModifierGroupName(modifier_group_name);
        modifierGroup.setModifierGroupOptionFlag(modifier_group_option_flag);
        modifierGroup.setModifierGroupOutletID(modifier_group_outlet_id);
        /**
         * When the transaction is committed, all changes a synced to disk.
         */
        realm.commitTransaction();
    }

    /**
     * Create realm database as a discount master table
     */
    public void RealmCreateDatabaseTableDiscountMaster (Realm realm, long realm_discount_master_id, String discount_master_id,
                                                        String discount_master_name, String discount_master_value,
                                                        String discount_master_type, String discount_master_outlet_id){
        /**
         * All writes must be wrapped in a transaction to facilitate safe multi threading
         */
        realm.beginTransaction();
        /**
         * Add table discount master
         */
        DiscountMaster discountMaster = realm.createObject(DiscountMaster.class);
        discountMaster.setRealmDiscountMasterID(realm_discount_master_id);
        discountMaster.setDiscountMasterID(discount_master_id);
        discountMaster.setDiscountMasterName(discount_master_name);
        discountMaster.setDiscountMasterValue(discount_master_value);
        discountMaster.setDiscountMasterType(discount_master_type);
        discountMaster.setDiscountMasterOutletID(discount_master_outlet_id);
        /**
         * When the transaction is committed, all changes a synced to disk.
         */
        realm.commitTransaction();
    }



    public void populateLoginDataToDatabase (JSONObject jsonObject){
        try {
            JSONArray jsonArrayLogin = jsonObject.getJSONArray("Login");
            for (int i = 0; i < jsonArrayLogin.length(); i++) {
                String url_image;
                JSONObject jsonObjectInArrayLogin  = jsonArrayLogin.getJSONObject(i);
                if (!jsonObjectInArrayLogin.getString("pict_user").equals("null")){
                    image.saveToSDCard(jsonObjectInArrayLogin.getString("username"),
                            jsonObjectInArrayLogin.getString("pict_user"));
                    url_image = Declaration.IMAGE_OUTPUT_PATH+jsonObjectInArrayLogin.getString("username")+".png";
                }else {
                    url_image = "null";
                }
                RealmCreateDatabaseTableLogin(
                        realm,
                        i,
                        jsonObjectInArrayLogin.getString("username"),
                        jsonObjectInArrayLogin.getString("user_password"),
                        jsonObjectInArrayLogin.getString("user_privilege"),
                        jsonObjectInArrayLogin.getString("merchant_id"),
                        jsonObjectInArrayLogin.getString("outlet_id"),
                        jsonObjectInArrayLogin.getString("counter"),
                        url_image
                );
            }
        }catch (JSONException e){
            e.printStackTrace();
        }


    }

    public void populateMerchantDataToDatabase (JSONObject jsonObject) {
        try {
            JSONArray jsonArrayMerchant = jsonObject.getJSONArray("merchant");
            for (int i = 0; i < jsonArrayMerchant.length(); i++) {
                JSONObject jsonObjectInArrayMerchant = jsonArrayMerchant.getJSONObject(i);
                RealmCreateDatabaseTableMerchant(
                        realm,
                        i,
                        jsonObjectInArrayMerchant.getString("merchant_id"),
                        jsonObjectInArrayMerchant.getString("merchant_name"),
                        jsonObjectInArrayMerchant.getString("merchant_address"),
                        jsonObjectInArrayMerchant.getString("merchant_telp")
                );
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void populateOutletDataToDatabase (JSONObject jsonObject) {
        try {
            JSONArray jsonArrayOutlet = jsonObject.getJSONArray("outlet");
            for (int i = 0; i < jsonArrayOutlet.length(); i++) {
                JSONObject jsonObjectInArrayOutlet = jsonArrayOutlet.getJSONObject(i);
                RealmCreateDatabaseTableOutlet(
                        realm,
                        i,
                        jsonObjectInArrayOutlet.getString("outlet_id"),
                        jsonObjectInArrayOutlet.getString("outlet_name"),
                        jsonObjectInArrayOutlet.getString("outlet_address"),
                        jsonObjectInArrayOutlet.getString("outlet_telp"),
                        jsonObjectInArrayOutlet.getString("outlet_table")
                );
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void populateCategoryDataToDatabase (JSONObject jsonObject){
        try {
            JSONArray jsonArrayCategory = jsonObject.getJSONArray("kategori");
            for (int i = 0; i < jsonArrayCategory.length(); i++) {
                RealmCreateDatabaseTableCategory(
                        realm,
                        i,
                        (String) jsonArrayCategory.get(i));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void populateItemDataToDatabase (JSONObject jsonObject){
        try {
            JSONArray jsonArrayItem = jsonObject.getJSONArray("item_master");
            for (int i = 0; i < jsonArrayItem.length(); i++) {
                JSONObject jsonObjectInArrayItem = jsonArrayItem.getJSONObject(i);

                LinkedList<String> listVarian = new LinkedList<>();
                LinkedList<String> listModifier = new LinkedList<>();
                JSONArray jsonArrayItemVarian = jsonObjectInArrayItem.getJSONArray("varian");
                if (!jsonObjectInArrayItem.getString("varian").equals("")){
                    for (int j = 0; j < jsonArrayItemVarian.length(); j++) {
                        listVarian.add(String.valueOf(jsonArrayItemVarian.get(j)));
                    }
                }
                if (!jsonObjectInArrayItem.getString("modifier").equals("")){
                    JSONArray jsonArrayItemModifier = jsonObjectInArrayItem.getJSONArray("modifier");
                    for (int k = 0; k < jsonArrayItemModifier.length(); k++) {
                        listModifier.add(String.valueOf(jsonArrayItemModifier.get(k)));
                    }
                }
                RealmCreateDatabaseTableItem(
                        realm,
                        i,
                        jsonObjectInArrayItem.getString("item_id"),
                        jsonObjectInArrayItem.getString("item_name"),
                        jsonObjectInArrayItem.getString("harga"),
                        jsonObjectInArrayItem.getString("discount"),
                        jsonObjectInArrayItem.getString("item_kategori"),
                        jsonObjectInArrayItem.getString("status_tax"),
                        listVarian,
                        listModifier,
                        jsonObjectInArrayItem.getString("outlet_id")
                );
                if (!jsonObjectInArrayItem.getString("pict").equals("")){
                    image.saveToSDCard(jsonObjectInArrayItem.getString("item_id"),
                            jsonObjectInArrayItem.getString("pict"));
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void populateServiceDataToDatabase (JSONObject jsonObject) {
        try {
            JSONArray jsonArrayService = jsonObject.getJSONArray("service");
            for (int i = 0; i < jsonArrayService.length(); i++) {
                JSONObject jsonObjectInArrayService = jsonArrayService.getJSONObject(i);
                RealmCreateDatabaseTableService(
                        realm,
                        i,
                        jsonObjectInArrayService.getString("outlet_id"),
                        jsonObjectInArrayService.getString("service_value")
                );
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void populateTaxDataToDatabase (JSONObject jsonObject) {
        try {
            JSONArray jsonArrayTax = jsonObject.getJSONArray("tax");
            for (int i = 0; i < jsonArrayTax.length(); i++) {
                JSONObject jsonObjectInArrayTax = jsonArrayTax.getJSONObject(i);
                RealmCreateDatabaseTableTax(
                        realm,
                        i,
                        jsonObjectInArrayTax.getString("outlet_id"),
                        jsonObjectInArrayTax.getString("tax_value")
                );
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void populateVariantDataToDatabase (JSONObject jsonObject) {
        try {
            JSONArray jsonArrayVariant = jsonObject.getJSONArray("varian");
            for (int i = 0; i < jsonArrayVariant.length(); i++) {
                JSONObject jsonObjectInArrayVariant = jsonArrayVariant.getJSONObject(i);
                RealmCreateDatabaseTableVariant(
                        realm,
                        i,
                        jsonObjectInArrayVariant.getString("varian_id"),
                        jsonObjectInArrayVariant.getString("varian_name"),
                        jsonObjectInArrayVariant.getString("varian_harga")
                );
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void populateModifierDetailDataToDatabase (JSONObject jsonObject) {
        try {
            JSONArray jsonArrayModifierDetail = jsonObject.getJSONArray("modifier_detail");
            for (int i = 0; i < jsonArrayModifierDetail.length(); i++) {
                JSONObject jsonObjectInArrayModifierDetail = jsonArrayModifierDetail.getJSONObject(i);
                RealmCreateDatabaseTableModifierDetail(
                        realm,
                        i,
                        jsonObjectInArrayModifierDetail.getString("modifier_id"),
                        jsonObjectInArrayModifierDetail.getString("modifier_name"),
                        jsonObjectInArrayModifierDetail.getString("modifier_price"),
                        jsonObjectInArrayModifierDetail.getString("modifier_group_id")
                );
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void populateModifierGroupDataToDatabase (JSONObject jsonObject) {
        try {
            JSONArray jsonArrayModifierGroup = jsonObject.getJSONArray("modifier_group");
            for (int i = 0; i < jsonArrayModifierGroup.length(); i++) {
                JSONObject jsonObjectInArrayModifierGroup = jsonArrayModifierGroup.getJSONObject(i);
                RealmCreateDatabaseTableModifierGroup(
                        realm,
                        i,
                        jsonObjectInArrayModifierGroup.getString("modifier_group_id"),
                        jsonObjectInArrayModifierGroup.getString("modifier_group_name"),
                        jsonObjectInArrayModifierGroup.getString("modifier_option_flag"),
                        jsonObjectInArrayModifierGroup.getString("outlet_id")
                );
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void populateDiscountMasterDataToDatabase (JSONObject jsonObject) {
        try {
            JSONArray jsonArrayDiscountMaster = jsonObject.getJSONArray("discount_master");
            for (int i = 0; i < jsonArrayDiscountMaster.length(); i++) {
                JSONObject jsonObjectInArrayDiscountMaster = jsonArrayDiscountMaster.getJSONObject(i);
                RealmCreateDatabaseTableDiscountMaster(
                        realm,
                        i,
                        jsonObjectInArrayDiscountMaster.getString("diskon_id"),
                        jsonObjectInArrayDiscountMaster.getString("diskon_name"),
                        jsonObjectInArrayDiscountMaster.getString("diskon_value"),
                        jsonObjectInArrayDiscountMaster.getString("diskon_type"),
                        jsonObjectInArrayDiscountMaster.getString("outlet_id")

                );
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public TextWatcher textWatcherKilos (final EditText editText){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.setText(convertValuePattern(editText.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public String revertAfterTextWatcher(String value){
        String aValue = "";
        if (value.contains(",")){aValue = value.replace(",","");}
        return aValue;
    }

    public String convertValuePattern (String value_before_pattern){
        if (!value_before_pattern.equals("")){
            return new DecimalFormat("###,###,###,###,###,###,###,###").format(Double.valueOf(value_before_pattern));
        }else {return "";}
    }

    public String revertValuePattern (String value_before_pattern){
        if (value_before_pattern.contains(",")){
            return value_before_pattern.replaceAll(",","");
        }else if (value_before_pattern.contains(".")){
            return value_before_pattern.replaceAll("\\.","");
        }
        return value_before_pattern;
    }

    public int integerOf(String stringInteger){
        return Integer.parseInt(revertValuePattern(stringInteger));
    }

    public String stringOf(int integerString){
        return String.valueOf(integerString);
    }


    public static String encodeImage (byte[] ImageByteArray){
        return Base64.encodeToString(ImageByteArray,Base64.DEFAULT);
    }

    public static byte[] decodeImage (String imageDataString){
        return Base64.decode(imageDataString, Base64.DEFAULT);
    }

    public static String convertHexToString(String hex){
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        for( int i=0; i<hex.length()-1; i+=2 ){
            String output = hex.substring(i, (i + 2));
            int decimal = Integer.parseInt(output, 16);
            sb.append((char)decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }

    public static Bitmap loadBitmapFromView(View v , int height, int width) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, width, height);
        v.draw(c);
        return b;
    }

    public void SynchronizingDataToServer (final JSONObject jsonObject){
        logger.addInfo("JSON Req",jsonObject.toString());
        String tag_json_req = "send_API";
        /**
         * Show progress dialog
         */
        showProgressDialog();

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,
                Declaration.URL_SYNC,  new Response.Listener<String>() {
            /**
             * Response volley from hit API
             */
            @Override
            public void onResponse(String response) {
                logger.addInfo("Response API",response);
/*                try {
                    JSONObject jObj = new JSONObject(response.toString());
                    String code = jObj.getString("code");
                    String msg = jObj.getString("msg");
                    /**
                     * Check code
                     */
/*                    if (code.equals("0007")){
                        Toast.makeText(getActivity(),
                                msg, Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getActivity(),
                                msg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    /**
                     * JSON error
                     */
/*                    e.printStackTrace();
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
                error.printStackTrace();
                logger.addInfo("VolleyError API",error.toString());
                Log.e("TAGTAG", "Error sync or email : " + error.getMessage());
                /**
                 * Show progress dialog
                 */
                hideProgressDialog();
            }
        }){
            /**
             * Posting parameter in url
             */
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sync", jsonObject.toString());
                logger.addInfo("Parameter in Volley",params.toString());
                return params;
            }

        };


        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                Declaration.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        /**
         * Adding request to request queue
         */
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_req);

    }


    public JSONObject JsonSync (RealmResults<TransactionMaster> realm_results_transaction_master,
                                 RealmResults<TransactionDetail> realm_results_transaction_detail){

        /**
         * json object for table transaction master and table transaction detail
         */
        JSONObject jsonObject = new JSONObject();
        /**
         * make json object for table transaction master
         */
        JSONArray jsonArrayTransactionMaster = new JSONArray();

        for (int i = 0; i <realm_results_transaction_master.size(); i++) {
            TransactionMaster transactionMaster = realm_results_transaction_master.get(i);
            try {
                JSONObject jsonObjectTransactionMaster = new JSONObject();
                jsonObjectTransactionMaster.put(KEY_TRANSACTION_MASTER_ID,transactionMaster.getTransactionMasterID());
                jsonObjectTransactionMaster.put(KEY_TRANSACTION_MASTER_CASHIER_NAME,transactionMaster.getTransactionMasterCashierName());
                jsonObjectTransactionMaster.put(KEY_TRANSACTION_MASTER_TOTAL_TRANSACTION,transactionMaster.getTransactionMasterTotalTransaction());
                jsonObjectTransactionMaster.put(KEY_TRANSACTION_MASTER_TOTAL_QUANTITY,transactionMaster.getTransactionMasterTotalQuantity());
                jsonObjectTransactionMaster.put(KEY_TRANSACTION_MASTER_TAX,transactionMaster.getTransactionMasterTax());
                jsonObjectTransactionMaster.put(KEY_TRANSACTION_MASTER_SERVICE,transactionMaster.getTransactionMasterService());
                jsonObjectTransactionMaster.put(KEY_TRANSACTION_MASTER_DISCOUNT,transactionMaster.getTransactionMasterDiscount());
                jsonObjectTransactionMaster.put(KEY_TRANSACTION_MASTER_SUB_TOTAL,transactionMaster.getTransactionMasterSubTotal());
                jsonObjectTransactionMaster.put(KEY_TRANSACTION_MASTER_DATE_AND_TIME,transactionMaster.getTransactionMasterDateAndTime());
                jsonObjectTransactionMaster.put(KEY_TRANSACTION_MASTER_PAYMENT_TYPE,transactionMaster.getTransactionMasterPaymentType());
                jsonObjectTransactionMaster.put(KEY_TRANSACTION_MASTER_TENDERED,transactionMaster.getTransactionMasterTendered());
                jsonObjectTransactionMaster.put(KEY_TRANSACTION_MASTER_CHANGE,transactionMaster.getTransactionMasterChange());
                jsonObjectTransactionMaster.put(KEY_TRANSACTION_MASTER_EMAIL,transactionMaster.getTransactionMasterEmail());

                jsonArrayTransactionMaster.put(jsonObjectTransactionMaster);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            jsonObject.put("transaction_master",jsonArrayTransactionMaster);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /**
         * make json object for table transaction detail
         */
        JSONArray jsonArrayTransactionDetail = new JSONArray();

        for (int i = 0; i <realm_results_transaction_detail.size(); i++) {
            TransactionDetail transactionDetail = realm_results_transaction_detail.get(i);
            try {
                JSONObject jsonObjectTransactionDetail = new JSONObject();
                jsonObjectTransactionDetail.put(KEY_TRANSACTION_DETAIL_ID,transactionDetail.getTransactionDetailID());
                jsonObjectTransactionDetail.put(KEY_TRANSACTION_DETAIL_ITEM_ID,transactionDetail.getTransactionDetailItemID());
                jsonObjectTransactionDetail.put(KEY_TRANSACTION_DETAIL_VARIANT_ID,transactionDetail.getTransactionDetailVariantID());

                RealmList<TransactionDetailModifier> realmListTransactionDetailModifier = transactionDetail.getTransactionDetailModifierID();
                JSONArray jsonArrayTransactionDetailModifier = new JSONArray();
                for (int j = 0; j < realmListTransactionDetailModifier.size(); j++) {
                    jsonArrayTransactionDetailModifier.put(realmListTransactionDetailModifier.get(j).getTransactionDetailModifierID());
                }
                jsonObjectTransactionDetail.put(KEY_TRANSACTION_DETAIL_MODIFIER_ID, jsonArrayTransactionDetailModifier);
                jsonObjectTransactionDetail.put(KEY_TRANSACTION_DETAIL_TOTAL_PRICE,transactionDetail.getTransactionDetailTotalPrice());

                RealmList<TransactionDetailDiscount> realmListTransactionDetailDiscount = transactionDetail.getTransactionDetailDiscountID();
                JSONArray jsonArrayTransactionDetailDiscountID = new JSONArray();
                for (int k = 0; k < realmListTransactionDetailDiscount.size(); k++) {
                    jsonArrayTransactionDetailDiscountID.put(realmListTransactionDetailDiscount.get(k).getDiscountID());
                }
                jsonObjectTransactionDetail.put(KEY_TRANSACTION_DETAIL_DISCOUNT_ID, jsonArrayTransactionDetailDiscountID);
                jsonObjectTransactionDetail.put(KEY_TRANSACTION_DETAIL_QUANTITY,transactionDetail.getTransactionDetailQuantity());


                jsonArrayTransactionDetail.put(jsonObjectTransactionDetail);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            jsonObject.put("transaction_detail",jsonArrayTransactionDetail);
            jsonObject.put("outlet_id",session.get(SessionManager.KEY_OUTLET_ID));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public void RealmCreateDatabaseTableDrawer (Realm realm, long drawer_id,
                                                String drawer_outlet_id,
                                                String drawer_casher_name, String drawer_starting_date_and_time,
                                                String drawer_starting_cash, String drawer_cash_sales, String drawer_card_sales,
                                                String drawer_expectation_cash, String drawer_ending_date_and_time,
                                                String drawer_description, String drawer_actual_cash){

        /**
         * All writes must be wrapped in a transaction to facilitate safe multi threading
         */
        realm.beginTransaction();
        /**
         * Add table modifier group
         */
        Drawer drawer = realm.createObject(Drawer.class);
        drawer.setDrawerID(drawer_id);
        drawer.setDrawerOutletID(drawer_outlet_id);
        drawer.setDrawerCashierName(drawer_casher_name);
        drawer.setDrawerStartingDateAndTime(drawer_starting_date_and_time);
        drawer.setDrawerStartingCash(drawer_starting_cash);
        drawer.setDrawerCashSales(drawer_cash_sales);
        drawer.setDrawerCardSales(drawer_card_sales);
        drawer.setDrawerExpetationCash(drawer_expectation_cash);
        drawer.setDrawerEndingDateAndTime(drawer_ending_date_and_time);
        drawer.setDrawerDescription(drawer_description);
        drawer.setDrawerActualCash(drawer_actual_cash);
        /**
         * When the transaction is committed, all changes a synced to disk.
         */
        realm.commitTransaction();

    }

    public void printBillTransaction(Realm realm,String transaction_id){
        RealmResults<TransactionMaster> realmResultsTransactionMaster = realm
                .where(TransactionMaster.class)
                .equalTo("transactionMasterID",transaction_id)
                .findAll();

        RealmResults<TransactionDetail> realmResultsTransactionDetail = realm
                .where(TransactionDetail.class)
                .equalTo("transactionDetailID",transaction_id)
                .findAll();

        RealmResults<Login> realmResultsLogin = realm
                .where(Login.class)
                .equalTo("username",realmResultsTransactionMaster.get(0).getTransactionMasterUserID())
                .findAll();

        RealmResults<Merchant> realmResultsMerchant = realm
                .where(Merchant.class)
                .equalTo("merchantID",realmResultsLogin.get(0).getMerchantID())
                .findAll();

        RealmResults<Outlet> realmResultsOutlet = realm
                .where(Outlet.class)
                .equalTo("outletID",realmResultsLogin.get(0).getOutletID())
                .findAll();

        LinkedList<ItemBill> linkedListItemBill = new LinkedList<>();
        for (int i = 0; i < realmResultsTransactionDetail.size(); i++) {
            RealmResults<Item> realmResultsItem = realm
                    .where(Item.class)
                    .equalTo("itemID",realmResultsTransactionDetail.get(i).getTransactionDetailItemID())
                    .findAll();
            ItemBill itemBill = new ItemBill();
            itemBill.setItemBillName(realmResultsItem.get(0).getItemName());
            itemBill.setItemBillPrice(convertValuePattern(realmResultsTransactionDetail.get(i).getTransactionDetailTotalPrice()));
            itemBill.setItemBillQuantity(realmResultsTransactionDetail.get(i).getTransactionDetailQuantity());
            if (!realmResultsTransactionDetail.get(i).getTransactionDetailVariantID().equals(Declaration.NULL_DATA)){
                RealmResults<Variant> realmResultsItemVariant = realm
                        .where(Variant.class)
                        .equalTo("VariantID",realmResultsTransactionDetail.get(i).getTransactionDetailVariantID())
                        .findAll();
                itemBill.setItemBillVariant(realmResultsItemVariant.get(0).getVariantName());
            }else {itemBill.setItemBillVariant(Declaration.NULL_DATA);}
            RealmList<TransactionDetailModifier> realmListTransactionDetailModifier =
                    realmResultsTransactionDetail.get(i).getTransactionDetailModifierID();
            LinkedList<String> linkedListModifier = new LinkedList<>();
            for (int j = 0; j < realmListTransactionDetailModifier.size(); j++) {
                RealmResults<ModifierDetail> realmResultsModifierDetail = realm
                        .where(ModifierDetail.class)
                        .equalTo("modifierDetailID",realmListTransactionDetailModifier.get(j).getTransactionDetailModifierID())
                        .findAll();
                if (realmResultsModifierDetail.size()!=0){
                    linkedListModifier.add(realmResultsModifierDetail.get(0).getModifierDetailName());
                }
            }
            itemBill.setItemBillModifier(linkedListModifier);
            linkedListItemBill.add(itemBill);

        }

        print(
                transaction_id,
                realmResultsTransactionMaster,
                realmResultsTransactionDetail,
                realmResultsLogin,
                realmResultsMerchant,
                realmResultsOutlet,
                linkedListItemBill
        );

    }

    public String generateVariantID(String merchant_id, int total_variant){
        return merchant_id+"varian"+total_variant;
    }

    public void printBillTransactionBeforePay(Realm realm,
                                              String transaction_id,
                                              String no_meja,
                                              String date_transaction,
                                              String time,
                                              String sub_total,
                                              String discount,
                                              String service,
                                              String tax,
                                              String total_transaction){
        RealmResults<TransactionDetail> realmResultsTransactionDetail = realm
                .where(TransactionDetail.class)
                .equalTo("transactionDetailID",transaction_id)
                .findAll();

        RealmResults<Login> realmResultsLogin = realm
                .where(Login.class)
                .equalTo("username",sessionManager.getSessionDetails().get(SessionManager.KEY_CASHIER_USERNAME_ID))
                .findAll();

        RealmResults<Merchant> realmResultsMerchant = realm
                .where(Merchant.class)
                .equalTo("merchantID",realmResultsLogin.get(0).getMerchantID())
                .findAll();

        RealmResults<Outlet> realmResultsOutlet = realm
                .where(Outlet.class)
                .equalTo("outletID",realmResultsLogin.get(0).getOutletID())
                .findAll();

        LinkedList<ItemBill> linkedListItemBill = new LinkedList<>();
        for (int i = 0; i < realmResultsTransactionDetail.size(); i++) {
            RealmResults<Item> realmResultsItem = realm
                    .where(Item.class)
                    .equalTo("itemID",realmResultsTransactionDetail.get(i).getTransactionDetailItemID())
                    .findAll();
            ItemBill itemBill = new ItemBill();
            itemBill.setItemBillName(realmResultsItem.get(0).getItemName());
            itemBill.setItemBillPrice(convertValuePattern(realmResultsTransactionDetail.get(i).getTransactionDetailTotalPrice()));
            itemBill.setItemBillQuantity(realmResultsTransactionDetail.get(i).getTransactionDetailQuantity());
            if (!realmResultsTransactionDetail.get(i).getTransactionDetailVariantID().equals(Declaration.NULL_DATA)){
                RealmResults<Variant> realmResultsItemVariant = realm
                        .where(Variant.class)
                        .equalTo("VariantID",realmResultsTransactionDetail.get(i).getTransactionDetailVariantID())
                        .findAll();
                itemBill.setItemBillVariant(realmResultsItemVariant.get(0).getVariantName());
            }else {itemBill.setItemBillVariant(Declaration.NULL_DATA);}
            RealmList<TransactionDetailModifier> realmListTransactionDetailModifier =
                    realmResultsTransactionDetail.get(i).getTransactionDetailModifierID();
            LinkedList<String> linkedListModifier = new LinkedList<>();
            for (int j = 0; j < realmListTransactionDetailModifier.size(); j++) {
                RealmResults<ModifierDetail> realmResultsModifierDetail = realm
                        .where(ModifierDetail.class)
                        .equalTo("modifierDetailID",realmListTransactionDetailModifier.get(j).getTransactionDetailModifierID())
                        .findAll();
                if (realmResultsModifierDetail.size()!=0){
                    linkedListModifier.add(realmResultsModifierDetail.get(0).getModifierDetailName());
                }
            }
            itemBill.setItemBillModifier(linkedListModifier);
            linkedListItemBill.add(itemBill);

        }

        printBeforePay(
                transaction_id,
                realmResultsTransactionDetail,
                realmResultsLogin,
                realmResultsMerchant,
                realmResultsOutlet,
                linkedListItemBill,
                no_meja,
                date_transaction,
                time,
                sub_total,
                discount,
                service,
                tax,
                total_transaction
        );

    }


    private void printBeforePay(
            String transaction_id,
            RealmResults<TransactionDetail> realmResultsTransactionDetail,
            RealmResults<Login> realmResultsLogin,
            RealmResults<Merchant> realmResultsMerchant,
            RealmResults<Outlet> realmResultsOutlet,
            LinkedList<ItemBill> linkedListItemBill,
            String no_meja,
            String date_transaction,
            String time,
            String sub_total,
            String discount,
            String service,
            String tax,
            String total_transaction){

        twoSecondProgressDialog();
        printer.openBluetoothConnection();
        try{
            printer.sendDataString(realmResultsMerchant.get(0).getMerchantName().toUpperCase(), PrinterCommands.SET_ALIGNMENT_CENTERED);
            printer.sendDataString(realmResultsOutlet.get(0).getOutletAddress(), PrinterCommands.SET_ALIGNMENT_CENTERED);
            printer.sendDataSpace();
            printer.sendDataString("TR no. " + transaction_id,PrinterCommands.SET_ALIGNMENT_LEFT);
            if (!no_meja.equals(Declaration.NULL_DATA)){
                printer.sendDataString(
                        header(
                                "No Meja. " + no_meja,
                                date_transaction
                        ),
                        PrinterCommands.SET_ALIGNMENT_LEFT
                );
            }else {
                printer.sendDataString(
                        header(
                                "No Meja. " + "-",
                                date_transaction
                        ),
                        PrinterCommands.SET_ALIGNMENT_LEFT
                );
            }
            printer.sendDataString(
                    header(
                            session.get(SessionManager.KEY_CASHIER_USERNAME),
                            time
                    ),
                    PrinterCommands.SET_ALIGNMENT_LEFT
            );
            printer.sendDataString(feedLine(),PrinterCommands.SET_ALIGNMENT_LEFT);

            for (int p = 0; p < linkedListItemBill.size(); p++) {
                printer.sendDataString(body(linkedListItemBill.get(p)),PrinterCommands.SET_ALIGNMENT_LEFT);
            }

            printer.sendDataString(feedLine(),PrinterCommands.SET_ALIGNMENT_LEFT);

            printer.sendDataString(
                    footer("Sub Total",sub_total),
                    PrinterCommands.SET_ALIGNMENT_LEFT
            );
            printer.sendDataString(
                    footer("Disc",discount),
                    PrinterCommands.SET_ALIGNMENT_LEFT
            );
            printer.sendDataString(
                    footer("Service",service),
                    PrinterCommands.SET_ALIGNMENT_LEFT
            );
            printer.sendDataString(
                    footer("Tax",tax),
                    PrinterCommands.SET_ALIGNMENT_LEFT
            );
            printer.sendDataString(feedLine(),PrinterCommands.SET_ALIGNMENT_LEFT);
            printer.sendDataString(
                    footer("Total",total_transaction),
                    PrinterCommands.SET_ALIGNMENT_LEFT
            );
            printer.sendDataString(feedLine(),PrinterCommands.SET_ALIGNMENT_LEFT);
//            printer.sendDataString(
//                    footer("Pay Amount",pay_amount),
//                    PrinterCommands.SET_ALIGNMENT_LEFT
//            );
//            printer.sendDataString(
//                    footer("Change",change),
//                    PrinterCommands.SET_ALIGNMENT_LEFT
//            );
//            printer.sendDataString(feedLine(),PrinterCommands.SET_ALIGNMENT_LEFT);
            printer.sendDataString("Thank You", PrinterCommands.SET_ALIGNMENT_CENTERED);
            printer.sendDataSpace();
//            printer.sendDataSpace();

//            printer.printQrCodeInStruck(
//                    transaction_id,
//                    revertValuePattern(total_transaction),
//                    realmResultsMerchant.get(0).getMerchantName(),
//                    realmResultsOutlet.get(0).getOutletName(),
//                    revertValuePattern(total_transaction).substring(0,revertValuePattern(total_transaction).length()-1),
//                    "get_point"
//            );
            printer.closeBluetoothConnection();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void print(
            String transaction_id,
            RealmResults<TransactionMaster> realmResultsTransactionMaster,
            RealmResults<TransactionDetail> realmResultsTransactionDetail,
            RealmResults<Login> realmResultsLogin,
            RealmResults<Merchant> realmResultsMerchant,
            RealmResults<Outlet> realmResultsOutlet,
            LinkedList<ItemBill> linkedListItemBill){

        twoSecondProgressDialog();
        printer.openBluetoothConnection();
        try{
            printer.sendDataString(realmResultsMerchant.get(0).getMerchantName().toUpperCase(), PrinterCommands.SET_ALIGNMENT_CENTERED);
            printer.sendDataString(realmResultsOutlet.get(0).getOutletAddress(), PrinterCommands.SET_ALIGNMENT_CENTERED);
            printer.sendDataSpace();
            printer.sendDataString("TR no. " + transaction_id,PrinterCommands.SET_ALIGNMENT_LEFT);
            if (!realmResultsTransactionMaster.get(0).getTransactionMasterTableNumber().equals(Declaration.NULL_DATA)){
                printer.sendDataString(
                        header(
                                "No Meja. " + realmResultsTransactionMaster.get(0).getTransactionMasterTableNumber(),
                                realmResultsTransactionMaster.get(0).getTransactionMasterDate()
                        ),
                        PrinterCommands.SET_ALIGNMENT_LEFT
                );
            }else {
                printer.sendDataString(
                        header(
                                "No Meja. " + "-",
                                realmResultsTransactionMaster.get(0).getTransactionMasterDate()
                        ),
                        PrinterCommands.SET_ALIGNMENT_LEFT
                );
            }
            printer.sendDataString(
                    header(
                            realmResultsTransactionMaster.get(0).getTransactionMasterCashierName(),
                            realmResultsTransactionMaster.get(0).getTransactionMasterDateAndTime().substring(11,19)
                    ),
                    PrinterCommands.SET_ALIGNMENT_LEFT
            );
            printer.sendDataString(feedLine(),PrinterCommands.SET_ALIGNMENT_LEFT);

            for (int p = 0; p < linkedListItemBill.size(); p++) {
                printer.sendDataString(body(linkedListItemBill.get(p)),PrinterCommands.SET_ALIGNMENT_LEFT);
            }

            printer.sendDataString(feedLine(),PrinterCommands.SET_ALIGNMENT_LEFT);

            printer.sendDataString(
                    footer("Sub Total",convertValuePattern(realmResultsTransactionMaster.get(0).getTransactionMasterSubTotal())),
                    PrinterCommands.SET_ALIGNMENT_LEFT
            );
            printer.sendDataString(
                    footer("Disc",convertValuePattern(realmResultsTransactionMaster.get(0).getTransactionMasterDiscount())),
                    PrinterCommands.SET_ALIGNMENT_LEFT
            );
            printer.sendDataString(
                    footer("Service",convertValuePattern(realmResultsTransactionMaster.get(0).getTransactionMasterService())),
                    PrinterCommands.SET_ALIGNMENT_LEFT
            );
            printer.sendDataString(
                    footer("Tax",convertValuePattern(realmResultsTransactionMaster.get(0).getTransactionMasterTax())),
                    PrinterCommands.SET_ALIGNMENT_LEFT
            );
            printer.sendDataString(feedLine(),PrinterCommands.SET_ALIGNMENT_LEFT);
            printer.sendDataString(
                    footer("Total",convertValuePattern(realmResultsTransactionMaster.get(0).getTransactionMasterTotalTransaction())),
                    PrinterCommands.SET_ALIGNMENT_LEFT
            );
            printer.sendDataString(feedLine(),PrinterCommands.SET_ALIGNMENT_LEFT);
            printer.sendDataString(
                    footer("Pay Amount",convertValuePattern(realmResultsTransactionMaster.get(0).getTransactionMasterTendered())),
                    PrinterCommands.SET_ALIGNMENT_LEFT
            );
            printer.sendDataString(
                    footer("Change",convertValuePattern(realmResultsTransactionMaster.get(0).getTransactionMasterChange())),
                    PrinterCommands.SET_ALIGNMENT_LEFT
            );
            printer.sendDataString(feedLine(),PrinterCommands.SET_ALIGNMENT_LEFT);
            printer.sendDataString("Thank You", PrinterCommands.SET_ALIGNMENT_CENTERED);
            printer.sendDataSpace();

//            printer.printQrCodeInStruck(
//                    transaction_id,
//                    revertValuePattern(realmResultsTransactionMaster.get(0).getTransactionMasterTotalTransaction()),
//                    realmResultsMerchant.get(0).getMerchantName(),
//                    realmResultsOutlet.get(0).getOutletName(),
//                    revertValuePattern(realmResultsTransactionMaster.get(0).getTransactionMasterTotalTransaction()).
//                            substring(0,revertValuePattern(realmResultsTransactionMaster.get(0).getTransactionMasterTotalTransaction()).length()-1),
//                    "get_point"
//            );

            printer.sendDataSpace();
            printer.closeBluetoothConnection();

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private String header(String string_1, String string_2){
        String header = "";
        if (string_1.length()<32&&string_2.length()<32){
            String spasi="";
            for (int i = 0; i < 32 - string_1.length() - string_2.length(); i++) {
                spasi+=" ";
            }
            header = string_1 + spasi + string_2;
        }

        return header;
    }

    private String body(ItemBill item_bill) {
        String modifier="";
        for (int i = 0; i < item_bill.getItemBillModifier().size(); i++) {
            modifier += make30Char(item_bill.getItemBillModifier().get(i));
        }
        if (!item_bill.getItemBillVariant().equals(Declaration.NULL_DATA)){
            return bodyItemName(item_bill.getItemBillQuantity(),item_bill.getItemBillName(),item_bill.getItemBillPrice())+
                    make30Char(item_bill.getItemBillVariant())+
                    modifier;
        }else {
            return bodyItemName(item_bill.getItemBillQuantity(),item_bill.getItemBillName(),item_bill.getItemBillPrice())+
                    modifier;
        }
    }
    private String feedLine(){
        String line = "";
        for (int i = 0; i < 32; i++) {
            line+="-";
        }
        return line;
    }

    private String bodyItemName(String quantity, String item_name, String price){
        String spasi = "";
        for (int i = 0; i < 30 - quantity.length() - item_name.length() - price.length(); i++) {
            spasi+=" ";
        }
        if (quantity.length()==3){
            return quantity+""+item_name+spasi+price;
        }else if (quantity.length()==2){
            return quantity+" "+item_name+spasi+price;
        }else {
            return quantity+"  "+item_name+spasi+price;
        }
    }

    private String make30Char(String string){
        String spasi ="";
        String frontSpasi = "   ";
        for (int i = 0; i < 29 - string.length(); i++) {
            spasi +=" ";
        }
        if (string.length()<29){
            return frontSpasi + string + spasi;
        }else {return string;}
    }

    private String footer (String string_1, String string_2){
        String header = "";
        if (string_1.length()<30 && string_2.length()<30){
            String spasi="";
            for (int i = 0; i < 29 - string_1.length() - string_2.length(); i++) {
                spasi+=" ";
            }
            header = "   "+string_1 + spasi + string_2;
        }

        return header;
    }


    private void twoSecondProgressDialog(){
        showProgressDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideProgressDialog();
            }
        }, 3000);
    }


//    private LinkedList<String> outletAddress (String address){
//        LinkedList<String> outletAddress = new LinkedList<>();
//        if (address.length()>)
//
//        return outletAddress;
//    }
}

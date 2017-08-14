package com.ingenico.PointOfSale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.Spannable;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.ImageSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ingenico.PointOfSale.Adapter.GridViewAdapterItemCashRegister;
import com.ingenico.PointOfSale.Adapter.GridViewAdapterSaveOrderCashRegister;
import com.ingenico.PointOfSale.Adapter.ListViewAdapterItemInCartCashRegister;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Controller.Image;
import com.ingenico.PointOfSale.Controller.RoundedImageView;
import com.ingenico.PointOfSale.Controller.SessionManager;
import com.ingenico.PointOfSale.Controller.TransactionID;
import com.ingenico.PointOfSale.Controller.UserInterface;
import com.ingenico.PointOfSale.Fragment.fHistory;
import com.ingenico.PointOfSale.Fragment.fInventory;
import com.ingenico.PointOfSale.Fragment.fOtherMenu;
import com.ingenico.PointOfSale.Fragment.fPayment;
import com.ingenico.PointOfSale.Fragment.fReport;
import com.ingenico.PointOfSale.Fragment.fSaveOrder;
import com.ingenico.PointOfSale.Fragment.fSetting;
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
import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailModifier;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporary;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryDiscount;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryModifier;
import com.ingenico.PointOfSale.ModelRealm.TransactionMaster;
import com.ingenico.PointOfSale.ModelRealm.Variant;
import com.ingenico.PointOfSale.ZoneActivity.PaymentSuccess;
import com.ingenico.pclservice.IPclServiceCallback;
import com.ingenico.pclservice.TransactionIn;
import com.ingenico.pclservice.TransactionOut;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 4/13/2016.
 */
public class CashRegisterActivity extends CommonActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    /**
     * The thread to manage user interface
     */
    UserInterface userInterface;
    /**
     * Declare in xml
     */
    private RadioGroup radioGroupCategories, radioGroupPaymentType;
    private RadioButton radioButtonCategory1, radioButtonCategory2, radioButtonCategory3, radioButtonCategory4,
            radioButtonCategory5, radioButtonCategory6, radioButtonCategory7, radioButtonCategory8, radioButtonCategory9,
            radioButtonCash, radioButtonCard, radioButtonOther;
    public GridView gridViewItemFromCategory, gridViewSaveOrder;
    private TextView textViewSubTotal, textViewCartVatTotal, textViewDiscount, textViewLayoutBillTotalAmount, textViewPaymentType,
            textViewPredictionPayment1, textViewPredictionPayment2, textViewPredictionPayment3, textViewPredictionPayment4,
            textViewCategories, textViewCartTotalAmountTotal, textViewService, textViewCartAddDiscount,
            textViewInput1, textViewInput2, textViewInput3, textViewInput4, textViewInput5, textViewInput6, textViewInput7,
            textViewInput8, textViewInput9, textViewInput0, textViewInput00, textViewLayoutBillInputAmount, textViewCartTransactionID,
            textViewCartClearCart, textViewDiscountRP, textViewNavigationGreetingTime, textViewNavigationCashierName,
            textViewNavigationMerchantName, textViewOrderType, imageViewLoadOrder, textViewCartTableNumber;
    private Button buttonNext, buttonConnectToIcmpCommendSale, buttonRedeem, textViewCartPrintBill, textViewCartSaveOrder;
    private ListView listViewCartInRegister;
    private LinearLayout linearLayoutBillToCharge, linearLayoutCartEmpty, linearLayoutCartBottom,
            linearLayoutInputDelete, linearLayoutInputEnter, linearLayoutCartListView,
            linearLayoutSaveOrder, linearLayoutRadioButtonCashRegister, linearLayoutGridViewCashRegister,
            linearLayoutConnectToICMP, linearLayoutCalculator, linearLayoutOther, linearLayoutQrCode;
    private RelativeLayout relativeLayoutCartTotalAmount, linearLayoutCartTop;
    private ImageView imageViewCloseSaveOrder, imageViewCloseBillToCharge, imageViewQrCode;
    private FrameLayout frameLayout, frameLayoutSaveOrder;
    private RoundedImageView imageViewNavigationHeader;

    //not visible
    private TextView mtvStatus;
    private EditText mtvResult;
    private Typeface mtfStrato;


    RealmChangeListener realmChangeListenerCart;
    RealmChangeListener realmChangeListenerSaveOrder;
    /**
     * declare for hash map in adapter
     */
    static final String KEY_TRANSACTION_ID = "transaction_id";
    static final String KEY_ITEM_ID = "item_id";
    static final String KEY_ITEM_NAME = "item_name";
    static final String KEY_ITEM_PRICE = "item_price";
    static final String KEY_ITEM_DISCOUNT = "item_discount";
    static final String KEY_ITEM_CATEGORY = "item_category";
    static final String KEY_ITEM_PICTURE_URL = "item_picture_url";
    static final String KEY_ITEM_TOTAL = "item_total";
    static final String KEY_ITEM_STATUS_TAX = "item_status_tax";
    static final String KEY_ITEM_VARIANT = "item_variant";
    static final String KEY_ITEM_MODIFIER_GROUP = "item_modifier_group";
    static final String KEY_ITEM_MODIFIER_NAME = "item_modifier_name";

    static final String KEY_VARIANT_ID = "variant_id";
    static final String KEY_VARIANT_NAME = "variant_name";

    static final String KEY_MODIFIER_ID = "modifier_id";
    static final String KEY_MODIFIER_NAME = "modifier_name";
    static final String KEY_MODIFIER_PRICE = "modifier_price";

    static final String KEY_MODIFIER_GROUP_ID = "modifier_group_id";
    static final String KEY_MODIFIER_GROUP_NAME = "modifier_group_name";

    static final String KEY_SAVE_ORDER_TRANSACTION_ID = "save_order_transaction_id";
    static final String KEY_SAVE_ORDER_NUMBER_TABLE = "save_order_number_table";
    static final String KEY_SAVE_ORDER_IS_BEING_SELECTED = "save_order_is_being_selected";
    /**
     * category value
     */
    String categoryValue;
    /**
     * array list temporary for add item from grid view to list view in cart
     */
    LinkedList<HashMap<String, String>> arrayListTemporary = new LinkedList<HashMap<String, String>>();
    LinkedList<HashMap<String, String>> linkedListTransactionDetailPremier = new LinkedList<>();
    HashMap<String, String> hashMap = new HashMap<>();

    /**
     * count total amount that will purchase
     */
    private String amountTotal = "";
    String price, de;

    ListViewAdapterItemInCartCashRegister listViewAdapterItemInCartCashRegister;
    GridViewAdapterSaveOrderCashRegister gridViewAdapterSaveOrderCashRegister;

    private TextView textViewTestingTransactionID;
    RealmResults<TransactionDetail> realmResultTransactionDetail;

    RadioButton[] radioButtonArray = new RadioButton[9];

    ArrayList<HashMap<String, String>> ArrayListItemNew;
    RealmChangeListener realmChangeListenerAllItem;
    RealmChangeListener realmChangeListenerCategory;
    RealmResults<Item> realmResultsListenerItem;
    RealmResults<Category> realmResultsListenerCategory;
    /**
     * param for transaction in icmp
     */
    /* VARIABLES USED DURING TESTS */
    private static final String TAG = "PCLTESTAPP";
    private static final int JUSTIFIED_CENTER = 0;
    private static final int JUSTIFIED_RIGHT = 1;
    private static final int JUSTIFIED_LEFT = 2;
    private static final String EXTRA_SIGNATURE_BMP = "signature";
    private String[] slistOfBmp;
    AssetManager assetManager;
    static int id = 1;
    private boolean mBcrRead;
    private Context mContext;
    private boolean mCallbackRegistered = false;
    private static Bitmap mLastSignature = null;

    private NetworkTask mNetworkTask;
    private boolean mPrintRawText;
    private int miScanState = 0;
    private static final int NB_ISO_FONTS = 7;

    // default hexa is false
    boolean hexa = false;

    String itemIDFromCategory, itemNAMEFromCategory, variant, modifierGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_register);
        //variable testing
        textViewTestingTransactionID = (TextView) findViewById(R.id.textViewTestingTransactionId);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mtfStrato = Typeface.createFromAsset(getAssets(), "Strato-linked.ttf");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        generateNewTransactionID();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);


        userInterface = new UserInterface(this);
//        userInterface.toggleHideyBar();

        /**
         * Declare in xml
         */
        radioGroupCategories = (RadioGroup) findViewById(R.id.radioGroupCategories);
        radioGroupPaymentType = (RadioGroup) findViewById(R.id.radioGroupPaymentType);
        radioButtonCategory1 = (RadioButton) findViewById(R.id.radioButtonCategory1);
        radioButtonCategory2 = (RadioButton) findViewById(R.id.radioButtonCategory2);
        radioButtonCategory3 = (RadioButton) findViewById(R.id.radioButtonCategory3);
        radioButtonCategory4 = (RadioButton) findViewById(R.id.radioButtonCategory4);
        radioButtonCategory5 = (RadioButton) findViewById(R.id.radioButtonCategory5);
        radioButtonCategory6 = (RadioButton) findViewById(R.id.radioButtonCategory6);
        radioButtonCategory7 = (RadioButton) findViewById(R.id.radioButtonCategory7);
        radioButtonCategory8 = (RadioButton) findViewById(R.id.radioButtonCategory8);
        radioButtonCategory9 = (RadioButton) findViewById(R.id.radioButtonCategory9);
        radioButtonCash = (RadioButton) findViewById(R.id.radioButtonCash);
        radioButtonCard = (RadioButton) findViewById(R.id.radioButtonCard);
        radioButtonOther = (RadioButton) findViewById(R.id.radioButtonOther);
        listViewCartInRegister = (ListView) findViewById(R.id.listViewCartInRegister);
        gridViewItemFromCategory = (GridView) findViewById(R.id.gridViewItemFromCategory);
        gridViewSaveOrder = (GridView) findViewById(R.id.gridViewSaveOrder);
        textViewSubTotal = (TextView) findViewById(R.id.textViewSubTotal);
        textViewCartVatTotal = (TextView) findViewById(R.id.textViewCartVatTotal);
        textViewDiscount = (TextView) findViewById(R.id.textViewDiscount);
        textViewPaymentType = (TextView) findViewById(R.id.textViewPaymentType);
        textViewCategories = (TextView) findViewById(R.id.textViewCategories);
        textViewLayoutBillTotalAmount = (TextView) findViewById(R.id.textViewLayoutBillTotalAmount);
        textViewCartTotalAmountTotal = (TextView) findViewById(R.id.textViewCartTotalAmountTotal);
        textViewCartSaveOrder = (Button) findViewById(R.id.textViewCartSaveOrder);
        textViewService = (TextView) findViewById(R.id.textViewService);
        textViewCartAddDiscount = (TextView) findViewById(R.id.textViewCartAddDiscount);
        textViewPredictionPayment1 = (TextView) findViewById(R.id.textViewPredictionPayment1);
        textViewPredictionPayment2 = (TextView) findViewById(R.id.textViewPredictionPayment2);
        textViewPredictionPayment3 = (TextView) findViewById(R.id.textViewPredictionPayment3);
        textViewPredictionPayment4 = (TextView) findViewById(R.id.textViewPredictionPayment4);
        textViewInput1 = (TextView) findViewById(R.id.textViewInput1);
        textViewInput2 = (TextView) findViewById(R.id.textViewInput2);
        textViewInput3 = (TextView) findViewById(R.id.textViewInput3);
        textViewInput4 = (TextView) findViewById(R.id.textViewInput4);
        textViewInput5 = (TextView) findViewById(R.id.textViewInput5);
        textViewInput6 = (TextView) findViewById(R.id.textViewInput6);
        textViewInput7 = (TextView) findViewById(R.id.textViewInput7);
        textViewInput8 = (TextView) findViewById(R.id.textViewInput8);
        textViewInput9 = (TextView) findViewById(R.id.textViewInput9);
        textViewInput0 = (TextView) findViewById(R.id.textViewInput0);
        textViewInput00 = (TextView) findViewById(R.id.textViewInput00);
        textViewLayoutBillInputAmount = (TextView) findViewById(R.id.textViewLayoutBillInputAmount);
        textViewCartTransactionID = (TextView) findViewById(R.id.textViewCartTransactionID);
        textViewCartClearCart = (TextView) findViewById(R.id.textViewCartClearCart);
        textViewDiscountRP = (TextView) findViewById(R.id.textViewDiscountRP);
        textViewNavigationGreetingTime = (TextView) header.findViewById(R.id.textViewNavigationGreetingTime);
        textViewNavigationCashierName = (TextView) header.findViewById(R.id.textViewNavigationCashierName);
        textViewNavigationMerchantName = (TextView) header.findViewById(R.id.textViewNavigationMerchantName);
        textViewOrderType = (TextView) findViewById(R.id.textViewOrderType);
        textViewCartTableNumber = (TextView) findViewById(R.id.textViewCartTableNumber);
        textViewCartPrintBill = (Button) findViewById(R.id.textViewCartPrintBill);
        imageViewCloseSaveOrder = (ImageView) findViewById(R.id.imageViewCloseSaveOrder);
        imageViewLoadOrder = (TextView) findViewById(R.id.imageViewLoadOrder);
        imageViewCloseBillToCharge = (ImageView) findViewById(R.id.imageViewCloseBillToCharge);
        imageViewQrCode = (ImageView) findViewById(R.id.imageViewQrCode);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonConnectToIcmpCommendSale = (Button) findViewById(R.id.buttonConnectToIcmpCommendSale);
        buttonRedeem = (Button) findViewById(R.id.buttonRedeem);
        relativeLayoutCartTotalAmount = (RelativeLayout) findViewById(R.id.relativeLayoutCartTotalAmount);
        linearLayoutCartTop = (RelativeLayout) findViewById(R.id.linearLayoutCartTop);
        linearLayoutBillToCharge = (LinearLayout) findViewById(R.id.linearLayoutBillToCharge);
        linearLayoutCartEmpty = (LinearLayout) findViewById(R.id.linearLayoutCartEmpty);
        linearLayoutInputDelete = (LinearLayout) findViewById(R.id.linearLayoutInputDelete);
        linearLayoutInputEnter = (LinearLayout) findViewById(R.id.linearLayoutInputEnter);
        linearLayoutCartBottom = (LinearLayout) findViewById(R.id.linearLayoutCartBottom);
        linearLayoutCartListView = (LinearLayout) findViewById(R.id.linearLayoutCartListView);
        linearLayoutSaveOrder = (LinearLayout) findViewById(R.id.linearLayoutSaveOrder);
        linearLayoutRadioButtonCashRegister = (LinearLayout) findViewById(R.id.linearLayoutRadioButtonCashRegister);
        linearLayoutGridViewCashRegister = (LinearLayout) findViewById(R.id.linearLayoutGridViewCashRegister);
        linearLayoutConnectToICMP = (LinearLayout) findViewById(R.id.linearLayoutConnectToICMP);
        linearLayoutCalculator = (LinearLayout) findViewById(R.id.linearLayoutCalculator);
        linearLayoutOther = (LinearLayout) findViewById(R.id.linearLayoutOther);
        linearLayoutQrCode = (LinearLayout) findViewById(R.id.linearLayoutQrCode);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        frameLayoutSaveOrder = (FrameLayout) findViewById(R.id.frameLayoutSaveOrder);
        imageViewNavigationHeader = (RoundedImageView) header.findViewById(R.id.imageViewNavigationHeader);

        /**
         * for card transaction event
         */
        mContext = this;
        Log.e("Test", sessionManager.getAlreadyConnected());
        mBcrRead = false;

        /**
         * setting navigation information
         */
        RealmResults<Merchant> merchantRealmResults = realm.where(Merchant.class).findAll();
        textViewNavigationCashierName.setText(sessionManager.getSessionDetails().get(SessionManager.KEY_CASHIER_USERNAME));
        textViewNavigationMerchantName.setText(merchantRealmResults.get(0).getMerchantName());
        settingGreetingTime(textViewNavigationGreetingTime);
        Image.loadImageFromPath(Declaration.IMAGE_OUTPUT_PATH + "merchant_logo.png", imageViewNavigationHeader);

        //not visible
        mtvResult = (EditText) findViewById(R.id.tvResult);
        mtvStatus = (TextView) findViewById(R.id.tvStatus);
        /**
         * prepare database for save order
         */
        RealmResults<Outlet> realmResultsOutlet = realm.where(Outlet.class)
                .equalTo("outletID", session.get(SessionManager.KEY_OUTLET_ID))
                .findAll();
        prepareSaveOrderData(realm, realmResultsOutlet.get(0).getOutletTable());

        /**
         * Put all radio button in array category
         */
        radioButtonArray[0] = radioButtonCategory1;
        radioButtonArray[1] = radioButtonCategory2;
        radioButtonArray[2] = radioButtonCategory3;
        radioButtonArray[3] = radioButtonCategory4;
        radioButtonArray[4] = radioButtonCategory5;
        radioButtonArray[5] = radioButtonCategory6;
        radioButtonArray[6] = radioButtonCategory7;
        radioButtonArray[7] = radioButtonCategory8;
        radioButtonArray[8] = radioButtonCategory9;

        generateViewCategory();

        /**
         * Control view radio button in list category
         */
        if (getCategoryFromDatabase(realm).size() <= 9) {
            for (int i = 0; i < getCategoryFromDatabase(realm).size(); i++) {
                radioButtonArray[i].setText(getCategoryFromDatabase(realm).get(i));
                radioButtonArray[i].setVisibility(View.VISIBLE);
            }
            radioButtonArray[getCategoryFromDatabase(realm).size()].setText("Other");
            radioButtonArray[getCategoryFromDatabase(realm).size()].setVisibility(View.VISIBLE);
            if (getCategoryFromDatabase(realm).size() == 0) {
                gridViewItemFromCategory.setVisibility(View.GONE);
                linearLayoutOther.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutOther, new fOtherMenu()).commit();
            }
        } else {
            for (int i = 0; i < 9; i++) {
                radioButtonArray[i].setText(getCategoryFromDatabase(realm).get(i));
            }
            radioButtonArray[getCategoryFromDatabase(realm).size()].setText("Other");
            radioButtonArray[getCategoryFromDatabase(realm).size()].setVisibility(View.VISIBLE);
        }

        /**
         * control item view in grid view
         */
        radioGroupCategories.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                categoryValue = ((RadioButton) findViewById(radioGroupCategories.getCheckedRadioButtonId())).getText().toString();
                if (categoryValue.equals("Other")) {
                    gridViewItemFromCategory.setVisibility(View.GONE);
                    linearLayoutOther.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutOther, new fOtherMenu()).commit();
                } else {
                    /**
                     * Set item name and image to view
                     */
                    gridViewItemFromCategory.setVisibility(View.VISIBLE);
                    linearLayoutOther.setVisibility(View.GONE);
                    generateGridViewItem(realm, categoryValue, session.get(SessionManager.KEY_OUTLET_ID));
                    //gridViewItemFromCategory.setAdapter(new GridViewAdapterItemCashRegister(
                    //      CashRegisterActivity.this,
                    //      ArrayListItem
                    //));

                    ((BaseAdapter) gridViewItemFromCategory.getAdapter()).notifyDataSetChanged();
                }
            }
        });
        /**
         * control payment type view
         */
        radioGroupPaymentType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroupPaymentType.getCheckedRadioButtonId()) {
                    case R.id.radioButtonCash: {
                        linearLayoutConnectToICMP.setVisibility(View.GONE);
                        textViewLayoutBillInputAmount.setVisibility(View.VISIBLE);
                        linearLayoutCalculator.setVisibility(View.VISIBLE);
                        linearLayoutQrCode.setVisibility(View.GONE);
                        break;
                    }
                    case R.id.radioButtonCard: {
                        linearLayoutConnectToICMP.setVisibility(View.VISIBLE);
                        textViewLayoutBillInputAmount.setVisibility(View.INVISIBLE);
                        linearLayoutCalculator.setVisibility(View.GONE);
                        linearLayoutQrCode.setVisibility(View.GONE);
                        break;
                    }
                    case R.id.radioButtonPoint: {
                        linearLayoutQrCode.setVisibility(View.VISIBLE);
                        linearLayoutConnectToICMP.setVisibility(View.GONE);
                        textViewLayoutBillInputAmount.setVisibility(View.GONE);
                        linearLayoutCalculator.setVisibility(View.GONE);
                        generateViewPoint();
                    }
                }
            }
        });
        /**
         * Set item data in grid view item for the first view and realm listener
         */
        realmResultsListenerItem = realm.where(Item.class).findAll();
        realmChangeListenerAllItem = new RealmChangeListener() {
            @Override
            public void onChange() {
                if (categoryValue != null) {
                    generateGridViewItem(realm, categoryValue, session.get(SessionManager.KEY_OUTLET_ID));
                } else {
//                    generateGridViewItem(realm, getCategoryFromDatabase(realm).get(0), session.get(SessionManager.KEY_OUTLET_ID));
                }
            }
        };
        realmResultsListenerItem.addChangeListener(realmChangeListenerAllItem);
        if (getCategoryFromDatabase(realm).size() > 0) {
            generateGridViewItem(realm, getCategoryFromDatabase(realm).get(0), session.get(SessionManager.KEY_OUTLET_ID));
        }

        realmResultsListenerCategory = realm.where(Category.class).findAll();
        realmChangeListenerCategory = new RealmChangeListener() {
            @Override
            public void onChange() {
                generateViewCategory();
            }
        };
        realmResultsListenerCategory.addChangeListener(realmChangeListenerCategory);

//        gridViewItemFromCategory.setAdapter(new GridViewAdapterItemCashRegister(
//                CashRegisterActivity.this,
//                ArrayListItem(realm,getCategoryFromDatabase(realm).get(0),session.get(SessionManager.KEY_OUTLET_ID))
//        ));

        Log.e("array kepannggul", "luar array");
        /**
         * handle grid view click event
         * variable sum is a variable for sum of item in first row
         */
        //final int[] sum = {1};
        gridViewItemFromCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                itemIDFromCategory = ArrayListItemNew.get(position).get(KEY_ITEM_ID);
                itemNAMEFromCategory = ArrayListItemNew.get(position).get(KEY_ITEM_NAME);

                if (categoryValue != null) {
                    Log.e(TAG, "MASUK IF 1");

                    variant = ArrayListItemNew.get(position).get(KEY_ITEM_VARIANT);
                    modifierGroup = ArrayListItemNew.get(position).get(KEY_ITEM_MODIFIER_GROUP);
                    Log.e(TAG, "MASUK LOH : " + variant + ";" + modifierGroup);

                    if (variant.equals(Declaration.DELIMITER) && modifierGroup.equals(Declaration.DELIMITER)) {
                        Log.e(TAG, "MASUK IF 2");

                        LinkedList<String> linkedListResultAllTopping = new LinkedList<>();
                        linkedListResultAllTopping.add("");
                        LinkedList<String> linkedListResultDiscount = new LinkedList<>();
                        linkedListResultDiscount.add("");

                        if (isLastItem(realm
                                , ArrayListItemNew.get(position).get(KEY_ITEM_ID)
                                , ArrayListItemNew.get(position).get(KEY_ITEM_PRICE))) {
                            Log.e(TAG, "MASUK IF 3");

                            updateQuantityItemOrder(realm, "1");
                            updateQuantityItemOrderInDatabaseTemporary(realm, "1");
                        } else {
                            Log.e(TAG, "MASUK ELSE 3");

                            saveOrderToDatabase(realm
                                    , sessionManager.getTansactionID()
                                    , ArrayListItemNew.get(position).get(KEY_ITEM_ID)
                                    , Declaration.NULL_DATA
                                    , ArrayListItemNew.get(position).get(KEY_ITEM_PRICE)
                                    , "1"
                                    , linkedListResultAllTopping
                                    , linkedListResultDiscount);

                            saveOrderToDatabaseTemporary(realm, sessionManager.getTansactionID()
                                    , ArrayListItemNew.get(position).get(KEY_ITEM_ID)
                                    , Declaration.NULL_DATA
                                    , ArrayListItemNew.get(position).get(KEY_ITEM_PRICE)
                                    , "1",
                                    linkedListResultAllTopping, linkedListResultDiscount);
                        }
                    } else {
                        Log.e(TAG, "MASUK ELSE 2");

                        gridViewItemFromCategory.setEnabled(false);

                        itemDialogAtCashRegister
                                .setTransaction_id(sessionManager.getTansactionID())
                                .setItem_id(ArrayListItemNew.get(position).get(KEY_ITEM_ID))
                                .setItem_name(ArrayListItemNew.get(position).get(KEY_ITEM_NAME))
                                .setItem_price(ArrayListItemNew.get(position).get(KEY_ITEM_PRICE))
                                .setItem_discount(ArrayListItemNew.get(position).get(KEY_ITEM_DISCOUNT))
                                .setItem_category(ArrayListItemNew.get(position).get(KEY_ITEM_CATEGORY))
                                .setItem_status_tax(ArrayListItemNew.get(position).get(KEY_ITEM_STATUS_TAX))
                                .setItem_variant(ArrayListItemNew.get(position).get(KEY_ITEM_VARIANT))
                                .setItem_modifier_group(ArrayListItemNew.get(position).get(KEY_ITEM_MODIFIER_GROUP))
                                .setFromCart(false)
                                .show(getFragmentManager(), "show_item_dialog");
                    }
                } else {
                    Log.e(TAG, "MASUK ELSE 1");
                    categoryValue = getCategoryFromDatabase(realm).get(0);
                    variant = ArrayListItemNew.get(position).get(KEY_ITEM_VARIANT);
                    modifierGroup = ArrayListItemNew.get(position).get(KEY_ITEM_MODIFIER_GROUP);

                    Log.e(TAG, "MASUK LOH : " + variant + ";" + modifierGroup);

                    if (variant.equals(Declaration.DELIMITER) && modifierGroup.equals(Declaration.DELIMITER)) {
                        Log.e(TAG, "MASUK IF 4");

                        LinkedList<String> linkedListResultAllTopping = new LinkedList<>();
                        linkedListResultAllTopping.add("");
                        LinkedList<String> linkedListResultDiscount = new LinkedList<>();
                        linkedListResultDiscount.add("");
                        if (isLastItem(realm
                                , ArrayListItemNew.get(position).get(KEY_ITEM_ID)
                                , ArrayListItemNew.get(position).get(KEY_ITEM_PRICE))) {
                            Log.e(TAG, "MASUK IF 5");

                            updateQuantityItemOrder(realm, "1");
                            updateQuantityItemOrderInDatabaseTemporary(realm, "1");
                        } else {
                            Log.e(TAG, "MASUK ELSE 5");

                            saveOrderToDatabase(realm, sessionManager.getTansactionID()
                                    , ArrayListItemNew.get(position).get(KEY_ITEM_ID)
                                    , Declaration.NULL_DATA
                                    , ArrayListItemNew.get(position).get(KEY_ITEM_PRICE)
                                    , "1",
                                    linkedListResultAllTopping, linkedListResultDiscount);

                            saveOrderToDatabaseTemporary(realm, sessionManager.getTansactionID()
                                    , ArrayListItemNew.get(position).get(KEY_ITEM_ID)
                                    , Declaration.NULL_DATA
                                    , ArrayListItemNew.get(position).get(KEY_ITEM_PRICE)
                                    , "1",
                                    linkedListResultAllTopping, linkedListResultDiscount);
                        }
                    } else {
                        Log.e(TAG, "MASUK ELSE 4");

                        gridViewItemFromCategory.setEnabled(false);

                        itemDialogAtCashRegister
                                .setTransaction_id(sessionManager.getTansactionID())
                                .setItem_id(ArrayListItemNew.get(position).get(KEY_ITEM_ID))
                                .setItem_name(ArrayListItemNew.get(position).get(KEY_ITEM_NAME))
                                .setItem_price(ArrayListItemNew.get(position).get(KEY_ITEM_PRICE))
                                .setItem_discount(ArrayListItemNew.get(position).get(KEY_ITEM_DISCOUNT))
                                .setItem_category(ArrayListItemNew.get(position).get(KEY_ITEM_CATEGORY))
                                .setItem_status_tax(ArrayListItemNew.get(position).get(KEY_ITEM_STATUS_TAX))
                                .setItem_variant(ArrayListItemNew.get(position).get(KEY_ITEM_VARIANT))
                                .setItem_modifier_group(ArrayListItemNew.get(position).get(KEY_ITEM_MODIFIER_GROUP))
                                .setFromCart(false)
                                .show(getFragmentManager(), "show_item_dialog");

//                        itemDialogAtCashRegister.setTransaction_id(sessionManager.getTansactionID());
//                        itemDialogAtCashRegister.setItem_id(ArrayListItemNew.get(position).get(KEY_ITEM_ID));
//                        itemDialogAtCashRegister.setItem_name(ArrayListItemNew.get(position).get(KEY_ITEM_NAME));
//                        itemDialogAtCashRegister.setItem_price(ArrayListItemNew.get(position).get(KEY_ITEM_PRICE));
//                        itemDialogAtCashRegister.setItem_discount(ArrayListItemNew.get(position).get(KEY_ITEM_DISCOUNT));
//                        itemDialogAtCashRegister.setItem_category(ArrayListItemNew.get(position).get(KEY_ITEM_CATEGORY));
//                        itemDialogAtCashRegister.setItem_status_tax(ArrayListItemNew.get(position).get(KEY_ITEM_STATUS_TAX));
//                        itemDialogAtCashRegister.setItem_variant(ArrayListItemNew.get(position).get(KEY_ITEM_VARIANT));
//                        itemDialogAtCashRegister.setItem_modifier_group(ArrayListItemNew.get(position).get(KEY_ITEM_MODIFIER_GROUP));
//                        itemDialogAtCashRegister.setFromCart(false);
//                        itemDialogAtCashRegister.show(getFragmentManager(), "show_item_dialog");
                    }
                }
            }
        });

        /**
         * handle add discount event
         */
        textViewCartAddDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddDiscount.setTransaction_id(sessionManager.getTansactionID()).show(getFragmentManager(), "show_struck");
//                dialogAddDiscount.show(getFragmentManager(), "show_struck");
//                if (textViewCartAddDiscount.getText().toString().equals("+ ADD DISCOUNT")){
//                    textViewCartAddDiscount.setText("DISCOUNT");
//                } else {
//                    textViewCartAddDiscount.setText("+ ADD DISCOUNT");
//                }

            }
        });

        /**
         * handle button click event
         */
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayoutSaveOrder.getVisibility() == View.VISIBLE) {
                    changeViewSaveOrder();
                    //linearLayoutSaveOrder.setVisibility(View.GONE);
                }

                if (linearLayoutOther.getVisibility() == View.VISIBLE) {
                    linearLayoutOther.setVisibility(View.GONE);
                }
                relativeLayoutCartTotalAmount.setVisibility(View.VISIBLE);
                textViewLayoutBillTotalAmount.setText(buttonNext.getText().toString());

                textViewCategories.setVisibility(View.GONE);
                radioGroupCategories.setVisibility(View.GONE);
                textViewCartSaveOrder.setVisibility(View.GONE);
                buttonNext.setVisibility(View.INVISIBLE);
                gridViewItemFromCategory.setVisibility(View.GONE);

                textViewPaymentType.setVisibility(View.VISIBLE);
                radioGroupPaymentType.setVisibility(View.VISIBLE);
                linearLayoutBillToCharge.setVisibility(View.VISIBLE);

                textViewCartTotalAmountTotal.setText(convertValuePattern(String.valueOf(
                        Integer.parseInt(revertValuePattern(textViewSubTotal.getText().toString()))
//                        - Integer.parseInt(revertValuePattern(textViewDiscount.getText().toString()))
                                + Integer.parseInt(revertValuePattern(textViewService.getText().toString()))
                                + Integer.parseInt(revertValuePattern(textViewCartVatTotal.getText().toString()))
                )));

                textViewLayoutBillTotalAmount.setText(textViewCartTotalAmountTotal.getText().toString());

            }
        });

        textViewPredictionPayment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), textViewPredictionPayment1.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        textViewPredictionPayment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), textViewPredictionPayment2.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        textViewPredictionPayment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), textViewPredictionPayment3.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        textViewPredictionPayment4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), textViewPredictionPayment4.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });


        textViewInput1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountTotal.length() <= 9) {
                    amountTotal += "1";
                    changeLayoutBillInputAmount(textViewLayoutBillInputAmount, convertValuePattern(amountTotal));
                } else {
                    showAlertDialogNullEvent(CashRegisterActivity.this, "Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountTotal.length() <= 9) {
                    amountTotal += "2";
                    changeLayoutBillInputAmount(textViewLayoutBillInputAmount, convertValuePattern(amountTotal));
                } else {
                    showAlertDialogNullEvent(CashRegisterActivity.this, "Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountTotal.length() <= 9) {
                    amountTotal += "3";
                    changeLayoutBillInputAmount(textViewLayoutBillInputAmount, convertValuePattern(amountTotal));
                } else {
                    showAlertDialogNullEvent(CashRegisterActivity.this, "Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountTotal.length() <= 9) {
                    amountTotal += "4";
                    changeLayoutBillInputAmount(textViewLayoutBillInputAmount, convertValuePattern(amountTotal));
                } else {
                    showAlertDialogNullEvent(CashRegisterActivity.this, "Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountTotal.length() <= 9) {
                    amountTotal += "5";
                    changeLayoutBillInputAmount(textViewLayoutBillInputAmount, convertValuePattern(amountTotal));
                } else {
                    showAlertDialogNullEvent(CashRegisterActivity.this, "Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountTotal.length() <= 9) {
                    amountTotal += "6";
                    changeLayoutBillInputAmount(textViewLayoutBillInputAmount, convertValuePattern(amountTotal));
                } else {
                    showAlertDialogNullEvent(CashRegisterActivity.this, "Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountTotal.length() <= 9) {
                    amountTotal += "7";
                    changeLayoutBillInputAmount(textViewLayoutBillInputAmount, convertValuePattern(amountTotal));
                } else {
                    showAlertDialogNullEvent(CashRegisterActivity.this, "Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountTotal.length() <= 9) {
                    amountTotal += "8";
                    changeLayoutBillInputAmount(textViewLayoutBillInputAmount, convertValuePattern(amountTotal));
                } else {
                    showAlertDialogNullEvent(CashRegisterActivity.this, "Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountTotal.length() <= 9) {
                    amountTotal += "9";
                    changeLayoutBillInputAmount(textViewLayoutBillInputAmount, convertValuePattern(amountTotal));
                } else {
                    showAlertDialogNullEvent(CashRegisterActivity.this, "Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountTotal.length() <= 9) {
                    amountTotal += "0";
                    changeLayoutBillInputAmount(textViewLayoutBillInputAmount, convertValuePattern(amountTotal));
                } else {
                    showAlertDialogNullEvent(CashRegisterActivity.this, "Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountTotal.length() <= 9) {
                    amountTotal += "00";
                    changeLayoutBillInputAmount(textViewLayoutBillInputAmount, convertValuePattern(amountTotal));
                } else {
                    showAlertDialogNullEvent(CashRegisterActivity.this, "Sorry", "Can't Count again.");
                }
            }
        });

        linearLayoutInputDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountTotal = removeLastCharacter(amountTotal);
                if (amountTotal.length() == 0) {
                    changeLayoutBillInputAmount(textViewLayoutBillInputAmount, amountTotal);
                } else {
                    changeLayoutBillInputAmount(textViewLayoutBillInputAmount, convertValuePattern(amountTotal));
                }
            }
        });

        imageViewCloseBillToCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewCategories.setVisibility(View.VISIBLE);
                radioGroupCategories.setVisibility(View.VISIBLE);
                gridViewItemFromCategory.setVisibility(View.VISIBLE);

                textViewCartSaveOrder.setVisibility(View.VISIBLE);
                relativeLayoutCartTotalAmount.setVisibility(View.INVISIBLE);
                buttonNext.setEnabled(true);
                buttonNext.setVisibility(View.VISIBLE);

                textViewPaymentType.setVisibility(View.GONE);
                radioGroupPaymentType.setVisibility(View.GONE);
                linearLayoutBillToCharge.setVisibility(View.GONE);
            }
        });

        /**
         * handle button enter click event
         */
        linearLayoutInputEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textViewLayoutBillInputAmount.getText().toString().equals("") && !textViewLayoutBillInputAmount.getText().toString().equals(0)) {
                    int intCharge = Integer.parseInt(revertValuePattern(textViewLayoutBillInputAmount.getText().toString())) - Integer.parseInt(price);
                    if (intCharge < 0) {
                        showAlertDialogNullEvent(CashRegisterActivity.this, "Please..", "Your fee is bigger than payment..");
                    } else {
                        deleteTransactionIdFromSaveOrder(realm, sessionManager.getTansactionID());
                        deleteOrderFromDatabaseTemporary(realm);
                        String charge = String.valueOf(intCharge);
                        saveTransactionToDatabase(
                                realm,
                                "",
                                sessionManager.getTansactionID(),
                                session.get(SessionManager.KEY_CASHIER_USERNAME_ID),
                                session.get(SessionManager.KEY_CASHIER_USERNAME),
                                price,
                                quantityAllItem(realm, sessionManager.getTansactionID()),
                                revertValuePattern(textViewCartVatTotal.getText().toString()),
                                revertValuePattern(textViewService.getText().toString()),
                                revertValuePattern(textViewDiscount.getText().toString()),
                                revertValuePattern(textViewSubTotal.getText().toString()),
                                dateAndTime(),
                                "Cash",
                                revertValuePattern(textViewLayoutBillInputAmount.getText().toString()),
                                charge,
                                sessionManager.getTableNumber());

                        connectTransactionToDrawerDatabase(realm, price, true);

                        Intent mIntent = new Intent(CashRegisterActivity.this, PaymentSuccess.class);
                        mIntent.putExtra("transaction_id", sessionManager.getTansactionID());
                        mIntent.putExtra("total_change", convertValuePattern(charge));
                        startActivity(mIntent);
                        finish();
                    }
                } else {
                    showAlertDialogNullEvent(CashRegisterActivity.this, "Please..", "Your fee is bigger than payment..");
                }

            }
        });

        textViewCartClearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateTransactionIdInSaveOrderDatabase(sessionManager.getTansactionID());
            }
        });

        /**
         * handle button save order click event
         */
        textViewCartSaveOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutRadioButtonCashRegister.setVisibility(View.INVISIBLE);
                linearLayoutGridViewCashRegister.setVisibility(View.INVISIBLE);

                linearLayoutSaveOrder.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.frameLayoutSaveOrder, new fSaveOrder(), "save_order")
                        .commit();
            }
        });

        textViewOrderType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeViewTextViewOrderType();
            }
        });


        textViewCartPrintBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RealmResults<SaveOrder> realmResultSaveOrderPrint = realm.where(SaveOrder.class)
                        .equalTo("saveOrderTransactionID", sessionManager.getTansactionID())
                        .findAll();
                String dateAndTime = dateAndTime();
                if (realmResultSaveOrderPrint.size() != 0) {
                    printBillTransactionBeforePay(
                            realm,
                            sessionManager.getTansactionID(),
                            realmResultSaveOrderPrint.get(0).getSaveOrderNumberTable(),
                            dateAndTime.substring(0, 10),
                            dateAndTime.substring(11, 19),
                            textViewSubTotal.getText().toString(),
                            textViewDiscount.getText().toString(),
                            textViewService.getText().toString(),
                            textViewCartVatTotal.getText().toString(),
                            buttonNext.getText().toString().replace("PAY Rp. ", "")
                    );
                } else {
                    printBillTransactionBeforePay(
                            realm,
                            sessionManager.getTansactionID(),
                            Declaration.NULL_DATA,
                            dateAndTime.substring(0, 10),
                            dateAndTime.substring(11, 19),
                            textViewSubTotal.getText().toString(),
                            textViewDiscount.getText().toString(),
                            textViewService.getText().toString(),
                            textViewCartVatTotal.getText().toString(),
                            buttonNext.getText().toString().replace("PAY Rp. ", "")
                    );
                }
            }
        });

//        /**
//         * handle button close save order layout click event
//         */
//        imageViewCloseSaveOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                linearLayoutRadioButtonCashRegister.setVisibility(View.VISIBLE);
//                linearLayoutGridViewCashRegister.setVisibility(View.VISIBLE);
//                unSelectAllDataInTable(realm);
//                linearLayoutSaveOrder.setVisibility(View.INVISIBLE);
//            }
//        });

        /**
         * handle changing of realm in database transaction detail that will view in cart
         * according the transaction id
         */
        realmResultTransactionDetail = realm.where(TransactionDetail.class).findAll();
        RealmResults<TransactionDetail> realmResultsTransDetailWithTrxID = realm.
                where(TransactionDetail.class)
                .equalTo("transactionDetailID", sessionManager.getTansactionID()).findAll();

        realmChangeListenerCart = new RealmChangeListener() {
            @Override
            public void onChange() {
                RealmResults<TransactionDetail> realmResultsTransDetail = realm.
                        where(TransactionDetail.class)
                        .equalTo("transactionDetailID", sessionManager.getTansactionID()).findAll();
                if (realmResultsTransDetail.size() != 0) {
                    //Toast.makeText(getApplicationContext(), "Transaction Detail is Changed", Toast.LENGTH_SHORT).show();
                    generateViewCart(
                            sessionManager.getTansactionID(),
                            realmResultsTransDetail,
                            linearLayoutCartListView,
                            linearLayoutCartEmpty,
                            textViewCartTransactionID,
                            listViewCartInRegister,
                            linearLayoutCartBottom,
                            textViewDiscount,
                            textViewSubTotal,
                            textViewService,
                            textViewCartVatTotal,
                            buttonNext
                    );
                }
            }
        };
        realmResultTransactionDetail.addChangeListener(realmChangeListenerCart);

        generateViewCart(
                sessionManager.getTansactionID(),
                realmResultsTransDetailWithTrxID,
                linearLayoutCartListView,
                linearLayoutCartEmpty,
                textViewCartTransactionID,
                listViewCartInRegister,
                linearLayoutCartBottom,
                textViewDiscount,
                textViewSubTotal,
                textViewService,
                textViewCartVatTotal,
                buttonNext
        );


        /**
         * handle changing of realm in database save order
         */
        final RealmResults<SaveOrder> realmResultSaveOrder = realm.where(SaveOrder.class).findAll();
        realmChangeListenerSaveOrder = new RealmChangeListener() {
            @Override
            public void onChange() {
                setVisibilityLoadSaveOrder(realmResultSaveOrder);
            }
        };
        realmResultSaveOrder.addChangeListener(realmChangeListenerSaveOrder);
        setVisibilityLoadSaveOrder(realmResultSaveOrder);

//
//
//        /**
//         * handle grid view save order click event
//         */
//        gridViewSaveOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                if (isFillWithOrder(realmResultSaveOrder,position)){
//                    sessionManager.setTransactionId(transactionIdFromSaveOrder(realmResultSaveOrder,position));
//                    textViewTestingTransactionID.setText(sessionManager.getTansactionID());
//                    realmResultTransactionDetail = realm.where(TransactionDetail.class).equalTo("transactionDetailID",sessionManager.getTansactionID()).findAll();
//                    generateViewCart(
//                            sessionManager.getTansactionID(),
//                            realmResultTransactionDetail,
//                            linearLayoutCartListView,
//                            linearLayoutCartEmpty,
//                            textViewCartTransactionID,
//                            listViewCartInRegister,
//                            linearLayoutCartBottom,
//                            textViewDiscount,
//                            textViewSubTotal,
//                            textViewService,
//                            textViewCartVatTotal,
//                            buttonNext
//                    );
//                    //showAlertDialogNullEvent(CashRegisterActivity.this,"Warning","Are you sure? Your activity can erase previous order.");
//                }else {
//                    if (orderAlreadySaved(sessionManager.getTansactionID(),realmResultSaveOrder)){
//                        Toast.makeText(CashRegisterActivity.this, "You already save this order.", Toast.LENGTH_SHORT).show();
//                    }else {
//                        updateSaveOrderToDatabase(realm,stringOf(position+1),sessionManager.getTansactionID());
//                        generateNewTransactionID();
//                        changeViewSaveOrder();
//                        clearCart();
//                    }
//                }
//            }
//        });

        /**
         * handle button load order click event
         */
        imageViewLoadOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeViewSaveOrder();
            }
        });

        /**
         * handle button transaction icmp event
         */
        buttonConnectToIcmpCommendSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initService();
                showProgressDialog();
                runDoTransactionEx(
                        revertValuePattern(textViewLayoutBillTotalAmount.getText().toString()),
                        "C",
                        "2",
                        "2",
                        "",
                        "");
            }
        });

        /**
         * check database temporary from transaction before
         */
        RealmResults<TransactionDetailTemporary> realmResultsTransactionDetailTemporary = realm
                .where(TransactionDetailTemporary.class).findAll();
        if (realmResultsTransactionDetailTemporary.size() != 0) {
            sessionManager.setTransactionId(realmResultsTransactionDetailTemporary.get(0).getTransactionTemporaryDetailID());
            RealmResults<TransactionDetail> realmResultsTransDetailTrxID = realm.
                    where(TransactionDetail.class)
                    .equalTo("transactionDetailID", sessionManager.getTansactionID()).findAll();
            generateViewCart(
                    sessionManager.getTansactionID(),
                    realmResultsTransDetailTrxID,
                    linearLayoutCartListView,
                    linearLayoutCartEmpty,
                    textViewCartTransactionID,
                    listViewCartInRegister,
                    linearLayoutCartBottom,
                    textViewDiscount,
                    textViewSubTotal,
                    textViewService,
                    textViewCartVatTotal,
                    buttonNext
            );
        }

        buttonRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTransactionIdFromSaveOrder(realm, sessionManager.getTansactionID());
                deleteOrderFromDatabaseTemporary(realm);
                String charge = String.valueOf(0);
                saveTransactionToDatabase(
                        realm,
                        "",
                        sessionManager.getTansactionID(),
                        session.get(SessionManager.KEY_CASHIER_USERNAME_ID),
                        session.get(SessionManager.KEY_CASHIER_USERNAME),
                        price,
                        quantityAllItem(realm, sessionManager.getTansactionID()),
                        revertValuePattern(textViewCartVatTotal.getText().toString()),
                        revertValuePattern(textViewService.getText().toString()),
                        revertValuePattern(textViewDiscount.getText().toString()),
                        revertValuePattern(textViewSubTotal.getText().toString()),
                        dateAndTime(),
                        "Point",
                        revertValuePattern(textViewLayoutBillInputAmount.getText().toString()),
                        charge,
                        sessionManager.getTableNumber());

                connectTransactionToDrawerDatabase(realm, price, true);

                Intent mIntent = new Intent(CashRegisterActivity.this, PaymentSuccess.class);
                mIntent.putExtra("transaction_id", sessionManager.getTansactionID());
                mIntent.putExtra("total_change", convertValuePattern(charge));
                startActivity(mIntent);
                finish();
            }
        });

    }

    private void generateViewCategory() {
        clearCategoryInView();
        if (getCategoryFromDatabase(realm).size() == 0) {

        } else if (getCategoryFromDatabase(realm).size() <= 9) {
            for (int i = 0; i < getCategoryFromDatabase(realm).size(); i++) {
                if (!getCategoryFromDatabase(realm).get(i).equals("")) {
                    radioButtonArray[i].setText(getCategoryFromDatabase(realm).get(i));
                    radioButtonArray[i].setVisibility(View.VISIBLE);
                }
            }
            radioButtonArray[getCategoryFromDatabase(realm).size()].setText("Other");
            radioButtonArray[getCategoryFromDatabase(realm).size()].setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < 9; i++) {
                if (!getCategoryFromDatabase(realm).get(i).equals("")) {
                    radioButtonArray[i].setText(getCategoryFromDatabase(realm).get(i));
                }
            }
            radioButtonArray[getCategoryFromDatabase(realm).size()].setText("Other");
            radioButtonArray[getCategoryFromDatabase(realm).size()].setVisibility(View.VISIBLE);
        }
    }

    private void clearCategoryInView() {
        for (int i = 0; i < radioButtonArray.length; i++) {
            radioButtonArray[i].setText("");
            radioButtonArray[i].setVisibility(View.INVISIBLE);
        }
    }

    private void generateViewPoint() {
        RealmResults<Merchant> realmResultsMerchant = realm
                .where(Merchant.class)
                .findAll();

        RealmResults<Outlet> realmResultsOutlet = realm
                .where(Outlet.class)
                .equalTo("outletID", session.get(SessionManager.KEY_OUTLET_ID))
                .findAll();

        String total_amount = buttonNext.getText().toString().replace("PAY Rp. ", "");
        buttonNext.setBackground(getResources().getDrawable(R.drawable.charge));

        String value = String.valueOf(
                jsonObject(
                        sessionManager.getTansactionID(),
                        revertValuePattern(total_amount),
                        realmResultsMerchant.get(0).getMerchantName(),
                        realmResultsOutlet.get(0).getOutletName(),
                        revertValuePattern(total_amount).substring(0, revertValuePattern(total_amount).length() - 1),
                        "redeem"
                )
        );

        try {
            Bitmap bmp = textToImageEncode(value);
            imageViewQrCode.setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

    private JSONObject jsonObject(String transaction_id, String total_amount,
                                  String merchant_name, String store_name,
                                  String total_point_acquired, String flag) {

        JSONObject obj = new JSONObject();
        try {
            obj.put("flag", flag);
            obj.put("transaction_id", transaction_id);
            obj.put("total_amount", total_amount);
            obj.put("merchant_name", merchant_name);
            obj.put("store_name", store_name);
            obj.put("total_point_acquired", total_point_acquired);

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
                        getResources().getColor(R.color.QRCodeBlackColor) : getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 200, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

    private void changeViewTextViewOrderType() {
        if (textViewOrderType.getText().toString().equals(
                getResources().getString(R.string.for_here_upper))) {
            textViewOrderType.setText(getResources().getString(R.string.take_away_upper));
        } else if (textViewOrderType.getText().toString().equals(
                getResources().getString(R.string.take_away_upper))) {
            textViewOrderType.setText(getResources().getString(R.string.for_here_upper));
        }
    }

    public void closeSaveOrderView() {
        linearLayoutRadioButtonCashRegister.setVisibility(View.VISIBLE);
        linearLayoutGridViewCashRegister.setVisibility(View.VISIBLE);
        unSelectAllDataInTable(realm);
        linearLayoutSaveOrder.setVisibility(View.INVISIBLE);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutSaveOrder);
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    public void disableFromMergeTable() {
        imageViewLoadOrder.setEnabled(false);
        textViewCartClearCart.setEnabled(false);
        textViewOrderType.setEnabled(false);
        listViewCartInRegister.setEnabled(false);
        textViewCartAddDiscount.setEnabled(false);
        textViewCartSaveOrder.setEnabled(false);
        buttonNext.setEnabled(false);
    }

    public void enableFromMergeTable() {
        imageViewLoadOrder.setEnabled(true);
        textViewCartClearCart.setEnabled(true);
        textViewOrderType.setEnabled(true);
        listViewCartInRegister.setEnabled(true);
        textViewCartAddDiscount.setEnabled(true);
        textViewCartSaveOrder.setEnabled(true);
        buttonNext.setEnabled(true);
    }

    public boolean cartVisible() {
        return linearLayoutCartEmpty.getVisibility() != View.VISIBLE;
    }

    private boolean orderAlreadySaved(String transaction_id, RealmResults<SaveOrder> realm_result_save_order) {
        boolean value = false;
        for (int i = 0; i < realm_result_save_order.size(); i++) {
            if (realm_result_save_order.get(i).getSaveOrderTransactionID().equals(transaction_id)) {
                value = true;
            }
        }
        return value;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume", "Activity");
        validateCurrentDrawer();
        if (sessionManager.getAlreadyConnected().equals("true")) {
            buttonConnectToIcmpCommendSale.setEnabled(true);
            initService();
            Log.e("Test2", sessionManager.getAlreadyConnected());
        } else {
            buttonConnectToIcmpCommendSale.setEnabled(false);
        }
        if (isCompanionConnected()) {
//            mReleaseService = 1;
//            sessionManager.setIsConnected(mReleaseService);
//            buttonConnectToIcmpCommendSale.setEnabled(true);
//            Log.e("Test7",sessionManager.getAlreadyConnected());
        } else {
//            mReleaseService = 0;
//            sessionManager.setIsConnected(mReleaseService);
//            buttonConnectToIcmpCommendSale.setEnabled(false);
//            Log.e("Test8",sessionManager.getAlreadyConnected());
        }
    }

    private void setVisibilityLoadSaveOrder(RealmResults<SaveOrder> realmResultSaveOrder) {
        if (showLoadOrderButton(realmResultSaveOrder)) {
            imageViewLoadOrder.setVisibility(View.VISIBLE);
        } else {
            imageViewLoadOrder.setVisibility(View.INVISIBLE);
        }
    }

    private void validateCurrentDrawer() {
        if (!sessionManager.isDrawerLoggedIn()) {
            RealmResults<Login> realmResultsLogin = realm
                    .where(Login.class)
                    .equalTo("username", session.get(SessionManager.KEY_CASHIER_USERNAME_ID))
                    .findAll();

            RealmResults<Drawer> realmResultsDrawer = realm.where(Drawer.class).findAll();
            if (realmResultsDrawer.size() == 0) {
                RealmCreateDatabaseTableDrawer(
                        realm,
                        0,
                        realmResultsLogin.get(0).getOutletID(),
                        realmResultsLogin.get(0).getUsername(),
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                );
                dialogCreateDrawer.setDrawerID(0);
                dialogCreateDrawer.show(getFragmentManager(), "show_create_drawer_dialog");
            } else {
                RealmCreateDatabaseTableDrawer(
                        realm,
                        realmResultsDrawer.size(),
                        realmResultsLogin.get(0).getOutletID(),
                        realmResultsLogin.get(0).getUsername(),
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                );
                dialogCreateDrawer.setDrawerID(realmResultsDrawer.size());
                dialogCreateDrawer.show(getFragmentManager(), "show_create_drawer_dialog");
            }
            sessionManager.setCashierUsername(realmResultsLogin.get(0).getUsername());
        }
    }


    @Override
    public void onBackPressed() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(home);
    }

    /**
     * Get category from database
     */
    private ArrayList<String> getCategoryFromDatabase(Realm realm) {
        RealmResults<Category> categoryRealmResults = realm.where(Category.class).findAll();
        ArrayList<String> listCategory = new ArrayList<String>();
        for (int i = 0; i < categoryRealmResults.size(); i++) {
            Category category = categoryRealmResults.get(i);
            if (!category.getCategory().equals("")) {
                listCategory.add(category.getCategory());
            }
        }
        return listCategory;
    }

    /**
     * Get price from database with item id and name with primary key
     */
    private String getPriceFromDatabase(Realm realm, String item_id, String item_name) {
        RealmResults<Item> itemRealmResults = realm.where(Item.class)
                .equalTo("itemID", item_id)
                .equalTo("itemName", item_name)
                .findAll();
        Item item = itemRealmResults.get(0);
        return item.getItemPrice();
    }

    /**
     * generate view item from database according category
     */

    private void generateGridViewItem(Realm realm, String category, String outlet_id) {
        RealmResults<Item> itemRealmResults = realm.where(Item.class).equalTo("itemCategory", category).findAll();
        ArrayList<HashMap<String, String>> arrayListItemName = new ArrayList<>();
        for (int i = 0; i < itemRealmResults.size(); i++) {
            if (itemRealmResults.get(i).getItemOutletID().equals(outlet_id)
                    || itemRealmResults.get(i).getItemOutletID().equals(Declaration.ALL_OUTLET)) {
                Item item = itemRealmResults.get(i);
                String itemVariantWithDelimiter = "";
                String itemModifierGroupWithDelimiter = "";
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(KEY_ITEM_ID, item.getItemID());
                hashMap.put(KEY_ITEM_NAME, item.getItemName());
                hashMap.put(KEY_ITEM_PRICE, item.getItemPrice());
                hashMap.put(KEY_ITEM_DISCOUNT, item.getItemDiscount());
                hashMap.put(KEY_ITEM_CATEGORY, item.getItemCategory());
                hashMap.put(KEY_ITEM_STATUS_TAX, item.getItemStatusTax());

                RealmList<ItemVariant> realmListItemVariant = item.getItemVarian();
                for (int j = 0; j < realmListItemVariant.size(); j++) {
                    itemVariantWithDelimiter += realmListItemVariant.get(j).getItemVariantID() + Declaration.DELIMITER;
                }
                hashMap.put(KEY_ITEM_VARIANT, itemVariantWithDelimiter);

                RealmList<ItemModifierGroup> realmListItemModifierGroup = item.getItemModifier();
                for (int k = 0; k < realmListItemModifierGroup.size(); k++) {
                    itemModifierGroupWithDelimiter += realmListItemModifierGroup.get(k).getItemModifierGroupID() + Declaration.DELIMITER;
                }
                hashMap.put(KEY_ITEM_MODIFIER_GROUP, itemModifierGroupWithDelimiter);

                hashMap.put(KEY_ITEM_TOTAL, "1");
                hashMap.put(KEY_ITEM_PICTURE_URL, Declaration.IMAGE_OUTPUT_PATH + item.getItemID() + ".png");
                arrayListItemName.add(hashMap);
            }
        }
        ArrayListItemNew = arrayListItemName;
        gridViewItemFromCategory.setAdapter(new GridViewAdapterItemCashRegister(
                CashRegisterActivity.this,
                arrayListItemName
        ));
        ((BaseAdapter) gridViewItemFromCategory.getAdapter()).notifyDataSetChanged();

    }


    /**
     * Get item from database according category
     */
//    private static ArrayList<HashMap<String, String>> ArrayListItem(Realm realm, String category,
//                                                                    String outlet_id){
//        Log.e("array kepannggul","dalam array");
//        RealmResults<Item> itemRealmResults = realm.where(Item.class).equalTo("itemCategory", category).findAll();
//        ArrayList<HashMap<String, String>> arrayListItemName = new ArrayList<>();
//        for (int i = 0; i < itemRealmResults.size(); i++) {
//            if (
//                    itemRealmResults.get(i).getItemOutletID().equals(outlet_id)
//                    || itemRealmResults.get(i).getItemOutletID().equals(Declaration.ALL_OUTLET)){
//                Item item = itemRealmResults.get(i);
//                String itemVariantWithDelimiter = "";
//                String itemModifierGroupWithDelimiter = "";
//                HashMap<String,String> hashMap =new HashMap<>();
//                hashMap.put(KEY_ITEM_ID,item.getItemID());
//                hashMap.put(KEY_ITEM_NAME,item.getItemName());
//                hashMap.put(KEY_ITEM_PRICE,item.getItemPrice());
//                hashMap.put(KEY_ITEM_DISCOUNT,item.getItemDiscount());
//                hashMap.put(KEY_ITEM_CATEGORY,item.getItemCategory());
//                hashMap.put(KEY_ITEM_STATUS_TAX,item.getItemStatusTax());
//
//                RealmList<ItemVariant> realmListItemVariant = item.getItemVarian();
//                for (int j = 0; j < realmListItemVariant.size(); j++) {
//                    itemVariantWithDelimiter += realmListItemVariant.get(j).getItemVariantID() + Declaration.DELIMITER;}
//                hashMap.put(KEY_ITEM_VARIANT,itemVariantWithDelimiter);
//
//                RealmList<ItemModifierGroup> realmListItemModifierGroup = item.getItemModifier();
//                for (int k = 0; k < realmListItemModifierGroup.size(); k++) {
//                    itemModifierGroupWithDelimiter += realmListItemModifierGroup.get(k).getItemModifierGroupID() + Declaration.DELIMITER;}
//                hashMap.put(KEY_ITEM_MODIFIER_GROUP,itemModifierGroupWithDelimiter);
//
//                hashMap.put(KEY_ITEM_TOTAL,"1");
//                hashMap.put(KEY_ITEM_PICTURE_URL,Declaration.IMAGE_OUTPUT_PATH+item.getItemID()+".png");
//                arrayListItemName.add(hashMap);
//            }
//        }
//        return arrayListItemName;
//    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_myShop:
                selectMenu(0);
                break;
            case R.id.nav_history:
                selectMenu(1);
                break;
            case R.id.nav_report:
                selectMenu(2);
                break;
//            case R.id.nav_inventory:
//                selectMenu(3);
//                break;
            case R.id.nav_setting:
                selectMenu(4);
                break;
//            case R.id.nav_payment:
//                selectMenu(5);
//                break;
            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void scrollMyListViewToBottom(final ListView myListView, final ListViewAdapterItemInCartCashRegister myListAdapter) {
        myListView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                myListView.setSelection(myListAdapter.getCount() - 1);
            }
        });
    }

    public void imageViewLoadOrderVisible() {
        imageViewLoadOrder.setVisibility(View.VISIBLE);
    }

    public void imageViewLoadOrderInvisible() {
        imageViewLoadOrder.setVisibility(View.INVISIBLE);
    }

    public LinearLayout linearLayoutCartListViewForSaveOrder() {
        return linearLayoutCartListView;
    }

    public LinearLayout linearLayoutCartEmptyForSaveOrder() {
        return linearLayoutCartEmpty;
    }

    public TextView textViewCartTransactionIDForSaveOrder() {
        return textViewCartTransactionID;
    }

    public ListView listViewCartInRegisterForSaveOrder() {
        return listViewCartInRegister;
    }

    public LinearLayout linearLayoutCartBottomForSaveOrder() {
        return linearLayoutCartBottom;
    }

    public TextView textViewDiscountForSaveOrder() {
        return textViewDiscount;
    }

    public TextView textViewSubTotalForSaveOrder() {
        return textViewSubTotal;
    }

    public TextView textViewServiceForSaveOrder() {
        return textViewService;
    }

    public TextView textViewCartVatTotalForSaveOrder() {
        return textViewCartVatTotal;
    }

    public Button buttonNextForSaveOrder() {
        return buttonNext;
    }

    private void changeViewInBill(TextView textView_discount, TextView textView_sub_total,
                                  TextView textView_service, TextView textView_tax_or_vat, Button button_next,
                                  String discount, String sub_total_price, String total_service,
                                  String total_tax_or_vat, String total_amount) {

        textView_sub_total.setText(convertValuePattern(sub_total_price));
        textView_service.setText(convertValuePattern(total_service));
        textView_tax_or_vat.setText(convertValuePattern(total_tax_or_vat));

        if (total_amount.equals("Rp. 0")) {
            button_next.setEnabled(false);
            button_next.setText("NO SALE");
            buttonNext.setBackground(getResources().getDrawable(R.drawable.skip_receipt));
        } else {
            button_next.setEnabled(true);
            String pay_total = "PAY " + total_amount;
            buttonNext.setBackground(getResources().getDrawable(R.drawable.charge));
            button_next.setText(pay_total);
        }

        if (!discount.equals("0")) {
            textViewCartAddDiscount.setText("DISCOUNT");
            textView_discount.setText(convertValuePattern(discount));
            textViewDiscountRP.setVisibility(View.VISIBLE);
            textView_discount.setVisibility(View.VISIBLE);
        } else {
            textViewCartAddDiscount.setText("+ ADD DISCOUNT");
            textViewDiscountRP.setVisibility(View.INVISIBLE);
            textView_discount.setVisibility(View.INVISIBLE);
        }
    }

    private String getServiceFromDatabase(Realm realm) {
        RealmResults<Service> realmResultsService = realm
                .where(Service.class)
                .equalTo("serviceOutletID", session.get(SessionManager.KEY_OUTLET_ID))
                .findAll();
        return realmResultsService.first().getServiceValue();
    }

    private String getTaxFromDatabase(Realm realm) {
        RealmResults<Tax> realmResultsTax = realm
                .where(Tax.class)
                .equalTo("taxOutletID", session.get(SessionManager.KEY_OUTLET_ID))
                .findAll();
        return realmResultsTax.get(0).getTaxValue();
    }

    private void changeLayoutBillInputAmount(TextView textView, String amount) {
        textView.setText(amount);
    }

    /**
     * Erase last character pin in layout
     */
    public String removeLastCharacter(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    private String macAddress() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        return wInfo.getMacAddress();
    }

    public void generateViewCart(String transaction_id,
                                 RealmResults<TransactionDetail> realm_results_transaction_detail,
                                 LinearLayout linear_layout_cart_list_view_order,
                                 LinearLayout linear_layout_cart_no_order,
                                 TextView text_view_cart_transaction_id,
                                 ListView list_view_cart,
                                 LinearLayout linear_layout_cart_bottom,
                                 TextView text_view_discount,
                                 TextView text_view_total,
                                 TextView text_view_service,
                                 TextView text_view_tax,
                                 Button button_next) {
        logger.addInfo("Function", "generateViewCart is Called");
        if (realm_results_transaction_detail.size() != 0) {
            LinkedList<HashMap<String, String>> linkedListTransactionDetailPremier = new LinkedList<>();
            RealmResults<TransactionDetail> realmResultTransactionDetail = realm.where(TransactionDetail.class)
                    .equalTo("transactionDetailID", transaction_id)
                    .findAll();

            linear_layout_cart_list_view_order.setVisibility(View.VISIBLE);
            linear_layout_cart_bottom.setVisibility(View.VISIBLE);
            linear_layout_cart_no_order.setVisibility(View.GONE);
            text_view_cart_transaction_id.setText(transaction_id);

            RealmResults<SaveOrder> realmResultSaveOrder = realm.where(SaveOrder.class)
                    .equalTo("saveOrderTransactionID", transaction_id)
                    .findAll();

            if (realmResultSaveOrder.size() != 0) {
                textViewCartTableNumber.setVisibility(View.VISIBLE);
                String table_number = "#" + realmResultSaveOrder.get(0).getSaveOrderNumberTable();
                textViewCartTableNumber.setText(table_number);
            } else {
                textViewCartTableNumber.setVisibility(View.INVISIBLE);
            }


            for (int i = 0; i < realmResultTransactionDetail.size(); i++) {
                String modifierNameWithDelimiter = "";
                String discountIdWithDelimiter = "";

                TransactionDetail transactionDetail = realmResultTransactionDetail.get(i);

                if (realmResultTransactionDetail.get(i).getTransactionDetailItemID().contains("item") ) {

                    Log.e(TAG, "IF DOOOOOOOOOO");

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(KEY_TRANSACTION_ID, transactionDetail.getTransactionDetailID());
                    hashMap.put(KEY_ITEM_ID, transactionDetail.getTransactionDetailItemID());
                    hashMap.put(KEY_ITEM_NAME, ArrayListItemFromID(realm, transactionDetail.getTransactionDetailItemID()).getFirst().get(KEY_ITEM_NAME));
                    hashMap.put(KEY_ITEM_PRICE, transactionDetail.getTransactionDetailTotalPrice());
                    hashMap.put(KEY_ITEM_CATEGORY, ArrayListItemFromID(realm, transactionDetail.getTransactionDetailItemID()).getFirst().get(KEY_ITEM_CATEGORY));
                    hashMap.put(KEY_ITEM_TOTAL, transactionDetail.getTransactionDetailQuantity());
                    if (!transactionDetail.getTransactionDetailVariantID().equals(Declaration.NULL_DATA)) {
                        LinkedList<HashMap<String, String>> linkedListVariant = linkedListVariantFromID(realm, transactionDetail.getTransactionDetailVariantID());
                        hashMap.put(KEY_VARIANT_ID, linkedListVariant.getFirst().get(KEY_VARIANT_ID));
                        hashMap.put(KEY_VARIANT_NAME, linkedListVariant.getFirst().get(KEY_VARIANT_NAME));
                    } else {
                        hashMap.put(KEY_VARIANT_ID, Declaration.NULL_DATA);
                        hashMap.put(KEY_VARIANT_NAME, Declaration.NULL_DATA);
                    }

                    RealmList<TransactionDetailModifier> realmListTransactionDetailModifier = transactionDetail.getTransactionDetailModifierID();
                    for (int j = 0; j < realmListTransactionDetailModifier.size(); j++) {

                        String variable = Declaration.DELIMITER + realmListTransactionDetailModifier.get(j).getTransactionDetailModifierID();
                        if (!variable.equals(Declaration.DELIMITER)) {
                            variable = variable.replace(Declaration.DELIMITER, "");
                            modifierNameWithDelimiter +=
                                    linkedListModifierGroupFromID(
                                            realm
                                            , linkedListModifierFromID(
                                                    realm
                                                    , variable
                                            ).getFirst().get(KEY_MODIFIER_GROUP_ID)
                                    ).getFirst().get(KEY_MODIFIER_GROUP_NAME)

                                            + " " + linkedListModifierFromID(realm, variable).getFirst().get(KEY_MODIFIER_NAME)
                                            + " Rp. " + convertValuePattern(linkedListModifierFromID(realm, variable).getFirst().get(KEY_MODIFIER_PRICE))
                                            + Declaration.DELIMITER;
                        }
                    }
                    hashMap.put(KEY_ITEM_MODIFIER_NAME, modifierNameWithDelimiter);

                    RealmList<TransactionDetailDiscount> realmListTransactionDetailDiscount = transactionDetail.getTransactionDetailDiscountID();
                    for (int k = 0; k < realmListTransactionDetailDiscount.size(); k++) {
                        if (!realmListTransactionDetailDiscount.get(k).equals(null)) {
                            discountIdWithDelimiter += realmListTransactionDetailDiscount.get(k).getDiscountID()
                                    + Declaration.DELIMITER;
                        }
                    }
                    hashMap.put(KEY_ITEM_DISCOUNT, discountIdWithDelimiter);

                    linkedListTransactionDetailPremier.addFirst(hashMap);
                } else {
                    Log.e(TAG, "ELSE DOOOOOOOOOO");
//                    TransactionDetail transactionDetail = realmResultTransactionDetail.get(i);
//                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(KEY_TRANSACTION_ID, transactionDetail.getTransactionDetailID());
                    hashMap.put(KEY_ITEM_ID, transactionDetail.getTransactionDetailItemID());
                    hashMap.put(KEY_ITEM_PRICE, transactionDetail.getTransactionDetailTotalPrice());
                    hashMap.put(KEY_ITEM_TOTAL, transactionDetail.getTransactionDetailQuantity());
                    hashMap.put(KEY_ITEM_MODIFIER_NAME, modifierNameWithDelimiter);
                    hashMap.put(KEY_ITEM_DISCOUNT, discountIdWithDelimiter);

                    linkedListTransactionDetailPremier.addFirst(hashMap);
                }
            }

            LinkedList<HashMap<String, String>> linkedListTransactionDetailForViewInListView = new LinkedList<>();

            for (int o = 0; o < linkedListTransactionDetailPremier.size(); o++) {
                if (linkedListTransactionDetailPremier.get(o).get(KEY_ITEM_ID).contains("item")) {
                    linkedListTransactionDetailForViewInListView.add(linkedListTransactionDetailPremier.get(o));
                }
            }

            listViewAdapterItemInCartCashRegister = new ListViewAdapterItemInCartCashRegister(
                    this,
                    linkedListTransactionDetailForViewInListView);

            list_view_cart.setAdapter(listViewAdapterItemInCartCashRegister);
            listViewAdapterItemInCartCashRegister.notifyDataSetChanged();

            Log.e("test", "BOLONG: " + linkedListTransactionDetailPremier + "");

            changeViewInBill(
                    text_view_discount,
                    text_view_total,
                    text_view_service,
                    text_view_tax,
                    button_next,
                    sumOfDiscount(realm, linkedListTransactionDetailPremier),
                    sumOfPrice(linkedListTransactionDetailPremier),
                    sumOfService(realm, sumOfPrice(linkedListTransactionDetailPremier)),
                    sumOfTax(realm, sumOfPrice(linkedListTransactionDetailPremier), sumOfService(realm, sumOfPrice(linkedListTransactionDetailPremier))),
                    "Rp. " + convertValuePattern(
                            stringOf(
                                    integerOf(sumOfPrice(linkedListTransactionDetailPremier))
                                            + integerOf(sumOfService(realm, sumOfPrice(linkedListTransactionDetailPremier)))
                                            + integerOf(sumOfTax(realm, sumOfPrice(linkedListTransactionDetailPremier), sumOfService(realm, sumOfPrice(linkedListTransactionDetailPremier))))
                            )
                    )
            );

            price = stringOf(
                    integerOf(sumOfPrice(linkedListTransactionDetailPremier))
                            + integerOf(sumOfService(realm, sumOfPrice(linkedListTransactionDetailPremier)))
                            + integerOf(sumOfTax(realm, sumOfPrice(linkedListTransactionDetailPremier), sumOfService(realm, sumOfPrice(linkedListTransactionDetailPremier)))));
        } else {
            linear_layout_cart_list_view_order.setVisibility(View.INVISIBLE);
            linear_layout_cart_bottom.setVisibility(View.INVISIBLE);
            linear_layout_cart_no_order.setVisibility(View.VISIBLE);
            button_next.setText("NO SALE");
            button_next.setEnabled(false);
            button_next.setBackground(getResources().getDrawable(R.drawable.skip_receipt));

        }

    }

    /**
     * Get item from database according item id
     */
    private static LinkedList<HashMap<String, String>> ArrayListItemFromID(Realm realm, String id) {
        RealmResults<Item> itemRealmResults = realm.where(Item.class).equalTo("itemID", id).findAll();
        LinkedList<HashMap<String, String>> arrayListItemName = new LinkedList<>();
        for (int i = 0; i < itemRealmResults.size(); i++) {
            Item item = itemRealmResults.get(i);
            String itemVariantWithDelimiter = "";
            String itemModifierGroupWithDelimiter = "";
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(KEY_ITEM_ID, item.getItemID());
            hashMap.put(KEY_ITEM_NAME, item.getItemName());
            hashMap.put(KEY_ITEM_PRICE, item.getItemPrice());
            hashMap.put(KEY_ITEM_DISCOUNT, item.getItemDiscount());
            hashMap.put(KEY_ITEM_CATEGORY, item.getItemCategory());
            hashMap.put(KEY_ITEM_STATUS_TAX, item.getItemStatusTax());

            RealmList<ItemVariant> realmListItemVariant = item.getItemVarian();
            for (int j = 0; j < realmListItemVariant.size(); j++) {
                itemVariantWithDelimiter += realmListItemVariant.get(j).getItemVariantID() + Declaration.DELIMITER;
            }
            hashMap.put(KEY_ITEM_VARIANT, itemVariantWithDelimiter);

            RealmList<ItemModifierGroup> realmListItemModifierGroup = item.getItemModifier();
            for (int k = 0; k < realmListItemModifierGroup.size(); k++) {
                itemModifierGroupWithDelimiter += realmListItemModifierGroup.get(k).getItemModifierGroupID() + Declaration.DELIMITER;
            }
            hashMap.put(KEY_ITEM_MODIFIER_GROUP, itemModifierGroupWithDelimiter);

            hashMap.put(KEY_ITEM_TOTAL, "1");
            hashMap.put(KEY_ITEM_PICTURE_URL, Declaration.IMAGE_OUTPUT_PATH + item.getItemID() + ".png");
            arrayListItemName.add(hashMap);
        }
        return arrayListItemName;
    }

    private String sumOfSubTotal(LinkedList<HashMap<String, String>> linkedList) {
        int sum = 0;
        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i).get(KEY_ITEM_ID).contains("item")) {
                sum += integerOf(linkedList.get(i).get(KEY_ITEM_PRICE));
            }
        }
        return stringOf(sum);
    }

    private String sumOfPrice(LinkedList<HashMap<String, String>> linkedList) {
        return stringOf(integerOf(sumOfSubTotal(linkedList)) - integerOf(sumOfDiscount(realm, linkedList)));
    }

    private String sumOfService(Realm realm, String total_amount) {
        return stringOf((int) (integerOf(total_amount) * integerOf(getServiceFromDatabase(realm)) / 100.0));
    }

    private String sumOfTax(Realm realm, String total_amount, String service) {
        return stringOf((int) ((integerOf(total_amount) + integerOf(service)) * (integerOf(getTaxFromDatabase(realm)) / 100.0)));
    }

    private String sumOfDiscount(Realm realm, LinkedList<HashMap<String, String>> linkedList) {
        int discountPrice = 0;
        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i).get(KEY_ITEM_ID).contains("item")) {
                for (String discountID : linkedList.get(i).get(KEY_ITEM_DISCOUNT).split(Declaration.DELIMITER)) {
                    if (!discountID.equals(Declaration.DELIMITER) && !discountID.equals("") && !discountID.equals(null)) {
                        RealmResults<DiscountMaster> discountMasterRealmResult = realm.where(DiscountMaster.class)
                                .equalTo("discountMasterID", discountID)
                                .findAll();
                        if (discountMasterRealmResult.get(0).getDiscountMasterType().equals("Persen")) {
                            discountPrice += (integerOf(discountMasterRealmResult.get(0).getDiscountMasterValue()) * integerOf(linkedList.get(i).get(KEY_ITEM_PRICE))) / 100.0;
                        }
                    }
                }
            } else {
                discountPrice += integerOf(linkedList.get(i).get(KEY_ITEM_PRICE));
            }

        }
        return stringOf(discountPrice);
    }

    private LinkedList<HashMap<String, String>> linkedListVariantFromID(Realm realm, String id) {
        RealmResults<Variant> variantRealmResults = realm.where(Variant.class).equalTo("VariantID", id).findAll();
        LinkedList<HashMap<String, String>> linkedListVariant = new LinkedList<>();
        for (int i = 0; i < variantRealmResults.size(); i++) {
            Variant variant = variantRealmResults.get(i);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(KEY_VARIANT_ID, variant.getVariantID());
            hashMap.put(KEY_VARIANT_NAME, variant.getVariantName());
            linkedListVariant.add(hashMap);
        }
        return linkedListVariant;
    }

    private LinkedList<HashMap<String, String>> linkedListModifierFromID(Realm realm, String id) {
        RealmResults<ModifierDetail> modifierDetailRealmResults = realm.where(ModifierDetail.class).equalTo("modifierDetailID", id).findAll();
        LinkedList<HashMap<String, String>> linkedListModifierDetail = new LinkedList<>();
        for (int i = 0; i < modifierDetailRealmResults.size(); i++) {
            ModifierDetail modifierDetail = modifierDetailRealmResults.get(0);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(KEY_MODIFIER_ID, modifierDetail.getModifierDetailID());
            hashMap.put(KEY_MODIFIER_NAME, modifierDetail.getModifierDetailName());
            hashMap.put(KEY_MODIFIER_PRICE, modifierDetail.getModifierDetailPrice());
            hashMap.put(KEY_MODIFIER_GROUP_ID, modifierDetail.getModifierDetailGroupID());
            linkedListModifierDetail.add(hashMap);
        }
        return linkedListModifierDetail;
    }


    private LinkedList<HashMap<String, String>> linkedListModifierGroupFromID(Realm realm, String id) {
        RealmResults<ModifierGroup> modifierGroupRealmResults = realm.where(ModifierGroup.class).equalTo("modifierGroupID", id).findAll();
        LinkedList<HashMap<String, String>> linkedListModifierGroup = new LinkedList<>();
        for (int i = 0; i < modifierGroupRealmResults.size(); i++) {
            ModifierGroup modifierGroup = modifierGroupRealmResults.get(0);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(KEY_MODIFIER_GROUP_ID, modifierGroup.getModifierGroupID());
            hashMap.put(KEY_MODIFIER_GROUP_NAME, modifierGroup.getModifierGroupName());
            linkedListModifierGroup.add(hashMap);
        }
        return linkedListModifierGroup;
    }

    private boolean isLastItem(Realm realm, String item_id, String total_price) {
        boolean value = true;
        RealmResults<TransactionDetail> realmResultTransactionDetail = realm.where(TransactionDetail.class).findAll();
        if (realmResultTransactionDetail.size() > 0) {
            TransactionDetail transactionDetail = realmResultTransactionDetail.get(realmResultTransactionDetail.size() - 1);
            value = item_id.equals(transactionDetail.getTransactionDetailItemID()) &&
                    sessionManager.getTansactionID().equals(transactionDetail.getTransactionDetailID());
        } else {
            value = false;
        }
        return value;
    }

    private boolean isSame(LinkedList<String> linkedListFirst, LinkedList<String> linkedListSecond) {
        boolean value = true;
        if (linkedListFirst.size() != linkedListSecond.size()) {
            value = false;
        } else {
            for (int i = 0; i < linkedListFirst.size(); i++) {
                if (!linkedListSecond.contains(linkedListFirst.get(i))) {
                    value = false;
                }
            }
        }

        return value;
    }

    private void updateQuantityItemOrder(Realm realm, String quantity) {
        RealmResults<TransactionDetail> realmResultTransactionDetail = realm.where(TransactionDetail.class).findAll();
        TransactionDetail transactionDetail = realmResultTransactionDetail.get(realmResultTransactionDetail.size() - 1);

        String newQuantity = stringOf(integerOf(quantity) + integerOf(transactionDetail.getTransactionDetailQuantity()));
        String newPrice = stringOf((integerOf(transactionDetail.getTransactionDetailTotalPrice()) / integerOf(transactionDetail.getTransactionDetailQuantity())) * integerOf(newQuantity));

        realm.beginTransaction();
        transactionDetail.setTransactionDetailQuantity(newQuantity);
        transactionDetail.setTransactionDetailTotalPrice(newPrice);
        realm.commitTransaction();
    }

    private void updateQuantityItemOrderInDatabaseTemporary(Realm realm, String quantity) {
        RealmResults<TransactionDetailTemporary> realmResultTransactionDetailTemporary = realm
                .where(TransactionDetailTemporary.class).findAll();
        TransactionDetailTemporary transactionDetailTemporary = realmResultTransactionDetailTemporary.get(realmResultTransactionDetailTemporary.size() - 1);
        String newQuantity = stringOf(integerOf(quantity) + integerOf(transactionDetailTemporary.getTransactionDetailTemporaryQuantity()));
        String newPrice = stringOf((integerOf(transactionDetailTemporary.getTransactionDetailTemporaryTotalPrice()) / integerOf(transactionDetailTemporary.getTransactionDetailTemporaryQuantity())) * integerOf(newQuantity));
        realm.beginTransaction();
        transactionDetailTemporary.setTransactionDetailTemporaryQuantity(newQuantity);
        transactionDetailTemporary.setTransactionDetailTemporaryTotalPrice(newPrice);
        realm.commitTransaction();
    }

    private void saveOrderToDatabase(Realm realm, String transaction_id, String item_id, String variant_id,
                                     String total_price, String quantity, LinkedList<String> modifier_id,
                                     LinkedList<String> discount_id) {
        realm.beginTransaction();
        TransactionDetail transactionDetail = realm.createObject(TransactionDetail.class);
        transactionDetail.setTransactionDetailID(transaction_id);
        transactionDetail.setTransactionDetailItemID(item_id);
        transactionDetail.setTransactionDetailVariantID(variant_id);
        transactionDetail.setTransactionDetailTotalPrice(revertValuePattern(total_price));
        transactionDetail.setTransactionDetailQuantity(quantity);
        for (int m = 0; m < modifier_id.size(); m++) {
            if (!modifier_id.get(m).contains("var")) {
                TransactionDetailModifier transactionDetailModifier = realm.createObject(TransactionDetailModifier.class);
                transactionDetailModifier.setTransactionDetailModifierID(modifier_id.get(m));
                transactionDetail.transactionDetailModifierID.add(transactionDetailModifier);
            }
        }

        for (int d = 0; d < discount_id.size(); d++) {
            TransactionDetailDiscount transactionDetailDiscount = realm.createObject(TransactionDetailDiscount.class);
            transactionDetailDiscount.setDiscountID(discount_id.get(d));
            transactionDetail.transactionDetailDiscountID.add(transactionDetailDiscount);
        }
        realm.commitTransaction();
    }

    private void saveOrderToDatabaseTemporary(Realm realm, String transaction_id, String item_id, String variant_id,
                                              String total_price, String quantity, LinkedList<String> modifier_id,
                                              LinkedList<String> discount_id) {
        realm.beginTransaction();
        TransactionDetailTemporary transactionDetailTemporary = realm.createObject(TransactionDetailTemporary.class);
        transactionDetailTemporary.setTransactionDetailTemporaryID(transaction_id);
        transactionDetailTemporary.setTransactionDetailTemporaryItemID(item_id);
        transactionDetailTemporary.setTransactionDetailTemporaryVariantID(variant_id);
        transactionDetailTemporary.setTransactionDetailTemporaryTotalPrice(revertValuePattern(total_price));
        transactionDetailTemporary.setTransactionDetailTemporaryQuantity(quantity);
        for (int m = 0; m < modifier_id.size(); m++) {
            if (!modifier_id.get(m).contains("var")) {
                TransactionDetailTemporaryModifier transactionDetailTemporaryModifier = realm
                        .createObject(TransactionDetailTemporaryModifier.class);
                transactionDetailTemporaryModifier.setTransactionDetailTemporaryModifierID(modifier_id.get(m));
                transactionDetailTemporary.transactionDetailTemporaryModifierID.add(transactionDetailTemporaryModifier);
            }
        }

        for (int d = 0; d < discount_id.size(); d++) {
            TransactionDetailTemporaryDiscount transactionDetailTemporaryDiscount = realm
                    .createObject(TransactionDetailTemporaryDiscount.class);
            transactionDetailTemporaryDiscount.setTransactionDetailTemporaryDiscountID(discount_id.get(d));
            transactionDetailTemporary.transactionDetailTemporaryDiscountID.add(transactionDetailTemporaryDiscount);
        }
        realm.commitTransaction();
    }

    private void deleteOrderFromDatabaseTemporary(Realm realm) {
        realm.beginTransaction();
        RealmResults<TransactionDetailTemporary> realmResultsTransactionDetailTemporary = realm
                .where(TransactionDetailTemporary.class).findAll();
        realmResultsTransactionDetailTemporary.deleteAllFromRealm();
        realm.commitTransaction();
    }

    private void generateGridViewSaveOrder(RealmResults<SaveOrder> realmResultSaveOrder) {
        gridViewAdapterSaveOrderCashRegister = new GridViewAdapterSaveOrderCashRegister(
                CashRegisterActivity.this,
                linkedListSaveOrder(realmResultSaveOrder)
        );

        if (showLoadOrderButton(realmResultSaveOrder)) {
            imageViewLoadOrder.setVisibility(View.VISIBLE);
        } else {
            imageViewLoadOrder.setVisibility(View.INVISIBLE);
        }

        gridViewSaveOrder.setAdapter(gridViewAdapterSaveOrderCashRegister);
        gridViewAdapterSaveOrderCashRegister.notifyDataSetChanged();
    }

    private LinkedList<HashMap<String, String>> linkedListSaveOrder(RealmResults<SaveOrder> realmResultSaveOrder) {
        LinkedList<HashMap<String, String>> linkedListData = new LinkedList<>();
        for (int i = 0; i < realmResultSaveOrder.size(); i++) {
            SaveOrder saveOrder = realmResultSaveOrder.get(i);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(KEY_SAVE_ORDER_TRANSACTION_ID, saveOrder.getSaveOrderTransactionID());
            hashMap.put(KEY_SAVE_ORDER_NUMBER_TABLE, saveOrder.getSaveOrderNumberTable());
            hashMap.put(KEY_SAVE_ORDER_IS_BEING_SELECTED, saveOrder.getSaveOrderIsBeingSelected());
            linkedListData.add(hashMap);
        }
        return linkedListData;
    }

    private void updateSaveOrderToDatabase(Realm realm, String number_table, String transaction_id) {
        RealmResults<SaveOrder> realmResultSaveDetail = realm.where(SaveOrder.class)
                .equalTo("saveOrderNumberTable", number_table)
                .findAll();
        realm.beginTransaction();
        realmResultSaveDetail.get(0).setSaveOrderTransactionID(transaction_id);
        realm.commitTransaction();
    }

    public void changeViewSaveOrder() {
        if (linearLayoutSaveOrder.getVisibility() != View.VISIBLE) {
            linearLayoutRadioButtonCashRegister.setVisibility(View.INVISIBLE);
            linearLayoutGridViewCashRegister.setVisibility(View.INVISIBLE);

            linearLayoutSaveOrder.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutSaveOrder, new fSaveOrder(), "save_order")
                    .commit();

        } else {
            linearLayoutRadioButtonCashRegister.setVisibility(View.VISIBLE);
            linearLayoutGridViewCashRegister.setVisibility(View.VISIBLE);

            linearLayoutSaveOrder.setVisibility(View.INVISIBLE);
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutSaveOrder);
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
        }
    }

    public void clearCart() {
        if (linearLayoutCartEmpty.getVisibility() != View.VISIBLE) {
            linearLayoutCartListView.setVisibility(View.INVISIBLE);
            linearLayoutCartBottom.setVisibility(View.INVISIBLE);
            linearLayoutCartEmpty.setVisibility(View.VISIBLE);
            buttonNext.setVisibility(View.VISIBLE);
            buttonNext.setText("NO SALE");
            buttonNext.setBackground(getResources().getDrawable(R.drawable.skip_receipt));
            buttonNext.setEnabled(false);
        } else {
            linearLayoutCartListView.setVisibility(View.VISIBLE);
            linearLayoutCartBottom.setVisibility(View.VISIBLE);
            linearLayoutCartEmpty.setVisibility(View.GONE);
        }
    }

    private boolean showLoadOrderButton(RealmResults<SaveOrder> realm_result) {
        boolean value = false;
        for (int i = 0; i < realm_result.size(); i++) {
            SaveOrder saveOrder = realm_result.get(i);
            if (!saveOrder.getSaveOrderTransactionID().equals(Declaration.NOT_FILLED)) {
                value = true;
            }
        }
        return value;
    }

    public void generateNewTransactionID() {
        sessionManager.setTransactionId(TransactionID.generate(realm, macAddress()));
        textViewTestingTransactionID.setText(sessionManager.getTansactionID());
    }

    private boolean isFillWithOrder(RealmResults<SaveOrder> realm_result_save_order, int number_table) {
        boolean value = false;
        if (!realm_result_save_order.get(number_table).getSaveOrderTransactionID().equals(Declaration.NOT_FILLED)) {
            value = true;
        }
        return value;
    }

    private boolean isSelectedOrder(RealmResults<SaveOrder> realm_result_save_order, int number_table) {
        boolean value = false;
        if (!realm_result_save_order.get(number_table).getSaveOrderIsBeingSelected().equals(Declaration.UNSELECTED)) {
            value = true;
        }
        return value;
    }


    private String transactionIdFromSaveOrder(RealmResults<SaveOrder> realm_result_save_order, int number_table) {
        return realm_result_save_order.get(number_table).getSaveOrderTransactionID();
    }

    private void deleteTransactionIdFromSaveOrder(Realm realm, String transaction_id) {
        RealmResults<SaveOrder> realmResultSaveOrder = realm.where(SaveOrder.class)
                .findAll();
        for (int i = 0; i < realmResultSaveOrder.size(); i++) {
            if (!realmResultSaveOrder.get(i).getSaveOrderTransactionID().equals(Declaration.NOT_FILLED)) {
                if (realmResultSaveOrder.get(i).getSaveOrderTransactionID().equals(transaction_id)) {
                    sessionManager.setTableNumber(stringOf(i + 1));
                    realm.beginTransaction();
                    realmResultSaveOrder.get(i).setSaveOrderTransactionID(Declaration.NOT_FILLED);
                    realm.commitTransaction();
                }
            }
        }
    }


    public void unSelectAllDataInTable(Realm realm) {
        RealmResults<SaveOrder> realmResultSaveDetail = realm.where(SaveOrder.class).findAll();
        realm.beginTransaction();
        for (int i = 0; i < realmResultSaveDetail.size(); i++) {
            realmResultSaveDetail.get(i).setSaveOrderIsBeingSelected(Declaration.UNSELECTED);
        }
        realm.commitTransaction();
    }

    private void disableTouchEventMyShop(RadioButton[] radio_button_array, GridView grid_view_item,
                                         RelativeLayout linear_layout_cart_top, LinearLayout linear_layout_cart_bottom,
                                         Button button_next) {
        for (RadioButton aRadio_button_array : radio_button_array) {
            aRadio_button_array.setEnabled(false);
        }
        grid_view_item.setEnabled(false);
        linear_layout_cart_top.setVisibility(View.INVISIBLE);
        linear_layout_cart_bottom.setVisibility(View.INVISIBLE);
        button_next.setEnabled(false);
    }

    private void enableTouchEventMyShop(RadioButton[] radio_button_array, GridView grid_view_item,
                                        RelativeLayout linear_layout_cart_top, LinearLayout linear_layout_cart_bottom,
                                        Button button_next) {
        for (RadioButton aRadio_button_array : radio_button_array) {
            aRadio_button_array.setEnabled(true);
        }
        grid_view_item.setEnabled(true);
        linear_layout_cart_top.setVisibility(View.VISIBLE);
        if (!button_next.getText().toString().equals("NO SALE")) {
            linear_layout_cart_bottom.setVisibility(View.VISIBLE);
            button_next.setEnabled(true);
            button_next.setBackground(getResources().getDrawable(R.drawable.charge));
        }
    }

    private void selectMenu(int position) {
        switch (position) {
            case 0:
                validateCurrentDrawer();
                frameLayout.setVisibility(View.GONE);
                enableTouchEventMyShop(radioButtonArray, gridViewItemFromCategory, linearLayoutCartTop, linearLayoutCartBottom, buttonNext);
                break;
            case 1:
                frameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new fHistory()).commit();
                disableTouchEventMyShop(radioButtonArray, gridViewItemFromCategory, linearLayoutCartTop, linearLayoutCartBottom, buttonNext);
                break;
            case 2:
                frameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new fReport()).commit();
                disableTouchEventMyShop(radioButtonArray, gridViewItemFromCategory, linearLayoutCartTop, linearLayoutCartBottom, buttonNext);
                break;
            case 3:
                frameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new fInventory()).commit();
                disableTouchEventMyShop(radioButtonArray, gridViewItemFromCategory, linearLayoutCartTop, linearLayoutCartBottom, buttonNext);
                break;
            case 4:
                frameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new fSetting()).commit();
                disableTouchEventMyShop(radioButtonArray, gridViewItemFromCategory, linearLayoutCartTop, linearLayoutCartBottom, buttonNext);
                break;
            case 5:
                frameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new fPayment()).commit();
                disableTouchEventMyShop(radioButtonArray, gridViewItemFromCategory, linearLayoutCartTop, linearLayoutCartBottom, buttonNext);
                break;
            default:
                break;
        }
    }

    private void saveTransactionToDatabase(Realm realm, String mpos_id, String transaction_id, String user_id,
                                           String cashier_name,
                                           String total_transaction, String total_quantity,
                                           String tax, String service, String discount, String sub_total,
                                           String date_and_time, String payment_type, String tendered,
                                           String change, String table_number) {
        sessionManager.setTableNumber(Declaration.NULL_DATA);
        /**
         * All writes must be wrapped in a transaction to facilitate safe multi threading
         */
        realm.beginTransaction();
        /**
         * Add table discount master
         */
        TransactionMaster transactionMaster = realm.createObject(TransactionMaster.class);
        transactionMaster.setTransactionTerminalID(mpos_id);
        transactionMaster.setTransactionMasterID(transaction_id + "");
        transactionMaster.setTransactionMasterUserID(user_id + "");
        transactionMaster.setTransactionMasterCashierName(cashier_name + "");
        transactionMaster.setTransactionMasterTotalTransaction(total_transaction + "");
        transactionMaster.setTransactionMasterTotalQuantity(total_quantity + "");
        transactionMaster.setTransactionMasterTax(tax + "");
        transactionMaster.setTransactionMasterService(service + "");
        transactionMaster.setTransactionMasterDiscount(discount + "");
        transactionMaster.setTransactionMasterSubTotal(sub_total + "");
        transactionMaster.setTransactionMasterDateAndTime(date_and_time + "");
        transactionMaster.setTransactionMasterDate(date_and_time.substring(0, 10) + "");
        transactionMaster.setTransactionMasterPaymentType(payment_type + "");
        transactionMaster.setTransactionMasterTendered(tendered + "");
        transactionMaster.setTransactionMasterChange(change + "");
        transactionMaster.setTransactionMasterEmail("");
        transactionMaster.setTransactionMasterTableNumber(table_number + "");
        /**
         * When the transaction is committed, all changes a synced to disk.
         */
        realm.commitTransaction();
    }

    private String dateAndTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return simpleDateFormat.format(calendar.getTime());
    }

    private String quantityAllItem(Realm realm, String transaction_id) {
        int totalItem = 0;
        RealmResults<TransactionDetail> realmResults = realm.where(TransactionDetail.class)
                .equalTo("transactionDetailID", transaction_id)
                .findAll();
        for (int i = 0; i < realmResults.size(); i++) {
            if (realmResults.get(i).getTransactionDetailQuantity().equals("1")) {
                totalItem += 1;
            } else {
                totalItem += integerOf(realmResults.get(i).getTransactionDetailQuantity());
            }
        }
        return stringOf(totalItem);
    }

    private void prepareSaveOrderData(Realm realm, String totalTable) {
        RealmResults<SaveOrder> realmResults = realm.where(SaveOrder.class).findAll();
        if (realmResults.size() == 0) {
            for (int i = 1; i < integerOf(totalTable) + 1; i++) {
                RealmPrepareDatabaseTableSaveOrder(realm, Declaration.NOT_FILLED, stringOf(i), Declaration.UNSELECTED);
            }
        } else {
            logger.addError("Save Order Database", "already exist");
        }
    }

    /**
     * Create realm database as a discount master table
     */
    private void RealmPrepareDatabaseTableSaveOrder(Realm realm, String save_order_transaction_id,
                                                    String save_order_number_table, String save_order_selected_flag) {
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

    private void connectTransactionToDrawerDatabase(Realm realm, String cash, boolean flag_is_cash) {
        RealmResults<Drawer> realmResultsDrawer = realm
                .where(Drawer.class)
                .findAll();

        String cashSale = realmResultsDrawer.last().getDrawerCashSales();
        String cardSale = realmResultsDrawer.last().getDrawerCardSales();
        String expectedCash = realmResultsDrawer.last().getDrawerExpetationCash();
        String actualCash = realmResultsDrawer.last().getDrawerActualCash();

        realm.beginTransaction();
        if (flag_is_cash) {
            realmResultsDrawer.last().setDrawerCashSales(addingTwoInteger(cashSale, cash));
        } else {
            realmResultsDrawer.last().setDrawerCardSales(addingTwoInteger(cardSale, cash));
        }
        realmResultsDrawer.last().setDrawerExpetationCash(addingTwoInteger(expectedCash, cash));
        //realmResultsDrawer.last().setDrawerActualCash(addingTwoInteger(actualCash,cash));

        realm.commitTransaction();

    }

    private String addingTwoInteger(String intOne, String intTwo) {
        return String.valueOf(Integer.parseInt(intOne) + Integer.parseInt(intTwo));
    }

    /**
     * code for transaction icmp
     */

    class NetworkTask extends AsyncTask<Void, Integer, Boolean> {
        private InputStream mmTcpInStream;
        private OutputStream mmTcpOutStream;
        private Socket mmTcpSocket;
        public ServerSocket mmTcpServerSocket;
        public boolean mmRunning;
        private int mPort;
        private int mTailleBuf;
        private int mLoopsNb;
        boolean ismAndroidServer;
        private long time_elapsed = 0;
        String strResult;
        private int mCount;

        NetworkTask(int port, int tailleBuf, int loopsNb, boolean isAndroidServer) {
            mmTcpSocket = null;
            mmTcpServerSocket = null;
            mmTcpInStream = null;
            mmTcpOutStream = null;
            mPort = port;
            mTailleBuf = tailleBuf;
            mLoopsNb = loopsNb;
            ismAndroidServer = isAndroidServer;
            mCount = 0;
        }

        protected Boolean doInBackground(Void... dummy) {
            Log.d(TAG, "TcpThread: Started");
            byte[] buffer = new byte[mTailleBuf];  // buffer store for the stream
            int total;
            int bytes;

            for (int i = 0; i < mTailleBuf; i++) {
                buffer[i] = (byte) i;
            }

            mmRunning = true;

            try {
                if (ismAndroidServer) {
                    Log.d(TAG, "TcpThread: Waiting for connection...");
                    mmTcpServerSocket = new ServerSocket(mPort);
                } else {
                    Log.d(TAG, "TcpThread: Connecting...");
                    mmTcpSocket = new Socket("127.0.0.1", mPort);
                }

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
                Log.e(TAG, "TcpThread: UnknownHostException");
                return Boolean.FALSE;
            } catch (IOException e1) {
                e1.printStackTrace();
                Log.e(TAG, "TcpThread: IOException");
                return Boolean.FALSE;
            }

            if (ismAndroidServer) {
                try {
                    mmTcpSocket = mmTcpServerSocket.accept();
                } catch (IOException e2) {
                    e2.printStackTrace();
                    try {
                        mmTcpServerSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (mmTcpSocket != null) {

                Log.d(TAG, "TcpThread: Connected");
                try {
                    mmTcpInStream = mmTcpSocket.getInputStream();
                    mmTcpOutStream = mmTcpSocket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "TcpThread: IOException");
                    try {
                        mmTcpSocket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    return Boolean.FALSE;
                }


                if (mLoopsNb > 0) {
                    long start_time = System.currentTimeMillis();
                    Log.d(TAG, String.format("TcpThread: Start sending %d", buffer.length));
                    mCount = 0;
                    while (mmRunning) {
                        if (isCancelled()) {
                            Log.d(TAG, String.format("TcpThread: Cancelled"));
                            closeStreams();
                            break;
                        }
                        if (mmTcpOutStream != null) {
                            try {
                                //Log.d(TAG, "TcpThread: Write");
                                mmTcpOutStream.write(buffer, 0, mTailleBuf);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                                Log.e(TAG, "TcpThread: Write IOException");
                                closeStreams();
                                break;
                            }
                        }

                        if (mmTcpInStream != null) {
                            total = 0;
                            while (total < mTailleBuf) {
                                try {
                                    bytes = mmTcpInStream.read(buffer);
                                    if (bytes == -1) {    //end of the stream
                                        Log.e(TAG, "TcpThread: End of stream");
                                        closeStreams();
                                        break;
                                    } else {
                                        total += bytes;
                                    }
                                } catch (IOException e) {
                                    Log.e(TAG, "TcpThread: IOException(read)");
                                    e.printStackTrace();
                                    closeStreams();
                                    break;
                                }
                            }
                            if (total == mTailleBuf)
                                mCount++;
                        } else {
                            break;
                        }

                        if (mCount % 10 == 0)
                            publishProgress(mCount);
                        if (mCount >= mLoopsNb)
                            break;
                    }
                    Log.d(TAG, String.format("TcpThread: Stop sending (count=%d)", mCount));
                    long stop_time = System.currentTimeMillis();
                    time_elapsed = stop_time - start_time;
                    Log.d(TAG, String.format("START = %d | STOP = %d | TIME = %d", start_time, stop_time, time_elapsed));
                    //mtvStatus.setText("OK\nEND OF TEST\n\n" );
                    Log.d(TAG, String.format("Bandwith = %d bits/second", (8 * ((1000 * mTailleBuf * mCount * 2) / time_elapsed))));
                }
            }

            if (ismAndroidServer) {
                // wait for other side to close connection
                if (mmTcpInStream != null) {

                    while (true) {
                        try {
                            bytes = mmTcpInStream.read(buffer);
                            if (bytes == -1) {    //end of the stream
                                Log.e(TAG, "TcpThread: End of stream");
                                closeStreams();
                                break;
                            }
                        } catch (IOException e) {
                            Log.e(TAG, "TcpThread: IOException(read)");
                            e.printStackTrace();
                            closeStreams();
                            break;
                        }
                    }
                }

                try {
                    mmTcpServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                closeStreams();
            }
            Log.d(TAG, "TcpThread: Ended");
            return Boolean.TRUE;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mtvStatus.setText(String.format("TEST IN PROGRESS...\nPackets: %d", values[0]));
            super.onProgressUpdate(values);
        }

        protected void onPostExecute(Boolean result) {
            if (result == true && mCount == mLoopsNb) {
                strResult = "OK";
                if (time_elapsed != 0) {
                    Toast.makeText(getApplicationContext(), String.format("Bandwith = %d kbits/second", (8 * mTailleBuf * mCount * 2) / time_elapsed), Toast.LENGTH_SHORT).show();
                }
                mtvResult.setText(String.format("Bandwith = %d kbits/second", (8 * mTailleBuf * mCount * 2) / time_elapsed));
            } else {
                strResult = "KO";
            }

            mtvStatus.setText(strResult + "\nEND OF TEST");
        }

        private void closeStreams() {
            if (mmTcpInStream != null) {
                try {
                    mmTcpInStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                mmTcpInStream = null;
            }
            if (mmTcpOutStream != null) {
                synchronized (mmTcpOutStream) {
                    try {
                        mmTcpOutStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mmTcpOutStream = null;
                }
            }
            if (mmTcpSocket != null) {
                try {
                    mmTcpSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mmTcpSocket = null;
            }
        }
    }

    /**
     * This implementation is used to receive callbacks from the local
     * service.
     */
    private IPclServiceCallback mCallback = new IPclServiceCallback() {

        public void shouldFeedPaper(int lines) {
            Log.d(TAG, "shouldFeedPaper");
            mtvStatus.setText("Feed Paper: " + lines + " lines");
            byte[] tmp = new byte[lines];
            for (int i = 0; i < lines; i++)
                tmp[i] = '\n';
            mtvResult.append(new String(tmp));
        }

        public void shouldCutPaper() {
            Log.d(TAG, "shouldCutPaper");
            mtvStatus.setText("Cut Paper");
            Bitmap image = Bitmap.createBitmap(mtvResult.getWidth(), mtvResult.getHeight(), Bitmap.Config.RGB_565);
            mtvResult.draw(new Canvas(image));
            String url = MediaStore.Images.Media.insertImage(getContentResolver(), image, "title", null);
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra("sms_body", "some text");
            sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(url));
            sendIntent.setType("image/bmp");
            startActivity(sendIntent);
        }

        public void shouldPrintText(String text, byte font, byte justification, byte xfactor, byte yfactor, byte underline, byte bold) {
            Log.d(TAG, "shouldPrintText");
            mtvStatus.setText(String.format("Print Text %s", text));
            int style;
            if (bold == 1)
                style = Typeface.BOLD;
            else
                style = Typeface.NORMAL;

            String family = "monospace";
            Typeface face = Typeface.DEFAULT;
            Layout.Alignment align = Layout.Alignment.ALIGN_NORMAL;
            float scaleX = 1;
            int size = (int) mtvResult.getTextSize();
            switch (font) {
                case 0:
                    face = Typeface.DEFAULT;
                    break;
                case 1:
                    family = "serif";
                    face = Typeface.SANS_SERIF;
                    break;
                case 2:
                    family = "sans-serif";
                    face = mtfStrato;
                    break;
            }

            switch (justification) {
                case JUSTIFIED_CENTER:
                    align = Layout.Alignment.ALIGN_CENTER;
                    break;
                case JUSTIFIED_LEFT:
                    align = Layout.Alignment.ALIGN_NORMAL;
                    break;
                case JUSTIFIED_RIGHT:
                    align = Layout.Alignment.ALIGN_OPPOSITE;
                    break;
            }

            if (yfactor == 1) {
                if (xfactor == 1) {
                    scaleX = 1;
                } else if (xfactor == 2) {
                    scaleX = 2;
                } else {
                    scaleX = 4;
                }
            } else if (yfactor == 2) {
                size *= 2;
                if (xfactor == 1) {
                    scaleX = (float) 0.5;
                } else if (xfactor == 2) {
                    scaleX = 1;
                } else {
                    scaleX = 2;
                }
            } else {
                size *= 4;
                if (xfactor == 1) {
                    scaleX = (float) 0.25;
                } else if (xfactor == 2) {
                    scaleX = (float) 0.5;
                } else {
                    scaleX = 1;
                }
            }

            int prev_len = mtvResult.getText().length();
            int len = text.length();
            mtvResult.append(text);

            Spannable str = mtvResult.getText();
            // Create our span sections, and assign a format to each.
            str.setSpan(new StyleSpan(style), prev_len, prev_len + len - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            str.setSpan(new CustomTypefaceSpan(family, face), prev_len, prev_len + len - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            str.setSpan(new AlignmentSpan.Standard(align), prev_len, prev_len + len - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            str.setSpan(new ScaleXSpan(scaleX), prev_len, prev_len + len - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            str.setSpan(new AbsoluteSizeSpan(size, false), prev_len, prev_len + len - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (underline == 1)
                str.setSpan(new UnderlineSpan(), prev_len, prev_len + len - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        public void shouldPrintRawText(byte[] text, byte charset, byte font, byte justification, byte xfactor, byte yfactor, byte underline, byte bold) {
            if (charset == 127) {
                try {
                    shouldPrintText(new String(text, "Cp1251"), font, justification, xfactor, yfactor, underline, bold);
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        public void shouldPrintImage(Bitmap image, byte justification) {
            Log.d(TAG, "shouldPrintImage");
            int prev_len = mtvResult.getText().length();
            Layout.Alignment align;
            mtvResult.append(" \n");
            Spannable str = mtvResult.getText();

            switch (justification) {
                case JUSTIFIED_CENTER:
                    align = Layout.Alignment.ALIGN_CENTER;
                    break;
                case JUSTIFIED_LEFT:
                    align = Layout.Alignment.ALIGN_NORMAL;
                    break;
                case JUSTIFIED_RIGHT:
                    align = Layout.Alignment.ALIGN_OPPOSITE;
                    break;
                default:
                    align = Layout.Alignment.ALIGN_NORMAL;
                    break;
            }

            str.setSpan(new ImageSpan(mContext, image), prev_len, prev_len + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            str.setSpan(new AlignmentSpan.Standard(align), prev_len, prev_len + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mtvResult.setText(str);
        }

        @Override
        public void shouldDoSignatureCapture(int pos_x, int pos_y, int width,
                                             int height, int timeout) {
            Log.d(TAG, "shouldDoSignatureCapture");
            Intent intent = new Intent(mContext, CaptureSignature.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("POS_X", pos_x);
            intent.putExtra("POS_Y", pos_y);
            intent.putExtra("WIDTH", width);
            intent.putExtra("HEIGHT", height);
            intent.putExtra("TIMEOUT", timeout);
            intent.putExtra("FINISH", false);
            startActivityForResult(intent, 1);
        }

        @Override
        public void signatureTimeoutExceeded() {
            Log.d(TAG, "signatureTimeoutExceeded");
            Intent intent = new Intent(mContext, CaptureSignature.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("FINISH", true);
            startActivity(intent);
        }

        @Override
        public void shouldAddSignature() {
            Log.d(TAG, "shouldAddSignature");
            if (mLastSignature != null) {
                //mHandler.sendMessage(mHandler.obtainMessage(MSG_ADD_SIGNATURE, mLastSignature));
                int prev_len = mtvResult.getText().length();
                mtvResult.append(" \n");
                Spannable str = mtvResult.getText();
                str.setSpan(new ImageSpan(mContext, mLastSignature), prev_len, prev_len + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                mtvResult.setText(str);
            }
        }

        @Override
        public int shouldStartReceipt(byte type) {
            //mtvResult.append(new String("******** START RECEIPT ***********\n"));
            switch (type) {
                case 0:
                    mtvResult.append(new String("MERCHANT\n"));
                    break;
                case 1:
                    mtvResult.append(new String("CUSTOMER\n"));
                    break;
            }
            return 0;
        }

        @Override
        public int shouldEndReceipt() {
            //mtvResult.append(new String("********  END RECEIPT  ***********"));
            return 0;
        }
    };

    @Override
    protected void onDestroy() {
        Log.d(TAG, "TestActivity: onDestroy");
        if (mReleaseService == 1) {
            if (mCallbackRegistered) {
                mPclService.unregisterCallback(mCallback);
                mCallbackRegistered = false;
            }
            if ((mNetworkTask != null) && (mNetworkTask.mmRunning)) {
                Log.d(TAG, "NetworkTask running");
                if (mNetworkTask.mmTcpServerSocket != null) {
                    Log.d(TAG, "Closing ServerSocket");
                    try {
                        mNetworkTask.mmTcpServerSocket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "Cancel NetworkTask");
                mNetworkTask.cancel(true);
            }
            if (miScanState == 2) {
                Toast.makeText(CashRegisterActivity.this, "onDestroyNeedRunBcrScan", Toast.LENGTH_SHORT).show();
                //runBcrScan();
            }

            releaseService();
        }
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, String.format("onActivityResult req=%d res=%d", requestCode, resultCode));
        if (requestCode == 1) {
            mLastSignature = null;
            if (resultCode == RESULT_OK) {
                mLastSignature = data.getParcelableExtra(EXTRA_SIGNATURE_BMP);
            }
            {
                mPclService.submitSignatureWithImage(mLastSignature);
            }
        }
    }

    /**
     * card type "C" for debit, "D" for credit
     * authorization type "1" or "2"; default "2"
     * ctrl cheque "1" or "2"; default "2"
     * user data can be null
     * application number can be null
     * extended data can be null
     * hexa can be true or false; default false
     */
    protected void runDoTransactionEx(String amount, String type_card, String authorization_type,
                                      String ctrl_cheque, String user_data, String aplication_number) {
        int appNumber;
        TransactionIn transIn = new TransactionIn();
        TransactionOut transOut = new TransactionOut();
        transIn.setAmount(amount);
        transIn.setCurrencyCode("978");
        transIn.setOperation(type_card);
        transIn.setTermNum("58");
        transIn.setAuthorizationType(authorization_type);
        transIn.setCtrlCheque(ctrl_cheque);
        transIn.setUserData1(user_data);
        if (aplication_number.length() == 0)
            appNumber = 0;
        else
            appNumber = Integer.parseInt(aplication_number);
        byte[] extDataIn = null;
        String strExtDataIn = "FC29DC010ADD2464336162623736322D643536332D343532382D626434662D373835623163346362323038";
        try {
            byte[] tmp = strExtDataIn.getBytes("ISO-8859-1");
            extDataIn = new byte[tmp.length / 2];
            int j = 0;
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i] >= 'a' && tmp[i] <= 'f')
                    tmp[i] = (byte) (tmp[i] - 'a' + 10);
                else if (tmp[i] >= 'A' && tmp[i] <= 'F')
                    tmp[i] = (byte) (tmp[i] - 'A' + 10);
                else if (tmp[i] >= '0' && tmp[i] <= '9')
                    tmp[i] = (byte) (tmp[i] - '0');
            }
            for (int i = 0; i < tmp.length; i += 2) {
                String str = String.format("%02x", tmp[i] * 16 + tmp[i + 1]);
                extDataIn[j++] = (byte) Integer.parseInt(str, 16);
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            mtvStatus.setText("TEST ERROR: UnsupportedEncodingException");
            return;
        }
        byte[] extDataOut = new byte[5000];
        Log.d(TAG, "Amount:" + transIn.getAmount() + " Currency:" + transIn.getCurrencyCode() + " Operation:" + transIn.getOperation());
        Log.d(TAG, "TermNum:" + transIn.getTermNum() + " AuthoType:" + transIn.getAuthorizationType() + " CtrlCheque:" + transIn.getCtrlCheque());
        Log.d(TAG, "UserData:" + transIn.getUserData1());
        //mtvStatus.setText("TEST STARTED ...\n");
        mtvResult.setText("");
        mPclService.registerCallback(mCallback);
        mCallbackRegistered = true;
        new DoTransactionExTask(transIn, transOut, appNumber, extDataIn, extDataOut).execute();
    }

    class DoTransactionExTask extends AsyncTask<Void, Void, Boolean> {
        private TransactionIn transIn;
        private TransactionOut transOut;
        private byte[] extDataIn;
        private byte[] extDataOut;
        private long[] extDataOutSize;
        private int appNumber;

        public DoTransactionExTask(TransactionIn transIn, TransactionOut transOut, int appNumber, byte[] extDataIn, byte[] extDataOut) {
            this.transIn = transIn;
            this.transOut = transOut;
            this.extDataIn = extDataIn;
            this.extDataOut = extDataOut;
            this.appNumber = appNumber;
            this.extDataOutSize = new long[1];
            this.extDataOutSize[0] = extDataOut.length;
        }

        protected Boolean doInBackground(Void... tmp) {
            return doTransactionEx(transIn, transOut, appNumber, extDataIn, extDataIn.length, extDataOut, extDataOutSize);
        }

        protected void onPostExecute(Boolean result) {
            Log.d(TAG, String.format("extDataOutSize=%d", extDataOutSize[0]));
            mtvStatus.setText(Boolean.toString(result));
            String strResult = "N/A";
            boolean isSuccess = false;
            de = "0"; // ???
            if (result) {
                String strExtDataOut;

                // Transform extDataOut in hex
                StringBuffer hex = new StringBuffer();
                String str;
                for (int i = 0; i < extDataOutSize[0]; i++) {
                    str = Integer.toHexString((int) extDataOut[i]);
                    if (str.length() > 2)
                        str = str.substring(str.length() - 2, str.length());
                    else if (str.length() == 1)
                        str = "0" + str;
                    hex.append(str);
                }
                strExtDataOut = hex.toString();
                Log.e(TAG, "Data out: " + strExtDataOut);
                //parsing no card
                String[] split = strExtDataOut.split("df5a");
                String[] splitSuccess = strExtDataOut.split("dfff02");
                String[] splitDE = strExtDataOut.split("de");
                String[] splitType = strExtDataOut.split("42");
                //String[] splitTypeCard = strExtDataOut.split("df911d");
                //String aftersplitTypeCard = splitTypeCard[1];
                //String tipecard = aftersplitTypeCard.substring(2, 17);
                //String typecard = convertHexToString(tipecard);
                //MID DAN TIDTRANS
                //String[] splitTIDtrans = strExtDataOut.split("df911b");
                //String[] splitMID = strExtDataOut.split("df911c");
                //DF9112 host response
                /*
                String[] splitHR = strExtDataOut.split("df9112");
                String aftersplitTIDtrans = splitTIDtrans[1];
                String aftersplitMID = splitMID[1];
                String afterSplitHR = splitHR[1];
                String TIDtrans = aftersplitTIDtrans.substring(2, 18);
                String MIDfrmICMP = aftersplitMID.substring(2, 32);
                String HostResponse1 = afterSplitHR.substring(2, 6);
                String tidtrans = convertHexToString(TIDtrans);
                String midicmp = convertHexToString(MIDfrmICMP);
                String hostreponse2 = convertHexToString(HostResponse1);

                //APPROVAL CODE
                String[] splitAC = strExtDataOut.split("df9108");
                String afterSplitAC = splitAC[1];
                String ApprovalCode1 = afterSplitAC.substring(2, 14);
                String approvalCode = convertHexToString(ApprovalCode1);
*/
                String afterSplit = split[1];
                String afterSplitSuccess = splitSuccess[1];
                String afterSplitDE = splitDE[1];
                String afterSplitType = splitType[1];
                String hexaLength = afterSplit.substring(0, 2);
                de = afterSplitDE.substring(0, 10);
                String[] de_split = de.split("0400");
                String de_real = de_split[1];
                Log.e("MPOS ID", de_real);
                Log.e("Success Read", afterSplitType); // DFFF02

                //Type card like visa or mastercard
                String type = afterSplitType.substring(1, 1);
                String successRead = afterSplitSuccess.substring(2, 4);


               /* if (type.equals("3")) {
                    typecard = "Visa";
                } else if (type.equals("4")) {
                    typecard = "Master Card";
                } else {
                    typecard = "Debit";
                }*/

                //card number still encrypte
                int cardLength = Integer.parseInt(hexaLength, 16);
                String CardNo = afterSplit.substring(2, (cardLength * 2) + 2);
                isSuccess = successRead.equals("00");
                String ab = "";
                String ac = "";
                for (int i = 0; i < (CardNo.length() / 2); i++) {
                    ab = "" + CardNo.charAt((i * 2) + 1);
                    if (ab.equals("a")) {
                        ac += "*";
                    } else {
                        ac += ab;
                    }
                }
                String realcard = ac.toString();


/*
                mtvResult.append("Amount:" + transOut.getAmount() + "\n" + "Currency:" + transOut.getCurrencyCode() + "\n" + "C3Error:" + transOut.getC3Error() + "\n" +
                        "TermNum:" + transOut.getTerminalNumber() + "\n"
                        + "UserData:" + transOut.getUserData() + "\n" + "FFU:" + transOut.getFFU() + "\n" +
                        "ExtData:" + strExtDataOut);
*/

                if (isSuccess) {
                    deleteTransactionIdFromSaveOrder(realm, sessionManager.getTansactionID());
                    deleteOrderFromDatabaseTemporary(realm);
                    saveTransactionToDatabase(
                            realm,
                            de_real,
                            sessionManager.getTansactionID(),
                            session.get(SessionManager.KEY_CASHIER_USERNAME_ID),
                            session.get(SessionManager.KEY_CASHIER_USERNAME),
                            price,
                            quantityAllItem(realm, sessionManager.getTansactionID()),
                            revertValuePattern(textViewCartVatTotal.getText().toString()),
                            revertValuePattern(textViewService.getText().toString()),
                            revertValuePattern(textViewDiscount.getText().toString()),
                            revertValuePattern(textViewSubTotal.getText().toString()),
                            dateAndTime(),
                            "Card",
                            revertValuePattern(textViewCartTotalAmountTotal.getText().toString()),
                            "0",
                            sessionManager.getTableNumber());

                    connectTransactionToDrawerDatabase(realm, price, false);
                    dialogStruck.setTransactionId(sessionManager.getTansactionID());
                    dialogStruck.setStringImageStruck(stringImageStruck());
                    dialogStruck.setStringStruck(mtvResult.getText().toString());
                    dialogStruck.show(getFragmentManager(), "show_struck");

                } else {
                    switch (successRead) {
                        case "01":
                            showAlertDialogNullEvent(CashRegisterActivity.this, "Information", getResources().getString(R.string.RC01));
                            break;
                        case "07":
                            showAlertDialogNullEvent(CashRegisterActivity.this, "Information", getResources().getString(R.string.RC015));
                            break;
                        case "15":
                            showAlertDialogNullEvent(CashRegisterActivity.this, "Information", getResources().getString(R.string.RC002));
                            break;
                        default:
                            showAlertDialogNullEvent(CashRegisterActivity.this, "Information", getResources().getString(R.string.RC01));
                            break;
                    }
                }

                if (transOut.getC3Error().equals(TransactionOut.ErrorCode.SUCCESS.toString())) {
                    strResult = "OK";
                } else {
                    strResult = "KO";
                }

                Log.e("Data Out", "Amount:" + transOut.getAmount() + "\n" + "Currency:" + transOut.getCurrencyCode() + "\n" + "C3Error:" + transOut.getC3Error() + "\n" +
                        "TermNum:" + transOut.getTerminalNumber() + "\n"
                        + "UserData:" + transOut.getUserData() + "\n" + "FFU:" + transOut.getFFU() + "\n" +
                        "ExtData:" + strExtDataOut);

                Log.e("Status Out", strResult);
            } else {
                showAlertDialogNullEvent(CashRegisterActivity.this, "Information", getResources().getString(R.string.RC009));
            }
            hideProgressDialog();
            mtvStatus.setText(strResult);
        }
    }

    @Override
    public void onBarCodeReceived(String barCodeValue, int symbology) {
        if (mBcrRead) {
            mtvResult.append(barCodeValue + " (" + mPclService.bcrSymbologyToText(symbology) + ")\r\n");
        }
    }

    @Override
    public void onBarCodeClosed() {
        mtvResult.setText("Barcode reader closed");

    }

    @Override
    public void onStateChanged(String state) {
        if (state.equals("CONNECTED")) {
            mReleaseService = 1;
            sessionManager.setIsConnected(mReleaseService);
            buttonConnectToIcmpCommendSale.setEnabled(true);
            Log.e("Test5", sessionManager.getAlreadyConnected());
        } else {
            mReleaseService = 0;
            sessionManager.setIsConnected(mReleaseService);
            buttonConnectToIcmpCommendSale.setEnabled(false);
            Log.e("Test6", sessionManager.getAlreadyConnected());
        }
    }

    @Override
    public void onPclServiceConnected() {
        Log.d(TAG, "onPclServiceConnected");

        if (isCompanionConnected()) {
            mReleaseService = 1;
            sessionManager.setIsConnected(mReleaseService);
            buttonConnectToIcmpCommendSale.setEnabled(true);
            Log.e("Test3", sessionManager.getAlreadyConnected());
        } else {
            mReleaseService = 0;
            sessionManager.setIsConnected(mReleaseService);
            buttonConnectToIcmpCommendSale.setEnabled(false);
            Log.e("Test4", sessionManager.getAlreadyConnected());
        }
    }

    private String stringImageStruck() {

        // get view
        View view = findViewById(R.id.tvResult);
        view.setDrawingCacheEnabled(true);

        // get height and width from scroll view
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollViewStruckICMP);
        int totalHeight = scrollView.getChildAt(0).getHeight();
        int totalWidth = scrollView.getChildAt(0).getWidth();
        Log.e("HEIGHT & WIDHT ", totalHeight + "|" + totalWidth);

        if (totalHeight != 0 && totalWidth != 0) {
            // set view
            view.layout(0, 0, totalWidth, totalHeight);

            // build drawing cache
            view.buildDrawingCache(true);
            Bitmap bmp_img;

//            if(view.getDrawingCache()!=null){
//                // bitmap storage
//                bmp_img = Bitmap.createBitmap(view.getDrawingCache());
//
//                // clear cache
//                view.setDrawingCacheEnabled(false);
//
//                // convert bitmap to byte array
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bmp_img.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                byte[] byteArrayBitmap = stream.toByteArray();
//
//                return encodeImage(byteArrayBitmap);
//            }else {
//                Log.e("getDrawingCache","NULL");
//                // bitmap storage
//                bmp_img = loadBitmapFromView(view, totalHeight, totalWidth);
//
//                // clear cache
//                view.setDrawingCacheEnabled(false);
//
//                // convert bitmap to byte array
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bmp_img.compress(Bitmap.CompressFormat.PNG, 100, stream);
//
//                byte[] byteArrayBitmap = stream.toByteArray();
//                return encodeImage(byteArrayBitmap);
//
//            }
            Log.e("getDrawingCache", "NULL");
            // bitmap storage
            bmp_img = newBitmap(loadBitmapFromView(view, totalHeight, totalWidth));

            // clear cache
            view.setDrawingCacheEnabled(false);

            // convert bitmap to byte array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp_img.compress(Bitmap.CompressFormat.PNG, 100, stream);

            byte[] byteArrayBitmap = stream.toByteArray();
            return encodeImage(byteArrayBitmap);
        } else {
            return "";
        }
    }

    public void addCartFromOtherMenu(String item_name, String amount, String quantity) {
        // get size of item in database
        RealmResults<Item> itemRealmResults = realm.where(Item.class).findAll();
        RealmResults<Item> itemOtherRealmResults = realm.where(Item.class).equalTo("itemCategory", "Other").findAll();
        RealmResults<Merchant> merchantRealmResults = realm.where(Merchant.class).findAll();

        // create item id for new item from other menu
        String item_id = merchantRealmResults.get(0).getMerchantID() + "_" + "item_other" + itemOtherRealmResults.size();

        // create new item in data base
        realm.beginTransaction();
        Item item = realm.createObject(Item.class);
        item.setRealmItemID(itemRealmResults.size());
        item.setItemID(item_id);
        item.setItemName(item_name);
        item.setItemPrice(amount);
        item.setItemDiscount("0");
        item.setItemPicture("");
        item.setItemCategory("Other");
        item.setItemStatusTax("1");

        ItemVariant itemVariant = realm.createObject(ItemVariant.class);
        itemVariant.setRealmItemVariantID(0);
        itemVariant.setItemVariantID(String.valueOf(""));
        item.itemVarian.add(itemVariant);

        ItemModifierGroup itemModifier = realm.createObject(ItemModifierGroup.class);
        itemModifier.setRealmItemModifierGroupID(0);
        itemModifier.setItemModifierGroupID(String.valueOf(""));
        item.itemModifier.add(itemModifier);

        item.setItemOutletID(session.get(SessionManager.KEY_OUTLET_ID));
        realm.commitTransaction();

        // input other menu to transaction detail
        realm.beginTransaction();
        TransactionDetail transactionDetail = realm.createObject(TransactionDetail.class);
        transactionDetail.setTransactionDetailID(sessionManager.getTansactionID());
        transactionDetail.setTransactionDetailItemID(item_id);
        transactionDetail.setTransactionDetailVariantID(Declaration.NULL_DATA);
        transactionDetail.setTransactionDetailTotalPrice(stringOf(integerOf(amount) * integerOf(quantity)));
        transactionDetail.setTransactionDetailQuantity(quantity);
//        for (int m = 0; m < modifier_id.size(); m++) {
//            if (!modifier_id.get(m).contains("var")){
//                TransactionDetailModifier transactionDetailModifier = realm.createObject(TransactionDetailModifier.class);
//                transactionDetailModifier.setTransactionDetailModifierID(modifier_id.get(m));
//                transactionDetail.transactionDetailModifierID.add(transactionDetailModifier);
//            }
//        }
//
//        for (int d = 0; d < discount_id.size(); d++) {
//            TransactionDetailDiscount transactionDetailDiscount = realm.createObject(TransactionDetailDiscount.class);
//            transact
// ionDetailDiscount.setDiscountID(discount_id.get(d));
//            transactionDetail.transactionDetailDiscountID.add(transactionDetailDiscount);
//        }
        realm.commitTransaction();
    }

    private void settingGreetingTime(TextView text_view) {
        int time = integerOf(dateAndTime().substring(11, 13));
        if (time >= 6 && time <= 10) {
            text_view.setText("Good Morning");
        } else if (time >= 12 && time < 17) {
            text_view.setText("Good Afternoon");
        } else if (time >= 17 && time < 20) {
            text_view.setText("Good Evening");
        } else if (time >= 20) {
            text_view.setText("Good Night");
        } else {
            text_view.setText("Hi!");
        }
    }

    private void validateTransactionIdInSaveOrderDatabase(String transaction_id) {
        RealmResults<SaveOrder> realmResultsSaveOrder = realm
                .where(SaveOrder.class)
                .equalTo("saveOrderTransactionID", transaction_id)
                .findAll();
        if (realmResultsSaveOrder.size() == 0) {
            new AlertDialog.Builder(CashRegisterActivity.this)
                    .setTitle("Confirmation")
                    .setMessage("Your order is not save yet, would you like to save your order ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            changeViewSaveOrder();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            generateNewTransactionID();
                            clearCart();
                        }
                    })
                    .create().show();
        } else {
            generateNewTransactionID();
            clearCart();
            if (linearLayoutSaveOrder.getVisibility() == View.VISIBLE) {
                closeSaveOrderView();
            }
        }
    }

    public void deleteItemFromCart(String item_id, String variant_id) {
        logger.addInfo("testing", "item_id " + item_id);
        logger.addInfo("testing", "variant_id " + variant_id);

        realm.beginTransaction();

//        RealmResults<TransactionDetail> realmResultsTransactionDetail = realm.where(TransactionDetail.class)
//                .equalTo("transactionDetailID", sessionManager.getTansactionID())
//                .equalTo("transactionDetailItemID", item_id)
//                .equalTo("transactionDetailVariantID", variant_id)
//                .findAll();
//        realmResultsTransactionDetail.deleteAllFromRealm();

        TransactionDetail TransactionDetail = realm.where(TransactionDetail.class)
                .equalTo("transactionDetailID", sessionManager.getTansactionID())
                .equalTo("transactionDetailItemID", item_id)
                .equalTo("transactionDetailVariantID", variant_id)
                .findFirst();

        int qtybefore = Integer.parseInt(TransactionDetail.getTransactionDetailQuantity());
        if (qtybefore <= 1){
            TransactionDetail realmResultsTransactionDetail = realm.where(TransactionDetail.class)
                .equalTo("transactionDetailID", sessionManager.getTansactionID())
                .equalTo("transactionDetailItemID", item_id)
                .equalTo("transactionDetailVariantID", variant_id)
                .findFirst();
            realmResultsTransactionDetail.deleteFromRealm();

            RealmResults<TransactionDetail> realmResultsTransDetail = realm.
                    where(TransactionDetail.class)
                    .equalTo("transactionDetailID", sessionManager.getTansactionID()).findAll();
            generateViewCart(
                    sessionManager.getTansactionID(),
                    realmResultsTransDetail,
                    linearLayoutCartListView,
                    linearLayoutCartEmpty,
                    textViewCartTransactionID,
                    listViewCartInRegister,
                    linearLayoutCartBottom,
                    textViewDiscount,
                    textViewSubTotal,
                    textViewService,
                    textViewCartVatTotal,
                    buttonNext
            );

            listViewAdapterItemInCartCashRegister.notifyDataSetChanged();

        }else{
            TransactionDetail.setTransactionDetailQuantity(String.valueOf(qtybefore - 1));

            int b = Integer.parseInt(TransactionDetail.getTransactionDetailTotalPrice());
            Log.e(TAG, "Qty Babi : " + qtybefore + "; Harga Total Babi :" + b );
            int hargaSatuan = b / qtybefore;
            int hargaSetelahKurangQty = b - hargaSatuan;

            TransactionDetail.setTransactionDetailTotalPrice(String.valueOf(hargaSetelahKurangQty));

            RealmResults<TransactionDetail> realmResultsTransDetail = realm.
                    where(TransactionDetail.class)
                    .equalTo("transactionDetailID", sessionManager.getTansactionID()).findAll();
            generateViewCart(
                    sessionManager.getTansactionID(),
                    realmResultsTransDetail,
                    linearLayoutCartListView,
                    linearLayoutCartEmpty,
                    textViewCartTransactionID,
                    listViewCartInRegister,
                    linearLayoutCartBottom,
                    textViewDiscount,
                    textViewSubTotal,
                    textViewService,
                    textViewCartVatTotal,
                    buttonNext
            );

            changeViewInBill(
                    textViewDiscount,
                    textViewSubTotal,
                    textViewService,
                    textViewCartVatTotal,
                    buttonNext,
                    sumOfDiscount(realm, linkedListTransactionDetailPremier),
                    sumOfPrice(linkedListTransactionDetailPremier),
                    sumOfService(realm, sumOfPrice(linkedListTransactionDetailPremier)),
                    sumOfTax(realm, sumOfPrice(linkedListTransactionDetailPremier), sumOfService(realm, sumOfPrice(linkedListTransactionDetailPremier))),
                    "Rp. " + convertValuePattern(
                            stringOf(
                                    integerOf(sumOfPrice(linkedListTransactionDetailPremier))
                                            + integerOf(sumOfService(realm, sumOfPrice(linkedListTransactionDetailPremier)))
                                            + integerOf(sumOfTax(realm, sumOfPrice(linkedListTransactionDetailPremier), sumOfService(realm, sumOfPrice(linkedListTransactionDetailPremier))))
                            )
                    )
            );

            listViewAdapterItemInCartCashRegister.notifyDataSetChanged();

        }

        realm.commitTransaction();

        RealmResults<TransactionDetail> realmResultsTransactionDetailTesting = realm.where(TransactionDetail.class)
                .equalTo("transactionDetailID", sessionManager.getTansactionID())
                .equalTo("transactionDetailItemID", item_id)
                .equalTo("transactionDetailVariantID", variant_id)
                .findAll();

        logger.addInfo("testing", "transactionDetailTesting " + realmResultsTransactionDetailTesting.size());

//        RealmResults<TransactionDetail> realmResultsTransDetail = realm.where(TransactionDetail.class)
//                .equalTo("transactionDetailID", sessionManager.getTansactionID())
//                .findAll();
//        generateViewCart(
//                sessionManager.getTansactionID(),
//                realmResultsTransDetail,
//                linearLayoutCartListView,
//                linearLayoutCartEmpty,
//                textViewCartTransactionID,
//                listViewCartInRegister,
//                linearLayoutCartBottom,
//                textViewDiscount,
//                textViewSubTotal,
//                textViewService,
//                textViewCartVatTotal,
//                buttonNext
//        );

        realmResultTransactionDetail = realm.where(TransactionDetail.class).findAll();

        realmChangeListenerCart = new RealmChangeListener() {
            @Override
            public void onChange() {
                RealmResults<TransactionDetail> realmResultsTransDetail = realm.
                        where(TransactionDetail.class)
                        .equalTo("transactionDetailID", sessionManager.getTansactionID()).findAll();

                if (realmResultsTransDetail.size() != 0) {
                    //Toast.makeText(getApplicationContext(), "Transaction Detail is Changed", Toast.LENGTH_SHORT).show();
                    generateViewCart(
                            sessionManager.getTansactionID(),
                            realmResultsTransDetail,
                            linearLayoutCartListView,
                            linearLayoutCartEmpty,
                            textViewCartTransactionID,
                            listViewCartInRegister,
                            linearLayoutCartBottom,
                            textViewDiscount,
                            textViewSubTotal,
                            textViewService,
                            textViewCartVatTotal,
                            buttonNext
                    );
                }
            }
        };
        realmResultTransactionDetail.addChangeListener(realmChangeListenerCart);

        logger.addInfo("testing", "transactionDetailAllItem " + realmResultTransactionDetail.size());
    }

    private Bitmap newBitmap(Bitmap bmp) {
        Bitmap newBitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bmp, 0, 0, null);
        return newBitmap;
    }

    //FOR REFUND
    protected void runDoTransactionrefund(String amount) {
        //Log.d("AMOUNT",amount);
        TransactionIn transIn = new TransactionIn();
        TransactionOut transOut = new TransactionOut();
        //transIn.setAmount(amount);
        transIn.setCurrencyCode("978");
        transIn.setOperation("C");
        transIn.setTermNum("58");
        transIn.setAuthorizationType("2");
        transIn.setCtrlCheque("2");
        transIn.setUserData1("");
        int appNumber = 0;
        byte[] extDataIn = null;
        String strExtDataIn = "FC33DC010BDF920606" + addPaddingZero(amount) + "DD2436316334333132372D373265382D346366652D616236642D633632663732303761333038";
        try {
            //byte[] tmp = metStatic6.getText().toString().getBytes("ISO-8859-1");
            byte[] tmp = strExtDataIn.getBytes("ISO-8859-1");
            extDataIn = new byte[tmp.length / 2];
            int j = 0;
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i] >= 'a' && tmp[i] <= 'f')
                    tmp[i] = (byte) (tmp[i] - 'a' + 10);
                else if (tmp[i] >= 'A' && tmp[i] <= 'F')
                    tmp[i] = (byte) (tmp[i] - 'A' + 10);
                else if (tmp[i] >= '0' && tmp[i] <= '9')
                    tmp[i] = (byte) (tmp[i] - '0');
            }
            for (int i = 0; i < tmp.length; i += 2) {
                String str = String.format("%02x", tmp[i] * 16 + tmp[i + 1]);
                extDataIn[j++] = (byte) Integer.parseInt(str, 16);
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // mtvStatus.setText("TEST ERROR: UnsupportedEncodingException");
            return;
        }
        byte[] extDataOut = new byte[5000];
        Log.d(TAG, "Amount:" + transIn.getAmount() + " Currency:" + transIn.getCurrencyCode() + " Operation:" + transIn.getOperation());
        Log.d(TAG, "TermNum:" + transIn.getTermNum() + " AuthoType:" + transIn.getAuthorizationType() + " CtrlCheque:" + transIn.getCtrlCheque());
        Log.d(TAG, "UserData:" + transIn.getUserData1());
        mtvStatus.setText("TEST STARTED ...\n");
        mtvResult.setText("");
        mPclService.registerCallback(mCallback);
        mCallbackRegistered = true;
        new DoTransactionExRefund(transIn, transOut, appNumber, extDataIn, extDataOut).execute();
    }

    //FOR REFUND
    class DoTransactionExRefund extends AsyncTask<Void, Void, Boolean> {
        private TransactionIn transIn;
        private TransactionOut transOut;
        private byte[] extDataIn;
        private byte[] extDataOut;
        private long[] extDataOutSize;
        private int appNumber;

        public DoTransactionExRefund(TransactionIn transIn, TransactionOut transOut, int appNumber, byte[] extDataIn, byte[] extDataOut) {
            //countDownTimer2.cancel();
            this.transIn = transIn;
            this.transOut = transOut;
            this.extDataIn = extDataIn;
            this.extDataOut = extDataOut;
            this.appNumber = appNumber;
            this.extDataOutSize = new long[1];
            this.extDataOutSize[0] = extDataOut.length;
        }

        protected void onPreExecute() {
        }

        protected Boolean doInBackground(Void... tmp) {
            return doTransactionEx(transIn, transOut, appNumber, extDataIn, extDataIn.length, extDataOut, extDataOutSize);
        }

        protected void onPostExecute(Boolean result) {
            Log.d(TAG, String.format("extDataOutSize=%d", extDataOutSize[0]));
            mtvStatus.setText(Boolean.toString(result));
            String strResult = "N/A";
            boolean isSuccess = false;
            de = "0";

            if (result == true) {
                String strExtDataOut;

                // Transform extDataOut in hex
                StringBuffer hex = new StringBuffer();
                String str;
                for (int i = 0; i < extDataOutSize[0]; i++) {
                    str = Integer.toHexString((int) extDataOut[i]);
                    if (str.length() > 2)
                        str = str.substring(str.length() - 2, str.length());
                    else if (str.length() == 1)
                        str = "0" + str;
                    hex.append(str);
                }
                strExtDataOut = hex.toString();
                Log.d(TAG, "Data out: " + strExtDataOut);
                //parsing no card
                String[] split = strExtDataOut.split("df5a");
                String[] splitDE = strExtDataOut.split("de");
                String[] splitType = strExtDataOut.split("42");
                String[] splitSuccess1 = strExtDataOut.split("ce");
                String[] splitSuccess2 = strExtDataOut.split("dfff02");
                //MID DAN TIDTRANS
                String[] splitTIDtrans = strExtDataOut.split("df911b");
                String[] splitMID = strExtDataOut.split("df911c");
                //APPROVAL CODE
                String[] splitAC = strExtDataOut.split("df9108");
                //DF9112 host response
                String[] splitTypeCard = strExtDataOut.split("df911d");

                String[] splitBatch = strExtDataOut.split("df911e");
                String[] splitHR = strExtDataOut.split("df9112");
                //DATE AND TIME
                String[] splitDATE = strExtDataOut.split("9a");
                String[] splitTIME = strExtDataOut.split("9f21");

                String aftersplitTIDtrans = splitTIDtrans[1];
                String aftersplitMID = splitMID[1];
                String afterSplitHR = splitHR[1];
                String aftersplitBatch = splitBatch[1];
                String afterSplitAC = splitAC[1];
                String afterSplit1 = splitSuccess1[1];
                String afterSplit2 = splitSuccess2[1];
                String afterSplit = split[1];
                String afterSplitSuccess = splitSuccess2[1];
                String afterSplitDE = splitDE[1];
                String afterSplitType = splitType[1];
                String afterSplitDATE = splitDATE[1];
                String afterSplitTIME = splitTIME[1];
                String ApprovalCode1 = afterSplitAC.substring(2, 14);
                String batchtrans = aftersplitBatch.substring(2, 14);
                String TIDtrans = aftersplitTIDtrans.substring(2, 18);
                String MIDfrmICMP = aftersplitMID.substring(14, 32);
                String HostResponse1 = afterSplitHR.substring(2, 6);
                String hexaLength1 = afterSplit1.substring(0, 2);
                String hexaLength2 = afterSplit2.substring(2, 4);
                String statusCode2 = afterSplit2.substring(2, 4);
                String hexaLength = afterSplit.substring(0, 2);
                String successRead = afterSplitSuccess.substring(2, 4);
                String DATES = afterSplitDATE.substring(2, 8);
                String TIMES = afterSplitTIME.substring(2, 8);

                int cardLength1 = Integer.parseInt(hexaLength1, 16);
                int cardLength2 = Integer.parseInt(hexaLength2, 16);
                int cardLength = Integer.parseInt(hexaLength, 16);
                String aftersplitTypeCard = splitTypeCard[1];
                String hexlengthtipecard2 = aftersplitTypeCard.substring(0, 2); // 0a
                int lengthtipecard2 = Integer.parseInt(hexlengthtipecard2, 16);
                String tipecard = aftersplitTypeCard.substring(2, (lengthtipecard2 * 2) + 2);

                String statusCode = afterSplit1.substring(2, (cardLength1 * 2) + 2);
               /* typecard = convertHexToString(tipecard);
                 CardNo = afterSplit.substring(2, (cardLength * 2) + 2);
                tidtrans = convertHexToString(TIDtrans);
                midicmp = convertHexToString(MIDfrmICMP);
                hostreponse2 = convertHexToString(HostResponse1);
                batch = convertHexToString(batchtrans);*/
                de = afterSplitDE.substring(0, 10);
                String[] de_split = de.split("0400");
               /* de_real = de_split[1];
                type = afterSplitType.substring(1, 1);
                String aa, a1 = "";
                for (int i = 0; i < (ApprovalCode1.length() / 2); i++) {
                    aa = "" + ApprovalCode1.charAt((i * 2) + 1);
                    a1 += aa;
                }
                approvalCode = a1.toString();
                Log.d(TAG, "status code CE: " + statusCode + " - status code DE: " + statusCode2);

                // GET REAL NUMBER CARD
                String ab = "";
                String ac = "";
                for (int i = 0; i < (CardNo.length() / 2); i++) {
                    ab = "" + CardNo.charAt((i * 2) + 1);
                    if (ab.equals("a")) {
                        ac += "*";
                    } else {
                        ac += ab;
                    }
                }
                realcard = ac.toString();
*/


                if (statusCode.equals("00000000") && statusCode2.equals("00")) { //success
                  //REFUND BERHASIL

                } else if (successRead.equals("01")) {

                    //REFUND GAGAL

                } else if (successRead.equals("02")) {

                    // KONEKSI INTERNET ERROR

                } else if (successRead.equals("03")) {

                    //REFUND GAGAL

                } else if (successRead.equals("05")) {
                    //HARAP LAKUKAN SETTLEMENT

                } else if (successRead.equals("0d")) {

                    //REFUND DISABLE

                } else if (successRead.equals("18")) {

                    //REFUND GAGAL

                } else if (successRead.equals("17")) {
                    //REFUND GAGAL, MOHON COBA KARTU LAIN

                } else {
                    //REFUND GAGAL


                }
            } else if (result == false) {
                //B;UETOOTH ERROR

            }
        }
    }

    //////////////////VOID////////////////////
    public void runDoTransactionvoid(String amountvoid, String no_invoice) {
        TransactionIn transIn = new TransactionIn();
        TransactionOut transOut = new TransactionOut();
        // transIn.setAmount(amountvoid);
//		transIn.setCurrencyCode("978");
        transIn.setOperation("C");
//		transIn.setTermNum("58");
//		transIn.setAuthorizationType("2");
//		transIn.setCtrlCheque("2");
//		transIn.setUserData1("");
        int appNumber = 0;
        byte[] extDataIn = null;
        String strExtDataIn = "FC39DC0102DF920606" + addPaddingZero(amountvoid) + "DD2431323736333462662D653937352D346336302D386638372D383933663130633839643865DE0400" + no_invoice;
        //FC2FDC0102DD2431323736333462662D653937352D346336302D386638372D383933663130633839643865DE0400100003
        //FC2FDC0102DD2466343430343862332D656434322D343963312D616161392D666464313561623661663630DE0400100001
        try {
            //metStatic6.setText("FC2FDC0110DD2466343430343862332D656434322D343963312D616161392D666464313561623661663630DE0400100109");
            byte[] tmp = strExtDataIn.getBytes("ISO-8859-1");
            //byte[] tmp = "FC29DC010ADD2464336162623736322D643536332D343532382D626434662D373835623163346362323038".getBytes("ISO-8859-1");
            //if (mcbHexa.isChecked())
            //{
            extDataIn = new byte[tmp.length / 2];
            int j = 0;
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i] >= 'a' && tmp[i] <= 'f')
                    tmp[i] = (byte) (tmp[i] - 'a' + 10);
                else if (tmp[i] >= 'A' && tmp[i] <= 'F')
                    tmp[i] = (byte) (tmp[i] - 'A' + 10);
                else if (tmp[i] >= '0' && tmp[i] <= '9')
                    tmp[i] = (byte) (tmp[i] - '0');
            }
            for (int i = 0; i < tmp.length; i += 2) {
                String str = String.format("%02x", tmp[i] * 16 + tmp[i + 1]);
                extDataIn[j++] = (byte) Integer.parseInt(str, 16);
            }
            //}
            //else
            //{
            //	extDataIn = tmp;
            //}


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //mtvStatus.setText("TEST ERROR: UnsupportedEncodingException");
            return;
        }
        byte[] extDataOut = new byte[5000];

        mPclService.registerCallback(mCallback);

        // In this case the service has crashed before we could even
        // do anything with it; we can count on soon being
        // disconnected (and then reconnected if it can be restarted)
        // so there is no need to do anything here.

        mCallbackRegistered = true;
        new DoTransactionExVoid(transIn, transOut, appNumber, extDataIn, extDataOut).execute();

    }

    //FOR VOID
    class DoTransactionExVoid extends AsyncTask<Void, Void, Boolean> {
        private TransactionIn transIn;
        private TransactionOut transOut;
        private byte[] extDataIn;
        private byte[] extDataOut;
        private long[] extDataOutSize;
        private int appNumber;
        //private HistoryModel model;

        public DoTransactionExVoid(TransactionIn transIn, TransactionOut transOut, int appNumber, byte[] extDataIn, byte[] extDataOut) {
            //countDownTimer2.cancel();
            this.transIn = transIn;
            this.transOut = transOut;
            this.extDataIn = extDataIn;
            this.extDataOut = extDataOut;
            this.appNumber = appNumber;
            this.extDataOutSize = new long[1];
            this.extDataOutSize[0] = extDataOut.length;
            //this.model=model;
        }

        protected void onPreExecute() {

        }

        protected Boolean doInBackground(java.lang.Void... tmp) {
            Boolean bRet = doTransactionEx(transIn, transOut, appNumber, extDataIn, extDataIn.length, extDataOut, extDataOutSize);
            return bRet;
        }

        protected void onPostExecute(Boolean result) {

            Log.d(TAG, String.format("extDataOutSize=%d", extDataOutSize[0]));
            de = "0";
            if (result == true) {
                String strExtDataOut;
                // Transform extDataOut in hex
                StringBuffer hex = new StringBuffer();
                String str;
                for (int i = 0; i < extDataOutSize[0]; i++) {
                    str = Integer.toHexString((int) extDataOut[i]);
                    if (str.length() > 2)
                        str = str.substring(str.length() - 2, str.length());
                    else if (str.length() == 1)
                        str = "0" + str;
                    hex.append(str);
                }
                strExtDataOut = hex.toString();
                Log.e(TAG, "Data out: " + strExtDataOut);

                String[] splitSuccess = strExtDataOut.split("ce");
                //String[] split=strExtDataOut.split("df5a");
                String[] splitType = strExtDataOut.split("42");
                String[] splitDE = strExtDataOut.split("de");
                String[] splitSuccess2 = strExtDataOut.split("dfff02");
               //DATE AND TIME
                String[] splitDATE = strExtDataOut.split("9a");
                String[] splitTIME = strExtDataOut.split("9f21");

                String afterSplitSuccess = splitSuccess2[1];
                String afterSplitDATE = splitDATE[1];
                String afterSplitTIME = splitTIME[1];

                 String DATES = afterSplitDATE.substring(2, 8);
                String TIMES = afterSplitTIME.substring(2, 8);


               /* batch = convertHexToString(batchtrans);

                typecard = convertHexToString(tipecard);
                tidtrans = convertHexToString(TIDtrans);
                midicmp = convertHexToString(MIDfrmICMP);
                hostreponse2 = convertHexToString(HostResponse1);

                //APPROVAL CODE
                String[] splitAC = strExtDataOut.split("df9108");
                String afterSplitAC = splitAC[1];
                String ApprovalCode1 = afterSplitAC.substring(2, 14);
                approvalCode = convertHexToString(ApprovalCode1);*/

                //String afterSplit1=split[1];
                String afterSplit = splitSuccess[1];
                String afterSplitDE = splitDE[1];
                String hexaLength = afterSplit.substring(0, 2);
                String successRead = afterSplitSuccess.substring(2, 4);
                //String hexaLength1=afterSplit1.substring(0, 2);

                //no invoice VOID
                de = afterSplitDE.substring(0, 10);
                int cardLength = Integer.parseInt(hexaLength, 16);

               /* de_real = de_split[1];
                //Type card like visa or mastercard
                type = afterSplitType.substring(1, 1);

                CardNo = afterSplit.substring(2, (cardLength * 2) + 2);

                String ab = "";
                String ac = "";
                for (int i = 0; i < (CardNo.length() / 2); i++) {
                    ab = "" + CardNo.charAt((i * 2) + 1);
                    if (ab.equals("a")) {
                        ac += "*";
                    } else {
                        ac += ab;
                    }
                }
                realcard = ac.toString();*/
                String statusCode = afterSplit.substring(2, (cardLength * 2) + 2);

                if (statusCode.equals("00000000") && successRead.equals("00")) { //success
                    //VOID SUKSES
                    Log.e(TAG, "SUKSES");
                } else if (successRead.equals("01")) {
                    //VOID GAGAL
                    Log.e(TAG, "VOID GAGAL");
                } else if (successRead.equals("07")) {
                    //VOID GAGAL
                    Log.e(TAG, "VOID GAGAL");
                } else if (successRead.equals("02")) {
                    //ALREADY VOID
                    Log.e(TAG, "ALREADY VOID");
                } else if (successRead.equals("03")) {
                    //VOID GAGAL
                    Log.e(TAG, "VOID GAGAL");
                } else if (successRead.equals("04")) {
                    //TRANSAKSI TIDAK DITEMUKAN
                    Log.e(TAG, "TRANSAKSI TIDAK DITEMUKAN");
                } else if (successRead.equals("05")) {
                    //HARAP LAKUKAN SETTLEMENT
                    Log.e(TAG, "HARAP LAKUKAN SETTLEMENT");
                } else if (successRead.equals("18")) {
                    //VOID GAGAL
                    Log.e(TAG, "VOID GAGAL");
                } else {
                    //VOID GAGAL
                    Log.e(TAG, "VOID GAGAL");
                }


            } else if (result == false) {
                //BLUETOOT MATI
                Log.e(TAG, "BLUETOOTH MATI");
            }
        }


    }

    ///////SETTLEMENT//////////////
    public void runDoTransactionsettlement() {
        TransactionIn transIn = new TransactionIn();
        TransactionOut transOut = new TransactionOut();
        transIn.setOperation("C");
        int appNumber = 0;
        byte[] extDataIn = null;
        String strExtDataIn = "FC03DC010F";
        try {
            //byte[] tmp = metStatic6.getText().toString().getBytes("ISO-8859-1");
            byte[] tmp = strExtDataIn.getBytes("ISO-8859-1");
            extDataIn = new byte[tmp.length / 2];
            int j = 0;
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i] >= 'a' && tmp[i] <= 'f')
                    tmp[i] = (byte) (tmp[i] - 'a' + 10);
                else if (tmp[i] >= 'A' && tmp[i] <= 'F')
                    tmp[i] = (byte) (tmp[i] - 'A' + 10);
                else if (tmp[i] >= '0' && tmp[i] <= '9')
                    tmp[i] = (byte) (tmp[i] - '0');
            }
            for (int i = 0; i < tmp.length; i += 2) {
                String str = String.format("%02x", tmp[i] * 16 + tmp[i + 1]);
                extDataIn[j++] = (byte) Integer.parseInt(str, 16);
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // mtvStatus.setText("TEST ERROR: UnsupportedEncodingException");
            return;
        }
        byte[] extDataOut = new byte[5000];
        Log.d(TAG, "Amount:" + transIn.getAmount() + " Currency:" + transIn.getCurrencyCode() + " Operation:" + transIn.getOperation());
        Log.d(TAG, "TermNum:" + transIn.getTermNum() + " AuthoType:" + transIn.getAuthorizationType() + " CtrlCheque:" + transIn.getCtrlCheque());
        Log.d(TAG, "UserData:" + transIn.getUserData1());
        mtvStatus.setText("TEST STARTED ...\n");
        mtvResult.setText("");
        mPclService.registerCallback(mCallback);
        mCallbackRegistered = true;
        new DoTransactionExSettlement(transIn, transOut, appNumber, extDataIn, extDataOut).execute();

    }

    //FOR SETTLEMENT
    class DoTransactionExSettlement extends AsyncTask<Void, Void, Boolean> {
        private TransactionIn transIn;
        private TransactionOut transOut;
        private byte[] extDataIn;
        private byte[] extDataOut;
        private long[] extDataOutSize;
        private int appNumber;

        public DoTransactionExSettlement(TransactionIn transIn, TransactionOut transOut, int appNumber, byte[] extDataIn, byte[] extDataOut) {
            //countDownTimer2.cancel();
            this.transIn = transIn;
            this.transOut = transOut;
            this.extDataIn = extDataIn;
            this.extDataOut = extDataOut;
            this.appNumber = appNumber;
            this.extDataOutSize = new long[1];
            this.extDataOutSize[0] = extDataOut.length;
        }

        protected void onPreExecute() {

        }

        protected Boolean doInBackground(Void... tmp) {
            Boolean bRet = doTransactionEx(transIn, transOut, appNumber, extDataIn, extDataIn.length, extDataOut, extDataOutSize);
            return bRet;
        }

        protected void onPostExecute(Boolean result) {

            Log.d(TAG, String.format("extDataOutSize=%d", extDataOutSize[0]));
            if (result == true) {
                String strExtDataOut;
                // Transform extDataOut in hex
                StringBuffer hex = new StringBuffer();
                String str;
                for (int i = 0; i < extDataOutSize[0]; i++) {
                    str = Integer.toHexString((int) extDataOut[i]);
                    if (str.length() > 2)
                        str = str.substring(str.length() - 2, str.length());
                    else if (str.length() == 1)
                        str = "0" + str;
                    hex.append(str);
                }
                strExtDataOut = hex.toString();
                Log.d(TAG, "Data out: " + strExtDataOut);
                //parsing no card
                String[] splitSuccess = strExtDataOut.split("ce");
                String[] splitSuccess2 = strExtDataOut.split("dfff0c");
                //String[] splitSuccess2=strExtDataOut.split("dfff02");

                String afterSplit = splitSuccess[1];
                String afterSplit2 = splitSuccess2[1];

                String hexaLength = afterSplit.substring(0, 2);
                String statusCode2 = afterSplit2.substring(2, 4);
                int cardLength = Integer.parseInt(hexaLength, 16);
                String statusCode = afterSplit.substring(2, (cardLength * 2) + 2);

                Log.d(TAG, "status code: " + statusCode + " status code2: " + statusCode2);


                if (statusCode2.equals("00")) {
                    //SUKSES
                    Log.e(TAG, "SUKSES");
                } else if (statusCode2.equals("01")) {
                    //FAILED
                    Log.e(TAG, "FAILED");
                } else if (statusCode2.equals("07")) {
                    //KONEKSI ERROR
                    Log.e(TAG, "PLEASE, TURN ON INTERNET CONNECTION");
                } else if (statusCode2.equals("15")) {
                    //KONEKSI INTERNET
                    Log.e(TAG, "PLEASE, TURN ON INTERNET CONNECTION");
                } else if (statusCode2.equals("20")) {
                    //TIDAK ADA TRANSAKSI
                    Log.e(TAG, "NO TRANSACTION");
                } else if (statusCode2.equals("04")) {
                    //TRANSAKSI TIDAK DI TEMUKAN
                    Log.e(TAG, "TRANSACTION NOT FOUND");
                } else {
                    //SETTLEMENT GAGAL
                    Log.e(TAG, "SETTLEMENT FAIL");
                }

            } else if (result == false) {
                //BLUETOOTH MATI
                Log.e(TAG, "BLUETOOTH MATI");

            }
        }
    }

    public String addPaddingZero(String amount){
        return String.format("%012d", Integer.parseInt(amount));
    }

}

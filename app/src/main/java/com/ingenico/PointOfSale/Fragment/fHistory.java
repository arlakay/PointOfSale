package com.ingenico.PointOfSale.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.ingenico.PointOfSale.Adapter.ListViewAdapterHistory;
import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.AppController;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Controller.HashPassword;
import com.ingenico.PointOfSale.Controller.SessionManager;
import com.ingenico.PointOfSale.LinearLayout.LinearLayoutHistoryItemList;
import com.ingenico.PointOfSale.ModelRealm.Login;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailModifier;
import com.ingenico.PointOfSale.ModelRealm.TransactionMaster;
import com.ingenico.PointOfSale.Object.VoidTransaction;
import com.ingenico.PointOfSale.R;
import com.ingenico.PointOfSale.retrofit.RestApi;
import com.ingenico.PointOfSale.retrofit.services.ApiService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator-Handy on 6/30/2016.
 */
public class fHistory extends Fragment {
    //Declare in xml

    private String TAG = fHistory.class.getSimpleName();
    private String currentDateTimeString;

    private SearchView searchViewHistory;
    private ListView listViewHistory;
    private LinearLayout linearLayoutHistoryEmpty, linearLayoutHistoryDescription, linearLayoutHistoryDescriptionItemList;
    private TextView textViewHistoryDescriptionDay,textViewHistoryDescriptionDate,textViewHistoryDescriptionTime,
            textViewHistoryDescriptionPricePay,textViewHistoryDiscount,textViewHistorySubTotal,textViewHistoryService,
            textViewHistoryVat,textViewHistoryTotal,textViewHistoryTendered,textViewHistoryChange,textViewHistorySync;
    private RelativeLayout relativeLayoutHistoryDescriptionDiscount, RelativeLayoutHistoryDescriptionService,
            RelativeLayoutHistoryDescriptionVat, RelativeLayoutHistoryDescriptionTendered, RelativeLayoutHistoryDescriptionChange,
            relativeLayoutHistoryDesc;
    private ImageView imageViewHistoryMail, imageViewHistoryPrint, imageViewHistoryVoid;
    private ProgressBar progressBarHistory;
    private Button btnVoid;

    //declare key

    static final String KEY_TRANSACTION_MASTER_ID = "key_transaction_master_id";

    static final String KEY_TRANSACTION_TERMINAL_ID = "key_transaction_terminal_id";

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

    private ListViewAdapterHistory listViewAdapterHistory;

    LinkedList <HashMap<String,Object>> linkedListHistory;
    HashMap<String, Object> hashMapDataHistory;
    SessionManager sessionManager;

    public fHistory (){}

    String a, b, c, d, e, f, g, h, i, j, k, l, m, cashierUsername, pass, tidParse, oulet_pass, cashier_pass, outlet_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        sessionManager = new SessionManager(getActivity().getApplicationContext());
        HashMap<String, String> user = sessionManager.getSessionDetails();
        cashierUsername = user.get(SessionManager.KEY_CASHIER_USERNAME_ID);
        c = user.get(SessionManager.KEY_CASHIER_PASSWORD);
        d = user.get(SessionManager.KEY_OUTLET_ID);
        e = user.get(SessionManager.KEY_OUTLET_NAME);
        f = user.get(SessionManager.KEY_OUTLET_PASS);
        g = user.get(SessionManager.KEY_TRANSACTION_ID);

        searchViewHistory = (android.support.v7.widget.SearchView) rootView.findViewById(R.id.searchViewHistory);
        searchViewHistory.setFocusable(true);
        listViewHistory = (ListView)rootView.findViewById(R.id.listViewHistory);
        linearLayoutHistoryEmpty = (LinearLayout)rootView.findViewById(R.id.linearLayoutHistoryEmpty);
        linearLayoutHistoryDescription = (LinearLayout)rootView.findViewById(R.id.linearLayoutHistoryDescription);
        linearLayoutHistoryDescriptionItemList = (LinearLayout)rootView.findViewById(R.id.linearLayoutHistoryDescriptionItemList);
        textViewHistoryDescriptionDay = (TextView)rootView.findViewById(R.id.textViewHistoryDescriptionDay);
        textViewHistoryDescriptionDate = (TextView)rootView.findViewById(R.id.textViewHistoryDescriptionDate);
        textViewHistorySubTotal = (TextView)rootView.findViewById(R.id.textViewHistorySubTotal);
        textViewHistoryDescriptionTime = (TextView)rootView.findViewById(R.id.textViewHistoryDescriptionTime);
        textViewHistoryDescriptionPricePay = (TextView)rootView.findViewById(R.id.textViewHistoryDescriptionPricePay);
        textViewHistoryDiscount = (TextView)rootView.findViewById(R.id.textViewHistoryDiscount);
        textViewHistoryService = (TextView)rootView.findViewById(R.id.textViewHistoryService);
        textViewHistoryVat = (TextView)rootView.findViewById(R.id.textViewHistoryVat);
        textViewHistoryTotal = (TextView)rootView.findViewById(R.id.textViewHistoryTotal);
        textViewHistoryTendered = (TextView)rootView.findViewById(R.id.textViewHistoryTendered);
        textViewHistoryChange = (TextView)rootView.findViewById(R.id.textViewHistoryChange);
        textViewHistorySync = (TextView)rootView.findViewById(R.id.textViewHistorySync);
        relativeLayoutHistoryDescriptionDiscount = (RelativeLayout)rootView.findViewById(R.id.relativeLayoutHistoryDescriptionDiscount);
        RelativeLayoutHistoryDescriptionService = (RelativeLayout)rootView.findViewById(R.id.RelativeLayoutHistoryDescriptionService);
        RelativeLayoutHistoryDescriptionVat = (RelativeLayout)rootView.findViewById(R.id.RelativeLayoutHistoryDescriptionVat);
        RelativeLayoutHistoryDescriptionTendered = (RelativeLayout)rootView.findViewById(R.id.RelativeLayoutHistoryDescriptionTendered);
        RelativeLayoutHistoryDescriptionChange = (RelativeLayout)rootView.findViewById(R.id.RelativeLayoutHistoryDescriptionChange);
        relativeLayoutHistoryDesc = (RelativeLayout)rootView.findViewById(R.id.relativeLayoutHistoryDesc);
        imageViewHistoryMail = (ImageView)rootView.findViewById(R.id.imageViewHistoryMail);
        imageViewHistoryPrint = (ImageView)rootView.findViewById(R.id.imageViewHistoryPrint);
        imageViewHistoryVoid = (ImageView)rootView.findViewById(R.id.imageViewHistoryCancel);
        progressBarHistory = (ProgressBar) rootView.findViewById(R.id.progressBarHistory);
        btnVoid = (Button) rootView.findViewById(R.id.btn_fhistory_void);

        final RealmResults<TransactionMaster> realmResultsTransactionMaster = ((CashRegisterActivity)getActivity()).realm
                .where(TransactionMaster.class)
                .findAll();

        final RealmResults<TransactionDetail> realmResultsTransactionDetail = ((CashRegisterActivity)getActivity()).realm
                .where(TransactionDetail.class)
                .findAll();

        textViewHistorySync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SynchronizingDataToServer(JsonSync(realmResultsTransactionMaster,realmResultsTransactionDetail));
            }
        });

        if (realmResultsTransactionMaster.size()!=0) {
            listViewHistory.setVisibility(View.VISIBLE);
            linearLayoutHistoryEmpty.setVisibility(View.GONE);
            generateListViewHistory(((CashRegisterActivity)getActivity()).realm,realmResultsTransactionMaster);
        }

        listViewHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hashMapDataHistory = linkedListHistory.get(position);
                relativeLayoutHistoryDesc.setVisibility(View.VISIBLE);
                changeViewHistory(hashMapDataHistory);
            }
        });

        searchViewHistory.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchTransactionIdFunction(newText);
                return false;
            }
        });

        imageViewHistoryMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CashRegisterActivity)getActivity()).dialogAddEmail.setTransaction_id((String) hashMapDataHistory.get(KEY_TRANSACTION_MASTER_ID));
                ((CashRegisterActivity)getActivity()).dialogAddEmail.show(getActivity().getFragmentManager(),"show_email_dialog");
            }
        });

        imageViewHistoryPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CashRegisterActivity)getActivity()).printBillTransaction(
                        ((CashRegisterActivity)getActivity()).realm,
                        (String) hashMapDataHistory.get(KEY_TRANSACTION_MASTER_ID));
            }
        });

        btnVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amount = ((CashRegisterActivity)getActivity()).convertValuePattern(
                        (String)hashMapDataHistory.get(KEY_TRANSACTION_MASTER_TOTAL_TRANSACTION));
                String no_inv = ((String)hashMapDataHistory.get(KEY_TRANSACTION_TERMINAL_ID));

                Log.e("YOYOYO", "data:" + no_inv + "-" + amount);

                CashRegisterActivity cr = (CashRegisterActivity) getActivity();
                cr.runDoTransactionvoid(amount, no_inv);
            }
        });

        imageViewHistoryVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "KEY_TRANSACTION_ID" + hashMapDataHistory.get(KEY_TRANSACTION_MASTER_ID));
                Log.e(TAG, "KEY_CASHIER_USERNAME_ID" + cashierUsername);

                RealmResults<Login> resultLogin = ((CashRegisterActivity)getActivity()).realm
                        .where(Login.class)
                        .equalTo("username", cashierUsername )
                        .equalTo("userPrivilege","3")
                        .findAll();

                cashier_pass = resultLogin.get(0).getUserPassword();
                outlet_id = resultLogin.get(0).getOutletID();

                Log.e(TAG, "KEY_CASHIER_PASSWORD" + cashier_pass);
                Log.e(TAG, "KEY_OUTLET_ID" + outlet_id);

                RealmResults<Login> resultLogin2 = ((CashRegisterActivity)getActivity()).realm
                        .where(Login.class)
                        .equalTo("outletID", outlet_id )
                        .equalTo("userPrivilege", "2")
                        .findAll();

                oulet_pass = resultLogin2.get(0).getUserPassword();
                Log.e(TAG, "KEY_OUTLET_PASSWORD" + oulet_pass);

                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.row_edittext_password_claim);
                dialog.setCancelable(true);

                final EditText etPass = (EditText) dialog.findViewById(R.id.et_password);
                Button btnOk = (Button) dialog.findViewById(R.id.button1);

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        pass = ((HashPassword.sha512(etPass.getText().toString())).toUpperCase());
                        Log.e(TAG, "KEY_DELETE:"+ getString(R.string.kode_buat_delete));
                        tidParse = hashMapDataHistory.get(KEY_TRANSACTION_MASTER_ID).toString();
                        if(pass.equals(oulet_pass)){
                            correctTransactionWithVoid(getString(R.string.kode_buat_delete), cashierUsername, cashier_pass, tidParse);
                        }else{
                            Toast.makeText(getActivity(), "Supervisor password inccorect !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();

            }
        });

        return rootView;
    }

    public void SynchronizingDataToServer (final JSONObject jsonObject){
        ((CashRegisterActivity)getActivity()).logger.addInfo("JSON Req",jsonObject.toString());
        String tag_json_req = "send_API";
        /**
         * Show progress dialog
         */
        ((CashRegisterActivity)getActivity()).showProgressDialog();

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,
                Declaration.URL_SYNC,  new Response.Listener<String>() {
            /**
             * Response volley from hit API
             */
            @Override
            public void onResponse(String response) {
                ((CashRegisterActivity)getActivity()).logger.addInfo("Response API",response);
                Log.e("SUCCESS", "Success : " + response.toString());
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
                ((CashRegisterActivity)getActivity()).hideProgressDialog();

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Success");
                alertDialog.setMessage("Transaction Sync");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();

            }
        }, new Response.ErrorListener() {
            /**
             * Volley response error
             */
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("ERROR SYNC", "SYNC ERROR : " + error.getStackTrace());
                ((CashRegisterActivity)getActivity()).logger.addInfo("VolleyError API",error.toString());
                /**
                 * Show progress dialog
                 */
                ((CashRegisterActivity)getActivity()).hideProgressDialog();

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Gagal");
                alertDialog.setMessage("Sync Failure");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        }){
            /**
             * Posting parameter in url
             */
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sync", jsonObject.toString());
                Log.e("History", "json sync kirim : " + jsonObject.toString());
                ((CashRegisterActivity)getActivity()).logger.addInfo("Parameter in Volley",params.toString());
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
            jsonObject.put("outlet_id",((CashRegisterActivity)getActivity()).session.get(SessionManager.KEY_OUTLET_ID));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private RealmResults<TransactionDetail> realmResultTransactionDetail (Realm realm, String transaction_id){
        return realm.where(TransactionDetail.class).equalTo("transactionDetailID",transaction_id).findAll();
    }

    private void generateListViewHistory(Realm realm, RealmResults<TransactionMaster> realm_results_transaction_master){
        ((CashRegisterActivity)getActivity()).showProgressDialog();
        linkedListHistory = new LinkedList<>();
        for (int i = 0; i < realm_results_transaction_master.size(); i++) {
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put(KEY_TRANSACTION_MASTER_ID,realm_results_transaction_master.get(i).getTransactionMasterID());

            hashMap.put(KEY_TRANSACTION_TERMINAL_ID,realm_results_transaction_master.get(i).getTransactionTerminalID());

            hashMap.put(KEY_TRANSACTION_MASTER_CASHIER_NAME,realm_results_transaction_master.get(i).getTransactionMasterCashierName());
            hashMap.put(KEY_TRANSACTION_MASTER_TOTAL_TRANSACTION,realm_results_transaction_master.get(i).getTransactionMasterTotalTransaction());
            hashMap.put(KEY_TRANSACTION_MASTER_TOTAL_QUANTITY,realm_results_transaction_master.get(i).getTransactionMasterTotalQuantity());
            hashMap.put(KEY_TRANSACTION_MASTER_TAX,realm_results_transaction_master.get(i).getTransactionMasterTax());
            hashMap.put(KEY_TRANSACTION_MASTER_SERVICE,realm_results_transaction_master.get(i).getTransactionMasterService());
            hashMap.put(KEY_TRANSACTION_MASTER_DISCOUNT,realm_results_transaction_master.get(i).getTransactionMasterDiscount());
            hashMap.put(KEY_TRANSACTION_MASTER_SUB_TOTAL,realm_results_transaction_master.get(i).getTransactionMasterSubTotal());
            hashMap.put(KEY_TRANSACTION_MASTER_DATE_AND_TIME,realm_results_transaction_master.get(i).getTransactionMasterDateAndTime());
            hashMap.put(KEY_TRANSACTION_MASTER_PAYMENT_TYPE,realm_results_transaction_master.get(i).getTransactionMasterPaymentType());
            hashMap.put(KEY_TRANSACTION_MASTER_TENDERED,realm_results_transaction_master.get(i).getTransactionMasterTendered());
            hashMap.put(KEY_TRANSACTION_MASTER_CHANGE,realm_results_transaction_master.get(i).getTransactionMasterChange());
            hashMap.put(KEY_TRANSACTION_MASTER_EMAIL,realm_results_transaction_master.get(i).getTransactionMasterEmail());


            RealmResults<TransactionDetail> realmResultTransactionDetail = realmResultTransactionDetail(realm,realm_results_transaction_master.get(i).getTransactionMasterID());
            LinkedList <HashMap<String,Object>> linkedListTransactionDetail = new LinkedList<>();
            for (int j = 0; j < realmResultTransactionDetail.size(); j++) {
                HashMap<String,Object> hashMapTransactionDetail = new HashMap<>();
                hashMapTransactionDetail.put(KEY_TRANSACTION_DETAIL_ID,realmResultTransactionDetail.get(j).getTransactionDetailID());
                hashMapTransactionDetail.put(KEY_TRANSACTION_DETAIL_ITEM_ID,realmResultTransactionDetail.get(j).getTransactionDetailItemID());
                hashMapTransactionDetail.put(KEY_TRANSACTION_DETAIL_VARIANT_ID,realmResultTransactionDetail.get(j).getTransactionDetailVariantID());
                hashMapTransactionDetail.put(KEY_TRANSACTION_DETAIL_TOTAL_PRICE,realmResultTransactionDetail.get(j).getTransactionDetailTotalPrice());
                hashMapTransactionDetail.put(KEY_TRANSACTION_DETAIL_QUANTITY,realmResultTransactionDetail.get(j).getTransactionDetailQuantity());

                RealmList<TransactionDetailModifier> realmListTransactionDetailModifier = realmResultTransactionDetail.get(j).getTransactionDetailModifierID();
                LinkedList<String> linkedListTransactionDetailModifier = new LinkedList<>();
                for (int k = 0; k < realmListTransactionDetailModifier.size(); k++) {
                    linkedListTransactionDetailModifier.add(realmListTransactionDetailModifier.get(k).getTransactionDetailModifierID());
                }
                hashMapTransactionDetail.put(KEY_TRANSACTION_DETAIL_MODIFIER_ID,linkedListTransactionDetailModifier);

                RealmList<TransactionDetailDiscount> realmListTransactionDetailDiscount = realmResultTransactionDetail.get(j).getTransactionDetailDiscountID();
                LinkedList<String> linkedListTransactionDetailDiscount = new LinkedList<>();
                for (int k = 0; k < realmListTransactionDetailDiscount.size(); k++) {
                    linkedListTransactionDetailDiscount.add(realmListTransactionDetailDiscount.get(k).getDiscountID());
                }
                hashMapTransactionDetail.put(KEY_TRANSACTION_DETAIL_DISCOUNT_ID,linkedListTransactionDetailDiscount);

                linkedListTransactionDetail.add(hashMapTransactionDetail);
            }
            hashMap.put(KEY_TRANSACTION_DETAIL,linkedListTransactionDetail);

            linkedListHistory.addFirst(hashMap);
        }

        listViewAdapterHistory = new ListViewAdapterHistory(
                getActivity(),
                linkedListHistory
        );

        listViewHistory.setAdapter(listViewAdapterHistory);
        ((CashRegisterActivity)getActivity()).hideProgressDialog();
    }

    private void changeViewHistory (HashMap<String, Object> hashMap_data_history){
        linearLayoutHistoryDescription.setVisibility(View.VISIBLE);
        textViewHistoryDescriptionDay.setText(nameOfDay((String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_DATE_AND_TIME)).toUpperCase());
        textViewHistoryDescriptionDate.setText(dateDesign((String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_DATE_AND_TIME)).toUpperCase());
        textViewHistoryDescriptionTime.setText(((String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_DATE_AND_TIME)).substring(11,16));
        textViewHistoryDescriptionPricePay.setText(
                ((CashRegisterActivity)getActivity()).convertValuePattern(
                        (String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_TOTAL_TRANSACTION)
                )
        );

        if (!((String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_DISCOUNT)).equals("") &&
                !((String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_DISCOUNT)).equals("0")){
            relativeLayoutHistoryDescriptionDiscount.setVisibility(View.VISIBLE);
            textViewHistoryDiscount.setText(
                    ((CashRegisterActivity)getActivity()).convertValuePattern(
                            (String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_DISCOUNT)
                    )
            );
        }else {
            relativeLayoutHistoryDescriptionDiscount.setVisibility(View.GONE);
        }

        textViewHistorySubTotal.setText(
                ((CashRegisterActivity)getActivity()).convertValuePattern(
                        (String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_SUB_TOTAL)
                )
        );

        if (!((String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_SERVICE)).equals("") &&
                !((String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_SERVICE)).equals("0")){
            RelativeLayoutHistoryDescriptionService.setVisibility(View.VISIBLE);
            textViewHistoryService.setText(
                    ((CashRegisterActivity)getActivity()).convertValuePattern(
                            (String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_SERVICE)
                    )
            );
        }else {
            RelativeLayoutHistoryDescriptionService.setVisibility(View.GONE);
        }

        if (!((String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_TAX)).equals("") &&
                !((String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_TAX)).equals("0")){
            RelativeLayoutHistoryDescriptionVat.setVisibility(View.VISIBLE);
            textViewHistoryVat.setText(
                    ((CashRegisterActivity)getActivity()).convertValuePattern(
                            (String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_TAX)
                    )
            );
        }else {
            RelativeLayoutHistoryDescriptionVat.setVisibility(View.GONE);
        }

        textViewHistoryTotal.setText(
                ((CashRegisterActivity)getActivity()).convertValuePattern(
                        (String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_TOTAL_TRANSACTION)
                )
        );

        if (!((String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_TENDERED)).equals("") &&
                !((String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_TENDERED)).equals("0")){
            RelativeLayoutHistoryDescriptionTendered.setVisibility(View.VISIBLE);
            textViewHistoryTendered.setText(
                    ((CashRegisterActivity)getActivity()).convertValuePattern(
                            (String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_TENDERED)
                    )
            );
        }else {
            RelativeLayoutHistoryDescriptionTendered.setVisibility(View.GONE);
        }

        if (!((String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_CHANGE)).equals("") &&
                !((String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_CHANGE)).equals("0")){
            RelativeLayoutHistoryDescriptionChange.setVisibility(View.VISIBLE);
            textViewHistoryChange.setText(
                    ((CashRegisterActivity)getActivity()).convertValuePattern(
                            (String)hashMap_data_history.get(KEY_TRANSACTION_MASTER_CHANGE)
                    )
            );
        }else {
            RelativeLayoutHistoryDescriptionChange.setVisibility(View.GONE);
        }



        LinkedList<HashMap<String,Object>> linkedListTransactionDetail = (LinkedList<HashMap<String,Object>>)hashMap_data_history.get(KEY_TRANSACTION_DETAIL);
        linearLayoutHistoryDescriptionItemList.removeAllViews();
        LinearLayoutHistoryItemList linearLayoutHistoryItemList = new LinearLayoutHistoryItemList();
        for (int i = 0; i < linkedListTransactionDetail.size(); i++) {
            if (((String)linkedListTransactionDetail.get(i).get(KEY_TRANSACTION_DETAIL_ITEM_ID)).contains("item")){
                LinearLayout linearLayoutDetail = linearLayoutHistoryItemList.LinearLayoutHistoryItemList(getContext(),
                        ((CashRegisterActivity)getActivity()).realm,linkedListTransactionDetail.get(i));
                linearLayoutHistoryDescriptionItemList.addView(linearLayoutDetail);
            }
        }

    }

    private void searchTransactionIdFunction (String transaction_id) {
        if (transaction_id.length() > 2){
            LinkedList<HashMap<String,Object>> linkedListSearchResult = new LinkedList<>();
            progressBarHistory.setVisibility(View.VISIBLE);
            linearLayoutHistoryDescription.setVisibility(View.INVISIBLE);
            for (int i = 0; i < linkedListHistory.size(); i++) {
                if (((String) linkedListHistory.get(i).get(KEY_TRANSACTION_MASTER_ID)).contains(transaction_id)){
                    linkedListSearchResult.add(linkedListHistory.get(i));
                }
            }
            if (linkedListSearchResult.size()==0){
                progressBarHistory.setVisibility(View.GONE);
                listViewHistory.setVisibility(View.GONE);
                linearLayoutHistoryEmpty.setVisibility(View.VISIBLE);
            }else {
                listViewAdapterHistory = new ListViewAdapterHistory(
                        getActivity(),
                        linkedListSearchResult
                );
                progressBarHistory.setVisibility(View.GONE);
                listViewHistory.setVisibility(View.VISIBLE);
                linearLayoutHistoryEmpty.setVisibility(View.GONE);
                listViewHistory.setAdapter(listViewAdapterHistory);
            }
        }else {
            listViewAdapterHistory = new ListViewAdapterHistory(
                    getActivity(),
                    linkedListHistory
            );
            progressBarHistory.setVisibility(View.GONE);
            listViewHistory.setVisibility(View.VISIBLE);
            linearLayoutHistoryEmpty.setVisibility(View.GONE);

            listViewHistory.setAdapter(listViewAdapterHistory);
        }
    }

    private String nameOfDay (String date_time){
        String dayOfTheWeek = "";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date date = format.parse(date_time);
            dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayOfTheWeek;
    }

    private String dateDesign(String date_time){
        String dateDesign = "";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date date = format.parse(date_time);
            dateDesign = (String) android.text.format.DateFormat.format("dd MMMM yyyy", date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateDesign;
    }

    private void correctTransactionWithVoid(String kode, String username, String pass, String transactId){
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "loading...");

        ApiService apiService = RestApi.getClient().create(ApiService.class);

        Call<VoidTransaction> call = apiService.voidTransaction(kode, username, pass, transactId);
        call.enqueue(new Callback<VoidTransaction>() {
            @Override
            public void onResponse(Call<VoidTransaction> call, retrofit2.Response<VoidTransaction> response) {
                dialog.dismiss();

                Log.d(TAG, "Status Code = " + response.code());
                Log.d(TAG, "Start Job received: " + new Gson().toJson(response.body()));

                if (response.code() == 200 && response.body().getStatus().equalsIgnoreCase("true") ) {
                    currentDateTimeString = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Success");
                    alertDialog.setMessage("Transaction Removed");
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String tes = hashMapDataHistory.get(KEY_TRANSACTION_MASTER_ID).toString();

                            ((CashRegisterActivity)getActivity()).realm.beginTransaction();

                            RealmResults<TransactionMaster> result = ((CashRegisterActivity)getActivity()).realm
                                    .where(TransactionMaster.class)
                                    .equalTo("transactionMasterID", tes )
                                    .findAll();
                            result.deleteAllFromRealm();

                            RealmResults<TransactionDetail> resultDetail = ((CashRegisterActivity)getActivity()).realm
                                    .where(TransactionDetail.class)
                                    .equalTo("transactionDetailID", tes )
                                    .findAll();
                            resultDetail.deleteAllFromRealm();

                            ((CashRegisterActivity)getActivity()).realm.commitTransaction();

                            getActivity()
                                    .getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.frameLayout, new fHistory())
                                    .commit();

                            Log.e(TAG, "SESUATU 2 : " + resultDetail);
                            Log.e(TAG, "SESUATU : " + result);
                        }
                    });
                    alertDialog.show();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setTitle("Warning!");
                    alertDialog.setMessage("Gagal membatalkan transaksi !");
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(Call<VoidTransaction>call, Throwable t) {
                dialog.dismiss();

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Try Again");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });
    }

}

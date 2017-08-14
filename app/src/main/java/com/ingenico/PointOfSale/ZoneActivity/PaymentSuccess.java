package com.ingenico.PointOfSale.ZoneActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.AppController;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.ModelRealm.TransactionMaster;
import com.ingenico.PointOfSale.PointOfSaleActivity;
import com.ingenico.PointOfSale.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 5/23/2016.
 */
public class PaymentSuccess extends PointOfSaleActivity {

    EditText editTextEmailAddress;
    TextView textViewChangePaymentSuccess, textViewPaymentSalesNewSales;
    Button buttonPrintReceipt, buttonNextSkipReceipt;
    String transaction_id,change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        editTextEmailAddress = (EditText)findViewById(R.id.editTextEmailAddress);
        textViewChangePaymentSuccess = (TextView)findViewById(R.id.textViewChangePaymentSuccess);
        textViewPaymentSalesNewSales = (TextView)findViewById(R.id.textViewPaymentSalesNewSales);
        buttonPrintReceipt = (Button)findViewById(R.id.buttonPrintReceipt);
        buttonNextSkipReceipt = (Button)findViewById(R.id.buttonNextSkipReceipt);

        final RealmResults<TransactionMaster> realmResultsTransactionMasterSync = realm
                .where(TransactionMaster.class)
                .findAll();

        final RealmResults<TransactionDetail> realmResultsTransactionDetailSync = realm
                .where(TransactionDetail.class)
                .findAll();

        JSONObject ses = JsonSync(realmResultsTransactionMasterSync,realmResultsTransactionDetailSync);
        Log.e("SEND EMAIL", "send_email : "+ ses.toString());

        transaction_id = getIntent().getExtras().getString("transaction_id");
        change = getIntent().getExtras().getString("total_change");
        textViewChangePaymentSuccess.setText(change);

        textViewPaymentSalesNewSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printer.closeBluetoothConnection();
                Intent mIntent = new Intent(getApplicationContext(), CashRegisterActivity.class);
                startActivity(mIntent);
                finish();
            }
        });

        editTextEmailAddress.addTextChangedListener(textWatcherListener());

        buttonNextSkipReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (buttonNextSkipReceipt.getText().toString()){
                    case "SEND RECEIPT":{
                        inputEmailToTransactionMaster(
                                realm,
                                transaction_id,
                                editTextEmailAddress.getText().toString());
                        break;
                    }case "SKIP RECEIPT":{
                        printer.closeBluetoothConnection();
                        startActivity(new Intent(PaymentSuccess.this, CashRegisterActivity.class));
                        finish();
                        break;
                    }
                }
            }
        });

        buttonPrintReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printBillTransaction(realm,transaction_id);
            }
        });

    }

    private TextWatcher textWatcherListener() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                if (emailValidator.validate(editTextEmailAddress.getText().toString())){
//                    buttonNextSkipReceipt.setBackground(getResources().getDrawable(R.drawable.charge));
//                    buttonNextSkipReceipt.setText("SEND RECEIPT");
//                }else {
//                    buttonNextSkipReceipt.setBackground(getResources().getDrawable(R.drawable.skip_receipt));
//                    buttonNextSkipReceipt.setText("SKIP RECEIPT");
//                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (emailValidator.validate(editTextEmailAddress.getText().toString())){
                    buttonNextSkipReceipt.setBackground(getResources().getDrawable(R.drawable.charge));
                    buttonNextSkipReceipt.setTextColor(getResources().getColor(R.color.white));
                    buttonNextSkipReceipt.setText("SEND RECEIPT");
                }else {
                    buttonNextSkipReceipt.setBackground(getResources().getDrawable(R.drawable.skip_receipt));
                    buttonNextSkipReceipt.setTextColor(getResources().getColor(android.R.color.white));
                    buttonNextSkipReceipt.setText("SKIP RECEIPT");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (emailValidator.validate(editTextEmailAddress.getText().toString())){
//                    buttonNextSkipReceipt.setBackground(getResources().getDrawable(R.drawable.charge));
//                    buttonNextSkipReceipt.setText("SEND RECEIPT");
//                }else {
//                    buttonNextSkipReceipt.setBackground(getResources().getDrawable(R.drawable.skip_receipt));
//                    buttonNextSkipReceipt.setText("SKIP RECEIPT");
//                }
            }
        };
        return textWatcher;
    }

    private void inputEmailToTransactionMaster (Realm realm, String transaction_id, String email){
        RealmResults<TransactionMaster> realmResultsTransactionMaster = realm.where(TransactionMaster.class)
                .equalTo("transactionMasterID", transaction_id)
                .findAll();
        realm.beginTransaction();
        realmResultsTransactionMaster.get(0).setTransactionMasterEmail(email);
        realm.commitTransaction();

        final RealmResults<TransactionMaster> realmResultsTransactionMasterSync = realm
                .where(TransactionMaster.class)
                .findAll();

        final RealmResults<TransactionDetail> realmResultsTransactionDetailSync = realm
                .where(TransactionDetail.class)
                .findAll();

        Log.e("SEND EMAIL", "send_email : "+ JsonSync(realmResultsTransactionMasterSync,realmResultsTransactionDetailSync).toString());

        SynchronizingDataToServer(JsonSync(realmResultsTransactionMasterSync,realmResultsTransactionDetailSync));

        Intent mIntent = new Intent(getApplicationContext(), CashRegisterActivity.class);
        startActivity(mIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(home);
    }

    public void SynchronizingDataToServer (final JSONObject jsonObject){
        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");
        logger.addInfo("JSON Req",jsonObject.toString());
        String tag_json_req = "send_API";
        /**
         * Show progress dialog
         */
        dialog.show();

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
                dialog.hide();
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
                dialog.hide();
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

}

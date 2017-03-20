package com.ingenico.PointOfSale.ZoneActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.PointOfSaleActivity;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.ModelRealm.TransactionMaster;
import com.ingenico.PointOfSale.R;

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
                        Intent mIntent = new Intent(getApplicationContext(), CashRegisterActivity.class);
                        startActivity(mIntent);
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
                    buttonNextSkipReceipt.setText("SEND RECEIPT");
                }else {
                    buttonNextSkipReceipt.setBackground(getResources().getDrawable(R.drawable.skip_receipt));
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
}

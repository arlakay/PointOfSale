package com.ingenico.PointOfSale.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.ModelRealm.TransactionMaster;
import com.ingenico.PointOfSale.R;
import com.ingenico.PointOfSale.CashRegisterActivity;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 7/21/2016.
 */
public class DialogAddEmail extends DialogFragment {


    private String transaction_id= "";
    public void setTransaction_id(String t_id){this.transaction_id = t_id;}

    EditText editTextDialogEmailSendReceipt;
    Button buttonDialogEmailSendReceipt;
    ImageView imageViewDialogHistoryClose;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View RootView=inflater.inflate(R.layout.dialog_add_email_history, null);

        editTextDialogEmailSendReceipt = (EditText) RootView.findViewById(R.id.editTextDialogEmailSendReceipt);
        buttonDialogEmailSendReceipt = (Button) RootView.findViewById(R.id.buttonDialogEmailSendReceipt);
        imageViewDialogHistoryClose = (ImageView) RootView.findViewById(R.id.imageViewDialogHistoryClose);

        imageViewDialogHistoryClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        buttonDialogEmailSendReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CashRegisterActivity)getActivity()).emailValidator.validate(
                        editTextDialogEmailSendReceipt.getText().toString())){
                    inputEmailToTransactionMaster(
                            ((CashRegisterActivity)getActivity()).realm,
                            transaction_id,
                            editTextDialogEmailSendReceipt.getText().toString()
                    );
                    getDialog().dismiss();
                }else {
                    Toast.makeText(getActivity(), "Please input your email correctly", Toast.LENGTH_SHORT).show();
                }

            }
        });

        setCancelable(false);
        builder.setView(RootView);
        return builder.create();
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

        ((CashRegisterActivity)getActivity()).SynchronizingDataToServer(
                ((CashRegisterActivity)getActivity()).JsonSync(
                        realmResultsTransactionMasterSync,
                        realmResultsTransactionDetailSync
                )
        );
    }

}

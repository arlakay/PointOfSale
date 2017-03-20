package com.ingenico.PointOfSale.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Controller.SessionManager;
import com.ingenico.PointOfSale.ModelRealm.Merchant;
import com.ingenico.PointOfSale.Object.Discount;
import com.ingenico.PointOfSale.R;

import java.util.Random;

import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 2/1/2017.
 */

public class DialogInventoryDiscount extends DialogFragment {

    private Discount discount = null;
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    private EditText editTextDialogInventoryDiscountName, editTextDialogInventoryDiscountValue;
    private TextView textViewDialogInventoryAdd;
    private ImageView imageViewDialogInventoryCancel;
    private Button buttonPercent, buttonFixValue, buttonDialogInventoryDiscountDelete;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View RootView=inflater.inflate(R.layout.dialog_inventory_discount, null);

        editTextDialogInventoryDiscountName = (EditText)RootView.findViewById(R.id.editTextDialogInventoryDiscountName);
        editTextDialogInventoryDiscountValue = (EditText)RootView.findViewById(R.id.editTextDialogInventoryDiscountValue);
        textViewDialogInventoryAdd = (TextView)RootView.findViewById(R.id.textViewDialogInventoryAdd);
        imageViewDialogInventoryCancel = (ImageView)RootView.findViewById(R.id.imageViewDialogInventoryCancel);
        buttonPercent = (Button)RootView.findViewById(R.id.buttonPercent);
        buttonFixValue = (Button)RootView.findViewById(R.id.buttonFixValue);
        buttonDialogInventoryDiscountDelete = (Button)RootView.findViewById(R.id.buttonDialogInventoryDiscountDelete);

        if (discount!=null){
            buttonDialogInventoryDiscountDelete.setVisibility(View.VISIBLE);
        }else {
            buttonDialogInventoryDiscountDelete.setVisibility(View.GONE);
        }

        generateView();

        textViewDialogInventoryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateView();
            }
        });

        buttonDialogInventoryDiscountDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<com.ingenico.PointOfSale.ModelRealm.DiscountMaster> realmResultDiscount =
                        ((CashRegisterActivity)getActivity()).realm.where(com.ingenico.PointOfSale.ModelRealm.DiscountMaster.class)
                                .equalTo("discountMasterID",discount.getDiscountMasterID())
                                .findAll();
                ((CashRegisterActivity)getActivity()).realm.beginTransaction();
                realmResultDiscount.deleteAllFromRealm();
                ((CashRegisterActivity)getActivity()).realm.commitTransaction();
                getDialog().dismiss();
            }
        });

        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableButtonFixValue();
            }
        });

        buttonFixValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableButtonFixValue();
            }
        });

        imageViewDialogInventoryCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        setCancelable(false);
        builder.setView(RootView);
        return builder.create();
    }

    private void validateView() {
        if (editTextDialogInventoryDiscountName.getText().toString().trim().length()>0){
            String value = editTextDialogInventoryDiscountValue.getText().toString().trim();
            if (value.trim().length()>0){
                if (Integer.parseInt(value)!=0){
                    if (!buttonPercent.isEnabled() && buttonFixValue.isEnabled()){
                        if (Integer.parseInt(value)<=100){
                            getAllData(Declaration.PERCENT);
                        }else {
                            showAlertDialog();
                        }
                    }else if(buttonPercent.isEnabled() && !buttonFixValue.isEnabled()){
                        getAllData(Declaration.FIX_PRICE);
                    }else {
                        showAlertDialog();
                    }
                }else {
                    showAlertDialog();
                }
            }else {
                showAlertDialog();
            }
        }else {
            showAlertDialog();
        }
    }

    private void getAllData(String discount_type){
        RealmResults<com.ingenico.PointOfSale.ModelRealm.Merchant> realmResultMerchant =
                ((CashRegisterActivity)getActivity()).realm.where(com.ingenico.PointOfSale.ModelRealm.Merchant.class)
                        .findAll();
        String discountID;
        if (discount!=null){
            discountID = discount.getDiscountMasterID();
        }else {
            discountID = createItemID(realmResultMerchant);
        }
        Discount discount_result = new Discount();
        discount_result.setDiscountMasterID(discountID);
        discount_result.setDiscountMasterName(editTextDialogInventoryDiscountName.getText().toString());
        discount_result.setDiscountMasterValue(editTextDialogInventoryDiscountValue.getText().toString());
        discount_result.setDiscountMasterType(discount_type);
        discount_result.setDiscountMasterOutletID(
                ((CashRegisterActivity)getActivity()).session.get(SessionManager.KEY_OUTLET_ID)
        );

        updateDiscountAtDatabase(discount_result);

    }

    private void updateDiscountAtDatabase(Discount discount) {
        RealmResults<com.ingenico.PointOfSale.ModelRealm.DiscountMaster> realmResultDiscount =
                ((CashRegisterActivity)getActivity()).realm.where(com.ingenico.PointOfSale.ModelRealm.DiscountMaster.class)
                        .equalTo("discountMasterID",discount.getDiscountMasterID())
                        .findAll();
        if (realmResultDiscount.size()>0){
            //delete discount
            ((CashRegisterActivity)getActivity()).realm.beginTransaction();
            realmResultDiscount.deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.commitTransaction();
            //create new discount with same discount id
            ((CashRegisterActivity)getActivity()).RealmCreateDatabaseTableDiscountMaster(
                    ((CashRegisterActivity)getActivity()).realm,
                    (new Random()).nextInt(9999) + 1,
                    discount.getDiscountMasterID(),
                    discount.getDiscountMasterName(),
                    discount.getDiscountMasterValue(),
                    discount.getDiscountMasterType(),
                    discount.getDiscountMasterOutletID()
            );
        }else {
            //create new discount with new discount id
            ((CashRegisterActivity)getActivity()).RealmCreateDatabaseTableDiscountMaster(
                    ((CashRegisterActivity)getActivity()).realm,
                    (new Random()).nextInt(9999) + 1,
                    discount.getDiscountMasterID(),
                    discount.getDiscountMasterName(),
                    discount.getDiscountMasterValue(),
                    discount.getDiscountMasterType(),
                    discount.getDiscountMasterOutletID()
            );
        }
        getDialog().dismiss();
    }

    private String createItemID(RealmResults<Merchant> realm_result_merchant){
        return realm_result_merchant.get(0).getMerchantID()+
                "_diskon_"+
                getMacAddress()+
                (new Random()).nextInt(9999) + 1;
    }

    private String getMacAddress(){
        String macAddress;
        WifiManager manager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        macAddress = info.getMacAddress();
        return macAddress;
    }

    private void showAlertDialog(){
        ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent(
                getActivity(),
                "Sorry",
                "Please input discount correctly"
        );
    }

    private void generateView() {
        if (discount!=null){
            editTextDialogInventoryDiscountName.setText(discount.getDiscountMasterName());
            editTextDialogInventoryDiscountValue.setText(discount.getDiscountMasterValue());
            if (discount.getDiscountMasterType().equals(Declaration.PERCENT)){
                enableButtonFixValue();
            }else {
                enableButtonPercent();
            }
        }else {
            enableButtonPercent();
        }

    }

    private void enableButtonFixValue(){
        buttonPercent.setEnabled(false);
        buttonFixValue.setEnabled(true);
    }
    private void enableButtonPercent(){
        buttonPercent.setEnabled(true);
        buttonFixValue.setEnabled(false);
    }

}

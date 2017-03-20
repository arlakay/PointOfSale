package com.ingenico.PointOfSale.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.ModelRealm.Drawer;
import com.ingenico.PointOfSale.ModelRealm.SaveOrder;
import com.ingenico.PointOfSale.R;
import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.ZoneActivity.LoginActivity;
import com.ingenico.PointOfSale.ZoneActivity.StaffDashboardActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 7/27/2016.
 */
public class DialogEndDrawer extends DialogFragment {

    private long drawer_id = -1;
    private String description= "";
    private String date = "";
    private String start_drawer = "";
    private String starting_cash = "";
    private String cash_sales = "";
    private String card_sales = "";
    private String expected_in_drawer = "";

    public void setDrawerID(long id){this.drawer_id = id;}
    public void setDescription(String d){this.description = d;}
    public void setDate(String dt){this.date = dt;}
    public void setStartDrawer(String sd){this.start_drawer = sd;}
    public void setStartingCash(String sc){this.starting_cash = sc;}
    public void setCashSales(String cs){this.cash_sales = cs;}
    public void setCardSales(String cds){this.card_sales = cds;}
    public void setExpectedInDrawer(String exd){this.expected_in_drawer = exd;}

    private ImageView imageViewReportDrawerDialogEndDrawerCancel;
    private TextView textViewReportDrawerDialogEndDrawerAdd,textViewReportDrawerDialogEndDrawerDate,
            textViewReportDrawerDialogEndDrawerStartDrawer,textViewReportDrawerDialogEndDrawerStartingCash,
            textViewReportDrawerDialogEndDrawerCardSale,
            textViewReportDrawerDialogEndDrawerCashSale,textViewReportDrawerDialogEndDrawerExpectedDrawer;
    private EditText editTextReportDrawerDialogEndDrawerExactAmount;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View RootView=inflater.inflate(R.layout.dialog_end_drawer, null);


        imageViewReportDrawerDialogEndDrawerCancel = (ImageView)RootView.findViewById(R.id.imageViewReportDrawerDialogEndDrawerCancel);
        textViewReportDrawerDialogEndDrawerAdd = (TextView)RootView.findViewById(R.id.textViewReportDrawerDialogEndDrawerAdd);
        textViewReportDrawerDialogEndDrawerDate = (TextView)RootView.findViewById(R.id.textViewReportDrawerDialogEndDrawerDate);
        textViewReportDrawerDialogEndDrawerStartDrawer = (TextView)RootView.findViewById(R.id.textViewReportDrawerDialogEndDrawerStartDrawer);
        textViewReportDrawerDialogEndDrawerStartingCash = (TextView)RootView.findViewById(R.id.textViewReportDrawerDialogEndDrawerStartingCash);
        textViewReportDrawerDialogEndDrawerCashSale = (TextView)RootView.findViewById(R.id.textViewReportDrawerDialogEndDrawerCashSale);
        textViewReportDrawerDialogEndDrawerCardSale = (TextView)RootView.findViewById(R.id.textViewReportDrawerDialogEndDrawerCardSale);
        textViewReportDrawerDialogEndDrawerExpectedDrawer = (TextView)RootView.findViewById(R.id.textViewReportDrawerDialogEndDrawerExpectedDrawer);
        editTextReportDrawerDialogEndDrawerExactAmount = (EditText)RootView.findViewById(R.id.editTextReportDrawerDialogEndDrawerExactAmount);

        textViewReportDrawerDialogEndDrawerDate.setText(date);
        textViewReportDrawerDialogEndDrawerStartDrawer.setText(start_drawer);
        textViewReportDrawerDialogEndDrawerStartingCash.setText(starting_cash);
        textViewReportDrawerDialogEndDrawerCashSale.setText(cash_sales);
        textViewReportDrawerDialogEndDrawerCardSale.setText(card_sales);
        textViewReportDrawerDialogEndDrawerExpectedDrawer.setText(expected_in_drawer);

//        editTextReportDrawerDialogEndDrawerExactAmount.addTextChangedListener(
//                ((CashRegisterActivity)getActivity()).textWatcherKilos(editTextReportDrawerDialogEndDrawerExactAmount)
//        );

        imageViewReportDrawerDialogEndDrawerCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        textViewReportDrawerDialogEndDrawerAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextReportDrawerDialogEndDrawerExactAmount.getText().toString().equals("")){
                    if (!haveTransactionInSaveOrder()){
                        String exact_amount = String.valueOf(Integer.parseInt(
//                                ((CashRegisterActivity)getActivity()).revertAfterTextWatcher(
                                        editTextReportDrawerDialogEndDrawerExactAmount.getText().toString()
//                                )
                        ));
                        if (!exact_amount.equals("") && !exact_amount.equals("0")){
                            updateDrawerToEndDrawer(description,endingDateAndTime(),exact_amount);
                        }else {
                            Toast.makeText(getActivity(), "Please insert valid value", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getActivity(),
                                "Sorry you still have a saved transaction,, please finish the transaction",
                                Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), "Please insert valid value", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setCancelable(false);
        builder.setView(RootView);
        return builder.create();
    }

    private void updateDrawerToEndDrawer(String description, String ending_date_and_time, String actual_cash){
        RealmResults<Drawer> realmResultsDrawer = ((CashRegisterActivity)getActivity()).realm.where(Drawer.class).findAll();

        ((CashRegisterActivity)getActivity()).realm.beginTransaction();
        realmResultsDrawer.last().setDrawerDescription(description);
        realmResultsDrawer.last().setDrawerEndingDateAndTime(ending_date_and_time);
        realmResultsDrawer.last().setDrawerActualCash(actual_cash);
        ((CashRegisterActivity)getActivity()).realm.commitTransaction();

        changeDrawerSessionAndLogoutCashier();
    }

    private void changeDrawerSessionAndLogoutCashier(){
        ((CashRegisterActivity)getActivity()).sessionManager.setKeyIsDrawerLoggedin(false);
        ((CashRegisterActivity)getActivity()).sessionManager.setKeyIsCashierLoggedin(false);
        if (((CashRegisterActivity)getActivity()).sessionManager.getKeyAccessCode().equals("3")){
            ((CashRegisterActivity)getActivity()).sessionManager.setAccessCode("null data");
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        }
        else {
            startActivity(new Intent(getActivity(), StaffDashboardActivity.class));
            getActivity().finish();
        }
    }

    private String endingDateAndTime (){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    private boolean haveTransactionInSaveOrder (){
        boolean value = false;

        RealmResults<SaveOrder> realmResultsSaveOrder = ((CashRegisterActivity)getActivity()).realm
                .where(SaveOrder.class)
                .findAll();
        for (int i = 0; i < realmResultsSaveOrder.size(); i++) {
            if (!realmResultsSaveOrder.get(i).getSaveOrderTransactionID().equals(Declaration.NOT_FILLED)){
                value = true;
            }
        }

        return value;
    }
}

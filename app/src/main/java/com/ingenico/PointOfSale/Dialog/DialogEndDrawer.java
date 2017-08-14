package com.ingenico.PointOfSale.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.AppController;
import com.ingenico.PointOfSale.Controller.Declaration;
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
import com.ingenico.PointOfSale.ModelRealm.TransactionMaster;
import com.ingenico.PointOfSale.ModelRealm.Variant;
import com.ingenico.PointOfSale.R;
import com.ingenico.PointOfSale.ZoneActivity.LoginActivity;
import com.ingenico.PointOfSale.ZoneActivity.StaffDashboardActivity;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.realm.RealmResults;

import static com.ingenico.PointOfSale.Controller.Declaration.LOG_OUTPUT_PATH;

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

                            final RealmResults<TransactionMaster> realmResultsTransactionMaster = ((CashRegisterActivity)getActivity()).realm
                                    .where(TransactionMaster.class)
                                    .findAll();

                            final RealmResults<TransactionDetail> realmResultsTransactionDetail = ((CashRegisterActivity)getActivity()).realm
                                    .where(TransactionDetail.class)
                                    .findAll();

                            JSONObject oiuhb = ((CashRegisterActivity)getActivity()).JsonSync(realmResultsTransactionMaster,realmResultsTransactionDetail);
                            Log.e("END DRAWER", "hayoloh: "+ oiuhb.toString());
                            ((CashRegisterActivity)getActivity()).logger.addInfo("JSON SYNC FROM END DRAWER = ",oiuhb.toString());
                            SynchronizingDataToServer(oiuhb);

                            String currentDateTimeString = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(new Date());

                            try {
                                Writer output = null;
                                File file = new File(LOG_OUTPUT_PATH + "OctoPosTransaction" + currentDateTimeString + ".json");
                                output = new BufferedWriter(new FileWriter(file));
                                output.write(oiuhb.toString());
                                output.close();
                                Toast.makeText(getActivity(), "Berhasil Mengexport transaksi ke file", Toast.LENGTH_SHORT).show();

                            } catch (Exception e) {
//                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                                Log.e("Error", "error:"+ e.getMessage());
                            }


//                            String content = oiuhb.toString();
//                            FileWriter fw = null;
//                            try {
//                                fw = new FileWriter("/storage/sdcard0/Android/data/com.ingenico.PointOfSale/files/transaction"+currentDateTimeString+".json");
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            BufferedWriter bw = new BufferedWriter(fw);
//                            try {
//                                bw.write(content);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                bw.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }

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

    private void changeDrawerSessionAndLogoutCashier() {
        ((CashRegisterActivity) getActivity()).sessionManager.setKeyIsDrawerLoggedin(false);
        ((CashRegisterActivity) getActivity()).sessionManager.setKeyIsCashierLoggedin(false);
        if (((CashRegisterActivity) getActivity()).sessionManager.getKeyAccessCode().equals("3")) {
            ((CashRegisterActivity) getActivity()).sessionManager.setAccessCode("null data");

            //please clean up database if cahsierlogin
            ((CashRegisterActivity)getActivity()).realm.beginTransaction();
            ((CashRegisterActivity)getActivity()).realm.allObjects(Category.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.allObjects(DiscountMaster.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.allObjects(Drawer.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.allObjects(Item.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.allObjects(ItemModifierGroup.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.allObjects(ItemVariant.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.allObjects(Login.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.allObjects(Merchant.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.allObjects(ModifierDetail.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.allObjects(ModifierGroup.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.allObjects(Outlet.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.allObjects(SaveOrder.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.allObjects(Service.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.allObjects(Tax.class).deleteAllFromRealm();
//            ((CashRegisterActivity)getActivity()).realm.allObjects(TransactionDetail.class).deleteAllFromRealm();
//            ((CashRegisterActivity)getActivity()).realm.allObjects(TransactionDetailDiscount.class).deleteAllFromRealm();
//            ((CashRegisterActivity)getActivity()).realm.allObjects(TransactionDetailModifier.class).deleteAllFromRealm();
//            ((CashRegisterActivity)getActivity()).realm.allObjects(TransactionMaster.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.allObjects(Variant.class).deleteAllFromRealm();
            ((CashRegisterActivity)getActivity()).realm.commitTransaction();

            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        } else {
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

    private void SynchronizingDataToServer (final JSONObject jsonObject){
        String tag_json_req = "send_API";
//        ((CashRegisterActivity)getActivity()).logger.addInfo("JSON Req",jsonObject.toString());
//        ((CashRegisterActivity)getActivity()).showProgressDialog();

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,
                Declaration.URL_SYNC,  new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//                ((CashRegisterActivity)getActivity()).logger.addInfo("Response API",response);
//                ((CashRegisterActivity)getActivity()).hideProgressDialog();

                Log.e("SUCCESS", "Success : " + response.toString());
//                Toast.makeText(getContext().getApplicationContext(), "Transaction Sync", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("ERROR SYNC", "SYNC ERROR : " + error.getStackTrace());
//                ((CashRegisterActivity)getActivity()).logger.addInfo("VolleyError API",error.toString());
//                ((CashRegisterActivity)getActivity()).hideProgressDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sync", jsonObject.toString());
                Log.e("History", "json sync kirim : " + jsonObject.toString());
//                ((CashRegisterActivity)getActivity()).logger.addInfo("Parameter in Volley",params.toString());
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                Declaration.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_req);
    }

}

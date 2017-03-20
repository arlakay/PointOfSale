package com.ingenico.PointOfSale.Fragment;

import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ingenico.PointOfSale.ModelRealm.Drawer;
import com.ingenico.PointOfSale.ModelRealm.Item;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.ModelRealm.TransactionMaster;
import com.ingenico.PointOfSale.R;
import com.ingenico.PointOfSale.CashRegisterActivity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 7/22/2016.
 */
public class fReport extends Fragment {

    private RadioGroup radioGroupReport;
    private RadioButton radioButtonSale, radioButtonDrawer;
    private LinearLayout linearLayoutReportSalesSelectDate, linearLayoutHistoryDescription, linearLayoutReportDrawer;
    private TextView textViewReportSalesSelectDate, textViewReportSalesNoTransactionWarning, textViewReportSalesGrossSalesTop,
            textViewReportSalesTotalTransaction,textViewReportSalesAverageSale,textViewReportSalesSummaryGrossSales,
            textViewReportSalesSummaryDiscount,textViewReportSalesSummaryNetSales,textViewReportSalesSummaryService,
            textViewReportSalesSummaryTax,textViewReportSalesSummaryTotalCollected,textViewReportSalesSummaryCash,
            textViewReportSalesSummaryNetTotal,textViewReportDrawerDate,textViewReportDrawerStartDrawer,
            textViewReportDrawerStartingCash, textViewReportDrawerCashSale,textViewReportDrawerExpectedDrawer,
            textViewReportSalesSummaryHotSalesItem,textViewReportSalesDay, textViewReportSalesHotSalesTop,
            textViewReportSalesSummaryCard,textViewReportDrawerCardSale;
    private ImageView imageViewReportSalesCalender;
    private EditText editTextReportDrawerDescription;
    private Button buttonReportDrawerEnd;
    private CardView cardViewReportSale;
    private DatePickerDialog fromDatePickerDialog;
    private String reportCategory;
    public fReport(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_report, container, false);
        radioGroupReport = (RadioGroup)rootView.findViewById(R.id.radioGroupReport);
        radioButtonSale = (RadioButton)rootView.findViewById(R.id.radioButtonSale);
        radioButtonDrawer = (RadioButton)rootView.findViewById(R.id.radioButtonDrawer);
        linearLayoutReportSalesSelectDate = (LinearLayout)rootView.findViewById(R.id.linearLayoutReportSalesSelectDate);
        linearLayoutHistoryDescription = (LinearLayout)rootView.findViewById(R.id.linearLayoutHistoryDescription);
        textViewReportSalesSelectDate = (TextView)rootView.findViewById(R.id.textViewReportSalesSelectDate);
        textViewReportSalesNoTransactionWarning = (TextView)rootView.findViewById(R.id.textViewReportSalesNoTransactionWarning);
        imageViewReportSalesCalender = (ImageView)rootView.findViewById(R.id.imageViewReportSalesCalender);
        cardViewReportSale = (CardView)rootView.findViewById(R.id.cardViewReportSale);
        linearLayoutReportDrawer = (LinearLayout)rootView.findViewById(R.id.linearLayoutReportDrawer);
        editTextReportDrawerDescription = (EditText)rootView.findViewById(R.id.editTextReportDrawerDescription);
        buttonReportDrawerEnd = (Button)rootView.findViewById(R.id.buttonReportDrawerEnd);

        textViewReportSalesGrossSalesTop = (TextView)rootView.findViewById(R.id.textViewReportSalesGrossSalesTop);
        textViewReportSalesTotalTransaction = (TextView)rootView.findViewById(R.id.textViewReportSalesTotalTransaction);
        textViewReportSalesAverageSale = (TextView)rootView.findViewById(R.id.textViewReportSalesAverageSale);
        textViewReportSalesSummaryGrossSales = (TextView)rootView.findViewById(R.id.textViewReportSalesSummaryGrossSales);
        textViewReportSalesSummaryDiscount = (TextView)rootView.findViewById(R.id.textViewReportSalesSummaryDiscount);
        textViewReportSalesSummaryNetSales = (TextView)rootView.findViewById(R.id.textViewReportSalesSummaryNetSales);
        textViewReportSalesSummaryService = (TextView)rootView.findViewById(R.id.textViewReportSalesSummaryService);
        textViewReportSalesSummaryTax = (TextView)rootView.findViewById(R.id.textViewReportSalesSummaryTax);
        textViewReportSalesSummaryTotalCollected = (TextView)rootView.findViewById(R.id.textViewReportSalesSummaryTotalCollected);
        textViewReportSalesSummaryCash = (TextView)rootView.findViewById(R.id.textViewReportSalesSummaryCash);
        textViewReportSalesSummaryCard = (TextView)rootView.findViewById(R.id.textViewReportSalesSummaryCard);
        textViewReportSalesSummaryNetTotal = (TextView)rootView.findViewById(R.id.textViewReportSalesSummaryNetTotal);
        textViewReportSalesSummaryHotSalesItem = (TextView)rootView.findViewById(R.id.textViewReportSalesSummaryHotSalesItem);
        textViewReportDrawerDate = (TextView)rootView.findViewById(R.id.textViewReportDrawerDate);
        textViewReportDrawerStartDrawer = (TextView)rootView.findViewById(R.id.textViewReportDrawerStartDrawer);
        textViewReportDrawerStartingCash = (TextView)rootView.findViewById(R.id.textViewReportDrawerStartingCash);
        textViewReportDrawerCashSale = (TextView)rootView.findViewById(R.id.textViewReportDrawerCashSale);
        textViewReportDrawerCardSale = (TextView)rootView.findViewById(R.id.textViewReportDrawerCardSale);
        textViewReportDrawerExpectedDrawer = (TextView)rootView.findViewById(R.id.textViewReportDrawerExpectedDrawer);
        textViewReportSalesDay = (TextView)rootView.findViewById(R.id.textViewReportSalesDay);
        textViewReportSalesHotSalesTop = (TextView)rootView.findViewById(R.id.textViewReportSalesHotSalesTop);

        radioGroupReport.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                reportCategory = ((RadioButton)rootView.findViewById(radioGroupReport.getCheckedRadioButtonId())).getText().toString();
                switch (reportCategory){
                    case "Sales":{
                        cardViewReportSale.setVisibility(View.VISIBLE);
                        linearLayoutReportDrawer.setVisibility(View.GONE);
                        break;
                    }case "Drawer":{
                        cardViewReportSale.setVisibility(View.GONE);
                        linearLayoutReportDrawer.setVisibility(View.VISIBLE);
                        break;
                    }
                }

            }
        });


        imageViewReportSalesCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });

        textViewReportSalesSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });

        final RealmResults<Drawer> realmResultsDrawer = ((CashRegisterActivity)getActivity()).realm
                .where(Drawer.class)
                .findAll();

        createViewReportDrawer(realmResultsDrawer.last());


        buttonReportDrawerEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CashRegisterActivity)getActivity()).dialogEndDrawer.setDrawerID(realmResultsDrawer.last().getDrawerID());
                ((CashRegisterActivity)getActivity()).dialogEndDrawer.setDescription(editTextReportDrawerDescription.getText().toString()+"");
                ((CashRegisterActivity)getActivity()).dialogEndDrawer.setDate(textViewReportDrawerDate.getText().toString());
                ((CashRegisterActivity)getActivity()).dialogEndDrawer.setStartDrawer(textViewReportDrawerStartDrawer.getText().toString());
                ((CashRegisterActivity)getActivity()).dialogEndDrawer.setStartingCash(textViewReportDrawerStartingCash.getText().toString());
                ((CashRegisterActivity)getActivity()).dialogEndDrawer.setCashSales(textViewReportDrawerCashSale.getText().toString());
                ((CashRegisterActivity)getActivity()).dialogEndDrawer.setCardSales(textViewReportDrawerCardSale.getText().toString());
                ((CashRegisterActivity)getActivity()).dialogEndDrawer.setExpectedInDrawer(textViewReportDrawerExpectedDrawer.getText().toString());
                ((CashRegisterActivity)getActivity()).dialogEndDrawer.show(getActivity().getFragmentManager(),"show_end_drawer_dialog");
            }
        });


        return rootView;
    }



    private void setDate (){
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String nameOfMonth;
                if (!(monthOfYear+1>9)){
                    nameOfMonth = "0"+String.valueOf(monthOfYear+1);
                }else {
                    nameOfMonth = String.valueOf(monthOfYear+1);
                }

                RealmResults<TransactionMaster> realmResultsTransactionMaster =
                        ((CashRegisterActivity)getActivity()).realm
                        .where(TransactionMaster.class)
                        .equalTo("transactionMasterDate", addPaddingZero(String.valueOf(dayOfMonth))+"-"+nameOfMonth+"-"+String.valueOf(year))
                        .findAll();
                RealmResults<TransactionMaster> realmResultsTransactionMastertest =
                        ((CashRegisterActivity)getActivity()).realm
                                .where(TransactionMaster.class)
                                .findAll();
                Log.e("Error",String.valueOf(realmResultsTransactionMaster));
                Log.e("DATE",String.valueOf(String.valueOf(dayOfMonth)+"-"+nameOfMonth+"-"+String.valueOf(year)));
                Log.e("Test",String.valueOf(realmResultsTransactionMastertest));
                setSalesDescription (
                        String.valueOf(dayOfMonth)+"-"+nameOfMonth+"-"+String.valueOf(year),
                        realmResultsTransactionMaster
                );
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.show();
    }

    private String addPaddingZero(String string){
        if (string.length()<2){
            return "0"+string;
        }else {return string;}
    }

    private void setSalesDescription (String date,RealmResults<TransactionMaster> realm_result_transaction_master){
        if (!(realm_result_transaction_master.size()>0)){
            textViewReportSalesNoTransactionWarning.setVisibility(View.VISIBLE);
            textViewReportSalesNoTransactionWarning.setText("No Transaction in "+nameOfDay(date)+", "+dateDesign(date));

            linearLayoutReportSalesSelectDate.setVisibility(View.VISIBLE);
            linearLayoutHistoryDescription.setVisibility(View.GONE);
            linearLayoutHistoryDescription.setVisibility(View.GONE);
        }else {
            linearLayoutReportSalesSelectDate.setVisibility(View.GONE);
            linearLayoutHistoryDescription.setVisibility(View.VISIBLE);
            linearLayoutHistoryDescription.setVisibility(View.VISIBLE);

            textViewReportSalesDay.setText(
                    nameOfDay(realm_result_transaction_master.get(0).getTransactionMasterDate().substring(0,10)).toUpperCase()
                            + ", "
                            + dateDesign(realm_result_transaction_master.get(0).getTransactionMasterDate().substring(0,10)).toUpperCase()
            );
            textViewReportSalesGrossSalesTop.setText(countGrossSales(realm_result_transaction_master));
            textViewReportSalesTotalTransaction.setText(countTotalTransaction(realm_result_transaction_master));
            textViewReportSalesAverageSale.setText(countAverageSale(realm_result_transaction_master));
            textViewReportSalesSummaryGrossSales.setText(countNetSales(realm_result_transaction_master));
            textViewReportSalesSummaryDiscount.setText(countDiscount(realm_result_transaction_master));
            textViewReportSalesSummaryNetSales.setText(countGrossSales(realm_result_transaction_master));
            textViewReportSalesSummaryService.setText(countService(realm_result_transaction_master));
            textViewReportSalesSummaryTax.setText(countTax(realm_result_transaction_master));
            textViewReportSalesSummaryTotalCollected.setText(countTotalCollected(realm_result_transaction_master));
            textViewReportSalesSummaryCash.setText(countCash(realm_result_transaction_master));
            textViewReportSalesSummaryCard.setText(countCard(realm_result_transaction_master));
            textViewReportSalesSummaryNetTotal.setText(countNetTotal(realm_result_transaction_master));
            //textViewReportSalesSummaryHotSalesItem.setText(findHotItem(realm_result_transaction_master));
            findHotItem(realm_result_transaction_master);

        }
    }

    private void findHotItem(RealmResults<TransactionMaster> realm_result_transaction_master) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        LinkedList <String> itemId = new LinkedList<>();
        for (int i = 0; i < realm_result_transaction_master.size(); i++) {
            RealmResults<TransactionDetail> realmResultsTransactionDetail =
                    ((CashRegisterActivity)getActivity()).realm
                    .where(TransactionDetail.class)
                    .equalTo("transactionDetailID",realm_result_transaction_master.get(i).getTransactionMasterID())
                    .findAll();
            if (realmResultsTransactionDetail.size()!=0){
                for (int j = 0; j < realmResultsTransactionDetail.size(); j++) {
                    if (realmResultsTransactionDetail.get(j).getTransactionDetailItemID().contains("item")){
                        if (!hashMap.containsKey(realmResultsTransactionDetail.get(j).getTransactionDetailItemID())){
                            hashMap.put(realmResultsTransactionDetail.get(j).getTransactionDetailItemID(),
                                    integerOf(realmResultsTransactionDetail.get(j).getTransactionDetailQuantity()));
                        }else {
                            int value = hashMap.get(realmResultsTransactionDetail.get(j).getTransactionDetailItemID())
                                    +integerOf(realmResultsTransactionDetail.get(j).getTransactionDetailQuantity());
                            hashMap.put(realmResultsTransactionDetail.get(j).getTransactionDetailItemID(),
                                    value);
                        }
                    }
                }
            }
        }
        Object[] objects = hashMap.entrySet().toArray();
        Arrays.sort(objects, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Integer>) o2).getValue().compareTo(
                        ((Map.Entry<String, Integer>) o1).getValue());
            }
        });
        for (Object e : objects) {
            itemId.add(((Map.Entry<String, Integer>) e).getKey());
        }

        RealmResults<Item> realmResultsItem = ((CashRegisterActivity)getActivity()).realm
                .where(Item.class)
                .equalTo("itemID",itemId.get(0))
                .findAll();
        textViewReportSalesSummaryHotSalesItem.setText(realmResultsItem.get(0).getItemName());
        textViewReportSalesHotSalesTop.setText(realmResultsItem.get(0).getItemName());

    }

    private void createViewReportDrawer(Drawer drawer){
        textViewReportDrawerDate.setText(
                nameOfDay(drawer.getDrawerStartingDateAndTime().substring(0,10)).toUpperCase()
                + ", "
                + dateDesign(drawer.getDrawerStartingDateAndTime().substring(0,10)).toUpperCase()
        );

        textViewReportDrawerStartDrawer.setText(
                drawer.getDrawerStartingDateAndTime().substring(11,16)
        );

        textViewReportDrawerStartingCash.setText(
                formatRupiah(drawer.getDrawerStartingCash())
        );

        textViewReportDrawerCashSale.setText(
                formatRupiah(drawer.getDrawerCashSales())
        );

        textViewReportDrawerCardSale.setText(
                formatRupiah(drawer.getDrawerCardSales())
        );

        textViewReportDrawerExpectedDrawer.setText(
                formatRupiah(drawer.getDrawerExpetationCash())
        );
    }


    private String nameOfDay (String date_time){
        String dayOfTheWeek = "";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
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
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = format.parse(date_time);
            dateDesign = (String) android.text.format.DateFormat.format("dd MMMM yyyy", date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateDesign;
    }

    public String convertValuePattern (String value_before_pattern){
        return new DecimalFormat("###,###,###,###,###,###,###,###").format(Double.valueOf(value_before_pattern));
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

    private String formatRupiah (String price_before_format){
        String after;
        after = "Rp " + convertValuePattern(price_before_format);
        return after;
    }

    private String unFormatRupiah (String formated_price){
        String price;
        price = revertValuePattern(formated_price.replace("Rp ",""));
        return price;
    }

    private String countGrossSales(RealmResults<TransactionMaster> realm_result_transaction_master){
        int result = 0;
        for (int i = 0; i < realm_result_transaction_master.size(); i++) {
            result += integerOf(realm_result_transaction_master.get(i).getTransactionMasterSubTotal());
        }
        return formatRupiah(stringOf(result));
    }

    private String countTotalTransaction(RealmResults<TransactionMaster> realm_result_transaction_master){
        int result = 0;
        for (int i = 0; i < realm_result_transaction_master.size(); i++) {
            result ++;
        }
        return stringOf(result);
    }

    private String countAverageSale(RealmResults<TransactionMaster> realm_result_transaction_master){
        return formatRupiah(stringOf(integerOf(unFormatRupiah(countGrossSales(realm_result_transaction_master)))/integerOf(countTotalTransaction(realm_result_transaction_master))));
    }

    private String countDiscount(RealmResults<TransactionMaster> realm_result_transaction_master){
        int result = 0;
        for (int i = 0; i < realm_result_transaction_master.size(); i++) {
            result += integerOf(realm_result_transaction_master.get(i).getTransactionMasterDiscount());
        }
        return formatRupiah("-"+stringOf(result));
    }

    private String countNetSales(RealmResults<TransactionMaster> realm_result_transaction_master){
        return formatRupiah(stringOf(
                integerOf(unFormatRupiah(countGrossSales(realm_result_transaction_master)))
                - integerOf(unFormatRupiah(countDiscount(realm_result_transaction_master)))
        ));
    }

    private String countService(RealmResults<TransactionMaster> realm_result_transaction_master){
        int result = 0;
        for (int i = 0; i < realm_result_transaction_master.size(); i++) {
            result += integerOf(realm_result_transaction_master.get(i).getTransactionMasterService());
        }
        return formatRupiah(stringOf(result));
    }

    private String countTax(RealmResults<TransactionMaster> realm_result_transaction_master){
        int result = 0;
        for (int i = 0; i < realm_result_transaction_master.size(); i++) {
            result += integerOf(realm_result_transaction_master.get(i).getTransactionMasterTax());
        }
        return formatRupiah(stringOf(result));
    }

    private String countTotalCollected(RealmResults<TransactionMaster> realm_result_transaction_master){
        return formatRupiah(
                stringOf(
                        integerOf(unFormatRupiah(countGrossSales(realm_result_transaction_master)))
                        + integerOf(unFormatRupiah(countTax(realm_result_transaction_master)))
                        + integerOf(unFormatRupiah(countService(realm_result_transaction_master)))
                )
        );
    }

    private String countCash(RealmResults<TransactionMaster> realm_result_transaction_master){
        int result = 0;
        for (int i = 0; i < realm_result_transaction_master.size(); i++) {
            if (realm_result_transaction_master.get(i).getTransactionMasterPaymentType().equals("Cash")){
                result += integerOf(realm_result_transaction_master.get(i).getTransactionMasterTotalTransaction());
            }
        }
        return formatRupiah(stringOf(result));
    }

    private String countCard(RealmResults<TransactionMaster> realm_result_transaction_master){
        int result = 0;
        for (int i = 0; i < realm_result_transaction_master.size(); i++) {
            if (realm_result_transaction_master.get(i).getTransactionMasterPaymentType().equals("Card")){
                result += integerOf(realm_result_transaction_master.get(i).getTransactionMasterTotalTransaction());
            }
        }
        return formatRupiah(stringOf(result));
    }

    private String countNetTotal(RealmResults<TransactionMaster> realm_result_transaction_master){
        int result = 0;
        for (int i = 0; i < realm_result_transaction_master.size(); i++) {
            result += integerOf(realm_result_transaction_master.get(i).getTransactionMasterTotalTransaction());
        }
        return formatRupiah(stringOf(result));
    }

}

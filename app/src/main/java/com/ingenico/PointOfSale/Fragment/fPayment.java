package com.ingenico.PointOfSale.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ingenico.PointOfSale.Adapter.ListViewAdapterHistoryVoid;
import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailModifier;
import com.ingenico.PointOfSale.ModelRealm.TransactionMaster;
import com.ingenico.PointOfSale.R;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.view.View.GONE;

/**
 * Created by Administrator-Handy on 11/3/2016.
 */

public class fPayment extends Fragment {
    private String paymentCategory;
    private RadioGroup radioGroupPayment;
    private CardView cardViewVoid, cardViewRefund, cardViewSettlement;
    private Button btnRefund, btnVoid, btnSettlement;
    private EditText etAmountRefund;

    private SearchView searchViewHistory;
    private ListView listViewHistory;
    private LinearLayout linearLayoutHistoryEmpty, linearLayoutHistoryDescription, linearLayoutHistoryDescriptionItemList;
    private TextView textViewHistoryDescriptionDay,textViewHistoryDescriptionDate,textViewHistoryDescriptionTime,
            textViewHistoryDescriptionPricePay,textViewHistoryDiscount,textViewHistorySubTotal,textViewHistoryService,
            textViewHistoryVat,textViewHistoryTotal,textViewHistoryTendered,textViewHistoryChange,textViewHistorySync;
    private RelativeLayout relativeLayoutHistoryDescriptionDiscount, RelativeLayoutHistoryDescriptionService,
            RelativeLayoutHistoryDescriptionVat, RelativeLayoutHistoryDescriptionTendered, RelativeLayoutHistoryDescriptionChange,
            relativeLayoutHistoryDesc;
    private ImageView imageViewHistoryMail, imageViewHistoryPrint;
    private ProgressBar progressBarHistory;

    private ListViewAdapterHistoryVoid listViewAdapterHistory;
    LinkedList <HashMap<String,Object>> linkedListHistory;
    HashMap<String, Object> hashMapDataHistory;

    /**
     * declare key
     */
    static final String KEY_TRANSACTION_TERMINAL_ID = "key_transaction_terminal_id";

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


    public fPayment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_payment, container, false);

        radioGroupPayment = (RadioGroup)rootView.findViewById(R.id.radioGroupPayment);
        cardViewVoid = (CardView)rootView.findViewById(R.id.cardViewVoidTransaction);
        cardViewRefund = (CardView)rootView.findViewById(R.id.cardViewRefundTransaction);
        cardViewSettlement = (CardView)rootView.findViewById(R.id.cardViewSettlementTransaction);
        btnRefund = (Button)rootView.findViewById(R.id.btn_refund);
        btnSettlement = (Button)rootView.findViewById(R.id.btn_fpayment_settlement);
        etAmountRefund = (EditText)rootView.findViewById(R.id.et_amount_refund);

        searchViewHistory = (android.support.v7.widget.SearchView) rootView.findViewById(R.id.searchViewHistory);
        searchViewHistory.setFocusable(true);
        listViewHistory = (ListView)rootView.findViewById(R.id.listViewHistory);
        progressBarHistory = (ProgressBar) rootView.findViewById(R.id.progressBarHistory);
        linearLayoutHistoryEmpty = (LinearLayout)rootView.findViewById(R.id.linearLayoutHistoryEmpty);

        final RealmResults<TransactionMaster> realmResultsTransactionMaster = ((CashRegisterActivity)getActivity()).realm
                .where(TransactionMaster.class)
                .findAll();

        final RealmResults<TransactionDetail> realmResultsTransactionDetail = ((CashRegisterActivity)getActivity()).realm
                .where(TransactionDetail.class)
                .findAll();

        radioGroupPayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                paymentCategory = ((RadioButton)rootView.findViewById(radioGroupPayment.getCheckedRadioButtonId())).getText().toString();
                switch (paymentCategory){
                    case "Void":{
                        cardViewVoid.setVisibility(View.VISIBLE);
                        cardViewRefund.setVisibility(GONE);
                        cardViewSettlement.setVisibility(GONE);
                        break;
                    }case "Refund":{
                        cardViewVoid.setVisibility(GONE);
                        cardViewRefund.setVisibility(View.VISIBLE);
                        cardViewSettlement.setVisibility(GONE);
                        break;
                    }case "Settlement":{
                        cardViewVoid.setVisibility(GONE);
                        cardViewRefund.setVisibility(GONE);
                        cardViewSettlement.setVisibility(View.VISIBLE);
                        break;
                    }
                }

            }
        });

        btnRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ambil = etAmountRefund.getText().toString().trim();
                Log.e("PaddingZero: ", addPaddingZero(ambil));
            }
        });

        btnSettlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CashRegisterActivity cr = (CashRegisterActivity) getActivity();
                cr.runDoTransactionsettlement();
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
//                hashMapDataHistory = linkedListHistory.get(position);
//                relativeLayoutHistoryDesc.setVisibility(View.VISIBLE);
//                changeViewHistory(hashMapDataHistory);

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

        return rootView;
    }

    public String addPaddingZero(String amount){
        return String.format("%012d", new BigInteger(amount));
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

        listViewAdapterHistory = new ListViewAdapterHistoryVoid(
                getActivity(),
                linkedListHistory
        );

        listViewHistory.setAdapter(listViewAdapterHistory);
        ((CashRegisterActivity)getActivity()).hideProgressDialog();
    }

    private void searchTransactionIdFunction (String transaction_id) {
        if (transaction_id.length() > 2){
            LinkedList<HashMap<String,Object>> linkedListSearchResult = new LinkedList<>();
            progressBarHistory.setVisibility(View.VISIBLE);
//            linearLayoutHistoryDescription.setVisibility(View.INVISIBLE);
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
                listViewAdapterHistory = new ListViewAdapterHistoryVoid(
                        getActivity(),
                        linkedListSearchResult
                );
                progressBarHistory.setVisibility(View.GONE);
                listViewHistory.setVisibility(View.VISIBLE);
                linearLayoutHistoryEmpty.setVisibility(View.GONE);
                listViewHistory.setAdapter(listViewAdapterHistory);
            }
        }else {
            listViewAdapterHistory = new ListViewAdapterHistoryVoid(
                    getActivity(),
                    linkedListHistory
            );
            progressBarHistory.setVisibility(View.GONE);
            listViewHistory.setVisibility(View.VISIBLE);
            linearLayoutHistoryEmpty.setVisibility(View.GONE);

            listViewHistory.setAdapter(listViewAdapterHistory);
        }
    }

}

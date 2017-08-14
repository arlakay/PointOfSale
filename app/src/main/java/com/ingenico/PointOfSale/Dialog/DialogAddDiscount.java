package com.ingenico.PointOfSale.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.ingenico.PointOfSale.Adapter.ListViewAdapterDialogDiscount;
import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.LinearLayout.LinearLayoutDialogDiscountDiscountList;
import com.ingenico.PointOfSale.ModelRealm.DiscountMaster;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount;
import com.ingenico.PointOfSale.Object.ItemDiscountInDialogDiscount;
import com.ingenico.PointOfSale.R;

import java.util.HashMap;
import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.view.View.GONE;

/**
 * Created by Administrator-Handy on 11/4/2016.
 */

public class DialogAddDiscount extends DialogFragment {

    private String transaction_id= "";

//    public void setTransaction_id(String t_id){this.transaction_id = t_id;}

    public DialogAddDiscount setTransaction_id(String t_id){
        this.transaction_id = t_id;
        return this;
    }

    private ImageView imageViewAddDiscountCancel;
    private TextView textViewAddDiscount;
    private LinearLayout linearLayoutAddDiscountLeft,linearLayoutAddDiscountRight, linearLayoutDiscountInTransaction,
            linearLayoutListDiscount, linearLayoutAddDiscountAvailable;
    private ListView listViewDiscount;

    private LinkedList<Switch> linkedListSwitch;
    private LinkedList<HashMap<String, String>> linkedListDiscount;
    private LinkedList<String> linkedListDiscountResult;

    private String KEY_DISCOUNT_ID = "key_discount_id";
    private String KEY_DISCOUNT_NAME = "key_discount_name";
    private String KEY_DISCOUNT_VALUE = "key_discount_value";
    private String KEY_DISCOUNT_TYPE = "key_discount_type";
    private String KEY_DISCOUNT_FLAG = "key_discount_flag";

    private String KEY_QUANTITY_ITEM_GOT_DISCOUNT = "key_quantity_item_got_discount";

    private ListViewAdapterDialogDiscount listViewAdapterDialogDiscount;

    public Realm realm;
    public static RealmConfiguration realmConfig;

    private RealmChangeListener realmChangeListener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View rootView=inflater.inflate(R.layout.dialog_add_discount, null);

        imageViewAddDiscountCancel = (ImageView)rootView.findViewById(R.id.imageViewAddDiscountCancel);
        textViewAddDiscount = (TextView)rootView.findViewById(R.id.textViewAddDiscount);
        linearLayoutAddDiscountLeft = (LinearLayout)rootView.findViewById(R.id.linearLayoutAddDiscountLeft);
        linearLayoutAddDiscountRight = (LinearLayout)rootView.findViewById(R.id.linearLayoutAddDiscountRight);
        linearLayoutAddDiscountAvailable = (LinearLayout)rootView.findViewById(R.id.linearLayoutAvailableDiscount);
        linearLayoutDiscountInTransaction = (LinearLayout)rootView.findViewById(R.id.linearLayoutDiscountInTransaction);
        linearLayoutListDiscount = (LinearLayout)rootView.findViewById(R.id.linearLayoutListDiscount);
        listViewDiscount = (ListView)rootView.findViewById(R.id.listViewDiscount);

        linkedListSwitch = new LinkedList<>();

        //Create Realm configuration if it doesn't exist.
        realmConfig = new RealmConfiguration.Builder(getActivity()).build();

        //Open the default Realm for the UI thread.
        this.realm = Realm.getInstance(realmConfig);

        /**
         * this transaction query needed for validate the view of discount dialog
         */
        final RealmResults<TransactionDetail> realmResultsTransactionDetail =
                realm.where(TransactionDetail.class)
                .equalTo("transactionDetailID", transaction_id)
                .findAll();
        validateDiscountInTransaction(realmResultsTransactionDetail);

        RealmResults<TransactionDetail> realmResultsTransactionDetailFor =
                realm.where(TransactionDetail.class)
                        .equalTo("transactionDetailID", transaction_id)
                        .findAll();

        /**
         * view for discount percentage
         */
        RealmResults<DiscountMaster> realmResultsDiscountMaster = realm.where(DiscountMaster.class)
                .findAll();
        ((CashRegisterActivity) getActivity()).logger.addInfo("DiscountMaster",realmResultsDiscountMaster.toString());
        linkedListDiscount = new LinkedList<>();
        for (int d = 0; d < realmResultsDiscountMaster.size(); d++) {
            DiscountMaster discountMaster = realmResultsDiscountMaster.get(d);
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put(KEY_DISCOUNT_ID,discountMaster.getDiscountMasterID());
            hashMap.put(KEY_DISCOUNT_NAME,discountMaster.getDiscountMasterName());
            hashMap.put(KEY_DISCOUNT_VALUE,discountMaster.getDiscountMasterValue());
            hashMap.put(KEY_DISCOUNT_TYPE,discountMaster.getDiscountMasterType());
            linkedListDiscount.add(hashMap);
        }

        for (int m = 0; m < linkedListDiscount.size(); m++) {
            if (linkedListDiscount.get(m).get(KEY_DISCOUNT_TYPE).equals("Persen")){
                if (!already(realmResultsTransactionDetailFor,linkedListDiscount.get(m).get(KEY_DISCOUNT_ID))){
                    linkedListDiscount.get(m).put(KEY_DISCOUNT_FLAG,"already_got_bill_discount");
                }else {
                    linkedListDiscount.get(m).put(KEY_DISCOUNT_FLAG,"not_yet_got_bill_discount");
                }
            }else {
                for (int h = 0; h < realmResultsTransactionDetail.size(); h++) {
                    if (realmResultsTransactionDetail.get(h).getTransactionDetailItemID().equals(
                            linkedListDiscount.get(m).get(KEY_DISCOUNT_ID)
                    )){
                        linkedListDiscount.get(m).put(KEY_DISCOUNT_FLAG,"already_got_bill_discount");
                    }else {
                        linkedListDiscount.get(m).put(KEY_DISCOUNT_FLAG,"not_yet_got_bill_discount");
                    }
                }
            }
        }

        addViewSwitchDiscount(linkedListDiscount,realmResultsTransactionDetail);

        textViewAddDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDiscount();
            }
        });

        /**
         * handle cancel button
         */
        imageViewAddDiscountCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange() {
                try {
                    if (getDialog().isShowing()){
                        validateDiscountInTransaction(realmResultsTransactionDetail);
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        };

        realmResultsTransactionDetail.addChangeListener(realmChangeListener);

        setCancelable(false);
        builder.setView(rootView);
        return builder.create();
    }

    private boolean already (RealmResults<TransactionDetail> realm_result_transaction_detail,
                              String discount_id){
        LinkedList<Boolean> linkedList = new LinkedList<>();
        int totalItemInTransactionDetail = 0;
        boolean value = false;
        for (int i = 0; i < realm_result_transaction_detail.size(); i++) {
            if (realm_result_transaction_detail.get(i).getTransactionDetailItemID().contains("item")){
                totalItemInTransactionDetail+=1;
            }
            RealmList<TransactionDetailDiscount> realmListTransactionDetailDiscount =
                    realm_result_transaction_detail.get(i).getTransactionDetailDiscountID();
            if (alreadyIn(realmListTransactionDetailDiscount,discount_id)){
                linkedList.add(true);
            }
        }
        if (linkedList.size()!=0) {
            for (int j = 0; j < linkedList.size(); j++) {
                if (!linkedList.get(j)){
                    value = true;
                }
            }
        } else {value = true;}
        if (linkedList.size()!=totalItemInTransactionDetail){
            value=true;
        }
        return value;
    }

    private LinkedList<String> linkedListSingleData (LinkedList<String> linkedList, String data) {
        if (!inList(linkedList,data)){
            linkedList.add(data);
        }
        return linkedList;
    }

    private boolean inList (LinkedList<String> linkedList, String data){
        boolean value = false;
        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i).equals(data)){
                value = true;
            }
        }
        return value;
    }

    private void validateDiscountInTransaction(RealmResults<TransactionDetail> realm_result_transaction_detail) {
        LinkedList<HashMap<String,String>> linkedListDiscountInAllTransaction = new LinkedList<>();

        for (int i = 0; i < realm_result_transaction_detail.size(); i++) {
            if (realm_result_transaction_detail.get(i).getTransactionDetailItemID().contains("item")){
                RealmList<TransactionDetailDiscount> realmListTransactionDetailDiscount =
                        realm_result_transaction_detail.get(i).getTransactionDetailDiscountID();
                if(realmListTransactionDetailDiscount.size()!=0){
                    for (int y = 0; y < realmListTransactionDetailDiscount.size(); y++) {
                        if (!realmListTransactionDetailDiscount.get(y).getDiscountID().equals("")){
                            RealmResults<DiscountMaster> realmResultsDiscountMaster =
                                    ((CashRegisterActivity)getActivity()).realm
                                            .where(DiscountMaster.class)
                                            .equalTo("discountMasterID",realmListTransactionDetailDiscount.get(y).getDiscountID())
                                            .findAll();
                            if (realmResultsDiscountMaster.get(0).getDiscountMasterType().equals("Persen")){
                                if (!isIn(linkedListDiscountInAllTransaction,realmListTransactionDetailDiscount.get(y).getDiscountID())){
                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put(KEY_DISCOUNT_ID,realmListTransactionDetailDiscount.get(y).getDiscountID());
                                    hashMap.put(KEY_QUANTITY_ITEM_GOT_DISCOUNT,"1");
                                    linkedListDiscountInAllTransaction.add(hashMap);
                                }else {
                                    linkedListDiscountInAllTransaction.remove(
                                            indexLinkedList(linkedListDiscountInAllTransaction,realmListTransactionDetailDiscount.get(y).getDiscountID()))
                                            .get(KEY_QUANTITY_ITEM_GOT_DISCOUNT);
                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put(KEY_DISCOUNT_ID,realmListTransactionDetailDiscount.get(y).getDiscountID());
                                    hashMap.put(KEY_QUANTITY_ITEM_GOT_DISCOUNT,String.valueOf(i+1));
                                    linkedListDiscountInAllTransaction.add(hashMap);
                                }
                            }else {
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put(KEY_DISCOUNT_ID,realmListTransactionDetailDiscount.get(y).getDiscountID());
                                hashMap.put(KEY_QUANTITY_ITEM_GOT_DISCOUNT,"Bill Discount");
                                linkedListDiscountInAllTransaction.add(hashMap);
                            }
                        }
                    }
                }
            }else {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(KEY_DISCOUNT_ID,realm_result_transaction_detail.get(i).getTransactionDetailItemID());
                hashMap.put(KEY_QUANTITY_ITEM_GOT_DISCOUNT,"Bill Discount");
                linkedListDiscountInAllTransaction.add(hashMap);
            }
        }
        putAllDiscountEveryTransactionToListView(linkedListDiscountInAllTransaction);

    }

    private void putAllDiscountEveryTransactionToListView(LinkedList<HashMap<String,String>> linked_list_discount_in_all_transaction){
        if (linked_list_discount_in_all_transaction.size()==0){
            linearLayoutDiscountInTransaction.setVisibility(GONE);
        }else {
            linearLayoutAddDiscountAvailable.setVisibility(GONE);
            linearLayoutDiscountInTransaction.setVisibility(View.VISIBLE);
            LinkedList<ItemDiscountInDialogDiscount> linkedList = new LinkedList<>();
            for (int i = 0; i < linked_list_discount_in_all_transaction.size(); i++) {
                ItemDiscountInDialogDiscount itemDiscountInDialogDiscount = new ItemDiscountInDialogDiscount();
                RealmResults<DiscountMaster> realmResultsDiscountMaster =
                        realm.where(DiscountMaster.class)
                                .equalTo("discountMasterID",linked_list_discount_in_all_transaction.get(i).get(KEY_DISCOUNT_ID))
                                .findAll();
                itemDiscountInDialogDiscount.setDiscountID(realmResultsDiscountMaster.get(0).getDiscountMasterID());
                itemDiscountInDialogDiscount.setDiscountName(realmResultsDiscountMaster.get(0).getDiscountMasterName());
                itemDiscountInDialogDiscount.setDiscountType(realmResultsDiscountMaster.get(0).getDiscountMasterType());
                itemDiscountInDialogDiscount.setDiscountValue(realmResultsDiscountMaster.get(0).getDiscountMasterValue());
                itemDiscountInDialogDiscount.setQuantityItemGotDiscount(
                        linked_list_discount_in_all_transaction.get(i).get(KEY_QUANTITY_ITEM_GOT_DISCOUNT)
                );
                linkedList.add(itemDiscountInDialogDiscount);
            }

            LinearLayoutDialogDiscountDiscountList linearLayoutDialogDiscountDiscountList =
                    new LinearLayoutDialogDiscountDiscountList();
            linearLayoutListDiscount.removeAllViews();
            for (int h = 0; h < linkedList.size(); h++) {
                linearLayoutListDiscount.addView(linearLayoutDialogDiscountDiscountList.LinearLayoutDialogDiscountDiscountList(
                        (CashRegisterActivity)getActivity(),
                        linkedList.get(h)
                ));
            }

        }
    }

    private int indexLinkedList(LinkedList<HashMap<String,String>> linkedList, String discount_id){
        int value = -1;
        if (linkedList.size()==-1){return value;
        }else {
            for (int i = 0; i < linkedList.size(); i++) {
                if (linkedList.get(i).get(KEY_DISCOUNT_ID).equals(discount_id)){value = i;}
            }return value;
        }
    }

    private boolean isIn(LinkedList<HashMap<String,String>> linkedList, String discount_id){
        boolean value = false;
        if (linkedList.size()==0){return value;
        }else {
            for (int i = 0; i < linkedList.size(); i++) {
                if (linkedList.get(i).get(KEY_DISCOUNT_ID).equals(discount_id)){value = true;}
            }return value;
        }
    }

    private void validateDiscount() {
        linkedListDiscountResult = new LinkedList<>();
        for (int d = 0; d < linkedListSwitch.size() ; d++) {
            if (linkedListSwitch.get(d).isChecked()){
                if (!linkedListDiscountResult.contains(linkedListDiscount.get(d).get(KEY_DISCOUNT_ID)))
                    linkedListDiscountResult.add(linkedListDiscount.get(d).get(KEY_DISCOUNT_ID));
            }else {
                linkedListDiscountResult.remove(linkedListDiscount.get(d).get(KEY_DISCOUNT_ID));
            }
        }

        /**
         * configure data check discount every item in transaction
         */
        RealmResults<TransactionDetail> realmResultsTransactionDetail =
                realm.where(TransactionDetail.class)
                .equalTo("transactionDetailID", transaction_id)
                .findAll();

        validateDiscountInTransaction(realmResultsTransactionDetail);
        for (int j = 0; j < linkedListDiscountResult.size(); j++) {
            RealmResults<DiscountMaster> realmResultsDiscountMaster =
                    realm.where(DiscountMaster.class)
                            .equalTo("discountMasterID", linkedListDiscountResult.get(j))
                            .findAll();
            if (realmResultsDiscountMaster.get(0).getDiscountMasterType().equals("Persen")){
                for (int i = 0; i < realmResultsTransactionDetail.size(); i++) {
                    RealmList<TransactionDetailDiscount> realmListTransactionDetailDiscount =
                            realmResultsTransactionDetail.get(i).getTransactionDetailDiscountID();
                    if (realmListTransactionDetailDiscount.size()!=0){
                        for (int k = 0; k < realmListTransactionDetailDiscount.size(); k++) {
                            if (!alreadyIn(realmListTransactionDetailDiscount,linkedListDiscountResult.get(j))){
                                inputDiscountToDatabase(linkedListDiscountResult.get(j),realmListTransactionDetailDiscount);
                            }
                        }
                    }else {
                        inputDiscountToDatabase(linkedListDiscountResult.get(j),realmListTransactionDetailDiscount);
                    }
                }
            }else {
                if (!discountAlreadyInTransactionDetail(linkedListDiscountResult.get(j),realmResultsTransactionDetail)){
                    inputNominalDiscountToDatabase(linkedListDiscountResult.get(j),realmResultsDiscountMaster);
                }
            }
        }

        getDialog().dismiss();
    }

    private boolean discountAlreadyInTransactionDetail(String discount_id ,RealmResults<TransactionDetail> realm_result_transaction_detail){
        boolean value = false;
        for (int i = 0; i < realm_result_transaction_detail.size(); i++) {
            if (realm_result_transaction_detail.get(i).getTransactionDetailItemID().equals(discount_id)){
                value = true;
            }
        }
        return value;
    }

    private void inputNominalDiscountToDatabase(String discount_id,RealmResults<DiscountMaster> realm_result_discount_master){
        realm.beginTransaction();
        TransactionDetail transactionDetail = realm.createObject(TransactionDetail.class);
        transactionDetail.setTransactionDetailID(((CashRegisterActivity)getActivity()).sessionManager.getTansactionID());
        transactionDetail.setTransactionDetailItemID(discount_id);
        transactionDetail.setTransactionDetailVariantID(Declaration.NULL_DATA);
        transactionDetail.setTransactionDetailTotalPrice(realm_result_discount_master.get(0).getDiscountMasterValue());
        transactionDetail.setTransactionDetailQuantity("1");
        realm.commitTransaction();
    }

    private boolean alreadyIn(RealmList<TransactionDetailDiscount> realm_list_transaction_detail_discount,
                              String discount_id){
        boolean value = false;
        for (int i = 0; i < realm_list_transaction_detail_discount.size(); i++) {
            if (realm_list_transaction_detail_discount.get(i).getDiscountID().equals(discount_id)){
                value = true;
            }
        }return value;
    }

    private void inputDiscountToDatabase(String discount_id, RealmList<TransactionDetailDiscount> realmListTransactionDetailDiscount){
        TransactionDetailDiscount  transactionDetailDiscount =
                new TransactionDetailDiscount();
        realm.beginTransaction();
        transactionDetailDiscount.setDiscountID(discount_id);
        realmListTransactionDetailDiscount.add(transactionDetailDiscount);
        realm.commitTransaction();
    }

    private void addViewSwitchDiscount (LinkedList<HashMap<String, String>> linkedListDiscountMaster,
                                        RealmResults<TransactionDetail> realm_results_transaction_detail){
        LinkedList<String> linkedListDiscountInTransactionDetail = new LinkedList<>();

        for (int z = 0; z < realm_results_transaction_detail.size(); z++) {
            RealmList<TransactionDetailDiscount> realmListTransactionDetailDiscount =
                    realm_results_transaction_detail.get(z).getTransactionDetailDiscountID();
            if(realmListTransactionDetailDiscount.size()!=0){
                for (int y = 0; y < realmListTransactionDetailDiscount.size(); y++) {
                    if (!linkedListDiscountInTransactionDetail.contains(realmListTransactionDetailDiscount.get(y).getDiscountID())){
                        linkedListDiscountInTransactionDetail.add(realmListTransactionDetailDiscount.get(y).getDiscountID());
                    }
                }
            }
        }

        final int leftSwitch;
        if (linkedListDiscountMaster.size()%2==1){
            leftSwitch = (linkedListDiscountMaster.size()/2)+1;
        }else {
            leftSwitch = (linkedListDiscountMaster.size()/2);
        }
        /**
         * create a switch for discount on left
         */
        for (int c = 0; c < leftSwitch; c++) {
            Switch aSwitchLeft = new Switch(getActivity());
            LinearLayout.LayoutParams layoutParamsSwitchLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 45);
            aSwitchLeft.setLayoutParams(layoutParamsSwitchLeft);
            String aSwitchLeftText ="";
            if (linkedListDiscountMaster.get(c).get(KEY_DISCOUNT_TYPE).equals("Persen")){
                aSwitchLeftText = linkedListDiscountMaster.get(c).get(KEY_DISCOUNT_NAME)+
                        " ("+linkedListDiscountMaster.get(c).get(KEY_DISCOUNT_VALUE)+"%)";
            }else {
                aSwitchLeftText = linkedListDiscountMaster.get(c).get(KEY_DISCOUNT_NAME)+
                        " ("+((CashRegisterActivity)getActivity()).convertValuePattern(
                        linkedListDiscountMaster.get(c).get(KEY_DISCOUNT_VALUE))+")";
            }
            aSwitchLeft.setText(aSwitchLeftText);
            if (linkedListDiscountMaster.get(c).get(KEY_DISCOUNT_FLAG).equals("already_got_bill_discount")){
                aSwitchLeft.setEnabled(false);
                aSwitchLeft.setChecked(true);
            }else {
                aSwitchLeft.setEnabled(true);
                aSwitchLeft.setChecked(false);
            }
            linearLayoutAddDiscountLeft.addView(aSwitchLeft);
            linkedListSwitch.add(c,aSwitchLeft);
            aSwitchLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                }
            });

        }
        /**
         * create a switch for discount on right
         */
        for (int g = leftSwitch; g < linkedListDiscountMaster.size(); g++) {
            Switch aSwitchRight = new Switch(getActivity());
            LinearLayout.LayoutParams layoutParamsSwitchRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 45);
            aSwitchRight.setLayoutParams(layoutParamsSwitchRight);
            String aSwitchRightText = "";
            if (linkedListDiscountMaster.get(g).get(KEY_DISCOUNT_TYPE).equals("Persen")){
                aSwitchRightText = linkedListDiscountMaster.get(g).get(KEY_DISCOUNT_NAME)+
                        " ("+linkedListDiscountMaster.get(g).get(KEY_DISCOUNT_VALUE)+"%)";
            }else {
                aSwitchRightText = linkedListDiscountMaster.get(g).get(KEY_DISCOUNT_NAME)+
                        " ("+((CashRegisterActivity)getActivity()).convertValuePattern(
                        linkedListDiscountMaster.get(g).get(KEY_DISCOUNT_VALUE))+")";
            }
            aSwitchRight.setText(aSwitchRightText);
            if (linkedListDiscountMaster.get(g).get(KEY_DISCOUNT_FLAG).equals("already_got_bill_discount")){
                aSwitchRight.setEnabled(false);
                aSwitchRight.setChecked(true);
            }else {
                aSwitchRight.setEnabled(true);
                aSwitchRight.setChecked(false);
            }
            linearLayoutAddDiscountRight.addView(aSwitchRight);
            linkedListSwitch.add(g,aSwitchRight);
            aSwitchRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                }
            });
        }

    }

    public void changeViewSwitch(String discount_id){
        for (int i = 0; i < linkedListDiscount.size(); i++) {
            if (linkedListDiscount.get(i).get(KEY_DISCOUNT_ID).equals(discount_id)){
                linkedListSwitch.get(i).setEnabled(true);
                linkedListSwitch.get(i).setChecked(false);
            }
        }
    }
}

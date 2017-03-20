package com.ingenico.PointOfSale.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ingenico.PointOfSale.Adapter.GridViewAdapterSaveOrderCashRegister;
import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.ModelRealm.SaveOrder;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.R;

import java.util.HashMap;
import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 10/18/2016.
 */

public class fSaveOrder extends Fragment {
    public fSaveOrder(){}

    private TextView textViewTableNumber, textViewMergeTable;
    private ImageView imageViewCloseSaveOrder;
    private GridView gridViewSaveOrder, gridViewMergeTable;
    private LinearLayout linearLayoutMergeTable,linearLayoutGridViewMergeTable;

    static final String KEY_SAVE_ORDER_TRANSACTION_ID = "save_order_transaction_id";
    static final String KEY_SAVE_ORDER_NUMBER_TABLE = "save_order_number_table";
    static final String KEY_SAVE_ORDER_IS_BEING_SELECTED = "save_order_is_being_selected";

    private RealmChangeListener realmChangeListenerSaveOrder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_save_order, container, false);

        textViewTableNumber = (TextView)rootView.findViewById(R.id.textViewTableNumber);
        imageViewCloseSaveOrder = (ImageView)rootView.findViewById(R.id.imageViewCloseSaveOrder);
        gridViewSaveOrder = (GridView)rootView.findViewById(R.id.gridViewSaveOrder);
        linearLayoutMergeTable = (LinearLayout)rootView.findViewById(R.id.linearLayoutMergeTable);
        linearLayoutGridViewMergeTable = (LinearLayout)rootView.findViewById(R.id.linearLayoutGridViewMergeTable);
        gridViewMergeTable = (GridView)rootView.findViewById(R.id.gridViewMergeTable);
        textViewMergeTable = (TextView)rootView.findViewById(R.id.textViewMergeTable);


        /**
         * handle changing of realm in database save order
         */
        final RealmResults<SaveOrder> realmResultSaveOrder = ((CashRegisterActivity)getActivity()).realm.
                where(SaveOrder.class).findAll();
        realmChangeListenerSaveOrder = new RealmChangeListener() {
            @Override
            public void onChange() {
                try {
                    RealmResults<TransactionDetail> realmResultTransactionDetail = ((CashRegisterActivity)getActivity()).realm
                            .where(TransactionDetail.class)
                            .equalTo("transactionDetailID",((CashRegisterActivity)getActivity()).sessionManager.getTansactionID())
                            .findAll();
                    ((CashRegisterActivity)getActivity()).generateViewCart(
                            ((CashRegisterActivity)getActivity()).sessionManager.getTansactionID(),
                            realmResultTransactionDetail,
                            ((CashRegisterActivity)getActivity()).linearLayoutCartListViewForSaveOrder(),
                            ((CashRegisterActivity)getActivity()).linearLayoutCartEmptyForSaveOrder(),
                            ((CashRegisterActivity)getActivity()).textViewCartTransactionIDForSaveOrder(),
                            ((CashRegisterActivity)getActivity()).listViewCartInRegisterForSaveOrder(),
                            ((CashRegisterActivity)getActivity()).linearLayoutCartBottomForSaveOrder(),
                            ((CashRegisterActivity)getActivity()).textViewDiscountForSaveOrder(),
                            ((CashRegisterActivity)getActivity()).textViewSubTotalForSaveOrder(),
                            ((CashRegisterActivity)getActivity()).textViewServiceForSaveOrder(),
                            ((CashRegisterActivity)getActivity()).textViewCartVatTotalForSaveOrder(),
                            ((CashRegisterActivity)getActivity()).buttonNextForSaveOrder()
                    );
                    generateGridViewSaveOrder(realmResultSaveOrder);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        };
        realmResultSaveOrder.addChangeListener(realmChangeListenerSaveOrder);
        generateGridViewSaveOrder(realmResultSaveOrder);
        validateMergeButton(realmResultSaveOrder);

        /**
         * handle grid view save order click event
         */
        gridViewSaveOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                RealmResults<TransactionDetail> realmResultsTransactionDetail = ((CashRegisterActivity)getActivity()).realm
                        .where(TransactionDetail.class)
                        .equalTo("transactionDetailID",((CashRegisterActivity)getActivity()).sessionManager.getTansactionID())
                        .findAll();
                RealmResults<SaveOrder> realmResultSavedOrder = ((CashRegisterActivity)getActivity()).realm.
                        where(SaveOrder.class).findAll();
                if (textViewTableNumber.getText().toString().equals(getResources().getString(R.string.table_number))){
                    if (isFillWithOrder(realmResultSavedOrder,position)){
                        ((CashRegisterActivity)getActivity()).sessionManager.
                                setTransactionId(realmResultSavedOrder.get(position).getSaveOrderTransactionID());
                        if (filledTable(realmResultSavedOrder)>=2){
                            linearLayoutMergeTable.setVisibility(View.VISIBLE);
                        }

//                    textViewTestingTransactionID.setText(
//                            ((CashRegisterActivity)getActivity()).sessionManager.getTansactionID()
//                    );

                        RealmResults<TransactionDetail> realmResultTransactionDetail = ((CashRegisterActivity)getActivity()).realm
                                .where(TransactionDetail.class)
                                .equalTo("transactionDetailID",((CashRegisterActivity)getActivity()).sessionManager.getTansactionID())
                                .findAll();

                        setSelectedTableHover(
                                ((CashRegisterActivity)getActivity()).realm,
                                realmResultSavedOrder,
                                position);

                        ((CashRegisterActivity)getActivity()).generateViewCart(
                                ((CashRegisterActivity)getActivity()).sessionManager.getTansactionID(),
                                realmResultTransactionDetail,
                                ((CashRegisterActivity)getActivity()).linearLayoutCartListViewForSaveOrder(),
                                ((CashRegisterActivity)getActivity()).linearLayoutCartEmptyForSaveOrder(),
                                ((CashRegisterActivity)getActivity()).textViewCartTransactionIDForSaveOrder(),
                                ((CashRegisterActivity)getActivity()).listViewCartInRegisterForSaveOrder(),
                                ((CashRegisterActivity)getActivity()).linearLayoutCartBottomForSaveOrder(),
                                ((CashRegisterActivity)getActivity()).textViewDiscountForSaveOrder(),
                                ((CashRegisterActivity)getActivity()).textViewSubTotalForSaveOrder(),
                                ((CashRegisterActivity)getActivity()).textViewServiceForSaveOrder(),
                                ((CashRegisterActivity)getActivity()).textViewCartVatTotalForSaveOrder(),
                                ((CashRegisterActivity)getActivity()).buttonNextForSaveOrder()
                        );
                        //showAlertDialogNullEvent(CashRegisterActivity.this,"Warning","Are you sure? Your activity can erase previous order.");
                    }else {
                        if (orderAlreadySaved(((CashRegisterActivity)getActivity()).sessionManager.getTansactionID(),realmResultSaveOrder)){
                            Toast.makeText(getActivity(), "You already save this order.", Toast.LENGTH_SHORT).show();
                        }else {
                            if (realmResultsTransactionDetail.size()!=0){
                                updateSaveOrderToDatabase(
                                        ((CashRegisterActivity)getActivity()).realm,
                                        ((CashRegisterActivity)getActivity()).stringOf(position+1),
                                        ((CashRegisterActivity)getActivity()).sessionManager.getTansactionID());
                                ((CashRegisterActivity)getActivity()).generateNewTransactionID();
                                ((CashRegisterActivity)getActivity()).clearCart();
                                ((CashRegisterActivity)getActivity()).changeViewSaveOrder();
                            }else {
                                Toast.makeText(getActivity(), "No Order in this Transaction", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }else if (textViewTableNumber.getText().toString().equals(getResources().getString(R.string.merge_with))){
                    mergeTable(
                            ((CashRegisterActivity)getActivity()).realm,
                            ((CashRegisterActivity)getActivity()).sessionManager.getTansactionID(),
                            position
                    );
                    changeViewMergeTable();
                }

            }
        });

        imageViewCloseSaveOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CashRegisterActivity)getActivity()).closeSaveOrderView();
            }
        });

        linearLayoutMergeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeViewMergeTable();
            }
        });

        return rootView;
    }

    private void mergeTable(Realm realm, String transaction_id, int position){
        RealmResults<SaveOrder> realmResultSavedOrder = ((CashRegisterActivity)getActivity()).realm.
                where(SaveOrder.class).findAll();
        RealmResults<TransactionDetail> realmResultTransactionDetail = ((CashRegisterActivity)getActivity()).realm
                .where(TransactionDetail.class)
                .equalTo("transactionDetailID",realmResultSavedOrder.get(position).getSaveOrderTransactionID())
                .findAll();
        if (!realmResultSavedOrder.get(position).getSaveOrderTransactionID().equals(transaction_id)){
            realm.beginTransaction();
            for (int i = 0; i < realmResultTransactionDetail.size(); i++) {
                realmResultTransactionDetail.get(i).setTransactionDetailID(transaction_id);
            }
            realm.commitTransaction();

            updateSaveOrderToDatabase(realm,String.valueOf(position+1),Declaration.NOT_FILLED);
        }else {
            changeViewMergeTable();
        }

        RealmResults<SaveOrder> realmResultSaveOrder = ((CashRegisterActivity)getActivity()).realm.
                where(SaveOrder.class).findAll();
        validateMergeButton(realmResultSaveOrder);
    }

    private void changeViewMergeTable (){
        if (!textViewMergeTable.getText().toString().equals(getResources().getString(R.string.cancel))){
            ((CashRegisterActivity)getActivity()).disableFromMergeTable();
            imageViewCloseSaveOrder.setEnabled(false);
            textViewMergeTable.setText(getResources().getString(R.string.cancel));
            textViewTableNumber.setText(getResources().getString(R.string.merge_with));
        }else {
            ((CashRegisterActivity)getActivity()).enableFromMergeTable();
            textViewMergeTable.setText(getResources().getString(R.string.merge_table));
            imageViewCloseSaveOrder.setEnabled(true);
            textViewTableNumber.setText(getResources().getString(R.string.table_number));
        }
    }

    private void validateMergeButton(RealmResults<SaveOrder> realmResultSaveOrder){
        if (((CashRegisterActivity)getActivity()).cartVisible()
                && filledTable(realmResultSaveOrder)>=2){
            linearLayoutMergeTable.setVisibility(View.VISIBLE);
        }else {
            linearLayoutMergeTable.setVisibility(View.GONE);
        }
    }

    private int filledTable(RealmResults<SaveOrder> realm_result_save_order){
        int value = 0;
        for (int i = 0; i < realm_result_save_order.size(); i++) {
            if (!realm_result_save_order.get(i).getSaveOrderTransactionID().equals(Declaration.NOT_FILLED)){
                value+=1;
            }
        }
        return value;
    }

    private void generateGridViewSaveOrder (RealmResults<SaveOrder> realmResultSaveOrder){
        if (getActivity() != null) {
            GridViewAdapterSaveOrderCashRegister gridViewAdapterSaveOrderCashRegister = new GridViewAdapterSaveOrderCashRegister(
                getActivity(),
                linkedListSaveOrder(realmResultSaveOrder)
            );

            if (showLoadOrderButton(realmResultSaveOrder)) {
                ((CashRegisterActivity)getActivity()).imageViewLoadOrderVisible();
            }else {
                ((CashRegisterActivity)getActivity()).imageViewLoadOrderInvisible();
            }

            gridViewSaveOrder.setAdapter(gridViewAdapterSaveOrderCashRegister);
            gridViewAdapterSaveOrderCashRegister.notifyDataSetChanged();
        }
    }

    private LinkedList<HashMap<String,String>> linkedListSaveOrder (RealmResults<SaveOrder> realmResultSaveOrder){
        LinkedList<HashMap<String, String>> linkedListData = new LinkedList<>();
        for (int i = 0; i < realmResultSaveOrder.size(); i++) {
            SaveOrder saveOrder = realmResultSaveOrder.get(i);
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put(KEY_SAVE_ORDER_TRANSACTION_ID,saveOrder.getSaveOrderTransactionID());
            hashMap.put(KEY_SAVE_ORDER_NUMBER_TABLE,saveOrder.getSaveOrderNumberTable());
            hashMap.put(KEY_SAVE_ORDER_IS_BEING_SELECTED,saveOrder.getSaveOrderIsBeingSelected());
            linkedListData.add(hashMap);
        }
        return linkedListData;
    }

    private boolean showLoadOrderButton (RealmResults<SaveOrder> realm_result){
        boolean value = false;
        for (int i = 0; i < realm_result.size(); i++) {
            SaveOrder saveOrder = realm_result.get(i);
            if (!saveOrder.getSaveOrderTransactionID().equals(Declaration.NOT_FILLED)){
                value = true;
            }
        }
        return value;
    }

    private boolean isFillWithOrder (RealmResults<SaveOrder> realm_result_save_order, int number_table){
        boolean value = false;
        if (!realm_result_save_order.get(number_table).getSaveOrderTransactionID().equals(Declaration.NOT_FILLED)){
            value = true;
        }
        return value;
    }

    private boolean orderAlreadySaved(String transaction_id, RealmResults<SaveOrder> realm_result_save_order) {
        boolean value = false;
        for (int i = 0; i < realm_result_save_order.size(); i++) {
            if (realm_result_save_order.get(i).getSaveOrderTransactionID().equals(transaction_id)){
                value = true;
            }
        }
        return value;
    }

    private void updateSaveOrderToDatabase (Realm realm, String number_table, String transaction_id){
        RealmResults<SaveOrder> realmResultSaveDetail = realm.where(SaveOrder.class)
                .equalTo("saveOrderNumberTable",number_table)
                .findAll();
        realm.beginTransaction();
        realmResultSaveDetail.get(0).setSaveOrderTransactionID(transaction_id);
        realm.commitTransaction();
    }

    private void setSelectedTableHover(Realm realm,
                                       RealmResults<SaveOrder> realm_results_save_order,
                                       int position){
        ((CashRegisterActivity)getActivity()).unSelectAllDataInTable(realm);
        realm.beginTransaction();
        realm_results_save_order.get(position).setSaveOrderIsBeingSelected(Declaration.SELECTED);
        realm.commitTransaction();
    }


}
package com.ingenico.PointOfSale.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount;
import com.ingenico.PointOfSale.Object.ItemDiscountInDialogDiscount;
import com.ingenico.PointOfSale.R;

import java.util.LinkedList;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 11/7/2016.
 */

public class ListViewAdapterDialogDiscount extends BaseAdapter {
    private Context context;
    private LinkedList<ItemDiscountInDialogDiscount> linkedListData;
    private static LayoutInflater layoutInflater;

    public ListViewAdapterDialogDiscount(Context mContext, LinkedList<ItemDiscountInDialogDiscount> mLinkedList){
        context = mContext;
        linkedListData = mLinkedList;
        layoutInflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return linkedListData.size();
    }

    @Override
    public Object getItem(int position) {
        return linkedListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class Holder {
        TextView textViewDiscountName,textViewQuantityTransactionGotTheDiscount;
        ImageView imageViewDeleteDiscount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        if (convertView == null) {
            rowView = layoutInflater.inflate(R.layout.item_listview_discount_in_dialog_discount, null);
            holder.textViewDiscountName = (TextView)rowView.findViewById(R.id.textViewDiscountName);
            holder.textViewQuantityTransactionGotTheDiscount = (TextView)rowView.findViewById(R.id.textViewQuantityTransactionGotTheDiscount);
            holder.imageViewDeleteDiscount = (ImageView) rowView.findViewById(R.id.imageViewDeleteDiscount);

            final ItemDiscountInDialogDiscount itemDiscount = linkedListData.get(position);
            if (itemDiscount.getDiscountType().equals("Persen")){
                holder.textViewDiscountName.setText(
                        itemDiscount.getDiscountName()
                                +" ("+itemDiscount.getDiscountValue()+"%)"
                );
            }else {
                holder.textViewDiscountName.setText(
                        itemDiscount.getDiscountName()
                                + " Rp "+ ((CashRegisterActivity)context).convertValuePattern(itemDiscount.getDiscountValue())
                );
            }

            holder.textViewQuantityTransactionGotTheDiscount.setText(
                    " ("+itemDiscount.getQuantityItemGotDiscount()+"item)"
            );

            holder.imageViewDeleteDiscount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteDiscount(itemDiscount.getDiscountID());
                }
            });

        }else {
            rowView = convertView;
        }

        return rowView;
    }

    private void deleteDiscount(String discount_id){
        RealmResults<TransactionDetail> realmResultsTransactionDetail = ((CashRegisterActivity)context).realm
                .where(TransactionDetail.class)
                .equalTo("transactionDetailID",((CashRegisterActivity)context).sessionManager.getTansactionID())
                .findAll();
        for (int i = 0; i < realmResultsTransactionDetail.size(); i++) {
            RealmList<TransactionDetailDiscount> realmList = realmResultsTransactionDetail.get(i).getTransactionDetailDiscountID();
            for (int j = 0; j < realmList.size(); j++) {
                if (realmList.get(j).getDiscountID().equals(discount_id)){
                    ((CashRegisterActivity)context).realm.beginTransaction();
                    realmList.get(j).deleteFromRealm();
                    ((CashRegisterActivity)context).realm.commitTransaction();
                }
            }
        }
    }
}

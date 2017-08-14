package com.ingenico.PointOfSale.LinearLayout;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.ModelRealm.DiscountMaster;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount;
import com.ingenico.PointOfSale.Object.ItemDiscountInDialogDiscount;
import com.ingenico.PointOfSale.R;

import java.util.LinkedList;

import io.realm.RealmList;
import io.realm.RealmResults;

import static android.R.color.black;
import static android.R.drawable.ic_delete;

/**
 * Created by Administrator-Handy on 11/8/2016.
 */

public class LinearLayoutDialogDiscountDiscountList {
    private Context context;
    private ItemDiscountInDialogDiscount itemDiscount;


    public LinearLayout LinearLayoutDialogDiscountDiscountList(Context mContext, ItemDiscountInDialogDiscount mItemDiscountInDialogDiscount){
        context = mContext;
        itemDiscount = mItemDiscountInDialogDiscount;

        /**
         * parent
         */
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParamsLinearLayout = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParamsLinearLayout);

        /**
         * child
         */
        final TextView textViewDiscountName = new TextView(context);
        LinearLayout.LayoutParams paramsTextViewDiscountName = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, dp_to_pixel(35f));
        paramsTextViewDiscountName.gravity = Gravity.CENTER;
        textViewDiscountName.setLayoutParams(paramsTextViewDiscountName);
        textViewDiscountName.setTextColor(context.getResources().getColor(R.color.black));
        textViewDiscountName.setTextSize(15);


        TextView textViewQuantityTransactionGotTheDiscount = new TextView(context);
        LinearLayout.LayoutParams paramsTextViewQuantityTransactionGotTheDiscount = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, dp_to_pixel(35f));
        paramsTextViewQuantityTransactionGotTheDiscount.gravity = Gravity.CENTER;
        textViewQuantityTransactionGotTheDiscount.setLayoutParams(paramsTextViewQuantityTransactionGotTheDiscount);
        textViewQuantityTransactionGotTheDiscount.setTextColor(context.getResources().getColor(R.color.black));
        textViewQuantityTransactionGotTheDiscount.setTextSize(15);


        LinearLayout linearLayoutCross = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParamsLinearLayoutCross = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutCross.setLayoutParams(layoutParamsLinearLayoutCross);
        linearLayoutCross.setWeightSum((float)1.0);

        ImageView imageViewDeleteDiscount = new ImageView(context);
        LinearLayout.LayoutParams layoutParamsImageViewDeleteDiscount = new LinearLayout.LayoutParams(
                dp_to_pixel(35f),dp_to_pixel(35f));
        layoutParamsImageViewDeleteDiscount.setMarginEnd(dp_to_pixel(5f));
        imageViewDeleteDiscount.setImageDrawable(context.getResources().getDrawable(ic_delete));

        linearLayoutCross.addView(imageViewDeleteDiscount);

        linearLayout.addView(textViewDiscountName);
        linearLayout.addView(textViewQuantityTransactionGotTheDiscount);
        linearLayout.addView(linearLayoutCross);

        if (itemDiscount.getDiscountType().equals("Persen")){
            textViewDiscountName.setText(
                    itemDiscount.getDiscountName()
                            +" ("+itemDiscount.getDiscountValue()+"%)"
            );
            textViewQuantityTransactionGotTheDiscount.setText(
                    " ("+itemDiscount.getQuantityItemGotDiscount()+" item)"
            );
        }else {
            textViewDiscountName.setText(
                    itemDiscount.getDiscountName()
                            + " (Rp "+ ((CashRegisterActivity)context).convertValuePattern(itemDiscount.getDiscountValue())+")"
            );
            textViewQuantityTransactionGotTheDiscount.setText(
                    " ("+itemDiscount.getQuantityItemGotDiscount()+")"
            );
        }

        imageViewDeleteDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDiscount(textViewDiscountName.getText().toString());
            }
        });

        return linearLayout;
    }

    private void deleteDiscount(String discount_name_extended){
        RealmResults<DiscountMaster> realmResultsDiscountMasterAll = ((CashRegisterActivity)context).realm
                .where(DiscountMaster.class)
                .findAll();
        String discount_id = "";
        for (int j = 0; j < realmResultsDiscountMasterAll.size(); j++) {
            if (discount_name_extended.contains(realmResultsDiscountMasterAll.get(j).getDiscountMasterName())){
                discount_id = realmResultsDiscountMasterAll.get(j).getDiscountMasterID();
            }
        }
        if (!discount_id.equals("")){
            RealmResults<TransactionDetail> realmResultsTransactionDetail = ((CashRegisterActivity)context).realm
                    .where(TransactionDetail.class)
                    .equalTo("transactionDetailID",((CashRegisterActivity)context).sessionManager.getTansactionID())
                    .findAll();
            RealmResults<DiscountMaster> realmResultsDiscountMaster = ((CashRegisterActivity)context).realm
                    .where(DiscountMaster.class)
                    .equalTo("discountMasterID",discount_id)
                    .findAll();
            if (realmResultsDiscountMaster.get(0).getDiscountMasterType().equals("Persen")){
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
            }else {
                ((CashRegisterActivity)context).deleteItemFromCart(discount_id, Declaration.NULL_DATA);
            }

            ((CashRegisterActivity)context).dialogAddDiscount.changeViewSwitch(discount_id);
        }

//        for (int i = 0; i < realmResultsTransactionDetail.size(); i++) {
//            if (realmResultsTransactionDetail.get(i).getTransactionDetailItemID().contains("item")){
//                RealmList<TransactionDetailDiscount> realmList = realmResultsTransactionDetail.get(i).getTransactionDetailDiscountID();
//                for (int j = 0; j < realmList.size(); j++) {
//                    if (realmList.get(j).getDiscountID().equals(discount_id)){
//                        ((CashRegisterActivity)context).realm.beginTransaction();
//                        realmList.get(j).deleteFromRealm();
//                        ((CashRegisterActivity)context).realm.commitTransaction();
//                    }
//                }
//            }else {
//                ((CashRegisterActivity)context).deleteItemFromCart(discount_id, Declaration.NULL_DATA);
//            }
//        }
    }


    private int dp_to_pixel(float dp){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float fpixels = metrics.density * dp;
        return (int) (fpixels + 0.5f);
    }
}

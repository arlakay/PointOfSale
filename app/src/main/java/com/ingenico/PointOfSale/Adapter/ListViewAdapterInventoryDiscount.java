package com.ingenico.PointOfSale.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Object.Discount;
import com.ingenico.PointOfSale.Object.Item;
import com.ingenico.PointOfSale.R;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Administrator-Handy on 1/4/2017.
 */

public class ListViewAdapterInventoryDiscount extends BaseAdapter {
    private Context context;
    private LinkedList<Discount> linkedListData;
    private static LayoutInflater layoutInflater;

    public ListViewAdapterInventoryDiscount(Context mContext, LinkedList<Discount> linkedList_data){
        context = mContext;
        linkedListData = linkedList_data;
        layoutInflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private class Holder {
        TextView textViewName;
        TextView textViewInformation;
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
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (linkedListData.size()==0){
            return 1;
        }else {
            return linkedListData.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;

        if (convertView == null) {
            rowView = layoutInflater.inflate(R.layout.item_list_view_inventory_discount, null);
            holder.textViewName = (TextView) rowView.findViewById(R.id.textViewName);
            holder.textViewInformation = (TextView) rowView.findViewById(R.id.textViewInformation);

            Discount discount = linkedListData.get(position);

            holder.textViewName.setText(discount.getDiscountMasterName());

            if (discount.getDiscountMasterType().equals("Persen")){
                holder.textViewInformation.setText(discount.getDiscountMasterValue()+" %");
            }else {
                holder.textViewInformation.setText("Rp "+
                        ((CashRegisterActivity)context).convertValuePattern(
                                discount.getDiscountMasterValue()
                        )
                );
            }


        }else {
            rowView = convertView;
        }

        return rowView;
    }

}

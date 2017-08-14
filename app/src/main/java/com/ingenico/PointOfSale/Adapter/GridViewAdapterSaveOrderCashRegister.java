package com.ingenico.PointOfSale.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.R;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Administrator-Handy on 6/23/2016.
 */
public class GridViewAdapterSaveOrderCashRegister extends BaseAdapter{
    private Context context;
    private LinkedList<HashMap<String,String>> linkedListData;
    private LayoutInflater layoutInflater;

    /**
     * declare for hash map in adapter
     */
    private static final String KEY_SAVE_ORDER_TRANSACTION_ID = "save_order_transaction_id";
    private static final String KEY_SAVE_ORDER_NUMBER_TABLE = "save_order_number_table";
    private static final String KEY_SAVE_ORDER_IS_BEING_SELECTED = "save_order_is_being_selected";

    public GridViewAdapterSaveOrderCashRegister (Context mContext, LinkedList<HashMap<String,String>> mLinkedList){
        context = mContext;
        linkedListData = mLinkedList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private class Holder {
        TextView textViewNumber;
    }

    @Override
    public int getCount() {
        return linkedListData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View view;
        if (convertView == null){
            view = layoutInflater.inflate(R.layout.item_gridview_save_order,null);
            holder.textViewNumber = (TextView) view.findViewById(R.id.textViewNumber);
            HashMap<String,String> hashMapData = linkedListData.get(position);

            holder.textViewNumber.setText(hashMapData.get(KEY_SAVE_ORDER_NUMBER_TABLE));

            if (hashMapData.get(KEY_SAVE_ORDER_IS_BEING_SELECTED).equals(Declaration.SELECTED)){
                holder.textViewNumber.setBackground(context.getResources().getDrawable(R.drawable.bg_rounded_selected_save_order_hover));
            }

            if (!hashMapData.get(KEY_SAVE_ORDER_TRANSACTION_ID).equals(Declaration.NOT_FILLED)) {
                holder.textViewNumber.setBackground(context.getResources().getDrawable(R.drawable.button_save_order_selected));
                holder.textViewNumber.setTextColor(context.getResources().getColor(R.color.white));
            }else {
                holder.textViewNumber.setBackground(context.getResources().getDrawable(R.drawable.button_save_order));
                holder.textViewNumber.setTextColor(context.getResources().getColor(R.color.grey));
            }

        }else {view = convertView;}


        return view;
    }

}

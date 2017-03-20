package com.ingenico.PointOfSale.Adapter;

import android.content.Context;
import android.hardware.camera2.CameraConstrainedHighSpeedCaptureSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.R;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Administrator-Handy on 5/9/2016.
 */
public class ListViewAdapterItemInCartCashRegister extends BaseAdapter {
    private Context context;
    private LinkedList<HashMap<String, String>> arrayListData;
    private static LayoutInflater layoutInflater;
    /**
     * declare for hash map in adapter
     */
    private static final String KEY_TRANSACTION_ID = "transaction_id";
    private static final String KEY_ITEM_ID = "item_id";
    private static final String KEY_ITEM_NAME = "item_name";
    private static final String KEY_ITEM_PRICE = "item_price";
    private static final String KEY_ITEM_CATEGORY = "item_category";
    private static final String KEY_ITEM_PICTURE_URL = "item_picture_url";
    private static final String KEY_ITEM_TOTAL = "item_total";
    private static final String KEY_ITEM_MODIFIER_NAME = "item_modifier_name";


    private static final String KEY_VARIANT_ID = "variant_id";
    private static final String KEY_VARIANT_NAME = "variant_name";

    private static final String KEY_MODIFIER_ID = "modifier_id";
    private static final String KEY_MODIFIER_NAME = "modifier_name";

    public ListViewAdapterItemInCartCashRegister(Context mContext, LinkedList<HashMap<String, String>> arrayList_data_master) {
        context = mContext;
        arrayListData = arrayList_data_master;
        layoutInflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private class Holder {
        TextView textViewCartItemName;
        TextView textViewCartItemPrice;
        TextView textViewCartItemCross;
        TextView textViewCartItemCount;
        ImageView imageViewCartDeleteOrderItem;
        LinearLayout linearLayoutListView;
    }

    @Override
    public int getCount() {
        return arrayListData.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;

        if (convertView == null) {
            rowView = layoutInflater.inflate(R.layout.item_listview_cash_register_order_list, null);
            holder.textViewCartItemName = (TextView)rowView.findViewById(R.id.textViewCartItemName);
            holder.textViewCartItemPrice = (TextView)rowView.findViewById(R.id.textViewCartItemPrice);
            holder.textViewCartItemCross = (TextView)rowView.findViewById(R.id.textViewCartItemCross);
            holder.textViewCartItemCount = (TextView)rowView.findViewById(R.id.textViewCartItemCount);
            holder.imageViewCartDeleteOrderItem = (ImageView)rowView.findViewById(R.id.imageViewCartDeleteOrderItem);
            holder.linearLayoutListView = (LinearLayout)rowView.findViewById(R.id.linearLayoutListView);

            final HashMap<String, String> hashMapData;
            hashMapData = arrayListData.get(position);

            holder.textViewCartItemName.setText(hashMapData.get(KEY_ITEM_NAME));
            holder.textViewCartItemPrice.setText(convertValuePattern(hashMapData.get(KEY_ITEM_PRICE)));
            holder.textViewCartItemCount.setText(hashMapData.get(KEY_ITEM_TOTAL));


            if (Integer.parseInt(hashMapData.get(KEY_ITEM_TOTAL)) > 1){
                holder.textViewCartItemCross.setVisibility(View.VISIBLE);
                holder.textViewCartItemCount.setVisibility(View.VISIBLE);
            }

            if (!hashMapData.get(KEY_VARIANT_NAME).equals(Declaration.NULL_DATA)){
                holder.linearLayoutListView.addView(textViewInformationItem(hashMapData.get(KEY_VARIANT_NAME)));
            }

            if (hashMapData.get(KEY_ITEM_MODIFIER_NAME).length()!= 0){
                for (String modifier_name : hashMapData.get(KEY_ITEM_MODIFIER_NAME).split(Declaration.DELIMITER)){
                    holder.linearLayoutListView.addView(textViewInformationItem(modifier_name));
                }
            }

            holder.imageViewCartDeleteOrderItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CashRegisterActivity)context).deleteItemFromCart(
                            hashMapData.get(KEY_ITEM_ID),
                            hashMapData.get(KEY_VARIANT_ID)
                    );
                    Toast.makeText(context, "Delete was pressed", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            rowView = convertView;
        }

        return rowView;
    }

    public String convertValuePattern (String value_before_pattern){
        return new DecimalFormat("###,###,###,###").format(Double.valueOf(value_before_pattern));
    }

    private TextView textViewInformationItem (String text){
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20,0,20,0);
        textView.setLayoutParams(layoutParams);
        textView.setText(text);
        return textView;
    }
}

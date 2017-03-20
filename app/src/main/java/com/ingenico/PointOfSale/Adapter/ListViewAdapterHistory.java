package com.ingenico.PointOfSale.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ingenico.PointOfSale.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Administrator-Handy on 7/12/2016.
 */
public class ListViewAdapterHistory extends BaseAdapter {
    private Context context;
    private LinkedList<HashMap<String, Object>> linkedListData;
    private static LayoutInflater layoutInflater;

    /**
     * declare key
     */
    private static final String KEY_TRANSACTION_MASTER_ID = "key_transaction_master_id";
    private static final String KEY_TRANSACTION_MASTER_CASHIER_NAME = "key_transaction_master_cashier_name";
    private static final String KEY_TRANSACTION_MASTER_TOTAL_TRANSACTION = "key_transaction_master_total_transaction";
    private static final String KEY_TRANSACTION_MASTER_TOTAL_QUANTITY = "key_transaction_master_total_quantity";
    private static final String KEY_TRANSACTION_MASTER_TAX = "key_transaction_master_tax";
    private static final String KEY_TRANSACTION_MASTER_SERVICE = "key_transaction_master_service";
    private static final String KEY_TRANSACTION_MASTER_DISCOUNT = "key_transaction_master_discount";
    private static final String KEY_TRANSACTION_MASTER_SUB_TOTAL = "key_transaction_master_sub_total";
    private static final String KEY_TRANSACTION_MASTER_DATE_AND_TIME = "key_transaction_master_date_and_time";
    private static final String KEY_TRANSACTION_MASTER_PAYMENT_TYPE = "key_transaction_master_payment_type";
    private static final String KEY_TRANSACTION_MASTER_TENDERED = "key_transaction_master_tendered";
    private static final String KEY_TRANSACTION_MASTER_CHANGE = "key_transaction_master_change";

    private static final String KEY_TRANSACTION_DETAIL = "key_transaction_detail";
    private static final String KEY_TRANSACTION_DETAIL_ID = "key_transaction_detail_id";
    private static final String KEY_TRANSACTION_DETAIL_ITEM_ID = "key_transaction_detail_item_id";
    private static final String KEY_TRANSACTION_DETAIL_VARIANT_ID = "key_transaction_detail_variant_id";
    private static final String KEY_TRANSACTION_DETAIL_MODIFIER_ID = "key_transaction_detail_modifier_id";
    private static final String KEY_TRANSACTION_DETAIL_TOTAL_PRICE = "key_transaction_detail_total_price";
    private static final String KEY_TRANSACTION_DETAIL_DISCOUNT_ID = "key_transaction_detail_discount_id";
    private static final String KEY_TRANSACTION_DETAIL_QUANTITY = "key_transaction_detail_quantity";

    private class Holder {
        View lineItemHistory;
        TextView textViewItemHistoryDate;
        TextView textViewItemHistoryAmount;
        TextView textViewItemHistoryTransactionID;
        TextView textViewItemHistoryTime;
        TextView textViewItemHistoryTypePayment;
    }

    public ListViewAdapterHistory(Context mContext, LinkedList<HashMap<String, Object>> linkedList_data_master) {
        context = mContext;
        linkedListData = linkedList_data_master;
        layoutInflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public int getViewTypeCount() {
        return getCount();
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
            rowView = layoutInflater.inflate(R.layout.item_listview_history, null);
            holder.lineItemHistory = (View) rowView.findViewById(R.id.lineItemHistory);
            holder.textViewItemHistoryDate = (TextView) rowView.findViewById(R.id.textViewItemHistoryDate);
            holder.textViewItemHistoryAmount = (TextView) rowView.findViewById(R.id.textViewItemHistoryAmount);
            holder.textViewItemHistoryTransactionID = (TextView) rowView.findViewById(R.id.textViewItemHistoryTransactionID);
            holder.textViewItemHistoryTime = (TextView) rowView.findViewById(R.id.textViewItemHistoryTime);
            holder.textViewItemHistoryTypePayment = (TextView) rowView.findViewById(R.id.textViewItemHistoryTypePayment);

            HashMap<String, Object> hashMapData = linkedListData.get(position);
            holder.textViewItemHistoryDate.setText(dateInDesign(((String) hashMapData.get(KEY_TRANSACTION_MASTER_DATE_AND_TIME)).substring(0,10)).toUpperCase());
            holder.textViewItemHistoryAmount.setText(convertValuePattern((String) hashMapData.get(KEY_TRANSACTION_MASTER_TOTAL_TRANSACTION)));
            holder.textViewItemHistoryTransactionID.setText((String) hashMapData.get(KEY_TRANSACTION_MASTER_ID));
            holder.textViewItemHistoryTime.setText(((String) hashMapData.get(KEY_TRANSACTION_MASTER_DATE_AND_TIME)).substring(11,16));
            holder.textViewItemHistoryTypePayment.setText((String) hashMapData.get(KEY_TRANSACTION_MASTER_PAYMENT_TYPE));


        }else {
            rowView = convertView;
        }

        return rowView;
    }

    public String convertValuePattern (String value_before_pattern){
        return new DecimalFormat("###,###,###,###").format(Double.valueOf(value_before_pattern));
    }

    private String dateInDesign (String date_time){
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = format.parse(date_time);
            result = (String) android.text.format.DateFormat.format("MMMM", date)+" "+
                    date_time.substring(0,2)+getDayOfMonthSuffix(Integer.valueOf(date_time.substring(0,2)))+", "+
                    date_time.substring(6,10)
            ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getDayOfMonthSuffix(final int n) {
        if (n >= 11 && n <= 13) {
            return "TH";
        }
        switch (n % 10) {
            case 1:  return "ST";
            case 2:  return "ND";
            case 3:  return "RD";
            default: return "TH";
        }
    }


}

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

import com.ingenico.PointOfSale.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator-Handy on 5/8/2016.
 */
public class GridViewAdapterItemCashRegister extends BaseAdapter {
    private Context context;
    private ArrayList<HashMap<String, String>> arrayListData;
    private static LayoutInflater layoutInflater;
    /**
     * declare for hash map in adapter
     */
    private static final String KEY_ITEM_ID = "item_id";
    private static final String KEY_ITEM_NAME = "item_name";
    private static final String KEY_ITEM_PRICE = "item_price";
    private static final String KEY_ITEM_DISCOUNT = "item_discount";
    private static final String KEY_ITEM_CATEGORY = "item_category";
    private static final String KEY_ITEM_PICTURE_URL = "item_picture_url";

    public GridViewAdapterItemCashRegister(Context mContext, ArrayList<HashMap<String, String>> arrayList) {
        context = mContext;
        arrayListData = arrayList;
        layoutInflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    private class Holder {
        TextView textViewItemName;
        ImageView imageViewItemImage;
        TextView textViewNoPictItem;
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

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;

        if (convertView == null) {
            rowView = layoutInflater.inflate(R.layout.item_gridview_item_cash_register, null);
            holder.textViewItemName = (TextView)rowView.findViewById(R.id.textViewItemName);
            holder.imageViewItemImage = (ImageView)rowView.findViewById(R.id.imageViewItemImage);
            holder.textViewNoPictItem = (TextView)rowView.findViewById(R.id.textViewNoPictItem);

            HashMap<String, String> hashMapData;
            hashMapData = arrayListData.get(position);

            holder.textViewItemName.setText(hashMapData.get(KEY_ITEM_NAME));
            if (hashMapData.get(KEY_ITEM_PICTURE_URL).equals("")){
                holder.textViewNoPictItem.setVisibility(View.VISIBLE);
            }
            holder.textViewNoPictItem.setText(hashMapData.get(KEY_ITEM_NAME).substring(0,2));
            loadImageFromPath(hashMapData.get(KEY_ITEM_PICTURE_URL),holder.imageViewItemImage);

        } else {
            rowView = convertView;
        }

        return rowView;
    }

    private void loadImageFromPath(String path, ImageView imageView){
        Log.d("Load Path",path);
        File imgFile = new  File(path);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
        }
    }

}

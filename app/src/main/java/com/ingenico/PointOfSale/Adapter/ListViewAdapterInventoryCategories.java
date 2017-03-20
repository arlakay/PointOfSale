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

import com.ingenico.PointOfSale.Object.Category;
import com.ingenico.PointOfSale.R;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by Administrator-Handy on 1/4/2017.
 */

public class ListViewAdapterInventoryCategories extends BaseAdapter {
    private Context context;
    private LinkedList<Category> linkedListData;
    private static LayoutInflater layoutInflater;

    public ListViewAdapterInventoryCategories(Context mContext, LinkedList<Category> linkedList_data){
        context = mContext;
        linkedListData = linkedList_data;
        layoutInflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private class Holder {
        TextView textViewName;
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
        return 1;
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
            rowView = layoutInflater.inflate(R.layout.item_list_view_inventory_categories, null);
            holder.textViewName = (TextView) rowView.findViewById(R.id.textViewName);

            Category category = linkedListData.get(position);

            holder.textViewName.setText(category.getCategory());

        }else {
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

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
import com.ingenico.PointOfSale.Object.Item;
import com.ingenico.PointOfSale.R;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Administrator-Handy on 1/4/2017.
 */

public class ListViewAdapterInventoryAllItem extends BaseAdapter {
    private Context context;
    private LinkedList<Item> linkedListData;
    private static LayoutInflater layoutInflater;

    public ListViewAdapterInventoryAllItem(Context mContext, LinkedList<Item> linkedList_data){
        context = mContext;
        linkedListData = linkedList_data;
        layoutInflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private class Holder {
        ImageView imageViewItem;
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
            rowView = layoutInflater.inflate(R.layout.item_list_view_inventory_all_item, null);
            holder.imageViewItem = (ImageView) rowView.findViewById(R.id.imageViewItem);
            holder.textViewName = (TextView) rowView.findViewById(R.id.textViewName);
            holder.textViewInformation = (TextView) rowView.findViewById(R.id.textViewInformation);

            Item item = linkedListData.get(position);

            loadImageFromPath(Declaration.IMAGE_OUTPUT_PATH+item.getItemID()+".png",
                    holder.imageViewItem);
            holder.textViewName.setText(item.getItemName());
            Log.e("test",item.getItemVarian().size()+"");
            if (item.getItemVarian().get(0).contains("var")){
                holder.textViewInformation.setText(item.getItemVarian().size()+" Variation");
            }else {
                holder.textViewInformation.setText("Rp "+
                        ((CashRegisterActivity)context).convertValuePattern(
                                item.getItemPrice()
                        )
                );
            }


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

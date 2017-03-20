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

/**
 * Created by Administrator-Handy on 5/3/2016.
 */
public class GridViewAdapterStaffInDashboard extends BaseAdapter {
    private Context context;
    private ArrayList<String> staffName;
    private ArrayList<String> staffImageUrl;
    private static LayoutInflater layoutInflater;


    public GridViewAdapterStaffInDashboard(Context mContext, ArrayList<String> mStaffName, ArrayList<String> mStaffImageUrl) {
        context = mContext;
        staffName = mStaffName;
        staffImageUrl = mStaffImageUrl;
        layoutInflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    private class Holder {
        TextView textViewStaffName;
        ImageView imageViewStaffImage;
    }

    @Override
    public int getCount() {
        return staffName.size();
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
            rowView = layoutInflater.inflate(R.layout.item_gridview_staff_dashboard, null);
            holder.textViewStaffName = (TextView)rowView.findViewById(R.id.textViewStaffName);
            holder.imageViewStaffImage = (ImageView)rowView.findViewById(R.id.imageViewStaffImage);

            holder.textViewStaffName.setText(staffName.get(position));

            if (staffImageUrl.get(position)!=null){
                loadImageFromPath(staffImageUrl.get(position),holder.imageViewStaffImage);
            }else {
                int imageResourceId = context.getResources().getIdentifier(
                        "ic_launcher", "drawable", context.getPackageName());
                holder.imageViewStaffImage.setImageResource(imageResourceId);
            }

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

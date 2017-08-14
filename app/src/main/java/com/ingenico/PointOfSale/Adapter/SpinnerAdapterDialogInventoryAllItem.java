package com.ingenico.PointOfSale.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Object.Category;
import com.ingenico.PointOfSale.R;

import java.util.LinkedList;

/**
 * Created by Administrator-Handy on 1/11/2017.
 */

public class SpinnerAdapterDialogInventoryAllItem extends ArrayAdapter {

    private Context context;
    private LinkedList<Category> linkedList;
    private static LayoutInflater layoutInflater;
    private Resources resources;

    public SpinnerAdapterDialogInventoryAllItem(Context mContext, int resource,
                                                LinkedList<Category> mLinkedList,
                                                Resources mResources) {
        super(mContext, resource, mLinkedList);
        context = mContext;
        linkedList = mLinkedList;
        resources = mResources;
        layoutInflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return customGetView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return customGetView(position, convertView, parent);
    }

    private class Holder {
        TextView textViewCategory;
    }

    private View customGetView (int position, View convertView, ViewGroup parent){
        Holder holder=new Holder();
        View row = layoutInflater.inflate(R.layout.spinner_dialog_inventory_all_item_category, parent, false);

        if (convertView == null){
            holder.textViewCategory = (TextView)row.findViewById(R.id.textViewCategory);

            Category category = linkedList.get(position);
            if (position==0){
                holder.textViewCategory.setText(Declaration.SELECT_CATEGORY);
            }else {
                holder.textViewCategory.setText(category.getCategory());
            }


        }else {
            row = convertView;
        }
        return row;
    }

}

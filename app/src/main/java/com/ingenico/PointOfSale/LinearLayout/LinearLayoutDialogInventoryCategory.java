package com.ingenico.PointOfSale.LinearLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Object.DialogCategory;
import com.ingenico.PointOfSale.R;

import java.io.File;

/**
 * Created by Administrator-Handy on 1/31/2017.
 */

public class LinearLayoutDialogInventoryCategory {
    private Context context;
    private DialogCategory dialogCategory;
    private ImageView imageViewItem;
    private TextView textViewItemName ,textViewCategoryName;
    private RadioButton radioButton;
    private LinearLayout linearLayoutOnClick;

    public LinearLayout LinearLayoutDialogInventoryCategory(
            Context mContext,
            DialogCategory mDialogCategory
    ){

        context = mContext;
        dialogCategory = mDialogCategory;

        LinearLayout linearLayoutParent = new LinearLayout(context);
        linearLayoutParent.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParamsLinearLayoutParent = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParent.setLayoutParams(layoutParamsLinearLayoutParent);

        RelativeLayout relativeLayoutChild = new RelativeLayout(context);
        LinearLayout.LayoutParams layoutParamsRelativeLayoutChild = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        relativeLayoutChild.setLayoutParams(layoutParamsRelativeLayoutChild);

        linearLayoutOnClick = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParamsLinearLayoutOnClick = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp_to_pixel(60f));
        linearLayoutOnClick.setLayoutParams(layoutParamsLinearLayoutOnClick);

        imageViewItem = new ImageView(context);
        RelativeLayout.LayoutParams layoutParamsImageViewItem = new RelativeLayout.LayoutParams(
                dp_to_pixel(60f), dp_to_pixel(60f));
        layoutParamsImageViewItem.addRule(RelativeLayout.ALIGN_PARENT_START,RelativeLayout.TRUE);
        imageViewItem.setLayoutParams(layoutParamsImageViewItem);
        if (!dialogCategory.getImageUrl().equals(Declaration.NULL_DATA)){
            loadImageFromPath(dialogCategory.getImageUrl(), imageViewItem);
        }else {
            imageViewItem.setBackgroundColor(context.getResources().getColor(
                    android.R.color.darker_gray));
        }
        imageViewItem.setId(View.generateViewId());


        radioButton = new RadioButton(context);
        RelativeLayout.LayoutParams layoutParamsRadioButton = new RelativeLayout.LayoutParams(
                dp_to_pixel(40f), dp_to_pixel(60f));
        layoutParamsRadioButton.addRule(RelativeLayout.ALIGN_PARENT_END,RelativeLayout.TRUE);
        radioButton.setLayoutParams(layoutParamsRadioButton);
        radioButton.setId(View.generateViewId());

        textViewItemName = new TextView(context);
        RelativeLayout.LayoutParams layoutParamsTextViewItemName = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsTextViewItemName.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
        layoutParamsTextViewItemName.setMarginStart(dp_to_pixel(25f));
        layoutParamsTextViewItemName.addRule(RelativeLayout.END_OF, imageViewItem.getId());
        textViewItemName.setLayoutParams(layoutParamsTextViewItemName);
        textViewItemName.setTextColor(context.getResources().getColor(android.R.color.black));
        textViewItemName.setTextSize(17);
        textViewItemName.setId(View.generateViewId());
        textViewItemName.setText(dialogCategory.getItemName());


        textViewCategoryName = new TextView(context);
        RelativeLayout.LayoutParams layoutParamsTextViewCategoryName = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsTextViewCategoryName.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
        layoutParamsTextViewCategoryName.setMarginEnd(dp_to_pixel(10f));
        layoutParamsTextViewCategoryName.addRule(RelativeLayout.LEFT_OF, radioButton.getId());
        textViewCategoryName.setLayoutParams(layoutParamsTextViewCategoryName);
        textViewCategoryName.setTextColor(context.getResources().getColor(android.R.color.darker_gray));
        textViewCategoryName.setTextSize(17);
        textViewCategoryName.setId(View.generateViewId());
        textViewCategoryName.setText(dialogCategory.getCategoryName());


        relativeLayoutChild.addView(imageViewItem);
        relativeLayoutChild.addView(radioButton);
        relativeLayoutChild.addView(textViewItemName);
        relativeLayoutChild.addView(textViewCategoryName);
        relativeLayoutChild.addView(linearLayoutOnClick);
        linearLayoutOnClick.bringToFront();

        View view = new View(context);
        LinearLayout.LayoutParams layoutParamsViewLine = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 1);
        view.setLayoutParams(layoutParamsViewLine);
        view.setBackgroundColor(context.getResources().getColor(
                android.R.color.darker_gray));

        linearLayoutParent.addView(relativeLayoutChild);
        linearLayoutParent.addView(view);

        linearLayoutOnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "text", Toast.LENGTH_SHORT).show();
            }
        });

        return linearLayoutParent;
    }

    private int dp_to_pixel(float dp){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float fpixels = metrics.density * dp;
        return (int) (fpixels + 0.5f);
    }

    private void loadImageFromPath(String path, ImageView imageView){
        Log.d("Load Path",path);
        File imgFile = new  File(path);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
        }
    }

    public LinearLayout getLinearLayoutOnClick (){
        return linearLayoutOnClick;
    }

    public ImageView getImageViewItem(){
        return imageViewItem;
    }

    public TextView getTextViewItemName(){
        return textViewItemName;
    }

    public TextView getTextViewCategoryName(){
        return textViewCategoryName;
    }

    public boolean isCheck(){
        return radioButton.isChecked();
    }

    public RadioButton getRadioButton(){
        return radioButton;
    }

    public void setActiveCategory(boolean active, String category){
        radioButton.setChecked(active);
        if (active){
            textViewCategoryName.setTextColor(
                    context.getResources().getColor(android.R.color.black)
            );
            textViewCategoryName.setText(category);
        }else {
            textViewCategoryName.setTextColor(
                    context.getResources().getColor(android.R.color.darker_gray)
            );
            textViewCategoryName.setText(dialogCategory.getCategoryName());
        }

    }

    public void setActiveRadioButton(boolean active){
        radioButton.setChecked(active);
    }

    public String getItemID(){
        return dialogCategory.getItemID();
    }

}

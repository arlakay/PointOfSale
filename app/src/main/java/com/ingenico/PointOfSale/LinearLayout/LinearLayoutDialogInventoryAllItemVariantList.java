package com.ingenico.PointOfSale.LinearLayout;

import android.content.Context;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Object.Variant;

/**
 * Created by Administrator-Handy on 1/13/2017.
 */

public class LinearLayoutDialogInventoryAllItemVariantList {
    private Context context;
    private EditText editTextNameVariant;
    private EditText editTextPriceVariant;
    private ImageView imageViewCancelVariant;
    private Variant variant;


    public LinearLayout LinearLayoutDialogInventoryAllItemVariantList(
            Context mContext,
            Variant mVariant
    ){
        context = mContext;
        variant = mVariant;

        /**
         * parent
         */
        LinearLayout linearLayoutParent = new LinearLayout(context);
        linearLayoutParent.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParamsLinearLayoutParent = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParent.setLayoutParams(layoutParamsLinearLayoutParent);


        /**
         * child
         */
        LinearLayout linearLayoutChild = new LinearLayout(context);
        linearLayoutChild.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParamsLinearLayoutChild = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutChild.setLayoutParams(layoutParamsLinearLayoutChild);

        /**
         * edit text name of variant
         */
        editTextNameVariant = new EditText(context);
        LinearLayout.LayoutParams layoutParamsLinearLayoutEditTextVariant = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsLinearLayoutEditTextVariant.weight = 0.3f;
        editTextNameVariant.setLayoutParams(layoutParamsLinearLayoutEditTextVariant);
        editTextNameVariant.setHint("Variant 1");
        editTextNameVariant.setTextSize(18);
        editTextNameVariant.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        editTextNameVariant.setSingleLine();
        if (variant.getVariantName().trim().length()!=0) {
            editTextNameVariant.setText(variant.getVariantName());
        }

        /**
         * edit text name of price
         */
        editTextPriceVariant = new EditText(context);
        LinearLayout.LayoutParams layoutParamsLinearLayoutEditTextVariantPrice = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsLinearLayoutEditTextVariantPrice.weight = 0.7f;
        editTextPriceVariant.setLayoutParams(layoutParamsLinearLayoutEditTextVariantPrice);
        editTextPriceVariant.setHint("Price");
        editTextPriceVariant.setTextSize(18);
        editTextPriceVariant.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        editTextPriceVariant.setSingleLine();

        if (variant.getVariantPrice().trim().length()!=0){
            if (Integer.parseInt(
                    variant.getVariantPrice().trim()
            ) != 0){
                Log.e("data_test_output_in_if", variant.getVariantPrice());
                editTextPriceVariant.setText(variant.getVariantPrice());
            }
        }

        /**
         * image view for cancel variant
         */
        imageViewCancelVariant = new ImageView(context);
        LinearLayout.LayoutParams layoutParamsImageViewCancelVariant = new LinearLayout.LayoutParams(
               dp_to_pixel(45f) , dp_to_pixel(45f));
        layoutParamsImageViewCancelVariant.gravity = Gravity.END;
        imageViewCancelVariant.setLayoutParams(layoutParamsImageViewCancelVariant);
        imageViewCancelVariant.setImageDrawable(context.getResources().getDrawable(
                android.R.drawable.ic_delete));
        if (variant.getVariantName().length()==0){
            imageViewCancelVariant.setVisibility(View.INVISIBLE);
        }else {
            imageViewCancelVariant.setVisibility(View.VISIBLE);
        }

        /**
         * view for line per variant
         */
        View view = new View(context);
        LinearLayout.LayoutParams layoutParamsViewLine = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 1);
        layoutParamsViewLine.setMargins(0,20,0,20);
        view.setLayoutParams(layoutParamsViewLine);
        view.setBackgroundColor(context.getResources().getColor(
                android.R.color.darker_gray));

        linearLayoutChild.addView(editTextNameVariant);
        linearLayoutChild.addView(editTextPriceVariant);
        linearLayoutChild.addView(imageViewCancelVariant);

        linearLayoutParent.addView(linearLayoutChild);
        linearLayoutParent.addView(view);

        return linearLayoutParent;
    }

    public EditText getEditTextVariantName(){
        return editTextNameVariant;
    }

    public EditText getEditTextVariantPrice(){
        return editTextPriceVariant;
    }

    public ImageView getImageViewVariantCancel(){
        return imageViewCancelVariant;
    }

    private int dp_to_pixel(float dp){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float fpixels = metrics.density * dp;
        return (int) (fpixels + 0.5f);
    }
}

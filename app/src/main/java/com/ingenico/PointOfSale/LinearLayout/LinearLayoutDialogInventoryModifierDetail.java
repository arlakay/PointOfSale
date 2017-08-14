package com.ingenico.PointOfSale.LinearLayout;

import android.content.Context;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ingenico.PointOfSale.Object.DialogCategory;
import com.ingenico.PointOfSale.Object.ModifierDetail;
import com.ingenico.PointOfSale.Object.ModifierGroup;
import com.ingenico.PointOfSale.R;

/**
 * Created by Administrator-Handy on 2/2/2017.
 */

public class LinearLayoutDialogInventoryModifierDetail {
    private Context context;
    private ModifierDetail modifierDetail;
    private EditText editTextOption, editTextValue;
    private ImageView imageViewDelete;


    public LinearLayout LinearLayoutDialogInventoryCategory(
            Context mContext,
            ModifierDetail mModifierDetail
    ){

        context = mContext;
        modifierDetail = mModifierDetail;

        LinearLayout linearLayoutParent = new LinearLayout(context);
        linearLayoutParent.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParamsLinearLayoutParent = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutParent.setLayoutParams(layoutParamsLinearLayoutParent);


        LinearLayout linearLayoutChild = new LinearLayout(context);
        linearLayoutChild.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParamsLinearLayoutChild = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutChild.setLayoutParams(layoutParamsLinearLayoutChild);

        editTextOption = new EditText(context);
        LinearLayout.LayoutParams layoutParamsEditTextOpt = new LinearLayout.LayoutParams(
                dp_to_pixel(0f), dp_to_pixel(50f));
        layoutParamsEditTextOpt.weight = 1;
        layoutParamsEditTextOpt.gravity = Gravity.CENTER_VERTICAL;
        editTextOption.setLayoutParams(layoutParamsEditTextOpt);
        editTextOption.setHint("New Option");
        editTextOption.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        editTextOption.setMaxLines(1);
        editTextOption.setInputType(InputType.TYPE_CLASS_TEXT);
        if (modifierDetail != null){
            editTextOption.setText(modifierDetail.getModifierDetailName());
        }

        editTextValue = new EditText(context);
        LinearLayout.LayoutParams layoutParamsEditTextValue = new LinearLayout.LayoutParams(
                dp_to_pixel(100f), dp_to_pixel(50f));
        layoutParamsEditTextValue.gravity = Gravity.CENTER_VERTICAL;
        editTextValue.setLayoutParams(layoutParamsEditTextValue);
        editTextValue.setHint("Value");
        editTextValue.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        editTextValue.setMaxLines(1);
        editTextValue.setInputType(InputType.TYPE_CLASS_NUMBER);
        if (modifierDetail != null){
            editTextValue.setText(modifierDetail.getModifierDetailPrice());
        }

        imageViewDelete = new ImageView(context);
        LinearLayout.LayoutParams layoutParamsImageViewDelete = new LinearLayout.LayoutParams(
                dp_to_pixel(40f), dp_to_pixel(50f));
        layoutParamsImageViewDelete.gravity = Gravity.CENTER;
        imageViewDelete.setLayoutParams(layoutParamsImageViewDelete);
        imageViewDelete.setImageDrawable(context.getResources().getDrawable(android.R.drawable.ic_delete));
        if (modifierDetail != null){
            if (modifierDetail.getModifierDetailName().length()>0){
                imageViewDelete.setVisibility(View.VISIBLE);
            }else {
                imageViewDelete.setVisibility(View.INVISIBLE);
            }
        }else {imageViewDelete.setVisibility(View.INVISIBLE);}




        linearLayoutChild.addView(editTextOption);
        linearLayoutChild.addView(editTextValue);
        linearLayoutChild.addView(imageViewDelete);


        View view = new View(context);
        LinearLayout.LayoutParams layoutParamsViewLine = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 1);
        view.setLayoutParams(layoutParamsViewLine);
        view.setBackgroundColor(context.getResources().getColor(
                android.R.color.darker_gray));

        linearLayoutParent.addView(linearLayoutChild);
        linearLayoutParent.addView(view);


        return linearLayoutParent;
    }


    private int dp_to_pixel(float dp){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float fpixels = metrics.density * dp;
        return (int) (fpixels + 0.5f);
    }

    public EditText getEditTextOption() {
        return editTextOption;
    }

    public EditText getEditTextValue() {
        return editTextValue;
    }

    public ImageView getImageViewDelete() {
        return imageViewDelete;
    }


}

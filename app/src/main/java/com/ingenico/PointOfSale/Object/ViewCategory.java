package com.ingenico.PointOfSale.Object;

import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Administrator-Handy on 2/1/2017.
 */

public class ViewCategory {
    private LinearLayout linearLayoutOnClick;
    private RadioButton radioButton;
    private TextView textViewCategory;

    public LinearLayout getLinearLayoutOnClick() {
        return linearLayoutOnClick;
    }

    public void setLinearLayoutOnClick(LinearLayout linearLayoutOnClick) {
        this.linearLayoutOnClick = linearLayoutOnClick;
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(RadioButton radioButton) {
        this.radioButton = radioButton;
    }

    public TextView getTextViewCategory() {
        return textViewCategory;
    }

    public void setTextViewCategory(TextView textViewCategory) {
        this.textViewCategory = textViewCategory;
    }
}

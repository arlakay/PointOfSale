package com.ingenico.PointOfSale.Controller;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ingenico.PointOfSale.R;


/**
 * Created by Administrator-Handy on 1/9/2017.
 */

public class MultiRowTextCheckedTextView extends LinearLayout
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    private LinearLayout topLayout;
    private String subTitleText;
    private String titleText;
    private boolean checkBoxCheckedState;
    private View horizontalView;
    private CheckBox checkBox;
    private TextView subTitleTextView;
    private TextView titleTextView;
    private boolean tempCheckBoxCheckedState = false;

    public MultiRowTextCheckedTextView(Context context) {
        this(context, null);
    }

    public MultiRowTextCheckedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        /**
         * Get the resource values
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MultiRowTextCheckedTextView, 0, 0);

        /**
         * Extract the resource values from typed array to variables
         */
        titleText = typedArray.getString(R.styleable.MultiRowTextCheckedTextView_cTitleText);
        int titleTextColor = typedArray.getColor(R.styleable.MultiRowTextCheckedTextView_cTitleTextColor,
                getResources().getColor(R.color.primaryTextColor));
        subTitleText = typedArray.getString(R.styleable.MultiRowTextCheckedTextView_cSubTitleText);
        int subTitleTextColor = typedArray.getColor(R.styleable.MultiRowTextCheckedTextView_cSubTitleTextColor,
                getResources().getColor(R.color.secondaryTextColor));
        checkBoxCheckedState = typedArray.getBoolean(R.styleable.MultiRowTextCheckedTextView_cCheckedState,
                false);
        int dividerColor = typedArray.getColor(R.styleable.MultiRowTextCheckedTextView_cDividerColor,
                getResources().getColor(R.color.dividerColor));
        boolean dividerCheckedState = typedArray.getBoolean(R.styleable.MultiRowTextCheckedTextView_cCheckedState,
                true);
        String titleFont = typedArray.getString(R.styleable.MultiRowTextCheckedTextView_cTitleTextFont);
        String subTitleFont = typedArray.getString(R.styleable.MultiRowTextCheckedTextView_cSubTitleTextFont);
        boolean isFixedHeight = typedArray.getBoolean(R.styleable.MultiRowTextCheckedTextView_cSetFixedHeight,
                false);
        typedArray.recycle();

        /**
         * Set Orientation and Gravity for the root layout
         */
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);

        /**
         * RD here
         */
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(isFixedHeight ? R.layout.custom_checked_textview
                : R.layout.custom_checked_textview_list_height, this, true);

        /**
         * Load preferences
         */
        tempCheckBoxCheckedState = checkBoxCheckedState;

        /**
         * Get the top layout
         */
        topLayout = (LinearLayout) getChildAt(0);

        /**
         * Get the first Relative layout which hold the Title and Sub title texts
         */
        LinearLayout titleLayout = (LinearLayout) topLayout.getChildAt(0);

        titleTextView = (TextView) titleLayout.getChildAt(0);
        subTitleTextView = (TextView) titleLayout.getChildAt(1);
        /**
         * Get the second Relative layout which hold the Check Box
         */
        LinearLayout checkBoxLayout = (LinearLayout) topLayout.getChildAt(1);
        checkBox = (CheckBox) checkBoxLayout.getChildAt(0);

        topLayout.setOnClickListener(this);
        /**
         * Get the bottom layout
         */
        horizontalView = getChildAt(1);

        if (TextUtils.isEmpty(titleText))
            titleTextView.setVisibility(View.GONE);
        else
            titleTextView.setText(titleText);

        titleTextView.setTextColor(titleTextColor);


        if (TextUtils.isEmpty(subTitleText))
            subTitleTextView.setVisibility(View.GONE);
        else
            subTitleTextView.setText(subTitleText);

        subTitleTextView.setTextColor(subTitleTextColor);

        if (!TextUtils.isEmpty(titleFont)) setTitleTextTypeface(titleFont);
        if (!TextUtils.isEmpty(subTitleFont)) setSubTitleTextTypeface(subTitleFont);

        checkBox.setChecked(checkBoxCheckedState);

        horizontalView.setBackgroundColor(dividerColor);
        horizontalView.setVisibility(dividerCheckedState ? View.VISIBLE : View.GONE);

    }

    public MultiRowTextCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MultiRowTextCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onClick(View v) {
        if (tempCheckBoxCheckedState) {
            checkBox.setChecked(false);
            checkBoxCheckedState = false;
            tempCheckBoxCheckedState = false;
        } else {
            checkBox.setChecked(true);
            checkBoxCheckedState = true;
            tempCheckBoxCheckedState = true;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        checkBoxCheckedState = tempCheckBoxCheckedState = isChecked;
    }

    public void setTitleText(String pTitleText) {
        this.titleTextView.setText(pTitleText);

        if (!TextUtils.isEmpty(pTitleText))
            switch (titleTextView.getVisibility()) {
                case View.GONE:
                case View.INVISIBLE:
                    titleTextView.setVisibility(View.VISIBLE);
                    break;
            }

    }

    public void setTitleTextColor(int pTitleTextColor) {
        this.titleTextView.setTextColor(pTitleTextColor);
    }

    public void setSubTitleText(String pSubTitleText) {
        this.subTitleTextView.setText(pSubTitleText);

        if (!TextUtils.isEmpty(pSubTitleText))
            switch (subTitleTextView.getVisibility()) {
                case View.GONE:
                case View.INVISIBLE:
                    subTitleTextView.setVisibility(View.VISIBLE);
                    break;
            }
    }

    public void setSubTitleTextColor(int pSubTitleTextColor) {
        this.subTitleTextView.setTextColor(pSubTitleTextColor);
    }

    public void setDividerColor(int pDividerColor) {
        this.horizontalView.setBackgroundColor(pDividerColor);
    }

    public String getTitleText() {
        return this.titleText;
    }

    public String getSubTitleText() {
        return this.subTitleText;
    }

    public void setChecked(boolean pCheckedState) {
        this.checkBox.setChecked(pCheckedState);
        this.tempCheckBoxCheckedState = pCheckedState;
    }

    public void setDividerVisibility(boolean pCheckedState) {
        this.horizontalView.setVisibility(pCheckedState ? View.VISIBLE : View.GONE);
    }

    public boolean getChecked() {
        return this.checkBoxCheckedState;
    }

    public void setTitleTextTypeface(String fontNameWithAssetPath) {

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), fontNameWithAssetPath);
        this.titleTextView.setTypeface(typeface);
    }

    public void setTitleTextTypeface(int resId) {
        setTitleTextTypeface(getContext().getString(resId));
    }

    public void setSubTitleTextTypeface(String fontNameWithAssetPath) {

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), fontNameWithAssetPath);
        this.subTitleTextView.setTypeface(typeface);
    }

    public void setSubTitleTextTypeface(int resId) {
        setSubTitleText(getContext().getString(resId));
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        topLayout.setOnClickListener(onClickListener);
    }

    public boolean isCheck(){
        return checkBox.isChecked();
    }

}
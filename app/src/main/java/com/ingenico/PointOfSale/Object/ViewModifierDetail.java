package com.ingenico.PointOfSale.Object;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Administrator-Handy on 2/2/2017.
 */

public class ViewModifierDetail {
    private LinearLayout linearLayoutParent;
    private EditText editTextOption;
    private EditText editTextValue;
    private ImageView imageViewDelete;

    public LinearLayout getLinearLayoutParent() {
        return linearLayoutParent;
    }

    public void setLinearLayoutParent(LinearLayout linearLayoutParent) {
        this.linearLayoutParent = linearLayoutParent;
    }

    public EditText getEditTextOption() {
        return editTextOption;
    }

    public void setEditTextOption(EditText editTextOption) {
        this.editTextOption = editTextOption;
    }

    public EditText getEditTextValue() {
        return editTextValue;
    }

    public void setEditTextValue(EditText editTextValue) {
        this.editTextValue = editTextValue;
    }

    public ImageView getImageViewDelete() {
        return imageViewDelete;
    }

    public void setImageViewDelete(ImageView imageViewDelete) {
        this.imageViewDelete = imageViewDelete;
    }
}

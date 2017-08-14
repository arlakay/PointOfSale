package com.ingenico.PointOfSale.Object;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Administrator-Handy on 1/16/2017.
 */

public class ViewVariant {
    private LinearLayout LinearLayoutParent;
    private EditText EditTextName;
    private EditText EditTextPrice;
    private ImageView ImageViewCancel;

    public ImageView getImageViewCancel() {
        return ImageViewCancel;
    }

    public void setImageViewCancel(ImageView imageViewCancel) {
        ImageViewCancel = imageViewCancel;
    }

    public EditText getEditTextPrice() {
        return EditTextPrice;
    }

    public void setEditTextPrice(EditText editTextPrice) {
        EditTextPrice = editTextPrice;
    }

    public EditText getEditTextName() {
        return EditTextName;
    }

    public void setEditTextName(EditText editTextName) {
        EditTextName = editTextName;
    }

    public LinearLayout getLinearLayoutParent() {
        return LinearLayoutParent;
    }

    public void setLinearLayoutParent(LinearLayout linearLayoutParent) {
        LinearLayoutParent = linearLayoutParent;
    }
}

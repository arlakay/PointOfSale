package com.ingenico.PointOfSale.Object;

import com.ingenico.PointOfSale.Controller.MultiRowTextCheckedTextView;

/**
 * Created by Administrator-Handy on 1/23/2017.
 */

public class ModifierSet {
    private MultiRowTextCheckedTextView multiRowTextCheckedTextView;
    private String modifierGroupID;

    public String getModifierGroupID() {
        return modifierGroupID;
    }

    public void setModifierGroupID(String modifierGroupID) {
        this.modifierGroupID = modifierGroupID;
    }

    public MultiRowTextCheckedTextView getMultiRowTextCheckedTextView() {
        return multiRowTextCheckedTextView;
    }

    public void setMultiRowTextCheckedTextView(MultiRowTextCheckedTextView multiRowTextCheckedTextView) {
        this.multiRowTextCheckedTextView = multiRowTextCheckedTextView;
    }
}

package com.ingenico.PointOfSale.Object;

/**
 * Created by Administrator-Handy on 1/5/2017.
 */

public class ModifierDetail {
    private String ModifierDetailID;
    private String ModifierDetailName;
    private String ModifierDetailPrice;
    private String ModifierDetailGroupID;

    public String getModifierDetailGroupID() {
        return ModifierDetailGroupID;
    }

    public void setModifierDetailGroupID(String modifierDetailGroupID) {
        ModifierDetailGroupID = modifierDetailGroupID;
    }

    public String getModifierDetailPrice() {
        return ModifierDetailPrice;
    }

    public void setModifierDetailPrice(String modifierDetailPrice) {
        ModifierDetailPrice = modifierDetailPrice;
    }

    public String getModifierDetailName() {
        return ModifierDetailName;
    }

    public void setModifierDetailName(String modifierDetailName) {
        ModifierDetailName = modifierDetailName;
    }

    public String getModifierDetailID() {
        return ModifierDetailID;
    }

    public void setModifierDetailID(String modifierDetailID) {
        ModifierDetailID = modifierDetailID;
    }


}

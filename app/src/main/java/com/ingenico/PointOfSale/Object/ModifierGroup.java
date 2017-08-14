package com.ingenico.PointOfSale.Object;


import java.util.LinkedList;

/**
 * Created by Administrator-Handy on 1/5/2017.
 */

public class ModifierGroup {
    private String ModifierGroupID;
    private String ModifierGroupName;
    private String ModifierGroupOptionFlag;
    private String ModifierGroupOutletID;
    private LinkedList<ModifierDetail> ModifierDetail;

    public LinkedList<com.ingenico.PointOfSale.Object.ModifierDetail> getModifierDetail() {
        return ModifierDetail;
    }

    public void setModifierDetail(LinkedList<com.ingenico.PointOfSale.Object.ModifierDetail> modifierDetail) {
        ModifierDetail = modifierDetail;
    }

    public String getModifierGroupOutletID() {
        return ModifierGroupOutletID;
    }

    public void setModifierGroupOutletID(String modifierGroupOutletID) {
        ModifierGroupOutletID = modifierGroupOutletID;
    }

    public String getModifierGroupOptionFlag() {
        return ModifierGroupOptionFlag;
    }

    public void setModifierGroupOptionFlag(String modifierGroupOptionFlag) {
        ModifierGroupOptionFlag = modifierGroupOptionFlag;
    }

    public String getModifierGroupName() {
        return ModifierGroupName;
    }

    public void setModifierGroupName(String modifierGroupName) {
        ModifierGroupName = modifierGroupName;
    }

    public String getModifierGroupID() {
        return ModifierGroupID;
    }

    public void setModifierGroupID(String modifierGroupID) {
        ModifierGroupID = modifierGroupID;
    }


}

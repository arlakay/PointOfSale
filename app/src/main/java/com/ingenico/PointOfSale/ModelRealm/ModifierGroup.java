package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 6/2/2016.
 */
public class ModifierGroup extends RealmObject {
    private long realmModifierGroupID;
    private String modifierGroupID;
    private String modifierGroupName;
    private String modifierGroupOptionFlag;
    private String modifierGroupOutletID;

    public void setRealmModifierGroupID(long realmModifierGroupID) {
        this.realmModifierGroupID = realmModifierGroupID;
    }

    public void setModifierGroupID(String modifierGroupID) {
        this.modifierGroupID = modifierGroupID;
    }

    public void setModifierGroupName(String modifierGroupName) {
        this.modifierGroupName = modifierGroupName;
    }

    public void setModifierGroupOptionFlag(String modifierGroupOptionFlag) {
        this.modifierGroupOptionFlag = modifierGroupOptionFlag;
    }

    public void setModifierGroupOutletID(String modifierGroupOutletID) {
        this.modifierGroupOutletID = modifierGroupOutletID;
    }

    public long getRealmModifierGroupID() {
        return realmModifierGroupID;
    }

    public String getModifierGroupID() {
        return modifierGroupID;
    }

    public String getModifierGroupName() {
        return modifierGroupName;
    }

    public String getModifierGroupOptionFlag() {
        return modifierGroupOptionFlag;
    }

    public String getModifierGroupOutletID() {
        return modifierGroupOutletID;
    }
}

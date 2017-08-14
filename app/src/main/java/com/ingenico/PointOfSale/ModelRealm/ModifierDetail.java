package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 5/24/2016.
 */
public class ModifierDetail extends RealmObject {
    private long realmModifierDetailID;
    private String modifierDetailID;
    private String modifierDetailName;
    private String modifierDetailPrice;
    private String modifierDetailGroupID;

    public void setRealmModifierDetailID(long realmModifierDetailID) {
        this.realmModifierDetailID = realmModifierDetailID;
    }

    public void setModifierDetailID(String modifierDetailID) {
        this.modifierDetailID = modifierDetailID;
    }

    public void setModifierDetailName(String modifierDetailName) {
        this.modifierDetailName = modifierDetailName;
    }

    public void setModifierDetailPrice(String modifierDetailPrice) {
        this.modifierDetailPrice = modifierDetailPrice;
    }

    public void setModifierDetailGroupID(String modifierDetailGroupID) {
        this.modifierDetailGroupID = modifierDetailGroupID;
    }

    public long getRealmModifierDetailID() {
        return realmModifierDetailID;
    }

    public String getModifierDetailID() {
        return modifierDetailID;
    }

    public String getModifierDetailName() {
        return modifierDetailName;
    }

    public String getModifierDetailPrice() {
        return modifierDetailPrice;
    }

    public String getModifierDetailGroupID() {
        return modifierDetailGroupID;
    }
}

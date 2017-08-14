package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 5/27/2016.
 */
public class ItemModifierGroup extends RealmObject {
    private long realmItemModifierGroupID;
    private String itemModifierGroupID;

    public void setRealmItemModifierGroupID(long realmItemModifierGroupID) {
        this.realmItemModifierGroupID = realmItemModifierGroupID;
    }

    public void setItemModifierGroupID(String itemModifierGroupID) {
        this.itemModifierGroupID = itemModifierGroupID;
    }

    public long getRealmItemModifierGroupID() {
        return realmItemModifierGroupID;
    }

    public String getItemModifierGroupID() {
        return itemModifierGroupID;
    }
}

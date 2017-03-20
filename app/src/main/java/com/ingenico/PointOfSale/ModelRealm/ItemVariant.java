package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 5/27/2016.
 */
public class ItemVariant extends RealmObject {
    private long realmItemVariantID;
    private String itemVariantID;

    public void setRealmItemVariantID(long realmItemVariantID) {
        this.realmItemVariantID = realmItemVariantID;
    }

    public void setItemVariantID(String itemVariantID) {
        this.itemVariantID = itemVariantID;
    }

    public long getRealmItemVariantID() {
        return realmItemVariantID;
    }

    public String getItemVariantID() {
        return itemVariantID;
    }
}

package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 5/24/2016.
 */
public class Variant extends RealmObject {
    private long realmVariantID;
    private String VariantID;
    private String VariantName;
    private String VariantPrice;

    public void setRealmVariantID(long realmVariantID) {
        this.realmVariantID = realmVariantID;
    }

    public void setVariantID(String variantID) {
        VariantID = variantID;
    }

    public void setVariantName(String variantName) {
        VariantName = variantName;
    }

    public void setVariantPrice(String variantPrice) {
        VariantPrice = variantPrice;
    }

    public long getRealmVariantID() {
        return realmVariantID;
    }

    public String getVariantID() {
        return VariantID;
    }

    public String getVariantName() {
        return VariantName;
    }

    public String getVariantPrice() {
        return VariantPrice;
    }
}

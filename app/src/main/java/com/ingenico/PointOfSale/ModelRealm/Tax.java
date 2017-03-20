package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 6/10/2016.
 */
public class Tax extends RealmObject {
    private long realmTaxID;
    private String taxOutletID;
    private String taxValue;

    public void setRealmTaxID(long realmTaxID) {
        this.realmTaxID = realmTaxID;
    }

    public void setTaxOutletID(String taxOutletID) {
        this.taxOutletID = taxOutletID;
    }

    public void setTaxValue(String taxValue) {
        this.taxValue = taxValue;
    }

    public long getRealmTaxID() {
        return realmTaxID;
    }

    public String getTaxOutletID() {
        return taxOutletID;
    }

    public String getTaxValue() {
        return taxValue;
    }
}

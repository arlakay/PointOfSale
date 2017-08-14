package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 6/2/2016.
 */
public class DiscountMaster extends RealmObject {
    private long realmDiscountMasterID;
    private String discountMasterID;
    private String discountMasterName;
    private String discountMasterValue;
    private String discountMasterType;
    private String discountMasterOutletID;

    public void setRealmDiscountMasterID(long realmDiscountMasterID) {
        this.realmDiscountMasterID = realmDiscountMasterID;
    }

    public void setDiscountMasterID(String discountMasterID) {
        this.discountMasterID = discountMasterID;
    }

    public void setDiscountMasterName(String discountMasterName) {
        this.discountMasterName = discountMasterName;
    }

    public void setDiscountMasterValue(String discountMasterValue) {
        this.discountMasterValue = discountMasterValue;
    }

    public void setDiscountMasterType(String discountMasterType) {
        this.discountMasterType = discountMasterType;
    }

    public void setDiscountMasterOutletID(String discountMasterOutletID) {
        this.discountMasterOutletID = discountMasterOutletID;
    }

    public long getRealmDiscountMasterID() {
        return realmDiscountMasterID;
    }

    public String getDiscountMasterID() {
        return discountMasterID;
    }

    public String getDiscountMasterName() {
        return discountMasterName;
    }

    public String getDiscountMasterType() {
        return discountMasterType;
    }

    public String getDiscountMasterValue() {
        return discountMasterValue;
    }

    public String getDiscountMasterOutletID() {
        return discountMasterOutletID;
    }
}

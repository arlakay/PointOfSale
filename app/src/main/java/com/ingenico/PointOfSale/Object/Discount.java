package com.ingenico.PointOfSale.Object;

/**
 * Created by Administrator-Handy on 1/5/2017.
 */

public class Discount {
    private String DiscountMasterID;
    private String DiscountMasterName;
    private String DiscountMasterValue;
    private String DiscountMasterType;
    private String DiscountMasterOutletID;

    public String getDiscountMasterOutletID() {
        return DiscountMasterOutletID;
    }

    public void setDiscountMasterOutletID(String discountMasterOutletID) {
        DiscountMasterOutletID = discountMasterOutletID;
    }

    public String getDiscountMasterType() {
        return DiscountMasterType;
    }

    public void setDiscountMasterType(String discountMasterType) {
        DiscountMasterType = discountMasterType;
    }

    public String getDiscountMasterValue() {
        return DiscountMasterValue;
    }

    public void setDiscountMasterValue(String discountMasterValue) {
        DiscountMasterValue = discountMasterValue;
    }

    public String getDiscountMasterName() {
        return DiscountMasterName;
    }

    public void setDiscountMasterName(String discountMasterName) {
        DiscountMasterName = discountMasterName;
    }

    public String getDiscountMasterID() {
        return DiscountMasterID;
    }

    public void setDiscountMasterID(String discountMasterID) {
        DiscountMasterID = discountMasterID;
    }

}

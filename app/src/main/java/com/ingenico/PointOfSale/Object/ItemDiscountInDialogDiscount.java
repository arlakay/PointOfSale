package com.ingenico.PointOfSale.Object;

/**
 * Created by Administrator-Handy on 11/7/2016.
 */

public class ItemDiscountInDialogDiscount {
    private String DiscountID;
    private String DiscountName;
    private String DiscountValue;
    private String DiscountType;
    private String QuantityItemGotDiscount;

    public String getDiscountID() {
        return DiscountID;
    }

    public void setDiscountID(String discountID) {
        DiscountID = discountID;
    }

    public String getDiscountName() {
        return DiscountName;
    }

    public void setDiscountName(String discountName) {
        DiscountName = discountName;
    }

    public String getDiscountValue() {
        return DiscountValue;
    }

    public void setDiscountValue(String discountValue) {
        DiscountValue = discountValue;
    }

    public String getDiscountType() {
        return DiscountType;
    }

    public void setDiscountType(String discountType) {
        DiscountType = discountType;
    }

    public String getQuantityItemGotDiscount() {
        return QuantityItemGotDiscount;
    }

    public void setQuantityItemGotDiscount(String quantityItemGotDiscount) {
        QuantityItemGotDiscount = quantityItemGotDiscount;
    }
}

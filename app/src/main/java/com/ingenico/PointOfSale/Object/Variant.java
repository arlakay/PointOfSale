package com.ingenico.PointOfSale.Object;

/**
 * Created by Administrator-Handy on 1/13/2017.
 */

public class Variant {
    private String VariantID;
    private String VariantName;
    private String VariantPrice;

    public String getVariantID() {
        return VariantID;
    }

    public void setVariantID(String variantID) {
        VariantID = variantID;
    }

    public String getVariantName() {
        return VariantName;
    }

    public void setVariantName(String variantName) {
        VariantName = variantName;
    }

    public String getVariantPrice() {
        return VariantPrice;
    }

    public void setVariantPrice(String variantPrice) {
        VariantPrice = variantPrice;
    }
}

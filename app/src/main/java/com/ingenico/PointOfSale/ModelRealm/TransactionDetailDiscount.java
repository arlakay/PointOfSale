package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 6/16/2016.
 */
public class TransactionDetailDiscount extends RealmObject {
    private String discountID;


    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

}

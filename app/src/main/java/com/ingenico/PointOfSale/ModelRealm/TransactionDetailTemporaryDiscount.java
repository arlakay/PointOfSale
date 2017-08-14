package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 6/16/2016.
 */
public class TransactionDetailTemporaryDiscount extends RealmObject {
    private String transactionDetailTemporaryDiscountID;


    public String getTransactionDetailTemporaryDiscountID() {
        return transactionDetailTemporaryDiscountID;
    }

    public void setTransactionDetailTemporaryDiscountID(String transactionDetailTemporaryDiscountID) {
        this.transactionDetailTemporaryDiscountID = transactionDetailTemporaryDiscountID;
    }



}

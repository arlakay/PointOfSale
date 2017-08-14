package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 6/15/2016.
 */
public class TransactionDetailModifier extends RealmObject {
    private String transactionDetailModifierID;

    public void setTransactionDetailModifierID(String transactionDetailModifierID) {
        this.transactionDetailModifierID = transactionDetailModifierID;
    }

    public String getTransactionDetailModifierID() {
        return transactionDetailModifierID;
    }
}

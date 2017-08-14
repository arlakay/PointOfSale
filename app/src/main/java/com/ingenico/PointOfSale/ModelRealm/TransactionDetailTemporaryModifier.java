package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 6/15/2016.
 */
public class TransactionDetailTemporaryModifier extends RealmObject {
    private String transactionDetailTemporaryModifierID;

    public void setTransactionDetailTemporaryModifierID(String transactionDetailTemporaryModifierID) {
        this.transactionDetailTemporaryModifierID = transactionDetailTemporaryModifierID;
    }

    public String getTransactionDetailTemporaryModifierID() {
        return transactionDetailTemporaryModifierID;
    }
}

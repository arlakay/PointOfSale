package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 10/20/2016.
 */

public class TransactionDetailTemporary extends RealmObject {
    private String transactionDetailTemporaryID; // item transaction
    private String transactionDetailTemporaryItemID;
    private String transactionDetailTemporaryVariantID;
    public RealmList<TransactionDetailTemporaryModifier> transactionDetailTemporaryModifierID;
    private String transactionDetailTemporaryTotalPrice;
    public RealmList<TransactionDetailTemporaryDiscount> transactionDetailTemporaryDiscountID;
    private String transactionDetailTemporaryQuantity;


    public void setTransactionDetailTemporaryID(String transactionDetailTemporaryID) {
        this.transactionDetailTemporaryID = transactionDetailTemporaryID;
    }

    public void setTransactionDetailTemporaryItemID(String transactionDetailTemporaryItemID) {
        this.transactionDetailTemporaryItemID = transactionDetailTemporaryItemID;
    }

    public void setTransactionDetailTemporaryVariantID(String transactionDetailTemporaryVariantID) {
        this.transactionDetailTemporaryVariantID = transactionDetailTemporaryVariantID;
    }

    public void setTransactionDetailTemporaryModifierID(RealmList<TransactionDetailTemporaryModifier> transactionDetailTemporaryModifierID) {
        this.transactionDetailTemporaryModifierID = transactionDetailTemporaryModifierID;
    }

    public void setTransactionDetailTemporaryTotalPrice(String transactionDetailTemporaryTotalPrice) {
        this.transactionDetailTemporaryTotalPrice = transactionDetailTemporaryTotalPrice;
    }

    public void setTransactionDetailTemporaryDiscountID(RealmList<TransactionDetailTemporaryDiscount> transactionDetailTemporaryDiscountID) {
        this.transactionDetailTemporaryDiscountID = transactionDetailTemporaryDiscountID;
    }

    public void setTransactionDetailTemporaryQuantity(String transactionDetailTemporaryQuantity) {
        this.transactionDetailTemporaryQuantity = transactionDetailTemporaryQuantity;
    }


    public String getTransactionTemporaryDetailID() {
        return transactionDetailTemporaryID;
    }

    public RealmList<TransactionDetailTemporaryModifier> getTransactionDetailTemporaryModifierID() {
        return transactionDetailTemporaryModifierID;
    }

    public RealmList<TransactionDetailTemporaryDiscount> getTransactionDetailTemporaryDiscountID() {
        return transactionDetailTemporaryDiscountID;
    }

    public String getTransactionDetailTemporaryItemID() {
        return transactionDetailTemporaryItemID;
    }

    public String getTransactionDetailTemporaryQuantity() {
        return transactionDetailTemporaryQuantity;
    }

    public String getTransactionDetailTemporaryTotalPrice() {
        return transactionDetailTemporaryTotalPrice;
    }

    public String getTransactionDetailTemporaryVariantID() {
        return transactionDetailTemporaryVariantID;
    }

}

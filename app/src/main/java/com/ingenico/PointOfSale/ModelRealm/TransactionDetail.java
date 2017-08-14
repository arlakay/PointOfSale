package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 6/15/2016.
 */
public class TransactionDetail extends RealmObject {
    private String transactionDetailID; // item transaction
    private String transactionDetailItemID;
    private String transactionDetailVariantID;
    public RealmList<TransactionDetailModifier> transactionDetailModifierID;
    private String transactionDetailTotalPrice;
    public RealmList<TransactionDetailDiscount> transactionDetailDiscountID;
    private String transactionDetailQuantity;

    public void setTransactionDetailID(String transactionDetailID) {
        this.transactionDetailID = transactionDetailID;
    }

    public void setTransactionDetailItemID(String transactionDetailItemID) {
        this.transactionDetailItemID = transactionDetailItemID;
    }

    public void setTransactionDetailVariantID(String transactionDetailVariantID) {
        this.transactionDetailVariantID = transactionDetailVariantID;
    }

    public void setTransactionDetailModifierID(RealmList<TransactionDetailModifier> transactionDetailModifierID) {
        this.transactionDetailModifierID = transactionDetailModifierID;
    }

    public void setTransactionDetailTotalPrice(String transactionDetailTotalPrice) {
        this.transactionDetailTotalPrice = transactionDetailTotalPrice;
    }

    public void setTransactionDetailDiscountID(RealmList<TransactionDetailDiscount> transactionDetailDiscountID) {
        this.transactionDetailDiscountID = transactionDetailDiscountID;
    }

    public void setTransactionDetailQuantity(String transactionDetailQuantity) {
        this.transactionDetailQuantity = transactionDetailQuantity;
    }

    public String getTransactionDetailID() {
        return transactionDetailID;
    }

    public RealmList<TransactionDetailModifier> getTransactionDetailModifierID() {
        return transactionDetailModifierID;
    }

    public RealmList<TransactionDetailDiscount> getTransactionDetailDiscountID() {
        return transactionDetailDiscountID;
    }

    public String getTransactionDetailItemID() {
        return transactionDetailItemID;
    }

    public String getTransactionDetailQuantity() {
        return transactionDetailQuantity;
    }

    public String getTransactionDetailTotalPrice() {
        return transactionDetailTotalPrice;
    }

    public String getTransactionDetailVariantID() {
        return transactionDetailVariantID;
    }

}

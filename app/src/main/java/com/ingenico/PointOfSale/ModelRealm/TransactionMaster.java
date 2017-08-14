package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 6/15/2016.
 */
public class TransactionMaster extends RealmObject {

    private String transactionTerminalID;
    private String transactionMasterID;
    private String transactionMasterUserID;
    private String transactionMasterCashierName;
    private String transactionMasterTotalTransaction;
    private String transactionMasterTotalQuantity;
    private String transactionMasterTax;
    private String transactionMasterService;
    private String transactionMasterDiscount;
    private String transactionMasterSubTotal;
    private String transactionMasterDate;
    private String transactionMasterDateAndTime;
    private String transactionMasterPaymentType;
    private String transactionMasterTendered;
    private String transactionMasterChange;
    private String transactionMasterEmail;
    private String transactionMasterTableNumber;

    public String getTransactionTerminalID() {
        return transactionTerminalID;
    }

    public void setTransactionTerminalID(String transactionTerminalID) {
        this.transactionTerminalID = transactionTerminalID;
    }

    public String getTransactionMasterID() {
        return transactionMasterID;
    }

    public void setTransactionMasterID(String transactionMasterID) {
        this.transactionMasterID = transactionMasterID;
    }

    public String getTransactionMasterCashierName() {
        return transactionMasterCashierName;
    }

    public void setTransactionMasterCashierName(String transactionMasterCashierName) {
        this.transactionMasterCashierName = transactionMasterCashierName;
    }

    public String getTransactionMasterTotalTransaction() {
        return transactionMasterTotalTransaction;
    }

    public void setTransactionMasterTotalTransaction(String transactionMasterTotalTransaction) {
        this.transactionMasterTotalTransaction = transactionMasterTotalTransaction;
    }

    public String getTransactionMasterTotalQuantity() {
        return transactionMasterTotalQuantity;
    }

    public void setTransactionMasterTotalQuantity(String transactionMasterTotalQuantity) {
        this.transactionMasterTotalQuantity = transactionMasterTotalQuantity;
    }

    public String getTransactionMasterTax() {
        return transactionMasterTax;
    }

    public void setTransactionMasterTax(String transactionMasterTax) {
        this.transactionMasterTax = transactionMasterTax;
    }

    public String getTransactionMasterService() {
        return transactionMasterService;
    }

    public void setTransactionMasterService(String transactionMasterService) {
        this.transactionMasterService = transactionMasterService;
    }

    public String getTransactionMasterDateAndTime() {
        return transactionMasterDateAndTime;
    }

    public void setTransactionMasterDateAndTime(String transactionMasterDateAndTime) {
        this.transactionMasterDateAndTime = transactionMasterDateAndTime;
    }

    public String getTransactionMasterPaymentType() {
        return transactionMasterPaymentType;
    }

    public void setTransactionMasterPaymentType(String transactionMasterPaymentType) {
        this.transactionMasterPaymentType = transactionMasterPaymentType;
    }

    public String getTransactionMasterTendered() {
        return transactionMasterTendered;
    }

    public void setTransactionMasterTendered(String transactionMasterTendered) {
        this.transactionMasterTendered = transactionMasterTendered;
    }

    public String getTransactionMasterChange() {
        return transactionMasterChange;
    }

    public void setTransactionMasterChange(String transactionMasterChange) {
        this.transactionMasterChange = transactionMasterChange;
    }

    public String getTransactionMasterDiscount() {
        return transactionMasterDiscount;
    }

    public void setTransactionMasterDiscount(String transactionMasterDiscount) {
        this.transactionMasterDiscount = transactionMasterDiscount;
    }

    public String getTransactionMasterSubTotal() {
        return transactionMasterSubTotal;
    }

    public void setTransactionMasterSubTotal(String transactionMasterSubTotal) {
        this.transactionMasterSubTotal = transactionMasterSubTotal;
    }

    public String getTransactionMasterEmail() {
        return transactionMasterEmail;
    }

    public void setTransactionMasterEmail(String transactionMasterEmail) {
        this.transactionMasterEmail = transactionMasterEmail;
    }

    public String getTransactionMasterDate() {
        return transactionMasterDate;
    }

    public void setTransactionMasterDate(String transactionMasterDate) {
        this.transactionMasterDate = transactionMasterDate;
    }

    public String getTransactionMasterUserID() {
        return transactionMasterUserID;
    }

    public void setTransactionMasterUserID(String transactionMasterUserID) {
        this.transactionMasterUserID = transactionMasterUserID;
    }

    public String getTransactionMasterTableNumber() {
        return transactionMasterTableNumber;
    }

    public void setTransactionMasterTableNumber(String transactionMasterTableNumber) {
        this.transactionMasterTableNumber = transactionMasterTableNumber;
    }
}

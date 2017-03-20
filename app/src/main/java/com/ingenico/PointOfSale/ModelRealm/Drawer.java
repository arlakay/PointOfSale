package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 7/26/2016.
 */
public class Drawer extends RealmObject {
    private long drawerID;
    private String drawerOutletID;
    private String drawerCashierID;
    private String drawerCashierName;
    private String drawerStartingDateAndTime;
    private String drawerStartingCash;
    private String drawerCashSales;
    private String drawerCardSales;
    private String drawerExpectationCash;
    private String drawerEndingDateAndTime;
    private String drawerDescription;
    private String drawerActualCash;

    public String getDrawerOutletID() {
        return drawerOutletID;
    }

    public void setDrawerOutletID(String drawerOutletID) {
        this.drawerOutletID = drawerOutletID;
    }

    public String getDrawerCashierID() {
        return drawerCashierID;
    }

    public void setDrawerCashierID(String drawerCashierID) {
        this.drawerCashierID = drawerCashierID;
    }

    public String getDrawerCashierName() {
        return drawerCashierName;
    }

    public void setDrawerCashierName(String drawerCashierName) {
        this.drawerCashierName = drawerCashierName;
    }

    public String getDrawerStartingDateAndTime() {
        return drawerStartingDateAndTime;
    }

    public void setDrawerStartingDateAndTime(String drawerStartingDateAndTime) {
        this.drawerStartingDateAndTime = drawerStartingDateAndTime;
    }

    public String getDrawerStartingCash() {
        return drawerStartingCash;
    }

    public void setDrawerStartingCash(String drawerStartingCash) {
        this.drawerStartingCash = drawerStartingCash;
    }

    public String getDrawerCashSales() {
        return drawerCashSales;
    }

    public void setDrawerCashSales(String drawerCashSales) {
        this.drawerCashSales = drawerCashSales;
    }

    public String getDrawerExpetationCash() {
        return drawerExpectationCash;
    }

    public void setDrawerExpetationCash(String drawerExpetationCash) {
        this.drawerExpectationCash = drawerExpetationCash;
    }

    public String getDrawerEndingDateAndTime() {
        return drawerEndingDateAndTime;
    }

    public void setDrawerEndingDateAndTime(String drawerEndingDateAndTime) {
        this.drawerEndingDateAndTime = drawerEndingDateAndTime;
    }

    public String getDrawerDescription() {
        return drawerDescription;
    }

    public void setDrawerDescription(String drawerDescription) {
        this.drawerDescription = drawerDescription;
    }

    public String getDrawerActualCash() {
        return drawerActualCash;
    }

    public void setDrawerActualCash(String drawerActualCash) {
        this.drawerActualCash = drawerActualCash;
    }

    public long getDrawerID() {
        return drawerID;
    }

    public void setDrawerID(long drawerID) {
        this.drawerID = drawerID;
    }

    public String getDrawerCardSales() {
        return drawerCardSales;
    }

    public void setDrawerCardSales(String drawerCardSales) {
        this.drawerCardSales = drawerCardSales;
    }
}

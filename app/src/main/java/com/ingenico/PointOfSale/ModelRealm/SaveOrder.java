package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 6/24/2016.
 */
public class SaveOrder extends RealmObject {
    private String saveOrderTransactionID;
    private String saveOrderNumberTable;
    private String saveOrderIsBeingSelected;

    public String getSaveOrderIsBeingSelected() {
        return saveOrderIsBeingSelected;
    }

    public void setSaveOrderIsBeingSelected(String saveOrderIsBeingSelected) {
        this.saveOrderIsBeingSelected = saveOrderIsBeingSelected;
    }


    public String getSaveOrderNumberTable() {
        return saveOrderNumberTable;
    }

    public void setSaveOrderNumberTable(String saveOrderNumberTable) {
        this.saveOrderNumberTable = saveOrderNumberTable;
    }

    public String getSaveOrderTransactionID() {
        return saveOrderTransactionID;
    }

    public void setSaveOrderTransactionID(String saveOrderTransactionID) {
        this.saveOrderTransactionID = saveOrderTransactionID;
    }

}

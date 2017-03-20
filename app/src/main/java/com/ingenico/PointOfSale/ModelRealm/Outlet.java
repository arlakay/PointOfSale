package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 5/2/2016.
 */
public class Outlet extends RealmObject {

    private long realmOutletID;
    private String outletID;
    private String outletName;
    private String outletAddress;
    private String outletPhone;
    private String outletTable;

    public void setRealmOutletID(long realmOutletID) {
        this.realmOutletID = realmOutletID;
    }

    public void setOutletID(String outletID) {
        this.outletID = outletID;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public void setOutletAddress(String outletAddress) {
        this.outletAddress = outletAddress;
    }

    public void setOutletPhone(String outletPhone) {
        this.outletPhone = outletPhone;
    }

    public long getRealmOutletID() {
        return realmOutletID;
    }

    public String getOutletID() {
        return outletID;
    }

    public String getOutletName() {
        return outletName;
    }

    public String getOutletAddress() {
        return outletAddress;
    }

    public String getOutletPhone() {
        return outletPhone;
    }

    public String getOutletTable() {
        return outletTable;
    }

    public void setOutletTable(String outletTable) {
        this.outletTable = outletTable;
    }
}

package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 6/10/2016.
 */
public class Service extends RealmObject {
    private long realmServiceID;
    private String serviceOutletID;
    private String serviceValue;

    public void setRealmServiceID(long realmServiceID) {
        this.realmServiceID = realmServiceID;
    }

    public void setServiceOutletID(String serviceOutletID) {
        this.serviceOutletID = serviceOutletID;
    }

    public void setServiceValue(String serviceValue) {
        this.serviceValue = serviceValue;
    }

    public long getRealmServiceID() {
        return realmServiceID;
    }

    public String getServiceOutletID() {
        return serviceOutletID;
    }

    public String getServiceValue() {
        return serviceValue;
    }
}

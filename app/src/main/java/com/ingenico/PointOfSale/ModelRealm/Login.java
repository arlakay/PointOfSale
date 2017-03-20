package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 4/29/2016.
 */
public class Login extends RealmObject {
    private long realmLoginID;
    private String username;
    private String userPassword;
    private String userPrivilege;
    private String merchantID;
    private String outletID;
    private String counter;
    private String pictureUser;

    public void setRealmLoginID(long realmLoginID) {
        this.realmLoginID = realmLoginID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserPrivilege(String userPrivilege) {
        this.userPrivilege = userPrivilege;
    }

    public void setOutletID(String outletID) {
        this.outletID = outletID;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public void setPictureUser(String pictureUser) {
        this.pictureUser = pictureUser;
    }

    public long getRealmLoginID() {
        return realmLoginID;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserPrivilege() {
        return userPrivilege;
    }

    public String getOutletID() {
        return outletID;
    }

    public String getCounter() {
        return counter;
    }

    public String getPictureUser() {
        return pictureUser;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }
}

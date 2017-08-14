package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 5/2/2016.
 */
public class Merchant extends RealmObject {

    private long realmMerchantID;
    private String merchantID;
    private String merchantName;
    private String merchantAddress;
    private String merhantPhone;

    public void setRealmMerchantID(long realmMerchantID) {
        this.realmMerchantID = realmMerchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public void setMerhantPhone(String merhantPhone) {
        this.merhantPhone = merhantPhone;
    }

    public long getRealmMerchantID() {
        return realmMerchantID;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public String getMerhantPhone() {
        return merhantPhone;
    }


}

package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 5/2/2016.
 */
public class Category extends RealmObject {

    private long realmCategoryID;
    private String category;

    public void setRealmCategoryID(long realmCategoryID) {
        this.realmCategoryID = realmCategoryID;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getRealmCategoryID() {
        return realmCategoryID;
    }

    public String getCategory() {
        return category;
    }
}

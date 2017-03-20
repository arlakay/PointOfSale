package com.ingenico.PointOfSale.ModelRealm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Administrator-Handy on 5/4/2016.
 */
public class Item extends RealmObject{
    private long realmItemID;
    private String itemID;
    private String itemName;
    private String itemPrice;
    private String itemDiscount;
    private String itemPicture; //not used
    private String itemCategory;
    private String itemStatusTax;
    public RealmList<ItemVariant> itemVarian; //variant that will show in dialog
    public RealmList<ItemModifierGroup> itemModifier; //modifier group that will show in dialog
    private String itemOutletID;


    public void setRealmItemID(long realmItemID) {
        this.realmItemID = realmItemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemPicture(String itemPicture) {
        this.itemPicture = itemPicture;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public void setItemDiscount(String itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public void setItemStatusTax(String itemStatusTax) {
        this.itemStatusTax = itemStatusTax;
    }

    public void setItemVarian(RealmList<ItemVariant> itemVarian) {
        this.itemVarian = itemVarian;
    }

    public void setItemModifier(RealmList<ItemModifierGroup> itemModifier) {
        this.itemModifier = itemModifier;
    }

    public void setItemOutletID(String itemOutletID) {
        this.itemOutletID = itemOutletID;
    }

    public long getRealmItemID() {
        return realmItemID;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public String getItemDiscount() {
        return itemDiscount;
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPicture() {
        return itemPicture;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getItemStatusTax() {
        return itemStatusTax;
    }

    public RealmList<ItemVariant> getItemVarian() {
        return itemVarian;
    }

    public RealmList<ItemModifierGroup> getItemModifier() {
        return itemModifier;
    }

    public String getItemOutletID() {
        return itemOutletID;
    }
}


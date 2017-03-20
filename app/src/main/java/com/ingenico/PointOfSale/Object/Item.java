package com.ingenico.PointOfSale.Object;

import java.util.LinkedList;

/**
 * Created by Administrator-Handy on 1/4/2017.
 */
public class Item {
    private String itemID;
    private String itemName;
    private String itemPrice;
    private String itemDiscount;
    private String itemPicture; //not used
    private String itemCategory;
    private String itemStatusTax;
    private LinkedList<String> itemVarian; //variant that will show in dialog
    private LinkedList<String> itemModifier; //modifier group that will show in dialog


    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(String itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public String getItemPicture() {
        return itemPicture;
    }

    public void setItemPicture(String itemPicture) {
        this.itemPicture = itemPicture;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemStatusTax() {
        return itemStatusTax;
    }

    public void setItemStatusTax(String itemStatusTax) {
        this.itemStatusTax = itemStatusTax;
    }

    public LinkedList<String> getItemVarian() {
        return itemVarian;
    }

    public void setItemVarian(LinkedList<String> itemVarian) {
        this.itemVarian = itemVarian;
    }

    public LinkedList<String> getItemModifier() {
        return itemModifier;
    }

    public void setItemModifier(LinkedList<String> itemModifier) {
        this.itemModifier = itemModifier;
    }

    public String getItemOutletID() {
        return itemOutletID;
    }

    public void setItemOutletID(String itemOutletID) {
        this.itemOutletID = itemOutletID;
    }

    private String itemOutletID;

}

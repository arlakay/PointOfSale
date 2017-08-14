package com.ingenico.PointOfSale.Object;

import java.util.LinkedList;

/**
 * Created by Administrator-Handy on 10/27/2016.
 */

public class ItemBill {
    private String itemBillName; // item transaction
    private String itemBillVariant;
    private LinkedList<String> itemBillModifier;
    private String itemBillQuantity;
    private String itemBillPrice;

    public void setItemBillModifier(LinkedList<String> itemBillModifier) {
        this.itemBillModifier = itemBillModifier;
    }

    public void setItemBillName(String itemBillName) {
        this.itemBillName = itemBillName;
    }

    public void setItemBillPrice(String itemBillPrice) {
        this.itemBillPrice = itemBillPrice;
    }

    public void setItemBillQuantity(String itemBillQuantity) {
        this.itemBillQuantity = itemBillQuantity;
    }

    public void setItemBillVariant(String itemBillVariant) {
        this.itemBillVariant = itemBillVariant;
    }

    public LinkedList<String> getItemBillModifier() {
        return itemBillModifier;
    }

    public String getItemBillName() {
        return itemBillName;
    }

    public String getItemBillPrice() {
        return itemBillPrice;
    }

    public String getItemBillQuantity() {
        return itemBillQuantity;
    }

    public String getItemBillVariant() {
        return itemBillVariant;
    }
}

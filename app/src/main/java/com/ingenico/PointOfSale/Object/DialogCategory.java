package com.ingenico.PointOfSale.Object;

/**
 * Created by Administrator-Handy on 1/31/2017.
 */

public class DialogCategory {
    private String ImageUrl;
    private String ItemID;
    private String ItemName;
    private String CategoryName;

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}

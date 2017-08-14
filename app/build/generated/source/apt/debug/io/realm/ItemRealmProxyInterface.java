package io.realm;


public interface ItemRealmProxyInterface {
    public long realmGet$realmItemID();
    public void realmSet$realmItemID(long value);
    public String realmGet$itemID();
    public void realmSet$itemID(String value);
    public String realmGet$itemName();
    public void realmSet$itemName(String value);
    public String realmGet$itemPrice();
    public void realmSet$itemPrice(String value);
    public String realmGet$itemDiscount();
    public void realmSet$itemDiscount(String value);
    public String realmGet$itemPicture();
    public void realmSet$itemPicture(String value);
    public String realmGet$itemCategory();
    public void realmSet$itemCategory(String value);
    public String realmGet$itemStatusTax();
    public void realmSet$itemStatusTax(String value);
    public RealmList<com.ingenico.PointOfSale.ModelRealm.ItemVariant> realmGet$itemVarian();
    public void realmSet$itemVarian(RealmList<com.ingenico.PointOfSale.ModelRealm.ItemVariant> value);
    public RealmList<com.ingenico.PointOfSale.ModelRealm.ItemModifierGroup> realmGet$itemModifier();
    public void realmSet$itemModifier(RealmList<com.ingenico.PointOfSale.ModelRealm.ItemModifierGroup> value);
    public String realmGet$itemOutletID();
    public void realmSet$itemOutletID(String value);
}

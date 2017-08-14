package io.realm;


public interface TransactionDetailRealmProxyInterface {
    public String realmGet$transactionDetailID();
    public void realmSet$transactionDetailID(String value);
    public String realmGet$transactionDetailItemID();
    public void realmSet$transactionDetailItemID(String value);
    public String realmGet$transactionDetailVariantID();
    public void realmSet$transactionDetailVariantID(String value);
    public RealmList<com.ingenico.PointOfSale.ModelRealm.TransactionDetailModifier> realmGet$transactionDetailModifierID();
    public void realmSet$transactionDetailModifierID(RealmList<com.ingenico.PointOfSale.ModelRealm.TransactionDetailModifier> value);
    public String realmGet$transactionDetailTotalPrice();
    public void realmSet$transactionDetailTotalPrice(String value);
    public RealmList<com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount> realmGet$transactionDetailDiscountID();
    public void realmSet$transactionDetailDiscountID(RealmList<com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount> value);
    public String realmGet$transactionDetailQuantity();
    public void realmSet$transactionDetailQuantity(String value);
}

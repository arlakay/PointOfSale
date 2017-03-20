package io.realm;


public interface TransactionDetailTemporaryRealmProxyInterface {
    public String realmGet$transactionDetailTemporaryID();
    public void realmSet$transactionDetailTemporaryID(String value);
    public String realmGet$transactionDetailTemporaryItemID();
    public void realmSet$transactionDetailTemporaryItemID(String value);
    public String realmGet$transactionDetailTemporaryVariantID();
    public void realmSet$transactionDetailTemporaryVariantID(String value);
    public RealmList<com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryModifier> realmGet$transactionDetailTemporaryModifierID();
    public void realmSet$transactionDetailTemporaryModifierID(RealmList<com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryModifier> value);
    public String realmGet$transactionDetailTemporaryTotalPrice();
    public void realmSet$transactionDetailTemporaryTotalPrice(String value);
    public RealmList<com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryDiscount> realmGet$transactionDetailTemporaryDiscountID();
    public void realmSet$transactionDetailTemporaryDiscountID(RealmList<com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryDiscount> value);
    public String realmGet$transactionDetailTemporaryQuantity();
    public void realmSet$transactionDetailTemporaryQuantity(String value);
}

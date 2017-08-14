package io.realm;


import android.util.JsonReader;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import com.ingenico.PointOfSale.ModelRealm.Category;
import com.ingenico.PointOfSale.ModelRealm.DiscountMaster;
import com.ingenico.PointOfSale.ModelRealm.Drawer;
import com.ingenico.PointOfSale.ModelRealm.Item;
import com.ingenico.PointOfSale.ModelRealm.ItemModifierGroup;
import com.ingenico.PointOfSale.ModelRealm.ItemVariant;
import com.ingenico.PointOfSale.ModelRealm.Login;
import com.ingenico.PointOfSale.ModelRealm.Merchant;
import com.ingenico.PointOfSale.ModelRealm.ModifierDetail;
import com.ingenico.PointOfSale.ModelRealm.ModifierGroup;
import com.ingenico.PointOfSale.ModelRealm.Outlet;
import com.ingenico.PointOfSale.ModelRealm.SaveOrder;
import com.ingenico.PointOfSale.ModelRealm.Service;
import com.ingenico.PointOfSale.ModelRealm.Tax;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailModifier;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporary;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryDiscount;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryModifier;
import com.ingenico.PointOfSale.ModelRealm.TransactionMaster;
import com.ingenico.PointOfSale.ModelRealm.Variant;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>();
        modelClasses.add(ModifierGroup.class);
        modelClasses.add(ModifierDetail.class);
        modelClasses.add(Tax.class);
        modelClasses.add(DiscountMaster.class);
        modelClasses.add(ItemVariant.class);
        modelClasses.add(SaveOrder.class);
        modelClasses.add(TransactionMaster.class);
        modelClasses.add(ItemModifierGroup.class);
        modelClasses.add(Service.class);
        modelClasses.add(TransactionDetailModifier.class);
        modelClasses.add(TransactionDetailTemporary.class);
        modelClasses.add(TransactionDetailTemporaryModifier.class);
        modelClasses.add(TransactionDetail.class);
        modelClasses.add(TransactionDetailDiscount.class);
        modelClasses.add(Login.class);
        modelClasses.add(Category.class);
        modelClasses.add(Merchant.class);
        modelClasses.add(Outlet.class);
        modelClasses.add(Item.class);
        modelClasses.add(Variant.class);
        modelClasses.add(TransactionDetailTemporaryDiscount.class);
        modelClasses.add(Drawer.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Table createTable(Class<? extends RealmModel> clazz, ImplicitTransaction transaction) {
        checkClass(clazz);

        if (clazz.equals(ModifierGroup.class)) {
            return ModifierGroupRealmProxy.initTable(transaction);
        } else if (clazz.equals(ModifierDetail.class)) {
            return ModifierDetailRealmProxy.initTable(transaction);
        } else if (clazz.equals(Tax.class)) {
            return TaxRealmProxy.initTable(transaction);
        } else if (clazz.equals(DiscountMaster.class)) {
            return DiscountMasterRealmProxy.initTable(transaction);
        } else if (clazz.equals(ItemVariant.class)) {
            return ItemVariantRealmProxy.initTable(transaction);
        } else if (clazz.equals(SaveOrder.class)) {
            return SaveOrderRealmProxy.initTable(transaction);
        } else if (clazz.equals(TransactionMaster.class)) {
            return TransactionMasterRealmProxy.initTable(transaction);
        } else if (clazz.equals(ItemModifierGroup.class)) {
            return ItemModifierGroupRealmProxy.initTable(transaction);
        } else if (clazz.equals(Service.class)) {
            return ServiceRealmProxy.initTable(transaction);
        } else if (clazz.equals(TransactionDetailModifier.class)) {
            return TransactionDetailModifierRealmProxy.initTable(transaction);
        } else if (clazz.equals(TransactionDetailTemporary.class)) {
            return TransactionDetailTemporaryRealmProxy.initTable(transaction);
        } else if (clazz.equals(TransactionDetailTemporaryModifier.class)) {
            return TransactionDetailTemporaryModifierRealmProxy.initTable(transaction);
        } else if (clazz.equals(TransactionDetail.class)) {
            return TransactionDetailRealmProxy.initTable(transaction);
        } else if (clazz.equals(TransactionDetailDiscount.class)) {
            return TransactionDetailDiscountRealmProxy.initTable(transaction);
        } else if (clazz.equals(Login.class)) {
            return LoginRealmProxy.initTable(transaction);
        } else if (clazz.equals(Category.class)) {
            return CategoryRealmProxy.initTable(transaction);
        } else if (clazz.equals(Merchant.class)) {
            return MerchantRealmProxy.initTable(transaction);
        } else if (clazz.equals(Outlet.class)) {
            return OutletRealmProxy.initTable(transaction);
        } else if (clazz.equals(Item.class)) {
            return ItemRealmProxy.initTable(transaction);
        } else if (clazz.equals(Variant.class)) {
            return VariantRealmProxy.initTable(transaction);
        } else if (clazz.equals(TransactionDetailTemporaryDiscount.class)) {
            return TransactionDetailTemporaryDiscountRealmProxy.initTable(transaction);
        } else if (clazz.equals(Drawer.class)) {
            return DrawerRealmProxy.initTable(transaction);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public ColumnInfo validateTable(Class<? extends RealmModel> clazz, ImplicitTransaction transaction) {
        checkClass(clazz);

        if (clazz.equals(ModifierGroup.class)) {
            return ModifierGroupRealmProxy.validateTable(transaction);
        } else if (clazz.equals(ModifierDetail.class)) {
            return ModifierDetailRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Tax.class)) {
            return TaxRealmProxy.validateTable(transaction);
        } else if (clazz.equals(DiscountMaster.class)) {
            return DiscountMasterRealmProxy.validateTable(transaction);
        } else if (clazz.equals(ItemVariant.class)) {
            return ItemVariantRealmProxy.validateTable(transaction);
        } else if (clazz.equals(SaveOrder.class)) {
            return SaveOrderRealmProxy.validateTable(transaction);
        } else if (clazz.equals(TransactionMaster.class)) {
            return TransactionMasterRealmProxy.validateTable(transaction);
        } else if (clazz.equals(ItemModifierGroup.class)) {
            return ItemModifierGroupRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Service.class)) {
            return ServiceRealmProxy.validateTable(transaction);
        } else if (clazz.equals(TransactionDetailModifier.class)) {
            return TransactionDetailModifierRealmProxy.validateTable(transaction);
        } else if (clazz.equals(TransactionDetailTemporary.class)) {
            return TransactionDetailTemporaryRealmProxy.validateTable(transaction);
        } else if (clazz.equals(TransactionDetailTemporaryModifier.class)) {
            return TransactionDetailTemporaryModifierRealmProxy.validateTable(transaction);
        } else if (clazz.equals(TransactionDetail.class)) {
            return TransactionDetailRealmProxy.validateTable(transaction);
        } else if (clazz.equals(TransactionDetailDiscount.class)) {
            return TransactionDetailDiscountRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Login.class)) {
            return LoginRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Category.class)) {
            return CategoryRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Merchant.class)) {
            return MerchantRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Outlet.class)) {
            return OutletRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Item.class)) {
            return ItemRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Variant.class)) {
            return VariantRealmProxy.validateTable(transaction);
        } else if (clazz.equals(TransactionDetailTemporaryDiscount.class)) {
            return TransactionDetailTemporaryDiscountRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Drawer.class)) {
            return DrawerRealmProxy.validateTable(transaction);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(ModifierGroup.class)) {
            return ModifierGroupRealmProxy.getFieldNames();
        } else if (clazz.equals(ModifierDetail.class)) {
            return ModifierDetailRealmProxy.getFieldNames();
        } else if (clazz.equals(Tax.class)) {
            return TaxRealmProxy.getFieldNames();
        } else if (clazz.equals(DiscountMaster.class)) {
            return DiscountMasterRealmProxy.getFieldNames();
        } else if (clazz.equals(ItemVariant.class)) {
            return ItemVariantRealmProxy.getFieldNames();
        } else if (clazz.equals(SaveOrder.class)) {
            return SaveOrderRealmProxy.getFieldNames();
        } else if (clazz.equals(TransactionMaster.class)) {
            return TransactionMasterRealmProxy.getFieldNames();
        } else if (clazz.equals(ItemModifierGroup.class)) {
            return ItemModifierGroupRealmProxy.getFieldNames();
        } else if (clazz.equals(Service.class)) {
            return ServiceRealmProxy.getFieldNames();
        } else if (clazz.equals(TransactionDetailModifier.class)) {
            return TransactionDetailModifierRealmProxy.getFieldNames();
        } else if (clazz.equals(TransactionDetailTemporary.class)) {
            return TransactionDetailTemporaryRealmProxy.getFieldNames();
        } else if (clazz.equals(TransactionDetailTemporaryModifier.class)) {
            return TransactionDetailTemporaryModifierRealmProxy.getFieldNames();
        } else if (clazz.equals(TransactionDetail.class)) {
            return TransactionDetailRealmProxy.getFieldNames();
        } else if (clazz.equals(TransactionDetailDiscount.class)) {
            return TransactionDetailDiscountRealmProxy.getFieldNames();
        } else if (clazz.equals(Login.class)) {
            return LoginRealmProxy.getFieldNames();
        } else if (clazz.equals(Category.class)) {
            return CategoryRealmProxy.getFieldNames();
        } else if (clazz.equals(Merchant.class)) {
            return MerchantRealmProxy.getFieldNames();
        } else if (clazz.equals(Outlet.class)) {
            return OutletRealmProxy.getFieldNames();
        } else if (clazz.equals(Item.class)) {
            return ItemRealmProxy.getFieldNames();
        } else if (clazz.equals(Variant.class)) {
            return VariantRealmProxy.getFieldNames();
        } else if (clazz.equals(TransactionDetailTemporaryDiscount.class)) {
            return TransactionDetailTemporaryDiscountRealmProxy.getFieldNames();
        } else if (clazz.equals(Drawer.class)) {
            return DrawerRealmProxy.getFieldNames();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public String getTableName(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(ModifierGroup.class)) {
            return ModifierGroupRealmProxy.getTableName();
        } else if (clazz.equals(ModifierDetail.class)) {
            return ModifierDetailRealmProxy.getTableName();
        } else if (clazz.equals(Tax.class)) {
            return TaxRealmProxy.getTableName();
        } else if (clazz.equals(DiscountMaster.class)) {
            return DiscountMasterRealmProxy.getTableName();
        } else if (clazz.equals(ItemVariant.class)) {
            return ItemVariantRealmProxy.getTableName();
        } else if (clazz.equals(SaveOrder.class)) {
            return SaveOrderRealmProxy.getTableName();
        } else if (clazz.equals(TransactionMaster.class)) {
            return TransactionMasterRealmProxy.getTableName();
        } else if (clazz.equals(ItemModifierGroup.class)) {
            return ItemModifierGroupRealmProxy.getTableName();
        } else if (clazz.equals(Service.class)) {
            return ServiceRealmProxy.getTableName();
        } else if (clazz.equals(TransactionDetailModifier.class)) {
            return TransactionDetailModifierRealmProxy.getTableName();
        } else if (clazz.equals(TransactionDetailTemporary.class)) {
            return TransactionDetailTemporaryRealmProxy.getTableName();
        } else if (clazz.equals(TransactionDetailTemporaryModifier.class)) {
            return TransactionDetailTemporaryModifierRealmProxy.getTableName();
        } else if (clazz.equals(TransactionDetail.class)) {
            return TransactionDetailRealmProxy.getTableName();
        } else if (clazz.equals(TransactionDetailDiscount.class)) {
            return TransactionDetailDiscountRealmProxy.getTableName();
        } else if (clazz.equals(Login.class)) {
            return LoginRealmProxy.getTableName();
        } else if (clazz.equals(Category.class)) {
            return CategoryRealmProxy.getTableName();
        } else if (clazz.equals(Merchant.class)) {
            return MerchantRealmProxy.getTableName();
        } else if (clazz.equals(Outlet.class)) {
            return OutletRealmProxy.getTableName();
        } else if (clazz.equals(Item.class)) {
            return ItemRealmProxy.getTableName();
        } else if (clazz.equals(Variant.class)) {
            return VariantRealmProxy.getTableName();
        } else if (clazz.equals(TransactionDetailTemporaryDiscount.class)) {
            return TransactionDetailTemporaryDiscountRealmProxy.getTableName();
        } else if (clazz.equals(Drawer.class)) {
            return DrawerRealmProxy.getTableName();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, ColumnInfo columnInfo) {
        checkClass(clazz);

        if (clazz.equals(ModifierGroup.class)) {
            return clazz.cast(new ModifierGroupRealmProxy(columnInfo));
        } else if (clazz.equals(ModifierDetail.class)) {
            return clazz.cast(new ModifierDetailRealmProxy(columnInfo));
        } else if (clazz.equals(Tax.class)) {
            return clazz.cast(new TaxRealmProxy(columnInfo));
        } else if (clazz.equals(DiscountMaster.class)) {
            return clazz.cast(new DiscountMasterRealmProxy(columnInfo));
        } else if (clazz.equals(ItemVariant.class)) {
            return clazz.cast(new ItemVariantRealmProxy(columnInfo));
        } else if (clazz.equals(SaveOrder.class)) {
            return clazz.cast(new SaveOrderRealmProxy(columnInfo));
        } else if (clazz.equals(TransactionMaster.class)) {
            return clazz.cast(new TransactionMasterRealmProxy(columnInfo));
        } else if (clazz.equals(ItemModifierGroup.class)) {
            return clazz.cast(new ItemModifierGroupRealmProxy(columnInfo));
        } else if (clazz.equals(Service.class)) {
            return clazz.cast(new ServiceRealmProxy(columnInfo));
        } else if (clazz.equals(TransactionDetailModifier.class)) {
            return clazz.cast(new TransactionDetailModifierRealmProxy(columnInfo));
        } else if (clazz.equals(TransactionDetailTemporary.class)) {
            return clazz.cast(new TransactionDetailTemporaryRealmProxy(columnInfo));
        } else if (clazz.equals(TransactionDetailTemporaryModifier.class)) {
            return clazz.cast(new TransactionDetailTemporaryModifierRealmProxy(columnInfo));
        } else if (clazz.equals(TransactionDetail.class)) {
            return clazz.cast(new TransactionDetailRealmProxy(columnInfo));
        } else if (clazz.equals(TransactionDetailDiscount.class)) {
            return clazz.cast(new TransactionDetailDiscountRealmProxy(columnInfo));
        } else if (clazz.equals(Login.class)) {
            return clazz.cast(new LoginRealmProxy(columnInfo));
        } else if (clazz.equals(Category.class)) {
            return clazz.cast(new CategoryRealmProxy(columnInfo));
        } else if (clazz.equals(Merchant.class)) {
            return clazz.cast(new MerchantRealmProxy(columnInfo));
        } else if (clazz.equals(Outlet.class)) {
            return clazz.cast(new OutletRealmProxy(columnInfo));
        } else if (clazz.equals(Item.class)) {
            return clazz.cast(new ItemRealmProxy(columnInfo));
        } else if (clazz.equals(Variant.class)) {
            return clazz.cast(new VariantRealmProxy(columnInfo));
        } else if (clazz.equals(TransactionDetailTemporaryDiscount.class)) {
            return clazz.cast(new TransactionDetailTemporaryDiscountRealmProxy(columnInfo));
        } else if (clazz.equals(Drawer.class)) {
            return clazz.cast(new DrawerRealmProxy(columnInfo));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(ModifierGroup.class)) {
            return clazz.cast(ModifierGroupRealmProxy.copyOrUpdate(realm, (ModifierGroup) obj, update, cache));
        } else if (clazz.equals(ModifierDetail.class)) {
            return clazz.cast(ModifierDetailRealmProxy.copyOrUpdate(realm, (ModifierDetail) obj, update, cache));
        } else if (clazz.equals(Tax.class)) {
            return clazz.cast(TaxRealmProxy.copyOrUpdate(realm, (Tax) obj, update, cache));
        } else if (clazz.equals(DiscountMaster.class)) {
            return clazz.cast(DiscountMasterRealmProxy.copyOrUpdate(realm, (DiscountMaster) obj, update, cache));
        } else if (clazz.equals(ItemVariant.class)) {
            return clazz.cast(ItemVariantRealmProxy.copyOrUpdate(realm, (ItemVariant) obj, update, cache));
        } else if (clazz.equals(SaveOrder.class)) {
            return clazz.cast(SaveOrderRealmProxy.copyOrUpdate(realm, (SaveOrder) obj, update, cache));
        } else if (clazz.equals(TransactionMaster.class)) {
            return clazz.cast(TransactionMasterRealmProxy.copyOrUpdate(realm, (TransactionMaster) obj, update, cache));
        } else if (clazz.equals(ItemModifierGroup.class)) {
            return clazz.cast(ItemModifierGroupRealmProxy.copyOrUpdate(realm, (ItemModifierGroup) obj, update, cache));
        } else if (clazz.equals(Service.class)) {
            return clazz.cast(ServiceRealmProxy.copyOrUpdate(realm, (Service) obj, update, cache));
        } else if (clazz.equals(TransactionDetailModifier.class)) {
            return clazz.cast(TransactionDetailModifierRealmProxy.copyOrUpdate(realm, (TransactionDetailModifier) obj, update, cache));
        } else if (clazz.equals(TransactionDetailTemporary.class)) {
            return clazz.cast(TransactionDetailTemporaryRealmProxy.copyOrUpdate(realm, (TransactionDetailTemporary) obj, update, cache));
        } else if (clazz.equals(TransactionDetailTemporaryModifier.class)) {
            return clazz.cast(TransactionDetailTemporaryModifierRealmProxy.copyOrUpdate(realm, (TransactionDetailTemporaryModifier) obj, update, cache));
        } else if (clazz.equals(TransactionDetail.class)) {
            return clazz.cast(TransactionDetailRealmProxy.copyOrUpdate(realm, (TransactionDetail) obj, update, cache));
        } else if (clazz.equals(TransactionDetailDiscount.class)) {
            return clazz.cast(TransactionDetailDiscountRealmProxy.copyOrUpdate(realm, (TransactionDetailDiscount) obj, update, cache));
        } else if (clazz.equals(Login.class)) {
            return clazz.cast(LoginRealmProxy.copyOrUpdate(realm, (Login) obj, update, cache));
        } else if (clazz.equals(Category.class)) {
            return clazz.cast(CategoryRealmProxy.copyOrUpdate(realm, (Category) obj, update, cache));
        } else if (clazz.equals(Merchant.class)) {
            return clazz.cast(MerchantRealmProxy.copyOrUpdate(realm, (Merchant) obj, update, cache));
        } else if (clazz.equals(Outlet.class)) {
            return clazz.cast(OutletRealmProxy.copyOrUpdate(realm, (Outlet) obj, update, cache));
        } else if (clazz.equals(Item.class)) {
            return clazz.cast(ItemRealmProxy.copyOrUpdate(realm, (Item) obj, update, cache));
        } else if (clazz.equals(Variant.class)) {
            return clazz.cast(VariantRealmProxy.copyOrUpdate(realm, (Variant) obj, update, cache));
        } else if (clazz.equals(TransactionDetailTemporaryDiscount.class)) {
            return clazz.cast(TransactionDetailTemporaryDiscountRealmProxy.copyOrUpdate(realm, (TransactionDetailTemporaryDiscount) obj, update, cache));
        } else if (clazz.equals(Drawer.class)) {
            return clazz.cast(DrawerRealmProxy.copyOrUpdate(realm, (Drawer) obj, update, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(ModifierGroup.class)) {
            return clazz.cast(ModifierGroupRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(ModifierDetail.class)) {
            return clazz.cast(ModifierDetailRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(Tax.class)) {
            return clazz.cast(TaxRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(DiscountMaster.class)) {
            return clazz.cast(DiscountMasterRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(ItemVariant.class)) {
            return clazz.cast(ItemVariantRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(SaveOrder.class)) {
            return clazz.cast(SaveOrderRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(TransactionMaster.class)) {
            return clazz.cast(TransactionMasterRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(ItemModifierGroup.class)) {
            return clazz.cast(ItemModifierGroupRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(Service.class)) {
            return clazz.cast(ServiceRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(TransactionDetailModifier.class)) {
            return clazz.cast(TransactionDetailModifierRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(TransactionDetailTemporary.class)) {
            return clazz.cast(TransactionDetailTemporaryRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(TransactionDetailTemporaryModifier.class)) {
            return clazz.cast(TransactionDetailTemporaryModifierRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(TransactionDetail.class)) {
            return clazz.cast(TransactionDetailRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(TransactionDetailDiscount.class)) {
            return clazz.cast(TransactionDetailDiscountRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(Login.class)) {
            return clazz.cast(LoginRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(Category.class)) {
            return clazz.cast(CategoryRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(Merchant.class)) {
            return clazz.cast(MerchantRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(Outlet.class)) {
            return clazz.cast(OutletRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(Item.class)) {
            return clazz.cast(ItemRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(Variant.class)) {
            return clazz.cast(VariantRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(TransactionDetailTemporaryDiscount.class)) {
            return clazz.cast(TransactionDetailTemporaryDiscountRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(Drawer.class)) {
            return clazz.cast(DrawerRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(ModifierGroup.class)) {
            return clazz.cast(ModifierGroupRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(ModifierDetail.class)) {
            return clazz.cast(ModifierDetailRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(Tax.class)) {
            return clazz.cast(TaxRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(DiscountMaster.class)) {
            return clazz.cast(DiscountMasterRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(ItemVariant.class)) {
            return clazz.cast(ItemVariantRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(SaveOrder.class)) {
            return clazz.cast(SaveOrderRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(TransactionMaster.class)) {
            return clazz.cast(TransactionMasterRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(ItemModifierGroup.class)) {
            return clazz.cast(ItemModifierGroupRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(Service.class)) {
            return clazz.cast(ServiceRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(TransactionDetailModifier.class)) {
            return clazz.cast(TransactionDetailModifierRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(TransactionDetailTemporary.class)) {
            return clazz.cast(TransactionDetailTemporaryRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(TransactionDetailTemporaryModifier.class)) {
            return clazz.cast(TransactionDetailTemporaryModifierRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(TransactionDetail.class)) {
            return clazz.cast(TransactionDetailRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(TransactionDetailDiscount.class)) {
            return clazz.cast(TransactionDetailDiscountRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(Login.class)) {
            return clazz.cast(LoginRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(Category.class)) {
            return clazz.cast(CategoryRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(Merchant.class)) {
            return clazz.cast(MerchantRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(Outlet.class)) {
            return clazz.cast(OutletRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(Item.class)) {
            return clazz.cast(ItemRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(Variant.class)) {
            return clazz.cast(VariantRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(TransactionDetailTemporaryDiscount.class)) {
            return clazz.cast(TransactionDetailTemporaryDiscountRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(Drawer.class)) {
            return clazz.cast(DrawerRealmProxy.createUsingJsonStream(realm, reader));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(ModifierGroup.class)) {
            return clazz.cast(ModifierGroupRealmProxy.createDetachedCopy((ModifierGroup) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(ModifierDetail.class)) {
            return clazz.cast(ModifierDetailRealmProxy.createDetachedCopy((ModifierDetail) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(Tax.class)) {
            return clazz.cast(TaxRealmProxy.createDetachedCopy((Tax) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(DiscountMaster.class)) {
            return clazz.cast(DiscountMasterRealmProxy.createDetachedCopy((DiscountMaster) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(ItemVariant.class)) {
            return clazz.cast(ItemVariantRealmProxy.createDetachedCopy((ItemVariant) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(SaveOrder.class)) {
            return clazz.cast(SaveOrderRealmProxy.createDetachedCopy((SaveOrder) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(TransactionMaster.class)) {
            return clazz.cast(TransactionMasterRealmProxy.createDetachedCopy((TransactionMaster) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(ItemModifierGroup.class)) {
            return clazz.cast(ItemModifierGroupRealmProxy.createDetachedCopy((ItemModifierGroup) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(Service.class)) {
            return clazz.cast(ServiceRealmProxy.createDetachedCopy((Service) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(TransactionDetailModifier.class)) {
            return clazz.cast(TransactionDetailModifierRealmProxy.createDetachedCopy((TransactionDetailModifier) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(TransactionDetailTemporary.class)) {
            return clazz.cast(TransactionDetailTemporaryRealmProxy.createDetachedCopy((TransactionDetailTemporary) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(TransactionDetailTemporaryModifier.class)) {
            return clazz.cast(TransactionDetailTemporaryModifierRealmProxy.createDetachedCopy((TransactionDetailTemporaryModifier) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(TransactionDetail.class)) {
            return clazz.cast(TransactionDetailRealmProxy.createDetachedCopy((TransactionDetail) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(TransactionDetailDiscount.class)) {
            return clazz.cast(TransactionDetailDiscountRealmProxy.createDetachedCopy((TransactionDetailDiscount) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(Login.class)) {
            return clazz.cast(LoginRealmProxy.createDetachedCopy((Login) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(Category.class)) {
            return clazz.cast(CategoryRealmProxy.createDetachedCopy((Category) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(Merchant.class)) {
            return clazz.cast(MerchantRealmProxy.createDetachedCopy((Merchant) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(Outlet.class)) {
            return clazz.cast(OutletRealmProxy.createDetachedCopy((Outlet) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(Item.class)) {
            return clazz.cast(ItemRealmProxy.createDetachedCopy((Item) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(Variant.class)) {
            return clazz.cast(VariantRealmProxy.createDetachedCopy((Variant) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(TransactionDetailTemporaryDiscount.class)) {
            return clazz.cast(TransactionDetailTemporaryDiscountRealmProxy.createDetachedCopy((TransactionDetailTemporaryDiscount) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(Drawer.class)) {
            return clazz.cast(DrawerRealmProxy.createDetachedCopy((Drawer) realmObject, 0, maxDepth, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

}

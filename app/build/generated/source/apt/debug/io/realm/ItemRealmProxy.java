package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.Item;
import com.ingenico.PointOfSale.ModelRealm.ItemModifierGroup;
import com.ingenico.PointOfSale.ModelRealm.ItemVariant;
import io.realm.RealmFieldType;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ItemRealmProxy extends Item
    implements RealmObjectProxy, ItemRealmProxyInterface {

    static final class ItemColumnInfo extends ColumnInfo {

        public final long realmItemIDIndex;
        public final long itemIDIndex;
        public final long itemNameIndex;
        public final long itemPriceIndex;
        public final long itemDiscountIndex;
        public final long itemPictureIndex;
        public final long itemCategoryIndex;
        public final long itemStatusTaxIndex;
        public final long itemVarianIndex;
        public final long itemModifierIndex;
        public final long itemOutletIDIndex;

        ItemColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(11);
            this.realmItemIDIndex = getValidColumnIndex(path, table, "Item", "realmItemID");
            indicesMap.put("realmItemID", this.realmItemIDIndex);

            this.itemIDIndex = getValidColumnIndex(path, table, "Item", "itemID");
            indicesMap.put("itemID", this.itemIDIndex);

            this.itemNameIndex = getValidColumnIndex(path, table, "Item", "itemName");
            indicesMap.put("itemName", this.itemNameIndex);

            this.itemPriceIndex = getValidColumnIndex(path, table, "Item", "itemPrice");
            indicesMap.put("itemPrice", this.itemPriceIndex);

            this.itemDiscountIndex = getValidColumnIndex(path, table, "Item", "itemDiscount");
            indicesMap.put("itemDiscount", this.itemDiscountIndex);

            this.itemPictureIndex = getValidColumnIndex(path, table, "Item", "itemPicture");
            indicesMap.put("itemPicture", this.itemPictureIndex);

            this.itemCategoryIndex = getValidColumnIndex(path, table, "Item", "itemCategory");
            indicesMap.put("itemCategory", this.itemCategoryIndex);

            this.itemStatusTaxIndex = getValidColumnIndex(path, table, "Item", "itemStatusTax");
            indicesMap.put("itemStatusTax", this.itemStatusTaxIndex);

            this.itemVarianIndex = getValidColumnIndex(path, table, "Item", "itemVarian");
            indicesMap.put("itemVarian", this.itemVarianIndex);

            this.itemModifierIndex = getValidColumnIndex(path, table, "Item", "itemModifier");
            indicesMap.put("itemModifier", this.itemModifierIndex);

            this.itemOutletIDIndex = getValidColumnIndex(path, table, "Item", "itemOutletID");
            indicesMap.put("itemOutletID", this.itemOutletIDIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final ItemColumnInfo columnInfo;
    private final ProxyState proxyState;
    private RealmList<ItemVariant> itemVarianRealmList;
    private RealmList<ItemModifierGroup> itemModifierRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("realmItemID");
        fieldNames.add("itemID");
        fieldNames.add("itemName");
        fieldNames.add("itemPrice");
        fieldNames.add("itemDiscount");
        fieldNames.add("itemPicture");
        fieldNames.add("itemCategory");
        fieldNames.add("itemStatusTax");
        fieldNames.add("itemVarian");
        fieldNames.add("itemModifier");
        fieldNames.add("itemOutletID");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ItemRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (ItemColumnInfo) columnInfo;
        this.proxyState = new ProxyState(Item.class);
    }

    @SuppressWarnings("cast")
    public long realmGet$realmItemID() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.realmItemIDIndex);
    }

    public void realmSet$realmItemID(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.realmItemIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$itemID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.itemIDIndex);
    }

    public void realmSet$itemID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.itemIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.itemIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$itemName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.itemNameIndex);
    }

    public void realmSet$itemName(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.itemNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.itemNameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$itemPrice() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.itemPriceIndex);
    }

    public void realmSet$itemPrice(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.itemPriceIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.itemPriceIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$itemDiscount() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.itemDiscountIndex);
    }

    public void realmSet$itemDiscount(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.itemDiscountIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.itemDiscountIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$itemPicture() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.itemPictureIndex);
    }

    public void realmSet$itemPicture(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.itemPictureIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.itemPictureIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$itemCategory() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.itemCategoryIndex);
    }

    public void realmSet$itemCategory(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.itemCategoryIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.itemCategoryIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$itemStatusTax() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.itemStatusTaxIndex);
    }

    public void realmSet$itemStatusTax(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.itemStatusTaxIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.itemStatusTaxIndex, value);
    }

    public RealmList<ItemVariant> realmGet$itemVarian() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (itemVarianRealmList != null) {
            return itemVarianRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.itemVarianIndex);
            itemVarianRealmList = new RealmList<ItemVariant>(ItemVariant.class, linkView, proxyState.getRealm$realm());
            return itemVarianRealmList;
        }
    }

    public void realmSet$itemVarian(RealmList<ItemVariant> value) {
        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.itemVarianIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmModel linkedObject : (RealmList<? extends RealmModel>) value) {
            if (!RealmObject.isValid(linkedObject)) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (((RealmObjectProxy)linkedObject).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(((RealmObjectProxy)linkedObject).realmGet$proxyState().getRow$realm().getIndex());
        }
    }

    public RealmList<ItemModifierGroup> realmGet$itemModifier() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (itemModifierRealmList != null) {
            return itemModifierRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.itemModifierIndex);
            itemModifierRealmList = new RealmList<ItemModifierGroup>(ItemModifierGroup.class, linkView, proxyState.getRealm$realm());
            return itemModifierRealmList;
        }
    }

    public void realmSet$itemModifier(RealmList<ItemModifierGroup> value) {
        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.itemModifierIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmModel linkedObject : (RealmList<? extends RealmModel>) value) {
            if (!RealmObject.isValid(linkedObject)) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (((RealmObjectProxy)linkedObject).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(((RealmObjectProxy)linkedObject).realmGet$proxyState().getRow$realm().getIndex());
        }
    }

    @SuppressWarnings("cast")
    public String realmGet$itemOutletID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.itemOutletIDIndex);
    }

    public void realmSet$itemOutletID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.itemOutletIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.itemOutletIDIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Item")) {
            Table table = transaction.getTable("class_Item");
            table.addColumn(RealmFieldType.INTEGER, "realmItemID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "itemID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "itemName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "itemPrice", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "itemDiscount", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "itemPicture", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "itemCategory", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "itemStatusTax", Table.NULLABLE);
            if (!transaction.hasTable("class_ItemVariant")) {
                ItemVariantRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "itemVarian", transaction.getTable("class_ItemVariant"));
            if (!transaction.hasTable("class_ItemModifierGroup")) {
                ItemModifierGroupRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "itemModifier", transaction.getTable("class_ItemModifierGroup"));
            table.addColumn(RealmFieldType.STRING, "itemOutletID", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_Item");
    }

    public static ItemColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Item")) {
            Table table = transaction.getTable("class_Item");
            if (table.getColumnCount() != 11) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 11 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 11; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final ItemColumnInfo columnInfo = new ItemColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("realmItemID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'realmItemID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realmItemID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'realmItemID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.realmItemIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'realmItemID' does support null values in the existing Realm file. Use corresponding boxed type for field 'realmItemID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("itemID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'itemID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("itemID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'itemID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.itemIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'itemID' is required. Either set @Required to field 'itemID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("itemName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'itemName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("itemName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'itemName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.itemNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'itemName' is required. Either set @Required to field 'itemName' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("itemPrice")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'itemPrice' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("itemPrice") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'itemPrice' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.itemPriceIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'itemPrice' is required. Either set @Required to field 'itemPrice' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("itemDiscount")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'itemDiscount' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("itemDiscount") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'itemDiscount' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.itemDiscountIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'itemDiscount' is required. Either set @Required to field 'itemDiscount' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("itemPicture")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'itemPicture' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("itemPicture") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'itemPicture' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.itemPictureIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'itemPicture' is required. Either set @Required to field 'itemPicture' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("itemCategory")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'itemCategory' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("itemCategory") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'itemCategory' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.itemCategoryIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'itemCategory' is required. Either set @Required to field 'itemCategory' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("itemStatusTax")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'itemStatusTax' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("itemStatusTax") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'itemStatusTax' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.itemStatusTaxIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'itemStatusTax' is required. Either set @Required to field 'itemStatusTax' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("itemVarian")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'itemVarian'");
            }
            if (columnTypes.get("itemVarian") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'ItemVariant' for field 'itemVarian'");
            }
            if (!transaction.hasTable("class_ItemVariant")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_ItemVariant' for field 'itemVarian'");
            }
            Table table_8 = transaction.getTable("class_ItemVariant");
            if (!table.getLinkTarget(columnInfo.itemVarianIndex).hasSameSchema(table_8)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'itemVarian': '" + table.getLinkTarget(columnInfo.itemVarianIndex).getName() + "' expected - was '" + table_8.getName() + "'");
            }
            if (!columnTypes.containsKey("itemModifier")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'itemModifier'");
            }
            if (columnTypes.get("itemModifier") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'ItemModifierGroup' for field 'itemModifier'");
            }
            if (!transaction.hasTable("class_ItemModifierGroup")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_ItemModifierGroup' for field 'itemModifier'");
            }
            Table table_9 = transaction.getTable("class_ItemModifierGroup");
            if (!table.getLinkTarget(columnInfo.itemModifierIndex).hasSameSchema(table_9)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'itemModifier': '" + table.getLinkTarget(columnInfo.itemModifierIndex).getName() + "' expected - was '" + table_9.getName() + "'");
            }
            if (!columnTypes.containsKey("itemOutletID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'itemOutletID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("itemOutletID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'itemOutletID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.itemOutletIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'itemOutletID' is required. Either set @Required to field 'itemOutletID' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Item class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Item";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static Item createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        Item obj = realm.createObject(Item.class);
        if (json.has("realmItemID")) {
            if (json.isNull("realmItemID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field realmItemID to null.");
            } else {
                ((ItemRealmProxyInterface) obj).realmSet$realmItemID((long) json.getLong("realmItemID"));
            }
        }
        if (json.has("itemID")) {
            if (json.isNull("itemID")) {
                ((ItemRealmProxyInterface) obj).realmSet$itemID(null);
            } else {
                ((ItemRealmProxyInterface) obj).realmSet$itemID((String) json.getString("itemID"));
            }
        }
        if (json.has("itemName")) {
            if (json.isNull("itemName")) {
                ((ItemRealmProxyInterface) obj).realmSet$itemName(null);
            } else {
                ((ItemRealmProxyInterface) obj).realmSet$itemName((String) json.getString("itemName"));
            }
        }
        if (json.has("itemPrice")) {
            if (json.isNull("itemPrice")) {
                ((ItemRealmProxyInterface) obj).realmSet$itemPrice(null);
            } else {
                ((ItemRealmProxyInterface) obj).realmSet$itemPrice((String) json.getString("itemPrice"));
            }
        }
        if (json.has("itemDiscount")) {
            if (json.isNull("itemDiscount")) {
                ((ItemRealmProxyInterface) obj).realmSet$itemDiscount(null);
            } else {
                ((ItemRealmProxyInterface) obj).realmSet$itemDiscount((String) json.getString("itemDiscount"));
            }
        }
        if (json.has("itemPicture")) {
            if (json.isNull("itemPicture")) {
                ((ItemRealmProxyInterface) obj).realmSet$itemPicture(null);
            } else {
                ((ItemRealmProxyInterface) obj).realmSet$itemPicture((String) json.getString("itemPicture"));
            }
        }
        if (json.has("itemCategory")) {
            if (json.isNull("itemCategory")) {
                ((ItemRealmProxyInterface) obj).realmSet$itemCategory(null);
            } else {
                ((ItemRealmProxyInterface) obj).realmSet$itemCategory((String) json.getString("itemCategory"));
            }
        }
        if (json.has("itemStatusTax")) {
            if (json.isNull("itemStatusTax")) {
                ((ItemRealmProxyInterface) obj).realmSet$itemStatusTax(null);
            } else {
                ((ItemRealmProxyInterface) obj).realmSet$itemStatusTax((String) json.getString("itemStatusTax"));
            }
        }
        if (json.has("itemVarian")) {
            if (json.isNull("itemVarian")) {
                ((ItemRealmProxyInterface) obj).realmSet$itemVarian(null);
            } else {
                ((ItemRealmProxyInterface) obj).realmGet$itemVarian().clear();
                JSONArray array = json.getJSONArray("itemVarian");
                for (int i = 0; i < array.length(); i++) {
                    com.ingenico.PointOfSale.ModelRealm.ItemVariant item = ItemVariantRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((ItemRealmProxyInterface) obj).realmGet$itemVarian().add(item);
                }
            }
        }
        if (json.has("itemModifier")) {
            if (json.isNull("itemModifier")) {
                ((ItemRealmProxyInterface) obj).realmSet$itemModifier(null);
            } else {
                ((ItemRealmProxyInterface) obj).realmGet$itemModifier().clear();
                JSONArray array = json.getJSONArray("itemModifier");
                for (int i = 0; i < array.length(); i++) {
                    com.ingenico.PointOfSale.ModelRealm.ItemModifierGroup item = ItemModifierGroupRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((ItemRealmProxyInterface) obj).realmGet$itemModifier().add(item);
                }
            }
        }
        if (json.has("itemOutletID")) {
            if (json.isNull("itemOutletID")) {
                ((ItemRealmProxyInterface) obj).realmSet$itemOutletID(null);
            } else {
                ((ItemRealmProxyInterface) obj).realmSet$itemOutletID((String) json.getString("itemOutletID"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static Item createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        Item obj = realm.createObject(Item.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("realmItemID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field realmItemID to null.");
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$realmItemID((long) reader.nextLong());
                }
            } else if (name.equals("itemID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemRealmProxyInterface) obj).realmSet$itemID(null);
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$itemID((String) reader.nextString());
                }
            } else if (name.equals("itemName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemRealmProxyInterface) obj).realmSet$itemName(null);
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$itemName((String) reader.nextString());
                }
            } else if (name.equals("itemPrice")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemRealmProxyInterface) obj).realmSet$itemPrice(null);
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$itemPrice((String) reader.nextString());
                }
            } else if (name.equals("itemDiscount")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemRealmProxyInterface) obj).realmSet$itemDiscount(null);
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$itemDiscount((String) reader.nextString());
                }
            } else if (name.equals("itemPicture")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemRealmProxyInterface) obj).realmSet$itemPicture(null);
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$itemPicture((String) reader.nextString());
                }
            } else if (name.equals("itemCategory")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemRealmProxyInterface) obj).realmSet$itemCategory(null);
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$itemCategory((String) reader.nextString());
                }
            } else if (name.equals("itemStatusTax")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemRealmProxyInterface) obj).realmSet$itemStatusTax(null);
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$itemStatusTax((String) reader.nextString());
                }
            } else if (name.equals("itemVarian")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemRealmProxyInterface) obj).realmSet$itemVarian(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.ingenico.PointOfSale.ModelRealm.ItemVariant item = ItemVariantRealmProxy.createUsingJsonStream(realm, reader);
                        ((ItemRealmProxyInterface) obj).realmGet$itemVarian().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("itemModifier")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemRealmProxyInterface) obj).realmSet$itemModifier(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.ingenico.PointOfSale.ModelRealm.ItemModifierGroup item = ItemModifierGroupRealmProxy.createUsingJsonStream(realm, reader);
                        ((ItemRealmProxyInterface) obj).realmGet$itemModifier().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("itemOutletID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemRealmProxyInterface) obj).realmSet$itemOutletID(null);
                } else {
                    ((ItemRealmProxyInterface) obj).realmSet$itemOutletID((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static Item copyOrUpdate(Realm realm, Item object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static Item copy(Realm realm, Item newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        Item realmObject = realm.createObject(Item.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((ItemRealmProxyInterface) realmObject).realmSet$realmItemID(((ItemRealmProxyInterface) newObject).realmGet$realmItemID());
        ((ItemRealmProxyInterface) realmObject).realmSet$itemID(((ItemRealmProxyInterface) newObject).realmGet$itemID());
        ((ItemRealmProxyInterface) realmObject).realmSet$itemName(((ItemRealmProxyInterface) newObject).realmGet$itemName());
        ((ItemRealmProxyInterface) realmObject).realmSet$itemPrice(((ItemRealmProxyInterface) newObject).realmGet$itemPrice());
        ((ItemRealmProxyInterface) realmObject).realmSet$itemDiscount(((ItemRealmProxyInterface) newObject).realmGet$itemDiscount());
        ((ItemRealmProxyInterface) realmObject).realmSet$itemPicture(((ItemRealmProxyInterface) newObject).realmGet$itemPicture());
        ((ItemRealmProxyInterface) realmObject).realmSet$itemCategory(((ItemRealmProxyInterface) newObject).realmGet$itemCategory());
        ((ItemRealmProxyInterface) realmObject).realmSet$itemStatusTax(((ItemRealmProxyInterface) newObject).realmGet$itemStatusTax());

        RealmList<ItemVariant> itemVarianList = ((ItemRealmProxyInterface) newObject).realmGet$itemVarian();
        if (itemVarianList != null) {
            RealmList<ItemVariant> itemVarianRealmList = ((ItemRealmProxyInterface) realmObject).realmGet$itemVarian();
            for (int i = 0; i < itemVarianList.size(); i++) {
                ItemVariant itemVarianItem = itemVarianList.get(i);
                ItemVariant cacheitemVarian = (ItemVariant) cache.get(itemVarianItem);
                if (cacheitemVarian != null) {
                    itemVarianRealmList.add(cacheitemVarian);
                } else {
                    itemVarianRealmList.add(ItemVariantRealmProxy.copyOrUpdate(realm, itemVarianList.get(i), update, cache));
                }
            }
        }


        RealmList<ItemModifierGroup> itemModifierList = ((ItemRealmProxyInterface) newObject).realmGet$itemModifier();
        if (itemModifierList != null) {
            RealmList<ItemModifierGroup> itemModifierRealmList = ((ItemRealmProxyInterface) realmObject).realmGet$itemModifier();
            for (int i = 0; i < itemModifierList.size(); i++) {
                ItemModifierGroup itemModifierItem = itemModifierList.get(i);
                ItemModifierGroup cacheitemModifier = (ItemModifierGroup) cache.get(itemModifierItem);
                if (cacheitemModifier != null) {
                    itemModifierRealmList.add(cacheitemModifier);
                } else {
                    itemModifierRealmList.add(ItemModifierGroupRealmProxy.copyOrUpdate(realm, itemModifierList.get(i), update, cache));
                }
            }
        }

        ((ItemRealmProxyInterface) realmObject).realmSet$itemOutletID(((ItemRealmProxyInterface) newObject).realmGet$itemOutletID());
        return realmObject;
    }

    public static Item createDetachedCopy(Item realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        Item standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (Item)cachedObject.object;
            } else {
                standaloneObject = (Item)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new Item();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((ItemRealmProxyInterface) standaloneObject).realmSet$realmItemID(((ItemRealmProxyInterface) realmObject).realmGet$realmItemID());
        ((ItemRealmProxyInterface) standaloneObject).realmSet$itemID(((ItemRealmProxyInterface) realmObject).realmGet$itemID());
        ((ItemRealmProxyInterface) standaloneObject).realmSet$itemName(((ItemRealmProxyInterface) realmObject).realmGet$itemName());
        ((ItemRealmProxyInterface) standaloneObject).realmSet$itemPrice(((ItemRealmProxyInterface) realmObject).realmGet$itemPrice());
        ((ItemRealmProxyInterface) standaloneObject).realmSet$itemDiscount(((ItemRealmProxyInterface) realmObject).realmGet$itemDiscount());
        ((ItemRealmProxyInterface) standaloneObject).realmSet$itemPicture(((ItemRealmProxyInterface) realmObject).realmGet$itemPicture());
        ((ItemRealmProxyInterface) standaloneObject).realmSet$itemCategory(((ItemRealmProxyInterface) realmObject).realmGet$itemCategory());
        ((ItemRealmProxyInterface) standaloneObject).realmSet$itemStatusTax(((ItemRealmProxyInterface) realmObject).realmGet$itemStatusTax());

        // Deep copy of itemVarian
        if (currentDepth == maxDepth) {
            ((ItemRealmProxyInterface) standaloneObject).realmSet$itemVarian(null);
        } else {
            RealmList<ItemVariant> manageditemVarianList = ((ItemRealmProxyInterface) realmObject).realmGet$itemVarian();
            RealmList<ItemVariant> standaloneitemVarianList = new RealmList<ItemVariant>();
            ((ItemRealmProxyInterface) standaloneObject).realmSet$itemVarian(standaloneitemVarianList);
            int nextDepth = currentDepth + 1;
            int size = manageditemVarianList.size();
            for (int i = 0; i < size; i++) {
                ItemVariant item = ItemVariantRealmProxy.createDetachedCopy(manageditemVarianList.get(i), nextDepth, maxDepth, cache);
                standaloneitemVarianList.add(item);
            }
        }

        // Deep copy of itemModifier
        if (currentDepth == maxDepth) {
            ((ItemRealmProxyInterface) standaloneObject).realmSet$itemModifier(null);
        } else {
            RealmList<ItemModifierGroup> manageditemModifierList = ((ItemRealmProxyInterface) realmObject).realmGet$itemModifier();
            RealmList<ItemModifierGroup> standaloneitemModifierList = new RealmList<ItemModifierGroup>();
            ((ItemRealmProxyInterface) standaloneObject).realmSet$itemModifier(standaloneitemModifierList);
            int nextDepth = currentDepth + 1;
            int size = manageditemModifierList.size();
            for (int i = 0; i < size; i++) {
                ItemModifierGroup item = ItemModifierGroupRealmProxy.createDetachedCopy(manageditemModifierList.get(i), nextDepth, maxDepth, cache);
                standaloneitemModifierList.add(item);
            }
        }
        ((ItemRealmProxyInterface) standaloneObject).realmSet$itemOutletID(((ItemRealmProxyInterface) realmObject).realmGet$itemOutletID());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Item = [");
        stringBuilder.append("{realmItemID:");
        stringBuilder.append(realmGet$realmItemID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemID:");
        stringBuilder.append(realmGet$itemID() != null ? realmGet$itemID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemName:");
        stringBuilder.append(realmGet$itemName() != null ? realmGet$itemName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemPrice:");
        stringBuilder.append(realmGet$itemPrice() != null ? realmGet$itemPrice() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemDiscount:");
        stringBuilder.append(realmGet$itemDiscount() != null ? realmGet$itemDiscount() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemPicture:");
        stringBuilder.append(realmGet$itemPicture() != null ? realmGet$itemPicture() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemCategory:");
        stringBuilder.append(realmGet$itemCategory() != null ? realmGet$itemCategory() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemStatusTax:");
        stringBuilder.append(realmGet$itemStatusTax() != null ? realmGet$itemStatusTax() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemVarian:");
        stringBuilder.append("RealmList<ItemVariant>[").append(realmGet$itemVarian().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemModifier:");
        stringBuilder.append("RealmList<ItemModifierGroup>[").append(realmGet$itemModifier().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemOutletID:");
        stringBuilder.append(realmGet$itemOutletID() != null ? realmGet$itemOutletID() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemRealmProxy aItem = (ItemRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aItem.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aItem.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aItem.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

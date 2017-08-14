package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporary;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryDiscount;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryModifier;
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

public class TransactionDetailTemporaryRealmProxy extends TransactionDetailTemporary
    implements RealmObjectProxy, TransactionDetailTemporaryRealmProxyInterface {

    static final class TransactionDetailTemporaryColumnInfo extends ColumnInfo {

        public final long transactionDetailTemporaryIDIndex;
        public final long transactionDetailTemporaryItemIDIndex;
        public final long transactionDetailTemporaryVariantIDIndex;
        public final long transactionDetailTemporaryModifierIDIndex;
        public final long transactionDetailTemporaryTotalPriceIndex;
        public final long transactionDetailTemporaryDiscountIDIndex;
        public final long transactionDetailTemporaryQuantityIndex;

        TransactionDetailTemporaryColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(7);
            this.transactionDetailTemporaryIDIndex = getValidColumnIndex(path, table, "TransactionDetailTemporary", "transactionDetailTemporaryID");
            indicesMap.put("transactionDetailTemporaryID", this.transactionDetailTemporaryIDIndex);

            this.transactionDetailTemporaryItemIDIndex = getValidColumnIndex(path, table, "TransactionDetailTemporary", "transactionDetailTemporaryItemID");
            indicesMap.put("transactionDetailTemporaryItemID", this.transactionDetailTemporaryItemIDIndex);

            this.transactionDetailTemporaryVariantIDIndex = getValidColumnIndex(path, table, "TransactionDetailTemporary", "transactionDetailTemporaryVariantID");
            indicesMap.put("transactionDetailTemporaryVariantID", this.transactionDetailTemporaryVariantIDIndex);

            this.transactionDetailTemporaryModifierIDIndex = getValidColumnIndex(path, table, "TransactionDetailTemporary", "transactionDetailTemporaryModifierID");
            indicesMap.put("transactionDetailTemporaryModifierID", this.transactionDetailTemporaryModifierIDIndex);

            this.transactionDetailTemporaryTotalPriceIndex = getValidColumnIndex(path, table, "TransactionDetailTemporary", "transactionDetailTemporaryTotalPrice");
            indicesMap.put("transactionDetailTemporaryTotalPrice", this.transactionDetailTemporaryTotalPriceIndex);

            this.transactionDetailTemporaryDiscountIDIndex = getValidColumnIndex(path, table, "TransactionDetailTemporary", "transactionDetailTemporaryDiscountID");
            indicesMap.put("transactionDetailTemporaryDiscountID", this.transactionDetailTemporaryDiscountIDIndex);

            this.transactionDetailTemporaryQuantityIndex = getValidColumnIndex(path, table, "TransactionDetailTemporary", "transactionDetailTemporaryQuantity");
            indicesMap.put("transactionDetailTemporaryQuantity", this.transactionDetailTemporaryQuantityIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final TransactionDetailTemporaryColumnInfo columnInfo;
    private final ProxyState proxyState;
    private RealmList<TransactionDetailTemporaryModifier> transactionDetailTemporaryModifierIDRealmList;
    private RealmList<TransactionDetailTemporaryDiscount> transactionDetailTemporaryDiscountIDRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("transactionDetailTemporaryID");
        fieldNames.add("transactionDetailTemporaryItemID");
        fieldNames.add("transactionDetailTemporaryVariantID");
        fieldNames.add("transactionDetailTemporaryModifierID");
        fieldNames.add("transactionDetailTemporaryTotalPrice");
        fieldNames.add("transactionDetailTemporaryDiscountID");
        fieldNames.add("transactionDetailTemporaryQuantity");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TransactionDetailTemporaryRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (TransactionDetailTemporaryColumnInfo) columnInfo;
        this.proxyState = new ProxyState(TransactionDetailTemporary.class);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionDetailTemporaryID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionDetailTemporaryIDIndex);
    }

    public void realmSet$transactionDetailTemporaryID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionDetailTemporaryIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionDetailTemporaryIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionDetailTemporaryItemID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionDetailTemporaryItemIDIndex);
    }

    public void realmSet$transactionDetailTemporaryItemID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionDetailTemporaryItemIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionDetailTemporaryItemIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionDetailTemporaryVariantID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionDetailTemporaryVariantIDIndex);
    }

    public void realmSet$transactionDetailTemporaryVariantID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionDetailTemporaryVariantIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionDetailTemporaryVariantIDIndex, value);
    }

    public RealmList<TransactionDetailTemporaryModifier> realmGet$transactionDetailTemporaryModifierID() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (transactionDetailTemporaryModifierIDRealmList != null) {
            return transactionDetailTemporaryModifierIDRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.transactionDetailTemporaryModifierIDIndex);
            transactionDetailTemporaryModifierIDRealmList = new RealmList<TransactionDetailTemporaryModifier>(TransactionDetailTemporaryModifier.class, linkView, proxyState.getRealm$realm());
            return transactionDetailTemporaryModifierIDRealmList;
        }
    }

    public void realmSet$transactionDetailTemporaryModifierID(RealmList<TransactionDetailTemporaryModifier> value) {
        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.transactionDetailTemporaryModifierIDIndex);
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
    public String realmGet$transactionDetailTemporaryTotalPrice() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionDetailTemporaryTotalPriceIndex);
    }

    public void realmSet$transactionDetailTemporaryTotalPrice(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionDetailTemporaryTotalPriceIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionDetailTemporaryTotalPriceIndex, value);
    }

    public RealmList<TransactionDetailTemporaryDiscount> realmGet$transactionDetailTemporaryDiscountID() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (transactionDetailTemporaryDiscountIDRealmList != null) {
            return transactionDetailTemporaryDiscountIDRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.transactionDetailTemporaryDiscountIDIndex);
            transactionDetailTemporaryDiscountIDRealmList = new RealmList<TransactionDetailTemporaryDiscount>(TransactionDetailTemporaryDiscount.class, linkView, proxyState.getRealm$realm());
            return transactionDetailTemporaryDiscountIDRealmList;
        }
    }

    public void realmSet$transactionDetailTemporaryDiscountID(RealmList<TransactionDetailTemporaryDiscount> value) {
        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.transactionDetailTemporaryDiscountIDIndex);
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
    public String realmGet$transactionDetailTemporaryQuantity() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionDetailTemporaryQuantityIndex);
    }

    public void realmSet$transactionDetailTemporaryQuantity(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionDetailTemporaryQuantityIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionDetailTemporaryQuantityIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_TransactionDetailTemporary")) {
            Table table = transaction.getTable("class_TransactionDetailTemporary");
            table.addColumn(RealmFieldType.STRING, "transactionDetailTemporaryID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionDetailTemporaryItemID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionDetailTemporaryVariantID", Table.NULLABLE);
            if (!transaction.hasTable("class_TransactionDetailTemporaryModifier")) {
                TransactionDetailTemporaryModifierRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "transactionDetailTemporaryModifierID", transaction.getTable("class_TransactionDetailTemporaryModifier"));
            table.addColumn(RealmFieldType.STRING, "transactionDetailTemporaryTotalPrice", Table.NULLABLE);
            if (!transaction.hasTable("class_TransactionDetailTemporaryDiscount")) {
                TransactionDetailTemporaryDiscountRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "transactionDetailTemporaryDiscountID", transaction.getTable("class_TransactionDetailTemporaryDiscount"));
            table.addColumn(RealmFieldType.STRING, "transactionDetailTemporaryQuantity", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_TransactionDetailTemporary");
    }

    public static TransactionDetailTemporaryColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_TransactionDetailTemporary")) {
            Table table = transaction.getTable("class_TransactionDetailTemporary");
            if (table.getColumnCount() != 7) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 7 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 7; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final TransactionDetailTemporaryColumnInfo columnInfo = new TransactionDetailTemporaryColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("transactionDetailTemporaryID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailTemporaryID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionDetailTemporaryID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionDetailTemporaryID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionDetailTemporaryIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionDetailTemporaryID' is required. Either set @Required to field 'transactionDetailTemporaryID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionDetailTemporaryItemID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailTemporaryItemID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionDetailTemporaryItemID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionDetailTemporaryItemID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionDetailTemporaryItemIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionDetailTemporaryItemID' is required. Either set @Required to field 'transactionDetailTemporaryItemID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionDetailTemporaryVariantID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailTemporaryVariantID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionDetailTemporaryVariantID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionDetailTemporaryVariantID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionDetailTemporaryVariantIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionDetailTemporaryVariantID' is required. Either set @Required to field 'transactionDetailTemporaryVariantID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionDetailTemporaryModifierID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailTemporaryModifierID'");
            }
            if (columnTypes.get("transactionDetailTemporaryModifierID") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'TransactionDetailTemporaryModifier' for field 'transactionDetailTemporaryModifierID'");
            }
            if (!transaction.hasTable("class_TransactionDetailTemporaryModifier")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_TransactionDetailTemporaryModifier' for field 'transactionDetailTemporaryModifierID'");
            }
            Table table_3 = transaction.getTable("class_TransactionDetailTemporaryModifier");
            if (!table.getLinkTarget(columnInfo.transactionDetailTemporaryModifierIDIndex).hasSameSchema(table_3)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'transactionDetailTemporaryModifierID': '" + table.getLinkTarget(columnInfo.transactionDetailTemporaryModifierIDIndex).getName() + "' expected - was '" + table_3.getName() + "'");
            }
            if (!columnTypes.containsKey("transactionDetailTemporaryTotalPrice")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailTemporaryTotalPrice' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionDetailTemporaryTotalPrice") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionDetailTemporaryTotalPrice' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionDetailTemporaryTotalPriceIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionDetailTemporaryTotalPrice' is required. Either set @Required to field 'transactionDetailTemporaryTotalPrice' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionDetailTemporaryDiscountID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailTemporaryDiscountID'");
            }
            if (columnTypes.get("transactionDetailTemporaryDiscountID") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'TransactionDetailTemporaryDiscount' for field 'transactionDetailTemporaryDiscountID'");
            }
            if (!transaction.hasTable("class_TransactionDetailTemporaryDiscount")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_TransactionDetailTemporaryDiscount' for field 'transactionDetailTemporaryDiscountID'");
            }
            Table table_5 = transaction.getTable("class_TransactionDetailTemporaryDiscount");
            if (!table.getLinkTarget(columnInfo.transactionDetailTemporaryDiscountIDIndex).hasSameSchema(table_5)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'transactionDetailTemporaryDiscountID': '" + table.getLinkTarget(columnInfo.transactionDetailTemporaryDiscountIDIndex).getName() + "' expected - was '" + table_5.getName() + "'");
            }
            if (!columnTypes.containsKey("transactionDetailTemporaryQuantity")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailTemporaryQuantity' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionDetailTemporaryQuantity") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionDetailTemporaryQuantity' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionDetailTemporaryQuantityIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionDetailTemporaryQuantity' is required. Either set @Required to field 'transactionDetailTemporaryQuantity' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The TransactionDetailTemporary class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_TransactionDetailTemporary";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static TransactionDetailTemporary createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        TransactionDetailTemporary obj = realm.createObject(TransactionDetailTemporary.class);
        if (json.has("transactionDetailTemporaryID")) {
            if (json.isNull("transactionDetailTemporaryID")) {
                ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryID(null);
            } else {
                ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryID((String) json.getString("transactionDetailTemporaryID"));
            }
        }
        if (json.has("transactionDetailTemporaryItemID")) {
            if (json.isNull("transactionDetailTemporaryItemID")) {
                ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryItemID(null);
            } else {
                ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryItemID((String) json.getString("transactionDetailTemporaryItemID"));
            }
        }
        if (json.has("transactionDetailTemporaryVariantID")) {
            if (json.isNull("transactionDetailTemporaryVariantID")) {
                ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryVariantID(null);
            } else {
                ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryVariantID((String) json.getString("transactionDetailTemporaryVariantID"));
            }
        }
        if (json.has("transactionDetailTemporaryModifierID")) {
            if (json.isNull("transactionDetailTemporaryModifierID")) {
                ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryModifierID(null);
            } else {
                ((TransactionDetailTemporaryRealmProxyInterface) obj).realmGet$transactionDetailTemporaryModifierID().clear();
                JSONArray array = json.getJSONArray("transactionDetailTemporaryModifierID");
                for (int i = 0; i < array.length(); i++) {
                    com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryModifier item = TransactionDetailTemporaryModifierRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((TransactionDetailTemporaryRealmProxyInterface) obj).realmGet$transactionDetailTemporaryModifierID().add(item);
                }
            }
        }
        if (json.has("transactionDetailTemporaryTotalPrice")) {
            if (json.isNull("transactionDetailTemporaryTotalPrice")) {
                ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryTotalPrice(null);
            } else {
                ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryTotalPrice((String) json.getString("transactionDetailTemporaryTotalPrice"));
            }
        }
        if (json.has("transactionDetailTemporaryDiscountID")) {
            if (json.isNull("transactionDetailTemporaryDiscountID")) {
                ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryDiscountID(null);
            } else {
                ((TransactionDetailTemporaryRealmProxyInterface) obj).realmGet$transactionDetailTemporaryDiscountID().clear();
                JSONArray array = json.getJSONArray("transactionDetailTemporaryDiscountID");
                for (int i = 0; i < array.length(); i++) {
                    com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryDiscount item = TransactionDetailTemporaryDiscountRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((TransactionDetailTemporaryRealmProxyInterface) obj).realmGet$transactionDetailTemporaryDiscountID().add(item);
                }
            }
        }
        if (json.has("transactionDetailTemporaryQuantity")) {
            if (json.isNull("transactionDetailTemporaryQuantity")) {
                ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryQuantity(null);
            } else {
                ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryQuantity((String) json.getString("transactionDetailTemporaryQuantity"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static TransactionDetailTemporary createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        TransactionDetailTemporary obj = realm.createObject(TransactionDetailTemporary.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("transactionDetailTemporaryID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryID(null);
                } else {
                    ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryID((String) reader.nextString());
                }
            } else if (name.equals("transactionDetailTemporaryItemID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryItemID(null);
                } else {
                    ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryItemID((String) reader.nextString());
                }
            } else if (name.equals("transactionDetailTemporaryVariantID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryVariantID(null);
                } else {
                    ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryVariantID((String) reader.nextString());
                }
            } else if (name.equals("transactionDetailTemporaryModifierID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryModifierID(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryModifier item = TransactionDetailTemporaryModifierRealmProxy.createUsingJsonStream(realm, reader);
                        ((TransactionDetailTemporaryRealmProxyInterface) obj).realmGet$transactionDetailTemporaryModifierID().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("transactionDetailTemporaryTotalPrice")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryTotalPrice(null);
                } else {
                    ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryTotalPrice((String) reader.nextString());
                }
            } else if (name.equals("transactionDetailTemporaryDiscountID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryDiscountID(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryDiscount item = TransactionDetailTemporaryDiscountRealmProxy.createUsingJsonStream(realm, reader);
                        ((TransactionDetailTemporaryRealmProxyInterface) obj).realmGet$transactionDetailTemporaryDiscountID().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("transactionDetailTemporaryQuantity")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryQuantity(null);
                } else {
                    ((TransactionDetailTemporaryRealmProxyInterface) obj).realmSet$transactionDetailTemporaryQuantity((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static TransactionDetailTemporary copyOrUpdate(Realm realm, TransactionDetailTemporary object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static TransactionDetailTemporary copy(Realm realm, TransactionDetailTemporary newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        TransactionDetailTemporary realmObject = realm.createObject(TransactionDetailTemporary.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((TransactionDetailTemporaryRealmProxyInterface) realmObject).realmSet$transactionDetailTemporaryID(((TransactionDetailTemporaryRealmProxyInterface) newObject).realmGet$transactionDetailTemporaryID());
        ((TransactionDetailTemporaryRealmProxyInterface) realmObject).realmSet$transactionDetailTemporaryItemID(((TransactionDetailTemporaryRealmProxyInterface) newObject).realmGet$transactionDetailTemporaryItemID());
        ((TransactionDetailTemporaryRealmProxyInterface) realmObject).realmSet$transactionDetailTemporaryVariantID(((TransactionDetailTemporaryRealmProxyInterface) newObject).realmGet$transactionDetailTemporaryVariantID());

        RealmList<TransactionDetailTemporaryModifier> transactionDetailTemporaryModifierIDList = ((TransactionDetailTemporaryRealmProxyInterface) newObject).realmGet$transactionDetailTemporaryModifierID();
        if (transactionDetailTemporaryModifierIDList != null) {
            RealmList<TransactionDetailTemporaryModifier> transactionDetailTemporaryModifierIDRealmList = ((TransactionDetailTemporaryRealmProxyInterface) realmObject).realmGet$transactionDetailTemporaryModifierID();
            for (int i = 0; i < transactionDetailTemporaryModifierIDList.size(); i++) {
                TransactionDetailTemporaryModifier transactionDetailTemporaryModifierIDItem = transactionDetailTemporaryModifierIDList.get(i);
                TransactionDetailTemporaryModifier cachetransactionDetailTemporaryModifierID = (TransactionDetailTemporaryModifier) cache.get(transactionDetailTemporaryModifierIDItem);
                if (cachetransactionDetailTemporaryModifierID != null) {
                    transactionDetailTemporaryModifierIDRealmList.add(cachetransactionDetailTemporaryModifierID);
                } else {
                    transactionDetailTemporaryModifierIDRealmList.add(TransactionDetailTemporaryModifierRealmProxy.copyOrUpdate(realm, transactionDetailTemporaryModifierIDList.get(i), update, cache));
                }
            }
        }

        ((TransactionDetailTemporaryRealmProxyInterface) realmObject).realmSet$transactionDetailTemporaryTotalPrice(((TransactionDetailTemporaryRealmProxyInterface) newObject).realmGet$transactionDetailTemporaryTotalPrice());

        RealmList<TransactionDetailTemporaryDiscount> transactionDetailTemporaryDiscountIDList = ((TransactionDetailTemporaryRealmProxyInterface) newObject).realmGet$transactionDetailTemporaryDiscountID();
        if (transactionDetailTemporaryDiscountIDList != null) {
            RealmList<TransactionDetailTemporaryDiscount> transactionDetailTemporaryDiscountIDRealmList = ((TransactionDetailTemporaryRealmProxyInterface) realmObject).realmGet$transactionDetailTemporaryDiscountID();
            for (int i = 0; i < transactionDetailTemporaryDiscountIDList.size(); i++) {
                TransactionDetailTemporaryDiscount transactionDetailTemporaryDiscountIDItem = transactionDetailTemporaryDiscountIDList.get(i);
                TransactionDetailTemporaryDiscount cachetransactionDetailTemporaryDiscountID = (TransactionDetailTemporaryDiscount) cache.get(transactionDetailTemporaryDiscountIDItem);
                if (cachetransactionDetailTemporaryDiscountID != null) {
                    transactionDetailTemporaryDiscountIDRealmList.add(cachetransactionDetailTemporaryDiscountID);
                } else {
                    transactionDetailTemporaryDiscountIDRealmList.add(TransactionDetailTemporaryDiscountRealmProxy.copyOrUpdate(realm, transactionDetailTemporaryDiscountIDList.get(i), update, cache));
                }
            }
        }

        ((TransactionDetailTemporaryRealmProxyInterface) realmObject).realmSet$transactionDetailTemporaryQuantity(((TransactionDetailTemporaryRealmProxyInterface) newObject).realmGet$transactionDetailTemporaryQuantity());
        return realmObject;
    }

    public static TransactionDetailTemporary createDetachedCopy(TransactionDetailTemporary realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        TransactionDetailTemporary standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (TransactionDetailTemporary)cachedObject.object;
            } else {
                standaloneObject = (TransactionDetailTemporary)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new TransactionDetailTemporary();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((TransactionDetailTemporaryRealmProxyInterface) standaloneObject).realmSet$transactionDetailTemporaryID(((TransactionDetailTemporaryRealmProxyInterface) realmObject).realmGet$transactionDetailTemporaryID());
        ((TransactionDetailTemporaryRealmProxyInterface) standaloneObject).realmSet$transactionDetailTemporaryItemID(((TransactionDetailTemporaryRealmProxyInterface) realmObject).realmGet$transactionDetailTemporaryItemID());
        ((TransactionDetailTemporaryRealmProxyInterface) standaloneObject).realmSet$transactionDetailTemporaryVariantID(((TransactionDetailTemporaryRealmProxyInterface) realmObject).realmGet$transactionDetailTemporaryVariantID());

        // Deep copy of transactionDetailTemporaryModifierID
        if (currentDepth == maxDepth) {
            ((TransactionDetailTemporaryRealmProxyInterface) standaloneObject).realmSet$transactionDetailTemporaryModifierID(null);
        } else {
            RealmList<TransactionDetailTemporaryModifier> managedtransactionDetailTemporaryModifierIDList = ((TransactionDetailTemporaryRealmProxyInterface) realmObject).realmGet$transactionDetailTemporaryModifierID();
            RealmList<TransactionDetailTemporaryModifier> standalonetransactionDetailTemporaryModifierIDList = new RealmList<TransactionDetailTemporaryModifier>();
            ((TransactionDetailTemporaryRealmProxyInterface) standaloneObject).realmSet$transactionDetailTemporaryModifierID(standalonetransactionDetailTemporaryModifierIDList);
            int nextDepth = currentDepth + 1;
            int size = managedtransactionDetailTemporaryModifierIDList.size();
            for (int i = 0; i < size; i++) {
                TransactionDetailTemporaryModifier item = TransactionDetailTemporaryModifierRealmProxy.createDetachedCopy(managedtransactionDetailTemporaryModifierIDList.get(i), nextDepth, maxDepth, cache);
                standalonetransactionDetailTemporaryModifierIDList.add(item);
            }
        }
        ((TransactionDetailTemporaryRealmProxyInterface) standaloneObject).realmSet$transactionDetailTemporaryTotalPrice(((TransactionDetailTemporaryRealmProxyInterface) realmObject).realmGet$transactionDetailTemporaryTotalPrice());

        // Deep copy of transactionDetailTemporaryDiscountID
        if (currentDepth == maxDepth) {
            ((TransactionDetailTemporaryRealmProxyInterface) standaloneObject).realmSet$transactionDetailTemporaryDiscountID(null);
        } else {
            RealmList<TransactionDetailTemporaryDiscount> managedtransactionDetailTemporaryDiscountIDList = ((TransactionDetailTemporaryRealmProxyInterface) realmObject).realmGet$transactionDetailTemporaryDiscountID();
            RealmList<TransactionDetailTemporaryDiscount> standalonetransactionDetailTemporaryDiscountIDList = new RealmList<TransactionDetailTemporaryDiscount>();
            ((TransactionDetailTemporaryRealmProxyInterface) standaloneObject).realmSet$transactionDetailTemporaryDiscountID(standalonetransactionDetailTemporaryDiscountIDList);
            int nextDepth = currentDepth + 1;
            int size = managedtransactionDetailTemporaryDiscountIDList.size();
            for (int i = 0; i < size; i++) {
                TransactionDetailTemporaryDiscount item = TransactionDetailTemporaryDiscountRealmProxy.createDetachedCopy(managedtransactionDetailTemporaryDiscountIDList.get(i), nextDepth, maxDepth, cache);
                standalonetransactionDetailTemporaryDiscountIDList.add(item);
            }
        }
        ((TransactionDetailTemporaryRealmProxyInterface) standaloneObject).realmSet$transactionDetailTemporaryQuantity(((TransactionDetailTemporaryRealmProxyInterface) realmObject).realmGet$transactionDetailTemporaryQuantity());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("TransactionDetailTemporary = [");
        stringBuilder.append("{transactionDetailTemporaryID:");
        stringBuilder.append(realmGet$transactionDetailTemporaryID() != null ? realmGet$transactionDetailTemporaryID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionDetailTemporaryItemID:");
        stringBuilder.append(realmGet$transactionDetailTemporaryItemID() != null ? realmGet$transactionDetailTemporaryItemID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionDetailTemporaryVariantID:");
        stringBuilder.append(realmGet$transactionDetailTemporaryVariantID() != null ? realmGet$transactionDetailTemporaryVariantID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionDetailTemporaryModifierID:");
        stringBuilder.append("RealmList<TransactionDetailTemporaryModifier>[").append(realmGet$transactionDetailTemporaryModifierID().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionDetailTemporaryTotalPrice:");
        stringBuilder.append(realmGet$transactionDetailTemporaryTotalPrice() != null ? realmGet$transactionDetailTemporaryTotalPrice() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionDetailTemporaryDiscountID:");
        stringBuilder.append("RealmList<TransactionDetailTemporaryDiscount>[").append(realmGet$transactionDetailTemporaryDiscountID().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionDetailTemporaryQuantity:");
        stringBuilder.append(realmGet$transactionDetailTemporaryQuantity() != null ? realmGet$transactionDetailTemporaryQuantity() : "null");
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
        TransactionDetailTemporaryRealmProxy aTransactionDetailTemporary = (TransactionDetailTemporaryRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTransactionDetailTemporary.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTransactionDetailTemporary.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTransactionDetailTemporary.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

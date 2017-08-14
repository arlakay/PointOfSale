package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailModifier;
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

public class TransactionDetailRealmProxy extends TransactionDetail
    implements RealmObjectProxy, TransactionDetailRealmProxyInterface {

    static final class TransactionDetailColumnInfo extends ColumnInfo {

        public final long transactionDetailIDIndex;
        public final long transactionDetailItemIDIndex;
        public final long transactionDetailVariantIDIndex;
        public final long transactionDetailModifierIDIndex;
        public final long transactionDetailTotalPriceIndex;
        public final long transactionDetailDiscountIDIndex;
        public final long transactionDetailQuantityIndex;

        TransactionDetailColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(7);
            this.transactionDetailIDIndex = getValidColumnIndex(path, table, "TransactionDetail", "transactionDetailID");
            indicesMap.put("transactionDetailID", this.transactionDetailIDIndex);

            this.transactionDetailItemIDIndex = getValidColumnIndex(path, table, "TransactionDetail", "transactionDetailItemID");
            indicesMap.put("transactionDetailItemID", this.transactionDetailItemIDIndex);

            this.transactionDetailVariantIDIndex = getValidColumnIndex(path, table, "TransactionDetail", "transactionDetailVariantID");
            indicesMap.put("transactionDetailVariantID", this.transactionDetailVariantIDIndex);

            this.transactionDetailModifierIDIndex = getValidColumnIndex(path, table, "TransactionDetail", "transactionDetailModifierID");
            indicesMap.put("transactionDetailModifierID", this.transactionDetailModifierIDIndex);

            this.transactionDetailTotalPriceIndex = getValidColumnIndex(path, table, "TransactionDetail", "transactionDetailTotalPrice");
            indicesMap.put("transactionDetailTotalPrice", this.transactionDetailTotalPriceIndex);

            this.transactionDetailDiscountIDIndex = getValidColumnIndex(path, table, "TransactionDetail", "transactionDetailDiscountID");
            indicesMap.put("transactionDetailDiscountID", this.transactionDetailDiscountIDIndex);

            this.transactionDetailQuantityIndex = getValidColumnIndex(path, table, "TransactionDetail", "transactionDetailQuantity");
            indicesMap.put("transactionDetailQuantity", this.transactionDetailQuantityIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final TransactionDetailColumnInfo columnInfo;
    private final ProxyState proxyState;
    private RealmList<TransactionDetailModifier> transactionDetailModifierIDRealmList;
    private RealmList<TransactionDetailDiscount> transactionDetailDiscountIDRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("transactionDetailID");
        fieldNames.add("transactionDetailItemID");
        fieldNames.add("transactionDetailVariantID");
        fieldNames.add("transactionDetailModifierID");
        fieldNames.add("transactionDetailTotalPrice");
        fieldNames.add("transactionDetailDiscountID");
        fieldNames.add("transactionDetailQuantity");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TransactionDetailRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (TransactionDetailColumnInfo) columnInfo;
        this.proxyState = new ProxyState(TransactionDetail.class);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionDetailID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionDetailIDIndex);
    }

    public void realmSet$transactionDetailID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionDetailIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionDetailIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionDetailItemID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionDetailItemIDIndex);
    }

    public void realmSet$transactionDetailItemID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionDetailItemIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionDetailItemIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionDetailVariantID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionDetailVariantIDIndex);
    }

    public void realmSet$transactionDetailVariantID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionDetailVariantIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionDetailVariantIDIndex, value);
    }

    public RealmList<TransactionDetailModifier> realmGet$transactionDetailModifierID() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (transactionDetailModifierIDRealmList != null) {
            return transactionDetailModifierIDRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.transactionDetailModifierIDIndex);
            transactionDetailModifierIDRealmList = new RealmList<TransactionDetailModifier>(TransactionDetailModifier.class, linkView, proxyState.getRealm$realm());
            return transactionDetailModifierIDRealmList;
        }
    }

    public void realmSet$transactionDetailModifierID(RealmList<TransactionDetailModifier> value) {
        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.transactionDetailModifierIDIndex);
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
    public String realmGet$transactionDetailTotalPrice() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionDetailTotalPriceIndex);
    }

    public void realmSet$transactionDetailTotalPrice(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionDetailTotalPriceIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionDetailTotalPriceIndex, value);
    }

    public RealmList<TransactionDetailDiscount> realmGet$transactionDetailDiscountID() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (transactionDetailDiscountIDRealmList != null) {
            return transactionDetailDiscountIDRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.transactionDetailDiscountIDIndex);
            transactionDetailDiscountIDRealmList = new RealmList<TransactionDetailDiscount>(TransactionDetailDiscount.class, linkView, proxyState.getRealm$realm());
            return transactionDetailDiscountIDRealmList;
        }
    }

    public void realmSet$transactionDetailDiscountID(RealmList<TransactionDetailDiscount> value) {
        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.transactionDetailDiscountIDIndex);
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
    public String realmGet$transactionDetailQuantity() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionDetailQuantityIndex);
    }

    public void realmSet$transactionDetailQuantity(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionDetailQuantityIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionDetailQuantityIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_TransactionDetail")) {
            Table table = transaction.getTable("class_TransactionDetail");
            table.addColumn(RealmFieldType.STRING, "transactionDetailID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionDetailItemID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionDetailVariantID", Table.NULLABLE);
            if (!transaction.hasTable("class_TransactionDetailModifier")) {
                TransactionDetailModifierRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "transactionDetailModifierID", transaction.getTable("class_TransactionDetailModifier"));
            table.addColumn(RealmFieldType.STRING, "transactionDetailTotalPrice", Table.NULLABLE);
            if (!transaction.hasTable("class_TransactionDetailDiscount")) {
                TransactionDetailDiscountRealmProxy.initTable(transaction);
            }
            table.addColumnLink(RealmFieldType.LIST, "transactionDetailDiscountID", transaction.getTable("class_TransactionDetailDiscount"));
            table.addColumn(RealmFieldType.STRING, "transactionDetailQuantity", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_TransactionDetail");
    }

    public static TransactionDetailColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_TransactionDetail")) {
            Table table = transaction.getTable("class_TransactionDetail");
            if (table.getColumnCount() != 7) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 7 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 7; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final TransactionDetailColumnInfo columnInfo = new TransactionDetailColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("transactionDetailID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionDetailID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionDetailID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionDetailIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionDetailID' is required. Either set @Required to field 'transactionDetailID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionDetailItemID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailItemID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionDetailItemID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionDetailItemID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionDetailItemIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionDetailItemID' is required. Either set @Required to field 'transactionDetailItemID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionDetailVariantID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailVariantID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionDetailVariantID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionDetailVariantID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionDetailVariantIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionDetailVariantID' is required. Either set @Required to field 'transactionDetailVariantID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionDetailModifierID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailModifierID'");
            }
            if (columnTypes.get("transactionDetailModifierID") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'TransactionDetailModifier' for field 'transactionDetailModifierID'");
            }
            if (!transaction.hasTable("class_TransactionDetailModifier")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_TransactionDetailModifier' for field 'transactionDetailModifierID'");
            }
            Table table_3 = transaction.getTable("class_TransactionDetailModifier");
            if (!table.getLinkTarget(columnInfo.transactionDetailModifierIDIndex).hasSameSchema(table_3)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'transactionDetailModifierID': '" + table.getLinkTarget(columnInfo.transactionDetailModifierIDIndex).getName() + "' expected - was '" + table_3.getName() + "'");
            }
            if (!columnTypes.containsKey("transactionDetailTotalPrice")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailTotalPrice' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionDetailTotalPrice") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionDetailTotalPrice' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionDetailTotalPriceIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionDetailTotalPrice' is required. Either set @Required to field 'transactionDetailTotalPrice' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionDetailDiscountID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailDiscountID'");
            }
            if (columnTypes.get("transactionDetailDiscountID") != RealmFieldType.LIST) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'TransactionDetailDiscount' for field 'transactionDetailDiscountID'");
            }
            if (!transaction.hasTable("class_TransactionDetailDiscount")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing class 'class_TransactionDetailDiscount' for field 'transactionDetailDiscountID'");
            }
            Table table_5 = transaction.getTable("class_TransactionDetailDiscount");
            if (!table.getLinkTarget(columnInfo.transactionDetailDiscountIDIndex).hasSameSchema(table_5)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid RealmList type for field 'transactionDetailDiscountID': '" + table.getLinkTarget(columnInfo.transactionDetailDiscountIDIndex).getName() + "' expected - was '" + table_5.getName() + "'");
            }
            if (!columnTypes.containsKey("transactionDetailQuantity")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailQuantity' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionDetailQuantity") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionDetailQuantity' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionDetailQuantityIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionDetailQuantity' is required. Either set @Required to field 'transactionDetailQuantity' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The TransactionDetail class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_TransactionDetail";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static TransactionDetail createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        TransactionDetail obj = realm.createObject(TransactionDetail.class);
        if (json.has("transactionDetailID")) {
            if (json.isNull("transactionDetailID")) {
                ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailID(null);
            } else {
                ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailID((String) json.getString("transactionDetailID"));
            }
        }
        if (json.has("transactionDetailItemID")) {
            if (json.isNull("transactionDetailItemID")) {
                ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailItemID(null);
            } else {
                ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailItemID((String) json.getString("transactionDetailItemID"));
            }
        }
        if (json.has("transactionDetailVariantID")) {
            if (json.isNull("transactionDetailVariantID")) {
                ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailVariantID(null);
            } else {
                ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailVariantID((String) json.getString("transactionDetailVariantID"));
            }
        }
        if (json.has("transactionDetailModifierID")) {
            if (json.isNull("transactionDetailModifierID")) {
                ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailModifierID(null);
            } else {
                ((TransactionDetailRealmProxyInterface) obj).realmGet$transactionDetailModifierID().clear();
                JSONArray array = json.getJSONArray("transactionDetailModifierID");
                for (int i = 0; i < array.length(); i++) {
                    com.ingenico.PointOfSale.ModelRealm.TransactionDetailModifier item = TransactionDetailModifierRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((TransactionDetailRealmProxyInterface) obj).realmGet$transactionDetailModifierID().add(item);
                }
            }
        }
        if (json.has("transactionDetailTotalPrice")) {
            if (json.isNull("transactionDetailTotalPrice")) {
                ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailTotalPrice(null);
            } else {
                ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailTotalPrice((String) json.getString("transactionDetailTotalPrice"));
            }
        }
        if (json.has("transactionDetailDiscountID")) {
            if (json.isNull("transactionDetailDiscountID")) {
                ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailDiscountID(null);
            } else {
                ((TransactionDetailRealmProxyInterface) obj).realmGet$transactionDetailDiscountID().clear();
                JSONArray array = json.getJSONArray("transactionDetailDiscountID");
                for (int i = 0; i < array.length(); i++) {
                    com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount item = TransactionDetailDiscountRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((TransactionDetailRealmProxyInterface) obj).realmGet$transactionDetailDiscountID().add(item);
                }
            }
        }
        if (json.has("transactionDetailQuantity")) {
            if (json.isNull("transactionDetailQuantity")) {
                ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailQuantity(null);
            } else {
                ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailQuantity((String) json.getString("transactionDetailQuantity"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static TransactionDetail createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        TransactionDetail obj = realm.createObject(TransactionDetail.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("transactionDetailID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailID(null);
                } else {
                    ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailID((String) reader.nextString());
                }
            } else if (name.equals("transactionDetailItemID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailItemID(null);
                } else {
                    ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailItemID((String) reader.nextString());
                }
            } else if (name.equals("transactionDetailVariantID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailVariantID(null);
                } else {
                    ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailVariantID((String) reader.nextString());
                }
            } else if (name.equals("transactionDetailModifierID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailModifierID(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.ingenico.PointOfSale.ModelRealm.TransactionDetailModifier item = TransactionDetailModifierRealmProxy.createUsingJsonStream(realm, reader);
                        ((TransactionDetailRealmProxyInterface) obj).realmGet$transactionDetailModifierID().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("transactionDetailTotalPrice")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailTotalPrice(null);
                } else {
                    ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailTotalPrice((String) reader.nextString());
                }
            } else if (name.equals("transactionDetailDiscountID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailDiscountID(null);
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount item = TransactionDetailDiscountRealmProxy.createUsingJsonStream(realm, reader);
                        ((TransactionDetailRealmProxyInterface) obj).realmGet$transactionDetailDiscountID().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("transactionDetailQuantity")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailQuantity(null);
                } else {
                    ((TransactionDetailRealmProxyInterface) obj).realmSet$transactionDetailQuantity((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static TransactionDetail copyOrUpdate(Realm realm, TransactionDetail object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static TransactionDetail copy(Realm realm, TransactionDetail newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        TransactionDetail realmObject = realm.createObject(TransactionDetail.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((TransactionDetailRealmProxyInterface) realmObject).realmSet$transactionDetailID(((TransactionDetailRealmProxyInterface) newObject).realmGet$transactionDetailID());
        ((TransactionDetailRealmProxyInterface) realmObject).realmSet$transactionDetailItemID(((TransactionDetailRealmProxyInterface) newObject).realmGet$transactionDetailItemID());
        ((TransactionDetailRealmProxyInterface) realmObject).realmSet$transactionDetailVariantID(((TransactionDetailRealmProxyInterface) newObject).realmGet$transactionDetailVariantID());

        RealmList<TransactionDetailModifier> transactionDetailModifierIDList = ((TransactionDetailRealmProxyInterface) newObject).realmGet$transactionDetailModifierID();
        if (transactionDetailModifierIDList != null) {
            RealmList<TransactionDetailModifier> transactionDetailModifierIDRealmList = ((TransactionDetailRealmProxyInterface) realmObject).realmGet$transactionDetailModifierID();
            for (int i = 0; i < transactionDetailModifierIDList.size(); i++) {
                TransactionDetailModifier transactionDetailModifierIDItem = transactionDetailModifierIDList.get(i);
                TransactionDetailModifier cachetransactionDetailModifierID = (TransactionDetailModifier) cache.get(transactionDetailModifierIDItem);
                if (cachetransactionDetailModifierID != null) {
                    transactionDetailModifierIDRealmList.add(cachetransactionDetailModifierID);
                } else {
                    transactionDetailModifierIDRealmList.add(TransactionDetailModifierRealmProxy.copyOrUpdate(realm, transactionDetailModifierIDList.get(i), update, cache));
                }
            }
        }

        ((TransactionDetailRealmProxyInterface) realmObject).realmSet$transactionDetailTotalPrice(((TransactionDetailRealmProxyInterface) newObject).realmGet$transactionDetailTotalPrice());

        RealmList<TransactionDetailDiscount> transactionDetailDiscountIDList = ((TransactionDetailRealmProxyInterface) newObject).realmGet$transactionDetailDiscountID();
        if (transactionDetailDiscountIDList != null) {
            RealmList<TransactionDetailDiscount> transactionDetailDiscountIDRealmList = ((TransactionDetailRealmProxyInterface) realmObject).realmGet$transactionDetailDiscountID();
            for (int i = 0; i < transactionDetailDiscountIDList.size(); i++) {
                TransactionDetailDiscount transactionDetailDiscountIDItem = transactionDetailDiscountIDList.get(i);
                TransactionDetailDiscount cachetransactionDetailDiscountID = (TransactionDetailDiscount) cache.get(transactionDetailDiscountIDItem);
                if (cachetransactionDetailDiscountID != null) {
                    transactionDetailDiscountIDRealmList.add(cachetransactionDetailDiscountID);
                } else {
                    transactionDetailDiscountIDRealmList.add(TransactionDetailDiscountRealmProxy.copyOrUpdate(realm, transactionDetailDiscountIDList.get(i), update, cache));
                }
            }
        }

        ((TransactionDetailRealmProxyInterface) realmObject).realmSet$transactionDetailQuantity(((TransactionDetailRealmProxyInterface) newObject).realmGet$transactionDetailQuantity());
        return realmObject;
    }

    public static TransactionDetail createDetachedCopy(TransactionDetail realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        TransactionDetail standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (TransactionDetail)cachedObject.object;
            } else {
                standaloneObject = (TransactionDetail)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new TransactionDetail();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((TransactionDetailRealmProxyInterface) standaloneObject).realmSet$transactionDetailID(((TransactionDetailRealmProxyInterface) realmObject).realmGet$transactionDetailID());
        ((TransactionDetailRealmProxyInterface) standaloneObject).realmSet$transactionDetailItemID(((TransactionDetailRealmProxyInterface) realmObject).realmGet$transactionDetailItemID());
        ((TransactionDetailRealmProxyInterface) standaloneObject).realmSet$transactionDetailVariantID(((TransactionDetailRealmProxyInterface) realmObject).realmGet$transactionDetailVariantID());

        // Deep copy of transactionDetailModifierID
        if (currentDepth == maxDepth) {
            ((TransactionDetailRealmProxyInterface) standaloneObject).realmSet$transactionDetailModifierID(null);
        } else {
            RealmList<TransactionDetailModifier> managedtransactionDetailModifierIDList = ((TransactionDetailRealmProxyInterface) realmObject).realmGet$transactionDetailModifierID();
            RealmList<TransactionDetailModifier> standalonetransactionDetailModifierIDList = new RealmList<TransactionDetailModifier>();
            ((TransactionDetailRealmProxyInterface) standaloneObject).realmSet$transactionDetailModifierID(standalonetransactionDetailModifierIDList);
            int nextDepth = currentDepth + 1;
            int size = managedtransactionDetailModifierIDList.size();
            for (int i = 0; i < size; i++) {
                TransactionDetailModifier item = TransactionDetailModifierRealmProxy.createDetachedCopy(managedtransactionDetailModifierIDList.get(i), nextDepth, maxDepth, cache);
                standalonetransactionDetailModifierIDList.add(item);
            }
        }
        ((TransactionDetailRealmProxyInterface) standaloneObject).realmSet$transactionDetailTotalPrice(((TransactionDetailRealmProxyInterface) realmObject).realmGet$transactionDetailTotalPrice());

        // Deep copy of transactionDetailDiscountID
        if (currentDepth == maxDepth) {
            ((TransactionDetailRealmProxyInterface) standaloneObject).realmSet$transactionDetailDiscountID(null);
        } else {
            RealmList<TransactionDetailDiscount> managedtransactionDetailDiscountIDList = ((TransactionDetailRealmProxyInterface) realmObject).realmGet$transactionDetailDiscountID();
            RealmList<TransactionDetailDiscount> standalonetransactionDetailDiscountIDList = new RealmList<TransactionDetailDiscount>();
            ((TransactionDetailRealmProxyInterface) standaloneObject).realmSet$transactionDetailDiscountID(standalonetransactionDetailDiscountIDList);
            int nextDepth = currentDepth + 1;
            int size = managedtransactionDetailDiscountIDList.size();
            for (int i = 0; i < size; i++) {
                TransactionDetailDiscount item = TransactionDetailDiscountRealmProxy.createDetachedCopy(managedtransactionDetailDiscountIDList.get(i), nextDepth, maxDepth, cache);
                standalonetransactionDetailDiscountIDList.add(item);
            }
        }
        ((TransactionDetailRealmProxyInterface) standaloneObject).realmSet$transactionDetailQuantity(((TransactionDetailRealmProxyInterface) realmObject).realmGet$transactionDetailQuantity());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("TransactionDetail = [");
        stringBuilder.append("{transactionDetailID:");
        stringBuilder.append(realmGet$transactionDetailID() != null ? realmGet$transactionDetailID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionDetailItemID:");
        stringBuilder.append(realmGet$transactionDetailItemID() != null ? realmGet$transactionDetailItemID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionDetailVariantID:");
        stringBuilder.append(realmGet$transactionDetailVariantID() != null ? realmGet$transactionDetailVariantID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionDetailModifierID:");
        stringBuilder.append("RealmList<TransactionDetailModifier>[").append(realmGet$transactionDetailModifierID().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionDetailTotalPrice:");
        stringBuilder.append(realmGet$transactionDetailTotalPrice() != null ? realmGet$transactionDetailTotalPrice() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionDetailDiscountID:");
        stringBuilder.append("RealmList<TransactionDetailDiscount>[").append(realmGet$transactionDetailDiscountID().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionDetailQuantity:");
        stringBuilder.append(realmGet$transactionDetailQuantity() != null ? realmGet$transactionDetailQuantity() : "null");
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
        TransactionDetailRealmProxy aTransactionDetail = (TransactionDetailRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTransactionDetail.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTransactionDetail.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTransactionDetail.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

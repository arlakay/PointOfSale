package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
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

public class ItemVariantRealmProxy extends ItemVariant
    implements RealmObjectProxy, ItemVariantRealmProxyInterface {

    static final class ItemVariantColumnInfo extends ColumnInfo {

        public final long realmItemVariantIDIndex;
        public final long itemVariantIDIndex;

        ItemVariantColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(2);
            this.realmItemVariantIDIndex = getValidColumnIndex(path, table, "ItemVariant", "realmItemVariantID");
            indicesMap.put("realmItemVariantID", this.realmItemVariantIDIndex);

            this.itemVariantIDIndex = getValidColumnIndex(path, table, "ItemVariant", "itemVariantID");
            indicesMap.put("itemVariantID", this.itemVariantIDIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final ItemVariantColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("realmItemVariantID");
        fieldNames.add("itemVariantID");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ItemVariantRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (ItemVariantColumnInfo) columnInfo;
        this.proxyState = new ProxyState(ItemVariant.class);
    }

    @SuppressWarnings("cast")
    public long realmGet$realmItemVariantID() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.realmItemVariantIDIndex);
    }

    public void realmSet$realmItemVariantID(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.realmItemVariantIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$itemVariantID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.itemVariantIDIndex);
    }

    public void realmSet$itemVariantID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.itemVariantIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.itemVariantIDIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_ItemVariant")) {
            Table table = transaction.getTable("class_ItemVariant");
            table.addColumn(RealmFieldType.INTEGER, "realmItemVariantID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "itemVariantID", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_ItemVariant");
    }

    public static ItemVariantColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_ItemVariant")) {
            Table table = transaction.getTable("class_ItemVariant");
            if (table.getColumnCount() != 2) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 2 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 2; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final ItemVariantColumnInfo columnInfo = new ItemVariantColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("realmItemVariantID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'realmItemVariantID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realmItemVariantID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'realmItemVariantID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.realmItemVariantIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'realmItemVariantID' does support null values in the existing Realm file. Use corresponding boxed type for field 'realmItemVariantID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("itemVariantID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'itemVariantID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("itemVariantID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'itemVariantID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.itemVariantIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'itemVariantID' is required. Either set @Required to field 'itemVariantID' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The ItemVariant class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_ItemVariant";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static ItemVariant createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        ItemVariant obj = realm.createObject(ItemVariant.class);
        if (json.has("realmItemVariantID")) {
            if (json.isNull("realmItemVariantID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field realmItemVariantID to null.");
            } else {
                ((ItemVariantRealmProxyInterface) obj).realmSet$realmItemVariantID((long) json.getLong("realmItemVariantID"));
            }
        }
        if (json.has("itemVariantID")) {
            if (json.isNull("itemVariantID")) {
                ((ItemVariantRealmProxyInterface) obj).realmSet$itemVariantID(null);
            } else {
                ((ItemVariantRealmProxyInterface) obj).realmSet$itemVariantID((String) json.getString("itemVariantID"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static ItemVariant createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        ItemVariant obj = realm.createObject(ItemVariant.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("realmItemVariantID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field realmItemVariantID to null.");
                } else {
                    ((ItemVariantRealmProxyInterface) obj).realmSet$realmItemVariantID((long) reader.nextLong());
                }
            } else if (name.equals("itemVariantID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemVariantRealmProxyInterface) obj).realmSet$itemVariantID(null);
                } else {
                    ((ItemVariantRealmProxyInterface) obj).realmSet$itemVariantID((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static ItemVariant copyOrUpdate(Realm realm, ItemVariant object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static ItemVariant copy(Realm realm, ItemVariant newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        ItemVariant realmObject = realm.createObject(ItemVariant.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((ItemVariantRealmProxyInterface) realmObject).realmSet$realmItemVariantID(((ItemVariantRealmProxyInterface) newObject).realmGet$realmItemVariantID());
        ((ItemVariantRealmProxyInterface) realmObject).realmSet$itemVariantID(((ItemVariantRealmProxyInterface) newObject).realmGet$itemVariantID());
        return realmObject;
    }

    public static ItemVariant createDetachedCopy(ItemVariant realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        ItemVariant standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (ItemVariant)cachedObject.object;
            } else {
                standaloneObject = (ItemVariant)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new ItemVariant();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((ItemVariantRealmProxyInterface) standaloneObject).realmSet$realmItemVariantID(((ItemVariantRealmProxyInterface) realmObject).realmGet$realmItemVariantID());
        ((ItemVariantRealmProxyInterface) standaloneObject).realmSet$itemVariantID(((ItemVariantRealmProxyInterface) realmObject).realmGet$itemVariantID());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("ItemVariant = [");
        stringBuilder.append("{realmItemVariantID:");
        stringBuilder.append(realmGet$realmItemVariantID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemVariantID:");
        stringBuilder.append(realmGet$itemVariantID() != null ? realmGet$itemVariantID() : "null");
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
        ItemVariantRealmProxy aItemVariant = (ItemVariantRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aItemVariant.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aItemVariant.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aItemVariant.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

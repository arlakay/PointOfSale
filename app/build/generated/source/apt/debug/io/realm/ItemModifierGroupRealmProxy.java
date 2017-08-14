package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.ItemModifierGroup;
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

public class ItemModifierGroupRealmProxy extends ItemModifierGroup
    implements RealmObjectProxy, ItemModifierGroupRealmProxyInterface {

    static final class ItemModifierGroupColumnInfo extends ColumnInfo {

        public final long realmItemModifierGroupIDIndex;
        public final long itemModifierGroupIDIndex;

        ItemModifierGroupColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(2);
            this.realmItemModifierGroupIDIndex = getValidColumnIndex(path, table, "ItemModifierGroup", "realmItemModifierGroupID");
            indicesMap.put("realmItemModifierGroupID", this.realmItemModifierGroupIDIndex);

            this.itemModifierGroupIDIndex = getValidColumnIndex(path, table, "ItemModifierGroup", "itemModifierGroupID");
            indicesMap.put("itemModifierGroupID", this.itemModifierGroupIDIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final ItemModifierGroupColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("realmItemModifierGroupID");
        fieldNames.add("itemModifierGroupID");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ItemModifierGroupRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (ItemModifierGroupColumnInfo) columnInfo;
        this.proxyState = new ProxyState(ItemModifierGroup.class);
    }

    @SuppressWarnings("cast")
    public long realmGet$realmItemModifierGroupID() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.realmItemModifierGroupIDIndex);
    }

    public void realmSet$realmItemModifierGroupID(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.realmItemModifierGroupIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$itemModifierGroupID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.itemModifierGroupIDIndex);
    }

    public void realmSet$itemModifierGroupID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.itemModifierGroupIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.itemModifierGroupIDIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_ItemModifierGroup")) {
            Table table = transaction.getTable("class_ItemModifierGroup");
            table.addColumn(RealmFieldType.INTEGER, "realmItemModifierGroupID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "itemModifierGroupID", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_ItemModifierGroup");
    }

    public static ItemModifierGroupColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_ItemModifierGroup")) {
            Table table = transaction.getTable("class_ItemModifierGroup");
            if (table.getColumnCount() != 2) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 2 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 2; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final ItemModifierGroupColumnInfo columnInfo = new ItemModifierGroupColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("realmItemModifierGroupID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'realmItemModifierGroupID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realmItemModifierGroupID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'realmItemModifierGroupID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.realmItemModifierGroupIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'realmItemModifierGroupID' does support null values in the existing Realm file. Use corresponding boxed type for field 'realmItemModifierGroupID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("itemModifierGroupID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'itemModifierGroupID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("itemModifierGroupID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'itemModifierGroupID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.itemModifierGroupIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'itemModifierGroupID' is required. Either set @Required to field 'itemModifierGroupID' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The ItemModifierGroup class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_ItemModifierGroup";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static ItemModifierGroup createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        ItemModifierGroup obj = realm.createObject(ItemModifierGroup.class);
        if (json.has("realmItemModifierGroupID")) {
            if (json.isNull("realmItemModifierGroupID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field realmItemModifierGroupID to null.");
            } else {
                ((ItemModifierGroupRealmProxyInterface) obj).realmSet$realmItemModifierGroupID((long) json.getLong("realmItemModifierGroupID"));
            }
        }
        if (json.has("itemModifierGroupID")) {
            if (json.isNull("itemModifierGroupID")) {
                ((ItemModifierGroupRealmProxyInterface) obj).realmSet$itemModifierGroupID(null);
            } else {
                ((ItemModifierGroupRealmProxyInterface) obj).realmSet$itemModifierGroupID((String) json.getString("itemModifierGroupID"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static ItemModifierGroup createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        ItemModifierGroup obj = realm.createObject(ItemModifierGroup.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("realmItemModifierGroupID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field realmItemModifierGroupID to null.");
                } else {
                    ((ItemModifierGroupRealmProxyInterface) obj).realmSet$realmItemModifierGroupID((long) reader.nextLong());
                }
            } else if (name.equals("itemModifierGroupID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ItemModifierGroupRealmProxyInterface) obj).realmSet$itemModifierGroupID(null);
                } else {
                    ((ItemModifierGroupRealmProxyInterface) obj).realmSet$itemModifierGroupID((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static ItemModifierGroup copyOrUpdate(Realm realm, ItemModifierGroup object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static ItemModifierGroup copy(Realm realm, ItemModifierGroup newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        ItemModifierGroup realmObject = realm.createObject(ItemModifierGroup.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((ItemModifierGroupRealmProxyInterface) realmObject).realmSet$realmItemModifierGroupID(((ItemModifierGroupRealmProxyInterface) newObject).realmGet$realmItemModifierGroupID());
        ((ItemModifierGroupRealmProxyInterface) realmObject).realmSet$itemModifierGroupID(((ItemModifierGroupRealmProxyInterface) newObject).realmGet$itemModifierGroupID());
        return realmObject;
    }

    public static ItemModifierGroup createDetachedCopy(ItemModifierGroup realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        ItemModifierGroup standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (ItemModifierGroup)cachedObject.object;
            } else {
                standaloneObject = (ItemModifierGroup)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new ItemModifierGroup();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((ItemModifierGroupRealmProxyInterface) standaloneObject).realmSet$realmItemModifierGroupID(((ItemModifierGroupRealmProxyInterface) realmObject).realmGet$realmItemModifierGroupID());
        ((ItemModifierGroupRealmProxyInterface) standaloneObject).realmSet$itemModifierGroupID(((ItemModifierGroupRealmProxyInterface) realmObject).realmGet$itemModifierGroupID());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("ItemModifierGroup = [");
        stringBuilder.append("{realmItemModifierGroupID:");
        stringBuilder.append(realmGet$realmItemModifierGroupID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{itemModifierGroupID:");
        stringBuilder.append(realmGet$itemModifierGroupID() != null ? realmGet$itemModifierGroupID() : "null");
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
        ItemModifierGroupRealmProxy aItemModifierGroup = (ItemModifierGroupRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aItemModifierGroup.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aItemModifierGroup.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aItemModifierGroup.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

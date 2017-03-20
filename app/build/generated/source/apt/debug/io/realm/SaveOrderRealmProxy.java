package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.SaveOrder;
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

public class SaveOrderRealmProxy extends SaveOrder
    implements RealmObjectProxy, SaveOrderRealmProxyInterface {

    static final class SaveOrderColumnInfo extends ColumnInfo {

        public final long saveOrderTransactionIDIndex;
        public final long saveOrderNumberTableIndex;
        public final long saveOrderIsBeingSelectedIndex;

        SaveOrderColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.saveOrderTransactionIDIndex = getValidColumnIndex(path, table, "SaveOrder", "saveOrderTransactionID");
            indicesMap.put("saveOrderTransactionID", this.saveOrderTransactionIDIndex);

            this.saveOrderNumberTableIndex = getValidColumnIndex(path, table, "SaveOrder", "saveOrderNumberTable");
            indicesMap.put("saveOrderNumberTable", this.saveOrderNumberTableIndex);

            this.saveOrderIsBeingSelectedIndex = getValidColumnIndex(path, table, "SaveOrder", "saveOrderIsBeingSelected");
            indicesMap.put("saveOrderIsBeingSelected", this.saveOrderIsBeingSelectedIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final SaveOrderColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("saveOrderTransactionID");
        fieldNames.add("saveOrderNumberTable");
        fieldNames.add("saveOrderIsBeingSelected");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    SaveOrderRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (SaveOrderColumnInfo) columnInfo;
        this.proxyState = new ProxyState(SaveOrder.class);
    }

    @SuppressWarnings("cast")
    public String realmGet$saveOrderTransactionID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.saveOrderTransactionIDIndex);
    }

    public void realmSet$saveOrderTransactionID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.saveOrderTransactionIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.saveOrderTransactionIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$saveOrderNumberTable() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.saveOrderNumberTableIndex);
    }

    public void realmSet$saveOrderNumberTable(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.saveOrderNumberTableIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.saveOrderNumberTableIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$saveOrderIsBeingSelected() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.saveOrderIsBeingSelectedIndex);
    }

    public void realmSet$saveOrderIsBeingSelected(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.saveOrderIsBeingSelectedIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.saveOrderIsBeingSelectedIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_SaveOrder")) {
            Table table = transaction.getTable("class_SaveOrder");
            table.addColumn(RealmFieldType.STRING, "saveOrderTransactionID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "saveOrderNumberTable", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "saveOrderIsBeingSelected", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_SaveOrder");
    }

    public static SaveOrderColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_SaveOrder")) {
            Table table = transaction.getTable("class_SaveOrder");
            if (table.getColumnCount() != 3) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 3 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final SaveOrderColumnInfo columnInfo = new SaveOrderColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("saveOrderTransactionID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'saveOrderTransactionID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("saveOrderTransactionID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'saveOrderTransactionID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.saveOrderTransactionIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'saveOrderTransactionID' is required. Either set @Required to field 'saveOrderTransactionID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("saveOrderNumberTable")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'saveOrderNumberTable' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("saveOrderNumberTable") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'saveOrderNumberTable' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.saveOrderNumberTableIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'saveOrderNumberTable' is required. Either set @Required to field 'saveOrderNumberTable' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("saveOrderIsBeingSelected")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'saveOrderIsBeingSelected' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("saveOrderIsBeingSelected") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'saveOrderIsBeingSelected' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.saveOrderIsBeingSelectedIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'saveOrderIsBeingSelected' is required. Either set @Required to field 'saveOrderIsBeingSelected' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The SaveOrder class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_SaveOrder";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static SaveOrder createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        SaveOrder obj = realm.createObject(SaveOrder.class);
        if (json.has("saveOrderTransactionID")) {
            if (json.isNull("saveOrderTransactionID")) {
                ((SaveOrderRealmProxyInterface) obj).realmSet$saveOrderTransactionID(null);
            } else {
                ((SaveOrderRealmProxyInterface) obj).realmSet$saveOrderTransactionID((String) json.getString("saveOrderTransactionID"));
            }
        }
        if (json.has("saveOrderNumberTable")) {
            if (json.isNull("saveOrderNumberTable")) {
                ((SaveOrderRealmProxyInterface) obj).realmSet$saveOrderNumberTable(null);
            } else {
                ((SaveOrderRealmProxyInterface) obj).realmSet$saveOrderNumberTable((String) json.getString("saveOrderNumberTable"));
            }
        }
        if (json.has("saveOrderIsBeingSelected")) {
            if (json.isNull("saveOrderIsBeingSelected")) {
                ((SaveOrderRealmProxyInterface) obj).realmSet$saveOrderIsBeingSelected(null);
            } else {
                ((SaveOrderRealmProxyInterface) obj).realmSet$saveOrderIsBeingSelected((String) json.getString("saveOrderIsBeingSelected"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static SaveOrder createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        SaveOrder obj = realm.createObject(SaveOrder.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("saveOrderTransactionID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((SaveOrderRealmProxyInterface) obj).realmSet$saveOrderTransactionID(null);
                } else {
                    ((SaveOrderRealmProxyInterface) obj).realmSet$saveOrderTransactionID((String) reader.nextString());
                }
            } else if (name.equals("saveOrderNumberTable")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((SaveOrderRealmProxyInterface) obj).realmSet$saveOrderNumberTable(null);
                } else {
                    ((SaveOrderRealmProxyInterface) obj).realmSet$saveOrderNumberTable((String) reader.nextString());
                }
            } else if (name.equals("saveOrderIsBeingSelected")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((SaveOrderRealmProxyInterface) obj).realmSet$saveOrderIsBeingSelected(null);
                } else {
                    ((SaveOrderRealmProxyInterface) obj).realmSet$saveOrderIsBeingSelected((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static SaveOrder copyOrUpdate(Realm realm, SaveOrder object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static SaveOrder copy(Realm realm, SaveOrder newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        SaveOrder realmObject = realm.createObject(SaveOrder.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((SaveOrderRealmProxyInterface) realmObject).realmSet$saveOrderTransactionID(((SaveOrderRealmProxyInterface) newObject).realmGet$saveOrderTransactionID());
        ((SaveOrderRealmProxyInterface) realmObject).realmSet$saveOrderNumberTable(((SaveOrderRealmProxyInterface) newObject).realmGet$saveOrderNumberTable());
        ((SaveOrderRealmProxyInterface) realmObject).realmSet$saveOrderIsBeingSelected(((SaveOrderRealmProxyInterface) newObject).realmGet$saveOrderIsBeingSelected());
        return realmObject;
    }

    public static SaveOrder createDetachedCopy(SaveOrder realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        SaveOrder standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (SaveOrder)cachedObject.object;
            } else {
                standaloneObject = (SaveOrder)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new SaveOrder();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((SaveOrderRealmProxyInterface) standaloneObject).realmSet$saveOrderTransactionID(((SaveOrderRealmProxyInterface) realmObject).realmGet$saveOrderTransactionID());
        ((SaveOrderRealmProxyInterface) standaloneObject).realmSet$saveOrderNumberTable(((SaveOrderRealmProxyInterface) realmObject).realmGet$saveOrderNumberTable());
        ((SaveOrderRealmProxyInterface) standaloneObject).realmSet$saveOrderIsBeingSelected(((SaveOrderRealmProxyInterface) realmObject).realmGet$saveOrderIsBeingSelected());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("SaveOrder = [");
        stringBuilder.append("{saveOrderTransactionID:");
        stringBuilder.append(realmGet$saveOrderTransactionID() != null ? realmGet$saveOrderTransactionID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{saveOrderNumberTable:");
        stringBuilder.append(realmGet$saveOrderNumberTable() != null ? realmGet$saveOrderNumberTable() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{saveOrderIsBeingSelected:");
        stringBuilder.append(realmGet$saveOrderIsBeingSelected() != null ? realmGet$saveOrderIsBeingSelected() : "null");
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
        SaveOrderRealmProxy aSaveOrder = (SaveOrderRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aSaveOrder.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aSaveOrder.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aSaveOrder.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

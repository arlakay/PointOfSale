package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.Service;
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

public class ServiceRealmProxy extends Service
    implements RealmObjectProxy, ServiceRealmProxyInterface {

    static final class ServiceColumnInfo extends ColumnInfo {

        public final long realmServiceIDIndex;
        public final long serviceOutletIDIndex;
        public final long serviceValueIndex;

        ServiceColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.realmServiceIDIndex = getValidColumnIndex(path, table, "Service", "realmServiceID");
            indicesMap.put("realmServiceID", this.realmServiceIDIndex);

            this.serviceOutletIDIndex = getValidColumnIndex(path, table, "Service", "serviceOutletID");
            indicesMap.put("serviceOutletID", this.serviceOutletIDIndex);

            this.serviceValueIndex = getValidColumnIndex(path, table, "Service", "serviceValue");
            indicesMap.put("serviceValue", this.serviceValueIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final ServiceColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("realmServiceID");
        fieldNames.add("serviceOutletID");
        fieldNames.add("serviceValue");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ServiceRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (ServiceColumnInfo) columnInfo;
        this.proxyState = new ProxyState(Service.class);
    }

    @SuppressWarnings("cast")
    public long realmGet$realmServiceID() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.realmServiceIDIndex);
    }

    public void realmSet$realmServiceID(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.realmServiceIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$serviceOutletID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.serviceOutletIDIndex);
    }

    public void realmSet$serviceOutletID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.serviceOutletIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.serviceOutletIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$serviceValue() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.serviceValueIndex);
    }

    public void realmSet$serviceValue(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.serviceValueIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.serviceValueIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Service")) {
            Table table = transaction.getTable("class_Service");
            table.addColumn(RealmFieldType.INTEGER, "realmServiceID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "serviceOutletID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "serviceValue", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_Service");
    }

    public static ServiceColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Service")) {
            Table table = transaction.getTable("class_Service");
            if (table.getColumnCount() != 3) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 3 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final ServiceColumnInfo columnInfo = new ServiceColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("realmServiceID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'realmServiceID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realmServiceID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'realmServiceID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.realmServiceIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'realmServiceID' does support null values in the existing Realm file. Use corresponding boxed type for field 'realmServiceID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("serviceOutletID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'serviceOutletID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("serviceOutletID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'serviceOutletID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.serviceOutletIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'serviceOutletID' is required. Either set @Required to field 'serviceOutletID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("serviceValue")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'serviceValue' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("serviceValue") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'serviceValue' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.serviceValueIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'serviceValue' is required. Either set @Required to field 'serviceValue' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Service class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Service";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static Service createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        Service obj = realm.createObject(Service.class);
        if (json.has("realmServiceID")) {
            if (json.isNull("realmServiceID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field realmServiceID to null.");
            } else {
                ((ServiceRealmProxyInterface) obj).realmSet$realmServiceID((long) json.getLong("realmServiceID"));
            }
        }
        if (json.has("serviceOutletID")) {
            if (json.isNull("serviceOutletID")) {
                ((ServiceRealmProxyInterface) obj).realmSet$serviceOutletID(null);
            } else {
                ((ServiceRealmProxyInterface) obj).realmSet$serviceOutletID((String) json.getString("serviceOutletID"));
            }
        }
        if (json.has("serviceValue")) {
            if (json.isNull("serviceValue")) {
                ((ServiceRealmProxyInterface) obj).realmSet$serviceValue(null);
            } else {
                ((ServiceRealmProxyInterface) obj).realmSet$serviceValue((String) json.getString("serviceValue"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static Service createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        Service obj = realm.createObject(Service.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("realmServiceID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field realmServiceID to null.");
                } else {
                    ((ServiceRealmProxyInterface) obj).realmSet$realmServiceID((long) reader.nextLong());
                }
            } else if (name.equals("serviceOutletID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ServiceRealmProxyInterface) obj).realmSet$serviceOutletID(null);
                } else {
                    ((ServiceRealmProxyInterface) obj).realmSet$serviceOutletID((String) reader.nextString());
                }
            } else if (name.equals("serviceValue")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ServiceRealmProxyInterface) obj).realmSet$serviceValue(null);
                } else {
                    ((ServiceRealmProxyInterface) obj).realmSet$serviceValue((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static Service copyOrUpdate(Realm realm, Service object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static Service copy(Realm realm, Service newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        Service realmObject = realm.createObject(Service.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((ServiceRealmProxyInterface) realmObject).realmSet$realmServiceID(((ServiceRealmProxyInterface) newObject).realmGet$realmServiceID());
        ((ServiceRealmProxyInterface) realmObject).realmSet$serviceOutletID(((ServiceRealmProxyInterface) newObject).realmGet$serviceOutletID());
        ((ServiceRealmProxyInterface) realmObject).realmSet$serviceValue(((ServiceRealmProxyInterface) newObject).realmGet$serviceValue());
        return realmObject;
    }

    public static Service createDetachedCopy(Service realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        Service standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (Service)cachedObject.object;
            } else {
                standaloneObject = (Service)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new Service();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((ServiceRealmProxyInterface) standaloneObject).realmSet$realmServiceID(((ServiceRealmProxyInterface) realmObject).realmGet$realmServiceID());
        ((ServiceRealmProxyInterface) standaloneObject).realmSet$serviceOutletID(((ServiceRealmProxyInterface) realmObject).realmGet$serviceOutletID());
        ((ServiceRealmProxyInterface) standaloneObject).realmSet$serviceValue(((ServiceRealmProxyInterface) realmObject).realmGet$serviceValue());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Service = [");
        stringBuilder.append("{realmServiceID:");
        stringBuilder.append(realmGet$realmServiceID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{serviceOutletID:");
        stringBuilder.append(realmGet$serviceOutletID() != null ? realmGet$serviceOutletID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{serviceValue:");
        stringBuilder.append(realmGet$serviceValue() != null ? realmGet$serviceValue() : "null");
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
        ServiceRealmProxy aService = (ServiceRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aService.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aService.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aService.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

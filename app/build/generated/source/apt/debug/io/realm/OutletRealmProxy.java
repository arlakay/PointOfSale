package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.Outlet;
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

public class OutletRealmProxy extends Outlet
    implements RealmObjectProxy, OutletRealmProxyInterface {

    static final class OutletColumnInfo extends ColumnInfo {

        public final long realmOutletIDIndex;
        public final long outletIDIndex;
        public final long outletNameIndex;
        public final long outletAddressIndex;
        public final long outletPhoneIndex;
        public final long outletTableIndex;

        OutletColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(6);
            this.realmOutletIDIndex = getValidColumnIndex(path, table, "Outlet", "realmOutletID");
            indicesMap.put("realmOutletID", this.realmOutletIDIndex);

            this.outletIDIndex = getValidColumnIndex(path, table, "Outlet", "outletID");
            indicesMap.put("outletID", this.outletIDIndex);

            this.outletNameIndex = getValidColumnIndex(path, table, "Outlet", "outletName");
            indicesMap.put("outletName", this.outletNameIndex);

            this.outletAddressIndex = getValidColumnIndex(path, table, "Outlet", "outletAddress");
            indicesMap.put("outletAddress", this.outletAddressIndex);

            this.outletPhoneIndex = getValidColumnIndex(path, table, "Outlet", "outletPhone");
            indicesMap.put("outletPhone", this.outletPhoneIndex);

            this.outletTableIndex = getValidColumnIndex(path, table, "Outlet", "outletTable");
            indicesMap.put("outletTable", this.outletTableIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final OutletColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("realmOutletID");
        fieldNames.add("outletID");
        fieldNames.add("outletName");
        fieldNames.add("outletAddress");
        fieldNames.add("outletPhone");
        fieldNames.add("outletTable");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    OutletRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (OutletColumnInfo) columnInfo;
        this.proxyState = new ProxyState(Outlet.class);
    }

    @SuppressWarnings("cast")
    public long realmGet$realmOutletID() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.realmOutletIDIndex);
    }

    public void realmSet$realmOutletID(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.realmOutletIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$outletID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.outletIDIndex);
    }

    public void realmSet$outletID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.outletIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.outletIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$outletName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.outletNameIndex);
    }

    public void realmSet$outletName(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.outletNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.outletNameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$outletAddress() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.outletAddressIndex);
    }

    public void realmSet$outletAddress(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.outletAddressIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.outletAddressIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$outletPhone() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.outletPhoneIndex);
    }

    public void realmSet$outletPhone(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.outletPhoneIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.outletPhoneIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$outletTable() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.outletTableIndex);
    }

    public void realmSet$outletTable(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.outletTableIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.outletTableIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Outlet")) {
            Table table = transaction.getTable("class_Outlet");
            table.addColumn(RealmFieldType.INTEGER, "realmOutletID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "outletID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "outletName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "outletAddress", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "outletPhone", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "outletTable", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_Outlet");
    }

    public static OutletColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Outlet")) {
            Table table = transaction.getTable("class_Outlet");
            if (table.getColumnCount() != 6) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 6 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 6; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final OutletColumnInfo columnInfo = new OutletColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("realmOutletID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'realmOutletID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realmOutletID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'realmOutletID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.realmOutletIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'realmOutletID' does support null values in the existing Realm file. Use corresponding boxed type for field 'realmOutletID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("outletID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'outletID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("outletID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'outletID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.outletIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'outletID' is required. Either set @Required to field 'outletID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("outletName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'outletName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("outletName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'outletName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.outletNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'outletName' is required. Either set @Required to field 'outletName' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("outletAddress")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'outletAddress' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("outletAddress") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'outletAddress' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.outletAddressIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'outletAddress' is required. Either set @Required to field 'outletAddress' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("outletPhone")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'outletPhone' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("outletPhone") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'outletPhone' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.outletPhoneIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'outletPhone' is required. Either set @Required to field 'outletPhone' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("outletTable")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'outletTable' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("outletTable") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'outletTable' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.outletTableIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'outletTable' is required. Either set @Required to field 'outletTable' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Outlet class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Outlet";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static Outlet createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        Outlet obj = realm.createObject(Outlet.class);
        if (json.has("realmOutletID")) {
            if (json.isNull("realmOutletID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field realmOutletID to null.");
            } else {
                ((OutletRealmProxyInterface) obj).realmSet$realmOutletID((long) json.getLong("realmOutletID"));
            }
        }
        if (json.has("outletID")) {
            if (json.isNull("outletID")) {
                ((OutletRealmProxyInterface) obj).realmSet$outletID(null);
            } else {
                ((OutletRealmProxyInterface) obj).realmSet$outletID((String) json.getString("outletID"));
            }
        }
        if (json.has("outletName")) {
            if (json.isNull("outletName")) {
                ((OutletRealmProxyInterface) obj).realmSet$outletName(null);
            } else {
                ((OutletRealmProxyInterface) obj).realmSet$outletName((String) json.getString("outletName"));
            }
        }
        if (json.has("outletAddress")) {
            if (json.isNull("outletAddress")) {
                ((OutletRealmProxyInterface) obj).realmSet$outletAddress(null);
            } else {
                ((OutletRealmProxyInterface) obj).realmSet$outletAddress((String) json.getString("outletAddress"));
            }
        }
        if (json.has("outletPhone")) {
            if (json.isNull("outletPhone")) {
                ((OutletRealmProxyInterface) obj).realmSet$outletPhone(null);
            } else {
                ((OutletRealmProxyInterface) obj).realmSet$outletPhone((String) json.getString("outletPhone"));
            }
        }
        if (json.has("outletTable")) {
            if (json.isNull("outletTable")) {
                ((OutletRealmProxyInterface) obj).realmSet$outletTable(null);
            } else {
                ((OutletRealmProxyInterface) obj).realmSet$outletTable((String) json.getString("outletTable"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static Outlet createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        Outlet obj = realm.createObject(Outlet.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("realmOutletID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field realmOutletID to null.");
                } else {
                    ((OutletRealmProxyInterface) obj).realmSet$realmOutletID((long) reader.nextLong());
                }
            } else if (name.equals("outletID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OutletRealmProxyInterface) obj).realmSet$outletID(null);
                } else {
                    ((OutletRealmProxyInterface) obj).realmSet$outletID((String) reader.nextString());
                }
            } else if (name.equals("outletName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OutletRealmProxyInterface) obj).realmSet$outletName(null);
                } else {
                    ((OutletRealmProxyInterface) obj).realmSet$outletName((String) reader.nextString());
                }
            } else if (name.equals("outletAddress")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OutletRealmProxyInterface) obj).realmSet$outletAddress(null);
                } else {
                    ((OutletRealmProxyInterface) obj).realmSet$outletAddress((String) reader.nextString());
                }
            } else if (name.equals("outletPhone")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OutletRealmProxyInterface) obj).realmSet$outletPhone(null);
                } else {
                    ((OutletRealmProxyInterface) obj).realmSet$outletPhone((String) reader.nextString());
                }
            } else if (name.equals("outletTable")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((OutletRealmProxyInterface) obj).realmSet$outletTable(null);
                } else {
                    ((OutletRealmProxyInterface) obj).realmSet$outletTable((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static Outlet copyOrUpdate(Realm realm, Outlet object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static Outlet copy(Realm realm, Outlet newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        Outlet realmObject = realm.createObject(Outlet.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((OutletRealmProxyInterface) realmObject).realmSet$realmOutletID(((OutletRealmProxyInterface) newObject).realmGet$realmOutletID());
        ((OutletRealmProxyInterface) realmObject).realmSet$outletID(((OutletRealmProxyInterface) newObject).realmGet$outletID());
        ((OutletRealmProxyInterface) realmObject).realmSet$outletName(((OutletRealmProxyInterface) newObject).realmGet$outletName());
        ((OutletRealmProxyInterface) realmObject).realmSet$outletAddress(((OutletRealmProxyInterface) newObject).realmGet$outletAddress());
        ((OutletRealmProxyInterface) realmObject).realmSet$outletPhone(((OutletRealmProxyInterface) newObject).realmGet$outletPhone());
        ((OutletRealmProxyInterface) realmObject).realmSet$outletTable(((OutletRealmProxyInterface) newObject).realmGet$outletTable());
        return realmObject;
    }

    public static Outlet createDetachedCopy(Outlet realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        Outlet standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (Outlet)cachedObject.object;
            } else {
                standaloneObject = (Outlet)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new Outlet();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((OutletRealmProxyInterface) standaloneObject).realmSet$realmOutletID(((OutletRealmProxyInterface) realmObject).realmGet$realmOutletID());
        ((OutletRealmProxyInterface) standaloneObject).realmSet$outletID(((OutletRealmProxyInterface) realmObject).realmGet$outletID());
        ((OutletRealmProxyInterface) standaloneObject).realmSet$outletName(((OutletRealmProxyInterface) realmObject).realmGet$outletName());
        ((OutletRealmProxyInterface) standaloneObject).realmSet$outletAddress(((OutletRealmProxyInterface) realmObject).realmGet$outletAddress());
        ((OutletRealmProxyInterface) standaloneObject).realmSet$outletPhone(((OutletRealmProxyInterface) realmObject).realmGet$outletPhone());
        ((OutletRealmProxyInterface) standaloneObject).realmSet$outletTable(((OutletRealmProxyInterface) realmObject).realmGet$outletTable());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Outlet = [");
        stringBuilder.append("{realmOutletID:");
        stringBuilder.append(realmGet$realmOutletID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{outletID:");
        stringBuilder.append(realmGet$outletID() != null ? realmGet$outletID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{outletName:");
        stringBuilder.append(realmGet$outletName() != null ? realmGet$outletName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{outletAddress:");
        stringBuilder.append(realmGet$outletAddress() != null ? realmGet$outletAddress() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{outletPhone:");
        stringBuilder.append(realmGet$outletPhone() != null ? realmGet$outletPhone() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{outletTable:");
        stringBuilder.append(realmGet$outletTable() != null ? realmGet$outletTable() : "null");
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
        OutletRealmProxy aOutlet = (OutletRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aOutlet.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aOutlet.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aOutlet.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

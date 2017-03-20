package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.DiscountMaster;
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

public class DiscountMasterRealmProxy extends DiscountMaster
    implements RealmObjectProxy, DiscountMasterRealmProxyInterface {

    static final class DiscountMasterColumnInfo extends ColumnInfo {

        public final long realmDiscountMasterIDIndex;
        public final long discountMasterIDIndex;
        public final long discountMasterNameIndex;
        public final long discountMasterValueIndex;
        public final long discountMasterTypeIndex;
        public final long discountMasterOutletIDIndex;

        DiscountMasterColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(6);
            this.realmDiscountMasterIDIndex = getValidColumnIndex(path, table, "DiscountMaster", "realmDiscountMasterID");
            indicesMap.put("realmDiscountMasterID", this.realmDiscountMasterIDIndex);

            this.discountMasterIDIndex = getValidColumnIndex(path, table, "DiscountMaster", "discountMasterID");
            indicesMap.put("discountMasterID", this.discountMasterIDIndex);

            this.discountMasterNameIndex = getValidColumnIndex(path, table, "DiscountMaster", "discountMasterName");
            indicesMap.put("discountMasterName", this.discountMasterNameIndex);

            this.discountMasterValueIndex = getValidColumnIndex(path, table, "DiscountMaster", "discountMasterValue");
            indicesMap.put("discountMasterValue", this.discountMasterValueIndex);

            this.discountMasterTypeIndex = getValidColumnIndex(path, table, "DiscountMaster", "discountMasterType");
            indicesMap.put("discountMasterType", this.discountMasterTypeIndex);

            this.discountMasterOutletIDIndex = getValidColumnIndex(path, table, "DiscountMaster", "discountMasterOutletID");
            indicesMap.put("discountMasterOutletID", this.discountMasterOutletIDIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final DiscountMasterColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("realmDiscountMasterID");
        fieldNames.add("discountMasterID");
        fieldNames.add("discountMasterName");
        fieldNames.add("discountMasterValue");
        fieldNames.add("discountMasterType");
        fieldNames.add("discountMasterOutletID");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DiscountMasterRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (DiscountMasterColumnInfo) columnInfo;
        this.proxyState = new ProxyState(DiscountMaster.class);
    }

    @SuppressWarnings("cast")
    public long realmGet$realmDiscountMasterID() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.realmDiscountMasterIDIndex);
    }

    public void realmSet$realmDiscountMasterID(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.realmDiscountMasterIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$discountMasterID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.discountMasterIDIndex);
    }

    public void realmSet$discountMasterID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.discountMasterIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.discountMasterIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$discountMasterName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.discountMasterNameIndex);
    }

    public void realmSet$discountMasterName(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.discountMasterNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.discountMasterNameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$discountMasterValue() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.discountMasterValueIndex);
    }

    public void realmSet$discountMasterValue(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.discountMasterValueIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.discountMasterValueIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$discountMasterType() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.discountMasterTypeIndex);
    }

    public void realmSet$discountMasterType(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.discountMasterTypeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.discountMasterTypeIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$discountMasterOutletID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.discountMasterOutletIDIndex);
    }

    public void realmSet$discountMasterOutletID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.discountMasterOutletIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.discountMasterOutletIDIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_DiscountMaster")) {
            Table table = transaction.getTable("class_DiscountMaster");
            table.addColumn(RealmFieldType.INTEGER, "realmDiscountMasterID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "discountMasterID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "discountMasterName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "discountMasterValue", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "discountMasterType", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "discountMasterOutletID", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_DiscountMaster");
    }

    public static DiscountMasterColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_DiscountMaster")) {
            Table table = transaction.getTable("class_DiscountMaster");
            if (table.getColumnCount() != 6) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 6 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 6; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final DiscountMasterColumnInfo columnInfo = new DiscountMasterColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("realmDiscountMasterID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'realmDiscountMasterID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realmDiscountMasterID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'realmDiscountMasterID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.realmDiscountMasterIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'realmDiscountMasterID' does support null values in the existing Realm file. Use corresponding boxed type for field 'realmDiscountMasterID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("discountMasterID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'discountMasterID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("discountMasterID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'discountMasterID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.discountMasterIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'discountMasterID' is required. Either set @Required to field 'discountMasterID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("discountMasterName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'discountMasterName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("discountMasterName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'discountMasterName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.discountMasterNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'discountMasterName' is required. Either set @Required to field 'discountMasterName' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("discountMasterValue")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'discountMasterValue' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("discountMasterValue") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'discountMasterValue' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.discountMasterValueIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'discountMasterValue' is required. Either set @Required to field 'discountMasterValue' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("discountMasterType")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'discountMasterType' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("discountMasterType") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'discountMasterType' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.discountMasterTypeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'discountMasterType' is required. Either set @Required to field 'discountMasterType' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("discountMasterOutletID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'discountMasterOutletID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("discountMasterOutletID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'discountMasterOutletID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.discountMasterOutletIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'discountMasterOutletID' is required. Either set @Required to field 'discountMasterOutletID' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The DiscountMaster class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_DiscountMaster";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static DiscountMaster createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        DiscountMaster obj = realm.createObject(DiscountMaster.class);
        if (json.has("realmDiscountMasterID")) {
            if (json.isNull("realmDiscountMasterID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field realmDiscountMasterID to null.");
            } else {
                ((DiscountMasterRealmProxyInterface) obj).realmSet$realmDiscountMasterID((long) json.getLong("realmDiscountMasterID"));
            }
        }
        if (json.has("discountMasterID")) {
            if (json.isNull("discountMasterID")) {
                ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterID(null);
            } else {
                ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterID((String) json.getString("discountMasterID"));
            }
        }
        if (json.has("discountMasterName")) {
            if (json.isNull("discountMasterName")) {
                ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterName(null);
            } else {
                ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterName((String) json.getString("discountMasterName"));
            }
        }
        if (json.has("discountMasterValue")) {
            if (json.isNull("discountMasterValue")) {
                ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterValue(null);
            } else {
                ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterValue((String) json.getString("discountMasterValue"));
            }
        }
        if (json.has("discountMasterType")) {
            if (json.isNull("discountMasterType")) {
                ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterType(null);
            } else {
                ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterType((String) json.getString("discountMasterType"));
            }
        }
        if (json.has("discountMasterOutletID")) {
            if (json.isNull("discountMasterOutletID")) {
                ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterOutletID(null);
            } else {
                ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterOutletID((String) json.getString("discountMasterOutletID"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static DiscountMaster createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        DiscountMaster obj = realm.createObject(DiscountMaster.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("realmDiscountMasterID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field realmDiscountMasterID to null.");
                } else {
                    ((DiscountMasterRealmProxyInterface) obj).realmSet$realmDiscountMasterID((long) reader.nextLong());
                }
            } else if (name.equals("discountMasterID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterID(null);
                } else {
                    ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterID((String) reader.nextString());
                }
            } else if (name.equals("discountMasterName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterName(null);
                } else {
                    ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterName((String) reader.nextString());
                }
            } else if (name.equals("discountMasterValue")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterValue(null);
                } else {
                    ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterValue((String) reader.nextString());
                }
            } else if (name.equals("discountMasterType")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterType(null);
                } else {
                    ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterType((String) reader.nextString());
                }
            } else if (name.equals("discountMasterOutletID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterOutletID(null);
                } else {
                    ((DiscountMasterRealmProxyInterface) obj).realmSet$discountMasterOutletID((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static DiscountMaster copyOrUpdate(Realm realm, DiscountMaster object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static DiscountMaster copy(Realm realm, DiscountMaster newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        DiscountMaster realmObject = realm.createObject(DiscountMaster.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((DiscountMasterRealmProxyInterface) realmObject).realmSet$realmDiscountMasterID(((DiscountMasterRealmProxyInterface) newObject).realmGet$realmDiscountMasterID());
        ((DiscountMasterRealmProxyInterface) realmObject).realmSet$discountMasterID(((DiscountMasterRealmProxyInterface) newObject).realmGet$discountMasterID());
        ((DiscountMasterRealmProxyInterface) realmObject).realmSet$discountMasterName(((DiscountMasterRealmProxyInterface) newObject).realmGet$discountMasterName());
        ((DiscountMasterRealmProxyInterface) realmObject).realmSet$discountMasterValue(((DiscountMasterRealmProxyInterface) newObject).realmGet$discountMasterValue());
        ((DiscountMasterRealmProxyInterface) realmObject).realmSet$discountMasterType(((DiscountMasterRealmProxyInterface) newObject).realmGet$discountMasterType());
        ((DiscountMasterRealmProxyInterface) realmObject).realmSet$discountMasterOutletID(((DiscountMasterRealmProxyInterface) newObject).realmGet$discountMasterOutletID());
        return realmObject;
    }

    public static DiscountMaster createDetachedCopy(DiscountMaster realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        DiscountMaster standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (DiscountMaster)cachedObject.object;
            } else {
                standaloneObject = (DiscountMaster)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new DiscountMaster();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((DiscountMasterRealmProxyInterface) standaloneObject).realmSet$realmDiscountMasterID(((DiscountMasterRealmProxyInterface) realmObject).realmGet$realmDiscountMasterID());
        ((DiscountMasterRealmProxyInterface) standaloneObject).realmSet$discountMasterID(((DiscountMasterRealmProxyInterface) realmObject).realmGet$discountMasterID());
        ((DiscountMasterRealmProxyInterface) standaloneObject).realmSet$discountMasterName(((DiscountMasterRealmProxyInterface) realmObject).realmGet$discountMasterName());
        ((DiscountMasterRealmProxyInterface) standaloneObject).realmSet$discountMasterValue(((DiscountMasterRealmProxyInterface) realmObject).realmGet$discountMasterValue());
        ((DiscountMasterRealmProxyInterface) standaloneObject).realmSet$discountMasterType(((DiscountMasterRealmProxyInterface) realmObject).realmGet$discountMasterType());
        ((DiscountMasterRealmProxyInterface) standaloneObject).realmSet$discountMasterOutletID(((DiscountMasterRealmProxyInterface) realmObject).realmGet$discountMasterOutletID());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("DiscountMaster = [");
        stringBuilder.append("{realmDiscountMasterID:");
        stringBuilder.append(realmGet$realmDiscountMasterID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{discountMasterID:");
        stringBuilder.append(realmGet$discountMasterID() != null ? realmGet$discountMasterID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{discountMasterName:");
        stringBuilder.append(realmGet$discountMasterName() != null ? realmGet$discountMasterName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{discountMasterValue:");
        stringBuilder.append(realmGet$discountMasterValue() != null ? realmGet$discountMasterValue() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{discountMasterType:");
        stringBuilder.append(realmGet$discountMasterType() != null ? realmGet$discountMasterType() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{discountMasterOutletID:");
        stringBuilder.append(realmGet$discountMasterOutletID() != null ? realmGet$discountMasterOutletID() : "null");
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
        DiscountMasterRealmProxy aDiscountMaster = (DiscountMasterRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aDiscountMaster.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aDiscountMaster.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aDiscountMaster.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

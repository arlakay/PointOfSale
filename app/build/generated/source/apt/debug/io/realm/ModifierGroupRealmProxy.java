package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.ModifierGroup;
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

public class ModifierGroupRealmProxy extends ModifierGroup
    implements RealmObjectProxy, ModifierGroupRealmProxyInterface {

    static final class ModifierGroupColumnInfo extends ColumnInfo {

        public final long realmModifierGroupIDIndex;
        public final long modifierGroupIDIndex;
        public final long modifierGroupNameIndex;
        public final long modifierGroupOptionFlagIndex;
        public final long modifierGroupOutletIDIndex;

        ModifierGroupColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(5);
            this.realmModifierGroupIDIndex = getValidColumnIndex(path, table, "ModifierGroup", "realmModifierGroupID");
            indicesMap.put("realmModifierGroupID", this.realmModifierGroupIDIndex);

            this.modifierGroupIDIndex = getValidColumnIndex(path, table, "ModifierGroup", "modifierGroupID");
            indicesMap.put("modifierGroupID", this.modifierGroupIDIndex);

            this.modifierGroupNameIndex = getValidColumnIndex(path, table, "ModifierGroup", "modifierGroupName");
            indicesMap.put("modifierGroupName", this.modifierGroupNameIndex);

            this.modifierGroupOptionFlagIndex = getValidColumnIndex(path, table, "ModifierGroup", "modifierGroupOptionFlag");
            indicesMap.put("modifierGroupOptionFlag", this.modifierGroupOptionFlagIndex);

            this.modifierGroupOutletIDIndex = getValidColumnIndex(path, table, "ModifierGroup", "modifierGroupOutletID");
            indicesMap.put("modifierGroupOutletID", this.modifierGroupOutletIDIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final ModifierGroupColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("realmModifierGroupID");
        fieldNames.add("modifierGroupID");
        fieldNames.add("modifierGroupName");
        fieldNames.add("modifierGroupOptionFlag");
        fieldNames.add("modifierGroupOutletID");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ModifierGroupRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (ModifierGroupColumnInfo) columnInfo;
        this.proxyState = new ProxyState(ModifierGroup.class);
    }

    @SuppressWarnings("cast")
    public long realmGet$realmModifierGroupID() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.realmModifierGroupIDIndex);
    }

    public void realmSet$realmModifierGroupID(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.realmModifierGroupIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$modifierGroupID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.modifierGroupIDIndex);
    }

    public void realmSet$modifierGroupID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.modifierGroupIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.modifierGroupIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$modifierGroupName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.modifierGroupNameIndex);
    }

    public void realmSet$modifierGroupName(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.modifierGroupNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.modifierGroupNameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$modifierGroupOptionFlag() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.modifierGroupOptionFlagIndex);
    }

    public void realmSet$modifierGroupOptionFlag(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.modifierGroupOptionFlagIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.modifierGroupOptionFlagIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$modifierGroupOutletID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.modifierGroupOutletIDIndex);
    }

    public void realmSet$modifierGroupOutletID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.modifierGroupOutletIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.modifierGroupOutletIDIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_ModifierGroup")) {
            Table table = transaction.getTable("class_ModifierGroup");
            table.addColumn(RealmFieldType.INTEGER, "realmModifierGroupID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "modifierGroupID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "modifierGroupName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "modifierGroupOptionFlag", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "modifierGroupOutletID", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_ModifierGroup");
    }

    public static ModifierGroupColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_ModifierGroup")) {
            Table table = transaction.getTable("class_ModifierGroup");
            if (table.getColumnCount() != 5) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 5 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 5; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final ModifierGroupColumnInfo columnInfo = new ModifierGroupColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("realmModifierGroupID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'realmModifierGroupID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realmModifierGroupID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'realmModifierGroupID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.realmModifierGroupIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'realmModifierGroupID' does support null values in the existing Realm file. Use corresponding boxed type for field 'realmModifierGroupID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("modifierGroupID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'modifierGroupID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("modifierGroupID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'modifierGroupID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.modifierGroupIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'modifierGroupID' is required. Either set @Required to field 'modifierGroupID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("modifierGroupName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'modifierGroupName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("modifierGroupName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'modifierGroupName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.modifierGroupNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'modifierGroupName' is required. Either set @Required to field 'modifierGroupName' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("modifierGroupOptionFlag")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'modifierGroupOptionFlag' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("modifierGroupOptionFlag") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'modifierGroupOptionFlag' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.modifierGroupOptionFlagIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'modifierGroupOptionFlag' is required. Either set @Required to field 'modifierGroupOptionFlag' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("modifierGroupOutletID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'modifierGroupOutletID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("modifierGroupOutletID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'modifierGroupOutletID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.modifierGroupOutletIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'modifierGroupOutletID' is required. Either set @Required to field 'modifierGroupOutletID' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The ModifierGroup class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_ModifierGroup";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static ModifierGroup createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        ModifierGroup obj = realm.createObject(ModifierGroup.class);
        if (json.has("realmModifierGroupID")) {
            if (json.isNull("realmModifierGroupID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field realmModifierGroupID to null.");
            } else {
                ((ModifierGroupRealmProxyInterface) obj).realmSet$realmModifierGroupID((long) json.getLong("realmModifierGroupID"));
            }
        }
        if (json.has("modifierGroupID")) {
            if (json.isNull("modifierGroupID")) {
                ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupID(null);
            } else {
                ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupID((String) json.getString("modifierGroupID"));
            }
        }
        if (json.has("modifierGroupName")) {
            if (json.isNull("modifierGroupName")) {
                ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupName(null);
            } else {
                ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupName((String) json.getString("modifierGroupName"));
            }
        }
        if (json.has("modifierGroupOptionFlag")) {
            if (json.isNull("modifierGroupOptionFlag")) {
                ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupOptionFlag(null);
            } else {
                ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupOptionFlag((String) json.getString("modifierGroupOptionFlag"));
            }
        }
        if (json.has("modifierGroupOutletID")) {
            if (json.isNull("modifierGroupOutletID")) {
                ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupOutletID(null);
            } else {
                ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupOutletID((String) json.getString("modifierGroupOutletID"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static ModifierGroup createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        ModifierGroup obj = realm.createObject(ModifierGroup.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("realmModifierGroupID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field realmModifierGroupID to null.");
                } else {
                    ((ModifierGroupRealmProxyInterface) obj).realmSet$realmModifierGroupID((long) reader.nextLong());
                }
            } else if (name.equals("modifierGroupID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupID(null);
                } else {
                    ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupID((String) reader.nextString());
                }
            } else if (name.equals("modifierGroupName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupName(null);
                } else {
                    ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupName((String) reader.nextString());
                }
            } else if (name.equals("modifierGroupOptionFlag")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupOptionFlag(null);
                } else {
                    ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupOptionFlag((String) reader.nextString());
                }
            } else if (name.equals("modifierGroupOutletID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupOutletID(null);
                } else {
                    ((ModifierGroupRealmProxyInterface) obj).realmSet$modifierGroupOutletID((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static ModifierGroup copyOrUpdate(Realm realm, ModifierGroup object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static ModifierGroup copy(Realm realm, ModifierGroup newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        ModifierGroup realmObject = realm.createObject(ModifierGroup.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((ModifierGroupRealmProxyInterface) realmObject).realmSet$realmModifierGroupID(((ModifierGroupRealmProxyInterface) newObject).realmGet$realmModifierGroupID());
        ((ModifierGroupRealmProxyInterface) realmObject).realmSet$modifierGroupID(((ModifierGroupRealmProxyInterface) newObject).realmGet$modifierGroupID());
        ((ModifierGroupRealmProxyInterface) realmObject).realmSet$modifierGroupName(((ModifierGroupRealmProxyInterface) newObject).realmGet$modifierGroupName());
        ((ModifierGroupRealmProxyInterface) realmObject).realmSet$modifierGroupOptionFlag(((ModifierGroupRealmProxyInterface) newObject).realmGet$modifierGroupOptionFlag());
        ((ModifierGroupRealmProxyInterface) realmObject).realmSet$modifierGroupOutletID(((ModifierGroupRealmProxyInterface) newObject).realmGet$modifierGroupOutletID());
        return realmObject;
    }

    public static ModifierGroup createDetachedCopy(ModifierGroup realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        ModifierGroup standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (ModifierGroup)cachedObject.object;
            } else {
                standaloneObject = (ModifierGroup)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new ModifierGroup();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((ModifierGroupRealmProxyInterface) standaloneObject).realmSet$realmModifierGroupID(((ModifierGroupRealmProxyInterface) realmObject).realmGet$realmModifierGroupID());
        ((ModifierGroupRealmProxyInterface) standaloneObject).realmSet$modifierGroupID(((ModifierGroupRealmProxyInterface) realmObject).realmGet$modifierGroupID());
        ((ModifierGroupRealmProxyInterface) standaloneObject).realmSet$modifierGroupName(((ModifierGroupRealmProxyInterface) realmObject).realmGet$modifierGroupName());
        ((ModifierGroupRealmProxyInterface) standaloneObject).realmSet$modifierGroupOptionFlag(((ModifierGroupRealmProxyInterface) realmObject).realmGet$modifierGroupOptionFlag());
        ((ModifierGroupRealmProxyInterface) standaloneObject).realmSet$modifierGroupOutletID(((ModifierGroupRealmProxyInterface) realmObject).realmGet$modifierGroupOutletID());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("ModifierGroup = [");
        stringBuilder.append("{realmModifierGroupID:");
        stringBuilder.append(realmGet$realmModifierGroupID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{modifierGroupID:");
        stringBuilder.append(realmGet$modifierGroupID() != null ? realmGet$modifierGroupID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{modifierGroupName:");
        stringBuilder.append(realmGet$modifierGroupName() != null ? realmGet$modifierGroupName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{modifierGroupOptionFlag:");
        stringBuilder.append(realmGet$modifierGroupOptionFlag() != null ? realmGet$modifierGroupOptionFlag() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{modifierGroupOutletID:");
        stringBuilder.append(realmGet$modifierGroupOutletID() != null ? realmGet$modifierGroupOutletID() : "null");
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
        ModifierGroupRealmProxy aModifierGroup = (ModifierGroupRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aModifierGroup.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aModifierGroup.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aModifierGroup.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.ModifierDetail;
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

public class ModifierDetailRealmProxy extends ModifierDetail
    implements RealmObjectProxy, ModifierDetailRealmProxyInterface {

    static final class ModifierDetailColumnInfo extends ColumnInfo {

        public final long realmModifierDetailIDIndex;
        public final long modifierDetailIDIndex;
        public final long modifierDetailNameIndex;
        public final long modifierDetailPriceIndex;
        public final long modifierDetailGroupIDIndex;

        ModifierDetailColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(5);
            this.realmModifierDetailIDIndex = getValidColumnIndex(path, table, "ModifierDetail", "realmModifierDetailID");
            indicesMap.put("realmModifierDetailID", this.realmModifierDetailIDIndex);

            this.modifierDetailIDIndex = getValidColumnIndex(path, table, "ModifierDetail", "modifierDetailID");
            indicesMap.put("modifierDetailID", this.modifierDetailIDIndex);

            this.modifierDetailNameIndex = getValidColumnIndex(path, table, "ModifierDetail", "modifierDetailName");
            indicesMap.put("modifierDetailName", this.modifierDetailNameIndex);

            this.modifierDetailPriceIndex = getValidColumnIndex(path, table, "ModifierDetail", "modifierDetailPrice");
            indicesMap.put("modifierDetailPrice", this.modifierDetailPriceIndex);

            this.modifierDetailGroupIDIndex = getValidColumnIndex(path, table, "ModifierDetail", "modifierDetailGroupID");
            indicesMap.put("modifierDetailGroupID", this.modifierDetailGroupIDIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final ModifierDetailColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("realmModifierDetailID");
        fieldNames.add("modifierDetailID");
        fieldNames.add("modifierDetailName");
        fieldNames.add("modifierDetailPrice");
        fieldNames.add("modifierDetailGroupID");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ModifierDetailRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (ModifierDetailColumnInfo) columnInfo;
        this.proxyState = new ProxyState(ModifierDetail.class);
    }

    @SuppressWarnings("cast")
    public long realmGet$realmModifierDetailID() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.realmModifierDetailIDIndex);
    }

    public void realmSet$realmModifierDetailID(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.realmModifierDetailIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$modifierDetailID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.modifierDetailIDIndex);
    }

    public void realmSet$modifierDetailID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.modifierDetailIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.modifierDetailIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$modifierDetailName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.modifierDetailNameIndex);
    }

    public void realmSet$modifierDetailName(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.modifierDetailNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.modifierDetailNameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$modifierDetailPrice() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.modifierDetailPriceIndex);
    }

    public void realmSet$modifierDetailPrice(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.modifierDetailPriceIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.modifierDetailPriceIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$modifierDetailGroupID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.modifierDetailGroupIDIndex);
    }

    public void realmSet$modifierDetailGroupID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.modifierDetailGroupIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.modifierDetailGroupIDIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_ModifierDetail")) {
            Table table = transaction.getTable("class_ModifierDetail");
            table.addColumn(RealmFieldType.INTEGER, "realmModifierDetailID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "modifierDetailID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "modifierDetailName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "modifierDetailPrice", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "modifierDetailGroupID", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_ModifierDetail");
    }

    public static ModifierDetailColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_ModifierDetail")) {
            Table table = transaction.getTable("class_ModifierDetail");
            if (table.getColumnCount() != 5) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 5 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 5; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final ModifierDetailColumnInfo columnInfo = new ModifierDetailColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("realmModifierDetailID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'realmModifierDetailID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realmModifierDetailID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'realmModifierDetailID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.realmModifierDetailIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'realmModifierDetailID' does support null values in the existing Realm file. Use corresponding boxed type for field 'realmModifierDetailID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("modifierDetailID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'modifierDetailID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("modifierDetailID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'modifierDetailID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.modifierDetailIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'modifierDetailID' is required. Either set @Required to field 'modifierDetailID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("modifierDetailName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'modifierDetailName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("modifierDetailName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'modifierDetailName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.modifierDetailNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'modifierDetailName' is required. Either set @Required to field 'modifierDetailName' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("modifierDetailPrice")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'modifierDetailPrice' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("modifierDetailPrice") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'modifierDetailPrice' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.modifierDetailPriceIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'modifierDetailPrice' is required. Either set @Required to field 'modifierDetailPrice' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("modifierDetailGroupID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'modifierDetailGroupID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("modifierDetailGroupID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'modifierDetailGroupID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.modifierDetailGroupIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'modifierDetailGroupID' is required. Either set @Required to field 'modifierDetailGroupID' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The ModifierDetail class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_ModifierDetail";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static ModifierDetail createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        ModifierDetail obj = realm.createObject(ModifierDetail.class);
        if (json.has("realmModifierDetailID")) {
            if (json.isNull("realmModifierDetailID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field realmModifierDetailID to null.");
            } else {
                ((ModifierDetailRealmProxyInterface) obj).realmSet$realmModifierDetailID((long) json.getLong("realmModifierDetailID"));
            }
        }
        if (json.has("modifierDetailID")) {
            if (json.isNull("modifierDetailID")) {
                ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailID(null);
            } else {
                ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailID((String) json.getString("modifierDetailID"));
            }
        }
        if (json.has("modifierDetailName")) {
            if (json.isNull("modifierDetailName")) {
                ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailName(null);
            } else {
                ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailName((String) json.getString("modifierDetailName"));
            }
        }
        if (json.has("modifierDetailPrice")) {
            if (json.isNull("modifierDetailPrice")) {
                ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailPrice(null);
            } else {
                ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailPrice((String) json.getString("modifierDetailPrice"));
            }
        }
        if (json.has("modifierDetailGroupID")) {
            if (json.isNull("modifierDetailGroupID")) {
                ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailGroupID(null);
            } else {
                ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailGroupID((String) json.getString("modifierDetailGroupID"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static ModifierDetail createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        ModifierDetail obj = realm.createObject(ModifierDetail.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("realmModifierDetailID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field realmModifierDetailID to null.");
                } else {
                    ((ModifierDetailRealmProxyInterface) obj).realmSet$realmModifierDetailID((long) reader.nextLong());
                }
            } else if (name.equals("modifierDetailID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailID(null);
                } else {
                    ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailID((String) reader.nextString());
                }
            } else if (name.equals("modifierDetailName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailName(null);
                } else {
                    ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailName((String) reader.nextString());
                }
            } else if (name.equals("modifierDetailPrice")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailPrice(null);
                } else {
                    ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailPrice((String) reader.nextString());
                }
            } else if (name.equals("modifierDetailGroupID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailGroupID(null);
                } else {
                    ((ModifierDetailRealmProxyInterface) obj).realmSet$modifierDetailGroupID((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static ModifierDetail copyOrUpdate(Realm realm, ModifierDetail object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static ModifierDetail copy(Realm realm, ModifierDetail newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        ModifierDetail realmObject = realm.createObject(ModifierDetail.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((ModifierDetailRealmProxyInterface) realmObject).realmSet$realmModifierDetailID(((ModifierDetailRealmProxyInterface) newObject).realmGet$realmModifierDetailID());
        ((ModifierDetailRealmProxyInterface) realmObject).realmSet$modifierDetailID(((ModifierDetailRealmProxyInterface) newObject).realmGet$modifierDetailID());
        ((ModifierDetailRealmProxyInterface) realmObject).realmSet$modifierDetailName(((ModifierDetailRealmProxyInterface) newObject).realmGet$modifierDetailName());
        ((ModifierDetailRealmProxyInterface) realmObject).realmSet$modifierDetailPrice(((ModifierDetailRealmProxyInterface) newObject).realmGet$modifierDetailPrice());
        ((ModifierDetailRealmProxyInterface) realmObject).realmSet$modifierDetailGroupID(((ModifierDetailRealmProxyInterface) newObject).realmGet$modifierDetailGroupID());
        return realmObject;
    }

    public static ModifierDetail createDetachedCopy(ModifierDetail realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        ModifierDetail standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (ModifierDetail)cachedObject.object;
            } else {
                standaloneObject = (ModifierDetail)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new ModifierDetail();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((ModifierDetailRealmProxyInterface) standaloneObject).realmSet$realmModifierDetailID(((ModifierDetailRealmProxyInterface) realmObject).realmGet$realmModifierDetailID());
        ((ModifierDetailRealmProxyInterface) standaloneObject).realmSet$modifierDetailID(((ModifierDetailRealmProxyInterface) realmObject).realmGet$modifierDetailID());
        ((ModifierDetailRealmProxyInterface) standaloneObject).realmSet$modifierDetailName(((ModifierDetailRealmProxyInterface) realmObject).realmGet$modifierDetailName());
        ((ModifierDetailRealmProxyInterface) standaloneObject).realmSet$modifierDetailPrice(((ModifierDetailRealmProxyInterface) realmObject).realmGet$modifierDetailPrice());
        ((ModifierDetailRealmProxyInterface) standaloneObject).realmSet$modifierDetailGroupID(((ModifierDetailRealmProxyInterface) realmObject).realmGet$modifierDetailGroupID());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("ModifierDetail = [");
        stringBuilder.append("{realmModifierDetailID:");
        stringBuilder.append(realmGet$realmModifierDetailID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{modifierDetailID:");
        stringBuilder.append(realmGet$modifierDetailID() != null ? realmGet$modifierDetailID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{modifierDetailName:");
        stringBuilder.append(realmGet$modifierDetailName() != null ? realmGet$modifierDetailName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{modifierDetailPrice:");
        stringBuilder.append(realmGet$modifierDetailPrice() != null ? realmGet$modifierDetailPrice() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{modifierDetailGroupID:");
        stringBuilder.append(realmGet$modifierDetailGroupID() != null ? realmGet$modifierDetailGroupID() : "null");
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
        ModifierDetailRealmProxy aModifierDetail = (ModifierDetailRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aModifierDetail.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aModifierDetail.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aModifierDetail.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

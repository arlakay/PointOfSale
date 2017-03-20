package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.Variant;
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

public class VariantRealmProxy extends Variant
    implements RealmObjectProxy, VariantRealmProxyInterface {

    static final class VariantColumnInfo extends ColumnInfo {

        public final long realmVariantIDIndex;
        public final long VariantIDIndex;
        public final long VariantNameIndex;
        public final long VariantPriceIndex;

        VariantColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(4);
            this.realmVariantIDIndex = getValidColumnIndex(path, table, "Variant", "realmVariantID");
            indicesMap.put("realmVariantID", this.realmVariantIDIndex);

            this.VariantIDIndex = getValidColumnIndex(path, table, "Variant", "VariantID");
            indicesMap.put("VariantID", this.VariantIDIndex);

            this.VariantNameIndex = getValidColumnIndex(path, table, "Variant", "VariantName");
            indicesMap.put("VariantName", this.VariantNameIndex);

            this.VariantPriceIndex = getValidColumnIndex(path, table, "Variant", "VariantPrice");
            indicesMap.put("VariantPrice", this.VariantPriceIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final VariantColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("realmVariantID");
        fieldNames.add("VariantID");
        fieldNames.add("VariantName");
        fieldNames.add("VariantPrice");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    VariantRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (VariantColumnInfo) columnInfo;
        this.proxyState = new ProxyState(Variant.class);
    }

    @SuppressWarnings("cast")
    public long realmGet$realmVariantID() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.realmVariantIDIndex);
    }

    public void realmSet$realmVariantID(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.realmVariantIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$VariantID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.VariantIDIndex);
    }

    public void realmSet$VariantID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.VariantIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.VariantIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$VariantName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.VariantNameIndex);
    }

    public void realmSet$VariantName(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.VariantNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.VariantNameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$VariantPrice() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.VariantPriceIndex);
    }

    public void realmSet$VariantPrice(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.VariantPriceIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.VariantPriceIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Variant")) {
            Table table = transaction.getTable("class_Variant");
            table.addColumn(RealmFieldType.INTEGER, "realmVariantID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "VariantID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "VariantName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "VariantPrice", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_Variant");
    }

    public static VariantColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Variant")) {
            Table table = transaction.getTable("class_Variant");
            if (table.getColumnCount() != 4) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 4 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 4; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final VariantColumnInfo columnInfo = new VariantColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("realmVariantID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'realmVariantID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realmVariantID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'realmVariantID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.realmVariantIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'realmVariantID' does support null values in the existing Realm file. Use corresponding boxed type for field 'realmVariantID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("VariantID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'VariantID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("VariantID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'VariantID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.VariantIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'VariantID' is required. Either set @Required to field 'VariantID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("VariantName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'VariantName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("VariantName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'VariantName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.VariantNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'VariantName' is required. Either set @Required to field 'VariantName' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("VariantPrice")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'VariantPrice' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("VariantPrice") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'VariantPrice' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.VariantPriceIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'VariantPrice' is required. Either set @Required to field 'VariantPrice' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Variant class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Variant";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static Variant createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        Variant obj = realm.createObject(Variant.class);
        if (json.has("realmVariantID")) {
            if (json.isNull("realmVariantID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field realmVariantID to null.");
            } else {
                ((VariantRealmProxyInterface) obj).realmSet$realmVariantID((long) json.getLong("realmVariantID"));
            }
        }
        if (json.has("VariantID")) {
            if (json.isNull("VariantID")) {
                ((VariantRealmProxyInterface) obj).realmSet$VariantID(null);
            } else {
                ((VariantRealmProxyInterface) obj).realmSet$VariantID((String) json.getString("VariantID"));
            }
        }
        if (json.has("VariantName")) {
            if (json.isNull("VariantName")) {
                ((VariantRealmProxyInterface) obj).realmSet$VariantName(null);
            } else {
                ((VariantRealmProxyInterface) obj).realmSet$VariantName((String) json.getString("VariantName"));
            }
        }
        if (json.has("VariantPrice")) {
            if (json.isNull("VariantPrice")) {
                ((VariantRealmProxyInterface) obj).realmSet$VariantPrice(null);
            } else {
                ((VariantRealmProxyInterface) obj).realmSet$VariantPrice((String) json.getString("VariantPrice"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static Variant createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        Variant obj = realm.createObject(Variant.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("realmVariantID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field realmVariantID to null.");
                } else {
                    ((VariantRealmProxyInterface) obj).realmSet$realmVariantID((long) reader.nextLong());
                }
            } else if (name.equals("VariantID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((VariantRealmProxyInterface) obj).realmSet$VariantID(null);
                } else {
                    ((VariantRealmProxyInterface) obj).realmSet$VariantID((String) reader.nextString());
                }
            } else if (name.equals("VariantName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((VariantRealmProxyInterface) obj).realmSet$VariantName(null);
                } else {
                    ((VariantRealmProxyInterface) obj).realmSet$VariantName((String) reader.nextString());
                }
            } else if (name.equals("VariantPrice")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((VariantRealmProxyInterface) obj).realmSet$VariantPrice(null);
                } else {
                    ((VariantRealmProxyInterface) obj).realmSet$VariantPrice((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static Variant copyOrUpdate(Realm realm, Variant object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static Variant copy(Realm realm, Variant newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        Variant realmObject = realm.createObject(Variant.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((VariantRealmProxyInterface) realmObject).realmSet$realmVariantID(((VariantRealmProxyInterface) newObject).realmGet$realmVariantID());
        ((VariantRealmProxyInterface) realmObject).realmSet$VariantID(((VariantRealmProxyInterface) newObject).realmGet$VariantID());
        ((VariantRealmProxyInterface) realmObject).realmSet$VariantName(((VariantRealmProxyInterface) newObject).realmGet$VariantName());
        ((VariantRealmProxyInterface) realmObject).realmSet$VariantPrice(((VariantRealmProxyInterface) newObject).realmGet$VariantPrice());
        return realmObject;
    }

    public static Variant createDetachedCopy(Variant realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        Variant standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (Variant)cachedObject.object;
            } else {
                standaloneObject = (Variant)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new Variant();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((VariantRealmProxyInterface) standaloneObject).realmSet$realmVariantID(((VariantRealmProxyInterface) realmObject).realmGet$realmVariantID());
        ((VariantRealmProxyInterface) standaloneObject).realmSet$VariantID(((VariantRealmProxyInterface) realmObject).realmGet$VariantID());
        ((VariantRealmProxyInterface) standaloneObject).realmSet$VariantName(((VariantRealmProxyInterface) realmObject).realmGet$VariantName());
        ((VariantRealmProxyInterface) standaloneObject).realmSet$VariantPrice(((VariantRealmProxyInterface) realmObject).realmGet$VariantPrice());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Variant = [");
        stringBuilder.append("{realmVariantID:");
        stringBuilder.append(realmGet$realmVariantID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{VariantID:");
        stringBuilder.append(realmGet$VariantID() != null ? realmGet$VariantID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{VariantName:");
        stringBuilder.append(realmGet$VariantName() != null ? realmGet$VariantName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{VariantPrice:");
        stringBuilder.append(realmGet$VariantPrice() != null ? realmGet$VariantPrice() : "null");
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
        VariantRealmProxy aVariant = (VariantRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aVariant.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aVariant.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aVariant.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

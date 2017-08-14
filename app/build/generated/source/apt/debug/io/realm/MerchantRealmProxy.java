package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.Merchant;
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

public class MerchantRealmProxy extends Merchant
    implements RealmObjectProxy, MerchantRealmProxyInterface {

    static final class MerchantColumnInfo extends ColumnInfo {

        public final long realmMerchantIDIndex;
        public final long merchantIDIndex;
        public final long merchantNameIndex;
        public final long merchantAddressIndex;
        public final long merhantPhoneIndex;

        MerchantColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(5);
            this.realmMerchantIDIndex = getValidColumnIndex(path, table, "Merchant", "realmMerchantID");
            indicesMap.put("realmMerchantID", this.realmMerchantIDIndex);

            this.merchantIDIndex = getValidColumnIndex(path, table, "Merchant", "merchantID");
            indicesMap.put("merchantID", this.merchantIDIndex);

            this.merchantNameIndex = getValidColumnIndex(path, table, "Merchant", "merchantName");
            indicesMap.put("merchantName", this.merchantNameIndex);

            this.merchantAddressIndex = getValidColumnIndex(path, table, "Merchant", "merchantAddress");
            indicesMap.put("merchantAddress", this.merchantAddressIndex);

            this.merhantPhoneIndex = getValidColumnIndex(path, table, "Merchant", "merhantPhone");
            indicesMap.put("merhantPhone", this.merhantPhoneIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final MerchantColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("realmMerchantID");
        fieldNames.add("merchantID");
        fieldNames.add("merchantName");
        fieldNames.add("merchantAddress");
        fieldNames.add("merhantPhone");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    MerchantRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (MerchantColumnInfo) columnInfo;
        this.proxyState = new ProxyState(Merchant.class);
    }

    @SuppressWarnings("cast")
    public long realmGet$realmMerchantID() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.realmMerchantIDIndex);
    }

    public void realmSet$realmMerchantID(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.realmMerchantIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$merchantID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.merchantIDIndex);
    }

    public void realmSet$merchantID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.merchantIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.merchantIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$merchantName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.merchantNameIndex);
    }

    public void realmSet$merchantName(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.merchantNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.merchantNameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$merchantAddress() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.merchantAddressIndex);
    }

    public void realmSet$merchantAddress(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.merchantAddressIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.merchantAddressIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$merhantPhone() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.merhantPhoneIndex);
    }

    public void realmSet$merhantPhone(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.merhantPhoneIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.merhantPhoneIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Merchant")) {
            Table table = transaction.getTable("class_Merchant");
            table.addColumn(RealmFieldType.INTEGER, "realmMerchantID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "merchantID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "merchantName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "merchantAddress", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "merhantPhone", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_Merchant");
    }

    public static MerchantColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Merchant")) {
            Table table = transaction.getTable("class_Merchant");
            if (table.getColumnCount() != 5) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 5 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 5; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final MerchantColumnInfo columnInfo = new MerchantColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("realmMerchantID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'realmMerchantID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realmMerchantID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'realmMerchantID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.realmMerchantIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'realmMerchantID' does support null values in the existing Realm file. Use corresponding boxed type for field 'realmMerchantID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("merchantID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'merchantID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("merchantID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'merchantID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.merchantIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'merchantID' is required. Either set @Required to field 'merchantID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("merchantName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'merchantName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("merchantName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'merchantName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.merchantNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'merchantName' is required. Either set @Required to field 'merchantName' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("merchantAddress")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'merchantAddress' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("merchantAddress") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'merchantAddress' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.merchantAddressIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'merchantAddress' is required. Either set @Required to field 'merchantAddress' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("merhantPhone")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'merhantPhone' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("merhantPhone") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'merhantPhone' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.merhantPhoneIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'merhantPhone' is required. Either set @Required to field 'merhantPhone' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Merchant class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Merchant";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static Merchant createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        Merchant obj = realm.createObject(Merchant.class);
        if (json.has("realmMerchantID")) {
            if (json.isNull("realmMerchantID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field realmMerchantID to null.");
            } else {
                ((MerchantRealmProxyInterface) obj).realmSet$realmMerchantID((long) json.getLong("realmMerchantID"));
            }
        }
        if (json.has("merchantID")) {
            if (json.isNull("merchantID")) {
                ((MerchantRealmProxyInterface) obj).realmSet$merchantID(null);
            } else {
                ((MerchantRealmProxyInterface) obj).realmSet$merchantID((String) json.getString("merchantID"));
            }
        }
        if (json.has("merchantName")) {
            if (json.isNull("merchantName")) {
                ((MerchantRealmProxyInterface) obj).realmSet$merchantName(null);
            } else {
                ((MerchantRealmProxyInterface) obj).realmSet$merchantName((String) json.getString("merchantName"));
            }
        }
        if (json.has("merchantAddress")) {
            if (json.isNull("merchantAddress")) {
                ((MerchantRealmProxyInterface) obj).realmSet$merchantAddress(null);
            } else {
                ((MerchantRealmProxyInterface) obj).realmSet$merchantAddress((String) json.getString("merchantAddress"));
            }
        }
        if (json.has("merhantPhone")) {
            if (json.isNull("merhantPhone")) {
                ((MerchantRealmProxyInterface) obj).realmSet$merhantPhone(null);
            } else {
                ((MerchantRealmProxyInterface) obj).realmSet$merhantPhone((String) json.getString("merhantPhone"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static Merchant createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        Merchant obj = realm.createObject(Merchant.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("realmMerchantID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field realmMerchantID to null.");
                } else {
                    ((MerchantRealmProxyInterface) obj).realmSet$realmMerchantID((long) reader.nextLong());
                }
            } else if (name.equals("merchantID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((MerchantRealmProxyInterface) obj).realmSet$merchantID(null);
                } else {
                    ((MerchantRealmProxyInterface) obj).realmSet$merchantID((String) reader.nextString());
                }
            } else if (name.equals("merchantName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((MerchantRealmProxyInterface) obj).realmSet$merchantName(null);
                } else {
                    ((MerchantRealmProxyInterface) obj).realmSet$merchantName((String) reader.nextString());
                }
            } else if (name.equals("merchantAddress")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((MerchantRealmProxyInterface) obj).realmSet$merchantAddress(null);
                } else {
                    ((MerchantRealmProxyInterface) obj).realmSet$merchantAddress((String) reader.nextString());
                }
            } else if (name.equals("merhantPhone")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((MerchantRealmProxyInterface) obj).realmSet$merhantPhone(null);
                } else {
                    ((MerchantRealmProxyInterface) obj).realmSet$merhantPhone((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static Merchant copyOrUpdate(Realm realm, Merchant object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static Merchant copy(Realm realm, Merchant newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        Merchant realmObject = realm.createObject(Merchant.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((MerchantRealmProxyInterface) realmObject).realmSet$realmMerchantID(((MerchantRealmProxyInterface) newObject).realmGet$realmMerchantID());
        ((MerchantRealmProxyInterface) realmObject).realmSet$merchantID(((MerchantRealmProxyInterface) newObject).realmGet$merchantID());
        ((MerchantRealmProxyInterface) realmObject).realmSet$merchantName(((MerchantRealmProxyInterface) newObject).realmGet$merchantName());
        ((MerchantRealmProxyInterface) realmObject).realmSet$merchantAddress(((MerchantRealmProxyInterface) newObject).realmGet$merchantAddress());
        ((MerchantRealmProxyInterface) realmObject).realmSet$merhantPhone(((MerchantRealmProxyInterface) newObject).realmGet$merhantPhone());
        return realmObject;
    }

    public static Merchant createDetachedCopy(Merchant realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        Merchant standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (Merchant)cachedObject.object;
            } else {
                standaloneObject = (Merchant)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new Merchant();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((MerchantRealmProxyInterface) standaloneObject).realmSet$realmMerchantID(((MerchantRealmProxyInterface) realmObject).realmGet$realmMerchantID());
        ((MerchantRealmProxyInterface) standaloneObject).realmSet$merchantID(((MerchantRealmProxyInterface) realmObject).realmGet$merchantID());
        ((MerchantRealmProxyInterface) standaloneObject).realmSet$merchantName(((MerchantRealmProxyInterface) realmObject).realmGet$merchantName());
        ((MerchantRealmProxyInterface) standaloneObject).realmSet$merchantAddress(((MerchantRealmProxyInterface) realmObject).realmGet$merchantAddress());
        ((MerchantRealmProxyInterface) standaloneObject).realmSet$merhantPhone(((MerchantRealmProxyInterface) realmObject).realmGet$merhantPhone());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Merchant = [");
        stringBuilder.append("{realmMerchantID:");
        stringBuilder.append(realmGet$realmMerchantID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{merchantID:");
        stringBuilder.append(realmGet$merchantID() != null ? realmGet$merchantID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{merchantName:");
        stringBuilder.append(realmGet$merchantName() != null ? realmGet$merchantName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{merchantAddress:");
        stringBuilder.append(realmGet$merchantAddress() != null ? realmGet$merchantAddress() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{merhantPhone:");
        stringBuilder.append(realmGet$merhantPhone() != null ? realmGet$merhantPhone() : "null");
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
        MerchantRealmProxy aMerchant = (MerchantRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aMerchant.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aMerchant.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aMerchant.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

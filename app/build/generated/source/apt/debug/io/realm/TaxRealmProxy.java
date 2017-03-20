package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.Tax;
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

public class TaxRealmProxy extends Tax
    implements RealmObjectProxy, TaxRealmProxyInterface {

    static final class TaxColumnInfo extends ColumnInfo {

        public final long realmTaxIDIndex;
        public final long taxOutletIDIndex;
        public final long taxValueIndex;

        TaxColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.realmTaxIDIndex = getValidColumnIndex(path, table, "Tax", "realmTaxID");
            indicesMap.put("realmTaxID", this.realmTaxIDIndex);

            this.taxOutletIDIndex = getValidColumnIndex(path, table, "Tax", "taxOutletID");
            indicesMap.put("taxOutletID", this.taxOutletIDIndex);

            this.taxValueIndex = getValidColumnIndex(path, table, "Tax", "taxValue");
            indicesMap.put("taxValue", this.taxValueIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final TaxColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("realmTaxID");
        fieldNames.add("taxOutletID");
        fieldNames.add("taxValue");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TaxRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (TaxColumnInfo) columnInfo;
        this.proxyState = new ProxyState(Tax.class);
    }

    @SuppressWarnings("cast")
    public long realmGet$realmTaxID() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.realmTaxIDIndex);
    }

    public void realmSet$realmTaxID(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.realmTaxIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$taxOutletID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.taxOutletIDIndex);
    }

    public void realmSet$taxOutletID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.taxOutletIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.taxOutletIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$taxValue() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.taxValueIndex);
    }

    public void realmSet$taxValue(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.taxValueIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.taxValueIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Tax")) {
            Table table = transaction.getTable("class_Tax");
            table.addColumn(RealmFieldType.INTEGER, "realmTaxID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "taxOutletID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "taxValue", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_Tax");
    }

    public static TaxColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Tax")) {
            Table table = transaction.getTable("class_Tax");
            if (table.getColumnCount() != 3) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 3 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final TaxColumnInfo columnInfo = new TaxColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("realmTaxID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'realmTaxID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realmTaxID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'realmTaxID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.realmTaxIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'realmTaxID' does support null values in the existing Realm file. Use corresponding boxed type for field 'realmTaxID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("taxOutletID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'taxOutletID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("taxOutletID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'taxOutletID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.taxOutletIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'taxOutletID' is required. Either set @Required to field 'taxOutletID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("taxValue")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'taxValue' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("taxValue") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'taxValue' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.taxValueIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'taxValue' is required. Either set @Required to field 'taxValue' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Tax class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Tax";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static Tax createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        Tax obj = realm.createObject(Tax.class);
        if (json.has("realmTaxID")) {
            if (json.isNull("realmTaxID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field realmTaxID to null.");
            } else {
                ((TaxRealmProxyInterface) obj).realmSet$realmTaxID((long) json.getLong("realmTaxID"));
            }
        }
        if (json.has("taxOutletID")) {
            if (json.isNull("taxOutletID")) {
                ((TaxRealmProxyInterface) obj).realmSet$taxOutletID(null);
            } else {
                ((TaxRealmProxyInterface) obj).realmSet$taxOutletID((String) json.getString("taxOutletID"));
            }
        }
        if (json.has("taxValue")) {
            if (json.isNull("taxValue")) {
                ((TaxRealmProxyInterface) obj).realmSet$taxValue(null);
            } else {
                ((TaxRealmProxyInterface) obj).realmSet$taxValue((String) json.getString("taxValue"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static Tax createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        Tax obj = realm.createObject(Tax.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("realmTaxID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field realmTaxID to null.");
                } else {
                    ((TaxRealmProxyInterface) obj).realmSet$realmTaxID((long) reader.nextLong());
                }
            } else if (name.equals("taxOutletID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TaxRealmProxyInterface) obj).realmSet$taxOutletID(null);
                } else {
                    ((TaxRealmProxyInterface) obj).realmSet$taxOutletID((String) reader.nextString());
                }
            } else if (name.equals("taxValue")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TaxRealmProxyInterface) obj).realmSet$taxValue(null);
                } else {
                    ((TaxRealmProxyInterface) obj).realmSet$taxValue((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static Tax copyOrUpdate(Realm realm, Tax object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static Tax copy(Realm realm, Tax newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        Tax realmObject = realm.createObject(Tax.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((TaxRealmProxyInterface) realmObject).realmSet$realmTaxID(((TaxRealmProxyInterface) newObject).realmGet$realmTaxID());
        ((TaxRealmProxyInterface) realmObject).realmSet$taxOutletID(((TaxRealmProxyInterface) newObject).realmGet$taxOutletID());
        ((TaxRealmProxyInterface) realmObject).realmSet$taxValue(((TaxRealmProxyInterface) newObject).realmGet$taxValue());
        return realmObject;
    }

    public static Tax createDetachedCopy(Tax realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        Tax standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (Tax)cachedObject.object;
            } else {
                standaloneObject = (Tax)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new Tax();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((TaxRealmProxyInterface) standaloneObject).realmSet$realmTaxID(((TaxRealmProxyInterface) realmObject).realmGet$realmTaxID());
        ((TaxRealmProxyInterface) standaloneObject).realmSet$taxOutletID(((TaxRealmProxyInterface) realmObject).realmGet$taxOutletID());
        ((TaxRealmProxyInterface) standaloneObject).realmSet$taxValue(((TaxRealmProxyInterface) realmObject).realmGet$taxValue());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Tax = [");
        stringBuilder.append("{realmTaxID:");
        stringBuilder.append(realmGet$realmTaxID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{taxOutletID:");
        stringBuilder.append(realmGet$taxOutletID() != null ? realmGet$taxOutletID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{taxValue:");
        stringBuilder.append(realmGet$taxValue() != null ? realmGet$taxValue() : "null");
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
        TaxRealmProxy aTax = (TaxRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTax.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTax.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTax.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

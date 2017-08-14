package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailModifier;
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

public class TransactionDetailModifierRealmProxy extends TransactionDetailModifier
    implements RealmObjectProxy, TransactionDetailModifierRealmProxyInterface {

    static final class TransactionDetailModifierColumnInfo extends ColumnInfo {

        public final long transactionDetailModifierIDIndex;

        TransactionDetailModifierColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(1);
            this.transactionDetailModifierIDIndex = getValidColumnIndex(path, table, "TransactionDetailModifier", "transactionDetailModifierID");
            indicesMap.put("transactionDetailModifierID", this.transactionDetailModifierIDIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final TransactionDetailModifierColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("transactionDetailModifierID");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TransactionDetailModifierRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (TransactionDetailModifierColumnInfo) columnInfo;
        this.proxyState = new ProxyState(TransactionDetailModifier.class);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionDetailModifierID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionDetailModifierIDIndex);
    }

    public void realmSet$transactionDetailModifierID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionDetailModifierIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionDetailModifierIDIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_TransactionDetailModifier")) {
            Table table = transaction.getTable("class_TransactionDetailModifier");
            table.addColumn(RealmFieldType.STRING, "transactionDetailModifierID", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_TransactionDetailModifier");
    }

    public static TransactionDetailModifierColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_TransactionDetailModifier")) {
            Table table = transaction.getTable("class_TransactionDetailModifier");
            if (table.getColumnCount() != 1) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 1 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 1; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final TransactionDetailModifierColumnInfo columnInfo = new TransactionDetailModifierColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("transactionDetailModifierID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailModifierID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionDetailModifierID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionDetailModifierID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionDetailModifierIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionDetailModifierID' is required. Either set @Required to field 'transactionDetailModifierID' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The TransactionDetailModifier class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_TransactionDetailModifier";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static TransactionDetailModifier createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        TransactionDetailModifier obj = realm.createObject(TransactionDetailModifier.class);
        if (json.has("transactionDetailModifierID")) {
            if (json.isNull("transactionDetailModifierID")) {
                ((TransactionDetailModifierRealmProxyInterface) obj).realmSet$transactionDetailModifierID(null);
            } else {
                ((TransactionDetailModifierRealmProxyInterface) obj).realmSet$transactionDetailModifierID((String) json.getString("transactionDetailModifierID"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static TransactionDetailModifier createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        TransactionDetailModifier obj = realm.createObject(TransactionDetailModifier.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("transactionDetailModifierID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailModifierRealmProxyInterface) obj).realmSet$transactionDetailModifierID(null);
                } else {
                    ((TransactionDetailModifierRealmProxyInterface) obj).realmSet$transactionDetailModifierID((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static TransactionDetailModifier copyOrUpdate(Realm realm, TransactionDetailModifier object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static TransactionDetailModifier copy(Realm realm, TransactionDetailModifier newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        TransactionDetailModifier realmObject = realm.createObject(TransactionDetailModifier.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((TransactionDetailModifierRealmProxyInterface) realmObject).realmSet$transactionDetailModifierID(((TransactionDetailModifierRealmProxyInterface) newObject).realmGet$transactionDetailModifierID());
        return realmObject;
    }

    public static TransactionDetailModifier createDetachedCopy(TransactionDetailModifier realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        TransactionDetailModifier standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (TransactionDetailModifier)cachedObject.object;
            } else {
                standaloneObject = (TransactionDetailModifier)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new TransactionDetailModifier();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((TransactionDetailModifierRealmProxyInterface) standaloneObject).realmSet$transactionDetailModifierID(((TransactionDetailModifierRealmProxyInterface) realmObject).realmGet$transactionDetailModifierID());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("TransactionDetailModifier = [");
        stringBuilder.append("{transactionDetailModifierID:");
        stringBuilder.append(realmGet$transactionDetailModifierID() != null ? realmGet$transactionDetailModifierID() : "null");
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
        TransactionDetailModifierRealmProxy aTransactionDetailModifier = (TransactionDetailModifierRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTransactionDetailModifier.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTransactionDetailModifier.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTransactionDetailModifier.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

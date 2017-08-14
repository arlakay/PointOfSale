package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryModifier;
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

public class TransactionDetailTemporaryModifierRealmProxy extends TransactionDetailTemporaryModifier
    implements RealmObjectProxy, TransactionDetailTemporaryModifierRealmProxyInterface {

    static final class TransactionDetailTemporaryModifierColumnInfo extends ColumnInfo {

        public final long transactionDetailTemporaryModifierIDIndex;

        TransactionDetailTemporaryModifierColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(1);
            this.transactionDetailTemporaryModifierIDIndex = getValidColumnIndex(path, table, "TransactionDetailTemporaryModifier", "transactionDetailTemporaryModifierID");
            indicesMap.put("transactionDetailTemporaryModifierID", this.transactionDetailTemporaryModifierIDIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final TransactionDetailTemporaryModifierColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("transactionDetailTemporaryModifierID");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TransactionDetailTemporaryModifierRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (TransactionDetailTemporaryModifierColumnInfo) columnInfo;
        this.proxyState = new ProxyState(TransactionDetailTemporaryModifier.class);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionDetailTemporaryModifierID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionDetailTemporaryModifierIDIndex);
    }

    public void realmSet$transactionDetailTemporaryModifierID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionDetailTemporaryModifierIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionDetailTemporaryModifierIDIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_TransactionDetailTemporaryModifier")) {
            Table table = transaction.getTable("class_TransactionDetailTemporaryModifier");
            table.addColumn(RealmFieldType.STRING, "transactionDetailTemporaryModifierID", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_TransactionDetailTemporaryModifier");
    }

    public static TransactionDetailTemporaryModifierColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_TransactionDetailTemporaryModifier")) {
            Table table = transaction.getTable("class_TransactionDetailTemporaryModifier");
            if (table.getColumnCount() != 1) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 1 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 1; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final TransactionDetailTemporaryModifierColumnInfo columnInfo = new TransactionDetailTemporaryModifierColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("transactionDetailTemporaryModifierID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailTemporaryModifierID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionDetailTemporaryModifierID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionDetailTemporaryModifierID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionDetailTemporaryModifierIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionDetailTemporaryModifierID' is required. Either set @Required to field 'transactionDetailTemporaryModifierID' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The TransactionDetailTemporaryModifier class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_TransactionDetailTemporaryModifier";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static TransactionDetailTemporaryModifier createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        TransactionDetailTemporaryModifier obj = realm.createObject(TransactionDetailTemporaryModifier.class);
        if (json.has("transactionDetailTemporaryModifierID")) {
            if (json.isNull("transactionDetailTemporaryModifierID")) {
                ((TransactionDetailTemporaryModifierRealmProxyInterface) obj).realmSet$transactionDetailTemporaryModifierID(null);
            } else {
                ((TransactionDetailTemporaryModifierRealmProxyInterface) obj).realmSet$transactionDetailTemporaryModifierID((String) json.getString("transactionDetailTemporaryModifierID"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static TransactionDetailTemporaryModifier createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        TransactionDetailTemporaryModifier obj = realm.createObject(TransactionDetailTemporaryModifier.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("transactionDetailTemporaryModifierID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailTemporaryModifierRealmProxyInterface) obj).realmSet$transactionDetailTemporaryModifierID(null);
                } else {
                    ((TransactionDetailTemporaryModifierRealmProxyInterface) obj).realmSet$transactionDetailTemporaryModifierID((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static TransactionDetailTemporaryModifier copyOrUpdate(Realm realm, TransactionDetailTemporaryModifier object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static TransactionDetailTemporaryModifier copy(Realm realm, TransactionDetailTemporaryModifier newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        TransactionDetailTemporaryModifier realmObject = realm.createObject(TransactionDetailTemporaryModifier.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((TransactionDetailTemporaryModifierRealmProxyInterface) realmObject).realmSet$transactionDetailTemporaryModifierID(((TransactionDetailTemporaryModifierRealmProxyInterface) newObject).realmGet$transactionDetailTemporaryModifierID());
        return realmObject;
    }

    public static TransactionDetailTemporaryModifier createDetachedCopy(TransactionDetailTemporaryModifier realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        TransactionDetailTemporaryModifier standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (TransactionDetailTemporaryModifier)cachedObject.object;
            } else {
                standaloneObject = (TransactionDetailTemporaryModifier)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new TransactionDetailTemporaryModifier();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((TransactionDetailTemporaryModifierRealmProxyInterface) standaloneObject).realmSet$transactionDetailTemporaryModifierID(((TransactionDetailTemporaryModifierRealmProxyInterface) realmObject).realmGet$transactionDetailTemporaryModifierID());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("TransactionDetailTemporaryModifier = [");
        stringBuilder.append("{transactionDetailTemporaryModifierID:");
        stringBuilder.append(realmGet$transactionDetailTemporaryModifierID() != null ? realmGet$transactionDetailTemporaryModifierID() : "null");
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
        TransactionDetailTemporaryModifierRealmProxy aTransactionDetailTemporaryModifier = (TransactionDetailTemporaryModifierRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTransactionDetailTemporaryModifier.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTransactionDetailTemporaryModifier.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTransactionDetailTemporaryModifier.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

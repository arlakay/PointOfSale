package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailTemporaryDiscount;
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

public class TransactionDetailTemporaryDiscountRealmProxy extends TransactionDetailTemporaryDiscount
    implements RealmObjectProxy, TransactionDetailTemporaryDiscountRealmProxyInterface {

    static final class TransactionDetailTemporaryDiscountColumnInfo extends ColumnInfo {

        public final long transactionDetailTemporaryDiscountIDIndex;

        TransactionDetailTemporaryDiscountColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(1);
            this.transactionDetailTemporaryDiscountIDIndex = getValidColumnIndex(path, table, "TransactionDetailTemporaryDiscount", "transactionDetailTemporaryDiscountID");
            indicesMap.put("transactionDetailTemporaryDiscountID", this.transactionDetailTemporaryDiscountIDIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final TransactionDetailTemporaryDiscountColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("transactionDetailTemporaryDiscountID");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TransactionDetailTemporaryDiscountRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (TransactionDetailTemporaryDiscountColumnInfo) columnInfo;
        this.proxyState = new ProxyState(TransactionDetailTemporaryDiscount.class);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionDetailTemporaryDiscountID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionDetailTemporaryDiscountIDIndex);
    }

    public void realmSet$transactionDetailTemporaryDiscountID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionDetailTemporaryDiscountIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionDetailTemporaryDiscountIDIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_TransactionDetailTemporaryDiscount")) {
            Table table = transaction.getTable("class_TransactionDetailTemporaryDiscount");
            table.addColumn(RealmFieldType.STRING, "transactionDetailTemporaryDiscountID", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_TransactionDetailTemporaryDiscount");
    }

    public static TransactionDetailTemporaryDiscountColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_TransactionDetailTemporaryDiscount")) {
            Table table = transaction.getTable("class_TransactionDetailTemporaryDiscount");
            if (table.getColumnCount() != 1) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 1 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 1; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final TransactionDetailTemporaryDiscountColumnInfo columnInfo = new TransactionDetailTemporaryDiscountColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("transactionDetailTemporaryDiscountID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionDetailTemporaryDiscountID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionDetailTemporaryDiscountID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionDetailTemporaryDiscountID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionDetailTemporaryDiscountIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionDetailTemporaryDiscountID' is required. Either set @Required to field 'transactionDetailTemporaryDiscountID' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The TransactionDetailTemporaryDiscount class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_TransactionDetailTemporaryDiscount";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static TransactionDetailTemporaryDiscount createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        TransactionDetailTemporaryDiscount obj = realm.createObject(TransactionDetailTemporaryDiscount.class);
        if (json.has("transactionDetailTemporaryDiscountID")) {
            if (json.isNull("transactionDetailTemporaryDiscountID")) {
                ((TransactionDetailTemporaryDiscountRealmProxyInterface) obj).realmSet$transactionDetailTemporaryDiscountID(null);
            } else {
                ((TransactionDetailTemporaryDiscountRealmProxyInterface) obj).realmSet$transactionDetailTemporaryDiscountID((String) json.getString("transactionDetailTemporaryDiscountID"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static TransactionDetailTemporaryDiscount createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        TransactionDetailTemporaryDiscount obj = realm.createObject(TransactionDetailTemporaryDiscount.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("transactionDetailTemporaryDiscountID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailTemporaryDiscountRealmProxyInterface) obj).realmSet$transactionDetailTemporaryDiscountID(null);
                } else {
                    ((TransactionDetailTemporaryDiscountRealmProxyInterface) obj).realmSet$transactionDetailTemporaryDiscountID((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static TransactionDetailTemporaryDiscount copyOrUpdate(Realm realm, TransactionDetailTemporaryDiscount object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static TransactionDetailTemporaryDiscount copy(Realm realm, TransactionDetailTemporaryDiscount newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        TransactionDetailTemporaryDiscount realmObject = realm.createObject(TransactionDetailTemporaryDiscount.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((TransactionDetailTemporaryDiscountRealmProxyInterface) realmObject).realmSet$transactionDetailTemporaryDiscountID(((TransactionDetailTemporaryDiscountRealmProxyInterface) newObject).realmGet$transactionDetailTemporaryDiscountID());
        return realmObject;
    }

    public static TransactionDetailTemporaryDiscount createDetachedCopy(TransactionDetailTemporaryDiscount realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        TransactionDetailTemporaryDiscount standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (TransactionDetailTemporaryDiscount)cachedObject.object;
            } else {
                standaloneObject = (TransactionDetailTemporaryDiscount)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new TransactionDetailTemporaryDiscount();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((TransactionDetailTemporaryDiscountRealmProxyInterface) standaloneObject).realmSet$transactionDetailTemporaryDiscountID(((TransactionDetailTemporaryDiscountRealmProxyInterface) realmObject).realmGet$transactionDetailTemporaryDiscountID());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("TransactionDetailTemporaryDiscount = [");
        stringBuilder.append("{transactionDetailTemporaryDiscountID:");
        stringBuilder.append(realmGet$transactionDetailTemporaryDiscountID() != null ? realmGet$transactionDetailTemporaryDiscountID() : "null");
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
        TransactionDetailTemporaryDiscountRealmProxy aTransactionDetailTemporaryDiscount = (TransactionDetailTemporaryDiscountRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTransactionDetailTemporaryDiscount.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTransactionDetailTemporaryDiscount.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTransactionDetailTemporaryDiscount.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

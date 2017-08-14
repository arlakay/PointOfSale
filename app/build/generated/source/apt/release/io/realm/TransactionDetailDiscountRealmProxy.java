package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount;
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

public class TransactionDetailDiscountRealmProxy extends TransactionDetailDiscount
    implements RealmObjectProxy, TransactionDetailDiscountRealmProxyInterface {

    static final class TransactionDetailDiscountColumnInfo extends ColumnInfo {

        public final long discountIDIndex;

        TransactionDetailDiscountColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(1);
            this.discountIDIndex = getValidColumnIndex(path, table, "TransactionDetailDiscount", "discountID");
            indicesMap.put("discountID", this.discountIDIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final TransactionDetailDiscountColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("discountID");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TransactionDetailDiscountRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (TransactionDetailDiscountColumnInfo) columnInfo;
        this.proxyState = new ProxyState(TransactionDetailDiscount.class);
    }

    @SuppressWarnings("cast")
    public String realmGet$discountID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.discountIDIndex);
    }

    public void realmSet$discountID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.discountIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.discountIDIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_TransactionDetailDiscount")) {
            Table table = transaction.getTable("class_TransactionDetailDiscount");
            table.addColumn(RealmFieldType.STRING, "discountID", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_TransactionDetailDiscount");
    }

    public static TransactionDetailDiscountColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_TransactionDetailDiscount")) {
            Table table = transaction.getTable("class_TransactionDetailDiscount");
            if (table.getColumnCount() != 1) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 1 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 1; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final TransactionDetailDiscountColumnInfo columnInfo = new TransactionDetailDiscountColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("discountID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'discountID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("discountID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'discountID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.discountIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'discountID' is required. Either set @Required to field 'discountID' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The TransactionDetailDiscount class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_TransactionDetailDiscount";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static TransactionDetailDiscount createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        TransactionDetailDiscount obj = realm.createObject(TransactionDetailDiscount.class);
        if (json.has("discountID")) {
            if (json.isNull("discountID")) {
                ((TransactionDetailDiscountRealmProxyInterface) obj).realmSet$discountID(null);
            } else {
                ((TransactionDetailDiscountRealmProxyInterface) obj).realmSet$discountID((String) json.getString("discountID"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static TransactionDetailDiscount createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        TransactionDetailDiscount obj = realm.createObject(TransactionDetailDiscount.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("discountID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionDetailDiscountRealmProxyInterface) obj).realmSet$discountID(null);
                } else {
                    ((TransactionDetailDiscountRealmProxyInterface) obj).realmSet$discountID((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static TransactionDetailDiscount copyOrUpdate(Realm realm, TransactionDetailDiscount object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static TransactionDetailDiscount copy(Realm realm, TransactionDetailDiscount newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        TransactionDetailDiscount realmObject = realm.createObject(TransactionDetailDiscount.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((TransactionDetailDiscountRealmProxyInterface) realmObject).realmSet$discountID(((TransactionDetailDiscountRealmProxyInterface) newObject).realmGet$discountID());
        return realmObject;
    }

    public static TransactionDetailDiscount createDetachedCopy(TransactionDetailDiscount realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        TransactionDetailDiscount standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (TransactionDetailDiscount)cachedObject.object;
            } else {
                standaloneObject = (TransactionDetailDiscount)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new TransactionDetailDiscount();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((TransactionDetailDiscountRealmProxyInterface) standaloneObject).realmSet$discountID(((TransactionDetailDiscountRealmProxyInterface) realmObject).realmGet$discountID());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("TransactionDetailDiscount = [");
        stringBuilder.append("{discountID:");
        stringBuilder.append(realmGet$discountID() != null ? realmGet$discountID() : "null");
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
        TransactionDetailDiscountRealmProxy aTransactionDetailDiscount = (TransactionDetailDiscountRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTransactionDetailDiscount.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTransactionDetailDiscount.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTransactionDetailDiscount.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.Category;
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

public class CategoryRealmProxy extends Category
    implements RealmObjectProxy, CategoryRealmProxyInterface {

    static final class CategoryColumnInfo extends ColumnInfo {

        public final long realmCategoryIDIndex;
        public final long categoryIndex;

        CategoryColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(2);
            this.realmCategoryIDIndex = getValidColumnIndex(path, table, "Category", "realmCategoryID");
            indicesMap.put("realmCategoryID", this.realmCategoryIDIndex);

            this.categoryIndex = getValidColumnIndex(path, table, "Category", "category");
            indicesMap.put("category", this.categoryIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final CategoryColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("realmCategoryID");
        fieldNames.add("category");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    CategoryRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (CategoryColumnInfo) columnInfo;
        this.proxyState = new ProxyState(Category.class);
    }

    @SuppressWarnings("cast")
    public long realmGet$realmCategoryID() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.realmCategoryIDIndex);
    }

    public void realmSet$realmCategoryID(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.realmCategoryIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$category() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.categoryIndex);
    }

    public void realmSet$category(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.categoryIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.categoryIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Category")) {
            Table table = transaction.getTable("class_Category");
            table.addColumn(RealmFieldType.INTEGER, "realmCategoryID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "category", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_Category");
    }

    public static CategoryColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Category")) {
            Table table = transaction.getTable("class_Category");
            if (table.getColumnCount() != 2) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 2 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 2; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final CategoryColumnInfo columnInfo = new CategoryColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("realmCategoryID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'realmCategoryID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realmCategoryID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'realmCategoryID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.realmCategoryIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'realmCategoryID' does support null values in the existing Realm file. Use corresponding boxed type for field 'realmCategoryID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("category")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'category' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("category") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'category' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.categoryIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'category' is required. Either set @Required to field 'category' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Category class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Category";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static Category createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        Category obj = realm.createObject(Category.class);
        if (json.has("realmCategoryID")) {
            if (json.isNull("realmCategoryID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field realmCategoryID to null.");
            } else {
                ((CategoryRealmProxyInterface) obj).realmSet$realmCategoryID((long) json.getLong("realmCategoryID"));
            }
        }
        if (json.has("category")) {
            if (json.isNull("category")) {
                ((CategoryRealmProxyInterface) obj).realmSet$category(null);
            } else {
                ((CategoryRealmProxyInterface) obj).realmSet$category((String) json.getString("category"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static Category createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        Category obj = realm.createObject(Category.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("realmCategoryID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field realmCategoryID to null.");
                } else {
                    ((CategoryRealmProxyInterface) obj).realmSet$realmCategoryID((long) reader.nextLong());
                }
            } else if (name.equals("category")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((CategoryRealmProxyInterface) obj).realmSet$category(null);
                } else {
                    ((CategoryRealmProxyInterface) obj).realmSet$category((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static Category copyOrUpdate(Realm realm, Category object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static Category copy(Realm realm, Category newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        Category realmObject = realm.createObject(Category.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((CategoryRealmProxyInterface) realmObject).realmSet$realmCategoryID(((CategoryRealmProxyInterface) newObject).realmGet$realmCategoryID());
        ((CategoryRealmProxyInterface) realmObject).realmSet$category(((CategoryRealmProxyInterface) newObject).realmGet$category());
        return realmObject;
    }

    public static Category createDetachedCopy(Category realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        Category standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (Category)cachedObject.object;
            } else {
                standaloneObject = (Category)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new Category();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((CategoryRealmProxyInterface) standaloneObject).realmSet$realmCategoryID(((CategoryRealmProxyInterface) realmObject).realmGet$realmCategoryID());
        ((CategoryRealmProxyInterface) standaloneObject).realmSet$category(((CategoryRealmProxyInterface) realmObject).realmGet$category());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Category = [");
        stringBuilder.append("{realmCategoryID:");
        stringBuilder.append(realmGet$realmCategoryID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{category:");
        stringBuilder.append(realmGet$category() != null ? realmGet$category() : "null");
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
        CategoryRealmProxy aCategory = (CategoryRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aCategory.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aCategory.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aCategory.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

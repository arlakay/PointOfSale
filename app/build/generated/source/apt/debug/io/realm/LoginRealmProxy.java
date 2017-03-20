package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.Login;
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

public class LoginRealmProxy extends Login
    implements RealmObjectProxy, LoginRealmProxyInterface {

    static final class LoginColumnInfo extends ColumnInfo {

        public final long realmLoginIDIndex;
        public final long usernameIndex;
        public final long userPasswordIndex;
        public final long userPrivilegeIndex;
        public final long merchantIDIndex;
        public final long outletIDIndex;
        public final long counterIndex;
        public final long pictureUserIndex;

        LoginColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(8);
            this.realmLoginIDIndex = getValidColumnIndex(path, table, "Login", "realmLoginID");
            indicesMap.put("realmLoginID", this.realmLoginIDIndex);

            this.usernameIndex = getValidColumnIndex(path, table, "Login", "username");
            indicesMap.put("username", this.usernameIndex);

            this.userPasswordIndex = getValidColumnIndex(path, table, "Login", "userPassword");
            indicesMap.put("userPassword", this.userPasswordIndex);

            this.userPrivilegeIndex = getValidColumnIndex(path, table, "Login", "userPrivilege");
            indicesMap.put("userPrivilege", this.userPrivilegeIndex);

            this.merchantIDIndex = getValidColumnIndex(path, table, "Login", "merchantID");
            indicesMap.put("merchantID", this.merchantIDIndex);

            this.outletIDIndex = getValidColumnIndex(path, table, "Login", "outletID");
            indicesMap.put("outletID", this.outletIDIndex);

            this.counterIndex = getValidColumnIndex(path, table, "Login", "counter");
            indicesMap.put("counter", this.counterIndex);

            this.pictureUserIndex = getValidColumnIndex(path, table, "Login", "pictureUser");
            indicesMap.put("pictureUser", this.pictureUserIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final LoginColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("realmLoginID");
        fieldNames.add("username");
        fieldNames.add("userPassword");
        fieldNames.add("userPrivilege");
        fieldNames.add("merchantID");
        fieldNames.add("outletID");
        fieldNames.add("counter");
        fieldNames.add("pictureUser");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    LoginRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (LoginColumnInfo) columnInfo;
        this.proxyState = new ProxyState(Login.class);
    }

    @SuppressWarnings("cast")
    public long realmGet$realmLoginID() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.realmLoginIDIndex);
    }

    public void realmSet$realmLoginID(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.realmLoginIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$username() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.usernameIndex);
    }

    public void realmSet$username(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.usernameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.usernameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$userPassword() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.userPasswordIndex);
    }

    public void realmSet$userPassword(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.userPasswordIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.userPasswordIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$userPrivilege() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.userPrivilegeIndex);
    }

    public void realmSet$userPrivilege(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.userPrivilegeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.userPrivilegeIndex, value);
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
    public String realmGet$outletID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.outletIDIndex);
    }

    public void realmSet$outletID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.outletIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.outletIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$counter() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.counterIndex);
    }

    public void realmSet$counter(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.counterIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.counterIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$pictureUser() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.pictureUserIndex);
    }

    public void realmSet$pictureUser(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.pictureUserIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.pictureUserIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Login")) {
            Table table = transaction.getTable("class_Login");
            table.addColumn(RealmFieldType.INTEGER, "realmLoginID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "username", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "userPassword", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "userPrivilege", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "merchantID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "outletID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "counter", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "pictureUser", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_Login");
    }

    public static LoginColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Login")) {
            Table table = transaction.getTable("class_Login");
            if (table.getColumnCount() != 8) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 8 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 8; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final LoginColumnInfo columnInfo = new LoginColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("realmLoginID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'realmLoginID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("realmLoginID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'realmLoginID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.realmLoginIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'realmLoginID' does support null values in the existing Realm file. Use corresponding boxed type for field 'realmLoginID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("username")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'username' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("username") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'username' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.usernameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'username' is required. Either set @Required to field 'username' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("userPassword")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'userPassword' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("userPassword") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'userPassword' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.userPasswordIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'userPassword' is required. Either set @Required to field 'userPassword' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("userPrivilege")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'userPrivilege' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("userPrivilege") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'userPrivilege' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.userPrivilegeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'userPrivilege' is required. Either set @Required to field 'userPrivilege' or migrate using RealmObjectSchema.setNullable().");
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
            if (!columnTypes.containsKey("outletID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'outletID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("outletID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'outletID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.outletIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'outletID' is required. Either set @Required to field 'outletID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("counter")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'counter' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("counter") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'counter' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.counterIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'counter' is required. Either set @Required to field 'counter' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("pictureUser")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'pictureUser' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("pictureUser") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'pictureUser' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.pictureUserIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'pictureUser' is required. Either set @Required to field 'pictureUser' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Login class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Login";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static Login createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        Login obj = realm.createObject(Login.class);
        if (json.has("realmLoginID")) {
            if (json.isNull("realmLoginID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field realmLoginID to null.");
            } else {
                ((LoginRealmProxyInterface) obj).realmSet$realmLoginID((long) json.getLong("realmLoginID"));
            }
        }
        if (json.has("username")) {
            if (json.isNull("username")) {
                ((LoginRealmProxyInterface) obj).realmSet$username(null);
            } else {
                ((LoginRealmProxyInterface) obj).realmSet$username((String) json.getString("username"));
            }
        }
        if (json.has("userPassword")) {
            if (json.isNull("userPassword")) {
                ((LoginRealmProxyInterface) obj).realmSet$userPassword(null);
            } else {
                ((LoginRealmProxyInterface) obj).realmSet$userPassword((String) json.getString("userPassword"));
            }
        }
        if (json.has("userPrivilege")) {
            if (json.isNull("userPrivilege")) {
                ((LoginRealmProxyInterface) obj).realmSet$userPrivilege(null);
            } else {
                ((LoginRealmProxyInterface) obj).realmSet$userPrivilege((String) json.getString("userPrivilege"));
            }
        }
        if (json.has("merchantID")) {
            if (json.isNull("merchantID")) {
                ((LoginRealmProxyInterface) obj).realmSet$merchantID(null);
            } else {
                ((LoginRealmProxyInterface) obj).realmSet$merchantID((String) json.getString("merchantID"));
            }
        }
        if (json.has("outletID")) {
            if (json.isNull("outletID")) {
                ((LoginRealmProxyInterface) obj).realmSet$outletID(null);
            } else {
                ((LoginRealmProxyInterface) obj).realmSet$outletID((String) json.getString("outletID"));
            }
        }
        if (json.has("counter")) {
            if (json.isNull("counter")) {
                ((LoginRealmProxyInterface) obj).realmSet$counter(null);
            } else {
                ((LoginRealmProxyInterface) obj).realmSet$counter((String) json.getString("counter"));
            }
        }
        if (json.has("pictureUser")) {
            if (json.isNull("pictureUser")) {
                ((LoginRealmProxyInterface) obj).realmSet$pictureUser(null);
            } else {
                ((LoginRealmProxyInterface) obj).realmSet$pictureUser((String) json.getString("pictureUser"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static Login createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        Login obj = realm.createObject(Login.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("realmLoginID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field realmLoginID to null.");
                } else {
                    ((LoginRealmProxyInterface) obj).realmSet$realmLoginID((long) reader.nextLong());
                }
            } else if (name.equals("username")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LoginRealmProxyInterface) obj).realmSet$username(null);
                } else {
                    ((LoginRealmProxyInterface) obj).realmSet$username((String) reader.nextString());
                }
            } else if (name.equals("userPassword")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LoginRealmProxyInterface) obj).realmSet$userPassword(null);
                } else {
                    ((LoginRealmProxyInterface) obj).realmSet$userPassword((String) reader.nextString());
                }
            } else if (name.equals("userPrivilege")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LoginRealmProxyInterface) obj).realmSet$userPrivilege(null);
                } else {
                    ((LoginRealmProxyInterface) obj).realmSet$userPrivilege((String) reader.nextString());
                }
            } else if (name.equals("merchantID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LoginRealmProxyInterface) obj).realmSet$merchantID(null);
                } else {
                    ((LoginRealmProxyInterface) obj).realmSet$merchantID((String) reader.nextString());
                }
            } else if (name.equals("outletID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LoginRealmProxyInterface) obj).realmSet$outletID(null);
                } else {
                    ((LoginRealmProxyInterface) obj).realmSet$outletID((String) reader.nextString());
                }
            } else if (name.equals("counter")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LoginRealmProxyInterface) obj).realmSet$counter(null);
                } else {
                    ((LoginRealmProxyInterface) obj).realmSet$counter((String) reader.nextString());
                }
            } else if (name.equals("pictureUser")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LoginRealmProxyInterface) obj).realmSet$pictureUser(null);
                } else {
                    ((LoginRealmProxyInterface) obj).realmSet$pictureUser((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static Login copyOrUpdate(Realm realm, Login object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static Login copy(Realm realm, Login newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        Login realmObject = realm.createObject(Login.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((LoginRealmProxyInterface) realmObject).realmSet$realmLoginID(((LoginRealmProxyInterface) newObject).realmGet$realmLoginID());
        ((LoginRealmProxyInterface) realmObject).realmSet$username(((LoginRealmProxyInterface) newObject).realmGet$username());
        ((LoginRealmProxyInterface) realmObject).realmSet$userPassword(((LoginRealmProxyInterface) newObject).realmGet$userPassword());
        ((LoginRealmProxyInterface) realmObject).realmSet$userPrivilege(((LoginRealmProxyInterface) newObject).realmGet$userPrivilege());
        ((LoginRealmProxyInterface) realmObject).realmSet$merchantID(((LoginRealmProxyInterface) newObject).realmGet$merchantID());
        ((LoginRealmProxyInterface) realmObject).realmSet$outletID(((LoginRealmProxyInterface) newObject).realmGet$outletID());
        ((LoginRealmProxyInterface) realmObject).realmSet$counter(((LoginRealmProxyInterface) newObject).realmGet$counter());
        ((LoginRealmProxyInterface) realmObject).realmSet$pictureUser(((LoginRealmProxyInterface) newObject).realmGet$pictureUser());
        return realmObject;
    }

    public static Login createDetachedCopy(Login realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        Login standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (Login)cachedObject.object;
            } else {
                standaloneObject = (Login)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new Login();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((LoginRealmProxyInterface) standaloneObject).realmSet$realmLoginID(((LoginRealmProxyInterface) realmObject).realmGet$realmLoginID());
        ((LoginRealmProxyInterface) standaloneObject).realmSet$username(((LoginRealmProxyInterface) realmObject).realmGet$username());
        ((LoginRealmProxyInterface) standaloneObject).realmSet$userPassword(((LoginRealmProxyInterface) realmObject).realmGet$userPassword());
        ((LoginRealmProxyInterface) standaloneObject).realmSet$userPrivilege(((LoginRealmProxyInterface) realmObject).realmGet$userPrivilege());
        ((LoginRealmProxyInterface) standaloneObject).realmSet$merchantID(((LoginRealmProxyInterface) realmObject).realmGet$merchantID());
        ((LoginRealmProxyInterface) standaloneObject).realmSet$outletID(((LoginRealmProxyInterface) realmObject).realmGet$outletID());
        ((LoginRealmProxyInterface) standaloneObject).realmSet$counter(((LoginRealmProxyInterface) realmObject).realmGet$counter());
        ((LoginRealmProxyInterface) standaloneObject).realmSet$pictureUser(((LoginRealmProxyInterface) realmObject).realmGet$pictureUser());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Login = [");
        stringBuilder.append("{realmLoginID:");
        stringBuilder.append(realmGet$realmLoginID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{username:");
        stringBuilder.append(realmGet$username() != null ? realmGet$username() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{userPassword:");
        stringBuilder.append(realmGet$userPassword() != null ? realmGet$userPassword() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{userPrivilege:");
        stringBuilder.append(realmGet$userPrivilege() != null ? realmGet$userPrivilege() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{merchantID:");
        stringBuilder.append(realmGet$merchantID() != null ? realmGet$merchantID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{outletID:");
        stringBuilder.append(realmGet$outletID() != null ? realmGet$outletID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{counter:");
        stringBuilder.append(realmGet$counter() != null ? realmGet$counter() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{pictureUser:");
        stringBuilder.append(realmGet$pictureUser() != null ? realmGet$pictureUser() : "null");
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
        LoginRealmProxy aLogin = (LoginRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aLogin.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aLogin.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aLogin.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.Drawer;
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

public class DrawerRealmProxy extends Drawer
    implements RealmObjectProxy, DrawerRealmProxyInterface {

    static final class DrawerColumnInfo extends ColumnInfo {

        public final long drawerIDIndex;
        public final long drawerOutletIDIndex;
        public final long drawerCashierIDIndex;
        public final long drawerCashierNameIndex;
        public final long drawerStartingDateAndTimeIndex;
        public final long drawerStartingCashIndex;
        public final long drawerCashSalesIndex;
        public final long drawerCardSalesIndex;
        public final long drawerExpectationCashIndex;
        public final long drawerEndingDateAndTimeIndex;
        public final long drawerDescriptionIndex;
        public final long drawerActualCashIndex;

        DrawerColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(12);
            this.drawerIDIndex = getValidColumnIndex(path, table, "Drawer", "drawerID");
            indicesMap.put("drawerID", this.drawerIDIndex);

            this.drawerOutletIDIndex = getValidColumnIndex(path, table, "Drawer", "drawerOutletID");
            indicesMap.put("drawerOutletID", this.drawerOutletIDIndex);

            this.drawerCashierIDIndex = getValidColumnIndex(path, table, "Drawer", "drawerCashierID");
            indicesMap.put("drawerCashierID", this.drawerCashierIDIndex);

            this.drawerCashierNameIndex = getValidColumnIndex(path, table, "Drawer", "drawerCashierName");
            indicesMap.put("drawerCashierName", this.drawerCashierNameIndex);

            this.drawerStartingDateAndTimeIndex = getValidColumnIndex(path, table, "Drawer", "drawerStartingDateAndTime");
            indicesMap.put("drawerStartingDateAndTime", this.drawerStartingDateAndTimeIndex);

            this.drawerStartingCashIndex = getValidColumnIndex(path, table, "Drawer", "drawerStartingCash");
            indicesMap.put("drawerStartingCash", this.drawerStartingCashIndex);

            this.drawerCashSalesIndex = getValidColumnIndex(path, table, "Drawer", "drawerCashSales");
            indicesMap.put("drawerCashSales", this.drawerCashSalesIndex);

            this.drawerCardSalesIndex = getValidColumnIndex(path, table, "Drawer", "drawerCardSales");
            indicesMap.put("drawerCardSales", this.drawerCardSalesIndex);

            this.drawerExpectationCashIndex = getValidColumnIndex(path, table, "Drawer", "drawerExpectationCash");
            indicesMap.put("drawerExpectationCash", this.drawerExpectationCashIndex);

            this.drawerEndingDateAndTimeIndex = getValidColumnIndex(path, table, "Drawer", "drawerEndingDateAndTime");
            indicesMap.put("drawerEndingDateAndTime", this.drawerEndingDateAndTimeIndex);

            this.drawerDescriptionIndex = getValidColumnIndex(path, table, "Drawer", "drawerDescription");
            indicesMap.put("drawerDescription", this.drawerDescriptionIndex);

            this.drawerActualCashIndex = getValidColumnIndex(path, table, "Drawer", "drawerActualCash");
            indicesMap.put("drawerActualCash", this.drawerActualCashIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final DrawerColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("drawerID");
        fieldNames.add("drawerOutletID");
        fieldNames.add("drawerCashierID");
        fieldNames.add("drawerCashierName");
        fieldNames.add("drawerStartingDateAndTime");
        fieldNames.add("drawerStartingCash");
        fieldNames.add("drawerCashSales");
        fieldNames.add("drawerCardSales");
        fieldNames.add("drawerExpectationCash");
        fieldNames.add("drawerEndingDateAndTime");
        fieldNames.add("drawerDescription");
        fieldNames.add("drawerActualCash");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DrawerRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (DrawerColumnInfo) columnInfo;
        this.proxyState = new ProxyState(Drawer.class);
    }

    @SuppressWarnings("cast")
    public long realmGet$drawerID() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.drawerIDIndex);
    }

    public void realmSet$drawerID(long value) {
        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.drawerIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$drawerOutletID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.drawerOutletIDIndex);
    }

    public void realmSet$drawerOutletID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.drawerOutletIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.drawerOutletIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$drawerCashierID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.drawerCashierIDIndex);
    }

    public void realmSet$drawerCashierID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.drawerCashierIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.drawerCashierIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$drawerCashierName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.drawerCashierNameIndex);
    }

    public void realmSet$drawerCashierName(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.drawerCashierNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.drawerCashierNameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$drawerStartingDateAndTime() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.drawerStartingDateAndTimeIndex);
    }

    public void realmSet$drawerStartingDateAndTime(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.drawerStartingDateAndTimeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.drawerStartingDateAndTimeIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$drawerStartingCash() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.drawerStartingCashIndex);
    }

    public void realmSet$drawerStartingCash(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.drawerStartingCashIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.drawerStartingCashIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$drawerCashSales() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.drawerCashSalesIndex);
    }

    public void realmSet$drawerCashSales(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.drawerCashSalesIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.drawerCashSalesIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$drawerCardSales() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.drawerCardSalesIndex);
    }

    public void realmSet$drawerCardSales(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.drawerCardSalesIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.drawerCardSalesIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$drawerExpectationCash() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.drawerExpectationCashIndex);
    }

    public void realmSet$drawerExpectationCash(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.drawerExpectationCashIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.drawerExpectationCashIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$drawerEndingDateAndTime() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.drawerEndingDateAndTimeIndex);
    }

    public void realmSet$drawerEndingDateAndTime(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.drawerEndingDateAndTimeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.drawerEndingDateAndTimeIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$drawerDescription() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.drawerDescriptionIndex);
    }

    public void realmSet$drawerDescription(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.drawerDescriptionIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.drawerDescriptionIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$drawerActualCash() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.drawerActualCashIndex);
    }

    public void realmSet$drawerActualCash(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.drawerActualCashIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.drawerActualCashIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_Drawer")) {
            Table table = transaction.getTable("class_Drawer");
            table.addColumn(RealmFieldType.INTEGER, "drawerID", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "drawerOutletID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "drawerCashierID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "drawerCashierName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "drawerStartingDateAndTime", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "drawerStartingCash", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "drawerCashSales", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "drawerCardSales", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "drawerExpectationCash", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "drawerEndingDateAndTime", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "drawerDescription", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "drawerActualCash", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_Drawer");
    }

    public static DrawerColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_Drawer")) {
            Table table = transaction.getTable("class_Drawer");
            if (table.getColumnCount() != 12) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 12 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 12; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final DrawerColumnInfo columnInfo = new DrawerColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("drawerID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'drawerID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("drawerID") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'drawerID' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.drawerIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'drawerID' does support null values in the existing Realm file. Use corresponding boxed type for field 'drawerID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("drawerOutletID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'drawerOutletID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("drawerOutletID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'drawerOutletID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.drawerOutletIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'drawerOutletID' is required. Either set @Required to field 'drawerOutletID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("drawerCashierID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'drawerCashierID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("drawerCashierID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'drawerCashierID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.drawerCashierIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'drawerCashierID' is required. Either set @Required to field 'drawerCashierID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("drawerCashierName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'drawerCashierName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("drawerCashierName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'drawerCashierName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.drawerCashierNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'drawerCashierName' is required. Either set @Required to field 'drawerCashierName' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("drawerStartingDateAndTime")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'drawerStartingDateAndTime' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("drawerStartingDateAndTime") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'drawerStartingDateAndTime' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.drawerStartingDateAndTimeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'drawerStartingDateAndTime' is required. Either set @Required to field 'drawerStartingDateAndTime' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("drawerStartingCash")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'drawerStartingCash' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("drawerStartingCash") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'drawerStartingCash' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.drawerStartingCashIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'drawerStartingCash' is required. Either set @Required to field 'drawerStartingCash' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("drawerCashSales")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'drawerCashSales' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("drawerCashSales") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'drawerCashSales' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.drawerCashSalesIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'drawerCashSales' is required. Either set @Required to field 'drawerCashSales' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("drawerCardSales")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'drawerCardSales' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("drawerCardSales") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'drawerCardSales' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.drawerCardSalesIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'drawerCardSales' is required. Either set @Required to field 'drawerCardSales' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("drawerExpectationCash")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'drawerExpectationCash' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("drawerExpectationCash") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'drawerExpectationCash' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.drawerExpectationCashIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'drawerExpectationCash' is required. Either set @Required to field 'drawerExpectationCash' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("drawerEndingDateAndTime")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'drawerEndingDateAndTime' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("drawerEndingDateAndTime") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'drawerEndingDateAndTime' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.drawerEndingDateAndTimeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'drawerEndingDateAndTime' is required. Either set @Required to field 'drawerEndingDateAndTime' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("drawerDescription")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'drawerDescription' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("drawerDescription") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'drawerDescription' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.drawerDescriptionIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'drawerDescription' is required. Either set @Required to field 'drawerDescription' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("drawerActualCash")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'drawerActualCash' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("drawerActualCash") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'drawerActualCash' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.drawerActualCashIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'drawerActualCash' is required. Either set @Required to field 'drawerActualCash' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The Drawer class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_Drawer";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static Drawer createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        Drawer obj = realm.createObject(Drawer.class);
        if (json.has("drawerID")) {
            if (json.isNull("drawerID")) {
                throw new IllegalArgumentException("Trying to set non-nullable field drawerID to null.");
            } else {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerID((long) json.getLong("drawerID"));
            }
        }
        if (json.has("drawerOutletID")) {
            if (json.isNull("drawerOutletID")) {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerOutletID(null);
            } else {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerOutletID((String) json.getString("drawerOutletID"));
            }
        }
        if (json.has("drawerCashierID")) {
            if (json.isNull("drawerCashierID")) {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerCashierID(null);
            } else {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerCashierID((String) json.getString("drawerCashierID"));
            }
        }
        if (json.has("drawerCashierName")) {
            if (json.isNull("drawerCashierName")) {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerCashierName(null);
            } else {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerCashierName((String) json.getString("drawerCashierName"));
            }
        }
        if (json.has("drawerStartingDateAndTime")) {
            if (json.isNull("drawerStartingDateAndTime")) {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerStartingDateAndTime(null);
            } else {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerStartingDateAndTime((String) json.getString("drawerStartingDateAndTime"));
            }
        }
        if (json.has("drawerStartingCash")) {
            if (json.isNull("drawerStartingCash")) {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerStartingCash(null);
            } else {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerStartingCash((String) json.getString("drawerStartingCash"));
            }
        }
        if (json.has("drawerCashSales")) {
            if (json.isNull("drawerCashSales")) {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerCashSales(null);
            } else {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerCashSales((String) json.getString("drawerCashSales"));
            }
        }
        if (json.has("drawerCardSales")) {
            if (json.isNull("drawerCardSales")) {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerCardSales(null);
            } else {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerCardSales((String) json.getString("drawerCardSales"));
            }
        }
        if (json.has("drawerExpectationCash")) {
            if (json.isNull("drawerExpectationCash")) {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerExpectationCash(null);
            } else {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerExpectationCash((String) json.getString("drawerExpectationCash"));
            }
        }
        if (json.has("drawerEndingDateAndTime")) {
            if (json.isNull("drawerEndingDateAndTime")) {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerEndingDateAndTime(null);
            } else {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerEndingDateAndTime((String) json.getString("drawerEndingDateAndTime"));
            }
        }
        if (json.has("drawerDescription")) {
            if (json.isNull("drawerDescription")) {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerDescription(null);
            } else {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerDescription((String) json.getString("drawerDescription"));
            }
        }
        if (json.has("drawerActualCash")) {
            if (json.isNull("drawerActualCash")) {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerActualCash(null);
            } else {
                ((DrawerRealmProxyInterface) obj).realmSet$drawerActualCash((String) json.getString("drawerActualCash"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static Drawer createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        Drawer obj = realm.createObject(Drawer.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("drawerID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field drawerID to null.");
                } else {
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerID((long) reader.nextLong());
                }
            } else if (name.equals("drawerOutletID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerOutletID(null);
                } else {
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerOutletID((String) reader.nextString());
                }
            } else if (name.equals("drawerCashierID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerCashierID(null);
                } else {
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerCashierID((String) reader.nextString());
                }
            } else if (name.equals("drawerCashierName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerCashierName(null);
                } else {
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerCashierName((String) reader.nextString());
                }
            } else if (name.equals("drawerStartingDateAndTime")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerStartingDateAndTime(null);
                } else {
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerStartingDateAndTime((String) reader.nextString());
                }
            } else if (name.equals("drawerStartingCash")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerStartingCash(null);
                } else {
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerStartingCash((String) reader.nextString());
                }
            } else if (name.equals("drawerCashSales")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerCashSales(null);
                } else {
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerCashSales((String) reader.nextString());
                }
            } else if (name.equals("drawerCardSales")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerCardSales(null);
                } else {
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerCardSales((String) reader.nextString());
                }
            } else if (name.equals("drawerExpectationCash")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerExpectationCash(null);
                } else {
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerExpectationCash((String) reader.nextString());
                }
            } else if (name.equals("drawerEndingDateAndTime")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerEndingDateAndTime(null);
                } else {
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerEndingDateAndTime((String) reader.nextString());
                }
            } else if (name.equals("drawerDescription")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerDescription(null);
                } else {
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerDescription((String) reader.nextString());
                }
            } else if (name.equals("drawerActualCash")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerActualCash(null);
                } else {
                    ((DrawerRealmProxyInterface) obj).realmSet$drawerActualCash((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static Drawer copyOrUpdate(Realm realm, Drawer object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static Drawer copy(Realm realm, Drawer newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        Drawer realmObject = realm.createObject(Drawer.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((DrawerRealmProxyInterface) realmObject).realmSet$drawerID(((DrawerRealmProxyInterface) newObject).realmGet$drawerID());
        ((DrawerRealmProxyInterface) realmObject).realmSet$drawerOutletID(((DrawerRealmProxyInterface) newObject).realmGet$drawerOutletID());
        ((DrawerRealmProxyInterface) realmObject).realmSet$drawerCashierID(((DrawerRealmProxyInterface) newObject).realmGet$drawerCashierID());
        ((DrawerRealmProxyInterface) realmObject).realmSet$drawerCashierName(((DrawerRealmProxyInterface) newObject).realmGet$drawerCashierName());
        ((DrawerRealmProxyInterface) realmObject).realmSet$drawerStartingDateAndTime(((DrawerRealmProxyInterface) newObject).realmGet$drawerStartingDateAndTime());
        ((DrawerRealmProxyInterface) realmObject).realmSet$drawerStartingCash(((DrawerRealmProxyInterface) newObject).realmGet$drawerStartingCash());
        ((DrawerRealmProxyInterface) realmObject).realmSet$drawerCashSales(((DrawerRealmProxyInterface) newObject).realmGet$drawerCashSales());
        ((DrawerRealmProxyInterface) realmObject).realmSet$drawerCardSales(((DrawerRealmProxyInterface) newObject).realmGet$drawerCardSales());
        ((DrawerRealmProxyInterface) realmObject).realmSet$drawerExpectationCash(((DrawerRealmProxyInterface) newObject).realmGet$drawerExpectationCash());
        ((DrawerRealmProxyInterface) realmObject).realmSet$drawerEndingDateAndTime(((DrawerRealmProxyInterface) newObject).realmGet$drawerEndingDateAndTime());
        ((DrawerRealmProxyInterface) realmObject).realmSet$drawerDescription(((DrawerRealmProxyInterface) newObject).realmGet$drawerDescription());
        ((DrawerRealmProxyInterface) realmObject).realmSet$drawerActualCash(((DrawerRealmProxyInterface) newObject).realmGet$drawerActualCash());
        return realmObject;
    }

    public static Drawer createDetachedCopy(Drawer realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        Drawer standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (Drawer)cachedObject.object;
            } else {
                standaloneObject = (Drawer)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new Drawer();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((DrawerRealmProxyInterface) standaloneObject).realmSet$drawerID(((DrawerRealmProxyInterface) realmObject).realmGet$drawerID());
        ((DrawerRealmProxyInterface) standaloneObject).realmSet$drawerOutletID(((DrawerRealmProxyInterface) realmObject).realmGet$drawerOutletID());
        ((DrawerRealmProxyInterface) standaloneObject).realmSet$drawerCashierID(((DrawerRealmProxyInterface) realmObject).realmGet$drawerCashierID());
        ((DrawerRealmProxyInterface) standaloneObject).realmSet$drawerCashierName(((DrawerRealmProxyInterface) realmObject).realmGet$drawerCashierName());
        ((DrawerRealmProxyInterface) standaloneObject).realmSet$drawerStartingDateAndTime(((DrawerRealmProxyInterface) realmObject).realmGet$drawerStartingDateAndTime());
        ((DrawerRealmProxyInterface) standaloneObject).realmSet$drawerStartingCash(((DrawerRealmProxyInterface) realmObject).realmGet$drawerStartingCash());
        ((DrawerRealmProxyInterface) standaloneObject).realmSet$drawerCashSales(((DrawerRealmProxyInterface) realmObject).realmGet$drawerCashSales());
        ((DrawerRealmProxyInterface) standaloneObject).realmSet$drawerCardSales(((DrawerRealmProxyInterface) realmObject).realmGet$drawerCardSales());
        ((DrawerRealmProxyInterface) standaloneObject).realmSet$drawerExpectationCash(((DrawerRealmProxyInterface) realmObject).realmGet$drawerExpectationCash());
        ((DrawerRealmProxyInterface) standaloneObject).realmSet$drawerEndingDateAndTime(((DrawerRealmProxyInterface) realmObject).realmGet$drawerEndingDateAndTime());
        ((DrawerRealmProxyInterface) standaloneObject).realmSet$drawerDescription(((DrawerRealmProxyInterface) realmObject).realmGet$drawerDescription());
        ((DrawerRealmProxyInterface) standaloneObject).realmSet$drawerActualCash(((DrawerRealmProxyInterface) realmObject).realmGet$drawerActualCash());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Drawer = [");
        stringBuilder.append("{drawerID:");
        stringBuilder.append(realmGet$drawerID());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{drawerOutletID:");
        stringBuilder.append(realmGet$drawerOutletID() != null ? realmGet$drawerOutletID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{drawerCashierID:");
        stringBuilder.append(realmGet$drawerCashierID() != null ? realmGet$drawerCashierID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{drawerCashierName:");
        stringBuilder.append(realmGet$drawerCashierName() != null ? realmGet$drawerCashierName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{drawerStartingDateAndTime:");
        stringBuilder.append(realmGet$drawerStartingDateAndTime() != null ? realmGet$drawerStartingDateAndTime() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{drawerStartingCash:");
        stringBuilder.append(realmGet$drawerStartingCash() != null ? realmGet$drawerStartingCash() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{drawerCashSales:");
        stringBuilder.append(realmGet$drawerCashSales() != null ? realmGet$drawerCashSales() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{drawerCardSales:");
        stringBuilder.append(realmGet$drawerCardSales() != null ? realmGet$drawerCardSales() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{drawerExpectationCash:");
        stringBuilder.append(realmGet$drawerExpectationCash() != null ? realmGet$drawerExpectationCash() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{drawerEndingDateAndTime:");
        stringBuilder.append(realmGet$drawerEndingDateAndTime() != null ? realmGet$drawerEndingDateAndTime() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{drawerDescription:");
        stringBuilder.append(realmGet$drawerDescription() != null ? realmGet$drawerDescription() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{drawerActualCash:");
        stringBuilder.append(realmGet$drawerActualCash() != null ? realmGet$drawerActualCash() : "null");
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
        DrawerRealmProxy aDrawer = (DrawerRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aDrawer.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aDrawer.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aDrawer.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

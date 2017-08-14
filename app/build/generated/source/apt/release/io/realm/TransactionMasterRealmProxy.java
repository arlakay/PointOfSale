package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.ingenico.PointOfSale.ModelRealm.TransactionMaster;
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

public class TransactionMasterRealmProxy extends TransactionMaster
    implements RealmObjectProxy, TransactionMasterRealmProxyInterface {

    static final class TransactionMasterColumnInfo extends ColumnInfo {

        public final long transactionTerminalIDIndex;
        public final long transactionMasterIDIndex;
        public final long transactionMasterUserIDIndex;
        public final long transactionMasterCashierNameIndex;
        public final long transactionMasterTotalTransactionIndex;
        public final long transactionMasterTotalQuantityIndex;
        public final long transactionMasterTaxIndex;
        public final long transactionMasterServiceIndex;
        public final long transactionMasterDiscountIndex;
        public final long transactionMasterSubTotalIndex;
        public final long transactionMasterDateIndex;
        public final long transactionMasterDateAndTimeIndex;
        public final long transactionMasterPaymentTypeIndex;
        public final long transactionMasterTenderedIndex;
        public final long transactionMasterChangeIndex;
        public final long transactionMasterEmailIndex;
        public final long transactionMasterTableNumberIndex;

        TransactionMasterColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(17);
            this.transactionTerminalIDIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionTerminalID");
            indicesMap.put("transactionTerminalID", this.transactionTerminalIDIndex);

            this.transactionMasterIDIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterID");
            indicesMap.put("transactionMasterID", this.transactionMasterIDIndex);

            this.transactionMasterUserIDIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterUserID");
            indicesMap.put("transactionMasterUserID", this.transactionMasterUserIDIndex);

            this.transactionMasterCashierNameIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterCashierName");
            indicesMap.put("transactionMasterCashierName", this.transactionMasterCashierNameIndex);

            this.transactionMasterTotalTransactionIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterTotalTransaction");
            indicesMap.put("transactionMasterTotalTransaction", this.transactionMasterTotalTransactionIndex);

            this.transactionMasterTotalQuantityIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterTotalQuantity");
            indicesMap.put("transactionMasterTotalQuantity", this.transactionMasterTotalQuantityIndex);

            this.transactionMasterTaxIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterTax");
            indicesMap.put("transactionMasterTax", this.transactionMasterTaxIndex);

            this.transactionMasterServiceIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterService");
            indicesMap.put("transactionMasterService", this.transactionMasterServiceIndex);

            this.transactionMasterDiscountIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterDiscount");
            indicesMap.put("transactionMasterDiscount", this.transactionMasterDiscountIndex);

            this.transactionMasterSubTotalIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterSubTotal");
            indicesMap.put("transactionMasterSubTotal", this.transactionMasterSubTotalIndex);

            this.transactionMasterDateIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterDate");
            indicesMap.put("transactionMasterDate", this.transactionMasterDateIndex);

            this.transactionMasterDateAndTimeIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterDateAndTime");
            indicesMap.put("transactionMasterDateAndTime", this.transactionMasterDateAndTimeIndex);

            this.transactionMasterPaymentTypeIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterPaymentType");
            indicesMap.put("transactionMasterPaymentType", this.transactionMasterPaymentTypeIndex);

            this.transactionMasterTenderedIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterTendered");
            indicesMap.put("transactionMasterTendered", this.transactionMasterTenderedIndex);

            this.transactionMasterChangeIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterChange");
            indicesMap.put("transactionMasterChange", this.transactionMasterChangeIndex);

            this.transactionMasterEmailIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterEmail");
            indicesMap.put("transactionMasterEmail", this.transactionMasterEmailIndex);

            this.transactionMasterTableNumberIndex = getValidColumnIndex(path, table, "TransactionMaster", "transactionMasterTableNumber");
            indicesMap.put("transactionMasterTableNumber", this.transactionMasterTableNumberIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final TransactionMasterColumnInfo columnInfo;
    private final ProxyState proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("transactionTerminalID");
        fieldNames.add("transactionMasterID");
        fieldNames.add("transactionMasterUserID");
        fieldNames.add("transactionMasterCashierName");
        fieldNames.add("transactionMasterTotalTransaction");
        fieldNames.add("transactionMasterTotalQuantity");
        fieldNames.add("transactionMasterTax");
        fieldNames.add("transactionMasterService");
        fieldNames.add("transactionMasterDiscount");
        fieldNames.add("transactionMasterSubTotal");
        fieldNames.add("transactionMasterDate");
        fieldNames.add("transactionMasterDateAndTime");
        fieldNames.add("transactionMasterPaymentType");
        fieldNames.add("transactionMasterTendered");
        fieldNames.add("transactionMasterChange");
        fieldNames.add("transactionMasterEmail");
        fieldNames.add("transactionMasterTableNumber");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TransactionMasterRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (TransactionMasterColumnInfo) columnInfo;
        this.proxyState = new ProxyState(TransactionMaster.class);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionTerminalID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionTerminalIDIndex);
    }

    public void realmSet$transactionTerminalID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionTerminalIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionTerminalIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterIDIndex);
    }

    public void realmSet$transactionMasterID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterUserID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterUserIDIndex);
    }

    public void realmSet$transactionMasterUserID(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterUserIDIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterUserIDIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterCashierName() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterCashierNameIndex);
    }

    public void realmSet$transactionMasterCashierName(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterCashierNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterCashierNameIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterTotalTransaction() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterTotalTransactionIndex);
    }

    public void realmSet$transactionMasterTotalTransaction(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterTotalTransactionIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterTotalTransactionIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterTotalQuantity() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterTotalQuantityIndex);
    }

    public void realmSet$transactionMasterTotalQuantity(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterTotalQuantityIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterTotalQuantityIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterTax() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterTaxIndex);
    }

    public void realmSet$transactionMasterTax(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterTaxIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterTaxIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterService() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterServiceIndex);
    }

    public void realmSet$transactionMasterService(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterServiceIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterServiceIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterDiscount() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterDiscountIndex);
    }

    public void realmSet$transactionMasterDiscount(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterDiscountIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterDiscountIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterSubTotal() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterSubTotalIndex);
    }

    public void realmSet$transactionMasterSubTotal(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterSubTotalIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterSubTotalIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterDate() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterDateIndex);
    }

    public void realmSet$transactionMasterDate(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterDateIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterDateIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterDateAndTime() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterDateAndTimeIndex);
    }

    public void realmSet$transactionMasterDateAndTime(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterDateAndTimeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterDateAndTimeIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterPaymentType() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterPaymentTypeIndex);
    }

    public void realmSet$transactionMasterPaymentType(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterPaymentTypeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterPaymentTypeIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterTendered() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterTenderedIndex);
    }

    public void realmSet$transactionMasterTendered(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterTenderedIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterTenderedIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterChange() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterChangeIndex);
    }

    public void realmSet$transactionMasterChange(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterChangeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterChangeIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterEmail() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterEmailIndex);
    }

    public void realmSet$transactionMasterEmail(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterEmailIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterEmailIndex, value);
    }

    @SuppressWarnings("cast")
    public String realmGet$transactionMasterTableNumber() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.transactionMasterTableNumberIndex);
    }

    public void realmSet$transactionMasterTableNumber(String value) {
        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.transactionMasterTableNumberIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.transactionMasterTableNumberIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_TransactionMaster")) {
            Table table = transaction.getTable("class_TransactionMaster");
            table.addColumn(RealmFieldType.STRING, "transactionTerminalID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterUserID", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterCashierName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterTotalTransaction", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterTotalQuantity", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterTax", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterService", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterDiscount", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterSubTotal", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterDate", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterDateAndTime", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterPaymentType", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterTendered", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterChange", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterEmail", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "transactionMasterTableNumber", Table.NULLABLE);
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_TransactionMaster");
    }

    public static TransactionMasterColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_TransactionMaster")) {
            Table table = transaction.getTable("class_TransactionMaster");
            if (table.getColumnCount() != 17) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 17 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 17; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final TransactionMasterColumnInfo columnInfo = new TransactionMasterColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("transactionTerminalID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionTerminalID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionTerminalID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionTerminalID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionTerminalIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionTerminalID' is required. Either set @Required to field 'transactionTerminalID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterID' is required. Either set @Required to field 'transactionMasterID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterUserID")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterUserID' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterUserID") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterUserID' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterUserIDIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterUserID' is required. Either set @Required to field 'transactionMasterUserID' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterCashierName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterCashierName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterCashierName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterCashierName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterCashierNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterCashierName' is required. Either set @Required to field 'transactionMasterCashierName' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterTotalTransaction")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterTotalTransaction' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterTotalTransaction") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterTotalTransaction' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterTotalTransactionIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterTotalTransaction' is required. Either set @Required to field 'transactionMasterTotalTransaction' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterTotalQuantity")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterTotalQuantity' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterTotalQuantity") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterTotalQuantity' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterTotalQuantityIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterTotalQuantity' is required. Either set @Required to field 'transactionMasterTotalQuantity' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterTax")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterTax' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterTax") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterTax' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterTaxIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterTax' is required. Either set @Required to field 'transactionMasterTax' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterService")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterService' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterService") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterService' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterServiceIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterService' is required. Either set @Required to field 'transactionMasterService' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterDiscount")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterDiscount' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterDiscount") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterDiscount' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterDiscountIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterDiscount' is required. Either set @Required to field 'transactionMasterDiscount' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterSubTotal")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterSubTotal' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterSubTotal") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterSubTotal' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterSubTotalIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterSubTotal' is required. Either set @Required to field 'transactionMasterSubTotal' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterDate")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterDate' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterDate") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterDate' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterDateIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterDate' is required. Either set @Required to field 'transactionMasterDate' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterDateAndTime")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterDateAndTime' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterDateAndTime") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterDateAndTime' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterDateAndTimeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterDateAndTime' is required. Either set @Required to field 'transactionMasterDateAndTime' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterPaymentType")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterPaymentType' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterPaymentType") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterPaymentType' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterPaymentTypeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterPaymentType' is required. Either set @Required to field 'transactionMasterPaymentType' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterTendered")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterTendered' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterTendered") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterTendered' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterTenderedIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterTendered' is required. Either set @Required to field 'transactionMasterTendered' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterChange")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterChange' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterChange") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterChange' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterChangeIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterChange' is required. Either set @Required to field 'transactionMasterChange' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterEmail")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterEmail' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterEmail") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterEmail' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterEmailIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterEmail' is required. Either set @Required to field 'transactionMasterEmail' or migrate using RealmObjectSchema.setNullable().");
            }
            if (!columnTypes.containsKey("transactionMasterTableNumber")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'transactionMasterTableNumber' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("transactionMasterTableNumber") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'transactionMasterTableNumber' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.transactionMasterTableNumberIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'transactionMasterTableNumber' is required. Either set @Required to field 'transactionMasterTableNumber' or migrate using RealmObjectSchema.setNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The TransactionMaster class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_TransactionMaster";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static TransactionMaster createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        TransactionMaster obj = realm.createObject(TransactionMaster.class);
        if (json.has("transactionTerminalID")) {
            if (json.isNull("transactionTerminalID")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionTerminalID(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionTerminalID((String) json.getString("transactionTerminalID"));
            }
        }
        if (json.has("transactionMasterID")) {
            if (json.isNull("transactionMasterID")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterID(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterID((String) json.getString("transactionMasterID"));
            }
        }
        if (json.has("transactionMasterUserID")) {
            if (json.isNull("transactionMasterUserID")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterUserID(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterUserID((String) json.getString("transactionMasterUserID"));
            }
        }
        if (json.has("transactionMasterCashierName")) {
            if (json.isNull("transactionMasterCashierName")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterCashierName(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterCashierName((String) json.getString("transactionMasterCashierName"));
            }
        }
        if (json.has("transactionMasterTotalTransaction")) {
            if (json.isNull("transactionMasterTotalTransaction")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTotalTransaction(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTotalTransaction((String) json.getString("transactionMasterTotalTransaction"));
            }
        }
        if (json.has("transactionMasterTotalQuantity")) {
            if (json.isNull("transactionMasterTotalQuantity")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTotalQuantity(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTotalQuantity((String) json.getString("transactionMasterTotalQuantity"));
            }
        }
        if (json.has("transactionMasterTax")) {
            if (json.isNull("transactionMasterTax")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTax(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTax((String) json.getString("transactionMasterTax"));
            }
        }
        if (json.has("transactionMasterService")) {
            if (json.isNull("transactionMasterService")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterService(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterService((String) json.getString("transactionMasterService"));
            }
        }
        if (json.has("transactionMasterDiscount")) {
            if (json.isNull("transactionMasterDiscount")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterDiscount(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterDiscount((String) json.getString("transactionMasterDiscount"));
            }
        }
        if (json.has("transactionMasterSubTotal")) {
            if (json.isNull("transactionMasterSubTotal")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterSubTotal(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterSubTotal((String) json.getString("transactionMasterSubTotal"));
            }
        }
        if (json.has("transactionMasterDate")) {
            if (json.isNull("transactionMasterDate")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterDate(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterDate((String) json.getString("transactionMasterDate"));
            }
        }
        if (json.has("transactionMasterDateAndTime")) {
            if (json.isNull("transactionMasterDateAndTime")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterDateAndTime(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterDateAndTime((String) json.getString("transactionMasterDateAndTime"));
            }
        }
        if (json.has("transactionMasterPaymentType")) {
            if (json.isNull("transactionMasterPaymentType")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterPaymentType(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterPaymentType((String) json.getString("transactionMasterPaymentType"));
            }
        }
        if (json.has("transactionMasterTendered")) {
            if (json.isNull("transactionMasterTendered")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTendered(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTendered((String) json.getString("transactionMasterTendered"));
            }
        }
        if (json.has("transactionMasterChange")) {
            if (json.isNull("transactionMasterChange")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterChange(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterChange((String) json.getString("transactionMasterChange"));
            }
        }
        if (json.has("transactionMasterEmail")) {
            if (json.isNull("transactionMasterEmail")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterEmail(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterEmail((String) json.getString("transactionMasterEmail"));
            }
        }
        if (json.has("transactionMasterTableNumber")) {
            if (json.isNull("transactionMasterTableNumber")) {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTableNumber(null);
            } else {
                ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTableNumber((String) json.getString("transactionMasterTableNumber"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static TransactionMaster createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        TransactionMaster obj = realm.createObject(TransactionMaster.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("transactionTerminalID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionTerminalID(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionTerminalID((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterID(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterID((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterUserID")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterUserID(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterUserID((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterCashierName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterCashierName(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterCashierName((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterTotalTransaction")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTotalTransaction(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTotalTransaction((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterTotalQuantity")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTotalQuantity(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTotalQuantity((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterTax")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTax(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTax((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterService")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterService(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterService((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterDiscount")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterDiscount(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterDiscount((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterSubTotal")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterSubTotal(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterSubTotal((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterDate")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterDate(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterDate((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterDateAndTime")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterDateAndTime(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterDateAndTime((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterPaymentType")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterPaymentType(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterPaymentType((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterTendered")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTendered(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTendered((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterChange")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterChange(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterChange((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterEmail")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterEmail(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterEmail((String) reader.nextString());
                }
            } else if (name.equals("transactionMasterTableNumber")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTableNumber(null);
                } else {
                    ((TransactionMasterRealmProxyInterface) obj).realmSet$transactionMasterTableNumber((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static TransactionMaster copyOrUpdate(Realm realm, TransactionMaster object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static TransactionMaster copy(Realm realm, TransactionMaster newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        TransactionMaster realmObject = realm.createObject(TransactionMaster.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionTerminalID(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionTerminalID());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterID(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterID());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterUserID(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterUserID());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterCashierName(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterCashierName());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterTotalTransaction(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterTotalTransaction());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterTotalQuantity(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterTotalQuantity());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterTax(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterTax());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterService(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterService());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterDiscount(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterDiscount());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterSubTotal(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterSubTotal());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterDate(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterDate());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterDateAndTime(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterDateAndTime());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterPaymentType(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterPaymentType());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterTendered(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterTendered());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterChange(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterChange());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterEmail(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterEmail());
        ((TransactionMasterRealmProxyInterface) realmObject).realmSet$transactionMasterTableNumber(((TransactionMasterRealmProxyInterface) newObject).realmGet$transactionMasterTableNumber());
        return realmObject;
    }

    public static TransactionMaster createDetachedCopy(TransactionMaster realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        TransactionMaster standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (TransactionMaster)cachedObject.object;
            } else {
                standaloneObject = (TransactionMaster)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new TransactionMaster();
            cache.put(realmObject, new RealmObjectProxy.CacheData(currentDepth, standaloneObject));
        }
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionTerminalID(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionTerminalID());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterID(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterID());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterUserID(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterUserID());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterCashierName(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterCashierName());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterTotalTransaction(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterTotalTransaction());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterTotalQuantity(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterTotalQuantity());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterTax(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterTax());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterService(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterService());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterDiscount(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterDiscount());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterSubTotal(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterSubTotal());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterDate(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterDate());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterDateAndTime(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterDateAndTime());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterPaymentType(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterPaymentType());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterTendered(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterTendered());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterChange(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterChange());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterEmail(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterEmail());
        ((TransactionMasterRealmProxyInterface) standaloneObject).realmSet$transactionMasterTableNumber(((TransactionMasterRealmProxyInterface) realmObject).realmGet$transactionMasterTableNumber());
        return standaloneObject;
    }

    @Override
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("TransactionMaster = [");
        stringBuilder.append("{transactionTerminalID:");
        stringBuilder.append(realmGet$transactionTerminalID() != null ? realmGet$transactionTerminalID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterID:");
        stringBuilder.append(realmGet$transactionMasterID() != null ? realmGet$transactionMasterID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterUserID:");
        stringBuilder.append(realmGet$transactionMasterUserID() != null ? realmGet$transactionMasterUserID() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterCashierName:");
        stringBuilder.append(realmGet$transactionMasterCashierName() != null ? realmGet$transactionMasterCashierName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterTotalTransaction:");
        stringBuilder.append(realmGet$transactionMasterTotalTransaction() != null ? realmGet$transactionMasterTotalTransaction() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterTotalQuantity:");
        stringBuilder.append(realmGet$transactionMasterTotalQuantity() != null ? realmGet$transactionMasterTotalQuantity() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterTax:");
        stringBuilder.append(realmGet$transactionMasterTax() != null ? realmGet$transactionMasterTax() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterService:");
        stringBuilder.append(realmGet$transactionMasterService() != null ? realmGet$transactionMasterService() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterDiscount:");
        stringBuilder.append(realmGet$transactionMasterDiscount() != null ? realmGet$transactionMasterDiscount() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterSubTotal:");
        stringBuilder.append(realmGet$transactionMasterSubTotal() != null ? realmGet$transactionMasterSubTotal() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterDate:");
        stringBuilder.append(realmGet$transactionMasterDate() != null ? realmGet$transactionMasterDate() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterDateAndTime:");
        stringBuilder.append(realmGet$transactionMasterDateAndTime() != null ? realmGet$transactionMasterDateAndTime() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterPaymentType:");
        stringBuilder.append(realmGet$transactionMasterPaymentType() != null ? realmGet$transactionMasterPaymentType() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterTendered:");
        stringBuilder.append(realmGet$transactionMasterTendered() != null ? realmGet$transactionMasterTendered() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterChange:");
        stringBuilder.append(realmGet$transactionMasterChange() != null ? realmGet$transactionMasterChange() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterEmail:");
        stringBuilder.append(realmGet$transactionMasterEmail() != null ? realmGet$transactionMasterEmail() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{transactionMasterTableNumber:");
        stringBuilder.append(realmGet$transactionMasterTableNumber() != null ? realmGet$transactionMasterTableNumber() : "null");
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
        TransactionMasterRealmProxy aTransactionMaster = (TransactionMasterRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTransactionMaster.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTransactionMaster.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTransactionMaster.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}

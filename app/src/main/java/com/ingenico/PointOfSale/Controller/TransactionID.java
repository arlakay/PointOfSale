package com.ingenico.PointOfSale.Controller;

import com.ingenico.PointOfSale.ModelRealm.TransactionMaster;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 6/16/2016.
 */
public class TransactionID {

    public static String generate (Realm realm, String macAddress){
        String transactionID = generateTransactionID(macAddress);
        while (!canBeUse(realm,transactionID)){
            transactionID = generateTransactionID(macAddress);
        }
        return transactionID;
    }

    public static boolean canBeUse(Realm realm, String transaction_id) {
        boolean value = true;
        RealmResults<TransactionMaster> realmResultsTransactionMaster = realm.where(TransactionMaster.class).findAll();
        for (int i = 0; i < realmResultsTransactionMaster.size(); i++) {
            TransactionMaster transactionMaster = realmResultsTransactionMaster.get(i);
            if (transaction_id.equals(transactionMaster.getTransactionMasterID())){
                value = false;
            }
        }
        return value;
    }

    private static String generateTransactionID(String mac){
        int iterator = new Random().nextInt(9998) + 1;
        String lastFourDigit = mac.substring(12).replace(":","").toUpperCase();
        String formatted = String.format("%04d", iterator);
        return lastFourDigit + formatted;
    }

}

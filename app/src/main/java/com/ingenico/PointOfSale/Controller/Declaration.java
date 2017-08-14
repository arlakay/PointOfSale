package com.ingenico.PointOfSale.Controller;

import android.os.Environment;

/**
 * Created by Administrator-Handy on 4/13/2016.
 */
public class Declaration {
    /**
     * Log output level (Set to Warn-Level when app release.)
     *  -Fatal  1
     *  -Error  2
     *  -Info   3
     *  -Warn   4
     *  -Debug  5
     *  -Trace  6
     */
    public static final int LOG_OUTPUT_LEVEL = 5;

    //Log output path
    public static final String LOG_OUTPUT_PATH = Environment.getExternalStorageDirectory().getPath()+
            "/Android/data/com.ingenico.PointOfSale/Log/";

    //Image output path
    public static final String IMAGE_OUTPUT_PATH = Environment.getExternalStorageDirectory().getPath()+
            "/Android/data/com.ingenico.PointOfSale/Image/";
    /**
     * Log output to LogCat (Set to false when app release.)
     */
    public static final Boolean LOG_OUTPUT_TO_LOGCAT = true;
    /**
     * Url Login Address
     */
    public static final String URL_LOGIN="http://182.23.81.179/api/pos/loginV2.php";
//    public static final String URL_LOGIN="http://192.168.72.2/api/pos/loginV2.php";
//    public static final String URL_LOGIN="http://192.168.0.101/api/pos/loginV2.php";
//    public static final String URL_LOGIN="http://192.168.1.44/pos_lama/api/pos/loginV2.php";

    /**
     * Url Login Address72
     */
    public static final String URL_SYNC="http://182.23.81.179/api/pos/hit_api.php";
//    public static final String URL_SYNC="http://192.168.72.2/api/pos/hit_api.php";
//    public static final String URL_SYNC="http://192.168.0.101/api/pos/hit_api.php";
//    public static final String URL_SYNC="http://192.168.1.44/pos_lama/api/pos/hit_api.php";

    /**
     * Delimiter for every data in session
     */
    public static final String DELIMITER="•♪•";
    /**
     * Key md5 for password
     */
    public static final String KEY_MD5="=-0k,.^&*@!";
    /**
     * Socket time our volley
     */
    public static final int MY_SOCKET_TIMEOUT_MS = 10000;
    /**
     * Flag for unselected table in save order
     */
    public static final String UNSELECTED = "unSelected";
    /**
     * Flag for selected table in save order
     */
    public static final String SELECTED = "selected";
    /**
     * Flag not filled order in save order
     */
    public static final String NOT_FILLED = "not_filled";

    public static final String NULL_DATA = "null_data";

    public static final String SELECT_CATEGORY = "Select Category";

    public static final String ITEM_ID = "item_id";
    public static final String ITEM_NAME = "item_name";
    public static final String HARGA = "harga";
    public static final String DISCOUNT = "discount";
    public static final String PERCENT = "Persen";
    public static final String FIX_PRICE = "fix_price";
    public static final String PICT = "pict";
    public static final String ITEM_KATEGORI = "item_kategori";
    public static final String STATUS_TAX = "status_tax";
    public static final String VARIAN = "varian";
    public static final String MODIFIER = "modifier";
    public static final String OUTLET_ID = "outlet_id";
    public static final String ALL_OUTLET = "All_outlet";
    public static final String NO_CATEGORY = "no_category";
    public static final String CHOOSE_MANY = "choose_many";
    public static final String CHOOSE_ONE = "choose_one";

}

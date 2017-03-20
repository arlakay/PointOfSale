package com.ingenico.PointOfSale.LinearLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Controller.Image;
import com.ingenico.PointOfSale.Controller.RoundedImageView;
import com.ingenico.PointOfSale.ModelRealm.Item;
import com.ingenico.PointOfSale.ModelRealm.ModifierDetail;
import com.ingenico.PointOfSale.ModelRealm.Variant;
import com.ingenico.PointOfSale.R;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 7/17/2016.
 */
public class LinearLayoutHistoryItemList  {
    private Context context;
    private HashMap<String, Object> hashMapData;
    private LayoutInflater layoutInflater;
    private Realm realm;
    private Image image;

    /**
     * declare key
     */
    private static final String KEY_TRANSACTION_MASTER_ID = "key_transaction_master_id";
    private static final String KEY_TRANSACTION_MASTER_CASHIER_NAME = "key_transaction_master_cashier_name";
    private static final String KEY_TRANSACTION_MASTER_TOTAL_TRANSACTION = "key_transaction_master_total_transaction";
    private static final String KEY_TRANSACTION_MASTER_TOTAL_QUANTITY = "key_transaction_master_total_quantity";
    private static final String KEY_TRANSACTION_MASTER_TAX = "key_transaction_master_tax";
    private static final String KEY_TRANSACTION_MASTER_SERVICE = "key_transaction_master_service";
    private static final String KEY_TRANSACTION_MASTER_DISCOUNT = "key_transaction_master_discount";
    private static final String KEY_TRANSACTION_MASTER_SUB_TOTAL = "key_transaction_master_sub_total";
    private static final String KEY_TRANSACTION_MASTER_DATE_AND_TIME = "key_transaction_master_date_and_time";
    private static final String KEY_TRANSACTION_MASTER_PAYMENT_TYPE = "key_transaction_master_payment_type";
    private static final String KEY_TRANSACTION_MASTER_TENDERED = "key_transaction_master_tendered";
    private static final String KEY_TRANSACTION_MASTER_CHANGE = "key_transaction_master_change";

    private static final String KEY_TRANSACTION_DETAIL = "key_transaction_detail";
    private static final String KEY_TRANSACTION_DETAIL_ID = "key_transaction_detail_id";
    private static final String KEY_TRANSACTION_DETAIL_ITEM_ID = "key_transaction_detail_item_id";
    private static final String KEY_TRANSACTION_DETAIL_VARIANT_ID = "key_transaction_detail_variant_id";
    private static final String KEY_TRANSACTION_DETAIL_MODIFIER_ID = "key_transaction_detail_modifier_id";
    private static final String KEY_TRANSACTION_DETAIL_TOTAL_PRICE = "key_transaction_detail_total_price";
    private static final String KEY_TRANSACTION_DETAIL_DISCOUNT_ID = "key_transaction_detail_discount_id";
    private static final String KEY_TRANSACTION_DETAIL_QUANTITY = "key_transaction_detail_quantity";


    public LinearLayout LinearLayoutHistoryItemList(Context mContext, Realm mRealm, HashMap<String, Object> mHashMapDataTransactionDetail) {
        context = mContext;
        realm = mRealm;
        hashMapData = mHashMapDataTransactionDetail;
        image = new Image(context);
        /**
         * parent
         */
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParamsLinearLayout = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsLinearLayout.setMargins(0,5,0,5);
        linearLayout.setLayoutParams(layoutParamsLinearLayout);
        /**
         * image as child
         */
        RoundedImageView roundedImageView = new RoundedImageView(context);
        LinearLayout.LayoutParams layoutParamsRoundedImageView = new LinearLayout.LayoutParams(150,150);
        layoutParamsRoundedImageView.setMargins(0,0,10,0);
        roundedImageView.setLayoutParams(layoutParamsRoundedImageView);
        if (haveImage((String)hashMapData.get(KEY_TRANSACTION_DETAIL_ITEM_ID))){
            loadImageFromPath(Declaration.IMAGE_OUTPUT_PATH
                    +((String)hashMapData.get(KEY_TRANSACTION_DETAIL_ITEM_ID))
                    +".png",roundedImageView);
        }else {
            roundedImageView.setBackgroundColor(context.getResources().getColor(R.color.abu));
        }

        linearLayout.addView(roundedImageView);
        /**
         * linear layout as a child
         */
        LinearLayout linearLayoutAllDescription = new LinearLayout(context);
        linearLayoutAllDescription.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParamsLinearLayoutAllDescription = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutAllDescription.setLayoutParams(layoutParamsLinearLayoutAllDescription);

        if (((String)hashMapData.get(KEY_TRANSACTION_DETAIL_ITEM_ID)).contains("item")){
            linearLayoutAllDescription.addView(
                    setDataItemHistoryDescriptionFromItemTable(
                            realm,
                            (String)hashMapData.get(KEY_TRANSACTION_DETAIL_ITEM_ID),
                            ((String)hashMapData.get(KEY_TRANSACTION_DETAIL_QUANTITY))
                    )
            );
        }

        if (!((String)hashMapData.get(KEY_TRANSACTION_DETAIL_VARIANT_ID)).equals(Declaration.NULL_DATA)) {
            linearLayoutAllDescription.addView(
                    setDataItemHistoryDescriptionFromVariantTable(
                            realm,
                            ((String) hashMapData.get(KEY_TRANSACTION_DETAIL_VARIANT_ID))
                    )
            );
        }

        LinkedList<String> linkedListModifierID = (LinkedList<String>) hashMapData.get(KEY_TRANSACTION_DETAIL_MODIFIER_ID);
        if (linkedListModifierID.size()>0){
            if (!linkedListModifierID.get(0).equals("")){
                for (int i = 0; i < linkedListModifierID.size(); i++) {
                    linearLayoutAllDescription.addView(
                            setDataItemHistoryDescriptionFromModifierTable(
                                    realm,
                                    linkedListModifierID.get(i)
                            )
                    );
                }
            }
        }

        linearLayout.addView(linearLayoutAllDescription);

        return linearLayout;
    }

    private boolean haveImage (String item_id) {
        return ((new File(Declaration.IMAGE_OUTPUT_PATH + item_id + ".png")).exists());
    }

    private LinearLayout setDataItemHistoryDescriptionFromItemTable (Realm realm, String item_id, String total_item){
        RealmResults<Item> realmResultsItem = realm.where(Item.class).equalTo("itemID",item_id).findAll();
        return linearLayoutDescription(realmResultsItem.get(0).getItemName(),
                formatRupiah(realmResultsItem.get(0).getItemPrice()),
                "major",
                total_item);
    }

    private LinearLayout setDataItemHistoryDescriptionFromVariantTable (Realm realm, String variant_id){
        RealmResults<Variant> realmResultsVariant = realm.where(Variant.class).equalTo("VariantID",variant_id).findAll();
        return linearLayoutDescription(realmResultsVariant.get(0).getVariantName(),
                formatRupiah(realmResultsVariant.get(0).getVariantPrice()),
                "",
                "1");
    }

    private LinearLayout setDataItemHistoryDescriptionFromModifierTable (Realm realm, String modifier_id){
        RealmResults<ModifierDetail> realmResultsModifier = realm.where(ModifierDetail.class).equalTo("modifierDetailID",modifier_id).findAll();
        return linearLayoutDescription(realmResultsModifier.get(0).getModifierDetailName(),
                formatRupiah(realmResultsModifier.get(0).getModifierDetailPrice()),
                "minor",
                "1");
    }

    private String formatRupiah (String price_before_format){
        String after;
        after = "Rp " + convertValuePattern(price_before_format);
        return after;
    }

    private String unFormatRupiah (String formated_price){
        String price;
        price = revertValuePattern(formated_price.replace("Rp ",""));
        return price;
    }

    private String convertValuePattern (String value_before_pattern){
        return new DecimalFormat("###,###,###,###,###,###,###,###").format(Double.valueOf(value_before_pattern));
    }

    private String revertValuePattern (String value_before_pattern){
        if (value_before_pattern.contains(",")){
            return value_before_pattern.replaceAll(",","");
        }return value_before_pattern;
    }

    private int integerOf(String stringInteger){
        return Integer.parseInt(revertValuePattern(stringInteger));
    }

    private String stringOf(int integerString){
        return String.valueOf(integerString);
    }

    private void loadImageFromPath(String path, RoundedImageView imageView){
        Log.d("Load Path",path);
        File imgFile = new  File(path);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
        }
    }

    private LinearLayout linearLayoutDescription (String modifier_name, String modifier_price, String flag, String total_item){
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);

        TextView textViewModifierName = textView(modifier_name);
        LinearLayout.LayoutParams paramsTextViewModifierName = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTextViewModifierName.gravity = Gravity.START;
        textViewModifierName.setLayoutParams(paramsTextViewModifierName);

        String priceTemp = "";
        if (!modifier_price.equals("Rp 0")){
            priceTemp = modifier_price;
        }
        TextView textViewModifierPrice = textView(priceTemp);
        LinearLayout.LayoutParams paramsTextViewModifierPrice = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewModifierPrice.setTypeface(Typeface.DEFAULT_BOLD);
        textViewModifierPrice.setGravity(Gravity.END);
        textViewModifierPrice.setLayoutParams(paramsTextViewModifierPrice);

        linearLayout.addView(textViewModifierName);

        if (flag.equals("major")){
            textViewModifierName.setTextColor(context.getResources().getColor(R.color.black));
            textViewModifierPrice.setTextColor(context.getResources().getColor(R.color.black));

            if (integerOf(total_item)>1){
                TextView textViewCross = textView(" x ");
                textViewCross.setTextColor(context.getResources().getColor(R.color.black));

                TextView textViewTotalItem = textView(total_item);
                textViewTotalItem.setTextColor(context.getResources().getColor(R.color.black));

                linearLayout.addView(textViewCross);
                linearLayout.addView(textViewTotalItem);
            }
        }else if (flag.equals("")){
            textViewModifierPrice.setTextColor(context.getResources().getColor(R.color.black));
        }

        linearLayout.addView(textViewModifierPrice);

        return linearLayout;
    }


    private TextView textView(String text){
        TextView textView = new TextView(context);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setText(text);
        textView.setTextSize(20);
        return textView;
    }
}

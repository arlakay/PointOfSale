package com.ingenico.PointOfSale.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.PointOfSaleActivity;
import com.ingenico.PointOfSale.ModelRealm.DiscountMaster;
import com.ingenico.PointOfSale.ModelRealm.ModifierDetail;
import com.ingenico.PointOfSale.ModelRealm.ModifierGroup;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetail;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailDiscount;
import com.ingenico.PointOfSale.ModelRealm.TransactionDetailModifier;
import com.ingenico.PointOfSale.ModelRealm.Variant;
import com.ingenico.PointOfSale.R;
import com.ingenico.PointOfSale.CashRegisterActivity;

import java.util.HashMap;
import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 5/25/2016.
 */
public class ItemDialogAtCashRegister extends DialogFragment {

    private String transaction_id= "";
    private String item_id="";
    private String item_name="";
    private String item_price="";
    private String item_discount="";
    private String item_category="";
    private String item_status_tax="";
    private String item_variant="";
    private String item_modifier_group="";
    private boolean fromCart=false;


    public void setTransaction_id(String t_id){this.transaction_id = t_id;}
    public void setItem_id(String id){this.item_id = id;}
    public void setItem_name(String item_name) {this.item_name = item_name;}
    public void setItem_price(String item_price) {this.item_price = item_price;}
    public void setItem_discount(String item_discount) {this.item_discount = item_discount;}
    public void setItem_category(String item_category) {this.item_category = item_category;}
    public void setItem_status_tax(String item_status_tax) {this.item_status_tax = item_status_tax;}
    public void setItem_variant(String item_variant) {this.item_variant = item_variant;}
    public void setItem_modifier_group(String item_modifier_group) {this.item_modifier_group = item_modifier_group;}
    public void setFromCart(boolean itemFromCart){this.fromCart = itemFromCart;}

    private String KEY_TOPPING_ID = "key_topping_id";
    private String KEY_TOPPING_NAME = "key_topping_name";
    private String KEY_TOPPING_PRICE = "key_topping_price";

    private String KEY_DISCOUNT_ID = "key_discount_id";
    private String KEY_DISCOUNT_NAME = "key_discount_name";
    private String KEY_DISCOUNT_VALUE = "key_discount_value";
    private String KEY_DISCOUNT_TYPE = "key_discount_type";

    private LinkedList<HashMap<String, String>> linkedListDiscount;

    private LinkedList<LinearLayout> linearLayoutTemporaryAllButtonChooseOneModifier;
    private LinkedList<String> linkedListAllDataToppingID;

    LinearLayout linearLayoutItemDialogVariantAndModifier, linearLayoutItemDialogDiscountLeft,
            linearLayoutItemDialogDiscountRight;
    TextView textViewItemDialogItemName, textViewItemDialogPrice, textViewItemDialogMinus, textViewItemDialogPlus,
            textViewItemDialogTotalItem, textViewItemDialogAdd;
    EditText editTextItemDialogNote;
    ImageView imageViewItemDialogCancel;
    int intTotalItem = 1;

    private LinkedList<Switch> linkedListSwitch;
    LinkedList<LinearLayout> linearLayoutTemporaryAllButtonVariant;


    LinkedList<String> linkedListVariantID;
    LinkedList<String> linkedListResultAllTopping;
    LinkedList<String> linkedListResultDiscount;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View RootView=inflater.inflate(R.layout.dialog_item_cash_register, null);
        linearLayoutItemDialogVariantAndModifier = (LinearLayout)RootView.findViewById(R.id.linearLayoutItemDialogVariantAndModifier);
        linearLayoutItemDialogDiscountLeft = (LinearLayout)RootView.findViewById(R.id.linearLayoutItemDialogDiscountLeft);
        linearLayoutItemDialogDiscountRight = (LinearLayout)RootView.findViewById(R.id.linearLayoutItemDialogDiscountRight);
        editTextItemDialogNote = (EditText)RootView.findViewById(R.id.editTextItemDialogNote);
        textViewItemDialogItemName = (TextView)RootView.findViewById(R.id.textViewItemDialogItemName);
        textViewItemDialogPrice = (TextView)RootView.findViewById(R.id.textViewItemDialogPrice);
        textViewItemDialogMinus  = (TextView)RootView.findViewById(R.id.textViewItemDialogMinus);
        textViewItemDialogPlus  = (TextView)RootView.findViewById(R.id.textViewItemDialogPlus);
        textViewItemDialogTotalItem  = (TextView)RootView.findViewById(R.id.textViewItemDialogTotalItem);
        textViewItemDialogAdd = (TextView)RootView.findViewById(R.id.textViewItemDialogAdd);
        imageViewItemDialogCancel = (ImageView)RootView.findViewById(R.id.imageViewItemDialogCancel);

        linkedListVariantID = new LinkedList<>();
        linkedListResultAllTopping = new LinkedList<>();
        linkedListResultDiscount = new LinkedList<>();
        textViewItemDialogItemName.setText(item_name);

        /**
         * view for variant
         */
        ((CashRegisterActivity)getActivity()).logger.addInfo("Item Variant",item_variant);
        if (!item_variant.equals(Declaration.DELIMITER)) {
            LinkedList<HashMap<String, String>> linkedListTemporaryVariant = new LinkedList<>();
            for (String itemVariantID : item_variant.split(Declaration.DELIMITER)) {
                linkedListTemporaryVariant.add(hashMapVariant(((CashRegisterActivity)getActivity()).realm,itemVariantID));
                linkedListVariantID.add(itemVariantID);
                ((CashRegisterActivity)getActivity()).logger.addInfo("List Variant",linkedListTemporaryVariant.toString());
            }
            linearLayoutItemDialogVariantAndModifier.addView(populateLinearLayoutVariant("Variant", linkedListTemporaryVariant));
        }

        /**
         * view for all modifier (choose many or just only one)
         */
        ((CashRegisterActivity)getActivity()).logger.addInfo("Item Modifier",item_modifier_group);
        if (!item_modifier_group.equals(Declaration.DELIMITER)){
            for (String itemModifierGroupTemp : item_modifier_group.split(Declaration.DELIMITER)){
                Log.e("tolong",itemModifierGroupTemp);
                if (modifierGroupOptionFlag(((CashRegisterActivity)getActivity()).realm,itemModifierGroupTemp)
                        .equals("choose_one")){
                    ((CashRegisterActivity)getActivity()).logger.addInfo("List Modifier",linkedListModifierGroup(
                            ((CashRegisterActivity)getActivity()).realm,
                            itemModifierGroupTemp).toString());

                    linearLayoutItemDialogVariantAndModifier.addView(
                            populateLinearLayoutChooseOneModifier(
                                    modifierGroupName(((CashRegisterActivity)getActivity()).realm,itemModifierGroupTemp)
                                    ,linkedListModifierGroup(
                                            ((CashRegisterActivity)getActivity()).realm
                                            ,itemModifierGroupTemp)));
                }else {
                    ((CashRegisterActivity)getActivity()).logger.addInfo("List Modifier",linkedListModifierGroup(
                            ((CashRegisterActivity)getActivity()).realm,
                            itemModifierGroupTemp).toString());

                    linearLayoutItemDialogVariantAndModifier.addView(populateLinearLayoutModifier(
                            modifierGroupName(((CashRegisterActivity)getActivity()).realm,itemModifierGroupTemp),
                            linkedListModifierGroup(
                                    ((CashRegisterActivity)getActivity()).realm,
                                    itemModifierGroupTemp)));
                }
            }

        }

        /**
         * view for discount percentage
         */
        RealmResults<DiscountMaster> realmResultsDiscountMaster = ((CashRegisterActivity)getActivity())
                .realm.where(DiscountMaster.class)
                .equalTo("discountMasterType","Persen")
                .findAll();
        ((CashRegisterActivity) getActivity()).logger.addInfo("DiscountMaster",realmResultsDiscountMaster.toString());
        linkedListDiscount = new LinkedList<>();
        for (int d = 0; d < realmResultsDiscountMaster.size(); d++) {
            DiscountMaster discountMaster = realmResultsDiscountMaster.get(d);
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put(KEY_DISCOUNT_ID,discountMaster.getDiscountMasterID());
            hashMap.put(KEY_DISCOUNT_NAME,discountMaster.getDiscountMasterName());
            hashMap.put(KEY_DISCOUNT_VALUE,discountMaster.getDiscountMasterValue());
            hashMap.put(KEY_DISCOUNT_TYPE,discountMaster.getDiscountMasterType());
            linkedListDiscount.add(hashMap);
        }
        addViewSwitchDiscount(linkedListDiscount);


        /**
         * handle add and minus item in dialog to view
         */
        textViewItemDialogPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intTotalItem +=1;
                textViewItemDialogTotalItem.setText(String.valueOf(intTotalItem));
                changePriceInDialog(textViewItemDialogPrice,linkedListResultAllTopping,textViewItemDialogTotalItem,item_price,linkedListDiscount);
            }
        });

        textViewItemDialogMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intTotalItem>1){
                    intTotalItem -=1;
                    textViewItemDialogTotalItem.setText(String.valueOf(intTotalItem));
                    changePriceInDialog(textViewItemDialogPrice,linkedListResultAllTopping,textViewItemDialogTotalItem,item_price,linkedListDiscount);
                }
            }
        });

        /**
         * handle add trigger fill item to cart
         */
        textViewItemDialogAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CashRegisterActivity) getActivity()).logger.addInfo("RESULT TOPPING",linkedListResultAllTopping.toString());
                ((CashRegisterActivity) getActivity()).logger.addInfo("RESULT Discount",linkedListResultDiscount.toString());
                String variant_id = Declaration.NULL_DATA;
                for (int o = 0; o < linkedListResultAllTopping.size(); o++) {
                    if (linkedListResultAllTopping.get(o).contains("var")){
                        variant_id = linkedListResultAllTopping.get(o);
                    }
                }
                if (isLastItem(((CashRegisterActivity) getActivity()).realm,item_id,variant_id,textViewItemDialogPrice.getText().toString(),
                        linkedListResultAllTopping, linkedListResultDiscount)){
                    updateQuantityItemOrder(((CashRegisterActivity) getActivity()).realm,textViewItemDialogTotalItem.getText().toString());
                }else {
                    ((CashRegisterActivity) getActivity()).logger.addInfo("RESULT VARIANT",variant_id);
                    saveOrderToDatabase(((CashRegisterActivity) getActivity()).realm,transaction_id,item_id,variant_id,
                            textViewItemDialogPrice.getText().toString(),textViewItemDialogTotalItem.getText().toString(),
                            linkedListResultAllTopping, linkedListResultDiscount);
                }


                ((CashRegisterActivity)getActivity()).gridViewItemFromCategory.setEnabled(true);
                getDialog().dismiss();
            }
        });

        /**
         * handle cancel button
         */
        imageViewItemDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CashRegisterActivity)getActivity()).gridViewItemFromCategory.setEnabled(true);
                getDialog().dismiss();
            }
        });


        changePriceInDialog(textViewItemDialogPrice,linkedListResultAllTopping,textViewItemDialogTotalItem,item_price,linkedListDiscount);
        setCancelable(false);
        builder.setView(RootView);
        return builder.create();
    }

    // Variantbutton
    private LinearLayout populateLinearLayoutVariant (String title,
                                                      final LinkedList<HashMap<String,String>> linkedListAllDataTopping){
        final int leftButton;
        //final int[] flag_choose_one = {0};
        linearLayoutTemporaryAllButtonVariant = new LinkedList<>();
        if (linkedListAllDataTopping.size()%2==1){
            leftButton = (linkedListAllDataTopping.size()/2)+1;
        }else {
            leftButton = (linkedListAllDataTopping.size()/2);
        }
        ((CashRegisterActivity)getActivity()).logger.addInfo("Button",linkedListAllDataTopping.toString());
        /**
         * created linear layout as a child
         */
        LinearLayout linearLayoutOfOneVariant= new LinearLayout(getActivity());
        LinearLayout.LayoutParams layoutParamsOfOneVariant = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsOfOneVariant.setMargins(0,0,0,5);
        linearLayoutOfOneVariant.setLayoutParams(layoutParamsOfOneVariant);
        linearLayoutOfOneVariant.setOrientation(LinearLayout.VERTICAL);
        /**
         * created line in view
         */
        View viewItemDialogGroupVariant = new View(getActivity());
        LinearLayout.LayoutParams layoutParamsViewLine = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        layoutParamsViewLine.setMargins(0,0,0,10);
        viewItemDialogGroupVariant.setLayoutParams(layoutParamsViewLine);
        viewItemDialogGroupVariant.setBackgroundColor(getResources().getColor(R.color.abu));
        /**
         * created text view variant or modifier name
         */
        TextView textViewVariantName = new TextView(getActivity());
        LinearLayout.LayoutParams layoutParamsTextViewVariantName = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsTextViewVariantName.setMargins(0,0,0,10);
        textViewVariantName.setLayoutParams(layoutParamsTextViewVariantName);
        textViewVariantName.setTextColor(getResources().getColor(R.color.black));
        textViewVariantName.setTextSize(18);
        textViewVariantName.setTypeface(null,Typeface.BOLD);
        textViewVariantName.setText(title);
        /**
         * created linear layout option as a child
         */
        LinearLayout linearLayoutOfOneVariantOption = new LinearLayout(getActivity());
        linearLayoutOfOneVariantOption.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayoutOfOneVariantOption.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutOfOneVariantOption.setWeightSum(10);
        /**
         * created linear layout option on left as a child
         */
        LinearLayout linearLayoutOfOneVariantOptionLeft = new LinearLayout(getActivity());
        LinearLayout.LayoutParams layoutParamsOfOneVariantOptionLeft = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsOfOneVariantOptionLeft.setMargins(0,0,10,0);
        layoutParamsOfOneVariantOptionLeft.weight = 5;
        linearLayoutOfOneVariantOptionLeft.setLayoutParams(layoutParamsOfOneVariantOptionLeft);
        linearLayoutOfOneVariantOptionLeft.setOrientation(LinearLayout.VERTICAL);
        linearLayoutOfOneVariantOptionLeft.setWeightSum(1);


        for (int j = 0; j < leftButton; j++) {
            final int final_j = j;
            final int[] flag_selected_left = {0};
            /**
             * created linear layout option item on as a child
             */
            final LinearLayout linearLayoutOfOneVariantOptionItemLeft = new LinearLayout(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOptionItemLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, dp_to_pixel(getResources().getDimension(R.dimen.height_variant_modifier)));
            layoutParamsOfOneVariantOptionItemLeft.setMargins(0,0,0,5);
            linearLayoutOfOneVariantOptionItemLeft.setLayoutParams(layoutParamsOfOneVariantOptionItemLeft);
            linearLayoutOfOneVariantOptionItemLeft.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutOfOneVariantOptionItemLeft.setWeightSum(10);
            linearLayoutOfOneVariantOptionItemLeft.setPadding(10,10,10,10);
            if (title.equals("Variant") && j==0){
                flag_selected_left[0] = 1;
                //flag_choose_one[0] = j;
                linearLayoutOfOneVariantOptionItemLeft.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded_selected));
                inputResultToppingIDVariant(linkedListResultAllTopping, linkedListVariantID, linkedListAllDataTopping.get(0).get(KEY_TOPPING_ID));
            } else {
                flag_selected_left[0] = 0;
                linearLayoutOfOneVariantOptionItemLeft.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
            }
            /**
             * created linear layout option item on as a child
             */
            TextView textViewVariantNameItemLeft = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsTextViewVariantNameItemLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParamsTextViewVariantNameItemLeft.setMargins(10,0,0,0);
            layoutParamsTextViewVariantNameItemLeft.weight = 5;
            textViewVariantNameItemLeft.setLayoutParams(layoutParamsTextViewVariantNameItemLeft);
            textViewVariantNameItemLeft.setTextSize(17);
            textViewVariantNameItemLeft.setGravity(Gravity.START|Gravity.CENTER);
            textViewVariantNameItemLeft.setText(linkedListAllDataTopping.get(j).get(KEY_TOPPING_NAME));

            /**
             * created linear layout option item on for price as a child
             */
            LinearLayout linearLayoutOfOneVariantOptionItemPriceLeft = new LinearLayout(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOptionItemPriceLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParamsOfOneVariantOptionItemPriceLeft.setMargins(0,0,10,0);
            layoutParamsOfOneVariantOptionItemPriceLeft.weight = 5;
            linearLayoutOfOneVariantOptionItemPriceLeft.setLayoutParams(layoutParamsOfOneVariantOptionItemPriceLeft);
            linearLayoutOfOneVariantOptionItemPriceLeft.setGravity(Gravity.END|Gravity.CENTER);
            /**
             * created price of variant and modifier as a child
             */
            TextView textViewOfOneVariantOptionItemPriceRPLeft = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOrModifierOptionItemPriceRPLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            textViewOfOneVariantOptionItemPriceRPLeft.setLayoutParams(layoutParamsOfOneVariantOrModifierOptionItemPriceRPLeft);
            textViewOfOneVariantOptionItemPriceRPLeft.setTextSize(17);
            textViewOfOneVariantOptionItemPriceRPLeft.setText("Rp. ");
            textViewOfOneVariantOptionItemPriceRPLeft.setGravity(Gravity.CENTER_VERTICAL);
            /**
             * created price of variant and modifier as a child
             */
            TextView textViewOfOneVariantOptionItemPriceAmountLeft = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOptionItemPriceAmountLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            textViewOfOneVariantOptionItemPriceAmountLeft.setLayoutParams(layoutParamsOfOneVariantOptionItemPriceAmountLeft);
            textViewOfOneVariantOptionItemPriceAmountLeft.setTextSize(17);
            textViewOfOneVariantOptionItemPriceAmountLeft.setGravity(Gravity.CENTER_VERTICAL);
            textViewOfOneVariantOptionItemPriceAmountLeft.setText(
                    ((PointOfSaleActivity)getActivity()).convertValuePattern(
                            linkedListAllDataTopping.get(j).get(KEY_TOPPING_PRICE)
                    )
            );
            /**
             * put text view variant or modifier price item to the parent
             */
            linearLayoutOfOneVariantOptionItemPriceLeft.addView(textViewOfOneVariantOptionItemPriceRPLeft);
            linearLayoutOfOneVariantOptionItemPriceLeft.addView(textViewOfOneVariantOptionItemPriceAmountLeft);
            /**
             * put text view variant or modifier name item to the parent
             */
            linearLayoutOfOneVariantOptionItemLeft.addView(textViewVariantNameItemLeft);
            linearLayoutOfOneVariantOptionItemLeft.addView(linearLayoutOfOneVariantOptionItemPriceLeft);

            /**
             * make a button (not use because cant chane view from inner private void
             * we need to change the view the background of linear layout of topping button)
             */
            LinearLayout ButtonVariantOrModifierLeft = new LinearLayout(getActivity());
/*            ButtonVariantOrModifierLeft.addView(populateButtonVariantOrModifier(
                    linkedListAllDataTopping.get(j).get(KEY_TOPPING_NAME),
                    linkedListAllDataTopping.get(j).get(KEY_TOPPING_PRICE)));
            /**
             * show button to view
             */
            linearLayoutOfOneVariantOptionLeft.addView(linearLayoutOfOneVariantOptionItemLeft);
            linearLayoutTemporaryAllButtonVariant.add(linearLayoutOfOneVariantOptionItemLeft);
            /**
             * handle button event
             */

            linearLayoutOfOneVariantOptionItemLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int a = 0; a < linearLayoutTemporaryAllButtonVariant.size(); a++) {
                        linearLayoutTemporaryAllButtonVariant.get(a).setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
                    }
                    linearLayoutOfOneVariantOptionItemLeft.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded_selected));
                    //Toast.makeText(getActivity(),linkedListAllDataTopping.get(final_j).get(KEY_TOPPING_ID),Toast.LENGTH_SHORT).show();
                    inputResultToppingIDVariant(linkedListResultAllTopping, linkedListVariantID, linkedListAllDataTopping.get(final_j).get(KEY_TOPPING_ID));
                    changePriceInDialog(textViewItemDialogPrice,linkedListResultAllTopping,textViewItemDialogTotalItem,item_price,linkedListDiscount);
                }
            });
        }

        /**
         * created linear layout option on right as a child
         */
        LinearLayout linearLayoutOfOneVariantOptionRight = new LinearLayout(getActivity());
        LinearLayout.LayoutParams layoutParamsOfOneVariantOptionRight = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsOfOneVariantOptionRight.weight = 5;
        linearLayoutOfOneVariantOptionRight.setLayoutParams(layoutParamsOfOneVariantOptionRight);
        linearLayoutOfOneVariantOptionRight.setOrientation(LinearLayout.VERTICAL);
        linearLayoutOfOneVariantOptionRight.setWeightSum(1);

        for (int k = leftButton; k < linkedListAllDataTopping.size(); k++) {
            /**
             * created linear layout option item on as a child
             */
            final LinearLayout linearLayoutOfOneVariantOptionItemRight = new LinearLayout(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOptionItemRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, dp_to_pixel(getResources().getDimension(R.dimen.height_variant_modifier)));
            layoutParamsOfOneVariantOptionItemRight.setMargins(0,0,0,5);
            linearLayoutOfOneVariantOptionItemRight.setLayoutParams(layoutParamsOfOneVariantOptionItemRight);
            linearLayoutOfOneVariantOptionItemRight.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutOfOneVariantOptionItemRight.setWeightSum(10);
            linearLayoutOfOneVariantOptionItemRight.setPadding(10,10,10,10);
            linearLayoutOfOneVariantOptionItemRight.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
            /**
             * created linear layout option item on as a child
             */
            TextView textViewVariantNameItemRight = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsTextViewVariantNameItemRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParamsTextViewVariantNameItemRight.setMargins(10,0,0,0);
            layoutParamsTextViewVariantNameItemRight.weight = 5;
            textViewVariantNameItemRight.setLayoutParams(layoutParamsTextViewVariantNameItemRight);
            textViewVariantNameItemRight.setTextSize(17);
            textViewVariantNameItemRight.setGravity(Gravity.START|Gravity.CENTER);
            textViewVariantNameItemRight.setText(linkedListAllDataTopping.get(k).get(KEY_TOPPING_NAME));

            /**
             * created linear layout option item on for price as a child
             */
            LinearLayout linearLayoutOfOneVariantOptionItemPriceRight = new LinearLayout(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOptionItemPriceRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParamsOfOneVariantOptionItemPriceRight.setMargins(0,0,10,0);
            layoutParamsOfOneVariantOptionItemPriceRight.weight = 5;
            linearLayoutOfOneVariantOptionItemPriceRight.setLayoutParams(layoutParamsOfOneVariantOptionItemPriceRight);
            linearLayoutOfOneVariantOptionItemPriceRight.setGravity(Gravity.END|Gravity.CENTER);
            /**
             * created price of variant and modifier as a child
             */
            TextView textViewOfOneVariantOptionItemPriceRPRight = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOptionItemPriceRPRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            textViewOfOneVariantOptionItemPriceRPRight.setLayoutParams(layoutParamsOfOneVariantOptionItemPriceRPRight);
            textViewOfOneVariantOptionItemPriceRPRight.setTextSize(17);
            textViewOfOneVariantOptionItemPriceRPRight.setText("Rp. ");
            textViewOfOneVariantOptionItemPriceRPRight.setGravity(Gravity.CENTER_VERTICAL);
            /**
             * created price of variant and modifier as a child
             */
            TextView textViewOfOneVariantOptionItemPriceAmountRight = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOptionItemPriceAmountRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            textViewOfOneVariantOptionItemPriceAmountRight.setLayoutParams(layoutParamsOfOneVariantOptionItemPriceAmountRight);
            textViewOfOneVariantOptionItemPriceAmountRight.setTextSize(17);
            textViewOfOneVariantOptionItemPriceAmountRight.setGravity(Gravity.CENTER_VERTICAL);
            textViewOfOneVariantOptionItemPriceAmountRight.setText(
                    ((PointOfSaleActivity)getActivity()).convertValuePattern(
                            linkedListAllDataTopping.get(k).get(KEY_TOPPING_PRICE)
                    )
            );
            /**
             * put text view variant or modifier price item to the parent
             */
            linearLayoutOfOneVariantOptionItemPriceRight.addView(textViewOfOneVariantOptionItemPriceRPRight);
            linearLayoutOfOneVariantOptionItemPriceRight.addView(textViewOfOneVariantOptionItemPriceAmountRight);
            /**
             * put text view variant or modifier name item to the parent
             */
            linearLayoutOfOneVariantOptionItemRight.addView(textViewVariantNameItemRight);
            linearLayoutOfOneVariantOptionItemRight.addView(linearLayoutOfOneVariantOptionItemPriceRight);

            /**
             * make a button
             */
            final LinearLayout ButtonVariantOrModifierRight = new LinearLayout(getActivity());
/*            ButtonVariantOrModifierRight.addView(populateButtonVariantOrModifier(
                    linkedListAllDataTopping.get(k).get(KEY_TOPPING_NAME),
                    linkedListAllDataTopping.get(k).get(KEY_TOPPING_PRICE)));
            /**
             * show button to view
             */
            linearLayoutOfOneVariantOptionRight.addView(linearLayoutOfOneVariantOptionItemRight);
            linearLayoutTemporaryAllButtonVariant.add(linearLayoutOfOneVariantOptionItemRight);
            /**
             * handle button event
             */
            final int final_k = k;
            final int[] flag_selected_Right = {0};
            linearLayoutOfOneVariantOptionItemRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int a = 0; a < linearLayoutTemporaryAllButtonVariant.size(); a++) {
                        linearLayoutTemporaryAllButtonVariant.get(a).setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
                    }
                    linearLayoutOfOneVariantOptionItemRight.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded_selected));
                    //Toast.makeText(getActivity(),linkedListAllDataTopping.get(final_k).get(KEY_TOPPING_ID),Toast.LENGTH_SHORT).show();
                    inputResultToppingIDVariant(linkedListResultAllTopping, linkedListVariantID, linkedListAllDataTopping.get(final_k).get(KEY_TOPPING_ID));
                    changePriceInDialog(textViewItemDialogPrice,linkedListResultAllTopping,textViewItemDialogTotalItem,item_price,linkedListDiscount);
                }
            });
        }

        /**
         * put text view title to the parent
         */
        linearLayoutOfOneVariant.addView(textViewVariantName);
        /**
         * put linear layout option on left to the parent
         */
        linearLayoutOfOneVariantOption.addView(linearLayoutOfOneVariantOptionLeft);
        /**
         * put linear layout option on right to the parent
         */
        linearLayoutOfOneVariantOption.addView(linearLayoutOfOneVariantOptionRight);
        /**
         * put linear layout option to the parent
         */
        linearLayoutOfOneVariant.addView(linearLayoutOfOneVariantOption);
        /**
         * put line to the parent
         */
        linearLayoutOfOneVariant.addView(viewItemDialogGroupVariant);

        return linearLayoutOfOneVariant;
    }

    // choosemanybutton
    private LinearLayout populateLinearLayoutModifier (String title,
                                                       final LinkedList<HashMap<String,String>> linkedListAllDataTopping){
        final int leftButton;
        final int[] flag_choose_one = {0};
        //final LinkedList<LinearLayout> linearLayout = new LinkedList<>();
        if (linkedListAllDataTopping.size()%2==1){
            leftButton = (linkedListAllDataTopping.size()/2)+1;
        }else {
            leftButton = (linkedListAllDataTopping.size()/2);
        }
        ((CashRegisterActivity)getActivity()).logger.addInfo("Button",linkedListAllDataTopping.toString());
        /**
         * created linear layout as a child
         */
        LinearLayout linearLayoutOfOneVariantOrModifier = new LinearLayout(getActivity());
        LinearLayout.LayoutParams layoutParamsOfOneVariantOrModifier = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsOfOneVariantOrModifier.setMargins(0,0,0,5);
        linearLayoutOfOneVariantOrModifier.setLayoutParams(layoutParamsOfOneVariantOrModifier);
        linearLayoutOfOneVariantOrModifier.setOrientation(LinearLayout.VERTICAL);
        /**
         * created line in view
         */
        View viewItemDialogGroupModifier = new View(getActivity());
        LinearLayout.LayoutParams layoutParamsViewLine = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        layoutParamsViewLine.setMargins(0,0,0,10);
        viewItemDialogGroupModifier.setLayoutParams(layoutParamsViewLine);
        viewItemDialogGroupModifier.setBackgroundColor(getResources().getColor(R.color.abu));
        /**
         * created text view variant or modifier name
         */
        TextView textViewVariantOrModifierName = new TextView(getActivity());
        LinearLayout.LayoutParams layoutParamsTextViewVariantOrModifierName = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsTextViewVariantOrModifierName.setMargins(0,0,0,10);
        textViewVariantOrModifierName.setLayoutParams(layoutParamsTextViewVariantOrModifierName);
        textViewVariantOrModifierName.setTextColor(getResources().getColor(R.color.black));
        textViewVariantOrModifierName.setTextSize(18);
        textViewVariantOrModifierName.setTypeface(null,Typeface.BOLD);
        textViewVariantOrModifierName.setText(title);
        /**
         * created linear layout option as a child
         */
        LinearLayout linearLayoutOfOneVariantOrModifierOption = new LinearLayout(getActivity());
        linearLayoutOfOneVariantOrModifierOption.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayoutOfOneVariantOrModifierOption.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutOfOneVariantOrModifierOption.setWeightSum(10);
        /**
         * created linear layout option on left as a child
         */
        LinearLayout linearLayoutOfOneVariantOrModifierOptionLeft = new LinearLayout(getActivity());
        LinearLayout.LayoutParams layoutParamsOfOneVariantOrModifierOptionLeft = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsOfOneVariantOrModifierOptionLeft.setMargins(0,0,10,0);
        layoutParamsOfOneVariantOrModifierOptionLeft.weight = 5;
        linearLayoutOfOneVariantOrModifierOptionLeft.setLayoutParams(layoutParamsOfOneVariantOrModifierOptionLeft);
        linearLayoutOfOneVariantOrModifierOptionLeft.setOrientation(LinearLayout.VERTICAL);
        linearLayoutOfOneVariantOrModifierOptionLeft.setWeightSum(1);


        for (int j = 0; j < leftButton; j++) {
            final int final_j = j;
            final int[] flag_selected_left = {0};
            /**
             * created linear layout option item on as a child
             */
            final LinearLayout linearLayoutOfOneVariantOrModifierOptionItemLeft = new LinearLayout(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOrModifierOptionItemLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, dp_to_pixel(getResources().getDimension(R.dimen.height_variant_modifier)));
            layoutParamsOfOneVariantOrModifierOptionItemLeft.setMargins(0,0,0,5);
            linearLayoutOfOneVariantOrModifierOptionItemLeft.setLayoutParams(layoutParamsOfOneVariantOrModifierOptionItemLeft);
            linearLayoutOfOneVariantOrModifierOptionItemLeft.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutOfOneVariantOrModifierOptionItemLeft.setWeightSum(10);
            linearLayoutOfOneVariantOrModifierOptionItemLeft.setPadding(10,10,10,10);
            linearLayoutOfOneVariantOrModifierOptionItemLeft.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
/*            if (title.equals("Variant") && j==0){
                flag_selected_left[0] = 1;
                flag_choose_one[0] = j;
                linearLayoutOfOneVariantOrModifierOptionItemLeft.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded_selected));
            } else {
                flag_selected_left[0] = 0;
                linearLayoutOfOneVariantOrModifierOptionItemLeft.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
            }
*/            /**
             * created linear layout option item on as a child
             */
            TextView textViewVariantOrModifierNameItemLeft = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsTextViewVariantOrModifierNameItemLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParamsTextViewVariantOrModifierNameItemLeft.setMargins(10,0,0,0);
            layoutParamsTextViewVariantOrModifierNameItemLeft.weight = 5;
            textViewVariantOrModifierNameItemLeft.setLayoutParams(layoutParamsTextViewVariantOrModifierNameItemLeft);
            textViewVariantOrModifierNameItemLeft.setTextSize(17);
            textViewVariantOrModifierNameItemLeft.setGravity(Gravity.START|Gravity.CENTER);
            textViewVariantOrModifierNameItemLeft.setText(linkedListAllDataTopping.get(j).get(KEY_TOPPING_NAME));

            /**
             * created linear layout option item on for price as a child
             */
            LinearLayout linearLayoutOfOneVariantOrModifierOptionItemPriceLeft = new LinearLayout(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOrModifierOptionItemPriceLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParamsOfOneVariantOrModifierOptionItemPriceLeft.setMargins(0,0,10,0);
            layoutParamsOfOneVariantOrModifierOptionItemPriceLeft.weight = 5;
            linearLayoutOfOneVariantOrModifierOptionItemPriceLeft.setLayoutParams(layoutParamsOfOneVariantOrModifierOptionItemPriceLeft);
            linearLayoutOfOneVariantOrModifierOptionItemPriceLeft.setGravity(Gravity.END|Gravity.CENTER);
            /**
             * created price of variant and modifier as a child
             */
            TextView textViewOfOneVariantOrModifierOptionItemPriceRPLeft = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOrModifierOptionItemPriceRPLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            textViewOfOneVariantOrModifierOptionItemPriceRPLeft.setLayoutParams(layoutParamsOfOneVariantOrModifierOptionItemPriceRPLeft);
            textViewOfOneVariantOrModifierOptionItemPriceRPLeft.setTextSize(17);
            textViewOfOneVariantOrModifierOptionItemPriceRPLeft.setGravity(Gravity.CENTER_VERTICAL);
            textViewOfOneVariantOrModifierOptionItemPriceRPLeft.setText("Rp. ");
            /**
             * created price of variant and modifier as a child
             */
            TextView textViewOfOneVariantOrModifierOptionItemPriceAmountLeft = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOrModifierOptionItemPriceAmountLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            textViewOfOneVariantOrModifierOptionItemPriceAmountLeft.setLayoutParams(layoutParamsOfOneVariantOrModifierOptionItemPriceAmountLeft);
            textViewOfOneVariantOrModifierOptionItemPriceAmountLeft.setTextSize(17);
            textViewOfOneVariantOrModifierOptionItemPriceAmountLeft.setGravity(Gravity.CENTER_VERTICAL);
            textViewOfOneVariantOrModifierOptionItemPriceAmountLeft.setText(
                    ((PointOfSaleActivity)getActivity()).convertValuePattern(
                            linkedListAllDataTopping.get(j).get(KEY_TOPPING_PRICE)
                    )
            );
            /**
             * put text view variant or modifier price item to the parent
             */
            linearLayoutOfOneVariantOrModifierOptionItemPriceLeft.addView(textViewOfOneVariantOrModifierOptionItemPriceRPLeft);
            linearLayoutOfOneVariantOrModifierOptionItemPriceLeft.addView(textViewOfOneVariantOrModifierOptionItemPriceAmountLeft);
            /**
             * put text view variant or modifier name item to the parent
             */
            linearLayoutOfOneVariantOrModifierOptionItemLeft.addView(textViewVariantOrModifierNameItemLeft);
            linearLayoutOfOneVariantOrModifierOptionItemLeft.addView(linearLayoutOfOneVariantOrModifierOptionItemPriceLeft);

            /**
             * make a button (not use because cant chane view from inner private void
             * we need to change the view the background of linear layout of topping button)
             */
            LinearLayout ButtonVariantOrModifierLeft = new LinearLayout(getActivity());
/*            ButtonVariantOrModifierLeft.addView(populateButtonVariantOrModifier(
                    linkedListAllDataTopping.get(j).get(KEY_TOPPING_NAME),
                    linkedListAllDataTopping.get(j).get(KEY_TOPPING_PRICE)));
            /**
             * show button to view
             */
            linearLayoutOfOneVariantOrModifierOptionLeft.addView(linearLayoutOfOneVariantOrModifierOptionItemLeft);
            //linearLayout.add(linearLayoutOfOneVariantOrModifierOptionItemLeft);
            /**
             * handle button event
             */

            linearLayoutOfOneVariantOrModifierOptionItemLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag_selected_left[0] == 0){
                        //flag_choose_one[0] = final_j;
                        //((CashRegisterActivity) getActivity()).logger.addInfo("Testing",String.valueOf(linearLayout.size()));
                        linearLayoutOfOneVariantOrModifierOptionItemLeft.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded_selected));
                        inputResultToppingIDModifier (linkedListResultAllTopping, linkedListAllDataTopping.get(final_j).get(KEY_TOPPING_ID));
                        //Toast.makeText(getActivity(),linkedListAllDataTopping.get(final_j).get(KEY_TOPPING_ID),Toast.LENGTH_SHORT).show();
                        flag_selected_left[0] = 1;
                    }else {
                        //linearLayout.get(0).setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
                        linearLayoutOfOneVariantOrModifierOptionItemLeft.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
                        inputResultToppingIDModifier (linkedListResultAllTopping, linkedListAllDataTopping.get(final_j).get(KEY_TOPPING_ID));
                        //Toast.makeText(getActivity(),"Cancel "+linkedListAllDataTopping.get(final_j).get(KEY_TOPPING_ID),Toast.LENGTH_SHORT).show();
                        flag_selected_left[0] = 0;
                    }
                    changePriceInDialog(textViewItemDialogPrice,linkedListResultAllTopping,textViewItemDialogTotalItem,item_price,linkedListDiscount);
                }
            });
        }

        /**
         * created linear layout option on right as a child
         */
        LinearLayout linearLayoutOfOneVariantOrModifierOptionRight = new LinearLayout(getActivity());
        LinearLayout.LayoutParams layoutParamsOfOneVariantOrModifierOptionRight = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsOfOneVariantOrModifierOptionRight.weight = 5;
        linearLayoutOfOneVariantOrModifierOptionRight.setLayoutParams(layoutParamsOfOneVariantOrModifierOptionRight);
        linearLayoutOfOneVariantOrModifierOptionRight.setOrientation(LinearLayout.VERTICAL);
        linearLayoutOfOneVariantOrModifierOptionRight.setWeightSum(1);

        for (int k = leftButton; k < linkedListAllDataTopping.size(); k++) {
            /**
             * created linear layout option item on as a child
             */
            final LinearLayout linearLayoutOfOneVariantOrModifierOptionItemRight = new LinearLayout(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOrModifierOptionItemRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, dp_to_pixel(getResources().getDimension(R.dimen.height_variant_modifier)));
            layoutParamsOfOneVariantOrModifierOptionItemRight.setMargins(0,0,0,5);
            linearLayoutOfOneVariantOrModifierOptionItemRight.setLayoutParams(layoutParamsOfOneVariantOrModifierOptionItemRight);
            linearLayoutOfOneVariantOrModifierOptionItemRight.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutOfOneVariantOrModifierOptionItemRight.setWeightSum(10);
            linearLayoutOfOneVariantOrModifierOptionItemRight.setPadding(10,10,10,10);
            linearLayoutOfOneVariantOrModifierOptionItemRight.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
            /**
             * created linear layout option item on as a child
             */
            TextView textViewVariantOrModifierNameItemRight = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsTextViewVariantOrModifierNameItemRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParamsTextViewVariantOrModifierNameItemRight.setMargins(10,0,0,0);
            layoutParamsTextViewVariantOrModifierNameItemRight.weight = 5;
            textViewVariantOrModifierNameItemRight.setLayoutParams(layoutParamsTextViewVariantOrModifierNameItemRight);
            textViewVariantOrModifierNameItemRight.setTextSize(17);
            textViewVariantOrModifierNameItemRight.setGravity(Gravity.START|Gravity.CENTER);
            textViewVariantOrModifierNameItemRight.setText(linkedListAllDataTopping.get(k).get(KEY_TOPPING_NAME));

            /**
             * created linear layout option item on for price as a child
             */
            LinearLayout linearLayoutOfOneVariantOrModifierOptionItemPriceRight = new LinearLayout(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOrModifierOptionItemPriceRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParamsOfOneVariantOrModifierOptionItemPriceRight.setMargins(0,0,10,0);
            layoutParamsOfOneVariantOrModifierOptionItemPriceRight.weight = 5;
            linearLayoutOfOneVariantOrModifierOptionItemPriceRight.setLayoutParams(layoutParamsOfOneVariantOrModifierOptionItemPriceRight);
            linearLayoutOfOneVariantOrModifierOptionItemPriceRight.setGravity(Gravity.END|Gravity.CENTER);
            /**
             * created price of variant and modifier as a child
             */
            TextView textViewOfOneVariantOrModifierOptionItemPriceRPRight = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOrModifierOptionItemPriceRPRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            textViewOfOneVariantOrModifierOptionItemPriceRPRight.setLayoutParams(layoutParamsOfOneVariantOrModifierOptionItemPriceRPRight);
            textViewOfOneVariantOrModifierOptionItemPriceRPRight.setTextSize(17);
            textViewOfOneVariantOrModifierOptionItemPriceRPRight.setGravity(Gravity.CENTER_VERTICAL);
            textViewOfOneVariantOrModifierOptionItemPriceRPRight.setText("Rp. ");
            /**
             * created price of variant and modifier as a child
             */
            TextView textViewOfOneVariantOrModifierOptionItemPriceAmountRight = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneVariantOrModifierOptionItemPriceAmountRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            textViewOfOneVariantOrModifierOptionItemPriceAmountRight.setLayoutParams(layoutParamsOfOneVariantOrModifierOptionItemPriceAmountRight);
            textViewOfOneVariantOrModifierOptionItemPriceAmountRight.setTextSize(17);
            textViewOfOneVariantOrModifierOptionItemPriceAmountRight.setGravity(Gravity.CENTER_VERTICAL);
            textViewOfOneVariantOrModifierOptionItemPriceAmountRight.setText(
                    ((PointOfSaleActivity)getActivity()).convertValuePattern(
                            linkedListAllDataTopping.get(k).get(KEY_TOPPING_PRICE)
                    )
            );
            /**
             * put text view variant or modifier price item to the parent
             */
            linearLayoutOfOneVariantOrModifierOptionItemPriceRight.addView(textViewOfOneVariantOrModifierOptionItemPriceRPRight);
            linearLayoutOfOneVariantOrModifierOptionItemPriceRight.addView(textViewOfOneVariantOrModifierOptionItemPriceAmountRight);
            /**
             * put text view variant or modifier name item to the parent
             */
            linearLayoutOfOneVariantOrModifierOptionItemRight.addView(textViewVariantOrModifierNameItemRight);
            linearLayoutOfOneVariantOrModifierOptionItemRight.addView(linearLayoutOfOneVariantOrModifierOptionItemPriceRight);

            /**
             * make a button
             */
            final LinearLayout ButtonVariantOrModifierRight = new LinearLayout(getActivity());
/*            ButtonVariantOrModifierRight.addView(populateButtonVariantOrModifier(
                    linkedListAllDataTopping.get(k).get(KEY_TOPPING_NAME),
                    linkedListAllDataTopping.get(k).get(KEY_TOPPING_PRICE)));
            /**
             * show button to view
             */
            linearLayoutOfOneVariantOrModifierOptionRight.addView(linearLayoutOfOneVariantOrModifierOptionItemRight);
            //linearLayout.add(linearLayoutOfOneVariantOrModifierOptionItemRight);
            /**
             * handle button event
             */
            final int final_k = k;
            final int[] flag_selected_Right = {0};
            linearLayoutOfOneVariantOrModifierOptionItemRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag_selected_Right[0] == 0){
                        linearLayoutOfOneVariantOrModifierOptionItemRight.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded_selected));
                        //Toast.makeText(getActivity(),linkedListAllDataTopping.get(final_k).get(KEY_TOPPING_ID),Toast.LENGTH_SHORT).show();
                        inputResultToppingIDModifier (linkedListResultAllTopping, linkedListAllDataTopping.get(final_k).get(KEY_TOPPING_ID));
                        flag_selected_Right[0] = 1;
                    }else {
                        linearLayoutOfOneVariantOrModifierOptionItemRight.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
                        //Toast.makeText(getActivity(),"Cancel "+linkedListAllDataTopping.get(final_k).get(KEY_TOPPING_ID),Toast.LENGTH_SHORT).show();
                        inputResultToppingIDModifier (linkedListResultAllTopping, linkedListAllDataTopping.get(final_k).get(KEY_TOPPING_ID));
                        flag_selected_Right[0] = 0;
                    }
                    changePriceInDialog(textViewItemDialogPrice,linkedListResultAllTopping,textViewItemDialogTotalItem,item_price,linkedListDiscount);
                }
            });

        }

        /**
         * put text view title to the parent
         */
        linearLayoutOfOneVariantOrModifier.addView(textViewVariantOrModifierName);
        /**
         * put linear layout option on left to the parent
         */
        linearLayoutOfOneVariantOrModifierOption.addView(linearLayoutOfOneVariantOrModifierOptionLeft);
        /**
         * put linear layout option on right to the parent
         */
        linearLayoutOfOneVariantOrModifierOption.addView(linearLayoutOfOneVariantOrModifierOptionRight);
        /**
         * put linear layout option to the parent
         */
        linearLayoutOfOneVariantOrModifier.addView(linearLayoutOfOneVariantOrModifierOption);
        /**
         * put line to the parent
         */
        linearLayoutOfOneVariantOrModifier.addView(viewItemDialogGroupModifier);

        return linearLayoutOfOneVariantOrModifier;
    }

    // chooseonebutton
    private LinearLayout populateLinearLayoutChooseOneModifier (String title,
                                                                final LinkedList<HashMap<String,String>> linkedListAllDataTopping){
        final int leftButton;
        //final int[] flag_choose_one = {0};
        final LinkedList<LinearLayout> linearLayoutTemporaryAllButtonChooseOneModifier = new LinkedList<>();
        final LinkedList<String> linkedListAllDataToppingID = new LinkedList<>();
        for (int u = 0; u < linkedListAllDataTopping.size(); u++) {
            linkedListAllDataToppingID.add(linkedListAllDataTopping.get(u).get(KEY_TOPPING_ID));
        }
        if (linkedListAllDataTopping.size()%2==1){
            leftButton = (linkedListAllDataTopping.size()/2)+1;
        }else {
            leftButton = (linkedListAllDataTopping.size()/2);
        }
        ((CashRegisterActivity)getActivity()).logger.addInfo("Button",linkedListAllDataTopping.toString());
        /**
         * created linear layout as a child
         */
        LinearLayout linearLayoutOfOneChooseOneModifier = new LinearLayout(getActivity());
        LinearLayout.LayoutParams layoutParamsOfOneChooseOneModifier = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsOfOneChooseOneModifier.setMargins(0,0,0,5);
        linearLayoutOfOneChooseOneModifier.setLayoutParams(layoutParamsOfOneChooseOneModifier);
        linearLayoutOfOneChooseOneModifier.setOrientation(LinearLayout.VERTICAL);
        /**
         * created line in view
         */
        View viewItemDialogGroupChooseOneModifier = new View(getActivity());
        LinearLayout.LayoutParams layoutParamsViewLine = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        layoutParamsViewLine.setMargins(0,0,0,10);
        viewItemDialogGroupChooseOneModifier.setLayoutParams(layoutParamsViewLine);
        viewItemDialogGroupChooseOneModifier.setBackgroundColor(getResources().getColor(R.color.abu));
        /**
         * created text view variant or modifier name
         */
        TextView textViewChooseOneModifierName = new TextView(getActivity());
        LinearLayout.LayoutParams layoutParamsTextViewChooseOneModifierName = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsTextViewChooseOneModifierName.setMargins(0,0,0,10);
        textViewChooseOneModifierName.setLayoutParams(layoutParamsTextViewChooseOneModifierName);
        textViewChooseOneModifierName.setTextColor(getResources().getColor(R.color.black));
        textViewChooseOneModifierName.setTextSize(18);
        textViewChooseOneModifierName.setTypeface(null,Typeface.BOLD);
        textViewChooseOneModifierName.setText(title);
        /**
         * created linear layout option as a child
         */
        LinearLayout linearLayoutOfOneChooseOneModifierOption = new LinearLayout(getActivity());
        linearLayoutOfOneChooseOneModifierOption.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayoutOfOneChooseOneModifierOption.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutOfOneChooseOneModifierOption.setWeightSum(10);
        /**
         * created linear layout option on left as a child
         */
        LinearLayout linearLayoutOfOneChooseOneModifierOptionLeft = new LinearLayout(getActivity());
        LinearLayout.LayoutParams layoutParamsOfOneChooseOneModifierOptionLeft = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsOfOneChooseOneModifierOptionLeft.setMargins(0,0,10,0);
        layoutParamsOfOneChooseOneModifierOptionLeft.weight = 5;
        linearLayoutOfOneChooseOneModifierOptionLeft.setLayoutParams(layoutParamsOfOneChooseOneModifierOptionLeft);
        linearLayoutOfOneChooseOneModifierOptionLeft.setOrientation(LinearLayout.VERTICAL);
        linearLayoutOfOneChooseOneModifierOptionLeft.setWeightSum(1);


        for (int j = 0; j < leftButton; j++) {
            final int final_j = j;
            final int[] flag_selected_left = {0};
            /**
             * created linear layout option item on as a child
             */
            final LinearLayout linearLayoutOfOneChooseOneModifierOptionItemLeft = new LinearLayout(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneChooseOneModifierOptionItemLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, dp_to_pixel(getResources().getDimension(R.dimen.height_variant_modifier)));
            layoutParamsOfOneChooseOneModifierOptionItemLeft.setMargins(0,0,0,5);
            linearLayoutOfOneChooseOneModifierOptionItemLeft.setLayoutParams(layoutParamsOfOneChooseOneModifierOptionItemLeft);
            linearLayoutOfOneChooseOneModifierOptionItemLeft.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutOfOneChooseOneModifierOptionItemLeft.setWeightSum(10);
            linearLayoutOfOneChooseOneModifierOptionItemLeft.setPadding(10,10,10,10);
            linearLayoutOfOneChooseOneModifierOptionItemLeft.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));

            /**
             * created linear layout option item on as a child
             */
            TextView textViewChooseOneModifierNameItemLeft = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsTextViewChooseOneModifierNameItemLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParamsTextViewChooseOneModifierNameItemLeft.setMargins(10,0,0,0);
            layoutParamsTextViewChooseOneModifierNameItemLeft.weight = 5;
            textViewChooseOneModifierNameItemLeft.setLayoutParams(layoutParamsTextViewChooseOneModifierNameItemLeft);
            textViewChooseOneModifierNameItemLeft.setTextSize(18);
            textViewChooseOneModifierNameItemLeft.setGravity(Gravity.START|Gravity.CENTER);
            textViewChooseOneModifierNameItemLeft.setText(linkedListAllDataTopping.get(j).get(KEY_TOPPING_NAME));

            /**
             * created linear layout option item on for price as a child
             */
            LinearLayout linearLayoutOfOneChooseOneModifierOptionItemPriceLeft = new LinearLayout(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneChooseOneModifierOptionItemPriceLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParamsOfOneChooseOneModifierOptionItemPriceLeft.setMargins(0,0,10,0);
            layoutParamsOfOneChooseOneModifierOptionItemPriceLeft.weight = 5;
            linearLayoutOfOneChooseOneModifierOptionItemPriceLeft.setLayoutParams(layoutParamsOfOneChooseOneModifierOptionItemPriceLeft);
            linearLayoutOfOneChooseOneModifierOptionItemPriceLeft.setGravity(Gravity.END|Gravity.CENTER);
            /**
             * created price of variant and modifier as a child
             */
            TextView textViewOfOneChooseOneModifierOptionItemPriceRPLeft = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneModifierOptionItemPriceRPLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            textViewOfOneChooseOneModifierOptionItemPriceRPLeft.setLayoutParams(layoutParamsOfOneModifierOptionItemPriceRPLeft);
            textViewOfOneChooseOneModifierOptionItemPriceRPLeft.setTextSize(18);
            textViewOfOneChooseOneModifierOptionItemPriceRPLeft.setGravity(Gravity.CENTER_VERTICAL);
            textViewOfOneChooseOneModifierOptionItemPriceRPLeft.setText("Rp. ");
            /**
             * created price of variant and modifier as a child
             */
            TextView textViewOfOneChooseOneModifierOptionItemPriceAmountLeft = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneChooseOneModifierOptionItemPriceAmountLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            textViewOfOneChooseOneModifierOptionItemPriceAmountLeft.setLayoutParams(layoutParamsOfOneChooseOneModifierOptionItemPriceAmountLeft);
            textViewOfOneChooseOneModifierOptionItemPriceAmountLeft.setTextSize(18);
            textViewOfOneChooseOneModifierOptionItemPriceAmountLeft.setGravity(Gravity.CENTER_VERTICAL);
            textViewOfOneChooseOneModifierOptionItemPriceAmountLeft.setText(
                    ((PointOfSaleActivity)getActivity()).convertValuePattern(
                            linkedListAllDataTopping.get(j).get(KEY_TOPPING_PRICE)
                    )
            );
            /**
             * put text view variant or modifier price item to the parent
             */
            linearLayoutOfOneChooseOneModifierOptionItemPriceLeft.addView(textViewOfOneChooseOneModifierOptionItemPriceRPLeft);
            linearLayoutOfOneChooseOneModifierOptionItemPriceLeft.addView(textViewOfOneChooseOneModifierOptionItemPriceAmountLeft);
            /**
             * put text view variant or modifier name item to the parent
             */
            linearLayoutOfOneChooseOneModifierOptionItemLeft.addView(textViewChooseOneModifierNameItemLeft);
            linearLayoutOfOneChooseOneModifierOptionItemLeft.addView(linearLayoutOfOneChooseOneModifierOptionItemPriceLeft);

            /**
             * make a button (not use because cant chane view from inner private void
             * we need to change the view the background of linear layout of topping button)
             */
            LinearLayout ButtonVariantOrModifierLeft = new LinearLayout(getActivity());
/*            ButtonVariantOrModifierLeft.addView(populateButtonVariantOrModifier(
                    linkedListAllDataTopping.get(j).get(KEY_TOPPING_NAME),
                    linkedListAllDataTopping.get(j).get(KEY_TOPPING_PRICE)));
            /**
             * show button to view
             */
            linearLayoutOfOneChooseOneModifierOptionLeft.addView(linearLayoutOfOneChooseOneModifierOptionItemLeft);
            linearLayoutTemporaryAllButtonChooseOneModifier.add(linearLayoutOfOneChooseOneModifierOptionItemLeft);
            /**
             * handle button event
             */

            linearLayoutOfOneChooseOneModifierOptionItemLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!linkedListResultAllTopping.contains(linkedListAllDataTopping.get(final_j).get(KEY_TOPPING_ID))){
                        for (int t = 0; t < linkedListAllDataToppingID.size(); t++) {
                            if (linkedListResultAllTopping.contains(linkedListAllDataToppingID.get(t))){
                                linkedListResultAllTopping.remove(linkedListAllDataToppingID.get(t));
                                for (int a = 0; a < linearLayoutTemporaryAllButtonChooseOneModifier.size(); a++) {
                                    linearLayoutTemporaryAllButtonChooseOneModifier.get(a).setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
                                }
                            }
                        }
                        linearLayoutOfOneChooseOneModifierOptionItemLeft.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded_selected));
                        linkedListResultAllTopping.add(linkedListAllDataTopping.get(final_j).get(KEY_TOPPING_ID));
                    }else {
                        for (int a = 0; a < linearLayoutTemporaryAllButtonChooseOneModifier.size(); a++) {
                            linearLayoutTemporaryAllButtonChooseOneModifier.get(a).setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
                        }
                        //linearLayoutOfOneChooseOneModifierOptionItemLeft.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
                        linkedListResultAllTopping.remove(linkedListAllDataTopping.get(final_j).get(KEY_TOPPING_ID));
                    }
/*
                    if (flag_selected_left[0] == 0){
                        //flag_choose_one[0] = final_j;
                        //((CashRegisterActivity) getActivity()).logger.addInfo("Testing",String.valueOf(linearLayout.size()));
                        linearLayoutOfOneChooseOneModifierOptionItemLeft.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded_selected));
                        inputResultToppingIDModifierChooseOne(true,linkedListResultAllTopping, linkedListAllDataToppingID, linkedListAllDataTopping.get(final_j).get(KEY_TOPPING_ID));
                        //Toast.makeText(getActivity(),linkedListAllDataTopping.get(final_j).get(KEY_TOPPING_ID),Toast.LENGTH_SHORT).show();
                        flag_selected_left[0] = 1;
                    }else {
                        //linearLayout.get(0).setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
                        linearLayoutOfOneChooseOneModifierOptionItemLeft.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
                        inputResultToppingIDModifierChooseOne(false,linkedListResultAllTopping, linkedListAllDataToppingID, linkedListAllDataTopping.get(final_j).get(KEY_TOPPING_ID));
                        //Toast.makeText(getActivity(),"Cancel "+linkedListAllDataTopping.get(final_j).get(KEY_TOPPING_ID),Toast.LENGTH_SHORT).show();
                        flag_selected_left[0] = 0;
                    }
*/
                    changePriceInDialog(textViewItemDialogPrice,linkedListResultAllTopping,textViewItemDialogTotalItem,item_price,linkedListDiscount);
                }
            });
        }

        /**
         * created linear layout option on right as a child
         */
        LinearLayout linearLayoutOfOneChooseOneModifierOptionRight = new LinearLayout(getActivity());
        LinearLayout.LayoutParams layoutParamsOfOneChooseOneModifierOptionRight = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsOfOneChooseOneModifierOptionRight.weight = 5;
        linearLayoutOfOneChooseOneModifierOptionRight.setLayoutParams(layoutParamsOfOneChooseOneModifierOptionRight);
        linearLayoutOfOneChooseOneModifierOptionRight.setOrientation(LinearLayout.VERTICAL);
        linearLayoutOfOneChooseOneModifierOptionRight.setWeightSum(1);

        for (int k = leftButton; k < linkedListAllDataTopping.size(); k++) {
            /**
             * created linear layout option item on as a child
             */
            final LinearLayout linearLayoutOfOneChooseOneModifierOptionItemRight = new LinearLayout(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneChooseOneModifierOptionItemRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, dp_to_pixel(getResources().getDimension(R.dimen.height_variant_modifier)));
            layoutParamsOfOneChooseOneModifierOptionItemRight.setMargins(0,0,0,5);
            linearLayoutOfOneChooseOneModifierOptionItemRight.setLayoutParams(layoutParamsOfOneChooseOneModifierOptionItemRight);
            linearLayoutOfOneChooseOneModifierOptionItemRight.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutOfOneChooseOneModifierOptionItemRight.setWeightSum(10);
            linearLayoutOfOneChooseOneModifierOptionItemRight.setPadding(10,10,10,10);
            linearLayoutOfOneChooseOneModifierOptionItemRight.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
            /**
             * created linear layout option item on as a child
             */
            TextView textViewChooseOneModifierNameItemRight = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsTextViewChooseOneModifierNameItemRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParamsTextViewChooseOneModifierNameItemRight.setMargins(10,0,0,0);
            layoutParamsTextViewChooseOneModifierNameItemRight.weight = 5;
            textViewChooseOneModifierNameItemRight.setLayoutParams(layoutParamsTextViewChooseOneModifierNameItemRight);
            textViewChooseOneModifierNameItemRight.setTextSize(18);
            textViewChooseOneModifierNameItemRight.setGravity(Gravity.START|Gravity.CENTER);
            textViewChooseOneModifierNameItemRight.setText(linkedListAllDataTopping.get(k).get(KEY_TOPPING_NAME));

            /**
             * created linear layout option item on for price as a child
             */
            LinearLayout linearLayoutOfOneChooseOneModifierOptionItemPriceRight = new LinearLayout(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneChooseOneModifierOptionItemPriceRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParamsOfOneChooseOneModifierOptionItemPriceRight.setMargins(0,0,10,0);
            layoutParamsOfOneChooseOneModifierOptionItemPriceRight.weight = 5;
            linearLayoutOfOneChooseOneModifierOptionItemPriceRight.setLayoutParams(layoutParamsOfOneChooseOneModifierOptionItemPriceRight);
            linearLayoutOfOneChooseOneModifierOptionItemPriceRight.setGravity(Gravity.END|Gravity.CENTER);
            /**
             * created price of variant and modifier as a child
             */
            TextView textViewOfOneChooseOneModifierOptionItemPriceRPRight = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneChooseOneModifierOptionItemPriceRPRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            textViewOfOneChooseOneModifierOptionItemPriceRPRight.setLayoutParams(layoutParamsOfOneChooseOneModifierOptionItemPriceRPRight);
            textViewOfOneChooseOneModifierOptionItemPriceRPRight.setTextSize(18);
            textViewOfOneChooseOneModifierOptionItemPriceRPRight.setGravity(Gravity.CENTER_VERTICAL);
            textViewOfOneChooseOneModifierOptionItemPriceRPRight.setText("Rp. ");
            /**
             * created price of variant and modifier as a child
             */
            TextView textViewOfOneChooseOneModifierOptionItemPriceAmountRight = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParamsOfOneChooseOneModifierOptionItemPriceAmountRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            textViewOfOneChooseOneModifierOptionItemPriceAmountRight.setLayoutParams(layoutParamsOfOneChooseOneModifierOptionItemPriceAmountRight);
            textViewOfOneChooseOneModifierOptionItemPriceAmountRight.setTextSize(18);
            textViewOfOneChooseOneModifierOptionItemPriceAmountRight.setGravity(Gravity.CENTER_VERTICAL);
            textViewOfOneChooseOneModifierOptionItemPriceAmountRight.setText(
                    ((PointOfSaleActivity)getActivity()).convertValuePattern(
                            linkedListAllDataTopping.get(k).get(KEY_TOPPING_PRICE)
                    )
            );
            /**
             * put text view variant or modifier price item to the parent
             */
            linearLayoutOfOneChooseOneModifierOptionItemPriceRight.addView(textViewOfOneChooseOneModifierOptionItemPriceRPRight);
            linearLayoutOfOneChooseOneModifierOptionItemPriceRight.addView(textViewOfOneChooseOneModifierOptionItemPriceAmountRight);
            /**
             * put text view variant or modifier name item to the parent
             */
            linearLayoutOfOneChooseOneModifierOptionItemRight.addView(textViewChooseOneModifierNameItemRight);
            linearLayoutOfOneChooseOneModifierOptionItemRight.addView(linearLayoutOfOneChooseOneModifierOptionItemPriceRight);

            /**
             * make a button
             */
            final LinearLayout ButtonVariantOrModifierRight = new LinearLayout(getActivity());
/*            ButtonVariantOrModifierRight.addView(populateButtonVariantOrModifier(
                    linkedListAllDataTopping.get(k).get(KEY_TOPPING_NAME),
                    linkedListAllDataTopping.get(k).get(KEY_TOPPING_PRICE)));
            /**
             * show button to view
             */
            linearLayoutOfOneChooseOneModifierOptionRight.addView(linearLayoutOfOneChooseOneModifierOptionItemRight);
            linearLayoutTemporaryAllButtonChooseOneModifier.add(linearLayoutOfOneChooseOneModifierOptionItemRight);
            /**
             * handle button event
             */
            final int final_k = k;
            final int[] flag_selected_Right = {0};
            linearLayoutOfOneChooseOneModifierOptionItemRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!linkedListResultAllTopping.contains(linkedListAllDataTopping.get(final_k).get(KEY_TOPPING_ID))){
                        for (int u = 0; u < linkedListAllDataToppingID.size(); u++) {
                            if (linkedListResultAllTopping.contains(linkedListAllDataToppingID.get(u))){
                                linkedListResultAllTopping.remove(linkedListAllDataToppingID.get(u));
                                for (int a = 0; a < linearLayoutTemporaryAllButtonChooseOneModifier.size(); a++) {
                                    linearLayoutTemporaryAllButtonChooseOneModifier.get(a).setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
                                }
                            }
                        }
                        linearLayoutOfOneChooseOneModifierOptionItemRight.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded_selected));
                        linkedListResultAllTopping.add(linkedListAllDataTopping.get(final_k).get(KEY_TOPPING_ID));
                    }else {
                        for (int a = 0; a < linearLayoutTemporaryAllButtonChooseOneModifier.size(); a++) {
                            linearLayoutTemporaryAllButtonChooseOneModifier.get(a).setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
                        }
                        //linearLayoutOfOneChooseOneModifierOptionItemRight.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
                        linkedListResultAllTopping.remove(linkedListAllDataTopping.get(final_k).get(KEY_TOPPING_ID));
                    }
/*
                    if (flag_selected_Right[0] == 0){
                        linearLayoutOfOneChooseOneModifierOptionItemRight.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded_selected));
                        //Toast.makeText(getActivity(),linkedListAllDataTopping.get(final_k).get(KEY_TOPPING_ID),Toast.LENGTH_SHORT).show();
                        inputResultToppingIDModifierChooseOne(true, linkedListResultAllTopping, linkedListAllDataToppingID, linkedListAllDataTopping.get(final_k).get(KEY_TOPPING_ID));
                        flag_selected_Right[0] = 1;
                    }else {
                        linearLayoutOfOneChooseOneModifierOptionItemRight.setBackground(getResources().getDrawable(R.drawable.button_hover_rounded));
                        //Toast.makeText(getActivity(),"Cancel "+linkedListAllDataTopping.get(final_k).get(KEY_TOPPING_ID),Toast.LENGTH_SHORT).show();
                        inputResultToppingIDModifierChooseOne(false, linkedListResultAllTopping, linkedListAllDataToppingID, linkedListAllDataTopping.get(final_k).get(KEY_TOPPING_ID));
                        flag_selected_Right[0] = 0;
                    }
*/
                    changePriceInDialog(textViewItemDialogPrice,linkedListResultAllTopping,textViewItemDialogTotalItem,item_price,linkedListDiscount);
                }
            });
        }

        /**
         * put text view title to the parent
         */
        linearLayoutOfOneChooseOneModifier.addView(textViewChooseOneModifierName);
        /**
         * put linear layout option on left to the parent
         */
        linearLayoutOfOneChooseOneModifierOption.addView(linearLayoutOfOneChooseOneModifierOptionLeft);
        /**
         * put linear layout option on right to the parent
         */
        linearLayoutOfOneChooseOneModifierOption.addView(linearLayoutOfOneChooseOneModifierOptionRight);
        /**
         * put linear layout option to the parent
         */
        linearLayoutOfOneChooseOneModifier.addView(linearLayoutOfOneChooseOneModifierOption);
        /**
         * put line to the parent
         */
        linearLayoutOfOneChooseOneModifier.addView(viewItemDialogGroupChooseOneModifier);

        return linearLayoutOfOneChooseOneModifier;
    }

    private HashMap<String,String> hashMapVariant (Realm realm, String variant_id){
        RealmResults<Variant> realmResultsVariants = realm.where(Variant.class).equalTo("VariantID",variant_id).findAll();
        Variant variant = realmResultsVariants.first();
        HashMap<String,String> hashMap= new HashMap<>();
        hashMap.put(KEY_TOPPING_ID, variant.getVariantID());
        hashMap.put(KEY_TOPPING_NAME,variant.getVariantName());
        hashMap.put(KEY_TOPPING_PRICE,variant.getVariantPrice());
        return hashMap;
    }

    private LinkedList<HashMap<String, String>> linkedListModifierGroup (Realm realm, String modifier_group){
        RealmResults<ModifierDetail> realmResultModifierDetail = realm.where(ModifierDetail.class).equalTo("modifierDetailGroupID",modifier_group).findAll();
        LinkedList<HashMap<String, String>> linkedList = new LinkedList<>();
        for (int i = 0; i < realmResultModifierDetail.size(); i++) {
            ModifierDetail modifierDetail = realmResultModifierDetail.get(i);
            HashMap<String,String> hashMap= new HashMap<>();
            hashMap.put(KEY_TOPPING_ID, modifierDetail.getModifierDetailID());
            hashMap.put(KEY_TOPPING_NAME,modifierDetail.getModifierDetailName());
            hashMap.put(KEY_TOPPING_PRICE,modifierDetail.getModifierDetailPrice());
            linkedList.add(hashMap);
        }
        return linkedList;
    }

    private String modifierGroupName (Realm realm, String modifier_group){
        RealmResults<ModifierGroup> realmResultsModifierGroup = realm.where(ModifierGroup.class).equalTo("modifierGroupID",modifier_group).findAll();
        ModifierGroup modifierGroup = realmResultsModifierGroup.first();
        return modifierGroup.getModifierGroupName();
    }

    private void addViewSwitchDiscount (LinkedList<HashMap<String, String>> linkedListDiscountMaster){
        linkedListSwitch = new LinkedList<>();
        final int leftSwitch;
        if (linkedListDiscountMaster.size()%2==1){
            leftSwitch = (linkedListDiscountMaster.size()/2)+1;
        }else {
            leftSwitch = (linkedListDiscountMaster.size()/2);
        }
        /**
         * create a switch for discount on left
         */
        for (int c = 0; c < leftSwitch; c++) {
            Switch aSwitchLeft = new Switch(getActivity());
            LinearLayout.LayoutParams layoutParamsSwitchLeft = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, dp_to_pixel(getResources().getDimension(R.dimen.height_variant_modifier)));
            aSwitchLeft.setLayoutParams(layoutParamsSwitchLeft);
            String aSwitchLeftText = linkedListDiscountMaster.get(c).get(KEY_DISCOUNT_NAME)+
                    " ("+linkedListDiscountMaster.get(c).get(KEY_DISCOUNT_VALUE)+"%)";
            aSwitchLeft.setText(aSwitchLeftText);
            linearLayoutItemDialogDiscountLeft.addView(aSwitchLeft);
            linkedListSwitch.add(c,aSwitchLeft);
            aSwitchLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    changePriceInDialog(textViewItemDialogPrice,linkedListResultAllTopping,textViewItemDialogTotalItem,item_price,linkedListDiscount);
                }
            });

        }
        /**
         * create a switch for discount on right
         */
        for (int g = leftSwitch; g < linkedListDiscountMaster.size(); g++) {
            Switch aSwitchRight = new Switch(getActivity());
            LinearLayout.LayoutParams layoutParamsSwitchRight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, dp_to_pixel(getResources().getDimension(R.dimen.height_variant_modifier)));
            aSwitchRight.setLayoutParams(layoutParamsSwitchRight);
            String aSwitchRightText = linkedListDiscountMaster.get(g).get(KEY_DISCOUNT_NAME)+
                    " ("+linkedListDiscountMaster.get(g).get(KEY_DISCOUNT_VALUE)+"%)";
            aSwitchRight.setText(aSwitchRightText);
            linearLayoutItemDialogDiscountRight.addView(aSwitchRight);
            linkedListSwitch.add(g,aSwitchRight);
            aSwitchRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    changePriceInDialog(textViewItemDialogPrice,linkedListResultAllTopping,textViewItemDialogTotalItem,item_price,linkedListDiscount);
                }
            });
        }

    }


    private void inputResultToppingIDVariant (LinkedList<String> linked_list_result,
                                              LinkedList<String> linked_list_variant_id, String topping_id){
        for (int i = 0; i < linked_list_variant_id.size(); i++) {
            if (linked_list_result.contains(linked_list_variant_id.get(i))){
                linked_list_result.remove(linked_list_variant_id.get(i));
            }
        }
        linked_list_result.add(topping_id);
    }

    private void inputResultToppingIDModifier (LinkedList<String> linked_list_result, String topping_id){
        if (linked_list_result.contains(topping_id)){
            linked_list_result.remove(topping_id);
        }else {
            linked_list_result.add(topping_id);
        }
    }

    private void inputResultToppingIDModifierChooseOne (boolean write, LinkedList<String> linked_list_result,
                                              LinkedList<String> linked_list_variant_id, String topping_id){
        for (int i = 0; i < linked_list_variant_id.size(); i++) {
            if (linked_list_result.contains(linked_list_variant_id.get(i))){
                linked_list_result.remove(linked_list_variant_id.get(i));
            }
        }
        /*
        if (write){
            linked_list_result.add(topping_id);
        }else {
            linked_list_result.remove(topping_id);
        }
   */
        if(linked_list_result.contains(topping_id)){
            linked_list_result.remove(topping_id);
        }else {
            linked_list_result.add(topping_id);
        }

    }

    private String modifierGroupOptionFlag (Realm realm, String modifier_group_id){
        RealmResults<ModifierGroup> realmResultsModifierGroup = realm.where(ModifierGroup.class).equalTo("modifierGroupID",modifier_group_id).findAll();
        Log.e("Tulung",realmResultsModifierGroup.size()+"");
        Log.e("Tulung1",modifier_group_id);
        ModifierGroup modifierGroup = realmResultsModifierGroup.first();
        return modifierGroup.getModifierGroupOptionFlag();
    }

    private void changePriceInDialog (TextView text_view_price, LinkedList<String> linked_list_topping,
                                      TextView text_view_total_item, String price_without_variant,
                                      LinkedList<HashMap<String, String>> linkedListDiscountMaster){
        int price = integerOf(price_without_variant);
        for (int q = 0; q < linked_list_topping.size(); q++) {
            if (linked_list_topping.get(q).contains("var")){
                price += integerOf(variant(((CashRegisterActivity)getActivity()).realm, linked_list_topping.get(q)).getVariantPrice())
                        - integerOf(price_without_variant);
            }else if (linked_list_topping.get(q).contains("mod")){
                price += integerOf(modifierDetail(((CashRegisterActivity)getActivity()).realm, linked_list_topping.get(q)).getModifierDetailPrice());
            }
        }

        for (int d = 0; d < linkedListSwitch.size() ; d++) {
            if (linkedListSwitch.get(d).isChecked()){
                //price = (int) (price*(1 - (integerOf(linkedListDiscountMaster.get(d).get(KEY_DISCOUNT_VALUE))/100.0)));
                linkedListResultDiscount = linkedListSingleData(linkedListResultDiscount,linkedListDiscountMaster.get(d).get(KEY_DISCOUNT_ID));
            }else {
                linkedListResultDiscount.remove(linkedListDiscountMaster.get(d).get(KEY_DISCOUNT_ID));
            }
        }
        text_view_price.setText(
                ((PointOfSaleActivity)getActivity()).convertValuePattern(
                        stringOf(price*integerOf(text_view_total_item.getText().toString()))
                )
        );
    }

    private Variant variant (Realm realm, String variant_id){
        RealmResults<Variant> realmResults = realm.where(Variant.class).equalTo("VariantID",variant_id).findAll();
        return realmResults.first();
    }
    private ModifierDetail modifierDetail (Realm realm, String modifier_id){
        RealmResults<ModifierDetail> realmResults = realm.where(ModifierDetail.class).equalTo("modifierDetailID",modifier_id).findAll();
        return realmResults.first();
    }

    private int integerOf(String stringInteger){
        return Integer.parseInt(stringInteger);
    }

    private String stringOf(int integerString){
        return String.valueOf(integerString);
    }

    private LinkedList<String> linkedListSingleData (LinkedList<String> linkedList, String data) {
        if (!inList(linkedList,data)){
            linkedList.add(data);
        }
        return linkedList;
    }

    private boolean inList (LinkedList<String> linkedList, String data){
        boolean value = false;
        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i).equals(data)){
                value = true;
            }
        }
        return value;
    }

    private void saveOrderToDatabase (Realm realm, String transaction_id, String item_id, String variant_id,
                                      String total_price, String quantity, LinkedList<String> modifier_id,
                                      LinkedList<String> discount_id){
        realm.beginTransaction();
        TransactionDetail transactionDetail = realm.createObject(TransactionDetail.class);
        transactionDetail.setTransactionDetailID(transaction_id);
        transactionDetail.setTransactionDetailItemID(item_id);
        transactionDetail.setTransactionDetailVariantID(variant_id);
        transactionDetail.setTransactionDetailTotalPrice(
                ((CashRegisterActivity)getActivity()).revertValuePattern(
                        total_price
                )
        );
        transactionDetail.setTransactionDetailQuantity(quantity);
        for (int m = 0; m < modifier_id.size(); m++) {
            if (!modifier_id.get(m).contains("var")){
                TransactionDetailModifier transactionDetailModifier = realm.createObject(TransactionDetailModifier.class);
                transactionDetailModifier.setTransactionDetailModifierID(modifier_id.get(m));
                transactionDetail.transactionDetailModifierID.add(transactionDetailModifier);
            }
        }

        for (int d = 0; d < discount_id.size(); d++) {
            TransactionDetailDiscount transactionDetailDiscount = realm.createObject(TransactionDetailDiscount.class);
            transactionDetailDiscount.setDiscountID(discount_id.get(d));
            transactionDetail.transactionDetailDiscountID.add(transactionDetailDiscount);
        }
        realm.commitTransaction();
    }

    private boolean isLastItem (Realm realm, String item_id, String variant_id, String total_price,
                                LinkedList<String> modifier_id, LinkedList<String> discount_id){
        boolean value = true;
        RealmResults<TransactionDetail> realmResultTransactionDetail = realm.where(TransactionDetail.class).findAll();
        if (realmResultTransactionDetail.size() > 0 ){
            TransactionDetail transactionDetail = realmResultTransactionDetail.get(realmResultTransactionDetail.size()-1);
            RealmList<TransactionDetailModifier> realmListTransactionDetailModifier = transactionDetail.getTransactionDetailModifierID();
            LinkedList <String> linkedListModifier = new LinkedList<>();
            for (int a = 0; a < realmListTransactionDetailModifier.size() ; a++) {
                linkedListModifier.add(realmListTransactionDetailModifier.get(a).getTransactionDetailModifierID());
            }

            RealmList<TransactionDetailDiscount> realmListTransactionDetailDiscount = transactionDetail.getTransactionDetailDiscountID();
            LinkedList <String> linkedListDiscount = new LinkedList<>();
            for (int b = 0; b < realmListTransactionDetailDiscount.size() ; b++) {
                linkedListDiscount.add(realmListTransactionDetailDiscount.get(b).getDiscountID());
            }

            if (
                    !item_id.equals(transactionDetail.getTransactionDetailItemID())
                    || !variant_id.equals(transactionDetail.getTransactionDetailVariantID())
                    || !isSame(linkedListModifier,modifier_id)
                    || !isSame(linkedListDiscount,discount_id)
            ){
                value = false;
            }
        } else {
            value = false;
        }
        return value;
    }

    private boolean isSame (LinkedList<String> linkedListFirst, LinkedList<String> linkedListSecond){
        for (int k = 0; k < linkedListFirst.size() ; k++) {
            if (linkedListFirst.get(k).contains("var")){
                linkedListFirst.remove(k);
            }
        }
        for (int k = 0; k < linkedListSecond.size() ; k++) {
            if (linkedListSecond.get(k).contains("var")){
                linkedListSecond.remove(k);
            }
        }
        boolean value = true;
        if (linkedListFirst.size() != linkedListSecond.size()){
            value = false;
        }else {
            for (int i = 0; i < linkedListFirst.size(); i++) {
                if (!linkedListSecond.contains(linkedListFirst.get(i))) {
                    value = false;
                }
            }
        }

        return value;
    }

    private void updateQuantityItemOrder (Realm realm,String quantity) {
        RealmResults<TransactionDetail> realmResultTransactionDetail = realm.where(TransactionDetail.class).findAll();
        TransactionDetail transactionDetail = realmResultTransactionDetail.get(realmResultTransactionDetail.size()-1);
        String newQuantity = stringOf(integerOf(quantity) + integerOf(transactionDetail.getTransactionDetailQuantity()));
        String newPrice = stringOf((integerOf(transactionDetail.getTransactionDetailTotalPrice())/integerOf(transactionDetail.getTransactionDetailQuantity()))*integerOf(newQuantity));
        realm.beginTransaction();
        transactionDetail.setTransactionDetailQuantity(newQuantity);
        transactionDetail.setTransactionDetailTotalPrice(newPrice);
        realm.commitTransaction();
    }

    private int dp_to_pixel(float dp){
        DisplayMetrics metrics = (getActivity()).getResources().getDisplayMetrics();
        float fpixels = metrics.density * dp;
        return (int) (fpixels + 0.5f);
    }

}

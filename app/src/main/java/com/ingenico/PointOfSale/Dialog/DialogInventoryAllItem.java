package com.ingenico.PointOfSale.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ingenico.PointOfSale.Adapter.SpinnerAdapterDialogInventoryAllItem;
import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Controller.MultiRowTextCheckedTextView;
import com.ingenico.PointOfSale.Controller.SessionManager;
import com.ingenico.PointOfSale.LinearLayout.LinearLayoutDialogInventoryAllItemVariantList;
import com.ingenico.PointOfSale.ModelRealm.ItemModifierGroup;
import com.ingenico.PointOfSale.ModelRealm.ItemVariant;
import com.ingenico.PointOfSale.Object.Category;
import com.ingenico.PointOfSale.Object.Discount;
import com.ingenico.PointOfSale.Object.Item;
import com.ingenico.PointOfSale.Object.ModifierSet;
import com.ingenico.PointOfSale.Object.Variant;
import com.ingenico.PointOfSale.Object.ViewVariant;
import com.ingenico.PointOfSale.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator-Handy on 1/9/2017.
 */

public class DialogInventoryAllItem extends DialogFragment {

    private Item item = null;
    public void setItem(Item item){this.item = item;}

    private final int OPEN_CAMERA = 0;
    private final int OPEN_GALLERY = 1;

    private ImageView imageViewDialogInventoryCancel, imageViewDialogInventoryAllItem,
            imageViewDialogInventoryAllItemNameHideKeyboard, imageViewDialogInventoryAllItemPriceHideKeyboard,
            imageViewDialogInventoryCancelVariant;
    private TextView textViewDialogInventoryAdd, textViewDialogInventoryAddVariant,
            textViewDialogInventoryAllItemTotalVariant;
    private EditText editTextDialogInventoryAllItemName, editTextDialogInventoryAllItemPrice;
    private Spinner spinnerDialogInventoryAllItemCategory;
    private LinearLayout linearLayoutDialogInventoryAllItemAddVariant,
            linearLayoutDialogInventoryAllItemModifierSet,
            linearLayoutDialogInventoryAllItemItem,
            linearLayoutDialogInventoryAllItemVariant,
            linearLayoutDialogInventoryAllItemVariantListContainer,
            linearLayoutDialogInventoryAllItemPrice;
    private Button buttonDialogInventoryAllItemDeleteItem;
    private Switch switchSalesTax;

    private LinkedList<Category> linkedListCategory;
    private SpinnerAdapterDialogInventoryAllItem spinnerAdapterDialogInventoryAllItem;

    private LinkedList<Switch> linkedListSwitch;
    private String KEY_DISCOUNT_ID = "key_discount_id";
    private String KEY_DISCOUNT_NAME = "key_discount_name";
    private String KEY_DISCOUNT_VALUE = "key_discount_value";
    private String KEY_DISCOUNT_TYPE = "key_discount_type";

    private LinkedList<ViewVariant> linkedListViewVariant;
    private LinkedList<ModifierSet> linkedListCheckedTextView;

    private LinkedList<Variant> linkedListVariantResult;

    private String pathPhotoInMedia;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View RootView=inflater.inflate(R.layout.dialog_inventory_all_item, null);

        imageViewDialogInventoryCancel = (ImageView)RootView.findViewById(R.id.imageViewDialogInventoryCancel);
        imageViewDialogInventoryAllItem = (ImageView)RootView.findViewById(R.id.imageViewDialogInventoryAllItem);
        imageViewDialogInventoryAllItemNameHideKeyboard = (ImageView)RootView.findViewById(R.id.imageViewDialogInventoryAllItemNameHideKeyboard);
        imageViewDialogInventoryAllItemPriceHideKeyboard = (ImageView)RootView.findViewById(R.id.imageViewDialogInventoryAllItemPriceHideKeyboard);
        imageViewDialogInventoryCancelVariant = (ImageView)RootView.findViewById(R.id.imageViewDialogInventoryCancelVariant);
        textViewDialogInventoryAdd = (TextView)RootView.findViewById(R.id.textViewDialogInventoryAdd);
        textViewDialogInventoryAddVariant = (TextView)RootView.findViewById(R.id.textViewDialogInventoryAddVariant);
        textViewDialogInventoryAllItemTotalVariant = (TextView)RootView.findViewById(R.id.textViewDialogInventoryAllItemTotalVariant);
        editTextDialogInventoryAllItemName = (EditText)RootView.findViewById(R.id.editTextDialogInventoryAllItemName);
        editTextDialogInventoryAllItemPrice = (EditText)RootView.findViewById(R.id.editTextDialogInventoryAllItemPrice);
        spinnerDialogInventoryAllItemCategory = (Spinner)RootView.findViewById(R.id.spinnerDialogInventoryAllItemCategory);
        linearLayoutDialogInventoryAllItemAddVariant = (LinearLayout)RootView.findViewById(R.id.linearLayoutDialogInventoryAllItemAddVariant);
        linearLayoutDialogInventoryAllItemVariant = (LinearLayout)RootView.findViewById(R.id.linearLayoutDialogInventoryAllItemVariant);
        linearLayoutDialogInventoryAllItemItem = (LinearLayout)RootView.findViewById(R.id.linearLayoutDialogInventoryAllItemItem);
        linearLayoutDialogInventoryAllItemModifierSet = (LinearLayout)RootView.findViewById(R.id.linearLayoutDialogInventoryAllItemModifierSet);
        linearLayoutDialogInventoryAllItemVariantListContainer = (LinearLayout)RootView.findViewById(R.id.linearLayoutDialogInventoryAllItemVariantListContainer);
        linearLayoutDialogInventoryAllItemPrice = (LinearLayout)RootView.findViewById(R.id.linearLayoutDialogInventoryAllItemPrice);
        buttonDialogInventoryAllItemDeleteItem = (Button)RootView.findViewById(R.id.buttonDialogInventoryAllItemDeleteItem);
        switchSalesTax = (Switch)RootView.findViewById(R.id.switchTax);

        editTextDialogInventoryAllItemName.clearFocus();
        editTextDialogInventoryAllItemPrice.clearFocus();

        controlSoftKeyboard(editTextDialogInventoryAllItemName,
                imageViewDialogInventoryAllItemNameHideKeyboard);
        controlSoftKeyboard(editTextDialogInventoryAllItemPrice,
                imageViewDialogInventoryAllItemPriceHideKeyboard);

        int visibilityButton = (item==null) ? View.GONE : View.VISIBLE;
        buttonDialogInventoryAllItemDeleteItem.setVisibility(visibilityButton);

        linkedListViewVariant = new LinkedList<>();
        linkedListCheckedTextView = new LinkedList<>();

        /**
         * setting view image
         */
        generateViewImageItem();
        /**
         * setting view name
         */
        generateViewItemName();
        /**
         * setting view category
         */
        generateSpinnerCategory(((CashRegisterActivity)getActivity()).realm);
        /**
         * setting view price and variant set
         */
        generateViewPriceAndVariantView(((CashRegisterActivity)getActivity()).realm);
        /**
         * setting view modifier set
         */
        generateViewForModifierSets(((CashRegisterActivity)getActivity()).realm);


        /**
         * listener image item
         */
        imageViewDialogInventoryAllItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        /**
         * listener add variant list
         */
        linearLayoutDialogInventoryAllItemAddVariant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutDialogInventoryAllItemItem.setVisibility(View.GONE);
                linearLayoutDialogInventoryAllItemVariant.setVisibility(View.VISIBLE);
                generateViewVariantList();
            }
        });

        imageViewDialogInventoryCancelVariant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateView();
            }
        });

        textViewDialogInventoryAddVariant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Added Variant", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * handle add button
         */
        textViewDialogInventoryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAllData();
            }
        });

        /**
         * handle add button
         */
        buttonDialogInventoryAllItemDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item != null){
                    deleteItem(((CashRegisterActivity)getActivity()).realm);
                    getDialog().dismiss();
                }
            }
        });


        /**
         * handle cancel button
         */
        imageViewDialogInventoryCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CashRegisterActivity)getActivity()).gridViewItemFromCategory.setEnabled(true);
                getDialog().dismiss();
            }
        });

        setCancelable(false);
        builder.setView(RootView);
        return builder.create();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case OPEN_CAMERA:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    imageViewDialogInventoryAllItem.setImageURI(selectedImage);
                    String[] projection = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getActivity().getContentResolver().query
                            (selectedImage, projection, null,
                            null, null);
                    int column_index_data = cursor
                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    pathPhotoInMedia = cursor.getString(column_index_data);
                }

                break;
            case OPEN_GALLERY:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    imageViewDialogInventoryAllItem.setImageURI(selectedImage);
                }
                break;
        }
    }

    private void generateViewImageItem(){
        if (item!=null){
            ((CashRegisterActivity)getActivity()).image.loadImageFromPathParamaterize(
                    Declaration.IMAGE_OUTPUT_PATH+item.getItemID()+".png",
                    imageViewDialogInventoryAllItem
            );
        }
    }

    private void generateViewItemName(){
        if (item!=null) {
            editTextDialogInventoryAllItemName.setText(item.getItemName());
        }
    }

    private void generateViewPriceAndVariantView(Realm realm){
        if (item!=null){
            if (item.getItemVarian().get(0).contains("var")){
                linearLayoutDialogInventoryAllItemPrice.setVisibility(View.GONE);
                textViewDialogInventoryAllItemTotalVariant.setText(
                        String.valueOf(item.getItemVarian().size())
                );
                for (int i = 0; i < item.getItemVarian().size(); i++) {
                    RealmResults<com.ingenico.PointOfSale.ModelRealm.Variant> realmResultVariant =
                            realm.where(com.ingenico.PointOfSale.ModelRealm.Variant.class)
                                    .equalTo("VariantID",item.getItemVarian().get(i))
                                    .findAll();
                    addLinearLayoutVariantList(
                            realmResultVariant.get(0).getVariantID(),
                            realmResultVariant.get(0).getVariantName(),
                            realmResultVariant.get(0).getVariantPrice()
                    );
                }
                addLinearLayoutVariantList("","","");
            }else {
                editTextDialogInventoryAllItemPrice.setText(
                        item.getItemPrice()
                );
            }
        }
    }

    private void listenerVariantListCancel(final LinkedList<ViewVariant> linked_list_view_variant) {
        for (int i = 0; i < linked_list_view_variant.size(); i++) {
            final int _i = i;
            linked_list_view_variant.get(i).getImageViewCancel().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeVariantList(_i);
                    if (linked_list_view_variant.size()<2){
                        addLinearLayoutVariantList("","","");
                    }
                }
            });
        }
    }

    private void removeVariantList(int position){
        linkedListViewVariant.get(position).getLinearLayoutParent().setVisibility(View.GONE);
        linkedListViewVariant.remove(position);
        listenerVariantListCancel(linkedListViewVariant);
        fixHintInEditTextVariant(linkedListViewVariant);
    }

    private void generateViewVariantList() {
        if (linkedListViewVariant.size()<2){
            if (linearLayoutDialogInventoryAllItemPrice.getVisibility()==View.VISIBLE){
                if (editTextDialogInventoryAllItemPrice.getText().toString().length()>0){
                    if (Integer.parseInt(
                            ((CashRegisterActivity)getActivity()).revertValuePattern(
                                    editTextDialogInventoryAllItemPrice.getText().toString()
                            )
                    )>0){
                        addLinearLayoutVariantList("",""
                                ,((CashRegisterActivity)getActivity()).revertValuePattern(
                                        editTextDialogInventoryAllItemPrice.getText().toString()
                                )
                        );
                    }
                }else {
                    addLinearLayoutVariantList("","","");
                }
                addLinearLayoutVariantList("","","");
            }
        }
    }

    private void addLinearLayoutVariantList(String variant_id, String variant_name, String variant_price){
        LinearLayoutDialogInventoryAllItemVariantList linearLayoutDialogInventoryAllItemVariantList=
                new LinearLayoutDialogInventoryAllItemVariantList();
        Variant variant = new Variant();
        variant.setVariantID(variant_id);
        variant.setVariantName(variant_name);
        variant.setVariantPrice(variant_price);
        LinearLayout linearLayout = linearLayoutDialogInventoryAllItemVariantList.LinearLayoutDialogInventoryAllItemVariantList(
                getActivity(),
                variant
        );
        linearLayoutDialogInventoryAllItemVariantListContainer.addView(
                linearLayout
        );
        ViewVariant viewVariant = new ViewVariant();
        viewVariant.setLinearLayoutParent(linearLayout);
        viewVariant.setEditTextName(linearLayoutDialogInventoryAllItemVariantList.getEditTextVariantName());
        viewVariant.setEditTextPrice(linearLayoutDialogInventoryAllItemVariantList.getEditTextVariantPrice());
        viewVariant.setImageViewCancel(linearLayoutDialogInventoryAllItemVariantList.getImageViewVariantCancel());


        TextWatcher textWatcherVariant = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0 && s.length()<2){
                    if (linkedListViewVariant.get(linkedListViewVariant.size()-1)
                            .getEditTextName().getText().toString().length() !=0 ){
                        linkedListViewVariant.get(linkedListViewVariant.size()-1)
                                .getImageViewCancel().setVisibility(View.VISIBLE);
                        addLinearLayoutVariantList("","","");
                    }else if (linkedListViewVariant.size()== 2){
                        linkedListViewVariant.get(0)
                                .getImageViewCancel().setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        viewVariant.getEditTextName().addTextChangedListener(textWatcherVariant);


        linkedListViewVariant.add(viewVariant);
        listenerVariantListCancel(linkedListViewVariant);
        fixHintInEditTextVariant(linkedListViewVariant);
    }

    private void fixHintInEditTextVariant(LinkedList<ViewVariant> linked_list_view_variant){
        for (int i = 0; i < linked_list_view_variant.size(); i++) {
            linked_list_view_variant.get(i).getEditTextName().setHint(
                    "Variant "+ (i+1)
            );
        }
    }

    private void validateView() {
        if (linkedListViewVariant.size()>0){
            if (linkedListViewVariant.size() == 2){
                editTextDialogInventoryAllItemPrice.setText(
                        linkedListViewVariant.get(0).getEditTextPrice().getText().toString()
                );
                linearLayoutDialogInventoryAllItemPrice.setVisibility(View.VISIBLE);
                linearLayoutDialogInventoryAllItemItem.setVisibility(View.VISIBLE);
                linearLayoutDialogInventoryAllItemVariant.setVisibility(View.GONE);
                textViewDialogInventoryAllItemTotalVariant.setVisibility(View.GONE);
            }else if (linkedListViewVariant.size() > 2){
                if (allVariantFilled(linkedListViewVariant)){
                    textViewDialogInventoryAllItemTotalVariant.setText(
                            String.valueOf(linkedListViewVariant.size()-1));
                    textViewDialogInventoryAllItemTotalVariant.setVisibility(View.VISIBLE);
                    linearLayoutDialogInventoryAllItemPrice.setVisibility(View.GONE);
                    linearLayoutDialogInventoryAllItemItem.setVisibility(View.VISIBLE);
                    linearLayoutDialogInventoryAllItemVariant.setVisibility(View.GONE);
                }else {
                    ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent(
                            getActivity(),
                            "Sorry",
                            "Please fill all data correctly"
                    );
                }
            }
        }
    }

    private boolean allVariantFilled(LinkedList<ViewVariant> linked_list){
        boolean value = true;
        for (int i = 0; i < linked_list.size()-1; i++) {
            if (linked_list.get(i).getEditTextName().getText().toString().trim().length()>0){
                if (linked_list.get(i).getEditTextPrice().getText().toString().trim().length()>0){
                    if (Integer.parseInt(
                            linked_list.get(i).getEditTextPrice().getText().toString())<0){
                        value = false;
                    }
                }else {
                    value = false;
                }
            }else {
                value = false;
            }
        }
        return value;
    }


    private void generateSpinnerCategory(Realm realm) {
        LinkedList<Category> linkedList = new LinkedList<>();

        RealmResults<com.ingenico.PointOfSale.ModelRealm.Category> realmResultCategory =
                realm.where(com.ingenico.PointOfSale.ModelRealm.Category.class)
                .findAll();

        for (int i = 0; i < realmResultCategory.size(); i++) {
            Category category = new Category();
            if (!realmResultCategory.get(i).getCategory().equals("")){
                category.setCategory(realmResultCategory.get(i).getCategory());
                linkedList.add(category);
            }
        }

        Resources resources = getResources();

        linkedListCategory = sortedLinkedListCategory(linkedList);
        Category mCategory = new Category();
        mCategory.setCategory(Declaration.SELECT_CATEGORY);
        linkedListCategory.addFirst(mCategory);

        spinnerAdapterDialogInventoryAllItem = new SpinnerAdapterDialogInventoryAllItem(
                getActivity(),
                R.layout.spinner_dialog_inventory_all_item_category,
                linkedListCategory,
                resources
        );
        spinnerDialogInventoryAllItemCategory.setAdapter(spinnerAdapterDialogInventoryAllItem);

        int position = 0;
        if (item!=null){
            for (int j = 0; j < linkedListCategory.size(); j++) {
                if (linkedListCategory.get(j).getCategory().equals(item.getItemCategory())){
                    position = j;
                }
            }
            spinnerDialogInventoryAllItemCategory.setSelection(position);
        }

    }

    private void generateViewForModifierSets(Realm realm) {
        RealmResults<com.ingenico.PointOfSale.ModelRealm.ModifierGroup> realmResultModifierGroup =
                realm.where(com.ingenico.PointOfSale.ModelRealm.ModifierGroup.class)
                        .equalTo("modifierGroupOutletID",((CashRegisterActivity)getActivity()).session.get(SessionManager.KEY_OUTLET_ID))
                        .findAll();

        for (int i = 0; i < realmResultModifierGroup.size(); i++) {
            RealmResults<com.ingenico.PointOfSale.ModelRealm.ModifierDetail> realmResultModifierDetail =
                    realm.where(com.ingenico.PointOfSale.ModelRealm.ModifierDetail.class)
                            .equalTo("modifierDetailGroupID",realmResultModifierGroup.get(i).getModifierGroupID())
                            .findAll();
            MultiRowTextCheckedTextView multiRowTextCheckedTextView = new MultiRowTextCheckedTextView(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            multiRowTextCheckedTextView.setLayoutParams(layoutParams);
            multiRowTextCheckedTextView.setTitleText(realmResultModifierGroup.get(i).getModifierGroupName());
            multiRowTextCheckedTextView.setSubTitleText(allModifierDetail(realmResultModifierDetail));

            if (item!=null){
                 if (item.getItemModifier().contains(realmResultModifierGroup.get(i).getModifierGroupID())){
                     multiRowTextCheckedTextView.setChecked(true);
                 }
            }
            ModifierSet modifierSet = new ModifierSet();
            modifierSet.setModifierGroupID(realmResultModifierGroup.get(i).getModifierGroupID());
            modifierSet.setMultiRowTextCheckedTextView(multiRowTextCheckedTextView);
            linkedListCheckedTextView.add(modifierSet);
            linearLayoutDialogInventoryAllItemModifierSet.addView(multiRowTextCheckedTextView);
        }

    }

    private void validateAllData() {
//        Log.e("Variant ", linkedListVariant().size()+"");
        if (editTextDialogInventoryAllItemName.getText().toString().trim().length()==0){
            ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent(
                    getActivity(),
                    "Warning",
                    "Please fill name of item"
            );
        }else {
            if (spinnerDialogInventoryAllItemCategory.getSelectedItem().toString().equals(
                    Declaration.SELECT_CATEGORY
            )){
                ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent(
                        getActivity(),
                        "Warning",
                        "Please fill category of item"
                );
            }else {
                if (!priceOrVariantIsFilled()) {
                    ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent(
                            getActivity(),
                            "Warning",
                            "Please fill price or variant of item"
                    );
                }else {
                    updateDatabase(((CashRegisterActivity)getActivity()).realm);
                    Log.e("item name  ", editTextDialogInventoryAllItemName.getText().toString()+"");
                    Log.e("item name  ",
                            ((Category) spinnerDialogInventoryAllItemCategory.getSelectedItem()).getCategory()+"");
                    Log.e("VariantVSize ", linkedListViewVariant.size()+"");
                    Log.e("isi price ", editTextDialogInventoryAllItemPrice.getText().toString()+"");
                    Log.e("vis price ", String.valueOf(linearLayoutDialogInventoryAllItemPrice.getVisibility()==View.VISIBLE)+"");
                    Log.e("VariantRe ", linkedListVariantResult+"");
                }
            }
        }

    }

    private void updateDatabase(Realm realm){
        RealmResults<com.ingenico.PointOfSale.ModelRealm.Merchant> realmResultMerchant =
                realm.where(com.ingenico.PointOfSale.ModelRealm.Merchant.class)
                        .findAll();
        Category category = (Category) spinnerDialogInventoryAllItemCategory.getSelectedItem();
//        getAndCreateVariantInDatabase(realm,realmResultMerchant);
        if (item == null){
            String item_id = createItemID(realmResultMerchant);
            createItemToDataBase(realm,realmResultMerchant,category,item_id);
        }else {
            deleteItemFromDataBase(realm, item.getItemID(), realmResultMerchant, category);
        }
        getDialog().dismiss();
    }

    private void deleteItemFromDataBase(Realm realm, String item_id,
                                        RealmResults<com.ingenico.PointOfSale.ModelRealm.Merchant> realmResultMerchant,
                                        Category category){
        realm.beginTransaction();
        RealmResults<com.ingenico.PointOfSale.ModelRealm.Item> realmResultItem =
                realm.where(com.ingenico.PointOfSale.ModelRealm.Item.class)
                        .equalTo("itemID",item_id)
                        .findAll();
        realmResultItem.deleteAllFromRealm();
        realm.commitTransaction();

        createItemToDataBase(realm, realmResultMerchant, category, item_id);
    }

    private void createItemToDataBase(Realm realm,
                                      RealmResults<com.ingenico.PointOfSale.ModelRealm.Merchant> realmResultMerchant,
                                      Category category, String item_id){
        RealmResults<com.ingenico.PointOfSale.ModelRealm.Item> realmResultItem =
                realm.where(com.ingenico.PointOfSale.ModelRealm.Item.class)
                        .findAll();
        savePhoto(item_id);
        if (linearLayoutDialogInventoryAllItemPrice.getVisibility() == View.VISIBLE){
            ((CashRegisterActivity)getActivity()).RealmCreateDatabaseTableItem(
                    realm,
                    realmResultItem.size(),
                    item_id,
                    editTextDialogInventoryAllItemName.getText().toString(),
                    editTextDialogInventoryAllItemPrice.getText().toString(),
                    "0",
                    category.getCategory(),
                    statusTax(),
                    linkedListVariant(),
                    linkedListModifier(),
                    ((CashRegisterActivity)getActivity()).session.get(SessionManager.KEY_OUTLET_ID));
        }else {
            LinkedList<String> linkedListVariant = new LinkedList<>();
            if (linkedListViewVariant.size()>0){
                linkedListVariantResult = new LinkedList<>();
                for (int i = 0; i < linkedListViewVariant.size()-1; i++) {
                    String variantID = createVariantID(realmResultMerchant);
                    Variant variant = new Variant();
                    variant.setVariantID(variantID);
                    variant.setVariantName(linkedListViewVariant.get(i).getEditTextName().getText().toString());
                    variant.setVariantName(linkedListViewVariant.get(i).getEditTextPrice().getText().toString());
                    linkedListVariantResult.add(variant);
                    ((CashRegisterActivity) getActivity()).RealmCreateDatabaseTableVariant(
                            realm,
                            (new Random()).nextInt(9999) + 1,
                            variantID,
                            linkedListViewVariant.get(i).getEditTextName().getText().toString(),
                            linkedListViewVariant.get(i).getEditTextPrice().getText().toString()
                    );
                }

                for (int i = 0; i < linkedListViewVariant.size()-1; i++) {
                    linkedListVariant.add(linkedListVariantResult.get(i).getVariantID());
                }

                Log.e("list variant",linkedListVariant+"");

                ((CashRegisterActivity)getActivity()).RealmCreateDatabaseTableItem(
                        realm,
                        realmResultItem.size(),
                        item_id,
                        editTextDialogInventoryAllItemName.getText().toString(),
                        "0",
                        "0",
                        category.getCategory(),
                        statusTax(),
                        linkedListVariant,
                        linkedListModifier(),
                        ((CashRegisterActivity)getActivity()).session.get(SessionManager.KEY_OUTLET_ID));
            }
        }
    }

    private void savePhoto(String item_id) {
        Bitmap bmp = ((BitmapDrawable) imageViewDialogInventoryAllItem.getDrawable()).getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        scaleDown(bmp,500f,false).compress(Bitmap.CompressFormat.PNG, 100, stream);
        ((CashRegisterActivity)getActivity()).image.saveToSDCard(
                item_id,
                encodeImage(stream.toByteArray())
        );
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());
        Bitmap newBitmap;
        if (ratio<1){
            newBitmap = Bitmap.createScaledBitmap(realImage, width,
                    height, filter);
        }else {newBitmap = realImage;}
        return newBitmap;
    }

    private static String encodeImage (byte[] ImageByteArray){
        return Base64.encodeToString(ImageByteArray,Base64.DEFAULT);
    }

    private String createItemID(RealmResults<com.ingenico.PointOfSale.ModelRealm.Merchant> realm_result_merchant){
        return realm_result_merchant.get(0).getMerchantID()+
                "_item_"+
                getMacAddress()+
                (new Random()).nextInt(9999) + 1;
    }

    private String createVariantID(RealmResults<com.ingenico.PointOfSale.ModelRealm.Merchant> realm_result_merchant){
        return realm_result_merchant.get(0).getMerchantID()+
                "_varian_"+
                getMacAddress()+"+"+
                (new Random()).nextInt(9999) + 1;
    }

    private String statusTax(){
        if (switchSalesTax.isChecked()){
            return "1";
        }else {return "0";}
    }

    private void getAndCreateVariantInDatabase(Realm realm,
                                     RealmResults<com.ingenico.PointOfSale.ModelRealm.Merchant> realm_result_merchant){
        linkedListVariantResult = new LinkedList<>();
        if (linkedListViewVariant.size()>0){
            for (int i = 0; i < linkedListViewVariant.size()-1; i++) {
                String variantID = createVariantID(realm_result_merchant);
                Variant variant = new Variant();
                variant.setVariantID(variantID);
                variant.setVariantName(linkedListViewVariant.get(i).getEditTextName().getText().toString());
                variant.setVariantName(linkedListViewVariant.get(i).getEditTextPrice().getText().toString());
                linkedListVariantResult.add(variant);
                ((CashRegisterActivity)getActivity()).RealmCreateDatabaseTableVariant(
                        realm,
                        (new Random()).nextInt(9999) + 1,
                        variantID,
                        linkedListViewVariant.get(i).getEditTextName().getText().toString(),
                        linkedListViewVariant.get(i).getEditTextPrice().getText().toString()
                );
            }
        }
    }

    private LinkedList<String> linkedListVariant(){
        LinkedList<String> linkedListVariant = new LinkedList<>();
        linkedListVariant.add("");
        return linkedListVariant;
    }

    private LinkedList<String> linkedListModifier(){
        LinkedList<String> linkedListModifierGroupResult = new LinkedList<>();
        for (int i = 0; i < linkedListCheckedTextView.size(); i++) {
            if (linkedListCheckedTextView.get(i).getMultiRowTextCheckedTextView().isCheck()){
                linkedListModifierGroupResult.add(linkedListCheckedTextView.get(i).getModifierGroupID());
            }
        }
        if (linkedListModifierGroupResult.size()==0){
            linkedListModifierGroupResult.add("");
        }
        return linkedListModifierGroupResult;
    }

    private String getMacAddress(){
        String macAddress;
        WifiManager manager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        macAddress = info.getMacAddress();
        return macAddress;
    }

    private boolean priceOrVariantIsFilled() {
        boolean value = true;
        if (linearLayoutDialogInventoryAllItemPrice.getVisibility()==View.VISIBLE){
            if (editTextDialogInventoryAllItemPrice.getText().toString().trim().length()==0){
                Log.e("flag","1");
                value = false;
            }else {
                if (Integer.valueOf(editTextDialogInventoryAllItemPrice.getText().toString()).equals(0)){
                    Log.e("flag","2");
                    value = false;
                }
            }
        }else {
            if (linkedListViewVariant.size()<2){
                Log.e("flag","3");
                value = false;
            }else {
                for (int i = 0; i < linkedListViewVariant.size()-1; i++) {
                    if (linkedListViewVariant.get(i).getEditTextName().getText().toString().trim().length()<0){
                        Log.e("flag","4");
                        value = false;
                    }else {
                        if (linkedListViewVariant.get(i).getEditTextPrice().getText().toString().length()==0){
                            Log.e("flag","5");
                            value = false;
                        }else {
                            if (Integer.valueOf(linkedListViewVariant.get(i).getEditTextPrice().getText().toString()).equals(0)){
                                Log.e("flag","6");
                                value = false;
                            }
                        }
                    }
                }
            }
        }
        return value;
    }

    private void deleteItem(Realm realm) {
        realm.beginTransaction();
        RealmResults<com.ingenico.PointOfSale.ModelRealm.Item> realmResultItem =
                realm.where(com.ingenico.PointOfSale.ModelRealm.Item.class)
                        .equalTo("itemID",item.getItemID())
                        .findAll();
        realmResultItem.deleteAllFromRealm();
        realm.commitTransaction();
    }

    private String allModifierDetail(RealmResults<com.ingenico.PointOfSale.ModelRealm.ModifierDetail> realm_result) {
        String value = "";
        for (int i = 0; i < realm_result.size(); i++) {
            value += realm_result.get(i).getModifierDetailName()+", ";
        }
        return value.substring(0,value.length()-2);
    }

    private void controlSoftKeyboard (final EditText editText, final ImageView imageView){
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int visibility = (hasFocus) ? View.VISIBLE : View.GONE;
                imageView.setVisibility(visibility);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.clearFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });
    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item){
                if (options[item].equals("Take Photo")){
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, OPEN_CAMERA);
                }
                else if (options[item].equals("Choose from Gallery")){
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , OPEN_GALLERY);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private LinkedList<Category> sortedLinkedListCategory (LinkedList<Category> linked_list){
        boolean flag = true;
        Category item;
        while(flag){
            flag = false;
            for (int i = 0; i < linked_list.size() - 1; i++) {
                if ((linked_list.get(i).getCategory())
                        .compareToIgnoreCase(
                                (linked_list.get(i+1).getCategory())
                        ) > 0){
                    item = linked_list.get(i);
                    linked_list.set(i,linked_list.get(i+1));
                    linked_list.set(i+1,item);
                    flag = true;
                }
            }
        }
        return linked_list;
    }

    private void copyPhoto(String sourceString, String destString) throws IOException {
        File sourceFile = new File(sourceString);
        File destFile = new File(destString);

        if (!sourceFile.exists()) {
            return;
        }

        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }


    }



}

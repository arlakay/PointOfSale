package com.ingenico.PointOfSale.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Controller.SessionManager;
import com.ingenico.PointOfSale.LinearLayout.LinearLayoutDialogInventoryCategory;
import com.ingenico.PointOfSale.ModelRealm.Item;
import com.ingenico.PointOfSale.Object.Category;
import com.ingenico.PointOfSale.Object.DialogCategory;
import com.ingenico.PointOfSale.Object.ViewCategory;
import com.ingenico.PointOfSale.R;

import java.io.File;
import java.util.LinkedList;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 1/23/2017.
 */

public class DialogInventoryCategory extends DialogFragment {

    private Category category = null;
    public void setCategory(Category category){this.category = category;}

    private ImageView imageViewDialogInventoryCancel;
    private EditText editTextDialogInventoryCategoryName;
    private LinearLayout linearLayoutContainer;
    private TextView textViewDialogInventoryAdd;
    private Button buttonDialogInventoryAllItemDeleteCategory;

    private LinkedList<LinearLayoutDialogInventoryCategory> linkedListViewCategory;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View RootView=inflater.inflate(R.layout.dialog_inventory_category, null);

        imageViewDialogInventoryCancel = (ImageView)RootView.findViewById(R.id.imageViewDialogInventoryCancel);
        linearLayoutContainer = (LinearLayout)RootView.findViewById(R.id.linearLayoutContainer);
        editTextDialogInventoryCategoryName = (EditText)RootView.findViewById(R.id.editTextDialogInventoryCategoryName);
        textViewDialogInventoryAdd = (TextView)RootView.findViewById(R.id.textViewDialogInventoryAdd);
        buttonDialogInventoryAllItemDeleteCategory = (Button)RootView.findViewById(R.id.buttonDialogInventoryAllItemDeleteCategory);

        if (category != null){
            editTextDialogInventoryCategoryName.setText(category.getCategory());
            buttonDialogInventoryAllItemDeleteCategory.setVisibility(View.VISIBLE);
        }else {
            buttonDialogInventoryAllItemDeleteCategory.setVisibility(View.GONE);
        }

        linkedListViewCategory = new LinkedList<>();
        generateView(((CashRegisterActivity)getActivity()).realm);

        textViewDialogInventoryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllData();
            }
        });

        buttonDialogInventoryAllItemDeleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCategory(category.getCategory());
            }
        });

        imageViewDialogInventoryCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        setCancelable(false);
        builder.setView(RootView);
        return builder.create();
    }

    private void generateView(Realm realm){
        RealmResults<Item> realmResultItem =
                realm.where(
                        com.ingenico.PointOfSale.ModelRealm.Item.class)
                        .findAll();

        for (int i = 0; i < realmResultItem.size(); i++) {
            if (realmResultItem.get(i).getItemOutletID().equals(
                    ((CashRegisterActivity)getActivity()).session.get(SessionManager.KEY_OUTLET_ID))
                    || (realmResultItem.get(i).getItemOutletID().equals(Declaration.ALL_OUTLET)))
            {
                addLayoutItem(
                        realmResultItem.get(i).getItemID(),
                        realmResultItem.get(i).getItemName(),
                        Declaration.IMAGE_OUTPUT_PATH + realmResultItem.get(i).getItemID()+".png",
                        realmResultItem.get(i).getItemCategory()
                );
            }
        }
    }

    private void addLayoutItem(String item_id,String item_name,String image_url, String category_name){
        DialogCategory dialogCategory = new DialogCategory();
        dialogCategory.setItemID(item_id);
        dialogCategory.setItemName(item_name);
        final File file = new File(image_url);
        if (file.exists()){
            dialogCategory.setImageUrl(image_url);
        }else {
            dialogCategory.setImageUrl(Declaration.NULL_DATA);
        }
        if (!category_name.equals(Declaration.NO_CATEGORY)){
            dialogCategory.setCategoryName(category_name);
        }else{
            dialogCategory.setCategoryName(" ");
        }

        final LinearLayoutDialogInventoryCategory linearLayoutDialogInventoryCategory = new
                LinearLayoutDialogInventoryCategory();
        LinearLayout linearLayoutItemWithCategory = linearLayoutDialogInventoryCategory.LinearLayoutDialogInventoryCategory(
                getActivity(),
                dialogCategory
        );
        linearLayoutContainer.addView(linearLayoutItemWithCategory);

        linearLayoutDialogInventoryCategory.getLinearLayoutOnClick().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!linearLayoutDialogInventoryCategory.isCheck()){
                    linearLayoutDialogInventoryCategory.setActiveCategory(
                            true,
                            editTextDialogInventoryCategoryName.getText().toString()
                    );
                }else {
                    linearLayoutDialogInventoryCategory.setActiveCategory(
                            false,
                            Declaration.NULL_DATA
                    );
                }
            }
        });

        ViewCategory viewCategory = new ViewCategory();
        viewCategory.setLinearLayoutOnClick(linearLayoutDialogInventoryCategory.getLinearLayoutOnClick());
        viewCategory.setRadioButton(linearLayoutDialogInventoryCategory.getRadioButton());
        viewCategory.setTextViewCategory(linearLayoutDialogInventoryCategory.getTextViewCategoryName());
        linkedListViewCategory.add(linearLayoutDialogInventoryCategory);
    }

    private void getAllData(){
        if (editTextDialogInventoryCategoryName.getText().toString().trim().length()>0){
            LinkedList<LinearLayoutDialogInventoryCategory> linkedListResult = new LinkedList<>();
            for (int i = 0; i < linkedListViewCategory.size(); i++) {
                if (linkedListViewCategory.get(i).getRadioButton().isChecked()){
                    linkedListResult.add(linkedListViewCategory.get(i));
                }
            }
            createCategoryAtDataBase(linkedListResult);
        }else {
            ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent(
                    getActivity(),
                    "Sorry",
                    "Please fill category name"
            );
        }
    }

    private void updateToDatabase(LinkedList<LinearLayoutDialogInventoryCategory> linkedListResult){
        for (int i = 0; i < linkedListResult.size(); i++) {
            RealmResults<Item> realmResultItem =
                    ((CashRegisterActivity)getActivity()).realm.where(
                            com.ingenico.PointOfSale.ModelRealm.Item.class)
                            .equalTo("itemID",
                                    linkedListResult.get(i).getItemID()
                            )
                            .findAll();
            ((CashRegisterActivity)getActivity()).realm.beginTransaction();
            realmResultItem.last().setItemCategory(
                    linkedListResult.get(i).getTextViewCategoryName().getText().toString()
            );
            ((CashRegisterActivity)getActivity()).realm.commitTransaction();
        }
        getDialog().dismiss();
    }

    private void createCategoryAtDataBase(LinkedList<LinearLayoutDialogInventoryCategory> linkedListResult){
        if (category == null){
            ((CashRegisterActivity)getActivity()).RealmCreateDatabaseTableCategory(
                    ((CashRegisterActivity)getActivity()).realm,
                    (new Random()).nextInt(9999) + 1,
                    editTextDialogInventoryCategoryName.getText().toString()
            );
        }
        updateToDatabase(linkedListResult);
    }

    private void deleteCategory(String category){
        Realm realm = ((CashRegisterActivity)getActivity()).realm;
        realm.beginTransaction();
        RealmResults<com.ingenico.PointOfSale.ModelRealm.Category> realmResultCategory =
                realm.where(com.ingenico.PointOfSale.ModelRealm.Category.class)
                        .equalTo("category",category)
                        .findAll();
        realmResultCategory.deleteAllFromRealm();
        realm.commitTransaction();

        updateAllItem(category);
    }

    private void updateAllItem(String category) {
        Realm realm = ((CashRegisterActivity)getActivity()).realm;

        RealmResults<Item> realmResultItem =
                realm.where(
                        com.ingenico.PointOfSale.ModelRealm.Item.class)
                        .equalTo("itemCategory", category)
                        .findAll();
        if (realmResultItem.size()>0){
            for (int i = 0; i < realmResultItem.size(); i++) {
                realm.beginTransaction();
                realmResultItem.get(i).setItemCategory(
                        Declaration.NO_CATEGORY
                );
                realm.commitTransaction();
            }
        }
    }

}

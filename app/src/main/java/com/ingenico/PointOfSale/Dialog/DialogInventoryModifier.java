package com.ingenico.PointOfSale.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Controller.SessionManager;
import com.ingenico.PointOfSale.LinearLayout.LinearLayoutDialogInventoryCategory;
import com.ingenico.PointOfSale.LinearLayout.LinearLayoutDialogInventoryModifierDetail;
import com.ingenico.PointOfSale.ModelRealm.Category;
import com.ingenico.PointOfSale.ModelRealm.Item;
import com.ingenico.PointOfSale.ModelRealm.ItemModifierGroup;
import com.ingenico.PointOfSale.ModelRealm.Merchant;
import com.ingenico.PointOfSale.Object.DialogCategory;
import com.ingenico.PointOfSale.Object.ModifierDetail;
import com.ingenico.PointOfSale.Object.ModifierGroup;
import com.ingenico.PointOfSale.Object.ViewCategory;
import com.ingenico.PointOfSale.Object.ViewModifierDetail;
import com.ingenico.PointOfSale.R;

import java.io.File;
import java.util.LinkedList;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 2/2/2017.
 */

public class DialogInventoryModifier extends DialogFragment {

    private ModifierGroup modifierGroup = null;
    public void setModifierGroup(ModifierGroup mModifierGroup){this.modifierGroup=mModifierGroup;}

    private EditText editTextDialogInventoryModifierGroupName;
    private TextView textViewTotalApply, textViewDialogInventoryAdd, textViewDialogInventoryAddApply;
    private LinearLayout linearLayoutContainer, linearLayoutContainerAllItem, linearLayoutModifier,
            linearLayoutApplyItem;
    private Button buttonDialogInventoryModifierDelete;
    private ImageView imageViewDialogInventoryCancel, imageViewDialogInventoryCancelApply;
    private RelativeLayout relativeLayoutPress;
    private CheckBox checkBoxChooseOne, checkBoxChooseMany;

    private LinkedList<ViewModifierDetail> linkedListViewModifierDetail;
    private LinkedList<LinearLayoutDialogInventoryCategory> linkedListViewCategory;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View RootView=inflater.inflate(R.layout.dialog_inventory_modifier, null);

        editTextDialogInventoryModifierGroupName = (EditText)RootView.findViewById(R.id.editTextDialogInventoryModifierGroupName);
        textViewTotalApply = (TextView)RootView.findViewById(R.id.textViewTotalApply);
        textViewDialogInventoryAdd = (TextView)RootView.findViewById(R.id.textViewDialogInventoryAdd);
        textViewDialogInventoryAddApply = (TextView)RootView.findViewById(R.id.textViewDialogInventoryAddApply);
        linearLayoutContainer = (LinearLayout)RootView.findViewById(R.id.linearLayoutContainer);
        linearLayoutContainerAllItem = (LinearLayout)RootView.findViewById(R.id.linearLayoutContainerAllItem);
        linearLayoutModifier = (LinearLayout)RootView.findViewById(R.id.linearLayoutModifier);
        linearLayoutApplyItem = (LinearLayout)RootView.findViewById(R.id.linearLayoutApplyItem);
        relativeLayoutPress = (RelativeLayout)RootView.findViewById(R.id.relativeLayoutPress);
        buttonDialogInventoryModifierDelete = (Button)RootView.findViewById(R.id.buttonDialogInventoryModifierDelete);
        imageViewDialogInventoryCancel = (ImageView)RootView.findViewById(R.id.imageViewDialogInventoryCancel);
        imageViewDialogInventoryCancelApply = (ImageView)RootView.findViewById(R.id.imageViewDialogInventoryCancelApply);
        checkBoxChooseOne = (CheckBox)RootView.findViewById(R.id.checkBoxChooseOne);
        checkBoxChooseMany = (CheckBox)RootView.findViewById(R.id.checkBoxChooseMany);

        linkedListViewModifierDetail = new LinkedList<>();
        linkedListViewCategory = new LinkedList<>();

        if (modifierGroup==null){
            buttonDialogInventoryModifierDelete.setVisibility(View.GONE);
        }else {
            buttonDialogInventoryModifierDelete.setVisibility(View.VISIBLE);
        }

        final Realm realm = ((CashRegisterActivity)getActivity()).realm;

        generateViewName();
        generateViewCheckBox();
        generateViewTotalItemApply(realm);
        generateViewModifierDetailList(realm);
        generateViewApplyModifierToItem(realm);

        checkBoxChooseOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checkBoxChooseMany.setChecked(false);
                }
            }
        });

        checkBoxChooseMany.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checkBoxChooseOne.setChecked(false);
                }
            }
        });

        relativeLayoutPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateView();
            }
        });

        buttonDialogInventoryModifierDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<Merchant> realmResultMerchant =
                        ((CashRegisterActivity)getActivity()).realm.where(
                                com.ingenico.PointOfSale.ModelRealm.Merchant.class)
                                .findAll();
                deleteModifierGroup(modifierGroup.getModifierGroupID(),realmResultMerchant, true);
                getDialog().dismiss();
            }
        });

        imageViewDialogInventoryCancelApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutModifier.setVisibility(View.VISIBLE);
                linearLayoutApplyItem.setVisibility(View.GONE);
                generateViewApplyModifierToItem(realm);
            }
        });

        textViewDialogInventoryAddApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutModifier.setVisibility(View.VISIBLE);
                linearLayoutApplyItem.setVisibility(View.GONE);
                changeTotalApplyItem();
            }
        });

        imageViewDialogInventoryCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        textViewDialogInventoryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Added", "check");
                validateAllData();
                getDialog().dismiss();
            }
        });

        setCancelable(false);
        builder.setView(RootView);
        return builder.create();
    }

    private void generateViewCheckBox() {
        if (modifierGroup != null){
            if (modifierGroup.getModifierGroupOptionFlag().equals(Declaration.CHOOSE_ONE)){
                checkBoxChooseOne.setChecked(true);
            }else {
                checkBoxChooseMany.setChecked(true);
            }
        }else {
            checkBoxChooseOne.setChecked(true);
        }
    }

    private void validateAllData() {
        if (editTextDialogInventoryModifierGroupName.getText().toString().trim().length()>0){
            if (linkedListViewModifierDetail.size()!=1){
                if (allOptionFilled()){
                    addModifierToDataBase();
                }else {
                    showAlertDialog();
                }
            }else {
                showAlertDialog();
            }
        }else {
            showAlertDialog();
        }
    }

    private void addModifierToDataBase() {
        RealmResults<Merchant> realmResultMerchant =
                ((CashRegisterActivity)getActivity()).realm.where(
                        com.ingenico.PointOfSale.ModelRealm.Merchant.class)
                        .findAll();
        if (modifierGroup==null){
            String modifier_group_id = createModifierGroupID(realmResultMerchant);
            createModifierGroup(modifier_group_id, realmResultMerchant);
        }else {
            deleteModifierGroup(modifierGroup.getModifierGroupID(),realmResultMerchant, false);
        }

    }

    private void deleteModifierGroup(String modifier_group_id, RealmResults<Merchant> realmResultMerchant,
                                     boolean isDeleted){
        RealmResults<com.ingenico.PointOfSale.ModelRealm.ModifierGroup> realmResultModifierGroup =
                ((CashRegisterActivity)getActivity()).realm.where(
                        com.ingenico.PointOfSale.ModelRealm.ModifierGroup.class)
                        .equalTo("modifierGroupID",modifier_group_id)
                        .findAll();
        ((CashRegisterActivity)getActivity()).realm.beginTransaction();
        realmResultModifierGroup.deleteAllFromRealm();
        ((CashRegisterActivity)getActivity()).realm.commitTransaction();

        RealmResults<com.ingenico.PointOfSale.ModelRealm.ModifierDetail> realmResultModifierDetail =
                ((CashRegisterActivity)getActivity()).realm.where(
                        com.ingenico.PointOfSale.ModelRealm.ModifierDetail.class)
                        .equalTo("modifierDetailGroupID",modifier_group_id)
                        .findAll();
        ((CashRegisterActivity)getActivity()).realm.beginTransaction();
        realmResultModifierDetail.deleteAllFromRealm();
        ((CashRegisterActivity)getActivity()).realm.commitTransaction();


        RealmResults<com.ingenico.PointOfSale.ModelRealm.Item> realmResultItem =
                ((CashRegisterActivity)getActivity()).realm.where(
                        com.ingenico.PointOfSale.ModelRealm.Item.class)
                        .findAll();

        for (int i = 0; i < realmResultItem.size(); i++) {
            if (modifierGroupInItem(realmResultItem.get(i).getItemModifier(),modifier_group_id)){
                for (int j = 0; j < realmResultItem.get(i).getItemModifier().size(); j++) {
                    if (realmResultItem.get(i).getItemModifier().get(j).getItemModifierGroupID().equals(modifier_group_id)){
                        ((CashRegisterActivity)getActivity()).realm.beginTransaction();
                        realmResultItem.get(i).getItemModifier().get(j).deleteFromRealm();
                        ((CashRegisterActivity)getActivity()).realm.commitTransaction();
                    }
                }
            }
        }

        if (!isDeleted){
            createModifierGroup(modifier_group_id,realmResultMerchant);
        }
    }

    private void createModifierGroup(String modifier_group_id, RealmResults<Merchant> realmResultMerchant) {
        String modifier_group_name = editTextDialogInventoryModifierGroupName.getText().toString().trim();
        String flag = flagModifierGroup();
        String outlet_id = ((CashRegisterActivity)getActivity()).session.get(SessionManager.KEY_OUTLET_ID);
        ((CashRegisterActivity)getActivity()).RealmCreateDatabaseTableModifierGroup(
                ((CashRegisterActivity)getActivity()).realm,
                (new Random()).nextInt(9999) + 1,
                modifier_group_id,
                modifier_group_name,
                flag,
                outlet_id
        );

        for (int i = 0; i < linkedListViewModifierDetail.size() - 1; i++) {
            if (!linkedListViewModifierDetail.get(i).getEditTextValue().getText().toString().equals("")){
                createModifierDetail(
                        realmResultMerchant,
                        modifier_group_id,
                        linkedListViewModifierDetail.get(i).getEditTextOption().getText().toString().trim(),
                        linkedListViewModifierDetail.get(i).getEditTextValue().getText().toString());
            }else {
                createModifierDetail(
                        realmResultMerchant,
                        modifier_group_id,
                        linkedListViewModifierDetail.get(i).getEditTextOption().getText().toString().trim(),
                        "0");
            }

        }

        updateApplyItem(modifier_group_id);
    }

    private void updateApplyItem(String modifier_group_id) {
        if (linkedListViewCategory.size()>0){
            for (int i = 0; i < linkedListViewCategory.size(); i++) {
                if (linkedListViewCategory.get(i).isCheck()){
                    RealmResults<Item> realmResultItem =
                            ((CashRegisterActivity)getActivity()).realm.where(
                                    com.ingenico.PointOfSale.ModelRealm.Item.class)
                                    .equalTo("itemID",linkedListViewCategory.get(i).getItemID())
                                    .findAll();
                    if (!modifierGroupInItem(realmResultItem.last().getItemModifier(),modifier_group_id)){
                        ((CashRegisterActivity)getActivity()).realm.beginTransaction();
                        RealmList<ItemModifierGroup> realmList = realmResultItem.last().getItemModifier();
                        ItemModifierGroup itemModifierGroup = new ItemModifierGroup();
                        itemModifierGroup.setRealmItemModifierGroupID((new Random()).nextInt(9999) + 1);
                        itemModifierGroup.setItemModifierGroupID(modifier_group_id);
                        realmList.add(itemModifierGroup);
                        ((CashRegisterActivity)getActivity()).realm.commitTransaction();
                    }
                }
            }
        }
    }

    private boolean modifierGroupInItem(RealmList<ItemModifierGroup> itemModifier, String modifier_group_id){
        boolean val = false;
        for (int i = 0; i < itemModifier.size(); i++) {
            if (itemModifier.get(i).getItemModifierGroupID().equals(modifier_group_id)){
                val = true;
            }
        }
        return val;
    }

    private void createModifierDetail(RealmResults<Merchant> realm_result_merchant,
                                      String modifier_group_id,
                                      String modifier_detail_name,
                                      String modifier_detail_price) {
        String modifier_detail_id = createModifierDetailID(realm_result_merchant);
        ((CashRegisterActivity)getActivity()).RealmCreateDatabaseTableModifierDetail(
                ((CashRegisterActivity)getActivity()).realm,
                (new Random()).nextInt(9999) + 1,
                modifier_detail_id,
                modifier_detail_name,
                modifier_detail_price,
                modifier_group_id
        );
    }


    private String flagModifierGroup(){
        if (checkBoxChooseOne.isChecked()){return Declaration.CHOOSE_ONE;
        }else {return Declaration.CHOOSE_MANY;}
    }

    private void changeTotalApplyItem() {
        int total = 0;
        for (int i = 0; i < linkedListViewCategory.size(); i++) {
            if (linkedListViewCategory.get(i).isCheck()){
                total +=1;
            }
        }
        textViewTotalApply.setText(String.valueOf(total));
    }

    private void generateViewApplyModifierToItem(Realm realm) {
        linearLayoutContainerAllItem.removeAllViews();
        linkedListViewCategory.clear();
        RealmResults<Item> realmResultItem =
                realm.where(
                        com.ingenico.PointOfSale.ModelRealm.Item.class)
                        .findAll();
        for (int i = 0; i < realmResultItem.size(); i++) {
            if (realmResultItem.get(i).getItemOutletID().equals(
                    ((CashRegisterActivity)getActivity()).session.get(SessionManager.KEY_OUTLET_ID))
                    || (realmResultItem.get(i).getItemOutletID().equals(Declaration.ALL_OUTLET)))
            {
                if (itemHaveModifierGroup(realmResultItem.get(i).getItemModifier())){
                    addLayoutItem(
                            realmResultItem.get(i).getItemID(),
                            realmResultItem.get(i).getItemName(),
                            Declaration.IMAGE_OUTPUT_PATH + realmResultItem.get(i).getItemID()+".png",
                            realmResultItem.get(i).getItemCategory(),
                            true
                    );
                }else {
                    addLayoutItem(
                            realmResultItem.get(i).getItemID(),
                            realmResultItem.get(i).getItemName(),
                            Declaration.IMAGE_OUTPUT_PATH + realmResultItem.get(i).getItemID()+".png",
                            realmResultItem.get(i).getItemCategory(),
                            false
                    );
                }
            }
        }
    }

    private void addLayoutItem(String item_id,String item_name,String image_url, String category_name,
                               boolean active){
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
        linearLayoutContainerAllItem.addView(linearLayoutItemWithCategory);

        linearLayoutDialogInventoryCategory.getLinearLayoutOnClick().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!linearLayoutDialogInventoryCategory.isCheck()){
                    linearLayoutDialogInventoryCategory.setActiveRadioButton(true);
                }else {
                    linearLayoutDialogInventoryCategory.setActiveRadioButton(false);
                }
            }
        });

        linearLayoutDialogInventoryCategory.getRadioButton().setChecked(active);

        ViewCategory viewCategory = new ViewCategory();
        viewCategory.setLinearLayoutOnClick(linearLayoutDialogInventoryCategory.getLinearLayoutOnClick());
        viewCategory.setRadioButton(linearLayoutDialogInventoryCategory.getRadioButton());
        viewCategory.setTextViewCategory(linearLayoutDialogInventoryCategory.getTextViewCategoryName());
        linkedListViewCategory.add(linearLayoutDialogInventoryCategory);
    }


    private void generateViewModifierDetailList(Realm realm) {
        if (modifierGroup==null){
            addViewModifierDetail("","","","");
        }else {
            //add view modifier detail according modifier detail in database
            RealmResults<com.ingenico.PointOfSale.ModelRealm.ModifierDetail> realmResultModifierDetail =
                    realm.where(com.ingenico.PointOfSale.ModelRealm.ModifierDetail.class)
                            .equalTo("modifierDetailGroupID",modifierGroup.getModifierGroupID())
                            .findAll();
            for (int i = 0; i < realmResultModifierDetail.size(); i++) {
                addViewModifierDetail(
                        realmResultModifierDetail.get(i).getModifierDetailID(),
                        realmResultModifierDetail.get(i).getModifierDetailName(),
                        realmResultModifierDetail.get(i).getModifierDetailPrice(),
                        realmResultModifierDetail.get(i).getModifierDetailGroupID()
                );
            }
            addViewModifierDetail("","","","");
        }
    }

    private void validateView(){
        if (editTextDialogInventoryModifierGroupName.getText().toString().trim().length()>0){
            if (allOptionFilled()){
                linearLayoutModifier.setVisibility(View.GONE);
                linearLayoutApplyItem.setVisibility(View.VISIBLE);
            }else {
                showAlertDialog();
            }
        }else {
            showAlertDialog();
        }
    }

    private boolean allOptionFilled(){
        boolean v = true;
        if (linkedListViewModifierDetail.size()==1){
            v = false;
        }else {
            for (int i = 0; i < linkedListViewModifierDetail.size()-1; i++) {
                v = linkedListViewModifierDetail.get(i).getEditTextOption().getText().toString().trim().length() > 0;
            }
        }
        return v;
    }

    private void generateViewTotalItemApply(Realm realm) {
        if (modifierGroup != null){
            RealmResults<Item> realmResultItem =
                    realm.where(com.ingenico.PointOfSale.ModelRealm.Item.class)
                            .findAll();
            int totalItem = 0;
            for (int i = 0; i < realmResultItem.size(); i++) {
                Log.e("",realmResultItem.get(i).getItemModifier()+"");
                if (itemHaveModifierGroup(realmResultItem.get(i).getItemModifier())){
                    totalItem +=1;
                }
            }
            textViewTotalApply.setText(String.valueOf(totalItem));
        }
    }

    private boolean itemHaveModifierGroup (RealmList<ItemModifierGroup> realm_list){
        boolean val = false;
        for (int i = 0; i < realm_list.size(); i++) {
            if (modifierGroup !=null){
                if (realm_list.get(i).getItemModifierGroupID().equals(modifierGroup.getModifierGroupID())){
                    val = true;
                }
            }
        }
        return val;
    }

    private void generateViewName() {
        if (modifierGroup != null){
            editTextDialogInventoryModifierGroupName.setText(modifierGroup.getModifierGroupName());
        }
    }

    private void addViewModifierDetail(
            String modifier_detail_id ,String modifier_detail_name,
            String modifier_detail_value, String modifier_detail_group_id
    ){
        LinearLayoutDialogInventoryModifierDetail linearLayoutDialogInventoryModifierDetail =
                new LinearLayoutDialogInventoryModifierDetail();

        ModifierDetail modifierDetail = new ModifierDetail();
        modifierDetail.setModifierDetailID(modifier_detail_id);
        modifierDetail.setModifierDetailName(modifier_detail_name);
        modifierDetail.setModifierDetailPrice(modifier_detail_value);
        modifierDetail.setModifierDetailGroupID(modifier_detail_group_id);

        LinearLayout linearLayout = linearLayoutDialogInventoryModifierDetail.LinearLayoutDialogInventoryCategory(
                getActivity(),
                modifierDetail
        );

        linearLayoutContainer.addView(linearLayout);

        ViewModifierDetail viewModifierDetail = new ViewModifierDetail();
        viewModifierDetail.setLinearLayoutParent(linearLayout);
        viewModifierDetail.setEditTextOption(linearLayoutDialogInventoryModifierDetail.getEditTextOption());
        viewModifierDetail.setEditTextValue(linearLayoutDialogInventoryModifierDetail.getEditTextValue());
        viewModifierDetail.setImageViewDelete(linearLayoutDialogInventoryModifierDetail.getImageViewDelete());

        TextWatcher textWatcherModifierDetail = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0 && s.length()<2){
                    if (linkedListViewModifierDetail.get(linkedListViewModifierDetail.size()-1)
                            .getEditTextOption().getText().toString().length() !=0 ){
                        linkedListViewModifierDetail.get(linkedListViewModifierDetail.size()-1)
                                .getImageViewDelete().setVisibility(View.VISIBLE);
                        addViewModifierDetail("","","","");
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        viewModifierDetail.getEditTextOption().addTextChangedListener(textWatcherModifierDetail);
        if (modifier_detail_id.length()>0){
            viewModifierDetail.getImageViewDelete().setVisibility(View.VISIBLE);
        }

        linkedListViewModifierDetail.add(viewModifierDetail);
        listenerModifierDetailListCancel(linkedListViewModifierDetail);


    }

    private void removeModifierDetail(int position){
        linkedListViewModifierDetail.get(position).getLinearLayoutParent().setVisibility(View.GONE);
        linkedListViewModifierDetail.remove(position);
        listenerModifierDetailListCancel(linkedListViewModifierDetail);
    }

    private void listenerModifierDetailListCancel(final LinkedList<ViewModifierDetail> linked_list) {
        for (int i = 0; i < linked_list.size(); i++) {
            final int _i = i;
            linked_list.get(i).getImageViewDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeModifierDetail(_i);
                    if (linked_list.size()<2){
                        addViewModifierDetail("","","","");
                    }
                }
            });
        }
    }

    private void showAlertDialog(){
        ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent(
                getActivity(),
                "Sorry",
                "Please input modifier correctly"
        );
    }

    private String createModifierGroupID(RealmResults<Merchant> realm_result_merchant){
        return realm_result_merchant.get(0).getMerchantID()+
                "_modifier_group_"+
                getMacAddress()+"_"+
                (new Random()).nextInt(9999) + 1;
    }

    private String createModifierDetailID(RealmResults<Merchant> realm_result_merchant){
        return realm_result_merchant.get(0).getMerchantID()+
                "_modifier_"+
                getMacAddress()+"_"+
                (new Random()).nextInt(9999) + 1;
    }


    private String getMacAddress(){
        String macAddress;
        WifiManager manager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        macAddress = info.getMacAddress();
        return macAddress;
    }

}

package com.ingenico.PointOfSale.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ingenico.PointOfSale.Adapter.ListViewAdapterHistory;
import com.ingenico.PointOfSale.Adapter.ListViewAdapterInventoryAllItem;
import com.ingenico.PointOfSale.Adapter.ListViewAdapterInventoryCategories;
import com.ingenico.PointOfSale.Adapter.ListViewAdapterInventoryDiscount;
import com.ingenico.PointOfSale.Adapter.ListViewAdapterInventoryModifier;
import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.AppController;
import com.ingenico.PointOfSale.Controller.Declaration;
import com.ingenico.PointOfSale.Controller.HashPassword;
import com.ingenico.PointOfSale.Controller.SessionManager;
import com.ingenico.PointOfSale.ModelRealm.ItemModifierGroup;
import com.ingenico.PointOfSale.ModelRealm.ItemVariant;
import com.ingenico.PointOfSale.Object.Category;
import com.ingenico.PointOfSale.Object.Discount;
import com.ingenico.PointOfSale.Object.Item;
import com.ingenico.PointOfSale.Object.ModifierDetail;
import com.ingenico.PointOfSale.Object.ModifierGroup;
import com.ingenico.PointOfSale.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 12/27/2016.
 */

public class fInventory extends Fragment {

    private RadioGroup radioGroupInventory;
    private RadioButton radioButtonAllItem, radioButtonCategories,
            radioButtonModifiers, radioButtonDiscount;
    private TextView textViewInventorySync;
    private EditText editTextInventorySearchAllItem, editTextInventorySearchCategories,
            editTextInventorySearchModifier, editTextInventorySearchDiscount,
            editTextPasswordOutlet;
    private ImageView imageViewCancelSearchAllItem, imageViewCancelSearchCategories,
            imageViewCancelSearchModifier, imageViewCancelSearchDiscount;
    private Button buttonCreateInventoryAllItem, buttonCreateInventoryCategories,
            buttonCreateInventoryModifier, buttonCreateInventoryDiscount, buttonEnter;
    private ListView listViewInventoryAllItem, listViewInventoryCategories, listViewInventoryModifier,
            listViewInventoryDiscount;
    private LinearLayout linearLayoutInventoryAllItem, linearLayoutInventoryCategories,
            linearLayoutInventoryModifier, linearLayoutInventoryDiscount,
            linearLayoutInputOutletPassword, linearLayoutFragmentInventory;

    private ListViewAdapterInventoryAllItem listViewAdapterInventoryAllItem;
    private ListViewAdapterInventoryCategories listViewAdapterInventoryCategories;
    private ListViewAdapterInventoryModifier listViewAdapterInventoryModifier;
    private ListViewAdapterInventoryDiscount listViewAdapterInventoryDiscount;

    private LinkedList<Item> linkedListItem;
    private LinkedList<Category> linkedListCategory;
    private LinkedList<ModifierGroup> linkedListModifierGroup;
    private LinkedList<Discount> linkedListDiscount;


    RealmChangeListener realmChangeListenerAllItem;
    RealmResults<com.ingenico.PointOfSale.ModelRealm.Item> realmResultsAllItem;

    RealmChangeListener realmChangeListenerCategory;
    RealmResults<com.ingenico.PointOfSale.ModelRealm.Category> realmResultsCategory;

    RealmChangeListener realmChangeListenerModifier;
    RealmResults<com.ingenico.PointOfSale.ModelRealm.ModifierGroup> realmResultsModifier;

    RealmChangeListener realmChangeListenerDiscount;
    RealmResults<com.ingenico.PointOfSale.ModelRealm.DiscountMaster> realmResultsDiscount;

    public fInventory(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inventory, container, false);

        radioGroupInventory = (RadioGroup)rootView.findViewById(R.id.radioGroupInventory);
        radioButtonAllItem = (RadioButton)rootView.findViewById(R.id.radioButtonAllItem);
        radioButtonCategories = (RadioButton)rootView.findViewById(R.id.radioButtonCategories);
        radioButtonModifiers = (RadioButton)rootView.findViewById(R.id.radioButtonModifiers);
        radioButtonDiscount = (RadioButton)rootView.findViewById(R.id.radioButtonDiscount);

        textViewInventorySync = (TextView)rootView.findViewById(R.id.textViewInventorySync);

        editTextInventorySearchAllItem = (EditText)rootView.findViewById(R.id.editTextInventorySearchAllItem);
        editTextInventorySearchCategories = (EditText)rootView.findViewById(R.id.editTextInventorySearchCategories);
        editTextInventorySearchModifier = (EditText)rootView.findViewById(R.id.editTextInventorySearchModifier);
        editTextInventorySearchDiscount = (EditText)rootView.findViewById(R.id.editTextInventorySearchDiscount);
        editTextPasswordOutlet = (EditText)rootView.findViewById(R.id.editTextPasswordOutlet);


        imageViewCancelSearchAllItem = (ImageView)rootView.findViewById(R.id.imageViewCancelSearchAllItem);
        imageViewCancelSearchCategories = (ImageView)rootView.findViewById(R.id.imageViewCancelSearchCategories);
        imageViewCancelSearchModifier = (ImageView)rootView.findViewById(R.id.imageViewCancelSearchModifier);
        imageViewCancelSearchDiscount = (ImageView)rootView.findViewById(R.id.imageViewCancelSearchDiscount);

        buttonCreateInventoryAllItem = (Button)rootView.findViewById(R.id.buttonCreateInventoryAllItem);
        buttonCreateInventoryCategories = (Button)rootView.findViewById(R.id.buttonCreateInventoryCategories);
        buttonCreateInventoryModifier = (Button)rootView.findViewById(R.id.buttonCreateInventoryModifier);
        buttonCreateInventoryDiscount = (Button)rootView.findViewById(R.id.buttonCreateInventoryDiscount);
        buttonEnter = (Button)rootView.findViewById(R.id.buttonEnter);

        listViewInventoryAllItem = (ListView)rootView.findViewById(R.id.listViewInventoryAllItem);
        listViewInventoryCategories = (ListView)rootView.findViewById(R.id.listViewInventoryCategories);
        listViewInventoryModifier = (ListView)rootView.findViewById(R.id.listViewInventoryModifier);
        listViewInventoryDiscount = (ListView)rootView.findViewById(R.id.listViewInventoryDiscount);

        linearLayoutInventoryAllItem = (LinearLayout)rootView.findViewById(R.id.linearLayoutInventoryAllItem);
        linearLayoutInventoryCategories = (LinearLayout)rootView.findViewById(R.id.linearLayoutInventoryCategories);
        linearLayoutInventoryModifier = (LinearLayout)rootView.findViewById(R.id.linearLayoutInventoryModifier);
        linearLayoutInventoryDiscount = (LinearLayout)rootView.findViewById(R.id.linearLayoutInventoryDiscount);
        linearLayoutInputOutletPassword = (LinearLayout)rootView.findViewById(R.id.linearLayoutInputOutletPassword);
        linearLayoutFragmentInventory = (LinearLayout)rootView.findViewById(R.id.linearLayoutFragmentInventory);

        Realm realm = ((CashRegisterActivity)getActivity()).realm;

        realmResultsAllItem = realm.where(
                com.ingenico.PointOfSale.ModelRealm.Item.class)
                .findAll();
        realmChangeListenerAllItem = new RealmChangeListener() {
            @Override
            public void onChange() {
                try {
                    generateAllItem(((CashRegisterActivity)getActivity()).realm);
                }catch (NullPointerException e){e.printStackTrace();}
            }
        };
        realmResultsAllItem.addChangeListener(realmChangeListenerAllItem);
        generateAllItem(((CashRegisterActivity)getActivity()).realm);

        realmResultsCategory = realm.where(
                com.ingenico.PointOfSale.ModelRealm.Category.class)
                .findAll();
        realmChangeListenerCategory = new RealmChangeListener() {
            @Override
            public void onChange() {
                try {
                    generateCategories(((CashRegisterActivity)getActivity()).realm);
                }catch (NullPointerException e){e.printStackTrace();}
            }
        };
        realmResultsCategory.addChangeListener(realmChangeListenerCategory);
        generateCategories(((CashRegisterActivity)getActivity()).realm);

        realmResultsModifier = realm.where(
                com.ingenico.PointOfSale.ModelRealm.ModifierGroup.class)
                .findAll();
        realmChangeListenerModifier = new RealmChangeListener() {
            @Override
            public void onChange() {
                try {
                    generateModifiers(((CashRegisterActivity)getActivity()).realm);
                }catch (NullPointerException e){e.printStackTrace();}
            }
        };
        realmResultsModifier.addChangeListener(realmChangeListenerModifier);
        generateModifiers(((CashRegisterActivity)getActivity()).realm);

        realmResultsDiscount = realm.where(
                com.ingenico.PointOfSale.ModelRealm.DiscountMaster.class)
                .findAll();
        realmChangeListenerDiscount = new RealmChangeListener() {
            @Override
            public void onChange() {
                try {
                    generateAllDiscount(((CashRegisterActivity)getActivity()).realm);
                }catch (NullPointerException e){e.printStackTrace();}
            }
        };
        realmResultsDiscount.addChangeListener(realmChangeListenerDiscount);
        generateAllDiscount(((CashRegisterActivity)getActivity()).realm);

        editTextInventorySearchAllItem.clearFocus();
        editTextInventorySearchCategories.clearFocus();
        editTextInventorySearchModifier.clearFocus();
        editTextInventorySearchDiscount.clearFocus();

        editTextInventorySearchAllItem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int visibility = (hasFocus) ? View.VISIBLE : View.GONE;
                imageViewCancelSearchAllItem.setVisibility(visibility);
            }
        });

        imageViewCancelSearchAllItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextInventorySearchAllItem.clearFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextInventorySearchAllItem.getWindowToken(), 0);
            }
        });

        editTextInventorySearchCategories.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int visibility = (hasFocus) ? View.VISIBLE : View.GONE;
                imageViewCancelSearchCategories.setVisibility(visibility);
            }
        });

        imageViewCancelSearchCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextInventorySearchCategories.clearFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextInventorySearchCategories.getWindowToken(), 0);
            }
        });

        editTextInventorySearchModifier.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int visibility = (hasFocus) ? View.VISIBLE : View.GONE;
                imageViewCancelSearchModifier.setVisibility(visibility);
            }
        });

        imageViewCancelSearchModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextInventorySearchModifier.clearFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextInventorySearchModifier.getWindowToken(), 0);
            }
        });

        editTextInventorySearchDiscount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int visibility = (hasFocus) ? View.VISIBLE : View.GONE;
                imageViewCancelSearchDiscount.setVisibility(visibility);
            }
        });

        imageViewCancelSearchDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextInventorySearchDiscount.clearFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextInventorySearchDiscount.getWindowToken(), 0);
            }
        });

        radioGroupInventory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonAllItem:
                        linearLayoutInventoryAllItem.setVisibility(View.VISIBLE);
                        linearLayoutInventoryCategories.setVisibility(View.GONE);
                        linearLayoutInventoryModifier.setVisibility(View.GONE);
                        linearLayoutInventoryDiscount.setVisibility(View.GONE);
                        break;
                    case R.id.radioButtonCategories:
                        linearLayoutInventoryAllItem.setVisibility(View.GONE);
                        linearLayoutInventoryCategories.setVisibility(View.VISIBLE);
                        linearLayoutInventoryModifier.setVisibility(View.GONE);
                        linearLayoutInventoryDiscount.setVisibility(View.GONE);
                        break;
                    case R.id.radioButtonModifiers:
                        linearLayoutInventoryAllItem.setVisibility(View.GONE);
                        linearLayoutInventoryCategories.setVisibility(View.GONE);
                        linearLayoutInventoryModifier.setVisibility(View.VISIBLE);
                        linearLayoutInventoryDiscount.setVisibility(View.GONE);
                        break;
                    case R.id.radioButtonDiscount:
                        linearLayoutInventoryAllItem.setVisibility(View.GONE);
                        linearLayoutInventoryCategories.setVisibility(View.GONE);
                        linearLayoutInventoryModifier.setVisibility(View.GONE);
                        linearLayoutInventoryDiscount.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        textViewInventorySync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronizedItem(((CashRegisterActivity)getActivity()).realm);
            }
        });

        buttonCreateInventoryAllItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CashRegisterActivity)getActivity()).dialogInventoryAllItem.setItem(
                        null
                );
                ((CashRegisterActivity)getActivity()).dialogInventoryAllItem.
                        show(getActivity().getFragmentManager(),"show_dialog");
            }
        });

        buttonCreateInventoryCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CashRegisterActivity)getActivity()).dialogInventoryCategory.setCategory(
                        null
                );
                ((CashRegisterActivity)getActivity()).dialogInventoryCategory.
                        show(getActivity().getFragmentManager(),"show_dialog");
            }
        });

        buttonCreateInventoryDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CashRegisterActivity)getActivity()).dialogInventoryDiscount.setDiscount(
                        null
                );
                ((CashRegisterActivity)getActivity()).dialogInventoryDiscount.
                        show(getActivity().getFragmentManager(),"show_dialog");
            }
        });

        buttonCreateInventoryModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CashRegisterActivity)getActivity()).dialogInventoryModifier.setModifierGroup(
                        null
                );
                ((CashRegisterActivity)getActivity()).dialogInventoryModifier.
                        show(getActivity().getFragmentManager(),"show_dialog");
            }
        });

        listViewInventoryAllItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((CashRegisterActivity)getActivity()).dialogInventoryAllItem.setItem(
                        sortedLinkedListItem(linkedListItem).get(position)
                );
                ((CashRegisterActivity)getActivity()).dialogInventoryAllItem.
                        show(getActivity().getFragmentManager(),"show_dialog");
            }
        });

        listViewInventoryCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((CashRegisterActivity)getActivity()).dialogInventoryCategory.setCategory(
                        sortedLinkedListCategory(linkedListCategory).get(position)
                );
                ((CashRegisterActivity)getActivity()).dialogInventoryCategory.
                        show(getActivity().getFragmentManager(),"show_dialog");
            }
        });

        listViewInventoryModifier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((CashRegisterActivity)getActivity()).dialogInventoryModifier.setModifierGroup(
                        sortedLinkedListModifier(linkedListModifierGroup).get(position)
                );
                ((CashRegisterActivity)getActivity()).dialogInventoryModifier.
                        show(getActivity().getFragmentManager(),"show_dialog");
            }
        });

        listViewInventoryDiscount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((CashRegisterActivity)getActivity()).dialogInventoryDiscount.setDiscount(
                        sortedLinkedListDiscount(linkedListDiscount).get(position)
                );
                ((CashRegisterActivity)getActivity()).dialogInventoryDiscount.
                        show(getActivity().getFragmentManager(),"show_dialog");
            }
        });

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatePasswordOutlet();
            }
        });

        return rootView;
    }

    private void validatePasswordOutlet() {
        RealmResults<com.ingenico.PointOfSale.ModelRealm.Login> itemRealmResults =
                ((CashRegisterActivity)getActivity()).realm.where(
                com.ingenico.PointOfSale.ModelRealm.Login.class)
                        .equalTo("userPrivilege","2")
                        .equalTo("outletID",((CashRegisterActivity)getActivity()).session.get(SessionManager.KEY_OUTLET_ID))
                        .findAll();
        if (((HashPassword.sha512(editTextPasswordOutlet.getText().toString())).toUpperCase()).equals(
                itemRealmResults.last().getUserPassword())){
            linearLayoutFragmentInventory.setVisibility(View.VISIBLE);
            linearLayoutInputOutletPassword.setVisibility(View.GONE);
        }else {
            ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent(
                    getActivity(),
                    "Warning",
                    "Your Password was not valid"
            );
        }
    }

    private void synchronizedItem(Realm realm) {
        RealmResults<com.ingenico.PointOfSale.ModelRealm.Item> itemRealmResults = realm.where(
                com.ingenico.PointOfSale.ModelRealm.Item.class)
                .findAll();
        sendData(JsonSync(itemRealmResults));
    }

    private void sendData(final JSONObject jsonObject){
        String tag_json_req = "send_API";
        /**
         * Show progress dialog
         */
        ((CashRegisterActivity)getActivity()).showProgressDialog();

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,
                Declaration.URL_SYNC,  new Response.Listener<String>() {
            /**
             * Response volley from hit API
             */
            @Override
            public void onResponse(String response) {
                ((CashRegisterActivity)getActivity()).logger.addInfo("Response API",response);
/*                try {
                    JSONObject jObj = new JSONObject(response.toString());
                    String code = jObj.getString("code");
                    String msg = jObj.getString("msg");
                    /**
                     * Check code
                     */
/*                    if (code.equals("0007")){
                        Toast.makeText(getActivity(),
                                msg, Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getActivity(),
                                msg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    /**
                     * JSON error
                     */
/*                    e.printStackTrace();
                }
                /**
                 * Hide progress dialog
                 */
                ((CashRegisterActivity)getActivity()).hideProgressDialog();

            }
        }, new Response.ErrorListener() {
            /**
             * Volley response error
             */
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                ((CashRegisterActivity)getActivity()).logger.addInfo("VolleyError API",error.toString());
                /**
                 * Show progress dialog
                 */
                ((CashRegisterActivity)getActivity()).hideProgressDialog();
            }
        }){
            /**
             * Posting parameter in url
             */
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sync", jsonObject.toString());
                ((CashRegisterActivity)getActivity()).logger.addInfo("Parameter in Volley",params.toString());
                return params;
            }

        };


        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                Declaration.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        /**
         * Adding request to request queue
         */
        AppController.getInstance().addToRequestQueue(jsonObjectRequest, tag_json_req);
    }

    private JSONObject JsonSync (RealmResults<com.ingenico.PointOfSale.ModelRealm.Item> realm_results_item){
        JSONObject jsonObjectItemMaster = new JSONObject();
        JSONArray jsonArrayItemDetail = new JSONArray();

        for (int i = 0; i <realm_results_item.size(); i++) {
            com.ingenico.PointOfSale.ModelRealm.Item item = realm_results_item.get(i);

            String image = "";
            File imgFile = new  File(
                    Declaration.IMAGE_OUTPUT_PATH + item.getItemID() +".png"
            );
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                image = encodeImage(stream.toByteArray());
            }

            try {
                JSONObject jsonObjectItem = new JSONObject();
                jsonObjectItem.put(Declaration.ITEM_ID,item.getItemID());
                jsonObjectItem.put(Declaration.ITEM_NAME,item.getItemName());
                jsonObjectItem.put(Declaration.HARGA,item.getItemPrice());
                jsonObjectItem.put(Declaration.DISCOUNT,item.getItemDiscount());
                jsonObjectItem.put(Declaration.PICT,image);
                jsonObjectItem.put(Declaration.ITEM_KATEGORI,item.getItemCategory());
                jsonObjectItem.put(Declaration.STATUS_TAX,item.getItemStatusTax());

                JSONArray jsonArrayVariant = new JSONArray();
                RealmList<ItemVariant> realmListItemVariant = item.getItemVarian();
                for (int j = 0; j < realmListItemVariant.size(); j++) {
                    jsonArrayVariant.put(realmListItemVariant.get(j).getItemVariantID());}
                jsonObjectItem.put(Declaration.VARIAN,jsonArrayVariant);

                JSONArray jsonArrayModifierGroup = new JSONArray();
                RealmList<com.ingenico.PointOfSale.ModelRealm.ItemModifierGroup> realmListItemModifier = item.getItemModifier();
                for (int k = 0; k < realmListItemModifier.size(); k++) {
                    jsonArrayModifierGroup.put(realmListItemModifier.get(k).getItemModifierGroupID());}
                jsonObjectItem.put(Declaration.MODIFIER,jsonArrayModifierGroup);

                jsonObjectItem.put(Declaration.OUTLET_ID,item.getItemOutletID());

                jsonArrayItemDetail.put(jsonObjectItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            jsonObjectItemMaster.put("item_master",jsonArrayItemDetail);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObjectItemMaster;
    }

    private static String encodeImage (byte[] ImageByteArray){
        return Base64.encodeToString(ImageByteArray,Base64.DEFAULT);
    }

    private void generateAllItem(Realm realm){
        linkedListItem = new LinkedList<>();

        RealmResults<com.ingenico.PointOfSale.ModelRealm.Item> itemRealmResults = realm.where(
                com.ingenico.PointOfSale.ModelRealm.Item.class)
                .findAll();
        for (int i = 0; i < itemRealmResults.size(); i++) {
            if (itemRealmResults.get(i).getItemOutletID().equals(
                    ((CashRegisterActivity)getActivity()).session.get(SessionManager.KEY_OUTLET_ID))
                    || itemRealmResults.get(i).getItemOutletID().equals(Declaration.ALL_OUTLET)){
                Item item = new Item();
                item.setItemID(itemRealmResults.get(i).getItemID());
                item.setItemName(itemRealmResults.get(i).getItemName());
                item.setItemPrice(itemRealmResults.get(i).getItemPrice());
                item.setItemDiscount(itemRealmResults.get(i).getItemDiscount());
                item.setItemPicture(itemRealmResults.get(i).getItemPicture());
                item.setItemCategory(itemRealmResults.get(i).getItemCategory());
                item.setItemStatusTax(itemRealmResults.get(i).getItemStatusTax());

                RealmList<ItemVariant> realmListItemVariant = itemRealmResults.get(i).getItemVarian();
                LinkedList<String> linkedListVariant = new LinkedList<>();
                for (int j = 0; j < realmListItemVariant.size(); j++) {
                    linkedListVariant.add(realmListItemVariant.get(j).getItemVariantID());}
                item.setItemVarian(linkedListVariant);

                RealmList<ItemModifierGroup> realmListItemModifierGroup = itemRealmResults.get(i).getItemModifier();
                LinkedList<String> linkedListModifierGroup = new LinkedList<>();
                for (int k = 0; k < realmListItemModifierGroup.size(); k++) {
                    linkedListModifierGroup.add(realmListItemModifierGroup.get(k).getItemModifierGroupID());}
                item.setItemModifier(linkedListModifierGroup);
                linkedListItem.add(item);
            }
        }


        listViewAdapterInventoryAllItem = new ListViewAdapterInventoryAllItem(
                getActivity(),
                sortedLinkedListItem(linkedListItem)
        );
        listViewInventoryAllItem.setAdapter(listViewAdapterInventoryAllItem);

        listViewAdapterInventoryAllItem.notifyDataSetChanged();

    }

    private void generateCategories(Realm realm){
        linkedListCategory = new LinkedList<>();

        RealmResults<com.ingenico.PointOfSale.ModelRealm.Category> realmResultCategory = realm.where(
                com.ingenico.PointOfSale.ModelRealm.Category.class)
                .findAll();

        for (int i = 0; i <realmResultCategory.size() ; i++) {
            Category category = new Category();
            if (!realmResultCategory.get(i).getCategory().equals("")){
                category.setCategory(realmResultCategory.get(i).getCategory());
                linkedListCategory.add(category);
            }
        }

        listViewAdapterInventoryCategories = new ListViewAdapterInventoryCategories(
                getActivity(),
                sortedLinkedListCategory(linkedListCategory)
        );
        listViewInventoryCategories.setAdapter(listViewAdapterInventoryCategories);

    }

    private void generateModifiers(Realm realm){
        linkedListModifierGroup = new LinkedList<>();

        RealmResults<com.ingenico.PointOfSale.ModelRealm.ModifierGroup> realmResultModifierGroup = realm.where(
                com.ingenico.PointOfSale.ModelRealm.ModifierGroup.class)
                .findAll();
        for (int i = 0; i < realmResultModifierGroup.size(); i++) {
            if (realmResultModifierGroup.get(i).getModifierGroupOutletID().equals(
                    ((CashRegisterActivity)getActivity()).session.get(SessionManager.KEY_OUTLET_ID))
                    || realmResultModifierGroup.get(i).getModifierGroupOutletID().equals(Declaration.ALL_OUTLET)){
                ModifierGroup modifierGroup = new ModifierGroup();
                modifierGroup.setModifierGroupID(realmResultModifierGroup.get(i).getModifierGroupID());
                modifierGroup.setModifierGroupName(realmResultModifierGroup.get(i).getModifierGroupName());
                modifierGroup.setModifierGroupOptionFlag(realmResultModifierGroup.get(i).getModifierGroupOptionFlag());
                modifierGroup.setModifierGroupOutletID(realmResultModifierGroup.get(i).getModifierGroupOutletID());

                LinkedList<ModifierDetail> linkedListModifierDetail = new LinkedList<>();
                RealmResults<com.ingenico.PointOfSale.ModelRealm.ModifierDetail> realmResultModifierDetail = realm.where(
                        com.ingenico.PointOfSale.ModelRealm.ModifierDetail.class)
                        .equalTo(
                                "modifierDetailGroupID",
                                realmResultModifierGroup.get(i).getModifierGroupID())
                        .findAll();
                for (int j = 0; j < realmResultModifierDetail.size(); j++) {
                    ModifierDetail modifierDetail = new ModifierDetail();
                    modifierDetail.setModifierDetailID(realmResultModifierDetail.get(j).getModifierDetailID());
                    modifierDetail.setModifierDetailName(realmResultModifierDetail.get(j).getModifierDetailName());
                    modifierDetail.setModifierDetailPrice(realmResultModifierDetail.get(j).getModifierDetailPrice());
                    modifierDetail.setModifierDetailGroupID(realmResultModifierDetail.get(j).getModifierDetailGroupID());
                    linkedListModifierDetail.add(modifierDetail);
                }
                modifierGroup.setModifierDetail(linkedListModifierDetail);
                linkedListModifierGroup.add(modifierGroup);
            }
        }

        listViewAdapterInventoryModifier = new ListViewAdapterInventoryModifier(
                getActivity(),
                sortedLinkedListModifier(linkedListModifierGroup)
        );
        listViewInventoryModifier.setAdapter(listViewAdapterInventoryModifier);
    }

    private void generateAllDiscount(Realm realm){
        linkedListDiscount = new LinkedList<>();

        RealmResults<com.ingenico.PointOfSale.ModelRealm.DiscountMaster> realmResultDiscount = realm.where(
                com.ingenico.PointOfSale.ModelRealm.DiscountMaster.class)
                .findAll();

        for (int i = 0; i < realmResultDiscount.size(); i++) {
            if (realmResultDiscount.get(i).getDiscountMasterOutletID().equals(
                    ((CashRegisterActivity)getActivity()).session.get(SessionManager.KEY_OUTLET_ID))
                    || realmResultDiscount.get(i).getDiscountMasterOutletID().equals(Declaration.ALL_OUTLET)){
                Discount discount = new Discount();
                discount.setDiscountMasterID(realmResultDiscount.get(i).getDiscountMasterID());
                discount.setDiscountMasterName(realmResultDiscount.get(i).getDiscountMasterName());
                discount.setDiscountMasterOutletID(realmResultDiscount.get(i).getDiscountMasterOutletID());
                discount.setDiscountMasterType(realmResultDiscount.get(i).getDiscountMasterType());
                discount.setDiscountMasterValue(realmResultDiscount.get(i).getDiscountMasterValue());
                linkedListDiscount.add(discount);
            }
        }

        listViewAdapterInventoryDiscount = new ListViewAdapterInventoryDiscount(
                getActivity(),
                sortedLinkedListDiscount(linkedListDiscount)
        );
        listViewInventoryDiscount.setAdapter(listViewAdapterInventoryDiscount);

    }

    private LinkedList<Item> sortedLinkedListItem(LinkedList<Item> linked_list){
        boolean flag = true;
        Item itemTemp;
        while(flag){
            flag = false;
            for (int i = 0; i < linked_list.size() - 1; i++) {
                if ((linked_list.get(i).getItemName())
                        .compareToIgnoreCase(
                                (linked_list.get(i+1).getItemName())
                        ) > 0){
                    itemTemp = linked_list.get(i);
                    linked_list.set(i,linked_list.get(i+1));
                    linked_list.set(i+1,itemTemp);
                    flag = true;
                }
            }
        }
        return linked_list;
    }

    private LinkedList<Category> sortedLinkedListCategory(LinkedList<Category> linked_list){
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

    private LinkedList<ModifierGroup> sortedLinkedListModifier(LinkedList<ModifierGroup> linked_list){
        boolean flag = true;
        ModifierGroup item;
        while(flag){
            flag = false;
            for (int i = 0; i < linked_list.size() - 1; i++) {
                if ((linked_list.get(i).getModifierGroupName())
                        .compareToIgnoreCase(
                                (linked_list.get(i+1).getModifierGroupName())
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

    private LinkedList<Discount> sortedLinkedListDiscount (LinkedList<Discount> linked_list){
        boolean flag = true;
        Discount item;
        while(flag){
            flag = false;
            for (int i = 0; i < linked_list.size() - 1; i++) {
                if ((linked_list.get(i).getDiscountMasterName())
                        .compareToIgnoreCase(
                                (linked_list.get(i+1).getDiscountMasterName())
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

}

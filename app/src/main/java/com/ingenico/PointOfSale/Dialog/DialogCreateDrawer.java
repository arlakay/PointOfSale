package com.ingenico.PointOfSale.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ingenico.PointOfSale.ModelRealm.Drawer;
import com.ingenico.PointOfSale.R;
import com.ingenico.PointOfSale.CashRegisterActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Administrator-Handy on 7/26/2016.
 */
public class DialogCreateDrawer extends DialogFragment {

    private EditText editTextDialogStartingCash;
    private Button buttonDialogStartDrawer;

    private long drawer_id = 0;
    public void setDrawerID(long id){this.drawer_id = id;}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View RootView=inflater.inflate(R.layout.dialog_create_drawer, null);


        editTextDialogStartingCash = (EditText)RootView.findViewById(R.id.editTextDialogStartingCash);
        buttonDialogStartDrawer = (Button)RootView.findViewById(R.id.buttonDialogStartDrawer);

//        editTextDialogStartingCash.addTextChangedListener(
//                ((CashRegisterActivity)getActivity()).textWatcherKilos(editTextDialogStartingCash)
//        );


        buttonDialogStartDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextDialogStartingCash.getText().toString().equals("")){
                    String startingCash = String.valueOf(Integer.parseInt(
//                            ((CashRegisterActivity)getActivity()).revertAfterTextWatcher(
                                    editTextDialogStartingCash.getText().toString()
//                            )
                    ));
                    if (startingCash.length() != 0
                            && !startingCash.equals("0")){
                        updateDrawerData(
                                ((CashRegisterActivity)getActivity()).realm,
                                drawer_id,
                                startingCash
                        );
                        getDialog().dismiss();
                    }else {
                        Toast.makeText(getActivity(), "Please input amount correctly", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), "Please input amount correctly", Toast.LENGTH_SHORT).show();
                }

            }
        });



        setCancelable(false);
        builder.setView(RootView);
        return builder.create();
    }

    private void updateDrawerData (Realm realm, long drawer_id, String starting_cast){
        RealmResults<Drawer> realmResultsDrawer = realm
                .where(Drawer.class)
                .equalTo("drawerID",drawer_id)
                .findAll();
        realm.beginTransaction();

        realmResultsDrawer.get(0).setDrawerStartingDateAndTime(dateAndTime());
        realmResultsDrawer.get(0).setDrawerStartingCash(starting_cast);
        realmResultsDrawer.get(0).setDrawerCashSales("0");
        realmResultsDrawer.get(0).setDrawerCardSales("0");
        realmResultsDrawer.get(0).setDrawerExpetationCash(starting_cast);
        //realmResultsDrawer.get(0).setDrawerActualCash(starting_cast);

        realm.commitTransaction();

        ((CashRegisterActivity)getActivity()).sessionManager.setKeyIsDrawerLoggedin(true);
    }

    private String dateAndTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

}

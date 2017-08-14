package com.ingenico.PointOfSale.Fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.Permissions;
import com.ingenico.PointOfSale.R;
import com.ingenico.PointOfSale.WelcomeActivity;

import java.io.File;
import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static android.view.View.GONE;

/**
 * Created by Administrator-Handy on 11/3/2016.
 */

public class fSetting extends Fragment {

    private static final String TAG = fSetting.class.getSimpleName();

    private TextView textViewSettingConnectToICMP, txtBackup;
    private LinearLayout linearLayoutConnectToICMP;
    private RadioGroup radioGroupSetting;
    private CardView cardICMP, cardBackup;
    private String settingCategory;

    public fSetting(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_setting, container, false);

        linearLayoutConnectToICMP = (LinearLayout)rootView.findViewById(R.id.linearLayoutConnectToICMP);
        textViewSettingConnectToICMP = (TextView)rootView.findViewById(R.id.textViewSettingConnectToICMP);
        txtBackup = (TextView)rootView.findViewById(R.id.txt_backup_db);
        radioGroupSetting = (RadioGroup)rootView.findViewById(R.id.radioGroupSetting);
        cardICMP = (CardView)rootView.findViewById(R.id.cardViewSetting);
        cardBackup = (CardView)rootView.findViewById(R.id.cardViewBackup);

        radioGroupSetting.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                settingCategory = ((RadioButton)rootView.findViewById(radioGroupSetting.getCheckedRadioButtonId())).getText().toString();
                switch (settingCategory){
                    case "ICMP":{
                        cardICMP.setVisibility(View.VISIBLE);
                        cardBackup.setVisibility(GONE);
                        break;
                    }case "Backup DB":{
                        cardICMP.setVisibility(View.GONE);
                        cardBackup.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        });

        textViewSettingConnectToICMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(((CashRegisterActivity)getActivity()), WelcomeActivity.class));
            }
        });

        txtBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "udah di klik cuk !");
                if (Permissions.get().isMarshmallow()) {
                    checkPermission();
                } else {
                    exportRealmFile();
                }
            }
        });

        return rootView;
    }


    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        switch (requestCode) {
            case 100: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    exportRealmFile();
                } else {
                    Toast.makeText(getActivity(), "Permission denied!", Toast.LENGTH_SHORT).show();
                }
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void checkPermission() {
        final String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        if (!Permissions.get().hasSelfPermissions(getActivity(), permissions)) {
            requestPermissions(permissions, 100);
        } else {
            exportRealmFile();
        }
    }

    public void exportRealmFile() {
        RealmConfiguration realmConfig;
        realmConfig = new RealmConfiguration.Builder(getActivity()).build();

        final Realm realm = Realm.getInstance(realmConfig);

        try {
            final File file = new File(Environment.getExternalStorageDirectory().getPath().concat("/sample.realm"));
            if (file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.delete();
            }

            realm.writeCopyTo(file);
            Toast.makeText(getActivity(), "Success export realm file", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            realm.close();
            e.printStackTrace();
        }
    }

}

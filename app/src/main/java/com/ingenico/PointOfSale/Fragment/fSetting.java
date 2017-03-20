package com.ingenico.PointOfSale.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.R;
import com.ingenico.PointOfSale.WelcomeActivity;

/**
 * Created by Administrator-Handy on 11/3/2016.
 */

public class fSetting extends Fragment {

    private TextView textViewSettingConnectToICMP;
    private LinearLayout linearLayoutConnectToICMP;

    public fSetting(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        linearLayoutConnectToICMP = (LinearLayout)rootView.findViewById(R.id.linearLayoutConnectToICMP);
        textViewSettingConnectToICMP = (TextView)rootView.findViewById(R.id.textViewSettingConnectToICMP);

        textViewSettingConnectToICMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(((CashRegisterActivity)getActivity()), WelcomeActivity.class));
            }
        });


        return rootView;
    }
}

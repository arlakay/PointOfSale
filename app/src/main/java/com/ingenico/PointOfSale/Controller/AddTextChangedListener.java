package com.ingenico.PointOfSale.Controller;

/**
 * Created by charles on 14/09/2015.
 */

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;

public class AddTextChangedListener implements TextWatcher {

    private DecimalFormat df;
    private DecimalFormat dfnd;
    private boolean hasFractionalPart;

    private EditText editText;

    public AddTextChangedListener(EditText et)
    {
        df = new DecimalFormat("#,###.##");
        df.setDecimalSeparatorAlwaysShown(true);
        dfnd = new DecimalFormat("#,###");
        this.editText = et;
        hasFractionalPart = false;
    }

    @SuppressWarnings("unused")
    private static final String TAG = "AddTextChangedListener";

    @Override
    public void afterTextChanged(Editable s)
    {
        editText.removeTextChangedListener(this);

        try {
            int inilen, endlen;
            inilen = editText.getText().length();

            String v = s.toString().replace(String.valueOf(df.getDecimalFormatSymbols().getGroupingSeparator()), "");
            Number n = df.parse(v);
            int cp = editText.getSelectionStart();
            if (hasFractionalPart) {
                editText.setText(df.format(n));
            } else {
                editText.setText(dfnd.format(n));
            }
            endlen = editText.getText().length();
            int sel = (cp + (endlen - inilen));
            if (sel > 0 && sel <= editText.getText().length()) {
                editText.setSelection(sel);
            } else {
                // place cursor at the end?
                editText.setSelection(editText.getText().length() - 1);
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        if (s.toString().contains(String.valueOf(df.getDecimalFormatSymbols().getDecimalSeparator())))
        {
            hasFractionalPart = true;
        } else {
            hasFractionalPart = false;
        }
    }

}
package com.ingenico.PointOfSale.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ingenico.PointOfSale.CashRegisterActivity;
import com.ingenico.PointOfSale.Controller.AddTextChangedListener;
import com.ingenico.PointOfSale.LinearLayout.LinearLayoutHistoryItemList;
import com.ingenico.PointOfSale.R;

/**
 * Created by Administrator-Handy on 10/13/2016.
 */

public class fOtherMenu extends Fragment {
    public fOtherMenu(){}


    private EditText editTextOtherMenu, textViewOtherMenuPrice;
    private TextView textViewInput1, textViewInput2, textViewInput3, textViewInput4
            ,textViewInput5,textViewInput6,textViewInput7,textViewInput8,textViewInput9,textViewInput00
            ,textViewInput0, textViewOtherMenuQuantity, textViewOtherMenuMinus, textViewOtherMenuPlus;
    private LinearLayout linearLayoutInputDelete, linearLayoutInputEnter;
    private Button buttonOtherMenuInput;
    private String amount = "";
    private int intTotalItem = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_other_menu, container, false);
        /**
         * Declare in xml
         */
        editTextOtherMenu = (EditText)rootView.findViewById(R.id.editTextOtherMenu);
        textViewOtherMenuPrice = (EditText) rootView.findViewById(R.id.textViewOtherMenuPrice);
        textViewInput1 = (TextView) rootView.findViewById(R.id.textViewInput1);
        textViewInput2 = (TextView) rootView.findViewById(R.id.textViewInput2);
        textViewInput3 = (TextView) rootView.findViewById(R.id.textViewInput3);
        textViewInput4 = (TextView) rootView.findViewById(R.id.textViewInput4);
        textViewInput5 = (TextView) rootView.findViewById(R.id.textViewInput5);
        textViewInput6 = (TextView) rootView.findViewById(R.id.textViewInput6);
        textViewInput7 = (TextView) rootView.findViewById(R.id.textViewInput7);
        textViewInput8 = (TextView) rootView.findViewById(R.id.textViewInput8);
        textViewInput9 = (TextView) rootView.findViewById(R.id.textViewInput9);
        textViewInput00 = (TextView) rootView.findViewById(R.id.textViewInput00);
        textViewInput0 = (TextView) rootView.findViewById(R.id.textViewInput0);
        textViewOtherMenuQuantity = (TextView) rootView.findViewById(R.id.textViewOtherMenuQuantity);
        textViewOtherMenuMinus = (TextView) rootView.findViewById(R.id.textViewOtherMenuMinus);
        textViewOtherMenuPlus = (TextView) rootView.findViewById(R.id.textViewOtherMenuPlus);
        linearLayoutInputDelete = (LinearLayout) rootView.findViewById(R.id.linearLayoutInputDelete);
        linearLayoutInputEnter = (LinearLayout) rootView.findViewById(R.id.linearLayoutInputEnter);
        buttonOtherMenuInput = (Button) rootView.findViewById(R.id.buttonOtherMenuInput);

        textViewOtherMenuPrice.addTextChangedListener(new AddTextChangedListener(textViewOtherMenuPrice));

        textViewInput1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.length() <= 9){
                    amount+="1";
                    textViewOtherMenuPrice.setText(((CashRegisterActivity)getActivity()).convertValuePattern(amount));
                } else {
                    ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent((getActivity()),"Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.length() <= 9){
                    amount+="2";
                    textViewOtherMenuPrice.setText(((CashRegisterActivity)getActivity()).convertValuePattern(amount));
                } else {
                    ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent((getActivity()),"Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.length() <= 9){
                    amount+="3";
                    textViewOtherMenuPrice.setText(((CashRegisterActivity)getActivity()).convertValuePattern(amount));
                } else {
                    ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent((getActivity()),"Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.length() <= 9){
                    amount+="4";
                    textViewOtherMenuPrice.setText(((CashRegisterActivity)getActivity()).convertValuePattern(amount));
                } else {
                    ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent((getActivity()),"Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.length() <= 9){
                    amount+="5";
                    textViewOtherMenuPrice.setText(((CashRegisterActivity)getActivity()).convertValuePattern(amount));
                } else {
                    ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent((getActivity()),"Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.length() <= 9){
                    amount+="6";
                    textViewOtherMenuPrice.setText(((CashRegisterActivity)getActivity()).convertValuePattern(amount));
                } else {
                    ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent((getActivity()),"Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.length() <= 9){
                    amount+="7";
                    textViewOtherMenuPrice.setText(((CashRegisterActivity)getActivity()).convertValuePattern(amount));
                } else {
                    ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent((getActivity()),"Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.length() <= 9){
                    amount+="8";
                    textViewOtherMenuPrice.setText(((CashRegisterActivity)getActivity()).convertValuePattern(amount));
                } else {
                    ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent((getActivity()),"Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.length() <= 9){
                    amount+="9";
                    textViewOtherMenuPrice.setText(((CashRegisterActivity)getActivity()).convertValuePattern(String.valueOf(Integer.parseInt(amount))));
                } else {
                    ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent((getActivity()),"Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.length() <= 9){
                    if (amount.length() != 0){
                        amount+="0";
                        textViewOtherMenuPrice.setText(((CashRegisterActivity)getActivity()).convertValuePattern(String.valueOf(Integer.parseInt(amount))));
                    }
                } else {
                    ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent((getActivity()),"Sorry", "Can't Count again.");
                }
            }
        });

        textViewInput00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.length() <= 9){
                    if (amount.length() != 0){
                        amount+="00";
                        textViewOtherMenuPrice.setText(((CashRegisterActivity)getActivity()).convertValuePattern(String.valueOf(Integer.parseInt(amount))));
                    }
                } else {
                    ((CashRegisterActivity)getActivity()).showAlertDialogNullEvent((getActivity()),"Sorry", "Can't Count again.");
                }
            }
        });

        linearLayoutInputDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = removeLastCharacter(amount);
                textViewOtherMenuPrice.setText(((CashRegisterActivity)getActivity()).convertValuePattern(amount));
            }
        });

        linearLayoutInputEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(editTextOtherMenu)){
                    Toast.makeText(getActivity(), "Please input name of order..", Toast.LENGTH_SHORT).show();
                }else {
                    if (Integer.valueOf(((CashRegisterActivity)getActivity()).revertValuePattern(textViewOtherMenuPrice.getText().toString())) == 0){
                        Toast.makeText(getActivity(), "Please input amount correcly..", Toast.LENGTH_SHORT).show();
                    }else {
                        ((CashRegisterActivity)getActivity()).addCartFromOtherMenu(
                                editTextOtherMenu.getText().toString(),
                                ((CashRegisterActivity)getActivity()).revertValuePattern(textViewOtherMenuPrice.getText().toString()),
                                textViewOtherMenuQuantity.getText().toString()
                        );
                        editTextOtherMenu.setText("");
                        textViewOtherMenuPrice.setText("");
                        intTotalItem = 1;
                        textViewOtherMenuQuantity.setText("1");
                        amount = "";
                    }
                }
            }
        });

        buttonOtherMenuInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(editTextOtherMenu)){
                    Toast.makeText(getActivity(), "Please input name of order..", Toast.LENGTH_SHORT).show();
                }else {
                    if (((CashRegisterActivity)getActivity()).revertValuePattern(textViewOtherMenuPrice.getText().toString()).equals("")){
                        Toast.makeText(getActivity(), "Please input amount correcly..", Toast.LENGTH_SHORT).show();
                    }else {
                        if (Integer.valueOf(((CashRegisterActivity)getActivity()).revertValuePattern(textViewOtherMenuPrice.getText().toString())) == 0){
                            Toast.makeText(getActivity(), "Please input amount correcly..", Toast.LENGTH_SHORT).show();
                        }else {
                            ((CashRegisterActivity)getActivity()).addCartFromOtherMenu(
                                    editTextOtherMenu.getText().toString(),
                                    ((CashRegisterActivity)getActivity()).revertValuePattern(textViewOtherMenuPrice.getText().toString()),
                                    textViewOtherMenuQuantity.getText().toString()
                            );
                            editTextOtherMenu.setText("");
                            textViewOtherMenuPrice.setText("");
                            intTotalItem = 1;
                            textViewOtherMenuQuantity.setText("1");
                            amount = "";
                        }
                    }
                }
            }
        });


        /**
         * handle add and minus item in view
         */
        textViewOtherMenuPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intTotalItem +=1;
                textViewOtherMenuQuantity.setText(String.valueOf(intTotalItem));
            }
        });

        textViewOtherMenuMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intTotalItem>1){
                    intTotalItem -=1;
                    textViewOtherMenuQuantity.setText(String.valueOf(intTotalItem));
                }
            }
        });

        editTextOtherMenu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextOtherMenu.getText().toString().length() != 0){
                    buttonOtherMenuInput.setEnabled(true);
                }else {buttonOtherMenuInput.setEnabled(false);}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return rootView;
    }

    /**
     * Erase last character in layout
     */
    private String removeLastCharacter (String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

}

package com.example.myaccountreg;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class AccountRegistration extends LinearLayout {
    private ArrayList<Row_Type> selectedFields;
    private TextView textView;

    private Button registerButton;
    private Row row;

    public AccountRegistration(Context context) {
        super(context);
        init();
    }

    public AccountRegistration(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AccountRegistration(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AccountRegistration(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        textView = new TextView(getContext());
        registerButton = new Button(getContext());
        selectedFields = new ArrayList<>();
    }

    private void onRegisterButtonClick() {
    }

    public void addField(String fieldName) {
        Row_Type rowType = getRowTypeFromFieldName(fieldName);
        if (rowType != null) {
            selectedFields.add(rowType);
            updateFields();
        } else {
            Log.d("AccountRegistration", "Invalid field type: " + fieldName);
        }
    }

    public void removeField(String fieldType) {
        Row_Type rowType = getRowTypeFromFieldName(fieldType);
        if (rowType != null) {
            selectedFields.remove(rowType);
            updateFields();
        } else {
            Log.d("Wrong fieldType", "removeField");
        }
    }

    public void updateFields() {
        removeAllViews();
        updateTextViewContent();
        for (Row_Type fieldType : selectedFields) {
            row = new Row(getContext());
            row.setRowType(fieldType);
            addView(row);
        }
        updateButtonViewContent();

    }
    private void updateButtonViewContent(){
        registerButton.setText("Register");
        registerButton.setOnClickListener(v -> onRegisterButtonClick());
        addView(registerButton);
    }
    private void updateTextViewContent() {
        String textContent = "Register Account";
        textView.setTextSize(18);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setGravity(Gravity.CENTER);
        textView.setText(textContent);
        addView(textView);
    }

    private Row_Type getRowTypeFromFieldName(String fieldType) {
        switch (fieldType.toLowerCase()) {
            case "password":
                return Row_Type.PASSWORD;
            case "email":
                return Row_Type.EMAIL;
            case "firstname":
                return Row_Type.FIRSTNAME;
            case "lastname":
                return Row_Type.LASTNAME;
            case "username":
                return Row_Type.USERNAME;
            case "age":
                return Row_Type.AGE;
            case "phonenumber":
                return Row_Type.PHONENUMBER;
            case "custom":
                return Row_Type.CUSTOM;
            default:
                Log.wtf("Wrong field type", "FieldType doesn't exist");
                break;
        }
        return null;
    }
}

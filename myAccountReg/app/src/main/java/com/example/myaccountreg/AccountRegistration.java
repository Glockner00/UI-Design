package com.example.myaccountreg;
import android.content.Context;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class AccountRegistration extends LinearLayout {
    private LinkedHashMap<String, Row> fields;
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
        fields = new LinkedHashMap<>();
    }

    private void onRegisterButtonClick() {

    }

    public void customizeInputType(String fieldName, int inputType){
        if(getField(fieldName)!=null){
            getField(fieldName).setInputType(inputType);
        }else{
            Log.d("AccountRegistration", "Row not found for field: " + fieldName);
        }

    }

    public void customizeBaseAppearance(String fieldName, int textSize, int textColor, String hint) {
        if (getField(fieldName) != null) {
            getField(fieldName).customizeBaseAppearance(textSize, textColor, hint);
        } else {
            Log.d("AccountRegistration", "Row not found for field: " + fieldName);
        }
    }

    private Row getField(String fieldName){
        return fields.get(fieldName);
    }

    public void addField(String name, RowType rowType){
        row = new Row(getContext());
        if(rowType!=null){
            row.setRowName(name);
            row.setRowType(rowType);
            fields.put(name,row);
            updateFields();
        }
    }
    public void removeField(String name) {
        if (!name.isEmpty()) {
            fields.remove(name);
            updateFields();
        } else {
            Log.d("Wrong fieldType", "removeField");
        }
    }
    private void updateFields() {
        removeAllViews();
        updateTextViewContent();
        for (Map.Entry<String, Row>entry : fields.entrySet()) {
            EditText field = entry.getValue().makeRow(entry.getValue().getRowType());
            addView(field);
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
}
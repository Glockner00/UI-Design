package com.example.myaccountreg;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * PASSWORD STRENGTH METER
 * ärven en layout som har två subkomponenter en edittext och en bar. finns färdiga i android.
 * validerar lösenordet, någon slag default logik
 * ska kunna implemtera egen logik
 * too weak.. osv
 * färger, storlek
 * lägga till antal nivåer, alltså påverka gränssnittet.
 */

public class AccountRegistration extends LinearLayout {
    private LinkedHashMap<String, Row> fields;
    private TextView textView;
    private Button registerButton;
    private Row row;
    private RegistrationValidator registrationValidator;
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
        registerButton.setOnClickListener(v -> onRegisterButtonClick());
        textView.setTextSize(30);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setGravity(Gravity.CENTER);
        registrationValidator = new DefaultRegistrationValidator();
    }

    private void onRegisterButtonClick() {
        Registration registration = createRegistration();
        if(registrationValidator.validate(registration)){
            Log.d("AccountRegistration", "Validation succeeded");
        }else{
            Log.d("AccountRegistration", "Validation failed");
        }
        reset();
    }

    public void setRegistrationValidator(RegistrationValidator validator) {
        this.registrationValidator = validator;
    }

    private Registration createRegistration() {
        Registration registration = new Registration();
        List<Row> rows = new ArrayList<>();
        for(Map.Entry<String, Row>entry : fields.entrySet()){
            Row field = entry.getValue();
            rows.add(field);
        }
        registration.setRows(rows);
        return registration;
    }

    public void reset(){
        for(Map.Entry<String, Row>entry : fields.entrySet()){
            Row field = entry.getValue();
            field.setText("");
        }
    }

    public void updateBaseAppearance(String fieldName, int textSize, int textColor, String hint, int inputType) {
        if (getField(fieldName) != null) {
            getField(fieldName).customizeBaseAppearance(textSize, textColor, hint, inputType);
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
        addView(registerButton);
    }
    private void updateTextViewContent() {
        String textContent = "Register Account";
        textView.setText(textContent);
        addView(textView);
    }
}
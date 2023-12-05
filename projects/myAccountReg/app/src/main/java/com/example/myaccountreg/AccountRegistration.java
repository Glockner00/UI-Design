package com.example.myaccountreg;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
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
 * A custom component for creating a registration application.
 * Customizable rows/fields, appearance and validation logic.
 */

public class AccountRegistration extends LinearLayout {
    private LinkedHashMap<String, Row> fields;
    private TextView textView;
    private Button registerButton;

    private List<Row> reg;

    private Registration registration;
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

    /**
     * Initializing all necessary data structures, and constants.
     */
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

    /**
     * Returns the view of a row.
     * @param fieldName
     * @return view
     */
    public View getRowView(String fieldName){
        if(fields.get(fieldName)!=null){
            return fields.get(fieldName).getRowView();
        }else {
            Log.d("getRowView", "Row doesn't exist");
            return null;
        }
    }

    /**
     * Validates a registration and resets all rows.
     */
    private void onRegisterButtonClick() {
        registration = createRegistration();
        if(registrationValidator.validate(registration)){
            setData(registration.getRows());
            Log.d("AccountRegistration", "Validation succeeded");
            reset();
        }else{
            Log.d("AccountRegistration", "Validation failed");
        }

    }

    /**
     * Creates an object registration with that has all rows/fields.
     * @return registration
     */
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

    /**
     * Clear text in all rows.
     */
    private void reset(){
        for(Map.Entry<String, Row>entry : fields.entrySet()){
            Row field = entry.getValue();
            field.setText("");
        }
    }

    /**
     * Update or set a row/fields apperance.
     * @param fieldName
     * @param textSize
     * @param textColor
     * @param hint
     * @param inputType
     */
    public void updateBaseAppearance(String fieldName, int textSize, int textColor, String hint, int inputType) {
        if (getField(fieldName) != null) {
            getField(fieldName).customizeBaseAppearance(textSize, textColor, hint, inputType);
        } else {
            Log.d("AccountRegistration", "Row not found for field: " + fieldName);
        }
    }

    /**
     * Adding a field the datastructure containing all rows/fields.
     * @param name
     * @param rowType
     */
    public void addField(String name, RowType rowType){
        row = new Row(getContext());
        if(rowType!=null){
            row.setRowName(name);
            row.setRowType(rowType);
            fields.put(name,row);
            updateFields();
        }
    }

    /**
     * Remove a field from the datastructure containing all row/fields.
     * @param name
     */
    public void removeField(String name) {
        if (!name.isEmpty()) {
            fields.remove(name);
            updateFields();
        } else {
            Log.d("Wrong fieldType", "removeField");
        }
    }

    /**
     * Uppdate the view. Clear the view and re-add all text, rows/fields, buttons.
     */
    private void updateFields() {
        removeAllViews();
        updateTextViewContent();
        for (Map.Entry<String, Row>entry : fields.entrySet()) {
            Row row = entry.getValue();
            EditText field = row.makeRow(entry.getValue().getRowType());
            row.setRowView(field);
            addView(field);
        }
        updateButtonViewContent();
    }

    /**
     * Adding view for a button.
     */
    private void updateButtonViewContent(){
        registerButton.setText("Register");
        addView(registerButton);
    }

    /**
     * Adding view for a textView.
     */
    private void updateTextViewContent() {
        String textContent = "Register Account";
        textView.setText(textContent);
        addView(textView);
    }

    /**
     * Getters and setters.
     */
    private Row getField(String fieldName){ return fields.get(fieldName); }
    public void setRegistrationValidator(RegistrationValidator validator) { this.registrationValidator = validator; }

    public void setData(List<Row> data){
        this.reg = data;
    }
    public List<Row> getData(){
        return registration.getRows();
    }
}
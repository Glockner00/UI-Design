package com.example.myaccountreg;
// ärver från layout
// klass för rad ärv från layour - hor/ver
// enum för olika typer av rader
// knapp för egen kod.. komma åt data och hämtar data från formuläret.
// validering utanför eller i komponenten? motiveringar
// kunna anpassa komponenten
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AccountRegistration extends LinearLayout {

    ArrayList<Row_Type> selectedFields;

    List<String> fieldSuggestions;
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
    private void init(){
        selectedFields = new ArrayList<>();
    }
    public void addField(String fieldName){
        Row_Type rowType = getRowTypeFromFieldName(fieldName);
        if(rowType!=null){
            selectedFields.add(rowType);
            updateFields();
        }else{
            Log.d("AccountRegistration", "Invalid field type: " + fieldName);
        }
    }

    public void removeField(String fieldType){
        Row_Type rowType = getRowTypeFromFieldName(fieldType);
        if(rowType!=null){
            selectedFields.remove(rowType);
            updateFields();
        }else{
            Log.d("Wrong fieldType", "removeField");
        }


    }
    public void updateFields(){
        removeAllViews();
        for(Row_Type fieldType: selectedFields){
            row = new Row(getContext());
            row.setRowType(fieldType);
            addView(row);
        }
    }
    private Row_Type getRowTypeFromFieldName(String fieldType){
        switch(fieldType.toLowerCase()){
            case "password":
                return Row_Type.PASSWORD;
            case "email":
                return Row_Type.EMAIL;
            default:
                Log.wtf("Wrong field type", "FieldType doesn't exist");
                break;
        }
        return null;
    }
}

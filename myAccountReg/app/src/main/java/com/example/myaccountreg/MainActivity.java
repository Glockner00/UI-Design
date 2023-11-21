package com.example.myaccountreg;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;

public class MainActivity extends AppCompatActivity {
    AccountRegistration accountRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountRegistration = findViewById(R.id.accountReg);
        accountRegistration.addField("firstname",RowType.FIRSTNAME);
        accountRegistration.addField("lastname", RowType.LASTNAME);
        accountRegistration.addField("password", RowType.PASSWORD);
        accountRegistration.addField("re-password", RowType.CUSTOM);
        accountRegistration.addField("age", RowType.AGE);
        accountRegistration.addField("email", RowType.EMAIL);
        accountRegistration.addField("phonenumber",RowType.PHONENUMBER);
        accountRegistration.customizeInputType("re-password", InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        accountRegistration.customizeBaseAppearance("re-password", 18, Color.BLACK, "re-password");




    }
}
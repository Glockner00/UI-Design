package com.example.myaccountreg;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
public class MainActivity extends AppCompatActivity {
    AccountRegistration accountRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountRegistration = findViewById(R.id.accountReg);
        accountRegistration.addField("firstname",RowType.FIRSTNAME);
        accountRegistration.addField("lastname", RowType.LASTNAME);
        accountRegistration.addField("age", RowType.AGE);
        accountRegistration.addField("email", RowType.EMAIL);
        accountRegistration.addField("phonenumber",RowType.PHONENUMBER);
    }
}
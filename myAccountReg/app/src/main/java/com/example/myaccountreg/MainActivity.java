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
        accountRegistration.addField("username");
        accountRegistration.addField("firstname");
        accountRegistration.addField("lastname");
        accountRegistration.addField("email");
        accountRegistration.addField("phonenumber");
        accountRegistration.addField("password");
    }
}
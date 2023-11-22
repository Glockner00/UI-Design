package com.example.myaccountreg;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    AccountRegistration accountRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountRegistration = findViewById(R.id.accountReg);
        accountRegistration.addField("firstname", RowType.FIRSTNAME);
        accountRegistration.addField("lastname", RowType.LASTNAME);
        accountRegistration.addField("password", RowType.PASSWORD);
        accountRegistration.addField("age", RowType.AGE);
        accountRegistration.addField("email", RowType.EMAIL);
        accountRegistration.addField("phonenumber",RowType.PHONENUMBER);
        accountRegistration.addField("customfield", RowType.CUSTOM);
        accountRegistration.updateBaseAppearance("customfield", 18, Color.BLACK,
                                                 "custom-field", InputType.TYPE_CLASS_TEXT);

        accountRegistration.setRegistrationValidator(new RegistrationValidator() {
            @Override
            public boolean validate(Registration registration) {
                List<Row> rows = registration.getRows();
                for (Row row : rows) {
                    Log.d("Developer Validation", "Type: " + row.getRowType() + ", Value: " + row.getText());
                }
                return true;
            }
        });

    }
}
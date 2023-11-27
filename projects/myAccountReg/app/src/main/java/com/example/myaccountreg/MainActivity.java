package com.example.myaccountreg;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import java.util.List;
/**
 * Example of how the AccountRegistration component could be used.
 */
public class MainActivity extends AppCompatActivity {
    private AccountRegistration accountRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountRegistration = findViewById(R.id.accountReg);

        // Set all rows/fields.
        accountRegistration.addField("firstname", RowType.FIRSTNAME);
        accountRegistration.addField("lastname", RowType.LASTNAME);
        accountRegistration.addField("password", RowType.PASSWORD);
        accountRegistration.addField("age", RowType.AGE);
        accountRegistration.addField("email", RowType.EMAIL);
        accountRegistration.addField("phonenumber",RowType.PHONENUMBER);

        // adding a custom field and setting its appearance.
        accountRegistration.addField("customfield", RowType.CUSTOM);
        accountRegistration.updateBaseAppearance("customfield", 18, Color.BLACK, "custom-field", InputType.TYPE_CLASS_TEXT);

        // Gaining access to a rows/fields view and an example of how to manipulate the appearance of that view.
        View firstNameView = accountRegistration.getRowView("firstname");
        firstNameView.setBackgroundColor(Color.RED);

        // Creating custom registration logic.
        accountRegistration.setRegistrationValidator(new RegistrationValidator() {
            @Override
            public boolean validate(Registration registration) {
                List<Row> rows = registration.getRows();
                boolean allFieldsFilled = true;
                for (Row row : rows) {
                    if(row.getText().isEmpty()) {
                        allFieldsFilled = false;
                        row.setError("Missing input");
                    }
                }
                return allFieldsFilled;
            }
        });
    }
}
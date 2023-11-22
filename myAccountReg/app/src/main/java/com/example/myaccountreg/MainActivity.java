package com.example.myaccountreg;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import java.util.List;
/**
 * Exempel på hur man skulle kunna använda komponenten AccountRegistration.
 */
public class MainActivity extends AppCompatActivity {
    private AccountRegistration accountRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountRegistration = findViewById(R.id.accountReg);

        /**
         * Skapar önskade fält.
         */
        accountRegistration.addField("firstname", RowType.FIRSTNAME);
        accountRegistration.addField("lastname", RowType.LASTNAME);
        accountRegistration.addField("password", RowType.PASSWORD);
        accountRegistration.addField("age", RowType.AGE);
        accountRegistration.addField("email", RowType.EMAIL);
        accountRegistration.addField("phonenumber",RowType.PHONENUMBER);

        /**
         * skapar ett tomt "custom" fält, och sätter nödvändiga parametrar.
         */
        accountRegistration.addField("customfield", RowType.CUSTOM);
        accountRegistration.updateBaseAppearance("customfield", 18, Color.BLACK, "custom-field", InputType.TYPE_CLASS_TEXT);

        /**
         * Hur man skulle kunna få åtkomst till varje fälts view och hur man skulle kunna manipulera den viewn.
         */
        View firstNameView = accountRegistration.getRowView("firstname");
        //firstNameView.setBackgroundColor(Color.RED);

        /**
         * Implementerar sin egen validerings-logik.
         */
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
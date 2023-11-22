package com.example.myaccountreg;
import android.util.Log;

import java.util.List;
public class DefaultRegistrationValidator implements RegistrationValidator {
    @Override
    public boolean validate(Registration registration) {
        List<Row> rows = registration.getRows();
        for (Row row : rows) {
            if (row.getText().isEmpty()) {
                Log.d("default", "false");
                return false;
            }
        }
        Log.d("default", "true");
        return true;
    }
}


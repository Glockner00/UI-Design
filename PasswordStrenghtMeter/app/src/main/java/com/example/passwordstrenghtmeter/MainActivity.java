package com.example.passwordstrenghtmeter;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

/**
 * An example of how a developer could use the PasswordStrengthMeter component.
 */
public class MainActivity extends AppCompatActivity {
    private PasswordStrengthMeter passwordStrengthMeter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordStrengthMeter = findViewById(R.id.passwordstrengthmeter);

        // How to gain access to both views.
        View passwordView = passwordStrengthMeter.getPasswordView();
        View progressBarView = passwordStrengthMeter.getProgressBarView();


        // Setting progressbar colors.
        passwordStrengthMeter.setErrorMessageColor(Color.rgb(255, 0, 0));
        passwordStrengthMeter.setProgressbarColors(Color.rgb(255, 0, 0),
                                                   Color.rgb(255, 165, 0),
                                                   Color.rgb(0, 255, 0));

        // Setting text visuals.
        passwordStrengthMeter.setPasswordTextColor(Color.BLACK);

        // Implementing custom validation logic.
        passwordStrengthMeter.setStrengthValidator(new StrengthValidator() {
            @Override
            public boolean ValidateLength(String password) {
                return (password.length()>6);
            }
            @Override
            public boolean ValidateSpecialCharacters(String password) {
                String specialChars = "/@#$%&.,;:)(=?!+-|<>{}[]~^*'§";
                int requiredCount = 1;
                int count = 0;
                for (int i = 0; i < password.length(); i++) {
                    if (specialChars.contains(String.valueOf(password.charAt(i)))) {
                        count++;
                        if (count >= requiredCount) {
                            return true;
                        }
                    }
                }
                return false;
            }
            @Override
            public boolean ValidateCapLetters(String password) {
                for (int i=0; i<password.length(); i++) {
                    if (Character.isUpperCase(password.charAt(i))) {
                        return true;
                    }
                }
                return false;
            }
            @Overridegit
            public String ErrorMessage() {
                return "Password should contain seven characters, one special character and one capital letter.";
            }
        });

    }
}
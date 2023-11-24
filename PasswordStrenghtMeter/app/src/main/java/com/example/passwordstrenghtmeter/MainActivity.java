package com.example.passwordstrenghtmeter;
import androidx.appcompat.app.AppCompatActivity;
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

        // Implementing custom validation logic.
        passwordStrengthMeter.setStrengthValidator(new StrengthValidator() {
            @Override
            public boolean ValidateLength(String password) {
                return (password.length()>6);
            }
            @Override
            public boolean ValidateSpecialCharacters(String password) {
                String specialChars = "/@#$%&.,;:)(=?!";
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
            @Override
            public String ErrorMessage() {
                return "Password should contain seven characters and have one special- and capital character.";
            }
        });

    }
}
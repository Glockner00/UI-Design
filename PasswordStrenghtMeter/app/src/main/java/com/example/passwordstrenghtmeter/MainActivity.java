package com.example.passwordstrenghtmeter;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private PasswordStrenghtMeter passwordStrenghtMeter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordStrenghtMeter = findViewById(R.id.passwordstrenghtmeter);
        View passwordView = passwordStrenghtMeter.getPasswordView();
        passwordStrenghtMeter.setStrengthValidator(new StrengthValidator() {
            @Override
            public int Validate(String password) {
                if(0<password.length() && password.length()<2){
                    return -1;
                } else if (2<password.length() && password.length()<4) {
                    return 0;
                }else{
                    return 1;
                }
            }
        });
    }
}
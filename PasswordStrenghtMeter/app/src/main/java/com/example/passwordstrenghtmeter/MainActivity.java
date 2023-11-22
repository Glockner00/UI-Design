package com.example.passwordstrenghtmeter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private PasswordStrenghtMeter passwordStrenghtMeter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordStrenghtMeter = findViewById(R.id.passwordstrenghtmeter);
    }
}
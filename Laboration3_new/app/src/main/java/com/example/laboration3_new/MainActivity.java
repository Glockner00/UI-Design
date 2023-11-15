package com.example.laboration3_new;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private int id;
    private InteractiveSearcher interactiveSearcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        interactiveSearcher = findViewById(R.id.interactiveSearcher);
        interactiveSearcher = new InteractiveSearcher(this);
    }
}
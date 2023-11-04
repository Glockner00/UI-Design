package com.example.laboration2new;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter = new SearchAdapter(new ArrayList<>());
        recyclerView.setAdapter(searchAdapter);
        input = findViewById(R.id.input);

        InteractiveSearcher interactiveSearcher = new InteractiveSearcher(this);
        input.addTextChangedListener(interactiveSearcher.getTextWatcher());

    }

    public SearchAdapter getSearchAdapter(){ return searchAdapter; }
}
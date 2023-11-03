package com.example.laboration2;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView; // Contains all search suggestions.
    private SearchAdapter searchAdapter; // Handles the view of all search suggestions.
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

    /**
     * A reference to the adapter.
     * @return searchAdapter
     */
    public SearchAdapter getSearchAdapter() {
        return searchAdapter;
    }
}

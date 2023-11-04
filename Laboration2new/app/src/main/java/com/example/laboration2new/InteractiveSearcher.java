package com.example.laboration2new;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

/**
 * A component that extends View. Main function is to connect EditText to RecyclerView.
 * Works together with MainActivity (SearchAdapter reference) to and indirectly updates
 * the RecyclerView.
 */
public class InteractiveSearcher extends View    {
    private EditText input;
    private MainActivity mainActivity;
    private SearchAdapter searchAdapter;
    public InteractiveSearcher(Context context) {
        super(context);
    }

    public TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString();
                fetchSearchSuggestions(searchText);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    private void fetchSearchSuggestions(String response){
        Log.d("message", "fetchSearchSuggestions");
    }
}

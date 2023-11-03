package com.example.laboration2;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * A component that extends View. Main function is to connect EditText to RecyclerView.
 * Works together with MainActivity (SearchAdapter reference) to and indirectly updates
 * the RecyclerView.
 */
public class InteractiveSearcher extends View {
    private EditText input;
    private MainActivity mainActivity;

    public InteractiveSearcher(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mainActivity = (MainActivity) context;

        input = mainActivity.findViewById(R.id.input);
    }


    public TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

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

    /**
     * Fetches all search suggestions from an URL.
     * @param searchText
     */
    private void fetchSearchSuggestions(String searchText) {
        /*
           TODO: 1. Inkludera kod för att göra nätverksanrop och hämta sökförslag.
                 2. Använd HttpURLConnection som nätverksbibliotek.
                 3. Uppdatera sökförslagen i MainActivity och adaptern.
                 4. Använd mainActivity.getSearchAdapter() för att få adaptern.
         */
    }
    /*
       TODO: 1. Inkludera tidigare kod för att hämta sökförslag med HttpURLConnection.
             2. Anropa metoden som hämtar sökförslagen från webbtjänsten
     */


}

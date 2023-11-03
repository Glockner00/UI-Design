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

    private void fetchSearchSuggestions(String searchText) {
        // Här ska du inkludera din kod för att göra nätverksanrop och hämta sökförslag
        // Använd HttpURLConnection eller valfritt nätverksbibliotek
        // Uppdatera sökförslagen i din MainActivity och din adapter
        // Använd mainActivity.getSearchAdapter() för att få adaptern
    }

    // Här kan du inkludera din tidigare kod för att hämta sökförslag med HttpURLConnection
    // Anropa metoden som hämtar sökförslagen från webbtjänsten
}

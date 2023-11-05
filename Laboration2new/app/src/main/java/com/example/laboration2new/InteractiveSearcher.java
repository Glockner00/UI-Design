package com.example.laboration2new;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A component that extends View. Main function is to connect EditText to RecyclerView.
 * Works together with MainActivity (SearchAdapter reference) to and indirectly updates
 * the RecyclerView.
 */
public class InteractiveSearcher extends View    {
    private EditText input;
    private MainActivity mainActivity;
    private SearchAdapter searchAdapter;
    private Executor executor = Executors.newCachedThreadPool(); // Create an Executor
    public InteractiveSearcher(Context context) {
        super(context);
    }

    public TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String searchText = s.toString();
                // Execute the fetchSearchSuggestions task using an Executor
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            fetchSearchSuggestions(searchText);
                        } catch (MalformedURLException e) {
                            Log.d("Message", "MalformedURLException: " + e.getMessage());
                        }
                    }
                });
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    private void fetchSearchSuggestions(String searchText) throws MalformedURLException {
        try {
            URL url = new URL("https://andla.pythonanywhere.com/getnames/3/" + searchText);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream responseStream = new BufferedInputStream(urlConnection.getInputStream());
                parseSearchSuggestions(responseStream);
            }else{
                Log.d("message", "Not a valid URL");            }
            urlConnection.disconnect();
        }catch(Exception e){
            e.printStackTrace();
            Log.d("Message", "Exception: " + e.getMessage());
        }
    }
    private List<String> parseSearchSuggestions(InputStream responseStream){
        List<String> suggestions = new ArrayList<String>();
        Log.d("Message", "OK response, starting to parse JSON");
        return suggestions;
    }
}

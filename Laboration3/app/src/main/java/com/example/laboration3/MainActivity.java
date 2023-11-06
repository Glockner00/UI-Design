package com.example.laboration3;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
public class MainActivity extends AppCompatActivity {
    private int id = 0;
    private EditText input;
    private TextView output;
    private TextView id_counter;
    private HashMap<String, Item> searchHistory = new HashMap<>();
    private InteractiveSearcher interactiveSearcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
        interactiveSearcher = findViewById(R.id.searcher);
        input.addTextChangedListener(getTextWatcher());
        id_counter = findViewById(R.id.id_view);
    }
    public TextWatcher getTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String searchText = s.toString();
                if(searchHistory.containsKey(searchText)){
                    interactiveSearcher.setData(searchHistory.get(searchText).getResult());
                    id_counter.setText(searchHistory.get(searchText).getId());
                }else{
                    if(!searchText.isEmpty() && !searchText.contains(" ")){
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                fetch(searchText);
                                interactiveSearcher.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        interactiveSearcher.setData(searchHistory.get(searchText).getResult());
                                        id_counter.setText(searchHistory.get(searchText).getId());
                                        id++;
                                    }
                                });
                            }
                        });
                        t.start();
                    }else{
                        id_counter.setText("-1");
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        };
    }
    private void fetch(String searchText){
        ArrayList<String> data = new ArrayList<>();
        try {
            URL url = new URL("https://andla.pythonanywhere.com/getnames/" + id + "/" + searchText);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream responseStream = new BufferedInputStream(connection.getInputStream());
                InputStreamReader reader = new InputStreamReader(responseStream);
                Gson gson = new Gson();
                Item item = gson.fromJson(reader, Item.class);
                data.addAll(item.getResult());
                searchHistory.put(searchText, item);
                connection.disconnect();
            }else{
                Log.d("Bad Connection", String.valueOf(connection.getResponseCode()));
            }
        }catch(IOException e){
            e.printStackTrace();
            Log.d("Exception", e.getMessage());
        }
    }
}
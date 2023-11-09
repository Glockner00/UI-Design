package com.example.laboration3_new;
import android.util.Log;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
public class Fetcher {
    private int id;
    private String searchText;
    public Fetcher(int id, String searchText){
        this.id = id;
        this.searchText = searchText;

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSearchText() {
        return searchText;
    }
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
    public ArrayList<String> getSearchSuggestions(String searchText){
        return fetch(searchText);
    }
    private ArrayList<String> fetch(String searchText){
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
                connection.disconnect();
            }else{
                Log.d("Bad Connection", String.valueOf(connection.getResponseCode()));
            }
        }catch(IOException e){
            e.printStackTrace();
            Log.d("Exception", e.getMessage());
        }
        return data;
    }
}

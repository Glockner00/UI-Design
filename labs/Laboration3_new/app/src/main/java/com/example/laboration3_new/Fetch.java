package com.example.laboration3_new;
import android.util.Log;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * fetch data of a search suggestion, keeping track of the searches with int id.
 */
public class Fetch {
    private int id;
    private String searchText;
    private int numberOfSuggestions;
    private ArrayList<String> data;
    public Fetch(int id, String searchText, int numberOfSuggestions){
        this.id = id;
        this.searchText = searchText;
        this.numberOfSuggestions = numberOfSuggestions;
        this.data = fetch(searchText);
    }
    public int getId() {
        return id;
    }
    public ArrayList<String> getSearchSuggestions(){
        return this.data;
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
                int limit = Math.min(item.getResult().size(), numberOfSuggestions);
                data.addAll(item.getResult().subList(0, limit));
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
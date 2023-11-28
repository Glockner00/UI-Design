package com.example.laboration3_new;
import java.util.ArrayList;

/**
 * Representing the structure of each JSON object. Used with GSON in Fetcher.java.
 */
public class Item {
    private String id;
    private ArrayList<String> result;
    public String getId(){
        return id;
    }
    public ArrayList<String> getResult() {
        return result;
    }
}

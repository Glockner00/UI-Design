package com.example.laboration2;

import java.util.ArrayList;

public class Item {
    int id;
    ArrayList<String> data = new ArrayList<>();

    public Item(int id, ArrayList<String> data){
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

}

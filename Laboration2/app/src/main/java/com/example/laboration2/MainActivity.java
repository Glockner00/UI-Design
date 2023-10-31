package com.example.laboration2;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String groupAdress ="";
    String childAdress = "";
    EditText input;
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> collection;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createGroupList();
        createCollection();
        input = findViewById(R.id.input);
        input.setText("/");
        expandableListView = findViewById(R.id.labb2);
        expandableListAdapter = new MyExpandableListAdapter(this, groupList, collection);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                String selected = expandableListAdapter.getGroup(i).toString();
                if(groupAdress.equals(selected)){
                    expandableListView.collapseGroup(i);
                    groupAdress = "";
                    input.setText("");
                    return false;
                }
                groupAdress = selected;
                input.setText("/" + groupAdress + "/");
                return true;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String selected = expandableListAdapter.getChild(i, i1).toString(); // grouppos, childpos.
                //Toast.makeText(getApplicationContext(), "Selected" + " " + selected, Toast.LENGTH_SHORT).show();
                childAdress = selected;
                groupAdress = expandableListAdapter.getGroup(i).toString();
                String tmp = "/" + groupAdress + "/" + childAdress + "/";
                input.setText(tmp);
                return false;
            }
        });
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                String searchText = editable.toString().trim();
                String[] data1 = searchText.split("/");
                // Remove empty elements from the resulting array
                String[] result = Arrays.stream(data1).filter(s -> !s.isEmpty()).toArray(String[]::new);
                if (isSearchTextValid(searchText) == 1) {
                    input.setBackgroundColor(Color.GREEN);
                    for (int i = 0; i < groupList.size(); i++) {
                        if(result.length==1){
                            for(String s : groupList){
                                if(s.startsWith(result[0])){
                                }
                            }
                        }
                        if (groupList.get(i).toString().equals(searchText.replaceAll("/", ""))) {
                            groupAdress = searchText.replaceAll("/", "");
                            expandableListView.expandGroup(i);
                            break;
                        }
                    }
                    if(result.length>1){
                        for (String s : collection.get(groupAdress)) {
                            if (s.startsWith(result[1])) {
                                Log.d("FOR-IF", "hittad");
                            }
                        }
                    }
                }else if(isSearchTextValid(searchText)==-1) {
                    for(int i=0; i<groupList.size(); i++){
                        if(groupList.get(i).equals(searchText.replaceAll("/", ""))){
                            expandableListView.collapseGroup(i);
                            break;
                        }
                    }
                    input.setBackgroundColor(Color.RED);
                }else{
                    input.setBackgroundColor(Color.WHITE);
                }
            }
        });
    }
    private void createCollection() {
        String[] data = {"green", "yellow", "red", "blue"};
        collection = new HashMap<String, List<String>>();
        for(String group: groupList){
            loadChild(data);
            collection.put(group, childList);
        }
    }
    private void loadChild(String[] data) {
        childList = new ArrayList<>();
        for(String color : data){
            childList.add(color);
        }
    }
    private void createGroupList() {
        groupList = new ArrayList<>();
        groupList.add("light");
        groupList.add("medium");
        groupList.add("dark");
    }
    public static List<String> splitter(String input) {
        List<String> resultList = new ArrayList<>();
        String[] parts = input.split("/");
        for (String part : parts) {
            if (!part.isEmpty()) {
                resultList.add("/");
                resultList.add(part);
            }
        }
        if (input.endsWith("/")) {
            resultList.add("/");
        }
        return resultList;
    }
    private int isSearchTextValid(String searchText) {
        List<String> string = splitter(searchText.trim());
        if (string.size() >= 3 && string.size()<5 && "/"==(string.get(0)) && groupList.contains(string.get(1)) && "/"==(string.get(2))) {
            return 1;
        } if (string.size() >= 5 && "/"==string.get(0) && groupList.contains(string.get(1)) && "/"==string.get(2)
                && collection.get(string.get(1)).contains(string.get(3)) && string.get(4)=="/") {
            return 1;
        } if(string.size() == 0){
            return 0;
        }
        return -1;
    }
}
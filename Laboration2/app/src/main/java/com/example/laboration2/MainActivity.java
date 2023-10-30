package com.example.laboration2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
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
        expandableListView = findViewById(R.id.labb2);
        expandableListAdapter = new MyExpandableListAdapter(this, groupList, collection);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                String selected = expandableListAdapter.getGroup(i).toString();
                groupAdress = selected;
                input.setText("/" + groupAdress + "/");
                Toast.makeText(getApplicationContext(),
                        expandableListAdapter.getGroup(i) + " List Expanded.", Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                String selected = expandableListAdapter.getGroup(i).toString();
                if(selected == groupAdress){
                    input.setText("");
                    return;
                }else{
                    groupAdress = selected;
                            input.setText("/" + groupAdress + "/");
                }
                Toast.makeText(getApplicationContext(),
                        expandableListAdapter.getGroup(i) + " List Collapsed.", Toast.LENGTH_SHORT).show();
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
                return true;
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
}
package com.example.laboration1;
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
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    int groupPos=-1;
    int oldGroupPos=-1;
    int childPos=-1;
    int oldChildPos=-1;
    String groupName="";
    String childName="";
    boolean hadChild = false;
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
        expandableListView = findViewById(R.id.listView);
        expandableListAdapter = new MyExpandableListAdapter(this, groupList, collection);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int i, long id) {
                String selected = expandableListAdapter.getGroup(i).toString();
                if(groupName.equals(selected)){
                    input.setText("/");
                    expandableListView.collapseGroup(i);
                    return false;
                }
                groupName = selected;
                input.setText("/" + groupName + "/");
                return true;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int i, int i1, long id) {
                groupName = expandableListAdapter.getGroup(i).toString();
                childName = expandableListAdapter.getChild(i, i1).toString();
                input.setText("/" + groupName + "/" + childName + "/");
                colorChildGray(i, i1);
                return true;
            }
        });
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString().trim();
                if(!text.isEmpty()){
                    StringTokenizer tokenizer = new StringTokenizer(text, "/", true);
                    while(tokenizer.hasMoreTokens()){
                        String delimiter = tokenizer.nextToken();
                        if(delimiter.equals("/")){
                            if(tokenizer.hasMoreTokens()){
                                String groupToken = tokenizer.nextToken();
                                for(int i=0; i<groupList.size(); i++){
                                    if(groupToken.equals(groupList.get(i))){
                                        groupPos = i;
                                        if(tokenizer.hasMoreTokens()){
                                            delimiter = tokenizer.nextToken();
                                            if(delimiter.equals("/")){
                                                expandableListView.expandGroup(groupPos);
                                            }
                                        }else{
                                            if(oldChildPos!=-1 && oldGroupPos!=-1){
                                                colorChildWhite(oldGroupPos, oldChildPos);
                                            }
                                            collapseGroups();
                                            return;
                                        }
                                    }
                                }
                            } if(tokenizer.hasMoreTokens()){
                                String childToken = tokenizer.nextToken();
                                for(int i=0; i<childList.size(); i++){
                                    if(childToken.equals(childList.get(i))){
                                        childPos = i;
                                        if(tokenizer.hasMoreTokens()){
                                            delimiter = tokenizer.nextToken();
                                            if(delimiter.equals("/")){
                                                decolorIfNewChildInSameGroup();
                                                colorChildGray(groupPos, childPos);
                                            }
                                        }else{
                                            hadChild = false;
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }else{
                    input.setText("/");
                }

            }
        });
    }

    public void decolorIfNewChildInSameGroup(){
        if(oldGroupPos!=-1 && oldChildPos!=-1 && childPos!=-1 && oldChildPos!=childPos){
            colorChildWhite(oldGroupPos, oldChildPos);
        }
    }
    public void collapseGroups(){
        for(int i=0; i<groupList.size(); i++){
            expandableListView.collapseGroup(i);
        }
    }
    public void colorChildWhite(int x, int y){
        int oldChildIndex = expandableListView.getFlatListPosition(ExpandableListView.
                getPackedPositionForChild(x, y));
        expandableListView.getChildAt(oldChildIndex).setBackgroundColor(Color.WHITE);
    }
    public void colorChildGray(int x, int y){
            int oldChildIndex = expandableListView.getFlatListPosition(ExpandableListView.
                    getPackedPositionForChild(x, y));
            expandableListView.getChildAt(oldChildIndex).setBackgroundColor(Color.GRAY);
            oldChildPos = y;
            oldGroupPos = x;
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
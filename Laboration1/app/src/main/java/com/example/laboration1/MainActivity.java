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
public class MainActivity extends AppCompatActivity {
    int groupPos=-1;
    int oldGroupPos=-1;
    int childPos=-1;
    int oldChildPos=-1;
    String groupName="";
    String childName="";
    boolean checkChild = false;
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
                colorChildWhite(i, i1);
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
                String[] tmpData = text.split("/");
                String[] inputData = Arrays.stream(tmpData).filter(s -> !s.isEmpty()).toArray(String[]::new);
                if(!text.isEmpty()){
                    if(text.endsWith("/")){
                        String selected = text.replaceAll("/", "");
                        for(int i=0; i<groupList.size(); i++){
                            if(groupList.get(i).equals(selected)){
                                expandableListView.expandGroup(i);
                                input.setBackgroundColor(Color.WHITE);
                                break;
                            }
                        }
                    } if(!text.endsWith("/") && inputData.length==1) {
                        for (int i = 0; i < groupList.size(); i++) {
                            expandableListView.collapseGroup(i);
                            input.setBackgroundColor(Color.WHITE);
                        }
                    }
                    if(inputData.length>1){
                        String groupName = inputData[0];
                        if(groupList.contains(groupName)){
                            groupPos = groupList.indexOf(groupName);
                            List<String> childrenOfGroup = collection.get(groupName);
                            for(int i=0; i<childrenOfGroup.size(); i++){
                                if(childrenOfGroup.get(i).startsWith(inputData[1])){
                                    input.setBackgroundColor(Color.WHITE);
                                    childPos = i;
                                    checkChild = true;
                                    break;
                                }else{
                                    input.setBackgroundColor(Color.RED);
                                    checkChild = false;
                                    if(groupPos!=-1 && childPos!=-1) {
                                        colorChildWhite(groupPos, childPos);
                                    }
                                }
                            }
                        }
                        if(oldChildPos!=childPos && groupPos!=-1 && childPos!=-1 & oldChildPos!=-1){
                            colorChildWhite(groupPos, oldChildPos);

                        } if(groupPos!=-1 && childPos!=-1 && checkChild && inputData.length==2){
                            colorChildGray(groupPos, childPos);
                            oldGroupPos = groupPos;
                            oldChildPos = childPos;
                        }
                    } if(inputData.length==1 && oldGroupPos!=-1 && oldChildPos!=-1){
                        colorChildWhite(oldGroupPos, oldChildPos);
                    }
                }else{
                    input.setText("/");
                }
            }
        });
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
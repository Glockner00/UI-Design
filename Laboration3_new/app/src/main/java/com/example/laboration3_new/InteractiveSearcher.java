package com.example.laboration3_new;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ListPopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;



public class InteractiveSearcher extends androidx.appcompat.widget.AppCompatEditText {
    private int id=0;
    private int fetchedID;
    private ArrayList<String> mySuggestions;
    private MyAdapter myAdapter;
    private Fetcher fetcher;
    private ListPopupWindow listPopupWindow;
    private Context context;
    public InteractiveSearcher(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }
    public InteractiveSearcher(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }
    public InteractiveSearcher(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }
    private void init() {
        mySuggestions = new ArrayList<>();
        listPopupWindow = new ListPopupWindow(this.context);
        listPopupWindow.setAnchorView(this);
        listPopupWindow.setWidth(500);
        listPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        myAdapter = new MyAdapter(this.context, mySuggestions);
        listPopupWindow.setAdapter(myAdapter);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String selected = s.toString().trim();
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        fetcher = new Fetcher(id, selected);
                        int fetchedId = fetcher.getId();
                        mySuggestions = fetcher.getSearchSuggestions();
                        post(new Runnable() {
                            @Override
                            public void run() {
                                if(fetchedId == id){
                                    myAdapter.setData(mySuggestions);
                                    myAdapter.notifyDataSetChanged();
                                    listPopupWindow.show();
                                }
                                id++;
                                //discard
                            }
                        });
                    }
                });
                t.start();
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }
}
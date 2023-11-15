package com.example.laboration3_new;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
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

    public InteractiveSearcher(@NonNull Context context) {
        super(context);
        init();
    }

    public InteractiveSearcher(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public InteractiveSearcher(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        listPopupWindow = new ListPopupWindow(this.getContext());
        myAdapter = new MyAdapter(getContext(), mySuggestions); // mySuggestions == null
        listPopupWindow.setAdapter(myAdapter);

        // varje gång vi uppdaterar adaptern setData... Notify.. (i adaptern) och rensa.
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
                                }
                                id++;
                                //discarda
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

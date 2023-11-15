package com.example.laboration3_new;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * Own component that for an EditText
 */

public class InteractiveSearcher extends androidx.appcompat.widget.AppCompatEditText {

    private int id=0;
    private MyListPopUpWindow myListPopUpWindow;

    private MyAdapter myAdapter;
    private Fetcher fetcher;

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

    private void init(){
        myListPopUpWindow = new MyListPopUpWindow(context, "");
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String selected = s.toString().trim();
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        fetcher = new Fetcher(0, selected);
                        ArrayList<String> data = fetcher.fetch(fetcher.toString());
                        post(new Runnable() {
                            @Override
                            public void run() {
                                if(data.size()>1){
                                    myListPopUpWindow.setResult(data.get(0));
                                }
                            }
                        });
                    }
                });
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

}

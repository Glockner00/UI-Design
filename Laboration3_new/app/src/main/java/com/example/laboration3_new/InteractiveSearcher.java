package com.example.laboration3_new;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListPopupWindow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
public class InteractiveSearcher extends androidx.appcompat.widget.AppCompatEditText {
    private int id=-1;
    private int numberOfSuggestions=10;
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
    public void setNumberOfSuggestions(int n){
        this.numberOfSuggestions = n;
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
        listPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        listPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        myAdapter = new MyAdapter(this.context, mySuggestions);
        listPopupWindow.setAdapter(myAdapter);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Row row = (Row) view;
                setText(row.getSuggestion());
            }
        });
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String selected = s.toString().trim();
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        id++;
                        fetcher = new Fetcher(id, selected, numberOfSuggestions);
                        mySuggestions = fetcher.getSearchSuggestions();
                        post(new Runnable() {
                            @Override
                            public void run() {
                                if(id == fetcher.getId()){
                                    myAdapter.setData(mySuggestions);
                                    myAdapter.notifyDataSetChanged();
                                    listPopupWindow.show();
                                }
                            }
                        });
                    }
                });
                t.start();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
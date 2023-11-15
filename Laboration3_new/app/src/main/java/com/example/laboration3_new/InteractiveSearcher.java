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
    private static final int DEFAULT_NUMBER_OF_SUGGESTIONS=10;
    private int id=-1;
    private int numberOfSuggestions=-1;
    private ArrayList<String> mySuggestions;
    private MyAdapter myAdapter;
    private Fetch fetch;
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
        if(numberOfSuggestions==-1){numberOfSuggestions=DEFAULT_NUMBER_OF_SUGGESTIONS;}
        mySuggestions = new ArrayList<>();
        listPopupWindow = new ListPopupWindow(this.context);
        listPopupWindow.setAnchorView(this);
        listPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        myAdapter = new MyAdapter(this.context, mySuggestions);
        listPopupWindow.setAdapter(myAdapter);
        listPopupWindow.setOnItemClickListener(getClickListener());
        addTextChangedListener(getTextWatcher());
    }
    public void setNumberOfSuggestions(int n){
        this.numberOfSuggestions = n;
    }
    private AdapterView.OnItemClickListener getClickListener(){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Row row = (Row) view;
                setText(row.getSuggestion());
            }
        };
    }
    public TextWatcher getTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString().trim();
                if(!input.isEmpty()) {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            id++;
                            fetch = new Fetch(id, input, numberOfSuggestions);
                            mySuggestions = fetch.getSearchSuggestions();
                            post(new Runnable() {
                                @Override
                                public void run() {
                                    if (id == fetch.getId()) {
                                        myAdapter.setData(mySuggestions);
                                        myAdapter.notifyDataSetChanged();
                                        listPopupWindow.setWidth(myAdapter.getWidestTextWidth());
                                        listPopupWindow.show();
                                    }
                                }
                            });
                        }
                    });
                    t.start();
                }else{
                    myAdapter.clearData();
                    myAdapter.notifyDataSetChanged();
                    listPopupWindow.dismiss();
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        };
    }
}
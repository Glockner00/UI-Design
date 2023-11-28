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
    public void setNumberOfSuggestions(int n){
        this.numberOfSuggestions = n;
    }

    /**
     * initialize the adapter, listpopupwindow and the data structure for all suggestions,
     * Adding a text watcher and a click-listener.
     */
    private void init() {
        if(numberOfSuggestions==-1){numberOfSuggestions=DEFAULT_NUMBER_OF_SUGGESTIONS;}
        mySuggestions = new ArrayList<>();
        myAdapter = new MyAdapter(this.context, mySuggestions);
        listPopupWindow = new ListPopupWindow(this.context);
        listPopupWindow.setAdapter(myAdapter);
        listPopupWindow.setAnchorView(this);
        listPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        addTextChangedListener(getTextWatcher());
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Row row = (Row) view;       // A Row is a view --> we can cast Row on each view.
                setText(row.getSuggestion()); // using the getter from Row.
            }
        });
    }
    private TextWatcher getTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String input = s.toString().trim();
                // open a thread for a network connection/fetching data.
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        id++;
                        fetch = new Fetch(id, input, numberOfSuggestions); // new fetch
                        mySuggestions = fetch.getSearchSuggestions(); // set our arraylist with suggestions.
                        post(new Runnable() {
                            @Override
                            public void run() {
                                if(mySuggestions.isEmpty()){ // if we get no search suggestions.
                                    clearWindow();
                                    return;
                                }
                                /**
                                 Check if the current id is the same as fetch id. We are doing this
                                 to ensure that we get the correct dataset, as we don't know how
                                 fast each fetch is or how fast the user is typing.
                                 */
                                if (id == fetch.getId()) {

                                    /**
                                     Setting data will start creating Rows, and the Rows will be
                                     drawn with help of onDraw in the Row class.
                                     */
                                    myAdapter.setData(mySuggestions); // set available data.
                                    myAdapter.notifyDataSetChanged(); // notify a change.
                                    listPopupWindow.setWidth(myAdapter.getWidestTextWidth()); // set the width.
                                    listPopupWindow.show(); // show the listpopupwindow-view.
                                }
                            }
                        });
                    }
                });
                t.start();
            }
            @Override
            public void afterTextChanged(Editable s) { }
        };
    }

    /**
     * Clearing out the listpopupwindow.
     */
    private void clearWindow(){
        myAdapter.clearData();
        myAdapter.notifyDataSetChanged();
        listPopupWindow.dismiss();
    }
}
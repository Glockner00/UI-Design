package com.example.laboration3_new;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;


/**
 * A class that fetches search suggestions and has a ListPopUpWindow.
 *
 * Should be able to draw one line at a time in the window.
 *
 */
public class MyRoute extends View {
    public MyRoute(Context context) {
        super(context);
    }

    public MyRoute(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRoute(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyRoute(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public TextWatcher getTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}

package com.example.laboration3_new;
import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ListPopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Own component that for an EditText
 */

public class InteractiveSearcher extends androidx.appcompat.widget.AppCompatEditText {

    private ListPopupWindow listPopupWindow;
    private Fetcher fetcher;
    private TextWatcher textWatcher;


    public InteractiveSearcher(@NonNull Context context) {
        super(context);
    }

    public InteractiveSearcher(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InteractiveSearcher(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}

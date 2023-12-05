package com.example.passwordstrenghtmeter;
import android.content.Context;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

public class Password extends androidx.appcompat.widget.AppCompatEditText {
    private View passwordView;
    public static int passwordId; // EditText id.

    public Password(@NonNull Context context) {
        super(context);
        init();
    }

    public Password(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Password(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setHint("enter password");
        setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        setGravity(android.view.Gravity.CENTER_VERTICAL | android.view.Gravity.CENTER_HORIZONTAL);
        this.passwordView = this;
        passwordId = generateID();
        setId(passwordId);
    }

    /**
     * Generate a unique id
     * @return id
     */
    private int generateID(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return ViewCompat.generateViewId();
        } else {
            return View.generateViewId();
        }
    }

    public void setView(View view){
        this.passwordView = view;
    }

    public View getView(){
        return this.passwordView;
    }

    protected void setPasswordTextColor(int color){
        setTextColor(color);
    }

}

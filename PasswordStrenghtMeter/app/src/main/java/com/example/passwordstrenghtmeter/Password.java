package com.example.passwordstrenghtmeter;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
public class Password extends androidx.appcompat.widget.AppCompatEditText {
    private View passwordView;
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
    }

    public void setView(View view){
        this.passwordView = view;
    }

    public View getView(){
        return this.passwordView;
    }
}

package com.example.passwordstrenghtmeter;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class PasswordStrenghtMeter extends LinearLayout {

    private Button button;

    private DefaultValidator defaultValidator;
    private StrengthValidator strengthValidator;
    private Password password;
    private StrengthBar strengthBar;
    public PasswordStrenghtMeter(Context context) {
        super(context);
    }

    public PasswordStrenghtMeter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PasswordStrenghtMeter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PasswordStrenghtMeter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        setOrientation(VERTICAL);
        password = new Password(getContext());
        strengthBar = new StrengthBar(getContext());
        strengthValidator = new DefaultValidator();
        password.addTextChangedListener(getTextWatcher());
        addView(password);
        addView(strengthBar);

    }

    private TextWatcher getTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                final String text = s.toString();
                strengthBar.updateStrength(strengthValidator.Validate(text));

            }
        };
    }

    public void setStrengthValidator(StrengthValidator validator){
        this.strengthValidator = validator;
    }
    public View getPasswordView(){
        return password.getView();
    }



}

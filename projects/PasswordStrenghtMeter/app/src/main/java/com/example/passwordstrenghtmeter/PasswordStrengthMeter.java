package com.example.passwordstrenghtmeter;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

public class PasswordStrengthMeter extends LinearLayout {
    private StrengthValidator strengthValidator; // an interface for validating password strength.
    private Password password;
    private StrengthBar strengthBar;
    private String passwordData;

    private String text;

    private Button button;

    private int passwordId;
    public PasswordStrengthMeter(Context context) {
        super(context);
    }

    public PasswordStrengthMeter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PasswordStrengthMeter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PasswordStrengthMeter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * Initialize all datastructures and adding all views.
     */
    private void init(){
        setOrientation(VERTICAL);
        password = new Password(getContext());
        strengthBar = new StrengthBar(getContext());
        strengthValidator = new DefaultValidator();
        button = new Button(getContext());
        password.addTextChangedListener(getTextWatcher());
        button.setText("Register");
        strengthBar.setErrorMessage(strengthValidator.ErrorMessage());
        button.setOnClickListener(v -> onButtonClicked());
        addView(password);
        addView(strengthBar);
        addView(button);
        addView(strengthBar.getErrorMessageView());
    }

    /**
     * If the password is strong, and the button is clicked we save the password.
     */
    private void onButtonClicked(){
        if(text!=null) {
            if (strengthValidator.ValidateLength(text)
                    && strengthValidator.ValidateSpecialCharacters(text)
                    && strengthValidator.ValidateCapLetters(text)) {
                setPasswordData(text);
            }
        }

    }
    /**
     * A text watcher to dynamically validate the password.
     * @return TextWatcher
     */
    private TextWatcher getTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                text = s.toString();
                if(text.isEmpty()){
                    strengthBar.hideErrorMessage();
                }
                strengthBar.updateStrengthNew(strengthValidator.ValidateLength(text),
                                              strengthValidator.ValidateSpecialCharacters(text),
                                              strengthValidator.ValidateCapLetters(text));
            }
        };
    }

    public void setProgressbarColors(int weakColor, int mediumColor, int strongColor){
        strengthBar.setProgressbarColors(weakColor, mediumColor, strongColor);
    }

    public void setErrorMessageColor(int color){
        strengthBar.setErrorMessageColor(color);
    }

    public void setPasswordTextColor(int color){
        password.setPasswordTextColor(color);
    }


    /**
     * Function for setting new validation logic.
     * @param validator
     */
    public void setStrengthValidator(StrengthValidator validator){
        this.strengthValidator = validator;
        strengthBar.setErrorMessage(strengthValidator.ErrorMessage());
    }

    /**
     * Access to the EditText view.
     * @return View
     */
    public View getPasswordView(){
        return password.getView();
    }

    /**
     * Access to the ProgressBar view.
     * @return View
     */
    public View getProgressBarView(){
        return strengthBar.getProgressBarView();
    }

    private void setPasswordData(String text){
        this.passwordData = text;
    }
    public String getPasswordData(){
        return this.passwordData;
    }

    public int getPasswordId(){
        return password.getId();
    }
}

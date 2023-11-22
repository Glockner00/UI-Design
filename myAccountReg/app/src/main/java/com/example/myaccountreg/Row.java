package com.example.myaccountreg;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
public class Row extends LinearLayout {
    private RowType rowType;
    private String name;
    private EditText editText;
    private View rowView;
    protected Row(Context context) {
        super(context);
        init();
    }
    protected Row(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    protected Row(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void setError(String error){
        editText.setError(error);
    }

    protected void customizeBaseAppearance(int textSize, int textColor, String hint, int inputType) {
        setTextSize(textSize);
        setTextColor(textColor);
        setHint(hint);
        setInputType(inputType);
    }

    protected void setTextColor(int textColor){
        editText.setTextColor(textColor);
    }

    protected void setTextSize(int textSize){
        editText.setTextSize(textSize);

    }
    protected void setBackGroundColor(int backGroundColor){
        editText.setBackgroundColor(backGroundColor);
    }

    protected void setHint(String text){
        editText.setHint(text);
    }

    protected void setInputType(int inputType){
        editText.setInputType(inputType);
    }


    protected EditText makeRow(RowType rowType){
        editText = new EditText(getContext());
        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT
        );
        editText.setLayoutParams(params);
        switch (rowType){
            case PASSWORD:
                editText.setHint("password");
                editText.setError("Missing input");
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
                break;
            case EMAIL:
                editText.setHint("email");
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case FIRSTNAME:
                editText.setHint("firstname");
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                break;
            case LASTNAME:
                editText.setHint("lastname");
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                break;
            case USERNAME:
                editText.setHint("username");
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case AGE:
                editText.setHint("age");
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case PHONENUMBER:
                editText.setHint("phone-number");
                editText.setInputType(InputType.TYPE_CLASS_PHONE);
                break;
            case CUSTOM:
                break;
            default:
                Log.d("Default", "Wrong type");
                break;
        }
        return editText;

    }
    public void setRowType(RowType type) {
        this.rowType = type;
    }
    public RowType getRowType() {
        return rowType;
    }
    public void setRowName(String name){
        this.name = name;
    }
    public String getText() {
        return editText.getText().toString();
    }
    public void setText(String text){
        editText.setText("");
    }
    public View getRowView(){
        return rowView;
    }
    public void setRowView(View view) {
        this.rowView = view;
    }

    private void init() {
        this.rowView = this;
    }

}

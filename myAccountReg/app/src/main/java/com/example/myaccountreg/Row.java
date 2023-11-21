package com.example.myaccountreg;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
public class Row extends CardView {
    private RowType rowType;
    private EditText editText;
    private EditText rePassword;
    public Row(Context context) {
        super(context);
    }
    public Row(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public Row(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void makeRow(){
        setCardElevation(8);
        setRadius(8);
        setUseCompatPadding(true);

        editText = new EditText(getContext());
        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT
        );
        editText.setLayoutParams(params);
        switch (rowType){
            case PASSWORD:
                editText.setHint("password");
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
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
                editText.setHint("");
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            default:
                Log.d("Default", "Wrong type");
                break;
        }
        addView(editText);
    }

    /**
     * update the row type
     * @param type
     */
    public void setRowType(RowType type) {
        this.rowType = type;
        makeRow();
    }
    public String getText() {
        return editText.getText().toString();
    }
}

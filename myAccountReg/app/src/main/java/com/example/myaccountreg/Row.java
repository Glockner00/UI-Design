package com.example.myaccountreg;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
public class Row extends LinearLayout {
    private Row_Type rowType;
    private EditText editText;

    public Row(Context context) {
        super(context);
    }

    public Row(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Row(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Row(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void makeRow(){
        editText = new EditText(getContext());
        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT
        );
        editText.setLayoutParams(params);

        switch (rowType){
            case PASSWORD:
                editText.setHint("Password");
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
            case EMAIL:
                editText.setHint("E-mail");
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
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
    public void setRowType(Row_Type type) {
        this.rowType = type;
        makeRow();
    }

    public String getText() {
        return editText.getText().toString();
    }
}

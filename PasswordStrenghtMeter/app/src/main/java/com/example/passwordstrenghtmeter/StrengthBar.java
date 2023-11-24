package com.example.passwordstrenghtmeter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class StrengthBar extends ProgressBar {
    private static final int defaultColor = Color.TRANSPARENT; // constant for transparent color.
    private static final int alpha = 255; // constant for 100% opacity.
    private TextView errorMessage;
    private int weakColor;
    private int mediumColor;
    private int strongColor;
    private View progressBarView;

    public StrengthBar(Context context) {
        super(context, null, android.R.attr.progressBarStyleHorizontal);
        init();
    }
    public StrengthBar(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.progressBarStyleHorizontal);
        init();
    }
    public StrengthBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, android.R.attr.progressBarStyleHorizontal);
        init();
    }
    public StrengthBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, android.R.attr.progressBarStyleHorizontal);
        init();
    }

    /**
     * Initialize colors and error messages.
     */
    private void init() {
        this.weakColor = Color.rgb(255, 0, 0);
        this.mediumColor = Color.rgb(255, 165, 0);
        this.strongColor = Color.rgb(0, 255, 0);
        errorMessage = new TextView(getContext());
        errorMessage.setTextColor(Color.RED);
        errorMessage.setVisibility(View.GONE);
        errorMessage.setGravity(android.view.Gravity.CENTER_VERTICAL | android.view.Gravity.CENTER_HORIZONTAL);
        this.progressBarView = this;
    }

    private void hideErrorMessage() {
        errorMessage.setVisibility(View.GONE);
    }

    private void showErrorMessage() {
        errorMessage.setVisibility(View.VISIBLE);
    }

    /**
     * Updates the Progressbar based on three parameters from the interface.
     * @param length
     * @param specChar
     * @param capLet
     */
    public void updateStrengthNew(boolean length, boolean specChar, boolean capLet){
        int trueCount = 0;
        int progress = 0;
        if(length){
            trueCount++;
        } if(specChar){
            trueCount++;
        } if(capLet){
            trueCount++;
        }
        if(trueCount==0){
            setProgressbarColor(defaultColor);
            showErrorMessage();
            setProgress(0);
        }if(trueCount==1){
            setProgressbarColor(weakColor);
            showErrorMessage();
            progress = 33;
        }if(trueCount==2){
            setProgressbarColor(mediumColor);
            showErrorMessage();
            progress = 66;
        }if(trueCount==3){
            setProgressbarColor(strongColor);
            hideErrorMessage();
            progress = 100;
        }
        setProgress(progress);
    }

    /**
     * Setting the color of the progressbar with 100% opacity.
     * @param color
     */
    private void setProgressbarColor(int color) {
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().setColorFilter(Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color)), PorterDuff.Mode.SRC_IN);
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().setColorFilter(Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color)), PorterDuff.Mode.SRC_IN);
        }
    }

    /**
     * Getters and setters.
     */
    public View getProgressBarView(){
        return this.progressBarView;
    }

    public void setProgressBarView(View view){
        this.progressBarView = view;
    }

    protected View getErrorMessageView(){
        return errorMessage;
    }

    public void setColors(int weakColor, int mediumColor, int strongColor) {
        this.weakColor = weakColor;
        this.mediumColor = mediumColor;
        this.strongColor = strongColor;
    }

    protected void setErrorMessage(String message){
        errorMessage.setText(message);
    }
}

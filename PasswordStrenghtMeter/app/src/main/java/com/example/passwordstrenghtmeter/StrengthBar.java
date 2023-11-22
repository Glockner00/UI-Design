package com.example.passwordstrenghtmeter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
public class StrengthBar extends ProgressBar {
    private static final int defaultColor = Color.TRANSPARENT;
    private static final int alpha = 255;
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
    private void init() {
        this.weakColor = Color.rgb(255, 0, 0);
        this.mediumColor = Color.rgb(255, 165, 0);
        this.strongColor = Color.rgb(0, 255, 0);
        this.progressBarView = this;
    }
    public void setColors(int weakColor, int mediumColor, int strongColor) {
        this.weakColor = weakColor;
        this.mediumColor = mediumColor;
        this.strongColor = strongColor;
    }
    public void updateStrength(int strengthLevel) {
        int progress = 0;
        if (strengthLevel == 1) {
            setProgressbarColor(weakColor);
            progress = 33;
        } else if (strengthLevel == 2) {
            setProgressbarColor(mediumColor);
            progress = 66;
        } else if (strengthLevel == 3) {
            setProgressbarColor(strongColor);
            progress = 100;
        } else {
            setProgressbarColor(defaultColor);
        }
        setProgress(progress);
    }
    public void setProgressBarView(View view){
        this.progressBarView = view;
    }
    public View getProgressBarView(){
        return this.progressBarView;
    }
    private void setProgressbarColor(int color) {
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().setColorFilter(Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color)), PorterDuff.Mode.SRC_IN);
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().setColorFilter(Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color)), PorterDuff.Mode.SRC_IN);
        }
        invalidate();
    }
}

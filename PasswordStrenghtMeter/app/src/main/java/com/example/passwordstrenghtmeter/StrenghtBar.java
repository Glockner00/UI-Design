package com.example.passwordstrenghtmeter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
public class StrenghtBar extends ProgressBar {
    private static final int defaultColor = Color.rgb(255,255,0);
    private int weakColor;
    private int mediumColor;
    private int strongColor;
    private View progressBarView;
    public StrenghtBar(Context context) {
        super(context, null, android.R.attr.progressBarStyleHorizontal);
    }

    public StrenghtBar(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.progressBarStyleHorizontal);
        init();
    }

    public StrenghtBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, android.R.attr.progressBarStyleHorizontal);
        init();
    }

    public StrenghtBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        int color;
        if(strengthLevel==-1){
            color = weakColor;
        }else if(strengthLevel==0){
            color = mediumColor;
        }else if(strengthLevel==1){
            color = strongColor;
        }else{
            color = defaultColor;
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().setTint(Color.RED);
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().setTint(Color.RED);
        }
    }
    public void setProgressBarView(View view){
        this.progressBarView = view;
    }
    public View getProgressBarView(){
        return this.progressBarView;
    }
}

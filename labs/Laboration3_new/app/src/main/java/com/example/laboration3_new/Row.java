package com.example.laboration3_new;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

/**
 * A class representing a single row in the listpopupwindow.
 * Main function of this class is to draw that row. With help of onDraw/onMeasure.
 */


public class Row extends View {
    private String suggestion;
    private Paint paint;
    private int textWidth;
    public Row(Context context, String suggestion) {
        super(context);
        paint = new Paint();
        paint.setTextSize(40);
        this.suggestion = suggestion; // single suggestion.
        this.textWidth = (int) paint.measureText(suggestion);  // later used in adapter for sizing
                                                               // the listpopupwindow.
    }
    public float getTextWidth(){
        return this.textWidth;
    }
    public Row(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public Row(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public Row(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawText(suggestion, 0, 50, paint);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, 100);
    }
    public String getSuggestion(){
        return this.suggestion.toString();
    }
}
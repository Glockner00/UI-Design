package com.example.laboration3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class InteractiveSearcher extends View {

    ArrayList<String> data = new ArrayList<>();

    public InteractiveSearcher(Context context) {
        super(context);
    }

    public InteractiveSearcher(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InteractiveSearcher(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public InteractiveSearcher(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setData(ArrayList<String> newData){
        this.data = newData;
        invalidate();
    }

    @Override
    public void onDraw (Canvas canvas){
        super.onDraw(canvas);
        Paint myPaint = new Paint();
        myPaint.setColor(Color.BLUE);
        myPaint.setTextSize(40);

        int counter = 0;
        for(String name : this.data){
            canvas.drawText(name,15,70*counter,myPaint);
            counter++;
        }


    }

}
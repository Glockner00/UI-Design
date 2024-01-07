package com.example.projekt_cards;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;



public class CustomCardView extends FrameLayout {
    private TextView cardTitle;
    private TextView cardTime;
    private ImageView cardImage;
    private boolean isClickable;
    private onCardClickListener onCardClickListener;

    //Only this constructor will be used because of how CustomCardViews are created in MainActivity
    public CustomCardView(@NonNull Context context) {
        super(context);
        init();
        LayoutInflater.from(context).inflate(R.layout.custom_card_view, this, true);
        cardTitle = findViewById(R.id.cardTitle);
        cardTime = findViewById(R.id.cardTime);
        cardImage = findViewById(R.id.cardImage);
    }


    public CustomCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        isClickable = true;
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCardClickListener != null) {
                    onCardClickListener.onCardClicked();
                }
            }
        });
    }

    interface onCardClickListener {
        void onCardClicked();
    }

    public void setOnCardClickListener(onCardClickListener listener) {
        if (isClickable) {
            this.onCardClickListener = listener;
        }
    }

    public void setIfClickable(boolean clickable) {
        isClickable = clickable;
    }

    public void setTitle(String title) {
        //Set the cardTitle TextView
        if (cardTitle != null) {
            cardTitle.setText(title);
        }
    }

    public void setTitleSize(int textSize) {
        //Set the text size in the cardTitle TextView
        if (cardTitle != null) {
            cardTitle.setTextSize(textSize);
        }
    }

    public void setTitleColor(int color) {
        //Set the color in the cardTitle TextView
        if (cardTitle != null) {
            cardTitle.setTextColor(color);
        }
    }

    public void setTime(int time) {
        //Set the cardTime TextView
        if (cardTime != null)  {
            String timeString = Integer.toString(time);
            cardTime.setText(timeString);
        }
    }

    public void setTimeSize(int textSize) {
        //Set the text size in the cardTime TextView
        if (cardTime != null) {
            cardTime.setTextSize(textSize);
        }
    }

    public void setTimeColor(int color) {
        //Set the color in the cardTime TextView
        if (cardTime != null) {
            cardTime.setTextColor(color);
        }
    }

    public void setImage(int resourceId) {
        //Set the image in the cardImage ImageView
        cardImage.setImageResource(resourceId);
    }

    public void setImageSize(int height, int width) {
        if (cardImage != null) {
            cardImage.getLayoutParams().height = height;
            cardImage.getLayoutParams().width = width;
        }
    }

    public ImageView getCardImage() {
        return cardImage;
    }

    public void setCardImage(ImageView cardImage) {
        this.cardImage = cardImage;
    }
}

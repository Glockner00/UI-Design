package com.example.projekt_cards;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

public class MainActivity extends AppCompatActivity {

    public static CustomCardView customCardView1;
    public static CustomCardView customCardView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout mainContainer = findViewById(R.id.mainContainer);

        customCardView1 = createCustomCardView("Salamipizza", 25, R.drawable.salami_pizza);
        customCardView1.setId(generateId());
        customCardView1.setIfClickable(true);
        customCardView1.setOnCardClickListener(new CustomCardView.onCardClickListener() {
            @Override
            public void onCardClicked() {
                customCardView1.setImage(R.drawable.salami_pizza2);
            }
        });
        mainContainer.addView(customCardView1);

        customCardView2 = createCustomCardView("Enköping Special", 30, R.drawable.enkoping_special);
        customCardView2.setId(generateId());
        customCardView2.setIfClickable(true);
        customCardView2.setOnCardClickListener(new CustomCardView.onCardClickListener() {
            @Override
            public void onCardClicked() {
                customCardView2.setImage(R.drawable.enkoping_special2);
            }
        });
        mainContainer.addView(customCardView2);
    }

    private CustomCardView createCustomCardView(String title, int time, int imageResource) {
        CustomCardView customCardView = new CustomCardView(this);
        customCardView.setTitle(title);
        customCardView.setTime(time);
        customCardView.setImage(imageResource);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.card_margin_bottom));

        customCardView.setLayoutParams(layoutParams);

        return customCardView;
    }

    private int generateId() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return ViewCompat.generateViewId();
        } else {
            return View.generateViewId();
        }
    }
}

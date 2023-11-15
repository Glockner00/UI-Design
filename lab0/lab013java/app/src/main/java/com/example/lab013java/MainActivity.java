package com.example.lab013java;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Vi skapar en stor vertical linearLayout
        LinearLayout myLayout = new LinearLayout(this);
        myLayout.setOrientation(LinearLayout.VERTICAL);

    // rad 1

        LinearLayout rad1 = new LinearLayout(this);
        rad1.setOrientation(LinearLayout.HORIZONTAL);

        TextView title=new TextView (this);
        title.setText("Laboration 1.3");
        title.setTextSize(30);
        title.setTypeface(null, Typeface.BOLD);
        rad1.setBackgroundColor(Color.LTGRAY);

        rad1.addView(title);

        //rad 2

        LinearLayout rad2 = new LinearLayout(this);
        rad2.setOrientation(LinearLayout.HORIZONTAL);



        TextView qs = new TextView (this);
        LinearLayout.LayoutParams rad2Params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);




        qs.setText("Hur trivs du på LiU");
        qs.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        qs.setTextSize(20);
        qs.setTextColor(Color.GRAY);

        rad2.addView(qs,rad2Params);


        //rad 3 CHECKBOXAR

     LinearLayout rad3 = new LinearLayout(this);
     rad3.setOrientation(LinearLayout.HORIZONTAL);

     CheckBox cb = new CheckBox(this);
     cb.setText("Bra");
     cb.setTextSize(20);
     CheckBox cb1 = new CheckBox(this);
     cb1.setText("Mycket Bra");
     cb1.setTextSize(20);
     CheckBox cb2 = new CheckBox(this);
     cb2.setText("Jättebra");
     cb2.setTextSize(20);

    rad3.addView(cb);
    rad3.addView(cb1);
    rad3.addView(cb2);

    //rad 4 fråga

        LinearLayout rad4 = new LinearLayout(this);
        rad4.setOrientation(LinearLayout.HORIZONTAL);



        TextView qs1 = new TextView (this);
        LinearLayout.LayoutParams rad4Params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);




        qs1.setText("Läser du på LiTH");
        qs1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        qs1.setTextSize(20);
        qs1.setTextColor(Color.GRAY);

        rad4.addView(qs1,rad4Params);


        //rad 5 fler checkboxar

        LinearLayout rad5 = new LinearLayout(this);
        rad5.setOrientation(LinearLayout.HORIZONTAL);

        CheckBox cb3 = new CheckBox(this);
        cb3.setText("Ja");
        cb3.setTextSize(20);
        CheckBox cb4 = new CheckBox(this);
        cb4.setText("Nej");
        cb4.setTextSize(20);


        rad5.addView(cb3);
        rad5.addView(cb4);

        //rad 6 BILD

        LinearLayout rad6 = new LinearLayout(this);
        rad6.setOrientation(LinearLayout.HORIZONTAL);

        ImageView img = new ImageView(this);
        LinearLayout.LayoutParams rad6Params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        img.setImageResource(R.drawable.logo);
        rad6Params.width = 650;
        rad6Params.height = 650;

        rad6.setGravity(CENTER_HORIZONTAL);

        rad6.addView(img, rad6Params);



//RAD 7 FRGA TVÅ

        LinearLayout rad7 = new LinearLayout(this);
        rad7.setOrientation(LinearLayout.HORIZONTAL);



        TextView qs7 = new TextView (this);
        LinearLayout.LayoutParams rad7Params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);




        qs7.setText("Är detta LiUs logotyp");
        qs7.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        qs7.setTextSize(20);
        qs7.setTextColor(Color.GRAY);

        rad7.addView(qs7,rad7Params);


        //RAD 8 FLER KNAPPAR

        LinearLayout rad8 = new LinearLayout(this);
        rad8.setOrientation(LinearLayout.HORIZONTAL);

        CheckBox cb8 = new CheckBox(this);
        cb8.setText("Ja");
        cb8.setTextSize(20);
        CheckBox cb9 = new CheckBox(this);
        cb9.setText("Nej");
        cb9.setTextSize(20);


        rad8.addView(cb8);
        rad8.addView(cb9);


        //RAD SKICKA IN (9)

        LinearLayout rad9 = new LinearLayout(this);

        Button myButton = new Button(this);

        myButton.setText("SKICKA IN");
        myButton.setTextSize(20);
        myButton.setBackgroundColor(Color.LTGRAY);
        rad9.setBackgroundColor(Color.LTGRAY);

        rad9.setGravity(CENTER_HORIZONTAL);

        rad9.addView(myButton);

    myLayout.addView(rad1);
    myLayout.addView(rad2);
    myLayout.addView(rad3);
    myLayout.addView(rad4);
    myLayout.addView(rad5);
    myLayout.addView(rad6);
    myLayout.addView(rad7);
    myLayout.addView(rad8);
    myLayout.addView(rad9);

        setContentView(myLayout);
        //setContentView(R.layout.activity_main);
    }
}
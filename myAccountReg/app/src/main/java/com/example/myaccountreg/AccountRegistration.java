package com.example.myaccountreg;
//ärver från layout
//klass för rad ärv från layour - hor/ver
// enum för olika typer av rader
// knapp för egen kod.. komma åt data och hämtar data från formuläret.
// validering utanför eller i komponenten? motiveringar
// kunna anpassa komponenten
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class AccountRegistration extends LinearLayout {

    public AccountRegistration(Context context) {
        super(context);
        init();
    }

    public AccountRegistration(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AccountRegistration(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        init();
    }

    public AccountRegistration(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
    }
}

package com.grepthor.weforyoufoundation;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class RegestrationActivity extends AppCompatActivity {
TextView tvTerms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        removePhoneKeypad();
        setContentView(R.layout.activity_registration);
        tvTerms=(TextView)findViewById(R.id.tvTerms);
        tvTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegestrationActivity.this,LoginActivity.class);
                startActivityForResult(i, 1);
                RegestrationActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out);
                finish();
            }
        });


    }
    public void removePhoneKeypad() {
        if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
            System.out.println("getCurrentFocus() in frag");
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            IBinder binder = getCurrentFocus().getWindowToken();
            inputManager.hideSoftInputFromWindow(binder,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}

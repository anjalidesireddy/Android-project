package com.grepthor.weforyoufoundation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.grepthor.weforyoufoundation.constant.IErrors;
import com.grepthor.weforyoufoundation.helper.Validations;

import org.json.JSONObject;


public class ForgotActivity extends AppCompatActivity {


    private EditText etMobile;
    private TextView tvSubmit,tvTerms;
    private Validations validations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        getSupportActionBar().hide();
        removePhoneKeypad();
        validations = new Validations();

        etMobile = (EditText) findViewById(R.id.etMobile);
        tvSubmit = (TextView) findViewById(R.id.tvSubmit);
        tvTerms= (TextView) findViewById(R.id.tvTerms);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForgotActivity.this,LoginActivity.class);
                startActivityForResult(i, 1);
                ForgotActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out);
                finish();
            }
        });

        tvTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForgotActivity.this,LoginActivity.class);
                startActivityForResult(i, 1);
                ForgotActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out);
                finish();
            }
        });
    }
    public void removePhoneKeypad() {
        if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
            System.out.println("getCurrentFocus() in frag");
            InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

            IBinder binder = getCurrentFocus().getWindowToken();
            inputManager.hideSoftInputFromWindow(binder,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private boolean isValid() {


        if (validations.isBlank(etMobile)) {
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etMobile.startAnimation(shake);
            etMobile.setError(IErrors.EMPTY);
            return false;
        }

        return true;
    }


}

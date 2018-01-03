package com.grepthor.weforyoufoundation;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.grepthor.weforyoufoundation.Showcase.ShowcaseActivity;
import com.grepthor.weforyoufoundation.constant.IErrors;
import com.grepthor.weforyoufoundation.helper.ConnectionDetector;
import com.grepthor.weforyoufoundation.helper.SharedPreferenceManager;
import com.grepthor.weforyoufoundation.helper.Validations;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private TextView tvSignIn,tvSignup/*, tvSignup*/;
    TextView tvForgot;
    private Validations validations;
    private ImageView ivSeenPassword, ivUnseenPassword;
    private EditText etLoginPassword, etLoginUsername;
    private SharedPreferenceManager sharedPrefMgr;
    private ProgressDialog prog;
    private String token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        getSupportActionBar().hide();
        removePhoneKeypad();
        validations = new Validations();

        tvSignup=(TextView)findViewById(R.id.tvSignup);
        sharedPrefMgr = new SharedPreferenceManager(this);
        sharedPrefMgr.connectDB();
        boolean islogin = sharedPrefMgr.getBoolean("IsLogin");
        sharedPrefMgr.closeDB();

        if(islogin)
        {

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        tvSignIn = (TextView) findViewById(R.id.tvSignIn);
        tvForgot = (TextView) findViewById(R.id.tvForgot);
   /*     tvSignup = (TextView) findViewById(R.id.tvSignup);*/
        ivSeenPassword = (ImageView) findViewById(R.id.ivSeenPassword);
        ivUnseenPassword = (ImageView) findViewById(R.id.ivUnSeenPassword);
        etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);
        etLoginUsername = (EditText) findViewById(R.id.etLoginUsername);


        ivUnseenPassword.setVisibility(View.VISIBLE);
        etLoginPassword.setTransformationMethod(PasswordTransformationMethod
                .getInstance());

        ivSeenPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivSeenPassword.setVisibility(View.INVISIBLE);
                ivUnseenPassword.setVisibility(View.VISIBLE);
                etLoginPassword.setTransformationMethod(PasswordTransformationMethod
                        .getInstance());


            }
        });
        ivUnseenPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivUnseenPassword.setVisibility(View.INVISIBLE);
                ivSeenPassword.setVisibility(View.VISIBLE);
                etLoginPassword.setTransformationMethod(HideReturnsTransformationMethod
                        .getInstance());

            }
        });tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this,RegestrationActivity.class);
                startActivityForResult(i, 1);
                LoginActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out);

            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValid()) {
                    ConnectionDetector connectionDetector = ConnectionDetector.getInstance(LoginActivity.this);
                    if (connectionDetector.isConnectionAvailable()) {


                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                        } else {
                        Toast.makeText(LoginActivity.this, "Please check your internet", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

/*        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivityForResult(i, 1);
                LoginActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out);
            }
        });*/
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivityForResult(i, 1);
                LoginActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out);

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

    private boolean isValid() {


        if (validations.isBlank(etLoginUsername)) {
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etLoginUsername.startAnimation(shake);
            etLoginUsername.setError(IErrors.EMPTY);
            return false;
        }
        if (validations.isBlank(etLoginPassword)) {
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etLoginPassword.startAnimation(shake);
            etLoginPassword.setError(IErrors.EMPTY);
            return false;
        }

        return true;
    }



}

package com.grepthor.weforyoufoundation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.grepthor.weforyoufoundation.Showcase.ShowcaseActivity;
import com.grepthor.weforyoufoundation.helper.AppController;


public class SplashActivity extends AppCompatActivity {

    private ImageView ivBackground;

    private Typeface fontHindi;
TextView tvslogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        ivBackground = (ImageView) findViewById(R.id.ivBackground);
        tvslogan=(TextView)findViewById(R.id.tvslogan);
        fontHindi = Typeface.createFromAsset(getAssets(),"fonts/Blacksword.otf");
//        if (Utils.requestingLocationUpdates(this)) {
//            if (!checkPermissions()) {
//                requestPermissions();
//            }
//        }
        tvslogan.setTypeface(fontHindi);

        if (AppController.isInternet(this)) {
            Thread timer = new Thread() {
                public void run() {
                    try {

                        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                        ivBackground.startAnimation(animation2);
                    /*    rlName.startAnimation(animation2);*/

                        animation2.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                            Intent i = new Intent(SplashActivity.this,ShowcaseActivity.class);
                            startActivityForResult(i, 1);
                            SplashActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out);
                            finish();

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                    } finally {
                    }
                }
            };
            timer.start();
        }
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        mRequestLocationUpdatesButton = (Button) findViewById(R.id.request_location_updates_button);
//
//        mRequestLocationUpdatesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!checkPermissions()) {
//                    requestPermissions();
//                } else {
//                    mService.requestLocationUpdates();
//                }
//            }
//        });
//
////        if (!checkPermissions()) {
////            //requestPermissions();
////        } else {
////            mService.requestLocationUpdates();
////        }
//
//        //mService.removeLocationUpdates();
//
//
//        Utils.requestingLocationUpdates(this);
//        bindService(new Intent(this, LocationUpdatesService.class), mServiceConnection,
//                Context.BIND_AUTO_CREATE);
//    }
//
//
//    @Override
//    protected void onStop() {
//        if (mBound) {
//            // Unbind from the service. This signals to the service that this activity is no longer
//            // in the foreground, and the service can respond by promoting itself to a foreground
//            // service.
//            unbindService(mServiceConnection);
//            mBound = false;
//        }
//        super.onStop();
//    }
//
//    /**
//     * Returns the current state of the permissions needed.
//     */
//    private boolean checkPermissions() {
//        return  PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION);
//    }
//
//    private void requestPermissions() {
//        boolean shouldProvideRationale =
//                ActivityCompat.shouldShowRequestPermissionRationale(this,
//                        android.Manifest.permission.ACCESS_FINE_LOCATION);
//
//        // Provide an additional rationale to the user. This would happen if the user denied the
//        // request previously, but didn't check the "Don't ask again" checkbox.
//        if (shouldProvideRationale) {
//            Log.i(TAG, "Displaying permission rationale to provide additional context.");
//            Snackbar.make(
//                    findViewById(R.id.activity_main),
//                    R.string.permission_rationale,
//                    Snackbar.LENGTH_INDEFINITE)
//                    .setAction(R.string.ok, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            // Request permission
//                            ActivityCompat.requestPermissions(SplashActivity.this,
//                                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                                    REQUEST_PERMISSIONS_REQUEST_CODE);
//                        }
//                    })
//                    .show();
//        } else {
//            Log.i(TAG, "Requesting permission");
//            // Request permission. It's possible this can be auto answered if device policy
//            // sets the permission in a given state or the user denied the permission
//            // previously and checked "Never ask again".
//            ActivityCompat.requestPermissions(SplashActivity.this,
//                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                    REQUEST_PERMISSIONS_REQUEST_CODE);
//        }
//    }
//
//    /**
//     * Callback received when a permissions request has been completed.
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        Log.i(TAG, "onRequestPermissionResult");
//        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
//            if (grantResults.length <= 0) {
//                // If user interaction was interrupted, the permission request is cancelled and you
//                // receive empty arrays.
//                Log.i(TAG, "User interaction was cancelled.");
//            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission was granted.
//                mService.requestLocationUpdates();
//            } else {
//                // Permission denied.
//                Snackbar.make(
//                        findViewById(R.id.activity_main),
//                        R.string.permission_denied_explanation,
//                        Snackbar.LENGTH_INDEFINITE)
//                        .setAction(R.string.settings, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                // Build intent that displays the App settings screen.
//                                Intent intent = new Intent();
//                                intent.setAction(
//                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                Uri uri = Uri.fromParts("package",
//                                        BuildConfig.APPLICATION_ID, null);
//                                intent.setData(uri);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                            }
//                        })
//                        .show();
//            }
//        }
//    }
}

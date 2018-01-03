package com.grepthor.weforyoufoundation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;




public class ContactUsActivity extends AppCompatActivity {

    private TextView textViewWeb, tvContactPhone2, textViewPhone, textViewmail, tvContactPhone3, tvContactPhone4;

    private LinearLayout llContactWeb;
    private LinearLayout llContactTel;
    private LinearLayout llContactTel2;
    private LinearLayout llContactEmail;
    private LinearLayout llEmailIdOne,llEmailIdTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contact Us");
        textViewWeb = (TextView) findViewById(R.id.tvContactWeb);
        textViewPhone = (TextView) findViewById(R.id.tvContactPhone);
        tvContactPhone2 = (TextView) findViewById(R.id.tvContactPhone2);
        textViewmail = (TextView) findViewById(R.id.tvContactEmail);

        llContactWeb = (LinearLayout) findViewById(R.id.llContactWeb) ;
        llContactTel = (LinearLayout) findViewById(R.id.llContactTel) ;
        llContactTel2 = (LinearLayout) findViewById(R.id.llContactTel2) ;
        llContactEmail = (LinearLayout) findViewById(R.id.llContactEmail) ;

        llEmailIdOne = (LinearLayout) findViewById(R.id.llEmailIdOne);
        llEmailIdTwo = (LinearLayout) findViewById(R.id.llEmailIdTwo);

        llContactWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url =getApplicationContext().getString(R.string.contact_web);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        llContactEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = getApplicationContext().getString(R.string.contact_email);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        llContactTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + getResources().getString(R.string.telephone_1)));
                startActivity(callIntent);
            }
        });
        llContactTel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + getResources().getString(R.string.telephone_2)));
                startActivity(callIntent);
            }
        });


        llEmailIdOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent email = new Intent(Intent.ACTION_SEND);
                String stringEmailId = getApplicationContext().getString(R.string.contact_email_id_one);
                email.putExtra(Intent.ACTION_SENDTO, stringEmailId);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{stringEmailId});
                email.putExtra(Intent.ACTION_SENDTO, stringEmailId);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client"));
            }
        });

        llEmailIdTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent email = new Intent(Intent.ACTION_SEND);
                String stringEmailId = getApplicationContext().getString(R.string.contact_email_id_two);
                email.putExtra(Intent.ACTION_SENDTO, stringEmailId);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{stringEmailId});
                email.putExtra(Intent.ACTION_SENDTO, stringEmailId);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client"));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

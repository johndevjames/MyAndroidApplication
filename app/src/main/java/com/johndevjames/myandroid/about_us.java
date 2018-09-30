package com.johndevjames.myandroid;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class about_us extends AppCompatActivity {
    LoginDataBaseAdapter LB;
    Button check_update;
    Createdetails createdetails;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_about_us);
        this.check_update = (Button) findViewById(R.id.button26);
        this.check_update.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String appPackageName = about_us.this.getPackageName();
                try {
                    about_us.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + appPackageName)));
                } catch (ActivityNotFoundException e) {
                    about_us.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void privacy_click(View v) {
        // does something very interesting
        try {
            about_us.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://invoiz.in/policy/so_privacy_policy.html" )));
        } catch (ActivityNotFoundException e) {
            about_us.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://invoiz.in/policy/so_privacy_policy.html")));
        }

    }

    public void t_c_click(View v) {
        // does something very interesting

        try {
            about_us.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://invoiz.in/policy/so_terms_and_conditions.html" )));
        } catch (ActivityNotFoundException e) {
            about_us.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://invoiz.in/policy/so_terms_and_conditions.html")));
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", 0);
        Intent intentSettings;
        switch (item.getItemId()) {
            case 16908332:
                intentSettings = new Intent(getApplicationContext(), MenuIconsAdmin.class);
                intentSettings.putExtra("key_id", prefs.getInt("UserId", 0));
                startActivity(intentSettings);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                return true;
            default:
                int id = item.getItemId();
                if (id == R.id.action_Menu) {
                    intentSettings = new Intent(getApplicationContext(), MenuIconsAdmin.class);
                    intentSettings.putExtra("key_id", prefs.getInt("UserId", 0));
                    startActivity(intentSettings);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    return true;
                } else if (id == R.id.action_Add_Order) {
                    Intent intentViewCustomer = new Intent(getApplicationContext(), Order_customer.class);
                    intentViewCustomer.putExtra("key_id", prefs.getInt("UserId", 0));
                    startActivity(intentViewCustomer);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    return true;
                } else if (id == R.id.action_View_Order) {
                    intentSettings = new Intent(getApplicationContext(), Order2.class);
                    intentSettings.putExtra("key_id", prefs.getInt("UserId", 0));
                    startActivity(intentSettings);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    return true;
                } else if (id == R.id.action_Add_customer) {
                    intentSettings = new Intent(getApplicationContext(), Addcustomer.class);
                    intentSettings.putExtra("key_id", prefs.getInt("UserId", 0));
                    startActivity(intentSettings);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    return true;
                } else if (id == R.id.action_view_customer) {
                    intentSettings = new Intent(getApplicationContext(), Customer_details.class);
                    intentSettings.putExtra("key_id", prefs.getInt("UserId", 0));
                    startActivity(intentSettings);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    return true;
                } else if (id == R.id.add_user_settings) {
                    intentSettings = new Intent(getApplicationContext(), Change_username.class);
                    intentSettings.putExtra("key_id", prefs.getInt("UserId", 0));
                    startActivity(intentSettings);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    return true;
                } else if (id == R.id.change_pass_settings) {
                    intentSettings = new Intent(getApplicationContext(), change_password.class);
                    intentSettings.putExtra("key_id", prefs.getInt("UserId", 0));
                    startActivity(intentSettings);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    return true;
                } else if (id == R.id.signout_settings) {
                    Editor editor = getSharedPreferences("MyPrefsFile", 0).edit();
                    editor.remove("UserId");
                    editor.commit();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    return true;
                } else if (id == R.id.about_version) {
                    Toast.makeText(getApplicationContext(), "Version Code " + 7 + " Version Name " + BuildConfig.VERSION_NAME, Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id != R.id.about_us) {
                    return super.onOptionsItemSelected(item);
                } else {
                    startActivity(new Intent(getApplicationContext(), about_us.class));
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    return true;
                }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        //this.createdetails.close();
//        this.LB.close();
    }
}

package com.johndevjames.myandroidapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.miguelcatalan.materialsearchview.BuildConfig;
import java.util.HashMap;
import java.util.Map;

public class change_password extends AppCompatActivity {
    LoginDataBaseAdapter LB;
    Button btnchangepassword;
    EditText editTextcurrentpass;
    EditText editTextnewpass;
    EditText editconfirmpass;
    TextView setUsername;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String user= getSharedPreferences("MyPrefsFile", 0).getString("UserName", "");
        if(user.contains("Demo"))
        {
            //moveTaskToBack(true);
            //super.onBackPressed();
            finish();
            Toast.makeText(change_password.this, "This option is unavailable in Trail Version", Toast.LENGTH_LONG).show();
        }
        requestWindowFeature(1);
        //getWindow().setFlags(1024, 1024);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_change_password);
        this.LB = new LoginDataBaseAdapter(this);
        this.LB = this.LB.open();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Integer key_id = Integer.valueOf(getIntent().getExtras().getInt("key_id"));
        this.editTextcurrentpass = (EditText) findViewById(R.id.editText9);
        this.editTextnewpass = (EditText) findViewById(R.id.editText8);
        this.editconfirmpass = (EditText) findViewById(R.id.editText10);
        this.setUsername = (TextView) findViewById(R.id.textView53);
        this.setUsername.setText("Welcome User: " + this.LB.getUsername(key_id));
        this.btnchangepassword = (Button) findViewById(R.id.button10);
        this.btnchangepassword.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String currentpass = change_password.this.editTextcurrentpass.getText().toString();
                String newpass = change_password.this.editTextnewpass.getText().toString();
                String confirmpass = change_password.this.editconfirmpass.getText().toString();
                String EuId = change_password.this.LB.getEid(key_id);
                if (currentpass.equals(BuildConfig.FLAVOR)) {
                    change_password.this.editTextcurrentpass.setError("Enter the Current Password");
                    change_password.this.editTextcurrentpass.requestFocus(change_password.this.editTextcurrentpass.length());
                } else if (newpass.equals(BuildConfig.FLAVOR)) {
                    change_password.this.editTextnewpass.setError("Enter a Valid Password");
                    change_password.this.editTextnewpass.requestFocus(change_password.this.editTextnewpass.length());
                } else if (confirmpass.equals(BuildConfig.FLAVOR) || !newpass.equals(confirmpass)) {
                    change_password.this.editconfirmpass.setError("Passwords do not match");
                    change_password.this.editconfirmpass.requestFocus(change_password.this.editconfirmpass.length());
                } else {
                    change_password.this.uploadnewpassworddetails(EuId, currentpass, newpass);
                    change_password.this.editTextcurrentpass.getText().clear();
                    change_password.this.editTextnewpass.getText().clear();
                    change_password.this.editconfirmpass.getText().clear();
                }
            }
        });
    }

    private boolean uploadnewpassworddetails(String Euid, String oldpass, String newpass) {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String str = Euid;
        final String str2 = oldpass;
        final String str3 = newpass;
        StringRequest postRequest = new StringRequest(1, "http://smartmds.in/GMobAppService.asmx/fn_ChangePass", new Listener<String>() {
            public void onResponse(String response) {
                Log.d("Response", response);
                if (response.contains("Current Password Is Incorrect")) {
                    Toast.makeText(change_password.this, "Current Password Is Incorrect", Toast.LENGTH_LONG).show();
                } else if (response.contains("Password Changed Sucessfully")) {
                    Toast.makeText(change_password.this, "Password Changed Sucessfully", Toast.LENGTH_LONG).show();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", String.valueOf(error));
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("ai_UserId", str);
                params.put("as_curpwd", str2);
                params.put("as_newpwd", str3);
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, 1, 1.0f));
        queue.add(postRequest);
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        String MY_PREFS_NAME = "MyPrefsFile";
        int key_id = getSharedPreferences("MyPrefsFile", 0).getInt("UserId", 0);
        getMenuInflater().inflate(R.menu.menu_tabs, menu);
        if (this.LB.getStatus(Integer.valueOf(key_id)).equals("N")) {
            menu.getItem(5).setEnabled(false);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                return true;
            default:
                int id = item.getItemId();
                String MY_PREFS_NAME = "MyPrefsFile";
                SharedPreferences prefs = getSharedPreferences("MyPrefsFile", 0);
                Intent intentSettings;
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
                    Toast.makeText(getApplicationContext(), "Version Code " + 7 + " Version Name " + BuildConfig.VERSION_NAME,Toast.LENGTH_SHORT).show();
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
        this.LB.close();
    }
}

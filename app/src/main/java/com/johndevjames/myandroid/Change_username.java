package com.johndevjames.myandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

public class Change_username extends AppCompatActivity {
    LoginDataBaseAdapter LB;
    CheckBox admin;
    Button btnCreateAccount;
    Builder builder;
    public SQLiteDatabase db;
    EditText editTextPassword;
    EditText editTextUserName;
    EditText edit_max_no;
    EditText editconfirmpass;
    EditText editprefix;
    String type = null;
    CheckBox user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String user= getSharedPreferences("MyPrefsFile", 0).getString("UserName", "");
        if(user.contains("Demo"))
        {
            this.builder = new Builder(Change_username.this);
            this.builder.setMessage("This option is not completely available for the trail version visit the website for more details").setPositiveButton("Visit the website", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://invoiz.in/SOApp/")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://invoiz.in/SOApp/")));
                    }


                }
            }).setNegativeButton("Maybe later", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            Change_username.this.builder.create().show();
            //moveTaskToBack(true);
            //super.onBackPressed();
            //finish();
           // Toast.makeText(Change_username.this, "This option is unavailable in Trail Version", Toast.LENGTH_LONG).show();

        }
        requestWindowFeature(1);
       // getWindow().setFlags(1024, 1024);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_change_username);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.LB = new LoginDataBaseAdapter(this);
        this.LB = this.LB.open();
        this.editTextUserName = (EditText) findViewById(R.id.editText10);
        this.editTextPassword = (EditText) findViewById(R.id.editText9);
        this.editconfirmpass = (EditText) findViewById(R.id.editText8);
        this.editprefix = (EditText) findViewById(R.id.editText12);
        this.edit_max_no = (EditText) findViewById(R.id.editText13);
        this.admin = (CheckBox) findViewById(R.id.checkBox);
        this.user = (CheckBox) findViewById(R.id.checkBox2);
        this.admin.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                Change_username.this.user.setChecked(false);
                return false;
            }
        });
        this.user.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                Change_username.this.admin.setChecked(false);
                return false;
            }
        });
        this.btnCreateAccount = (Button) findViewById(R.id.button10);
        this.btnCreateAccount.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Change_username.this.builder = new Builder(Change_username.this);
                String userName = Change_username.this.editTextUserName.getText().toString();
                String pass = Change_username.this.editTextPassword.getText().toString();
                String confirmpass = Change_username.this.editconfirmpass.getText().toString();
                String prefix = Change_username.this.editprefix.getText().toString();
                String max_no = Change_username.this.edit_max_no.getText().toString();
                String Euid =  getSharedPreferences("MyPrefsFile", 0).getString("Euid","");
                if (userName.equals(BuildConfig.FLAVOR)) {
                    Change_username.this.editTextUserName.setError("Enter a valid Name");
                    Change_username.this.editTextUserName.requestFocus(Change_username.this.editTextUserName.length());
                } else if (prefix.equals(BuildConfig.FLAVOR)) {
                    Change_username.this.editprefix.setError("Enter a Prefix No");
                    Change_username.this.editprefix.requestFocus(Change_username.this.editprefix.length());
                } else if (max_no.equals(BuildConfig.FLAVOR)) {
                    Change_username.this.edit_max_no.setError("Enter a Max No");
                    Change_username.this.edit_max_no.requestFocus(Change_username.this.edit_max_no.length());
                } else if (pass.equals(BuildConfig.FLAVOR)) {
                    Change_username.this.editTextPassword.setError("Enter a Valid Password");
                    Change_username.this.editTextPassword.requestFocus(Change_username.this.editTextPassword.length());
                } else if (confirmpass.equals(BuildConfig.FLAVOR) || !pass.equals(confirmpass)) {
                    Change_username.this.editconfirmpass.setError("Passwords do not match");
                    Change_username.this.editconfirmpass.requestFocus(Change_username.this.editconfirmpass.length());
                } else if (Change_username.this.admin.isChecked() || Change_username.this.user.isChecked()) {
                    if (Change_username.this.admin.isChecked()) {
                        Change_username.this.type = "S";
                    } else if (Change_username.this.user.isChecked()) {
                        Change_username.this.type = "N";
                    }
                    Change_username.this.uploadnewuserdetails(userName, pass, Change_username.this.type, prefix, max_no,Euid);
                    Change_username.this.editTextUserName.getText().clear();
                    Change_username.this.editTextPassword.getText().clear();
                    Change_username.this.editconfirmpass.getText().clear();
                    Change_username.this.editprefix.getText().clear();
                    Change_username.this.edit_max_no.getText().clear();
                } else {
                    Change_username.this.builder = new Builder(Change_username.this);
                    Change_username.this.builder.setMessage("Select the Type");
                    Change_username.this.builder.create().show();
                }
            }
        });
    }

    private boolean uploadnewuserdetails(String usrname, String pass, String type, String prefix, String max_no,String company_ID) {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String str = usrname;
        final String str2 = pass;
        final String str3 = type;
        final String str4 = prefix;
        final String str5 = max_no;
        final String str6 =company_ID;
        StringRequest postRequest = new StringRequest(1, "http://smartmds.in/GMobAppService.asmx/fn_InsertAppUser", new Listener<String>() {
            public void onResponse(String response) {
                Log.d("Response", response);
                if (response.contains("User Name Already Registered")) {
                    Toast.makeText(Change_username.this, "User Name Already Registered", Toast.LENGTH_LONG).show();
                } else if (response.contains("Record Added Sucessfully")) {
                    Toast.makeText(Change_username.this, "Record Added Sucessfully", Toast.LENGTH_LONG).show();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", String.valueOf(error));
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("username", str);
                params.put("pwd", str2);
                params.put("AccPower", str3);
                params.put("prefix", str4);
                params.put("maxid", str5);
                params.put("ls_euid",str6);
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, 1, 1.0f));
        queue.add(postRequest);
        Log.d("Error.Response", String.valueOf(postRequest));
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
            case 0:
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

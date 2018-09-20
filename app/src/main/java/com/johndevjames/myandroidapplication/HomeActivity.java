package com.johndevjames.myandroidapplication;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.ads.AdView;
import com.miguelcatalan.materialsearchview.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends Activity {
    Createdetails Cd;
    ProgressDialog Pdialog;
    Button btnSignIn1;
    Button demo;
    ImageView img;
    LoginDataBaseAdapter loginDataBaseAdapter;
    private AdView mAdView;
    private AdView mAdView2;
    SharedPreferences prefs;
    Button register_new_company;
    int UserId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.main);
        this.img = (ImageView) findViewById(R.id.imageView4);
        this.demo = (Button) findViewById(R.id.button24);
        this.Cd = new Createdetails(this);
        this.Pdialog = new ProgressDialog(this);
        String MY_PREFS_NAME = "MyPrefsFile";

        prefs = HomeActivity.this.getSharedPreferences("MyPrefsFile", 0);
        UserId = HomeActivity.this.prefs.getInt("UserId", 0);
        String companyName = this.prefs.getString("CompanyName", null);
        int i = 0;

        if (UserId!= 0) {
            Intent intentSettings = new Intent(HomeActivity.this.getApplicationContext(), MenuIconsAdmin.class);
            intentSettings.putExtra("key_id", UserId);
            HomeActivity.this.startActivity(intentSettings);
            HomeActivity.this.overridePendingTransition(R.anim.enter, R.anim.exit);
        }

        if (companyName != null && companyName.contains("Dev")) {
            i = 1;
            this.demo.setVisibility(View.VISIBLE);
        }
        String imgUrl = null;
        switch (UserId) {
            case 0/*0*/:
                imgUrl = "http://www.goldensoftwareservices.com/images/SO2.png";
                break;
            case 1/*1*/:
                imgUrl = "http://www.goldensoftwareservices.com/images/Dev1.png";
                break;
            case R.styleable.View_paddingStart /*2*/:
                imgUrl = BuildConfig.FLAVOR;
                break;
        }

        Glide.with(this).load(imgUrl).thumbnail(0.5f).crossFade()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).into(this.img);

        this.loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        this.loginDataBaseAdapter = this.loginDataBaseAdapter.open();
        this.Cd.open();
        this.demo.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                HomeActivity.this.Cd.deleteTABLE_TableExists();
                HomeActivity.this.makeJsonObjReq("demo", "demo");
            }
        });
        this.btnSignIn1 = (Button) findViewById(R.id.buttonSignIN);
        this.register_new_company = (Button) findViewById(R.id.button9);
        this.register_new_company.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                HomeActivity.this.startActivity(new Intent(HomeActivity.this.getApplicationContext(), addnewcompany.class));
            }
        });
        this.btnSignIn1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String MY_PREFS_NAME = "MyPrefsFile";

                String Status = HomeActivity.this.prefs.getString("Status", null);
                HomeActivity.this.Pdialog.setMessage("Loading....Please wait");
                HomeActivity.this.Pdialog.setCancelable(false);
                HomeActivity.this.Pdialog.setInverseBackgroundForced(false);
                HomeActivity.this.Pdialog.show();
                Intent intentAddCust;
                if (UserId != 0 && Status.matches("Y")) {
                    intentAddCust = new Intent(HomeActivity.this.getApplicationContext(), MenuIconsAdmin.class);
                    intentAddCust.putExtra("key_id", UserId);
                    HomeActivity.this.startActivity(intentAddCust);
                } else if (UserId == 0 || !Status.matches("N")) {
                    HomeActivity.this.Pdialog.hide();
                    HomeActivity.this.signIn();
                } else {
                    intentAddCust = new Intent(HomeActivity.this.getApplicationContext(), MenuIconsAdmin.class);
                    intentAddCust.putExtra("key_id", UserId);
                    HomeActivity.this.startActivity(intentAddCust);
                }
            }
        });
    }

    private void signIn() {
        Dialog dialog = new Dialog(this);
        Window window = dialog.getWindow();
        dialog.setContentView(R.layout.login);
        dialog.setTitle("Login");
        String MY_PREFS_NAME = "MyPrefsFile";
        this.prefs = getSharedPreferences("MyPrefsFile", 0);
        int UserId = this.prefs.getInt("UserId", 0);
        final EditText editTextUserName = (EditText) dialog.findViewById(R.id.editTextUserNameToLogin);
        final EditText editTextPassword = (EditText) dialog.findViewById(R.id.editTextPasswordToLogin);
        ((Button) dialog.findViewById(R.id.buttonSignIn)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                HomeActivity.this.Pdialog = new ProgressDialog(HomeActivity.this);
                if (userName.matches(BuildConfig.FLAVOR) || password.matches(BuildConfig.FLAVOR)) {
                    Toast.makeText(HomeActivity.this, " Enter Details Properly.... ", Toast.LENGTH_SHORT).show();
                    return;
                }
                HomeActivity.this.Pdialog.setMessage("Loading....Please wait");
                HomeActivity.this.Pdialog.setCancelable(false);
                HomeActivity.this.Pdialog.setInverseBackgroundForced(false);
                HomeActivity.this.Pdialog.show();
                if (userName.equals(HomeActivity.this.prefs.getString("UserName", null))) {
                    int user_Id = Integer.parseInt(HomeActivity.this.loginDataBaseAdapter.getuser_id(userName));
                    Intent intentAddCust = new Intent(HomeActivity.this.getApplicationContext(), MenuIconsAdmin.class);
                    String MY_PREFS_NAME = "MyPrefsFile";
                    Editor editor = HomeActivity.this.getSharedPreferences("MyPrefsFile", 0).edit();
                    editor.putInt("UserId", user_Id);
                    editor.commit();
                    intentAddCust.putExtra("key_id", user_Id);
                    HomeActivity.this.startActivity(intentAddCust);
                    return;
                }
                HomeActivity.this.Cd.deleteTABLE_TableExists();
                HomeActivity.this.makeJsonObjReq(userName, password);
            }
        });
        dialog.show();
        window.setLayout(-1, -2);
    }

    public void makeJsonObjReq(String USER, String PWD) {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String str = USER;
        final String str2 = PWD;
        StringRequest postRequest = new StringRequest(1, "http://smartmds.in/GMobAppService.asmx/fn_CheckLogin", new Listener<String>() {
            public void onResponse(String response) {
                Log.e("User_Response", response);
                JSONArray jsonResponse = null;
                try {
                    jsonResponse = new JSONArray(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String BranchName = null;
                int CompanyId = 0;
                String CompanyName = null;
                String Email = null;
                String ComStatus = null;
                String Eid = null;
                String Error = null;
                String ErroMsg = null;
                String Euid = null;
                String Mob = null;
                String Status = null;
                int UserId = 0;
                String UserName = null;
                Integer Max_order_no = Integer.valueOf(0);
                for (int i = 0; i < jsonResponse.length(); i++) {
                    JSONObject cast = jsonResponse.optJSONObject(i);
                    try {
                        Error = (String) cast.get("Error");
                        if (Error.matches("True")) {
                            HomeActivity.this.Pdialog.hide();
                            Toast.makeText(HomeActivity.this, "Incorrect User Name or Password", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        BranchName = (String) cast.get("branchname");
                        CompanyId = ((Integer) cast.get("companyid")).intValue();
                        CompanyName = (String) cast.get("companyname");
                        ComStatus = (String) cast.get("ComStatus");
                        Eid = (String) cast.get("eid");
                        Email = (String) cast.get("Email");
                        Error = (String) cast.get("Error");
                        ErroMsg = (String) cast.get("ErrorMsg");
                        Euid = (String) cast.get("euid");
                        Mob = (String) cast.get("mob");
                        Status = (String) cast.get("accpower");
                        UserId = ((Integer) cast.get("userid")).intValue();
                        UserName = (String) cast.get("UserName");
                        String prefix = (String) cast.get("prefix");
                        Max_order_no = Integer.valueOf(((Integer) cast.get("maxid")).intValue());
                        if (Error.matches("False")) {
                            HomeActivity.this.loginDataBaseAdapter.insertEntry(BranchName, Integer.valueOf(CompanyId), CompanyName, ComStatus, Eid, Email, Error, ErroMsg, Euid, UserName, Mob, UserId, Status, prefix, Max_order_no);
                            Intent intent = new Intent(HomeActivity.this.getApplicationContext(), MenuIconsAdmin.class);
                            intent.putExtra("key_id", UserId);
                            HomeActivity.this.startActivity(intent);
                        } else if (Error.matches("True")) {
                            Toast.makeText(HomeActivity.this, "Incorrect User Name or Password", Toast.LENGTH_SHORT).show();
                            HomeActivity.this.Pdialog.hide();
                        }
                    } catch (JSONException e22) {
                        e22.printStackTrace();
                    }
                    String MY_PREFS_NAME = "MyPrefsFile";
                    Editor editor = HomeActivity.this.getSharedPreferences("MyPrefsFile", 0).edit();
                    editor.putString("BranchName", BranchName);
                    editor.putString("CompanyId", String.valueOf(CompanyId));
                    editor.putString("CompanyName", CompanyName);
                    editor.putString("ComStatus", ComStatus);
                    editor.putString("Eid", Eid);
                    editor.putString("Email", Email);
                    editor.putString("Error", Error);
                    editor.putString("ErroMsg", ErroMsg);
                    editor.putString("Euid", Euid);
                    editor.putString("Mob", Mob);
                    editor.putString("Status", Status);
                    editor.putInt("UserId", UserId);
                    editor.putString("UserName", UserName);
                    editor.commit();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", String.valueOf(error));
                Toast.makeText(HomeActivity.this, "Check Connection and try again later", Toast.LENGTH_LONG).show();
                HomeActivity.this.Pdialog.hide();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("strUserName", str);
                params.put("strPwd", str2);
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, 1, 1.0f));
        queue.add(postRequest);
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.Cd.close();
        this.loginDataBaseAdapter.close();
    }
}

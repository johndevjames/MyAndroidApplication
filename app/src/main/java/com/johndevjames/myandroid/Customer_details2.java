package com.johndevjames.myandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class Customer_details2 extends AppCompatActivity {
    Createdetails Cd;
    LoginDataBaseAdapter LB;
    Builder builder;
    Button delbtn;
    Button editbtn;
    int newpostion;
    String position;
    RecyclerView recyclerView;
    Tutorial tt;
    private List<Tutorial> tutorialList = new ArrayList();
    TutorialsAdapter tutorialsAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_customer_details2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.editbtn = (Button) findViewById(R.id.button19);
        this.delbtn = (Button) findViewById(R.id.button20);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.Cd = new Createdetails(this);
        this.Cd = this.Cd.open();
        this.LB = new LoginDataBaseAdapter(this);
        this.LB = this.LB.open();
        List Details = new ArrayList();
        position = getIntent().getExtras().getString("pos");
        Log.d(this.position, "String");
        tutorialList = Cd.getIDSinlgeE(position);
        this.tt = (Tutorial) this.tutorialList.get(0);
        newpostion = tt.getid().intValue();

        if (Cd.getnullSinlgeEE(Integer.valueOf(position)).equals("0")) {
            this.editbtn.setVisibility(View.VISIBLE);
            this.delbtn.setVisibility(View.VISIBLE);
        } else {
            this.editbtn.setVisibility(View.GONE);
            this.delbtn.setVisibility(View.GONE);
        }
        this.tutorialsAdapter = new TutorialsAdapter(this.tutorialList);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setAdapter(this.tutorialsAdapter);
        this.editbtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intentSignUP = new Intent(Customer_details2.this.getApplicationContext(), Editcustomer.class);
                intentSignUP.putExtra("post", Integer.toString(newpostion));
                Customer_details2.this.startActivity(intentSignUP);
                Customer_details2.this.overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
        this.delbtn.setOnClickListener(new OnClickListener() {
            public void onClick(final View v) {
                Customer_details2.this.builder = new Builder(Customer_details2.this);
                Customer_details2.this.builder.setMessage("Are you sure").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Customer_details2.this.Cd.deletecustomer(Integer.valueOf(Customer_details2.this.newpostion));
                        Snackbar bar = Snackbar.make(v, Customer_details2.this.tt.getShopname() + "Deleted", 0);
                        bar.show();
                        bar.setActionTextColor(getResources().getColor(R.color.apple_grey));
                        ((TextView) bar.getView().findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.teal_green));
                        Customer_details2.this.startActivity(new Intent(Customer_details2.this.getApplicationContext(), Customer_details.class));
                        Customer_details2.this.overridePendingTransition(R.anim.enter, R.anim.exit);
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                Customer_details2.this.builder.create().show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        String MY_PREFS_NAME = "MyPrefsFile";
        int key_id = getSharedPreferences("MyPrefsFile", 0).getInt("UserId", 0);
        getMenuInflater().inflate(R.menu.menu_tabs, menu);
        this.LB.open();
        if (this.LB.getStatus(Integer.valueOf(key_id)).equals("N")) {
            menu.getItem(5).setEnabled(false);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", 0);
        Intent intentSettings;
        switch (item.getItemId()) {
            case 16908332:
                intentSettings = new Intent(getApplicationContext(), Customer_details.class);
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
        this.LB.close();
        this.Cd.close();
    }
}

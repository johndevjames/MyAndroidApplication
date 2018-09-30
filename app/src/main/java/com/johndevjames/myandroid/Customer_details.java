package com.johndevjames.myandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Customer_details extends AppCompatActivity {
    Createdetails Cd;
    LoginDataBaseAdapter LB;
    List PAR = new ArrayList();
    TextView Tx;
    CustomListAdapter5 adapter;
    CustomListAdapter4 adapter4;
    EditText editsearch;
    List<Tutorial2> tutorialList = new ArrayList();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_customer_details);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar4));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.editsearch = (EditText) findViewById(R.id.inputSearch);
        this.Tx = (TextView) findViewById(R.id.textView32);
        this.Cd = new Createdetails(this);
        this.Cd = this.Cd.open();
        this.Cd = this.Cd.read();
        this.LB = new LoginDataBaseAdapter(this);
        this.LB = this.LB.open();
        registerclickcallback();
        this.tutorialList = Cd.getSinlgeEntry();
        Tx.setText(String.valueOf(tutorialList.size()));
        this.adapter4 = new CustomListAdapter4(this, 0, this.tutorialList);
        ((ListView) findViewById(R.id.list_main)).setAdapter(this.adapter4);
        this.editsearch.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void afterTextChanged(Editable arg0) {
                Customer_details.this.adapter4.filter(Customer_details.this.editsearch.getText().toString().toLowerCase(Locale.getDefault()));
            }
        });
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

    private void registerclickcallback() {
        ((ListView) findViewById(R.id.list_main)).setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                for (int i = 0; i < tutorialList.size(); i++) {
                    PAR.add(i, ((Tutorial2) tutorialList.get(i)).getcustomer_id());
                }
                int newposition = ((Integer) PAR.get(position)).intValue();
                Intent intentCUstomerdetails2 = new Intent(Customer_details.this.getApplicationContext(), Customer_details2.class);
                intentCUstomerdetails2.putExtra("pos", Integer.toString(newposition));
                Customer_details.this.startActivity(intentCUstomerdetails2);
                Customer_details.this.overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
    }

    public void onBackPressed() {
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", 0);
        Intent intentSettings = new Intent(getApplicationContext(), MenuIconsAdmin.class);
        intentSettings.putExtra("key_id", prefs.getInt("UserId", 0));
        startActivity(intentSettings);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    public void onDestroy() {
        super.onDestroy();
        this.LB.close();
        this.Cd.close();
    }
}

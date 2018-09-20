package com.johndevjames.myandroidapplication;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.johndevjames.myandroidapplication.CustomListAdapter2.customImageListener2;
import java.util.ArrayList;
import java.util.Locale;

public class item_activity extends AppCompatActivity implements customImageListener2 {
    LoginDataBaseAdapter LB;
    ArrayList<DataModel> Order_Details;
    CustomListAdapter2 adapter;
    Createdetails createdetails;
    EditText editsearch;
    ListView listView;
    ArrayList<DataModel> r;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_item);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar8));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.createdetails = new Createdetails(this);
        this.createdetails = this.createdetails.open();
        this.LB = new LoginDataBaseAdapter(this);
        this.LB = this.LB.open();
        this.Order_Details = this.createdetails.getAllOrder();
        this.adapter = new CustomListAdapter2(this, 0, this.Order_Details);
        this.editsearch = (EditText) findViewById(R.id.editText7);
        this.listView = (ListView) findViewById(R.id.list_main2);
        this.listView.setAdapter(this.adapter);
        this.adapter.setCustomImageListner2(this);
        this.editsearch.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            public void afterTextChanged(Editable s) {
                item_activity.this.adapter.filter(item_activity.this.editsearch.getText().toString().toLowerCase(Locale.getDefault()));
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

    public void onImageClickListner2(int position) {
        DataModel dataModel = (DataModel) this.listView.getItemAtPosition(position);

        Intent ne=(new Intent(getApplicationContext(),addImage.class));
        ne.putExtra("Item_id",  dataModel.getItemId());
        startActivity(ne);

        //startActivity(new Intent(getApplicationContext(), addImage.class));

        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.createdetails.close();
    }
}

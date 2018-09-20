package com.johndevjames.myandroidapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Order_customer extends AppCompatActivity {
    Createdetails Cd;
    LoginDataBaseAdapter LB;
    TextView Tx;
    CustomListAdapter4 adapter;
    EditText editsearch;
    ListView listView;
    RecyclerView recyclerView;
    private List<Tutorial2> tutorialList = new ArrayList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_order_customer);
        this.LB = new LoginDataBaseAdapter(this);
        this.LB = this.LB.open();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar4));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.editsearch = (EditText) findViewById(R.id.inputSearch);
        this.Cd = new Createdetails(this);
        this.Cd = this.Cd.open();
        this.Cd = this.Cd.read();
        registerclickcallback();
        this.listView = (ListView) findViewById(R.id.list_main);
        List Details = new ArrayList();
        this.adapter = new CustomListAdapter4(this, 0, this.Cd.getAllCustDeatils());
        this.listView.setAdapter(this.adapter);
        this.editsearch.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void afterTextChanged(Editable arg0) {
                Order_customer.this.adapter.filter(Order_customer.this.editsearch.getText().toString().toLowerCase(Locale.getDefault()));
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
        final Integer key_id = Integer.valueOf(getIntent().getExtras().getInt("key_id"));
        final ListView listView = (ListView) findViewById(R.id.list_main);
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Tutorial2 dataModel = (Tutorial2) listView.getItemAtPosition(position);
                Integer customerid = dataModel.getcustomer_id();
                String MY_PREFS_NAME = "MyPrefsFile";
                final SharedPreferences prefs = Order_customer.this.getSharedPreferences("MyPrefsFile", 0);
                if (Order_customer.this.Cd.getnullSinlgeE(customerid) == null) {
                    Snackbar bar = Snackbar.make(view, dataModel.getCustomer_name() + "Sync the Customers First", 0).setAction("Sync", new OnClickListener() {
                        public void onClick(View v) {
                            Intent intentAddCust = new Intent(Order_customer.this.getApplicationContext(), MenuIconsAdmin.class);
                            intentAddCust.putExtra("key_id", prefs.getInt("UserId", 0));
                            Order_customer.this.startActivity(intentAddCust);
                            Order_customer.this.overridePendingTransition(R.anim.enter, R.anim.exit);
                        }
                    });
                    bar.show();
                    bar.setActionTextColor(getResources().getColor(R.color.apple_grey));
                    ((TextView) bar.getView().findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.teal_green));
                    return;
                }
                Intent intentCUstomerdetails2 = new Intent(Order_customer.this.getApplicationContext(), Order.class);
                intentCUstomerdetails2.putExtra("customer_id", customerid);
                intentCUstomerdetails2.putExtra("key_id", key_id);
                intentCUstomerdetails2.putExtra("old_order_no", 0);
                intentCUstomerdetails2.putExtra("old_customer_id", 0);
                intentCUstomerdetails2.putExtra("old_user_id", 0);
                Order_customer.this.startActivity(intentCUstomerdetails2);
                Order_customer.this.overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        this.Cd.close();
        this.LB.close();
    }
}

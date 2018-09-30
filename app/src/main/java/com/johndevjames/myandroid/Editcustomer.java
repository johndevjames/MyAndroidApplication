package com.johndevjames.myandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Editcustomer extends AppCompatActivity {
    LoginDataBaseAdapter LB;
    Button btnSubmit;
    Createdetails createdetails;
    Tutorial dataModel;
    EditText editTextDistrict;
    EditText editTextEmail;
    EditText editTextLandMark;
    EditText editTextMobileNumber;
    EditText editTextOwnerName;
    EditText editTextPhoneNumber;
    EditText editTextPin;
    EditText editTextShopAddress;
    EditText editTextShopArea;
    EditText editTextShopName;
    EditText editTextShopPlace;
    EditText editTextTin;
    String position;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        //getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_editcustomer);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar11));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.createdetails = new Createdetails(this);
        this.createdetails = this.createdetails.open();
        this.LB = new LoginDataBaseAdapter(this);
        this.LB = this.LB.open();
        List Details = new ArrayList();
        this.position = getIntent().getExtras().getString("post");
        dataModel = (Tutorial) createdetails.getIDSinlgeE(position).get(0);
        this.editTextShopName = (EditText) findViewById(R.id.et_customer_shop_name);
        this.editTextOwnerName = (EditText) findViewById(R.id.et_customer_owner_name);
        this.editTextShopPlace = (EditText) findViewById(R.id.et_customer_shop_place);
        this.editTextShopAddress = (EditText) findViewById(R.id.et_customer_shop_address);
        this.editTextPhoneNumber = (EditText) findViewById(R.id.et_customer_Phone_numberll);
        this.editTextMobileNumber = (EditText) findViewById(R.id.et_customer_Mobile_numbermb);
        this.editTextShopArea = (EditText) findViewById(R.id.et_customer_shop_area);
        this.editTextDistrict = (EditText) findViewById(R.id.et_customer_district);
        this.editTextEmail = (EditText) findViewById(R.id.et_customer_email);
        this.editTextPin = (EditText) findViewById(R.id.et_customer_pin);
        this.editTextTin = (EditText) findViewById(R.id.et_customer_tin_no);
        this.editTextLandMark = (EditText) findViewById(R.id.et_customer_land_mark);
        this.editTextShopName.setText(this.dataModel.getShopname());
        this.editTextOwnerName.setText(this.dataModel.getOwnerName());
        this.editTextShopPlace.setText(this.dataModel.getPlace());
        this.editTextShopAddress.setText(this.dataModel.getAddress());
        this.editTextPhoneNumber.setText(this.dataModel.getMobile1());
        this.editTextMobileNumber.setText(this.dataModel.getMobile2());
        this.editTextShopArea.setText(this.dataModel.getShopArea());
        this.editTextDistrict.setText(this.dataModel.getDistrict());
        this.editTextEmail.setText(this.dataModel.getEmail());
        this.editTextPin.setText(String.valueOf(this.dataModel.getPin()));
        this.editTextTin.setText(String.valueOf(this.dataModel.getTin()));
        this.editTextLandMark.setText(this.dataModel.getLand_mark());
        this.btnSubmit = (Button) findViewById(R.id.button5);
        this.btnSubmit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Editcustomer.this.createdetails.updateEntry("0", editTextShopName.getText().toString(),
                       editTextOwnerName.getText().toString(), editTextShopPlace.getText().toString(),
                        editTextShopAddress.getText().toString(),editTextPhoneNumber.getText().toString(),
                        editTextMobileNumber.getText().toString(),editTextShopArea.getText().toString(),
                        editTextDistrict.getText().toString(), editTextEmail.getText().toString(),
                        editTextPin.getText().toString(), editTextTin.getText().toString(),
                        editTextLandMark.getText().toString(),
                        null,
                        null,
                        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()),
                        Integer.valueOf(position));
                Toast.makeText(Editcustomer.this.getApplicationContext(), "Data Successfully Updated ", Toast.LENGTH_LONG).show();
                Editcustomer.this.startActivity(new Intent(Editcustomer.this.getApplicationContext(), Customer_details.class));
                Editcustomer.this.overridePendingTransition(R.anim.enter, R.anim.exit);
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
        switch (item.getItemId()) {
            case 16908332:
                finish();
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

    public void onDestroy() {
        super.onDestroy();
        this.createdetails.close();
        this.LB.close();
    }
}

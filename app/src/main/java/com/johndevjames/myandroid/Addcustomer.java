package com.johndevjames.myandroid;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.miguelcatalan.materialsearchview.BuildConfig;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Addcustomer extends AppCompatActivity {
    LoginDataBaseAdapter LB;
    Button btnSubmit;
    Createdetails createdetails;
    Dialog dialog;
    EditText editTextDistrict;
    EditText editTextEmail;
    EditText editTextLand_mark;
    EditText editTextMobileNumber;
    EditText editTextOwnerName;
    EditText editTextPhoneNumber;
    EditText editTextPin;
    EditText editTextShopAddress;
    EditText editTextShopArea;
    EditText editTextShopName;
    EditText editTextShopPlace;
    EditText editTextTin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(1);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(1024, 1024);
       //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_editcustomer);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar11));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.dialog = new Dialog(this);
        this.createdetails = new Createdetails(this);
        this.createdetails = this.createdetails.open();
        this.LB = new LoginDataBaseAdapter(this);
        this.LB = this.LB.open();
        this.editTextShopName = (EditText) findViewById(R.id.et_customer_shop_name);
        this.editTextOwnerName = (EditText) findViewById(R.id.et_customer_owner_name);
        this.editTextShopPlace = (EditText) findViewById(R.id.et_customer_shop_place);
        this.editTextShopAddress = (EditText) findViewById(R.id.et_customer_shop_address);
        this.editTextMobileNumber = (EditText) findViewById(R.id.et_customer_Mobile_numbermb);
        this.editTextPhoneNumber = (EditText) findViewById(R.id.et_customer_Phone_numberll);
        this.editTextShopArea = (EditText) findViewById(R.id.et_customer_shop_area);
        this.editTextDistrict = (EditText) findViewById(R.id.et_customer_district);
        this.editTextEmail = (EditText) findViewById(R.id.et_customer_email);
        this.editTextPin = (EditText) findViewById(R.id.et_customer_pin);
        this.editTextTin = (EditText) findViewById(R.id.et_customer_tin_no);
        this.editTextLand_mark = (EditText) findViewById(R.id.et_customer_land_mark);
        this.btnSubmit = (Button) findViewById(R.id.button5);
        this.btnSubmit.setOnClickListener(new OnClickListener() {
            public int hashCode() {
                return super.hashCode();
            }

            public void onClick(View v) {
                String ShopId = "0";
                String ShopName = Addcustomer.this.editTextShopName.getText().toString();
                String OwnerName = Addcustomer.this.editTextOwnerName.getText().toString();
                String ShopPlace = Addcustomer.this.editTextShopPlace.getText().toString();
                String ShopAddress = Addcustomer.this.editTextShopAddress.getText().toString();
                String MobileNumber = Addcustomer.this.editTextMobileNumber.getText().toString();
                String PhoneNumber = Addcustomer.this.editTextPhoneNumber.getText().toString();
                String Area = Addcustomer.this.editTextShopArea.getText().toString();
                String District = Addcustomer.this.editTextDistrict.getText().toString();
                String Email = Addcustomer.this.editTextEmail.getText().toString();
                String Pin = Addcustomer.this.editTextPin.getText().toString();
                String Tin = Addcustomer.this.editTextTin.getText().toString();
                String Land_mark = Addcustomer.this.editTextLand_mark.getText().toString();
                Integer e = Addcustomer.this.createdetails.checkifnumberalreadyexist(MobileNumber);
                if (ShopName.equals(BuildConfig.FLAVOR)) {
                    Addcustomer.this.editTextShopName.setError("Enter Shop Name");
                    Addcustomer.this.editTextShopName.requestFocus(Addcustomer.this.editTextShopName.length());
                } else if (MobileNumber.equals(BuildConfig.FLAVOR)) {
                    Addcustomer.this.editTextMobileNumber.setError("Enter Mobile Number");
                    Addcustomer.this.editTextMobileNumber.requestFocus(Addcustomer.this.editTextMobileNumber.length());
                } else if (MobileNumber.length() > 10 || MobileNumber.length() < 10) {
                    Addcustomer.this.editTextMobileNumber.setError("Invalid Mobile Number");
                    Addcustomer.this.editTextMobileNumber.requestFocus(Addcustomer.this.editTextMobileNumber.length());
                } else if (MobileNumber.equals(e)) {
                    Addcustomer.this.editTextMobileNumber.setError("Already Entered  Mobile Number");
                    Addcustomer.this.editTextMobileNumber.requestFocus(Addcustomer.this.editTextMobileNumber.length());
                } else if (Addcustomer.this.editTextEmail.getText().toString().length() == 0 || Addcustomer.this.isEmailValid(Addcustomer.this.editTextEmail.getText().toString())) {
                    Addcustomer.this.createdetails.insertEntry("0", ShopName, OwnerName,
                            ShopPlace, ShopAddress, PhoneNumber, MobileNumber, Area, District,
                            Email, Pin, Tin, Land_mark, null, null, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
                    Addcustomer.this.editTextShopName.getText().clear();
                    Addcustomer.this.editTextOwnerName.getText().clear();
                    Addcustomer.this.editTextShopPlace.getText().clear();
                    Addcustomer.this.editTextShopAddress.getText().clear();
                    Addcustomer.this.editTextPhoneNumber.getText().clear();
                    Addcustomer.this.editTextMobileNumber.getText().clear();
                    Addcustomer.this.editTextShopArea.getText().clear();
                    Addcustomer.this.editTextDistrict.getText().clear();
                    Addcustomer.this.editTextEmail.getText().clear();
                    Addcustomer.this.editTextPin.getText().clear();
                    Addcustomer.this.editTextTin.getText().clear();
                    Addcustomer.this.editTextLand_mark.getText().clear();
                    Snackbar bar = Snackbar.make(v, ShopName + "  Entered", 0).setAction("View Entered Details", new OnClickListener() {
                        public void onClick(View v) {
                            Addcustomer.this.startActivity(new Intent(Addcustomer.this.getApplicationContext(), Customer_details.class));
                            Addcustomer.this.overridePendingTransition(R.anim.enter, R.anim.exit);
                        }
                    });
                    bar.show();
                    bar.setActionTextColor(getResources().getColor(R.color.apple_grey));
                    ((TextView) bar.getView().findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.teal_green));
                } else {
                    Toast.makeText(Addcustomer.this, "Enter the correct Email", Toast.LENGTH_SHORT).show();
                    Addcustomer.this.editTextEmail.requestFocus(Addcustomer.this.editTextEmail.length());
                }
            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
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

    public void onDestroy() {
        super.onDestroy();
        this.createdetails.close();
        this.LB.close();
    }
}

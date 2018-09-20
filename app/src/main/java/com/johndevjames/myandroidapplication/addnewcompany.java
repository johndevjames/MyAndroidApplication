package com.johndevjames.myandroidapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.miguelcatalan.materialsearchview.BuildConfig;

public class addnewcompany extends AppCompatActivity {
    Button btnSubmit;
    EditText et_company_name;
    EditText et_company_place;
    EditText et_confirm_passeord;
    EditText et_email;
    EditText et_mobile;
    EditText et_password;
    EditText et_user_name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_company);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar11));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.et_company_name = (EditText) findViewById(R.id.et_company_name);
        this.et_company_place = (EditText) findViewById(R.id.et_company_place);
        this.et_mobile = (EditText) findViewById(R.id.et_mobile);
        this.et_email = (EditText) findViewById(R.id.et_email);
        this.et_user_name = (EditText) findViewById(R.id.et_user_name);
        this.et_password = (EditText) findViewById(R.id.et_password);
        this.et_confirm_passeord = (EditText) findViewById(R.id.et_confirm_passeord);
        this.btnSubmit = (Button) findViewById(R.id.button5);
        this.btnSubmit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String company_name = addnewcompany.this.et_company_name.getText().toString();
                String company_place = addnewcompany.this.et_company_place.getText().toString();
                String Mobile = addnewcompany.this.et_mobile.getText().toString();
                String email = addnewcompany.this.et_email.getText().toString();
                String username = addnewcompany.this.et_user_name.getText().toString();
                String password = addnewcompany.this.et_password.getText().toString();
                String confirm_password = addnewcompany.this.et_confirm_passeord.getText().toString();
                if (company_name.equals(BuildConfig.FLAVOR)) {
                    addnewcompany.this.et_company_name.setError("Enter Company Name");
                    addnewcompany.this.et_company_name.requestFocus(addnewcompany.this.et_company_name.length());
                } else if (Mobile.equals(BuildConfig.FLAVOR)) {
                    addnewcompany.this.et_mobile.setError("Enter Mobile Number");
                    addnewcompany.this.et_mobile.requestFocus(addnewcompany.this.et_mobile.length());
                } else if (username.equals(BuildConfig.FLAVOR)) {
                    addnewcompany.this.et_user_name.setError("Enter User Name");
                    addnewcompany.this.et_user_name.requestFocus(addnewcompany.this.et_user_name.length());
                } else if (password.equals(BuildConfig.FLAVOR)) {
                    addnewcompany.this.et_password.setError("Enter Password");
                    addnewcompany.this.et_password.requestFocus(addnewcompany.this.et_password.length());
                } else if (!confirm_password.equals(password)) {
                    addnewcompany.this.et_confirm_passeord.setError("Passwords Do not Match");
                    addnewcompany.this.et_confirm_passeord.requestFocus(addnewcompany.this.et_confirm_passeord.length());
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

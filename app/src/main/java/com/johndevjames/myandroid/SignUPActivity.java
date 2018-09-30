package com.johndevjames.myandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.miguelcatalan.materialsearchview.BuildConfig;

public class SignUPActivity extends HomeActivity {
    Button btnCreateAccount;
    EditText editTextConfirmPassword;
    EditText editTextPassword;
    EditText editTextUserName;
    LoginDataBaseAdapter loginDataBaseAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        this.loginDataBaseAdapter = this.loginDataBaseAdapter.open();
        this.editTextUserName = (EditText) findViewById(R.id.editText2);
        this.editTextPassword = (EditText) findViewById(R.id.editText5);
        this.editTextConfirmPassword = (EditText) findViewById(R.id.editText4);
        this.btnCreateAccount = (Button) findViewById(R.id.button14);
        this.btnCreateAccount.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String userName = SignUPActivity.this.editTextUserName.getText().toString();
                String password = SignUPActivity.this.editTextPassword.getText().toString();
                String confirmPassword = SignUPActivity.this.editTextConfirmPassword.getText().toString();
                if (userName.equals(BuildConfig.FLAVOR) || password.equals(BuildConfig.FLAVOR) || confirmPassword.equals(BuildConfig.FLAVOR)) {
                    Toast.makeText(SignUPActivity.this.getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                } else if (password.equals(confirmPassword)) {
                    int id = Integer.parseInt(null);
                    String type = BuildConfig.FLAVOR;
                    SignUPActivity.this.startActivity(new Intent(SignUPActivity.this.getApplicationContext(), HomeActivity.class));
                    Toast.makeText(SignUPActivity.this.getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SignUPActivity.this.getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        this.loginDataBaseAdapter.close();
    }
}

package com.johndevjames.myandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Settings extends HomeActivity {
    Button btnchangepasswd;
    Button btnchangeusername;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.btnchangeusername = (Button) findViewById(R.id.button1);
        this.btnchangeusername.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Settings.this.startActivity(new Intent(Settings.this.getApplicationContext(), Change_username.class));
            }
        });
    }
}

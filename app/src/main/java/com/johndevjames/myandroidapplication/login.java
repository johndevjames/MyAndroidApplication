package com.johndevjames.myandroidapplication;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends HomeActivity {
    public void signIn(View V) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.login);
        dialog.setTitle("Login");
        final EditText editTextUserName = (EditText) dialog.findViewById(R.id.editTextUserNameToLogin);
        final EditText editTextPassword = (EditText) dialog.findViewById(R.id.editTextPasswordToLogin);
        ((Button) dialog.findViewById(R.id.buttonSignIn)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (editTextPassword.getText().toString().equals(login.this.loginDataBaseAdapter.getSinlgeEntry(editTextUserName.getText().toString()))) {
                    Toast.makeText(login.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    return;
                }
                Toast.makeText(login.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }
}

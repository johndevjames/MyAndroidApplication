package com.johndevjames.myandroid;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class view_orders extends AppCompatActivity {
    OnDateSetListener End_date;
    OnDateSetListener Star_date;
    EditText end_date;
    Calendar myCalendar = Calendar.getInstance();
    EditText start_date;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_orders);
        this.start_date = (EditText) findViewById(R.id.spinner3);
        this.end_date = (EditText) findViewById(R.id.spinner);
        this.start_date.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new DatePickerDialog(view_orders.this, view_orders.this.Star_date, view_orders.this.myCalendar.get(Calendar.YEAR),
                        view_orders.this.myCalendar.get(Calendar.MONTH), view_orders.this.myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        this.end_date.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new DatePickerDialog(view_orders.this, view_orders.this.End_date, view_orders.this.myCalendar.get(Calendar.YEAR),
                        view_orders.this.myCalendar.get(Calendar.MONTH), view_orders.this.myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        this.End_date = new OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                view_orders.this.myCalendar.set(Calendar.YEAR, year);
                view_orders.this.myCalendar.set(Calendar.MONTH, monthOfYear);
                view_orders.this.myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                view_orders.this.endupdateLabel();
            }
        };
        this.Star_date = new OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                view_orders.this.myCalendar.set(Calendar.YEAR, year);
                view_orders.this.myCalendar.set(Calendar.MONTH, monthOfYear);
                view_orders.this.myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                view_orders.this.updateLabel();
            }
        };
    }

    private void updateLabel() {
        this.start_date.setText(new SimpleDateFormat("MM/dd/yy", Locale.US).format(this.myCalendar.getTime()));
    }

    private void endupdateLabel() {
        this.end_date.setText(new SimpleDateFormat("MM/dd/yy", Locale.US).format(this.myCalendar.getTime()));
    }
}

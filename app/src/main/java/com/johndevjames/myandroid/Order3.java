package com.johndevjames.myandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.johndevjames.myandroid.CustomListAdapter6.customButtonListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Order3 extends AppCompatActivity implements customButtonListener {
    Createdetails Cd;
    TextView Customername;
    TextView Date;
    LoginDataBaseAdapter LB;
    int Np;
    List<DataModel2> Order2_Details;
    TextView OrderNo;
    TextView Total;
    TextView ViewTextDateOrdered;
    TextView ViewTextMRP;
    TextView ViewTextOrderNo;
    TextView ViewTextProductId;
    TextView ViewTextProductName;
    TextView ViewTextQuantity;
    TextView ViewTextRatePerItem;
    TextView ViewTextShopName;
    TextView ViewTextTotalRate;
    CustomListAdapter6 adapter;
    Button btnSync;
    DataModel2 dt;
    EditText editqty;
    EditText editsearch;
    Button extra;
    LayoutInflater inflater;
    String last_update;
    View layout;
    ListView listView2;
    TextView narration;
    int orderNo;
    PopupWindow pwindo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_order3);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar8));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.editsearch = (EditText) findViewById(R.id.inputSearch);
        this.extra = (Button) findViewById(R.id.button25);
        this.Cd = new Createdetails(this);
        this.Cd = this.Cd.open();
        this.LB = new LoginDataBaseAdapter(this);
        this.LB = this.LB.open();
        Bundle extras = getIntent().getExtras();
        this.orderNo = Integer.parseInt(extras.getString("order_no"));
        String totaal = extras.getString("total");
        this.last_update = extras.getString("last_ipdate");
        this.Order2_Details = this.Cd.getAllOrderDetailsbasedonorderno(this.orderNo);
        this.dt = (DataModel2) this.Order2_Details.get(this.Order2_Details.size() - 1);
        String last_update_check = this.Cd.getUPDATED_STATUS2();
        String date = this.Cd.sameorderno(Integer.valueOf(this.orderNo));
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dateer = null;
        Date ol_date = null;
        try {
            dateer = format.parse(last_update_check);
            ol_date = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.extra.setVisibility(View.GONE);
        if (dateer.before(ol_date)) {
            this.extra.setVisibility(View.VISIBLE);
        }
        this.extra.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent add_old_order = new Intent(Order3.this.getApplicationContext(), Order.class);
                add_old_order.putExtra("old_order_no", Order3.this.dt.getOrder_no());
                add_old_order.putExtra("old_customer_id", Order3.this.dt.getCustomer_id());
                add_old_order.putExtra("old_user_id", Order3.this.dt.getUser_Id());
                Order3.this.startActivity(add_old_order);
                Order3.this.overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
        this.Customername = (TextView) findViewById(R.id.textView20);
        this.OrderNo = (TextView) findViewById(R.id.textView);
        this.Date = (TextView) findViewById(R.id.textView21);
        this.Total = (TextView) findViewById(R.id.textView38);
        this.narration = (TextView) findViewById(R.id.textView30);
        this.narration.setText(this.dt.getNarration());
        this.Customername.setText(this.Cd.GETfinalOrder(this.dt.getCustomer_id()));
        this.OrderNo.setText("Order No: " + this.LB.get_Prfeix(this.dt.getUser_Id()) + this.dt.getOrder_no());
        this.Date.setText("" + this.dt.getOrder_date());
        this.Total.setText("Total: " + totaal);
        this.listView2 = (ListView) findViewById(R.id.list_main3);
        this.adapter = new CustomListAdapter6(this, 0, this.Order2_Details);
        this.listView2.setAdapter(this.adapter);
        this.adapter.setCustomButtonListner(this);
        click();
        this.adapter.getCount();
        this.editsearch.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void afterTextChanged(Editable arg0) {
                Order3.this.adapter.filter(Order3.this.editsearch.getText().toString().toLowerCase(Locale.getDefault()));
            }
        });
    }

    public void click() {
        this.listView2.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Order3.this.inflater = (LayoutInflater) Order3.this.getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                Order3.this.layout = Order3.this.inflater.inflate(R.layout.screen_popup2, (ViewGroup) Order3.this.findViewById(R.id.popup_element2));
                Order3.this.pwindo = new PopupWindow(Order3.this.layout);
                Order3.this.pwindo.setContentView(Order3.this.layout);
                Order3.this.pwindo.setWidth(-1);
                Order3.this.pwindo.setHeight(-1);
                Order3.this.pwindo.setFocusable(true);
                //Order3.this.layout.setAnimation(AnimationUtils.loadAnimation(Order3.this, R.anim.popup_enter));
                Order3.this.pwindo.setAnimationStyle(R.style.animationName);
                Order3.this.pwindo.showAtLocation(Order3.this.layout, 17, 0, 0);
                Order3.this.layout.getBackground().setAlpha(230);
                Order3.this.ViewTextShopName = (TextView) Order3.this.layout.findViewById(R.id.textView35);
                Order3.this.ViewTextOrderNo = (TextView) Order3.this.layout.findViewById(R.id.textView28);
                Order3.this.ViewTextProductName = (TextView) Order3.this.layout.findViewById(R.id.textView27);
                Order3.this.ViewTextProductId = (TextView) Order3.this.layout.findViewById(R.id.textView37);
                Order3.this.ViewTextDateOrdered = (TextView) Order3.this.layout.findViewById(R.id.textView39);
                Order3.this.ViewTextRatePerItem = (TextView) Order3.this.layout.findViewById(R.id.textView40);
                Order3.this.ViewTextMRP = (TextView) Order3.this.layout.findViewById(R.id.textView41);
                Order3.this.ViewTextTotalRate = (TextView) Order3.this.layout.findViewById(R.id.textView42);
                Order3.this.editqty = (EditText) Order3.this.layout.findViewById(R.id.textView38);
                int newlistcount = Order3.this.listView2.getCount() - 1;
                Order3.this.Np = newlistcount - position;
                DataModel2 dataModel3 = (DataModel2) Order3.this.Order2_Details.get(position);
                Integer Item_id = Integer.valueOf(dataModel3.getProduct_no().intValue());
                Integer ShopId = Integer.valueOf(dataModel3.getCustomer_id().intValue());
                final int ID = Integer.valueOf(dataModel3.getId().intValue()).intValue();
                String SHOPNAME = Order3.this.Cd.GETfinalOrder(ShopId);
                ArrayList Ap = new ArrayList();
                DataModel dataModel2 = (DataModel) Order3.this.Cd.GETfinalOrder2(Item_id).get(0);
                Order3.this.ViewTextShopName.setText(String.valueOf(SHOPNAME));
                Order3.this.ViewTextOrderNo.setText(Order3.this.LB.get_Prfeix(dataModel3.getUser_Id()) + Integer.toString(dataModel3.getOrder_no().intValue()));
                Order3.this.ViewTextProductName.setText(dataModel2.getItemName());
                Order3.this.ViewTextProductId.setText(dataModel2.getItemCode());
                Order3.this.editqty.setText(Integer.toString(dataModel3.getQty().intValue()));
                Order3.this.ViewTextDateOrdered.setText(dataModel3.getOrder_date());
                Order3.this.ViewTextRatePerItem.setText(dataModel2.getRate());
                Order3.this.ViewTextMRP.setText(dataModel2.getMrp());
                float Rate = Float.parseFloat(dataModel2.getRate());
                int Qty = dataModel3.getQty().intValue();
                float amount = 0.0f;
                if (Rate != 0.0f) {
                    amount = Rate * ((float) Qty);
                }
                Order3.this.ViewTextTotalRate.setText(String.valueOf(amount));
                Button save = (Button) Order3.this.layout.findViewById(R.id.button11);
                ArrayList Order4_Details = (ArrayList) Order3.this.Cd.getorderdetaislafterdate(Order3.this.last_update);
                save.setVisibility(View.GONE);
                for (int i = 0; i < Order4_Details.size(); i++) {
                    if (((DataModel2) Order4_Details.get(i)).getId() == dataModel3.getId()) {
                        save.setVisibility(View.VISIBLE);
                    }
                }
                save.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        Integer Id = Integer.valueOf(ID);
                        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                        Order3.this.Cd.updateOrderEntry(Order3.this.editqty.getText().toString(), Id, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
                        Order3.this.Order2_Details = Order3.this.Cd.getAllOrderDetailsbasedonorderno(Order3.this.orderNo);
                        Order3.this.adapter = new CustomListAdapter6(Order3.this, 0, Order3.this.Order2_Details);
                        Order3.this.listView2.setAdapter(Order3.this.adapter);
                        Order3.this.adapter.notifyDataSetChanged();
                        Order3.this.pwindo.dismiss();
                        Order3.this.finish();
                        Order3.this.startActivity(Order3.this.getIntent());
                    }
                });
                Order3.this.adapter.notifyDataSetChanged();
                ((Button) Order3.this.layout.findViewById(R.id.btn_close_popup2)).setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        Order3.this.listView2.setAdapter(Order3.this.adapter);
                        Order3.this.adapter.notifyDataSetChanged();
                        Order3.this.pwindo.dismiss();
                    }
                });
                Order3.this.adapter.notifyDataSetChanged();
            }
        });
        this.adapter.notifyDataSetChanged();
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
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", 0);
        Intent intentSettings;
        switch (item.getItemId()) {
            case 16908332:
                intentSettings = new Intent(getApplicationContext(), Order2.class);
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

    public void onButtonClickListner(int position) {
        this.Cd.delete(((DataModel2) this.Order2_Details.get(position)).getId());
        this.Order2_Details.remove(position);
        this.adapter.notifyDataSetChanged();
    }

    public void onBackPressed() {
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", 0);
        Intent intentSettings = new Intent(getApplicationContext(), Order2.class);
        intentSettings.putExtra("key_id", prefs.getInt("UserId", 0));
        startActivity(intentSettings);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.Cd.close();
        this.LB.close();
    }
}

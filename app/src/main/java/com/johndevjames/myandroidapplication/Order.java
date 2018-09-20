package com.johndevjames.myandroidapplication;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.johndevjames.myandroidapplication.CustomListAdapter.customButtonListener;
import com.johndevjames.myandroidapplication.CustomListAdapter2.customImageListener2;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Order extends AppCompatActivity implements customButtonListener, customImageListener2 {
    LoginDataBaseAdapter LB;
    ArrayList<DataModel> Order5_Details;
    ArrayList<DataModel> Order_Details;
    int Sizetwo;
    String a;
    CustomListAdapter2 adapter;
    CustomListAdapter adapter1;
    String b;
    Button btnAdd;
    Button btnsave;
    String c;
    Button cleartext;
    Createdetails createdetails;
    String d;
    DataModel dd;
    String e;
    EditText editOrder;
    EditText editsearch;
    Integer item_id;
    String itemcodez;
    ListView listView;
    ListView listView2;
    EditText narration;
    int old_customer_id = 0;
    int old_order_no = 0;
    int old_user_id = 0;
    int p;
    ArrayList<DataModel> r;
    double total_amount = 0.0d;
    TextView totalamount;
    List<Tutorial> tutorialList = new ArrayList();
    TutorialsAdapter tutorialsAdapter;
    View ee;
    Snackbar bar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_order);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar8));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        click();
        this.createdetails = new Createdetails(this);
        this.createdetails = this.createdetails.open();
        this.LB = new LoginDataBaseAdapter(this);
        this.LB = this.LB.open();
        this.Order_Details = this.createdetails.getAllOrder();
        this.r = new ArrayList();
        this.adapter1 = new CustomListAdapter(this, 0, this.r);
        this.adapter = new CustomListAdapter2(this, 0, this.Order_Details);
        this.adapter.setCustomImageListner2(this);
        this.adapter1.setCustomButtonListner(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar8);
        toolbar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            }
        });
        Bundle extras = getIntent().getExtras();
        this.old_order_no = extras.getInt("old_order_no");
        this.old_customer_id = extras.getInt("old_customer_id");
        this.old_user_id = extras.getInt("old_user_id");
        this.listView = (ListView) findViewById(R.id.list_main);
        this.listView2 = (ListView) findViewById(R.id.list_main2);
        this.narration = (EditText) findViewById(R.id.narration);
        this.listView2.setVisibility(View.GONE);
        this.narration.setVisibility(View.VISIBLE);
        this.listView.setVisibility(View.VISIBLE);
        this.editsearch = (EditText) findViewById(R.id.inputSearch);
        this.cleartext = (Button) findViewById(R.id.button22);
        this.cleartext.setVisibility(View.VISIBLE);
        this.cleartext.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                Order.this.editsearch.getText().clear();
                Order.this.cleartext.setVisibility(View.VISIBLE);
                return false;
            }
        });
        this.listView.setAdapter(this.adapter);
        this.editsearch.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                Order.this.listView.setVisibility(View.VISIBLE);
                Order.this.listView2.setVisibility(View.GONE);
                Order.this.narration.setVisibility(View.GONE);
                if (Order.this.editsearch.getText().toString().equals(BuildConfig.FLAVOR)) {
                    Order.this.cleartext.setVisibility(View.VISIBLE);
                } else {
                    Order.this.cleartext.setVisibility(View.GONE);
                }
                return false;
            }
        });
        this.editsearch.addTextChangedListener(new TextWatcher() {
            Bundle extras = Order.this.getIntent().getExtras();
            final String position = this.extras.getString("poster");

            public void beforeTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            public void afterTextChanged(Editable s) {
                Order.this.adapter.filter(Order.this.editsearch.getText().toString().toLowerCase(Locale.getDefault()));
            }
        });
        toolbar.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                Order.this.listView.setVisibility(View.VISIBLE);
                Order.this.listView2.setVisibility(View.GONE);
                Order.this.narration.setVisibility(View.GONE);
                return false;
            }
        });
        this.btnAdd = (Button) findViewById(R.id.button7);
        this.btnAdd.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                EditText order_noe = (EditText) Order.this.findViewById(R.id.editText3);
                EditText shopnamee = (EditText) Order.this.findViewById(R.id.inputSearch);
                Integer Order_no = null;
                try {
                    Order_no = Integer.valueOf(Integer.parseInt(order_noe.getText().toString()));
                } catch (NumberFormatException e) {
                }
                String Shopname = shopnamee.getText().toString();
                if (TextUtils.isEmpty(shopnamee.getText().toString()) || Shopname.length() == 0 || Shopname.equals(BuildConfig.FLAVOR) || Shopname == null) {
                    shopnamee.setError("Enter the Item Name");
                } else if (TextUtils.isEmpty(order_noe.getText().toString()) || Order_no.intValue() == 0 || Order_no.equals(null) || Order_no.equals(BuildConfig.FLAVOR) || Order_no == null) {
                    order_noe.setError("Enter the Quantity");
                }else
                {
                    Order.this.listView2.setVisibility(View.VISIBLE);
                }
                return false;
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

    public void click() {
        final ListView listView = (ListView) findViewById(R.id.list_main);
        listView2 = (ListView) findViewById(R.id.list_main2);

        listView.setOnItemClickListener(new OnItemClickListener() {
            private OnClickListener addlistener = new OnClickListener() {
                public void onClick(View v) {
                    EditText order_no = (EditText) Order.this.findViewById(R.id.editText3);
                    EditText shopname = (EditText) Order.this.findViewById(R.id.inputSearch);
                    Order.this.totalamount = (TextView) Order.this.findViewById(R.id.textView37);
                    Integer Order_no = null;
                    try {
                        Order_no = Integer.valueOf(Integer.parseInt(order_no.getText().toString()));
                    } catch (NumberFormatException e) {
                    }
                    String Shopname = shopname.getText().toString();
                    if (TextUtils.isEmpty(shopname.getText().toString()) || Shopname.length() == 0 || Shopname.equals(BuildConfig.FLAVOR) || Shopname == null) {
                        shopname.setError("Enter the Item Name");
                        Order.this.editsearch.requestFocus(Order.this.editsearch.length());
                    } else if (TextUtils.isEmpty(order_no.getText().toString()) || Order_no.intValue() == 0 || Order_no.equals(null) || Order_no.equals(BuildConfig.FLAVOR) || Order_no == null) {
                        order_no.setError("Enter the Quantity");
                        Order.this.editOrder.requestFocus(Order.this.editOrder.length());
                    } else {
                        listView.setVisibility(View.GONE);
                        Order.this.listView2.setVisibility(View.VISIBLE);
                        Order.this.narration.setVisibility(View.VISIBLE);
                        Order.this.r.add(new DataModel(Order.this.item_id, Shopname, Order.this.d, Integer.toString(Order_no.intValue()), Order.this.c, Order.this.a));
                        Order.this.Order5_Details = Order.this.createdetails.GETfinalOrder2(Order.this.item_id);
                        Order.this.dd = (DataModel) Order.this.Order5_Details.get(0);
                        float rate = Float.parseFloat(Order.this.dd.getRate());
                        int qty = Order_no.intValue();
                        Order order = Order.this;
                        order.total_amount += (double) (((float) qty) * rate);
                        Order.this.totalamount.setText("           " + String.format("%.2f", new Object[]{Double.valueOf(Order.this.total_amount)}));
                        Order.this.listView2.setAdapter(Order.this.adapter1);
                        order_no.getText().clear();
                        shopname.getText().clear();
                        shopname.requestFocus();
                        Order.this.listView2.setVisibility(View.VISIBLE);
                        Order.this.narration.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                        Order.this.adapter1.notifyDataSetChanged();
                    }
                }
            };

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                DataModel dataModel = (DataModel) listView.getItemAtPosition(position);
                Order.this.Sizetwo = listView.getCount();
                Order.this.item_id = dataModel.getItemId();
                Order.this.e = String.valueOf(dataModel.getItemName());
                Order.this.d = String.valueOf(dataModel.getItemCode());
                Order.this.b = String.valueOf(dataModel.getQuantity());
                Order.this.c = String.valueOf(dataModel.getRate());
                Order.this.a = String.valueOf(dataModel.getMrp());
                listView.setAdapter(Order.this.adapter);
                listView.setVisibility(View.VISIBLE);
                Order.this.editsearch = (EditText) Order.this.findViewById(R.id.inputSearch);
                Order.this.editOrder = (EditText) Order.this.findViewById(R.id.editText3);
                Order.this.editsearch.setText(Order.this.e);
                Order.this.editOrder.requestFocus(Order.this.editOrder.length());
                //EditText shopname = (EditText) Order.this.findViewById(R.id.editText);
                Order.this.btnAdd = (Button) Order.this.findViewById(R.id.button7);
                Order.this.btnsave = (Button) Order.this.findViewById(R.id.button8);
                Order.this.btnAdd.setOnClickListener(this.addlistener);
            }
        });

        listView2.setOnItemClickListener(new OnItemClickListener() {
            private OnClickListener addlistener2 = new OnClickListener() {
                public void onClick(View v) {
                    EditText order_no = (EditText) Order.this.findViewById(R.id.editText3);
                    EditText shopname = (EditText) Order.this.findViewById(R.id.inputSearch);
                    Integer Order_no = null;
                    try {
                        Order_no = Integer.valueOf(Integer.parseInt(order_no.getText().toString()));
                    } catch (NumberFormatException e) {
                    }
                    String Shopname = shopname.getText().toString();
                    if (TextUtils.isEmpty(order_no.getText().toString()) || Order_no.intValue() == 0 || Order_no.equals(null) || Order_no.equals(BuildConfig.FLAVOR) || Order_no == null) {
                        order_no.setError("Enter the Quantity");
                        Order.this.editOrder.requestFocus(Order.this.editOrder.length());
                    } else if (TextUtils.isEmpty(shopname.getText().toString()) || Shopname.length() == 0 || Shopname.equals(BuildConfig.FLAVOR) || Shopname == null) {
                        shopname.setError("Enter the Item Name");
                        Order.this.editsearch.requestFocus(Order.this.editsearch.length());
                    } else if (listView2.getCount() <= 0 || TextUtils.isEmpty(order_no.getText().toString()) || Order_no.intValue() == 0 || Order_no.equals(null) || Order_no.equals(BuildConfig.FLAVOR) || Order_no == null) {
                        order_no.setError("Enter the Quantity");
                        Order.this.editOrder.requestFocus(Order.this.editOrder.length());
                    } else if (TextUtils.isEmpty(shopname.getText().toString()) || Shopname.length() == 0 || Shopname.equals(BuildConfig.FLAVOR) || Shopname == null) {
                        shopname.setError("Enter the Item Name");
                        Order.this.editsearch.requestFocus(Order.this.editsearch.length());
                    } else {
                        ArrayList arrayList = Order.this.r;
                        arrayList.set(Order.this.p, new DataModel(Order.this.item_id, Shopname, Order.this.d, Integer.toString(Order_no.intValue()), Order.this.c, Order.this.a));
                        listView2.setAdapter(Order.this.adapter1);
                        Order.this.adapter1.notifyDataSetChanged();
                        Order.this.Order5_Details = Order.this.createdetails.GETfinalOrder2(Order.this.item_id);
                        Order.this.dd = (DataModel) Order.this.Order5_Details.get(0);
                        float rate = Float.parseFloat(Order.this.dd.getRate());
                        int qty = Order_no.intValue();
                        float oldamount = rate * ((float) Integer.parseInt(dataModel1.getQuantity()));
                        Order.this.total_amount -= (double) oldamount;
                        Order order = Order.this;
                        order.total_amount += (double) (((float) qty) * rate);
                        Order.this.totalamount.setText("           " + String.format("%.2f", new Object[]{Double.valueOf(Order.this.total_amount)}));
                        order_no.getText().clear();
                        shopname.getText().clear();
                    }
                }
            };
            DataModel dataModel1;

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                this.dataModel1 = (DataModel) listView2.getItemAtPosition(position);
                String y = String.valueOf(this.dataModel1.getItemName());
                String aa = String.valueOf(this.dataModel1.getQuantity());
                Order.this.item_id = this.dataModel1.getItemId();
                Order.this.d = String.valueOf(this.dataModel1.getItemCode());
                Order.this.c = String.valueOf(this.dataModel1.getRate());
                Order.this.a = String.valueOf(this.dataModel1.getMrp());
                Order.this.p = position;
                Order.this.editsearch = (EditText) Order.this.findViewById(R.id.inputSearch);
                Order.this.editOrder = (EditText) Order.this.findViewById(R.id.editText3);
                Order.this.btnAdd = (Button) Order.this.findViewById(R.id.button7);
                Order.this.btnsave = (Button) Order.this.findViewById(R.id.button8);
                Order.this.btnAdd.setOnClickListener(this.addlistener2);
                Order.this.editsearch.setText(y);
                Order.this.editOrder.setText(aa);
                Order.this.editOrder.requestFocus(Order.this.editOrder.length());
                Order.this.adapter1.notifyDataSetChanged();
            }
        });

        this.btnsave = (Button) findViewById(R.id.button8);

        this.btnsave.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                EditText shopname = (EditText) Order.this.findViewById(R.id.inputSearch);
                ListView listView2 = (ListView) findViewById(R.id.list_main2);
                String Order_no = ((EditText) Order.this.findViewById(R.id.editText3)).getText().toString();
                String Shopname = shopname.getText().toString();
                Integer item_ide,quantitye;
                DataModel dataModel2;

                if (listView2.getCount() <= 0) {
                    Dialog dialog = new Dialog(Order.this);
                    dialog.setContentView(R.layout.screen_popup);
                    dialog.setTitle("Validation");
                    final Dialog dialog2 = dialog;
                    ((Button) dialog.findViewById(R.id.btn_close_popup)).setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            dialog2.dismiss();
                        }
                    });
                    dialog.show();
                } else if (Order.this.old_user_id == 0 && Order.this.old_customer_id == 0 && Order.this.old_order_no == 0) {
                    listView2 = (ListView) Order.this.findViewById(R.id.list_main2);
                    int size = listView2.getCount();
                    Bundle extras = Order.this.getIntent().getExtras();
                    Integer customerId = Integer.valueOf(extras.getInt("customer_id"));
                    Integer Ordered_user = Integer.valueOf(extras.getInt("key_id"));
                    Integer Order_no1 = Integer.valueOf(0);
                    if (Order.this.createdetails.Check_max_id_Order() == 0) {
                        Order_no1 = Integer.valueOf(Order.this.LB.CheckOrder(Ordered_user));
                    } else {
                        Order_no1 = Integer.valueOf(Integer.valueOf(Order.this.createdetails.Check_max_id_Order()).intValue() + 1);
                    }
                    ArrayList Order5_Details = new ArrayList();
                    for (int i = 0; i < size; i++) {
                        dd = (DataModel) listView2.getItemAtPosition(i);
                        String itemname = String.valueOf(dd.getItemName());

                        Order.this.itemcodez = String.valueOf(dd.getItemCode());

                        item_ide = dd.getItemId();
                        quantitye = Integer.valueOf(dd.getQuantity());
                        String rate = String.valueOf(dd.getRate());
                        String mrp = String.valueOf(dd.getMrp());
                        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                        String last_updated = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
                        String narrate = Order.this.narration.getText().toString();
                        Order.this.createdetails.insertOrder(customerId.intValue(), date, Order_no1.intValue(), Ordered_user.intValue(), item_ide.intValue(), quantitye.intValue(), last_updated, Order.this.narration.getText().toString());
                        Order.this.totalamount.setText(BuildConfig.FLAVOR);
                    }
                    ee = v;
                    bar = Snackbar.make(ee, listView2.getCount() + " Orders Saved", 0).setAction("View Order", new OnClickListener() {
                        public void onClick(View v) {
                            Intent intentAddCust = new Intent(Order.this.getApplicationContext(), Order2.class);
                            String MY_PREFS_NAME = "MyPrefsFile";
                            intentAddCust.putExtra("key_id", Order.this.getSharedPreferences("MyPrefsFile", 0).getInt("UserId", 0));
                            Order.this.startActivity(intentAddCust);
                            Order.this.overridePendingTransition(R.anim.enter, R.anim.exit);
                        }
                    });
                    View view = bar.getView();
                    LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                    //params.width = 48;
                    view.setLayoutParams(params);
                    bar.show();
                    bar.setActionTextColor(getResources().getColor(R.color.apple_grey));
                    ((TextView) bar.getView().findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.teal_green));
                    Order.this.adapter1.clear();
                    Order.this.adapter1.notifyDataSetChanged();
                } else if (Order.this.old_user_id != 0 && Order.this.old_customer_id != 0 && Order.this.old_order_no != 0) {
                    listView2 = (ListView) Order.this.findViewById(R.id.list_main2);
                    for (int i = 0; i < listView2.getCount(); i++) {
                        dataModel2 = (DataModel) listView2.getItemAtPosition(i);
                        Order.this.itemcodez = String.valueOf(dataModel2.getItemCode());
                        item_ide = dataModel2.getItemId();
                        quantitye = Integer.valueOf(dataModel2.getQuantity());
                        Order.this.createdetails.insertOrder(Order.this.old_customer_id, new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()), Order.this.old_order_no, Order.this.old_user_id, item_ide.intValue(), quantitye.intValue(), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()), Order.this.narration.getText().toString());
                    }
                    ee = v;
                    bar = Snackbar.make(ee, listView2.getCount() + " Orders Saved", 0).setAction("View Order", new OnClickListener() {
                        public void onClick(View v) {
                            Intent intentAddCust = new Intent(Order.this.getApplicationContext(), Order2.class);
                            String MY_PREFS_NAME = "MyPrefsFile";
                            intentAddCust.putExtra("key_id", Order.this.getSharedPreferences("MyPrefsFile", 0).getInt("UserId", 0));
                            Order.this.startActivity(intentAddCust);
                            Order.this.overridePendingTransition(R.anim.enter, R.anim.exit);
                        }
                    });
                    bar.show();
                    bar.setActionTextColor(getResources().getColor(R.color.apple_grey));
                    ((TextView) bar.getView().findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.teal_green));
                    Order.this.adapter1.clear();
                    Order.this.adapter1.notifyDataSetChanged();
                }
            }
        });
    }

    public void onButtonClickListner(int position) {
        DataModel dataModele = (DataModel) this.listView2.getItemAtPosition(position);
        double sumrate = ((double) Float.parseFloat(dataModele.getRate())) * ((double) Integer.parseInt(dataModele.getQuantity()));
        if (this.adapter1 == null || sumrate == 9.536743E-7d) {
            this.total_amount = 0.0d;
        } else {
            this.total_amount -= sumrate;
        }
        this.totalamount.setText("           " + String.format("%.2f", new Object[]{Double.valueOf(this.total_amount)}));
        this.r.remove(position);
        this.adapter1.notifyDataSetChanged();
    }

    public void onImageClickListner2(int position) {
        DataModel dataModel = (DataModel) this.listView.getItemAtPosition(position);
        startActivity(new Intent(getApplicationContext(), addImage.class));
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.createdetails.close();
        this.LB.close();
    }
}

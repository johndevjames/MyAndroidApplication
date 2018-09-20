package com.johndevjames.myandroidapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

public class Order2 extends AppCompatActivity {
    Createdetails Cd;
    LoginDataBaseAdapter LB;
    List<DataModel3> Order2_Details;
    CustomListAdapter3 adapter;
    Button btnSync;
    Builder builder;
    EditText editsearch;
    JSONArray jse = new JSONArray();
    String last_update_check = null;
    ListView listView2;
    String prefix;
    ProgressBar progressBar;
    String responsecheck = null;
    RelativeLayout rl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_order2);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar8));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.editsearch = (EditText) findViewById(R.id.inputSearch);
        this.btnSync = (Button) findViewById(R.id.button17);
        this.rl = (RelativeLayout) findViewById(R.id.relativelayout);
        this.Cd = new Createdetails(this);
        this.Cd = this.Cd.open();
        this.LB = new LoginDataBaseAdapter(this);
        this.LB = this.LB.open();
        this.Order2_Details = this.Cd.getAllOrderDetails();
        this.listView2 = (ListView) findViewById(R.id.list_main3);
        Collections.reverse(this.Order2_Details);
        this.adapter = new CustomListAdapter3(this, 0, this.Order2_Details);
        this.listView2.setAdapter(this.adapter);
        click();
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        this.progressBar.setVisibility(View.GONE);
        this.btnSync.setVisibility(View.VISIBLE);
        this.adapter.getCount();
        this.btnSync.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Order2.this.progressBar.setVisibility(View.VISIBLE);
                Order2.this.btnSync.setVisibility(View.GONE);
                Order2.this.builder = new Builder(Order2.this);
                Order2.this.builder.setMessage("Are you sure ").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Integer key_id = Integer.valueOf(Order2.this.getIntent().getExtras().getInt("key_id"));
                        Order2.this.prefix = Order2.this.LB.get_Prfeix(key_id);
                        Order2.this.last_update_check = Order2.this.Cd.getUPDATED_STATUS2();
                        if (((ArrayList) Order2.this.Cd.getorderdetaislafterdate(Order2.this.last_update_check)).size() != 0) {
                            String EuId = Order2.this.LB.getEid(key_id);
                            Integer max_id = Integer.valueOf(Order2.this.Cd.Check_max_id_Order());
                            Order2.this.UploadOrderDetails(EuId, Order2.this.last_update_check);
                            Order2.this.Uploadmax_no(EuId, max_id);
                            dialog.dismiss();
                            Toast.makeText(Order2.this, "Sync Completed", Toast.LENGTH_SHORT).show();
                            Order2.this.progressBar.setVisibility(View.GONE);
                            Order2.this.btnSync.setVisibility(View.VISIBLE);
                            return;
                        }
                        else
                        {
                            Toast.makeText(Order2.this, "Not Synced", Toast.LENGTH_SHORT).show();
                            Order2.this.progressBar.setVisibility(View.GONE);
                            Order2.this.btnSync.setVisibility(View.VISIBLE);
                        }

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Order2.this.progressBar.setVisibility(View.GONE);
                        Order2.this.btnSync.setVisibility(View.VISIBLE);
                    }
                });
                Order2.this.builder.create().show();
            }
        });
        this.editsearch.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void afterTextChanged(Editable arg0) {
                Order2.this.adapter.filter(Order2.this.editsearch.getText().toString().toLowerCase(Locale.getDefault()));
            }
        });
    }

    public void click() {
        this.listView2.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                DataModel3 dt3 = (DataModel3) Order2.this.Order2_Details.get(position);
                String r = ((TextView) view.findViewById(R.id.textView36)).getText().toString().substring(13);
                dt3.getOrder_no();
                Intent intentSettings = new Intent(Order2.this.getApplicationContext(), Order3.class);
                intentSettings.putExtra("order_no", Integer.toString(dt3.getOrder_no().intValue()));
                intentSettings.putExtra("total", r);
                intentSettings.putExtra("last_ipdate", Order2.this.Cd.getUPDATED_STATUS2());
                Order2.this.startActivity(intentSettings);
                Order2.this.overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
    }

    private boolean UploadOrderDetails(String Euid, String last_date) {
        RequestQueue queue = Volley.newRequestQueue(this);
        ArrayList Order4_Details = new ArrayList();
        Order4_Details = (ArrayList) this.Cd.getorderdetaislafterdate(last_date);
        final int Alength = Order4_Details.size();
        final ArrayList finalOrder4_Details = Order4_Details;
        final String str = Euid;
        StringRequest postRequest = new StringRequest(1, "http://smartmds.in/GMobAppService.asmx/fn_PostAppOrder", new Listener<String>() {
            public void onResponse(String response) {
                Log.d("Response", response);
                if (response.contains("Record Added Sucessfully")) {
                    Toast.makeText(Order2.this, "Order Uploaded", Toast.LENGTH_SHORT).show();
                }
                Order2.this.responsecheck = response;
                Snackbar bar = Snackbar.make(Order2.this.rl, " Orders Synced", 0);
                bar.show();
                bar.setActionTextColor(getResources().getColor(R.color.apple_grey));
                ((TextView) bar.getView().findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.teal_green));
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", String.valueOf(error));
                Toast.makeText(Order2.this, "Check Connection and try again later", Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                int i = 0;
                while (i < Alength) {
                    DataModel2 dataModel3 = (DataModel2) finalOrder4_Details.get(i);
                    String line1 = String.valueOf(dataModel3.getCustomer_id());
                    String line2 = String.valueOf(dataModel3.getOrder_date());
                    String line3 = String.valueOf(dataModel3.getOrder_no());
                    String line4 = String.valueOf(dataModel3.getProduct_no());
                    String line5 = String.valueOf(dataModel3.getQty());
                    String line6 = String.valueOf(dataModel3.getId());
                    int id = dataModel3.getId().intValue();
                    try {
                        Order2.this.jse.put(i, line1 + "|" + line2 + "|" + Order2.this.prefix + line3 + "|" + line4 + "|" + line5 + "|" + line6);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
                int finalI = i;
                params.put("jsonString", String.valueOf(Order2.this.jse));
                params.put("ls_euid", str);
                if (Alength == finalI) {
                    Order2.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Order2.this.progressBar.setVisibility(View.VISIBLE);
                            Order2.this.btnSync.setVisibility(View.GONE);
                            Order2.this.Cd.update_last_date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
                        }
                    });
                }
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, 1, 1.0f));
        queue.add(postRequest);
        return true;
    }

    private boolean Uploadmax_no(String Euid, Integer max_id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        final Integer num = max_id;
        final String str = Euid;
        StringRequest postRequest = new StringRequest(1, "http://smartmds.in/GMobAppService.asmx/fn_UploadMaxId", new Listener<String>() {
            public void onResponse(String response) {
                Log.e("Response", response);
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", String.valueOf(error));
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                String jsonstring = String.valueOf(Order2.this.jse);
                params.put("li_maxid", String.valueOf(num));
                params.put("ls_euid", str);
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, 1, 1.0f));
        queue.add(postRequest);
        return true;
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

    public void onBackPressed() {
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", 0);
        Intent intentSettings = new Intent(getApplicationContext(), MenuIconsAdmin.class);
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

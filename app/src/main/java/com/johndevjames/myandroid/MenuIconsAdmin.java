package com.johndevjames.myandroid;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue.RequestFinishedListener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics.Param;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.miguelcatalan.materialsearchview.BuildConfig;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MenuIconsAdmin extends AppCompatActivity {
    Createdetails Cd;
    LoginDataBaseAdapter LB;
    ProgressDialog Pdialog;
    int add;
    int addtime;
    Button btnAddOrder;
    Button btnAdd_customer;
    Button btnSync;
    Button btnView_customer;
    Button btn_view_order;
    Button btnitems;
    Builder builder;
    int checkNet = 0;
    String errorResponse = null;
    int finalI;
    JSONArray jse = new JSONArray();
    JSONArray jsonResponsecustomer = null;
    JSONArray jsonResponseitem = null;
    Integer key_id;
    TextView last_sync;
    String laste_server_date;
    FirebaseRemoteConfig mFirebaseRemoteConfig;
    String prefix;
    ProgressBar progressBar;
    String responsecheck = null;
    RelativeLayout rl;
    String server_date;
    String success = null;
    Button test;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.menuadmin);
        this.Cd = new Createdetails(this);
        this.Cd = this.Cd.open();
        this.Cd = this.Cd.read();
        this.LB = new LoginDataBaseAdapter(this);
        this.LB = this.LB.open();
        this.Pdialog = new ProgressDialog(this);

        this.rl = (RelativeLayout) findViewById(R.id.relativelayoutmenu);
        checknet();
        String versionName = BuildConfig.VERSION_NAME;
        this.last_sync = (TextView) findViewById(R.id.textView29);
        String datew = this.Cd.getUPDATED_STATUS2();
        this.last_sync.setText("Last Synced on " + this.Cd.getUPDATED_STATUS2());
        this.mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        this.mFirebaseRemoteConfig.setConfigSettings(new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(false).build());
        this.mFirebaseRemoteConfig.fetch(3600).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    MenuIconsAdmin.this.mFirebaseRemoteConfig.activateFetched();
                }
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.imageView3);
        String MY_PREFS_NAME = "MyPrefsFile";
        int i = 0;
        if (getSharedPreferences("MyPrefsFile", 0).getString("CompanyName", null).contains("Dev")) {
            i = 1;
        }
        String imgUrl = null;
        switch (i) {
            case R.styleable.View_android_theme /*0*/:
                imgUrl = "http://www.goldensoftwareservices.com/images/SO1.png";
                break;
            case R.styleable.View_android_focusable /*1*/:
                imgUrl = "http://www.goldensoftwareservices.com/images/Dev2.png";
                break;
            case R.styleable.View_paddingStart /*2*/:
                imgUrl = BuildConfig.FLAVOR;
                break;
        }
        //Drawable imag = null;
        //Glide.with(this).load(imgUrl).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(rl);
        Glide.with(this)
                .load(imgUrl)
                .transition(withCrossFade())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                .into(imageView);
        this.key_id = Integer.valueOf(getIntent().getExtras().getInt("key_id"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (this.Cd.getUPDATED_STATUS2() == null) {
            String date = "2015-06-20 02:42:54";
            this.Cd.createfirstlastdate(date);
            this.Cd.Server_update_last_date(date);
            synce();
            this.Pdialog.show();
            this.Pdialog.setMessage("Please wait while all of your files are Loading");
        }
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        this.progressBar.setVisibility(View.GONE);
        this.prefix = this.LB.get_Prfeix(this.key_id);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        this.btnView_customer = (Button) findViewById(R.id.button1);
        this.btnAdd_customer = (Button) findViewById(R.id.button2);
        this.btnAddOrder = (Button) findViewById(R.id.button3);
        this.btn_view_order = (Button) findViewById(R.id.button4);
        this.btnitems = (Button) findViewById(R.id.button21);

        this.btnSync = (Button) findViewById(R.id.button18);
        this.btnView_customer.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MenuIconsAdmin.this.startActivity(new Intent(MenuIconsAdmin.this.getApplicationContext(), Customer_details.class));
                MenuIconsAdmin.this.overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
        this.btnitems.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MenuIconsAdmin.this.startActivity(new Intent(MenuIconsAdmin.this.getApplicationContext(), item_activity.class));
                MenuIconsAdmin.this.overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
        this.btnAddOrder.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intentSettings = new Intent(MenuIconsAdmin.this.getApplicationContext(), Order_customer.class);
                intentSettings.putExtra("key_id", MenuIconsAdmin.this.key_id);
                MenuIconsAdmin.this.startActivity(intentSettings);
                MenuIconsAdmin.this.overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
        this.btn_view_order.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intentSettings = new Intent(MenuIconsAdmin.this.getApplicationContext(), Order2.class);
                intentSettings.putExtra("key_id", MenuIconsAdmin.this.key_id);
                MenuIconsAdmin.this.startActivity(intentSettings);
                MenuIconsAdmin.this.overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
        this.btnAdd_customer.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intentSettings = new Intent(MenuIconsAdmin.this.getApplicationContext(), Addcustomer.class);
                intentSettings.putExtra("key_id", MenuIconsAdmin.this.key_id);
                MenuIconsAdmin.this.startActivity(intentSettings);
                MenuIconsAdmin.this.overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
        this.btnSync.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MenuIconsAdmin.this.checknet();
                if (MenuIconsAdmin.this.hasInternetAccess(MenuIconsAdmin.this.getApplicationContext())) {
                    MenuIconsAdmin.this.synce();
                } else {
                    Toast.makeText(MenuIconsAdmin.this, "No Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checknet() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Socket sock = new Socket();
                    sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
                    sock.close();
                    Log.e("CHECK Net", "yes");
                    MenuIconsAdmin.this.checkNet = 1;
                } catch (IOException e) {
                    Log.e("CHECK Net", "no");
                    MenuIconsAdmin.this.checkNet = 0;
                }
            }
        }).start();
        return true;
    }

    public boolean hasInternetAccess(Context context) {
        if (isNetworkAvailable(context) && this.checkNet == 1) {
            return true;
        }
        Log.d("TAG", "No network available!");
        return false;
    }

    private boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    private void synce() {
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        this.btnSync = (Button) findViewById(R.id.button18);
        this.progressBar.setVisibility(View.VISIBLE);
        this.btnSync.setVisibility(View.GONE);
        Integer key_id = Integer.valueOf(getIntent().getExtras().getInt("key_id"));
        String last_update_check = this.Cd.getUPDATED_STATUS2();
        this.laste_server_date = this.Cd.getLAST_SERVER_STATUS2();
        final String EuId = this.LB.getEid(key_id);
        ArrayList UploadCUstomer = new ArrayList();
        UploadCUstomer = (ArrayList) this.Cd.getcustomerdetailsafterthedate(last_update_check);
        ArrayList Order4_Details = new ArrayList();
        Order4_Details = (ArrayList) this.Cd.getorderdetaislafterdate(this.Cd.getUPDATED_STATUS2());
        if (UploadCUstomer.size() != 0) {
            uploadcustomerdetails(EuId, last_update_check);
        }
        String finalLast_update_check = last_update_check;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                MenuIconsAdmin.this.getCustomerdetails(EuId, MenuIconsAdmin.this.laste_server_date);
            }
        }, 3000);
        getItemdetails(EuId, this.laste_server_date);
        if (Order4_Details.size() != 0) {
            UploadOrderDetails(EuId, this.Cd.getUPDATED_STATUS2());
            Uploadmax_no(EuId, Integer.valueOf(this.Cd.Check_max_id_Order()));
        }
    }

    private void get_server_date() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(1, "http://smartmds.in/GMobAppService.asmx/fn_GetAppCurServerTime", new Listener<String>() {
            public void onResponse(String response) {
                JSONArray jSONArray;
                JSONException e;
                Log.d("Response", response);
                try {
                    JSONArray jsonserverdate = new JSONArray(response);
                    try {
                        String myString = jsonserverdate.optJSONObject(0).getString("CurDatetime");
                        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                        String str = null;
                        try {
                            str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(inputFormat.parse(myString));
                        } catch (ParseException e2) {
                            e2.printStackTrace();
                        }
                        MenuIconsAdmin.this.Cd.Server_update_last_date(str);
                        jSONArray = jsonserverdate;
                    } catch (JSONException e3) {
                        e = e3;
                        jSONArray = jsonserverdate;
                        e.printStackTrace();
                    }
                } catch (JSONException e4) {
                    e = e4;
                    e.printStackTrace();
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", String.valueOf(error));
                MenuIconsAdmin.this.progressBar = (ProgressBar) MenuIconsAdmin.this.findViewById(R.id.progressBar2);
                MenuIconsAdmin.this.progressBar.setVisibility(View.GONE);
                MenuIconsAdmin.this.btnSync = (Button) MenuIconsAdmin.this.findViewById(R.id.button18);
                MenuIconsAdmin.this.btnSync.setVisibility(View.VISIBLE);
            }
        }) {
            protected Map<String, String> getParams() {
                return new HashMap();
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, 1, 1.0f));
        queue.add(postRequest);
    }

    private boolean uploadcustomerdetails(String eu_id, String last_date) {
        RequestQueue queue = Volley.newRequestQueue(this);
        ArrayList UploadCUstomer = new ArrayList();
        UploadCUstomer = (ArrayList) this.Cd.getcustomerdetailsafterthedate(last_date);
        final int Alength = UploadCUstomer.size();
        final ArrayList finalUploadCUstomer = UploadCUstomer;
        final String str = eu_id;
        StringRequest postRequest = new StringRequest(1, "http://smartmds.in/GMobAppService.asmx/fn_PostAppCustomer", new Listener<String>() {
            public void onResponse(String response) {
                if (response.contains("Record Added Sucessfully")) {
                    Toast.makeText(MenuIconsAdmin.this, "Customer Uploaded", Toast.LENGTH_SHORT).show();
                }
                Log.d("Response", response);
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", String.valueOf(error));
                MenuIconsAdmin.this.progressBar = (ProgressBar) MenuIconsAdmin.this.findViewById(R.id.progressBar2);
                MenuIconsAdmin.this.progressBar.setVisibility(View.VISIBLE);
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                JSONArray js = null;
                for (int i = 0; i < Alength; i++) {
                    Tutorial dataModel3 = (Tutorial) finalUploadCUstomer.get(i);
                    String line0 = String.valueOf(dataModel3.getid());
                    String line1 = String.valueOf(dataModel3.getOwnerName());
                    String line2 = String.valueOf(dataModel3.getShopname());
                    String line3 = String.valueOf(dataModel3.getPlace());
                    String line4 = String.valueOf(dataModel3.getAddress());
                    String line5 = String.valueOf(dataModel3.getMobile1());
                    String line6 = String.valueOf(dataModel3.getMobile2());
                    String line7 = String.valueOf(dataModel3.getShopArea());
                    String line8 = String.valueOf(dataModel3.getDistrict());
                    String line9 = String.valueOf(dataModel3.getEmail());
                    String line10 = String.valueOf(dataModel3.getPin());
                    String line11 = String.valueOf(dataModel3.getTin());
                    String line12 = String.valueOf(dataModel3.getLand_mark());
                    String line13 = MenuIconsAdmin.this.LB.getUsername(MenuIconsAdmin.this.key_id);
                    js = new JSONArray();
                    try {
                        js.put(i, line0 + "|" + line1 + "|" + line2 + "|" + line3 + "|" + line4 + "|" + line5 + "|" + line6 + "|" + line7 + "|" + line8 + "|" + line9 + "|" + line10 + "|" + line11 + "|" + line12 + "|" + line13);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                params.put("jsonString", String.valueOf(js));
                params.put("ls_euid", str);
                Log.d("APPCustomerSend", String.valueOf(params));
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, 1, 1.0f));
        queue.add(postRequest);
        return true;
    }

    private boolean getCustomerdetails(String eu_id, String last_date) {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String str = eu_id;
        final String str2 = last_date;
        Cd.deleteEnteredCustomer(0);
        StringRequest postRequest = new StringRequest(1, "http://smartmds.in/GMobAppService.asmx/fn_GetAppCustomers", new Listener<String>() {
            public void onResponse(String response) {
                //Log.d("Response", response);
                MenuIconsAdmin.this.success = response;
                try {
                    MenuIconsAdmin.this.jsonResponsecustomer = new JSONArray(response);
                    Log.d("customerResponse", String.valueOf(jsonResponsecustomer.length()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new Thread(new Runnable() {
                    public void run() {
                        int i = 0;
                        while (i < MenuIconsAdmin.this.jsonResponsecustomer.length()) {
                            JSONObject cast = MenuIconsAdmin.this.jsonResponsecustomer.optJSONObject(i);
                            try {
                                String shop_id = cast.getString("CustomerId");
                                String shop_name = cast.getString("shop_name");
                                String owner_name = cast.getString("owner_name");
                                String shop_place = cast.getString("shop_place");
                                String shop_address = cast.getString("shop_address");
                                String phone_number = cast.getString("phone_number");
                                String mobile_number = cast.getString("mobile_number");
                                String area = cast.getString("area");
                                String district = cast.getString("district");
                                String email = cast.getString("email");
                                String pin = cast.getString("pin");
                                String Tin = cast.getString("tinno");
                                String Land_mark = cast.getString("landmark");
                                String pendingamount = cast.getString("PenAmt");
                                String User_Name = cast.getString("UserName");
                                List getnullid = new ArrayList();
                                String last_updated = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());


                                 if (MenuIconsAdmin.this.Cd.getalreadtentercusid(shop_id).intValue() != 0)
                                 {
                                    MenuIconsAdmin.this.Cd.updateEntryUsingcus_id(shop_id, shop_name, owner_name, shop_place, shop_address, phone_number, mobile_number,
                                            area, district, email, pin, Tin, Land_mark, pendingamount, User_Name, last_updated, shop_id);
                                } else {
                                    MenuIconsAdmin.this.Cd.insertEntry(shop_id, shop_name, owner_name, shop_place, shop_address, phone_number, mobile_number,
                                            area, district, email, pin, Tin, Land_mark, pendingamount, User_Name, last_updated);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            i++;
                        }
                        MenuIconsAdmin.this.finalI = i;
                        if (MenuIconsAdmin.this.finalI == MenuIconsAdmin.this.jsonResponsecustomer.length() && MenuIconsAdmin.this.success != null) {
                            MenuIconsAdmin.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    MenuIconsAdmin.this.Pdialog.dismiss();
                                    MenuIconsAdmin.this.progressBar = (ProgressBar) MenuIconsAdmin.this.findViewById(R.id.progressBar2);
                                    MenuIconsAdmin.this.btnSync = (Button) MenuIconsAdmin.this.findViewById(R.id.button18);
                                    MenuIconsAdmin.this.progressBar.setVisibility(View.GONE);
                                    MenuIconsAdmin.this.btnSync.setVisibility(View.VISIBLE);
                                    MenuIconsAdmin.this.Cd.update_last_date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
                                    MenuIconsAdmin.this.last_sync.setText("Last Synced on " + MenuIconsAdmin.this.Cd.getUPDATED_STATUS2());
                                    MenuIconsAdmin.this.get_server_date();
                                }
                            });
                        }
                    }
                }).start();
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", String.valueOf(error));
                MenuIconsAdmin.this.progressBar = (ProgressBar) MenuIconsAdmin.this.findViewById(R.id.progressBar2);
                MenuIconsAdmin.this.progressBar.setVisibility(View.GONE);
                MenuIconsAdmin.this.btnSync = (Button) MenuIconsAdmin.this.findViewById(R.id.button18);
                MenuIconsAdmin.this.btnSync.setVisibility(View.VISIBLE);
                MenuIconsAdmin.this.errorResponse = String.valueOf(error);
                if (MenuIconsAdmin.this.errorResponse != null) {
                    Toast.makeText(MenuIconsAdmin.this, "Network Error", Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("ls_euid", str);
                params.put("ls_lastdate", str2);
                Log.d("APPCustomerget", String.valueOf(params));
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, 1, 1.0f));
        queue.add(postRequest);
        queue.addRequestFinishedListener(new RequestFinishedListener<String>() {
            public void onRequestFinished(Request<String> request) {
            }
        });
        return true;
    }

    private boolean getItemdetails(String eu_id, String last_date) {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String str = eu_id;
        final String str2 = last_date;
        StringRequest postRequest = new StringRequest(1, "http://smartmds.in/GMobAppService.asmx/fn_GetAppitems", new Listener<String>() {
            public void onResponse(String response) {
                Log.d("Response", response);
                try {
                    MenuIconsAdmin.this.jsonResponseitem = new JSONArray(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new Thread(new Runnable() {
                    public void run() {
                        String quantity = BuildConfig.FLAVOR;
                        for (int i = 0; i < MenuIconsAdmin.this.jsonResponseitem.length(); i++) {
                            JSONObject cast = MenuIconsAdmin.this.jsonResponseitem.optJSONObject(i);
                            try {
                                String item_id = cast.getString("ItemId");
                                String item_name = cast.getString(Param.ITEM_NAME);
                                String item_code = cast.getString("item_code");
                                String rate = cast.getString("rate");
                                String mrp = cast.getString("mrp");
                                if (MenuIconsAdmin.this.Cd.get_the_already_enterd_item(Integer.valueOf(item_id)) != Integer.valueOf(item_id).intValue()) {
                                    MenuIconsAdmin.this.Cd.insertItems(item_id, item_name, item_code, quantity, rate, mrp);
                                } else {
                                    MenuIconsAdmin.this.Cd.updateItems(item_id, item_name, item_code, quantity, rate, mrp);
                                    MenuIconsAdmin.this.Cd.update_last_date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            int value = i;
                            MenuIconsAdmin.this.add = i;
                        }
                        MenuIconsAdmin menuIconsAdmin = MenuIconsAdmin.this;
                        menuIconsAdmin.addtime += MenuIconsAdmin.this.jsonResponseitem.length();
                    }
                }).start();
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", String.valueOf(error));
                MenuIconsAdmin.this.progressBar = (ProgressBar) MenuIconsAdmin.this.findViewById(R.id.progressBar2);
                MenuIconsAdmin.this.progressBar.setVisibility(View.GONE);
                MenuIconsAdmin.this.btnSync = (Button) MenuIconsAdmin.this.findViewById(R.id.button18);
                MenuIconsAdmin.this.btnSync.setVisibility(View.VISIBLE);
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("ls_euid", str);
                params.put("ls_lastdate", str2);
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, 1, 1.0f));
        queue.add(postRequest);
        return true;
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
                    Toast.makeText(MenuIconsAdmin.this, "Order Uploaded", Toast.LENGTH_SHORT).show();
                }
                MenuIconsAdmin.this.responsecheck = response;
                Snackbar bar = Snackbar.make(MenuIconsAdmin.this.rl, " Orders Synced", 0);
                bar.show();
                bar.setActionTextColor(getResources().getColor(R.color.apple_grey));
                ((TextView) bar.getView().findViewById(R.id.snackbar_text)).setTextColor(getResources().getColor(R.color.teal_green));
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MenuIconsAdmin.this, "Check Connection and try again later", Toast.LENGTH_LONG).show();
                Log.d("Error.Response", String.valueOf(error));
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                for (int i = 0; i < Alength; i++) {
                    DataModel2 dataModel3 = (DataModel2) finalOrder4_Details.get(i);
                    String line1 = String.valueOf(dataModel3.getCustomer_id());
                    String line2 = String.valueOf(dataModel3.getOrder_date());
                    String line3 = String.valueOf(dataModel3.getOrder_no());
                    String line4 = String.valueOf(dataModel3.getProduct_no());
                    String line5 = String.valueOf(dataModel3.getQty());
                    String line6 = String.valueOf(dataModel3.getId());
                    int id = dataModel3.getId().intValue();
                    try {
                        MenuIconsAdmin.this.jse.put(i, line1 + "|" + line2 + "|" + MenuIconsAdmin.this.prefix + line3 + "|" + line4 + "|" + line5 + "|" + line6);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                params.put("jsonString", String.valueOf(MenuIconsAdmin.this.jse));
                params.put("ls_euid", str);
                MenuIconsAdmin.this.Cd.update_last_date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
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
                String jsonstring = String.valueOf(MenuIconsAdmin.this.jse);
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
        getMenuInflater().inflate(R.menu.menu_tabs_main, menu);
        this.LB.open();
        if (this.LB.getStatus(Integer.valueOf(key_id)).equals("N")) {
            menu.getItem(5).setEnabled(false);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        item.getItemId();
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
            String last_update_check = this.Cd.getUPDATED_STATUS2();
            ArrayList UploadCUstomer = new ArrayList();
            UploadCUstomer = (ArrayList) this.Cd.getcustomerdetailsafterthedate(last_update_check);
            ArrayList Order4_Details = new ArrayList();
            Order4_Details = (ArrayList) this.Cd.getorderdetaislafterdate(last_update_check);
            Editor editor = getSharedPreferences("MyPrefsFile", 0).edit();
            editor.remove("UserId");
            editor.commit();

            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            overridePendingTransition(R.anim.enter, R.anim.exit);
            return true;
        } else if (id == R.id.about_version) {
            Toast.makeText(this, "Version Code " + 7 + " Version Name " + BuildConfig.VERSION_NAME, Toast.LENGTH_SHORT).show();
            return true;
        } else if (id != R.id.about_us) {
            return super.onOptionsItemSelected(item);
        } else {
            startActivity(new Intent(getApplicationContext(), about_us.class));
            overridePendingTransition(R.anim.enter, R.anim.exit);
            return true;
        }
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void ending() {
        final Dialog dialoge = new Dialog(this);
        dialoge.setContentView(R.layout.confirmexit);
        dialoge.setTitle("Confirm");
        Button nobtn = (Button) dialoge.findViewById(R.id.button16);
        ((Button) dialoge.findViewById(R.id.button15)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MenuIconsAdmin.this.startActivity(new Intent(MenuIconsAdmin.this.getApplicationContext(), HomeActivity.class));
            }
        });
        nobtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dialoge.dismiss();
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        this.Cd.close();
        this.LB.close();
    }
}

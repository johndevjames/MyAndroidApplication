package com.johndevjames.myandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.miguelcatalan.materialsearchview.BuildConfig;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Testvolley3 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tesst);
        try {
            makeJsonObjReq();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        UploadOrderDetails();
        tester();
        postNewComment();
    }

    private void makeJsonObjReq() throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONArray js = new JSONArray();
        js.put("1095|487");
        js.put("322|311");
        String jse = js.toString();
        queue.add(new JsonArrayRequest(1, "http://smartmds.in/GMobAppService.asmx/fn_GetAppTest", js, new Listener<JSONArray>() {
            public void onResponse(JSONArray response) {
                Log.d("Response", String.valueOf(response));
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", String.valueOf(error));
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("strUserName", "john");
                params.put("strPwd", "123456");
                return params;
            }
        });
    }

    private void tester() {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Title", "Android Volley Demo");
            jsonBody.put("Author", "BNK");
            final String requestBody = jsonBody.toString();
            requestQueue.add(new StringRequest(1, "http://johndevjames.somee.com/Service1.svc/json/OrderDetails", new Listener<String>() {
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                public byte[] getBody() throws AuthFailureError {
                    byte[] bArr = null;
                    try {
                        if (requestBody != null) {
                            bArr = requestBody.getBytes("utf-8");
                        }
                    } catch (UnsupportedEncodingException e) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", new Object[]{requestBody, "utf-8"});
                    }
                    return bArr;
                }

                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = BuildConfig.FLAVOR;
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void postNewComment() {
        Volley.newRequestQueue(this).add(new StringRequest(1, "http://smartmds.in/GMobAppService.asmx/fn_GetAppTest", new Listener<String>() {
            public void onResponse(String response) {
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                JSONArray js = new JSONArray();
                js.put("1095|487");
                js.put("322|311");
                params.put("jsonString", String.valueOf(js));
                return params;
            }
        });
    }

    private void UploadOrderDetails() {
        Volley.newRequestQueue(this).add(new StringRequest(1, "http://smartmds.in/GMobAppService.asmx/fn_GetAppTest", new Listener<String>() {
            public void onResponse(String response) {
                Log.d("Response", response);
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("Error.Response", String.valueOf(error));
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("ID", "1");
                params.put("bookingID", "1");
                params.put("Schema", "2:00 Lunch");
                return params;
            }
        });
    }
}

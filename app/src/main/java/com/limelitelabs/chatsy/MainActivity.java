package com.limelitelabs.chatsy;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;


public class MainActivity extends Activity {

    EditText aliasInput, passkeyInput;
    CheckBox userTypeCheckbox;
    ViewGroup passkeyContainer;
    Button joinButton;
    boolean isPersistent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);

        aliasInput = (EditText) findViewById(R.id.aliasInput_input);
        passkeyInput = (EditText) findViewById(R.id.passkeyInput_input);
        passkeyContainer = (ViewGroup) findViewById(R.id.passkeyInput_container);
        userTypeCheckbox = (CheckBox) findViewById(R.id.userType_checkbox);
        joinButton = (Button) findViewById(R.id.joinAction_button);

        userTypeCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    passkeyContainer.setVisibility(View.VISIBLE);
                    isPersistent = true;
                } else {
                    passkeyContainer.setVisibility(View.GONE);
                    isPersistent = false;
                }
            }
        });

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passkeyContainer.setVisibility(View.VISIBLE);
                final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                String authUrl = "http://chatsy-alpha.herokuapp.com/";
                JSONObject postValues = new JSONObject();
                try {
                    postValues.put("alias", aliasInput.getText().toString());
                    if (isPersistent) {
                        postValues.put("passkey", passkeyInput.getText().toString());
                    }
                    JsonObjectRequest getAuth = new JsonObjectRequest(Request.Method.POST, authUrl, postValues, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("DEBUG", response.get("success").toString());
                                Log.d("DEBUG", cookieManager.getCookieStore().getURIs().size() + "");
                                Log.d("DEBUG", cookieManager.getCookieStore().getCookies().get(0) + "");
                                String getUrl = "http://chatsy-alpha.herokuapp.com/groups";
                                JsonObjectRequest getGroups = new JsonObjectRequest(Request.Method.GET,getUrl, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            Log.d("DEBUG", response.get("data").toString() + "");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        Log.d("DEBUG", cookieManager.getCookieStore().getURIs().size() + "");
                                        Log.d("DEBUG", cookieManager.getCookieStore().getCookies().get(0) + "");
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                requestQueue.add(getGroups);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    requestQueue.add(getAuth);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

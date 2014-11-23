package com.limelitelabs.chatsy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieManager;
import java.net.CookiePolicy;

/**
 * Created by Dell on 11/22/2014.
 */
public class BrowseGroupsActivity extends Activity {

    ListView browseGroupsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsegroups);
        browseGroupsList = (ListView) findViewById(R.id.browseGroups_list);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String getUrl = "http://chatsy-alpha.herokuapp.com/groups";
        JsonObjectRequest getGroups = new JsonObjectRequest(Request.Method.GET,getUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("DEBUG", response.get("data").toString() + "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(getGroups);
    }
}

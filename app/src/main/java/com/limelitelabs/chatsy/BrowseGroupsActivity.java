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
import com.limelitelabs.chatsy.models.Group;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity which allows users to browse through publicly visible groups
 */
public class BrowseGroupsActivity extends Activity {

    ListView browseGroupsListView;
    List<Group> browseGroupsList;
    BrowseGroupsListAdapter browseGroupsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsegroups);
        browseGroupsListView = (ListView) findViewById(R.id.browseGroups_list);
        browseGroupsList = new ArrayList<Group>();
        browseGroupsListAdapter = new BrowseGroupsListAdapter(BrowseGroupsActivity.this, browseGroupsList);
        browseGroupsListView.setAdapter(browseGroupsListAdapter);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String getUrl = "http://chatsy-alpha.herokuapp.com/groups";
        JsonObjectRequest getGroups = new JsonObjectRequest(Request.Method.GET,getUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    for(int i=0; i<data.length();i++) {
                        Group group = new Group();
                        group.parseJSON(data.getJSONObject(i));
                        browseGroupsList.add(group);
                    }
                    browseGroupsListAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
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

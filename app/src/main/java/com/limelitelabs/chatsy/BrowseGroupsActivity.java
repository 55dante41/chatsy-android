package com.limelitelabs.chatsy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

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
    }
}

package com.limelitelabs.chatsy;

import android.content.Context;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import com.limelitelabs.chatsy.models.Group;

/**
 * The Adapter for BrowseGroupsActivity ListView
 */
public class BrowseGroupsListAdapter extends ArrayAdapter<Group> {
    public BrowseGroupsListAdapter(Context context, int resource, Group[] objects) {
        super(context, resource, objects);
    }
}

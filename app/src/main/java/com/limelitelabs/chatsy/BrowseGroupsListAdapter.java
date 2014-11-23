package com.limelitelabs.chatsy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.limelitelabs.chatsy.models.Group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Adapter for BrowseGroupsActivity ListView
 */
public class BrowseGroupsListAdapter extends ArrayAdapter<Group> {

    Context context;
    List<Group> groups;

    // Default constructor with a predefined layout
    public BrowseGroupsListAdapter(Context context, List<Group> groups) {
        super(context, R.layout.listitem_browsegroups,groups);
        this.context = context;
        this.groups = groups;
    }

    // Constructor with custom item layout
    public BrowseGroupsListAdapter(Context context, int resource, List<Group> groups) {
        super(context, resource, groups);
        this.context = context;
        this.groups = groups;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Group getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GroupHolder groupHolder;
        View view = convertView;
        if(view!=null) {
            groupHolder = (GroupHolder) view.getTag();
        } else {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listitem_browsegroups, parent, false);
            groupHolder = new GroupHolder(view);
            view.setTag(groupHolder);
        }

        Group group = getItem(position);
        groupHolder.name.setText(group.name);
        groupHolder.description.setText(group.description);
        groupHolder.createdBy.setText(group.createdBy);
        //groupHolder.tagHolder.addView(new TextView(context).setText(group.tags[0]));

        return view;
    }

    static class GroupHolder {
        TextView name, description, createdBy;
        ViewGroup tagHolder;

        public GroupHolder(View v) {
            name = (TextView) v.findViewById(R.id.browseGroups_name);
            description = (TextView) v.findViewById(R.id.browseGroups_description);
            createdBy = (TextView) v.findViewById(R.id.browseGroups_createdBy);
            tagHolder = (ViewGroup) v.findViewById(R.id.browseGroups_tagHolder);
        }
    }
}

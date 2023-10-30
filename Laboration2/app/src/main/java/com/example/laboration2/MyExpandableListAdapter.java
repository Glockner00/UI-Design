package com.example.laboration2;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyExpandableListAdapter extends BaseExpandableListAdapter implements Filterable {
    private Context context;
    private Map<String, List<String>> collection;
    private List<String> groupList;
    private List<String> filteredGroupList;
    private List<String> filteredChildList;

    public MyExpandableListAdapter(Context context, List<String> groupList,
                                   Map<String, List<String>> collection) {
        this.context = context;
        this.groupList = groupList;
        this.collection = collection;
        this.filteredGroupList = new ArrayList<>(groupList);
        this.filteredChildList = new ArrayList<>();
    }

    @Override
    public int getGroupCount() {
        return filteredGroupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return collection.get(filteredGroupList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return filteredGroupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return collection.get(filteredGroupList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String name = getGroup(i).toString();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.group_item, null);
        }
        TextView item = view.findViewById(R.id.group);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(name);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String model = getChild(i, i1).toString();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_item, null);
        }
        TextView item = view.findViewById(R.id.child);
        item.setText(model);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                List<String> filteredGroups = new ArrayList<>();
                List<String> filteredChildren = new ArrayList<>();

                for (String group : groupList) {
                    for (String child : collection.get(group)) {
                        String combined = "/" + group + "/" + child + "/";
                        if (combined.contains(charSequence)) {
                            filteredGroups.add(group);
                            filteredChildren.add(child);
                        }
                    }
                }

                filterResults.values = filteredGroups;
                filterResults.count = filteredGroups.size();
                filteredChildList = filteredChildren;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredGroupList = (List<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

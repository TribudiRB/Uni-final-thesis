package com.bcuzek.magisterka.controllers.about.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.about.view.item.AboutItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.val;

public final class AboutAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<AboutItem> items;

    public AboutAdapter(Context context, List<AboutItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return items.get(groupPosition).getItems().size();
    }

    @Override
    public AboutItem getGroup(int position) {
        return items.get(position);
    }

    @Override
    public AboutItem getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).getItems().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.about_row_parent, parent, false);
            new ViewHolderParent(convertView);
        }
        ((ExpandableListView) parent).expandGroup(groupPosition);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderChild holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.about_row_child, parent, false);
            holder = new ViewHolderChild(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolderChild) convertView.getTag();
        val item = getChild(groupPosition, childPosition);
        holder.header.setText(context.getText(item.getHeader()));
        holder.subHeader.setText(context.getText(item.getSubHeader()));
        holder.image.setImageResource(item.getIcon());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    static class ViewHolderParent {
        @BindView(R.id.header)
        TextView header;

        public ViewHolderParent(View view) {
            ButterKnife.bind(this, view);
            header.setVisibility(View.GONE);
        }
    }

    static class ViewHolderChild {
        @BindView(R.id.header)
        TextView header;

        @BindView(R.id.subheader)
        TextView subHeader;

        @BindView(R.id.image)
        ImageView image;

        public ViewHolderChild(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

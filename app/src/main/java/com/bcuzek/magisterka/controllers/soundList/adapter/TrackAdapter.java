package com.bcuzek.magisterka.controllers.soundList.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.player.enums.Theme;
import com.bcuzek.magisterka.controllers.soundList.model.TrackViewModel;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.val;

public class TrackAdapter extends ArrayAdapter<TrackViewModel> {
    @Inject
    IPreference preferenceService;
    private Context context;
    private List<TrackViewModel> list;

    public TrackAdapter(Context context, List<TrackViewModel> list) {
        super(context, R.layout.track_single_controller, list);
        this.context = context;
        this.list = list;
        MagisterkaApplication.component().inject(this);
    }

    public View getView(int position, View rowView, ViewGroup parent) {
        ViewHolder holder;
        if (rowView == null) {
            rowView = LayoutInflater.from(context).inflate(R.layout.track_single_controller, parent, false);
            holder = new ViewHolder(rowView);
            rowView.setTag(holder);
        }
        holder = (ViewHolder) rowView.getTag();
        int element = preferenceService.readStyle() - 1;
        val model = list.get(position);
        holder.layout.setBackgroundResource(Theme.values()[element].getDrawable());
        holder.txtTitle.setText(model.getNames().split("\\- ")[1]);
        holder.composer.setText(model.getNames().split("\\- ")[0]);
        Picasso.with(context).load(model.getTracks().getIcon()).into(holder.imageView);
        holder.statistic.setText(model.getDescription());
        return rowView;
    }

    static class ViewHolder {
        @BindView(R.id.title)
        TextView txtTitle;

        @BindView(R.id.composer)
        TextView composer;

        @BindView(R.id.icon)
        ImageView imageView;

        @BindView(R.id.statistic)
        TextView statistic;

        @BindView(R.id.layout)
        RelativeLayout layout;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

package com.bcuzek.magisterka.controllers.soundList.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcuzek.magisterka.MainActivity;
import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.player.enums.Theme;
import com.bcuzek.magisterka.controllers.soundList.custom.CustomDTO;
import com.bcuzek.magisterka.controllers.soundList.custom.ExtrasCustomOptionListener;
import com.bcuzek.magisterka.controllers.soundList.custom.extrasPopupMenu.ExtrasPopupMenuListener;
import com.bcuzek.magisterka.controllers.soundList.model.RecordViewModel;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Setter;
import lombok.val;

public class RecordAdapter extends ArrayAdapter<RecordViewModel> {
    private final Context context;
    private final List<RecordViewModel> list;

    @Inject
    IPreference preferenceService;

    @Inject
    IIntentManager intentManager;

    @Setter
    private ExtrasPopupMenuListener.IOnDelete action;

    @Setter
    private Activity activity;

    public RecordAdapter(Context context, List<RecordViewModel> list) {
        super(context, R.layout.records_single_controller, list);
        this.context = context;
        this.list = list;
        MagisterkaApplication.component().inject(this);
    }

    public View getView(int position, View rowView, ViewGroup parent) {
        ViewModel holder;
        if (rowView == null) {
            rowView = LayoutInflater.from(context).inflate(R.layout.records_single_controller, parent, false);
            holder = new ViewModel(rowView);
            rowView.setTag(holder);
        }
        val listener = new ExtrasCustomOptionListener(context);
        holder = (ViewModel) rowView.getTag();
        val model = list.get(position);
        listener.setModel(CustomDTO
                .builder()
                .activity(activity)
                .id(model.getRecordses().getUuid())
                .action(action)
                .build());
        holder.extras.setOnClickListener(listener);
        holder.row.setOnClickListener(v -> {
            intentManager.send(intentManager.stopPlay());
            ((MainActivity) activity).openPlayer(model.getRecordses().getUuid());
        });
        int element = preferenceService.readStyle() - 1;
        holder.layout.setBackgroundResource(Theme.values()[element].getDrawable());
        holder.txtTitle.setText(model.getRecordses().getTitle().substring(20));
        Picasso.with(context)
                .load(model.getRecordses().getIcon())
                .into(holder.imageView);
        holder.statistic.setText(model.getDescription());
        return rowView;
    }

    static class ViewModel {
        @BindView(R.id.title)
        TextView txtTitle;

        @BindView(R.id.icon)
        ImageView imageView;

        @BindView(R.id.statistic)
        TextView statistic;

        @BindView(R.id.list_view_sound_icon2)
        ImageView extras;

        @BindView(R.id.all_row_custom)
        LinearLayout row;

        @BindView(R.id.layout)
        RelativeLayout layout;

        public ViewModel(View view) {
            ButterKnife.bind(this, view);
        }
    }

}

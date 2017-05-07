package com.bcuzek.magisterka.controllers.soundList.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.base.BaseController;
import com.bcuzek.magisterka.controllers.soundList.adapter.TrackAdapter;
import com.bcuzek.magisterka.controllers.soundList.model.track.ITrackView;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.infrastructure.constans.ViewTag;
import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnItemClick;
import lombok.Getter;
import lombok.val;

public class TracksController extends BaseController {
    @BindView(R.id.list)
    ListView list;

    @BindString(R.string.drawer_sound)
    @Getter
    String title;

    @Inject
    ITrackView data;

    @Inject
    IIntentManager intentManager;

    @Getter
    String fragmentTag = ViewTag.SoundListView;

    @Getter
    int fragmentIdentifier = R.layout.track_list_container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        val view = super.onCreateView(inflater, container, savedInstanceState);
        list.setAdapter(new TrackAdapter(getContext(), data.prepareData()));
        return view;
    }

    @OnItemClick(R.id.list)
    public void listOnClick(AdapterView<?> parent, View view, int position, long id) {
        intentManager.send(intentManager.stopPlay());
        getMainActivity().openPlayer(position);
    }

    @Override
    protected void initComponent() {
        MagisterkaApplication.component().inject(this);
    }

}

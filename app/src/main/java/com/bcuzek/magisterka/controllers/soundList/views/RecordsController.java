package com.bcuzek.magisterka.controllers.soundList.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.base.BaseController;
import com.bcuzek.magisterka.controllers.soundList.adapter.RecordAdapter;
import com.bcuzek.magisterka.controllers.soundList.model.record.IRecordView;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.infrastructure.constans.ViewTag;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import lombok.Getter;
import lombok.val;

public class RecordsController extends BaseController {

    @BindString(R.string.drawer_sound)
    @Getter
    String title;

    @BindView(R.id.list)
    ListView list;

    @Inject
    IRecordView data;

    @Getter
    String fragmentTag = ViewTag.CustomSoundListView;

    @Getter
    int fragmentIdentifier = R.layout.records_list_controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        val view = super.onCreateView(inflater, container, savedInstanceState);
        list.setAdapter(createAdapter());
        return view;
    }

    public void refresh() {
        list.setAdapter(createAdapter());
        getView().invalidate();
    }

    private RecordAdapter createAdapter() {
        val adapter = new RecordAdapter(getContext(), data.prepareData());
        adapter.setAction(() -> refresh());
        adapter.setActivity(getActivity());
        return adapter;
    }

    @Override
    protected void initComponent() {
        MagisterkaApplication.component().inject(this);
    }

}

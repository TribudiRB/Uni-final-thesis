package com.bcuzek.magisterka.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.about.dialog.IDialogHolder;
import com.bcuzek.magisterka.controllers.about.view.AboutAdapter;
import com.bcuzek.magisterka.controllers.about.view.item.IAboutListItems;
import com.bcuzek.magisterka.controllers.base.BaseController;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.infrastructure.constans.ViewTag;

import javax.inject.Inject;

import butterknife.BindView;
import lombok.Getter;
import lombok.val;

public class AboutController extends BaseController {
    @BindView(R.id.list)
    ExpandableListView infoItemsList;

    @Inject
    IDialogHolder dialogHolder;

    @Inject
    IAboutListItems listItem;

    @Getter
    String fragmentTag = ViewTag.AboutView;

    @Getter
    int fragmentIdentifier = R.layout.about_container;

    @Override
    protected void initComponent() {
        MagisterkaApplication.component().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        val view = super.onCreateView(inflater, container, savedInstanceState);
        val items = listItem.createListItems();
        infoItemsList.setAdapter(new AboutAdapter(getActivity(), items));
        infoItemsList.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            dialogHolder.getAction(items.get(groupPosition).getItems().get(childPosition).getIdentifier()).doAction(getActivity());
            return true;
        });
        return view;
    }
}

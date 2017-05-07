package com.bcuzek.magisterka.controllers.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bcuzek.magisterka.MainActivity;
import com.bcuzek.magisterka.infrastructure.fragments.IFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseController extends Fragment implements IFragment {
    protected Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentIdentifier(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initComponent();
        return view;
    }

    protected abstract int getFragmentIdentifier();

    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }

    protected abstract void initComponent();

}

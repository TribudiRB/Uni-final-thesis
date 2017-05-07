package com.bcuzek.magisterka.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.base.BaseRecordController;
import com.bcuzek.magisterka.controllers.record.RecordModel;
import com.bcuzek.magisterka.controllers.record.dialog.IOptionDialog;
import com.bcuzek.magisterka.infrastructure.MagisterkaApplication;
import com.bcuzek.magisterka.layout.BackAwareEditText;
import com.bcuzek.magisterka.layout.circuitTimer.CircuitTimer;
import com.bcuzek.magisterka.utils.color.IColorUtils;
import com.bcuzek.magisterka.utils.validator.IValidator;
import com.scalified.fab.ActionButton;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;

public class RecordController extends BaseRecordController {
    @BindView(R.id.timer)
    CircuitTimer clock;

    @BindView(R.id.record_button)
    ActionButton recordButton;

    @BindView(R.id.input)
    BackAwareEditText inputText;

    @BindString(R.string.wrong_name)
    String incorrectValidation;

    @Inject
    RecordModel recordModel;

    @Inject
    IOptionDialog optionDialog;

    @Inject
    IColorUtils colorUtils;

    @Inject
    IValidator validator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        clock.selectTimeDialog(() -> {});
        recordButton.setButtonColor(colorUtils.getCurrentColor(getActivity()));
        return view;
    }

    @OnEditorAction(R.id.input)
    public boolean inputEditorAction() {
        clock.setVisibility(View.VISIBLE);
        return false;
    }

    @OnClick(R.id.input)
    public void inputClickListener() {
        clock.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (recordModel != null && recordModel.isRecording())
            recordModel.stopRecording(null);
    }

    @Override
    protected void initComponent() {
        MagisterkaApplication.component().inject(this);
    }

    @OnClick(R.id.record_button)
    public void onRecordClickListener() {
        if (validator.validData(inputText.getText().toString())) {
            if (recordModel.isRecording()) {
                recordModel.stopRecording(inputText);
                recordButton.setButtonColor(colorUtils.getCurrentColor(getActivity()));
                optionDialog.showDialog(getActivity(),
                        () -> recordModel.deleteRecord(clock, inputText),
                        () -> recordModel.saveRecord(clock, inputText));
                recordButton.setState(ActionButton.State.NORMAL);
            } else {
                recordModel.createFileName(inputText);
                recordModel.startRecording(clock, inputText);
                recordButton.setState(ActionButton.State.PRESSED);
            }
        } else
            Toast.makeText(getActivity(), incorrectValidation, Toast.LENGTH_LONG).show();
    }

}

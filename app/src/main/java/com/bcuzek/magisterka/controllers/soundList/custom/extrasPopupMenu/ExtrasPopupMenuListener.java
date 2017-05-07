package com.bcuzek.magisterka.controllers.soundList.custom.extrasPopupMenu;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.soundList.custom.CustomDTO;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.IRecordRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.track.ITrackRepository;
import com.bcuzek.magisterka.infrastructure.intentManager.IIntentManager;
import com.bcuzek.magisterka.utils.file.IFileUtils;

import lombok.experimental.Builder;
import lombok.val;

@Builder
public class ExtrasPopupMenuListener implements IExtrasPopupMenuListener {
    private ITrackRepository custom;
    private IRecordRepository recordRepository;
    private IIntentManager intentCreator;
    private CustomDTO model;
    private IFileUtils manager;
    private int content;
    private int negativeText;
    private int positiveText;

    public void setCustomTransferModel(CustomDTO model) {
        this.model = model;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        showDialog(model.getActivity());
        return true;
    }

    private void showDialog(@NonNull Activity activity) {
        new MaterialDialog.Builder(activity)
                .content(content)
                .limitIconToDefaultSize()
                .negativeText(negativeText)
                .onNegative((dialog, which) -> deleteSelected())
                .positiveText(positiveText)
                .contentColorRes(R.color.colorRedDark)
                .contentGravity(GravityEnum.CENTER)
                .buttonsGravity(GravityEnum.CENTER)
                .positiveColorRes(R.color.colorBlue)
                .negativeColorRes(R.color.colorRedDark)
                .build()
                .show();
    }

    private void deleteSelected() {
        intentCreator.send(intentCreator.stopPlay());
        val record = recordRepository.getById(model.getId());
        val title = record.getTitle();
        recordRepository.delete(model.getId());
        manager.remove(title);
        model.getAction().action();
    }

    public interface IOnDelete {
        void action();
    }
}

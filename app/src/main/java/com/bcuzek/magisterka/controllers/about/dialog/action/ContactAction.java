package com.bcuzek.magisterka.controllers.about.dialog.action;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.utils.string.IStringUtils;

import lombok.experimental.Builder;
import lombok.val;

@Builder
public class ContactAction implements IDialogAction {
    private static final String MailTo = "mailto:";
    private IStringUtils stringUtils;

    @Override
    public void doAction(Activity activity) {
        val emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(MailTo.concat(stringUtils.getStringById(R.string.contact_mail))));
        activity.startActivity(emailIntent);
    }
}

package com.bcuzek.magisterka.modules;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.controllers.about.view.AboutIdentifier;
import com.bcuzek.magisterka.controllers.about.view.item.AboutItem;
import com.bcuzek.magisterka.controllers.about.view.item.AboutListItems;
import com.bcuzek.magisterka.controllers.about.view.item.IAboutListItems;
import com.bcuzek.magisterka.infrastructure.constans.qualifiers.AboutApplicationQualifiers;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 16.11.2016.
 */
@Module
public class AboutApplicationListItemsModule {

    @Provides
    public IAboutListItems getAboutApplicationListItem(@Named(AboutApplicationQualifiers.PARENT) AboutItem parent,
                                                       @Named(AboutApplicationQualifiers.DEVELOPER) AboutItem developer,
                                                       @Named(AboutApplicationQualifiers.VERSION) AboutItem version,
                                                       @Named(AboutApplicationQualifiers.ABOUT) AboutItem about,
                                                       @Named(AboutApplicationQualifiers.CONTACT) AboutItem contact,
                                                       @Named(AboutApplicationQualifiers.PERMISSION) AboutItem permission) {
        return AboutListItems
                .builder()
                .parent(parent)
                .developer(developer)
                .version(version)
                .about(about)
                .contact(contact)
                .permission(permission)
                .build();
    }

    @Provides
    @Named(AboutApplicationQualifiers.PARENT)
    AboutItem getPatent() {
        return AboutItem
                .builder()
                .header(R.string.application_developer)
                .icon(R.drawable.ic_account_circle_black_24dp)
                .subHeader(R.string.developer_name)
                .identifier(AboutIdentifier.DEVELOPER)
                .build();
    }

    @Singleton
    @Provides
    @Named(AboutApplicationQualifiers.DEVELOPER)
    AboutItem getDeveloper() {
        return AboutItem
                .builder()
                .header(R.string.application_developer)
                .icon(R.drawable.ic_account_circle_black_24dp)
                .subHeader(R.string.developer_name)
                .identifier(AboutIdentifier.DEVELOPER)
                .build();
    }

    @Singleton
    @Provides
    @Named(AboutApplicationQualifiers.VERSION)
    AboutItem getVersion() {
        return AboutItem
                .builder()
                .header(R.string.version)
                .icon(R.drawable.ic_android_black_24dp)
                .subHeader(R.string.version_information)
                .identifier(AboutIdentifier.VERSION)
                .build();
    }

    @Singleton
    @Provides
    @Named(AboutApplicationQualifiers.PERMISSION)
    AboutItem getPermission() {
        return AboutItem
                .builder()
                .header(R.string.permissions)
                .icon(R.drawable.ic_perm_device_information_black_24dp)
                .subHeader(R.string.permissions_question)
                .identifier(AboutIdentifier.PERMISSIONS)
                .build();
    }

    @Singleton
    @Provides
    @Named(AboutApplicationQualifiers.ABOUT)
    AboutItem getAbout() {
        return AboutItem
                .builder()
                .header(R.string.final_work)
                .icon(R.drawable.ic_description_black_24dp)
                .subHeader(R.string.about_final_work)
                .identifier(AboutIdentifier.THESIS)
                .build();
    }

    @Singleton
    @Provides
    @Named(AboutApplicationQualifiers.CONTACT)
    AboutItem getContact() {
        return AboutItem
                .builder()
                .header(R.string.contact)
                .icon(R.drawable.ic_android_black_24dp)
                .subHeader(R.string.contact_mail)
                .identifier(AboutIdentifier.CONTACT)
                .build();
    }
}
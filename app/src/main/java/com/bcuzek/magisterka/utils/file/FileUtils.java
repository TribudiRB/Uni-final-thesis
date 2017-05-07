package com.bcuzek.magisterka.utils.file;

import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.experimental.Builder;
import lombok.val;

/**
 * Created by robert on 06.10.2016.
 */
@Builder
public class FileUtils implements IFileUtils {
    private String catalog;
    private String format;
    private String dateFormatPrefix;

    public String createFile(String name) {
        val folder = new File(createFolderPath());
        boolean success = true;
        if (!folder.exists())
            success = folder.mkdir();
        return success ? createFilePath(name) : "";
    }

    public String createFileTitle(String name) {
        val dateFormat = new SimpleDateFormat(dateFormatPrefix);
        val today = Calendar.getInstance().getTime();
        return dateFormat.format(today).concat(name);
    }

    public void remove(String name) {
        new File(createFilePath(name)).delete();
    }

    @Override
    public int getPrefix() {
        return dateFormatPrefix.length();
    }

    private String createFilePath(String name) {
        return createFolderPath()
                .concat(File.separator)
                .concat(name)
                .concat(format);
    }

    @NonNull
    private String createFolderPath() {
        return Environment
                .getExternalStorageDirectory()
                .getAbsolutePath()
                .concat(File.separator)
                .concat(catalog);
    }

}

package com.bcuzek.magisterka.utils.file;

/**
 * Created by robert on 17.03.2017.
 */

public interface IFileUtils {
    String createFile(String name);

    String createFileTitle(String name);

    void remove(String name);

    int getPrefix();
}

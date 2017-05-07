package com.bcuzek.magisterka.controllers.soundList.model;

import java.util.List;

/**
 * Created by robert on 21.04.2017.
 */

public interface IDataView<T> {
    List<T> prepareData();
}

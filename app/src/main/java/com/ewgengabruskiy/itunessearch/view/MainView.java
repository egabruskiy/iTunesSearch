package com.ewgengabruskiy.itunessearch.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView  extends MvpView {

    void updateAlbumList();
    void init();
    void showError(String error);
}

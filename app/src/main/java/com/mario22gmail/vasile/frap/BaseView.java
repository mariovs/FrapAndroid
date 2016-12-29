package com.mario22gmail.vasile.frap;

/**
 * Created by mario.vasile on 10/20/2016.
 */

public interface BaseView <T extends BasePresenter> {
    void setPresenter(T presenter);
}

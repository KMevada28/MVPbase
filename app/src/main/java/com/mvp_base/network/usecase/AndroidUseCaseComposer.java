/*
 * Copyright © 2017 Demo_By_K_Mevada. All rights reserved.
 */

package com.mvp_base.network.usecase;


import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AndroidUseCaseComposer implements UseCaseComposer {

    @Override
    public <T> ObservableTransformer<T, T> apply() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

package com.mvp_base;

import android.content.SharedPreferences;

import com.mvp_base.base.BasePresenter;
import com.mvp_base.errors.ErrorHandler;
import com.mvp_base.network.usecase.DemoRequestUseCase;

import javax.inject.Inject;

/**
 * Created by kundankumar.mevada on 9/20/2018.
 */

public class MainPresenter extends BasePresenter<MainView> {

    SharedPreferences sharedPreferences;
    DemoRequestUseCase demoRequestUseCase;
    ErrorHandler errorHandler;

    @Inject
    public MainPresenter(SharedPreferences sharedPreferences, DemoRequestUseCase demoRequestUseCase, ErrorHandler errorHandler) {
        this.sharedPreferences = sharedPreferences;
        this.demoRequestUseCase = demoRequestUseCase;
        this.errorHandler = errorHandler;
    }

    boolean calculateData(String userName) {
        if (userName.length() > 0 && view != null) {
            view.displaySpeechRecognizer();
            return true;
        } else {
            return false;
        }
    }

    void getEmployee(String empId) {

        if (view == null) {
            return;
        }

        demoRequestUseCase.execute(empId)
                .compose(bindToLifecycle())
                .subscribe(empResponseEntity -> view.showEmpData(empResponseEntity), throwable -> {
                    if (view != null) {
                        view.handleAPIError(errorHandler.getErrorMessage(throwable).getMessage());
                    }
                });

    }

}
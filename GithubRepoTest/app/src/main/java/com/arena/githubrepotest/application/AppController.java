package com.arena.githubrepotest.application;

import android.app.Application;

import com.arena.githubrepotest.application.di.AppComponent;
import com.arena.githubrepotest.application.di.AppContextModule;
import com.arena.githubrepotest.application.di.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

import timber.log.BuildConfig;
import timber.log.Timber;

public class AppController extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initialiseLogger();
        initAppComponent();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder().appContextModule(new AppContextModule(this)).build();
    }

    private void initialiseLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    //TODO: preencher informação para release version
                }
            });
        }
    }

    public static AppComponent getNetComponent() {
        return appComponent;
    }


}

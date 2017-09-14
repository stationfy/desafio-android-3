package com.arena.githubrepotest.application.di;

import com.arena.githubrepotest.application.rx.AppRxSchedulers;
import com.arena.githubrepotest.application.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {

    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppRxSchedulers();
    }
}

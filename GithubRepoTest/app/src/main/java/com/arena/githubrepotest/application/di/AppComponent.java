package com.arena.githubrepotest.application.di;

import com.arena.githubrepotest.api.GitHubApi;
import com.arena.githubrepotest.application.rx.RxSchedulers;

import dagger.Component;

@AppScope
@Component(modules = {NetworkModule.class, AppContextModule.class, RxModule.class, GithubApiServiceModule.class})
public interface AppComponent {

    RxSchedulers rxSchedulers();
    GitHubApi apiService();
}

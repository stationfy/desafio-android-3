package com.arena.githubrepotest.views.repos.dagger;

import com.arena.githubrepotest.application.di.AppComponent;
import com.arena.githubrepotest.views.repos.RepositoriesListActivity;

import dagger.Component;

@RepositoriesScope
@Component(dependencies = {AppComponent.class} , modules = {RepositoriesModule.class})
public interface RepositoriesComponent {

    void inject (RepositoriesListActivity repositoriesListActivity);
}

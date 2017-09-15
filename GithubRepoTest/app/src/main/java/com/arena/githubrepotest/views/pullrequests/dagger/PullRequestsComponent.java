package com.arena.githubrepotest.views.pullrequests.dagger;

import com.arena.githubrepotest.application.di.AppComponent;
import com.arena.githubrepotest.views.pullrequests.PullRequestsActivity;

import dagger.Component;

@PullRequestsScope
@Component(dependencies = {AppComponent.class} , modules = {PullRequestsModule.class})
public interface PullRequestsComponent {

    void inject (PullRequestsActivity repositoriesListActivity);
}
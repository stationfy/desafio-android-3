package com.arena.githubrepotest.views.repos.core;

import com.arena.githubrepotest.utils.NetworkUtils;
import com.arena.githubrepotest.api.GitHubApi;
import com.arena.githubrepotest.models.Repositories;
import com.arena.githubrepotest.models.Repository;
import com.arena.githubrepotest.views.repos.RepositoriesListActivity;

import rx.Observable;

public class RepositoriesModel {

    RepositoriesListActivity context;
    GitHubApi api;

    public RepositoriesModel(RepositoriesListActivity context, GitHubApi api) {
        this.context = context;
        this.api = api;
    }

    Observable<Repositories> provideRepositories() {
        return api.getRepositories();
    }

    Observable<Boolean> isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailableObservable(context);
    }

    public void goToPullRequests(Repository repository) {
        context.goToPullRequests(repository);
    }
}

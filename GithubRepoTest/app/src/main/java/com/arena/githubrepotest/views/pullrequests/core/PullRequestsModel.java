package com.arena.githubrepotest.views.pullrequests.core;

import com.arena.githubrepotest.api.GitHubApi;
import com.arena.githubrepotest.models.PullRequest;
import com.arena.githubrepotest.utils.NetworkUtils;
import com.arena.githubrepotest.views.pullrequests.PullRequestsActivity;

import java.util.List;

import rx.Observable;

public class PullRequestsModel {

    PullRequestsActivity context;
    GitHubApi api;

    public PullRequestsModel(PullRequestsActivity context, GitHubApi api) {
        this.context = context;
        this.api = api;
    }

    Observable<List<PullRequest>> providePullRequests(String url) {
        return api.getPullRequests("repos/" + url + "/pulls");
    }

    Observable<Boolean> isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailableObservable(context);
    }

    public void openPullRequestInBrowser(String url) {
        context.openPullRequestInBrowser(url);
    }
}

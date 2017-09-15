package com.arena.githubrepotest.views.pullrequests.core;

import android.util.Log;

import com.arena.githubrepotest.application.rx.RxSchedulers;
import com.arena.githubrepotest.models.PullRequest;
import com.arena.githubrepotest.models.Repository;
import com.arena.githubrepotest.utils.UiUtils;

import java.util.ArrayList;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class PullRequestsPresenter {

    PullRequestsView view;
    PullRequestsModel model;
    RxSchedulers rxSchedulers;
    CompositeSubscription subscriptions;
    ArrayList<PullRequest> pullRequests = new ArrayList<>();

    public PullRequestsPresenter(RxSchedulers rxSchedulers, PullRequestsView view, PullRequestsModel model, CompositeSubscription subscriptions) {
        this.view = view;
        this.model = model;
        this.rxSchedulers = rxSchedulers;
        this.subscriptions = subscriptions;
    }

    public void onCreate(Repository repositorySelected) {
        Log.d("pull_requests", "Criando pull requests presenter");
        subscriptions.add(getPullRequests(repositorySelected.getFullName()));
        subscriptions.add(respondToClick());
    }

    public void onDestroy() {
        subscriptions.clear();
    }

    private Subscription respondToClick() {
        return view.itemClicks()
                .subscribe(integer -> model.openPullRequestInBrowser(pullRequests.get(integer).getUrl()));
    }

    private Subscription getPullRequests(String fullnameRepository) {

        return model.isNetworkAvailable()
                .doOnNext(networkAvailable -> {
                    if (!networkAvailable) {
                        Log.d("pull_requests", "Sem conexão com internet");
                    }
                })
                .filter(isNetworkAvailable -> true)
                .flatMap(isAvailable -> model.providePullRequests(fullnameRepository))
                .subscribeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread()).subscribe(pullRequests -> {
                            Log.d("pull_requests", "Pull requests carregados");
                            view.swapAdapter((ArrayList<PullRequest>) pullRequests);
                            this.pullRequests = (ArrayList<PullRequest>) pullRequests;
                        }, throwable -> {
                            UiUtils.handleThrowable(throwable);
                        }
                );

    }
}

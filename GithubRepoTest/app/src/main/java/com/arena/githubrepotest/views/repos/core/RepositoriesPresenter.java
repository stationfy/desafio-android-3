package com.arena.githubrepotest.views.repos.core;

import android.util.Log;

import com.arena.githubrepotest.utils.UiUtils;
import com.arena.githubrepotest.application.rx.RxSchedulers;
import com.arena.githubrepotest.models.Repository;

import java.util.ArrayList;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class RepositoriesPresenter {

    RepositoriesView view;
    RepositoriesModel model;
    RxSchedulers rxSchedulers;
    CompositeSubscription subscriptions;
    ArrayList<Repository> repositories = new ArrayList<>();

    public RepositoriesPresenter(RxSchedulers rxSchedulers, RepositoriesView view, RepositoriesModel model, CompositeSubscription subscriptions) {
        this.view = view;
        this.model = model;
        this.rxSchedulers = rxSchedulers;
        this.subscriptions = subscriptions;
    }

    public void onCreate() {
        Log.d("repositories", "Criando repositeries presenter");
        subscriptions.add(getRepositoriesList());
        subscriptions.add(respondToClick());
    }

    public void onDestroy() {
        subscriptions.clear();
    }

    private Subscription respondToClick() {
        return view.itemClicks()
                .subscribe(integer -> model.goToPullRequests(repositories.get(integer)));
    }

    private Subscription getRepositoriesList() {

        return model.isNetworkAvailable()
                .doOnNext(networkAvailable -> {
                    if (!networkAvailable) {
                        Log.d("repositories", "Sem conexão com internet");
                    }
                })
                .filter(isNetworkAvailable -> true)
                .flatMap(isAvailable -> model.provideRepositories())
                .subscribeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread()).subscribe(repositories -> {
                    Log.d("repositories", "Repositórios carregados");
                    view.swapAdapter((ArrayList<Repository>) repositories.getItems());
                    this.repositories = (ArrayList<Repository>) repositories.getItems();
                }, throwable -> {
                    UiUtils.handleThrowable(throwable);
                }
        );

    }
}

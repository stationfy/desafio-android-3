package com.arena.githubrepotest.views.repos.dagger;

import com.arena.githubrepotest.api.GitHubApi;
import com.arena.githubrepotest.application.rx.RxSchedulers;
import com.arena.githubrepotest.views.repos.RepositoriesListActivity;
import com.arena.githubrepotest.views.repos.core.RepositoriesModel;
import com.arena.githubrepotest.views.repos.core.RepositoriesPresenter;
import com.arena.githubrepotest.views.repos.core.RepositoriesView;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class RepositoriesModule {

    RepositoriesListActivity repositoriesListActivity;

    public RepositoriesModule(RepositoriesListActivity context) {
        this.repositoriesListActivity = context;
    }

    @RepositoriesScope
    @Provides
    RepositoriesView provideView() {
        return new RepositoriesView(repositoriesListActivity);
    }

    @RepositoriesScope
    @Provides
    RepositoriesPresenter providePresenter(RxSchedulers schedulers, RepositoriesView view, RepositoriesModel model) {
        CompositeSubscription subscriptions = new CompositeSubscription();
        return new RepositoriesPresenter(schedulers, view, model, subscriptions);
    }

    @RepositoriesScope
    @Provides
    RepositoriesListActivity provideContext() {
        return repositoriesListActivity;
    }

    @RepositoriesScope
    @Provides
    RepositoriesModel provideModel(GitHubApi api) {
        return new RepositoriesModel(repositoriesListActivity, api);
    }
}

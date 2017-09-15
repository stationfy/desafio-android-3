package com.arena.githubrepotest.views.pullrequests.dagger;

import com.arena.githubrepotest.api.GitHubApi;
import com.arena.githubrepotest.application.rx.RxSchedulers;
import com.arena.githubrepotest.models.PullRequest;
import com.arena.githubrepotest.models.Repository;
import com.arena.githubrepotest.views.pullrequests.PullRequestsActivity;
import com.arena.githubrepotest.views.pullrequests.core.PullRequestsModel;
import com.arena.githubrepotest.views.pullrequests.core.PullRequestsPresenter;
import com.arena.githubrepotest.views.pullrequests.core.PullRequestsView;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class PullRequestsModule {

    PullRequestsActivity context;
    Repository repository;

    public PullRequestsModule(PullRequestsActivity context, Repository repository) {
        this.context = context;
        this.repository = repository;
    }

    @PullRequestsScope
    @Provides
    PullRequestsView provideView() {
        return new PullRequestsView(context, repository);
    }

    @PullRequestsScope
    @Provides
    PullRequestsPresenter providePresenter(RxSchedulers schedulers, PullRequestsView view, PullRequestsModel model) {
        CompositeSubscription subscriptions = new CompositeSubscription();
        return new PullRequestsPresenter(schedulers, view, model, subscriptions);
    }

    @PullRequestsScope
    @Provides
    PullRequestsActivity provideContext() {
        return context;
    }

    @PullRequestsScope
    @Provides
    PullRequestsModel provideModel(GitHubApi api) {
        return new PullRequestsModel(context, api);
    }
}

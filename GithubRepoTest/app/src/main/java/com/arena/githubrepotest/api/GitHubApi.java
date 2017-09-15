package com.arena.githubrepotest.api;

import com.arena.githubrepotest.models.PullRequest;
import com.arena.githubrepotest.models.Repositories;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface GitHubApi {

    //TODO: Analisar a chamada a API aqui
    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    Observable<Repositories> getRepositories();

    @GET
    Observable<List<PullRequest>> getPullRequests(@Url String url);

}

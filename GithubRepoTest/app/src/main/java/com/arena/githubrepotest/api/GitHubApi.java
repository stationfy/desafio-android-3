package com.arena.githubrepotest.api;

import com.arena.githubrepotest.models.Repositories;

import retrofit2.http.GET;
import rx.Observable;

public interface GitHubApi {

    //TODO: Analisar a chamada a API aqui
    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    Observable<Repositories> getRepositories();

    @GET("repos/ReactiveX/RxJava/pulls")
    Observable<Repositories> getPullRequests();

}

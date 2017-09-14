package com.arena.githubrepotest.application.di;

import com.arena.githubrepotest.api.GitHubApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class GithubApiServiceModule {

    private static final String BASE_URL_API = "https://api.github.com/";

    @AppScope
    @Provides
    GitHubApi provideApiService(OkHttpClient client, GsonConverterFactory gson, RxJavaCallAdapterFactory rxAdapterFactory) {

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL_API)
                .addConverterFactory(gson)
                .addCallAdapterFactory(rxAdapterFactory)
                .build();

        return retrofit.create(GitHubApi.class);
    }
}

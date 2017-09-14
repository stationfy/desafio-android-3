package com.arena.githubrepotest.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Repositories {

    @Expose
    private List<Repository> items;

    public List<Repository> getItems() {
        return items;
    }

    public void setItems(List<Repository> items) {
        this.items = items;
    }
}

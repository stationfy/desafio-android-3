package com.arena.githubrepotest.utils;

import timber.log.Timber;

public class UiUtils {

    public static void handleThrowable(Throwable throwable) {
        Timber.e(throwable, throwable.toString());
    }
}

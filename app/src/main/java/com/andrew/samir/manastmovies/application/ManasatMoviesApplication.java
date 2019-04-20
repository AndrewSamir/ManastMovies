
package com.andrew.samir.manastmovies.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.andrew.samir.manastmovies.retorfitconfig.ApiCall;
import com.andrew.samir.manastmovies.retorfitconfig.RestManasatMovies;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class ManasatMoviesApplication extends MultiDexApplication {

    private ApiCall apiCallInterface;
    private Scheduler scheduler;

    private static ManasatMoviesApplication get(Context context) {
        return (ManasatMoviesApplication) context.getApplicationContext();
    }

    public static ManasatMoviesApplication create(Context context) {
        return ManasatMoviesApplication.get(context);
    }

    public ApiCall getApiCall() {
        if (apiCallInterface == null) {
            apiCallInterface = RestManasatMovies.create();
        }

        return apiCallInterface;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setApiCall(ApiCall apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}

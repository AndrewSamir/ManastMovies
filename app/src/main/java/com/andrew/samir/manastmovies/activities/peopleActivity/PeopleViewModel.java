package com.andrew.samir.manastmovies.activities.peopleActivity;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;


import com.andrew.samir.manastmovies.Data.PeopleResponseData.PersonDetailsData;
import com.andrew.samir.manastmovies.Data.PeopleResponseData.ResponseData;
import com.andrew.samir.manastmovies.R;
import com.andrew.samir.manastmovies.activities.BaseViewModel;
import com.andrew.samir.manastmovies.application.ManasatMoviesApplication;
import com.andrew.samir.manastmovies.retorfitconfig.ApiCall;
import com.andrew.samir.manastmovies.utlities.HelpMe;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class PeopleViewModel extends BaseViewModel {

    //region fields
    public ObservableField<String> queryString = new ObservableField<>();
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<PersonDetailsData> peopleList;
    private int page = 1;
    private int searchPage = 1;
    boolean isSearch = false;

    //endregion

    //region constructor
    PeopleViewModel(@NonNull Context context) {
        this.context = context;
        peopleList = new ArrayList<>();
        fetchCategories();
    }
    //endregion

    //region clicks
    public void AtBtnClick() {
        callSearchPeople();
    }

    //endregion

    //region calls

    private void fetchCategories() {
        HelpMe.showLoading(true, context);
        ManasatMoviesApplication peopleApplication = ManasatMoviesApplication.create(context);
        ApiCall peopleService = peopleApplication.getApiCall();
        searchPage = 1;
        isSearch = false;
        Disposable disposable = peopleService.callGetPeopleList(peopleApplication.getString(R.string.movie_key), page)
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData>() {
                    @Override
                    public void accept(ResponseData peopleResponse) {
                        HelpMe.showLoading(false, context);
                        changePeopleDataSet(peopleResponse.getResults());
                        Log.d("calls", peopleResponse.getResults().get(0).getName());
                        page++;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        HelpMe.showLoading(false, context);
                        showMessage(R.string.connection_error, context);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void callSearchPeople() {
        HelpMe.showLoading(true, context);
        ManasatMoviesApplication peopleApplication = ManasatMoviesApplication.create(context);
        ApiCall peopleService = peopleApplication.getApiCall();
        page = 1;
        isSearch = true;
        Disposable disposable = peopleService.callSearchPeople(peopleApplication.getString(R.string.movie_key), queryString.get(), searchPage)
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData>() {
                    @Override
                    public void accept(ResponseData peopleResponse) {
                        HelpMe.showLoading(false, context);
                        changePeopleDataSetSearch(peopleResponse.getResults());
                        searchPage++;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        HelpMe.showLoading(false, context);
                        showMessage(R.string.connection_error, context);
                    }
                });

        compositeDisposable.add(disposable);
    }

    void callSearchPeopleMore() {
        HelpMe.showLoading(true, context);
        ManasatMoviesApplication peopleApplication = ManasatMoviesApplication.create(context);
        ApiCall peopleService = peopleApplication.getApiCall();
        page = 1;
        Disposable disposable = peopleService.callSearchPeople(peopleApplication.getString(R.string.movie_key), queryString.get(), searchPage)
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData>() {
                    @Override
                    public void accept(ResponseData peopleResponse) {
                        HelpMe.showLoading(false, context);
                        changePeopleDataSet(peopleResponse.getResults());
                        searchPage++;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        HelpMe.showLoading(false, context);
                        showMessage(R.string.connection_error, context);
                    }
                });

        compositeDisposable.add(disposable);
    }

    void fetchCategoriesMore() {
        HelpMe.showLoading(true, context);
        ManasatMoviesApplication peopleApplication = ManasatMoviesApplication.create(context);
        ApiCall peopleService = peopleApplication.getApiCall();

        Disposable disposable = peopleService.callGetPeopleList(peopleApplication.getString(R.string.movie_key), page)
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData>() {
                    @Override
                    public void accept(ResponseData peopleResponse) {
                        HelpMe.showLoading(false, context);
                        page++;
                        changePeopleDataSet(peopleResponse.getResults());
                        Log.d("calls", peopleResponse.getResults().get(0).getName());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        HelpMe.showLoading(false, context);
                        showMessage(R.string.connection_error, context);
                    }
                });

        compositeDisposable.add(disposable);
    }

    //endregion

    //region functions
    private void changePeopleDataSet(List<PersonDetailsData> peoples) {
        peopleList.addAll(peoples);
        setChanged();
        notifyObservers("call");
    }

    private void changePeopleDataSetSearch(List<PersonDetailsData> peoples) {
        peopleList.clear();
        peopleList.addAll(peoples);
        setChanged();
        notifyObservers("call");
    }

    List<PersonDetailsData> getPeopleList() {
        return peopleList;
    }


    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
    //endregion
}

package com.andrew.samir.manastmovies.activities.peopleActivity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.widget.ImageView;


import com.andrew.samir.manastmovies.Data.PersonDetailsData;
import com.andrew.samir.manastmovies.Data.ResponseData;
import com.andrew.samir.manastmovies.R;
import com.andrew.samir.manastmovies.activities.BaseViewModel;
import com.andrew.samir.manastmovies.application.ManasatMoviesApplication;
import com.andrew.samir.manastmovies.retorfitconfig.ApiCall;
import com.andrew.samir.manastmovies.utlities.HelpMe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class PeopleViewModel extends BaseViewModel {

    //region fields
    public ObservableField<String> testString = new ObservableField<>();
    private ObservableInt testInt = new ObservableInt(0);
    private MutableLiveData<Integer> x = new MutableLiveData<>();
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<PersonDetailsData> peopleList;
    int page = 1;
    int searchPage = 1;
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
//        Log.d("testText",testString.get());
        callSearchPeople();
    }

    //endregion

    //region Live Data
    LiveData<Integer> testLiveData() {
        return x;
    }
    //endregion

    //region calls

    private void fetchCategories() {
        HelpMe.showLoading(true);
        ManasatMoviesApplication peopleApplication = ManasatMoviesApplication.create(context);
        ApiCall peopleService = peopleApplication.getApiCall();
        searchPage = 1;
        isSearch = false;
        Disposable disposable = peopleService.callGetPeopleList("c9118723dfaacb064858a46444a9a6c8", page)
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData>() {
                    @Override
                    public void accept(ResponseData peopleResponse) {
                        HelpMe.showLoading(false);
                        changePeopleDataSet(peopleResponse.getResults());
                        Log.d("calls", peopleResponse.getResults().get(0).getName());
                        page++;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        HelpMe.showLoading(false);
                        showMessage(R.string.connection_error, context);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void callSearchPeople() {
        HelpMe.showLoading(true);
        ManasatMoviesApplication peopleApplication = ManasatMoviesApplication.create(context);
        ApiCall peopleService = peopleApplication.getApiCall();
        page = 1;
        isSearch = true;
        Disposable disposable = peopleService.callSearchPeople("c9118723dfaacb064858a46444a9a6c8", testString.get(), searchPage)
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData>() {
                    @Override
                    public void accept(ResponseData peopleResponse) {
                        HelpMe.showLoading(false);
                        changePeopleDataSetSearch(peopleResponse.getResults());
                        searchPage++;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        HelpMe.showLoading(false);
                        showMessage(R.string.connection_error, context);
                    }
                });

        compositeDisposable.add(disposable);
    }

    void callSearchPeopleMore() {
        HelpMe.showLoading(true);
        ManasatMoviesApplication peopleApplication = ManasatMoviesApplication.create(context);
        ApiCall peopleService = peopleApplication.getApiCall();
        page = 1;
        Disposable disposable = peopleService.callSearchPeople("c9118723dfaacb064858a46444a9a6c8", testString.get(), searchPage)
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData>() {
                    @Override
                    public void accept(ResponseData peopleResponse) {
                        HelpMe.showLoading(false);
                        changePeopleDataSet(peopleResponse.getResults());
                        searchPage++;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        HelpMe.showLoading(false);
                        showMessage(R.string.connection_error, context);
                    }
                });

        compositeDisposable.add(disposable);
    }

    void fetchCategoriesMore() {
        HelpMe.showLoading(true);
        ManasatMoviesApplication peopleApplication = ManasatMoviesApplication.create(context);
        ApiCall peopleService = peopleApplication.getApiCall();

        Disposable disposable = peopleService.callGetPeopleList("c9118723dfaacb064858a46444a9a6c8", page)
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData>() {
                    @Override
                    public void accept(ResponseData peopleResponse) {
                        HelpMe.showLoading(false);
                        page++;
                        changePeopleDataSet(peopleResponse.getResults());
                        Log.d("calls", peopleResponse.getResults().get(0).getName());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        HelpMe.showLoading(false);
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

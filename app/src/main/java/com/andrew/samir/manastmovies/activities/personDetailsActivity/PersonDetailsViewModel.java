package com.andrew.samir.manastmovies.activities.personDetailsActivity;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.andrew.samir.manastmovies.Data.PeopleResponseData.PersonDetailsData;
import com.andrew.samir.manastmovies.Data.PersonDetailsResponseData.PersonDetailsResponseData;
import com.andrew.samir.manastmovies.Data.PersonImagesResponseData.PersonImagesResponseData;
import com.andrew.samir.manastmovies.Data.PersonImagesResponseData.Profiles;
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

public class PersonDetailsViewModel extends BaseViewModel {

    //region fields
    public ObservableField<PersonDetailsResponseData> personDetails = new ObservableField<>();
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private int personId;
    private List<Profiles> profilesList;
    public ObservableField<Drawable> profileImage;
    private BindableFieldTarget bindableFieldTarget;
    //endregion

    //region constructor
    PersonDetailsViewModel(int personId, @NonNull Context context) {
        this.context = context;
        this.personId = personId;
        profileImage = new ObservableField<>();
        bindableFieldTarget = new BindableFieldTarget(profileImage, context.getResources());
        profilesList = new ArrayList<>();
        callPersonDetails();
    }
    //endregion

    //region calls

    private void callPersonDetails() {
        HelpMe.showLoading(true, context);
        ManasatMoviesApplication peopleApplication = ManasatMoviesApplication.create(context);
        ApiCall peopleService = peopleApplication.getApiCall();
        Disposable disposable = peopleService.callPersonDetails(personId, peopleApplication.getString(R.string.movie_key))
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PersonDetailsResponseData>() {
                    @Override
                    public void accept(PersonDetailsResponseData personDetailsResponseData) {
                        HelpMe.showLoading(false, context);
                        personDetails.set(personDetailsResponseData);

                        Picasso.with(context)
                                .load(context.getString(R.string.base_url) + personDetailsResponseData.getProfilePath())
                                .placeholder(R.mipmap.ic_launcher)
                                .into(bindableFieldTarget);
                        callPersonImages();
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

    private void callPersonImages() {
        HelpMe.showLoading(true, context);
        ManasatMoviesApplication peopleApplication = ManasatMoviesApplication.create(context);
        ApiCall peopleService = peopleApplication.getApiCall();
        Disposable disposable = peopleService.callPersonImages(personId, peopleApplication.getString(R.string.movie_key))
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PersonImagesResponseData>() {
                    @Override
                    public void accept(PersonImagesResponseData personImagesResponseData) {
                        HelpMe.showLoading(false, context);
                        changePersonImagesDataSet(personImagesResponseData.getProfiles());

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

    private void changePersonImagesDataSet(List<Profiles> profiles) {
        profilesList.addAll(profiles);
        setChanged();
        notifyObservers("call");
    }

    List<Profiles> getProfilesList() {
        return profilesList;
    }


    //endregion
}

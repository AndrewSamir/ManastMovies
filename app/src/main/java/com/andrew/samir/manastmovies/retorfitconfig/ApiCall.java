package com.andrew.samir.manastmovies.retorfitconfig;

import com.andrew.samir.manastmovies.Data.PersonDetailsResponseData.PersonDetailsResponseData;
import com.andrew.samir.manastmovies.Data.PeopleResponseData.ResponseData;
import com.andrew.samir.manastmovies.Data.PersonImagesResponseData.PersonImagesResponseData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCall {

    @GET("person/popular")
    Observable<ResponseData> callGetPeopleList(@Query("api_key") String api_key, @Query("page") int page);

    @GET("search/person")
    Observable<ResponseData> callSearchPeople(@Query("api_key") String api_key, @Query("query") String query, @Query("page") int page);

    @GET("person/{id}")
    Observable<PersonDetailsResponseData> callPersonDetails(@Path("id") int id, @Query("api_key") String api_key);

    @GET("person/{id}/images")
    Observable<PersonImagesResponseData> callPersonImages(@Path("id") int id, @Query("api_key") String api_key);

}


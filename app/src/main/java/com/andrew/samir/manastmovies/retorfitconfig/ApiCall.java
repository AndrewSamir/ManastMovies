package com.andrew.samir.manastmovies.retorfitconfig;

import com.andrew.samir.manastmovies.Data.ResponseData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {

    @GET("person/popular")
    Observable<ResponseData> callGetPeopleList(@Query("api_key") String api_key, @Query("page") int page);

    @GET("search/person")
    Observable<ResponseData> callSearchPeople(@Query("api_key") String api_key, @Query("query") String query, @Query("page") int page);

}


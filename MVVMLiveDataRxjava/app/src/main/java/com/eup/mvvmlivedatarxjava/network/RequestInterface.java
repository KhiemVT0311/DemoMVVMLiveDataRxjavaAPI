package com.eup.mvvmlivedatarxjava.network;

import com.eup.mvvmlivedatarxjava.model.Android;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RequestInterface {
    @GET("android/jsonarray/")
    Observable<List<Android>> register();
}

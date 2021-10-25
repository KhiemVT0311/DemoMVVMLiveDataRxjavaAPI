package com.eup.mvvmlivedatarxjava.ui.screen.main;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.eup.mvvmlivedatarxjava.model.Android;
import com.eup.mvvmlivedatarxjava.network.RequestInterface;
import com.eup.mvvmlivedatarxjava.ui.base.BaseViewModel;
import com.eup.mvvmlivedatarxjava.util.SystemData;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends BaseViewModel {
    public static final String BASE_URL = "https://api.learn2crack.com/";
    private CompositeDisposable mCompositeDisposable;


    private MutableLiveData<List<Android>> listMutableLiveData;
    private List<Android> mandroids;
    public MainViewModel(){
        listMutableLiveData = new MutableLiveData<>();
        initData();
    }

    private void initData() {
        mCompositeDisposable = new CompositeDisposable();
        loadJSON();
    }
    private void loadJSON() {
        RequestInterface requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestInterface.class);

        Disposable disposable = requestInterface.register()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError,this::handleSuccess);
        mCompositeDisposable.add(disposable);
    }
    private void handleResponse(List<Android> androids) {
        mandroids = new ArrayList<>(androids);
        listMutableLiveData.setValue(mandroids);
    }

    private void handleError(Throwable error) { //xử lý khi lỗi
        Log.e("Log","lỗi"+error);
    }

    private void handleSuccess() { // xử lý khi get data thành công
        Log.e("Log","thanh cong");
    }

    public MutableLiveData<List<Android>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void setMandroids(List<Android> mandroids) {
        this.mandroids = mandroids;
        listMutableLiveData.setValue(mandroids);
    }

    public List<Android> getAndroids(Android android) {
        return mandroids;
    }
    public void addAndroid(List<Android> android){
        mandroids.add((Android) android);
        listMutableLiveData.setValue(mandroids);
    }
}

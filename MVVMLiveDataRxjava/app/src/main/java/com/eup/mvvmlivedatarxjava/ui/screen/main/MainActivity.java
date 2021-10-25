package com.eup.mvvmlivedatarxjava.ui.screen.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import com.eup.mvvmlivedatarxjava.R;
import com.eup.mvvmlivedatarxjava.databinding.ActivityMainBinding;
import com.eup.mvvmlivedatarxjava.model.Android;
import com.eup.mvvmlivedatarxjava.network.RequestInterface;
import com.eup.mvvmlivedatarxjava.ui.base.BaseActivity;
import com.eup.mvvmlivedatarxjava.ui.base.BaseBindingAdapter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainViewModel> {
    private BaseBindingAdapter<Android> adapter;

    @Override
    protected Class<MainViewModel> getViewModelClass() {
        return MainViewModel.class;
    }

    @Override
    protected void init() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getListMutableLiveData().observe(this, new Observer<List<Android>>() {
            @Override
            public void onChanged(List<Android> androids) {
                Log.e("Log", String.valueOf(androids));
                adapter = new BaseBindingAdapter<>(R.layout.version_android_item_view,getLayoutInflater());
                binding.setAdapter(adapter);
                adapter.setData((ArrayList<Android>) androids);
            }
        });

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
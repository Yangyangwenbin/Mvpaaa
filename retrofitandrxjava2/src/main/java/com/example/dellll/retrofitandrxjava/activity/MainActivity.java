package com.example.dellll.retrofitandrxjava.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dellll.retrofitandrxjava.R;
import com.example.dellll.retrofitandrxjava.api.Api;
import com.example.dellll.retrofitandrxjava.bean.News;
import com.example.dellll.retrofitandrxjava.inter.ApiService;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getNoParams();
    }

    private void getNoParams() {
        //数据源
        Retrofit retorfit= new Retrofit.Builder()
                .baseUrl(Api.BASE_PATH)
                .addConverterFactory(GsonConverterFactory.create())//gson解析
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
                .build();
        ApiService apiService = retorfit.create(ApiService.class);
        //得到observable
        Observable<News> observable = apiService.getNoParams();//生产事件
        //被观察者订阅观察 默认在同一个线程
        observable
                .subscribeOn(Schedulers.io())//指定IO做耗时操作
                .observeOn(AndroidSchedulers.mainThread())//指定更新UI在主线程
                .subscribe(new Observer<News>() {

            @Override
            public void onCompleted() {//完成

            }

            @Override
            public void onError(Throwable e) {//失败

            }

            @Override
            public void onNext(News news) {//消费事件
                List<News.AdsBean> ads = news.getAds();
                for (int i = 0; i <ads.size() ; i++) {
                    News.AdsBean adsBean = ads.get(i);
                    String gonggaoren = adsBean.getGonggaoren();
                    Log.i("xxx",gonggaoren);

                }

            }
        });

    }
}

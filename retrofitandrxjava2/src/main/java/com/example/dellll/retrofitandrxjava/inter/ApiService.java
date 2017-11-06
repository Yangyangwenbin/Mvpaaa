package com.example.dellll.retrofitandrxjava.inter;

import com.example.dellll.retrofitandrxjava.bean.News;
import com.example.dellll.retrofitandrxjava.bean.User;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by dellll on 2017.11.05.
 */

public interface ApiService {
    /**
     * 结合Retrofit+RxJava
     * thtp://service.meiyinkeqiu.com/service/ads/cptj
     * @param
     * @return
     */
    @GET("ads/cptj")
    Observable <News> getNoParams();
    /**
     * 结合RxJava
     * @param user
     * @return
     * https://api.github.com/users/forever
     */
    @GET("users/{user}")
    Observable<User>getHasparams(@Path("user")String user);
}

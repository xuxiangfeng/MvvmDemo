package cn.xxf.mvvm.httplib;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xuxiangfeng on 2018/1/20.
 */

public class HttpFactory {

    public static IServerConfig mConfig;

    public static <T> T create(String url,Class<T> service){
        return new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(service);
    }

    public static <T> T create(Class<T> service){
        return create(mConfig.getRequestPath(),service);
    }

    public static void init(IServerConfig serverConfig){
        mConfig = serverConfig;
    }

}
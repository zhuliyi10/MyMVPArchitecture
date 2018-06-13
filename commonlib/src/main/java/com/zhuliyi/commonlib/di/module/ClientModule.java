package com.zhuliyi.commonlib.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhuliyi.commonlib.http.GlobalHttpHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Describe : 提供一些三方库客户端实例的 {@link dagger.Module}
 * Author : zhuly
 * Date : 2018-06-12
 */
@Module
public class ClientModule {
    private static final int TIME_OUT = 10;//s
    @Singleton
    @Provides
    static Retrofit provideRetrofit(Application application, @Nullable RetrofitConfiguration configuration, Retrofit.Builder builder, OkHttpClient client, HttpUrl httpUrl, Gson gson) {
        builder.baseUrl(httpUrl)
                .client(client);
        if(configuration!=null){
            configuration.configRetrofit(application,builder);
        }
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
        return builder.build();
    }

    @Singleton
    @Provides
    static OkHttpClient provideClient(Application application, OkHttpClient.Builder builder,  @Nullable List<Interceptor> interceptors, @Nullable final OkHttpConfiguration configuration, @Nullable final GlobalHttpHandler handler){
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT,TimeUnit.SECONDS);
        if(handler!=null) {
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    return chain.proceed(handler.onHttpRequestBefore(chain, chain.request()));
                }
            });
        }
        if(interceptors!=null){
            for (Interceptor interceptor1:interceptors){
                builder.addInterceptor(interceptor1);
            }
        }
        if(configuration!=null){
            configuration.configOkHttp(application,builder);
        }
        return builder.build();
    }
    @Singleton
    @Provides
    static Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    static OkHttpClient.Builder provideClientBuilder() {
        return new OkHttpClient.Builder();
    }


    @Singleton
    @Provides
    static Gson provideGson(Context context,@Nullable GsonConfiguration configuration){
        GsonBuilder builder=new GsonBuilder();
        if (configuration != null) {
            configuration.configGson(context,builder);
        }
        return builder.create();
    }

    public interface GsonConfiguration{
        void configGson(Context context,GsonBuilder builder);
    }
    public interface RetrofitConfiguration {
        void configRetrofit(Context context, Retrofit.Builder builder);
    }
    public interface OkHttpConfiguration {
        void configOkHttp(Context context, OkHttpClient.Builder builder);
    }
}

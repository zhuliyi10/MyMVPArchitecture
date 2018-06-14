package com.zhuliyi.commonlib.di.module;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.zhuliyi.commonlib.cache.Cache;
import com.zhuliyi.commonlib.cache.CacheType;
import com.zhuliyi.commonlib.cache.IntelligentCache;
import com.zhuliyi.commonlib.cache.LruCache;
import com.zhuliyi.commonlib.http.GlobalHttpHandler;
import com.zhuliyi.commonlib.image.BaseImageLoaderStrategy;
import com.zhuliyi.commonlib.image.glide.GlideImageLoaderStrategy;
import com.zhuliyi.commonlib.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;

/**
 * Describe : 框架独创的建造者模式 {@link Module},用户可向框架中注入外部配置的自定义参数
 * Author : zhuly
 * Date : 2018-06-12
 */
@Module
public class GlobalConfigModule {
    private HttpUrl httpUrl;
    private GlobalHttpHandler handler;
    private List<Interceptor> interceptors;
    private BaseImageLoaderStrategy loaderStrategy;
    private File cacheFile;
    private ClientModule.RetrofitConfiguration retrofitConfiguration;
    private ClientModule.OkHttpConfiguration okHttpConfiguration;
    private ClientModule.GsonConfiguration gsonConfiguration;
    private Cache.Factory cacheFactory;
    private GlobalConfigModule(Builder builder) {
        this.httpUrl = builder.httpUrl;
        this.handler = builder.handler;
        this.interceptors = builder.interceptors;
        this.loaderStrategy=builder.loaderStrategy;
        this.cacheFile=builder.cacheFile;
        this.retrofitConfiguration = builder.retrofitConfiguration;
        this.okHttpConfiguration = builder.okHttpConfiguration;
        this.gsonConfiguration = builder.gsonConfiguration;
        this.cacheFactory=builder.cacheFactory;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Singleton
    @Provides
    HttpUrl provideBaseUrl() {
        return httpUrl == null ? HttpUrl.parse("https://api.github.com/") : httpUrl;
    }

    @Singleton
    @Provides
    @Nullable
    GlobalHttpHandler provideGlobalHttpHandler() {
        return handler;
    }

    @Singleton
    @Provides
    @Nullable
    List<Interceptor> provideInterceptors() {
        return interceptors;
    }

    @Singleton
    @Provides
    BaseImageLoaderStrategy provideImageLoaderStrategy(){
        return loaderStrategy==null? new GlideImageLoaderStrategy():loaderStrategy;
    }

    /**
     * 提供缓存文件
     */
    @Singleton
    @Provides
    File provideCacheFile(Application application) {
        return cacheFile == null ? FileUtils.getCacheFile(application) : cacheFile;
    }

    @Singleton
    @Provides
    @Nullable
    ClientModule.RetrofitConfiguration provideRetrofitConfiguration() {
        return retrofitConfiguration;
    }

    @Singleton
    @Provides
    @Nullable
    ClientModule.OkHttpConfiguration provideOkHttpConfiguration() {
        return okHttpConfiguration;
    }

    @Singleton
    @Provides
    @Nullable
    ClientModule.GsonConfiguration provideGsonConfiguration() {
        return gsonConfiguration;
    }

    @Singleton
    @Provides
    Cache.Factory provideCacheFactory(final Application application){
        return  cacheFactory==null?new Cache.Factory() {
            @NonNull
            @Override
            public Cache build(CacheType type) {
                //若想自定义 LruCache 的 size, 或者不想使用 LruCache, 想使用自己自定义的策略
                //使用 GlobalConfigModule.Builder#cacheFactory() 即可扩展
                switch (type.getCacheTypeId()){
                    //Activity、Fragment 以及 Extras 使用 IntelligentCache (具有 LruCache 和 可永久存储数据的 Map)
                    case CacheType.EXTRAS_TYPE_ID:
                    case CacheType.ACTIVITY_CACHE_TYPE_ID:
                    case CacheType.FRAGMENT_CACHE_TYPE_ID:
                        return new IntelligentCache(type.calculateCacheSize(application));
                    //其余使用 LruCache (当达到最大容量时可根据 LRU 算法抛弃不合规数据)
                    default:
                        return new LruCache(type.calculateCacheSize(application));
                }
            }
        }:cacheFactory;
    }
    public static final class Builder {
        private HttpUrl httpUrl;
        private GlobalHttpHandler handler;
        private List<Interceptor> interceptors;
        private BaseImageLoaderStrategy loaderStrategy;
        private File cacheFile;
        private ClientModule.RetrofitConfiguration retrofitConfiguration;
        private ClientModule.OkHttpConfiguration okHttpConfiguration;
        private ClientModule.GsonConfiguration gsonConfiguration;
        private Cache.Factory cacheFactory;
        public Builder baseUrl(String baseUrl) {
            if (TextUtils.isEmpty(baseUrl)) {
                throw new NullPointerException("BaseUrl can not be empty");
            }
            this.httpUrl = HttpUrl.parse(baseUrl);
            return this;
        }

        public Builder globalHttpHandler(GlobalHttpHandler handler) {
            this.handler = handler;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            if (interceptors == null) {
                interceptors = new ArrayList<>();
            }
            interceptors.add(interceptor);
            return this;
        }

        public Builder imageLoaderStrategy(BaseImageLoaderStrategy loaderStrategy){
            this.loaderStrategy=loaderStrategy;
            return this;
        }

        public Builder cacheFile(File cacheFile){
            this.cacheFile=cacheFile;
            return this;
        }
        public Builder retrofitConfiguration(ClientModule.RetrofitConfiguration retrofitConfiguration) {
            this.retrofitConfiguration = retrofitConfiguration;
            return this;
        }

        public Builder okHttpConfiguration(ClientModule.OkHttpConfiguration okHttpConfiguration) {
            this.okHttpConfiguration = okHttpConfiguration;
            return this;
        }

        public Builder gsonConfiguration(ClientModule.GsonConfiguration gsonConfiguration) {
            this.gsonConfiguration = gsonConfiguration;
            return this;
        }
        public Builder cacheFactory(Cache.Factory cacheFactory) {
            this.cacheFactory = cacheFactory;
            return this;
        }

        public GlobalConfigModule build() {
            return new GlobalConfigModule(this);
        }
    }
}

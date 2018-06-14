package com.zhuliyi.commonlib.http;

import android.app.Application;
import android.content.Context;

import com.zhuliyi.commonlib.cache.Cache;
import com.zhuliyi.commonlib.cache.CacheType;
import com.zhuliyi.commonlib.mvp.IModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;
import io.rx_cache2.internal.RxCache;
import retrofit2.Retrofit;

/**
 * Describe :  *用来管理网络请求层,以及数据缓存层,以后可能添加数据库请求层
 * 提供给 {@link IModel} 层必要的 Api 做数据处理
 * Author : zhuly
 * Date : 2018-06-13
 */

@Singleton
public class RepositoryManager implements IRepositoryManager{
    @Inject
    Lazy<Retrofit>retrofit;
    @Inject
    Lazy<RxCache> rxCache;
    @Inject
    Application application;
    @Inject
    Cache.Factory cachefactory;
    private Cache<String, Object> retrofitServiceCache;
    private Cache<String, Object> cacheServiceCache;

    @Inject
    public RepositoryManager() {
    }

    /**
     * 根据传入的 Class 获取对应的 Retrofit service
     *
     * @param service
     * @param <T>
     * @return
     */
    @Override
    public <T> T obtainRetrofitService(Class<T> service) {
        if (retrofitServiceCache == null)
            retrofitServiceCache = cachefactory.build(CacheType.RETROFIT_SERVICE_CACHE);
        T retrofitService = (T) retrofitServiceCache.get(service.getCanonicalName());
        if (retrofitService == null) {
            retrofitService = retrofit.get().create(service);
            retrofitServiceCache.put(service.getCanonicalName(), retrofitService);
        }
        return retrofitService;
    }

    /**
     * 根据传入的 Class 获取对应的 RxCache service
     *
     * @param cache
     * @param <T>
     * @return
     */
    @Override
    public synchronized <T> T obtainCacheService(Class<T> cache) {
        if (cacheServiceCache == null)
            cacheServiceCache = cachefactory.build(CacheType.CACHE_SERVICE_CACHE);
        T cacheService = (T) cacheServiceCache.get(cache.getCanonicalName());
        if (cacheService == null) {
            cacheService = rxCache.get().using(cache);
            cacheServiceCache.put(cache.getCanonicalName(), cacheService);
        }
        return cacheService;
    }

    @Override
    public void clearAllCache() {
        rxCache.get().evictAll();
    }

    @Override
    public Context getContext() {
        return application;
    }
}

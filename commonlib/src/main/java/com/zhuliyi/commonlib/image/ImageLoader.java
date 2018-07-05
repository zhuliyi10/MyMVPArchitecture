package com.zhuliyi.commonlib.image;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Describe : {@link ImageLoader} 使用策略模式和建造者模式,可以动态切换图片请求框架(比如说切换成 Picasso )
 * 当需要切换图片请求框架或图片请求框架升级后变更了 Api 时
 * 这里可以将影响范围降到最低,所以封装 {@link ImageLoader} 是为了屏蔽这个风险
 * Author : zhuly
 * Date : 2018-06-12
 */

@Singleton
public final class ImageLoader {
    @Inject
    BaseImageLoaderStrategy strategy;
    @Inject
    public ImageLoader(){
    }

    /**
     * 加载图片
     * @param context
     * @param config
     * @param <T>
     */
    public <T extends BaseImageConfig>void loadImage(Context context, T config){
        this.strategy.loadImage(context,config);
    }

    /**
     * 停止加载或清理缓存
     * @param context
     * @param config
     * @param <T>
     */
    public <T extends BaseImageConfig>void clear(Context context, T config){
        this.strategy.clear(context,config);
    }

    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy){
        this.strategy=strategy;
    }

    public BaseImageLoaderStrategy getLoadImgStrategy(){
        return strategy;
    }
}

package com.zhuliyi.commonlib.mvp;

import com.zhuliyi.commonlib.http.IRepositoryManager;

/**
 * Describe : 基类 Model
 * Author : zhuly
 * Date : 2018-06-13
 */

public class BaseModel implements IModel{

    protected final String TAG=this.getClass().getSimpleName();
    protected IRepositoryManager repositoryManager;//用于管理网络请求层, 以及数据缓存层
    public BaseModel(IRepositoryManager repositoryManager){
        this.repositoryManager=repositoryManager;
    }
    @Override
    public void onDestroy() {
        repositoryManager=null;
    }
}

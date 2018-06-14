package com.zhuliyi.business2;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhuliyi.commonlib.app.RouterHub;
import com.zhuliyi.commonlib.base.BaseActivity;

/**
 * Describe : 组件2首页
 * Author : zhuly
 * Date : 2018-06-14
 */

@Route(path = RouterHub.BUSINESS2_BUSINESS2ACTIVITY)
public class Business2Activity extends BaseActivity{
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}

package com.zhuliyi.business2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhuliyi.commonlib.base.BaseActivity;
import com.zhuliyi.interactions.EventBusBHub;
import com.zhuliyi.interactions.RouterHub;

import org.simple.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Describe : 组件2首页
 * Author : zhuly
 * Date : 2018-06-14
 */

@Route(path = RouterHub.BUSINESS2_BUSINESS2ACTIVITY)
public class Business2Activity extends BaseActivity {
    @BindView(R2.id.txt_receive)
    TextView txtReceive;

    @Autowired(name = "key")
    String key;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        txtReceive.setText(key);
    }


    @OnClick(R2.id.sent)
    public void onViewClicked() {
        EventBus.getDefault().post("我是组件2", EventBusBHub.TAG_BUSINESS2_UPDATE);
    }
}

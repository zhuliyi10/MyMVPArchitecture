package com.zhuliyi.business1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhuliyi.commonlib.base.BaseActivity;
import com.zhuliyi.commonlib.image.ImageConfig;
import com.zhuliyi.commonlib.utils.AppUtils;
import com.zhuliyi.commonlib.utils.LogUtils;
import com.zhuliyi.commonlib.utils.ToastUtils;
import com.zhuliyi.interactions.EventBusBHub;
import com.zhuliyi.interactions.RouterHub;

import org.simple.eventbus.Subscriber;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Describe : 业务1的Activity
 * Author : zhuly
 * Date : 2018-06-14
 */

@Route(path = RouterHub.BUSINESS1_BUSINESS1ACTIVITY)
public class Business1Activity extends BaseActivity {


    @BindView(R2.id.btn1)
    Button btn1;
    @BindView(R2.id.btn2)
    Button btn2;
    @BindView(R2.id.img)
    ImageView img;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_business1;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ImageConfig config = new ImageConfig.Builder()
                .url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528864685635&di=5ea41bdc8923e7b587e7bc1a623bde05&imgtype=0&src=http%3A%2F%2Fwww.taopic.com%2Fuploads%2Fallimg%2F140320%2F235013-14032020515270.jpg")
                .imageView(img)
                .build();
        AppUtils.obtainImageLoader().loadImage(this, config);
    }


    @OnClick({R2.id.btn1, R2.id.btn2})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.btn1) {
            ToastUtils.showShort("点击了按键1");
        } else if (i == R.id.btn2) {
            ARouter.getInstance()
                    .build(RouterHub.BUSINESS2_BUSINESS2ACTIVITY)
                    .withString("key", "这是从组件1过来的数据")
                    .navigation(this);
            ToastUtils.showShort("跳到组件2");
        }
    }

    @Subscriber(tag = EventBusBHub.TAG_BUSINESS2_UPDATE)
    private void getDataFromBusiness2(String data) {
        LogUtils.d(TAG, "getDataFromBusiness2: ");
        ToastUtils.showShort("组件1收到消息：" + data);
    }

}

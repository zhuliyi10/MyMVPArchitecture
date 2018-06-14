package com.zhuliyi.mymvparchitecture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhulilyi.mymvparchitecture.R;
import com.zhuliyi.commonlib.app.RouterHub;
import com.zhuliyi.commonlib.base.BaseActivity;
import com.zhuliyi.commonlib.utils.AppUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = RouterHub.APP_MAINACTIVITY)
public class MainActivity extends BaseActivity {


    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }


    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                AppUtils.navigation(this,RouterHub.BUSINESS1_BUSINESS1ACTIVITY);
                Toast.makeText(this,"跳到组件1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                AppUtils.navigation(this,RouterHub.BUSINESS2_BUSINESS2ACTIVITY);
                Toast.makeText(this,"跳到组件2",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

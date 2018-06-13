package com.zhuliyi.mymvparchitecture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.zhulilyi.mymvparchitecture.R;
import com.zhuliyi.commonlib.base.BaseActivity;
import com.zhuliyi.commonlib.image.ImageLoader;
import com.zhuliyi.commonlib.image.glide.GlideConfig;
import com.zhuliyi.commonlib.mvp.IPresenter;
import com.zhuliyi.commonlib.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.img)
    ImageView img;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        GlideConfig config=new GlideConfig.Builder()
                .url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528864685635&di=5ea41bdc8923e7b587e7bc1a623bde05&imgtype=0&src=http%3A%2F%2Fwww.taopic.com%2Fuploads%2Fallimg%2F140320%2F235013-14032020515270.jpg")
                .imageView(img)
                .build();
       AppUtils.obtainImageLoader(this).loadImage(this,config);
    }
}

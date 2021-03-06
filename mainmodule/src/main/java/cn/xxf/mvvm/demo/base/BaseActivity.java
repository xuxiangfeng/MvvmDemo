package cn.xxf.mvvm.demo.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

/**
 * Created by xuxiangfeng on 2018/1/17.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends Activity {

    private final String TAG = "BaseActivity";

    protected T mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        initModel(mBinding);
        initView(getIntent());
    }

    protected void initView(Intent intent){};
    public void initModel(T t){}
    public abstract int getLayoutId();

}

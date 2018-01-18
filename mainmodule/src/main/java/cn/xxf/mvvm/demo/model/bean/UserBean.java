package cn.xxf.mvvm.demo.model.bean;

import android.databinding.BaseObservable;

import cn.xxf.mvvm.demo.base.BaseBean;

/**
 * Created by xuxiangfeng on 2018/1/17.
 */

public class UserBean extends BaseBean{

    private String helloWorld;

    public String getHelloWorld() {
        return helloWorld;
    }

    public void setHelloWorld(String helloWorld) {
        this.helloWorld = helloWorld;
    }
}

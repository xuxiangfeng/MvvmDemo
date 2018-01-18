package cn.xxf.mvvm.demo.model;

import android.databinding.Bindable;

import cn.xxf.mvvm.demo.BR;
import cn.xxf.mvvm.demo.base.BaseModel;
import cn.xxf.mvvm.demo.model.bean.UserBean;

/**
 * Created by xuxiangfeng on 2018/1/17.
 */

public class UserModel extends BaseModel<UserBean> {

    private String helloWorld;

    public UserModel() {
        super();
    }

    public UserModel(UserBean userBean) {
        super(userBean);
        helloWorld = userBean.getHelloWorld();
        notifyChange();
    }

    @Bindable
    public String getHelloWorld() {
        return helloWorld;
    }

    public void setHelloWorld(String helloWorld) {
        this.helloWorld = helloWorld;
        notifyPropertyChanged(BR.helloWorld);
    }
}

package cn.xxf.mvvm.demo.viewmodel;

import android.databinding.Bindable;

import cn.xxf.mvvm.demo.BR;
import cn.xxf.mvvm.demo.base.BaseMV;
import cn.xxf.mvvm.demo.model.UserModel;

/**
 * Created by xuxiangfeng on 2018/1/17.
 */

public class UserVM extends BaseMV<UserModel> {

    private String helloWorld;

    public UserVM() {
        super();
    }

    public UserVM(UserModel userBean) {
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

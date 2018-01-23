package cn.xxf.mvvm.demo.viewmodel.presenter;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import cn.xxf.mvvm.demo.base.BasePres;
import cn.xxf.mvvm.demo.message.MainCountAddEvent;
import cn.xxf.mvvm.demo.viewmodel.UserVM;

/**
 * Created by xuxiangfeng on 2018/1/17.
 */

public class MainPres extends BasePres {

    private int count = 0;

    public void addCount(UserVM user){
        count++;
        user.setHelloWorld("this clicked "+count);
        EventBus.getDefault().post(new MainCountAddEvent());
    }


}

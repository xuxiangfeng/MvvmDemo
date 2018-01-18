package cn.xxf.mvvm.demo.viewmodel;

import android.databinding.BaseObservable;
import android.util.Log;
import android.view.View;

import java.util.List;

import cn.xxf.mvvm.demo.base.BaseVM;
import cn.xxf.mvvm.demo.model.UserModel;

/**
 * Created by xuxiangfeng on 2018/1/17.
 */

public class MainVM extends BaseVM {

    private int count = 0;

    private CallBack mCallBack;

    public MainVM(CallBack callBack){
        mCallBack = callBack;
    }

    public void addCount(UserModel user){
        count++;
        user.setHelloWorld("this clicked "+count);
        Log.d("xxfxxf","onClick - "+count);
        if(mCallBack != null){
            mCallBack.onAddCountComplete();
        }
    }

    public interface CallBack{
        void onAddCountComplete();
    }

}

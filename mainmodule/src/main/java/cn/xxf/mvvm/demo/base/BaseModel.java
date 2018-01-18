package cn.xxf.mvvm.demo.base;

import android.databinding.BaseObservable;

/**
 * model,是bean的封装，业务逻辑中使用，用来刷新数据
 * Created by xuxiangfeng on 2018/1/17.
 */

public class  BaseModel<T extends BaseBean> extends BaseObservable {

    public BaseModel(){
    }

    public BaseModel(T t){
    }

}

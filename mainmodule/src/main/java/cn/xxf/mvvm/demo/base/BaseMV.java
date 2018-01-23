package cn.xxf.mvvm.demo.base;

import android.databinding.BaseObservable;

/**
 * model,是bean的封装，业务逻辑中使用，用来刷新数据
 * Created by xuxiangfeng on 2018/1/17.
 */

public class BaseMV<T extends BaseModel> extends BaseObservable {

    public BaseMV(){
    }

    public BaseMV(T t){
    }

}

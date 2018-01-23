package cn.xxf.mvvm.demo.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.xxf.mvvm.demo.R;
import cn.xxf.mvvm.demo.base.BaseActivity;
import cn.xxf.mvvm.demo.databinding.ActivityMainBinding;
import cn.xxf.mvvm.demo.message.MainCountAddEvent;
import cn.xxf.mvvm.demo.viewmodel.UserVM;
import cn.xxf.mvvm.demo.viewmodel.presenter.MainPres;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    
    private final String TAG = "MainActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initModel(ActivityMainBinding binding) {
        MainPres mainPres = new MainPres();
        binding.setMainPres(mainPres);
        UserVM model = new UserVM();
        binding.setUser(model);
        mainPres.addCount(model);
    }

    @Override
    protected void initView(Intent intent) {
        super.initView(intent);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onAddClick(View view){
        Log.d(TAG, "onAddClick: ");
        mBinding.getMainPres().addCount(mBinding.getUser());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventAddCount(MainCountAddEvent event){
        Toast.makeText(getApplicationContext(),R.string.add_success,Toast.LENGTH_SHORT).show();
    }

}

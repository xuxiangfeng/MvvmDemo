package cn.xxf.mvvm.demo.view;

import android.nfc.Tag;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cn.xxf.mvvm.demo.R;
import cn.xxf.mvvm.demo.base.BaseActivity;
import cn.xxf.mvvm.demo.databinding.ActivityMainBinding;
import cn.xxf.mvvm.demo.model.UserModel;
import cn.xxf.mvvm.demo.viewmodel.MainVM;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainVM.CallBack {
    
    private final String TAG = "MainActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initModel(ActivityMainBinding binding) {
        MainVM mainVm = new MainVM(this);
        binding.setVm(mainVm);
        UserModel model = new UserModel();
        mainVm.addCount(model);
        binding.setUser(model);
    }

    @Override
    public void onAddCountComplete() {
        runOnUIThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),R.string.add_success,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onAddClick(View view){
        Log.d(TAG, "onAddClick: ");
        mBinding.getVm().addCount(mBinding.getUser());
    }


}

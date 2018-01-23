package cn.xxf.mvvm.demo.config;

import cn.xxf.mvvm.httplib.IServerConfig;

/**
 * Created by xuxiangfeng on 2018/1/20.
 */

public class ServerConfig implements IServerConfig {

    private String serverPath = null;

    public final CONFIG_MODE serverConfig = CONFIG_MODE.DEBUG;

    public void init(){
        if(serverConfig == CONFIG_MODE.RELEASE){

        }else{

        }
    }

    @Override
    public String getRequestPath() {
        return serverPath;
    }

    enum CONFIG_MODE{
        DEBUG,
        RELEASE
    }

}

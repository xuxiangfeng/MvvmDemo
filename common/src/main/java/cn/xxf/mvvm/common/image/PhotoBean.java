package cn.xxf.mvvm.common.image;

/**
 * Created by xuxiangfeng on 2018/1/20.
 */

public class PhotoBean {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isNet(){
        return path.toLowerCase().startsWith("http://") || path.toLowerCase().startsWith("https://");
    }
}

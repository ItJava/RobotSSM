package com.ssm.promotion.core.util;

import com.qiniu.util.Auth;

import java.util.ResourceBundle;

public class QiNiuYunTools {


    ResourceBundle resource = ResourceBundle.getBundle("config");
    String thumbnailPictureFolder = resource.getString("QiNiuHeaderAC");
    String accessKey = resource.getString("AccessKey");
    String secretKey = resource.getString("SecretKey");
    String bucketName = resource.getString("BucketName");

    Auth auth;

    private QiNiuYunTools(){
        auth= Auth.create(accessKey, secretKey);
    }
    /**
     *    类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     *    没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
     */
    private static class QiNiuYunToolsHolder{
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static QiNiuYunTools instance = new QiNiuYunTools();
    }

    public static QiNiuYunTools getInstance(){
        return QiNiuYunToolsHolder.instance;
    }


    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}

package com.zyh.playerpicture.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.zyh.playerpicture.App.BaseApp;


/**
 * <信息缓存>
 */
public class SPTools {
    private static final String SPF_NAME = "ruijie_iib";//app的SharedPreferences文件名称
    private static final String CACHE_SEPARATOR = "###";//缓存对象时间分隔符
    private static final long CACHE_TIME = 7 * 24 * 3600 * 1000;//对象缓存的时间长度



    //打开默认的SharedPreferences
    protected static SharedPreferences getDefault() {
        return getSPF(SPF_NAME);
    }

    //根据文件名打开SharedPreferences
    protected static SharedPreferences getSPF(String spf_name) {
        return BaseApp.application.getSharedPreferences(spf_name, Context.MODE_PRIVATE);
    }






    /**
     * 缓存数据对象，在末尾拼接时间戳
     *
     * @param key
     * @param object
     */

}

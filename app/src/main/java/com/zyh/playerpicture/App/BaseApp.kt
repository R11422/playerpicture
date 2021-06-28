package com.zyh.playerpicture.App

import android.app.Application

/**
 * @Description
 * @Author created by 张永宏
 * date :2021/6/25 3:04 PM
 */
class BaseApp:Application() {
    companion object{
        lateinit var application:Application
    }

    override fun onCreate() {
        super.onCreate()
        application=this
    }
}
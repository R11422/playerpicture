package com.zyh.playerpicture

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.zyh.playerpicture.cache.SPToolsImpl


/**
 * @Description
 * @Author created by 张永宏
 * date :2021/6/25 10:51 AM
 */
class BootCompleteReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent!!.action ?: return

        if (action == Intent.ACTION_BOOT_COMPLETED) {
            //开机
            if (context != null&&!SPToolsImpl.getCheckUp()) {
                MainActivity.launch(context, true)
            }
        } else if (action == Intent.ACTION_SHUTDOWN) {
            //关机登出

        }
    }
}
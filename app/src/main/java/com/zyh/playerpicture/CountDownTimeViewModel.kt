package com.zyh.playerpicture

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zyh.playerpicture.cache.SPToolsImpl


/**
 * @Description
 * @Author created by 张永宏
 * date :2021/6/25 11:05 AM
 */
class CountDownTimeViewModel :ViewModel() {
    var countdowntimer=MutableLiveData<Long>()
    var mtime:Long=0
    lateinit var mFinishCountdown:FinishCountdown
    fun setStartTime(){
        val countDownTime1= SPToolsImpl.getCountDownTime()
        Log.i(TAG, "值: "+countDownTime1)
        val countDownTime=object : CountDownTimer(countDownTime1,1000){
            override fun onTick(millisUntilFinished: Long) {
                countdowntimer.value=millisUntilFinished/1000
            }

            override fun onFinish() {
                SPToolsImpl.setCheckUp(true)
                mFinishCountdown.finish()
            }

        }
        countDownTime.start()
    }

    fun setFinishCountDown(finishCountdown: ColorCheckFragment) {
        mFinishCountdown=finishCountdown
    }

    companion object{
        val TAG="CountDownTimeViewModel"
    }
    interface FinishCountdown{
        fun finish()
    }

}
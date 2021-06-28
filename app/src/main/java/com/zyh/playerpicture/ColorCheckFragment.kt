package com.zyh.playerpicture

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2

import com.zyh.playerpicture.cache.SPToolsImpl
import com.zyh.playerpicture.databinding.FragmentColorCheckBinding
import java.util.ArrayList


/**
 *author: luqihua
 *date:2021/4/26
 *description:
 **/
class ColorCheckFragment : BaseNavFragment(),CountDownTimeViewModel.FinishCountdown {

    companion object {
        var preferences=null
        private const val TAG = "ColorCheckFragment"

    }
    protected var mDrawableList: MutableList<Int> = ArrayList()
    private var itemPosition = 0
    private var isFinish = false
    private val TIME = 1700
    private lateinit var binding: FragmentColorCheckBinding
    private val mHandler = Handler(Looper.getMainLooper())
    private val mHandler1 = Handler(Looper.getMainLooper())
    lateinit var viewModel: CountDownTimeViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel=ViewModelProvider(this).get(CountDownTimeViewModel::class.java)

        binding = FragmentColorCheckBinding.inflate(inflater, container, false)
        return binding.root
    }
    private fun initData() {
        for (i in 0..5) {
            val drawable = resources.getIdentifier("guide$i", "drawable", context?.packageName)
            mDrawableList.add(drawable)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHandler.removeCallbacks(hideIndicatorTask)
        mHandler1.removeCallbacks(runnableForViewPager)
        setIndicatorVisible(true)
        initData()
        mHandler.postDelayed(hideIndicatorTask, 2000)
        mHandler1.postDelayed(runnableForViewPager, 1700)
        binding.apply {
            colorPager.apply {
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                adapter = ColorCheckAdapter(requireContext(), mDrawableList).also {
                    it.setOnPagerClickListener {
                        mHandler.removeCallbacks(hideIndicatorTask)
                        setIndicatorVisible(true)
                        mHandler.postDelayed(hideIndicatorTask, 2000)
                    }
                }

                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {

                        Log.i(TAG, "onPageSelected: "+position)
                        super.onPageSelected(position)
                        itemPosition=position
                        start()
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                        when (state) {
                            ViewPager2.SCROLL_STATE_SETTLING,
                            ViewPager2.SCROLL_STATE_DRAGGING -> {
                                mHandler.removeCallbacks(hideIndicatorTask)
                                setIndicatorVisible(true)
                                llNav.isVisible = false
                            }

                            ViewPager2.SCROLL_STATE_IDLE -> {
                                mHandler.postDelayed(hideIndicatorTask, 2000)
                            }
                        }
                    }
                })
            }
            //colorIndicator.init(mColorData, 0)
            //colorIndicator.attach2ViewPager(colorPager)

            checkFinish.setOnClickListener {
                activity?.finish()
            }
            viewModel.setFinishCountDown(this@ColorCheckFragment)
            if (SPToolsImpl.getCountDownTime()>0){
                viewModel.setStartTime()
            }else{
                producetest.text="测试已完成"
                countdowntime.text=""
            }

            viewModel.countdowntimer.observe(getViewLifecycleOwner(), Observer {
                Log.i(TAG, "onViewCreated: "+it.toString())
                val min=it/60
                val sec=it%60
                countdowntime.text = min.toString()+""+"分"+sec+"秒"
                SPToolsImpl.setCountDownTime(it*1000)

            })

        }

    }


    private val hideIndicatorTask = Runnable {
        setIndicatorVisible(false)
    }

    override fun onPause() {
        super.onPause()

    }


    private fun setIndicatorVisible(isVisible: Boolean) {
        binding.apply {
           // colorIndicator.isVisible = isVisible
        }
    }

    /**
     * ViewPager的定时器
     */
    var runnableForViewPager: Runnable = object : Runnable {
        override fun run() {
            try {
                itemPosition++
                binding.colorPager.setCurrentItem(itemPosition)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun start() {
        mHandler1.removeCallbacksAndMessages(null)
        mHandler1.postDelayed(runnableForViewPager, 1700)
    }

    override fun finish() {
        activity?.finish()
    }
}
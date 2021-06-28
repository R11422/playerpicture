package com.zyh.playerpicture

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2


/**
 *author: luqihua
 *date:2021/4/27
 *description: 画质检测指示器
 **/
class ColorIndicator(context: Context, attributeSet: AttributeSet? = null) :
    LinearLayout(context, attributeSet) {

    companion object {
        private const val TAG = "ColorIndicator"
        const val TEXT_SIZE = 9f;
        private const val NORMAL_BG_SIZE = 20
        const val SELECT_BG_WIDTH = 104;
        const val SELECT_BG_HEIGHT = 78

        private fun createNormalDrawable(color: Int) =
            GradientDrawable().also {
                it.setSize(NORMAL_BG_SIZE, NORMAL_BG_SIZE)
                it.setColor(color)
                it.shape = GradientDrawable.OVAL
            }

        private fun createSelectDrawable(color: Int) =
            GradientDrawable().also {
                it.setSize(SELECT_BG_WIDTH, SELECT_BG_HEIGHT)
                it.setColor(color)
                it.shape = GradientDrawable.RECTANGLE
                it.cornerRadius = SELECT_BG_HEIGHT / 2f
            }
    }


    private val mColorList: MutableList<Pair<Int, Int>> = ArrayList()
    private var mSelectIndex = -1


    init {
        background = GradientDrawable().also {
            it.setColor(Color.parseColor("#DEE3E9"))
            it.shape = GradientDrawable.RECTANGLE
            it.cornerRadius = SELECT_BG_HEIGHT / 2f
        }
        setPadding(1, 1, 1, 1)
    }

    fun init(colorList: List<Pair<Int, Int>>?, selectIndex: Int = 0) {
        colorList?.let {
            mColorList.clear()
            mColorList.addAll(colorList)
        }
        removeAllViews()
        val count = mColorList.size
        for (i in 0 until count) {
            addView(createIndicator(context.getColor(mColorList[i].second)))
        }
        updateSelectState(selectIndex)
    }


    fun attach2ViewPager(viewPager2: ViewPager2) {
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                changeSelect(position % mColorList.size)
            }
        })
    }


    fun changeSelect(index: Int) {
        updateSelectState(index)
    }

    private fun createIndicator(color: Int): View {
        val textView = TextView(context).also {
            it.gravity = Gravity.CENTER
            it.textSize = TEXT_SIZE
            it.setTextColor(Color.WHITE)
            it.includeFontPadding = false
            it.layoutParams =
                FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                    .apply {
                        gravity = Gravity.CENTER
                    }
            it.background = createNormalDrawable(color)
        }

        return FrameLayout(context).also {
            it.layoutParams = FrameLayout.LayoutParams(
                SELECT_BG_WIDTH, SELECT_BG_HEIGHT
            )
            it.addView(textView)
        }
    }


    private fun updateSelectState(newIndex: Int) {
        if (newIndex == mSelectIndex || newIndex < 0 || newIndex >= childCount) return

        //将上一个选中的改为未选中
        if (mSelectIndex != -1) {
            val preSelectView = (getChildAt(mSelectIndex) as FrameLayout).getChildAt(0) as TextView
            preSelectView.text = ""
            preSelectView.background =
                createNormalDrawable(context.getColor(mColorList[mSelectIndex].second))
        }

        mSelectIndex = newIndex

        val selectView = (getChildAt(mSelectIndex) as FrameLayout).getChildAt(0) as TextView
        selectView.text = context.getString(mColorList[mSelectIndex].first)

        val color = context.getColor(mColorList[mSelectIndex].second)
        //纯白的背景  文字颜色改为黑色
        if (color == Color.WHITE) {
            selectView.setTextColor(context.getColor(R.color.tv_normal_black))
        } else {
            selectView.setTextColor(Color.WHITE)
        }
        selectView.background =
            createSelectDrawable(context.getColor(mColorList[mSelectIndex].second))
    }
}
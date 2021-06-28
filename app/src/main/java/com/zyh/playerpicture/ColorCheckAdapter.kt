package com.zyh.playerpicture

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 *author: luqihua
 *date:2021/4/26
 *description:
 **/
class ColorCheckAdapter(
    private val mContext: Context,
    private val mData: List<Pair<Int, Int>> = emptyList()
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var pageClickListener: ((Int) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = View(parent.context)
        val params = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        itemView.setOnClickListener {
            pageClickListener?.invoke(it.tag as Int)
        }
        Log.i(TAG, "onCreateViewHolder: ")
        itemView.layoutParams = params
        return object : RecyclerView.ViewHolder(itemView) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemData = mData[position % mData.size]
        Log.i(TAG, "onBindViewHolder: ")
        holder.itemView.apply {
            tag = position
            setBackgroundColor(mContext.getColor(itemData.second))
        }
    }

    override fun getItemCount(): Int {
        return mData.size * 10000
    }

    fun setOnPagerClickListener(listener: (Int) -> Unit) {
        this.pageClickListener = listener
    }
    companion object{
        val TAG="ColorCheckAdapter"
    }

}
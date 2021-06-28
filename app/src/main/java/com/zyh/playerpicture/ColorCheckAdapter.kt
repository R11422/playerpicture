package com.zyh.playerpicture

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

/**
 *author: luqihua
 *date:2021/4/26
 *description:
 **/
class ColorCheckAdapter(
    private val mContext: Context,
    private val mData: MutableList<Int>
) :
    RecyclerView.Adapter<ColorCheckAdapter.SimpleViewHolder>() {

    private var pageClickListener: ((Int) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
//        val itemView = View(parent.context)
//        val params = RecyclerView.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT
//        )
//        itemView.setOnClickListener {
//            pageClickListener?.invoke(it.tag as Int)
//        }
//        Log.i(TAG, "onCreateViewHolder: ")
//        itemView.layoutParams = params
//        return object : RecyclerView.ViewHolder(itemView) {}
        return SimpleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_custom_view,parent,false))
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        val itemData = mData[position % mData.size]
        Log.i(TAG, "onBindViewHolder: ")
        holder.imageview.setImageResource(itemData)
//        holder.itemView.apply {
//            tag = position
//            //setBackgroundColor(mContext.getColor(itemData))
//            val imageStart: ImageView = holder.findViewById(R.id.iv_logo)
//            holder.setImageResource(R.id.banner_image, data!!.imageRes)
//        }
    }
    inner class SimpleViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val imageview=itemView.findViewById<ImageView>(R.id.banner_image)
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
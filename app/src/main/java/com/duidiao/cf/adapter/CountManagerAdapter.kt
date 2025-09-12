package com.duidiao.cf.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duidiao.cf.R
import com.duidiao.cf.adapter.CountManagerAdapter.CountViewHolder
import com.duidiao.cf.model.CountManagerItem

class CountManagerAdapter(
    private var mCountDataList: MutableList<CountManagerItem>?
) : RecyclerView.Adapter<CountViewHolder>() {
    // 点击事件监听器
    private var mOnItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountViewHolder {
        // 加载item布局
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_count_manager, parent, false)
        return CountViewHolder(view)
    }


    override fun onBindViewHolder(holder: CountViewHolder, position: Int) {
        // 获取当前位置的数据
        val countData = mCountDataList!![position]
        // 设置数据到视图
        holder.teamName.text = countData.teamName

        // 设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener { v: View? ->
                mOnItemClickListener!!.onItemClick(
                    position,
                    countData
                )
            }
        }
    }

    /**
     * 获取数据项数量
     */
    override fun getItemCount(): Int {
        return if (mCountDataList == null) 0 else mCountDataList!!.size
    }

    /**
     * 更新数据
     * @param newData 新的数据列表
     */
    fun updateData(newData: MutableList<CountManagerItem>?) {
        this.mCountDataList = newData
        notifyDataSetChanged()
    }

    /**
     * 添加单个数据项
     * @param data 要添加的数据
     */
    fun addItem(data: CountManagerItem) {
        if (mCountDataList != null) {
            mCountDataList!!.add(data)
            notifyItemInserted(mCountDataList!!.size - 1)
        }
    }

    /**
     * 移除指定位置的数据项
     * @param position 要移除的数据位置
     */
    fun removeItem(position: Int) {
        if (mCountDataList != null && position >= 0 && position < mCountDataList!!.size) {
            mCountDataList!!.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, mCountDataList!!.size - position)
        }
    }

    /**
     * 设置点击事件监听器
     * @param listener 点击事件监听器
     */
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.mOnItemClickListener = listener
    }

    /**
     * 点击事件监听器接口
     */
    interface OnItemClickListener {
        fun onItemClick(position: Int, data: CountManagerItem?)
    }

    /**
     * 自定义ViewHolder
     */
    class CountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 初始化视图
        var teamName: TextView = itemView.findViewById<TextView>(R.id.team_name)
    }
}

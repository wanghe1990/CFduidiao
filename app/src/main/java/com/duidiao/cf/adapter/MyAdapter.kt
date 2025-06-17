package com.duidiao.cf.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duidiao.cf.R

// 数据模型
data class Item(val id: Int, val name: String)

// 适配器类（独立文件）
class MyAdapter(private val dataList: List<Item>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // 创建ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_item, parent, false)
        return ViewHolder(view)
    }

    // 绑定数据到ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
//        holder.textView.text = item.name
    }

    // 返回数据项数量
    override fun getItemCount(): Int = dataList.size

    // ViewHolder类
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}
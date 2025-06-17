package com.duidiao.cf.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duidiao.cf.R
import com.duidiao.cf.model.Item


// 适配器类（独立文件）
class MyAdapter(private val dataList: List<Item>?) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // 创建ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_item, parent, false)
        return ViewHolder(view)
    }

    // 绑定数据到ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList?.get(position)
        holder.index.text = item?.index.toString()



    }

    // 返回数据项数量
    override fun getItemCount(): Int = dataList?.size!!

    // ViewHolder类
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val index: TextView = itemView.findViewById(R.id.tv_index)
//        val openTeam1: TextView = itemView.findViewById(R.id.et_open_team1)
//        val openTeam2: TextView = itemView.findViewById(R.id.et_open_team2)
        val openBig1: TextView = itemView.findViewById(R.id.tv_open_big1)
        val openBig2: TextView = itemView.findViewById(R.id.tv_open_big2)
        val openSmall1: TextView = itemView.findViewById(R.id.tv_open_small1)
        val openSmall2: TextView = itemView.findViewById(R.id.tv_open_small2)
        val openLL: LinearLayout = itemView.findViewById(R.id.ll_open)
        val openDoubleBig: TextView = itemView.findViewById(R.id.tv_open_double_big)
        val openDoubleSmall: TextView = itemView.findViewById(R.id.tv_open_double_small)
        val openSingleBig: TextView = itemView.findViewById(R.id.tv_open_single_big)
        val openSingleSmall: TextView = itemView.findViewById(R.id.tv_open_single_small)

//        val closeTeam1: TextView = itemView.findViewById(R.id.et_close_team1)
//        val closeTeam2: TextView = itemView.findViewById(R.id.et_close_team2)
        val closeBig1: TextView = itemView.findViewById(R.id.tv_close_big1)
        val closeBig2: TextView = itemView.findViewById(R.id.tv_close_big2)
        val closeSmall1: TextView = itemView.findViewById(R.id.tv_close_small1)
        val closeSmall2: TextView = itemView.findViewById(R.id.tv_close_small2)
        val closeLL: LinearLayout = itemView.findViewById(R.id.ll_close)
        val closeDoubleBig: TextView = itemView.findViewById(R.id.tv_close_double_big)
        val closeDoubleSmall: TextView = itemView.findViewById(R.id.tv_close_double_small)
        val closeSingleBig: TextView = itemView.findViewById(R.id.tv_close_single_big)
        val closeSingleSmall: TextView = itemView.findViewById(R.id.tv_close_single_small)
    }
}
package com.duidiao.cf

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.duidiao.cf.adapter.MyAdapter
import com.duidiao.cf.model.Item

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"
    private var recyclerView: RecyclerView? = null
    private val dateList = mutableListOf<Item>()
    private var start: TextView? = null
    private var result: TextView? = null
    private var index: TextView? = null
    private var next: ImageView? = null
    private var delete: ImageView? = null
    private var currentPosition = 0
    private var adapter: MyAdapter? = null

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initView()
        initList()
        initRecycleView()
    }

    private fun initList() {
        var item = Item()
        dateList.add(item)
    }

    private fun initRecycleView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = layoutManager

        // 设置PagerSnapHelper实现整页滑动
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        // 禁用过度滚动效果
        recyclerView?.overScrollMode = View.OVER_SCROLL_NEVER


        adapter = MyAdapter(dateList)
        recyclerView?.adapter = adapter

        // 添加页面切换监听
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    currentPosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                    if (currentPosition != RecyclerView.NO_POSITION) {
                        Log.i(TAG, "currentPosition,     position$currentPosition")
                        Log.i(TAG, "position $currentPosition, data= ${
                            dateList?.get(currentPosition).toString()}")
                        index?.text = "第 ${currentPosition+1} 局"
                    }
                }
            }
        })
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
        start = findViewById(R.id.tv_start)
        result = findViewById(R.id.tv_result)
        delete = findViewById(R.id.iv_delete)
        next = findViewById(R.id.iv_next)
        index = findViewById(R.id.tv_index)

        start?.setOnClickListener {
            handleScore(dateList)
        }
        next?.setOnClickListener {
            var item = Item()
            dateList.add(item)
            recyclerView?.smoothScrollToPosition(dateList.size - 1)
            adapter?.notifyDataSetChanged()
            index?.text = "第 ${dateList.size - 1} 局"
        }
        delete?.setOnClickListener {
            Log.i(TAG, "delete : dateList ${dateList.size}")
            if (dateList.size > 1) {
                dateList.removeAt(currentPosition)
                adapter?.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "最后一个不可删除", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleScore(dataList: List<Item>?) {
        Log.i(TAG, "")
    }

}
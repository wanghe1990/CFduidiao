package com.duidiao.cf

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.duidiao.cf.adapter.MyAdapter
import com.duidiao.cf.model.Item

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"
    private var recyclerView: RecyclerView? = null
    private var dataList: List<Item>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initView()
        initList()
        initRecycleView()
    }

    private fun initList() {
        var item = Item(1,0,0,0,0,0,0, "", 0)
        dataList = listOf(item, item, item)
    }

    private fun initRecycleView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = layoutManager

        // 设置PagerSnapHelper实现整页滑动
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        // 禁用过度滚动效果
        recyclerView?.overScrollMode = View.OVER_SCROLL_NEVER


        val adapter = MyAdapter(dataList)
        recyclerView?.adapter = adapter

        // 添加页面切换监听
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val currentPosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                    if (currentPosition != RecyclerView.NO_POSITION) {
                        Log.i(TAG, "currentPosition $newState, position$currentPosition")
                    }
                }
            }
        })
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
    }

}
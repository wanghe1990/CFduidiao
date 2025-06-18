package com.duidiao.cf

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
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
    private var start: TextView? = null
    private var result: TextView? = null
    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initView()
        initList()
        initRecycleView()
    }

    private fun initList() {
        var item = Item()
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
                    currentPosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                    if (currentPosition != RecyclerView.NO_POSITION) {
                        Log.i(TAG, "currentPosition,     position$currentPosition")
                    }
                }
            }
        })
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
        start = findViewById(R.id.tv_start)
        result = findViewById(R.id.tv_result)

        start?.setOnClickListener {
            handleScore()
        }
    }

    private fun handleScore() {
        var date = dataList?.get(currentPosition)
        Log.i(TAG, "date = ${date.toString()}")

        var resultScore = 0
        //潇洒
        if (date?.openUseBig1Select!!) {
            resultScore += 3
        }
        if (date?.openUseBig2Select!!) {
            resultScore += 3
        }
        if (date?.openUseSmall1Select!!) {
            resultScore += 2
        }
        if (date?.openUseSmall2Select!!) {
            resultScore += 2
        }
        //扣抵
        if (date?.openDoubleBigKouSelect!!) {
            resultScore += 6
            date.openTeam = 1
        }
        if (date?.openDoubleSmallKouSelect!!) {
            resultScore += 4
            date.openTeam = 1
        }
        if (date?.openSingleBigKouSelect!!) {
            resultScore += 3
            date.openTeam = 1
        }
        if (date?.openSingleSmallKouSelect!!) {
            resultScore += 2
            date.openTeam = 1
        }



    }

}
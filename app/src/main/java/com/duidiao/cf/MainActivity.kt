package com.duidiao.cf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.duidiao.cf.adapter.MyAdapter

class MainActivity : ComponentActivity() {

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initView()
        initRecycleView()
    }

    private fun initRecycleView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView?.layoutManager = layoutManager
        val adapter = MyAdapter(dataList)
        recyclerView?.adapter = adapter
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
    }

}
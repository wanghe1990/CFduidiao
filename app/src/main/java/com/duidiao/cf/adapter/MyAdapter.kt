package com.duidiao.cf.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.duidiao.cf.R
import com.duidiao.cf.model.Item


// 适配器类（独立文件）
class MyAdapter(private val dataList: List<Item>?) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private val TAG = "MyAdapter"
    private var ctx: Context? = null

    // 创建ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_item, parent, false)
        ctx = parent.context
        return ViewHolder(view)
    }

    // 绑定数据到ViewHolder
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList?.get(position)
        Log.i(TAG, "onBindViewHolder position = $position, data item = ${item.toString()}")
//        item?.index = "第 ${position+1} 局"
//        holder.index.text = item?.index.toString()
        holder.openRbEast.text = item?.openTeam1
        holder.openRbNorth.text = item?.openTeam2
        holder.openRbNorth.isSelected = item?.openNorthCheckable!!
        holder.openRbEast.isSelected = item?.openEastCheckable!!
        holder.openUseBig1.isSelected = item?.openUseBig1Select!!
        holder.openUseBig2.isSelected = item?.openUseBig2Select!!
        holder.openUseSmall1.isSelected = item?.openUseSmall1Select!!
        holder.openUseSmall2.isSelected = item?.openUseSmall2Select!!
        holder.openDoubleBigKou.isSelected = item?.openDoubleBigKouSelect!!
        holder.openDoubleSmallKou.isSelected = item?.openDoubleSmallKouSelect!!
        holder.openSingleBigKou.isSelected = item?.openSingleBigKouSelect!!
        holder.openSingleSmallKou.isSelected = item?.openSingleSmallKouSelect!!
        holder.openScore.setText(item?.openScore.toString())

        holder.closeRbEast.text = item?.closeTeam2
        holder.closeRbNorth.text = item?.closeTeam1
        holder.closeRbNorth.isSelected = item?.closeNorthCheckable!!
        holder.closeRbEast.isSelected = item?.closeEastCheckable!!
        holder.closeRbNorth.isClickable = false
        holder.closeRbEast.isClickable = false
        holder.closeUseBig1.isSelected = item?.closeUseBig1Select!!
        holder.closeUseBig2.isSelected = item?.closeUseBig2Select!!
        holder.closeUseSmall1.isSelected = item?.closeUseSmall1Select!!
        holder.closeUseSmall2.isSelected = item?.closeUseSmall2Select!!
        holder.closeDoubleBigKou.isSelected = item?.closeDoubleBigKouSelect!!
        holder.closeDoubleSmallKou.isSelected = item?.closeDoubleSmallKouSelect!!
        holder.closeSingleBigKou.isSelected = item?.closeSingleBigKouSelect!!
        holder.closeSingleSmallKou.isSelected = item?.closeSingleSmallKouSelect!!
        holder.closeScore.setText(item?.closeScore.toString())
        initEvent(holder, item, position)
    }

    @SuppressLint("ResourceAsColor")
    private fun initEvent(holder: ViewHolder, item: Item?, position: Int) {
        holder.openRbNorth.setOnClickListener {
            val isChecked = holder.openRbNorth.isSelected
            holder.openRbNorth.isSelected = !isChecked
            holder.openRbEast.isSelected = isChecked
            holder.closeRbNorth.isSelected = !isChecked
            holder.closeRbEast.isSelected = isChecked

            item?.openNorthCheckable = !isChecked
            item?.openEastCheckable = isChecked
            item?.closeNorthCheckable = !isChecked
            item?.closeEastCheckable = isChecked

        }

        holder.openRbEast.setOnClickListener {
            val isChecked = holder.openRbEast.isSelected
            holder.openRbEast.isSelected = !isChecked
            holder.openRbNorth.isSelected = isChecked
            holder.closeRbEast.isSelected = !isChecked
            holder.closeRbNorth.isSelected = isChecked

            item?.openNorthCheckable = isChecked
            item?.openEastCheckable = !isChecked
            item?.closeNorthCheckable = isChecked
            item?.closeEastCheckable = !isChecked

        }
        holder.openDoubleBigKou.setOnClickListener {
            val isSelect = holder.openDoubleBigKou.isSelected
            holder.openDoubleBigKou.isSelected = !isSelect
            item?.openDoubleBigKouSelect = holder.openDoubleBigKou.isSelected
        }
        holder.openDoubleSmallKou.setOnClickListener {
            val isSelect = holder.openDoubleSmallKou.isSelected
            holder.openDoubleSmallKou.isSelected = !isSelect
            item?.openDoubleSmallKouSelect = holder.openDoubleSmallKou.isSelected
        }
        holder.openSingleBigKou.setOnClickListener {
            val isSelect = holder.openSingleBigKou.isSelected
            holder.openSingleBigKou.isSelected = !isSelect
            item?.openSingleBigKouSelect = holder.openSingleBigKou.isSelected
        }
        holder.openSingleSmallKou.setOnClickListener {
            val isSelect = holder.openSingleSmallKou.isSelected
            holder.openSingleSmallKou.isSelected = !isSelect
            item?.openSingleSmallKouSelect = holder.openSingleSmallKou.isSelected
        }

        holder.openUseBig1.setOnClickListener {
            val isSelect = holder.openUseBig1.isSelected
            holder.openUseBig1.isSelected = !isSelect
            item?.openUseBig1Select = holder.openUseBig1.isSelected
        }
        holder.openUseBig2.setOnClickListener {
            val isSelect = holder.openUseBig2.isSelected
            holder.openUseBig2.isSelected = !isSelect
            item?.openUseBig2Select = holder.openUseBig2.isSelected
        }
        holder.openUseSmall1.setOnClickListener {
            val isSelect = holder.openUseSmall1.isSelected
            holder.openUseSmall1.isSelected = !isSelect
            item?.openUseSmall1Select = holder.openUseSmall1.isSelected
        }
        holder.openUseSmall2.setOnClickListener {
            val isSelect = holder.openUseSmall2.isSelected
            holder.openUseSmall2.isSelected = !isSelect
            item?.openUseSmall2Select = holder.openUseSmall2.isSelected
        }

        val textWatcherOpen = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isEmpty()) {
                    item?.openScore = 0
                } else {
                    item?.openScore = p0.toString().toInt()
                }
            }
        }

        holder.openScore.addTextChangedListener(textWatcherOpen)

        //close
        holder.closeDoubleBigKou.setOnClickListener {
            val isSelect = holder.closeDoubleBigKou.isSelected
            holder.closeDoubleBigKou.isSelected = !isSelect
            item?.closeDoubleBigKouSelect = holder.closeDoubleBigKou.isSelected
        }
        holder.closeDoubleSmallKou.setOnClickListener {
            val isSelect = holder.closeDoubleSmallKou.isSelected
            holder.closeDoubleSmallKou.isSelected = !isSelect
            item?.closeDoubleSmallKouSelect = holder.closeDoubleSmallKou.isSelected
        }
        holder.closeSingleBigKou.setOnClickListener {
            val isSelect = holder.closeSingleBigKou.isSelected
            holder.closeSingleBigKou.isSelected = !isSelect
            item?.closeSingleBigKouSelect = holder.closeSingleBigKou.isSelected
        }
        holder.closeSingleSmallKou.setOnClickListener {
            val isSelect = holder.closeSingleSmallKou.isSelected
            holder.closeSingleSmallKou.isSelected = !isSelect
            item?.closeSingleSmallKouSelect = holder.closeSingleSmallKou.isSelected
        }

        holder.closeUseBig1.setOnClickListener {
            val isSelect = holder.closeUseBig1.isSelected
            holder.closeUseBig1.isSelected = !isSelect
            item?.closeUseBig1Select = holder.closeUseBig1.isSelected
        }
        holder.closeUseBig2.setOnClickListener {
            val isSelect = holder.closeUseBig2.isSelected
            holder.closeUseBig2.isSelected = !isSelect
            item?.closeUseBig2Select = holder.closeUseBig2.isSelected
        }
        holder.closeUseSmall1.setOnClickListener {
            val isSelect = holder.closeUseSmall1.isSelected
            holder.closeUseSmall1.isSelected = !isSelect
            item?.closeUseSmall1Select = holder.closeUseSmall1.isSelected
        }
        holder.closeUseSmall2.setOnClickListener {
            val isSelect = holder.closeUseSmall2.isSelected
            holder.closeUseSmall2.isSelected = !isSelect
            item?.closeUseSmall2Select = holder.closeUseSmall2.isSelected
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isEmpty()) {
                    item?.closeScore = 0
                } else {
                    item?.closeScore = p0.toString().toInt()
                }
            }
        }

        holder.closeScore.addTextChangedListener(textWatcher)

        holder.clear.setOnClickListener {
//            holder.openRadioGroup.clearCheck()
//            holder.openRgKou.clearCheck()
            holder.openScore.setText("")
            item?.openScore = 0
            holder.openRbNorth.isSelected = false
            holder.openRbEast.isSelected = false
            holder.openDoubleBigKou.isSelected = false
            holder.openDoubleSmallKou.isSelected = false
            holder.openSingleBigKou.isSelected = false
            holder.openSingleSmallKou.isSelected = false
            holder.openUseBig1.isSelected = false
            holder.openUseBig2.isSelected = false
            holder.openUseSmall1.isSelected = false
            holder.openUseSmall2.isSelected = false
//            holder.openRgKou.clearCheck()
            item?.openResultScore = 0

//            holder.closeRadioGroup.clearCheck()
            holder.closeScore.setText("")
//            holder.closeRgKou.clearCheck()
            item?.closeScore = 0
            holder. closeRbNorth.isSelected = false
            holder. closeRbEast.isSelected = false
            holder. closeDoubleBigKou.isSelected = false
            holder. closeDoubleSmallKou.isSelected = false
            holder. closeSingleBigKou.isSelected = false
            holder. closeSingleSmallKou.isSelected = false
            holder.closeUseBig1.isSelected = false
            holder.closeUseBig2.isSelected = false
            holder.closeUseSmall1.isSelected = false
            holder.closeUseSmall2.isSelected = false
            item?.closeResultScore = 0
            item?.realWinTeam = ""
            item?.realResultScore = 0
            holder.result.text = "赢家：___,得分:___"
        }

        holder.start.setOnClickListener {
            Log.i(TAG, "计算： position = $position, date item = ${item.toString()}")
            if (!holder.openRbEast.isSelected && !holder.openRbNorth.isSelected) {
                Toast.makeText(ctx, "请先选择庄家", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            handlerScore(holder, item)
        }

        holder.openSubImage.setOnClickListener {
            var score = holder.openScore.text.toString()
            if (score.isEmpty()) {
                score = "0"
            }
            if (score.toInt() >= 5) {
                holder.openScore.setText((score.toInt()-5).toString())
            } else {
                holder.openScore.setText("0")
            }
            item?.openScore = holder.openScore.text.toString().toInt()
        }

        holder.openAddImage.setOnClickListener {
            var score = holder.openScore.text.toString()
            if (score.isEmpty()) {
                score = "0"
            }
            if (score.toInt() < 200) {
                holder.openScore.setText((score.toInt() + 10).toString())
            } else {
                holder.openScore.setText("200")
            }
            item?.openScore = holder.openScore.text.toString().toInt()
        }

        holder.closeSubImage.setOnClickListener {
            var score = holder.closeScore.text.toString()
            if (score.isEmpty()) {
                score = "0"
            }
            if (score.toInt() >= 5) {
                holder.closeScore.setText((score.toInt()-5).toString())
            } else {
                holder.closeScore.setText("0")
            }
            item?.closeScore = holder.closeScore.text.toString().toInt()
        }

        holder.closeAddImage.setOnClickListener {
            var score = holder.closeScore.text.toString()
            if (score.isEmpty()) {
                score = "0"
            }
            if (score.toInt() < 200) {
                holder.closeScore.setText((score.toInt() + 10).toString())
            } else {
                holder.closeScore.setText("200")
            }
            item?.closeScore = holder.closeScore.text.toString().toInt()
        }

    }

    private fun handlerScore(holder: ViewHolder, item: Item?) {
        var date = item ?: Item()
        Log.i(TAG, "handlerScore date = ${date.toString()}")

        date.openResultScore = 0
        date.openWinTeam = if (date.openEastCheckable) date.openTeam1 else date.openTeam2
        //开室计算
        if (date.openUseBig1Select) {
            date.openResultScore += 3
        }
        if (date.openUseBig2Select) {
            date.openResultScore += 3
        }
        if (date.openUseSmall1Select) {
            date.openResultScore += 2
        }
        if (date.openUseSmall2Select) {
            date.openResultScore += 2
        }
        //扣抵
        if (date.openDoubleBigKouSelect) {
            date.openWinTeam = if (date.openEastCheckable) date.openTeam2 else date.openTeam1
            date.openResultScore += 3
        }
        if (date.openDoubleSmallKouSelect) {
            date.openWinTeam = if (date.openEastCheckable) date.openTeam2 else date.openTeam1
            date.openResultScore += 3
        }
        if (date.openSingleBigKouSelect) {
            date.openWinTeam = if (date.openEastCheckable) date.openTeam2 else date.openTeam1
            date.openResultScore += 2
        }
        if (date.openSingleSmallKouSelect) {
            date.openWinTeam = if (date.openEastCheckable) date.openTeam2 else date.openTeam1
            date.openResultScore += 2
        }

        if (date.openScore >= 80) {
            date.openWinTeam = if (date.openEastCheckable) date.openTeam2 else date.openTeam1
        }

        Log.i(TAG, "打光秃逻辑 ${date.openScore} ， win team = ${date.openWinTeam} ")
        if ((date.openWinTeam == if (date.openEastCheckable) date.openTeam1 else date.openTeam2) && date.openScore == 0) {
            date.openResultScore += 3
        }

        //闭室计算
        date.closeResultScore = 0
        date.closeWinTeam = if (date.openEastCheckable) date.closeTeam2 else date.closeTeam1
        //潇洒
        if (date.closeUseBig1Select) {
            date.closeResultScore += 3
        }
        if (date.closeUseBig2Select) {
            date.closeResultScore += 3
        }
        if (date.closeUseSmall1Select) {
            date.closeResultScore += 2
        }
        if (date.closeUseSmall2Select) {
            date.closeResultScore += 2
        }
        //扣抵
        if (date.closeDoubleBigKouSelect) {
            date.closeWinTeam = if (date.closeEastCheckable) date.closeTeam1 else date.closeTeam2
            date.closeResultScore += 3
        }
        if (date.closeDoubleSmallKouSelect) {
            date.closeWinTeam = if (date.closeEastCheckable) date.closeTeam1 else date.closeTeam2
            date.closeResultScore += 3
        }
        if (date.closeSingleBigKouSelect) {
            date.closeWinTeam = if (date.closeEastCheckable) date.closeTeam1 else date.closeTeam2
            date.closeResultScore += 2
        }
        if (date.closeSingleSmallKouSelect) {
            date.closeWinTeam = if (date.closeEastCheckable) date.closeTeam1 else date.closeTeam2
            date.closeResultScore += 2
        }

        if (date.closeScore >= 80) {
            date.closeWinTeam = if (date.closeEastCheckable) date.closeTeam1 else date.closeTeam2
        }

        if ((date.closeWinTeam == if (date.closeEastCheckable) date.closeTeam2 else date.closeTeam1) && date.closeScore == 0) {
            date.closeResultScore += 3
        }

        Log.i(TAG, "比分结果，赢家: 上-${date.openWinTeam} ,赢家: 下-${date.closeWinTeam},， 上桌得分：${date.openResultScore}, 下桌得分：${date.closeResultScore}")
        if (date.openWinTeam == date.closeWinTeam) {
            date.realResultScore = date.openResultScore + date.closeResultScore
            if (date.openResultScore == 0) { //双赢+1
                date.realResultScore += 1
            }
            if (date.closeResultScore == 0) {
                date.realResultScore += 1
            }
            date.realWinTeam = date.openWinTeam
        } else {
            if (date.openResultScore > date.closeResultScore) {
                date.realResultScore = date.openResultScore - date.closeResultScore
                date.realWinTeam = date.openWinTeam
            } else if (date.openResultScore < date.closeResultScore) {
                date.realResultScore = date.closeResultScore - date.openResultScore
                date.realWinTeam = date.closeWinTeam
            } else {
                //都是庄赢
                if ((date.openWinTeam == if (date.openEastCheckable) date.openTeam1 else date.openTeam2)
                    && (date.closeWinTeam == if (date.openEastCheckable) date.closeTeam2 else date.closeTeam1)) {
                    if (date.openScore > date.closeScore) {
                        date.realWinTeam = date.closeWinTeam
                        date.realResultScore = 1
                    } else if (date.openScore < date.closeScore) {
                        date.realWinTeam = date.openWinTeam
                        date.realResultScore = 1
                    } else {
                        date.realResultScore = 0
                        date.realWinTeam = "default"
                    }

                } else {
                    if (date.openScore > date.closeScore) {
                        date.realWinTeam = date.openWinTeam
                        date.realResultScore = 1
                    } else if (date.openScore < date.closeScore) {
                        date.realWinTeam = date.closeWinTeam
                        date.realResultScore = 1
                    } else {
                        date.realResultScore = 0
                        date.realWinTeam = "default"
                    }
                }
            }
        }
        holder.result.text =
            "赢家：" + if (date.realResultScore == 0) "平局" else date.realWinTeam + ",得分: " + date.realResultScore
    }

    // 返回数据项数量
    override fun getItemCount(): Int = dataList?.size!!

    // ViewHolder类
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val index: TextView = itemView.findViewById(R.id.tv_index)
        val openRadioGroup: LinearLayout = itemView.findViewById(R.id.rg_open)
        val openRbNorth: TextView = itemView.findViewById(R.id.rb_open_north) //东西
        val openRbEast: TextView = itemView.findViewById(R.id.rb_open_east) //南北
        val openUseBig1: TextView = itemView.findViewById(R.id.tv_open_use_big1)
        val openUseBig2: TextView = itemView.findViewById(R.id.tv_open_use_big2)
        val openUseSmall1: TextView = itemView.findViewById(R.id.tv_open_use_small1)
        val openUseSmall2: TextView = itemView.findViewById(R.id.tv_open_use_small2)
        val openRgKou: LinearLayout = itemView.findViewById(R.id.rg_open_kou)
        val openDoubleBigKou: TextView = itemView.findViewById(R.id.tv_open_double_big)
        val openDoubleSmallKou: TextView = itemView.findViewById(R.id.tv_open_double_small)
        val openSingleBigKou: TextView = itemView.findViewById(R.id.tv_open_single_big)
        val openSingleSmallKou: TextView = itemView.findViewById(R.id.tv_open_single_small)
        val openSubImage: ImageView = itemView.findViewById(R.id.iv_open_sub)
        val openScore: EditText = itemView.findViewById(R.id.et_open_score)
        val openAddImage: ImageView = itemView.findViewById(R.id.iv_open_add)

        val closeRadioGroup: LinearLayout = itemView.findViewById(R.id.rg_close)
        val closeRbNorth: TextView = itemView.findViewById(R.id.rb_close_north) //东西
        val closeRbEast: TextView = itemView.findViewById(R.id.rb_close_east) //南北
        val closeUseBig1: TextView = itemView.findViewById(R.id.tv_close_use_big1)
        val closeUseBig2: TextView = itemView.findViewById(R.id.tv_close_use_big2)
        val closeUseSmall1: TextView = itemView.findViewById(R.id.tv_close_use_small1)
        val closeUseSmall2: TextView = itemView.findViewById(R.id.tv_close_use_small2)
        val closeRgKou: LinearLayout = itemView.findViewById(R.id.rg_close_kou)
        val closeDoubleBigKou: TextView = itemView.findViewById(R.id.tv_close_double_big)
        val closeDoubleSmallKou: TextView = itemView.findViewById(R.id.tv_close_double_small)
        val closeSingleBigKou: TextView = itemView.findViewById(R.id.tv_close_single_big)
        val closeSingleSmallKou: TextView = itemView.findViewById(R.id.tv_close_single_small)
        val closeSubImage: ImageView = itemView.findViewById(R.id.iv_close_sub)
        val closeScore: EditText = itemView.findViewById(R.id.et_close_score)
        val closeAddImage: ImageView = itemView.findViewById(R.id.iv_close_add)

        val clear: ImageView = itemView.findViewById(R.id.tv_clear)
        val start: TextView = itemView.findViewById(R.id.tv_start)
        val result: TextView = itemView.findViewById(R.id.result)
    }
}
package com.duidiao.cf.adapter

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
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
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
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList?.get(position)
        item?.index = "第 ${position+1} 局"
        holder.index.text = item?.index.toString()
        holder.openRbNorth.isChecked = item?.openNorthCheckable!!
        holder.openRbEast.isChecked = item?.openEastCheckable!!
        holder.openUseBig1.isSelected = item?.openUseBig1Select!!
        holder.openUseBig2.isSelected = item?.openUseBig2Select!!
        holder.openUseSmall1.isSelected = item?.openUseSmall1Select!!
        holder.openUseSmall2.isSelected = item?.openUseSmall2Select!!
        holder.openDoubleBigKou.isSelected = item?.openDoubleBigKouSelect!!
        holder.openDoubleSmallKou.isSelected = item?.openDoubleSmallKouSelect!!
        holder.openSingleBigKou.isSelected = item?.openSingleBigKouSelect!!
        holder.openSingleSmallKou.isSelected = item?.openSingleSmallKouSelect!!
        holder.openScore.setText(item?.openScore.toString())

        holder.closeRbNorth.isChecked = item?.closeNorthCheckable!!
        holder.closeRbEast.isChecked = item?.closeEastCheckable!!
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
        initEvent(holder, item)
    }

    private fun initEvent(holder: ViewHolder, item: Item?) {
        holder.openRbNorth.setOnCheckedChangeListener { button, isChecked ->
            item?.openNorthCheckable = isChecked
            item?.closeNorthCheckable = isChecked
            holder.closeRbNorth.isChecked = isChecked
            holder.closeRbEast.isChecked = !isChecked
            if (isChecked) {
                item?.openTeam1 = "1队"
                item?.openTeam2 = "2队"
            }
        }

        holder.openRbEast.setOnCheckedChangeListener { button, isChecked ->
            item?.openEastCheckable = isChecked
            item?.closeEastCheckable = isChecked
            holder.closeRbEast.isChecked = isChecked
            holder.closeRbNorth.isChecked = !isChecked
            if (isChecked) {
                item?.closeTeam1 = "1队"
                item?.closeTeam2 = "2队"
            }
        }
        holder.openDoubleBigKou.setOnCheckedChangeListener { button, isChecked ->
            Log.i(TAG, "openDoubleBigKouSelect = = $isChecked")
            item?.openDoubleBigKouSelect = isChecked
        }
        holder.openDoubleSmallKou.setOnCheckedChangeListener { button, isChecked ->
            item?.openDoubleSmallKouSelect = isChecked
        }
        holder.openSingleBigKou.setOnCheckedChangeListener { button, isChecked ->
            item?.openSingleBigKouSelect = isChecked
        }
        holder.openSingleSmallKou.setOnCheckedChangeListener { button, isChecked ->
            item?.openSingleSmallKouSelect = isChecked
        }

        holder.openUseBig1.setOnCheckedChangeListener { button, isChecked ->
            item?.openUseBig1Select = isChecked
        }
        holder.openUseBig2.setOnCheckedChangeListener { button, isChecked ->
            item?.openUseBig2Select = isChecked
        }
        holder.openUseSmall1.setOnCheckedChangeListener { button, isChecked ->
            item?.openUseSmall1Select = isChecked
        }
        holder.openUseSmall2.setOnCheckedChangeListener { button, isChecked ->
            item?.openUseSmall2Select = isChecked
        }

        holder.openScore.addTextChangedListener {
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    item?.openScore = p0.toString().toInt()
                }
            }
        }

        //close
        holder.closeDoubleBigKou.setOnCheckedChangeListener { button, isChecked ->
            item?.closeDoubleBigKouSelect = isChecked
        }
        holder.closeDoubleSmallKou.setOnCheckedChangeListener { button, isChecked ->
            item?.closeDoubleSmallKouSelect = isChecked
        }
        holder.closeSingleBigKou.setOnCheckedChangeListener { button, isChecked ->
            item?.closeSingleBigKouSelect = isChecked
        }
        holder.closeSingleSmallKou.setOnCheckedChangeListener { button, isChecked ->
            item?.closeSingleSmallKouSelect = isChecked
        }

        holder.closeUseBig1.setOnCheckedChangeListener { button, isChecked ->
            item?.closeUseBig1Select = isChecked
        }
        holder.closeUseBig2.setOnCheckedChangeListener { button, isChecked ->
            item?.closeUseBig2Select = isChecked
        }
        holder.closeUseSmall1.setOnCheckedChangeListener { button, isChecked ->
            item?.closeUseSmall1Select = isChecked
        }
        holder.closeUseSmall2.setOnCheckedChangeListener { button, isChecked ->
            item?.closeUseSmall2Select = isChecked
        }
        holder.closeScore.addTextChangedListener {
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    item?.closeScore = p0.toString().toInt()
                }
            }
        }

        holder.clear.setOnClickListener {
            holder.openRadioGroup.clearCheck()
            holder.openRgKou.clearCheck()
            holder.openScore.setText("")
            item?.openScore = 0
            holder.openUseBig1.isChecked = false
            holder.openUseBig2.isChecked = false
            holder.openUseSmall1.isChecked = false
            holder.openUseSmall2.isChecked = false
            holder.openRgKou.clearCheck()
            item?.openResultScore = 0

            holder.closeRadioGroup.clearCheck()
            holder.closeScore.setText("")
            holder.closeRgKou.clearCheck()
            item?.closeScore = 0
            holder.closeUseBig1.isChecked = false
            holder.closeUseBig2.isChecked = false
            holder.closeUseSmall1.isChecked = false
            holder.closeUseSmall2.isChecked = false
            item?.closeResultScore = 0
            item?.realWinTeam = ""
            item?.realResultScore = 0
            holder.result.text = "赢家：___,得分:___"
        }

        holder.start.setOnClickListener {
            if (holder.openRadioGroup.checkedRadioButtonId == -1) {
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
            date.openResultScore += 6
        }
        if (date.openDoubleSmallKouSelect) {
            date.openWinTeam = if (date.openEastCheckable) date.openTeam2 else date.openTeam1
            date.openResultScore += 4
        }
        if (date.openSingleBigKouSelect) {
            date.openWinTeam = if (date.openEastCheckable) date.openTeam2 else date.openTeam1
            date.openResultScore += 3
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
            date.closeResultScore += 6
        }
        if (date.closeDoubleSmallKouSelect) {
            date.closeWinTeam = if (date.closeEastCheckable) date.closeTeam1 else date.closeTeam2
            date.closeResultScore += 4
        }
        if (date.closeSingleBigKouSelect) {
            date.closeWinTeam = if (date.closeEastCheckable) date.closeTeam1 else date.closeTeam2
            date.closeResultScore += 3
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
                if (date.openScore > date.closeScore) {
                    date.realWinTeam = date.closeWinTeam
                    date.realResultScore = 1
                } else if (date.openScore < date.closeScore) {
                    date.realWinTeam = date.openWinTeam
                    date.realResultScore = 1
                } else {
                    date.realResultScore = 0
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
        val index: TextView = itemView.findViewById(R.id.tv_index)
        val openRadioGroup: RadioGroup = itemView.findViewById(R.id.rg_open)
        val openRbNorth: RadioButton = itemView.findViewById(R.id.rb_open_north) //东西
        val openRbEast: RadioButton = itemView.findViewById(R.id.rb_open_east) //南北
        val openUseBig1: CheckBox = itemView.findViewById(R.id.tv_open_use_big1)
        val openUseBig2: CheckBox = itemView.findViewById(R.id.tv_open_use_big2)
        val openUseSmall1: CheckBox = itemView.findViewById(R.id.tv_open_use_small1)
        val openUseSmall2: CheckBox = itemView.findViewById(R.id.tv_open_use_small2)
        val openRgKou: RadioGroup = itemView.findViewById(R.id.rg_open_kou)
        val openDoubleBigKou: RadioButton = itemView.findViewById(R.id.tv_open_double_big)
        val openDoubleSmallKou: RadioButton = itemView.findViewById(R.id.tv_open_double_small)
        val openSingleBigKou: RadioButton = itemView.findViewById(R.id.tv_open_single_big)
        val openSingleSmallKou: RadioButton = itemView.findViewById(R.id.tv_open_single_small)
        val openSubImage: ImageView = itemView.findViewById(R.id.iv_open_sub)
        val openScore: EditText = itemView.findViewById(R.id.et_open_score)
        val openAddImage: ImageView = itemView.findViewById(R.id.iv_open_add)

        val closeRadioGroup: RadioGroup = itemView.findViewById(R.id.rg_close)
        val closeRbNorth: RadioButton = itemView.findViewById(R.id.rb_close_north) //东西
        val closeRbEast: RadioButton = itemView.findViewById(R.id.rb_close_east) //南北
        val closeUseBig1: CheckBox = itemView.findViewById(R.id.tv_close_use_big1)
        val closeUseBig2: CheckBox = itemView.findViewById(R.id.tv_close_use_big2)
        val closeUseSmall1: CheckBox = itemView.findViewById(R.id.tv_close_use_small1)
        val closeUseSmall2: CheckBox = itemView.findViewById(R.id.tv_close_use_small2)
        val closeRgKou: RadioGroup = itemView.findViewById(R.id.rg_close_kou)
        val closeDoubleBigKou: RadioButton = itemView.findViewById(R.id.tv_close_double_big)
        val closeDoubleSmallKou: RadioButton = itemView.findViewById(R.id.tv_close_double_small)
        val closeSingleBigKou: RadioButton = itemView.findViewById(R.id.tv_close_single_big)
        val closeSingleSmallKou: RadioButton = itemView.findViewById(R.id.tv_close_single_small)
        val closeSubImage: ImageView = itemView.findViewById(R.id.iv_close_sub)
        val closeScore: EditText = itemView.findViewById(R.id.et_close_score)
        val closeAddImage: ImageView = itemView.findViewById(R.id.iv_close_add)

        val clear: ImageView = itemView.findViewById(R.id.tv_clear)
        val start: TextView = itemView.findViewById(R.id.tv_start)
        val result: TextView = itemView.findViewById(R.id.result)
    }
}
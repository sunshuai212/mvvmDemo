package com.chomper.watermap.ui.data

import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.chomper.watermap.BR
import com.chomper.watermap.R
import com.chomper.watermap.databinding.ActivityDetailLayoutBinding
import com.chomper.watermap.entity.HomeData
import com.chomper.watermap.ui.MyMarkerView
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.Utils
import kotlinx.android.synthetic.main.activity_detail_layout.*
import me.goldze.mvvmhabit.base.BaseActivity
import me.goldze.mvvmhabit.utils.StatusBarUtil
import java.util.*
import kotlin.math.roundToInt


class DataDetailActivity : BaseActivity<ActivityDetailLayoutBinding, DataDetailViewModel>(),
        OnChartValueSelectedListener {
    override fun onNothingSelected() {


    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {

    }

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_detail_layout
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        StatusBarUtil.setTransparentForImageView(this, toolBar)

        val homeData = intent.getParcelableExtra("data") as HomeData
        viewModel.initData(homeData)
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.refreshData.observe(this, Observer {
            if (it) {
                rv_btn.adapter?.notifyDataSetChanged()
            }
        })
        initChartData()
    }

    private fun initChartData() {
        // background color
        chart.setBackgroundColor(Color.WHITE)

        // disable description text
        chart.description.isEnabled = false

        // enable touch gestures
        chart.setTouchEnabled(true)

        // set listeners
        chart.setOnChartValueSelectedListener(this)
        chart.setDrawGridBackground(false)

        // create marker to display box when values are selected
        val mv = MyMarkerView(this, R.layout.custom_maker_view)
        // Set the marker to the chart
        mv.chartView = chart
        chart.marker = mv

        // enable scaling and dragging
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)

        // force pinch zoom along both axis
        chart.setPinchZoom(true)

        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawAxisLine(true)
        xAxis.setDrawGridLines(false)
        xAxis.xOffset = 500f
        xAxis.textColor = Color.parseColor("#a2a2a2")
        xAxis.setValueFormatter { value, axis -> value.toString() }
        val leftAxis = chart.axisLeft
        leftAxis.setValueFormatter { value, axis -> String.format(Locale.getDefault(), "%d", value.roundToInt()) }
        leftAxis.removeAllLimitLines() // reset all limit lines to avoid overlapping lines
//        leftAxis.addLimitLine(ll1);
//        leftAxis.addLimitLine(ll2);
        /*  leftAxis.setAxisMaximum(200f);*/
        leftAxis.setDrawAxisLine(true)
        leftAxis.setDrawGridLines(true)
        leftAxis.textColor = Color.parseColor("#a2a2a2")
        leftAxis.gridColor = Color.parseColor("#e9e9e9")
//        leftAxis.axisMinimum = 0f
//        leftAxis.zeroLineWidth = 0f
//        leftAxis.labelCount = 4
        chart.axisRight.isEnabled = false
        viewModel.lineDataList.observe(this, Observer {
            setData(it)
            // draw points over time
            chart.animateXY(1500, 1500)
        })
    }

    private fun setData(dataList: ArrayList<com.chomper.watermap.entity.LineData>) {

        val values = ArrayList<Entry>()

        for (item in dataList) {

            values.add(Entry(item.date.toFloat(), item.value.toFloat(), resources.getDrawable(R.drawable.circle_shape)))
        }

        val set1: LineDataSet

        if (chart.data != null && chart.data.dataSetCount > 0) {
            set1 = chart.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            set1.notifyDataSetChanged()
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "")

            set1.setDrawIcons(true)
            set1.setDrawCircles(false)

            set1.disableDashedHighlightLine()
            set1.setDrawValues(false)
            // black lines and points
            set1.color = Color.parseColor("#91C5FE")
            // line thickness and point size
            set1.lineWidth = 2f
            set1.circleRadius = 3f

            // draw points as solid circles
            set1.setDrawCircleHole(false)

            // customize legend entry
            set1.formSize = 15f

            set1.setDrawHighlightIndicators(true)

            // text size of values
            set1.valueTextSize = 9f
            set1.setDrawFilled(true)
            // set the filled area
            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                val drawable = ContextCompat.getDrawable(this, R.drawable.fade_red)
                set1.fillDrawable = drawable
            } else {
                set1.fillColor = Color.BLACK
            }
        }

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1) // add the data sets

        // create a data object with the data sets
        val data = LineData(dataSets)

        // set data
        chart.data = data
        val legend = chart.legend
        legend.form = Legend.LegendForm.NONE
    }
}

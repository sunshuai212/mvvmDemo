package com.chomper.watermap.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chomper.watermap.BR
import com.chomper.watermap.R
import com.chomper.watermap.entity.HomeData
import com.chomper.watermap.ui.adapter.HirstoryDataHeader
import com.github.promeg.pinyinhelper.Pinyin
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.activity_search_layout.*
import me.goldze.mvvmhabit.base.BaseActivity
import me.goldze.mvvmhabit.utils.StatusBarUtil

class SearchActivity : BaseActivity<com.chomper.watermap.databinding.ActivitySearchLayoutBinding, SearchViewModel>() {

    var homeDatas: ArrayList<HomeData>? = null
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_search_layout
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initParam() {
        super.initParam()
        homeDatas = intent.getParcelableArrayListExtra<HomeData>("data")
    }

    @SuppressLint("CheckResult")
    override fun initData() {
        super.initData()
        StatusBarUtil.setTransparentForImageView(this, toolBar)

        recycle.layoutManager = LinearLayoutManager(this)
        RxTextView.textChanges(search)
            .subscribe {
                if (it.isNotEmpty()) {
                    filterData(it.toString().trim())
                }
            }
    }

    private fun filterData(key: String) {
        val names = ArrayList<String>()
        homeDatas?.forEach { item ->
            if (item.name.contains(key) || transformPinYin(item.name).toUpperCase().contains(key.toUpperCase())) {
                names.add(item.name)
            }
        }
        recycle.adapter = HirstoryDataHeader(names)
    }

    fun transformPinYin(params: String): String {
        return StringBuffer().apply {
            params.forEach {
                append(Pinyin.toPinyin(it))
            }
        }.toString()
    }


}
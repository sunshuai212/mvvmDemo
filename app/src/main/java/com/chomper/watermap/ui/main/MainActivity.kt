package com.chomper.watermap.ui.main

import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.chomper.watermap.BR
import com.chomper.watermap.R
import com.chomper.watermap.databinding.ActivityMainBinding
import com.chomper.watermap.entity.TabEntity
import com.flyco.tablayout.listener.CustomTabEntity
import kotlinx.android.synthetic.main.activity_main.*
import me.goldze.mvvmhabit.base.BaseActivity
import me.goldze.mvvmhabit.utils.StatusBarUtil
import java.util.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    private val mTitles = arrayOf("首页", "数据", "地图")
    private val mIconUnselectIds = intArrayOf(
        R.mipmap.tabbar_icon_home_default,
        R.mipmap.tabbar_icon_fx_default,
        R.mipmap.tabbar_icon_dt_default
    )
    private val mIconSelectIds = intArrayOf(
        R.mipmap.tabbar_icon_home_selected,
        R.mipmap.tabbar_icon_fx_selected,
        R.mipmap.tabbar_icon_dt_selected
    )
    private val mTabEntities = ArrayList<CustomTabEntity>()
    private val mFragments = ArrayList<Fragment>()

    override fun initData() {
        super.initData()
        mFragments.add(HomeFragment.newInstance())
        mFragments.add(DataFragment.newInstance())
        mFragments.add(MapFragment.newInstance())

        for (i in mTitles.indices) {
            mTabEntities.add(TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]))
        }
        tab.setTabData(mTabEntities, this, R.id.content, mFragments)

        val params = drawerContent.layoutParams as DrawerLayout.LayoutParams
        params.topMargin = StatusBarUtil.getStatusBarHeight(this)
        drawerContent.layoutParams = params
    }
}

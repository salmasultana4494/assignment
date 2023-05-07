package com.example.frindshipassignment.ui.home

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.frindshipassignment.model.UserInfo

class ViewpagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    private val mFragmentList = ArrayList<Fragment>()
    val mFragmentTitleList = ArrayList<String>()
    private val mFragmentData = ArrayList<MutableList<UserInfo>>()

    fun addFragment(fragment: Fragment, title: String, userInfo: MutableList<UserInfo>) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
        mFragmentData.add(userInfo)
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{ ActiveFragment.newInstance(mFragmentData[position]) }
            1->{ DeactiveFragment.newInstance(mFragmentData[position]) }
            else->{throw Resources.NotFoundException("Resource not found")           }
        }
    }

    override fun getItemCount(): Int {
        return mFragmentList.size
    }
}

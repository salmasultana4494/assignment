package com.example.frindshipassignment.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.frindshipassignment.R
import com.example.frindshipassignment.databinding.HomeFragmentBinding
import com.example.frindshipassignment.model.UserInfo
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment: Fragment() {
    private var binding: HomeFragmentBinding? = null
    private var adapter: ViewpagerAdapter? = null

    lateinit var viewModel: HomeViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding?.root ?: HomeFragmentBinding.inflate(inflater).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        initObserver()
        initClickListener()

    }

    private fun initClickListener() {
        binding?.createUserButton?.setOnClickListener{
            val bundle = Bundle().apply {
                putBoolean("isCreate", true)
            }
            findNavController().navigate(R.id.action_homeFragment_to_createAndUpdateFragment, bundle)
        }
    }

    private fun initObserver() {
        viewModel.getUsers().observe(viewLifecycleOwner, Observer {
            Log.d("TAG", "initObserver: $it")
            if (!it.isNullOrEmpty()) {
                initView(it)
            }
        })
    }

    private fun initView(userInfo: MutableList<UserInfo>) {
        binding?.viewPager?.let { setupViewPager(it,userInfo) }


        binding?.tabLayout?.let {
            binding?.viewPager?.let { it1 ->
                TabLayoutMediator(it, it1){ tab, index ->
                    tab.text = adapter?.mFragmentTitleList?.get(index)
                }.attach()
            }
        }
        for (i in 0 until binding?.tabLayout?.tabCount!!) {
            val tv = LayoutInflater.from(activity).inflate(R.layout.custom_tab, null) as TextView
            binding?.tabLayout?.getTabAt(i)?.customView = tv
        }
    }
    private fun setupViewPager(viewPager: ViewPager2, userInfo: MutableList<UserInfo>) {
        adapter = activity?.supportFragmentManager?.let { activity?.lifecycle?.let { it1 ->
            ViewpagerAdapter(it,
                it1
            )
        } }

        var activeUser: MutableList<UserInfo> = userInfo.filter { it.status == "active" } as MutableList<UserInfo>
        var inactiveUser: MutableList<UserInfo> = userInfo.filter { it.status == "inactive" } as MutableList<UserInfo>

        adapter?.addFragment(ActiveFragment(), "Active", activeUser)
        adapter?.addFragment(DeactiveFragment(), "Deactivate", inactiveUser)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1
    }

    override fun onResume() {
        super.onResume()
    }
}
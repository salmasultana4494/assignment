package com.example.frindshipassignment.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frindshipassignment.databinding.FragmentInactiveBinding
import com.example.frindshipassignment.model.UserInfo
import java.io.Serializable

class DeactiveFragment() : Fragment() {

    private var binding: FragmentInactiveBinding? = null
    private var myData: MutableList<UserInfo>? = null

    companion object {
        fun newInstance(myData: MutableList<UserInfo>): DeactiveFragment {
            val fragment = DeactiveFragment()
            val bundle = Bundle().apply {
                putSerializable("dataList", myData as Serializable)
            }
            fragment.arguments = bundle
            return fragment
        }
        private const val ARG_MY_DATA = "dataList"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding?.root ?: FragmentInactiveBinding.inflate(inflater).also {
            binding = it
        }.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            myData = it.getSerializable(ARG_MY_DATA) as? MutableList<UserInfo>
        }
        initAdapter()
    }
    private fun initAdapter() {
        binding?.deactivateRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = UserAdapter(requireContext(), myData ?: mutableListOf(), 1)
        }
    }
}
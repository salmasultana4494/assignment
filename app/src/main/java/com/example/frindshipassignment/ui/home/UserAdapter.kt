package com.example.frindshipassignment.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.frindshipassignment.R
import com.example.frindshipassignment.databinding.ItemActiveBinding
import com.example.frindshipassignment.databinding.ItemInactiveBinding
import com.example.frindshipassignment.model.UserInfo
import java.io.Serializable

class UserAdapter(
    var context: Context,
    var dataList: MutableList<UserInfo>?,
    var viewType: Int,
    private val navController: NavController
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewType1 = viewType
    var onItemClick: ((model: UserInfo, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType1){
            1->{
                val binding: ItemActiveBinding = ItemActiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolder(binding)
            }
            0-> {
                val binding: ItemInactiveBinding = ItemInactiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolder2(binding)
            }
            else ->{ throw IllegalArgumentException("Invalid view type") }
        }

    }

    override fun getItemCount(): Int = dataList?.size!!

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ViewHolder) {
            val model = dataList?.get(position)
            val binding = holder.binding
            binding.nameTV.text = model?.name
            binding.genderTV.text = model?.gender
            binding.emailTV.text = model?.email
            binding.textView.text = model?.name?.first().toString()
            binding.editIV.setOnClickListener {
                val bundle = Bundle().apply {
                    putBoolean("isCreate", false)
                    putSerializable("dataList", model as Serializable)
                }
                navController.navigate(R.id.action_homeFragment_to_createAndUpdateFragment, bundle)
            }
        }else if (holder is ViewHolder2){
            val model = dataList?.get(position)
            val binding = holder.binding

            binding.nameTV.text = model?.name
            binding.genderTV.text = model?.gender
            binding.emailTV.text = model?.email
            binding.textView.text = model?.name?.first().toString()
            binding.editIV.setOnClickListener {
                val bundle = Bundle().apply {
                    putBoolean("isCreate", false)
                    putSerializable("dataList", model as Serializable)
                }
                navController.navigate(R.id.action_homeFragment_to_createAndUpdateFragment, bundle)
            }
        }

    }

    inner class ViewHolder(val binding: ItemActiveBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.editIV.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick?.invoke(dataList!![adapterPosition], adapterPosition)
                }
            }
        }
    }
    inner class ViewHolder2(val binding: ItemInactiveBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.emailTV.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick?.invoke(dataList!![adapterPosition], adapterPosition)
                }
            }
        }
    }

}



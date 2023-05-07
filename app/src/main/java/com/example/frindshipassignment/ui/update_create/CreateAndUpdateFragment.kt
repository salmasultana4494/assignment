package com.example.frindshipassignment.ui.update_create

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.frindshipassignment.CustomSpinnerAdapter
import com.example.frindshipassignment.R
import com.example.frindshipassignment.databinding.FragmentCreateAndUpdateBinding
import com.example.frindshipassignment.databinding.HomeFragmentBinding
import com.example.frindshipassignment.model.RequestBody
import com.example.frindshipassignment.ui.home.HomeViewModel
import kotlin.math.log

class CreateAndUpdateFragment : Fragment() {
    var binding: FragmentCreateAndUpdateBinding? = null
    private var gender = mutableListOf("male","female")
    lateinit var viewModel: HomeViewModel
    var genderRes = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding?.root ?: FragmentCreateAndUpdateBinding.inflate(inflater).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.my_toolbar)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        toolbar.title = "create"
        initClickListener()
    }

    private fun initClickListener() {
        binding?.genderDD?.let { viewSpinner(it,gender,"") }
        var status = ""

        binding?.status?.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                // Switch is on
                status = "active"
            } else {
                // Switch is off
                status = "inactive"
            }
        }
        binding?.saveSubmitBTN?.setOnClickListener {
            var name = binding?.nameET?.text.toString()
            var email = binding?.emailET?.text.toString()
            var requestBody = RequestBody(
                email,genderRes,name,status
            )
            Log.d("TAG", "initClickListener: $requestBody")

            requestBody?.let { it1 ->
                viewModel.createUser(it1).observe(viewLifecycleOwner, Observer {
                Log.d("TAG", "initClickListener: $it")
                findNavController().navigate(R.id.homeFragment)
            }) }
        }

    }

    private fun viewSpinner(viewSpinner: AppCompatSpinner, itemsList :MutableList<String> = mutableListOf(), preselectedItem: String) {
        val spinnerAdapter = CustomSpinnerAdapter(requireContext(), R.layout.item_view_spinner_item, itemsList)
        viewSpinner.adapter = spinnerAdapter

        /*if (hasPreviouslySelected){
            indexOfSelectedItem = itemsList.indexOf(preselectedItem)
            viewSpinner.setSelection(indexOfSelectedItem)
        }else{
            viewSpinner.setSelection(0)
        }*/

        viewSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                genderRes = itemsList[position]

            }
        }
    }
}
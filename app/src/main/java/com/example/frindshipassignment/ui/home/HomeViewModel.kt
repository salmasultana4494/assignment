package com.example.frindshipassignment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frindshipassignment.model.RequestBody
import com.example.frindshipassignment.model.UserInfo
import com.example.frindshipassignment.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ProjectRepository): ViewModel() {

    fun getUsers(): LiveData<MutableList<UserInfo>> {
        val responseBody = MutableLiveData<MutableList<UserInfo>>()

        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getUsers()
            val resCode = response.code()
            withContext(Dispatchers.Main) {
                when (resCode) {
                     200 -> {
                        responseBody.value = response.body() as MutableList<UserInfo>
                    }
                }
            }
        }

        return responseBody
    }
    fun createUser(requestBody: RequestBody): LiveData<UserInfo> {
        val responseBody = MutableLiveData<UserInfo>()

        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.createUser(requestBody)
            val resCode = response.code()
            withContext(Dispatchers.Main) {
                when (resCode) {
                     201 -> {
                        responseBody.value = response.body()
                    }
                    200 -> {
                        responseBody.value = response.body()
                    }
                }
            }
        }

        return responseBody
    }
}
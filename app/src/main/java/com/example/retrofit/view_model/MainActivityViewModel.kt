package com.example.retrofit.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit.retrofit.RetrofitInstance
import com.example.retrofit.retrofit.RetrofitService
import com.example.retrofit.model.UserList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    var recyclerListData: MutableLiveData<UserList> = MutableLiveData()

    fun getUserListObservable(): MutableLiveData<UserList> {
        return recyclerListData
    }

    fun getUsersList() {
        RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java).getUsersList()
            .enqueue(object : Callback<UserList> {
                override fun onFailure(call: Call<UserList>, t: Throwable) {
                    recyclerListData.postValue(null)
                }

                override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                    if (response.isSuccessful) {
                        recyclerListData.postValue(response.body())
                    } else {
                        recyclerListData.postValue(null)
                    }
                }
            })
    }

    fun searchUser(searchText: String) {
        RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
            .searchUsers(searchText)
            .enqueue(object : Callback<UserList> {
                override fun onFailure(call: Call<UserList>, t: Throwable) {
                    recyclerListData.postValue(null)
                }

                override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                    if (response.isSuccessful) {
                        recyclerListData.postValue(response.body())
                    } else {
                        recyclerListData.postValue(null)
                    }
                }
            })
    }
}
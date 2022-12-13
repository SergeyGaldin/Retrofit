package com.example.retrofit.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit.model.User
import com.example.retrofit.model.UserList
import com.example.retrofit.model.UserResponse
import com.example.retrofit.retrofit.RetrofitInstance
import com.example.retrofit.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateViewModel : ViewModel() {
    private val createLiveData: MutableLiveData<UserResponse?> = MutableLiveData()
    private val loadUserData: MutableLiveData<UserResponse?> = MutableLiveData()
    private val deleteUserLiveData: MutableLiveData<UserResponse?> = MutableLiveData()

    fun getCreateUserObservable(): MutableLiveData<UserResponse?> {
        return createLiveData
    }

    fun getDeleteUserObservable(): MutableLiveData<UserResponse?> {
        return deleteUserLiveData
    }

    fun getLoadUserObservable(): MutableLiveData<UserResponse?> {
        return loadUserData
    }

    fun createUser(user: User) {
        RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java).createUser(user)
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    createLiveData.postValue(null)
                }

                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        createLiveData.postValue(response.body())
                    } else {
                        createLiveData.postValue(null)
                    }
                }
            })
    }

    fun getUserData(user_id: String?) {
        val retroInstance =
            RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        val call = retroInstance.getUser(user_id!!)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                loadUserData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if (response.isSuccessful) {
                    loadUserData.postValue(response.body())
                } else {
                    loadUserData.postValue(null)
                }
            }
        })
    }

    fun updateUser(user_id: String, user: User) {
        RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
            .updateUser(user_id, user)
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    createLiveData.postValue(null)
                }

                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        createLiveData.postValue(response.body())
                    } else {
                        createLiveData.postValue(null)
                    }
                }
            })
    }

    fun deleteUser(user_id: String?) {
        RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
            .deleteUser(user_id!!)
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    deleteUserLiveData.postValue(null)
                }

                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        deleteUserLiveData.postValue(response.body())
                    } else {
                        deleteUserLiveData.postValue(null)
                    }
                }
            })
    }
}
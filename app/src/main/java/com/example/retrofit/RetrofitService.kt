package com.example.retrofit

import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUsersList(): Call<UserList>

    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun searchUsers(@Query("name") searchText: String): Call<UserList>

    @GET("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUser(@Part("user_id") user_id: String): Call<UserList>

    @POST("users")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
        "Authorization: Bearer 3c8f9d0f52951e7eec0cd9e5b5e904709a61a48834f46476424bb990539bc391"
    )
    fun createUser(@Body params: User): Call<UserResponse>

    @PATCH("users/{user_id}")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
        "Authorization: Bearer 3c8f9d0f52951e7eec0cd9e5b5e904709a61a48834f46476424bb990539bc391"
    )
    fun updateUser(@Part("user_id") user_id: String, @Body params: User): Call<UserResponse>

    @DELETE("users/{user_id}")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
        "Authorization: Bearer 3c8f9d0f52951e7eec0cd9e5b5e904709a61a48834f46476424bb990539bc391"
    )
    fun deleteUser(@Part("user_id") user_id: String): Call<UserResponse>
}
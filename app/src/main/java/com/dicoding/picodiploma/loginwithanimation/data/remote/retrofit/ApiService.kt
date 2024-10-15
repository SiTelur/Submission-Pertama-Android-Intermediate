package com.dicoding.picodiploma.loginwithanimation.data.remote.retrofit

import com.dicoding.picodiploma.loginwithanimation.data.remote.response.LoginResponse
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.RegisterResponse
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.StoriesResponse
import retrofit2.http.*


interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun registerNew(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun logIn(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @GET("stories")
    suspend fun getStories(): StoriesResponse

}
package com.dicoding.picodiploma.loginwithanimation.data.remote.retrofit

import com.dicoding.picodiploma.loginwithanimation.data.remote.response.LoginRequestBody
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.LoginResponse
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.RegisterRequestBody
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.RegisterResponse
import retrofit2.http.POST


interface ApiService {
    @POST
    suspend fun registerNew(registerRequestBody: RegisterRequestBody) : RegisterResponse

    @POST
    suspend fun logIn(loginRequestBody: LoginRequestBody) : LoginResponse
}
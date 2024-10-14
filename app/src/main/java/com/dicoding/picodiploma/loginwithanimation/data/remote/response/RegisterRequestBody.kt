package com.dicoding.picodiploma.loginwithanimation.data.remote.response

import retrofit2.http.Field

data class RegisterRequestBody(
    @Field("name") val name:String,
    @Field("email") val email: String,
    @Field("password") val password: String
)
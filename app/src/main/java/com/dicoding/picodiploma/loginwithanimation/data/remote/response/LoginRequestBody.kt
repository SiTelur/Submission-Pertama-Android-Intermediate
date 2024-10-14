package com.dicoding.picodiploma.loginwithanimation.data.remote.response

import retrofit2.http.Field

class LoginRequestBody(
    @Field("email") val email: String,
    @Field("password") val password: String,
)
package com.dicoding.picodiploma.loginwithanimation.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.dicoding.picodiploma.loginwithanimation.data.pref.UserModel
import com.dicoding.picodiploma.loginwithanimation.data.pref.UserPreference
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.ErrorResponse
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.LoginResponse
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.RegisterResponse
import com.dicoding.picodiploma.loginwithanimation.data.remote.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.map
import retrofit2.HttpException

class StoryRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {

    fun registerStory(name:String,email:String,password:String) : LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
       try {
           val respnse = apiService.registerNew(name = name,email = email,password = password)
           emit(Result.Success(respnse))
        }catch (e: HttpException) {
           val jsonInString = e.response()?.errorBody()?.string()
           val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
           val errorMessage = errorBody.message
           emit(Result.Error(errorMessage.toString()))
        }
    }

    fun loginStory(email:String,password: String) :LiveData<Result<UserModel>> = liveData {
        emit(Result.Loading)

        try {
            val response = apiService.logIn(email,password).loginResult
            userPreference.saveSession(UserModel(email,response.token,true))
        }catch (e : HttpException){
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
        val data : LiveData<Result<UserModel>> =   userPreference.getSession().asLiveData().map {
            Result.Success(it)
        }
        emitSource(data)
    }

    companion object {
        const val TAG = "StoreRepository"
        @Volatile
        private var instance: StoryRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): StoryRepository =
            instance ?: synchronized(this) {
                instance ?: StoryRepository(apiService,userPreference)
            }.also { instance = it }
    }
}
package com.dicoding.picodiploma.loginwithanimation.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.picodiploma.loginwithanimation.data.pref.UserPreference
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.RegisterResponse
import com.dicoding.picodiploma.loginwithanimation.data.remote.retrofit.ApiConfig
import com.dicoding.picodiploma.loginwithanimation.data.remote.retrofit.ApiService

class StoryRepository private constructor(
    private val apiService: ApiService
) {

    fun registerStory(name:String,email:String,password:String) : LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
       try {
            val service = apiService.registerNew(name = name,email = email,password = password)
            emit(Result.Success(service))
        }catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        const val TAG = "StoreRepository"
        @Volatile
        private var instance: StoryRepository? = null
        fun getInstance(
            apiService: ApiService
        ): StoryRepository =
            instance ?: synchronized(this) {
                instance ?: StoryRepository(apiService)
            }.also { instance = it }
    }
}
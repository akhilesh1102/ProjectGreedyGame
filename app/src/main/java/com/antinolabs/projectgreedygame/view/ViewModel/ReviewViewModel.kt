package com.antinolabs.projectgreedygame.view.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.antinolabs.projectgreedygame.model.Cast
import com.antinolabs.projectgreedygame.model.Review
import com.antinolabs.projectgreedygame.utils.ApiResponse
import com.antinolabs.projectgreedygame.utils.Retrofithit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ReviewViewModel(application: Application) : AndroidViewModel(application) {
    var prodId=-1


    fun ReviewMovies() : LiveData<ApiResponse<Any>> {
        val mutableLiveData = MutableLiveData<ApiResponse<Any>>()
        viewModelScope.launch {
            Retrofithit().getReviewMovies(id = prodId)
                .catch { e ->
                    var errorResponse: Review? = null
                    when (e) {
                        is HttpException -> {
                            val gson = Gson()
                            val type = object : TypeToken<Review>() {}.type
                            errorResponse = gson.fromJson(
                                e.response()?.errorBody()!!.charStream(), type
                            )
                        }
                    }
                    mutableLiveData.value = errorResponse.let { ApiResponse.Error(e.message, it) }
                }.collect { data ->
                    mutableLiveData.value = data.let {
                        ApiResponse.Success(it)
                    }
                }
        }
        return mutableLiveData
    }
}
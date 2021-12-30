package com.antinolabs.projectgreedygame.view.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.antinolabs.projectgreedygame.model.MovieDetail
import com.antinolabs.projectgreedygame.model.PopularMovie
import com.antinolabs.projectgreedygame.model.SimilarMovie
import com.antinolabs.projectgreedygame.utils.ApiResponse
import com.antinolabs.projectgreedygame.utils.Retrofithit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    var prodId=-1

    fun productItembyid() : LiveData<ApiResponse<Any>> {
        val mutableLiveData = MutableLiveData<ApiResponse<Any>>()
        viewModelScope.launch {
            Retrofithit().getMoviesByid(id = prodId)
                .catch { e ->
                    var errorResponse: MovieDetail? = null
                    when (e) {
                        is HttpException -> {
                            val gson = Gson()
                            val type = object : TypeToken<MovieDetail>() {}.type
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

    fun similarMovies() : LiveData<ApiResponse<Any>> {
        val mutableLiveData = MutableLiveData<ApiResponse<Any>>()
        viewModelScope.launch {
            Retrofithit().getSimilarMovies(id = prodId)
                .catch { e ->
                    var errorResponse: SimilarMovie? = null
                    when (e) {
                        is HttpException -> {
                            val gson = Gson()
                            val type = object : TypeToken<SimilarMovie>() {}.type
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
package com.antinolabs.projectgreedygame.view.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.antinolabs.projectgreedygame.model.NowPlaying
import com.antinolabs.projectgreedygame.model.PopularMovie
import com.antinolabs.projectgreedygame.utils.ApiResponse
import com.antinolabs.projectgreedygame.utils.Retrofithit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    var popularMoviePage = 1

    fun productItembyid() : LiveData<ApiResponse<Any>> {
        val mutableLiveData = MutableLiveData<ApiResponse<Any>>()
        viewModelScope.launch {
            Retrofithit().getPopularMovies(page = popularMoviePage)
                .catch { e ->
                    var errorResponse: PopularMovie? = null
                    when (e) {
                        is HttpException -> {
                            val gson = Gson()
                            val type = object : TypeToken<PopularMovie>() {}.type
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

    fun NowPlaying() : LiveData<ApiResponse<Any>> {
        val mutableLiveData = MutableLiveData<ApiResponse<Any>>()
        viewModelScope.launch {
            Retrofithit().getNowPlayingMovies(page = 1)
                .catch { e ->
                    var errorResponse: NowPlaying? = null
                    when (e) {
                        is HttpException -> {
                            val gson = Gson()
                            val type = object : TypeToken<NowPlaying>() {}.type
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


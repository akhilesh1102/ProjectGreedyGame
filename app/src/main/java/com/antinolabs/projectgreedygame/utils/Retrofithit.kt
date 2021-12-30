package com.antinolabs.projectgreedygame.utils

import com.antinolabs.projectgreedygame.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Retrofithit {
    fun getPopularMovies(page: Int): Flow<PopularMovie> = flow {
        val response =
            RetrofitClient().retrofitApiSerwithoutInterceptor.getPopularMovies(api_Key = Constant.api_Key, language = Constant.Lang, page = page)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getMoviesByid(id: Int): Flow<MovieDetail> = flow {
        val response =
            RetrofitClient().retrofitApiSerwithoutInterceptor.getMoviesId(api_Key = Constant.api_Key, language = Constant.Lang, id = id)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getSimilarMovies(id: Int): Flow<SimilarMovie> = flow {
        val response =
            RetrofitClient().retrofitApiSerwithoutInterceptor.getSimilarMovies(api_Key = Constant.api_Key, language = Constant.Lang, id = id)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getCreditMovies(id: Int): Flow<Cast> = flow {
        val response =
            RetrofitClient().retrofitApiSerwithoutInterceptor.getCreditMovies(api_Key = Constant.api_Key, language = Constant.Lang, id = id)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getReviewMovies(id: Int): Flow<Review> = flow {
        val response =
            RetrofitClient().retrofitApiSerwithoutInterceptor.getReviewMovies(api_Key = Constant.api_Key, language = Constant.Lang, id = id)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getNowPlayingMovies(page: Int): Flow<NowPlaying> = flow {
        val response =
            RetrofitClient().retrofitApiSerwithoutInterceptor.getNowPlayigMovies(api_Key = Constant.api_Key, language = Constant.Lang, page = page)
        emit(response)
    }.flowOn(Dispatchers.IO)
}
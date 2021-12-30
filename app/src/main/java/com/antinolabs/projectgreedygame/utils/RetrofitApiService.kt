package com.antinolabs.projectgreedygame.utils

import com.antinolabs.projectgreedygame.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApiService {
    @GET(Constant.apiend_PopularMovies)
    suspend fun getPopularMovies(
        @Query("api_key") api_Key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): PopularMovie

    @GET(Constant.apiend_PopularMoviesId)
    suspend fun getMoviesId(
        @Path("id") id: Int,
        @Query("api_key") api_Key: String,
        @Query("language") language: String
    ): MovieDetail

    @GET(Constant.apiend_SimilarMoviesId)
    suspend fun getSimilarMovies(
        @Path("id") id: Int,
        @Query("api_key") api_Key: String,
        @Query("language") language: String
    ): SimilarMovie

    @GET(Constant.apiend_CreditMoviesId)
    suspend fun getCreditMovies(
        @Path("id") id: Int,
        @Query("api_key") api_Key: String,
        @Query("language") language: String
    ): Cast

    @GET(Constant.apiend_ReviewMoviesId)
    suspend fun getReviewMovies(
        @Path("id") id: Int,
        @Query("api_key") api_Key: String,
        @Query("language") language: String
    ): Review

    @GET(Constant.apiend_NowPlayingMoviesId)
    suspend fun getNowPlayigMovies(
        @Query("page") page: Int,
        @Query("api_key") api_Key: String,
        @Query("language") language: String
    ): NowPlaying
}
package com.antinolabs.projectgreedygame.model

data class Review(
    val id: Int,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int,
    val status_code: Int,
    val status_message: String,
    val success: Boolean
){
    class Result(
        val author: String,
        val author_details: AuthorDetails,
        val content: String,
        val created_at: String,
        val id: String,
        val updated_at: String,
        val url: String
    ){
        class AuthorDetails(
            val avatar_path: String,
            val name: String,
            val rating: Float?,
            val username: String
        )
    }
}
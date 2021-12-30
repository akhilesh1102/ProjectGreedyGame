package com.antinolabs.projectgreedygame.model

data class Cast(
    val cast: List<CastX>,
    val crew: List<Crew>,
    val id: Int,
    val status_code: Int,
    val status_message: String,
    val success: Boolean
){
    class CastX(
        val adult: Boolean,
        val cast_id: Int,
        val character: String,
        val credit_id: String,
        val gender: Int,
        val id: Int,
        val known_for_department: String,
        val name: String,
        val order: Int,
        val original_name: String,
        val popularity: Double,
        val profile_path: String?
    )
    class Crew(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?
    )
}
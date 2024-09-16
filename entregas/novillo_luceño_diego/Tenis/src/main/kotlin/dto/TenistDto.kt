package org.example.tenist.dto

import kotlinx.serialization.Serializable

@Serializable
class TenistDto(
    val id : Int,
    val name : String,
    val country : String,
    var weight : Int,
    val height : Double,
    val dominantHand : String,
    val points : Int,
    val birthDate : String,
    val createdAt : String,
    val updatedAt : String
)
package org.example.tenist.models

import java.time.LocalDate
import java.time.LocalDateTime

data class Tenist (
    val id : Int,
    val name : String,
    val country : String,
    var weight : Int,
    val height : Double,
    val dominantHand : Dexterity?,
    val points : Int,
    val birthDate : LocalDate,
    val createdAt : LocalDateTime = LocalDateTime.now(),
    val updatedAt : LocalDateTime = LocalDateTime.now()
)

enum class Dexterity {
    RIGHTHANDED, LEFTHANDED, AMBIDEXTROUS
}

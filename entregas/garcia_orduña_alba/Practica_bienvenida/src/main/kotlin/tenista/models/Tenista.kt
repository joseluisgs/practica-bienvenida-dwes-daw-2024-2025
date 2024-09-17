package org.example.persona.models

import java.time.LocalDateTime

class Tenista(
   val id: Int,
   val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Int,
    val puntos: Int,
    val mano:ManoTenista,
    val fecha_nacimiento: LocalDateTime,
    val created_at: LocalDateTime,
    val updated_at: LocalDateTime,

) {
}
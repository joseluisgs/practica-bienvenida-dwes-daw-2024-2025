package org.example.mappers
import java.time.LocalDate

/*Clase con funciones de extensión para transformar los datos de la clase DTO al modelo y viceversa
 */

data class TenistaDto(
    val id: Long,
    val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Int,
    val mano: String,
    val puntos: Int,
    val createdAt: String,
    val updatedAt: String?,
    val isDeleted: Boolean
)

/*
data class Tenista(
    val id: Long,
    val nombre: String,
    val ranking: Int,
    val pais: String,
    val edad: Int,
    val mano: String,
    val puntos: Int,
    val createdAt: LocalDate,
    val updatedAt: LocalDate?,
    val isDeleted: Boolean
)

fun TenistaDto.toTenista(): Tenista {
    return Tenista(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        mano = this.mano,
        puntos = this.puntos,
        createdAt = LocalDate.parse(this.createdAt),
        updatedAt = LocalDate.parse(this.createdAt)
    )
}

fun Tenista.toTenistaDto(): TenistaDto {
    return TenistaDto(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        mano = this.mano,
        puntos = this.puntos,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt?.toString()
    )
}

*/
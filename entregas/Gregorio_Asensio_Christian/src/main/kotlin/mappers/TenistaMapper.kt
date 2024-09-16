package org.example.mappers
import models.Tenista
import org.example.dto.TenistaDto

// Extensión para convertir de TenistaDto a Tenista

/*
fun TenistaDto.toModel(): Tenista {
    return Tenista(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura,
        peso = this.peso,
        mano = this.mano,
        puntos = this.puntos,
        createdAt = LocalDate.parse(this.createdAt),
        updatedAt = LocalDate.parse(this.createdAt)
    )
}

// Extensión para convertir de Tenista a TenistaDto
fun Tenista.toDto(): TenistaDto {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    return TenistaDto(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura,
        peso = this.peso,
        mano = this.mano,
        puntos = this.puntos,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt?.toString()
    )
}
*/

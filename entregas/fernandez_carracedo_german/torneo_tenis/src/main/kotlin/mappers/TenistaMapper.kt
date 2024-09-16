package org.example.mappers

import database.TenistaEntity
import org.example.dto.TenistaDto
import org.example.models.Mano
import org.example.models.Tenista
import java.time.LocalDate
import java.time.LocalDateTime

fun Tenista.toTenistaDto(): TenistaDto {
    return TenistaDto(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura,
        peso = this.peso,
        puntos = this.puntos,
        mano = this.mano.name,
        fechaNacimiento = this.fechaNacimiento.toString(),
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString()
    )
}

fun TenistaDto.toTenista(): Tenista {
    return Tenista(
        id=this.id,
        nombre = this.nombre,
        pais= this.pais,
        altura = this.altura.toInt(),
        peso = this.peso.toInt(),
        puntos = this.puntos,
        mano = Mano.valueOf(this.mano),
        fechaNacimiento = LocalDate.parse(this.fechaNacimiento),
        createdAt = LocalDate.parse( this.createdAt),
        updatedAt = LocalDate.parse( this.updatedAt)
    )
}

fun TenistaEntity.toTenista():Tenista{
    return Tenista(
        id=this.id,
        nombre = this.nombre,
        pais= this.pais,
        altura = this.altura.toInt(),
        peso = this.peso.toInt(),
        puntos = this.puntos.toInt(),
        mano = Mano.valueOf(this.mano),
        fechaNacimiento = LocalDate.parse(this.fechaNacimiento.toString()),
        createdAt = LocalDate.parse(this.created_at),
        updatedAt = LocalDate.parse(this.updated_at)
    )
}

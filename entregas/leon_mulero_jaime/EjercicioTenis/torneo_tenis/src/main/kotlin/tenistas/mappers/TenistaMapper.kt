package org.example.tenistas.mappers

import database.TenistaEntity
import org.example.tenistas.dto.TenistaDto
import org.example.tenistas.dto.TenistaDtoRank
import org.example.tenistas.models.ManoTenista
import org.example.tenistas.models.Tenista
import java.time.LocalDate
import java.time.LocalDateTime

fun TenistaDto.toTenista(): Tenista {
    return Tenista(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura,
        peso = this.peso,
        puntos = this.puntos,
        mano = ManoTenista.valueOf(this.mano.uppercase()),
        fechaNacimiento = LocalDate.parse(this.fechaNacimiento),
        createdAt = LocalDateTime.parse(this.createdAt),
        updatedAt = LocalDateTime.parse(this.updatedAt)
    )
}

fun Tenista.toTenistaDto(): TenistaDto {
    return TenistaDto(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura,
        peso = this.peso,
        puntos = this.puntos,
        mano = this.mano.toString(),
        fechaNacimiento = this.fechaNacimiento.toString(),
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}

fun TenistaDto.toTenistaDtoRanking(ranking: Int): TenistaDtoRank {
    return TenistaDtoRank(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura,
        peso = this.peso,
        puntos = this.puntos,
        mano = this.mano,
        fechaNacimiento = this.fechaNacimiento,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        ranking = ranking
    )
}

fun TenistaEntity.toTenista(): Tenista {
    return Tenista(
        id = this.id.toInt(),
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura.toInt(),
        peso = this.peso.toInt(),
        puntos = this.puntos.toInt(),
        mano = ManoTenista.valueOf(this.mano.uppercase()),
        fechaNacimiento = LocalDate.parse(this.fecha_nacimiento),
        createdAt = LocalDateTime.parse(this.created_at),
        updatedAt = LocalDateTime.parse(this.updated_at)
    )
}

fun List<Tenista>.toListTenistaDto(): List<TenistaDto> {
    return this.map { it.toTenistaDto() }
}

fun List<TenistaDto>.toListTenista(): List<Tenista> {
    return this.map { it.toTenista() }
}
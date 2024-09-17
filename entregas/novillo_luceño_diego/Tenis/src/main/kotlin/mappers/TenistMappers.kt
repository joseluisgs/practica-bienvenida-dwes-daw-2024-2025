package org.example.tenist.mappers

import database.TenistEntity
import org.example.tenist.dto.TenistDto
import org.example.tenist.models.Dexterity
import org.example.tenist.models.Tenist
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Toma un objeto TenistEntity y devuelve uno Tenist
 */
fun TenistEntity.toTenist() : Tenist {
    return Tenist(
        id = this.id.toInt(),
        name = this.name,
        age = this.age.toInt(),
        country =  this.country,
        weight = this.weight.toInt(),
        height = this.height,
        dominantHand = findDexteriry(this.dominantHand),
        points = this.points.toInt(),
        birthDate = LocalDate.parse(this.birthDate),
        createdAt = LocalDateTime.parse(this.createdAt),
        updatedAt = LocalDateTime.parse(this.updatedAt)
    )
}

/**
 * Toma un objeto Tenist y devuelve uno TenistDto
 */
fun Tenist.toTenistDto() : TenistDto{
    return TenistDto(
        id = this.id.toInt(),
        name = this.name,
        age = this.age.toInt(),
        country =  this.country,
        weight = this.weight.toInt(),
        height = this.height,
        dominantHand = this.dominantHand!!.name,
        points = this.points.toInt(),
        birthDate = this.birthDate.toString(),
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString()
    )
}

/**
 * Toma un objeto TenistEntity y devuelve uno Tenist
 */
fun TenistDto.toTenist() : Tenist{
    return Tenist(
        id = this.id,
        name = this.name,
        age = this.age,
        country =  this.country,
        weight = this.weight,
        height = this.height,
        dominantHand = findDexteriry(this.dominantHand),
        points = this.points.toInt(),
        birthDate = LocalDate.parse(this.birthDate),
        createdAt = LocalDateTime.parse(this.createdAt),
        updatedAt = LocalDateTime.parse(this.updatedAt)
    )
}
/**
 * Devuelve el tipo de dexteridad dependiendo del input
 * @param input en lo que se basará la funcion para determinar que dexteridad tiene
 * @return null si el input no es valido y una clase Dexterity si es valido
 */
fun findDexteriry(input : String) : Dexterity? {
    return when (input) {
        "RIGHTHANDED" -> Dexterity.RIGHTHANDED
        "LEFTHANDED" -> Dexterity.LEFTHANDED
        "AMBIDEXTROUS" -> Dexterity.AMBIDEXTROUS
        else -> null
    }
}
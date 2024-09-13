package tenistas.mappers

import database.TenistaEntity
import tenistas.dto.TenistaDto
import tenistas.models.MANO
import tenistas.models.Tenista
import java.time.LocalDate

fun TenistaEntity.toTenista():Tenista{
    val mano=when(mano){
        "ZURDO"->MANO.ZURDO
        "DIESTRO"->MANO.DIESTRO
        else -> null
    }
    return Tenista(
        id = id,
        nombre = nombre,
        pais = pais,
        altura = altura.toInt(),
        peso = peso.toInt(),
        puntos = puntos.toInt(),
        fechaNacimiento = LocalDate.parse(fecha_nacimiento),
        created_at = created_at,
        updated_at = updated_at,
        mano = mano!!
    )
}

fun Tenista.toDto(): TenistaDto {
    return TenistaDto(
        id = id.toString(),
        nombre = nombre,
        pais = pais,
        altura = altura,
        peso = peso,
        puntos = puntos,
        mano = mano.toString(),
        fechaNacimiento = fechaNacimiento.toString(),
        created_at = created_at,
        updated_at = updated_at
    )
}

fun TenistaDto.toEntity(): Tenista {
    val mano=when(mano){
        "ZURDO"->MANO.ZURDO
        "DIESTRO"->MANO.DIESTRO
        else -> null
    }
    return Tenista(
        id = id.toLong(),
        nombre = nombre,
        pais = pais,
        altura = altura,
        peso = peso,
        puntos = puntos,
        mano = mano!!,
        fechaNacimiento = LocalDate.parse(fechaNacimiento),
        created_at = created_at,
        updated_at = updated_at
    )
}
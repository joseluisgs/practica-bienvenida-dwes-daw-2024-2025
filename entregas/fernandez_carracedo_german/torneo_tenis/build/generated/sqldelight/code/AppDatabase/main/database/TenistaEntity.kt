package database

import kotlin.Long
import kotlin.String

public data class TenistaEntity(
  public val id: Long,
  public val nombre: String,
  public val pais: String,
  public val altura: Long,
  public val peso: Long,
  public val puntos: Long,
  public val mano: String,
  public val fechaNacimiento: String?,
  public val created_at: String,
  public val updated_at: String,
)

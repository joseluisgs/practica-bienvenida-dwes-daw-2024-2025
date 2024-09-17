package database

import kotlin.Long
import kotlin.String

public data class Tenistas_Entity(
  public val id: Long,
  public val nombre: String,
  public val pais: String,
  public val altura: Long,
  public val peso: Long,
  public val puntos: Long,
  public val mano: String,
  public val fecha_nacimiento: String,
  public val created_at: String,
  public val upadated_at: String,
)

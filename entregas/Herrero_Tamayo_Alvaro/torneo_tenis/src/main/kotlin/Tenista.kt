import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import org.simpleframework.xml.Element



@Root(name = "tenista")
data class Tenista(
    @field:Element(name = "id") var id: Int = 0,
    @field:Element(name = "nombre") var nombre: String = "",
    @field:Element(name = "pais") var pais: String = "",
    @field:Element(name = "altura") var altura: Int = 0,
    @field:Element(name = "peso") var peso: Int = 0,
    @field:Element(name = "puntos") var puntos: Int = 0,
    @field:Element(name = "mano") var mano: String = "",
    @field:Element(name = "fechaNacimiento") var fechaNacimiento: String = ""
)

@Root(name = "tenistas")
data class TenistasWrapper(
    @field:ElementList(name = "tenistas", inline = true) var tenistas: List<Tenista> = listOf()
)
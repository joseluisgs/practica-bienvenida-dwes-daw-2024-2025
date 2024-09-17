/**
 * Clase que proporciona métodos para guardar una lista de tenistas en formato XML.
 *
 * Utiliza la biblioteca `xmlutil` para serializar los datos en formato XML.
 */
class StorageXml {

    /**
     * Guarda una lista de tenistas en un archivo XML.
     *
     * @param list Lista de objetos [Tenista] que se guardarán en el archivo.
     * @param file Archivo en el que se almacenarán los datos en formato XML.
     * @return Un [Result] que contiene el número de tenistas guardados (como [Long]) en caso de éxito,
     * o un [TenistaError] en caso de fallo (por ejemplo, si hay un problema al escribir el archivo).
     */
    fun save(list: List<Tenista>, file: File): Result<Long, TenistaError> {
        logger.debug { "Guardando en fichero xml: $file" }
        return try {
            // Configurar el serializador XML
            val xml = XML { indent = 4 } // Configura la indentación para una mejor legibilidad
            // Convertir la lista de tenistas a XML y escribirla en el archivo
            file.writeText(xml.encodeToString<List<Tenista>>(list))
            Ok(list.size.toLong())
        } catch (e: Exception) {
            logger.debug { "No se ha guardado el fichero" }
            Err(TenistaError.StorageTenistaError("No se ha podido guardar el fichero: ${e.message}"))
        }
    }
}
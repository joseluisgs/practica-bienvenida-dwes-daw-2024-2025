package storage

import com.github.michaelbull.result.Result
import tenistas.errors.TenistaError
import tenistas.models.Tenista
import java.io.File

/**
 * Interfaz genérica para la gestión de almacenamiento de objetos de tipo [T].
 *
 * @param T Tipo genérico de los objetos que serán gestionados por las implementaciones de esta interfaz.
 * @author Natalia González
 * @version 1.0
 */
interface Storage<T> {

    /**
     * Carga una lista de objetos de tipo [Tenista] desde un archivo proporcionado.
     *
     * @param file Archivo desde el cual se leerán los datos.
     * @return Un [Result] que contiene una lista de objetos [Tenista] en caso de éxito, o un [TenistaError] en caso de fallo.
     */
    fun load(file: File): Result<List<Tenista>, TenistaError>

    /**
     * Guarda una lista de objetos de tipo [T] en el archivo proporcionado.
     *
     * @param list Lista de objetos de tipo [T] que se guardarán.
     * @param file Archivo en el cual se almacenarán los datos.
     * @return Un [Result] que contiene el número de elementos guardados (como [Long]) en caso de éxito, o un [TenistaError] en caso de fallo.
     */
    fun save(list: List<T>, file: File): Result<Long, TenistaError>
}
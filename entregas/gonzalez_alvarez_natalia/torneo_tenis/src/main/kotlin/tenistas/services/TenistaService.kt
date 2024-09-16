package tenistas.services

import com.github.michaelbull.result.Result
import tenistas.errors.TenistaError
import tenistas.models.Tenista

/**
 * Interfaz que define los servicios para manejar operaciones relacionadas con los tenistas.
 *
 * @author Natalia González
 * @version 1.0
 */
interface TenistaService {

    /**
     * Obtiene todos los tenistas.
     *
     * @return Un [Result] que contiene una lista de todos los tenistas si la operación es exitosa,
     *         o un [TenistaError] en caso de error.
     */
    fun getAll(): Result<List<Tenista>, TenistaError>

    /**
     * Obtiene un tenista específico por su identificador.
     *
     * @param id Identificador del tenista a obtener.
     * @return Un [Result] que contiene el tenista con el identificador proporcionado si la operación es exitosa,
     *         o un [TenistaError] si el tenista no se encuentra o si ocurre un error.
     */
    fun getTenistaById(id: Long): Result<Tenista, TenistaError>

    /**
     * Guarda un nuevo tenista.
     *
     * @param tenista El tenista a guardar.
     * @return Un [Result] que contiene el tenista guardado si la operación es exitosa,
     *         o un [TenistaError] en caso de error.
     */
    fun saveTenista(tenista: Tenista): Result<Tenista, TenistaError>

    /**
     * Actualiza un tenista existente.
     *
     * @param id Identificador del tenista a actualizar.
     * @param tenista El tenista con la nueva información.
     * @return Un [Result] que contiene el tenista actualizado si la operación es exitosa,
     *         o un [TenistaError] si el tenista no se encuentra o si ocurre un error.
     */
    fun updateTenista(id: Long, tenista: Tenista): Result<Tenista, TenistaError>

    /**
     * Elimina todos los tenistas.
     *
     * @return Un [Result] que indica el éxito de la operación con [Unit] si la operación es exitosa,
     *         o un [TenistaError] en caso de error.
     */
    fun deteleAll(): Result<Unit, TenistaError>

    /**
     * Elimina un tenista específico por su identificador.
     *
     * @param id Identificador del tenista a eliminar.
     * @return Un [Result] que contiene el tenista eliminado si la operación es exitosa,
     *         o un [TenistaError] si el tenista no se encuentra o si ocurre un error.
     */
    fun deleteTenista(id: Long): Result<Tenista, TenistaError>
}
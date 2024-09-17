package org.example.tenist.validator

import com.github.michaelbull.result.Result
import org.example.tenist.errors.TenistError
import org.example.tenist.models.Tenist

interface TenistValidator {
    fun validate(tenist: Tenist) : Result<Tenist,TenistError>
}
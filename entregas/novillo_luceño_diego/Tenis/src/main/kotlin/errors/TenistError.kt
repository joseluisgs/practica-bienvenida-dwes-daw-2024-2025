package org.example.tenist.errors

sealed class TenistError (msg : String){
    class InvalidTenist(msg : String) : TenistError(msg)
    class ImportError(msg : String) : TenistError(msg)
    class ExportError(msg : String) : TenistError(msg)
    
}
package cache.errors

sealed class CacheError(message:String){
    class CacheErrorValid(message: String):CacheError(message)
}
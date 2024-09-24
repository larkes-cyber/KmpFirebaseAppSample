package com.larkes.firebasecryptoportfoliosample.utils

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Add<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class LoadingEnd<T>(data: T? = null) : Resource<T>(data)
}
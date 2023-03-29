package com.example.remote.models

data class NetworkResult<T>(
    val isSuccessful: Boolean,
    val message: String,
    val data: T? = null
)
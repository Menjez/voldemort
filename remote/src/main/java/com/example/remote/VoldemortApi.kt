package com.example.remote

import com.example.remote.models.CharacterDto
import com.example.remote.models.NetworkResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class VoldemortApi private constructor(private val client: HttpClient) {

    companion object {
        fun getInstance(client: HttpClient = VoldemortClient.getInstance()) =
            VoldemortApi(client = client)
    }

    private val base = "https://hp-api.onrender.com/api/"

    private suspend fun <T> safeApiCall(block: suspend () -> T): NetworkResult<T> {
        return try {
            val data = block()
            NetworkResult(isSuccessful = true, message = "success", data = data)
        } catch (e: Exception) {
            NetworkResult(isSuccessful = false, message = e.localizedMessage, data = null)
        }
    }

    suspend fun getCharacters(): NetworkResult<List<CharacterDto>> = safeApiCall {
        client.get(base.plus("characters")).body()
    }

}
package com.larkes.firebasecryptoportfoliosample.features.auth.domain

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun performUserAuthentification(idToken:String, accessToken:String?)
    fun checkUserAuthorization(): Flow<String>
}
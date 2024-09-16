package com.larkes.firebasecryptoportfoliosample.features.auth.data

import com.larkes.firebasecryptoportfoliosample.features.auth.data.source.AuthFirebaseDataSource
import com.larkes.firebasecryptoportfoliosample.features.auth.domain.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val authFirebaseDataSource: AuthFirebaseDataSource
):AuthRepository {
    override suspend fun performUserAuthentification(idToken: String, accessToken: String?) {
        authFirebaseDataSource.sendSignIn(idToken = idToken, accessToken = accessToken)
    }

    override fun checkUserAuthorization(): Flow<String> {
        return authFirebaseDataSource.checkAuthStatus()
    }
}
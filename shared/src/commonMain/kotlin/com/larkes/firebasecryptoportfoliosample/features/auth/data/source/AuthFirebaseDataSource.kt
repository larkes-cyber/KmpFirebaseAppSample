package com.larkes.firebasecryptoportfoliosample.features.auth.data.source

import com.larkes.firebasecryptoportfoliosample.utils.AuthStatus
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AuthFirebaseDataSource(
    private val firebaseAuth: FirebaseAuth
) {

    suspend fun sendSignIn(idToken:String, accessToken:String?){
        val credential = GoogleAuthProvider.credential(idToken = idToken, accessToken = accessToken)
        firebaseAuth.signInWithCredential(credential)
    }
    fun checkAuthStatus(): Flow<String> = channelFlow {
        coroutineScope {
            launch {
                firebaseAuth.authStateChanged.onEach {
                    trySend(if(it == null) AuthStatus.UNAUTHORIZED.name else AuthStatus.AUTHORIZED.name)
                }.launchIn(this)
            }
        }

    }

}
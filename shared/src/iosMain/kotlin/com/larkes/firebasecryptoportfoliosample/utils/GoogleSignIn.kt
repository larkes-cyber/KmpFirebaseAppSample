package com.larkes.firebasecryptoportfoliosample.utils

import androidx.compose.runtime.Composable
import cocoapods.GoogleSignIn.GIDSignIn
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication


@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun googleSignIn(callback:(String, String?) -> Unit){
    val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController!!
    GIDSignIn.sharedInstance
        .signInWithPresentingViewController(rootViewController) { gidSignInResult, nsError ->
            nsError?.let { println("Error While signing: $nsError") }
            val idToken = gidSignInResult?.user?.idToken?.tokenString
            val accessToken = gidSignInResult?.user?.accessToken?.tokenString
            val profile = gidSignInResult?.user?.profile
            if (idToken != null && accessToken != null) {
                callback(idToken, accessToken)
            }
        }
}
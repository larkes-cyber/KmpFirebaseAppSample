package com.larkes.firebasecryptoportfoliosample.utils

import Constants.SERVER_CLIENT_ID
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



@Composable
actual fun googleSignIn(callback: (String, String?) -> Unit) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.result
            if(account != null) {
                val idToken = account.idToken
                if(idToken != null) callback(idToken, null)
            }
        }catch (e:Exception){

        }
    }
    LaunchedEffect(Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val gso =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(SERVER_CLIENT_ID)
                    .requestEmail()
                    .build()
            val googleSignInClient = GoogleSignIn.getClient(Inject.configuration.context, gso)
            launcher.launch(googleSignInClient.signInIntent)
        }
    }

}
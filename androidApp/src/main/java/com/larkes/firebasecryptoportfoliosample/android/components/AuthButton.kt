package com.larkes.firebasecryptoportfoliosample.android.components

import Constants.SERVER_CLIENT_ID
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.larkes.firebasecryptoportfoliosample.android.Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AuthButton(
     modifier: Modifier = Modifier,
     title:String,
     image:Int,
     onSuccess:(String) -> Unit
) {

     val ctx = LocalContext.current

     val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
          val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
          try {
               val account = task.result
               if(account != null) {
                    val idToken = account.idToken
                    onSuccess(idToken!!)
               }
          }catch (e:Exception){

          }
     }

     fun signInWithGoogle(){
          CoroutineScope(Dispatchers.IO).launch {
               val gso =
                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                         .requestIdToken(SERVER_CLIENT_ID)
                         .requestEmail()
                         .build()
               val googleSignInClient = GoogleSignIn.getClient(ctx, gso)
               launcher.launch(googleSignInClient.signInIntent)
          }
     }

     Button(
          onClick = {
               signInWithGoogle()
          },
          shape = RoundedCornerShape(17),
          modifier = modifier.height(60.dp),
          colors = ButtonDefaults.buttonColors(containerColor = Theme.colors.action)
     ){
          Box(modifier = Modifier.fillMaxWidth()) {

               Text(
                    text = title,
                    fontSize = 20.sp,
                    color = Theme.colors.title,
                    modifier = Modifier.align(Alignment.Center)
               )
          }
     }

}
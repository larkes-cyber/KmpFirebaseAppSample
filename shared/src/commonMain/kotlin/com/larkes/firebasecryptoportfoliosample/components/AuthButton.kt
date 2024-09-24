package com.larkes.firebasecryptoportfoliosample.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larkes.firebasecryptoportfoliosample.theme.Theme
import com.larkes.firebasecryptoportfoliosample.utils.googleSignIn

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    title:String,
    onSuccess:(String, String?) -> Unit
) {

    var isSignInTapped by remember {
        mutableStateOf(false)
    }

    if(isSignInTapped){
        googleSignIn(callback = {idToken, accessToken ->
            onSuccess(idToken, accessToken)

        })
    }

    Button(
        onClick = {
            isSignInTapped = true
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
package com.larkes.firebasecryptoportfoliosample.features.auth.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larkes.firebasecryptoportfoliosample.components.AuthButton
import com.larkes.firebasecryptoportfoliosample.features.auth.presentation.models.AuthUIEvent
import com.larkes.firebasecryptoportfoliosample.theme.Theme

@Composable
fun AuthView(
    onEvent:(AuthUIEvent) -> Unit
) {

    Scaffold(
        containerColor = Theme.colors.primary
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = androidx.compose.ui.Modifier
                    .padding(it)
                    .padding(horizontal = 22.dp)
            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.landing_img),
//                    contentDescription = "",
//                    modifier = Modifier.fillMaxWidth(),
//                    contentScale = ContentScale.Crop
//                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Start your crypto portfolio",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Theme.colors.title
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Take your investment portfolio to next level",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Theme.colors.secondTitle
                )
                Spacer(modifier = Modifier.height(44.dp))
                AuthButton(title = "Google") {idToken, accessToken ->
                    onEvent(AuthUIEvent.SignInSucceeded(idToken, accessToken))
                }
            }
        }
    }

}
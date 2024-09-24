package com.larkes.firebasecryptoportfoliosample.utils

import androidx.compose.runtime.Composable

@Composable
expect fun googleSignIn(callback:(String, String?) -> Unit)
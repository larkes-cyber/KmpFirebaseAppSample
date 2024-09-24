package com.larkes.firebasecryptoportfoliosample.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


data class AppColors(
    val primary: Color,
    val title:Color,
    val secondTitle:Color,
    val action:Color,
    val secondPrimary:Color
)


val darkThemePalette = AppColors(
    primary = Color(0xff151A2B),
    title = Color.White,
    action = Color(0xff1C8CF3),
    secondTitle = Color(0xff82848F),
    secondPrimary = Color(0xff1A2B51)
)

val LocalColorProvider = staticCompositionLocalOf<AppColors> {
    error("")
}

@Composable
fun AppTheme(content:@Composable () -> Unit) {

    CompositionLocalProvider(
        LocalColorProvider provides  darkThemePalette,
        content = content
    )

}

object Theme{
    val colors:AppColors
        @Composable
        get() = LocalColorProvider.current
}
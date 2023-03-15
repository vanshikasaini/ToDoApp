package com.todoapp.ui.theme

import android.app.Activity
import android.graphics.ColorSpace
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
//import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
    //tertiary = Pink80,
    primary = Blue,
    secondary = Violet,
    tertiary = Pink80,
    background = BabyBlue,
)

private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
    primary = Blue,
    secondary = Violet,
    tertiary = Pink40,

    /* Other default colors to override*/
    background = BabyBlue//Color(0xFFFFFBFE),
    /* surface = Color(0xFFFFFBFE),
     onPrimary = Color.White,
     onSecondary = Color.White,
     onTertiary = Color.White,
     onBackground = Color(0xFF1C1B1F),
     onSurface = Color(0xFF1C1B1F),
     */
)

@Composable
fun ToDoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    CompositionLocalProvider(
        LocalPaddings provides Paddings()
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = Typography,
            content = content
        )
    }

}

object ToDoAppTheme {
    val colors: ColorScheme
        @Composable
        get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes
    val paddings: Paddings
        @Composable
        get() = LocalPaddings.current
}

@Immutable
data class Paddings(
    val defaultPadding: Dp = 16.dp,
    val tinyPadding: Dp = 4.dp,
    val smallPadding: Dp = 8.dp,
    val largePadding: Dp = 24.dp,

    val padding_3: Dp = 3.dp,
    val padding_10: Dp = 10.dp,
    val padding_15: Dp = 15.dp,
    val padding_30: Dp = 30.dp,
    val padding_32: Dp = 32.dp,
    val padding_50: Dp = 50.dp,
)

internal val LocalPaddings = staticCompositionLocalOf { Paddings() }
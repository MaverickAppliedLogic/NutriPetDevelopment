package com.maverickapps.tailyCare.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.window.core.layout.WindowWidthSizeClass
import com.maverickapps.tailyCare.core.ui.AppUtils
import com.maverickapps.tailyCare.core.ui.LocalAppDimens
import com.maverickapps.tailyCare.core.ui.dimens.CompactDimens
import com.maverickapps.tailyCare.core.ui.dimens.CompactMediumDimens
import com.maverickapps.tailyCare.core.ui.dimens.CompactSmallDimens
import com.maverickapps.tailyCare.core.ui.dimens.Dimens
import com.maverickapps.tailyCare.core.ui.dimens.ExpandedDimens
import com.maverickapps.tailyCare.core.ui.dimens.MediumDimens
import com.maverickapps.tailyCare.ui.view.theme.CompactMediumTypography
import com.maverickapps.tailyCare.ui.view.theme.CompactSmallTypography
import com.maverickapps.tailyCare.ui.view.theme.CompactTypography
import com.maverickapps.tailyCare.ui.view.theme.ExpandedTypography
import com.maverickapps.tailyCare.ui.view.theme.MediumTypography

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    secondary = SecondaryDark,
    background = NeutralLight,
    onBackground = SecondaryDarkest,
    onPrimary = SecondaryDarkest,
    tertiary = Neutral
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    background = NeutralLight,
    onBackground = Color.Black,
    tertiary = PrimaryLight,


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)


@Composable
fun TailyCareTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val config = LocalConfiguration.current
    val typography : Typography
    val dimens : Dimens

    when(windowSizeClass.windowWidthSizeClass){
        WindowWidthSizeClass.COMPACT -> {
            if (config.screenWidthDp <= 360){
                dimens = CompactSmallDimens
                typography = CompactSmallTypography
            }
            else if ( config.screenWidthDp < 599){
                dimens = CompactMediumDimens
                typography = CompactMediumTypography
            }
            else{
                dimens = CompactDimens
                typography = CompactTypography
            }
        }
        WindowWidthSizeClass.MEDIUM -> {
            dimens = MediumDimens
            typography = MediumTypography
        }
        else -> {
            dimens = ExpandedDimens
            typography = ExpandedTypography
        }
    }



    AppUtils(dimens) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            typography = typography,
            content = content,
        )
    }
}

val MaterialTheme.dimens @Composable get() = LocalAppDimens.current

val ScreenOrientation @Composable get() = LocalConfiguration.current.orientation
val ScreenHeight @Composable get() = LocalConfiguration.current.screenHeightDp


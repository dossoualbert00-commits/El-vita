package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
  primary = NavyPrimary,
  onPrimary = Color.White,
  primaryContainer = Color(0xFFDBEAFE),
  onPrimaryContainer = Color(0xFF1E3A8A),
  secondary = MedicalBlue,
  onSecondary = Color.White,
  secondaryContainer = Color(0xFFE0F2FE),
  onSecondaryContainer = Color(0xFF0369A1),
  tertiary = MedicalTeal,
  onTertiary = Color.White,
  tertiaryContainer = Color(0xFFCCFBF1),
  onTertiaryContainer = Color(0xFF115E59),
  background = BackgroundLight,
  onBackground = TextPrimaryLight,
  surface = SurfaceLight,
  onSurface = TextPrimaryLight,
  surfaceVariant = SurfaceVariantLight,
  onSurfaceVariant = TextSecondaryLight,
  error = AlertRed,
  onError = Color.White,
  errorContainer = AlertRedContainer,
  onErrorContainer = AlertRed,
  outline = OutlineLight
)

private val DarkColorScheme = darkColorScheme(
  primary = MedicalBlueLight,
  onPrimary = NavyDark,
  primaryContainer = Color(0xFF1E3A8A),
  onPrimaryContainer = Color(0xFFDBEAFE),
  secondary = MedicalTealLight,
  onSecondary = NavyDark,
  secondaryContainer = Color(0xFF134E4A),
  onSecondaryContainer = Color(0xFFCCFBF1),
  tertiary = Color(0xFF7DD3FC),
  onTertiary = NavyDark,
  tertiaryContainer = Color(0xFF0C4A6E),
  onTertiaryContainer = Color(0xFFE0F2FE),
  background = BackgroundDark,
  onBackground = TextPrimaryDark,
  surface = SurfaceDark,
  onSurface = TextPrimaryDark,
  surfaceVariant = SurfaceVariantDark,
  onSurfaceVariant = TextSecondaryDark,
  error = Color(0xFFF87171),
  onError = Color.Black,
  errorContainer = Color(0xFF7F1D1D),
  onErrorContainer = Color(0xFFFECACA),
  outline = OutlineDark
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Keep consistent medical palette
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

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}

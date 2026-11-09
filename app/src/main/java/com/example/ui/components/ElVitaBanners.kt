package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AlertGreen
import com.example.ui.theme.AlertGreenContainer
import com.example.ui.theme.AlertOrange
import com.example.ui.theme.AlertOrangeContainer
import com.example.ui.theme.AlertRed
import com.example.ui.theme.AlertRedContainer
import com.example.ui.theme.MedicalBlue

@Composable
fun LegalDisclaimerBanner(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f))
      .border(
        width = 1.dp,
        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
        shape = RoundedCornerShape(12.dp)
      )
      .padding(horizontal = 12.dp, vertical = 10.dp)
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        imageVector = Icons.Default.Info,
        contentDescription = "Information légale",
        tint = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.size(20.dp)
      )
      Spacer(modifier = Modifier.width(10.dp))
      Text(
        text = "« El Vita est un outil d’aide à l’observation et au raisonnement clinique. Il ne remplace ni le jugement médical ni l’enseignement. »",
        style = MaterialTheme.typography.bodySmall.copy(
          fontSize = 11.5.sp,
          lineHeight = 15.sp,
          fontWeight = FontWeight.Medium
        ),
        color = MaterialTheme.colorScheme.onSecondaryContainer
      )
    }
  }
}

@Composable
fun AlertLevelChip(
  alertLevel: String,
  modifier: Modifier = Modifier,
  customLabel: String? = null
) {
  val (bgColor, textColor, label) = when (alertLevel.uppercase()) {
    "CRITICAL" -> Triple(AlertRedContainer, AlertRed, customLabel ?: "Alerte Élevée")
    "MODERATE" -> Triple(AlertOrangeContainer, AlertOrange, customLabel ?: "Alerte Modérée")
    else -> Triple(AlertGreenContainer, AlertGreen, customLabel ?: "Normal")
  }

  Box(
    modifier = modifier
      .clip(RoundedCornerShape(20.dp))
      .background(bgColor)
      .padding(horizontal = 10.dp, vertical = 4.dp),
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = label,
      color = textColor,
      style = MaterialTheme.typography.labelSmall.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp
      )
    )
  }
}

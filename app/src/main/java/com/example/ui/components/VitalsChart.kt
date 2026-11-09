package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal
import java.util.Locale

data class ChartPoint(
  val timestamp: Long,
  val label: String,
  val value: Float,
  val secondaryValue: Float? = null // For Diastolic BP when primary is Systolic BP
)

@Composable
fun VitalsLineChart(
  title: String,
  unit: String,
  points: List<ChartPoint>,
  primaryColor: Color = MedicalBlue,
  secondaryColor: Color = MedicalTeal,
  isDualCurve: Boolean = false,
  primaryLabel: String = "Mesure",
  secondaryLabel: String = "Secondaire",
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(16.dp))
      .background(MaterialTheme.colorScheme.surface)
      .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
      .padding(16.dp)
  ) {
    Column {
      // Header
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Unité : $unit",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        // Legend
        if (isDualCurve) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(primaryColor))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = primaryLabel, style = MaterialTheme.typography.labelSmall, fontSize = 11.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(secondaryColor))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = secondaryLabel, style = MaterialTheme.typography.labelSmall, fontSize = 11.sp)
          }
        } else if (points.isNotEmpty()) {
          val lastVal = points.last().value
          val formatted = if (lastVal % 1 == 0f) "${lastVal.toInt()}" else String.format(Locale.FRANCE, "%.1f", lastVal)
          Text(
            text = "Dernier: $formatted $unit",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = primaryColor
          )
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      if (points.isEmpty()) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "Aucune mesure enregistrée sur la période",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      } else {
        // Chart canvas
        val allValues = mutableListOf<Float>()
        points.forEach {
          allValues.add(it.value)
          it.secondaryValue?.let { sv -> allValues.add(sv) }
        }
        val minVal = (allValues.minOrNull() ?: 0f) * 0.9f
        val maxVal = (allValues.maxOrNull() ?: 100f) * 1.1f
        val range = if (maxVal - minVal > 0) maxVal - minVal else 1f

        val gridLineColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)

        Canvas(
          modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
        ) {
          val w = size.width
          val h = size.height
          val paddingBottom = 20.dp.toPx()
          val chartHeight = h - paddingBottom

          // Horizontal grid lines
          val gridSteps = 3
          for (i in 0..gridSteps) {
            val y = chartHeight * (i.toFloat() / gridSteps)
            drawLine(
              color = gridLineColor,
              start = Offset(0f, y),
              end = Offset(w, y),
              strokeWidth = 1f
            )
          }

          if (points.size == 1) {
            val pt = points[0]
            val y1 = chartHeight - ((pt.value - minVal) / range) * chartHeight
            drawCircle(color = primaryColor, radius = 6.dp.toPx(), center = Offset(w / 2f, y1))
            if (isDualCurve && pt.secondaryValue != null) {
              val y2 = chartHeight - ((pt.secondaryValue - minVal) / range) * chartHeight
              drawCircle(color = secondaryColor, radius = 6.dp.toPx(), center = Offset(w / 2f, y2))
            }
          } else {
            val stepX = w / (points.size - 1)

            // Primary Curve
            val primaryPath = Path()
            points.forEachIndexed { index, pt ->
              val x = index * stepX
              val y = chartHeight - ((pt.value - minVal) / range) * chartHeight
              if (index == 0) primaryPath.moveTo(x, y) else primaryPath.lineTo(x, y)
            }
            drawPath(
              path = primaryPath,
              color = primaryColor,
              style = Stroke(width = 3.dp.toPx())
            )
            // Points
            points.forEachIndexed { index, pt ->
              val x = index * stepX
              val y = chartHeight - ((pt.value - minVal) / range) * chartHeight
              drawCircle(color = Color.White, radius = 5.dp.toPx(), center = Offset(x, y))
              drawCircle(color = primaryColor, radius = 3.5.dp.toPx(), center = Offset(x, y))
            }

            // Dual curve (e.g. Diastolic BP)
            if (isDualCurve) {
              val secondaryPath = Path()
              points.forEachIndexed { index, pt ->
                val sv = pt.secondaryValue ?: pt.value
                val x = index * stepX
                val y = chartHeight - ((sv - minVal) / range) * chartHeight
                if (index == 0) secondaryPath.moveTo(x, y) else secondaryPath.lineTo(x, y)
              }
              drawPath(
                path = secondaryPath,
                color = secondaryColor,
                style = Stroke(width = 3.dp.toPx())
              )
              // Points
              points.forEachIndexed { index, pt ->
                val sv = pt.secondaryValue ?: pt.value
                val x = index * stepX
                val y = chartHeight - ((sv - minVal) / range) * chartHeight
                drawCircle(color = Color.White, radius = 5.dp.toPx(), center = Offset(x, y))
                drawCircle(color = secondaryColor, radius = 3.5.dp.toPx(), center = Offset(x, y))
              }
            }
          }
        }

        // X labels (first, middle, last)
        Row(
          modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          if (points.isNotEmpty()) {
            Text(text = points.first().label, style = MaterialTheme.typography.labelSmall, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            if (points.size > 2) {
              Text(text = points[points.size / 2].label, style = MaterialTheme.typography.labelSmall, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Text(text = points.last().label, style = MaterialTheme.typography.labelSmall, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
          }
        }
      }
    }
  }
}

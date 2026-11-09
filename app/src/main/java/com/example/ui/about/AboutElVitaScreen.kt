package com.example.ui.about

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Diversity3
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.BuildConfig
import com.example.R
import com.example.ui.components.LegalDisclaimerBanner
import com.example.ui.theme.AlertGreen
import com.example.ui.theme.AlertGreenContainer
import com.example.ui.theme.AlertOrange
import com.example.ui.theme.AlertRed
import com.example.ui.theme.MedicalBlue
import com.example.ui.theme.MedicalTeal
import com.example.ui.theme.NavyDark

const val OFFICIAL_EMAIL = "dossoualbert00@gmail.com"

@Composable
fun AboutElVitaScreen(
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current

  fun openEmailClient(subject: String = "Contact El Vita") {
    try {
      val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$OFFICIAL_EMAIL")
        putExtra(Intent.EXTRA_SUBJECT, subject)
      }
      context.startActivity(intent)
    } catch (e: Exception) {
      Toast.makeText(context, "Aucune application de messagerie trouvée.", Toast.LENGTH_SHORT).show()
    }
  }

  fun copyEmailToClipboard() {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Email officiel El Vita", OFFICIAL_EMAIL)
    clipboard.setPrimaryClip(clip)
    Toast.makeText(context, "Adresse copiée : $OFFICIAL_EMAIL", Toast.LENGTH_SHORT).show()
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("about_elvita_screen"),
    contentPadding = PaddingValues(bottom = 36.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // ══════════════════════════════════════════════════════
    // EN-TÊTE
    // ══════════════════════════════════════════════════════
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(
            Brush.verticalGradient(
              listOf(NavyDark, MaterialTheme.colorScheme.primary)
            )
          )
          .padding(top = 16.dp, bottom = 22.dp, start = 16.dp, end = 16.dp)
      ) {
        Column {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            IconButton(
              onClick = onBack,
              modifier = Modifier
                .testTag("about_back_button")
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.15f))
            ) {
              Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Retour",
                tint = Color.White
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text(
                text = "À propos d'El Vita",
                style = MaterialTheme.typography.titleLarge.copy(
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 0.5.sp
                ),
                color = Color.White
              )
              Text(
                text = "Identité institutionnelle, vision & propriété",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp),
                color = Color.White.copy(alpha = 0.82f)
              )
            }
          }
        }
      }
    }

    // Avertissement médical obligatoire
    item {
      Box(modifier = Modifier.padding(horizontal = 16.dp)) {
        LegalDisclaimerBanner()
      }
    }

    // ══════════════════════════════════════════════════════
    // SECTION A — PRÉSENTATION D'EL VITA
    // ══════════════════════════════════════════════════════
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .testTag("section_presentation"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(
          modifier = Modifier.padding(20.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          // Logo officiel
          Box(
            modifier = Modifier
              .size(80.dp)
              .clip(RoundedCornerShape(18.dp))
              .background(NavyDark)
              .padding(4.dp),
            contentAlignment = Alignment.Center
          ) {
            Image(
              painter = painterResource(id = R.drawable.img_elvita_logo),
              contentDescription = "Logo officiel El Vita",
              modifier = Modifier.size(72.dp),
              contentScale = ContentScale.Fit
            )
          }

          Spacer(modifier = Modifier.height(12.dp))

          Text(
            text = "EL VITA",
            style = MaterialTheme.typography.headlineMedium.copy(
              fontWeight = FontWeight.Black,
              letterSpacing = 2.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
          )

          Text(
            text = "« Apprendre. Comprendre. Pratiquer. »",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = MedicalBlue
            )
          )

          Spacer(modifier = Modifier.height(8.dp))

          // Badges d'identité
          Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(vertical = 4.dp)
          ) {
            IdentityPill(text = "Plateforme indépendante")
            IdentityPill(text = "Médecine 3e à 7e année")
          }

          Spacer(modifier = Modifier.height(14.dp))
          HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
          Spacer(modifier = Modifier.height(14.dp))

          Text(
            text = "El Vita est une plateforme numérique d'apprentissage médical destinée aux étudiants en médecine de la 3e à la 7e année.\n\n" +
              "Elle met à disposition des outils pédagogiques permettant de mieux comprendre la sémiologie, d'apprendre à interpréter les données cliniques, de s'exercer au raisonnement médical et de renforcer les connaissances théoriques et pratiques.\n\n" +
              "Notre ambition est de rendre l'apprentissage médical plus accessible, interactif et structuré.",
            style = MaterialTheme.typography.bodyMedium.copy(
              lineHeight = 22.sp,
              color = MaterialTheme.colorScheme.onSurface
            ),
            textAlign = TextAlign.Justify
          )
        }
      }
    }

    // ══════════════════════════════════════════════════════
    // SECTION SPÉCIALE : LE FONDATEUR (ALBERT DOSSOU)
    // ══════════════════════════════════════════════════════
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .testTag("section_founder"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, MedicalBlue.copy(alpha = 0.4f))
      ) {
        Column(modifier = Modifier.padding(20.dp)) {
          // Titre de section
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
          ) {
            Box(
              modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Verified,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(20.dp)
              )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = "LE FONDATEUR",
                style = MaterialTheme.typography.labelMedium.copy(
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 1.2.sp,
                  color = MaterialTheme.colorScheme.primary
                )
              )
              Text(
                text = "Direction & Propriété de la Plateforme",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }

          Spacer(modifier = Modifier.height(18.dp))

          // Photographie officielle originale du fondateur
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(16.dp))
              .background(MaterialTheme.colorScheme.surfaceVariant)
              .border(
                1.dp,
                MaterialTheme.colorScheme.outline.copy(alpha = 0.6f),
                RoundedCornerShape(16.dp)
              )
          ) {
            Image(
              painter = painterResource(id = R.drawable.img_founder_albert_dossou),
              contentDescription = "Albert Dossou, Fondateur et propriétaire d'El Vita",
              modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f),
              contentScale = ContentScale.Crop,
              alignment = Alignment.Center
            )
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Identité officielle
          Text(
            text = "ALBERT DOSSOU",
            style = MaterialTheme.typography.titleLarge.copy(
              fontWeight = FontWeight.Black,
              letterSpacing = 1.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
          )

          Text(
            text = "Fondateur et propriétaire d'El Vita",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.SemiBold,
              color = MedicalBlue
            )
          )

          Spacer(modifier = Modifier.height(12.dp))
          HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))
          Spacer(modifier = Modifier.height(12.dp))

          Text(
            text = "« El Vita est une initiative indépendante portée par Albert Dossou, son fondateur et propriétaire.\n\n" +
              "À travers ce projet, il souhaite contribuer à rendre l'apprentissage médical plus accessible et à mettre les outils numériques au service de la formation des futurs professionnels de santé.\n\n" +
              "El Vita est développée avec une ambition : proposer aux étudiants en médecine un environnement d'apprentissage utile, pratique et évolutif. »",
            style = MaterialTheme.typography.bodyMedium.copy(
              lineHeight = 22.sp,
              color = MaterialTheme.colorScheme.onSurface
            ),
            textAlign = TextAlign.Justify
          )
        }
      }
    }

    // ══════════════════════════════════════════════════════
    // SECTION : GRATUITÉ ACTUELLE D'EL VITA
    // ══════════════════════════════════════════════════════
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .testTag("section_free_access"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
          containerColor = AlertGreen.copy(alpha = 0.08f)
        ),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, AlertGreen.copy(alpha = 0.45f))
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "EL VITA EST ACTUELLEMENT GRATUIT",
              style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.6.sp
              ),
              color = AlertGreen
            )

            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(AlertGreen)
                .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
              Text(
                text = "GRATUIT ACTUELLEMENT",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = Color.White,
                  fontSize = 10.sp
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          Text(
            text = "Dans sa phase actuelle de développement, El Vita est accessible gratuitement aux étudiants en médecine.\n\n" +
              "Notre priorité est de développer une plateforme utile, fiable et accessible, qui accompagne les étudiants dans leur apprentissage.\n\n" +
              "Nous souhaitons d'abord construire une communauté d'utilisateurs et améliorer continuellement la qualité des ressources et des outils pédagogiques proposés.",
            style = MaterialTheme.typography.bodyMedium.copy(
              lineHeight = 21.sp,
              color = MaterialTheme.colorScheme.onSurface
            ),
            textAlign = TextAlign.Justify
          )

          Spacer(modifier = Modifier.height(10.dp))

          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
          ) {
            Icon(
              imageVector = Icons.Default.Check,
              contentDescription = null,
              tint = AlertGreen,
              modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Accès intégral sans paiement ni publicité commerciale imposée",
              style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium,
                color = AlertGreen
              )
            )
          }
        }
      }
    }

    // ══════════════════════════════════════════════════════
    // SECTION B — NOTRE HISTOIRE
    // ══════════════════════════════════════════════════════
    item {
      SectionCard(
        title = "Notre histoire",
        icon = Icons.Default.School,
        content = "El Vita est née d'une volonté de contribuer à l'amélioration de l'apprentissage médical grâce aux nouvelles technologies.\n\n" +
          "Le projet part d'un constat : les étudiants en médecine ont besoin d'outils pratiques, accessibles et structurés pour mieux assimiler les connaissances théoriques et développer leur raisonnement clinique.\n\n" +
          "C'est dans cette perspective qu'El Vita a été conçue : réunir dans un même environnement numérique des ressources pédagogiques, des outils d'apprentissage et des fonctionnalités destinées à accompagner les étudiants tout au long de leur formation."
      )
    }

    // ══════════════════════════════════════════════════════
    // SECTION C — NOTRE MISSION
    // ══════════════════════════════════════════════════════
    item {
      SectionCard(
        title = "Notre mission",
        icon = Icons.Default.AutoAwesome,
        content = "Faciliter l'apprentissage médical en proposant aux étudiants des outils numériques pédagogiques, accessibles et pratiques.\n\n" +
          "Nous souhaitons accompagner les étudiants dans la compréhension des signes cliniques, l'interprétation des examens, l'apprentissage de la sémiologie et le développement du raisonnement clinique."
      )
    }

    // ══════════════════════════════════════════════════════
    // SECTION D — NOTRE VISION
    // ══════════════════════════════════════════════════════
    item {
      SectionCard(
        title = "Notre vision",
        icon = Icons.Default.Public,
        content = "Construire une plateforme de référence pour l'apprentissage médical numérique dans l'espace francophone africain.\n\n" +
          "Nous souhaitons contribuer à une nouvelle manière d'apprendre la médecine, en associant connaissances scientifiques, pédagogie, innovation et technologies numériques."
      )
    }

    // ══════════════════════════════════════════════════════
    // SECTION E — NOS VALEURS
    // ══════════════════════════════════════════════════════
    item {
      Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Text(
          text = "Nos valeurs",
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onBackground
        )

        ValueCard(
          number = "1",
          title = "ACCESSIBILITÉ",
          description = "Faciliter l'accès aux ressources pédagogiques pour les étudiants.",
          icon = Icons.Default.AccessibilityNew,
          color = MedicalBlue
        )

        ValueCard(
          number = "2",
          title = "EXCELLENCE PÉDAGOGIQUE",
          description = "Privilégier des connaissances rigoureuses, structurées et utiles à la formation médicale.",
          icon = Icons.Default.School,
          color = NavyDark
        )

        ValueCard(
          number = "3",
          title = "INNOVATION",
          description = "Utiliser les technologies numériques pour améliorer l'apprentissage.",
          icon = Icons.Default.Lightbulb,
          color = MedicalTeal
        )

        ValueCard(
          number = "4",
          title = "PARTAGE",
          description = "Favoriser la transmission et l'accessibilité des connaissances.",
          icon = Icons.Default.Diversity3,
          color = Color(0xFF6366F1)
        )

        ValueCard(
          number = "5",
          title = "RESPONSABILITÉ",
          description = "Promouvoir une utilisation éthique et responsable des connaissances médicales.",
          icon = Icons.Default.VerifiedUser,
          color = AlertGreen
        )
      }
    }

    // ══════════════════════════════════════════════════════
    // SECTION : NOS AMBITIONS ET ÉVOLUTIONS FUTURES
    // ══════════════════════════════════════════════════════
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .testTag("section_future_ambitions"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Text(
            text = "El Vita évolue avec vous",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
          )

          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = "El Vita est un projet évolutif.\n\n" +
              "De nouvelles fonctionnalités et de nouveaux outils pédagogiques pourront progressivement enrichir la plateforme afin de répondre aux besoins des étudiants en médecine.\n\n" +
              "Notre ambition est de faire évoluer El Vita tout en conservant sa mission éducative et son engagement en faveur de l'accessibilité.",
            style = MaterialTheme.typography.bodyMedium.copy(
              lineHeight = 22.sp,
              color = MaterialTheme.colorScheme.onSurface
            ),
            textAlign = TextAlign.Justify
          )

          Spacer(modifier = Modifier.height(14.dp))
          Text(
            text = "Perspectives & axes d'enrichissement futur :",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.secondary
          )
          Spacer(modifier = Modifier.height(8.dp))

          val futureFeatures = listOf(
            "Nouveaux modules pédagogiques",
            "Parcours avancés de révision",
            "Examens blancs",
            "Cas cliniques approfondis",
            "Statistiques avancées de progression",
            "Outils supplémentaires d'apprentissage"
          )

          futureFeatures.forEach { feature ->
            Row(
              modifier = Modifier.padding(vertical = 3.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Box(
                modifier = Modifier
                  .size(6.dp)
                  .clip(CircleShape)
                  .background(MedicalTeal)
              )
              Spacer(modifier = Modifier.width(10.dp))
              Text(
                text = feature,
                style = MaterialTheme.typography.bodyMedium.copy(
                  fontSize = 13.sp,
                  color = MaterialTheme.colorScheme.onSurface
                )
              )
            }
          }
        }
      }
    }

    // ══════════════════════════════════════════════════════
    // SECTION : CONTACT ET ASSISTANCE
    // ══════════════════════════════════════════════════════
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .testTag("section_contact"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
      ) {
        Column(modifier = Modifier.padding(20.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.SupportAgent,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.size(20.dp)
              )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = "Contact et Assistance",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = "Une équipe à votre écoute pour vous accompagner",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Bloc e-mail officiel
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
              .background(MaterialTheme.colorScheme.surfaceVariant)
              .padding(14.dp)
          ) {
            Column {
              Text(
                text = "Adresse e-mail officielle de la plateforme :",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.SemiBold,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              )
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = OFFICIAL_EMAIL,
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.primary
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          // Boutons d'action : Nous contacter & Copier
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Button(
              onClick = { openEmailClient("[El Vita] Contact & Renseignements") },
              modifier = Modifier
                .weight(1.2f)
                .height(48.dp)
                .testTag("contact_email_button"),
              shape = RoundedCornerShape(12.dp),
              colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
              Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text("Nous contacter", fontWeight = FontWeight.Bold)
            }

            OutlinedButton(
              onClick = { copyEmailToClipboard() },
              modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .testTag("copy_email_button"),
              shape = RoundedCornerShape(12.dp)
            ) {
              Icon(
                imageVector = Icons.Default.ContentCopy,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text("Copier", fontWeight = FontWeight.SemiBold)
            }
          }

          Spacer(modifier = Modifier.height(16.dp))
          Text(
            text = "Accès directs par motif :",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Spacer(modifier = Modifier.height(8.dp))

          // 4 motifs de contact
          ContactReasonItem(
            icon = Icons.Default.SupportAgent,
            title = "Assistance technique",
            subtitle = "Difficulté d'utilisation ou problème d'affichage",
            onClick = { openEmailClient("[El Vita] Assistance technique") }
          )

          ContactReasonItem(
            icon = Icons.Default.Lightbulb,
            title = "Suggestions d'amélioration",
            subtitle = "Idées de fiches ou nouvelles fonctionnalités pédagogiques",
            onClick = { openEmailClient("[El Vita] Suggestions d'amélioration") }
          )

          ContactReasonItem(
            icon = Icons.Default.ReportProblem,
            title = "Signalement d'un problème",
            subtitle = "Remarque sur un calcul ou une fiche sémiologique",
            onClick = { openEmailClient("[El Vita] Signalement d'un problème") }
          )

          ContactReasonItem(
            icon = Icons.Default.Handshake,
            title = "Demandes de partenariat",
            subtitle = "Associations étudiantes, facultés et enseignants",
            onClick = { openEmailClient("[El Vita] Demande de partenariat") }
          )
        }
      }
    }

    // ══════════════════════════════════════════════════════
    // SECTION : PROPRIÉTÉ INTELLECTUELLE
    // ══════════════════════════════════════════════════════
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .testTag("section_intellectual_property"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Gavel,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
              modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
              text = "Propriété intellectuelle",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = MaterialTheme.colorScheme.onSurface
            )
          }

          Spacer(modifier = Modifier.height(12.dp))

          Text(
            text = "El Vita est une plateforme indépendante développée sous la responsabilité de son fondateur.\n\n" +
              "La marque El Vita, son identité visuelle et les éléments originaux développés pour la plateforme sont soumis aux dispositions applicables en matière de propriété intellectuelle.\n\n" +
              "Les contenus pédagogiques provenant de sources externes restent soumis aux droits de leurs auteurs et éditeurs respectifs.\n\n" +
              "Toute reproduction ou utilisation non autorisée des éléments protégés est susceptible de constituer une violation des droits applicables.",
            style = MaterialTheme.typography.bodyMedium.copy(
              lineHeight = 22.sp,
              color = MaterialTheme.colorScheme.onSurface
            ),
            textAlign = TextAlign.Justify
          )

          Spacer(modifier = Modifier.height(14.dp))
          HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))
          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = "© 2026 El Vita. Tous droits réservés.\nUne initiative d'Albert Dossou.",
            style = MaterialTheme.typography.bodySmall.copy(
              fontWeight = FontWeight.Medium,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          )
        }
      }
    }

    // ══════════════════════════════════════════════════════
    // SECTION : MENTIONS LÉGALES ET CONFIDENTIALITÉ
    // ══════════════════════════════════════════════════════
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .testTag("section_legal_and_privacy"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Text(
            text = "Mentions Légales & Confidentialité",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "Cadre d'utilisation, éthique et protection des données",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Cartes dépliables
          ExpandableLegalCard(
            title = "Avertissement Médical et Pédagogique",
            content = "« El Vita est un outil d'aide à l'observation et au raisonnement clinique. Il ne remplace ni le jugement médical ni un enseignement. »\n\n" +
              "L'application est conçue exclusivement pour l'accompagnement pédagogique des étudiants en médecine et ne doit en aucun cas se substituer à une décision médicale réelle, une prescription ou un avis spécialisé."
          )

          Spacer(modifier = Modifier.height(8.dp))

          ExpandableLegalCard(
            title = "Protection des Données Personnelles & Confidentialité",
            content = "1. Stockage Local Sécurisé : Les données saisies lors des exercices cliniques (dossiers, constantes, observations) sont enregistrées localement sur l'appareil dans une base SQLite/Room chiffrée par les mécanismes de bac à sable d'Android.\n\n" +
              "2. Pseudonymisation Recommandée : Pour tout exercice d'apprentissage basé sur un cas réel, l'étudiant s'engage à n'utiliser que des données strictement pseudonymisées (initiales ou identifiants fictifs) respectant le secret médical et la vie privée du patient.\n\n" +
              "3. Droits des Utilisateurs : Conformément aux principes de protection des données, vous disposez à tout moment du droit de modifier ou de supprimer vos dossiers et relevés enregistrés directement depuis l'application."
          )

          Spacer(modifier = Modifier.height(8.dp))

          ExpandableLegalCard(
            title = "Conditions Générales d'Utilisation (CGU)",
            content = "L'accès à la plateforme El Vita est réservé aux étudiants et professionnels de santé en formation. L'utilisateur s'engage à utiliser les outils dans le respect de l'éthique médicale, des règles déontologiques et de la dignité des personnes."
          )

          Spacer(modifier = Modifier.height(8.dp))

          ExpandableLegalCard(
            title = "Mentions Légales Institutionnelles",
            content = "Éditeur de la plateforme : El Vita\n" +
              "Fondateur et propriétaire : Albert Dossou\n" +
              "Nature : Plateforme numérique indépendante d'apprentissage médical\n" +
              "Contact officiel : $OFFICIAL_EMAIL\n" +
              "Hébergement & Exécution : Environnement Android natif autonome"
          )
        }
      }
    }

    // ══════════════════════════════════════════════════════
    // SECTION : INFORMATIONS SUR L'APPLICATION
    // ══════════════════════════════════════════════════════
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .testTag("section_app_info"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Text(
            text = "Informations sur l'application",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )

          Spacer(modifier = Modifier.height(12.dp))

          AppInfoRow(label = "Nom de la plateforme", value = "El Vita")
          AppInfoRow(label = "Signature", value = "« Apprendre. Comprendre. Pratiquer. »")
          AppInfoRow(label = "Version", value = "1.0 (Build 1)")
          AppInfoRow(label = "Plateforme", value = "Android")
          AppInfoRow(label = "Date de dernière mise à jour", value = "Novembre 2026")
          AppInfoRow(label = "Propriétaire", value = "Albert Dossou")
          AppInfoRow(label = "Contact officiel", value = OFFICIAL_EMAIL)
        }
      }
    }
  }
}

// ══════════════════════════════════════════════════════
// COMPOSANTS REUTILISABLES
// ══════════════════════════════════════════════════════

@Composable
fun SectionCard(
  title: String,
  icon: ImageVector,
  content: String,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .size(32.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primaryContainer),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(18.dp)
          )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
          text = title,
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      Text(
        text = content,
        style = MaterialTheme.typography.bodyMedium.copy(
          lineHeight = 22.sp,
          color = MaterialTheme.colorScheme.onSurface
        ),
        textAlign = TextAlign.Justify
      )
    }
  }
}

@Composable
fun ValueCard(
  number: String,
  title: String,
  description: String,
  icon: ImageVector,
  color: Color,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(42.dp)
          .clip(RoundedCornerShape(10.dp))
          .background(color.copy(alpha = 0.12f)),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = color,
          modifier = Modifier.size(22.dp)
        )
      }

      Spacer(modifier = Modifier.width(12.dp))

      Column(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "$number. $title",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
          text = description,
          style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }
  }
}

@Composable
fun IdentityPill(text: String) {
  Box(
    modifier = Modifier
      .clip(RoundedCornerShape(20.dp))
      .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f))
      .padding(horizontal = 10.dp, vertical = 4.dp)
  ) {
    Text(
      text = text,
      style = MaterialTheme.typography.labelSmall.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.5.sp
      ),
      color = MaterialTheme.colorScheme.onSecondaryContainer
    )
  }
}

@Composable
fun ContactReasonItem(
  icon: ImageVector,
  title: String,
  subtitle: String,
  onClick: () -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 4.dp)
      .clip(RoundedCornerShape(10.dp))
      .clickable { onClick() }
      .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f))
      .padding(horizontal = 12.dp, vertical = 10.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = MaterialTheme.colorScheme.primary,
      modifier = Modifier.size(20.dp)
    )
    Spacer(modifier = Modifier.width(10.dp))
    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = title,
        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
        color = MaterialTheme.colorScheme.onSurface
      )
      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }
  }
}

@Composable
fun ExpandableLegalCard(title: String, content: String) {
  var expanded by remember { mutableStateOf(false) }

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(10.dp))
      .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
      .clickable { expanded = !expanded }
      .padding(12.dp)
  ) {
    Column {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = title,
          style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
          color = MaterialTheme.colorScheme.onSurface,
          modifier = Modifier.weight(1f)
        )
        Icon(
          imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }

      AnimatedVisibility(visible = expanded) {
        Column(modifier = Modifier.padding(top = 10.dp)) {
          HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = content,
            style = MaterialTheme.typography.bodySmall.copy(
              lineHeight = 18.sp,
              color = MaterialTheme.colorScheme.onSurface
            ),
            textAlign = TextAlign.Justify
          )
        }
      }
    }
  }
}

@Composable
fun AppInfoRow(label: String, value: String) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 4.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = label,
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    Text(
      text = value,
      style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
      color = MaterialTheme.colorScheme.onSurface
    )
  }
}

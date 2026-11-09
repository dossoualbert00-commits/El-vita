package com.example.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.entities.ClinicalObservationEntity
import com.example.data.local.entities.ClinicalUpdateEntity
import com.example.data.local.entities.PatientEntity
import com.example.data.local.entities.VitalEntity
import com.example.data.repository.MedicalRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
  val recentVitals: List<VitalEntity> = emptyList(),
  val patients: List<PatientEntity> = emptyList(),
  val recentObservations: List<ClinicalObservationEntity> = emptyList(),
  val recentUpdates: List<ClinicalUpdateEntity> = emptyList(),
  val patientCount: Int = 0,
  val vitalCount: Int = 0,
  val observationCount: Int = 0
)

data class PreventionTopic(
  val id: String,
  val title: String,
  val subtitle: String,
  val keyPoints: List<String>,
  val clinicalMessage: String,
  val tag: String
)

class HomeViewModel(private val repository: MedicalRepository) : ViewModel() {

  val preventionTopics: List<PreventionTopic> = listOf(
    PreventionTopic(
      id = "malaria",
      title = "Paludisme : Urgence & Diagnostic",
      subtitle = "Goutte épaisse, TDR et critères de gravité OMS",
      keyPoints = listOf(
        "Toute fièvre au retour de zone d'endémie est un paludisme jusqu'à preuve du contraire.",
        "Rechercher immédiatement les signes de gravité : coma, détresse respiratoire, convulsions, hypoglycémie, ictère.",
        "Diagnostic parasitologique en urgence (Frottis-Goutte Épaisse et TDR)."
      ),
      clinicalMessage = "Traitement du neuropaludisme / accès grave : Artésunate intraveineux en extrême urgence.",
      tag = "Infectiologie"
    ),
    PreventionTopic(
      id = "polio",
      title = "Poliomyélite & Paralysie Flasque",
      subtitle = "Surveillance mondiale et couverture vaccinale",
      keyPoints = listOf(
        "Transmission féco-orale interhumaine par les poliovirus sauvages ou dérivés de souches vaccinales.",
        "Tableau clinique : paralysie flasque aiguë asymétrique avec abolition des ROT, sans atteinte sensitive.",
        "Prévention absolue par la vaccination (VPI injectable et VPO)."
      ),
      clinicalMessage = "Toute paralysie flasque aiguë (PFA) chez l'enfant impose une déclaration et un prélèvement de selles.",
      tag = "Santé Publique"
    ),
    PreventionTopic(
      id = "hygiene",
      title = "Hygiène des Mains & Précautions Standard",
      subtitle = "Les 5 indications OMS au lit du patient",
      keyPoints = listOf(
        "Avant de toucher un patient.",
        "Avant un geste aseptique (pose de cathéter, pansement).",
        "Après un risque d'exposition à un liquide biologique.",
        "Après avoir touché un patient.",
        "Après avoir touché l'environnement du patient."
      ),
      clinicalMessage = "La friction hydro-alcoolique (FHA) sur mains sèches et non souillées est la référence absolue.",
      tag = "Sécurité du soin"
    ),
    PreventionTopic(
      id = "vaccines",
      title = "Vaccination des Étudiants et Soignants",
      subtitle = "Protection individuelle et des patients vulnérables",
      keyPoints = listOf(
        "Hépatite B obligatoire avec contrôle du titre des anticorps anti-HBs (> 100 UI/L = immunité durable).",
        "DTP (Diphtérie, Tétanos, Poliomyélite) à jour selon le calendrier.",
        "Coqueluche, ROR, Grippe saisonnière et Covid-19 fortement recommandés."
      ),
      clinicalMessage = "Protéger le soignant, c'est avant tout protéger le patient immunodéprimé hospitalisé.",
      tag = "Immunologie"
    ),
    PreventionTopic(
      id = "cardio_prev",
      title = "Prévention Cardiovasculaire Primaire",
      subtitle = "Contrôle des facteurs de risque modifiables",
      keyPoints = listOf(
        "Objectif tensionnel : PA < 140/90 mmHg (voire < 130/80 mmHg si bien toléré).",
        "Sevrage tabagique complet : bénéfice cardiovasculaire précoce dès les premières semaines.",
        "Activité physique régulière : au moins 150 min d'activité aérobie modérée par semaine.",
        "Régime méditerranéen / DASH et contrôle du bilan lipidique (LDL-c ciblé selon le risque)."
      ),
      clinicalMessage = "Calculer systématiquement le risque cardiovasculaire global (SCORE2) chez l'adulte.",
      tag = "Cardiologie"
    ),
    PreventionTopic(
      id = "nosocomial",
      title = "Infections Associées aux Soins (IAS)",
      subtitle = "Bon usage des antibiotiques et asepsie",
      keyPoints = listOf(
        "Retirer tout dispositif invasif (cathéter veineux périphérique, sonde urinaire) dès qu'il n'est plus indispensable.",
        "Antibiogouvernance : réévaluer toute antibiothérapie probabiliste à 48-72h avec les résultats bactériologiques.",
        "Respect strict des isolements : Contact (BMR), Gouttelettes (Grippe), Air (Tuberculose)."
      ),
      clinicalMessage = "La sonde vésicale est la première cause d'infection nosocomiale : pas de sonde de confort !",
      tag = "Hygiène"
    )
  )

  val uiState: StateFlow<HomeUiState> = combine(
    repository.getRecentVitals(6),
    repository.getAllPatients(),
    repository.getAllObservations(),
    repository.getAllUpdates()
  ) { vitals, patients, observations, updates ->
    HomeUiState(
      recentVitals = vitals,
      patients = patients,
      recentObservations = observations.take(3),
      recentUpdates = updates.take(3),
      patientCount = patients.size,
      vitalCount = vitals.size,
      observationCount = observations.size
    )
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000),
    initialValue = HomeUiState()
  )
}

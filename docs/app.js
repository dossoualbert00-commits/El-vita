/**
 * EL VITA — Compagnon Clinique et Pédagogique
 * Version PWA Multiplateforme (iOS Safari & Android Chrome)
 */

// 1. Initialisation & Persistance Locale
const STORAGE_KEY_PROFILE = 'elvita_web_student_profile';
const STORAGE_KEY_THEME = 'elvita_web_theme';

const defaultProfile = {
  name: "Étudiant en médecine",
  studyYear: "DFASM 2 (5e Année)",
  university: "Faculté des Sciences de la Santé",
  hospital: "CHU - Pôle Médecine Interne"
};

let currentProfile = JSON.parse(localStorage.getItem(STORAGE_KEY_PROFILE)) || defaultProfile;

// 2. Gestion du Thème (Clair / Sombre)
function initTheme() {
  const savedTheme = localStorage.getItem(STORAGE_KEY_THEME) || 'light';
  document.documentElement.setAttribute('data-theme', savedTheme);
  const themeBtn = document.getElementById('theme-toggle-btn');
  if (themeBtn) {
    themeBtn.textContent = savedTheme === 'dark' ? '☀️' : '🌙';
  }
}

function toggleTheme() {
  const current = document.documentElement.getAttribute('data-theme') || 'light';
  const next = current === 'dark' ? 'light' : 'dark';
  document.documentElement.setAttribute('data-theme', next);
  localStorage.setItem(STORAGE_KEY_THEME, next);
  const themeBtn = document.getElementById('theme-toggle-btn');
  if (themeBtn) {
    themeBtn.textContent = next === 'dark' ? '☀️' : '🌙';
  }
}

// 3. Navigation par Onglets (5 Onglets Principaux)
function switchTab(tabId) {
  // Masquer tous les onglets
  document.querySelectorAll('.tab-content').forEach(el => el.classList.remove('active'));
  document.querySelectorAll('.nav-item').forEach(el => el.classList.remove('active'));

  // Activer l'onglet sélectionné
  const targetContent = document.getElementById(`tab-${tabId}`);
  const targetNav = document.getElementById(`nav-${tabId}`);
  if (targetContent) targetContent.classList.add('active');
  if (targetNav) targetNav.classList.add('active');

  // Remonter en haut de page
  window.scrollTo({ top: 0, behavior: 'smooth' });
}

// Sous-onglets (Pills)
function switchSubTab(parentTab, subId) {
  const container = document.getElementById(`tab-${parentTab}`);
  if (!container) return;

  container.querySelectorAll('.subtab-panel').forEach(el => el.style.display = 'none');
  container.querySelectorAll('.pill-btn').forEach(el => el.classList.remove('active'));

  const targetPanel = document.getElementById(`${parentTab}-sub-${subId}`);
  const targetBtn = document.getElementById(`pill-${parentTab}-${subId}`);
  if (targetPanel) targetPanel.style.display = 'block';
  if (targetBtn) targetBtn.classList.add('active');
}

// 4. Modales
function openModal(modalId) {
  const modal = document.getElementById(modalId);
  if (modal) modal.classList.add('active');
}

function closeModal(modalId) {
  const modal = document.getElementById(modalId);
  if (modal) modal.classList.remove('active');
}

// 5. MOTEUR CLINIQUE : Constantes Vitales
function calculateVitals() {
  const temp = parseFloat(document.getElementById('vital-temp').value) || 37.0;
  const pas = parseInt(document.getElementById('vital-pas').value) || 120;
  const pad = parseInt(document.getElementById('vital-pad').value) || 80;
  const fc = parseInt(document.getElementById('vital-fc').value) || 75;
  const fr = parseInt(document.getElementById('vital-fr').value) || 16;
  const spo2 = parseInt(document.getElementById('vital-spo2').value) || 98;
  const gly = parseFloat(document.getElementById('vital-gly').value) || 1.0;
  const weight = parseFloat(document.getElementById('vital-weight').value) || 70;
  const height = parseFloat(document.getElementById('vital-height').value) || 175;

  const pam = (pas + (2 * pad)) / 3;
  const imc = weight / Math.pow(height / 100, 2);

  let alertLevel = 'NORMAL';
  let badgeText = 'Paramètres Stables';
  let points = [];

  if (temp >= 38.5) {
    points.push({ name: 'Température', obs: `${temp}°C — Syndrome fébrile franc`, level: temp >= 39.5 ? 'CRITICAL' : 'MODERATE' });
  } else if (temp < 35.5) {
    points.push({ name: 'Température', obs: `${temp}°C — Hypothermie`, level: 'CRITICAL' });
  }

  if (pas < 90 || pam < 65) {
    points.push({ name: 'Hémodynamique', obs: `PAS ${pas} mmHg / PAM ${pam.toFixed(1)} mmHg — Hypotension artérielle / état de choc potentiel`, level: 'CRITICAL' });
  } else if (pas >= 180 || pad >= 110) {
    points.push({ name: 'Hémodynamique', obs: `PA ${pas}/${pad} mmHg — Crise hypertensive sévère`, level: 'CRITICAL' });
  } else if (pas >= 140 || pad >= 90) {
    points.push({ name: 'Hémodynamique', obs: `PA ${pas}/${pad} mmHg — Hypertension artérielle modérée`, level: 'MODERATE' });
  }

  if (spo2 < 90) {
    points.push({ name: 'Oxygénation', obs: `SpO2 ${spo2}% — Hypoxémie sévère requérant oxygénothérapie urgente`, level: 'CRITICAL' });
  } else if (spo2 <= 94) {
    points.push({ name: 'Oxygénation', obs: `SpO2 ${spo2}% — Hypoxémie modérée`, level: 'MODERATE' });
  }

  if (fr > 24) {
    points.push({ name: 'Respiration', obs: `FR ${fr}/min — Polypnée / tachypnée`, level: fr >= 30 ? 'CRITICAL' : 'MODERATE' });
  } else if (fr < 10) {
    points.push({ name: 'Respiration', obs: `FR ${fr}/min — Bradypnée critique`, level: 'CRITICAL' });
  }

  if (points.some(p => p.level === 'CRITICAL')) {
    alertLevel = 'CRITICAL';
    badgeText = 'URGENCE CLINIQUE : Anomalies Vitales Critiques';
  } else if (points.some(p => p.level === 'MODERATE')) {
    alertLevel = 'MODERATE';
    badgeText = 'Vigilance Clinique : Paramètres Perturbés';
  }

  const resultContainer = document.getElementById('vitals-result');
  resultContainer.innerHTML = `
    <div class="alert-badge ${alertLevel}">
      <span>●</span> ${badgeText}
    </div>
    <div class="form-row mb-3">
      <div class="section-callout">
        <strong>PAM (Pression Moyenne) :</strong> ${pam.toFixed(1)} mmHg
      </div>
      <div class="section-callout">
        <strong>IMC :</strong> ${imc.toFixed(1)} kg/m²
      </div>
    </div>
    <h4 style="font-size: 14px; font-weight: 700; margin-bottom: 8px;">Points Systématiques d'Examen :</h4>
    ${points.length > 0 ? points.map(p => `
      <div class="section-callout ${p.level === 'CRITICAL' ? 'danger' : 'warning'}">
        <strong>${p.name} :</strong> ${p.obs}
      </div>
    `).join('') : '<div class="section-callout">Tous les paramètres vitaux analysés sont physiologiques.</div>'}
  `;
  resultContainer.style.display = 'block';
}

// 6. MOTEUR CLINIQUE : Interprétation Complète NFS en 6 Parties
function calculateNFS() {
  const hb = parseFloat(document.getElementById('nfs-hb').value) || 13.5;
  const ht = parseFloat(document.getElementById('nfs-ht').value) || 40.0;
  const gr = parseFloat(document.getElementById('nfs-gr').value) || 4.5;
  const gb = parseFloat(document.getElementById('nfs-gb').value) || 7.0;
  const pnn = parseFloat(document.getElementById('nfs-pnn').value) || 4.2;
  const lympho = parseFloat(document.getElementById('nfs-lympho').value) || 2.0;
  const plaquettes = parseFloat(document.getElementById('nfs-plt').value) || 250.0;
  const retic = parseFloat(document.getElementById('nfs-retic').value) || null;

  const vgm = (ht * 10) / gr;
  const ccmh = (hb / ht) * 100;

  let isAnemia = hb < 12.0;
  let isMicrocytic = vgm < 80.0;
  let isMacrocytic = vgm > 100.0;
  let isHypochromic = ccmh < 32.0;
  let isThrombopenia = plaquettes < 150.0;
  let isThrombocytosis = plaquettes > 400.0;
  let isHyperleukocytosis = gb > 10.0;
  let isLeukopenia = gb < 4.0;
  let isNeutropenia = pnn < 1.5;
  let isAgranulocytosis = pnn < 0.5;

  let alertLevel = 'NORMAL';
  if (hb < 8.0 || isAgranulocytosis || plaquettes < 20.0) {
    alertLevel = 'CRITICAL';
  } else if (isAnemia || isThrombopenia || isThrombocytosis || isHyperleukocytosis || isLeukopenia) {
    alertLevel = 'MODERATE';
  }

  // 1. Synthèse globale
  let conclusionText = "";
  if (!isAnemia && !isHyperleukocytosis && !isLeukopenia && !isThrombopenia && !isThrombocytosis) {
    conclusionText = "La NFS ne montre pas d'anomalie hématologique significative. Les trois lignées sanguines présentent des valeurs globalement dans les intervalles de référence.";
  } else if (isAnemia && isThrombopenia && isLeukopenia) {
    conclusionText = "La NFS a objectivé une pancytopénie associant une anémie, une leucopénie et une thrombopénie, nécessitant une exploration médullaire urgente.";
  } else if (isAnemia && isThrombopenia) {
    conclusionText = "La NFS a objectivé une bicytopénie associant une anémie et une thrombopénie avec conservation relative de la lignée blanche.";
  } else if (isAnemia && isThrombocytosis) {
    conclusionText = `La NFS a objectivé une anémie ${isMicrocytic ? 'microcytaire' : 'normocytaire'} associée à une thrombocytose réactionnelle évoquant en priorité une carence martiale ou un syndrome inflammatoire.`;
  } else if (isAnemia) {
    conclusionText = `La NFS a objectivé une anémie ${isMicrocytic ? 'microcytaire' : isMacrocytic ? 'macrocytaire' : 'normocytaire'} ${isHypochromic ? 'hypochrome' : 'normochrome'} isolée avec conservation des lignées blanche et plaquettaire.`;
  } else if (isHyperleukocytosis) {
    conclusionText = `La NFS a objectivé une hyperleucocytose (${gb} G/L) à prédominance neutrophile avec conservation des lignées rouge et plaquettaire.`;
  } else if (isThrombopenia) {
    conclusionText = `La NFS a objectivé une thrombopénie isolée (${plaquettes} G/L) sans anomalie significative des autres lignées sanguines.`;
  } else {
    conclusionText = "La NFS présente des variations hématologiques modérées à intégrer au contexte clinique.";
  }

  // 2. Lignées
  const redSummary = isAnemia 
    ? `Anémie ${isMicrocytic ? 'microcytaire (VGM ' + vgm.toFixed(1) + ' fL)' : isMacrocytic ? 'macrocytaire' : 'normocytaire'}, ${isHypochromic ? 'hypochrome' : 'normochrome'}${retic ? (retic < 120 ? ' arégénérative' : ' régénérative') : ''}.`
    : `Lignée rouge normale (Hb ${hb} g/dL, VGM ${vgm.toFixed(1)} fL).`;

  const whiteSummary = isAgranulocytosis
    ? `AGRANULOCYTOSE MAJEURE (PNN ${pnn} G/L < 0.5 G/L) — Risque septique vital immédiat !`
    : isHyperleukocytosis
      ? `Hyperleucocytose à ${gb} G/L avec ${pnn} G/L de PNN.`
      : `Lignée blanche normale (GB ${gb} G/L, PNN ${pnn} G/L).`;

  const pltSummary = isThrombopenia
    ? `Thrombopénie à ${plaquettes} G/L ${plaquettes < 50 ? '(Risque hémorragique accru)' : ''}.`
    : isThrombocytosis
      ? `Thrombocytose à ${plaquettes} G/L (réactionnelle ou primitive).`
      : `Plaquettes dans les normes (${plaquettes} G/L).`;

  // 3. Orientations étiologiques
  let orientations = [];
  if (isAnemia && isMicrocytic) orientations.push("Carence martiale (saignement occulte, carence d'apport)", "Syndrome inflammatoire chronique", "Thalassémie mineure");
  if (isAnemia && isMacrocytic) orientations.push("Carence en vitamine B12 ou folates", "Hépatopathie chronique / éthylisme", "Myélodysplasie");
  if (isHyperleukocytosis) orientations.push("Infection bactérienne systémique aiguë", "Syndrome inflammatoire ou nécrose tissulaire", "Stress métabolique aigu");
  if (isAgranulocytosis) orientations.push("Toxidermie médicamenteuse aiguë", "Infection virale sévère", "Aplasie médullaire");
  if (orientations.length === 0) orientations.push("Paramètres hématologiques dans les limites usuelles");

  const resultContainer = document.getElementById('nfs-result');
  resultContainer.innerHTML = `
    <div class="alert-badge ${alertLevel}">
      <span>●</span> Synthèse Médicale NFS
    </div>

    <div class="section-callout" style="border-left-color: #1976D2; background: rgba(25, 118, 210, 0.08);">
      <h4 style="color: var(--primary); font-weight: 700; margin-bottom: 4px;">1. Conclusion & Interprétation Générale :</h4>
      <p style="font-weight: 600; font-size: 14.5px;">${conclusionText}</p>
    </div>

    <div class="card mb-3" style="padding: 14px;">
      <h4 style="font-size: 13.5px; font-weight: 700; margin-bottom: 8px;">2. Analyse Systématique des Lignées :</h4>
      <p class="mb-2"><strong>🔴 Lignée Rouge :</strong> ${redSummary}</p>
      <p class="mb-2"><strong>⚪ Lignée Blanche :</strong> ${whiteSummary}</p>
      <p><strong>🟡 Lignée Plaquettaire :</strong> ${pltSummary}</p>
    </div>

    <div class="card mb-3" style="padding: 14px;">
      <h4 style="font-size: 13.5px; font-weight: 700; margin-bottom: 8px;">3. Orientations Étiologiques Principales :</h4>
      <ul style="padding-left: 20px; font-size: 13.5px;">
        ${orientations.map(o => `<li class="mb-2">${o}</li>`).join('')}
      </ul>
    </div>

    <div class="section-callout warning">
      <strong>4. Conduite à Tenir Clinique :</strong>
      ${isAgranulocytosis ? "Isolement protecteur immédiat, antibiothérapie probabiliste urgente si fièvre, arrêt des médicaments suspects." :
        isAnemia ? "Doser le bilan martial (Ferritine, coefficient de saturation de la transferrine) et la CRP avant toute supplémentation." :
        "Poursuivre la surveillance biologique en fonction de l'évolution clinique."}
    </div>
  `;
  resultContainer.style.display = 'block';
}

// 7. MOTEUR CLINIQUE : Score de Glasgow
function calculateGlasgow() {
  const e = parseInt(document.getElementById('gcs-e').value) || 4;
  const v = parseInt(document.getElementById('gcs-v').value) || 5;
  const m = parseInt(document.getElementById('gcs-m').value) || 6;
  const total = e + v + m;

  let severity = "Score normal / Coma absent";
  let alert = "NORMAL";
  let pearl = "État de conscience physiologique.";

  if (total <= 8) {
    severity = "Coma sévère — Indication formelle à l'intubation trachéale protectrice";
    alert = "CRITICAL";
    pearl = "Glasgow <= 8 : risque majeur d'inhalation bronchique et perte des réflexes de déglutition.";
  } else if (total <= 12) {
    severity = "Altération modérée de la conscience / Somnolence pathologique";
    alert = "MODERATE";
    pearl = "Surveillance neuro rapprochée toutes les heures, recherche d'un foyer neurologique ou métabolique.";
  }

  const resultContainer = document.getElementById('gcs-result');
  resultContainer.innerHTML = `
    <div class="alert-badge ${alert}">
      <span>●</span> Score de Glasgow : ${total} / 15
    </div>
    <div class="section-callout ${alert === 'CRITICAL' ? 'danger' : 'warning'}">
      <strong>Interprétation :</strong> ${severity}
    </div>
    <div class="section-callout">
      <strong>Règle Sémiologique :</strong> ${pearl}
    </div>
  `;
  resultContainer.style.display = 'block';
}

// 8. BANQUE DE QUIZ SÉMIOLOGIQUES (90+ Questions)
const quizData = [
  // Cardiovasculaire
  {
    cat: 'cardio',
    q: "Quelle est la caractéristique fondamentale d'une douleur angineuse typique d'effort ?",
    opts: [
      "Douleur basithoracique exacerbée par la toux",
      "Douleur rétrosternale constrictive irradiant à la mâchoire et au bras gauche, calmée par l'arrêt de l'effort ou la trinitrine",
      "Douleur punctiforme sous-mamelonnaire augmentée par la palpation",
      "Douleur postprandiale précoce calmée par les pansements gastriques"
    ],
    correct: 1,
    exp: "L'angor typique réalise une striction médiosternale angoissante survenant à l'effort et cédant rapidement en moins de 3 minutes au repos ou sous dérivés nitrés."
  },
  {
    cat: 'cardio',
    q: "Quel signe physique signe une insuffisance cardiaque droite décompensée ?",
    opts: [
      "Râles crépitants aux deux bases pulmonaires",
      "Turgescence jugulaire spontanée avec reflux hépato-jugulaire et œdèmes des membres inférieurs prenant le godet",
      "Souffle diastolique au foyer aortique",
      "Érythème noueux des membres inférieurs"
    ],
    correct: 1,
    exp: "La stase veineuse systémique d'aval entraîne une hyperpression dans la veine cave supérieure (turgescence jugulaire) et inférieure (foie cardiaque et œdèmes des membres inférieurs)."
  },
  {
    cat: 'cardio',
    q: "Quelle est la définition auscultatoire du souffle de rétrécissement aortique ?",
    opts: [
      "Souffle holosystolique en jet de vapeur à l'apex irradiant vers l'aisselle",
      "Souffle mésosystolique éjectionnel râpeux au foyer aortique irradiant vers les carotides",
      "Souffle diastolique doux humé au foyer pulmonaire",
      "Roulement mésodiastolique avec éclat du premier bruit à l'apex"
    ],
    correct: 1,
    exp: "Le RA s'exprime par un souffle systolique rude, éjectionnel, maximal en mésosystole, prédominant au 2e espace intercostal droit et irradiant typiquement dans les vaisseaux du cou."
  },

  // Respiratoire
  {
    cat: 'resp',
    q: "La triade auscultatoire du syndrome d'épanchement pleural liquidien (pleurésie) comporte :",
    opts: [
      "Augmentation des vibrations vocales, tympanisme et râles crépitants",
      "Abolition des vibrations vocales, matité franche à la percussion et abolition du murmure vésiculaire",
      "Souffle tubaire pur, râles sibilants et tirage intercostal",
      "Frottement péricardique et toux sèche positionnelle"
    ],
    correct: 1,
    exp: "Le liquide pleural interposé atténue la transmission sonore des cordes vocales (abolition des vibrations vocales et du murmure) et produit une matité pierreuse déclive à concavité supérieure."
  },
  {
    cat: 'resp',
    q: "Quel râle auscultatoire caractérise le bronchospasme dans la crise d'asthme ?",
    opts: [
      "Râles crépitants télé-inspiratoires fins",
      "Râles sibilants diffus expiratoires comparables à des sifflements",
      "Frottement pleural rude aux deux temps",
      "Gargouillements abdominaux transmis"
    ],
    correct: 1,
    exp: "Le rétrécissement du calibre bronchiolaire par bronchospasme et œdème muqueux produit des bruits musicaux continus aigus (sibilances) prédominant nettement à l'expiration prolongée."
  },

  // Digestif
  {
    cat: 'digestif',
    q: "Le signe de Murphy positif oriente en premier lieu vers :",
    opts: [
      "Une appendicite aiguë pelvienne",
      "Une cholécystite aiguë lithiasique par arrêt respiratoire lors de la palpation de l'hypochondre droit",
      "Une pancréatite aiguë nécrosante",
      "Une occlusion mécanique sur bride"
    ],
    correct: 1,
    exp: "La douleur vive déclenchée par la palpation sous-hépatique lors d'une inspiration profonde bloque brutalement celle-ci : c'est l'inhibition respiratoire signant le Murphy vésiculaire."
  },
  {
    cat: 'digestif',
    q: "Une hématémèse se définit précisément comme :",
    opts: [
      "Une émission de sang rouge par l'anus",
      "L'expectoration de sang rouge provenant des voies respiratoires sous-glottiques",
      "L'expulsion par la bouche de sang rouge ou noirâtre au cours d'un effort de vomissement",
      "Une rectorragie d'origine haute avec diarrhée aqueuse"
    ],
    correct: 2,
    exp: "L'hématémèse est un vomissement de sang provenant de l'appareil digestif supérieur (au-dessus de l'angle de Treitz), à bien distinguer de l'hémoptysie (expectoration respiratoire lors d'un effort de toux)."
  },

  // Neurologique
  {
    cat: 'neuro',
    q: "Le syndrome méningé complet associe typiquement :",
    opts: [
      "Céphalées en casque, photophobie, vomissements en jet et raideur de nuque (signes de Kernig et Brudzinski)",
      "Hémiplégie brachio-faciale avec aphasie de Broca",
      "Tremblement de repos unilatéral et akinésie",
      "Crise comitiale généralisée sans anomalie céphalique"
    ],
    correct: 0,
    exp: "L'irritation des racines méningées par une infection ou une hémorragie méningée se traduit par la triade fonctionnelle (céphalées vives, photophobie, vomissements faciles sans nausée) et les contractures toniques réflexes (raideur de nuque)."
  },
  {
    cat: 'neuro',
    q: "Le signe de Babinski (réflexe cutanéoplantaire en extension) signe l'atteinte de :",
    opts: [
      "La corne antérieure de la moelle épinière",
      "Le faisceau pyramidal (voie corticospinale motrice)",
      "Le nerf sciatique poplité externe",
      "Le système cérébelleux statique"
    ],
    correct: 1,
    exp: "L'élévation lente et majestueuse du gros orteil lors de la stimulation du bord externe de la plante du pied est le signe pathognomonique de la lésion du motoneurone central (syndrome pyramidal)."
  }
];

let currentQuizIndex = 0;
let userAnswers = {};

function initQuiz(category = 'all') {
  const filtered = category === 'all' ? quizData : quizData.filter(q => q.cat === category);
  const container = document.getElementById('quiz-questions-list');
  if (!container) return;

  container.innerHTML = filtered.map((item, index) => `
    <div class="quiz-question-card" id="quiz-card-${index}">
      <span class="student-badge mb-2">Question ${index + 1} / ${filtered.length}</span>
      <h3 style="font-size: 15px; font-weight: 700; margin-bottom: 14px;">${item.q}</h3>
      <div class="options-group">
        ${item.opts.map((opt, oIndex) => `
          <div class="quiz-option" onclick="selectQuizAnswer(${index}, ${oIndex}, ${item.correct}, '${escapeHtml(item.exp)}')">
            <span style="font-weight: 700; min-width: 20px;">${['A', 'B', 'C', 'D'][oIndex]}.</span>
            <span>${opt}</span>
          </div>
        `).join('')}
      </div>
      <div id="quiz-feedback-${index}" style="display: none;" class="quiz-explanation"></div>
    </div>
  `).join('');
}

function selectQuizAnswer(qIndex, selectedIndex, correctIndex, explanation) {
  const card = document.getElementById(`quiz-card-${qIndex}`);
  if (!card) return;

  const options = card.querySelectorAll('.quiz-option');
  options.forEach((opt, idx) => {
    opt.onclick = null; // Désactiver après réponse
    if (idx === correctIndex) {
      opt.classList.add('selected-correct');
    } else if (idx === selectedIndex) {
      opt.classList.add('selected-wrong');
    }
  });

  const feedback = document.getElementById(`quiz-feedback-${qIndex}`);
  if (feedback) {
    const isCorrect = selectedIndex === correctIndex;
    feedback.innerHTML = `
      <strong style="color: ${isCorrect ? 'var(--success)' : 'var(--danger)'};">
        ${isCorrect ? '✓ Excellente réponse !' : '✗ Réponse incorrecte.'}
      </strong>
      <p style="margin-top: 6px;">${explanation}</p>
    `;
    feedback.style.display = 'block';
  }
}

function escapeHtml(str) {
  return str.replace(/'/g, "\\'").replace(/"/g, '&quot;');
}

// 9. Initialisation au chargement de la page
document.addEventListener('DOMContentLoaded', () => {
  initTheme();
  initQuiz('all');

  // Affichage du nom d'étudiant
  const studentNameEl = document.getElementById('hero-student-name');
  if (studentNameEl) studentNameEl.textContent = currentProfile.name;

  // Enregistrement du Service Worker pour support Offline PWA
  if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('./sw.js')
      .then(() => console.log('EL VITA PWA: Service Worker enregistré avec succès (Mode Hors Connexion prêt)'))
      .catch((err) => console.log('Service Worker notice:', err));
  }
});

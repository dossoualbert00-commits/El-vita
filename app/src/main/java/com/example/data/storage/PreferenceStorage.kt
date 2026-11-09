package com.example.data.storage

import android.content.Context
import android.content.SharedPreferences

/**
 * Interface d'abstraction multiplateforme pour le stockage clé-valeur (préférences).
 * Permet d'utiliser SharedPreferences sous Android et NSUserDefaults sous iOS.
 */
interface PreferenceStorage {
  fun getString(key: String, default: String? = null): String?
  fun putString(key: String, value: String)
  fun getBoolean(key: String, default: Boolean = false): Boolean
  fun putBoolean(key: String, value: Boolean)
}

/**
 * Implémentation Android native basée sur SharedPreferences.
 */
class AndroidSharedPrefsStorage(context: Context) : PreferenceStorage {
  private val prefs: SharedPreferences =
    context.getSharedPreferences("elvita_student_profile_prefs", Context.MODE_PRIVATE)

  override fun getString(key: String, default: String?): String? {
    return prefs.getString(key, default) ?: default
  }

  override fun putString(key: String, value: String) {
    prefs.edit().putString(key, value).apply()
  }

  override fun getBoolean(key: String, default: Boolean): Boolean {
    return prefs.getBoolean(key, default)
  }

  override fun putBoolean(key: String, value: Boolean) {
    prefs.edit().putBoolean(key, value).apply()
  }
}

/**
 * Implémentation mémoire / multiplateforme pour les environnements de test,
 * prévisualisation ou cibles sans Context Android direct.
 */
class InMemoryPreferenceStorage : PreferenceStorage {
  private val stringMap = mutableMapOf<String, String>()
  private val boolMap = mutableMapOf<String, Boolean>()

  override fun getString(key: String, default: String?): String? {
    return stringMap[key] ?: default
  }

  override fun putString(key: String, value: String) {
    stringMap[key] = value
  }

  override fun getBoolean(key: String, default: Boolean): Boolean {
    return boolMap[key] ?: default
  }

  override fun putBoolean(key: String, value: Boolean) {
    boolMap[key] = value
  }
}

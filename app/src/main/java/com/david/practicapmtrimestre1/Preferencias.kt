package com.david.practicapmtrimestre1

import android.content.Context
import androidx.activity.result.launch
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {

    private val sharedPrefs = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE)

    val countdownDuration: Int
        get() = sharedPrefs.getInt("cuenta_atras_dur", 20)

    val animationEnabled: Boolean
        get() = sharedPrefs.getBoolean("animacion", false)

    val operators: String
        get() = sharedPrefs.getString("operaciones", "+,-") ?: "+,-"

    val maxOperatorValue: Int
        get() = sharedPrefs.getInt("max_operador_valor", 10)

    val minOperatorValue: Int
        get() = sharedPrefs.getInt("min_operador_valor", 1)


    fun updateCountdownDuration(duration: Int) {
        sharedPrefs.edit().putInt("cuenta_atras_dur", duration).apply()
    }

    fun updateAnimationEnabled(enabled: Boolean) {
        sharedPrefs.edit().putBoolean("animacion", enabled).apply()
    }

    fun updateOperators(operators: String) {
        sharedPrefs.edit().putString("operaciones", operators).apply()
    }

    fun updateMaxOperatorValue(value: Int) {
        sharedPrefs.edit().putInt("max_operador_valor", value).apply()
    }

    fun updateMinOperatorValue(value: Int) {
        sharedPrefs.edit().putInt("min_operador_valor", value).apply()
    }
}
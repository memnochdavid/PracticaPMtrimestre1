package com.david.practicapmtrimestre1

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {

    companion object {
        val COUNTDOWN_DURATION = intPreferencesKey("cuenta_atras_dur")
        val ANIMATION_ENABLED = booleanPreferencesKey("animacion")
        val OPERATORS = stringPreferencesKey("operaciones")
        val MAX_OPERATOR_VALUE = intPreferencesKey("max_operador_valor")
    }

    val countdownDuration: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[COUNTDOWN_DURATION] ?: 20
    }

    val animationEnabled: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[ANIMATION_ENABLED] ?: false
    }

    val operators: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[OPERATORS] ?: "+,-"
    }

    val maxOperatorValue: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[MAX_OPERATOR_VALUE] ?: 10
    }

    suspend fun updateCountdownDuration(duration: Int) {
        context.dataStore.edit { preferences ->
            preferences[COUNTDOWN_DURATION] = duration
        }
    }

    suspend fun updateAnimationEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ANIMATION_ENABLED] = enabled
        }
    }

    suspend fun updateOperators(operators: String) {
        context.dataStore.edit { preferences ->
            preferences[OPERATORS] = operators
        }
    }

    suspend fun updateMaxOperatorValue(value: Int) {
        context.dataStore.edit { preferences ->
            preferences[MAX_OPERATOR_VALUE] = value
        }
    }
}
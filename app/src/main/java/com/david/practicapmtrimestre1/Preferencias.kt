package com.david.practicapmtrimestre1

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource

class SettingsDataStore(private val context: Context) {

    //carga desde el xml de preferencias
    val resources = context.resources
    val countdownDurationDef = resources.getInteger(R.integer.cuenta_atras_dur)
    val animationEnabledDef = resources.getBoolean(R.bool.animacion)
    val operationsDef = resources.getString(R.string.operaciones)
    val maxOperandValueDef = resources.getInteger(R.integer.max_operador_valor)
    val minOperandValueDef = resources.getInteger(R.integer.min_operador_valor)
    ///////////////

    private val sharedPrefs = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE)

    val countdownDuration: Int
        get() = sharedPrefs.getInt("cuenta_atras_dur", countdownDurationDef)

    val animationEnabled: Boolean
        get() = sharedPrefs.getBoolean("animacion", animationEnabledDef)

    val operators: String
        get() = sharedPrefs.getString("operaciones", operationsDef) ?: operationsDef

    val maxOperatorValue: Int
        get() = sharedPrefs.getInt("max_operador_valor", maxOperandValueDef)

    val minOperatorValue: Int
        get() = sharedPrefs.getInt("min_operador_valor", minOperandValueDef)


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

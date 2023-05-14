package com.exirium.pe.exiriumviewmodelkit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


open class BaseViewModel : ViewModel() {
    private val fieldStates = mutableMapOf<Enum<*>, MutableState<Any?>>()

    fun <T : Enum<T>> registerField(enumClass: Class<T>) {
        enumClass.enumConstants?.let { constants ->
            constants.forEach { constant ->
                fieldStates[constant] = mutableStateOf(null)
            }
        }
    }

    fun onValueChanged(id: Enum<*>, newValue: Any?) {
        fieldStates[id]?.value = newValue
    }

    fun getFieldValue(id: Enum<*>): Any? {
        return fieldStates[id]?.value
    }
}

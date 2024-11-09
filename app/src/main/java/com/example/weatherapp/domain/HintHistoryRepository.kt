package com.example.weatherapp.domain

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.weatherapp.domain.model.Hint
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HintHistoryRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)
    private val hintListKey = stringPreferencesKey(PREFERENCES_KEY)

    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val jsonAdapter: JsonAdapter<List<Hint>> = moshi.adapter(
        Types.newParameterizedType(List::class.java, Hint::class.java)
    )

    suspend fun saveHint(hint: Hint) {
        val currentList = getHints().firstOrNull() ?: emptyList()
        if (currentList.contains(hint)) {
            return
        }
        val updatedList = currentList.toMutableList().apply {
            add(hint)
        }
        val json = jsonAdapter.toJson(updatedList)
        context.dataStore.edit { preferences ->
            preferences[hintListKey] = json
        }
    }

    fun getHints(): Flow<List<Hint>> =
        context.dataStore.data
            .map { preferences ->
                val json = preferences[hintListKey] ?: EMPTY_MAP
                jsonAdapter.fromJson(json) ?: emptyList()
            }

    companion object {
        const val PREFERENCES_NAME = "hint_history"
        const val PREFERENCES_KEY = "hint_list"
        const val EMPTY_MAP = "[]"
    }
}

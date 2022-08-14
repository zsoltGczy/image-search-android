package com.gzslt.imagesearch.data.preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QueryPreferences @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences(QUERY_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun saveQuery(query: String) {
        sharedPreferences.edit().putString(QUERY_PREFERENCES_KEY, query).apply()
    }

    fun getSavedQuery() = sharedPreferences.getString(QUERY_PREFERENCES_KEY, null)

    companion object {
        private const val QUERY_PREFERENCES_NAME = "query"
        private const val QUERY_PREFERENCES_KEY = "query_key"
    }
}

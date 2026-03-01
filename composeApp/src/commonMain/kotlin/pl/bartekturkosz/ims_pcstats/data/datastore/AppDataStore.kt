package pl.bartekturkosz.ims_pcstats.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath
import pl.bartekturkosz.ims_pcstats.data.model.UserCredentials

fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )

internal const val dataStoreFileName = "prefs.preferences_pb"

class ApplicationPreferences(private val preferences: DataStore<Preferences>) {

    suspend fun saveCredentials(credentials: UserCredentials) {
        preferences.edit {
            it[LOGIN] = credentials.login
            it[PASSWORD] = credentials.password
            it[URL] = credentials.url
        }
    }

    suspend fun getCredentials() = preferences.data.map {
        UserCredentials(
            login = it[LOGIN] ?: return@map null,
            password = it[PASSWORD] ?: return@map null,
            url = it[URL] ?: return@map null,
        )
    }.firstOrNull()

    companion object {
        private val LOGIN = stringPreferencesKey("pref_login")
        private val PASSWORD = stringPreferencesKey("pref_password")
        private val URL = stringPreferencesKey("pref_url")
    }
}
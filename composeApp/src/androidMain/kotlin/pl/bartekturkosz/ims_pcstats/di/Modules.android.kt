package pl.bartekturkosz.ims_pcstats.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.bartekturkosz.ims_pcstats.data.AndroidMqttClientService
import pl.bartekturkosz.ims_pcstats.data.MqttClientService
import pl.bartekturkosz.ims_pcstats.data.datastore.createDataStore

actual val platformModule = module {
    singleOf(::AndroidMqttClientService).bind<MqttClientService>()
    single<DataStore<Preferences>> { createDataStore(androidContext()) }
}
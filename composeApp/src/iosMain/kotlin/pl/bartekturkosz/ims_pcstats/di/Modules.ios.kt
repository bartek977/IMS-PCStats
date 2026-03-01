package pl.bartekturkosz.ims_pcstats.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.bartekturkosz.ims_pcstats.data.IosMqttClientService
import pl.bartekturkosz.ims_pcstats.data.MqttClientService
import pl.bartekturkosz.ims_pcstats.data.database.AppDatabase
import pl.bartekturkosz.ims_pcstats.data.database.getDatabaseBuilder
import pl.bartekturkosz.ims_pcstats.data.datastore.createDataStore

actual val platformModule = module {
    singleOf(::IosMqttClientService).bind<MqttClientService>()
    single<DataStore<Preferences>> { createDataStore() }
    single<RoomDatabase.Builder<AppDatabase>> { getDatabaseBuilder() }
}
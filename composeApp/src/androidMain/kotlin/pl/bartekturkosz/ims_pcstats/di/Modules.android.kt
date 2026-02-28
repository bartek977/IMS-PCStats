package pl.bartekturkosz.ims_pcstats.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.bartekturkosz.ims_pcstats.data.AndroidMqttClientService
import pl.bartekturkosz.ims_pcstats.data.MqttClientService

actual val platformModule = module {
    singleOf(::AndroidMqttClientService).bind<MqttClientService>()
}
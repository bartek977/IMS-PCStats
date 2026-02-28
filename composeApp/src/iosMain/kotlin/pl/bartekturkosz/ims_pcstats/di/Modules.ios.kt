package pl.bartekturkosz.ims_pcstats.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pl.bartekturkosz.ims_pcstats.data.IosMqttClientService
import pl.bartekturkosz.ims_pcstats.data.MqttClientService

actual val platformModule = module {
    singleOf(::IosMqttClientService).bind<MqttClientService>()
}
package pl.bartekturkosz.ims_pcstats.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import pl.bartekturkosz.ims_pcstats.presentation.login.LoginViewModel

val commonModule = module {
    viewModelOf(::LoginViewModel)
}
package pl.bartekturkosz.ims_pcstats.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import pl.bartekturkosz.ims_pcstats.presentation.login.LoginViewModel

expect val platformModule: Module

val commonModule = module {
    viewModelOf(::LoginViewModel)
}
package pl.bartekturkosz.ims_pcstats.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import pl.bartekturkosz.ims_pcstats.data.database.Database
import pl.bartekturkosz.ims_pcstats.data.datastore.ApplicationPreferences
import pl.bartekturkosz.ims_pcstats.presentation.dashboard.DashboardViewModel
import pl.bartekturkosz.ims_pcstats.presentation.login.LoginViewModel

expect val platformModule: Module

val commonModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::DashboardViewModel)
    singleOf(::ApplicationPreferences)
    singleOf(::Database)
}
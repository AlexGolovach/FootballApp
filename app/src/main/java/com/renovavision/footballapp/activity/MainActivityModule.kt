package com.renovavision.footballapp.activity

import androidx.lifecycle.ViewModelProvider
import com.renovavision.footballapp.inject.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @Binds
    fun viewModelProviderFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(
        modules = [
            FragmentsModule::class,
            ViewModelsModule::class,
            NavHostFragmentModule::class
        ]
    )
    fun mainActivity(): MainActivity
}
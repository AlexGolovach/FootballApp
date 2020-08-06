package com.renovavision.footballapp.app

import android.app.Application
import com.renovavision.footballapp.activity.FragmentsModule
import com.renovavision.footballapp.activity.MainActivityModule
import com.renovavision.footballapp.activity.NavigationModule
import com.renovavision.footballapp.activity.ViewModelsModule
import com.renovavision.footballapp.data.NetworkModule
import com.renovavision.footballapp.data.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        NavigationModule::class,
        ViewModelsModule::class,
        FragmentsModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
@Singleton
interface AppInjector {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application,
            @BindsInstance @Named("apiUrl")
            apiUrl: String,
            @BindsInstance @Named("cacheDir")
            cacheDir: File?
        ): AppInjector
    }

    fun inject(app: App)
}
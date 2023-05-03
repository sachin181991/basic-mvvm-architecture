package com.basic.architecture

import android.app.Application
import com.basic.architecture.data.network.repositories.MainActivityRepository
import com.basic.architecture.factory.MainActivityViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class BasicArchitecture : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@BasicArchitecture))

        bind() from singleton { MainActivityRepository(instance()) }
        bind() from provider { MainActivityViewModelFactory(instance()) }

    }
}
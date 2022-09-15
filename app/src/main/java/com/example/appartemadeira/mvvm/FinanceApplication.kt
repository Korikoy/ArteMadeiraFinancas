package com.example.appartemadeira.mvvm

import android.app.Application
import com.example.appartemadeira.mvvm.di.financeModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

class FinanceApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                financeModule
            )
        }
    }
}
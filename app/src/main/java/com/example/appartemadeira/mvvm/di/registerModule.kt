package com.example.appartemadeira.mvvm.di

import com.example.appartemadeira.mvvm.data.finance.RegisterRepository
import com.example.appartemadeira.mvvm.domain.register.RegisterBusiness
import com.example.appartemadeira.mvvm.presentation.register.viewmodel.RegisterViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registerModule = module {
    single { RegisterBusiness(get()) }
    single { RegisterRepository(get()) }
    single { FirebaseFirestore.getInstance() }
    viewModel { RegisterViewModel(get()) }
}
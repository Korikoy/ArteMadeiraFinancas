package com.example.appartemadeira.mvvm.di

import com.example.appartemadeira.mvvm.data.finance.FinanceRepository
import com.example.appartemadeira.mvvm.domain.finance.FinanceBusiness
import com.example.appartemadeira.mvvm.presentation.finance.viewmodel.FinanceViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val financeModule = module {
    single { FinanceBusiness(get()) }
    single { FinanceRepository(get()) }
    single { FirebaseFirestore.getInstance() }
    viewModel { FinanceViewModel(get()) }
}
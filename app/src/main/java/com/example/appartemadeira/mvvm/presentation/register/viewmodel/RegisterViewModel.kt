package com.example.appartemadeira.mvvm.presentation.register.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appartemadeira.mvvm.data.finance.model.Bills

import com.example.appartemadeira.mvvm.domain.register.RegisterBusiness

class RegisterViewModel(
    private val registerBusiness: RegisterBusiness
):ViewModel() {
        fun saveBills(bills: Bills){
        registerBusiness.saveBills(bills)
    }
}
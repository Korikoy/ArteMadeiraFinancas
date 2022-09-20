package com.example.appartemadeira.mvvm.domain.register

import com.example.appartemadeira.mvvm.data.finance.FinanceRepository
import com.example.appartemadeira.mvvm.data.finance.RegisterRepository
import com.example.appartemadeira.mvvm.data.finance.model.Bills

class RegisterBusiness(
    private val registerRepository: RegisterRepository
) {
        fun saveBills(bills: Bills){
            if(bills.type == "Contas a pagar"){
                registerRepository.saveBills(bills,"BillsToPay")
            }else{
                registerRepository.saveBills(bills,"BillsToRecive")
            }
    }
}
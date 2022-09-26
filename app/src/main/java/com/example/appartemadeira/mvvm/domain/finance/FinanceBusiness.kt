package com.example.appartemadeira.mvvm.domain.finance

import com.example.appartemadeira.mvvm.data.finance.FinanceRepository
import com.example.appartemadeira.mvvm.data.finance.model.Bills

class FinanceBusiness(
    private val financeRepository : FinanceRepository
) {

    suspend fun getBillsToPay(): MutableList<Bills> {
        return financeRepository.getBills("BillsToPay")
    }

    suspend fun getBillsToReceive(): MutableList<Bills> {
        return financeRepository.getBills("BillsToRecive")
    }
     fun deleteBills(bills: Bills){
        if(bills.type == "Contas a pagar"){
        financeRepository.deleteBills(bills,"BillsToPay")
        }else{
            financeRepository.deleteBills(bills,"BillsToRecive")
        }

    }
    fun atualizeBills(bills: Bills){
        if(bills.type == "Contas a pagar"){
            financeRepository.atualizeBills(bills,"BillsToPay")
        }else{
            financeRepository.atualizeBills(bills,"BillsToRecive")
        }
    }
}
package com.example.appartemadeira.mvvm.presentation.finance.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appartemadeira.mvvm.data.finance.model.Bills
import com.example.appartemadeira.mvvm.domain.finance.FinanceBusiness
import kotlinx.coroutines.launch

class FinanceViewModel(
    private val financeBusiness : FinanceBusiness
) : ViewModel() {


    val billsToPayLiveData = MutableLiveData<List<Bills>>()
    val billsToReceiveLiveData = MutableLiveData<List<Bills>>()

    fun getBillsToPay() {
        viewModelScope.launch { billsToPayLiveData.value = financeBusiness.getBillsToPay() }
    }

    fun getBillsToReceive() {
        viewModelScope.launch { billsToReceiveLiveData.value = financeBusiness.getBillsToReceive() }
    }
    fun atualizeBills(bills: Bills){
        financeBusiness.atualizeBills(bills)
    }
    fun deleteBills(bills: Bills){
        financeBusiness.deleteBills(bills)

    }
}
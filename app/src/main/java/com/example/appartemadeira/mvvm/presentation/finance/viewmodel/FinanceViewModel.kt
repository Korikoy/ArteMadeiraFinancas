package com.example.appartemadeira.mvvm.presentation.finance.viewmodel

import android.system.Os.remove
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
    fun updateBills(bills: Bills){
        financeBusiness.updateBills(bills)
    }
    fun deleteBills(bills: Bills){
        financeBusiness.deleteBills(bills)
        val listUpdate: MutableList<Bills> = (billsToPayLiveData.value as MutableList<Bills>?)!!
        for(i in listUpdate){
            if(i.bid == bills.bid){
                listUpdate.remove(i)
                viewModelScope.launch{billsToPayLiveData.value = listUpdate}
            }
        }
        val listUpdate2: MutableList<Bills> = (billsToReceiveLiveData.value as MutableList<Bills>?)!!
        for(i in listUpdate2){
            if(i.bid == bills.bid){
                listUpdate2.remove(i)
                viewModelScope.launch{billsToReceiveLiveData.value = listUpdate}
            }
        }


    }

}
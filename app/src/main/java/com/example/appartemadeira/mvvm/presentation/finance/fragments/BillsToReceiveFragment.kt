package com.example.appartemadeira.mvvm.presentation.finance.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appartemadeira.databinding.FragmentBillsToReceiveBinding
import com.example.appartemadeira.mvvm.data.finance.model.Bills
import com.example.appartemadeira.mvvm.presentation.finance.adapters.BillsAdapter
import com.example.appartemadeira.mvvm.presentation.finance.viewmodel.FinanceViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BillsToReceiveFragment : Fragment(),BillsAdapter.OnDeleteClickButton, BillsAdapter.OnSwitchClickButton {
    private lateinit var binding: FragmentBillsToReceiveBinding
    private val vm by sharedViewModel<FinanceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBillsToReceiveBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getBillsToReceive()
        binding.recycleBills2.setHasFixedSize(true)

        vm.billsToReceiveLiveData.observe(requireActivity()) {
            binding.recycleBills2.adapter = BillsAdapter(it,this,this)
        }
    }

    override fun onClick(bills: Bills) {
        vm.deleteBills(bills)

    }

    override fun onSwitch(bills: Bills) {
        vm.updateBills(bills)
    }
}
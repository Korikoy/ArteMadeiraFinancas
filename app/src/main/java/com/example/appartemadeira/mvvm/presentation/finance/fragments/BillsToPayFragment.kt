package com.example.appartemadeira.mvvm.presentation.finance.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appartemadeira.databinding.FragmentBillsToPayBinding
import com.example.appartemadeira.mvvm.data.finance.model.Bills
import com.example.appartemadeira.mvvm.presentation.finance.adapters.BillsAdapter
import com.example.appartemadeira.mvvm.presentation.finance.viewmodel.FinanceViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BillsToPayFragment : Fragment(), BillsAdapter.OnDeleteClickButton {
    private lateinit var binding: FragmentBillsToPayBinding
    private val vm by sharedViewModel<FinanceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBillsToPayBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getBillsToPay()
        binding.recycleBills.setHasFixedSize(true)

        vm.billsToPayLiveData.observe(requireActivity()) {
            binding.recycleBills.adapter = BillsAdapter(it,this)
        }
    }

    override fun onClick(bills: Bills) {
        vm.deleteBills(bills)
    }
}
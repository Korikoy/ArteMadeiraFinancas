package com.example.appartemadeira.mvvm.presentation.finance

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.appartemadeira.R
import com.example.appartemadeira.mvvm.presentation.register.RegisterActivity

import com.example.appartemadeira.databinding.ActivityFinanceBinding
import com.example.appartemadeira.mvvm.presentation.finance.fragments.BillsToPayFragment
import com.example.appartemadeira.mvvm.presentation.finance.fragments.BillsToReceiveFragment
import com.example.appartemadeira.mvvm.presentation.finance.viewmodel.FinanceViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FinanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinanceBinding
    private lateinit var toolbar: Toolbar
    private val vm by viewModel<FinanceViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        replaceFragment(BillsToReceiveFragment())
        vm.getValueToPay()
        vm.billsToPayLiveData.observe(this){
            vm.getValueToPay()
        }
        vm.valueBillsToPay.observe(this){
            binding.textViewPay.text = "Valor a Pagar: $it"
        }
        vm.getValueToRecive()
        vm.billsToReceiveLiveData.observe(this){
            vm.getValueToRecive()
        }
        vm.valueBillsToRecive.observe(this){
            binding.textViewRecive.text = "Valor a Receber: $it"
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameFragment, fragment)
        fragmentTransaction.commit()
    }

    private fun init() {
        toolbar = binding.include.toolbarPrincipal
        toolbar.title = getString(R.string.title_finance)
        setSupportActionBar(toolbar)

        binding.bottomNaviView.background = null
        binding.bottomNaviView.menu.getItem(1).isEnabled = false

        binding.fab.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.bottomNaviView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_financeBills -> replaceFragment(BillsToPayFragment())
                R.id.ic_financeCash -> replaceFragment(BillsToReceiveFragment())
                else -> {}
            }
            true
        }
    }
}
package com.example.appartemadeira.mvvm.presentation.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.appartemadeira.R
import com.example.appartemadeira.databinding.ActivityResisterBinding
import com.example.appartemadeira.mvvm.data.finance.model.Bills
import com.example.appartemadeira.mvvm.presentation.finance.FinanceActivity
import com.example.appartemadeira.mvvm.presentation.register.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResisterBinding
    private lateinit var toolbar: Toolbar
    private val vm by viewModel<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }
    private fun registerBills(){
        if(validadeFilds()) {
            val name = binding.editTextName.text.toString()
            val category = binding.autoCompleteText.text.toString()
            val type = if (binding.radioButtonBillsToRecive.isChecked) {
                "Contas a receber"
            } else {
                "Contas a pagar"
            }
            val valor = binding.editTextCash.text.toString()
            val description: String? = binding.editTextDescrip.text?.toString()
            val bills = Bills(name, type,category,valor,description)
            vm.saveBills(bills)
            Toast.makeText(this,"Sucesso ao cadastrar conta", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Falha ao cadastrar conta, preencha os dados", Toast.LENGTH_LONG).show()
        }
    }

    private fun dropBoxBillsToPay(){
        val categoryBillsToPay = resources.getStringArray(R.array.categoryBillsToPay)
        val arrayAdapter = ArrayAdapter(this,R.layout.dropdonw_item,categoryBillsToPay)
        binding.textInputLayoutCategory.isEnabled = true
        binding.autoCompleteText.setAdapter(arrayAdapter)
    }
    private fun dropBoxBillsToRecive(){
        val categoryBillsToRec = resources.getStringArray(R.array.categoryBillsToRecive)
        val arrayAdapter = ArrayAdapter(this,R.layout.dropdonw_item,categoryBillsToRec)
        binding.textInputLayoutCategory.isEnabled = true
        binding.autoCompleteText.setAdapter(arrayAdapter)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }
    private fun validadeFilds():Boolean{
        if (binding.editTextName.text != null && binding.editTextCash.text != null && binding.autoCompleteText.text != null){
            return true
        }
        return false
    }
    private fun init(){
        toolbar = binding.include.toolbarPrincipal
        toolbar.title = "Registrar Conta"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24)

        binding.radioButtonBillsToPay.setOnClickListener {
            dropBoxBillsToPay()
        }
        binding.radioButtonBillsToRecive.setOnClickListener {
            dropBoxBillsToRecive()
        }
        binding.buttonAdd.setOnClickListener {
            registerBills()
            startActivity(Intent(this, FinanceActivity::class.java))
            finish()
        }
    }


}



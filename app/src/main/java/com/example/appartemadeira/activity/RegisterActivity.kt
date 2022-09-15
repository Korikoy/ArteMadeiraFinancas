package com.example.appartemadeira.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.appartemadeira.R
import com.example.appartemadeira.databinding.ActivityResisterBinding
import com.example.appartemadeira.model.Bills

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResisterBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResisterBinding.inflate(layoutInflater)
        toolbarConfiguration()
        setSupportActionBar(toolbar)
        setContentView(binding.root)




        binding.radioButtonBillsToPay.setOnClickListener {
            dropBoxBillsToPay()
        }
        binding.radioButtonBillsToRecive.setOnClickListener {
            dropBoxBillsToRecive()
        }
        binding.buttonAdd.setOnClickListener {
            registerBills()
            val i: Intent = Intent(this, FinanceActivity::class.java)
            startActivity(i)
            finish()
        }



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
            bills.save()
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

    private fun toolbarConfiguration(){
        toolbar = binding.include.toolbarPrincipal
        toolbar.title = "Registrar Conta"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24)
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


}



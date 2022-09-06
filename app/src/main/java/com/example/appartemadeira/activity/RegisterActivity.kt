package com.example.appartemadeira.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import com.example.appartemadeira.R
import com.example.appartemadeira.databinding.ActivityResisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResisterBinding
    private lateinit var toolbar: Toolbar


    override fun onResume() {
        super.onResume()
        val categoryBillsToPay = resources.getStringArray(R.array.categoryBillsToPay)
        val categoryBillsToRec = resources.getStringArray(R.array.categoryBillsToRecive)
        if(binding.radioButtonBillsToPay.isChecked){
            val arrayAdapter = ArrayAdapter(this,R.layout.dropdonw_item,categoryBillsToPay)
            binding.autoCompleteText.setAdapter(arrayAdapter)
        }else if(binding.radioButtonBillsToRecive.isChecked){
            val arrayAdapter = ArrayAdapter(this,R.layout.dropdonw_item,categoryBillsToRec)
            binding.autoCompleteText.setAdapter(arrayAdapter)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResisterBinding.inflate(layoutInflater)
        toolbarConfiguration()
        setSupportActionBar(toolbar)
        setContentView(binding.root)

    }
    private fun toolbarConfiguration(){
        toolbar = binding.include.toolbarPrincipal
        toolbar.title = "Registrar Conta"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24)
    }
}
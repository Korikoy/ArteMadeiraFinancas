package com.example.appartemadeira.activity

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appartemadeira.Fragments.BillsToPayFragment
import com.example.appartemadeira.Fragments.BillsToReceiveFragment
import com.example.appartemadeira.R

import com.example.appartemadeira.databinding.ActivityFinanceBinding

class FinanceActivity : AppCompatActivity() {

  
    private lateinit var binding: ActivityFinanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinanceBinding.inflate(layoutInflater)
        val toolbar = binding.include.toolbarPrincipal
        setContentView(binding.root)
        toolbar.title = "Finanças"
        setSupportActionBar(toolbar)
        replaceFragment(BillsToPayFragment())




        binding.fab.setOnClickListener { view ->
            val i: Intent = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
        binding.include2.topNavView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.ic_financeBills -> replaceFragment(BillsToPayFragment())
                R.id.ic_financeCash -> replaceFragment(BillsToReceiveFragment())
                else -> {}
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.viewPager,fragment)
        fragmentTransaction.commit()

    }




}
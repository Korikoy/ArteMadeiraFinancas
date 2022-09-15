package com.example.appartemadeira.activity

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.appartemadeira.Fragments.BillsToPayFragment
import com.example.appartemadeira.Fragments.BillsToReceiveFragment
import com.example.appartemadeira.R

import com.example.appartemadeira.databinding.ActivityFinanceBinding

class FinanceActivity : AppCompatActivity() {

  
    private lateinit var binding: ActivityFinanceBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNaviView.background = null
        binding.bottomNaviView.menu.getItem(1).isEnabled = false
        toolbarConfiguration()
        replaceFragment(BillsToReceiveFragment())


        binding.fab.setOnClickListener { view ->
            val i: Intent = Intent(this, RegisterActivity::class.java)
            startActivity(i)
            finish()
        }
        binding.bottomNaviView.setOnItemSelectedListener{
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
        fragmentTransaction.replace(R.id.frameFragment,fragment)
        fragmentTransaction.commit()

    }
    private fun toolbarConfiguration(){
        toolbar = binding.include.toolbarPrincipal
        toolbar.title = "Finanças"
        setSupportActionBar(toolbar)

    }





}
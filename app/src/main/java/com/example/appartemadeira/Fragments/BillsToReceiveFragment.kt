package com.example.appartemadeira.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appartemadeira.R
import com.example.appartemadeira.adapter.BillsToPayAdaper
import com.example.appartemadeira.adapter.BillsToReciveAdapter
import com.example.appartemadeira.databinding.FragmentBillsToPayBinding
import com.example.appartemadeira.databinding.FragmentBillsToReceiveBinding
import com.example.appartemadeira.model.Bills
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class BillsToReceiveFragment : Fragment() {
    private lateinit var binding: FragmentBillsToReceiveBinding
    lateinit var adapter: BillsToReciveAdapter
    var list = ArrayList<Bills>()
    private val db =  FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBillsToReceiveBinding.inflate(layoutInflater)
        val view: View = binding.root
        adapter =activity?.let{ BillsToReciveAdapter(list,it) }!!
        recycleBills()

        eventChangeListener()


        return view
    }
    private fun recycleBills(){
        val layoutManager = LinearLayoutManager(activity)
        binding.recycleBills2.layoutManager = layoutManager
        binding.recycleBills2.setHasFixedSize(true)
        binding.recycleBills2.adapter = adapter
    }
    private fun eventChangeListener(){
        db.collection("BillsToRecive").addSnapshotListener { value, error ->
            if(error != null){
                Log.e("Error","listen.error", error)
                return@addSnapshotListener
            }
            for(dc in value!!.documentChanges){
                when(dc.type) {
                    DocumentChange.Type.ADDED ->  list.add(dc.document.toObject(Bills::class.java))
                    DocumentChange.Type.REMOVED-> list.remove(dc.document.toObject(Bills::class.java))
                }

            }
            adapter.notifyDataSetChanged()
        }
    }

}
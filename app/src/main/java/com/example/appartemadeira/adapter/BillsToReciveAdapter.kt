package com.example.appartemadeira.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appartemadeira.databinding.AdapterBillsBinding
import com.example.appartemadeira.model.Bills

class BillsToReciveAdapter(
    listBills:ArrayList<Bills>,
    var context:Context
): RecyclerView.Adapter<BillsToReciveAdapter.MyViewHolder>(){
    private val bills = listBills

    inner class
    MyViewHolder(val binding: AdapterBillsBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val binding =
            AdapterBillsBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: BillsToReciveAdapter.MyViewHolder, position: Int) {
        val bills: Bills = bills[position]
        holder.binding.textNameBills.text = bills.name.toString()
        holder.binding.textViewCategory.text = bills.category.toString()
        holder.binding.textViewMoney.text = bills.valor.toString()
        if (bills.description == null){
            holder.binding.textViewDescription.text = ""
        }else{holder.binding.textViewDescription.text = bills.description}

    }

    override fun getItemCount(): Int {
        return bills.size
    }
    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
}
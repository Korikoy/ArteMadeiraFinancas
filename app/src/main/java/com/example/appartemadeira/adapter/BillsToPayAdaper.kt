package com.example.appartemadeira.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appartemadeira.databinding.AdapterBillsBinding
import com.example.appartemadeira.model.Bills

class BillsToPayAdaper(
    listBills:ArrayList<Bills>,
    var context:Context
): RecyclerView.Adapter<BillsToPayAdaper.MyViewHolder>(){
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
    override fun onBindViewHolder(holder: BillsToPayAdaper.MyViewHolder, position: Int) {
        val bills: Bills = bills[position]
        holder.binding.textNameBills.text = bills.name.toString()
        holder.binding.textViewCategory.text = bills.category.toString()
        holder.binding.textViewMoney.text = bills.valor.toString()
        holder.binding.textStatus.text = bills.status.toString()
        if (bills.description == null){
            holder.binding.textViewDescription.text = ""
        }else{holder.binding.textViewDescription.text = bills.description}
        holder.binding.switchStats.isChecked = bills.status == "Pago"

        holder.binding.switchStats.setOnCheckedChangeListener { _, b ->
            if (b){
                bills.status = "Pago"
                holder.binding.textStatus.setText("Pago")
                bills.atualizeStatusBtP(true)
            }else{
                holder.binding.textStatus.setText("Pendente")
                bills.status = "Pendente"
                bills.atualizeStatusBtP(false)
            }

        }

        

    }

    override fun getItemCount(): Int {
    return bills.size
    }
    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

}
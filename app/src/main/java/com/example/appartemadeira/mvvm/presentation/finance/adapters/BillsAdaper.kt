package com.example.appartemadeira.mvvm.presentation.finance.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appartemadeira.databinding.AdapterBillsBinding
import com.example.appartemadeira.mvvm.data.finance.model.Bills

class BillsAdaper(
    listBills: List<Bills>
) : RecyclerView.Adapter<BillsAdaper.MyViewHolder>() {
    private val bills = listBills


    inner class
    MyViewHolder(val binding: AdapterBillsBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            AdapterBillsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bills: Bills = bills[position]
        holder.binding.textNameBills.text = bills.name
        holder.binding.textViewCategory.text = bills.category
        holder.binding.textViewMoney.text = bills.valor
        holder.binding.textStatus.text = bills.status
        if (bills.description == null) {
            holder.binding.textViewDescription.text = ""
        } else {
            holder.binding.textViewDescription.text = bills.description
        }
        holder.binding.switchStats.isChecked = bills.status == "Pago"

        holder.binding.switchStats.setOnCheckedChangeListener { _, b ->
            if (b) {
                bills.status = "Pago"
                holder.binding.textStatus.setText("Pago")
                bills.atualize()
            } else {
                holder.binding.textStatus.setText("Pendente")
                bills.status = "Pendente"
                bills.atualize()
            }

        }
        holder.binding.deleteButton.setOnClickListener {
            bills.delete()
        }
    }

    override fun getItemCount(): Int {
        return bills.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

}
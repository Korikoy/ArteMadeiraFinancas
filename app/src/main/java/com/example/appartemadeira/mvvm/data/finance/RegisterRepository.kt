package com.example.appartemadeira.mvvm.data.finance

import android.util.Log
import com.example.appartemadeira.mvvm.data.finance.model.Bills
import com.google.firebase.firestore.FirebaseFirestore

class RegisterRepository(
    private val db: FirebaseFirestore
) {
    fun saveBills(bills: Bills, typeBills: String) {
        db.collection(typeBills).add(bills.map())
            .addOnCompleteListener { task ->
                Log.i("user", "sucesso")
                bills.bid = task.result.id
                atualizeBills(bills,typeBills)
            }.addOnFailureListener { e ->
                Log.i("user", "falha")
            }
    }

    fun atualizeBills(bills: Bills,typeBills:String) {
            db.collection(typeBills).document(bills.bid).update(bills.map())
                .addOnCompleteListener { task ->
                    Log.i("user", "sucesso")

                }.addOnFailureListener { e ->
                    Log.i("user", "falha")
                }
        }

}

package com.example.appartemadeira.mvvm.data.finance

import android.util.Log
import com.example.appartemadeira.mvvm.data.finance.model.Bills
import com.example.appartemadeira.mvvm.data.finance.model.Bills.Companion.toBills
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FinanceRepository(
    private val db: FirebaseFirestore
) {

    suspend fun getBills(collectionPath: String): MutableList<Bills> {
        val query = db.collection(collectionPath).get().await()
        val billsList = mutableListOf<Bills>()

        for (document in query.documents) {
            billsList.add(document.toBills()!!)
        }
        return billsList
    }
    fun updateBills(bills: Bills, typeBills:String) {
        db.collection(typeBills).document(bills.bid).update(bills.map())
            .addOnCompleteListener { task ->
                Log.i("user", "sucesso")

            }.addOnFailureListener { e ->
                Log.i("user", "falha")
            }
    }
    fun deleteBills(bills: Bills, typeBills:String){
                db.collection(typeBills).document(bills.bid).delete()


    }
}
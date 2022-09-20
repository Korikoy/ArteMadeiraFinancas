package com.example.appartemadeira.mvvm.data.finance

import com.example.appartemadeira.mvvm.data.finance.model.Bills
import com.example.appartemadeira.mvvm.data.finance.model.Bills.Companion.toBills
import com.google.firebase.firestore.FirebaseFirestore
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

}
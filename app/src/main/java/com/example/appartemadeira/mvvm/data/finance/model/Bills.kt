package com.example.appartemadeira.mvvm.data.finance.model

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import java.io.Serializable

class Bills(
    var name: String = "",
    var type: String = "",
    var category: String = "",
    var valor: Double = 0.00,
    var description: String? = "",
    var bid: String = "",
    var status: String = "Pendente"

) : Serializable {

    companion object {
        fun DocumentSnapshot.toBills(): Bills? {
            return try {
                val name = getString("name")!!
                val type = getString("type")!!
                val category = getString("category")!!
                val valor = getDouble("valor")!!
                val description = getString("description")!!
                val bid = getString("bid")!!
                val status = getString("status")!!

                Bills(name, type, category, valor, description, bid, status)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        private const val TAG = "User"
    }

    @Exclude
    fun map(): HashMap<String, Any> {
        val usermap: HashMap<String, Any> = HashMap()
        usermap.put("name", name)
        usermap.put("type", type)
        usermap.put("category", category)
        usermap.put("valor", valor)
        usermap.put("description", description!!)
        usermap.put("bid", bid)
        usermap.put("status", status)

        return usermap

    }

}
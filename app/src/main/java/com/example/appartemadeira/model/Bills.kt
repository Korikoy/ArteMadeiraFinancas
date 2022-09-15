package com.example.appartemadeira.model

import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.Serializable

class Bills(
    var name: String = "",
    var type:String = "",
    var category:String = "",
    var valor:String = "",
    var description:String? = "",
    var bid: String = "",
    var status: String = "Pendente"

    ):Serializable {


    @Exclude
    fun save(){
        val db = Firebase.firestore
        if(type == "Contas a pagar"){
        db.collection("BillsToPay").add(map())
            .addOnCompleteListener { task -> Log.i("user","sucesso")
                bid = task.result.id
                atualize()

        }.addOnFailureListener { e ->
            Log.i("user", "falha",)
        }
        }else{
            db.collection("BillsToRecive").add(map())
                .addOnCompleteListener {task -> Log.i("user","sucesso")
                    bid = task.result.id
                    atualize()


                }.addOnFailureListener { e ->
                    Log.i("user", "falha",)
        }

    }
    }
    @Exclude
    fun map(): HashMap<String, Any> {
        val usermap: HashMap<String,Any> = HashMap()
        usermap.put("name", name)
        usermap.put("type", type)
        usermap.put("category", category)
        usermap.put("valor", valor)
        usermap.put("description", description!!)
        usermap.put("bid",bid)
        usermap.put("status",status)

        return usermap

    }
    @Exclude
    fun atualize() {
        val db = Firebase.firestore
        if (type == "Contas a pagar") {
            db.collection("BillsToPay").document(bid).update(map())
                .addOnCompleteListener { task ->
                    Log.i("user", "sucesso")


                }.addOnFailureListener { e ->
                    Log.i("user", "falha",)
                }
        } else {
            db.collection("BillsToRecive").document(bid).update(map())
                .addOnCompleteListener { task ->
                    Log.i("user", "sucesso")


                }.addOnFailureListener { e ->
                    Log.i("user", "falha",)

                }


        }

    }
    fun atualizeStatusBtP(e: Boolean){
        val db = Firebase.firestore
        if(e){
            db.collection("BillsToPay").document(bid).collection("Status").add("Pago")
        }else{
            db.collection("BillsToPay").document(bid).collection("Status").add("Pendente")
        }

    }
    }
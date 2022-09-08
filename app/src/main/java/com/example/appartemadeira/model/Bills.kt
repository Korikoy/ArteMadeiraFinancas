package com.example.appartemadeira.model

import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.Serializable

class Bills(
    var name: String,
    var type:String,
    var category:String,
    var valor:String,
    var description:String? = "",

    ):Serializable {
    fun save(){
        val db = Firebase.firestore
        if(type == "Contas a pagar"){
        db.collection("bills to pay").add(map())
            .addOnCompleteListener {
            it -> Log.i("user","sucesso")

        }.addOnFailureListener { e ->
            Log.i("user", "falha",)
        }
        }else{
            db.collection("bills to recive").add(map())
                .addOnCompleteListener {
                        it -> Log.i("user","sucesso")

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

        return usermap

    }





}
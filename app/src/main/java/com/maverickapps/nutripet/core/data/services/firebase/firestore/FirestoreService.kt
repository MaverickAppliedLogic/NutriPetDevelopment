package com.maverickapps.nutripet.core.data.services.firebase.firestore

import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore

class FirestoreService{

    val db = Firebase.firestore

    fun getCollection(collectionName: String) : Task<QuerySnapshot> =
        db.collection(collectionName)
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    println(document.data)
                    return@addOnSuccessListener
                }
            }
            .addOnFailureListener{
                println(it)
            }

    fun createDocument(collectionName: String, documentObject: Any) =
        db.collection(collectionName)
            .add(documentObject)
            .addOnSuccessListener {
                println("Document created")
            }
            .addOnFailureListener{
                println(it)

            }

}
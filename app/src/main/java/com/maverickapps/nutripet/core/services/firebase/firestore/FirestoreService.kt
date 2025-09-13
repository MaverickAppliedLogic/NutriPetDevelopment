package com.maverickapps.nutripet.core.services.firebase.firestore

import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore

class FirestoreService{

    private val db = Firebase.firestore

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

    fun getDocument(collectionName: String, documentId: String) : Task<DocumentSnapshot> =
        db.collection(collectionName)
            .document(documentId)
            .get()

    fun setDocument(collectionName: String, documentId: String, documentObject: Any) =
        db.collection(collectionName)
            .document(documentId)
            .set(documentObject)
            .addOnSuccessListener {
                println("Document updated: $documentObject")
            }
            .addOnFailureListener{
                println(it)
            }
}
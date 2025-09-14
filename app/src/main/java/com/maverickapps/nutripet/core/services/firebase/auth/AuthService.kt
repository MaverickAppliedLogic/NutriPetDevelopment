package com.maverickapps.nutripet.core.services.firebase.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine

class AuthService {

    private val auth = FirebaseAuth.getInstance()

   suspend fun signInAnonymously(): Result<String> {
        return if (auth.currentUser != null) {
            Log.i("AuthService", "Already logged in")
            Result.success(auth.currentUser!!.uid)
        } else {
            suspendCancellableCoroutine { continuation ->
                auth.signInAnonymously()
                    .addOnSuccessListener { result ->
                        val uid = result.user?.uid
                        if (uid != null) {
                            Log.i("AuthService", "Signed in with UID: $uid")
                            continuation.resume(
                                Result.success(uid)) { _, _, _ ->
                            }
                        } else {
                            continuation.resume(
                                Result.failure(Exception("User ID is null"))){ _, _, _ -> }
                        }
                    }
                    .addOnFailureListener { e ->
                        continuation.resume(
                            Result.failure(Exception(e.toString()))){ _, _, _ -> }                    }
            }
        }
    }

    fun getUid(): String? {
        return auth.currentUser?.uid
    }
}
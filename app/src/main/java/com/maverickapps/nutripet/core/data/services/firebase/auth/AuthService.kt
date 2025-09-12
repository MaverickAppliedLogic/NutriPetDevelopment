package com.maverickapps.nutripet.core.data.services.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine

class AuthService {

    private val auth = FirebaseAuth.getInstance()

   suspend fun signInAnonymously(): Result<String> {
        return if (auth.currentUser != null) {
            Result.success("Already logged");
        } else {
            suspendCancellableCoroutine { continuation ->
                auth.signInAnonymously()
                    .addOnSuccessListener { result ->
                        val uid = result.user?.uid
                        if (uid != null) {
                            continuation.resume(
                                Result.success("Signed in with UID: $uid")) { _, _, _ ->
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
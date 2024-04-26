package com.example.geliculon

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth

fun Context.login(email:String,){
    val intent= Intent(this,MainActivity::class.java).apply {
        flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    intent.putExtra("user",email)
    intent.putExtra("tipousuario",1)
    startActivity(intent)
}

fun Context.logout(){
    FirebaseAuth.getInstance().signOut()

    val intent= Intent(this,Login::class.java).apply {
        flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}
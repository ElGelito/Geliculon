package com.example.geliculon

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.geliculon.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class Login : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private val Google_Sign_In = 100
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btn_ano = findViewById<Button>(R.id.button5)
        firebaseAuth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // Verificar si el usuario ya está autenticado
        val savedEmail = sharedPreferences.getString("email", null)
        val savedProvider = sharedPreferences.getString("provider", null)
        if (savedEmail != null && savedProvider != null) {
            val currentUser = firebaseAuth.currentUser
            if (currentUser != null && currentUser.email == savedEmail) {
                // El usuario ya está autenticado, iniciar la actividad principal directamente
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user", currentUser.email)
                intent.putExtra("tipousuario", 0)
                startActivity(intent)
                finish()
            } else if (savedProvider == "google" && currentUser != null) {
                // El usuario se autenticó previamente con Google, iniciar la actividad principal
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user", currentUser.email)
                intent.putExtra("tipousuario", 0)
                startActivity(intent)
                finish()
            }
        }

        boton()

        binding.button2.setOnClickListener {
            val email = binding.correo2.text.toString()
            val password = binding.contraseA3.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Guardar el correo electrónico del usuario en SharedPreferences
                        val editor = sharedPreferences.edit()
                        editor.putString("email", email)
                        editor.putString("provider", "email")
                        editor.apply()

                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("user", email)
                        intent.putExtra("tipousuario", 0)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, getString(R.string.rellena), Toast.LENGTH_LONG).show()
            }
        }

        binding.button5.setOnClickListener {
            loginanonymous()
        }
        binding.redirijelogin.setOnClickListener {
            val intent = Intent(this, Registrar::class.java)
            startActivity(intent)
        }

        binding.textView2.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialog_forgot, null)
            val useremail = view.findViewById<EditText>(R.id.editTextTextEmailAddress)
            builder.setView(view)
            val dialog = builder.create()

            view.findViewById<Button>(R.id.button4).setOnClickListener {
                compareEmail(useremail)
                dialog.dismiss()
            }
            view.findViewById<Button>(R.id.button3).setOnClickListener {
                dialog.dismiss()
            }

            if (dialog.window != null) {
                dialog.window!!.setBackgroundDrawable(ColorDrawable(1414))
            }
            dialog.show()
        }
    }

    private fun boton() {
        binding.button6.setOnClickListener {
            val googleConfig = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleClient = GoogleSignIn.getClient(this, googleConfig)
            val signInIntent = googleClient.signInIntent
            startActivityForResult(signInIntent, Google_Sign_In)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Google_Sign_In) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    Log.d("TAG", "FIREBASEGOOGLEID $account.id")
                    firebaseAuthWithGoogle(account.idToken!!)
                } else {
                    Toast.makeText(this, getString(R.string.noexiste), Toast.LENGTH_LONG).show()
                }
            } catch (e: ApiException) {
                Log.w("TAG", "GOOGLE SIGN IN FAILED $e")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser?.email.toString()
                    // Guardar el correo electrónico del usuario en SharedPreferences
                    val editor = sharedPreferences.edit()
                    editor.putString("email", user)
                    editor.putString("provider", "google")
                    editor.apply()

                    login(user)
                } else {
                    Log.w("TAG", "GOOGLE SIGN IN FAILED ")
                }
            }
    }

    private fun loginanonymous() {
        firebaseAuth.signInAnonymously().addOnCompleteListener {
            if (it.isSuccessful) {
                val user = FirebaseAuth.getInstance().currentUser
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user", user)
                intent.putExtra("tipousuario", 2)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
        }
    }

    private fun compareEmail(email: EditText) {
        if (email.text.toString().isEmpty()) {
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            return
        }
        firebaseAuth.sendPasswordResetEmail(email.text.toString()).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, getString(R.string.checkea), Toast.LENGTH_LONG).show()
            }
        }
    }
}

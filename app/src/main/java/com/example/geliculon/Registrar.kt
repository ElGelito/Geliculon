package com.example.geliculon
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.geliculon.databinding.ActivityRegistrarBinding
import com.google.firebase.auth.FirebaseAuth
import android.util.Patterns
import java.util.regex.Pattern
import android.net.Uri
import android.widget.TextView

class Registrar : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrarBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    if (isValidPassword(password)) {
                        showPrivacyPolicyConfirmationDialog(email, password)
                    } else {
                        Toast.makeText(
                            this,
                            "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un carácter especial",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.nocoin),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.vacio),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.redirijelogin.setOnClickListener {
            val loginIntent = Intent(this, Login::class.java)
            startActivity(loginIntent)
        }
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$"
        )
        return passwordPattern.matcher(password).matches()
    }

    private fun showPrivacyPolicyConfirmationDialog(email: String, password: String) {
        val builder = AlertDialog.Builder(this)
        val privacyPolicyText = "Al registrarte, estás aceptando la <a href='https://www.geliculon.com/?page_id=3'>política de privacidad de Geliculon</a>."
        builder.setTitle("Aceptación de Política de Privacidad")
            .setMessage(Html.fromHtml(privacyPolicyText))
            .setPositiveButton("Aceptar") { _, _ ->
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this,
                                task.exception.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()

        val dialog = builder.show()
        val textView = dialog.findViewById<View>(android.R.id.message) as? TextView
        textView?.movementMethod = LinkMovementMethod.getInstance()
    }
}

package ru.perelyginva.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import ru.perelyginva.authentication.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private var binding: ActivityLoginBinding? = null
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        auth = FirebaseAuth.getInstance()
        binding?.btnLogin?.setOnClickListener(View.OnClickListener {
            loginUser()
        })

        binding?.tvRegisterHere?.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@Login, Register::class.java))
        })

    }

    private fun loginUser() {
        val email: String = binding?.etLoginEmail?.text.toString()
        val password: String = binding?.etLoginPass?.text.toString()

        if (TextUtils.isEmpty(email)){
            binding?.etLoginEmail?.error = "Email cannot be empty!"
            binding?.etLoginEmail?.requestFocus()
        }
        else if (TextUtils.isEmpty(password)){

            binding?.etLoginPass?.error = "Password cannot be empty!"
            binding?.etLoginPass?.requestFocus()
        }
        else {
            auth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener{

                    task ->
                if (task.isSuccessful){

                    Toast.makeText(
                        this@Login,
                        "User logged i n successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(Intent(this@Login, MainActivity::class.java))
                }
                else{
                    Toast.makeText(
                        this@Login,
                        "Log in error" + task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
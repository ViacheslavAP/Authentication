package ru.perelyginva.authentication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import ru.perelyginva.authentication.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {

    private var binding: ActivityRegisterBinding? = null
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        auth = FirebaseAuth.getInstance()

        binding?.btnRegister?.setOnClickListener(View.OnClickListener {

            createUser()
        })

        binding?.tvLoginHere?.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@Register, Login::class.java))
        })
    }

    private fun createUser() {
        val email: String = binding?.etRegEmail?.text.toString()
        val password: String = binding?.etRegPass?.text.toString()

        if (TextUtils.isEmpty(email)){
            binding?.etRegEmail?.error = "Email cannot be empty!"
            binding?.etRegEmail?.requestFocus()
        }
        else if (TextUtils.isEmpty(password)){

            binding?.etRegPass?.error = "Password cannot be empty!"
            binding?.etRegPass?.requestFocus()
        }
        else {
            auth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener{

                task ->
                if (task.isSuccessful){

                    Toast.makeText(
                        this@Register,
                        "User register successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(Intent(this@Register, Login::class.java))
                }
                else{
                    Toast.makeText(
                        this@Register,
                        "Registration error" + task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
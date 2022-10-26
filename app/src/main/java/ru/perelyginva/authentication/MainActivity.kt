 package ru.perelyginva.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import ru.perelyginva.authentication.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
     var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        auth = FirebaseAuth.getInstance()

        binding?.btnLogout?.setOnClickListener(View.OnClickListener {
            auth!!.signOut()
            startActivity(Intent(this@MainActivity, Login::class.java))
        })
    }

     override fun onStart() {
         super.onStart()
         val user = auth!!.currentUser
         if (user == null){
             startActivity(Intent(this@MainActivity, Login::class.java))
         }
     }
}
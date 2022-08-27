package com.dldmswo1209.pockettrainer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dldmswo1209.pockettrainer.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val user = sharedPreferences.getString("user_uid","")
        Handler().postDelayed({
            if(user != ""){
                startActivity(Intent(this, MainActivity::class.java))
            }
            else{
                startActivity(Intent(this, LoginActivity::class.java))
            }
        },DURATION)
    }
    companion object{
        private const val DURATION : Long = 2000
    }
}
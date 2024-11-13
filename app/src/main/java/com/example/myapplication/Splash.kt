package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar

class Splash : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val prgBar = findViewById<ProgressBar>(R.id.progressBar)
        val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)

        // Проверка данных в SharedPreferences
        val email = sharedPreferences.getString("email",null)
        val password = sharedPreferences.getString("password",null)
        val autoLogin = sharedPreferences.getBoolean("autoLogin",false)
        val input = sharedPreferences.getString("phone", null)


        if ((email != null || input != null) && password != null) {
            if (autoLogin) {
                startActivity(Intent(this, Content::class.java))
            } else
                startActivity(Intent(this, Login::class.java))
            }
         else {
            startActivity(Intent(this, Registration::class.java))
        }
    }
}



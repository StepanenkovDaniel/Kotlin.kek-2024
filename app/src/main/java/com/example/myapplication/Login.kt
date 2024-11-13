package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var autoLoginCheckBox: CheckBox
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.user_email)
        passwordEditText = findViewById(R.id.user_pasword)
        loginButton = findViewById(R.id.button_on)
        autoLoginCheckBox = findViewById(R.id.checkBoxRememberMe)

        sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)

        loginButton.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val inputText = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Получаем данные из SharedPreferences
        val savedEmail = sharedPreferences.getString("email", null)
        val savedPhone = sharedPreferences.getString("phone",null)
        val savedPassword = sharedPreferences.getString("password", null)


        // Проверка введённых данных
        if ((inputText == savedEmail || inputText == savedPhone) && password == savedPassword) {
            // Данные совпадают, сохраняем состояние автологина
            with(sharedPreferences.edit()) {
                putBoolean("autoLogin", autoLoginCheckBox.isChecked)
                apply()
            }
            // Переход на ContentActivity
            startActivity(Intent(this, Content::class.java))
            finish()
        } else {
            // Если данные не совпадают, показываем Toast
            Toast.makeText(this, "Неверный email или пароль", Toast.LENGTH_SHORT).show()
        }

    }
}
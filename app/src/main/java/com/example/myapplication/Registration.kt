package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Registration : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        var isOptionSelected = false //Флаг(кнопка)
        val number_phone_button = findViewById<Button>(R.id.buttonByNumber)
        val email_phone = findViewById<Button>(R.id.buttonByEmail)
        val email_users_taxt = findViewById<EditText>(R.id.user_email)
        val password_try = findViewById<EditText>(R.id.user_pasword)
        val regestr_button = findViewById<Button>(R.id.reg_butt)
        val password_more = findViewById<EditText>(R.id.user_pasword_more)
        val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)

        number_phone_button.setOnClickListener(){
            if (!isOptionSelected){
                number_phone_button.setBackgroundColor(Color.GREEN)
                email_phone.setBackgroundColor(Color.WHITE)
                isOptionSelected = true
            }
            email_users_taxt.hint = "Введите телефон"
            email_users_taxt.inputType = InputType.TYPE_CLASS_PHONE
        }

        email_phone.setOnClickListener(){
            if(isOptionSelected){
                email_phone.setBackgroundColor(Color.RED)
                number_phone_button.setBackgroundColor(Color.WHITE)
                isOptionSelected = false
            }
            email_users_taxt.hint = "Введите email"
            email_users_taxt.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }

        regestr_button.setOnClickListener {
            val input = email_users_taxt.text.toString().trim() // Либо email, либо телефон
            val password = password_more.text.toString().trim()

            // Проверка на корректность введеных данных
            if (isOptionSelected) { // Регистрация по номеру телефона
                if (!input.contains("+")) {
                    Toast.makeText(this, "Некорректный номер телефона", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            } else { // Регистрация по email
                if (!input.contains("@")) {
                    Toast.makeText(this, "Некорректный email", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            if (password.length < 8) {
                Toast.makeText(this, "Пароль должен содержать 8 символов", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password != password_try.text.toString()) {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //Сохраняем данные в SharedPreferences
            with(sharedPreferences.edit()) {
                if (isOptionSelected) {
                    putString("phone", input) // Сохраняем номер телефона
                } else {
                    putString("email", input) // Сохраняем email
                }
                putString("password", password)
                putBoolean("autoLogin", false)
                apply()
            }
            //Переход в ContentActivity
            startActivity(Intent(this, Content::class.java))
            finish()
        }
    }
}
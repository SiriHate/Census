package com.example.census

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val auth = FirebaseAuth.getInstance()
        val loginBtn = findViewById<Button>(R.id.login_btn)
        val userMethod = findViewById<Button>(R.id.user_method)
        val adminMethod = findViewById<Button>(R.id.admin_method)
        val emailInput = findViewById<EditText>(R.id.email_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val adminLayout = findViewById<LinearLayout>(R.id.admin_layout)
        var btnChecked = false

        //Включение и выключение окна входа
        adminMethod.setOnClickListener {
            if (!btnChecked) {
                adminLayout.visibility = View.VISIBLE
                btnChecked = true
            }
            else {
                adminLayout.visibility = View.GONE
                btnChecked = false
            }
        }

        //Запуск приложения от имени пользователя
        userMethod.setOnClickListener {
            val intent = Intent(this@AuthActivity,MainActivity::class.java)
            intent.putExtra("Admin",false)
            startActivity(intent)
        }

        //Запуск приложения от имени администратора
        loginBtn.setOnClickListener {
            if (TextUtils.isEmpty(emailInput.text.toString()) && TextUtils.isEmpty(passwordInput.text.toString()))
            {
                Toast.makeText(this, "Введите почту и пароль!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(emailInput.text.toString())) {
                Toast.makeText(this, "Введите почту!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(passwordInput.text.toString())) {
                Toast.makeText(this, "Введите пароль!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(emailInput.text.toString(), passwordInput.text.toString())
                .addOnSuccessListener {
                    val intent = Intent(this@AuthActivity,MainActivity::class.java)
                    intent.putExtra("Admin",true)
                    startActivity(intent)
                }
                .addOnFailureListener { Toast.makeText(this, "Неверный логин/пароль!", Toast.LENGTH_SHORT).show() }
        }


    }
}
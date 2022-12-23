package com.example.census

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Состояние кнопок
        val btnChecked = BooleanArray(9)
        for(i in 1..8){
            btnChecked[i] = false
        }

        //Инициализация БД
        val database = Firebase.database
        //Выбор узла
        val ref = database.getReference("info")

        //TextView
        val text1 = findViewById<TextView>(R.id.text1)
        val text2 = findViewById<TextView>(R.id.text2)
        val text3 = findViewById<TextView>(R.id.text3)
        val text4 = findViewById<TextView>(R.id.text4)
        val text5 = findViewById<TextView>(R.id.text5)
        val text6 = findViewById<TextView>(R.id.text6)
        val text7 = findViewById<TextView>(R.id.text7)
        val text8 = findViewById<TextView>(R.id.text8)
        val text9 = findViewById<TextView>(R.id.text9)
        val exampleText1 = findViewById<TextView>(R.id.example1_text)
        val exampleText2 = findViewById<TextView>(R.id.example2_text)
        val exampleText3 = findViewById<TextView>(R.id.example3_text)
        val exampleId1 = findViewById<TextView>(R.id.example_1_id)
        val exampleId2 = findViewById<TextView>(R.id.example_2_id)
        val exampleId3 = findViewById<TextView>(R.id.example_3_id)

        //EditText
        val adminTextInput = findViewById<EditText>(R.id.admin_text_input)
        val adminId = findViewById<EditText>(R.id.admin_id)

        //Button
        val btn1 = findViewById<Button>(R.id.btn1); val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3); val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5); val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7); val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)
        val adminBtn = findViewById<Button>(R.id.admin_btn)

        //LinearLayout
        val exampleLayout = findViewById<LinearLayout>(R.id.example_layout)
        val adminPanel = findViewById<LinearLayout>(R.id.admin_panel)

        //Массив кнопок
        val buttons = arrayOf(btn1, btn2, btn3, btn4, btn5, btn6 , btn7, btn8, btn9)
        val mainTexts = arrayOf(text1, text2, text3, text4, text5, text6, text7, text8, text9)
        val exampleIds = arrayOf(exampleId1, exampleId2, exampleId3)
        val exampleTexts = arrayOf(exampleText1, exampleText2, exampleText3)

        //Алгоритм слушателя изменений в БД
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (i in 0..8) {
                    mainTexts[i].text = dataSnapshot.child((i+1).toString()).value.toString().replace("\\n", "\n")
                }
                for (i in 0..2) {
                    exampleTexts[i].text = dataSnapshot.child((i+81).toString()).value.toString().replace("\\n", "\n")

                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MainActivity,"Ошибка загрузки данных!", Toast.LENGTH_SHORT).show()
            }
        }
        //Слушатель изменений в БД
        ref.addValueEventListener(menuListener)

        //Видимость админ панели
        if (intent.getBooleanExtra("Admin", false)) {
            adminPanel.visibility = View.VISIBLE
            //Добавление id кнопкам
            for (i in 0..8) {
                buttons[i].text = resources.getStringArray(R.array.array)[i].toString()
            }
            //Добавление id подписям к примерам
            for (i in 0..2) {
                exampleIds[i].visibility = View.VISIBLE
            }
        }

        //Кнопка изменить
        adminBtn.setOnClickListener {
            val text = adminTextInput.text.toString()
            val id = adminId.text.toString()
            if (text != "" && id != "") {
                if (id.toInt() in 1..9 || id.toInt() in 81..83) {
                    ref.child(id).setValue(adminTextInput.text.toString())
                    adminId.text.clear()
                    adminTextInput.text.clear()
                    Toast.makeText(this, "Изменения успешно сохранены!", Toast.LENGTH_SHORT).show()
                }
                else {
                    adminId.text.clear()
                    adminTextInput.text.clear()
                    Toast.makeText(this, "Данного id не существует!", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                adminId.text.clear()
                adminTextInput.text.clear()
                Toast.makeText(this, "Ошибка ввода данных!", Toast.LENGTH_SHORT).show()
            }
        }

        //Обработчик нажатий 1 кнопки
        btn1.setOnClickListener {
            if (!btnChecked[0]) { text1.visibility = View.VISIBLE; btnChecked[0] = true }
            else { text1.visibility = View.GONE; btnChecked[0] = false }
        }

        //Обработчик нажатий 2 кнопки
        btn2.setOnClickListener {
            if (!btnChecked[1]) { text2.visibility = View.VISIBLE; btnChecked[1] = true }
            else { text2.visibility = View.GONE; btnChecked[1] = false }
        }

        //Обработчик нажатий 3 кнопки
        btn3.setOnClickListener {
            if (!btnChecked[2]) { text3.visibility = View.VISIBLE; btnChecked[2] = true }
            else { text3.visibility = View.GONE; btnChecked[2] = false }
        }

        //Обработчик нажатий 4 кнопки
        btn4.setOnClickListener {
            if (!btnChecked[3]) { text4.visibility = View.VISIBLE; btnChecked[3] = true }
            else { text4.visibility = View.GONE; btnChecked[3] = false }
        }

        //Обработчик нажатий 5 кнопки
        btn5.setOnClickListener {
            if (!btnChecked[4]) { text5.visibility = View.VISIBLE; btnChecked[4] = true }
            else { text5.visibility = View.GONE; btnChecked[4] = false }
        }

        //Обработчик нажатий 6 кнопки
        btn6.setOnClickListener {
            if (!btnChecked[5]) { text6.visibility = View.VISIBLE; btnChecked[5] = true }
            else { text6.visibility = View.GONE; btnChecked[5] = false }
        }

        //Обработчик нажатий 7 кнопки
        btn7.setOnClickListener {
            if (!btnChecked[6]) { text7.visibility = View.VISIBLE; btnChecked[6] = true }
            else { text7.visibility = View.GONE; btnChecked[6] = false }
        }

        //Обработчик нажатий 8 кнопки
        btn8.setOnClickListener {
            if (!btnChecked[7]) {
                text8.visibility = View.VISIBLE
                exampleLayout.visibility = View.VISIBLE
                btnChecked[7] = true
            }
            else { text8.visibility = View.GONE; exampleLayout.visibility = View.GONE
                btnChecked[7] = false }
        }

        //Обработчик нажатий 9 кнопки
        btn9.setOnClickListener {
            if (!btnChecked[8]) { text9.visibility = View.VISIBLE ; btnChecked[8] = true }
            else { text9.visibility = View.GONE; btnChecked[8] = false }
        }


    }
}
package com.example.smartgarden2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.appbar.MaterialToolbar

class HomeActivity : BaseMenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Toolbar para menú
        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Texto de bienvenida
        val texto = findViewById<TextView>(R.id.textHome)
        texto.text = "🏡 Bienvenido al inicio de SmartGarden"

        // Botón para ir a MainActivity (gestión de plantas)
        val botonPlantas: Button = findViewById(R.id.buttonPlantas)
        botonPlantas.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}

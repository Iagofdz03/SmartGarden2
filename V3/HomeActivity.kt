package com.example.smartgarden2

import android.os.Bundle
import android.widget.TextView

class HomeActivity : BaseMenuActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val texto = findViewById<TextView>(R.id.textHome)
        texto.text = "🏡 Bienvenido al inicio de SmartGarden"
    }

    override fun onResume() {
        super.onResume()
        BaseMenuActivity.actividadActual = 1
    }
}

package com.example.smartgarden2

import android.os.Bundle
import android.widget.TextView

class SettingsActivity : BaseMenuActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val texto = findViewById<TextView>(R.id.textSettings)
        texto.text = "⚙️ Ajustes de SmartGarden"
    }

    override fun onResume() {
        super.onResume()
        BaseMenuActivity.actividadActual = 2
    }
}

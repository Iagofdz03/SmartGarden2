package com.example.smartgarden2

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.appbar.MaterialToolbar

class SettingsActivity : BaseMenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val texto = findViewById<TextView>(R.id.textSettings)
        texto.text = "⚙️ Ajustes de SmartGarden"
    }
}

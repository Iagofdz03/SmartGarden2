package com.example.smartgarden2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : BaseMenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Mensaje opcional para comprobar que la pantalla funciona
        Toast.makeText(this, "Ajustes abiertos", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        BaseMenuActivity.actividadActual = 2
    }
}

package com.example.smartgraden3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class SensoresActivity : BaseMenuActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensores)
        title = "Sensores"

        val tvHumedad = findViewById<TextView>(R.id.tvHumedad)
        val tvTemp = findViewById<TextView>(R.id.tvTemp)
        val btnActualizar = findViewById<Button>(R.id.btnActualizar)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Simular lectura de sensores
        fun actualizarSensores() {
            val humedad = Random.nextInt(0, 100)
            val temp = Random.nextInt(10, 35)
            tvHumedad.text = "Humedad: $humedad %"
            tvTemp.text = "Temperatura: $temp ºC"
        }

        btnActualizar.setOnClickListener {
            actualizarSensores()
        }

        btnBack.setOnClickListener {
            finish() // vuelve a Dashboard
        }

        // Actualizar al abrir la pantalla
        actualizarSensores()
    }
}

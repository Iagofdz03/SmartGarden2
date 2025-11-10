package com.example.smartgraden3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        title = "Dashboard"

        val btnRiego = findViewById<Button>(R.id.btnRiego)
        val btnSensores = findViewById<Button>(R.id.btnSensores)

        btnRiego.setOnClickListener {
            startActivity(Intent(this, RiegoActivity::class.java))
        }

        btnSensores.setOnClickListener {
            startActivity(Intent(this, SensoresActivity::class.java))
        }
    }
}

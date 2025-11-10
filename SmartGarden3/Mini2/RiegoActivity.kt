package com.example.smartgraden3

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class RiegoActivity : BaseMenuActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riego)
        title = "Riego"

        val btnOn = findViewById<Button>(R.id.btnOn)
        val btnOff = findViewById<Button>(R.id.btnOff)
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnOn.setOnClickListener {
            Toast.makeText(this, "Riego encendido", Toast.LENGTH_SHORT).show()
        }

        btnOff.setOnClickListener {
            val rootView = findViewById<android.view.View>(R.id.btnOff)
            Snackbar.make(rootView, "Riego apagado", Snackbar.LENGTH_SHORT).show()
        }

        btnBack.setOnClickListener {
            finish() // vuelve a la actividad anterior (Dashboard)
        }
    }
}

package com.example.smartgarden2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class BaseMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Inflar el menú en todas las pantallas que hereden de esta clase
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    // Manejar la navegación y acciones del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.menu_home -> {
                if (this !is HomeActivity) {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                true
            }

            R.id.menu_settings -> {
                if (this !is SettingsActivity) {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    finish()
                }
                true
            }

            R.id.menu_logout -> {
                Snackbar.make(findViewById(android.R.id.content),
                    "Sesión cerrada correctamente.", Snackbar.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}

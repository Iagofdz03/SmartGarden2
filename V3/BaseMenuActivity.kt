package com.example.smartgarden2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseMenuActivity : AppCompatActivity() {

    companion object {
        // 0 = Main, 1 = Home, 2 = Settings
        var actividadActual = 0
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)

        // Oculta el ítem de la pantalla actual
        for (i in 0 until menu.size()) {
            menu.getItem(i).isVisible = i != actividadActual
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_home -> {
            if (actividadActual != 1) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                actividadActual = 1
                startActivity(intent)
            }
            true
        }
        R.id.menu_settings -> {
            if (actividadActual != 2) {
                val intent = Intent(this, SettingsActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                actividadActual = 2
                startActivity(intent)
            }
            true
        }
        R.id.menu_logout -> {
            Toast.makeText(this, "Sesión cerrada correctamente.", Toast.LENGTH_SHORT).show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}

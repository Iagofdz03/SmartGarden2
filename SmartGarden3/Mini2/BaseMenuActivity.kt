package com.example.smartgraden3

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseMenuActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_dashboard -> {
            startActivity(Intent(this, DashboardActivity::class.java))
            true
        }
        R.id.action_riego -> {
            startActivity(Intent(this, RiegoActivity::class.java))
            true
        }
        R.id.action_sensores -> {
            startActivity(Intent(this, SensoresActivity::class.java))
            true
        }
        R.id.action_acerca -> {
            Toast.makeText(this, "Desarrollador: Igor Lorenzo", Toast.LENGTH_SHORT).show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}

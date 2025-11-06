package com.example.smartgarden2

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar

class MainActivity : BaseMenuActivity(), ConfirmDialog.ConfirmDialogListener {

    private val CHANNEL_ID = "smartgarden_channel"
    private val NOTIFICATION_ID = 1
    private val REQUEST_PERMISSION = 1001

    private lateinit var etNombre: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        etNombre = findViewById(R.id.editTextText)
        val botonGuardar = findViewById<Button>(R.id.button)
        val botonAviso = findViewById<Button>(R.id.buttonAviso)
        val botonNotificacion = findViewById<Button>(R.id.buttonNotificacion)

        // M9 — Registrar menú contextual en el EditText
        registerForContextMenu(etNombre)

        // Botón Guardar → abrir diálogo
        botonGuardar.setOnClickListener {
            ConfirmDialog().show(supportFragmentManager, "confirmDialog")
        }

        // Botón Aviso → Snackbar
        botonAviso.setOnClickListener { view ->
            Snackbar.make(view, "Acción realizada", Snackbar.LENGTH_LONG)
                .setAction("Deshacer") {
                    Toast.makeText(this, "Acción cancelada", Toast.LENGTH_SHORT).show()
                }.show()
        }

        // Botón Notificación
        createNotificationChannel()
        botonNotificacion.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_PERMISSION
                )
            } else {
                enviarNotificacion()
            }
        }
    }

    // M9 — Crear el menú contextual
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v?.id == R.id.editTextText) {
            menuInflater.inflate(R.menu.menu_contextual, menu)
            menu?.setHeaderTitle("Nombre de la planta")
        }
    }

    // M9 — Acciones del menú contextual
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_limpiar -> {
                etNombre.setText("")
                true
            }
            R.id.action_copiar -> {
                val cm = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                cm.setPrimaryClip(ClipData.newPlainText("planta", etNombre.text))
                Toast.makeText(this, "Copiado", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_pegar -> {
                val cm = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                if (cm.hasPrimaryClip()) {
                    etNombre.setText(cm.primaryClip?.getItemAt(0)?.text ?: "")
                }
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    // M10 — Notificación sin duplicar Activity
    private fun enviarNotificacion() {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        else
            PendingIntent.FLAG_UPDATE_CURRENT

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, flags)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("SmartGarden")
            .setContentText("🌿 Riego automático activado a las 20:00")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                notify(NOTIFICATION_ID, builder.build())
                Toast.makeText(this@MainActivity, "✅ Notificación enviada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "SmartGarden", NotificationManager.IMPORTANCE_HIGH)
            channel.description = "Notificaciones del sistema de riego"
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    // Diálogo Confirmación
    override fun onDialogPositiveClick() {
        Snackbar.make(findViewById(android.R.id.content), "Datos guardados", Snackbar.LENGTH_SHORT).show()
    }

    override fun onDialogNegativeClick() {
        Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        BaseMenuActivity.actividadActual = 0
    }
}

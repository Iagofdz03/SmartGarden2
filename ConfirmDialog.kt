package com.example.smartgarden2

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ConfirmDialog : DialogFragment() {

    interface ConfirmDialogListener {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    private lateinit var listener: ConfirmDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as ConfirmDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context debe implementar ConfirmDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Confirmar acción")
            .setMessage("¿Deseas guardar los datos de esta planta?")
            .setPositiveButton("Aceptar") { _, _ ->
                listener.onDialogPositiveClick()
            }
            .setNegativeButton("Cancelar") { _, _ ->
                listener.onDialogNegativeClick()
            }
            .create()
    }
}

package com.john.proyecto_pmdm_john_2023_2024.ui.view


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.john.proyecto_pmdm_john_2023_2024.ui.restaurante.RestaurantViewModel


class DialogDeleteRestaurant(
    val pos: Int,
    val name: String,
    val onDeleteRestaurantDialog: RestaurantViewModel
) : DialogFragment() {

    // Interfaz para manejar eventos del diálogo
    interface DeleteRestaurantDialogListener {
        fun onDialogPositiveClick(pos: Int)
        fun onDialogNegativeClick()
    }

    // Instancia de la interfaz para enviar eventos a la actividad o fragmento
    private var mListener: DeleteRestaurantDialogListener? = null

    // Override del método onCreateView para construir el diálogo
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setMessage("¿Deseas borrar el restaurante $name?")
            .setPositiveButton("Sí") { dialog, id ->
                // Enviar el evento del botón positivo de vuelta a la actividad o fragmento principal
                mListener?.onDialogPositiveClick(pos)
            }
            .setNegativeButton("No") { dialog, id ->
                // Descartar el diálogo si se hace clic en "No"
                mListener?.onDialogNegativeClick()
                dialog.dismiss()
            }
            .create()
    }

    // Método para establecer el Listener
    fun setDeleteRestaurantDialogListener(listener: DeleteRestaurantDialogListener) {
        mListener = listener
    }
}
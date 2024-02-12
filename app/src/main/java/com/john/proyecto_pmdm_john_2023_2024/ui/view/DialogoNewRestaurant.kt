package com.john.proyecto_pmdm_john_2023_2024.ui.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.john.proyecto_pmdm_john_2023_2024.R

class DialogNewRestaurant(
    var onNewRestaurantDialogListener: NewRestaurantDialogListener
) : DialogFragment() {

    interface NewRestaurantDialogListener {
        fun onDialogPositiveClick(
            newName: String,
            newCity: String,
            newProvince: String,
            newPhoneNumber: String,
            newImageUrl: String
        )

        fun onDialogNegativeClick()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout
            .edit_restaurant_dialog_listener, null)
        val newNameEditText: EditText = view.findViewById(R.id.editTextNewName)
        val newCityEditText: EditText = view.findViewById(R.id.editTextNewCity)
        val newProvinceEditText: EditText = view.findViewById(R.id.editTextNewProvince)
        val newPhoneNumberEditText: EditText = view.findViewById(R.id.editTextNewPhoneNumber)
        val newImageUrlEditText: EditText = view.findViewById(R.id.editTextNewImageUrl)

        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setTitle("Nuevo restaurante")
            .setPositiveButton("Guardar") { dialog, id ->
                val newName = newNameEditText.text.toString()
                val newCity = newCityEditText.text.toString()
                val newProvince = newProvinceEditText.text.toString()
                val newPhoneNumber = newPhoneNumberEditText.text.toString()
                val newImageUrl = newImageUrlEditText.text.toString()
                onNewRestaurantDialogListener?.onDialogPositiveClick(newName, newCity,
                    newProvince, newPhoneNumber, newImageUrl)
            }
            .setNegativeButton("Cancelar") { dialog, id ->
                onNewRestaurantDialogListener?.onDialogNegativeClick()
                dialog.dismiss()
            }
            .create()
    }
    fun setNewRestaurantDialogListener(listener: NewRestaurantDialogListener) {
        onNewRestaurantDialogListener = listener
    }
}
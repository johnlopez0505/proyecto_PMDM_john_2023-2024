package com.john.proyecto_pmdm_john_2023_2024.ui.view.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.john.proyecto_pmdm_john_2023_2024.R
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseRestaurant.CreateRestaurantUseCase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogNewRestaurant : DialogFragment() {

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

    // Instancia de la interfaz para enviar eventos a la actividad o fragmento
    private var mListener : NewRestaurantDialogListener? = null
    @SuppressLint("UseGetLayoutInflater")
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
                mListener?.onDialogPositiveClick(newName, newCity,
                    newProvince, newPhoneNumber, newImageUrl)
            }
            .setNegativeButton("Cancelar") { dialog, id ->
                mListener?.onDialogNegativeClick()
                dialog.dismiss()
            }
            .create()
    }
    private fun setNewRestaurantDialogListener(listener: NewRestaurantDialogListener) {
        mListener = listener
    }

    fun mostrarDialogoNewRestaurant(
        recyclerView: RecyclerView,
        context: FragmentActivity,
        addRestaurantUseCase: CreateRestaurantUseCase,
        restaurantListLiveData: MutableLiveData<List<Restaurant>>
    ) {
        val dialog = DialogNewRestaurant()
        dialog.setNewRestaurantDialogListener(object :
            NewRestaurantDialogListener {
            override fun onDialogPositiveClick(
                newName: String,
                newCity: String,
                newProvince: String,
                newPhoneNumber: String,
                newImageUrl: String
            ) {
                // agregamos un nuevo restaurante
                val newRestaurant = Restaurant(
                    newName, newCity, newProvince, newPhoneNumber,
                    newImageUrl
                )
                addRestaurantUseCase.setRestaurant(newRestaurant)
                restaurantListLiveData.value = addRestaurantUseCase()
                Toast.makeText(context, "Nuevo restaurante agregado", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onDialogNegativeClick() {
                Toast.makeText(
                    context, "Cancelada la creacion de un nuevo Restaurante",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
        dialog.show((context as AppCompatActivity).supportFragmentManager, "DialogNewRestaurant")
    }

}
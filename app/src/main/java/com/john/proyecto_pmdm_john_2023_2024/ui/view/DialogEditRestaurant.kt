package com.john.proyecto_pmdm_john_2023_2024.ui.view


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.john.proyecto_pmdm_john_2023_2024.R
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.EditRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.ui.adapter.AdapterRestaurant
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Provider

@AndroidEntryPoint
class DialogEditRestaurant(
    val pos: Int = 0,
    val restaurant: Restaurant? = null,
) : DialogFragment() {

    private lateinit var newNameEditText: EditText
    private lateinit var newCityEditText: EditText
    private lateinit var newProvinceEditText: EditText
    private lateinit var newPhoneNumberEditText: EditText
    private lateinit var newImageUrlEditText: EditText

    interface EditRestaurantDialogListener {
        fun onDialogPositiveClick(pos: Int, newName: String, newCity: String,
                                  newProvince: String, newPhoneNumber: String, newImageUrl: String)
        fun onDialogNegativeClick()
    }

    // Instancia de la interfaz para enviar eventos a la actividad o fragmento
    private var mListener : EditRestaurantDialogListener? = null
    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout
            .edit_restaurant_dialog_listener, null)
        newNameEditText = view.findViewById(R.id.editTextNewName)
        newCityEditText = view.findViewById(R.id.editTextNewCity)
        newProvinceEditText = view.findViewById(R.id.editTextNewProvince)
        newPhoneNumberEditText = view.findViewById(R.id.editTextNewPhoneNumber)
        newImageUrlEditText = view.findViewById(R.id.editTextNewImageUrl)

        // Prellenar los campos con los valores actuales del restaurante
        newNameEditText.setText(restaurant?.name)
        newCityEditText.setText(restaurant?.city)
        newProvinceEditText.setText(restaurant?.province)
        newPhoneNumberEditText.setText(restaurant?.phone)
        newImageUrlEditText.setText(restaurant?.image)

        return AlertDialog.Builder(requireContext())

            .setView(view)
            .setTitle("Editar restaurante")
            .setPositiveButton("Guardar") { dialog, id ->
                val newName = newNameEditText.text.toString().trim()
                val newCity = newCityEditText.text.toString().trim()
                val newProvince = newProvinceEditText.text.toString().trim()
                val newPhoneNumber = newPhoneNumberEditText.text.toString().trim()
                val newImageUrl = newImageUrlEditText.text.toString().trim()
                mListener?.onDialogPositiveClick(pos, newName, newCity,
                    newProvince, newPhoneNumber, newImageUrl)
            }
            .setNegativeButton("Cancelar") { dialog, id ->
                mListener?.onDialogNegativeClick()
                dialog.dismiss()
            }
            .create()
    }

    private fun setEditRestaurantDialogListener(listener: EditRestaurantDialogListener) {
        mListener = listener
    }

    fun mostrarDialogoEditarRestaurante(
            pos: Int,
            recyclerView: RecyclerView,
            context: Context,
            editRestaurantUseCase: EditRestaurantUseCase,
            restaurantListLiveData: MutableLiveData<List<Restaurant>>
            ) : Restaurant{
        val adapter = recyclerView.adapter  as AdapterRestaurant
        var listarest = adapter.restaurantRepository
        var editedRestaurant = Restaurant()
        if (pos in listarest.indices) {
            val dialog = DialogEditRestaurant(pos, listarest[pos])
            dialog.setEditRestaurantDialogListener(object :
                EditRestaurantDialogListener{
                override fun onDialogPositiveClick(
                    pos: Int,
                    newName: String,
                    newCity: String,
                    newProvince: String,
                    newPhoneNumber: String,
                    newImageUrl: String
                ) {

                    // Lógica para guardar la edición del restaurante
                    editedRestaurant = Restaurant(newName, newCity, newProvince,
                        newPhoneNumber, newImageUrl)

                    editRestaurantUseCase.setRestaurant(pos,editedRestaurant)
                    restaurantListLiveData.value = editRestaurantUseCase()
                    Toast.makeText(context, "Restaurante editado correctamente",
                        Toast.LENGTH_LONG).show()
                }

                override fun onDialogNegativeClick() {
                    // Acciones después de que el usuario hace clic en "Cancelar"
                    Toast.makeText(context, "Edición del restaurante cancelada",
                        Toast.LENGTH_LONG).show()
                }

            })

            dialog.show((context as AppCompatActivity).supportFragmentManager,
                "DialogEditRestaurant")
        } else {
            // Manejar el caso en el que pos no es válido
            Toast.makeText(context, "Posición no válida", Toast.LENGTH_SHORT).show()
        }
        return editedRestaurant
    }
}

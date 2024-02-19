package com.john.proyecto_pmdm_john_2023_2024.ui.view



import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.john.proyecto_pmdm_john_2023_2024.data.models.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.DeleteRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.ui.adapter.AdapterRestaurant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogDeleteRestaurant(
    val pos: Int = 0,
    val name: String = "",
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
    private fun setDeleteRestaurantDialogListener(listener: DeleteRestaurantDialogListener) {
        mListener = listener
    }

    fun mostrarDialogoEliminarRestaurante(
        pos: Int,
        recyclerView: RecyclerView,
        context: Context,
        deleteRestaurantUseCase: DeleteRestaurantUseCase,
        restaurantListLiveData: MutableLiveData<List<Restaurant>>
    ) {

        val adapter = recyclerView.adapter  as AdapterRestaurant
        var listarest = adapter.restaurantRepository
        if(pos in listarest.indices) {
            val dialog = DialogDeleteRestaurant(pos, listarest[pos].name)
            dialog.setDeleteRestaurantDialogListener(object :
                DeleteRestaurantDialogListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onDialogPositiveClick(pos: Int) {
                    Toast.makeText(
                        context, "Borrado el Restaurante ${listarest[pos].name}" +
                                " de la posición $pos", Toast.LENGTH_LONG
                    ).show()
                    deleteRestaurantUseCase.setPosition(pos)
                    restaurantListLiveData.value = deleteRestaurantUseCase()
                }

                override fun onDialogNegativeClick() {
                    Toast.makeText(context, "Cancelado el borrado de  " +
                            "${listarest[pos].name} de la posición $pos", Toast.LENGTH_LONG).show()
                }
            })
            dialog.show((context as AppCompatActivity).supportFragmentManager, "DialogDeleteRestaurant")
        }else {
            // Manejar el caso en el que pos no es válido
            Toast.makeText(context, "Posición no válida", Toast.LENGTH_SHORT).show()
        }
    }
}
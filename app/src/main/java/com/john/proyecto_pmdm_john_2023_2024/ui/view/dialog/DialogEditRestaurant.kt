package com.john.proyecto_pmdm_john_2023_2024.ui.view.dialog


import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.webkit.MimeTypeMap
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.john.proyecto_pmdm_john_2023_2024.R
import com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseRestaurant.EditRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.ui.adapter.AdapterRestaurant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class DialogEditRestaurant(
    val pos: Int = 0,
    val restaurant: Restaurant? = null,
) : DialogFragment() {

    private lateinit var newNameEditText: EditText
    private lateinit var newCityEditText: EditText
    private lateinit var newProvinceEditText: EditText
    private lateinit var newPhoneNumberEditText: EditText
    private lateinit var newImageUrlEditText: ImageView

    interface EditRestaurantDialogListener {
        fun onDialogPositiveClick(
            pos: Int, newName: String, newCity: String,
            newProvince: String, newPhoneNumber: String, newImageUrl: String?
        )
        fun onDialogNegativeClick()
    }

    // Instancia de la interfaz para enviar eventos a la actividad o fragmento
    private var mListener : EditRestaurantDialogListener? = null
    private var imgBase64: String? = null
    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout
            .edit_restaurant_dialog_listener, null)
        newNameEditText = view.findViewById(R.id.editTextNewName)
        newCityEditText = view.findViewById(R.id.editTextNewCity)
        newProvinceEditText = view.findViewById(R.id.editTextNewProvince)
        newPhoneNumberEditText = view.findViewById(R.id.editTextNewPhoneNumber)
        newImageUrlEditText = view.findViewById(R.id.text_view_imagen_foto3)

        // Prellenar los campos con los valores actuales del restaurante
        newNameEditText.setText(restaurant?.nombre)
        newCityEditText.setText(restaurant?.ciudad)
        newProvinceEditText.setText(restaurant?.provincia)
        newPhoneNumberEditText.setText(restaurant?.telefono)
        Log.i(TAG, "onCreateDialog:imagen traida : $newImageUrlEditText")
        Glide
            .with(this)
            .load(restaurant?. imagen)
            .centerCrop()
            .into( newImageUrlEditText)
        val buttonCamara  = view.findViewById<ImageButton>(R.id.imag_button_cap_foto3)
        val buttonBuscar = view.findViewById<ImageButton>(R.id.image_button_open3)

        buttonCamara.setOnClickListener {
            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))

        }
        buttonBuscar.setOnClickListener {
            pickPhotoFromGallery()
        }

        return AlertDialog.Builder(requireContext())

            .setView(view)
            .setTitle("Editar restaurante")
            .setPositiveButton("Guardar") { dialog, id ->
                val newName = newNameEditText.text.toString().trim()
                val newCity = newCityEditText.text.toString().trim()
                val newProvince = newProvinceEditText.text.toString().trim()
                val newPhoneNumber = newPhoneNumberEditText.text.toString().trim()

                val newImageUrl = imgBase64
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
        restaurantListLiveData: MutableLiveData<List<Restaurant>>,
        token: String?,
        id: String?
    ) {
        val adapter = recyclerView.adapter  as AdapterRestaurant
        var listarest = adapter.restaurantRepository.toMutableList()


        if (pos in listarest.indices) {
            val dialog = DialogEditRestaurant(pos, listarest[pos])
            dialog.setEditRestaurantDialogListener(object :
                EditRestaurantDialogListener {
                override fun onDialogPositiveClick(
                    pos: Int,
                    newName: String,
                    newCity: String,
                    newProvince: String,
                    newPhoneNumber: String,
                    newImageUrl: String?
                ) {

                    // Lógica para guardar la edición del restaurante
                    val imagen = restaurant?.imagen

                    val editedRestaurant = Restaurant(
                        newName, newCity, newProvince,
                        newPhoneNumber, newImageUrl, id
                    )
                    Log.i(TAG, "onDialogPositiveClick este es el nuevo restaurante: ${listarest}")
                    lifecycleScope.launch {
                        editRestaurantUseCase.invoke(listarest[pos].id.toInt(), editedRestaurant,token)
                        listarest[pos] = editedRestaurant
                        restaurantListLiveData.value = listarest
                        Toast.makeText(context, "Restaurante editado correctamente",
                            Toast.LENGTH_LONG).show()
                    }
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
    }
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val imageBitmap = intent?.extras?.get("data") as Bitmap
                val imageView =  newImageUrlEditText
                imageView.setImageBitmap(imageBitmap)
                // Convertimos la imagen a base 64
                val base64Image = bitmapToBase64(imageBitmap,"PNG")
                imgBase64 = base64Image
                Toast.makeText(requireActivity(), "Imagen selecionada correctamente", Toast.LENGTH_LONG).show()
            }
        }


    private fun bitmapToBase64(bitmap: Bitmap, type : String): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        val imagebase64 = Base64.encodeToString(byteArray, Base64.DEFAULT)
        return "data:image/$type;base64,$imagebase64"
    }

    private val startForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.data
            val  bitmap: Bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, data);

            val cR = requireActivity().contentResolver;
            val mime =  MimeTypeMap.getSingleton();
            val type = mime.getExtensionFromMimeType(cR.getType(data!!));
            type.toString()
            val base64Image = bitmapToBase64(bitmap,  type.toString())
            newImageUrlEditText.setImageURI(data)
            imgBase64 = base64Image

        }
    }
    private fun pickPhotoFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startForActivityGallery.launch(intent)
    }
}

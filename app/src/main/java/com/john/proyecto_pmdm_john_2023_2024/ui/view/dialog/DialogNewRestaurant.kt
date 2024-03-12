package com.john.proyecto_pmdm_john_2023_2024.ui.view.dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.john.proyecto_pmdm_john_2023_2024.R
import com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.domain.useCase.useCaseRestaurant.CreateRestaurantUseCase
import com.john.proyecto_pmdm_john_2023_2024.ui.adapter.AdapterRestaurant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

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
    private lateinit var imgBase64: String
    private lateinit var  newImageUrlEditText : ImageView
    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout
            .edit_restaurant_dialog_listener, null)
        val newNameEditText: EditText = view.findViewById(R.id.editTextNewName)
        val newCityEditText: EditText = view.findViewById(R.id.editTextNewCity)
        val newProvinceEditText: EditText = view.findViewById(R.id.editTextNewProvince)
        val newPhoneNumberEditText: EditText = view.findViewById(R.id.editTextNewPhoneNumber)
        newImageUrlEditText = view.findViewById(R.id.text_view_imagen_foto3)
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
            .setTitle("Nuevo restaurante")
            .setPositiveButton("Guardar") { dialog, id ->
                val newName = newNameEditText.text.toString()
                val newCity = newCityEditText.text.toString()
                val newProvince = newProvinceEditText.text.toString()
                val newPhoneNumber = newPhoneNumberEditText.text.toString()
                val imagenUrl = imgBase64
                Glide
                    .with(this)
                    .load(imagenUrl)
                    .centerCrop()
                    .into( newImageUrlEditText)
                mListener?.onDialogPositiveClick(newName, newCity,
                    newProvince, newPhoneNumber, imagenUrl)
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

    fun mostrarDialogoNewRestaurant (
        recyclerView: RecyclerView,
        context: FragmentActivity,
        addRestaurantUseCase: CreateRestaurantUseCase,
        restaurantListLiveData: MutableLiveData<List<Restaurant>>,
        token: String?,
        id: String?,
    ) {
        val adapter = recyclerView.adapter  as AdapterRestaurant
        var listarest = adapter.restaurantRepository.toMutableList()
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
                    newImageUrl, id
                )
                listarest.add(newRestaurant)
                lifecycleScope.launch {
                    addRestaurantUseCase.invoke(newRestaurant,token)
                }
                restaurantListLiveData.value =listarest
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
                Toast.makeText(requireActivity(), "Imagen en Base64: $base64Image", Toast.LENGTH_LONG).show()
            }
        }


    private fun bitmapToBase64(bitmap: Bitmap,type : String): String {
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
            val  bitmap:Bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, data);

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
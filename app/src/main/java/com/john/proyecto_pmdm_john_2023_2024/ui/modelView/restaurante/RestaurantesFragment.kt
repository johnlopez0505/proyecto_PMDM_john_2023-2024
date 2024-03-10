package com.john.proyecto_pmdm_john_2023_2024.ui.modelView.restaurante

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.john.proyecto_pmdm_john_2023_2024.R
import com.john.proyecto_pmdm_john_2023_2024.databinding.FragmentRestaurantesBinding
import com.john.proyecto_pmdm_john_2023_2024.domain.model.restaurant.Restaurant
import com.john.proyecto_pmdm_john_2023_2024.ui.adapter.AdapterRestaurant
import com.john.proyecto_pmdm_john_2023_2024.ui.view.mainActivity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantesFragment : Fragment() {
    lateinit var binding: FragmentRestaurantesBinding
    lateinit var listRestaurants : MutableList<Restaurant> //lista de objetos
    lateinit var navController : NavController
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: AdapterRestaurant
    private val restaurantViewModel: RestaurantViewModel by viewModels() //tiene que ser constante.
    private lateinit var contexto : MainActivity
    private lateinit var shared : SharedPreferences
    private var post = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRestaurantesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AdapterRestaurant( { pos -> delRestaurant(pos) },
            { pos -> updateRestaurant(pos)},{pos ->sendInfoRestaurant(pos)})
        initRecyclerView() //inicializamos el recyclerView. De memento, contendrá lista empty.
        init()
        restaurantViewModel.listarRestarurants()

    }

    private fun cargarPreferenciasCompartidas() {
        val fichePreferencias : String = getString(R.string.preferencias_fichero_login)
        shared = contexto.getSharedPreferences(fichePreferencias, AppCompatActivity.MODE_PRIVATE)
    }

    private fun initRecyclerView(){
        binding.myRecyclerView.layoutManager = LinearLayoutManager( requireContext())
        recyclerView = binding.myRecyclerView.findViewById(R.id.my_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

    }


    private fun init(){
        contexto = requireActivity() as MainActivity
        cargarPreferenciasCompartidas()
        registerLiveData()
        val token = shared.getString(getString(R.string.token),"")
        restaurantViewModel.iniciar(adapter,token)
    }
    private fun delRestaurant(pos:Int){
        val token = shared.getString(getString(R.string.token),"")
        restaurantViewModel.delRestaurant(pos,recyclerView,contexto,token)
        post = pos
    }

    private fun updateRestaurant(pos: Int){
        val token = shared.getString(getString(R.string.token),"")
        val id = shared.getString("id","")
        restaurantViewModel.updateRestaurant(pos,recyclerView,contexto,token,id)
        post = pos
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun registerLiveData() {
        restaurantViewModel.restaurantListLiveData.observe(requireActivity()) {
            myList ->
            Log.i(TAG, "registerLiveData: $myList")
            listRestaurants = myList.toMutableList()
            adapter.restaurantRepository = myList!! //aseguro los datos.
            adapter.notifyDataSetChanged()
            adapter.notifyItemChanged(myList.size -1)
            adapter.notifyItemRemoved(post)
            adapter.notifyItemChanged(post)
        }

        restaurantViewModel.progressBarLiveData.observe(requireActivity()) {
            visible ->
            binding.progressBar.isVisible = visible
            Log.i("TAG-DOGS", "ProgressBar esta $visible")
        }

        binding.fab.setOnClickListener{
            val token = shared.getString(getString(R.string.token),"")
            val id = shared.getString("id","")
            Log.i(TAG, "id usuario  : $id")
            restaurantViewModel.addRestaurant(recyclerView,requireActivity(),token,id)
        }
    }

    private fun sendInfoRestaurant(pos: Int) {

        val myActivity = requireActivity()
        val navHost = myActivity //referencia del activity
            .supportFragmentManager //administrador de Fragmentos
            .findFragmentById(R.id.nav_host_fragment_content_main)

        navHost.let {//Si entramos dentro, no es nulo.
            navController = navHost!!.findNavController() //buscamos su NavController
            val (name, city, province, phone, image) = adapter.restaurantRepository[pos]

            navController.navigate(
                RestaurantesFragmentDirections.actionNavRestaurantesToDescripcionFragment(
                    data = arrayOf("$pos", name, city, province, phone, image
                    )
                )
            )
        }
        Toast.makeText(
            context, "Este es el restaurante ${listRestaurants[pos].nombre}" +
                    " de la posición $pos", Toast.LENGTH_LONG
        ).show()
    }
}






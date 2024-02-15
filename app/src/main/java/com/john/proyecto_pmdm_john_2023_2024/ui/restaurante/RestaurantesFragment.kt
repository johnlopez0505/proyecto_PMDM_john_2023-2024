package com.john.proyecto_pmdm_john_2023_2024.ui.restaurante

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.john.proyecto_pmdm_john_2023_2024.R
import com.john.proyecto_pmdm_john_2023_2024.databinding.FragmentRestaurantesBinding
import com.john.proyecto_pmdm_john_2023_2024.ui.adapter.AdapterRestaurant

class RestaurantesFragment : Fragment() {
    lateinit var binding: FragmentRestaurantesBinding
    lateinit var recyclerView : RecyclerView
    lateinit var adapter: AdapterRestaurant
    private val restaurantViewModel: RestaurantViewModel by viewModels()
    //tiene que ser constante.

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

        initRecyclerView() //inicializamos el recyclerView. De memento, contendrá lista empty.
        init()
        registerLiveData()
        loadDada()

    }

    private fun initRecyclerView(){
        binding.myRecyclerView.layoutManager = LinearLayoutManager( requireContext())
        recyclerView = binding.myRecyclerView.findViewById(R.id.my_recycler_view)
    }

    private fun loadDada() {
        restaurantViewModel.listarRestarurants() //simulamos un evento para iniciar la carga de datos desde el viewmodel

    }


    fun init(){
        restaurantViewModel.iniciar(recyclerView, requireActivity())
    }

    private fun registerLiveData() {
        restaurantViewModel.restaurantListLiveData.observe(requireActivity()) {
            myList ->
            //Aquí hacemos la actualización del adapter.
            adapter.restaurantRepository = myList!!.toMutableList() //aseguro los datos.
            binding.myRecyclerView.adapter = this.adapter //le asigno el adapter.
            adapter.notifyDataSetChanged()
        }

        restaurantViewModel.progressBarLiveData.observe(requireActivity()) {
            visible ->
            binding.progressBar.isVisible = visible
            Log.i("TAG-DOGS", "ProgressBar esta $visible")
        }

        binding.fab.setOnClickListener{
            restaurantViewModel.addRestaurant(recyclerView,requireContext())
        }

        restaurantViewModel.deleteRestaurantLiveData.observe(requireActivity()){
            restaurant->
            adapter.restaurantRepository.remove(restaurant)
            binding.myRecyclerView.adapter = this.adapter
            adapter.notifyDataSetChanged()
        }

        restaurantViewModel.updateRestauranteLiveData.observe(requireActivity()){
            restaurant->
            adapter.restaurantRepository.add(restaurant)
            binding.myRecyclerView.adapter = this.adapter
            adapter.notifyDataSetChanged()
        }


    }

}






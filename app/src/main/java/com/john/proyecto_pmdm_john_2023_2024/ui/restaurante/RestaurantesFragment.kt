package com.john.proyecto_pmdm_john_2023_2024.ui.restaurante

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.john.proyecto_pmdm_john_2023_2024.R
import com.john.proyecto_pmdm_john_2023_2024.databinding.FragmentRestaurantesBinding
import com.john.proyecto_pmdm_john_2023_2024.ui.adapter.AdapterRestaurant
import com.john.proyecto_pmdm_john_2023_2024.ui.view.MainActivity

class RestaurantesFragment : Fragment() {
    lateinit var binding: FragmentRestaurantesBinding
    lateinit var recyclerView : RecyclerView
    lateinit var adapter: AdapterRestaurant
    private val restaurantViewModel: RestaurantViewModel by viewModels() //tiene que ser constante.
    private lateinit var contexto : MainActivity
    var post = 0
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

        adapter = AdapterRestaurant( { pos -> delRestaurant(pos) }, { pos -> updateRestaurant(pos)})
        initRecyclerView() //inicializamos el recyclerView. De memento, contendrá lista empty.
        init()

    }

    private fun initRecyclerView(){
        binding.myRecyclerView.layoutManager = LinearLayoutManager( requireContext())
        recyclerView = binding.myRecyclerView.findViewById(R.id.my_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        //binding.myRecyclerView.adapter = adapter
        recyclerView.adapter = adapter
        //adapter = AdapterRestaurant()
    }


    fun init(){
        contexto = requireActivity() as MainActivity
        //restaurantViewModel.iniciar(recyclerView, requireActivity())
        registerLiveData()
    }
    fun delRestaurant(pos:Int){
        restaurantViewModel.delRestaurant(pos,recyclerView,contexto)
        post = pos
    }

    fun updateRestaurant(pos: Int){
        restaurantViewModel.updateRestaurant(pos,recyclerView,contexto)
    }

    private fun registerLiveData() {
        restaurantViewModel.restaurantListLiveData.observe(requireActivity()) {
            myList ->
            adapter.restaurantRepository = myList!! //aseguro los datos.
            adapter.notifyDataSetChanged()
            adapter.notifyItemChanged(myList.size -1)
            adapter.notifyItemRemoved(post)
            adapter.notifyDataSetChanged()
            adapter.notifyItemChanged(post)
        }

        restaurantViewModel.progressBarLiveData.observe(requireActivity()) {
            visible ->
            binding.progressBar.isVisible = visible
            Log.i("TAG-DOGS", "ProgressBar esta $visible")
        }

        binding.fab.setOnClickListener{
            restaurantViewModel.addRestaurant(recyclerView,requireContext())
        }


    }

}






package com.example.feedm.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feedm.R
import com.example.feedm.databinding.ActivityPetsBinding
import com.example.feedm.domain.model.Pet
import com.example.feedm.ui.view.FragmentEditPet.Companion.BUNDLE_ID
import com.example.feedm.ui.view.managementClasses.petsAdapter.MyPetsAdapter
import com.example.feedm.ui.viewmodel.PetViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PetsActivity : AppCompatActivity() {

    private val petViewModel: PetViewModel by viewModels()
    // Controla si el fragmento de edición ha sido iniciado
    var fragmentWasStarted = false
    // RecyclerView que muestra la lista de mascotas
    private lateinit var adapter: MyPetsAdapter
    private var recyclerViewIsInitialized = false

    private lateinit var binding: ActivityPetsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val metrics = resources.displayMetrics
        val densityDpi = metrics.densityDpi


        when (densityDpi) {
            DisplayMetrics.DENSITY_LOW -> Log.d("DimensInfo", "Densidad aplicada: ldpi (120dpi)")
            DisplayMetrics.DENSITY_MEDIUM -> Log.d("DimensInfo", "Densidad aplicada: mdpi (160dpi)")
            DisplayMetrics.DENSITY_HIGH -> Log.d("DimensInfo", "Densidad aplicada: hdpi (240dpi)")
            DisplayMetrics.DENSITY_XHIGH -> Log.d("DimensInfo", "Densidad aplicada: xhdpi (320dpi)")
            DisplayMetrics.DENSITY_XXHIGH -> Log.d(
                "DimensInfo",
                "Densidad aplicada: xxhdpi (480dpi)"
            )

            DisplayMetrics.DENSITY_XXXHIGH -> Log.d(
                "DimensInfo",
                "Densidad aplicada: xxxhdpi (640dpi)"
            )

            else -> Log.d(
                "DimensInfo",
                "Densidad personalizada o desconocida: " + densityDpi + "dpi"
            )
        }
        petViewModel.onCreate()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pa_lytConstraint)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        configureFragment()  // Configura el fragmento de edición
        innitViews() // Inicializa las vistas del Activity
    }

    // Inicializa y configura las vistas del Activity
    private fun innitViews() {
        // Si hay mascotas registradas, crea y actualiza el RecyclerView
        petViewModel.pets.observe(this, Observer {
            if(it.isNullOrEmpty()){
                Toast.makeText(this,"Todavia no hay mascotas",Toast.LENGTH_SHORT).show()
            }
            else{
                createRecyclerView()
                setRecyclerView(it)
                binding.paRecyclerView.alpha = 1f
            }
        })
        binding.paFloatbtnAddPet.setOnClickListener { addPet() }
    }

    // Crea el RecyclerView y asigna un layout manager
    private fun createRecyclerView() {
        binding.paRecyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewIsInitialized = true

    }

    // Inicia la actividad para añadir una nueva mascota
    private fun addPet() {
        val intent = Intent(this, FormActivity::class.java)
        startActivity(intent)
    }


    // Actualiza el RecyclerView con la lista de mascotas
    private fun setRecyclerView(petList: List<Pet>) {
        val intent = Intent(this, SearchActivity::class.java)
        adapter = MyPetsAdapter(this, petList,
            // Al hacer clic en un item, inicia la actividad de búsqueda con la información de la mascota
            onItemClick = { pet ->
                val bundle = Bundle()
                bundle.putInt("petId", pet.id)
                intent.putExtras(bundle)
                startActivity(intent)
            },
            // Al hacer clic largo, muestra un menú emergente con opciones
            onItemLongClick = { view, pet, pos ->
                val popupMenu = PopupMenu(this, view)
                popupMenu.menuInflater.inflate(R.menu.item_menu_petsrecyclerview, popupMenu.menu)

                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.item1MenuPetRV -> {
                            showFragment(pet.id)
                            Log.i("step8", "lifecycleScope")
                            true
                        }
                        R.id.item2MenuPetRV -> {
                            petViewModel.deletePet(pet)
                            binding.paRecyclerView.alpha = 0f
                            true
                        }
                        else -> false
                    }
                }
                popupMenu.show()
            }
        )
        binding.paRecyclerView.adapter = adapter

    }


    // Configura el listener para recibir resultados del fragmento de edición
    private fun configureFragment() {
        supportFragmentManager.setFragmentResultListener("result", this) { _, result ->
            val finished = result.getBoolean("finished")
            if (finished) {
                Log.i("step7", "setFragmentResultListener()")
                val fragment = supportFragmentManager.findFragmentByTag("fragment")
                supportFragmentManager.beginTransaction().remove(fragment!!).commit()  // Elimina el fragmento
                fragmentWasStarted = false
            }
        }
    }

    // Muestra el fragmento de edición de mascotas
    private fun showFragment(petId: Int) {
        if (!fragmentWasStarted) {
            Log.i("step2", "showFragment()")
            val bundle = bundleOf(BUNDLE_ID to petId )

            supportFragmentManager.beginTransaction()
                .replace<FragmentEditPet>(R.id.fragmentContainer_EditPet, "fragment", args = bundle)
                .commit()

            fragmentWasStarted = true
        }
    }
}

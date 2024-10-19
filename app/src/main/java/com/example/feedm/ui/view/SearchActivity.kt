package com.example.feedm.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feedm.R
import com.example.feedm.core.RetrofitServiceGoogleFactory
import com.example.feedm.data.model.remoteResultModel.GoogleRemoteResult
import com.example.feedm.data.PetsRepository
import com.example.feedm.ui.view.managementClasses.resultAdapter.MySearchAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {


    private lateinit var saRecyclerView: RecyclerView
    private val service = RetrofitServiceGoogleFactory.makeRetrofitServiceGoogle()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        createRecyclerView()
        val imgPet : ImageView = findViewById(R.id.sa_imgPet)
        val txtNamePet : TextView = findViewById(R.id.sa_txtNamePet)
        /*val pet =  PetsRepository(this).getPet(intent.getIntExtra("petId",-1))

        txtNamePet.text = pet.nombre
        if (pet.animal.equals("dog"))imgPet.setImageDrawable(AppCompatResources
            .getDrawable(this, R.drawable.img_dog_illustration))
        else imgPet.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.gato))

        hacerBusqueda(pet.query){resultados ->
            actualizarRecyclerView(resultados)
        }
*/

        val saBtnBack: ImageButton = findViewById(R.id.sa_BtnBack)
        saBtnBack.setOnClickListener{goBack()}

    }

    private fun createRecyclerView(){
        saRecyclerView = findViewById(R.id.sa_RecyclerView)
        saRecyclerView.layoutManager = GridLayoutManager(this, 2)

    }


    private fun hacerBusqueda(query: String, googleRemoteResult: (GoogleRemoteResult)->Unit){

        val request= "https://www.googleapis.com/customsearch/v1?key=" +
                "AIzaSyCtgeX8hWKQ8rgKphaNd6zL50LoHmWAQWM&cx=c2c6c2e186dfa40da&q=" + query

        CoroutineScope(Dispatchers.Main).launch {
            val result = service.searchPhoto(request)
            Log.d("hacerBusqueda","EL RESULT EN HACERBUSQUEDA ES $request")
            googleRemoteResult(result)
        }

    }


    private fun actualizarRecyclerView(resultados: GoogleRemoteResult){
        val intent = Intent(this, PetsActivity::class.java)
        Log.d("actualizarRecyclerView","EL RESULT EN ACTUALIZAR ES $resultados")
        val adapter = MySearchAdapter(resultados){ _ ->
            startActivity(intent)
        }
        saRecyclerView.adapter = adapter




    }

    private fun goBack(){
        finish()
    }
}
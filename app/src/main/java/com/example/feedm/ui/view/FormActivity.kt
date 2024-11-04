package com.example.feedm.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.feedm.R
import com.example.feedm.databinding.ActivityFormBinding
import com.example.feedm.domain.model.Pet
import com.example.feedm.ui.viewmodel.PetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormActivity : AppCompatActivity() {

    private val petViewModel: PetViewModel by viewModels()

    // Variables de datos
    private var animal= "dog"
    private var id = 0
    private lateinit var edad: String
    private lateinit var nombre: String
    private var peso: Double = 0.0
    private lateinit var sexo: String
    private lateinit var esterilizado: String
    private lateinit var actividad: String
    private lateinit var objetivo: String
    private lateinit var alergia: String
    private lateinit var query: String

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        petViewModel.onCreate()
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar vistas del formulario
        innitFormViews()

        // Botón para confirmar el formulario
        val intent = Intent(this, PetsActivity::class.java)

        // Listener para el botón de confirmar
        binding.faBtnCommit.setOnClickListener {
            if (recogerDatos()) {
                // Crear y guardar una nueva mascota
                val pet = Pet(
                    id,
                    animal,
                    nombre,
                    edad,
                    peso,
                    sexo,
                    esterilizado,
                    actividad,
                    objetivo,
                    alergia,
                    query
                )
                petViewModel.addPet(pet)
                Toast.makeText(this, R.string.fa_toastGetDataSuccess, Toast.LENGTH_SHORT).show()
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.fa_toastErrorGetData, Toast.LENGTH_LONG).show()
            }
        }

        // Botón de regreso
        binding.faBtnBack.setOnClickListener { goBack() }
    }

    // Inicializa y configura todas las vistas del formulario
    private fun innitFormViews() {
        binding.faImgSelectAnimal.setOnClickListener { animal = changeForm(animal) }

        // Configurar vistas del formulario
        setUpFormViews()
    }

    // Configura los listeners y adaptadores de las vistas del formulario
    private fun setUpFormViews() {
        var stringForTxtWeight: String
        var weightAdjusted: Int

        // Listener para el SeekBar del peso
        binding.faSbWeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                weightAdjusted = (binding.faSbWeight.progress / 5) * 5
                binding.faSbWeight.progress = weightAdjusted
                stringForTxtWeight = "${weightAdjusted / 10.0} Kg"
                binding.faTxtWeight.text = stringForTxtWeight
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Restablecer el color al tocar el SeekBar
                binding.faSbWeight.thumbTintList =
                    ContextCompat.getColorStateList(baseContext, R.color.tintilist_seekbar_standard)
                binding.faSbWeight.progressBackgroundTintList =
                    ContextCompat.getColorStateList(baseContext, R.color.tintlist_standard)
                binding.faSbWeight.progressTintList =
                    ContextCompat.getColorStateList(baseContext, R.color.tintilist_seekbar_standard)
                binding.faTxtWeight.setTextColor(resources.getColor(R.color.black, null))
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Adaptadores para los spinners
        val faArrayAdapter = arrayListOf(
            ArrayAdapter(
                this,
                R.layout.fa_item_spinner,
                resources.getStringArray(R.array.fa_arraySpinnerEdad)
            ),
            ArrayAdapter(
                this,
                R.layout.fa_item_spinner,
                resources.getStringArray(R.array.fa_arraySpinnerSexo)
            ),
            ArrayAdapter(
                this,
                R.layout.fa_item_spinner,
                resources.getStringArray(R.array.fa_arraySpinnerActividadFisica)
            ),
            ArrayAdapter(
                this,
                R.layout.fa_item_spinner,
                resources.getStringArray(R.array.fa_arraySpinnerObjetivo)
            )
        )

        val faArraySpinners = arrayListOf(
            binding.faSpinnerEdad,
            binding.faSpinnerSexo,
            binding.faSpinnerActividad,
            binding.faSpinnerObjetivo
        )

        // Asignar adaptadores a los spinners
        for (i in faArraySpinners.indices) {
            faArraySpinners[i].adapter = faArrayAdapter[i] as ArrayAdapter<*>
        }

        // Restablecer el color al seleccionar un RadioButton del RadioGroup
        binding.faRgEsterilizado.setOnCheckedChangeListener { _, _ ->
            for (i in 0 until binding.faRgEsterilizado.childCount) {
                val radioButton = binding.faRgEsterilizado.getChildAt(i) as RadioButton
                radioButton.buttonTintList =
                    ContextCompat.getColorStateList(this, R.color.tintlist_standard)
            }
        }
    }

    // Cambia el formulario entre perro y gato, ajustando la imagen y el peso
    private fun changeForm(animalIs: String): String {
        return if (animalIs == "dog") {
            binding.faImgSelectAnimal.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.gato
                )

            )
            binding.faSbWeight.max = 250
            "cat"
        } else {
            binding.faImgSelectAnimal.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.img_dog_illustration
                )
            )
            binding.faSbWeight.max = 800
            "dog"
        }
    }

    // Recoge los datos del formulario y valida la información
    private fun recogerDatos(): Boolean {
        if (petViewModel.pets.value!!.size > 0) {
            var foundId = 0
            for (i in petViewModel.pets.value as List<Pet>) {
                if (i.id != foundId) id = foundId
                else {
                    foundId++
                    id = foundId
                }
            }
        }
        // Validar nombre de la mascota
        if (binding.faEtPetName.text.isEmpty()) {
            binding.faEtPetName.error = "This can not be empty"
            return false
        }

        // Asignar valores de los campos
        nombre = binding.faEtPetName.text.toString()
        alergia = binding.faTextInputAlergias.text.toString()
        edad = binding.faSpinnerEdad.selectedItem.toString()

        // Validar el peso
        if (binding.faSbWeight.progress == 0) {
            binding.faSbWeight.thumbTintList =
                ContextCompat.getColorStateList(this, R.color.tintlist_error)
            binding.faSbWeight.progressBackgroundTintList =
                ContextCompat.getColorStateList(this, R.color.tintlist_error)
            binding.faTxtWeight.setTextColor(resources.getColor(R.color.red, null))
            return false
        }

        // Asignar más valores del formulario
        peso = binding.faSbWeight.progress / 10.0
        sexo = binding.faSpinnerSexo.selectedItem.toString()

        // Validar si se seleccionó la opción de esterilización
        esterilizado = when (binding.faRgEsterilizado.checkedRadioButtonId) {
            R.id.fa_rbEsterilizado1 -> "Si"
            R.id.fa_rbEsterilizado2 -> "No"
            else -> ""
        }

        if (esterilizado.isEmpty()) {
            for (i in 0 until binding.faRgEsterilizado.childCount) {
                val radioButton = binding.faRgEsterilizado.getChildAt(i) as RadioButton
                radioButton.buttonTintList =
                    ContextCompat.getColorStateList(this, R.color.tintlist_error)
            }
            return false
        }

        actividad = binding.faSpinnerActividad.selectedItem.toString()
        objetivo = binding.faSpinnerObjetivo.selectedItem.toString()

        // Crear consulta para el registro
        query = this.resources.getString(R.string.query, animal, sexo, edad, peso)
        return true
    }

    // Regresa a la actividad anterior
    private fun goBack() {
        finish()
    }
}

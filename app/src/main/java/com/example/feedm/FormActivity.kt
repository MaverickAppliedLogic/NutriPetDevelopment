package com.example.feedm

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.feedm.data.Pet
import com.example.feedm.managementClasses.PetsManager

class FormActivity : AppCompatActivity() {

    // Declaración de vistas y variables de formulario
    private lateinit var faEditTextPetName: EditText
    private lateinit var faTextInputAlergias: EditText
    private lateinit var faRgEsterilizado: RadioGroup
    private lateinit var faArraySpinners: ArrayList<Spinner>
    private lateinit var faArrayAdapter: ArrayList<Adapter>
    private lateinit var faImgSelectAnimal: ImageView
    private lateinit var txtWeight: TextView
    private lateinit var sbWeight: SeekBar

    // Variables de datos
    private var id = 0
    private var animal: String = ""
    private lateinit var edad: String
    private lateinit var nombre: String
    private var peso: Double = 0.0
    private lateinit var sexo: String
    private lateinit var esterilizado: String
    private lateinit var actividad: String
    private lateinit var objetivo: String
    private lateinit var alergia: String
    private lateinit var query: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar vistas del formulario
        innitFormViews()

        // Botón para confirmar el formulario
        val faBtnCommit: Button = findViewById(R.id.ma_btnCommit)
        val intent = Intent(this, PetsActivity::class.java)

        // Listener para el botón de confirmar
        faBtnCommit.setOnClickListener {
            if (recogerDatos()) {
                // Crear y guardar una nueva mascota
                val pet = Pet(id, animal, nombre, edad, peso, sexo, esterilizado, actividad, objetivo, alergia, query)
                PetsManager(this).addPet(pet)
                Toast.makeText(this, R.string.fa_toastGetDataSuccess, Toast.LENGTH_SHORT).show()
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.fa_toastErrorGetData, Toast.LENGTH_LONG).show()
            }
        }

        // Botón de regreso
        val faBtnBack: ImageButton = findViewById(R.id.fa_btnBack)
        faBtnBack.setOnClickListener { goBack() }
    }

    // Inicializa y configura todas las vistas del formulario
    private fun innitFormViews() {
        sbWeight = findViewById(R.id.fa_sbWeight)
        txtWeight = findViewById(R.id.fa_txtWeight)
        animal = "dog"  // Valor por defecto del animal
        faImgSelectAnimal = findViewById(R.id.fa_imgSelectAnimal)

        // Spinners para seleccionar datos del formulario
        faArraySpinners = arrayListOf(
            findViewById(R.id.fa_SpinnerEdad),
            findViewById(R.id.fa_SpinnerSexo),
            findViewById(R.id.fa_SpinnerActividad),
            findViewById(R.id.fa_SpinnerObjetivo)
        )

        // Elementos de nombre, alergias y esterilización
        faRgEsterilizado = findViewById(R.id.fa_rgEsterilizado)
        faEditTextPetName = findViewById(R.id.fa_etPetName)
        faTextInputAlergias = findViewById(R.id.fa_TextInputAlergias)

        // Configurar vistas del formulario
        setUpFormViews()
    }

    // Configura los listeners y adaptadores de las vistas del formulario
    private fun setUpFormViews() {
        var stringForTxtWeight: String
        var weightAdjusted: Int

        // Listener para el SeekBar del peso
        sbWeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                weightAdjusted = (sbWeight.progress / 5) * 5
                sbWeight.progress = weightAdjusted
                stringForTxtWeight = "${weightAdjusted / 10.0} Kg"
                txtWeight.text = stringForTxtWeight
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Restablecer el color al tocar el SeekBar
                sbWeight.thumbTintList = ContextCompat.getColorStateList(baseContext,R.color.tintilist_seekbar_standard)
                sbWeight.progressBackgroundTintList = ContextCompat.getColorStateList(baseContext,R.color.tintlist_standard)
                sbWeight.progressTintList = ContextCompat.getColorStateList(baseContext,R.color.tintilist_seekbar_standard)
                txtWeight.setTextColor(resources.getColor(R.color.black, null))
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Listener para cambiar el tipo de animal (gato/perro)
        faImgSelectAnimal.setOnClickListener { animal = changeForm(animal) }

        // Adaptadores para los spinners
        faArrayAdapter = arrayListOf(
            ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.fa_arraySpinnerEdad)),
            ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.fa_arraySpinnerSexo)),
            ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.fa_arraySpinnerActividadFisica)),
            ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.fa_arraySpinnerObjetivo))
        )

        // Asignar adaptadores a los spinners
        for (i in faArraySpinners.indices) {
            faArraySpinners[i].adapter = faArrayAdapter[i] as ArrayAdapter<*>
        }

        // Restablecer el color al seleccionar un RadioButton del RadioGroup
        faRgEsterilizado.setOnCheckedChangeListener { radioGroup, _ ->
            for(i in 0 until faRgEsterilizado.childCount){
                val radioButton = faRgEsterilizado.getChildAt(i) as RadioButton
                radioButton.buttonTintList = ContextCompat.getColorStateList(this,R.color.tintlist_standard)
            }
        }
    }

    // Cambia el formulario entre perro y gato, ajustando la imagen y el peso
    private fun changeForm(animalIs: String): String {
        return if (animalIs == "dog") {
            faImgSelectAnimal.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.gato))
            sbWeight.max = 250
            "cat"
        } else {
            faImgSelectAnimal.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.perro))
            sbWeight.max = 800
            "dog"
        }
    }

    // Recoge los datos del formulario y valida la información
    private fun recogerDatos(): Boolean {
        // Asignar ID basado en la cantidad de mascotas ya registradas
        if (PetsManager(this).getPetList().isNotEmpty()) {
            id = PetsManager(this).getPetList().size
        }

        // Validar nombre de la mascota
        if (faEditTextPetName.text.isEmpty()) {
            faEditTextPetName.error = "This can not be empty"
            return false
        }

        // Asignar valores de los campos
        nombre = faEditTextPetName.text.toString()
        alergia = faTextInputAlergias.text.toString()
        edad = faArraySpinners[0].selectedItem.toString()

        // Validar el peso
        if (sbWeight.progress == 0) {
            sbWeight.thumbTintList = ContextCompat.getColorStateList(this,R.color.tintlist_error)
            sbWeight.progressBackgroundTintList = ContextCompat.getColorStateList(this,R.color.tintlist_error)
            txtWeight.setTextColor(resources.getColor(R.color.red, null))
            return false
        }

        // Asignar más valores del formulario
        peso = sbWeight.progress / 10.0
        sexo = faArraySpinners[1].selectedItem.toString()

        // Validar si se seleccionó la opción de esterilización
        esterilizado = when (faRgEsterilizado.checkedRadioButtonId) {
            R.id.fa_rbEsterilizado1 -> "Si"
            R.id.fa_rbEsterilizado2 -> "No"
            else -> ""
        }

        if (esterilizado.isEmpty()) {
            for(i in 0 until faRgEsterilizado.childCount){
                val radioButton = faRgEsterilizado.getChildAt(i) as RadioButton
                radioButton.buttonTintList = ContextCompat.getColorStateList(this,R.color.tintlist_error)
            }
            return false
        }

        actividad = faArraySpinners[2].selectedItem.toString()
        objetivo = faArraySpinners[3].selectedItem.toString()

        // Crear consulta para el registro
        query = this.resources.getString(R.string.query, animal, sexo, edad, peso)
        return true
    }

    // Regresa a la actividad anterior
    private fun goBack() {
        finish()
    }
}

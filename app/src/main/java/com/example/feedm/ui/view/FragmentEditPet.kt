package com.example.feedm.ui.view


import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import android.util.Log
import com.example.feedm.R

import com.example.feedm.ui.view.managementClasses.PetsManager

import kotlin.math.roundToInt



class FragmentEditPet : Fragment() {
    private var id : Int = -1
    private var pos: Int = -1
    private var animal: String? = null
    private var name: String? = null
    private var allergies: String? = null
    private var sterilized: String? = null
    private var weight: Double? = null
    private val bundle = Bundle()
    private lateinit var fragmenteditpetTxtName: TextView
    private lateinit var fragmenteditpetTxtWeight: TextView
    private lateinit var fragmenteditpetImgpet: ImageView
    private lateinit var fragmenteditpetEtAllergies: EditText
    private lateinit var fragmenteditpetSbWeight: SeekBar
    private lateinit var fragmenteditpetRgSterilized: RadioGroup
    private lateinit var fragmenteditpetBtncancel: Button
    private lateinit var fragmenteditpetBtnapply: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(BUNDLE_ID)
            pos = it.getInt(BUNDLE_POS)
            animal = it.getString(BUNDLE_ANIMAL)
            name = it.getString(BUNDLE_NAME)
            allergies = it.getString(BUNDLE_ALLERGIES)
            sterilized = it.getString(BUNDLE_STERILIZED)
            weight = it.getDouble(BUNDLE_WEIGHT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_form_edit_pet, container, false)

    }


    override fun onResume(){
        super.onResume()
        val paLytConstraint = requireActivity().findViewById<ConstraintLayout>(R.id.pa_lytRecyclerView)
        val blurEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            RenderEffect.createBlurEffect(20f,20f,Shader.TileMode.CLAMP)
        } else {
            TODO("VERSION.SDK_INT < S")
        }
        Log.i("step3","onResume()")
        paLytConstraint.setRenderEffect(blurEffect)
        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("step5","onDestroy()")
        val paLytConstraint = requireActivity().findViewById<ConstraintLayout>(R.id.pa_lytRecyclerView)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            paLytConstraint.setRenderEffect(null)
        }
    }


    private fun initViews(){
        fragmenteditpetTxtName = view?.findViewById(R.id.fragmentEditPet_txtName)!!
        fragmenteditpetTxtWeight = view?.findViewById(R.id.fragmentEditPet_txtWeight)!!
        fragmenteditpetImgpet = view?.findViewById(R.id.fragmentEditPet_ImageView)!!
        fragmenteditpetEtAllergies = view?.findViewById(R.id.fragmentEditPet_TextInputAlergias)!!
        fragmenteditpetSbWeight = view?.findViewById(R.id.fragmentEditPet_sbWeight)!!
        fragmenteditpetRgSterilized = view?.findViewById(R.id.fragmentEditPet_rgEsterilizado)!!
        fragmenteditpetBtncancel = view?.findViewById(R.id.fragmentEditPet_btnCancelar)!!
        fragmenteditpetBtnapply = view?.findViewById(R.id.fragmentEditPet_btnConfirmar)!!
        fragmenteditpetTxtName.text = name
        if(animal.equals("dog")){
            fragmenteditpetImgpet.setImageDrawable(ResourcesCompat.getDrawable(resources,
                R.drawable.img_dog_illustration,null))
        }
        else {
            fragmenteditpetImgpet.setImageDrawable(ResourcesCompat.getDrawable(resources,
                R.drawable.gato,null))
        }

        fragmenteditpetEtAllergies.setText(allergies)
        if(sterilized.equals("No")){
            fragmenteditpetRgSterilized.check(R.id.fragmentEditPet_rbSterilized2)
        }
        else {
            fragmenteditpetRgSterilized.check(R.id.fragmentEditPet_rbSterilized1)
        }
        fragmenteditpetTxtWeight.text = String.format(null,"%.1f Kg", weight)
        fragmenteditpetSbWeight.progress=weight!!.times(10).roundToInt()

        var numberForTxtWeight : Double
        var weightAdjusted : Int
        fragmenteditpetSbWeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val weight = fragmenteditpetSbWeight.progress
                weightAdjusted = (weight / 5) * 5
                numberForTxtWeight= weightAdjusted / 10.0
                fragmenteditpetTxtWeight.text = String.format(null,"%.1f Kg", numberForTxtWeight)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
        fragmenteditpetBtncancel.setOnClickListener{
            bundle.putBoolean("finished", true)
            parentFragmentManager.setFragmentResult("result", bundle)
        }

        fragmenteditpetBtnapply.setOnClickListener{
                commitEditPet()
        }
    }


    private fun commitEditPet(){
        Log.i("step4","commitEditPet()")
            val pet = PetsManager(requireContext()).getPet(id)
            pet.alergia = fragmenteditpetEtAllergies.text.toString()
            pet.peso = fragmenteditpetSbWeight.progress.div(10).toDouble()
            when(fragmenteditpetRgSterilized.checkedRadioButtonId){
                R.id.fragmentEditPet_rbSterilized1 -> pet.esterilizado = "Si"
                R.id.fragmentEditPet_rbSterilized2 -> pet.esterilizado = "No"
        }
        PetsManager(requireContext()).editPet(pet,pos)
        bundle.putBoolean("finished", true)
        parentFragmentManager.setFragmentResult("result", bundle)
        Log.i("step3223","ENVIADO")
    }

    companion object {

        const val BUNDLE_ID = "bundle_id"
        const val BUNDLE_POS = "bundle_pos"
        const val BUNDLE_ANIMAL = "bundle_animal"
        const val BUNDLE_NAME = "bundle_name"
        const val BUNDLE_ALLERGIES = "bundle_allergies"
        const val BUNDLE_WEIGHT = "bundle_weight"
        const val BUNDLE_STERILIZED = "bundle_sterilized"



        @JvmStatic
        fun newInstance(animal: String, name: String, allergies: String, sterilized: String, weight: Int) =
            FragmentEditPet().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_ANIMAL,animal)
                    putString(BUNDLE_NAME, name)
                    putString(BUNDLE_ALLERGIES, allergies)
                    putString(BUNDLE_STERILIZED, sterilized)
                    putInt(BUNDLE_WEIGHT, weight)
                }
            }
    }
}
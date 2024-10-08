package com.example.feedm.data.model

class PetProvider {
    companion object{
        var pets: ArrayList<PetModel> = ArrayList()


        fun getPet(pos: Int): PetModel{
            return pets[pos]
        }

        fun getAllPets(): ArrayList<PetModel>{
            return pets
        }
    }
}
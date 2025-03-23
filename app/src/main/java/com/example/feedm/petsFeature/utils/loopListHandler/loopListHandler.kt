package com.example.feedm.petsFeature.utils.loopListHandler

class LoopListHandler<T> {
    fun loopListHandler(
        mutableList: MutableList<T>,
        action: Int,
        onModifiedList: (List<T>) -> Unit
    ) {
        val newList = mutableListOf<T>()
        val firstElement = mutableList.first()
        mutableList.remove(firstElement)
        for(i in mutableList){
            newList.add(i)
        }
        newList.add(firstElement)
        onModifiedList(newList)
    }
}
package com.maverickapps.nutripet.features.pets.utils.loopListHandler

class LoopListHandler<T> {

    companion object {
        const val RECOVER_DATA = 0
        const val GENERATE_DATA = 1
    }

    fun manageLoop(
        mutableList: MutableList<T>,
        action: Int,
        onModifiedList: (List<T>) -> Unit
    ) {

        val newList = mutableListOf<T>()

        when (action) {
            RECOVER_DATA -> {
                val firstElement = mutableList.first()
                mutableList.remove(firstElement)
                for (i in mutableList) {
                    newList.add(i)
                }
                newList.add(firstElement)
            }

            GENERATE_DATA -> {
                val lastElement = mutableList.last()
                mutableList.remove(lastElement)
                newList.add(lastElement)
                for (i in mutableList) {
                    newList.add(i)
                }
            }

        }
        onModifiedList(newList)
    }
}
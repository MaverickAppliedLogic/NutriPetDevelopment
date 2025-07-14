package com.maverickapps.nutripet.features.events.contract

interface EventScheduler {

    fun scheduleEvent(time: Long,
                      eventId: Int = 0,
                      extraData: String? = null,
                      needToBeCleared: Boolean = false)
}
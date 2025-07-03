package com.maverickapps.nutripet.features.events.contract

interface EventSetter{
    fun setEvent(time: Long, eventId: Int = 0, extraData: String? = null)
}
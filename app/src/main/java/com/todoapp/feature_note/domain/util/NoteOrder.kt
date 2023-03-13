package com.todoapp.feature_note.domain.util

sealed class NoteOrder(val orderType:OrderType){
    class Title(orderTye: OrderType):NoteOrder(orderTye)
    class Date(orderTye: OrderType):NoteOrder(orderTye)
    class Color(orderTye: OrderType):NoteOrder(orderTye)

    fun copy(orderTye: OrderType):NoteOrder{
        return when(this){
            is Title-> Title(orderTye)
            is Date-> Date(orderTye)
            is Color-> Color(orderTye)
        }

    }

}

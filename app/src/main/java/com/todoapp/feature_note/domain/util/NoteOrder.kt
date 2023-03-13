package com.todoapp.feature_note.domain.util

sealed class NoteOrder(val orderTye:OrderType){
    class Title(orderTye: OrderType):NoteOrder(orderTye)
    class Date(orderTye: OrderType):NoteOrder(orderTye)
    class Color(orderTye: OrderType):NoteOrder(orderTye)
}

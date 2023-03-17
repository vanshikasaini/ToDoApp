package com.todoapp.feature_note.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.todoapp.ui.theme.BabyBlue
import com.todoapp.ui.theme.Blue
import com.todoapp.ui.theme.CrownBlue
import com.todoapp.ui.theme.Green
import com.todoapp.ui.theme.LightBlue
import com.todoapp.ui.theme.LightGreen
import com.todoapp.ui.theme.Pink40
import com.todoapp.ui.theme.Pink80
import com.todoapp.ui.theme.Raspberry
import com.todoapp.ui.theme.RedOrange
import com.todoapp.ui.theme.RedPink
import com.todoapp.ui.theme.Yellow

@Entity
data class Note(
    val title:String,
    val content:String,
    val timestamp:Long,
    val color:Int,
    @PrimaryKey val id :Int?=null

){

    companion object{
         val notesColors= listOf(RedOrange, RedPink,CrownBlue,Pink40,Pink80, LightGreen,LightBlue,Raspberry,Yellow,Green)
    }
}
class InvalidNoteException(message:String):Exception(message)
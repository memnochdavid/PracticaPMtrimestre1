package com.david.practicapmtrimestre1

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

data class Carta(
    var numero: Int,
    var img: Int,
    val dorso: Int =R.drawable.dorso,
    var volteada: Boolean = false
)

val baraja= mutableListOf<Carta>(
    Carta(1,R.drawable.bra),
    Carta(2,R.drawable.cauliffla),
    Carta(3,R.drawable.chichibulma),
    Carta(4,R.drawable.launch),
    Carta(5,R.drawable.videl),
    Carta(6,R.drawable.zangya)
)

@Composable
fun MuestraCarta(carta: Carta){
    if(carta.volteada){
        Image(painter = painterResource(id = carta.img), contentDescription = "")
    }else{
        Image(painter = painterResource(id = carta.dorso), contentDescription = "")
    }
}

fun mazoBarajado(): MutableList<Carta>{
    baraja.shuffle()
    return baraja
}
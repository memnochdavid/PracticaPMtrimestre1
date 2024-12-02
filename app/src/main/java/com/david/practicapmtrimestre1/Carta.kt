package com.david.practicapmtrimestre1

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

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
    Carta(6,R.drawable.zangya),
    Carta(1,R.drawable.bra),
    Carta(2,R.drawable.cauliffla),
    Carta(3,R.drawable.chichibulma),
    Carta(4,R.drawable.launch),
    Carta(5,R.drawable.videl),
    Carta(6,R.drawable.zangya)
)

@Composable
fun MuestraCarta(carta: Carta) {
    var isFlipped by remember { mutableStateOf(carta.volteada) } // State to track flipped state

    Image(
        painter = painterResource(id = if (isFlipped) carta.img else carta.dorso),
        contentDescription = "",
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp)
            .clickable {
                isFlipped = !isFlipped // Toggle flipped state on click
                carta.volteada = !carta.volteada
            }
    )
}

@Composable
fun MuestraBaraja() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.wrapContentSize()
    ) {
        items(mazoBarajado()) { carta ->
            MuestraCarta(carta)
        }
    }
}


fun mazoBarajado(): MutableList<Carta>{
    baraja.shuffle()
    return baraja
}
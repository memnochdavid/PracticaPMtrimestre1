package com.david.practicapmtrimestre1

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

data class Carta(
    var numero: Int,
    var img: Int,
    val dorso: Int =R.drawable.dorso,
)
//global

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
var volteadas = MutableList(12) { false }

@Composable
fun MuestraCarta(carta: Carta, index: Int) {
    var volteada by remember { mutableStateOf(volteadas[index]) }

    Image(
        painter = painterResource(id = if (volteada) carta.img else carta.dorso),
        contentDescription = "",
        modifier = Modifier
            .size(130.dp)
            .padding(7.dp)
            .clickable {
                if (!volteadas[index]) {
                    volteadas[index] = true
                    volteada=true

                }

            }
    )
}
@Composable
fun MuestraBaraja(mazo: List<Carta>) {


    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.wrapContentSize()
    ) {
        itemsIndexed(mazo) { index, carta ->
            MuestraCarta(carta, index)
        }
    }
}


fun mazoBarajado(): List<Carta> {
    return baraja.shuffled()
}


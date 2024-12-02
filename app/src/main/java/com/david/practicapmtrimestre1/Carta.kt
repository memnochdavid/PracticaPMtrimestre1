package com.david.practicapmtrimestre1

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    var volteada by remember { mutableStateOf(carta.volteada) }
    var indice=0

    Image(
        painter = painterResource(id = if (volteada) carta.img else carta.dorso),
        contentDescription = "",
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp)
            .clickable {
                if (volteadas.size == 0) {
                    volteada = !volteada
                    volteadas.add(carta)
                    carta.volteada = !carta.volteada
                } else {
                    if (volteadas[indice].numero == carta.numero) {
                        volteada = !volteada
                        carta.volteada = !carta.volteada
                        volteadas.add(carta)
                        indice++
                    } else if (volteadas[indice].numero != carta.numero) {
                        volteada = !volteada
                        volteadas = mutableListOf<Carta>()
                        indice = 0
                        vidas--
                        carta.volteada = !carta.volteada
                    }
                }
            }
    )
}

@Composable
fun MuestraBaraja(mazo: MutableList<Carta>) {
    //var volteadas by remember { mutableStateOf(mutableListOf<Carta>()) }
    var currentVidas by remember { mutableStateOf(vidas) } // Track vidas state


    // Trigger desvolteaTodasLasCartas when vidas changes


    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.wrapContentSize()
    ) {
        items(mazo) { carta ->
            MuestraCarta(carta)
        }
    }
}


fun mazoBarajado(): MutableList<Carta>{
    baraja.shuffle()
    return baraja
}
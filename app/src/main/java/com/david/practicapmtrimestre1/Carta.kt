package com.david.practicapmtrimestre1

import android.util.Log
import androidx.activity.result.launch
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class Carta(
    var numero: Int,
    var img: Int,
    val dorso: Int =R.drawable.dorso,
){
    override fun toString(): String {
        return "Carta(numero=$numero, img=$img)"
    }
}
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


@Composable
fun MuestraCarta(carta: Carta, index: Int) {
    var seleccionada by remember { mutableStateOf<Carta?>(null) }
    val lifecycleScope = rememberCoroutineScope()
    var turno=0
    LaunchedEffect(key1 = vidas) {
        delay(500)
        if (volteadas.isNotEmpty()) {
            volteadas.forEachIndexed { i, _ ->
                volteadas[i] = false
            }
        }
    }

    Image(
        painter = painterResource(id = if (volteadas[index]) carta.img else carta.dorso),
        contentDescription = "",
        modifier = Modifier
            .size(130.dp)
            .padding(7.dp)
            .clickable {
                //lifecycleScope.launch {
                    //if (!volteadas[index]) {//si no esta volteada
                        if (seleccionada == null) {
                            seleccionada = carta
                            volteadas[index] = true
                        } else {
                            if (seleccionada!!.numero == carta.numero) {
                                volteadas[index] = true
                                seleccionada = null
                            }
                            else {
                                volteadas[index] = true
                                seleccionada = null
                                //delay(300)
                                vidas--
                            }
                        }
                    //}
                //}
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


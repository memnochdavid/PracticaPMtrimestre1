package com.david.practicapmtrimestre1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.david.practicapmtrimestre1.ui.theme.PracticaPMtrimestre1Theme

var vidas by mutableStateOf<Int>(5)
var volteadas by mutableStateOf(mutableListOf<Carta>())
var mazo by mutableStateOf(mutableListOf<Carta>())


class MemoTron : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaPMtrimestre1Theme {
                Mesa()
            }
        }
    }
}

@Composable
fun Mesa() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.tapete))
    ){
        val (fila1, fila2, fila3) = createRefs()
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(all = 20.dp)
                .constrainAs(fila1) {
                    top.linkTo(parent.top)
                    bottom.linkTo(fila2.top)
                }
        ){
            mazo= mazoBarajado()
            MuestraBaraja(mazo)
        }
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(all = 20.dp)
                .constrainAs(fila2) {
                    top.linkTo(fila1.bottom)
                    bottom.linkTo(parent.bottom)
                }
        ){
            Text("Vidas: $vidas")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mesa()
}
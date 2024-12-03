package com.david.practicapmtrimestre1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.david.practicapmtrimestre1.ui.theme.PracticaPMtrimestre1Theme

var vidas by mutableIntStateOf(5)
var mazo by mutableStateOf(mutableListOf<Carta>())
var volteadas = mutableStateListOf(false, false, false, false, false, false, false, false, false, false, false, false)


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
        if(vidas>0){
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(all = 20.dp)
                    .constrainAs(fila1) {
                        top.linkTo(parent.top)
                        bottom.linkTo(fila2.top)
                    },
                verticalAlignment = Alignment.CenterVertically,
            ){
                MuestraBaraja(mazo)
            }
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(all = 20.dp)
                    .constrainAs(fila2) {
                        top.linkTo(fila1.bottom)
                        bottom.linkTo(parent.bottom)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ){
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(0.5f)
                ) {
                    Text(text="Vidas: $vidas", fontSize = 20.sp, color = colorResource(R.color.white))
                }
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(0.5f)
                ) {
                    Button(onClick = {
                        vidas=5
                        volteadas.forEachIndexed { index, _ ->
                            if(volteadas.size>0){
                                volteadas[index] = false
                            }
                        }
                        mazo=mazoBarajado().toMutableList()
                    }) {
                        Text("Comenzar")
                    }
                }


            }
        }
        else{
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(fila3) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                ){
                    HasPerdido()
                }
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                ){
                    Button(onClick = {
                        vidas=5
                        volteadas.forEachIndexed { index, _ ->
                            if(volteadas.size>0){
                                volteadas[index] = false
                            }
                        }
                        mazo=mazoBarajado().toMutableList()
                    }) {
                        Text("Reiniciar")
                    }
                }
            }
        }
    }
}

@Composable
fun HasPerdido(){
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        contentDescription = "",
        painter = painterResource(id=R.drawable.lose)
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mesa()
}


package com.david.practicapmtrimestre1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.david.practicapmtrimestre1.ui.theme.PracticaPMtrimestre1Theme
import kotlinx.coroutines.launch

class Resultados : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaPMtrimestre1Theme {
                ResultadosUI()
            }
        }
    }
}

@Composable
fun ResultadosUI() {
    val coloresBoton: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = colorResource(R.color.purple_500),
        contentColor = colorResource(R.color.white)
    )
    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.purple_100))
    ){
        val (fila1, fila2, fila3, fila4, fila5, fila6, fila7) = createRefs()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 30.dp)
                .constrainAs(fila1) {
                    top.linkTo(fila1.bottom)
                    bottom.linkTo(fila2.top)
                },
            horizontalArrangement = Arrangement.Center
        ){
            //Partida anterior
            Text(text = "Partida Anterior", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)
                .constrainAs(fila2) {
                    top.linkTo(fila1.bottom)
                    bottom.linkTo(fila3.top)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ){
            //acertadas - falladas
            Text(text = "Acertadas: ${aciertos.intValue}", fontSize = 15.sp)
            Text(text = "Falladas: ${fallos.intValue}", fontSize = 15.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)
                .constrainAs(fila3) {
                    top.linkTo(fila2.bottom)
                    bottom.linkTo(fila4.top)
                },
            horizontalArrangement = Arrangement.Center
        ){
            //porcentaje aciertos
            Text(text = "Porcentaje Aciertos: ${(aciertos.intValue*100)/(operacionesResueltas.intValue).toFloat()}%", fontSize = 15.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 30.dp)
                .constrainAs(fila4) {
                    top.linkTo(fila3.bottom)
                    bottom.linkTo(fila5.top)
                },
            horizontalArrangement = Arrangement.Center
        ){
            Text(text = "Total", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)
                .constrainAs(fila5) {
                    top.linkTo(fila4.bottom)
                    bottom.linkTo(fila6.top)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ){
            //acertadas - falladas
            //acertadas - falladas
            Text(text = "Acertadas: ", fontSize = 15.sp)
            Text(text = "Falladas: ", fontSize = 15.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)
                .constrainAs(fila6) {
                    top.linkTo(fila5.bottom)
                    bottom.linkTo(fila7.top)
                },
            horizontalArrangement = Arrangement.Center
        ){
            //porcentaje aciertos
            Text(text = "Porcentaje Aciertos: ", fontSize = 15.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 30.dp)
                .constrainAs(fila7) {
                    //top.linkTo(fila6.bottom)
                    bottom.linkTo(parent.bottom)
                },
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = {
                    (context as Resultados).finish()
                },
                colors = coloresBoton,
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Volver")
            }
        }





    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    PracticaPMtrimestre1Theme {
        ResultadosUI()
    }
}
package com.david.practicapmtrimestre1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.david.practicapmtrimestre1.ui.theme.PracticaPMtrimestre1Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

var escribe by mutableStateOf("")
var confirma by mutableStateOf(false)
var historial = mutableStateListOf<Operacion>()
var operacionesPool = mutableStateListOf<Operacion>()
var aciertos= mutableIntStateOf(0)
var fallos= mutableIntStateOf(0)
var intentoResultado= mutableIntStateOf(0)



class CalcuTron : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        operacionesPool+=generaOperacion()//genera la primera operación
        //enableEdgeToEdge()
        setContent {
            PracticaPMtrimestre1Theme {
                CalcuTronUI()
            }
        }
    }
}

@Composable
fun CalcuTronUI() {
    val context = LocalContext.current
    var intent= Intent(context, CalcuTronOpc::class.java)

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.purple_100))
    ) {
        val (fila1, fila2, fila3, fila4, fila5, fila6, fila7, fila0) = createRefs()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .constrainAs(fila0) {
                    top.linkTo(parent.top)
                    bottom.linkTo(fila1.top)
                },
            horizontalArrangement = Arrangement.End
        ){
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        context.startActivity(intent)
                    },
                contentDescription = "",
                painter = painterResource(id=R.drawable.settings)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .constrainAs(fila1) {
                    top.linkTo(fila0.bottom)
                    bottom.linkTo(fila2.top)
                },
            horizontalArrangement = Arrangement.Center
        ){
            //cuenta atrás
            CuentaAtras()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .constrainAs(fila2) {
                    top.linkTo(fila1.bottom)
                    bottom.linkTo(fila3.top)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ){
            //aciertos y fallos
            AciertosFallos(1)//aciertos
            AciertosFallos(2)//fallos
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .constrainAs(fila3) {
                    top.linkTo(fila2.bottom)
                    bottom.linkTo(fila4.top)
                }
        ){
            //cuenta anterior

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .constrainAs(fila4) {
                    top.linkTo(fila3.bottom)
                    bottom.linkTo(fila5.top)
                }
        ){
            //cuenta actual
            OperacionActual()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .constrainAs(fila5) {
                    top.linkTo(fila4.bottom)
                    bottom.linkTo(fila6.top)
                }
        ){
            //cuenta siguiente
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .constrainAs(fila6) {
                    top.linkTo(fila5.bottom)
                    bottom.linkTo(parent.bottom)
                },
            horizontalArrangement = Arrangement.Center
        ){
            //teclado
            Teclado()
        }
    }

}


@Composable
fun CuentaAtras() {
    var remainingTime by remember { mutableIntStateOf(countdownDuration) }
    LaunchedEffect(key1 = remainingTime) {
        if (remainingTime > 0) {
            delay(1000)
            remainingTime--
        }
    }
    Text(
        text = "$remainingTime",
        fontSize = 50.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun AciertosFallos(opc:Int){
    when(opc){
        1->{
            Text(text = "Aciertos: ${aciertos.intValue}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        2->{
            Text(text = "Fallos: ${fallos.intValue}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun OperacionActual(){
    val coloresTextInput: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = colorResource(R.color.black),
        unfocusedBorderColor = colorResource(R.color.gris),
        cursorColor = colorResource(R.color.black),
        focusedContainerColor= colorResource(R.color.purple_050),
        unfocusedContainerColor=colorResource(R.color.purple_100),
        focusedTextColor= colorResource(R.color.black),
        unfocusedTextColor= colorResource(R.color.gris),
    )
    var cuenta=operacionesPool.last()

    LaunchedEffect(key1 = confirma) {
        if (confirma) {
            if (checkResultado(cuenta, escribe)) {
                aciertos.intValue++
                operacionesPool+=generaOperacion()
                cuenta=operacionesPool.last()
                escribe=""
                confirma=false
                historial+=cuenta
            }
            else{
                fallos.intValue++
                operacionesPool+=generaOperacion()
                cuenta=operacionesPool.last()
                escribe=""
                confirma=false
                historial+=cuenta
            }
        }
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ){
        Text(text = "${cuenta} = ", fontSize = 50.sp, fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = escribe,
            onValueChange = { newValue ->
                intentoResultado = (newValue.toIntOrNull() ?: intentoResultado) as MutableIntState
            },
            label = { Text(
                color= colorResource(R.color.black),
                text="R",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)

            },
            modifier = Modifier
                .padding(vertical = 20.dp)
                .size(width = 80.dp, height = 80.dp)
                //.focusable(false)
                .background(colorResource(id = R.color.transparente)),
            placeholder = { Text("",style = TextStyle(color = colorResource(id = R.color.white)))},
            shape = RoundedCornerShape(6.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = coloresTextInput,
            readOnly = true,
            textStyle = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
        )
    }

}





@Preview(showBackground = true)
@Composable
fun PreviewCalcuTron() {
    PracticaPMtrimestre1Theme {
        CalcuTronUI()
    }
}
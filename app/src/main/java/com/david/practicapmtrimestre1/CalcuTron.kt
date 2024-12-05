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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

var countdownDuration by mutableIntStateOf(20)
var animationEnabled by mutableStateOf(false)
var operators by mutableStateOf("")
var maxOperatorValue by mutableIntStateOf(10)
var minOperatorValue by mutableIntStateOf(1)

var listaOperaciones by mutableStateOf(listOf<String>())
var opcionesAnimaciones by mutableStateOf(listOf<String>())
var preferenciasCargadas by mutableStateOf(false)



var escribe by mutableStateOf("")
var confirma by mutableStateOf(false)
var historial = mutableStateListOf<Operacion>()
var operacionesPool = mutableStateListOf<Operacion>()
var aciertos= mutableIntStateOf(0)
var fallos= mutableIntStateOf(0)
var operacionesResueltas= mutableIntStateOf(0)
var intentoResultado= mutableIntStateOf(0)
var seAcertoUltimaCuenta= mutableStateOf(false)




class CalcuTron : ComponentActivity() {
    private lateinit var settingsDataStore: SettingsDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsDataStore = SettingsDataStore(this)
        val settingsDataStore = SettingsDataStore(this)

        //enableEdgeToEdge()
        setContent {
            PracticaPMtrimestre1Theme {
                CalcuTronUI(settingsDataStore)
            }
        }
    }
}

@Composable
fun CalcuTronUI(settingsDataStore: SettingsDataStore) {
    val context = LocalContext.current
    var intentOpciones= Intent(context, CalcuTronOpc::class.java)
    var intentResul= Intent(context, Resultados::class.java)
    //genera la primera y la segunda operación
    operacionesPool+=generaOperacion()
    operacionesPool+=generaOperacion()
    LaunchedEffect(Unit) {
        //countdownDuration= settingsDataStore.countdownDuration
        animationEnabled = settingsDataStore.animationEnabled
        operators = settingsDataStore.operators
        //borramos la primera posición de operators, por algún motivo pillaba un operador vacío ""
        operators = operators.substring(1)
        maxOperatorValue = settingsDataStore.maxOperatorValue
        minOperatorValue = settingsDataStore.minOperatorValue
        //listas
        listaOperaciones = operators.split(",")
        opcionesAnimaciones= listOf("Activado", "Desactivado")
        preferenciasCargadas = true
    }

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
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        context.startActivity(intentResul)
                    },
                contentDescription = "",
                painter = painterResource(id=R.drawable.stats)
            )
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        context.startActivity(intentOpciones)
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
            CuentaAtras(settingsDataStore)
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
                .wrapContentHeight()
                .padding(horizontal = 10.dp)
                .constrainAs(fila3) {
                    top.linkTo(fila2.bottom)
                    bottom.linkTo(fila4.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        ){
            //cuenta anterior
            if(operacionesPool.size>4){
                OperacionAnterior()
            }

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
            OperacionSiguiente()
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
fun CuentaAtras(settingsDataStore: SettingsDataStore) {
    val listaColores = listOf(colorResource(R.color.black),colorResource(R.color.magenta), colorResource(R.color.granate), colorResource(R.color.rojo), colorResource(R.color.purple_500))
    var colorContador by remember { mutableStateOf(listaColores[0]) }
    var remainingTime by remember { mutableIntStateOf(settingsDataStore.countdownDuration) }
    LaunchedEffect(key1 = remainingTime) {
        if (remainingTime > 0) {
            delay(1000)
            remainingTime--
            if (settingsDataStore.animationEnabled) {
                // Cambia el color solo si la animación está habilitada
                colorContador = listaColores[(listaColores.indexOf(colorContador) + 1) % listaColores.size]
            }
        }
    }
    Text(
        text = "$remainingTime",
        fontSize = 50.sp,
        color = colorContador,
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
    var cuenta=operacionesPool[operacionesPool.lastIndex -1]

    LaunchedEffect(key1 = confirma) {
        if (confirma) {
            if (checkResultado(cuenta, escribe)) {
                aciertos.intValue++
                operacionesPool+=generaOperacion()
                cuenta=operacionesPool.last()
                escribe=""
                confirma=false
                seAcertoUltimaCuenta.value = true
                historial+=cuenta
                operacionesResueltas.intValue++
            }
            else{
                fallos.intValue++
                operacionesPool+=generaOperacion()
                cuenta=operacionesPool.last()
                escribe=""
                confirma=false
                seAcertoUltimaCuenta.value = false
                historial+=cuenta
                operacionesResueltas.intValue++
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

@Composable
fun OperacionAnterior(){
    var cuenta:Operacion
    var painter: Painter
    if(seAcertoUltimaCuenta.value){
        painter=painterResource(id=R.drawable.check)
    }
    else{
        painter=painterResource(id=R.drawable.close)
    }
    cuenta = operacionesPool.getOrNull(operacionesPool.lastIndex - 2) ?: Operacion("+",0,0)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ){
        Text(text = "${cuenta} = ${cuenta.res}", fontSize = 30.sp)
        Image(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(40.dp),
            contentDescription = "",
            painter = painter
        )
    }
}

@Composable
fun OperacionSiguiente(){
    var cuenta:Operacion
    var painter: Painter
    if(seAcertoUltimaCuenta.value){
        painter=painterResource(id=R.drawable.check)
    }
    else{
        painter=painterResource(id=R.drawable.close)
    }
    cuenta = operacionesPool.getOrNull(operacionesPool.lastIndex) ?: Operacion("+",0,0)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ){
        Text(text = "${cuenta} = ", fontSize = 30.sp)
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewCalcuTron() {
    lateinit var settingsDataStore: SettingsDataStore
    val context = LocalContext.current
    settingsDataStore = SettingsDataStore(context)
    PracticaPMtrimestre1Theme {
        CalcuTronUI(settingsDataStore)
    }
}
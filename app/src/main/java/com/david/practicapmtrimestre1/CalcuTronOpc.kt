package com.david.practicapmtrimestre1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.launch
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.david.practicapmtrimestre1.ui.theme.PracticaPMtrimestre1Theme
import kotlinx.coroutines.launch
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope

class CalcuTronOpc : ComponentActivity() {
    private lateinit var settingsDataStore: SettingsDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsDataStore = SettingsDataStore(this)
        val settingsDataStore = SettingsDataStore(this)
        enableEdgeToEdge()
        setContent {
            PracticaPMtrimestre1Theme {
                Opciones(settingsDataStore)
            }
        }
    }
}

var countdownDuration by mutableIntStateOf(20)
var animationEnabled by mutableStateOf(false)
var operators by mutableStateOf("+,-,*")
var maxOperatorValue by mutableIntStateOf(10)
var minOperatorValue by mutableIntStateOf(1)

var listaOperaciones by mutableStateOf(listOf<String>())
var opcionesAnimaciones by mutableStateOf(listOf<String>())


@Composable
fun Opciones(settingsDataStore: SettingsDataStore) {
    val lifecycleScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        countdownDuration= settingsDataStore.countdownDuration
        animationEnabled = settingsDataStore.animationEnabled
        operators = settingsDataStore.operators
        maxOperatorValue = settingsDataStore.maxOperatorValue
        minOperatorValue = settingsDataStore.minOperatorValue
        //listas
        listaOperaciones = operators.split(",")
        opcionesAnimaciones= listOf("Activado", "Desactivado")
    }

    //AQUÍ LA IU
    OpcionesUI(lifecycleScope,settingsDataStore)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpcionesUI(lifecycleScope: CoroutineScope,settingsDataStore: SettingsDataStore){

    val coloresTextInput: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = colorResource(R.color.black),
        unfocusedBorderColor = colorResource(R.color.gris),
        cursorColor = colorResource(R.color.black),
        focusedContainerColor= colorResource(R.color.purple_050),
        unfocusedContainerColor=colorResource(R.color.purple_100),
        focusedTextColor= colorResource(R.color.black),
        unfocusedTextColor= colorResource(R.color.gris),
    )
    val coloresCheckbox: CheckboxColors = CheckboxDefaults.colors(
        checkedColor = colorResource(R.color.verde),
        uncheckedColor = colorResource(R.color.white),
        checkmarkColor = colorResource(R.color.white),
    )

    val coloresSpinner:TextFieldColors= ExposedDropdownMenuDefaults.textFieldColors(
        focusedTextColor = colorResource(R.color.black),
        unfocusedTextColor = colorResource(R.color.gris),
        focusedContainerColor= colorResource(R.color.purple_050),
        unfocusedContainerColor=colorResource(R.color.purple_100),
        focusedIndicatorColor = colorResource(R.color.black),
        unfocusedIndicatorColor = colorResource(R.color.gris),
    )

    val coloresBoton: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = colorResource(R.color.purple_500),
        contentColor = colorResource(R.color.white)
    )


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.purple_200)),
    ){
        val (fila1, fila2, fila3, fila4, fila5, fila6, fila7, fila8) = createRefs()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 20.dp, vertical = 25.dp)
                .constrainAs(fila1) {
                    top.linkTo(parent.top)
                    bottom.linkTo(fila2.top)
                },
            horizontalArrangement = Arrangement.Center
        ){
            Text(text = "CONFIGURACIÓN", fontSize = 30.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .constrainAs(fila2) {
                    top.linkTo(fila1.bottom)
                    bottom.linkTo(fila3.top)
                },
            horizontalArrangement = Arrangement.Center
            ){
                //cuenta atras
                CuentaAtras(coloresTextInput)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .constrainAs(fila3) {
                    top.linkTo(fila2.bottom)
                    bottom.linkTo(fila4.top)
                },
            horizontalArrangement = Arrangement.Center
        ){
            MaxMinoOperador(coloresTextInput)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .constrainAs(fila4) {
                    top.linkTo(fila3.bottom)
                    bottom.linkTo(fila5.top)
                },
            horizontalArrangement = Arrangement.Center
        ){
            Text(text = "Operaciones seleccionadas", fontSize = 20.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .constrainAs(fila5) {
                    top.linkTo(fila4.bottom)
                    bottom.linkTo(fila6.top)
                },
            horizontalArrangement = Arrangement.Center
        ){
            //checkboxes
            Operaciones(coloresCheckbox)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .constrainAs(fila6) {
                    top.linkTo(fila5.bottom)
                    bottom.linkTo(fila7.top)
                },
            horizontalArrangement = Arrangement.Center
        ){
            Text(text = "Animación", fontSize = 20.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 10.dp)
                .constrainAs(fila7) {
                    top.linkTo(fila6.bottom)
                    //bottom.linkTo(fila8.top)
                },
            horizontalArrangement = Arrangement.Center
        ){
            //spinner
            Animaciones(coloresSpinner)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 50.dp)
                .constrainAs(fila8) {
                    //top.linkTo(fila7.bottom)
                    bottom.linkTo(parent.bottom)
                },
            horizontalArrangement = Arrangement.Center
        ){
            //Guardar
            Button(
                onClick = {
                    lifecycleScope.launch {
                        settingsDataStore.updateCountdownDuration(countdownDuration)
                        settingsDataStore.updateAnimationEnabled(animationEnabled)
                        settingsDataStore.updateOperators(operators)
                        settingsDataStore.updateMaxOperatorValue(maxOperatorValue)
                        settingsDataStore.updateMinOperatorValue(minOperatorValue)
                    }
                },
                colors = coloresBoton,
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Guardar")
            }

        }



    }
}


@Composable
fun CuentaAtras(coloresTextInput: TextFieldColors){
    OutlinedTextField(
        value = countdownDuration.toString(),
        onValueChange = { newValue ->
            countdownDuration = newValue.toIntOrNull() ?: countdownDuration
        },
        label = { Text(
            color= colorResource(R.color.black),
            text="Cuenta Atrás")},
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 10.dp)
            .fillMaxWidth()
            .background(colorResource(id = R.color.transparente)),
            //.clip(RoundedCornerShape(0.dp)),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.AccessAlarm,
                tint = colorResource(id = R.color.black),
                contentDescription = "Buscar"
            )
        },
        placeholder = { Text("nombre del Pokémon",style = TextStyle(color = colorResource(id = R.color.white)))},
        shape = RoundedCornerShape(6.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = coloresTextInput
    )
}
@Composable
fun MaxMinoOperador(coloresTextInput: TextFieldColors){
    Row(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .wrapContentHeight()
            .background(colorResource(id = R.color.transparente)),
    ){
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
                .wrapContentHeight()
        ){
            OutlinedTextField(
                value = minOperatorValue.toString(),
                onValueChange = { newValue ->
                    minOperatorValue = newValue.toIntOrNull() ?: minOperatorValue
                },
                label = { Text(
                    color= colorResource(R.color.black),
                    text="Min. valor")},
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.transparente)),
                //.clip(RoundedCornerShape(0.dp)),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDownward,
                        tint = colorResource(id = R.color.black),
                        contentDescription = ""
                    )
                },
                placeholder = { Text("",style = TextStyle(color = colorResource(id = R.color.white)))},
                shape = RoundedCornerShape(6.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = coloresTextInput
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
                .wrapContentHeight()
        ){
            OutlinedTextField(
                value = maxOperatorValue.toString(),
                onValueChange = { newValue ->
                    maxOperatorValue = newValue.toIntOrNull() ?: maxOperatorValue
                },
                label = { Text(
                    color= colorResource(R.color.black),
                    text="Max. valor")},
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.transparente)),
                //.clip(RoundedCornerShape(0.dp)),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowUpward,
                        tint = colorResource(id = R.color.black),
                        contentDescription = ""
                    )
                },
                placeholder = { Text("",style = TextStyle(color = colorResource(id = R.color.white)))},
                shape = RoundedCornerShape(6.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = coloresTextInput
            )
        }
    }
}
@Composable
fun Operaciones(coloresCheckbox: CheckboxColors){
    //borrar
    listaOperaciones = operators.split(",")
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        var operacionTexto=""
        for (operacion in listaOperaciones) {
            when (operacion) {
                "+" -> operacionTexto = "Suma"
                "-" -> operacionTexto = "Resta"
                "*" -> operacionTexto = "Multi"
            }
            Checkbox(
                checked = operators.contains(operacion),
                onCheckedChange = { isChecked ->
                    if (isChecked) {
                        listaOperaciones += ",$operacion"
                    } else {
                        listaOperaciones = listaOperaciones.filter { it != operacion }
                    }
                },
                colors = coloresCheckbox
            )
            Text(operacionTexto, fontSize = 13.sp,modifier = Modifier.padding(end = 10.dp))
        }



    }
}

@ExperimentalMaterial3Api
@Composable
fun Animaciones(coloresSpinner: TextFieldColors) {
    val animationOptions = listOf("Activado", "Desactivado")
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(animationOptions[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor()
                .fillMaxWidth(),
            colors = coloresSpinner
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            animationOptions.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                    }
                )
            }
        }
    }
}























@Preview(showBackground = true)
@Composable
fun PreviewOpciones() {
    val lifecycleScope = rememberCoroutineScope()
    val settingsDataStore = SettingsDataStore(LocalContext.current)
    PracticaPMtrimestre1Theme {
        OpcionesUI(lifecycleScope,settingsDataStore)
    }
}
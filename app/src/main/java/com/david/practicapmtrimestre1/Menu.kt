package com.david.practicapmtrimestre1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.david.practicapmtrimestre1.ui.theme.PracticaPMtrimestre1Theme
import androidx.constraintlayout.compose.ConstraintLayout

class Menu : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Actividad()
        }
    }
}


@Composable
fun Actividad() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (fila1, fila2, fila3) = createRefs()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .background(colorResource(R.color.granate))
                .constrainAs(fila1) {
                    top.linkTo(parent.top)
                    bottom.linkTo(fila2.top)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = stringResource(id = R.string.motivo),
                color = colorResource(id = R.color.white),
                fontSize = 30.sp,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .background(colorResource(R.color.rojo))
                .constrainAs(fila2) {
                    top.linkTo(fila1.bottom)
                    bottom.linkTo(fila3.top)
                }
                .padding(all = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(colorResource(R.color.naranja))
                    .weight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    modifier = Modifier.padding(all = 20.dp),
                    text = stringResource(id = R.string.memotron),
                    color = colorResource(id = R.color.white),
                    fontSize = 25.sp,
                )
                Boton(1)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(colorResource(R.color.naranja))
                    .weight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    modifier = Modifier.padding(all = 20.dp),
                    text = stringResource(id = R.string.calcutron),
                    color = colorResource(id = R.color.white),
                    fontSize = 25.sp,
                )
                Boton(2)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .background(colorResource(R.color.granate))
                .constrainAs(fila3) {
                    top.linkTo(fila2.bottom)
                    bottom.linkTo(parent.bottom)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = stringResource(id = R.string.autor),
                color = colorResource(id = R.color.white),
                fontSize = 30.sp,
            )
        }

    }
}


@Composable
fun Boton(opc:Int){
    var painter = painterResource(id = R.drawable.memotron)
    val context = LocalContext.current
    var intent : Intent= Intent()
    when(opc){
        1->{
            painter= painterResource(id = R.drawable.memotron)
            intent = Intent(context, MemoTron::class.java)
        }
        2->{
            painter= painterResource(id = R.drawable.calcutron)
            intent = Intent(context, CalcuTron::class.java)
        }
    }
    Image(
        painter = painter,
        contentDescription = "",
        modifier = Modifier
            .padding(all = 20.dp)
            .clickable {
                context.startActivity(intent)
            }
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    PracticaPMtrimestre1Theme {
        Actividad()
    }
}
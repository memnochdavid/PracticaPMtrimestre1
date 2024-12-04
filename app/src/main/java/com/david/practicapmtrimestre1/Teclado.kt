package com.david.practicapmtrimestre1

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension


@Composable
fun Teclado(){
    ConstraintLayout(
        modifier = Modifier
            .padding(all = 2.dp)
            .wrapContentSize()
    ) {
        val (siete, ocho, nueve, ce, cuatro, cinco, seis,igual,uno,dos,tres,cero,c) = createRefs()
        val colores:ButtonColors=ButtonDefaults.buttonColors(
            containerColor= colorResource(R.color.purple_500),
            contentColor= colorResource(R.color.magenta))
        val colorTexto=colorResource(R.color.white)

        Button(
            onClick = {
                escribe+="7"
            },modifier = Modifier
                .wrapContentSize()
                .padding(all = 1.dp)
                .constrainAs(siete) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(ocho.start)
                    bottom.linkTo(cuatro.top)
                },
            shape = RoundedCornerShape(4.dp),
            colors = colores
        ){Text(text = "7", color = colorTexto)}
        Button(
            onClick = {
                escribe+="8"
            },modifier = Modifier
                .wrapContentSize()
                .padding(all = 1.dp)
                .constrainAs(ocho) {
                    top.linkTo(parent.top)
                    start.linkTo(siete.end)
                    end.linkTo(ocho.start)
                    bottom.linkTo(cinco.top)
                },
            shape = RoundedCornerShape(4.dp),
            colors = colores
        ){Text(text = "8", color = colorTexto)}
        Button(
            onClick = {
                escribe+="9"
            },modifier = Modifier
                .wrapContentSize()
                .padding(all = 1.dp)
                .constrainAs(nueve) {
                    top.linkTo(parent.top)
                    start.linkTo(ocho.end)
                    end.linkTo(ce.start)
                    bottom.linkTo(seis.top)
                },
            shape = RoundedCornerShape(4.dp),
            colors = colores
        ){Text(text = "9", color = colorTexto)}
        Button(
            onClick = {
                escribe+=""
            },modifier = Modifier
                .wrapContentSize()
                .padding(all = 1.dp)
                .constrainAs(ce) {
                    top.linkTo(parent.top)
                    start.linkTo(igual.start)
                    end.linkTo(igual.end)
                    bottom.linkTo(igual.top)
                },
            shape = RoundedCornerShape(4.dp),
            colors = colores
        ){Text(text = "CE", fontSize = 9.sp, color = colorTexto)}
        Button(
            onClick = {
                escribe+="4"
            },modifier = Modifier
                .wrapContentSize()
                .padding(all = 1.dp)
                .constrainAs(cuatro) {
                    start.linkTo(parent.start)
                    end.linkTo(cinco.start)
                    top.linkTo(siete.bottom)
                    bottom.linkTo(uno.top)
                },
            shape = RoundedCornerShape(4.dp),
            colors = colores
        ){Text(text = "4", color = colorTexto)}
        Button(
            onClick = {
                escribe+="5"
            },modifier = Modifier
                .wrapContentSize()
                .padding(all = 1.dp)
                .constrainAs(cinco) {
                    start.linkTo(cuatro.end)
                    end.linkTo(seis.start)
                    top.linkTo(ocho.bottom)
                    bottom.linkTo(dos.top)
                },
            shape = RoundedCornerShape(4.dp),
            colors = colores
        ){Text(text = "5", color = colorTexto)}
        Button(
            onClick = {
                escribe+="6"
            },modifier = Modifier
                .wrapContentSize()
                .padding(all = 1.dp)
                .constrainAs(seis) {
                    start.linkTo(cinco.end)
                    end.linkTo(igual.start)
                    top.linkTo(igual.top)
                    bottom.linkTo(tres.top)
                },
            shape = RoundedCornerShape(4.dp),
            colors = colores
        ){Text(text = "6", color = colorTexto)}
        Button(
            onClick = {
                escribe+="="
            },modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 1.dp, vertical = 5.dp)
                .constrainAs(igual) {
                    start.linkTo(seis.end)
                    end.linkTo(parent.end)
                    top.linkTo(ce.bottom)
                    bottom.linkTo(c.bottom)
                    height = Dimension.fillToConstraints
                },
            shape = RoundedCornerShape(4.dp),
            colors = colores
        ){Text(text = "=", color = colorTexto)}
        Button(
            onClick = {
                escribe+="1"
            },modifier = Modifier
                .wrapContentSize()
                .padding(all = 1.dp)
                .constrainAs(uno) {
                    start.linkTo(parent.start)
                    end.linkTo(dos.start)
                    top.linkTo(cuatro.bottom)
                    bottom.linkTo(cero.top)
                },
            shape = RoundedCornerShape(4.dp),
            colors = colores
        ){Text(text = "1", color = colorTexto)}
        Button(
            onClick = {
                escribe+="2"
            },modifier = Modifier
                .wrapContentSize()
                .padding(all = 1.dp)
                .constrainAs(dos) {
                    start.linkTo(uno.end)
                    end.linkTo(tres.start)
                    top.linkTo(cinco.bottom)
                    bottom.linkTo(c.top)
                },
            shape = RoundedCornerShape(4.dp),
            colors = colores
        ){Text(text = "2", color = colorTexto)}
        Button(
            onClick = {
                escribe+="3"
            },modifier = Modifier
                .wrapContentSize()
                .padding(all = 1.dp)
                .constrainAs(tres) {
                    start.linkTo(dos.end)
                    end.linkTo(igual.start)
                    top.linkTo(seis.bottom)
                    bottom.linkTo(c.top)
                },
            shape = RoundedCornerShape(4.dp),
            colors = colores
        ){Text(text = "3", color = colorTexto)}
        Button(
            onClick = {
                escribe+="0"
            },modifier = Modifier
                .fillMaxWidth()
                .padding(all = 1.dp)
                .constrainAs(cero) {
                    start.linkTo(uno.start)
                    end.linkTo(dos.end)
                    top.linkTo(uno.bottom)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                },
            shape = RoundedCornerShape(4.dp),
            colors = colores
        ){Text(text = "0", color = colorTexto)}
        Button(
            onClick = {
                escribe=escribe.dropLast(1)
            },modifier = Modifier
                .wrapContentSize()
                .padding(all = 1.dp)
                .constrainAs(c) {
                    start.linkTo(cero.end)
                    end.linkTo(igual.start)
                    top.linkTo(dos.bottom)
                    bottom.linkTo(igual.bottom)
                },
            shape = RoundedCornerShape(4.dp),
            colors = colores
        ){Text(text = "C", color = colorTexto)}

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTeclado() {
    Teclado()
}
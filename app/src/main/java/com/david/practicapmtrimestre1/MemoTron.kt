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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.david.practicapmtrimestre1.ui.theme.PracticaPMtrimestre1Theme

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
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.tapete)),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(all = 20.dp),
        ){
            MuestraBaraja()
        }
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(all = 20.dp),
        ){
            Text("")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mesa()
}
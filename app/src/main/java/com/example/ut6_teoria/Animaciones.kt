package com.example.ut6_teoria

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorAnimationSimple() {
    var colorPer by rememberSaveable {
        mutableStateOf(false)
    }
    var showText by rememberSaveable {
        mutableStateOf(false)
    }

    val realColor2 by animateColorAsState(
        targetValue = if (colorPer) Color.Red else Color.Blue,
        animationSpec = tween(durationMillis = 2000),
        label = "Animación color",
        finishedListener = {showText=true}
    )


    Column {

        Box(modifier = Modifier
            .size(100.dp)
            .background(realColor2)
            .clickable { colorPer = !colorPer })
        if(showText){
           Text(text = "Finaliza animación")
        }
    }
}
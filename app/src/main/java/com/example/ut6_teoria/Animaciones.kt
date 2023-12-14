package com.example.ut6_teoria

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
        finishedListener = { showText = true }
    )


    Column {

        Box(modifier = Modifier
            .size(100.dp)
            .background(realColor2)
            .clickable { colorPer = !colorPer })
        if (showText) {
            Text(text = "Finaliza animación")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun sizeAnimation() {
    var smallSize by rememberSaveable {
        mutableStateOf(true)
    }
    var texto = ""
    val size by animateDpAsState(
        targetValue = if (smallSize) 50.dp else 100.dp, animationSpec = tween(500),
        label = "Animación tamaño"
    )
    Column {
        Box(modifier = Modifier
            .size(size)
            .background(Color.Cyan)
            .clickable { smallSize = !smallSize })
    }

}

@Composable
fun visibilityAnimation() {
    var isVisible by remember {
        mutableStateOf(true)
    }
    Column(Modifier.fillMaxSize()) {
        Button(onClick = { isVisible = !isVisible }) {
            Text(text = "Mostrar /Ocultar")
        }
        Spacer(modifier = Modifier.size(10.dp))

        AnimatedVisibility(isVisible, enter = slideInHorizontally(), exit = slideOutHorizontally()) {
            Box(
                Modifier
                    .size(150.dp)
                    .background(Color.Cyan)
            )
        }
    }
}

@Composable
fun crossFadeExample(){

    var myComponent: ComponentTye by remember {
        mutableStateOf(ComponentTye.Text)
    }

    Column(Modifier.fillMaxSize()){
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Cambiar componente")
        }
        when(myComponent){
            ComponentTye.Image ->Icon(Icons.Default.Edit, contentDescription = null)
            ComponentTye.Text -> Text(text = "Texto")
            ComponentTye.Box -> Box(modifier = Modifier.size(10.dp))
            ComponentTye.Error -> TODO()
        }
    }

}

enum class ComponentTye(){
    Image, Text, Box, Error
}
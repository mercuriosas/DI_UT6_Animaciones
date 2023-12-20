package com.example.ut6_teoria

import android.util.Half.toFloat
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.random.Random.Default.nextInt

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

        AnimatedVisibility(
            isVisible,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ) {
            Box(
                Modifier
                    .size(150.dp)
                    .background(Color.Cyan)
            )
        }
    }
}

@Composable
fun crossFadeExample() {

    var myComponent: ComponentTye by remember {
        mutableStateOf(ComponentTye.Text)
    }

    Column(Modifier.fillMaxSize()) {
        Button(onClick = { myComponent= getComponentTypeRandon()}) {
            Text(text = "Cambiar componente")
        }
        Crossfade(targetState = myComponent, label = "Crossfade") { myComponent->
            when (myComponent) {
                ComponentTye.Image -> Icon(Icons.Default.Edit, contentDescription = null)
                ComponentTye.Text -> Text(text = "Texto")
                ComponentTye.Box -> Box(modifier = Modifier
                    .size(150.dp)
                    .background(Color.Red))
                ComponentTye.Error -> Text(text = "Error")
            }
        }

    }

}

enum class ComponentTye() {
    Image, Text, Box, Error
}

fun getComponentTypeRandon(): ComponentTye {
   return when (nextInt(from = 0, until = 3)) {
        0 -> ComponentTye.Image
        1 -> ComponentTye.Text
        2 -> ComponentTye.Box
        else -> ComponentTye.Error
    }
}

@Composable
fun cardAnimation() {
    val elevacion = 0f
    val value by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 1f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "Floating Card"
    )
    Column() {
        Row() {
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = value.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { }
            ) {
                Column(Modifier.padding(5.dp)) {
                    Text(text = "$value")
                    Text(text = "Texto 3")
                }
            }
        }
        Row(Modifier.padding(5.dp)) {
            val value2 by rememberInfiniteTransition().animateFloat(
                initialValue = 25f,
                targetValue = -25f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 600,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "",
                modifier = Modifier
                    .graphicsLayer(
                        transformOrigin = TransformOrigin(
                            pivotFractionX = 0.5f,
                            pivotFractionY = 0.0f,
                        ),
                        rotationZ = value2
                    )
            )
        }
    }
}

@Composable
fun ElevatedCardExample() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    // Creates a child animation of float type as a part of the [InfiniteTransition].
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = scale.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Elevated",
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }
}


@Composable
fun InfinitelyPulsingHeart() {
    // Creates an [InfiniteTransition] instance for managing child animations.
    val infiniteTransition = rememberInfiniteTransition(label = "")

    // Creates a child animation of float type as a part of the [InfiniteTransition].
    val scale by infiniteTransition.animateFloat(
        initialValue = 3f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(
            // Infinitely repeating a 1000ms tween animation using default easing curve.
            animation = tween(1000),
            // After each iteration of the animation (i.e. every 1000ms), the animation will
            // start again from the [initialValue] defined above.
            // This is the default [RepeatMode]. See [RepeatMode.Reverse] below for an
            // alternative.
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    // Creates a Color animation as a part of the [InfiniteTransition].
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color(0xff800000), // Dark Red
        animationSpec = infiniteRepeatable(
            // Linearly interpolate between initialValue and targetValue every 1000ms.
            animation = tween(1000, easing = LinearEasing),
            // Once [TargetValue] is reached, starts the next iteration in reverse (i.e. from
            // TargetValue to InitialValue). Then again from InitialValue to TargetValue. This
            // [RepeatMode] ensures that the animation value is *always continuous*.
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(Modifier.fillMaxSize()) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale
                ),
            tint = color
        )
    }
}

@Composable
fun circleAnimation() {
    val value by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        ), label = ""
    )
    val colors = listOf(
        Color(0xFF405DE6),
        Color(0xFFC13584),
        Color(0xFFFD1D1D),
        Color(0xFFFFDC80)
    )
    var gradientBrush by remember {
        mutableStateOf(
            Brush.horizontalGradient(
                colors = colors,
                startX = -10.0f,
                endX = 400.0f,
                tileMode = TileMode.Repeated
            )
        )
    }

    Box(modifier = Modifier
        .drawBehind {
            rotate(value) {
                drawCircle(
                    gradientBrush, style = Stroke(width = 12.dp.value)
                )
            }
        }
        .size(125.dp)
    )
}

@Composable
fun DraggableTextLowLevel() {
    /*Box(modifier = Modifier.fillMaxSize()) {*/
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }


    Box(
        Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .background(Color.Blue)
            .size(50.dp)
            .pointerInput(Unit) {
                detectDragGestures(onDragEnd = {}) { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
    ) {

    }
    //}
}

@Composable
fun DraggableCard() {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .fillMaxWidth()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectDragGestures(onDragEnd = {}) { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
    ) {
        Text(
            text = "Diseño de interfaces 2023",
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun contentAnimation() {
    Row {
        var count by remember { mutableStateOf(0) }
        Button(onClick = { count++ }) {
            Text("Add")
        }
        AnimatedContent(targetState = count, label = "") { targetCount ->
            // Make sure to use `targetCount`, not `count`.
            Text(text = "Count: $targetCount")
        }
        Text(text = "Count: ")
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (targetState > initialState) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }, label = ""
        ) { targetCount ->
            Text(text = "$targetCount")
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun advanceContentAnimation() {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier.padding(2.dp),
        color = MaterialTheme.colorScheme.primary,
        onClick = { expanded = !expanded }
    ) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) with
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    // Expand horizontally first.
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    // Shrink vertically first.
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
            }, label = ""
        ) { targetExpanded ->
            if (targetExpanded) {
                Text(text = "Lorem ipsum dolor sit amet consectetur adipiscing elit morbi, ut donec lacus montes vulputate conubia ridiculus. Natoque purus taciti laoreet erat ad sodales nisi nisl curabitur, class id nascetur ligula ultrices dis penatibus aenean, venenatis vehicula massa lacinia primis conubia eu volutpat. Et convallis velit suscipit aliquam feugiat nisl magnis facilisi cubilia quam conubia, ligula praesent tristique est dapibus nisi gravida eleifend duis.")

            } else {
                Icon(imageVector = Icons.Default.Call, contentDescription = "Llamada")
            }
        }
    }
}

@Composable
fun gradient() {
    val brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue))
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawCircle(brush)
        }
    )
}
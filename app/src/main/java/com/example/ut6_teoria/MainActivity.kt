package com.example.ut6_teoria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ut6_teoria.ui.theme.UT6_teoriaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UT6_teoriaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column ( Modifier.padding(5.dp)) {
                        //ColorAnimationSimple()
                        //Spacer(modifier = Modifier.size(10.dp))
                        //sizeAnimation()
                        //visibilityAnimation()
                       /* cardAnimation()
                        Spacer(modifier = Modifier.size(5.dp))
                        ElevatedCardExample()
                        Spacer(modifier = Modifier.size(5.dp))
                        circleAnimation()
                        Spacer(modifier = Modifier.size(5.dp))
                        DraggableTextLowLevel()
                        Spacer(modifier = Modifier.size(5.dp))
                        DraggableCard()
                        Spacer(Modifier.size(5.dp))
                        contentAnimation()
                        Spacer(modifier = Modifier.size(5.dp))
                        advanceContentAnimation()
                        //InfinitelyPulsingHeart()

                        dibujoRect()
                        Spacer(modifier = Modifier.size(5.dp))
                        dibujoRectRound()
                        Spacer(modifier = Modifier.size(5.dp))
                        dibujoCirc()
                        Spacer(modifier = Modifier.size(5.dp))
                        dibujoCircStr()
                        Spacer(modifier = Modifier.size(5.dp))
                        positionDraw()*/
                        crossFadeExample()
                    }
                    //Greeting("Android")

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UT6_teoriaTheme {
        Greeting("Android")
    }
}
package com.example.colordots

import androidx.annotation.IntRange
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlin.random.Random

@Composable
fun Dot (
    color: Color,
    modifier: Modifier = Modifier,
    onClick: (List<Color>) -> Unit
) {
    Spacer(modifier = modifier
        .height(80.dp)
        .width(80.dp)
        .clip(shape = RoundedCornerShape(40.dp))
        .background(color = color)
        .clickable {
            onClick(
                listOf(
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                        1f
                    ),
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                        1f
                    ),
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                        1f
                    )
                )
            )
        }
    )
}

@Composable
fun DotsField () {
    val colorDot1 = remember { mutableStateOf(Color.Red) }
    val colorDot2 = remember { mutableStateOf(Color.Yellow) }
    val colorDot3 = remember { mutableStateOf(Color.Blue) }
    val colorDot4 = remember { mutableStateOf(Color.Green) }

    val color1 = animateColorAsState(targetValue = colorDot1.value, tween(durationMillis = 500))
    val color2 = animateColorAsState(targetValue = colorDot2.value, tween(durationMillis = 500))
    val color3 = animateColorAsState(targetValue = colorDot3.value, tween(durationMillis = 500))
    val color4 = animateColorAsState(targetValue = colorDot4.value, tween(durationMillis = 500))

    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.8f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier.height(200.dp),
            verticalArrangement = Arrangement.SpaceBetween) {
            Dot(color = color1.value) {
                colorDot2.value = it[0]
                colorDot3.value = it[1]
                colorDot4.value = it[2]
            }
            Dot(color = color2.value) {
                colorDot1.value = it[0]
                colorDot3.value = it[1]
                colorDot4.value = it[2]
            }
        }
        Column(
            modifier = Modifier.height(200.dp),
            verticalArrangement = Arrangement.SpaceBetween) {
            Dot(color = color3.value) {
                colorDot2.value = it[0]
                colorDot1.value = it[1]
                colorDot4.value = it[2]
            }
            Dot(color = color4.value) {
                colorDot2.value = it[0]
                colorDot3.value = it[1]
                colorDot1.value = it[2]
            }
        }
    }
}

@Composable
fun BtnToScreen(text: String, onClick: ()->Unit) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}

@Composable
fun Screen1(navController: NavHostController) {
    Column {
        DotsField()
        BtnToScreen(text = "Next") {
            navController.navigate(route = Screen.Screen2.route)
        }
    }

}


@Composable
fun Screen2(navController: NavHostController) {
    ColorAdjusterLayout()
}

@Composable
fun ColorAdjusterLayout() {
    var red by remember {
        mutableStateOf(1f)
    }

    var green by remember {
        mutableStateOf(1f)
    }

    var blue by remember {
        mutableStateOf(1f)
    }

    var targetColor = Color(red, green, blue, 1f)
    val color by animateColorAsState(targetValue = targetColor, tween(500))

    fun addRed(){
        //if(red < 1f) red += 0.1f

        if(green > 0.1f) green -= 0.1f
        if(blue > 0.1f) blue -= 0.1f
    }
    fun minusRed(){
        //f(red > 0.1f) red -= 0.1f

        if(green < 1f) green += 0.1f
        if(blue < 1f) blue += 0.1f
    }

    fun addGreen(){
        //if(green < 1f) green += 0.1f

        if(red > 0.1f) red -= 0.1f
        if(blue > 0.1f) blue -= 0.1f

    }
    fun minusGreen(){
        //if(green > 0.1f) green -= 0.1f

        if(red < 1f) red += 0.1f
        if(blue < 1f) blue += 0.1f
    }

    fun addBlue(){
        if(green > 0.1f) green -= 0.1f
        if(red > 0.1f) red -= 0.1f

        //if(blue < 1f) blue += 0.1f
    }
    fun minusBlue(){
        if(green < 1f) green += 0.1f
        if(red < 1f) red += 0.1f


        //if(blue > 0.1f) blue -= 0.1f
    }

    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(2f)
            .background(color)
        )
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            colorAdjustColor(
                colorDegree = (red * 10).toInt(),
                color = Color.Red,
                addfunc = ::addRed,
                minusfunc = ::minusRed,
                modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
            )

            colorAdjustColor(
                colorDegree = (green * 10).toInt(),

                color = Color.Green,
                addfunc = ::addGreen,
                minusfunc = ::minusGreen,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )

            colorAdjustColor(
                colorDegree = (blue * 10).toInt(),
                color = Color.Blue,
                addfunc = ::addBlue,
                minusfunc = ::minusBlue,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )



        }
    }
}

@Composable
fun colorAdjustColor(colorDegree: Int, color: Color, addfunc: () -> Unit, minusfunc: ()->Unit,  modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Button(
            modifier = Modifier
                .height(80.dp)
                .width(80.dp),
            onClick = {addfunc() }
        ) {
            Text(text = "+")
        }
        Box(modifier = Modifier
            .height(80.dp)
            .width(80.dp)
            .background(color)
        ) {
            Text("$colorDegree")
        }
        Button(
            modifier = Modifier
                .height(80.dp)
                .width(80.dp),
            onClick = { minusfunc() }
        ) {
            Text(text = "-")
        }
    }
}
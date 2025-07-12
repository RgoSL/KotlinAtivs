package com.example.cineko.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.cineko.ui.theme.Black100
import com.example.cineko.ui.theme.Blue200
import com.example.cineko.ui.theme.Blue500
import com.example.cineko.ui.theme.Blue700
import com.example.cineko.ui.theme.Red200
import com.example.cineko.ui.theme.Red500
import com.example.cineko.ui.theme.Red700

@Composable
fun AnimatedBorderCard(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(size = 0.dp),
    borderWidth: Dp = 1.dp,
    gradient: Brush = Brush.sweepGradient(listOf(Red200, Red500)),
    animationDuration: Int = 10000,
    content: @Composable () -> Unit
){
    val infiniteTransition = rememberInfiniteTransition(label = "Animação de cores Infinitas")
    val degrees by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f ,
        animationSpec =  infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Cores infinitas"
    )

    Surface(
        modifier = modifier,
        shape = shape
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .padding(borderWidth)
                .drawWithContent {
                    rotate(degrees = degrees) {
                        drawCircle(
                            brush = gradient,
                            radius = size.width,
                            blendMode = BlendMode.SrcIn
                        )
                    }
                    drawContent()
                },
            color = Red500,
            shape = shape
        ) {
            content()
        }
    }
}

@Composable
@Preview
private fun AnimatedBorderCardPreview(){
    AnimatedBorderCard (
        modifier = Modifier.fillMaxWidth().height(200.dp),
        shape =  RoundedCornerShape(30.dp),
        borderWidth = 1.dp,
        gradient = Brush.sweepGradient(
            listOf(
                Red700,
                Red200,
            )
        ),
        animationDuration = 10000
    ){

    }
}
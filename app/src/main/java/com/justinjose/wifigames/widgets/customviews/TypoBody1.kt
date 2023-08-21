package com.justinjose.wifigames.widgets.customviews

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.justinjose.wifigames.ui.theme.Montserrat

@Composable
fun TypoBody1(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.secondary
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = 16.sp,
        fontFamily = Montserrat,
        fontWeight = FontWeight.Light,
        textAlign = TextAlign.Center,
    )
}
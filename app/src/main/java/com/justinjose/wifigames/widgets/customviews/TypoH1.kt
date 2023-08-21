package com.justinjose.wifigames.widgets.customviews

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.justinjose.wifigames.ui.theme.Montserrat

@Composable
fun TypoH1(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.secondary,
    fontSize: TextUnit = 36.sp
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize,
        fontFamily = Montserrat,
        fontWeight = FontWeight.ExtraBold,
    )
}
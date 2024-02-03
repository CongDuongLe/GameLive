package com.cdle.gamelive.presentation.components

import ExpandableText
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp


@Composable
fun DisplayHtmlText(
    htmlText: String
) {

    var showMore by remember {
        mutableStateOf(false)
    }

    val annotatedString = buildAnnotatedString {
        // when got /n add the newline in text
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
            )
        ) {
            append(htmlText)
        }

    }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
       ExpandableText(text = annotatedString.toString(), fontSize = 16.sp )
    }
}


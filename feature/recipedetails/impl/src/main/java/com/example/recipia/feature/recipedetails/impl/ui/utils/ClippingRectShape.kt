package com.example.recipia.feature.recipedetails.impl.ui.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class ClippingRectShape(private val fillWidthFraction: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val clippingRect = Rect(Offset.Zero, Size(size.width * fillWidthFraction, size.height))
        return Outline.Rectangle(clippingRect)
    }
}
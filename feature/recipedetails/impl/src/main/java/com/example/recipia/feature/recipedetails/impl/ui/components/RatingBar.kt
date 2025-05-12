package com.example.recipia.feature.recipedetails.impl.ui.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.recipia.feature.recipedetails.impl.ui.utils.ClippingRectShape
import com.example.recipia.feature.recipedetails.impl.ui.utils.RatingBarDefaults
import kotlin.math.round

@Composable
fun RatingBar(
    rating: Float,
    starSize: Dp,
    onRatingChanged: (newRating: Float) -> Unit,
    modifier: Modifier = Modifier,
    @FloatRange(0.0, 1.0)
    ratingStep: Float = 0.5f,
    starsCount: Int = 5,
    starSpacing: Dp = 0.dp,
    unratedContent: @Composable BoxScope.(starIndex: Int) -> Unit = {
        RatingBarDefaults.UnratedContent()
    },
    ratedContent: @Composable BoxScope.(starIndex: Int) -> Unit = {
        RatingBarDefaults.RatedContent()
    },
    enableDragging: Boolean = true,
    enableTapping: Boolean = true
) {
    val bounds = remember { mutableMapOf<Int, Rect>() }

    Row(
        horizontalArrangement = Arrangement.spacedBy(starSpacing),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.then(
            if (enableDragging) {
                Modifier.pointerInput(Unit) {
                    detectHorizontalDragGestures { change, _ ->
                        val (index, rect) = bounds.entries.find { (_, rect) ->
                            rect.contains(Offset(change.position.x, 0f))
                        } ?: return@detectHorizontalDragGestures
                        val baseRating = (index - 1)
                        val normalizedX = (change.position.x - rect.left)
                        val fractionalRating = (normalizedX / rect.width).coerceIn(0f, 1f)
                        val roundedRating = when (ratingStep) {
                            1f -> round(fractionalRating)
                            0f -> fractionalRating
                            else -> roundToStep(fractionalRating, ratingStep)
                        }
                        onRatingChanged(baseRating + roundedRating)
                    }
                }
            } else {
                modifier
            }
        )
    ) {
        for (index in 1..starsCount) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(starSize)
                    .onGloballyPositioned { layoutCoordinates ->
                        bounds[index] = layoutCoordinates.boundsInParent()
                    }
                    .then(
                        if (enableTapping) {
                            Modifier.pointerInput(Unit) {
                                detectTapGestures {
                                    onRatingChanged(index.toFloat())
                                }
                            }
                        } else {
                            Modifier
                        }
                    )
            ) {
                unratedContent(index)

                val fillWidthFraction = when {
                    (rating >= index) -> 1f
                    (rating > index - 1) && (rating <= index) -> rating - (index - 1)
                    else -> 0f
                }

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(ClippingRectShape(fillWidthFraction)),
                    contentAlignment = Alignment.Center,
                    content = {
                        ratedContent(index)
                    }
                )
            }
        }
    }
}

private fun roundToStep(value: Float, step: Float): Float {
    return round(value / step) * step
}
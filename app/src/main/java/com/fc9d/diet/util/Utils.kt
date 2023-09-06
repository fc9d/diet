package com.fc9d.diet.util

import java.math.RoundingMode
import java.text.DecimalFormat

fun formatDoubleToOneDecimalPlaceWithoutRounding(value: Double): String {
    val decimalFormat = DecimalFormat("#.#")
    decimalFormat.roundingMode = RoundingMode.DOWN
    return decimalFormat.format(value)
}

fun Double.withRound(): String {
    val decimalFormat = DecimalFormat("#.#")
    decimalFormat.roundingMode = RoundingMode.DOWN
    return decimalFormat.format(this)
}

fun hasOneDecimalPlace(input: String): Boolean {
    val regex = """^\d{1,3}(\.\d{0,1})?$""".toRegex()
    return regex.matches(input)
}
package app.raybritton.roman

import java.lang.IllegalArgumentException

/**
 * Converts a list of RomanNumerals to a string
 *
 * So [V, I, I] becomes "VII"
 */
fun List<RomanNumeral>.toNumeralString(): String {
    return joinToString("") { it.name }
}

/**
 * Coverts a list of RomanNumerals to an integer
 *
 * So [V, I, I] becomes 7
 */
fun List<RomanNumeral>.toInt(): Int {
    if (isEmpty()) return 0
    if (size == 1) return this[0].decimalValue

    val iterator = iterator()
    var total = 0
    var previousNumeral: RomanNumeral = iterator.next()
    var runningTotal = previousNumeral.decimalValue
    while (iterator.hasNext()) {
        val numeral = iterator.next()
        if (numeral > previousNumeral) {
            total += (numeral.decimalValue - runningTotal)
            runningTotal = 0
        } else if (numeral < previousNumeral) {
            total += runningTotal
            runningTotal = numeral.decimalValue
        } else {
            runningTotal += numeral.decimalValue
        }

        previousNumeral = numeral
    }
    return total + runningTotal
}

/**
 * Converts an integer to a list of RomanNumerals
 *
 * Ideally this will be the most simple representation
 *
 * 7 becomes [V, I, I]
 */
fun Int.toNumeralList(): List<RomanNumeral> {
    val numerals = mutableListOf<RomanNumeral>()
    val digits = this.toString()
    digits.forEachIndexed { idx, char ->
        val value = char.toString().toInt()
        val magnitude = (digits.length - idx) - 1
        when (magnitude) {
            0 -> numerals.addAll(convertDigitToNumerals(value, RomanNumeral.I, RomanNumeral.V, RomanNumeral.X))
            1 -> numerals.addAll(convertDigitToNumerals(value, RomanNumeral.X, RomanNumeral.L, RomanNumeral.C))
            2 -> numerals.addAll(convertDigitToNumerals(value, RomanNumeral.C, RomanNumeral.D, RomanNumeral.M))
            3 -> (0 until value).forEach { _ -> numerals.add(RomanNumeral.M) }
            else -> {
                val total = value * ((magnitude - 3) * 10)
                (0 until total).forEach { _ -> numerals.add(RomanNumeral.M) }
            }
        }
    }
    return numerals
}

private fun convertDigitToNumerals(value: Int, oneNumeral: RomanNumeral, fiveNumeral: RomanNumeral, tenNumeral: RomanNumeral): List<RomanNumeral> {
    return when (value) {
        in 0..3 -> List(value) { oneNumeral }
        4 -> listOf(oneNumeral, fiveNumeral)
        5 -> listOf(fiveNumeral)
        in 6..8 -> {
            val result = MutableList(value - 5) { oneNumeral }
            result.add(0, fiveNumeral)
            result
        }
        9 -> listOf(oneNumeral, tenNumeral)
        else -> throw IllegalArgumentException("value ($value) must be in 0..9")
    }
}
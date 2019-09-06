package app.raybritton.roman

import java.lang.IllegalArgumentException

/**
 * Represents Roman numerals up to 1000, each numeral has its decimal value as well
 */
enum class RomanNumeral(val decimalValue: Int) {
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);

    companion object {
        /**
         * @return RomanNumeral that matches the input
         *
         * @throws IllegalArgumentException if the input does not have a matching RomanNumeral
         */
        fun toNumeral(letter: Char): RomanNumeral {
            val possibleNumeral = letter.toUpperCase().toString()
            return values().firstOrNull { it.name == possibleNumeral }
                    ?: throw IllegalArgumentException("$letter is not a valid Roman numeral, only I, V, X, L, C, D and M are allowed")
        }

        /**
         * Convert a string into a list of RomanNumerals
         *
         * @return List of RomanNumerals, one for each letter in the input
         *
         * @throws IllegalArgumentException if the input has any invalid characters
         *
         */
        fun toNumerals(data: String): List<RomanNumeral> {
            return data.map { toNumeral(it) }
        }

        /**
         * Check if string is a valid roman numeral
         */
        fun isValid(input: String): Boolean {
            val numerals = values().map { it.name[0] }
            return input.all { numerals.contains(it) }
        }
    }
}
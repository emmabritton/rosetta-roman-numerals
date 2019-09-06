package app.raybritton.roman

fun main() {
    println("Kotlin Roman Numeral converter")
    println()
    println("1) Numeral to integer")
    println("2) Integer to numeral")
    println()
    println("0) Exit")
    println()

    while (true) {
        when (readLine()) {
            "0" -> return
            "1" -> {
                convertNumeral()
                return
            }
            "2" -> {
                convertInt()
                return
            }
            else -> println("try again")
        }
    }
}

private fun convertNumeral() {
    println("Enter Roman Numeral")
    val numeral = readLine()?.trim()
    if (numeral != null && RomanNumeral.isValid(numeral)) {
        val value = RomanNumeral.toNumerals(numeral).toInt()
        println()
        println("Value: $value")
    } else {
        println("Invalid input")
    }
}

private fun convertInt() {
    println("Enter integer")
    val number = readLine()?.trim()
    if (number?.all { it.isDigit() } == true) {
        val numerals = (number.toInt()).toNumeralList().toNumeralString()
        println()
        println("Numerals: $numerals")
    } else {
        println("Invalid input")
    }
}
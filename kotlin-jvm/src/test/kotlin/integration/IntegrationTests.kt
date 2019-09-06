package integration

import app.raybritton.roman.RomanNumeral
import app.raybritton.roman.toInt
import app.raybritton.roman.toNumeralList
import app.raybritton.roman.toNumeralString
import junit.framework.TestCase.assertEquals
import org.junit.Test

class IntegrationTests {
    @Test
    fun `test full conversions`() {
        testFullConversion(8, "VIII", listOf(RomanNumeral.V, RomanNumeral.I, RomanNumeral.I, RomanNumeral.I))
        testFullConversion(14, "XIV", listOf(RomanNumeral.X, RomanNumeral.I, RomanNumeral.V))
        testFullConversion(49, "XLIX", listOf(RomanNumeral.X, RomanNumeral.L, RomanNumeral.I, RomanNumeral.X))
        testFullConversion(534, "DXXXIV", listOf(RomanNumeral.D, RomanNumeral.X, RomanNumeral.X, RomanNumeral.X, RomanNumeral.I, RomanNumeral.V))
        testFullConversion(1987, "MCMLXXXVII", listOf(RomanNumeral.M, RomanNumeral.C, RomanNumeral.M, RomanNumeral.L, RomanNumeral.X, RomanNumeral.X, RomanNumeral.X, RomanNumeral.V, RomanNumeral.I, RomanNumeral.I))
    }

    private fun testFullConversion(originalNumber: Int, expectedString: String, expectedList: List<RomanNumeral>) {
        val numeralList = originalNumber.toNumeralList()
        val numeralString = numeralList.toNumeralString()
        val convertedList = RomanNumeral.toNumerals(numeralString)
        val convertedNumber = convertedList.toInt()
        val convertedString = convertedList.toNumeralString()
        assertEquals(expectedList, numeralList)
        assertEquals(expectedString, numeralString)
        assertEquals(expectedList, convertedList)
        assertEquals(originalNumber, convertedNumber)
        assertEquals(expectedString, convertedString)
    }

    @Test
    fun `test alternative or unused forms`() {
        assertEquals(4, RomanNumeral.toNumerals("IIII").toInt())
        assertEquals(18, RomanNumeral.toNumerals("XIIX").toInt())
        assertEquals(18, RomanNumeral.toNumerals("IIXX").toInt())
        assertEquals(98, RomanNumeral.toNumerals("IIC").toInt())
        assertEquals(99, RomanNumeral.toNumerals("IC").toInt())
        assertEquals(1903, RomanNumeral.toNumerals("MDCDIII").toInt())
        assertEquals(1910, RomanNumeral.toNumerals("MDCCCCX").toInt())
    }
}
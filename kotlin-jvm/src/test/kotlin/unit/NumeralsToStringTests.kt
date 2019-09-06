package unit

import app.raybritton.roman.RomanNumeral
import app.raybritton.roman.toNumeralString
import junit.framework.TestCase.assertEquals
import org.junit.Test

class NumeralsToStringTests {
    @Test
    fun `test individual numerals are correctly converted`() {
        assertEquals("I", listOf(RomanNumeral.I).toNumeralString())
        assertEquals("V", listOf(RomanNumeral.V).toNumeralString())
        assertEquals("X", listOf(RomanNumeral.X).toNumeralString())
        assertEquals("L", listOf(RomanNumeral.L).toNumeralString())
        assertEquals("C", listOf(RomanNumeral.C).toNumeralString())
        assertEquals("D", listOf(RomanNumeral.D).toNumeralString())
        assertEquals("M", listOf(RomanNumeral.M).toNumeralString())
    }

    @Test
    fun `test groups of numerals are correctly converted`() {
        assertEquals("VII", listOf(RomanNumeral.V, RomanNumeral.I, RomanNumeral.I).toNumeralString())
        assertEquals("XL", listOf(RomanNumeral.X, RomanNumeral.L).toNumeralString())
        assertEquals("ILDM", listOf(RomanNumeral.I, RomanNumeral.L, RomanNumeral.D, RomanNumeral.M).toNumeralString())
    }
}
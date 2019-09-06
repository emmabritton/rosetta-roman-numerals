package unit

import app.raybritton.roman.RomanNumeral
import junit.framework.TestCase.assertEquals
import org.junit.Test

class RomanNumeralTests {
    @Test
    fun `test that all valid letters become the correct numeral`() {
        assertEquals(RomanNumeral.I, RomanNumeral.toNumeral('i'))
        assertEquals(RomanNumeral.I, RomanNumeral.toNumeral('I'))
        assertEquals(RomanNumeral.V, RomanNumeral.toNumeral('v'))
        assertEquals(RomanNumeral.V, RomanNumeral.toNumeral('V'))
        assertEquals(RomanNumeral.X, RomanNumeral.toNumeral('x'))
        assertEquals(RomanNumeral.X, RomanNumeral.toNumeral('X'))
        assertEquals(RomanNumeral.L, RomanNumeral.toNumeral('l'))
        assertEquals(RomanNumeral.L, RomanNumeral.toNumeral('L'))
        assertEquals(RomanNumeral.C, RomanNumeral.toNumeral('c'))
        assertEquals(RomanNumeral.C, RomanNumeral.toNumeral('C'))
        assertEquals(RomanNumeral.D, RomanNumeral.toNumeral('d'))
        assertEquals(RomanNumeral.D, RomanNumeral.toNumeral('D'))
        assertEquals(RomanNumeral.M, RomanNumeral.toNumeral('m'))
        assertEquals(RomanNumeral.M, RomanNumeral.toNumeral('M'))
    }

    @Test
    fun `test numerals have the correct decimal value`() {
        assertEquals(RomanNumeral.I.decimalValue, 1)
        assertEquals(RomanNumeral.V.decimalValue, 5)
        assertEquals(RomanNumeral.X.decimalValue, 10)
        assertEquals(RomanNumeral.L.decimalValue, 50)
        assertEquals(RomanNumeral.C.decimalValue, 100)
        assertEquals(RomanNumeral.D.decimalValue, 500)
        assertEquals(RomanNumeral.M.decimalValue, 1000)
    }

    @Test
    fun `test that more than one character can be correctly converter, ignoring case`() {
        assertEquals(listOf(RomanNumeral.I, RomanNumeral.I), RomanNumeral.toNumerals("II"))
        assertEquals(listOf(RomanNumeral.I, RomanNumeral.I), RomanNumeral.toNumerals("ii"))
        assertEquals(listOf(RomanNumeral.I, RomanNumeral.I), RomanNumeral.toNumerals("iI"))

        assertEquals(listOf(RomanNumeral.D, RomanNumeral.I), RomanNumeral.toNumerals("DI"))
        assertEquals(listOf(RomanNumeral.D, RomanNumeral.I), RomanNumeral.toNumerals("Di"))
        assertEquals(listOf(RomanNumeral.D, RomanNumeral.I), RomanNumeral.toNumerals("dI"))

        assertEquals(listOf(RomanNumeral.V, RomanNumeral.C), RomanNumeral.toNumerals("VC"))
        assertEquals(listOf(RomanNumeral.V, RomanNumeral.C), RomanNumeral.toNumerals("vc"))
        assertEquals(listOf(RomanNumeral.V, RomanNumeral.C), RomanNumeral.toNumerals("vC"))
    }

    @Test
    fun `test that a long number can be converted`() {
        assertEquals(listOf(RomanNumeral.M, RomanNumeral.C, RomanNumeral.M, RomanNumeral.L, RomanNumeral.X, RomanNumeral.X, RomanNumeral.V, RomanNumeral.I), RomanNumeral.toNumerals("MCMLXXVI"))
    }
}
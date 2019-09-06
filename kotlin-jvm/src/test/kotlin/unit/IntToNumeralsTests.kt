package unit

import app.raybritton.roman.RomanNumeral
import app.raybritton.roman.toNumeralList
import junit.framework.TestCase.assertEquals
import org.junit.Test

class IntToNumeralsTests {
    @Test
    fun `test that individual numbers are correctly converted`() {
        assertEquals(listOf(RomanNumeral.I), 1.toNumeralList())
        assertEquals(listOf(RomanNumeral.V), 5.toNumeralList())
        assertEquals(listOf(RomanNumeral.X), 10.toNumeralList())
        assertEquals(listOf(RomanNumeral.L), 50.toNumeralList())
        assertEquals(listOf(RomanNumeral.C), 100.toNumeralList())
        assertEquals(listOf(RomanNumeral.D), 500.toNumeralList())
        assertEquals(listOf(RomanNumeral.M), 1000.toNumeralList())
        assertEquals(listOf(RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M), 5000.toNumeralList())
    }

    @Test
    fun `test numbers where the numerals only go down`() {
        assertEquals(listOf(RomanNumeral.V, RomanNumeral.I), 6.toNumeralList())
        assertEquals(listOf(RomanNumeral.X, RomanNumeral.I), 11.toNumeralList())
        assertEquals(listOf(RomanNumeral.I, RomanNumeral.I), 2.toNumeralList())
        assertEquals(listOf(RomanNumeral.D, RomanNumeral.C, RomanNumeral.C, RomanNumeral.C, RomanNumeral.X, RomanNumeral.L, RomanNumeral.I, RomanNumeral.X), 849.toNumeralList())
    }

    @Test
    fun `test numbers where the numerals only go up`() {
        assertEquals(listOf(RomanNumeral.I, RomanNumeral.V), 4.toNumeralList())
        assertEquals(listOf(RomanNumeral.X, RomanNumeral.X), 20.toNumeralList())
        assertEquals(listOf(RomanNumeral.I, RomanNumeral.X), 9.toNumeralList())
        assertEquals(listOf(RomanNumeral.D, RomanNumeral.C, RomanNumeral.C, RomanNumeral.C, RomanNumeral.L, RomanNumeral.I), 851.toNumeralList())
        assertEquals(listOf(RomanNumeral.C, RomanNumeral.M), 900.toNumeralList())
    }

    @Test
    fun `test numbers where the numbers go up and down`() {
        assertEquals(listOf(RomanNumeral.M, RomanNumeral.C, RomanNumeral.M), 1900.toNumeralList())
        assertEquals(listOf(RomanNumeral.X, RomanNumeral.V, RomanNumeral.I, RomanNumeral.I, RomanNumeral.I), 18.toNumeralList())
        assertEquals(listOf(RomanNumeral.M, RomanNumeral.C, RomanNumeral.M, RomanNumeral.X, RomanNumeral.X, RomanNumeral.I, RomanNumeral.V), 1924.toNumeralList())
        assertEquals(listOf(RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.D, RomanNumeral.C, RomanNumeral.C, RomanNumeral.C, RomanNumeral.X, RomanNumeral.L, RomanNumeral.V), 13845.toNumeralList())
    }
}
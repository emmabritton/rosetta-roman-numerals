package unit

import app.raybritton.roman.RomanNumeral
import app.raybritton.roman.toInt
import junit.framework.TestCase.assertEquals
import org.junit.Test

class NumeralsToIntTests {
    @Test
    fun `test that individual numerals are correctly converted`() {
        assertEquals(1, listOf(RomanNumeral.I).toInt())
        assertEquals(5, listOf(RomanNumeral.V).toInt())
        assertEquals(10, listOf(RomanNumeral.X).toInt())
        assertEquals(50, listOf(RomanNumeral.L).toInt())
        assertEquals(100, listOf(RomanNumeral.C).toInt())
        assertEquals(500, listOf(RomanNumeral.D).toInt())
        assertEquals(1000, listOf(RomanNumeral.M).toInt())
        assertEquals(5000, listOf(RomanNumeral.M,RomanNumeral.M,RomanNumeral.M,RomanNumeral.M,RomanNumeral.M).toInt())
    }

    @Test
    fun `test numbers where the numerals only go down`() {
        assertEquals(8, listOf(RomanNumeral.V, RomanNumeral.I, RomanNumeral.I, RomanNumeral.I).toInt())
        assertEquals(2, listOf(RomanNumeral.I, RomanNumeral.I).toInt())
        assertEquals(20, listOf(RomanNumeral.X, RomanNumeral.X).toInt())
        assertEquals(16, listOf(RomanNumeral.X, RomanNumeral.V, RomanNumeral.I).toInt())
        assertEquals(1252, listOf(RomanNumeral.M, RomanNumeral.C, RomanNumeral.C, RomanNumeral.L, RomanNumeral.I, RomanNumeral.I).toInt())
    }

    @Test
    fun `test numbers where the numerals only go up`() {
        assertEquals(90, listOf(RomanNumeral.X, RomanNumeral.C).toInt())
        assertEquals(800, listOf(RomanNumeral.C, RomanNumeral.C, RomanNumeral.M).toInt())
        assertEquals(7, listOf(RomanNumeral.I, RomanNumeral.I, RomanNumeral.I, RomanNumeral.X).toInt())
    }

    @Test
    fun `test numbers where the numerals go up and down`() {
        assertEquals(1900, listOf(RomanNumeral.M, RomanNumeral.C, RomanNumeral.M).toInt())
        assertEquals(1954, listOf(RomanNumeral.M, RomanNumeral.C, RomanNumeral.M, RomanNumeral.L, RomanNumeral.I, RomanNumeral.V).toInt())
        assertEquals(82, listOf(RomanNumeral.X, RomanNumeral.X, RomanNumeral.C, RomanNumeral.I, RomanNumeral.I).toInt())
        assertEquals(1928, listOf(RomanNumeral.M, RomanNumeral.C, RomanNumeral.M, RomanNumeral.X, RomanNumeral.X, RomanNumeral.I, RomanNumeral.I, RomanNumeral.X).toInt())
        assertEquals(13845, listOf(RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.M, RomanNumeral.D, RomanNumeral.C, RomanNumeral.C, RomanNumeral.C, RomanNumeral.X, RomanNumeral.L, RomanNumeral.V).toInt())
    }
}
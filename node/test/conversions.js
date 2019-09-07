const numeral = require("../numerals");
const conversion = require("../conversion");
const assert = require("assert");
const mocha = require("mocha");

mocha.suite("numerals to int", function() {
  mocha.test("down", function() {
    assert.strictEqual(8, conversion.convertNumeralsToInt("VIII"));
    assert.strictEqual(1252, conversion.convertNumeralsToInt("MCCLII"));
    assert.strictEqual(849, conversion.convertNumeralsToInt("DCCCXLIX"));
  });

  mocha.test("up", function() {
    assert.strictEqual(90, conversion.convertNumeralsToInt("XC"));
    assert.strictEqual(800, conversion.convertNumeralsToInt("CCM"));
    assert.strictEqual(7, conversion.convertNumeralsToInt("IIIX"));
  });

  mocha.test("up and down", function() {
    assert.strictEqual(1900, conversion.convertNumeralsToInt("MCM"));
    assert.strictEqual(1954, conversion.convertNumeralsToInt("MCMLIV"));
    assert.strictEqual(82, conversion.convertNumeralsToInt("XXCII"));
    assert.strictEqual(1928, conversion.convertNumeralsToInt("MCMXXIIX"));
    assert.strictEqual(13845, conversion.convertNumeralsToInt("MMMMMMMMMMMMMDCCCXLV"));
  });
});

mocha.suite("int to numerals", function() {
  mocha.test("down", function () {
    assert.strictEqual("VIII", conversion.convertIntToNumerals(8));
    assert.strictEqual("IX", conversion.convertIntToNumerals(9));
    assert.strictEqual("MCCLII", conversion.convertIntToNumerals(1252));
    assert.strictEqual("DCCCXLIX", conversion.convertIntToNumerals(849));
  });

  mocha.test("up", function () {
    assert.strictEqual("XC", conversion.convertIntToNumerals(90));
    assert.strictEqual("DCCC", conversion.convertIntToNumerals(800));
    assert.strictEqual("VII", conversion.convertIntToNumerals(7));
  });

  mocha.test("up and down", function () {
    assert.strictEqual("MCM", conversion.convertIntToNumerals(1900));
    assert.strictEqual("MCMLIV", conversion.convertIntToNumerals(1954));
    assert.strictEqual("LXXXII", conversion.convertIntToNumerals(82));
    assert.strictEqual("MCMXXVIII", conversion.convertIntToNumerals(1928));
    assert.strictEqual("MMMMMMMMMMMMMDCCCXLV", conversion.convertIntToNumerals(13845));
  });
});
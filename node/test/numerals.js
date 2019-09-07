const numeral = require("../numerals");
const assert = require("assert");
const mocha = require("mocha");

mocha.suite('isValid', function () {
  mocha.test("single letter", function() {
    assert.ok(numeral.isValid("I"), "I");
    assert.ok(numeral.isValid("V"), "V");
    assert.ok(numeral.isValid("X"), "X");
    assert.ok(numeral.isValid("L"), "L");
    assert.ok(numeral.isValid("C"), "C");
    assert.ok(numeral.isValid("D"), "D");
    assert.ok(numeral.isValid("M"), "M");
  });

  mocha.test("multiple letter", function() {
    assert.ok(numeral.isValid("VI"), "VI");
    assert.ok(numeral.isValid("XV"), "XV");
    assert.ok(numeral.isValid("XL"), "XL");
    assert.ok(numeral.isValid("DXM"), "DXM");
    assert.ok(numeral.isValid("MDCMLIVXIM"), "MDCMLIVXIM");
  });

  mocha.test("invalid", function() {
    assert.ok(!numeral.isValid("a"), "a");
    assert.ok(!numeral.isValid("OP"), "OP");
    assert.ok(!numeral.isValid("34"), "34");
    assert.ok(!numeral.isValid("_"), "_");
  });
});

mocha.suite('numeral enum', function () {
  mocha.test("numeral letters", function () {
    assert.strictEqual(numeral.Numeral.I.letter, "I");
    assert.strictEqual(numeral.Numeral.V.letter, "V");
    assert.strictEqual(numeral.Numeral.X.letter, "X");
    assert.strictEqual(numeral.Numeral.L.letter, "L");
    assert.strictEqual(numeral.Numeral.C.letter, "C");
    assert.strictEqual(numeral.Numeral.D.letter, "D");
    assert.strictEqual(numeral.Numeral.M.letter, "M");
  });

  mocha.test("numeral values", function () {
    assert.strictEqual(numeral.Numeral.I.value, 1);
    assert.strictEqual(numeral.Numeral.V.value, 5);
    assert.strictEqual(numeral.Numeral.X.value, 10);
    assert.strictEqual(numeral.Numeral.L.value, 50);
    assert.strictEqual(numeral.Numeral.C.value, 100);
    assert.strictEqual(numeral.Numeral.D.value, 500);
    assert.strictEqual(numeral.Numeral.M.value, 1000);
  });
});
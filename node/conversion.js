const { Numeral } = require("./numerals");

module.exports.convertNumeralsToInt = function (input) {
  if (input.length == 0) return 0;
  if (input.size == 1) return Numeral[this[0]].value;

  const chars = input.toUpperCase().split("").slice(1);
  var total = 0;
  var previousNumeral = Numeral[input[0]].value;
  var runningTotal = previousNumeral;
  chars.forEach(char => {
    const numeral = Numeral[char].value;
    if (numeral > previousNumeral) {
      total += numeral - runningTotal;
      runningTotal = 0;
    } else if (numeral < previousNumeral) {
      total += runningTotal;
      runningTotal = numeral;
    } else {
      runningTotal += numeral;
    }

    previousNumeral = numeral;
  });

  return total + runningTotal
}

module.exports.convertIntToNumerals = function (input) {
  var numerals = [];
  const digits = input.toString();
  digits.split("").forEach((char, idx) => {
    const value = parseInt(char);
    const magnitude = digits.length - idx - 1;
    switch (magnitude) {
      case 0: 
        numerals = numerals.concat(convertDigitToNumerals(value, Numeral.I.letter, Numeral.V.letter, Numeral.X.letter));
        break;
      case 1: 
        numerals = numerals.concat(convertDigitToNumerals(value, Numeral.X.letter, Numeral.L.letter, Numeral.C.letter));
        break;
      case 2: 
        numerals = numerals.concat(convertDigitToNumerals(value, Numeral.C.letter, Numeral.D.letter, Numeral.M.letter));
        break;
      case 3: 
        for (var i = 0; i < value; i++) {
          numerals.push(Numeral.M.letter);
        }
        break;
      default: 
        const total = value * (magnitude - 3) * 10;
        for (var i = 0; i < total; i++) {
          numerals.push(Numeral.M.letter);
        }
    }
  });
  return numerals.join("");
}

function convertDigitToNumerals(value, oneNumeral, fiveNumeral, tenNumeral) {
  switch (true) {
    case (value < 4): return repeatElement(oneNumeral, value)
    case (value == 4): return [oneNumeral, fiveNumeral]
    case (value == 5): return [fiveNumeral]
    case (value < 9): {
      var numerals = [fiveNumeral];
      for (var i = 0; i < (value - 5); i++) {
        numerals.push(oneNumeral);
      }
      return numerals;
    }
    case (value == 9): return [oneNumeral, tenNumeral]
  }
}

const repeatElement = (element, count) => Array(count).fill(element)
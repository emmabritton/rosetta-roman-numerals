module.exports.Numeral = Numeral = {
  I: {
    value: 1,
    letter: "I"
  },
  V: {
    value: 5,
    letter: "V"
  },
  X: {
    value: 10,
    letter: "X"
  },
  L:  {
    value: 50,
    letter: "L"
  },
  C:  {
    value: 100,
    letter: "C"
  },
  D:  {
    value: 500,
    letter: "D"
  },
  M:  {
    value: 1000,
    letter: "M"
  }
};

module.exports.isValid = function(input) {
  const letters = "IVXLCDM";
  return input.toUpperCase().split("").every((letter) => letters.includes(letter));
}
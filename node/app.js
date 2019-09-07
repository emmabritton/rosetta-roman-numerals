const readline = require("readline-sync");
const numerals = require("./numerals");
const conversion = require("./conversion");

console.log("Node Roman Numeral Converter");
console.log();
console.log("1) Numeral to integer");
console.log("2) Integer to numeral");
console.log();
console.log("0) Exit");
console.log();

while (true) {
  const input = readline.questionInt(">");
  switch (input) {
    case 0:
      return;
    case 1: {
      convertToInteger();
      return;
    }
    case 2: {
      convertToNumeral();
      return;
    }
    default:
      console.log("Try again")
  }
}

function convertToInteger() {
  console.log("Enter Roman Numeral");
  const input = readline.question(">");
  if (numerals.isValid(input)) {
    const value = conversion.convertNumeralsToInt(input);
    console.log(`Value: ${value}`);
  } else {
    console.log("Invalid input");
  }
}

function convertToNumeral() {
  console.log("Enter Roman Numeral");
  const input = readline.questionInt(">");
  if (!isNaN(input) && parseInt(input) == input) {
    const value = parseInt(input);
    const numerals = conversion.convertIntToNumerals(value);
    console.log(`Numerals: ${numerals}`);
  } else {
    console.log("Invalid input");
  }
}
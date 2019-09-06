## Roman Numeral converter specification

The program should start by printing

```
[language] Roman Numeral Converter

1) Numeral to integer
2) Integer to numeral

0) Exit
```

If the user enters anything other than `1`, `2` or `0` the program should print `Try again` and wait for more input.
If the user enters `0` then the program should exit successfully.

If the user enters `1` then the program should print `Enter Roman Numeral` and accept input.
When the user presses enter/return then the program should check that the input is a valid Roman Numeral printing `Invalid input` otherwise and exiting.
If the input is valid the program should convert the input to an integer. See below for more info.
Then the program should print `Value: [integer]`.

 If the user enters `2` then the program should print `Enter integer` and accept input.
When the user presses enter/return then the program should check that the input is a valid integer printing `Invalid input` otherwise and exiting.
If the input is valid the program should convert the input to Roman Numerals. See below for more info.
Then the program should print `Numerals: [numerals]`.

## Roman Numeral values

```
I = 1
V = 5
X = 10
L = 50
C = 100
D = 500
M = 1000
```

Numerals are read left to right, if the numeral to the right of the current one is higher then it is added to a running total, if it is lower then the running total is subtracted from the next numeral and the result becomes the new running total:

```
II = 2
IV = 4
VI = 6
XXL = 30
```

[More info at wikipedia](https://en.wikipedia.org/wiki/Roman_numerals)

## Invalid values

As an example, VV probably isn't a valid numeral so how a program handles it is up to an implementation developers to decide.

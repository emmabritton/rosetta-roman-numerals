extern crate strum;
extern crate strum_macros;

use crate::list_numerals::ToInt;
use crate::list_numerals::ToNumeralList;
use crate::list_numerals::ToNumeralString;
use crate::roman_numerals::RomanNumeral;
use std::io;

mod list_numerals;
mod roman_numerals;

fn main() {
    println!("Rust Roman Numeral converter");
    println!();
    println!("1) Numeral to integer");
    println!("2) Integer to numeral");
    println!();
    println!("0) Exit");
    println!();

    let mut input = String::new();
    loop {
        match io::stdin().read_line(&mut input) {
            Ok(_) => match input.trim() {
                "0" => return,
                "1" => {
                    convert_numeral();
                    return;
                }
                "2" => {
                    convert_int();
                    return;
                }
                _ => {
                    println!("try again");
                }
            },
            Err(err) => {
                eprintln!("{}", err);
            }
        }
    }
}

fn convert_numeral() {
    println!("Enter Roman Numeral");
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();
    let value = RomanNumeral::convert_to_numerals(input.trim()).into_int();
    println!();
    println!("Value: {}", value);
}

fn convert_int() {
    println!("Enter integer");
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();
    let number = input.trim().parse::<u32>().expect("Not a valid number");
    let numerals = number.into_list().into_numeral_string();
    println!();
    println!("Numerals: {}", numerals);
}

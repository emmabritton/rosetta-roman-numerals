use std::str::FromStr;
use strum_macros::{Display, EnumString};

#[derive(EnumString, Display, PartialEq, PartialOrd, Debug, Clone, Copy)]
pub enum RomanNumeral {
    I,
    V,
    X,
    L,
    C,
    D,
    M,
}

impl RomanNumeral {
    pub fn decimal_value(self) -> u32 {
        match self {
            RomanNumeral::I => 1,
            RomanNumeral::V => 5,
            RomanNumeral::X => 10,
            RomanNumeral::L => 50,
            RomanNumeral::C => 100,
            RomanNumeral::D => 500,
            RomanNumeral::M => 1000,
        }
    }

    pub fn convert_to_numeral(letter: char) -> RomanNumeral {
        let possible_numeral = letter.to_ascii_uppercase();

        RomanNumeral::from_str(possible_numeral.to_string().as_str()).expect(&format!("{} is not a valid Roman numeral, only I, V, X, L, C, D and M are allowed", letter))
    }

    pub fn convert_to_numerals(data: &str) -> Vec<RomanNumeral> {
        data.chars().map(RomanNumeral::convert_to_numeral).collect()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_that_all_valid_letters_become_the_correct_numeral() {
        assert_eq!(RomanNumeral::I, RomanNumeral::convert_to_numeral('I'));
        assert_eq!(RomanNumeral::V, RomanNumeral::convert_to_numeral('V'));
        assert_eq!(RomanNumeral::X, RomanNumeral::convert_to_numeral('X'));
        assert_eq!(RomanNumeral::L, RomanNumeral::convert_to_numeral('L'));
        assert_eq!(RomanNumeral::C, RomanNumeral::convert_to_numeral('C'));
        assert_eq!(RomanNumeral::D, RomanNumeral::convert_to_numeral('D'));
        assert_eq!(RomanNumeral::M, RomanNumeral::convert_to_numeral('M'));
    }

    #[test]
    fn test_numerals_have_the_correct_decimal_value() {
        assert_eq!(RomanNumeral::I.decimal_value(), 1);
        assert_eq!(RomanNumeral::V.decimal_value(), 5);
        assert_eq!(RomanNumeral::X.decimal_value(), 10);
        assert_eq!(RomanNumeral::L.decimal_value(), 50);
        assert_eq!(RomanNumeral::C.decimal_value(), 100);
        assert_eq!(RomanNumeral::D.decimal_value(), 500);
        assert_eq!(RomanNumeral::M.decimal_value(), 1000);
    }

    #[test]
    fn test_that_more_than_one_character_can_be_correctly_converter_ignoring_case() {
        assert_eq!(
            vec![RomanNumeral::I, RomanNumeral::I],
            RomanNumeral::convert_to_numerals("II")
        );
        assert_eq!(
            vec![RomanNumeral::I, RomanNumeral::I],
            RomanNumeral::convert_to_numerals("ii")
        );
        assert_eq!(
            vec![RomanNumeral::I, RomanNumeral::I],
            RomanNumeral::convert_to_numerals("Ii")
        );

        assert_eq!(
            vec![RomanNumeral::D, RomanNumeral::I],
            RomanNumeral::convert_to_numerals("DI")
        );
        assert_eq!(
            vec![RomanNumeral::D, RomanNumeral::I],
            RomanNumeral::convert_to_numerals("Di")
        );
        assert_eq!(
            vec![RomanNumeral::D, RomanNumeral::I],
            RomanNumeral::convert_to_numerals("dI")
        );

        assert_eq!(
            vec![RomanNumeral::V, RomanNumeral::C],
            RomanNumeral::convert_to_numerals("VC")
        );
        assert_eq!(
            vec![RomanNumeral::V, RomanNumeral::C],
            RomanNumeral::convert_to_numerals("vc")
        );
        assert_eq!(
            vec![RomanNumeral::V, RomanNumeral::C],
            RomanNumeral::convert_to_numerals("vC")
        );
    }

    #[test]
    fn test_that_a_long_number_can_be_converted() {
        assert_eq!(
            vec![
                RomanNumeral::M,
                RomanNumeral::C,
                RomanNumeral::M,
                RomanNumeral::L,
                RomanNumeral::X,
                RomanNumeral::X,
                RomanNumeral::V,
                RomanNumeral::I
            ],
            RomanNumeral::convert_to_numerals("MCMLXXVI")
        );
    }
}

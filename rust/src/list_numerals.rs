use crate::roman_numerals::*;

pub trait ToNumeralString {
    fn into_numeral_string(self) -> String;
}

pub trait ToInt {
    fn into_int(self) -> u32;
}

pub trait ToNumeralList {
    fn into_list(self) -> Vec<RomanNumeral>;
}

impl ToNumeralString for Vec<RomanNumeral> {
    fn into_numeral_string(self) -> String {
        self.into_iter()
            .map(|numeral| numeral.to_string())
            .collect()
    }
}

impl ToInt for Vec<RomanNumeral> {
    fn into_int(self) -> u32 {
        if self.is_empty() {
            return 0;
        }
        if self.len() == 1 {
            return self.first().unwrap().decimal_value();
        }

        let mut iterator = self.into_iter();
        let mut total = 0;
        let mut previous_numeral = iterator.next().unwrap();
        let mut running_total = previous_numeral.decimal_value();

        for numeral in iterator {
            if numeral > previous_numeral {
                total += numeral.decimal_value() - running_total;
                running_total = 0;
            } else if numeral < previous_numeral {
                total += running_total;
                running_total = numeral.decimal_value();
            } else {
                running_total += numeral.decimal_value();
            }

            previous_numeral = numeral;
        }

        total + running_total
    }
}

impl ToNumeralList for u32 {
    fn into_list(self) -> Vec<RomanNumeral> {
        let mut numerals: Vec<RomanNumeral> = vec![];
        let digits_string = self.to_string();
        let digits = digits_string.chars().enumerate();
        for (idx, digit) in digits {
            let value = digit.to_digit(10).expect("Not a valid number");
            let magnitude = (digits_string.len() - idx) - 1;
            match magnitude {
                0 => numerals.append(&mut convert_digit_to_numerals(
                    value,
                    RomanNumeral::I,
                    RomanNumeral::V,
                    RomanNumeral::X,
                )),
                1 => numerals.append(&mut convert_digit_to_numerals(
                    value,
                    RomanNumeral::X,
                    RomanNumeral::L,
                    RomanNumeral::C,
                )),
                2 => numerals.append(&mut convert_digit_to_numerals(
                    value,
                    RomanNumeral::C,
                    RomanNumeral::D,
                    RomanNumeral::M,
                )),
                3 => (0..value).for_each(|_| numerals.push(RomanNumeral::M)),
                _ => {
                    let total = (value as usize) * ((magnitude - 3) * 10);
                    (0..total).for_each(|_| numerals.push(RomanNumeral::M));
                }
            }
        }

        numerals
    }
}

fn convert_digit_to_numerals(
    value: u32,
    one_numeral: RomanNumeral,
    five_numeral: RomanNumeral,
    ten_number: RomanNumeral,
) -> Vec<RomanNumeral> {
    match value {
        0 | 1 | 2 | 3 => return vec![one_numeral; value as usize],
        4 => return vec![one_numeral, five_numeral],
        5 => return vec![five_numeral],
        6 | 7 | 8 => {
            let mut result = vec![one_numeral; (value - 5) as usize];
            result.insert(0, five_numeral);
            return result;
        }
        9 => return vec![one_numeral, ten_number],
        _ => panic!("value {} must be in 0..9", value),
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn into_int_test_that_individual_numerals_are_correctly_converted() {
        assert_eq!(1, vec![RomanNumeral::I].into_int());
        assert_eq!(5, vec![RomanNumeral::V].into_int());
        assert_eq!(10, vec![RomanNumeral::X].into_int());
        assert_eq!(50, vec![RomanNumeral::L].into_int());
        assert_eq!(100, vec![RomanNumeral::C].into_int());
        assert_eq!(500, vec![RomanNumeral::D].into_int());
        assert_eq!(1000, vec![RomanNumeral::M].into_int());
    }

    #[test]
    fn into_int_test_numbers_where_the_numerals_only_go_down() {
        assert_eq!(
            8,
            vec![
                RomanNumeral::V,
                RomanNumeral::I,
                RomanNumeral::I,
                RomanNumeral::I
            ]
            .into_int()
        );
        assert_eq!(2, vec![RomanNumeral::I, RomanNumeral::I].into_int());
        assert_eq!(20, vec![RomanNumeral::X, RomanNumeral::X].into_int());
        assert_eq!(
            16,
            vec![RomanNumeral::X, RomanNumeral::V, RomanNumeral::I].into_int()
        );
        assert_eq!(
            1252,
            vec![
                RomanNumeral::M,
                RomanNumeral::C,
                RomanNumeral::C,
                RomanNumeral::L,
                RomanNumeral::I,
                RomanNumeral::I
            ]
            .into_int()
        );
    }

    #[test]
    fn into_int_test_numbers_where_the_numerals_only_go_up() {
        assert_eq!(90, vec![RomanNumeral::X, RomanNumeral::C].into_int());
        assert_eq!(
            800,
            vec![RomanNumeral::C, RomanNumeral::C, RomanNumeral::M].into_int()
        );
        assert_eq!(
            7,
            vec![
                RomanNumeral::I,
                RomanNumeral::I,
                RomanNumeral::I,
                RomanNumeral::X
            ]
            .into_int()
        );
    }

    #[test]
    fn into_int_test_numbers_where_the_numerals_go_up_and_down() {
        assert_eq!(
            1900,
            vec![RomanNumeral::M, RomanNumeral::C, RomanNumeral::M].into_int()
        );
        assert_eq!(
            1954,
            vec![
                RomanNumeral::M,
                RomanNumeral::C,
                RomanNumeral::M,
                RomanNumeral::L,
                RomanNumeral::I,
                RomanNumeral::V
            ]
            .into_int()
        );
        assert_eq!(
            82,
            vec![
                RomanNumeral::X,
                RomanNumeral::X,
                RomanNumeral::C,
                RomanNumeral::I,
                RomanNumeral::I
            ]
            .into_int()
        );
        assert_eq!(
            1928,
            vec![
                RomanNumeral::M,
                RomanNumeral::C,
                RomanNumeral::M,
                RomanNumeral::X,
                RomanNumeral::X,
                RomanNumeral::I,
                RomanNumeral::I,
                RomanNumeral::X
            ]
            .into_int()
        );
        assert_eq!(
            13845,
            vec![
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::D,
                RomanNumeral::C,
                RomanNumeral::C,
                RomanNumeral::C,
                RomanNumeral::X,
                RomanNumeral::L,
                RomanNumeral::V
            ]
            .into_int()
        );
    }

    #[test]
    fn into_list_test_that_individual_numerals_are_correctly_converted() {
        assert_eq!(vec![RomanNumeral::I], 1.into_list());
        assert_eq!(vec![RomanNumeral::V], 5.into_list());
        assert_eq!(vec![RomanNumeral::X], 10.into_list());
        assert_eq!(vec![RomanNumeral::L], 50.into_list());
        assert_eq!(vec![RomanNumeral::C], 100.into_list());
        assert_eq!(vec![RomanNumeral::D], 500.into_list());
        assert_eq!(vec![RomanNumeral::M], 1000.into_list());
        assert_eq!(
            vec![
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M
            ],
            5000.into_list()
        );
    }

    #[test]
    fn into_list_test_numbers_where_the_numerals_only_go_down() {
        assert_eq!(vec![RomanNumeral::V, RomanNumeral::I], 6.into_list());
        assert_eq!(vec![RomanNumeral::X, RomanNumeral::I], 11.into_list());
        assert_eq!(vec![RomanNumeral::I, RomanNumeral::I], 2.into_list());
        assert_eq!(
            vec![
                RomanNumeral::D,
                RomanNumeral::C,
                RomanNumeral::C,
                RomanNumeral::C,
                RomanNumeral::X,
                RomanNumeral::L,
                RomanNumeral::I,
                RomanNumeral::X
            ],
            849.into_list()
        );
    }

    #[test]
    fn into_list_test_numbers_where_the_numerals_only_go_up() {
        assert_eq!(vec![RomanNumeral::I, RomanNumeral::V], 4.into_list());
        assert_eq!(vec![RomanNumeral::X, RomanNumeral::X], 20.into_list());
        assert_eq!(vec![RomanNumeral::I, RomanNumeral::X], 9.into_list());
        assert_eq!(
            vec![
                RomanNumeral::D,
                RomanNumeral::C,
                RomanNumeral::C,
                RomanNumeral::C,
                RomanNumeral::L,
                RomanNumeral::I
            ],
            851.into_list()
        );
        assert_eq!(vec![RomanNumeral::C, RomanNumeral::M], 900.into_list());
    }

    #[test]
    fn into_list_test_numbers_where_the_numerals_go_up_and_down() {
        assert_eq!(
            vec![RomanNumeral::M, RomanNumeral::C, RomanNumeral::M],
            1900.into_list()
        );
        assert_eq!(
            vec![
                RomanNumeral::X,
                RomanNumeral::V,
                RomanNumeral::I,
                RomanNumeral::I,
                RomanNumeral::I
            ],
            18.into_list()
        );
        assert_eq!(
            vec![
                RomanNumeral::M,
                RomanNumeral::C,
                RomanNumeral::M,
                RomanNumeral::X,
                RomanNumeral::X,
                RomanNumeral::I,
                RomanNumeral::V
            ],
            1924.into_list()
        );
        assert_eq!(
            vec![
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::M,
                RomanNumeral::D,
                RomanNumeral::C,
                RomanNumeral::C,
                RomanNumeral::C,
                RomanNumeral::X,
                RomanNumeral::L,
                RomanNumeral::V
            ],
            13845.into_list()
        );
    }

    #[test]
    fn into_str_test_individuals_numbers_are_correctly_converted() {
        assert_eq!("I".to_string(), vec![RomanNumeral::I].into_numeral_string());
        assert_eq!("V".to_string(), vec![RomanNumeral::V].into_numeral_string());
        assert_eq!("X".to_string(), vec![RomanNumeral::X].into_numeral_string());
        assert_eq!("L".to_string(), vec![RomanNumeral::L].into_numeral_string());
        assert_eq!("C".to_string(), vec![RomanNumeral::C].into_numeral_string());
        assert_eq!("D".to_string(), vec![RomanNumeral::D].into_numeral_string());
        assert_eq!("M".to_string(), vec![RomanNumeral::M].into_numeral_string());
    }

    #[test]
    fn into_str_test_groups_of_numerals_are_correctly_converted() {
        assert_eq!(
            "VII".to_string(),
            vec![RomanNumeral::V, RomanNumeral::I, RomanNumeral::I].into_numeral_string()
        );
        assert_eq!(
            "XL".to_string(),
            vec![RomanNumeral::X, RomanNumeral::L].into_numeral_string()
        );
        assert_eq!(
            "ILDM".to_string(),
            vec![
                RomanNumeral::I,
                RomanNumeral::L,
                RomanNumeral::D,
                RomanNumeral::M
            ]
            .into_numeral_string()
        );
    }
}

(ns aoc-2021.day10-test
  (:require [clojure.test :refer :all]
            [aoc-2021.day10 :refer :all]))

(deftest test-first-corrupted
  (are [a b] (= (first a) b)
    (first-corrupted "{([(<{}[<>[]}>{[]{[(<()>") \}
    (first-corrupted "[[<[([]))<([[{}[[()]]]") \)
    (first-corrupted "[{[{({}]{}}([{[{{{}}([]") \]
    (first-corrupted "[<(<(<(<{}))><([]([]()") \)
    (first-corrupted "<{([([[(<>()){}]>(<<{{") \>))

(deftest test-score
  (are [a b] (= a b)
    (score "}}]])})]") 288957
    (score ")}>]})") 5566
    (score "}}>}>))))") 1480781
    (score "]]}}]}]}>") 995444
    (score "])}>") 294))

(deftest solve-puzzle
  (let [input (slurp "resources/2021/day10.txt")]
    (is (= (part-1 input) 319329))
    (is (= (part-2 input) 3515583998))))

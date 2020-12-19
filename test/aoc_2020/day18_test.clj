(ns aoc-2020.day18-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day18 :refer :all]
    [clojure.test :refer :all]))

(deftest test-calc
  (is (= (calc "1 + 2 * 3 + 4 * 5 + 6") 71))
  (is (= (calc "2 * 3 + (4 * 5)") 26))
  (is (= (calc "5 + (8 * 3 + 9 + 3 * 4 * 3)") 437))
  (is (= (calc "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))") 12240))
  (is (= (calc "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2") 13632)))

(deftest test-calc-advanced
  (is (= (calc-advanced "1 + 2 * 3 + 4 * 5 + 6") 231))
  (is (= (calc-advanced "1 + (2 * 3) + (4 * (5 + 6))") 51))
  (is (= (calc-advanced "2 * 3 + (4 * 5)") 46))
  (is (= (calc-advanced "5 + (8 * 3 + 9 + 3 * 4 * 3)") 1445))
  (is (= (calc-advanced "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))") 669060))
  (is (= (calc-advanced "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2") 23340)))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day18.txt")]
    (is (= (part-1 input) 5374004645253))
    (is (= (part-2 input) 88782789402798))))

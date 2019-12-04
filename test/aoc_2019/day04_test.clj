(ns aoc-2019.day04-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.utils :refer :all]
    [aoc-2019.day04 :refer :all]))

(deftest run
  (is (= (password? 111111) true))
  (is (= (password? 122345) true))
  (is (= (password? 223450) false))
  (is (= (password? 123789) false)))

(deftest solve-puzzle
  (let [input (slurp "resources/day04.txt")]
    (is (= (part-1 input) 1864))))
;    (is (= (part-2 input) 7912))))

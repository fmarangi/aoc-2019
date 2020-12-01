(ns aoc-2020.day01-test
  (:require
    [clojure.test :refer :all]
    [aoc-2020.day01 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2020/day01.txt")]
    (is (= (part-1 input) 731731))
    (is (= (part-2 input) 116115990))))

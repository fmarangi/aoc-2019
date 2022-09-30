(ns aoc-2021.day01-test
  (:require [clojure.test :refer :all]
            [aoc-2021.day01 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2021/day01.txt")]
    (is (= (part-1 input) 1681))
    (is (= (part-2 input) 1704))))

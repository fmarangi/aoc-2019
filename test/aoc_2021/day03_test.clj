(ns aoc-2021.day03-test
  (:require [clojure.test :refer :all]
            [aoc-2021.day03 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2021/day03.txt")]
    (is (= (part-1 input) 2003336))
    (is (= (part-2 input) 1877139))))

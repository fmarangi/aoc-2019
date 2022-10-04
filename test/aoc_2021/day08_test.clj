(ns aoc-2021.day08-test
  (:require [clojure.test :refer :all]
            [aoc-2021.day08 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2021/day08.txt")]
    (is (= (part-1 input) 534))
    (is (= (part-2 input) 1070188))))

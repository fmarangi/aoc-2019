(ns aoc-2017.day12-test
  (:require [clojure.test :refer :all]
            [aoc-2017.day12 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2017/day12.txt")]
    (is (= (part-1 input) 283))
    (is (= (part-2 input) 195))))

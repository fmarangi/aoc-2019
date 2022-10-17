(ns aoc-2017.day11-test
  (:require [clojure.test :refer :all]
            [aoc-2017.day11 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2017/day11.txt")]
    (is (= (part-1 input) 794))
    (is (= (part-2 input) 1524))))

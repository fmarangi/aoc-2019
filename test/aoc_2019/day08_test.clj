(ns aoc-2019.day08-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.day08 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/day08.txt")]
    (is (= (part-1 input) 2318))
    (is (= (part-2 input) "AHFCB"))))

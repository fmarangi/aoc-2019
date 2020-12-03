(ns aoc-2020.day03-test
  (:require
    [clojure.test :refer :all]
    [aoc-2020.day03 :refer :all]))

(deftest calculate-pattern
  (are [actual expected] (= actual expected)
    (count-trees (slurp "resources/2020/examples/day03a.txt") 3 1) 7))

(deftest check-alternative-slopes
  (are [actual expected] (= actual expected)
    (all-slopes (slurp "resources/2020/examples/day03a.txt")) 336))

(deftest solve-puzzle
  (let [input (slurp "resources/2020/day03.txt")]
    (is (= (part-1 input) 280))
    (is (= (part-2 input) 4355551200))))

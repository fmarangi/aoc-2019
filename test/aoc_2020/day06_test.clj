(ns aoc-2020.day06-test
  (:require
    [clojure.test :refer :all]
    [aoc-2020.day06 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2020/day06.txt")]
    (is (= (part-1 input) 7027))
    (is (= (part-2 input) 3579))))

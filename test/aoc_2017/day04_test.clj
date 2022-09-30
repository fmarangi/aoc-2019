(ns aoc-2017.day04-test
  (:require [clojure.test :refer :all]
            [aoc-2017.day04 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2017/day04.txt")]
    (is (= (part-1 input) 455))
    (is (= (part-2 input) 186))))

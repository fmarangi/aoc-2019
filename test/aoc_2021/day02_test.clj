(ns aoc-2021.day02-test
  (:require [clojure.test :refer :all]
            [aoc-2021.day02 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2021/day02.txt")]
    (is (= (part-1 input) 1936494))
    (is (= (part-2 input) 1997106066))))

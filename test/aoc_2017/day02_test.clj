(ns aoc-2017.day02-test
  (:require [clojure.test :refer :all]
            [aoc-2017.day02 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2017/day02.txt")]
    (is (= (part-1 input) 36174))
    (is (= (part-2 input) 244))))

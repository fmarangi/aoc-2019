(ns aoc-2021.day06-test
  (:require [clojure.test :refer :all]
            [aoc-2021.day06 :refer :all]))

(deftest solve-puzzle
  (let [input (slurp "resources/2021/day06.txt")]
    (is (= (part-1 input) 350917))
    (is (= (part-2 input) 1592918715629))))

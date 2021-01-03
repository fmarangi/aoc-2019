(ns aoc-2017.day01-test
  (:require [clojure.test :refer :all]
            [aoc-2017.day01 :refer :all]
            [aoc-2019.utils :refer [puzzle-input]]))

(deftest solve-puzzle
  (let [input (puzzle-input "2017/day01.txt")]
    (is (= (part-1 input) 1341))
    (is (= (part-2 input) 1348))))

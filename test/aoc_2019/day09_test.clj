(ns aoc-2019.day09-test
  (:require
    [clojure.test :refer :all]
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2019.day09 :refer :all]))

(deftest solve-puzzle
  (let [input (puzzle-input "2019/day09.txt")]
    (is (= (part-1 input) 2457252183))
    (is (= (part-2 input) 70634))))

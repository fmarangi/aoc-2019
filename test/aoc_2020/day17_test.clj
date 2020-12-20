(ns aoc-2020.day17-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day17 :refer :all]
    [clojure.test :refer :all]))

(deftest test-calc-state
  ;  (is (= (calc-state-4d ".#.\n..#\n###" 6) 848))
  (is (= (calc-state ".#.\n..#\n###" 6)) 112))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day17.txt")]
    ;  (is (= (part-2 input) 1392))
    (is (= (part-1 input) 247))))

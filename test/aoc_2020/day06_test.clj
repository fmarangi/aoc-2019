(ns aoc-2020.day06-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day06 :refer :all]
    [clojure.test :refer :all]))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day06.txt")]
    (is (= (part-1 input) 7027))
    (is (= (part-2 input) 3579))))

(ns aoc-2020.day16-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2020.day16 :refer :all]
    [clojure.test :refer :all]))

(deftest solve-puzzle
  (let [input (puzzle-input "2020/day16.txt")]
    (is (= (part-1 input) 25895))
    (is (= (part-2 input) 5865723727753))))

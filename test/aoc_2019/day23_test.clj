(ns aoc-2019.day23-test
  (:require [aoc-2019.day23 :refer :all]
            [aoc-2019.utils :refer [puzzle-input]]
            [clojure.test :refer :all]))

(deftest solve-puzzle
  (let [input (puzzle-input "2019/day23.txt")]
    (is (= (part-1 input) 19724))
    (is (= (part-2 input) 15252))))

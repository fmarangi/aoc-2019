(ns aoc-2019.day25-test
  (:require [aoc-2019.day25 :refer :all]
            [aoc-2019.utils :refer [puzzle-input]]
            [clojure.test :refer :all]))

(deftest solve-puzzle
  (let [input (puzzle-input "2019/day25.txt")]
    (is (= (part-1 input) 2622472))))

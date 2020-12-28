(ns aoc-2019.day13-test
  (:require [aoc-2019.day13 :refer :all]
            [aoc-2019.utils :refer [puzzle-input]]
            [clojure.test :refer :all]))

(deftest solve-puzzle
  (let [input (puzzle-input "2019/day13.txt")]
    (is (= (part-1 input) 412))
    (is (= (part-2 input) 20940))))

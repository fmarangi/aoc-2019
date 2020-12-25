(ns aoc-2019.day17-test
  (:require
    [aoc-2019.utils :refer [puzzle-input]]
    [aoc-2019.day17 :refer :all]
    [clojure.test :refer :all]))

(deftest solve-puzzle
  (let [input (puzzle-input "2019/day17.txt")]
    (is (= (part-1 input) 4688))
    (is (= (part-2 input) 714866))))

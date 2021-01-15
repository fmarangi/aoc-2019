(ns aoc-2019.day21-test
  (:require [aoc-2019.day21 :refer :all]
            [aoc-2019.utils :refer [puzzle-input]]
            [clojure.test :refer :all]))

(deftest solve-puzzle
  (let [input (puzzle-input "2019/day21.txt")]
    (is (= (part-1 input) 19357390))
    (is (= (part-2 input) 1142844041))))
